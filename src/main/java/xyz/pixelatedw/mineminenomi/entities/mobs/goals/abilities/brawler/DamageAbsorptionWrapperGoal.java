/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.brawler.DamageAbsorptionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class DamageAbsorptionWrapperGoal extends AbilityWrapperGoal<MobEntity, DamageAbsorptionAbility> {
/*    */   private LivingEntity target;
/* 13 */   private double distance = 10.0D;
/* 14 */   private int lastHurtTicks = (int)WyHelper.secondsToTicks(5.0F);
/*    */   
/*    */   public DamageAbsorptionWrapperGoal(MobEntity entity) {
/* 17 */     super(entity, DamageAbsorptionAbility.INSTANCE);
/*    */   }
/*    */   
/*    */   public DamageAbsorptionWrapperGoal setMinDistance(double distance) {
/* 21 */     this.distance = distance;
/* 22 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUseWrapper() {
/* 27 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 28 */       return false;
/*    */     }
/*    */     
/* 31 */     this.target = this.entity.func_70638_az();
/*    */     
/* 33 */     if (!GoalUtil.isWithinDistance((LivingEntity)this.entity, this.target, this.distance)) {
/* 34 */       return false;
/*    */     }
/*    */     
/* 37 */     if (this.entity.func_142015_aE() <= 0 || this.entity.field_70173_aa > this.entity.func_142015_aE() + this.lastHurtTicks) {
/* 38 */       return false;
/*    */     }
/*    */     
/* 41 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinueToUseWrapper() {
/* 46 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 47 */       return false;
/*    */     }
/*    */     
/* 50 */     if (!GoalUtil.isWithinDistance((LivingEntity)this.entity, this.target, this.distance * 1.5D)) {
/* 51 */       return false;
/*    */     }
/*    */     
/* 54 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void startWrapper() {}
/*    */ 
/*    */   
/*    */   public void tickWrapper() {
/* 63 */     this.entity.func_70661_as().func_75499_g();
/* 64 */     GoalUtil.lookAtEntity(this.entity, (Entity)this.target);
/*    */   }
/*    */   
/*    */   public void stopWrapper() {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\brawler\DamageAbsorptionWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */