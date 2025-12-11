/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.HookGrabAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*    */ 
/*    */ public class HookGrabWrapperGoal
/*    */   extends AbilityWrapperGoal<MobEntity, HookGrabAbility> {
/*    */   public HookGrabWrapperGoal(MobEntity entity) {
/* 13 */     super(entity, HookGrabAbility.INSTANCE);
/*    */   }
/*    */   private LivingEntity target;
/*    */   
/*    */   public boolean canUseWrapper() {
/* 18 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 19 */       return false;
/*    */     }
/*    */     
/* 22 */     this.target = this.entity.func_70638_az();
/*    */     
/* 24 */     if (GoalUtil.isWithinDistance((LivingEntity)this.entity, this.target, 15.0D)) {
/* 25 */       return false;
/*    */     }
/*    */     
/* 28 */     if (GoalUtil.isOutsideDistance((LivingEntity)this.entity, this.target, 50.0D)) {
/* 29 */       return false;
/*    */     }
/*    */     
/* 32 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinueToUseWrapper() {
/* 37 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 38 */       return false;
/*    */     }
/*    */     
/* 41 */     if (GoalUtil.isOutsideDistance((LivingEntity)this.entity, this.target, 50.0D)) {
/* 42 */       return false;
/*    */     }
/*    */     
/* 45 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void startWrapper() {
/* 50 */     GoalUtil.lookAtEntity(this.entity, (Entity)this.target);
/*    */   }
/*    */   
/*    */   public void tickWrapper() {}
/*    */   
/*    */   public void stopWrapper() {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\HookGrabWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */