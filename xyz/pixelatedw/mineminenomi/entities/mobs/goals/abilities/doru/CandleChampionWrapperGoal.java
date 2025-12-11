/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.doru;
/*    */ 
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.doru.CandleChampionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*    */ 
/*    */ public class CandleChampionWrapperGoal
/*    */   extends AbilityWrapperGoal<MobEntity, CandleChampionAbility> {
/*    */   public CandleChampionWrapperGoal(MobEntity entity) {
/* 11 */     super(entity, CandleChampionAbility.INSTANCE);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUseWrapper() {
/* 16 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 17 */       return false;
/*    */     }
/*    */     
/* 20 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinueToUseWrapper() {
/* 25 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 26 */       return false;
/*    */     }
/*    */     
/* 29 */     return true;
/*    */   }
/*    */   
/*    */   public void startWrapper() {}
/*    */   
/*    */   public void tickWrapper() {}
/*    */   
/*    */   public void stopWrapper() {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\doru\CandleChampionWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */