/*    */ package xyz.pixelatedw.mineminenomi.api.abilities;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RepeaterComponent;
/*    */ 
/*    */ public abstract class RepeaterAbility2
/*    */   extends Ability {
/* 11 */   protected final ContinuousComponent continuousComponent = (new ContinuousComponent(this, true)).addStartEvent(200, this::onContinuityStart);
/* 12 */   protected final RepeaterComponent repeaterComponent = (new RepeaterComponent(this)).addTriggerEvent(200, this::onRepeaterTrigger).addStopEvent(200, this::onRepeaterStop);
/* 13 */   protected final ProjectileComponent projectileComponent = new ProjectileComponent(this, this::getProjectileFactory);
/*    */   
/* 15 */   private IOnTriggerShootEvent customShootLogic = null;
/*    */   
/*    */   public RepeaterAbility2(AbilityCore<? extends RepeaterAbility2> core) {
/* 18 */     super((AbilityCore)core);
/*    */     
/* 20 */     this.isNew = true;
/* 21 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.repeaterComponent, (AbilityComponent)this.projectileComponent });
/*    */     
/* 23 */     addUseEvent(200, this::onUseEvent);
/*    */   }
/*    */   
/*    */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/* 27 */     if (this.continuousComponent.isContinuous()) {
/* 28 */       this.repeaterComponent.stop(entity);
/*    */       
/*    */       return;
/*    */     } 
/* 32 */     this.continuousComponent.triggerContinuity(entity);
/*    */   }
/*    */   
/*    */   private void onContinuityStart(LivingEntity entity, IAbility ability) {
/* 36 */     if (entity.field_70170_p.field_72995_K) {
/*    */       return;
/*    */     }
/*    */     
/* 40 */     this.repeaterComponent.start(entity, getMaxTriggers(), getTriggerInterval());
/*    */   }
/*    */   
/*    */   private void onRepeaterTrigger(LivingEntity entity, IAbility ability) {
/* 44 */     if (canUse(entity).isFail()) {
/* 45 */       this.repeaterComponent.stop(entity);
/*    */     }
/*    */     
/* 48 */     if (this.customShootLogic != null) {
/* 49 */       this.customShootLogic.customShootLogic(entity);
/*    */     } else {
/*    */       
/* 52 */       this.projectileComponent.shoot(entity, getProjectileSpeed(), getProjectileSpread());
/*    */     } 
/*    */   }
/*    */   
/*    */   private void onRepeaterStop(LivingEntity entity, IAbility ability) {
/* 57 */     this.continuousComponent.stopContinuity(entity);
/*    */     
/* 59 */     this.cooldownComponent.startCooldown(entity, getRepeaterCooldown());
/*    */   }
/*    */   
/*    */   public void setCustomShootLogic(IOnTriggerShootEvent event) {
/* 63 */     this.customShootLogic = event;
/*    */   }
/*    */   
/*    */   public float getProjectileSpeed() {
/* 67 */     return 2.0F;
/*    */   }
/*    */   
/*    */   public float getProjectileSpread() {
/* 71 */     return 1.0F;
/*    */   }
/*    */   
/*    */   public abstract int getMaxTriggers();
/*    */   
/*    */   public abstract int getTriggerInterval();
/*    */   
/*    */   public abstract float getRepeaterCooldown();
/*    */   
/*    */   public abstract <P extends xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity> P getProjectileFactory(LivingEntity paramLivingEntity);
/*    */   
/*    */   @FunctionalInterface
/*    */   public static interface IOnTriggerShootEvent {
/*    */     void customShootLogic(LivingEntity param1LivingEntity);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\RepeaterAbility2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */