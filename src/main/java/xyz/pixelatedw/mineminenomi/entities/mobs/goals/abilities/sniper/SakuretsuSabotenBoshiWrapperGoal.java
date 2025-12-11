/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.sniper;
/*    */ 
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.sniper.SakuretsuSabotenBoshiAbility;
/*    */ 
/*    */ public class SakuretsuSabotenBoshiWrapperGoal
/*    */   extends ChargeSniperAbilityGoal<MobEntity, SakuretsuSabotenBoshiAbility> {
/*    */   public SakuretsuSabotenBoshiWrapperGoal(MobEntity entity) {
/*  9 */     super(entity, SakuretsuSabotenBoshiAbility.INSTANCE, 40);
/*    */   }
/*    */   
/*    */   public SakuretsuSabotenBoshiWrapperGoal(MobEntity entity, int chargeup) {
/* 13 */     super(entity, SakuretsuSabotenBoshiAbility.INSTANCE, chargeup);
/* 14 */     setMinDistance(5.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUseWrapper() {
/* 19 */     if (!super.canUseWrapper()) {
/* 20 */       return false;
/*    */     }
/*    */     
/* 23 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinueToUseWrapper() {
/* 28 */     return super.canContinueToUseWrapper();
/*    */   }
/*    */ 
/*    */   
/*    */   public void startWrapper() {
/* 33 */     super.startWrapper();
/*    */   }
/*    */ 
/*    */   
/*    */   public void tickWrapper() {
/* 38 */     super.tickWrapper();
/*    */   }
/*    */ 
/*    */   
/*    */   public void stopWrapper() {
/* 43 */     super.stopWrapper();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\sniper\SakuretsuSabotenBoshiWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */