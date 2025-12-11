/*     */ package xyz.pixelatedw.mineminenomi.abilities.ryupteranodon;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
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
/*     */ import xyz.pixelatedw.mineminenomi.api.util.Interval;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ 
/*     */ public class TankyudonAbility extends Ability {
/*  31 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "tankyudon", new Pair[] {
/*  32 */         (Pair)ImmutablePair.of("Flying into enemies at great speeds deals damage and knocks them back.", null)
/*     */       });
/*     */   
/*     */   private static final int COOLDOWN = 200;
/*     */   private static final float RANGE = 1.2F;
/*     */   private static final double PUSH_FORCE = 2.2D;
/*     */   private static final float DAMAGE = 10.0F;
/*  39 */   public static final AbilityCore<TankyudonAbility> INSTANCE = (new AbilityCore.Builder("Tankyudon", AbilityCategory.DEVIL_FRUITS, TankyudonAbility::new))
/*  40 */     .addDescriptionLine(DESCRIPTION)
/*  41 */     .addDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, RequireMorphComponent.getTooltip()
/*  42 */       }).addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(200.0F), ContinuousComponent.getTooltip(), DealDamageComponent.getTooltip(10.0F), RangeComponent.getTooltip(1.2F, RangeComponent.RangeType.LINE)
/*  43 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/*  44 */     .setSourceType(new SourceType[] { SourceType.FIST
/*  45 */       }).build();
/*     */   
/*  47 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this))
/*  48 */     .addStartEvent(this::onContinuityStart)
/*  49 */     .addTickEvent(this::onContinuityTick)
/*  50 */     .addEndEvent(this::onContinuityEnd);
/*  51 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*  52 */   private final HitTrackerComponent hitTrackerComponent = new HitTrackerComponent((IAbility)this);
/*  53 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*  54 */   private final RequireMorphComponent requireMorphComponent = new RequireMorphComponent((IAbility)this, (MorphInfo)ModMorphs.PTERA_FLY.get(), new MorphInfo[0]);
/*     */   
/*  56 */   private Interval clearHitsInterval = new Interval(25);
/*     */   
/*     */   public TankyudonAbility(AbilityCore<TankyudonAbility> core) {
/*  59 */     super(core);
/*     */     
/*  61 */     this.isNew = true;
/*     */     
/*  63 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.dealDamageComponent, (AbilityComponent)this.hitTrackerComponent, (AbilityComponent)this.rangeComponent, (AbilityComponent)this.requireMorphComponent });
/*     */     
/*  65 */     addCanUseCheck(AbilityHelper::canUseMomentumAbilities);
/*     */     
/*  67 */     addUseEvent(this::onUseEvent);
/*     */   }
/*     */   
/*     */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/*  71 */     this.continuousComponent.triggerContinuity(entity, WyHelper.secondsToTicks(10.0F));
/*     */   }
/*     */   
/*     */   private void onContinuityStart(LivingEntity entity, IAbility ability) {
/*  75 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/*  79 */     this.hitTrackerComponent.clearHits();
/*  80 */     this.clearHitsInterval.restartIntervalToZero();
/*     */     
/*  82 */     entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.DASH_ABILITY_SWOOSH_SFX.get(), SoundCategory.PLAYERS, 2.0F, 1.0F);
/*     */   }
/*     */   
/*     */   private void onContinuityTick(LivingEntity entity, IAbility ability) {
/*  86 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/*  90 */     if (canUse(entity).isFail()) {
/*  91 */       this.continuousComponent.stopContinuity(entity);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*  96 */     List<LivingEntity> targets = this.rangeComponent.getTargetsInLine(entity, 1.2F, 1.5F);
/*     */     
/*  98 */     if (this.clearHitsInterval.canTick()) {
/*  99 */       this.hitTrackerComponent.clearHits();
/*     */     }
/*     */     
/* 102 */     for (LivingEntity target : targets) {
/* 103 */       if (this.hitTrackerComponent.canHit((Entity)target) && this.dealDamageComponent.hurtTarget(entity, target, 10.0F)) {
/* 104 */         Vector3d pushVec = entity.func_70040_Z().func_178788_d(target.func_70040_Z()).func_72432_b().func_186678_a(2.2D);
/*     */         
/* 106 */         AbilityHelper.setDeltaMovement((Entity)target, pushVec);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void onContinuityEnd(LivingEntity entity, IAbility ability) {
/* 112 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 116 */     this.cooldownComponent.startCooldown(entity, 200.0F);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\ryupteranodon\TankyudonAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */