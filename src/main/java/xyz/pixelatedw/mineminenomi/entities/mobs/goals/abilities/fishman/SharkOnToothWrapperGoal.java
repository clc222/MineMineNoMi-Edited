/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.fishman;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.fishmankarate.SharkOnToothAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
/*    */ 
/*    */ public class SharkOnToothWrapperGoal
/*    */   extends AbilityWrapperGoal<MobEntity, SharkOnToothAbility> {
/*    */   public static final double DEFAULT_MAX_DISTANCE = 10.0D;
/*    */   private LivingEntity target;
/* 15 */   private double groundDistance = 5.0D;
/* 16 */   private double minDistance = 3.0D;
/* 17 */   private double maxDistance = 10.0D;
/*    */   
/*    */   public SharkOnToothWrapperGoal(MobEntity entity) {
/* 20 */     super(entity, SharkOnToothAbility.INSTANCE);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUseWrapper() {
/* 25 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 26 */       return false;
/*    */     }
/*    */     
/* 29 */     this.target = this.entity.func_70638_az();
/*    */     
/* 31 */     if (GoalUtil.isWithinDistance((LivingEntity)this.entity, this.target, this.minDistance)) {
/* 32 */       return false;
/*    */     }
/*    */     
/* 35 */     if (GoalUtil.isOutsideDistance((LivingEntity)this.entity, this.target, this.maxDistance)) {
/* 36 */       return false;
/*    */     }
/*    */     
/* 39 */     if (DevilFruitHelper.getDifferenceToFloor((Entity)this.entity) > this.groundDistance) {
/* 40 */       return false;
/*    */     }
/*    */     
/* 43 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinueToUseWrapper() {
/* 48 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 49 */       return false;
/*    */     }
/*    */     
/* 52 */     if (GoalUtil.isOutsideDistance((LivingEntity)this.entity, this.target, this.maxDistance)) {
/* 53 */       return false;
/*    */     }
/*    */     
/* 56 */     if (DevilFruitHelper.getDifferenceToFloor((Entity)this.entity) > this.groundDistance) {
/* 57 */       return false;
/*    */     }
/*    */     
/* 60 */     return ((SharkOnToothAbility)getAbility()).isContinuous();
/*    */   }
/*    */ 
/*    */   
/*    */   public void startWrapper() {
/* 65 */     this.entity.func_70661_as().func_75499_g();
/* 66 */     GoalUtil.lookAtEntity(this.entity, (Entity)this.target);
/*    */   }
/*    */ 
/*    */   
/*    */   public void tickWrapper() {
/* 71 */     this.entity.func_70661_as().func_75499_g();
/*    */   }
/*    */ 
/*    */   
/*    */   public void stopWrapper() {}
/*    */ 
/*    */   
/*    */   public void setMaxDistance(double maxDistance) {
/* 79 */     this.maxDistance = maxDistance;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\fishman\SharkOnToothWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */