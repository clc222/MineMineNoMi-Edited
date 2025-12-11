/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.supa;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.supa.SparClawAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*    */ 
/*    */ public class SparClawWrapperGoal extends AbilityWrapperGoal<MobEntity, SparClawAbility> {
/*    */   public SparClawWrapperGoal(MobEntity entity) {
/* 12 */     super(entity, SparClawAbility.INSTANCE);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUseWrapper() {
/* 17 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 18 */       return false;
/*    */     }
/* 20 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinueToUseWrapper() {
/* 25 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void startWrapper() {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void tickWrapper() {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void stopWrapper() {
/* 40 */     ((SparClawAbility)getAbility()).getComponent(ModAbilityKeys.CONTINUOUS).ifPresent(comp -> comp.stopContinuity((LivingEntity)this.entity));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\supa\SparClawWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */