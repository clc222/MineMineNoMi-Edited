/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.suna;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.suna.BarjanAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*    */ 
/*    */ public class BarjanWrapperGoal extends AbilityWrapperGoal<MobEntity, BarjanAbility> {
/*    */   private LivingEntity target;
/*    */   
/*    */   public BarjanWrapperGoal(MobEntity entity) {
/* 14 */     super(entity, BarjanAbility.INSTANCE);
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
/* 25 */     if (GoalUtil.isOutsideDistance((LivingEntity)this.entity, this.target, 15.0D)) {
/* 26 */       return false;
/*    */     }
/*    */     
/* 29 */     if (!GoalUtil.isAtSameHeight((LivingEntity)this.entity, this.target, 2.0D)) {
/* 30 */       return false;
/*    */     }
/*    */     
/* 33 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinueToUseWrapper() {
/* 38 */     return false;
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


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\suna\BarjanWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */