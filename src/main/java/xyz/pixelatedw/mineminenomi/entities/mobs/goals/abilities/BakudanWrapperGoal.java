/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.BakudanAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.IChallengeBoss;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*    */ 
/*    */ public class BakudanWrapperGoal
/*    */   extends AbilityWrapperGoal<MobEntity, BakudanAbility> {
/*    */   public BakudanWrapperGoal(MobEntity entity) {
/* 13 */     super(entity, BakudanAbility.INSTANCE);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUseWrapper() {
/* 18 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 19 */       return false;
/*    */     }
/*    */     
/* 22 */     LivingEntity target = this.entity.func_70638_az();
/*    */     
/* 24 */     if (GoalUtil.isOutsideDistance((LivingEntity)this.entity, target, 30.0D)) {
/* 25 */       return false;
/*    */     }
/*    */     
/* 28 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinueToUseWrapper() {
/* 33 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void startWrapper() {
/* 38 */     if (this.entity instanceof IChallengeBoss && (
/* 39 */       (IChallengeBoss)this.entity).isDifficultyHardOrAbove())
/* 40 */       ((BakudanAbility)getAbility()).setAmount(7, 10); 
/*    */   }
/*    */   
/*    */   public void tickWrapper() {}
/*    */   
/*    */   public void stopWrapper() {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\BakudanWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */