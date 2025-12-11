/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.awa;
/*    */ 
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.awa.GoldenHourAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*    */ 
/*    */ public class GoldenHourWrapperGoal extends AbilityWrapperGoal<MobEntity, GoldenHourAbility> {
/*    */   public GoldenHourWrapperGoal(MobEntity entity) {
/* 10 */     super(entity, GoldenHourAbility.INSTANCE);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUseWrapper() {
/* 15 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 16 */       return false;
/*    */     }
/*    */     
/* 19 */     if (GoalUtil.hasEnoughTargetsAround(this.entity, 1, 10.0F)) {
/* 20 */       return true;
/*    */     }
/*    */     
/* 23 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinueToUseWrapper() {
/* 28 */     return true;
/*    */   }
/*    */   
/*    */   public void startWrapper() {}
/*    */   
/*    */   public void tickWrapper() {}
/*    */   
/*    */   public void stopWrapper() {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\awa\GoldenHourWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */