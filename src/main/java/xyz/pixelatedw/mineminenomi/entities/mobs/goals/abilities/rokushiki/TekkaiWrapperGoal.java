/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.rokushiki;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.fluid.FluidState;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.rokushiki.TekkaiAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*    */ 
/*    */ public class TekkaiWrapperGoal extends AbilityWrapperGoal<MobEntity, TekkaiAbility> {
/*    */   private LivingEntity target;
/* 13 */   private double distance = 10.0D;
/* 14 */   private int lastHurtTicks = 60;
/* 15 */   private int hits = 0;
/*    */   private long lastHitTimestamp;
/*    */   
/*    */   public TekkaiWrapperGoal(MobEntity entity) {
/* 19 */     super(entity, TekkaiAbility.INSTANCE);
/*    */   }
/*    */   
/*    */   public TekkaiWrapperGoal setMinDistance(double distance) {
/* 23 */     this.distance = distance;
/* 24 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUseWrapper() {
/* 29 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 30 */       return false;
/*    */     }
/*    */     
/* 33 */     this.target = this.entity.func_70638_az();
/*    */     
/* 35 */     if (!GoalUtil.isWithinDistance((LivingEntity)this.entity, this.target, this.distance)) {
/* 36 */       return false;
/*    */     }
/*    */     
/* 39 */     if (this.hits < 3) {
/* 40 */       if (this.lastHitTimestamp == 0L) {
/* 41 */         this.lastHitTimestamp = this.entity.field_70170_p.func_82737_E();
/*    */       }
/*    */       
/* 44 */       if (GoalUtil.lastHurtCheckBefore((LivingEntity)this.entity, this.lastHurtTicks) && this.entity.field_70170_p.func_82737_E() > this.lastHitTimestamp + this.lastHurtTicks) {
/* 45 */         this.hits++;
/* 46 */         this.lastHitTimestamp = this.entity.field_70170_p.func_82737_E();
/*    */       } 
/* 48 */       return false;
/*    */     } 
/*    */     
/* 51 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinueToUseWrapper() {
/* 56 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 57 */       return false;
/*    */     }
/*    */     
/* 60 */     if (!GoalUtil.isWithinDistance((LivingEntity)this.entity, this.target, this.distance * 1.5D)) {
/* 61 */       return false;
/*    */     }
/*    */     
/* 64 */     if (GoalUtil.lastHurtCheckAfter((LivingEntity)this.entity, (int)(this.lastHurtTicks / 1.5D))) {
/* 65 */       return false;
/*    */     }
/*    */     
/* 68 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void startWrapper() {
/* 73 */     FluidState fluidState = this.entity.field_70170_p.func_204610_c(this.entity.func_233580_cy_());
/* 74 */     if (!fluidState.func_206888_e()) {
/* 75 */       ((TekkaiAbility)getAbility()).switchToWalkMode((LivingEntity)this.entity);
/*    */     } else {
/*    */       
/* 78 */       ((TekkaiAbility)getAbility()).switchToHeavyMode((LivingEntity)this.entity);
/*    */     } 
/* 80 */     this.hits = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void tickWrapper() {
/* 85 */     GoalUtil.lookAtEntity(this.entity, (Entity)this.target);
/*    */   }
/*    */ 
/*    */   
/*    */   public void stopWrapper() {
/* 90 */     this.hits = 0;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\rokushiki\TekkaiWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */