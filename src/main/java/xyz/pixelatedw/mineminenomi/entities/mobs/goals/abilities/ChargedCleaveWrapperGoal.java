/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.ChargedCleaveAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*    */ 
/*    */ public class ChargedCleaveWrapperGoal extends AbilityWrapperGoal<MobEntity, ChargedCleaveAbility> {
/*    */   private LivingEntity target;
/* 12 */   private double distance = 3.0D;
/*    */   
/*    */   public ChargedCleaveWrapperGoal(MobEntity entity) {
/* 15 */     super(entity, ChargedCleaveAbility.INSTANCE);
/*    */   }
/*    */   
/*    */   public ChargedCleaveWrapperGoal setBleedingPower(int power) {
/* 19 */     ((ChargedCleaveAbility)getAbility()).setBleedingPower(power);
/* 20 */     return this;
/*    */   }
/*    */   
/*    */   public ChargedCleaveWrapperGoal setBleedingTime(int time) {
/* 24 */     ((ChargedCleaveAbility)getAbility()).setBleedingTime(time);
/* 25 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUseWrapper() {
/* 30 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 31 */       return false;
/*    */     }
/*    */     
/* 34 */     this.target = this.entity.func_70638_az();
/*    */     
/* 36 */     if (!GoalUtil.isWithinDistance((LivingEntity)this.entity, this.target, this.distance)) {
/* 37 */       return false;
/*    */     }
/*    */     
/* 40 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinueToUseWrapper() {
/* 45 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void startWrapper() {}
/*    */ 
/*    */   
/*    */   public void tickWrapper() {
/* 54 */     GoalUtil.lookAtEntity(this.entity, (Entity)this.target);
/*    */   }
/*    */   
/*    */   public void stopWrapper() {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\ChargedCleaveWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */