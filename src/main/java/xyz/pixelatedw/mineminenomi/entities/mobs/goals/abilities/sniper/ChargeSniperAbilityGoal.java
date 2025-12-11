/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.sniper;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.util.Hand;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*    */ 
/*    */ public abstract class ChargeSniperAbilityGoal<E extends MobEntity, A extends IAbility> extends AbilityWrapperGoal<E, A> {
/*    */   private boolean fired;
/*    */   private LivingEntity target;
/* 15 */   private double distance = 2.0D;
/*    */   private int chargeup;
/*    */   private final int maxChargeup;
/*    */   
/*    */   public ChargeSniperAbilityGoal(E entity, AbilityCore<A> core, int chargeup) {
/* 20 */     super((MobEntity)entity, core);
/* 21 */     this.maxChargeup = chargeup;
/*    */   }
/*    */   
/*    */   public ChargeSniperAbilityGoal<E, A> setMinDistance(double distance) {
/* 25 */     this.distance = distance;
/* 26 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUseWrapper() {
/* 31 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 32 */       return false;
/*    */     }
/*    */     
/* 35 */     this.target = this.entity.func_70638_az();
/*    */     
/* 37 */     if (!GoalUtil.canSee(this.entity, this.target)) {
/* 38 */       return false;
/*    */     }
/*    */     
/* 41 */     if (GoalUtil.isWithinDistance((LivingEntity)this.entity, this.target, this.distance)) {
/* 42 */       return false;
/*    */     }
/*    */     
/* 45 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinueToUseWrapper() {
/* 50 */     return !this.fired;
/*    */   }
/*    */ 
/*    */   
/*    */   public void startWrapper() {
/* 55 */     this.fired = false;
/* 56 */     this.entity.func_184598_c(Hand.MAIN_HAND);
/* 57 */     GoalUtil.lookAtEntity(this.entity, (Entity)this.target);
/*    */   }
/*    */ 
/*    */   
/*    */   public void tickWrapper() {
/* 62 */     this.chargeup++;
/* 63 */     GoalUtil.lookAtEntity(this.entity, (Entity)this.target);
/* 64 */     if (this.chargeup >= this.maxChargeup && !this.fired) {
/* 65 */       this.entity.func_184597_cx();
/* 66 */       this.fired = true;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void stopWrapper() {}
/*    */ 
/*    */   
/*    */   public LivingEntity getTarget() {
/* 75 */     return this.target;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\sniper\ChargeSniperAbilityGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */