/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.WootzNetLauncherAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*    */ 
/*    */ public class WootzNetLauncherWrapperGoal
/*    */   extends AbilityWrapperGoal<MobEntity, WootzNetLauncherAbility> {
/*    */   public WootzNetLauncherWrapperGoal(MobEntity entity) {
/* 13 */     super(entity, WootzNetLauncherAbility.INSTANCE);
/*    */   }
/*    */   private LivingEntity target;
/*    */   
/*    */   public boolean canUseWrapper() {
/* 18 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 19 */       return false;
/*    */     }
/*    */     
/* 22 */     this.target = this.entity.func_70638_az();
/*    */     
/* 24 */     if (!GoalUtil.canSee(this.entity, this.target)) {
/* 25 */       return false;
/*    */     }
/*    */     
/* 28 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinueToUseWrapper() {
/* 33 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void startWrapper() {
/* 38 */     GoalUtil.lookAtEntity(this.entity, (Entity)this.target);
/*    */   }
/*    */   
/*    */   public void tickWrapper() {}
/*    */   
/*    */   public void stopWrapper() {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\WootzNetLauncherWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */