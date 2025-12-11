/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.supa;
/*    */ 
/*    */ import java.util.EnumSet;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.entity.ai.goal.Goal;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.supa.AtomicRushAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*    */ 
/*    */ public class AtomicRushWrapperGoal extends AbilityWrapperGoal<MobEntity, AtomicRushAbility> {
/*    */   public AtomicRushWrapperGoal(MobEntity entity) {
/* 15 */     super(entity, AtomicRushAbility.INSTANCE);
/* 16 */     func_220684_a(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE, Goal.Flag.LOOK));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUseWrapper() {
/* 21 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 22 */       return false;
/*    */     }
/* 24 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinueToUseWrapper() {
/* 29 */     boolean isActive = ((Boolean)((AtomicRushAbility)getAbility()).getComponent(ModAbilityKeys.CONTINUOUS).map(ContinuousComponent::isContinuous).orElse(Boolean.valueOf(false))).booleanValue();
/* 30 */     if (!isActive) {
/* 31 */       return false;
/*    */     }
/* 33 */     return true;
/*    */   }
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
/* 47 */     ((AtomicRushAbility)getAbility()).getComponent(ModAbilityKeys.CONTINUOUS).ifPresent(comp -> comp.stopContinuity((LivingEntity)this.entity));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\supa\AtomicRushWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */