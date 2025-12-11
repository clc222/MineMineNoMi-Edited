/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.swordsman;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.swordsman.ShiShishiSonsonAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*    */ 
/*    */ public class ShiShishiSonsonWrapperGoal extends AbilityWrapperGoal<MobEntity, ShiShishiSonsonAbility> {
/* 11 */   private double distance = 10.0D; private LivingEntity target;
/*    */   
/*    */   public ShiShishiSonsonWrapperGoal(MobEntity entity) {
/* 14 */     super(entity, ShiShishiSonsonAbility.INSTANCE);
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
/* 25 */     if (GoalUtil.isOutsideDistance((LivingEntity)this.entity, this.target, this.distance)) {
/* 26 */       return false;
/*    */     }
/*    */     
/* 29 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinueToUseWrapper() {
/* 34 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void startWrapper() {
/* 39 */     GoalUtil.lookAtEntity(this.entity, (Entity)this.target);
/*    */   }
/*    */ 
/*    */   
/*    */   public void tickWrapper() {
/* 44 */     GoalUtil.lookAtEntity(this.entity, (Entity)this.target);
/*    */   }
/*    */   
/*    */   public void stopWrapper() {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\swordsman\ShiShishiSonsonWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */