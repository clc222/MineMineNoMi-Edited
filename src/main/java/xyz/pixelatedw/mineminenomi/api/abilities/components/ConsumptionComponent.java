/*    */ package xyz.pixelatedw.mineminenomi.api.abilities.components;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.util.PriorityEventPool;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*    */ 
/*    */ public class ConsumptionComponent extends AbilityComponent<IAbility> {
/*  9 */   private final PriorityEventPool<IOnConsumptionEvent> consumptionEvents = new PriorityEventPool();
/*    */   
/*    */   public ConsumptionComponent(IAbility ability) {
/* 12 */     super(ModAbilityKeys.CONSUMPTION, ability);
/*    */   }
/*    */   
/*    */   public ConsumptionComponent addConsumptionEvent(IOnConsumptionEvent event) {
/* 16 */     this.consumptionEvents.addEvent(event);
/*    */     
/* 18 */     return this;
/*    */   }
/*    */   
/*    */   public boolean tryConsumption(LivingEntity entity, int nutrition, float saturationModifier) {
/* 22 */     return !this.consumptionEvents.dispatchCancelable(event -> !event.consume(entity, getAbility(), nutrition, saturationModifier));
/*    */   }
/*    */   
/*    */   @FunctionalInterface
/*    */   public static interface IOnConsumptionEvent {
/*    */     boolean consume(LivingEntity param1LivingEntity, IAbility param1IAbility, int param1Int, float param1Float);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\components\ConsumptionComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */