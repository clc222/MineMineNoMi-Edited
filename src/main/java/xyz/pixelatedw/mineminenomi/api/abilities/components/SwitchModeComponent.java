/*     */ package xyz.pixelatedw.mineminenomi.api.abilities.components;
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.math.EasingFunctionHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.PriorityEventPool;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.components.SChangeAbilitySwitchModePacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ public class SwitchModeComponent extends AbilityComponent<IAbility> {
/*  20 */   private final PriorityEventPool<ISwitchStateEvent> switchEvents = new PriorityEventPool();
/*     */   
/*     */   private boolean switchState = true;
/*     */   
/*     */   private boolean playReadyAnim = false;
/*  25 */   private float readyAnim = 0.0F;
/*     */   
/*     */   public SwitchModeComponent(IAbility ability) {
/*  28 */     super(ModAbilityKeys.SWITCH_MODE, ability);
/*     */   }
/*     */   
/*     */   public SwitchModeComponent addSwitchStateEvent(ISwitchStateEvent event) {
/*  32 */     this.switchEvents.addEvent(event);
/*  33 */     return this;
/*     */   }
/*     */   
/*     */   public SwitchModeComponent addSwitchStateEvent(int priority, ISwitchStateEvent event) {
/*  37 */     this.switchEvents.addEvent(priority, event);
/*  38 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public void postInit(IAbility ability) {
/*  43 */     ability.getComponent(ModAbilityKeys.SLOT_DECORATION).ifPresent(component -> component.addPostRenderEvent(100, ()));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void switchState(LivingEntity entity) {
/*  74 */     setSwitchState(entity, !this.switchState);
/*     */   }
/*     */   
/*     */   public void setSwitchState(LivingEntity entity, boolean mode) {
/*  78 */     ensureIsRegistered();
/*  79 */     this.switchState = mode;
/*  80 */     this.playReadyAnim = true;
/*     */     
/*  82 */     this.switchEvents.dispatch(event -> event.switchMode(entity, getAbility()));
/*     */     
/*  84 */     if (entity instanceof ServerPlayerEntity) {
/*  85 */       ServerPlayerEntity player = (ServerPlayerEntity)entity;
/*  86 */       WyNetwork.sendToAllTrackingAndSelf(new SChangeAbilitySwitchModePacket((LivingEntity)player, getAbility(), getSwitchState()), (Entity)player);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean getSwitchState() {
/*  91 */     return this.switchState;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public CompoundNBT save() {
/*  97 */     CompoundNBT nbt = super.save();
/*     */     
/*  99 */     nbt.func_74757_a("currentState", this.switchState);
/*     */     
/* 101 */     return nbt;
/*     */   }
/*     */ 
/*     */   
/*     */   public void load(CompoundNBT nbt) {
/* 106 */     super.load(nbt);
/* 107 */     this.switchState = nbt.func_74767_n("currentState");
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface ISwitchStateEvent {
/*     */     void switchMode(LivingEntity param1LivingEntity, IAbility param1IAbility);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\components\SwitchModeComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */