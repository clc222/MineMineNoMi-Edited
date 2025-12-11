/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.TailSpinAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*    */ 
/*    */ public class TailSpinWrapperGoal
/*    */   extends AbilityWrapperGoal<MobEntity, TailSpinAbility> {
/*    */   private float range;
/*    */   
/*    */   public TailSpinWrapperGoal(MobEntity entity) {
/* 14 */     super(entity, TailSpinAbility.INSTANCE);
/* 15 */     this.range = 8.0F;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canUseWrapper() {
/* 21 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 22 */       return false;
/*    */     }
/*    */     
/* 25 */     LivingEntity target = this.entity.func_70638_az();
/*    */     
/* 27 */     if (GoalUtil.isOutsideDistance((LivingEntity)this.entity, target, this.range)) {
/* 28 */       return false;
/*    */     }
/*    */     
/* 31 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinueToUseWrapper() {
/* 36 */     return true;
/*    */   }
/*    */   
/*    */   public void startWrapper() {}
/*    */   
/*    */   public void tickWrapper() {}
/*    */   
/*    */   public void stopWrapper() {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\TailSpinWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */