/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.StealthFootAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*    */ 
/*    */ public class StealthFootWrapperGoal extends AbilityWrapperGoal<MobEntity, StealthFootAbility> {
/* 12 */   private double minDistance = 10.0D; private LivingEntity target;
/* 13 */   private double maxDistance = 20.0D;
/*    */   
/*    */   public StealthFootWrapperGoal(MobEntity entity) {
/* 16 */     super(entity, StealthFootAbility.INSTANCE);
/* 17 */     this.minDistance = ((Float)((StealthFootAbility)getAbility()).getComponent(ModAbilityKeys.RANGE).map(comp -> Float.valueOf(comp.getRange())).orElse(Float.valueOf(10.0F))).floatValue();
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
/* 36 */     if (GoalUtil.isWithinDistance((LivingEntity)this.entity, this.target, this.minDistance)) {
/* 37 */       return false;
/*    */     }
/*    */     
/* 40 */     if (GoalUtil.isOutsideDistance((LivingEntity)this.entity, this.target, this.maxDistance)) {
/* 41 */       return false;
/*    */     }
/*    */     
/* 44 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinueToUseWrapper() {
/* 49 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void startWrapper() {
/* 54 */     GoalUtil.lookAtEntity(this.entity, (Entity)this.target);
/*    */   }
/*    */   
/*    */   public void tickWrapper() {}
/*    */   
/*    */   public void stopWrapper() {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\StealthFootWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */