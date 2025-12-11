/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.doru;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.doru.TokudaiCandleAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.InProgressChallenge;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.baroqueworks.Mr3Entity;
/*    */ 
/*    */ public class TokudaiCandleWrapperGoal
/*    */   extends AbilityWrapperGoal<MobEntity, TokudaiCandleAbility> {
/*    */   private LivingEntity target;
/*    */   private InProgressChallenge challenge;
/*    */   
/*    */   public TokudaiCandleWrapperGoal(MobEntity entity) {
/* 18 */     super(entity, TokudaiCandleAbility.INSTANCE);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUseWrapper() {
/* 23 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 24 */       return false;
/*    */     }
/*    */     
/* 27 */     if (!this.entity.func_233570_aj_()) {
/* 28 */       return false;
/*    */     }
/*    */     
/* 31 */     if (this.entity instanceof Mr3Entity) {
/* 32 */       Mr3Entity mr3Entity = (Mr3Entity)this.entity;
/* 33 */       this.challenge = mr3Entity.getChallengeInfo().getInProgressChallengeData();
/* 34 */       return true;
/*    */     } 
/*    */     
/* 37 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinueToUseWrapper() {
/* 42 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void startWrapper() {
/* 47 */     if (this.challenge != null) {
/* 48 */       BlockPos pos = this.challenge.getArenaPos();
/* 49 */       int groundLevel = this.challenge.getArena().getGroundLevel();
/* 50 */       ((TokudaiCandleAbility)getAbility()).setCenterPos(new BlockPos(pos.func_177958_n(), groundLevel, pos.func_177952_p()));
/*    */     } 
/*    */   }
/*    */   
/*    */   public void tickWrapper() {}
/*    */   
/*    */   public void stopWrapper() {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\doru\TokudaiCandleWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */