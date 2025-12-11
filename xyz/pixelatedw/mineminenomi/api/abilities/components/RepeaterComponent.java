/*    */ package xyz.pixelatedw.mineminenomi.api.abilities.components;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.util.Interval;
/*    */ import xyz.pixelatedw.mineminenomi.api.util.PriorityEventPool;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*    */ 
/*    */ public class RepeaterComponent
/*    */   extends AbilityComponent<IAbility> {
/*    */   private int maxTriggers;
/*    */   private Interval triggerInterval;
/*    */   private boolean isActive;
/*    */   private int triggers;
/* 15 */   private final PriorityEventPool<IRepeaterTriggerEvent> triggerRepeaterEvents = new PriorityEventPool();
/* 16 */   private final PriorityEventPool<IRepeaterStopEvent> stopRepeaterEvents = new PriorityEventPool();
/*    */   
/*    */   public RepeaterComponent(IAbility ability) {
/* 19 */     super(ModAbilityKeys.REPEATER, ability);
/*    */   }
/*    */   
/*    */   public RepeaterComponent addTriggerEvent(IRepeaterTriggerEvent event) {
/* 23 */     this.triggerRepeaterEvents.addEvent(event);
/* 24 */     return this;
/*    */   }
/*    */   
/*    */   public RepeaterComponent addTriggerEvent(int priority, IRepeaterTriggerEvent event) {
/* 28 */     this.triggerRepeaterEvents.addEvent(priority, event);
/* 29 */     return this;
/*    */   }
/*    */   
/*    */   public RepeaterComponent addStopEvent(IRepeaterStopEvent event) {
/* 33 */     this.stopRepeaterEvents.addEvent(event);
/* 34 */     return this;
/*    */   }
/*    */   
/*    */   public RepeaterComponent addStopEvent(int priority, IRepeaterStopEvent event) {
/* 38 */     this.stopRepeaterEvents.addEvent(priority, event);
/* 39 */     return this;
/*    */   }
/*    */   
/*    */   public boolean hasFinished() {
/* 43 */     return (this.triggers >= this.maxTriggers);
/*    */   }
/*    */   
/*    */   private void resetShots() {
/* 47 */     this.triggers = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void doTick(LivingEntity entity) {
/* 52 */     getAbility().getComponent(ModAbilityKeys.ALT_MODE).ifPresent(component -> component.setDisabled(this.isActive));
/*    */ 
/*    */ 
/*    */     
/* 56 */     if (this.isActive && this.triggerInterval.canTick()) {
/* 57 */       this.triggers++;
/*    */       
/* 59 */       this.triggerRepeaterEvents.dispatch(event -> event.trigger(entity, getAbility()));
/*    */       
/* 61 */       if (hasFinished()) {
/* 62 */         stop(entity);
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   public void start(LivingEntity user, int maxTriggers, int triggerInterval) {
/* 68 */     ensureIsRegistered();
/* 69 */     if (this.isActive) {
/*    */       return;
/*    */     }
/* 72 */     this.isActive = true;
/* 73 */     this.maxTriggers = maxTriggers;
/* 74 */     this.triggerInterval = (Interval)(new Interval(triggerInterval)).trackTPS();
/*    */     
/* 76 */     resetShots();
/*    */   }
/*    */   
/*    */   public void stop(LivingEntity entity) {
/* 80 */     if (!this.isActive) {
/*    */       return;
/*    */     }
/* 83 */     this.isActive = false;
/*    */     
/* 85 */     this.stopRepeaterEvents.dispatch(event -> event.stop(entity, getAbility()));
/*    */   }
/*    */   
/*    */   public int getTriggerCount() {
/* 89 */     return this.triggers;
/*    */   }
/*    */   
/*    */   public int getMaxTriggers() {
/* 93 */     return this.maxTriggers;
/*    */   }
/*    */   
/*    */   @FunctionalInterface
/*    */   public static interface IRepeaterStopEvent {
/*    */     void stop(LivingEntity param1LivingEntity, IAbility param1IAbility);
/*    */   }
/*    */   
/*    */   @FunctionalInterface
/*    */   public static interface IRepeaterTriggerEvent {
/*    */     void trigger(LivingEntity param1LivingEntity, IAbility param1IAbility);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\components\RepeaterComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */