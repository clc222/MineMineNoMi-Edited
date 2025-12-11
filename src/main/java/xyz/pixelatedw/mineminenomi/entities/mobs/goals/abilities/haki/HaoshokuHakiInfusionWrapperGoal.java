/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.haki;
/*    */ 
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.haki.HaoshokuHakiInfusionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*    */ 
/*    */ public class HaoshokuHakiInfusionWrapperGoal
/*    */   extends AbilityWrapperGoal<MobEntity, HaoshokuHakiInfusionAbility> {
/*    */   public HaoshokuHakiInfusionWrapperGoal(MobEntity entity) {
/* 11 */     super(entity, HaoshokuHakiInfusionAbility.INSTANCE);
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


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\haki\HaoshokuHakiInfusionWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */