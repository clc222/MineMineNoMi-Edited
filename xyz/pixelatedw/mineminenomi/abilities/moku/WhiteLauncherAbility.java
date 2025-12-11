/*     */ package xyz.pixelatedw.mineminenomi.abilities.moku;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.DamageSource;
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
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.HitTrackerComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.Interval;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class WhiteLauncherAbility extends Ability {
/*  29 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "white_launcher", new Pair[] {
/*  30 */         (Pair)ImmutablePair.of("Transforms the user into smoke and launches them forward", null)
/*     */       });
/*     */   
/*     */   private static final int COOLDOWN = 200;
/*     */   
/*     */   private static final float RANGE = 1.6F;
/*     */   private static final float DAMAGE = 15.0F;
/*     */   public static final int ON_HOLD = 15;
/*  38 */   public static final AbilityCore<WhiteLauncherAbility> INSTANCE = (new AbilityCore.Builder("White Launcher", AbilityCategory.DEVIL_FRUITS, WhiteLauncherAbility::new))
/*  39 */     .addDescriptionLine(DESCRIPTION)
/*  40 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(200.0F), ContinuousComponent.getTooltip(15.0F), RangeComponent.getTooltip(1.6F, RangeComponent.RangeType.AOE), DealDamageComponent.getTooltip(15.0F)
/*  41 */       }).build();
/*     */   
/*  43 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addStartEvent(this::onContinuityStart).addTickEvent(this::onContinuityTick).addEndEvent(this::onContinuityEnd);
/*  44 */   private final HitTrackerComponent hitTrackerComponent = new HitTrackerComponent((IAbility)this);
/*  45 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*  46 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*     */   
/*  48 */   private final Interval particleInterval = new Interval(2);
/*     */   
/*     */   public WhiteLauncherAbility(AbilityCore<WhiteLauncherAbility> core) {
/*  51 */     super(core);
/*     */     
/*  53 */     this.isNew = true;
/*     */     
/*  55 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.hitTrackerComponent, (AbilityComponent)this.rangeComponent, (AbilityComponent)this.dealDamageComponent });
/*     */     
/*  57 */     addCanUseCheck(AbilityHelper::canUseMomentumAbilities);
/*     */     
/*  59 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  63 */     if (!AbilityHelper.canUseMomentumAbilities(entity)) {
/*     */       return;
/*     */     }
/*     */     
/*  67 */     this.continuousComponent.startContinuity(entity);
/*     */   }
/*     */   
/*     */   private void onContinuityStart(LivingEntity entity, IAbility ability) {
/*  71 */     this.particleInterval.restartIntervalToZero();
/*  72 */     this.hitTrackerComponent.clearHits();
/*     */   }
/*     */   
/*     */   private void onContinuityTick(LivingEntity entity, IAbility ability) {
/*  76 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/*  80 */     if (this.continuousComponent.getContinueTime() > 15.0F) {
/*  81 */       if (entity.func_233570_aj_()) {
/*  82 */         this.continuousComponent.stopContinuity(entity);
/*     */         
/*     */         return;
/*     */       } 
/*     */       return;
/*     */     } 
/*  88 */     if (canUse(entity).isFail()) {
/*  89 */       this.continuousComponent.stopContinuity(entity);
/*     */     }
/*     */     
/*  92 */     Vector3d dir = entity.func_70040_Z().func_72432_b().func_186678_a(3.0D);
/*     */     
/*  94 */     AbilityHelper.setDeltaMovement((Entity)entity, dir);
/*     */     
/*  96 */     if (this.particleInterval.canTick()) {
/*  97 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.WHITE_LAUNCHER.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/*     */     }
/*     */     
/* 100 */     List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(entity, 1.6F);
/*     */     
/* 102 */     for (LivingEntity target : targets) {
/* 103 */       if (this.hitTrackerComponent.canHit((Entity)target) && entity.func_70685_l((Entity)target)) {
/* 104 */         target.func_70097_a((DamageSource)ModDamageSource.causeAbilityDamage(entity, (IAbility)this, "player"), 15.0F);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void onContinuityEnd(LivingEntity entity, IAbility ability) {
/* 110 */     this.cooldownComponent.startCooldown(entity, 200.0F);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\moku\WhiteLauncherAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */