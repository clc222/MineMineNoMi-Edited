/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.GunArrayAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*    */ 
/*    */ public class GunArrayWrapperGoal extends AbilityWrapperGoal<MobEntity, GunArrayAbility> {
/*    */   private LivingEntity target;
/*    */   
/*    */   public GunArrayWrapperGoal(MobEntity entity) {
/* 14 */     super(entity, GunArrayAbility.INSTANCE);
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
/* 29 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinueToUseWrapper() {
/* 34 */     if (!GoalUtil.canSee(this.entity, this.target)) {
/* 35 */       return false;
/*    */     }
/*    */     
/* 38 */     if (GoalUtil.isOutsideDistance((LivingEntity)this.entity, this.target, 30.0D)) {
/* 39 */       return false;
/*    */     }
/*    */     
/* 42 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void startWrapper() {
/* 47 */     GoalUtil.lookAtEntity(this.entity, (Entity)this.target);
/*    */   }
/*    */ 
/*    */   
/*    */   public void tickWrapper() {
/* 52 */     GoalUtil.lookAtEntity(this.entity, (Entity)this.target);
/*    */   }
/*    */   
/*    */   public void stopWrapper() {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\GunArrayWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */