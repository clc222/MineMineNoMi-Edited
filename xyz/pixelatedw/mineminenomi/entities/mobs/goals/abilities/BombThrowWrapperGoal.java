/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.BombThrowAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*    */ 
/*    */ public class BombThrowWrapperGoal extends AbilityWrapperGoal<MobEntity, BombThrowAbility> {
/* 11 */   private double minDistance = 10.0D; private LivingEntity target;
/* 12 */   private double maxDistance = 25.0D;
/*    */   
/*    */   public BombThrowWrapperGoal(MobEntity entity) {
/* 15 */     super(entity, BombThrowAbility.INSTANCE);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUseWrapper() {
/* 20 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 21 */       return false;
/*    */     }
/*    */     
/* 24 */     if (!GoalUtil.canMove((LivingEntity)this.entity)) {
/* 25 */       return false;
/*    */     }
/*    */     
/* 28 */     this.target = this.entity.func_70638_az();
/*    */     
/* 30 */     if (GoalUtil.isWithinDistance((LivingEntity)this.entity, this.target, this.minDistance)) {
/* 31 */       return false;
/*    */     }
/*    */     
/* 34 */     if (GoalUtil.isOutsideDistance((LivingEntity)this.entity, this.target, this.maxDistance)) {
/* 35 */       return false;
/*    */     }
/*    */     
/* 38 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinueToUseWrapper() {
/* 43 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void startWrapper() {
/* 48 */     GoalUtil.lookAtEntity(this.entity, (Entity)this.target);
/*    */   }
/*    */   
/*    */   public void tickWrapper() {}
/*    */   
/*    */   public void stopWrapper() {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\BombThrowWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */