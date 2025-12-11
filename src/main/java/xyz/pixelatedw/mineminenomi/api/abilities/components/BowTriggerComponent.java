/*    */ package xyz.pixelatedw.mineminenomi.api.abilities.components;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.util.PriorityEventPool;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*    */ 
/*    */ public class BowTriggerComponent
/*    */   extends AbilityComponent<IAbility> {
/* 10 */   private final PriorityEventPool<IOnShootEvent> shootEvents = new PriorityEventPool();
/*    */   
/*    */   public BowTriggerComponent(IAbility ability) {
/* 13 */     super(ModAbilityKeys.BOW_TRIGGER, ability);
/*    */   }
/*    */   
/*    */   public BowTriggerComponent addShootEvent(IOnShootEvent event) {
/* 17 */     this.shootEvents.addEvent(event);
/* 18 */     return this;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean tryShoot(LivingEntity entity) {
/* 30 */     ensureIsRegistered();
/* 31 */     return !this.shootEvents.dispatchCancelable(event -> !event.shoot(entity, getAbility()));
/*    */   }
/*    */   
/*    */   @FunctionalInterface
/*    */   public static interface IOnShootEvent {
/*    */     boolean shoot(LivingEntity param1LivingEntity, IAbility param1IAbility);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\components\BowTriggerComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */