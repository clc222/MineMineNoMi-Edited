/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.mandemontactics;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.mandemontactics.DemonicDanceAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*    */ 
/*    */ public class DemonicDanceWrapperGoal extends AbilityWrapperGoal<MobEntity, DemonicDanceAbility> {
/* 11 */   private double minDistance = 2.0D; private LivingEntity target;
/* 12 */   private double maxDistance = 15.0D;
/*    */   
/*    */   public DemonicDanceWrapperGoal(MobEntity entity) {
/* 15 */     super(entity, DemonicDanceAbility.INSTANCE);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUseWrapper() {
/* 20 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 21 */       return false;
/*    */     }
/*    */     
/* 24 */     this.target = this.entity.func_70638_az();
/*    */     
/* 26 */     if (GoalUtil.isWithinDistance((LivingEntity)this.entity, this.target, this.minDistance)) {
/* 27 */       return false;
/*    */     }
/*    */     
/* 30 */     if (GoalUtil.isOutsideDistance((LivingEntity)this.entity, this.target, this.maxDistance)) {
/* 31 */       return false;
/*    */     }
/*    */     
/* 34 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinueToUseWrapper() {
/* 39 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 40 */       return false;
/*    */     }
/*    */     
/* 43 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void startWrapper() {
/* 48 */     ((DemonicDanceAbility)getAbility()).setTarget((Entity)this.target);
/* 49 */     GoalUtil.lookAtEntity(this.entity, (Entity)this.target);
/*    */   }
/*    */ 
/*    */   
/*    */   public void tickWrapper() {
/* 54 */     GoalUtil.lookAtEntity(this.entity, (Entity)this.target);
/*    */   }
/*    */   
/*    */   public void stopWrapper() {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\mandemontactics\DemonicDanceWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */