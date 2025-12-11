/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.LeapAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*    */ 
/*    */ public class LeapWrapperGoal extends AbilityWrapperGoal<MobEntity, LeapAbility> {
/* 11 */   private double minDistance = 5.0D; private LivingEntity target;
/*    */   
/*    */   public LeapWrapperGoal(MobEntity entity) {
/* 14 */     super(entity, LeapAbility.INSTANCE);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUseWrapper() {
/* 19 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 20 */       return false;
/*    */     }
/*    */     
/* 23 */     if (!GoalUtil.canMove((LivingEntity)this.entity)) {
/* 24 */       return false;
/*    */     }
/*    */     
/* 27 */     this.target = this.entity.func_70638_az();
/*    */     
/* 29 */     if (GoalUtil.isWithinDistance((LivingEntity)this.entity, this.target, this.minDistance)) {
/* 30 */       return false;
/*    */     }
/*    */     
/* 33 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinueToUseWrapper() {
/* 38 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void startWrapper() {
/* 43 */     GoalUtil.lookAtEntity(this.entity, (Entity)this.target);
/*    */   }
/*    */   
/*    */   public void tickWrapper() {}
/*    */   
/*    */   public void stopWrapper() {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\LeapWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */