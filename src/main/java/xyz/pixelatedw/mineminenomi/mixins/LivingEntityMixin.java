/*     */ package xyz.pixelatedw.mineminenomi.mixins;
/*     */ 
/*     */ import com.google.common.base.Strings;
/*     */ import java.util.Map;
/*     */ import net.minecraft.entity.EntitySize;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.Pose;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.spongepowered.asm.mixin.Mixin;
/*     */ import org.spongepowered.asm.mixin.injection.At;
/*     */ import org.spongepowered.asm.mixin.injection.Inject;
/*     */ import org.spongepowered.asm.mixin.injection.ModifyArgs;
/*     */ import org.spongepowered.asm.mixin.injection.ModifyVariable;
/*     */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*     */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*     */ import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.PotionAfterAddedEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.WyLivingAttackEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.WyLivingDamageEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.WyLivingHurtEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.MorphHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ @Mixin({LivingEntity.class})
/*     */ public class LivingEntityMixin
/*     */ {
/*  35 */   private static final Logger WLOGGER = LogManager.getLogger();
/*     */   
/*     */   private WyLivingAttackEvent livingAttackEvent;
/*     */   
/*     */   @Inject(method = {"hurt"}, at = {@At("HEAD")}, cancellable = true)
/*     */   public void hurt(DamageSource pSource, float pAmount, CallbackInfoReturnable<Boolean> cir) {
/*  41 */     LivingEntity entity = (LivingEntity)this;
/*     */     
/*  43 */     this.livingAttackEvent = new WyLivingAttackEvent(entity, pSource, pAmount);
/*     */     
/*  45 */     if (entity instanceof net.minecraft.entity.player.PlayerEntity) {
/*     */       return;
/*     */     }
/*     */     
/*  49 */     MinecraftForge.EVENT_BUS.post((Event)this.livingAttackEvent);
/*     */     
/*  51 */     if (this.livingAttackEvent.isCanceled()) {
/*  52 */       cir.cancel();
/*     */     }
/*     */   }
/*     */   
/*     */   @ModifyVariable(method = {"hurt"}, at = @At("HEAD"), ordinal = 0)
/*     */   public DamageSource hurtDamageSource(DamageSource pSource) {
/*  58 */     return this.livingAttackEvent.getSource();
/*     */   }
/*     */   
/*     */   @ModifyVariable(method = {"hurt"}, at = @At("HEAD"), ordinal = 0)
/*     */   public float hurtAmount(float pAmount) {
/*  63 */     return this.livingAttackEvent.getAmount();
/*     */   }
/*     */   
/*     */   @ModifyArgs(method = {"actuallyHurt"}, at = @At(value = "INVOKE", target = "Lnet/minecraftforge/common/ForgeHooks;onLivingHurt(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/util/DamageSource;F)F", remap = false))
/*     */   public void actuallyHurtOnLivingHurt(Args args) {
/*  68 */     LivingEntity entity = (LivingEntity)args.get(0);
/*     */     
/*  70 */     DamageSource damageSource = (DamageSource)args.get(1);
/*     */     
/*  72 */     float amount = ((Float)args.get(2)).floatValue();
/*     */     
/*  74 */     WyLivingHurtEvent event = new WyLivingHurtEvent(entity, damageSource, amount);
/*     */     
/*  76 */     MinecraftForge.EVENT_BUS.post((Event)event);
/*     */     
/*  78 */     if (Float.isNaN(event.getAmount())) {
/*  79 */       WLOGGER.warn("NaN value received in hurt event, original value was " + amount);
/*     */       
/*     */       return;
/*     */     } 
/*  83 */     if (!event.isCanceled()) {
/*  84 */       args.set(1, event.getSource());
/*  85 */       args.set(2, Float.valueOf(event.getAmount()));
/*     */     } else {
/*     */       
/*  88 */       args.set(2, Float.valueOf(0.0F));
/*     */     } 
/*     */   }
/*     */   
/*     */   @ModifyArgs(method = {"actuallyHurt"}, at = @At(value = "INVOKE", target = "Lnet/minecraftforge/common/ForgeHooks;onLivingDamage(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/util/DamageSource;F)F", remap = false))
/*     */   public void actuallyHurtOnLivingDamage(Args args) {
/*  94 */     LivingEntity entity = (LivingEntity)args.get(0);
/*     */     
/*  96 */     DamageSource damageSource = (DamageSource)args.get(1);
/*     */     
/*  98 */     float amount = ((Float)args.get(2)).floatValue();
/*     */     
/* 100 */     WyLivingDamageEvent event = new WyLivingDamageEvent(entity, damageSource, amount);
/*     */     
/* 102 */     MinecraftForge.EVENT_BUS.post((Event)event);
/*     */     
/* 104 */     if (Float.isNaN(event.getAmount())) {
/* 105 */       WLOGGER.warn("NaN value received in hurt event, original value was " + amount);
/*     */       
/*     */       return;
/*     */     } 
/* 109 */     if (!event.isCanceled()) {
/* 110 */       args.set(1, event.getSource());
/* 111 */       args.set(2, Float.valueOf(event.getAmount()));
/*     */     } else {
/* 113 */       args.set(2, Float.valueOf(0.0F));
/*     */     } 
/*     */   }
/*     */   
/*     */   @Inject(method = {"dropAllDeathLoot"}, at = {@At("HEAD")}, cancellable = true)
/*     */   public void dropAllDeathLoot(DamageSource pDamageSource, CallbackInfo ci) {
/* 119 */     LivingEntity entity = (LivingEntity)this;
/* 120 */     if (WyHelper.isInChallengeDimension(entity.field_70170_p)) {
/* 121 */       ci.cancel();
/*     */     }
/*     */   }
/*     */   
/*     */   @Inject(method = {"getDimensions"}, at = {@At("HEAD")}, cancellable = true)
/*     */   public void getSize(Pose pose, CallbackInfoReturnable<EntitySize> callback) {
/* 127 */     LivingEntity living = (LivingEntity)this;
/* 128 */     IDevilFruit props = DevilFruitCapability.get(living);
/* 129 */     if (!Strings.isNullOrEmpty(props.getZoanPoint())) {
/* 130 */       MorphInfo info = MorphHelper.getZoanInfo(living);
/* 131 */       if (info == null) {
/*     */         return;
/*     */       }
/* 134 */       Map<Pose, EntitySize> poses = info.getSizes();
/* 135 */       if (poses != null && poses.containsKey(living.func_213283_Z()) && poses.get(living.func_213283_Z()) != null) {
/* 136 */         callback.setReturnValue(poses.get(living.func_213283_Z()));
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   @Inject(method = {"onEffectAdded"}, at = {@At("HEAD")})
/*     */   public void fireAfterEffectAddedEvent(EffectInstance instance, CallbackInfo callback) {
/* 143 */     LivingEntity living = (LivingEntity)this;
/* 144 */     PotionAfterAddedEvent event = new PotionAfterAddedEvent(living, instance);
/* 145 */     MinecraftForge.EVENT_BUS.post((Event)event);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\mixins\LivingEntityMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */