/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.doru;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.doru.DoruDoruBallAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*    */ 
/*    */ public class DoruDoruBallWrapperGoal
/*    */   extends AbilityWrapperGoal<MobEntity, DoruDoruBallAbility> {
/*    */   private LivingEntity target;
/* 12 */   private double distance = 5.0D;
/* 13 */   private int triggerHits = 5;
/*    */   private int hits;
/*    */   private float previousHealth;
/*    */   
/*    */   public DoruDoruBallWrapperGoal(MobEntity entity) {
/* 18 */     super(entity, DoruDoruBallAbility.INSTANCE);
/*    */   }
/*    */   
/*    */   public DoruDoruBallWrapperGoal setMinTriggerHits(int hits) {
/* 22 */     this.triggerHits = hits;
/* 23 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUseWrapper() {
/* 28 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 29 */       return false;
/*    */     }
/*    */     
/* 32 */     this.target = this.entity.func_70638_az();
/*    */     
/* 34 */     if (!GoalUtil.canSee(this.entity, this.target)) {
/* 35 */       return false;
/*    */     }
/*    */     
/* 38 */     if (this.previousHealth == 0.0F) {
/* 39 */       this.previousHealth = this.entity.func_110143_aJ();
/*    */     } else {
/*    */       
/* 42 */       if (this.entity.func_110143_aJ() >= this.entity.func_110138_aP()) {
/* 43 */         return false;
/*    */       }
/*    */       
/* 46 */       if (this.entity.func_110143_aJ() < this.previousHealth) {
/* 47 */         this.hits++;
/* 48 */         this.previousHealth = this.entity.func_110143_aJ();
/*    */       }
/* 50 */       else if (this.entity.func_110143_aJ() > this.previousHealth) {
/* 51 */         this.previousHealth = this.entity.func_110143_aJ();
/*    */       } 
/*    */     } 
/*    */     
/* 55 */     if (this.hits < this.triggerHits) {
/* 56 */       return false;
/*    */     }
/*    */     
/* 59 */     if (GoalUtil.isOutsideDistance((LivingEntity)this.entity, this.target, this.distance)) {
/* 60 */       return false;
/*    */     }
/*    */     
/* 63 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinueToUseWrapper() {
/* 68 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 69 */       return false;
/*    */     }
/*    */     
/* 72 */     if (GoalUtil.isOutsideDistance((LivingEntity)this.entity, this.target, this.distance)) {
/* 73 */       return false;
/*    */     }
/*    */     
/* 76 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void startWrapper() {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void tickWrapper() {}
/*    */ 
/*    */   
/*    */   public void stopWrapper() {
/* 89 */     this.hits = 0;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\doru\DoruDoruBallWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */