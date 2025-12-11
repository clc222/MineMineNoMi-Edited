/*     */ package xyz.pixelatedw.mineminenomi.abilities;
/*     */ 
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.GrabEntityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.HitTriggerComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.PoolComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityPools;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ 
/*     */ public class GrabLockAbility
/*     */   extends Ability {
/*  29 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "grab_lock", new Pair[] {
/*  30 */         (Pair)ImmutablePair.of("Grabs an opponent from the back and keeps them pinned like that.", null)
/*     */       });
/*     */   
/*     */   private static final int HOLD_TIME = 40;
/*     */   private static final int PULL_TIME = 200;
/*     */   private static final float COOLDOWN = 200.0F;
/*  36 */   public static final AbilityCore<GrabLockAbility> INSTANCE = (new AbilityCore.Builder("Grab Lock", AbilityCategory.STYLE, GrabLockAbility::new))
/*  37 */     .addDescriptionLine(DESCRIPTION)
/*  38 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(200.0F), ContinuousComponent.getTooltip(40.0F)
/*  39 */       }).build();
/*     */   
/*  41 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addTickEvent(this::duringContinuityEvent).addEndEvent(this::endContinuityEvent);
/*  42 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*  43 */   private final GrabEntityComponent grabComponent = (new GrabEntityComponent((IAbility)this, true, true, true, 2.0F))
/*  44 */     .addPullStartEvent(this::onPullStart)
/*  45 */     .addPullEndEvent(this::onPullEnd)
/*  46 */     .addGrabEvent(this::grabEvent)
/*  47 */     .addReleaseEvent(this::releaseEvent);
/*  48 */   private final HitTriggerComponent hitTriggerComponent = (new HitTriggerComponent((IAbility)this)).addTryHitEvent(this::tryHitEvent).addOnHitEvent(this::onHitEvent);
/*  49 */   private final PoolComponent poolComponent = new PoolComponent((IAbility)this, ModAbilityPools.GRAB_ABILITY, new xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool2[0]);
/*     */   
/*     */   public GrabLockAbility(AbilityCore<GrabLockAbility> core) {
/*  52 */     super(core);
/*     */     
/*  54 */     this.isNew = true;
/*  55 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.animationComponent, (AbilityComponent)this.grabComponent, (AbilityComponent)this.hitTriggerComponent, (AbilityComponent)this.poolComponent });
/*     */     
/*  57 */     addCanUseCheck(AbilityHelper::canUseBrawlerAbilities);
/*  58 */     addUseEvent(this::onUseEvent);
/*     */   }
/*     */   
/*     */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/*  62 */     this.continuousComponent.triggerContinuity(entity);
/*     */   }
/*     */   
/*     */   private HitTriggerComponent.HitResult tryHitEvent(LivingEntity entity, LivingEntity target, ModDamageSource source, IAbility ability) {
/*  66 */     if (!this.continuousComponent.isContinuous() || this.grabComponent.hasGrabbedEntity()) {
/*  67 */       return HitTriggerComponent.HitResult.PASS;
/*     */     }
/*     */     
/*  70 */     return HitTriggerComponent.HitResult.HIT;
/*     */   }
/*     */   
/*     */   private boolean onHitEvent(LivingEntity entity, LivingEntity target, ModDamageSource source, IAbility ability) {
/*  74 */     if (this.grabComponent.grabManually(entity, target)) {
/*  75 */       this.grabComponent.startPulling(entity);
/*     */     }
/*     */     
/*  78 */     target.func_195064_c(new EffectInstance((Effect)ModEffects.ANTI_KNOCKBACK.get(), 1));
/*     */     
/*  80 */     return false;
/*     */   }
/*     */   
/*     */   public void onPullStart(LivingEntity entity, IAbility ability) {
/*  84 */     this.continuousComponent.setThresholdTime(entity, 200.0F);
/*     */   }
/*     */   
/*     */   public void onPullEnd(LivingEntity entity, IAbility ability) {
/*  88 */     if (this.grabComponent.canContinueGrab(entity)) {
/*  89 */       this.continuousComponent.setThresholdTime(entity, 40.0F);
/*     */     }
/*     */   }
/*     */   
/*     */   private void duringContinuityEvent(LivingEntity entity, IAbility ability) {
/*  94 */     if (this.grabComponent.getState() == GrabEntityComponent.GrabState.IDLE) {
/*     */       return;
/*     */     }
/*     */     
/*  98 */     if (canUse(entity).isFail() || !this.grabComponent.canContinueGrab(entity)) {
/*  99 */       this.continuousComponent.stopContinuity(entity);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 104 */     LivingEntity target = this.grabComponent.getGrabbedEntity();
/*     */     
/* 106 */     if (target != null) {
/* 107 */       entity.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 5, 1));
/* 108 */       target.func_195064_c(new EffectInstance((Effect)ModEffects.GRABBED.get(), 5, 3));
/*     */     } 
/*     */   }
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 113 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 117 */     this.grabComponent.release(entity);
/* 118 */     this.animationComponent.stop(entity);
/* 119 */     this.cooldownComponent.startCooldown(entity, 200.0F);
/*     */   }
/*     */   
/*     */   private boolean grabEvent(LivingEntity entity, LivingEntity target, IAbility ability) {
/* 123 */     this.animationComponent.start(entity, ModAnimations.POINT_ARMS);
/* 124 */     return true;
/*     */   }
/*     */   
/*     */   private void releaseEvent(LivingEntity entity, LivingEntity target, IAbility ability) {}
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\GrabLockAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */