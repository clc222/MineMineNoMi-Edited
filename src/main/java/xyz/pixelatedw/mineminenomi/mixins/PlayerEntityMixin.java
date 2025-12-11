/*     */ package xyz.pixelatedw.mineminenomi.mixins;
/*     */ 
/*     */ import com.google.common.base.Strings;
/*     */ import java.util.Map;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntitySize;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.Pose;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.util.CooldownTracker;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.spongepowered.asm.mixin.Mixin;
/*     */ import org.spongepowered.asm.mixin.Unique;
/*     */ import org.spongepowered.asm.mixin.injection.At;
/*     */ import org.spongepowered.asm.mixin.injection.Constant;
/*     */ import org.spongepowered.asm.mixin.injection.Inject;
/*     */ import org.spongepowered.asm.mixin.injection.ModifyArgs;
/*     */ import org.spongepowered.asm.mixin.injection.ModifyConstant;
/*     */ import org.spongepowered.asm.mixin.injection.ModifyVariable;
/*     */ import org.spongepowered.asm.mixin.injection.Redirect;
/*     */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*     */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*     */ import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
/*     */ import xyz.pixelatedw.mineminenomi.api.ISweep;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.WyLivingAttackEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.WyLivingDamageEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.WyLivingHurtEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AttributeHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.MorphHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ 
/*     */ @Mixin(value = {PlayerEntity.class}, priority = 990)
/*     */ public abstract class PlayerEntityMixin
/*     */   implements ISweep
/*     */ {
/*  44 */   private static final Logger WLOGGER = LogManager.getLogger();
/*     */   
/*     */   private WyLivingAttackEvent livingAttackEvent;
/*     */   
/*     */   @Unique
/*     */   private boolean isSweeping = false;
/*     */ 
/*     */   
/*     */   public boolean isSweeping() {
/*  53 */     return this.isSweeping;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSweeping(boolean isSweeping) {
/*  58 */     this.isSweeping = isSweeping;
/*     */   }
/*     */   
/*     */   @ModifyConstant(method = {"attack(Lnet/minecraft/entity/Entity;)V"}, constant = {@Constant(doubleValue = 9.0D)})
/*     */   private double getActualAttackRange(double attackRange) {
/*  63 */     PlayerEntity player = (PlayerEntity)this;
/*  64 */     return AttributeHelper.getSquaredAttackRangeDistance((LivingEntity)player, attackRange);
/*     */   }
/*     */   
/*     */   @Inject(method = {"getDimensions"}, at = {@At("HEAD")}, cancellable = true)
/*     */   public void getSize(Pose pose, CallbackInfoReturnable<EntitySize> callback) {
/*  69 */     PlayerEntity player = (PlayerEntity)this;
/*  70 */     IDevilFruit props = DevilFruitCapability.get((LivingEntity)player);
/*  71 */     if (!Strings.isNullOrEmpty(props.getZoanPoint())) {
/*  72 */       MorphInfo info = MorphHelper.getZoanInfo((LivingEntity)player);
/*  73 */       if (info == null) {
/*     */         return;
/*     */       }
/*  76 */       Map<Pose, EntitySize> poses = info.getSizes();
/*  77 */       if (poses != null && poses.containsKey(player.func_213283_Z()) && poses.get(player.func_213283_Z()) != null) {
/*  78 */         callback.setReturnValue(poses.get(player.func_213283_Z()));
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   @Redirect(method = {"tick"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/util/CooldownTracker;tick()V"))
/*     */   public void noroTick(CooldownTracker cooldownTracker) {
/*  85 */     PlayerEntity player = (PlayerEntity)this;
/*  86 */     if (player.func_70644_a((Effect)ModEffects.NORO_SLOWNESS.get())) {
/*  87 */       int amplifier = Math.max(5, 5 * player.func_70660_b((Effect)ModEffects.NORO_SLOWNESS.get()).func_76458_c());
/*  88 */       if (player.field_70173_aa % amplifier == 0) {
/*  89 */         cooldownTracker.func_185144_a();
/*     */       }
/*     */     } else {
/*     */       
/*  93 */       cooldownTracker.func_185144_a();
/*     */     } 
/*     */   }
/*     */   
/*     */   @Inject(method = {"attack"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/util/DamageSource;playerAttack(Lnet/minecraft/entity/player/PlayerEntity;)Lnet/minecraft/util/DamageSource;", ordinal = 1, shift = At.Shift.BEFORE)})
/*     */   public void attackWithSweeping(Entity target, CallbackInfo ci) {
/*  99 */     setSweeping(true);
/*     */   }
/*     */   
/*     */   @Inject(method = {"hurt"}, at = {@At("HEAD")}, cancellable = true)
/*     */   public void hurt(DamageSource pSource, float pAmount, CallbackInfoReturnable<Boolean> cir) {
/* 104 */     PlayerEntity entity = (PlayerEntity)this;
/*     */     
/* 106 */     this.livingAttackEvent = new WyLivingAttackEvent((LivingEntity)entity, pSource, pAmount);
/*     */     
/* 108 */     MinecraftForge.EVENT_BUS.post((Event)this.livingAttackEvent);
/*     */     
/* 110 */     if (this.livingAttackEvent.isCanceled()) {
/* 111 */       cir.cancel();
/*     */     }
/*     */   }
/*     */   
/*     */   @ModifyVariable(method = {"hurt"}, at = @At("HEAD"), ordinal = 0)
/*     */   public DamageSource hurtDamageSource(DamageSource pSource) {
/* 117 */     return this.livingAttackEvent.getSource();
/*     */   }
/*     */   
/*     */   @ModifyVariable(method = {"hurt"}, at = @At("HEAD"), ordinal = 0)
/*     */   public float hurtAmount(float pAmount) {
/* 122 */     return this.livingAttackEvent.getAmount();
/*     */   }
/*     */   
/*     */   @ModifyArgs(method = {"actuallyHurt"}, at = @At(value = "INVOKE", target = "Lnet/minecraftforge/common/ForgeHooks;onLivingHurt(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/util/DamageSource;F)F", remap = false))
/*     */   public void actuallyHurtOnLivingHurt(Args args) {
/* 127 */     LivingEntity entity = (LivingEntity)args.get(0);
/*     */     
/* 129 */     DamageSource damageSource = (DamageSource)args.get(1);
/*     */     
/* 131 */     float amount = ((Float)args.get(2)).floatValue();
/*     */     
/* 133 */     WyLivingHurtEvent event = new WyLivingHurtEvent(entity, damageSource, amount);
/*     */     
/* 135 */     MinecraftForge.EVENT_BUS.post((Event)event);
/*     */     
/* 137 */     if (Float.isNaN(event.getAmount())) {
/* 138 */       WLOGGER.warn("NaN value received in hurt event, original value was " + amount);
/*     */       
/*     */       return;
/*     */     } 
/* 142 */     if (!event.isCanceled()) {
/* 143 */       args.set(1, event.getSource());
/* 144 */       args.set(2, Float.valueOf(event.getAmount()));
/*     */     } else {
/* 146 */       args.set(2, Float.valueOf(0.0F));
/*     */     } 
/*     */   }
/*     */   
/*     */   @ModifyArgs(method = {"actuallyHurt"}, at = @At(value = "INVOKE", target = "Lnet/minecraftforge/common/ForgeHooks;onLivingDamage(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/util/DamageSource;F)F", remap = false))
/*     */   public void actuallyHurtOnLivingDamage(Args args) {
/* 152 */     LivingEntity entity = (LivingEntity)args.get(0);
/*     */     
/* 154 */     DamageSource damageSource = (DamageSource)args.get(1);
/*     */     
/* 156 */     float amount = ((Float)args.get(2)).floatValue();
/*     */     
/* 158 */     WyLivingDamageEvent event = new WyLivingDamageEvent(entity, damageSource, amount);
/*     */     
/* 160 */     MinecraftForge.EVENT_BUS.post((Event)event);
/*     */     
/* 162 */     if (Float.isNaN(event.getAmount())) {
/* 163 */       WLOGGER.warn("NaN value received in hurt event, original value was " + amount);
/*     */       
/*     */       return;
/*     */     } 
/* 167 */     if (!event.isCanceled()) {
/* 168 */       args.set(1, event.getSource());
/* 169 */       args.set(2, Float.valueOf(event.getAmount()));
/*     */     } else {
/* 171 */       args.set(2, Float.valueOf(0.0F));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\mixins\PlayerEntityMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */