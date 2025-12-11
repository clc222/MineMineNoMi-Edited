/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.brawler.TackleAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*    */ 
/*    */ public class TackleWrapperGoal extends AbilityWrapperGoal<MobEntity, TackleAbility> {
/*    */   private LivingEntity target;
/* 12 */   private float distance = 10.0F;
/*    */   
/*    */   public TackleWrapperGoal(MobEntity entity) {
/* 15 */     super(entity, TackleAbility.INSTANCE);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUseWrapper() {
/* 20 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 21 */       return false;
/*    */     }
/*    */     
/* 24 */     if (!GoalUtil.canMove((LivingEntity)this.entity)) {
/* 25 */       return false;
/*    */     }
/*    */     
/* 28 */     this.target = this.entity.func_70638_az();
/*    */     
/* 30 */     if (!GoalUtil.canSee(this.entity, this.target)) {
/* 31 */       return false;
/*    */     }
/*    */     
/* 34 */     if (GoalUtil.isOutsideDistance((LivingEntity)this.entity, this.target, this.distance)) {
/* 35 */       return false;
/*    */     }
/*    */     
/* 38 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinueToUseWrapper() {
/* 43 */     if (!canUseWrapper()) {
/* 44 */       return false;
/*    */     }
/*    */     
/* 47 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void startWrapper() {
/* 52 */     this.entity.func_70661_as().func_75499_g();
/* 53 */     GoalUtil.lookAtEntity(this.entity, (Entity)this.target);
/*    */   }
/*    */ 
/*    */   
/*    */   public void tickWrapper() {
/* 58 */     this.entity.func_70661_as().func_75499_g();
/* 59 */     GoalUtil.lookAtEntity(this.entity, (Entity)this.target);
/*    */   }
/*    */   
/*    */   public void stopWrapper() {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\brawler\TackleWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */