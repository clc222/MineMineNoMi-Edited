/*     */ package xyz.pixelatedw.mineminenomi.abilities.toriphoenix;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
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
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.HitTrackerComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RequireMorphComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.toriphoenix.BlueBirdParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class BlueBirdAbility extends Ability {
/*  34 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "blue_bird", new Pair[] {
/*  35 */         (Pair)ImmutablePair.of("The user builds up momentum through blue flames, to deliver a devastating kick.", null)
/*     */       });
/*     */   
/*     */   private static final int CHARGE_TIME = 20;
/*     */   private static final int HOLD_TIME = 10;
/*     */   private static final int COOLDOWN = 200;
/*     */   private static final int RANGE = 2;
/*     */   private static final int DAMAGE = 25;
/*  43 */   public static final AbilityCore<BlueBirdAbility> INSTANCE = (new AbilityCore.Builder("Blue Bird", AbilityCategory.DEVIL_FRUITS, BlueBirdAbility::new))
/*  44 */     .addDescriptionLine(DESCRIPTION)
/*  45 */     .addDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, RequireMorphComponent.getTooltip()
/*  46 */       }).addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(200.0F), ChargeComponent.getTooltip(20.0F), DealDamageComponent.getTooltip(25.0F)
/*  47 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/*  48 */     .setSourceType(new SourceType[] { SourceType.FIST
/*  49 */       }).build();
/*     */   
/*  51 */   private static final BlueBirdParticleEffect.Details DETAILS = new BlueBirdParticleEffect.Details();
/*     */   
/*  53 */   private final RequireMorphComponent requireMorphComponent = new RequireMorphComponent((IAbility)this, (MorphInfo)ModMorphs.PHOENIX_ASSAULT.get(), new MorphInfo[0]);
/*  54 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this)).addTickEvent(this::duringChargeEvent).addEndEvent(this::endChargeEvent);
/*  55 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addStartEvent(this::startContinuityEvent).addTickEvent(this::duringContinuityEvent).addEndEvent(this::endContinuityEvent);
/*  56 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*  57 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*  58 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*  59 */   private final HitTrackerComponent hitTrackerComponent = new HitTrackerComponent((IAbility)this);
/*     */   
/*     */   public BlueBirdAbility(AbilityCore<BlueBirdAbility> core) {
/*  62 */     super(core);
/*     */     
/*  64 */     this.isNew = true;
/*  65 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.requireMorphComponent, (AbilityComponent)this.chargeComponent, (AbilityComponent)this.continuousComponent, (AbilityComponent)this.animationComponent, (AbilityComponent)this.rangeComponent, (AbilityComponent)this.dealDamageComponent, (AbilityComponent)this.hitTrackerComponent });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  74 */     addCanUseCheck(AbilityHelper::canUseMomentumAbilities);
/*     */     
/*  76 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  80 */     if (!this.continuousComponent.isContinuous()) {
/*  81 */       this.chargeComponent.startCharging(entity, 20.0F);
/*     */     }
/*     */   }
/*     */   
/*     */   private void duringChargeEvent(LivingEntity entity, IAbility ability) {
/*  86 */     DETAILS.setMaxChargeTime(this.chargeComponent.getMaxChargeTime());
/*  87 */     DETAILS.setCurrentChargeTime(this.chargeComponent.getChargeTime());
/*     */     
/*  89 */     WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.BLUE_BIRD.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_(), (ParticleEffect.Details)DETAILS);
/*     */   }
/*     */   
/*     */   private void endChargeEvent(LivingEntity entity, IAbility ability) {
/*  93 */     this.continuousComponent.startContinuity(entity, 10.0F);
/*     */   }
/*     */   
/*     */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/*  97 */     this.animationComponent.start(entity, ModAnimations.PHOENIX_KICK);
/*  98 */     this.hitTrackerComponent.clearHits();
/*     */   }
/*     */   
/*     */   private void duringContinuityEvent(LivingEntity entity, IAbility ability) {
/* 102 */     float time = this.continuousComponent.getContinueTime();
/*     */     
/* 104 */     Vector3d velocity = entity.func_70040_Z().func_216369_h(new Vector3d(5.0D * time / 10.0D, 5.0D, 5.0D * time / 10.0D));
/*     */     
/* 106 */     AbilityHelper.setDeltaMovement((Entity)entity, velocity);
/*     */     
/* 108 */     List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(entity, 2.0F);
/*     */     
/* 110 */     for (LivingEntity target : targets) {
/* 111 */       if (this.hitTrackerComponent.canHit((Entity)target)) {
/* 112 */         this.dealDamageComponent.hurtTarget(entity, target, 25.0F);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 118 */     this.animationComponent.stop(entity);
/* 119 */     this.cooldownComponent.startCooldown(entity, 200.0F);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\toriphoenix\BlueBirdAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */