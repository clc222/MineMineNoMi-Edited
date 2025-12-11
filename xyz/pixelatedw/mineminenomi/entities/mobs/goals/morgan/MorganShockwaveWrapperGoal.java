/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.morgan;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.morgan.MorganShockwaveAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*    */ 
/*    */ public class MorganShockwaveWrapperGoal extends AbilityWrapperGoal<MobEntity, MorganShockwaveAbility> {
/*    */   private LivingEntity target;
/* 12 */   private double minDistance = 10.0D;
/* 13 */   private double maxDistance = 40.0D;
/*    */   
/*    */   public MorganShockwaveWrapperGoal(MobEntity entity) {
/* 16 */     super(entity, MorganShockwaveAbility.INSTANCE);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUseWrapper() {
/* 21 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 22 */       return false;
/*    */     }
/*    */     
/* 25 */     this.target = this.entity.func_70638_az();
/*    */     
/* 27 */     if (!GoalUtil.canSee(this.entity, this.target)) {
/* 28 */       return false;
/*    */     }
/*    */     
/* 31 */     if (GoalUtil.isWithinDistance((LivingEntity)this.entity, this.target, this.minDistance)) {
/* 32 */       return false;
/*    */     }
/*    */     
/* 35 */     if (GoalUtil.isOutsideDistance((LivingEntity)this.entity, this.target, this.maxDistance)) {
/* 36 */       return false;
/*    */     }
/*    */     
/* 39 */     if (!this.target.func_233570_aj_()) {
/* 40 */       return false;
/*    */     }
/*    */     
/* 43 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinueToUseWrapper() {
/* 48 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void startWrapper() {
/* 53 */     GoalUtil.lookAtEntity(this.entity, (Entity)this.target);
/*    */   }
/*    */   
/*    */   public void tickWrapper() {}
/*    */   
/*    */   public void stopWrapper() {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\morgan\MorganShockwaveWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */