/*    */ package xyz.pixelatedw.mineminenomi.api.abilities.components;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.util.PriorityEventPool;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.ability.components.SSwingTriggerPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*    */ 
/*    */ public class SwingTriggerComponent extends AbilityComponent<IAbility> {
/* 12 */   private final PriorityEventPool<IOnSwingEvent> swingEvents = new PriorityEventPool();
/*    */   
/*    */   private long lastSwingTime;
/*    */   
/*    */   public SwingTriggerComponent(IAbility ability) {
/* 17 */     super(ModAbilityKeys.SWING_TRIGGER, ability);
/*    */   }
/*    */   
/*    */   public SwingTriggerComponent addSwingEvent(IOnSwingEvent event) {
/* 21 */     this.swingEvents.addEvent(event);
/* 22 */     return this;
/*    */   }
/*    */   
/*    */   public SwingTriggerComponent addSwingEvent(int priority, IOnSwingEvent event) {
/* 26 */     this.swingEvents.addEvent(priority, event);
/* 27 */     return this;
/*    */   }
/*    */   
/*    */   public void swing(LivingEntity entity) {
/* 31 */     ensureIsRegistered();
/* 32 */     if (getAbility().hasComponent(ModAbilityKeys.DISABLE) && ((DisableComponent)getAbility().getComponent(ModAbilityKeys.DISABLE).get()).isDisabled()) {
/*    */       return;
/*    */     }
/*    */     
/* 36 */     if (getAbility().hasComponent(ModAbilityKeys.COOLDOWN) && ((CooldownComponent)getAbility().getComponent(ModAbilityKeys.COOLDOWN).get()).isOnCooldown()) {
/*    */       return;
/*    */     }
/*    */     
/* 40 */     this.swingEvents.dispatch(event -> event.swing(entity, getAbility()));
/*    */     
/* 42 */     this.lastSwingTime = entity.field_70170_p.func_82737_E();
/*    */     
/* 44 */     if (!entity.field_70170_p.field_72995_K) {
/* 45 */       WyNetwork.sendToAllTrackingAndSelf(new SSwingTriggerPacket(entity, getAbility()), (Entity)entity);
/*    */     }
/*    */   }
/*    */   
/*    */   public long getLastSwingTime() {
/* 50 */     return this.lastSwingTime;
/*    */   }
/*    */   
/*    */   @FunctionalInterface
/*    */   public static interface IOnSwingEvent {
/*    */     void swing(LivingEntity param1LivingEntity, IAbility param1IAbility);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\components\SwingTriggerComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */