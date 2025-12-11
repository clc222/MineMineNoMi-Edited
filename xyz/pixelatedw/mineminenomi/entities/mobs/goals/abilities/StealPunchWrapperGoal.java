/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.StealPunchAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*    */ 
/*    */ public class StealPunchWrapperGoal extends AbilityWrapperGoal<MobEntity, StealPunchAbility> {
/* 10 */   private float distance = 5.0F;
/*    */   
/*    */   public StealPunchWrapperGoal(MobEntity entity) {
/* 13 */     super(entity, StealPunchAbility.INSTANCE);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUseWrapper() {
/* 18 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 19 */       return false;
/*    */     }
/*    */     
/* 22 */     if (!this.entity.func_184614_ca().func_190926_b()) {
/* 23 */       return false;
/*    */     }
/*    */     
/* 26 */     LivingEntity target = this.entity.func_70638_az();
/*    */     
/* 28 */     if (GoalUtil.isOutsideDistance((LivingEntity)this.entity, target, this.distance)) {
/* 29 */       return false;
/*    */     }
/*    */     
/* 32 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinueToUseWrapper() {
/* 37 */     return true;
/*    */   }
/*    */   
/*    */   public void startWrapper() {}
/*    */   
/*    */   public void tickWrapper() {}
/*    */   
/*    */   public void stopWrapper() {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\StealPunchWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */