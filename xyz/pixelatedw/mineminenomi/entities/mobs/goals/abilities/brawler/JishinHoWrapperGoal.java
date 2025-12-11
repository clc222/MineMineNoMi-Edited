/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.brawler.JishinHoAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*    */ 
/*    */ public class JishinHoWrapperGoal
/*    */   extends AbilityWrapperGoal<MobEntity, JishinHoAbility> {
/* 11 */   private float distance = 5.0F;
/*    */   
/*    */   public JishinHoWrapperGoal(MobEntity entity) {
/* 14 */     super(entity, JishinHoAbility.INSTANCE);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUseWrapper() {
/* 19 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 20 */       return false;
/*    */     }
/*    */     
/* 23 */     LivingEntity target = this.entity.func_70638_az();
/*    */     
/* 25 */     if (GoalUtil.isOutsideDistance((LivingEntity)this.entity, target, this.distance)) {
/* 26 */       return false;
/*    */     }
/*    */     
/* 29 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinueToUseWrapper() {
/* 34 */     return true;
/*    */   }
/*    */   
/*    */   public void startWrapper() {}
/*    */   
/*    */   public void tickWrapper() {}
/*    */   
/*    */   public void stopWrapper() {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\brawler\JishinHoWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */