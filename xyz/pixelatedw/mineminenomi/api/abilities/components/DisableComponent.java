/*     */ package xyz.pixelatedw.mineminenomi.api.abilities.components;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.PriorityEventPool;
/*     */ import xyz.pixelatedw.mineminenomi.config.ClientConfig;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.components.SStartDisablePacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.components.SStopDisablePacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ public class DisableComponent
/*     */   extends AbilityComponent<IAbility> {
/*     */   private boolean isDisabled;
/*     */   private float startDisableTime;
/*  27 */   private final PriorityEventPool<IStartDisableEvent> startDisableEvents = new PriorityEventPool(); private float disableTime; private boolean isInfinite;
/*  28 */   private final PriorityEventPool<IEndDisableEvent> stopDisableEvents = new PriorityEventPool();
/*     */   
/*     */   public DisableComponent(IAbility ability) {
/*  31 */     super(ModAbilityKeys.DISABLE, ability);
/*  32 */     setTickRate(1);
/*     */   }
/*     */ 
/*     */   
/*     */   public void postInit(IAbility ability) {
/*  37 */     ability.getComponent(ModAbilityKeys.SLOT_DECORATION).ifPresent(component -> {
/*     */           component.addPreRenderEvent(1000, ());
/*     */           component.addPostRenderEvent(1000, ());
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DisableComponent addStartEvent(IStartDisableEvent event) {
/*  62 */     this.startDisableEvents.addEvent(event);
/*  63 */     return this;
/*     */   }
/*     */   
/*     */   public DisableComponent addStartEvent(int priority, IStartDisableEvent event) {
/*  67 */     this.startDisableEvents.addEvent(priority, event);
/*  68 */     return this;
/*     */   }
/*     */   
/*     */   public DisableComponent addEndEvent(IEndDisableEvent event) {
/*  72 */     this.stopDisableEvents.addEvent(event);
/*  73 */     return this;
/*     */   }
/*     */   
/*     */   public DisableComponent addEndEvent(int priority, IEndDisableEvent event) {
/*  77 */     this.stopDisableEvents.addEvent(priority, event);
/*  78 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void doTick(LivingEntity entity) {
/*  83 */     getAbility().getComponent(ModAbilityKeys.ALT_MODE).ifPresent(component -> component.setDisabled(isDisabled()));
/*     */ 
/*     */ 
/*     */     
/*  87 */     if (isDisabled()) {
/*  88 */       if (this.disableTime > 0.0F) {
/*  89 */         ModifiableAttributeInstance inst = entity.func_110148_a((Attribute)ModAttributes.TIME_PROGRESSION.get());
/*  90 */         double timeProgression = 1.0D;
/*  91 */         if (inst != null) {
/*  92 */           timeProgression = inst.func_111126_e();
/*     */         }
/*  94 */         this.disableTime = (float)(this.disableTime - 1.0D * timeProgression);
/*     */       }
/*  96 */       else if (this.disableTime <= 0.0F && !this.isInfinite) {
/*  97 */         stopDisable(entity);
/*     */         return;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDisabled() {
/* 105 */     return this.isDisabled;
/*     */   }
/*     */   
/*     */   public float getStartDisableTime() {
/* 109 */     return this.startDisableTime;
/*     */   }
/*     */   
/*     */   public float getDisableTime() {
/* 113 */     return this.disableTime;
/*     */   }
/*     */   
/*     */   public boolean isInfinite() {
/* 117 */     return this.isInfinite;
/*     */   }
/*     */   
/*     */   public void startDisable(LivingEntity entity, float time) {
/* 121 */     ensureIsRegistered();
/* 122 */     if (isDisabled()) {
/*     */       return;
/*     */     }
/* 125 */     if (time < 0.0F) {
/* 126 */       this.isInfinite = true;
/*     */     } else {
/*     */       
/* 129 */       this.isInfinite = false;
/*     */     } 
/* 131 */     this.startDisableTime = time;
/* 132 */     this.disableTime = time;
/* 133 */     this.isDisabled = true;
/* 134 */     this.startDisableEvents.dispatch(event -> event.startDisable(entity, getAbility()));
/* 135 */     if (!entity.field_70170_p.field_72995_K) {
/* 136 */       WyNetwork.sendToAllTrackingAndSelf(new SStartDisablePacket(entity, getAbility(), time), (Entity)entity);
/*     */     }
/*     */   }
/*     */   
/*     */   public void stopDisable(LivingEntity entity) {
/* 141 */     if (!isDisabled()) {
/*     */       return;
/*     */     }
/* 144 */     this.stopDisableEvents.dispatch(event -> event.endDisable(entity, getAbility()));
/*     */     
/* 146 */     this.disableTime = 0.0F;
/* 147 */     this.isDisabled = false;
/*     */     
/* 149 */     getAbility().getComponent(ModAbilityKeys.SLOT_DECORATION).ifPresent(c -> c.resetDecoration());
/*     */ 
/*     */ 
/*     */     
/* 153 */     if (!entity.field_70170_p.field_72995_K) {
/* 154 */       WyNetwork.sendToAllTrackingAndSelf(new SStopDisablePacket(entity, getAbility()), (Entity)entity);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public CompoundNBT save() {
/* 161 */     CompoundNBT nbt = super.save();
/* 162 */     nbt.func_74776_a("disableTime", this.disableTime);
/* 163 */     nbt.func_74776_a("startDisableTime", this.startDisableTime);
/* 164 */     nbt.func_74757_a("isInfinite", this.isInfinite);
/* 165 */     return nbt;
/*     */   }
/*     */ 
/*     */   
/*     */   public void load(CompoundNBT nbt) {
/* 170 */     super.load(nbt);
/* 171 */     this.disableTime = nbt.func_74760_g("disableTime");
/* 172 */     this.startDisableTime = nbt.func_74760_g("startDisableTime");
/* 173 */     this.isInfinite = nbt.func_74767_n("isInfinite");
/* 174 */     if (this.disableTime > 0.0F)
/* 175 */       this.isDisabled = true; 
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface IEndDisableEvent {
/*     */     void endDisable(LivingEntity param1LivingEntity, IAbility param1IAbility);
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface IStartDisableEvent {
/*     */     void startDisable(LivingEntity param1LivingEntity, IAbility param1IAbility);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\components\DisableComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */