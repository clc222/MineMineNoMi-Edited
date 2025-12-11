/*    */ package xyz.pixelatedw.mineminenomi.api.abilities;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.HitTrackerComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.util.TargetsPredicate;
/*    */ 
/*    */ public abstract class DashAbility extends Ability {
/* 16 */   private static final TargetsPredicate TARGET_CHECK = TargetsPredicate.DEFAULT_AREA_CHECK;
/*    */   
/* 18 */   protected final ContinuousComponent continuousComponent = (new ContinuousComponent(this, isParallel())).addStartEvent(200, this::startContinuityEvent).addTickEvent(200, this::duringContinuity).addEndEvent(200, this::endContinuityEvent);
/* 19 */   protected final HitTrackerComponent hitTrackerComponent = new HitTrackerComponent(this);
/* 20 */   protected final RangeComponent rangeComponent = new RangeComponent(this);
/* 21 */   protected final DealDamageComponent dealDamageComponent = new DealDamageComponent(this);
/*    */   
/*    */   public DashAbility(AbilityCore<? extends DashAbility> core) {
/* 24 */     super((AbilityCore)core);
/*    */     
/* 26 */     this.isNew = true;
/* 27 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.hitTrackerComponent, (AbilityComponent)this.rangeComponent, (AbilityComponent)this.dealDamageComponent });
/*    */     
/* 29 */     addCanUseCheck(AbilityHelper::canUseMomentumAbilities);
/*    */     
/* 31 */     addUseEvent(200, this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 35 */     this.continuousComponent.startContinuity(entity, getHoldTime());
/*    */   }
/*    */   
/*    */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/* 39 */     this.hitTrackerComponent.clearHits();
/*    */     
/* 41 */     double dashSpeed = getSpeed();
/*    */     
/* 43 */     Vector3d speed = entity.func_70040_Z().func_216372_d(dashSpeed, 0.0D, dashSpeed);
/* 44 */     AbilityHelper.setDeltaMovement((Entity)entity, speed.field_72450_a, 0.2D, speed.field_72449_c);
/*    */   }
/*    */   
/*    */   private void duringContinuity(LivingEntity entity, IAbility ability) {
/* 48 */     if (entity.func_70089_S()) {
/* 49 */       List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(entity, getRange() + entity.func_213311_cf() / 2.0F, getTargetCheck());
/*    */       
/* 51 */       float damage = getDamage();
/* 52 */       DamageSource source = getDamageSource(entity);
/*    */       
/* 54 */       for (LivingEntity target : targets) {
/* 55 */         if (this.hitTrackerComponent.canHit((Entity)target) && this.dealDamageComponent.hurtTarget(entity, target, damage, source)) {
/* 56 */           onTargetHit(entity, target, damage, source);
/*    */         }
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 63 */     this.cooldownComponent.startCooldown(entity, getDashCooldown());
/*    */   }
/*    */   
/*    */   public abstract void onTargetHit(LivingEntity paramLivingEntity1, LivingEntity paramLivingEntity2, float paramFloat, DamageSource paramDamageSource);
/*    */   
/*    */   public DamageSource getDamageSource(LivingEntity entity) {
/* 69 */     return this.dealDamageComponent.getDamageSource(entity);
/*    */   }
/*    */   
/*    */   public TargetsPredicate getTargetCheck() {
/* 73 */     return TARGET_CHECK;
/*    */   }
/*    */   
/*    */   public boolean isParallel() {
/* 77 */     return false;
/*    */   }
/*    */   
/*    */   public abstract float getDashCooldown();
/*    */   
/*    */   public abstract float getDamage();
/*    */   
/*    */   public abstract float getRange();
/*    */   
/*    */   public abstract double getSpeed();
/*    */   
/*    */   public int getHoldTime() {
/* 89 */     return 10;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\DashAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */