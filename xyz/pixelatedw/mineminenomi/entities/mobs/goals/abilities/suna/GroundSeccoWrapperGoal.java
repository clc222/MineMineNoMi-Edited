/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.suna;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.suna.GroundSeccoAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*    */ 
/*    */ public class GroundSeccoWrapperGoal extends AbilityWrapperGoal<MobEntity, GroundSeccoAbility> {
/*    */   public GroundSeccoWrapperGoal(MobEntity entity) {
/* 11 */     super(entity, GroundSeccoAbility.INSTANCE);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUseWrapper() {
/* 16 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 17 */       return false;
/*    */     }
/*    */     
/* 20 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinueToUseWrapper() {
/* 25 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void startWrapper() {
/* 30 */     ((GroundSeccoAbility)getAbility()).changeRandomPattern((LivingEntity)this.entity);
/*    */   }
/*    */   
/*    */   public void tickWrapper() {}
/*    */   
/*    */   public void stopWrapper() {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\suna\GroundSeccoWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */