/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.ito;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.ito.TamaitoAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*    */ 
/*    */ public class TamaitoWrapperGoal extends AbilityWrapperGoal<MobEntity, TamaitoAbility> {
/*    */   private LivingEntity target;
/*    */   
/*    */   public TamaitoWrapperGoal(MobEntity entity) {
/* 14 */     super(entity, TamaitoAbility.INSTANCE);
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
/* 30 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void startWrapper() {
/* 35 */     GoalUtil.lookAtEntity(this.entity, (Entity)this.target);
/*    */   }
/*    */   
/*    */   public void tickWrapper() {}
/*    */   
/*    */   public void stopWrapper() {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\ito\TamaitoWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */