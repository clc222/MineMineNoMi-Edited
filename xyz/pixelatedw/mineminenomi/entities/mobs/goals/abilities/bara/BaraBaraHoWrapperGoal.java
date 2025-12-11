/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.bara;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.bara.BaraBaraHoAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*    */ 
/*    */ public class BaraBaraHoWrapperGoal extends AbilityWrapperGoal<MobEntity, BaraBaraHoAbility> {
/*    */   private LivingEntity target;
/* 12 */   private double distance = 20.0D;
/*    */   
/*    */   public BaraBaraHoWrapperGoal(MobEntity entity) {
/* 15 */     super(entity, BaraBaraHoAbility.INSTANCE);
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
/* 26 */     if (!GoalUtil.canSee(this.entity, this.target)) {
/* 27 */       return false;
/*    */     }
/*    */     
/* 30 */     if (GoalUtil.isOutsideDistance((LivingEntity)this.entity, this.target, this.distance)) {
/* 31 */       return false;
/*    */     }
/*    */     
/* 34 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinueToUseWrapper() {
/* 39 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void startWrapper() {
/* 44 */     GoalUtil.lookAtEntity(this.entity, (Entity)this.target);
/*    */   }
/*    */   
/*    */   public void tickWrapper() {}
/*    */   
/*    */   public void stopWrapper() {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\bara\BaraBaraHoWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */