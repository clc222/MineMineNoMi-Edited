/*     */ package xyz.pixelatedw.mineminenomi.abilities.mera;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.world.IWorld;
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
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.Interval;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntityPredicates;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.mera.HibashiraParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class HibashiraAbility extends Ability {
/*  30 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "hibashira", new Pair[] {
/*  31 */         (Pair)ImmutablePair.of("Creates a fire pillar extending both upwards and downwards, burning every enemy within it", null)
/*     */       });
/*     */   
/*     */   private static final int ON_HOLD = 100;
/*     */   private static final int MIN_COOLDOWN = 200;
/*     */   private static final int MAX_COOLDOWN = 250;
/*     */   private static final double PILLAR_SIZE = 3.5D;
/*     */   private static final float DAMAGE = 5.0F;
/*  39 */   public static final AbilityCore<HibashiraAbility> INSTANCE = (new AbilityCore.Builder("Hibashira", AbilityCategory.DEVIL_FRUITS, HibashiraAbility::new))
/*  40 */     .addDescriptionLine(DESCRIPTION)
/*  41 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(200.0F, 250.0F), ContinuousComponent.getTooltip(100.0F), DealDamageComponent.getTooltip(5.0F)
/*  42 */       }).setSourceHakiNature(SourceHakiNature.SPECIAL)
/*  43 */     .setSourceElement(SourceElement.FIRE)
/*  44 */     .build();
/*     */   
/*  46 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this))
/*  47 */     .addStartEvent(this::onContinuityStart)
/*  48 */     .addTickEvent(this::onContinuityTick)
/*  49 */     .addEndEvent(this::onContinuityEnd);
/*     */   
/*  51 */   private final HitTrackerComponent hitTrackerComponent = new HitTrackerComponent((IAbility)this);
/*     */   
/*  53 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*     */   
/*  55 */   private final Interval particleInterval = new Interval(2);
/*  56 */   private final Interval clearHitsInterval = new Interval(20);
/*     */   
/*     */   public HibashiraAbility(AbilityCore<HibashiraAbility> core) {
/*  59 */     super(core);
/*     */     
/*  61 */     this.isNew = true;
/*     */     
/*  63 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.hitTrackerComponent, (AbilityComponent)this.dealDamageComponent });
/*     */     
/*  65 */     addCanUseCheck(MeraHelper::canUseMeraAbilities);
/*     */     
/*  67 */     addUseEvent(this::onUseEvent);
/*     */   }
/*     */   
/*     */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/*  71 */     this.continuousComponent.triggerContinuity(entity, 100.0F);
/*     */   }
/*     */   
/*     */   private void onContinuityStart(LivingEntity entity, IAbility ability) {
/*  75 */     this.hitTrackerComponent.clearHits();
/*     */     
/*  77 */     this.particleInterval.restartIntervalToZero();
/*     */     
/*  79 */     ((ModDamageSource)this.dealDamageComponent.getDamageSource(entity)).setUnavoidable();
/*     */   }
/*     */   
/*     */   private void onContinuityTick(LivingEntity entity, IAbility ability) {
/*  83 */     if (canUse(entity).isFail()) {
/*  84 */       this.continuousComponent.stopContinuity(entity);
/*     */     }
/*     */     
/*  87 */     if (this.particleInterval.canTick()) {
/*  88 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.HIBASHIRA.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_(), (ParticleEffect.Details)HibashiraParticleEffect.NO_DETAILS);
/*     */     }
/*     */     
/*  91 */     List<LivingEntity> targets = WyHelper.getNearbyLiving(entity.func_213303_ch(), (IWorld)entity.field_70170_p, 3.5D, 10.0D, 3.5D, ModEntityPredicates.getEnemyFactions(entity));
/*     */     
/*  93 */     for (LivingEntity target : targets) {
/*  94 */       if (this.hitTrackerComponent.canHit((Entity)target) && this.dealDamageComponent.hurtTarget(entity, target, 5.0F)) {
/*  95 */         target.func_70015_d(4);
/*     */       }
/*     */     } 
/*     */     
/*  99 */     if (this.clearHitsInterval.canTick()) {
/* 100 */       this.hitTrackerComponent.clearHits();
/*     */     }
/*     */     
/* 103 */     AbilityHelper.slowEntityFall(entity);
/*     */     
/* 105 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 5, 1, false, false));
/*     */   }
/*     */   
/*     */   private void onContinuityEnd(LivingEntity entity, IAbility ability) {
/* 109 */     float cooldown = 200.0F + this.continuousComponent.getContinueTime() / 2.0F;
/*     */     
/* 111 */     this.cooldownComponent.startCooldown(entity, cooldown);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\mera\HibashiraAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */