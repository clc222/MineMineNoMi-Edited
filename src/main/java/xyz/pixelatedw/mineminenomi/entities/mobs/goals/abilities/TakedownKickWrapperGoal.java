/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.TakedownKickAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
/*    */ 
/*    */ public class TakedownKickWrapperGoal extends AbilityWrapperGoal<MobEntity, TakedownKickAbility> {
/* 12 */   private double groundDistance = 5.0D; private LivingEntity target;
/* 13 */   private double minDistance = 10.0D;
/* 14 */   private double maxDistance = 20.0D;
/*    */   
/*    */   public TakedownKickWrapperGoal(MobEntity entity) {
/* 17 */     super(entity, TakedownKickAbility.INSTANCE);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUseWrapper() {
/* 22 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 23 */       return false;
/*    */     }
/*    */     
/* 26 */     if (!GoalUtil.canMove((LivingEntity)this.entity)) {
/* 27 */       return false;
/*    */     }
/*    */     
/* 30 */     this.target = this.entity.func_70638_az();
/*    */     
/* 32 */     if (!GoalUtil.canSee(this.entity, this.target)) {
/* 33 */       return false;
/*    */     }
/*    */     
/* 36 */     if (DevilFruitHelper.getDifferenceToFloor((Entity)this.entity) > this.groundDistance) {
/* 37 */       return false;
/*    */     }
/*    */     
/* 40 */     if (DevilFruitHelper.getDifferenceToFloor((Entity)this.target) < this.minDistance) {
/* 41 */       return false;
/*    */     }
/*    */     
/* 44 */     if (GoalUtil.isWithinDistance((LivingEntity)this.entity, this.target, this.minDistance)) {
/* 45 */       return false;
/*    */     }
/*    */     
/* 48 */     if (GoalUtil.isOutsideDistance((LivingEntity)this.entity, this.target, this.maxDistance)) {
/* 49 */       return false;
/*    */     }
/*    */     
/* 52 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinueToUseWrapper() {
/* 57 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 58 */       return false;
/*    */     }
/*    */     
/* 61 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void startWrapper() {
/* 66 */     ((TakedownKickAbility)getAbility()).setTarget((Entity)this.target);
/* 67 */     GoalUtil.lookAtEntity(this.entity, (Entity)this.target);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void tickWrapper() {}
/*    */ 
/*    */   
/*    */   public void stopWrapper() {}
/*    */ 
/*    */   
/*    */   public void setMinDistance(float minDistance) {
/* 79 */     this.minDistance = minDistance;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\TakedownKickWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */