/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.haki;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.haki.KenbunshokuHakiFutureSightAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*    */ 
/*    */ public class KenbunshokuHakiFutureSightWrapperGoal
/*    */   extends AbilityWrapperGoal<MobEntity, KenbunshokuHakiFutureSightAbility> {
/*    */   private LivingEntity target;
/* 12 */   private double distance = 30.0D;
/*    */   
/*    */   public KenbunshokuHakiFutureSightWrapperGoal(MobEntity entity) {
/* 15 */     super(entity, KenbunshokuHakiFutureSightAbility.INSTANCE);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUseWrapper() {
/* 20 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 21 */       return false;
/*    */     }
/*    */     
/* 24 */     this.target = this.entity.func_70638_az();
/*    */     
/* 26 */     if (GoalUtil.isOutsideDistance((LivingEntity)this.entity, this.target, this.distance)) {
/* 27 */       return false;
/*    */     }
/*    */     
/* 30 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinueToUseWrapper() {
/* 35 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 36 */       return false;
/*    */     }
/*    */     
/* 39 */     double minDistance = this.distance;
/* 40 */     if (((KenbunshokuHakiFutureSightAbility)getAbility()).getProtectionStacks() > 8) {
/* 41 */       minDistance /= 2.0D;
/*    */     }
/*    */     
/* 44 */     if (GoalUtil.isOutsideDistance((LivingEntity)this.entity, this.target, minDistance)) {
/* 45 */       return false;
/*    */     }
/*    */     
/* 48 */     return true;
/*    */   }
/*    */   
/*    */   public void startWrapper() {}
/*    */   
/*    */   public void tickWrapper() {}
/*    */   
/*    */   public void stopWrapper() {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\haki\KenbunshokuHakiFutureSightWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */