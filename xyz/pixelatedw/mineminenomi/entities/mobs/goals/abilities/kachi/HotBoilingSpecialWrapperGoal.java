/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.kachi;
/*    */ 
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.kachi.HotBoilingSpecialAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*    */ 
/*    */ public class HotBoilingSpecialWrapperGoal extends AbilityWrapperGoal<MobEntity, HotBoilingSpecialAbility> {
/*    */   public HotBoilingSpecialWrapperGoal(MobEntity entity) {
/* 10 */     super(entity, HotBoilingSpecialAbility.INSTANCE);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUseWrapper() {
/* 15 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 16 */       return false;
/*    */     }
/*    */     
/* 19 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinueToUseWrapper() {
/* 24 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 25 */       return false;
/*    */     }
/*    */     
/* 28 */     return true;
/*    */   }
/*    */   
/*    */   public void startWrapper() {}
/*    */   
/*    */   public void tickWrapper() {}
/*    */   
/*    */   public void stopWrapper() {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\kachi\HotBoilingSpecialWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */