/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.haki;
/*    */ 
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.haki.BusoshokuHakiFullBodyHardeningAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*    */ 
/*    */ public class BusoshokuHakiFullbodyHardeningWrapperGoal
/*    */   extends AbilityWrapperGoal<MobEntity, BusoshokuHakiFullBodyHardeningAbility> {
/*    */   public BusoshokuHakiFullbodyHardeningWrapperGoal(MobEntity entity) {
/* 11 */     super(entity, BusoshokuHakiFullBodyHardeningAbility.INSTANCE);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUseWrapper() {
/* 16 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 17 */       return false;
/*    */     }
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


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\haki\BusoshokuHakiFullbodyHardeningWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */