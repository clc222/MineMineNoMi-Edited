/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.rokushiki;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.rokushiki.RankyakuAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*    */ 
/*    */ public class RankyakuWrapperGoal extends AbilityWrapperGoal<MobEntity, RankyakuAbility> {
/*    */   private LivingEntity target;
/* 12 */   private double maxDistance = 100.0D;
/* 13 */   private double minDistance = 5.0D;
/*    */   
/*    */   public RankyakuWrapperGoal(MobEntity entity) {
/* 16 */     super(entity, RankyakuAbility.INSTANCE);
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
/* 31 */     if (GoalUtil.isOutsideDistance((LivingEntity)this.entity, this.target, this.maxDistance)) {
/* 32 */       return false;
/*    */     }
/*    */     
/* 35 */     if (GoalUtil.isWithinDistance((LivingEntity)this.entity, this.target, this.minDistance)) {
/* 36 */       return false;
/*    */     }
/*    */     
/* 39 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinueToUseWrapper() {
/* 44 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void startWrapper() {
/* 49 */     GoalUtil.lookAtEntity(this.entity, (Entity)this.target);
/*    */   }
/*    */   
/*    */   public void tickWrapper() {}
/*    */   
/*    */   public void stopWrapper() {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\rokushiki\RankyakuWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */