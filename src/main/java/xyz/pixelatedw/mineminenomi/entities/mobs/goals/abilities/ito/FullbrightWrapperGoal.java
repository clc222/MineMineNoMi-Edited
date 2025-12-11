/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.ito;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.ito.FullbrightAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*    */ 
/*    */ public class FullbrightWrapperGoal extends AbilityWrapperGoal<MobEntity, FullbrightAbility> {
/*    */   private LivingEntity target;
/*    */   
/*    */   public FullbrightWrapperGoal(MobEntity entity) {
/* 14 */     super(entity, FullbrightAbility.INSTANCE);
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
/* 25 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinueToUseWrapper() {
/* 30 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 31 */       return false;
/*    */     }
/*    */     
/* 34 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void startWrapper() {
/* 39 */     GoalUtil.lookAtEntity(this.entity, (Entity)this.target);
/* 40 */     ((FullbrightAbility)getAbility()).setTarget(this.target);
/*    */   }
/*    */   
/*    */   public void tickWrapper() {}
/*    */   
/*    */   public void stopWrapper() {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\ito\FullbrightWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */