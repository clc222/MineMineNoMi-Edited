/*     */ package xyz.pixelatedw.mineminenomi.abilities.ryubrachiosaurus;
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
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.GrabEntityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.HitTriggerComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.PoolComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RequireMorphComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.Interval;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityPools;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*     */ 
/*     */ public class BrachioGrabAbility extends Ability {
/*  36 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "brachio_grab", new Pair[] {
/*  37 */         (Pair)ImmutablePair.of("Grabs an opponent and squashes them.", null)
/*     */       });
/*     */   
/*     */   private static final int COOLDOWN = 300;
/*     */   private static final int CHARGE_TIME = 140;
/*     */   private static final int PULL_TIME = 200;
/*     */   private static final float DAMAGE = 6.0F;
/*  44 */   public static final AbilityCore<BrachioGrabAbility> INSTANCE = (new AbilityCore.Builder("Brachio Grab", AbilityCategory.DEVIL_FRUITS, BrachioGrabAbility::new))
/*  45 */     .addDescriptionLine(DESCRIPTION)
/*  46 */     .addDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, RequireMorphComponent.getTooltip()
/*  47 */       }).addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(300.0F), ChargeComponent.getTooltip(140.0F)
/*  48 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/*  49 */     .setSourceType(new SourceType[] { SourceType.FIST
/*  50 */       }).build();
/*     */   
/*  52 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*  53 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this)).addStartEvent(100, this::onChargeStart).addTickEvent(this::onChargeTick).addEndEvent(this::onChargeEnd);
/*  54 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*  55 */   private final RequireMorphComponent requireMorphComponent = new RequireMorphComponent((IAbility)this, (MorphInfo)ModMorphs.BRACHIO_HEAVY.get(), new MorphInfo[0]);
/*  56 */   private final GrabEntityComponent grabComponent = (new GrabEntityComponent((IAbility)this, true, true, true, 2.0F)).addPullStartEvent(this::onPullStart).addPullEndEvent(this::onPullEnd);
/*  57 */   private final HitTriggerComponent hitTriggerComponent = (new HitTriggerComponent((IAbility)this)).addTryHitEvent(this::tryHitEvent).addOnHitEvent(this::onHitEvent);
/*  58 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addEndEvent(this::onContinuityEnd);
/*  59 */   private final PoolComponent poolComponent = new PoolComponent((IAbility)this, ModAbilityPools.GRAB_ABILITY, new xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool2[0]);
/*     */   
/*  61 */   private Interval hurtInterval = new Interval(30);
/*     */   
/*     */   public BrachioGrabAbility(AbilityCore<BrachioGrabAbility> core) {
/*  64 */     super(core);
/*     */     
/*  66 */     this.isNew = true;
/*     */     
/*  68 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.dealDamageComponent, (AbilityComponent)this.chargeComponent, (AbilityComponent)this.animationComponent, (AbilityComponent)this.requireMorphComponent, (AbilityComponent)this.continuousComponent, (AbilityComponent)this.grabComponent, (AbilityComponent)this.hitTriggerComponent, (AbilityComponent)this.poolComponent });
/*     */     
/*  70 */     addCanUseCheck(AbilityHelper::canUseBrawlerAbilities);
/*     */     
/*  72 */     addUseEvent(this::onUseEvent);
/*     */   }
/*     */   
/*     */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/*  76 */     if (this.chargeComponent.isCharging()) {
/*     */       return;
/*     */     }
/*     */     
/*  80 */     if (this.continuousComponent.isContinuous()) {
/*  81 */       this.grabComponent.release(entity);
/*  82 */       this.continuousComponent.stopContinuity(entity);
/*  83 */     } else if (this.grabComponent.getState() == GrabEntityComponent.GrabState.IDLE && this.grabComponent.grabNearest(entity, false)) {
/*  84 */       this.grabComponent.triggerPulling(entity);
/*     */     } else {
/*  86 */       this.continuousComponent.startContinuity(entity);
/*     */     } 
/*     */   }
/*     */   
/*     */   private HitTriggerComponent.HitResult tryHitEvent(LivingEntity entity, LivingEntity target, ModDamageSource source, IAbility ability) {
/*  91 */     if (!this.continuousComponent.isContinuous() || this.grabComponent.hasGrabbedEntity()) {
/*  92 */       return HitTriggerComponent.HitResult.PASS;
/*     */     }
/*     */     
/*  95 */     return HitTriggerComponent.HitResult.HIT;
/*     */   }
/*     */   
/*     */   private boolean onHitEvent(LivingEntity entity, LivingEntity target, ModDamageSource source, IAbility ability) {
/*  99 */     if (this.grabComponent.grabManually(entity, target)) {
/* 100 */       this.grabComponent.startPulling(entity);
/*     */     }
/*     */     
/* 103 */     target.func_195064_c(new EffectInstance((Effect)ModEffects.ANTI_KNOCKBACK.get(), 1));
/*     */     
/* 105 */     return false;
/*     */   }
/*     */   
/*     */   public void onPullStart(LivingEntity entity, IAbility ability) {
/* 109 */     this.continuousComponent.setThresholdTime(entity, 200.0F);
/*     */   }
/*     */   
/*     */   public void onPullEnd(LivingEntity entity, IAbility ability) {
/* 113 */     this.continuousComponent.stopContinuity(entity);
/*     */     
/* 115 */     if (this.grabComponent.canContinueGrab(entity)) {
/* 116 */       this.chargeComponent.startCharging(entity, 140.0F);
/*     */     }
/*     */   }
/*     */   
/*     */   private void onContinuityEnd(LivingEntity entity, IAbility ability) {
/* 121 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 125 */     if (this.grabComponent.getState() != GrabEntityComponent.GrabState.GRABBED) {
/* 126 */       this.grabComponent.release(entity);
/*     */     }
/*     */     
/* 129 */     if (!this.grabComponent.canContinueGrab(entity)) {
/* 130 */       this.cooldownComponent.startCooldown(entity, 300.0F);
/*     */     }
/*     */   }
/*     */   
/*     */   public void onChargeStart(LivingEntity entity, IAbility ability) {
/* 135 */     this.hurtInterval.restartIntervalToZero();
/* 136 */     this.animationComponent.start(entity, ModAnimations.POINT_ARMS);
/*     */   }
/*     */   
/*     */   public void onChargeTick(LivingEntity entity, IAbility ability) {
/* 140 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 144 */     if (canUse(entity).isFail() || !this.grabComponent.canContinueGrab(entity)) {
/* 145 */       this.chargeComponent.stopCharging(entity);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 150 */     LivingEntity target = this.grabComponent.getGrabbedEntity();
/*     */     
/* 152 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 2, 1));
/*     */     
/* 154 */     if (this.hurtInterval.canTick()) {
/* 155 */       this.dealDamageComponent.hurtTarget(entity, target, 6.0F);
/*     */     }
/*     */   }
/*     */   
/*     */   public void onChargeEnd(LivingEntity entity, IAbility ability) {
/* 160 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 164 */     this.grabComponent.release(entity);
/* 165 */     this.animationComponent.stop(entity);
/*     */     
/* 167 */     this.cooldownComponent.startCooldown(entity, 300.0F);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\ryubrachiosaurus\BrachioGrabAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */