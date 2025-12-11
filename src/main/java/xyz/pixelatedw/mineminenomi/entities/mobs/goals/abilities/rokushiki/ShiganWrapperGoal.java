/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.rokushiki;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.rokushiki.ShiganAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*    */ 
/*    */ public class ShiganWrapperGoal
/*    */   extends AbilityWrapperGoal<MobEntity, ShiganAbility> {
/* 11 */   private float distance = 5.0F;
/*    */   
/*    */   public ShiganWrapperGoal(MobEntity entity) {
/* 14 */     super(entity, ShiganAbility.INSTANCE);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUseWrapper() {
/* 19 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 20 */       return false;
/*    */     }
/*    */     
/* 23 */     if (!this.entity.func_184614_ca().func_190926_b()) {
/* 24 */       return false;
/*    */     }
/*    */     
/* 27 */     LivingEntity target = this.entity.func_70638_az();
/*    */     
/* 29 */     if (GoalUtil.isOutsideDistance((LivingEntity)this.entity, target, this.distance)) {
/* 30 */       return false;
/*    */     }
/*    */     
/* 33 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinueToUseWrapper() {
/* 38 */     return true;
/*    */   }
/*    */   
/*    */   public void startWrapper() {}
/*    */   
/*    */   public void tickWrapper() {}
/*    */   
/*    */   public void stopWrapper() {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\rokushiki\ShiganWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */