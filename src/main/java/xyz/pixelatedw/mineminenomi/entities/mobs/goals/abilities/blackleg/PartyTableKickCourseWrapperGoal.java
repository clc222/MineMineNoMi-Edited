/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.blackleg;
/*    */ 
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.blackleg.PartyTableKickCourseAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*    */ import xyz.pixelatedw.mineminenomi.api.util.Interval;
/*    */ 
/*    */ public class PartyTableKickCourseWrapperGoal
/*    */   extends AbilityWrapperGoal<MobEntity, PartyTableKickCourseAbility> {
/* 11 */   private Interval canUseInterval = new Interval(10);
/*    */   
/*    */   public PartyTableKickCourseWrapperGoal(MobEntity entity) {
/* 14 */     super(entity, PartyTableKickCourseAbility.INSTANCE);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUseWrapper() {
/* 19 */     if (!this.canUseInterval.canTick()) {
/* 20 */       return false;
/*    */     }
/*    */     
/* 23 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 24 */       return false;
/*    */     }
/*    */     
/* 27 */     if (!GoalUtil.hasEnoughTargetsAround(this.entity, 1, 4.0F)) {
/* 28 */       return false;
/*    */     }
/*    */     
/* 31 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinueToUseWrapper() {
/* 36 */     return true;
/*    */   }
/*    */   
/*    */   public void startWrapper() {}
/*    */   
/*    */   public void tickWrapper() {}
/*    */   
/*    */   public void stopWrapper() {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\blackleg\PartyTableKickCourseWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */