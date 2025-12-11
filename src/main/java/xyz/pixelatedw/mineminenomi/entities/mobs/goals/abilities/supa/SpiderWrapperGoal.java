/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.supa;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.supa.SpiderAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class SpiderWrapperGoal extends AbilityWrapperGoal<MobEntity, SpiderAbility> {
/*    */   private LivingEntity target;
/* 13 */   private double distance = 6.0D;
/* 14 */   private float lastHurtTicks = WyHelper.secondsToTicks(5.0F);
/*    */   
/*    */   public SpiderWrapperGoal(MobEntity entity) {
/* 17 */     super(entity, SpiderAbility.INSTANCE);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUseWrapper() {
/* 22 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 23 */       return false;
/*    */     }
/*    */     
/* 26 */     this.target = this.entity.func_70638_az();
/*    */     
/* 28 */     if (GoalUtil.isOutsideDistance((LivingEntity)this.entity, this.target, this.distance)) {
/* 29 */       return false;
/*    */     }
/*    */     
/* 32 */     if (this.entity.func_142015_aE() <= 0 || this.entity.field_70173_aa > this.entity.func_142015_aE() + this.lastHurtTicks) {
/* 33 */       return false;
/*    */     }
/*    */     
/* 36 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinueToUseWrapper() {
/* 41 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 42 */       return false;
/*    */     }
/*    */     
/* 45 */     if (GoalUtil.isOutsideDistance((LivingEntity)this.entity, this.target, this.distance)) {
/* 46 */       return false;
/*    */     }
/* 48 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void startWrapper() {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void tickWrapper() {
/* 58 */     GoalUtil.lookAtEntity(this.entity, (Entity)this.target);
/*    */   }
/*    */   
/*    */   public void stopWrapper() {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\supa\SpiderWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */