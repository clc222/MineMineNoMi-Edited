/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.mandemontactics;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.mandemontactics.DemonicDashAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*    */ 
/*    */ public class DemonicDashWrapperGoal extends AbilityWrapperGoal<MobEntity, DemonicDashAbility> {
/* 11 */   private double minDistance = 10.0D; private LivingEntity target;
/*    */   
/*    */   public DemonicDashWrapperGoal(MobEntity entity) {
/* 14 */     super(entity, DemonicDashAbility.INSTANCE);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUseWrapper() {
/* 19 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 20 */       return false;
/*    */     }
/*    */     
/* 23 */     this.target = this.entity.func_70638_az();
/*    */     
/* 25 */     if (!GoalUtil.canSee(this.entity, this.target)) {
/* 26 */       return false;
/*    */     }
/*    */     
/* 29 */     if (GoalUtil.isOutsideDistance((LivingEntity)this.entity, this.target, this.minDistance)) {
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
/*    */   
/*    */   public void tickWrapper() {
/* 48 */     GoalUtil.lookAtEntity(this.entity, (Entity)this.target);
/*    */   }
/*    */   
/*    */   public void stopWrapper() {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\mandemontactics\DemonicDashWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */