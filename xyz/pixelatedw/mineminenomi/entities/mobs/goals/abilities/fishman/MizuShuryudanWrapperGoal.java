/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.fishman;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.fishmankarate.MizuShuryudanAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*    */ 
/*    */ public class MizuShuryudanWrapperGoal extends AbilityWrapperGoal<MobEntity, MizuShuryudanAbility> {
/*    */   private LivingEntity target;
/* 12 */   private double minDistance = 5.0D;
/* 13 */   private double maxDistance = 25.0D;
/*    */   
/*    */   public MizuShuryudanWrapperGoal(MobEntity entity) {
/* 16 */     super(entity, MizuShuryudanAbility.INSTANCE);
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
/* 27 */     if (GoalUtil.isWithinDistance((LivingEntity)this.entity, this.target, this.minDistance)) {
/* 28 */       return false;
/*    */     }
/*    */     
/* 31 */     if (GoalUtil.isOutsideDistance((LivingEntity)this.entity, this.target, this.maxDistance)) {
/* 32 */       return false;
/*    */     }
/*    */     
/* 35 */     if (!GoalUtil.canSee(this.entity, this.target)) {
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


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\fishman\MizuShuryudanWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */