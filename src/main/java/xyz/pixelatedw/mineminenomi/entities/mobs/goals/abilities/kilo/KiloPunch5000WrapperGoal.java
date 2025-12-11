/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.kilo;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.kilo.KiloPunch5000Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*    */ 
/*    */ public class KiloPunch5000WrapperGoal
/*    */   extends AbilityWrapperGoal<MobEntity, KiloPunch5000Ability> {
/*    */   private LivingEntity target;
/*    */   
/*    */   public KiloPunch5000WrapperGoal(MobEntity entity) {
/* 14 */     super(entity, KiloPunch5000Ability.INSTANCE);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUseWrapper() {
/* 19 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 20 */       return false;
/*    */     }
/*    */     
/* 23 */     if (!this.entity.func_233570_aj_()) {
/* 24 */       return false;
/*    */     }
/*    */     
/* 27 */     this.target = this.entity.func_70638_az();
/*    */     
/* 29 */     if (GoalUtil.isOutsideDistance((LivingEntity)this.entity, this.target, 5.0D)) {
/* 30 */       return false;
/*    */     }
/*    */     
/* 33 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinueToUseWrapper() {
/* 38 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 39 */       return false;
/*    */     }
/*    */     
/* 42 */     if (GoalUtil.isOutsideDistance((LivingEntity)this.entity, this.target, 5.0D)) {
/* 43 */       return false;
/*    */     }
/*    */     
/* 46 */     return true;
/*    */   }
/*    */   
/*    */   public void startWrapper() {}
/*    */   
/*    */   public void tickWrapper() {}
/*    */   
/*    */   public void stopWrapper() {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\kilo\KiloPunch5000WrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */