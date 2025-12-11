/*     */ package xyz.pixelatedw.mineminenomi.abilities.mera;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.HitTrackerComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.Interval;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class HeatDashAbility extends Ability {
/*  29 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "heat_dash", new Pair[] {
/*  30 */         (Pair)ImmutablePair.of("Transforms the user into fire and launches them forward, setting on fire all enemies around the user.", null)
/*     */       });
/*     */   
/*     */   private static final int COOLDOWN = 200;
/*     */   private static final int ON_HOLD = 15;
/*     */   private static final float RANGE = 1.4F;
/*  36 */   public static final AbilityCore<HeatDashAbility> INSTANCE = (new AbilityCore.Builder("Heat Dash", AbilityCategory.DEVIL_FRUITS, HeatDashAbility::new))
/*  37 */     .addDescriptionLine(DESCRIPTION)
/*  38 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(200.0F), ContinuousComponent.getTooltip(15.0F), RangeComponent.getTooltip(1.4F, RangeComponent.RangeType.AOE)
/*  39 */       }).build();
/*     */   
/*  41 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this))
/*  42 */     .addStartEvent(this::onContinuityStart)
/*  43 */     .addTickEvent(this::onContinuityTick)
/*  44 */     .addEndEvent(this::onContinuityEnd);
/*  45 */   private final HitTrackerComponent hitTrackerComponent = new HitTrackerComponent((IAbility)this);
/*  46 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*     */   
/*  48 */   private final Interval particleInterval = new Interval(2);
/*     */   
/*     */   public HeatDashAbility(AbilityCore<HeatDashAbility> core) {
/*  51 */     super(core);
/*     */     
/*  53 */     this.isNew = true;
/*     */     
/*  55 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.hitTrackerComponent, (AbilityComponent)this.rangeComponent });
/*     */     
/*  57 */     addCanUseCheck(MeraHelper::canUseMeraAbilities);
/*     */     
/*  59 */     addUseEvent(this::onUseEvent);
/*     */   }
/*     */   
/*     */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/*  63 */     if (!AbilityHelper.canUseMomentumAbilities(entity)) {
/*     */       return;
/*     */     }
/*     */     
/*  67 */     this.continuousComponent.startContinuity(entity);
/*     */   }
/*     */   
/*     */   private void onContinuityStart(LivingEntity entity, IAbility ability) {
/*  71 */     this.hitTrackerComponent.clearHits();
/*  72 */     this.particleInterval.restartIntervalToZero();
/*     */     
/*  74 */     entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.MERA_SFX.get(), SoundCategory.PLAYERS, 2.0F, 0.5F + this.random.nextFloat() / 3.0F);
/*     */   }
/*     */   
/*     */   private void onContinuityTick(LivingEntity entity, IAbility ability) {
/*  78 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/*  82 */     if (this.continuousComponent.getContinueTime() > 15.0F) {
/*  83 */       if (entity.func_233570_aj_()) {
/*  84 */         this.continuousComponent.stopContinuity(entity);
/*     */         
/*     */         return;
/*     */       } 
/*     */       return;
/*     */     } 
/*  90 */     if (canUse(entity).isFail()) {
/*  91 */       this.continuousComponent.stopContinuity(entity);
/*     */     }
/*     */     
/*  94 */     Vector3d dir = entity.func_70040_Z().func_72432_b().func_186678_a(3.0D);
/*     */     
/*  96 */     AbilityHelper.setDeltaMovement((Entity)entity, dir);
/*     */     
/*  98 */     if (this.particleInterval.canTick()) {
/*  99 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.HEAT_DASH.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/*     */     }
/*     */     
/* 102 */     List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(entity, 1.4F);
/*     */     
/* 104 */     for (LivingEntity target : targets) {
/* 105 */       if (this.hitTrackerComponent.canHit((Entity)target) && entity.func_70685_l((Entity)target)) {
/* 106 */         AbilityHelper.setSecondsOnFireBy((Entity)target, 2, entity);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void onContinuityEnd(LivingEntity entity, IAbility ability) {
/* 112 */     this.cooldownComponent.startCooldown(entity, 200.0F);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\mera\HeatDashAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */