/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.sniper;
/*    */ 
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.sniper.TokuyoAburaBoshiAbility;
/*    */ 
/*    */ public class TokuyoAburaBoshiWrapperGoal
/*    */   extends ChargeSniperAbilityGoal<MobEntity, TokuyoAburaBoshiAbility> {
/*    */   public TokuyoAburaBoshiWrapperGoal(MobEntity entity) {
/*  9 */     super(entity, TokuyoAburaBoshiAbility.INSTANCE, 40);
/*    */   }
/*    */   
/*    */   public TokuyoAburaBoshiWrapperGoal(MobEntity entity, int chargeup) {
/* 13 */     super(entity, TokuyoAburaBoshiAbility.INSTANCE, chargeup);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUseWrapper() {
/* 18 */     if (!super.canUseWrapper()) {
/* 19 */       return false;
/*    */     }
/*    */     
/* 22 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinueToUseWrapper() {
/* 27 */     return super.canContinueToUseWrapper();
/*    */   }
/*    */ 
/*    */   
/*    */   public void startWrapper() {
/* 32 */     super.startWrapper();
/*    */   }
/*    */ 
/*    */   
/*    */   public void tickWrapper() {
/* 37 */     super.tickWrapper();
/*    */   }
/*    */ 
/*    */   
/*    */   public void stopWrapper() {
/* 42 */     super.stopWrapper();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\sniper\TokuyoAburaBoshiWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */