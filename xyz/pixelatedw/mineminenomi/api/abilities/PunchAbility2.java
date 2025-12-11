/*     */ package xyz.pixelatedw.mineminenomi.api.abilities;
/*     */ 
/*     */ import java.util.function.Predicate;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChangeStatsComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.HitTriggerComponent;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ 
/*     */ public abstract class PunchAbility2
/*     */   extends Ability {
/*  13 */   protected final ContinuousComponent continuousComponent = (new ContinuousComponent(this, isParallel())).addStartEvent(90, this::startContinuityEvent).addTickEvent(90, this::tickContinuityEvent).addEndEvent(90, this::endContinuityEvent);
/*  14 */   protected final ChangeStatsComponent statsComponent = new ChangeStatsComponent(this);
/*  15 */   protected final HitTriggerComponent hitTriggerComponent = (new HitTriggerComponent(this)).addTryHitEvent(200, this::tryHitEvent).addOnHitEvent(200, this::onHitEvent);
/*     */   
/*  17 */   private int uses = 0;
/*     */   private boolean markForStopping;
/*     */   
/*     */   public PunchAbility2(AbilityCore<? extends PunchAbility2> core) {
/*  21 */     super((AbilityCore)core);
/*     */     
/*  23 */     this.isNew = true;
/*  24 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.statsComponent, (AbilityComponent)this.hitTriggerComponent });
/*     */     
/*  26 */     addUseEvent(200, this::onUseEvent);
/*     */   }
/*     */   
/*     */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/*  30 */     this.continuousComponent.triggerContinuity(entity, getPunchHoldTime());
/*     */   }
/*     */   
/*     */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/*  34 */     this.uses = 0;
/*  35 */     this.markForStopping = false;
/*     */     
/*  37 */     this.statsComponent.applyModifiers(entity);
/*     */   }
/*     */   
/*     */   private void tickContinuityEvent(LivingEntity entity, IAbility ability) {
/*  41 */     if (this.markForStopping) {
/*  42 */       this.continuousComponent.stopContinuity(entity);
/*  43 */       this.markForStopping = false;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/*  48 */     this.statsComponent.removeModifiers(entity);
/*  49 */     float cooldown = getPunchCooldown();
/*  50 */     if (cooldown > 0.0F) {
/*  51 */       this.cooldownComponent.startCooldown(entity, cooldown);
/*     */     }
/*     */   }
/*     */   
/*     */   private HitTriggerComponent.HitResult tryHitEvent(LivingEntity entity, LivingEntity target, ModDamageSource source, IAbility ability) {
/*  56 */     if (canActivate().test(entity)) {
/*  57 */       return HitTriggerComponent.HitResult.HIT;
/*     */     }
/*     */     
/*  60 */     return HitTriggerComponent.HitResult.PASS;
/*     */   }
/*     */   
/*     */   private boolean onHitEvent(LivingEntity entity, LivingEntity target, ModDamageSource source, IAbility ability) {
/*  64 */     boolean result = true;
/*     */     
/*  66 */     boolean wasMarkedForStopping = this.markForStopping;
/*     */     
/*  68 */     if (wasMarkedForStopping) {
/*  69 */       return result;
/*     */     }
/*     */     
/*  72 */     increaseUses();
/*     */     
/*  74 */     if (!source.isBlocked()) {
/*  75 */       result = onHitEffect(entity, target, source);
/*     */     }
/*     */     
/*  78 */     return result;
/*     */   }
/*     */   
/*     */   public void increaseUses() {
/*  82 */     this.uses++;
/*     */     
/*  84 */     if (getUseLimit() > 0 && this.uses >= getUseLimit())
/*     */     {
/*  86 */       this.markForStopping = true;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getPunchDamage() {
/*  96 */     return 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract float getPunchCooldown();
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract boolean onHitEffect(LivingEntity paramLivingEntity1, LivingEntity paramLivingEntity2, ModDamageSource paramModDamageSource);
/*     */ 
/*     */ 
/*     */   
/*     */   public float getPunchHoldTime() {
/* 110 */     return -1.0F;
/*     */   }
/*     */   
/*     */   public boolean isParallel() {
/* 114 */     return false;
/*     */   }
/*     */   
/*     */   public abstract Predicate<LivingEntity> canActivate();
/*     */   
/*     */   public abstract int getUseLimit();
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\PunchAbility2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */