/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.cyborg;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.cyborg.RadicalBeamAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ 
/*    */ public class RadicalBeamWrapperGoal extends AbilityWrapperGoal<MobEntity, RadicalBeamAbility> {
/* 16 */   private double minDistance = 1.0D; private LivingEntity target;
/* 17 */   private double maxDistance = 50.0D;
/*    */   
/*    */   public RadicalBeamWrapperGoal(MobEntity entity) {
/* 20 */     super(entity, RadicalBeamAbility.INSTANCE);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUseWrapper() {
/* 25 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 26 */       return false;
/*    */     }
/*    */     
/* 29 */     this.target = this.entity.func_70638_az();
/*    */     
/* 31 */     if (!GoalUtil.canSee(this.entity, this.target)) {
/* 32 */       return false;
/*    */     }
/*    */     
/* 35 */     if (GoalUtil.isWithinDistance((LivingEntity)this.entity, this.target, this.minDistance)) {
/* 36 */       return false;
/*    */     }
/*    */     
/* 39 */     if (GoalUtil.isOutsideDistance((LivingEntity)this.entity, this.target, this.maxDistance)) {
/* 40 */       return false;
/*    */     }
/*    */     
/* 43 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinueToUseWrapper() {
/* 48 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void startWrapper() {
/* 53 */     ((AnimationComponent)((RadicalBeamAbility)getAbility()).getComponent(ModAbilityKeys.ANIMATION).get()).start((LivingEntity)this.entity, ModAnimations.HEAD_LASER_CHARGE);
/* 54 */     GoalUtil.lookAtEntity(this.entity, (Entity)this.target);
/*    */   }
/*    */ 
/*    */   
/*    */   public void tickWrapper() {
/* 59 */     this.entity.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 10, 0));
/* 60 */     GoalUtil.lookAtEntity(this.entity, (Entity)this.target);
/*    */   }
/*    */ 
/*    */   
/*    */   public void stopWrapper() {
/* 65 */     ((AnimationComponent)((RadicalBeamAbility)getAbility()).getComponent(ModAbilityKeys.ANIMATION).get()).stop((LivingEntity)this.entity);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\cyborg\RadicalBeamWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */