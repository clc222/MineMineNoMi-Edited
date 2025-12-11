/*     */ package xyz.pixelatedw.mineminenomi.abilities.suna;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.potion.Effects;
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
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.BonusOperation;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.HitTrackerComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.TargetsPredicate;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class BarjanAbility extends Ability {
/*     */   private static final float COOLDOWN_BONUS = 0.8F;
/*     */   private static final float DAMAGE_BONUS = 1.25F;
/*  38 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "barjan", new Pair[] {
/*  39 */         (Pair)ImmutablePair.of("Dashes forwards hitting all enemies it touches dehydrating them.", null), 
/*  40 */         (Pair)ImmutablePair.of("While in a desert the cooldown of this ability is reduced by %s and the damage is increased by %s.", new Object[] {
/*  41 */             "§a" + Math.round(19.999998F) + "%§r", "§a" + 
/*  42 */             Math.round(Math.abs(-0.25F) * 100.0F) + "%§r"
/*     */           })
/*     */       });
/*     */   private static final int COOLDOWN = 200;
/*     */   private static final int HOLD_TIME = 40;
/*     */   private static final float DAMAGE = 30.0F;
/*     */   private static final float RANGE = 1.6F;
/*  49 */   public static final AbilityCore<BarjanAbility> INSTANCE = (new AbilityCore.Builder("Barjan", AbilityCategory.DEVIL_FRUITS, BarjanAbility::new))
/*  50 */     .addDescriptionLine(DESCRIPTION)
/*  51 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(200.0F), ContinuousComponent.getTooltip(40.0F), DealDamageComponent.getTooltip(30.0F), RangeComponent.getTooltip(1.6F, RangeComponent.RangeType.AOE)
/*  52 */       }).setSourceHakiNature(SourceHakiNature.IMBUING)
/*  53 */     .build();
/*     */   
/*  55 */   private static final TargetsPredicate TARGETS_PREDICATE = (new TargetsPredicate()).testEnemyFaction().testAdvancedInView();
/*     */   
/*  57 */   private final ContinuousComponent continuityComponent = (new ContinuousComponent((IAbility)this)).addStartEvent(this::startContinuityEvent).addTickEvent(this::duringContinuityEvent).addEndEvent(this::endContinuityEvent);
/*  58 */   private final HitTrackerComponent hitTrackerComponent = new HitTrackerComponent((IAbility)this);
/*  59 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*  60 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*  61 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*     */   
/*     */   public BarjanAbility(AbilityCore<BarjanAbility> core) {
/*  64 */     super(core);
/*     */     
/*  66 */     this.isNew = true;
/*     */     
/*  68 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.hitTrackerComponent, (AbilityComponent)this.animationComponent, (AbilityComponent)this.continuityComponent, (AbilityComponent)this.rangeComponent, (AbilityComponent)this.dealDamageComponent });
/*     */     
/*  70 */     addCanUseCheck(AbilityHelper::requiresDryUser);
/*  71 */     addCanUseCheck(AbilityHelper::canUseMomentumAbilities);
/*     */     
/*  73 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  77 */     this.continuityComponent.startContinuity(entity, 40.0F);
/*     */   }
/*     */   
/*     */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/*  81 */     this.animationComponent.start(entity, ModAnimations.BARJAN, 40);
/*     */     
/*  83 */     Vector3d speed = WyHelper.propulsion(entity, 4.0D, 4.0D);
/*  84 */     AbilityHelper.setDeltaMovement((Entity)entity, speed.field_72450_a, 0.30000001192092896D + entity.func_213322_ci().func_82617_b(), speed.field_72449_c);
/*     */     
/*  86 */     this.hitTrackerComponent.clearHits();
/*     */   }
/*     */   
/*     */   private void duringContinuityEvent(LivingEntity entity, IAbility ability) {
/*  90 */     WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.BARJAN.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/*  91 */     List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(entity, 1.6F);
/*     */     
/*  93 */     this.dealDamageComponent.getBonusManager().removeBonus(SunaHelper.DESERT_DAMAGE_BONUS);
/*  94 */     if (SunaHelper.isFruitBoosted(entity)) {
/*  95 */       this.dealDamageComponent.getBonusManager().addBonus(SunaHelper.DESERT_DAMAGE_BONUS, "Desert Damage Bonus", BonusOperation.MUL, 1.25F);
/*     */     }
/*     */     
/*  98 */     float finalDamage = 30.0F * (SunaHelper.isFruitBoosted(entity) ? 1.25F : 1.0F);
/*  99 */     for (LivingEntity target : targets) {
/* 100 */       if (this.hitTrackerComponent.canHit((Entity)target)) {
/* 101 */         boolean isHurt = this.dealDamageComponent.hurtTarget(entity, target, finalDamage);
/* 102 */         if (isHurt) {
/* 103 */           target.func_195064_c(new EffectInstance((Effect)ModEffects.DEHYDRATION.get(), 200, 2, false, true));
/* 104 */           target.func_195064_c(new EffectInstance(Effects.field_76437_t, 150, 0, false, false));
/* 105 */           target.func_195064_c(new EffectInstance(Effects.field_76421_d, 150, 0, false, false));
/* 106 */           target.func_195064_c(new EffectInstance(Effects.field_76419_f, 150, 0, false, false));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 113 */     this.cooldownComponent.getBonusManager().removeBonus(SunaHelper.DESERT_COOLDOWN_BONUS);
/* 114 */     if (SunaHelper.isFruitBoosted(entity)) {
/* 115 */       this.cooldownComponent.getBonusManager().addBonus(SunaHelper.DESERT_COOLDOWN_BONUS, "Desert Cooldown Bonus", BonusOperation.MUL, 0.8F);
/*     */     }
/*     */     
/* 118 */     this.cooldownComponent.startCooldown(entity, 200.0F);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\suna\BarjanAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */