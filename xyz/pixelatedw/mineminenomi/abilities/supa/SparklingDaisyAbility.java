/*     */ package xyz.pixelatedw.mineminenomi.abilities.supa;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.math.BlockPos;
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
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.HitTrackerComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.DefaultProtectionRules;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.supa.SparklingDaisyAftereffectProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.BreakingBlocksParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class SparklingDaisyAbility extends Ability {
/*  41 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "sparkling_daisy", new Pair[] {
/*  42 */         (Pair)ImmutablePair.of("Launches the user forward, slicing anything in their path", null)
/*     */       });
/*     */   
/*     */   private static final float COOLDOWN = 140.0F;
/*     */   private static final float RANGE = 1.6F;
/*     */   private static final float DAMAGE = 25.0F;
/*  48 */   public static final AbilityCore<SparklingDaisyAbility> INSTANCE = (new AbilityCore.Builder("Sparkling Daisy", AbilityCategory.DEVIL_FRUITS, SparklingDaisyAbility::new))
/*  49 */     .addDescriptionLine(DESCRIPTION)
/*  50 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(140.0F), RangeComponent.getTooltip(1.6F, RangeComponent.RangeType.AOE), DealDamageComponent.getTooltip(25.0F)
/*  51 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/*  52 */     .setSourceType(new SourceType[] { SourceType.FIST
/*  53 */       }).build();
/*     */   
/*  55 */   private final HitTrackerComponent hitTrackerComponent = new HitTrackerComponent((IAbility)this);
/*  56 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this)).addStartEvent(this::chargeStartsEvent).addEndEvent(this::chargeEndsEvent);
/*  57 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*  58 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addTickEvent(this::continuousTickEvent).addEndEvent(this::continuousEndsEvent);
/*  59 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*  60 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*  61 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*     */   
/*  63 */   private BreakingBlocksParticleEffect.Details details = new BreakingBlocksParticleEffect.Details(20);
/*     */   
/*     */   private int initialY;
/*     */   private boolean isFull = false;
/*  67 */   private int projectiles = 1;
/*     */   
/*     */   public SparklingDaisyAbility(AbilityCore<SparklingDaisyAbility> core) {
/*  70 */     super(core);
/*     */     
/*  72 */     this.isNew = true;
/*     */     
/*  74 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.hitTrackerComponent, (AbilityComponent)this.chargeComponent, (AbilityComponent)this.animationComponent, (AbilityComponent)this.continuousComponent, (AbilityComponent)this.rangeComponent, (AbilityComponent)this.dealDamageComponent });
/*     */     
/*  76 */     addCanUseCheck(AbilityHelper::canUseMomentumAbilities);
/*  77 */     addUseEvent(this::onUseEvent);
/*     */   }
/*     */   
/*     */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/*  81 */     this.hitTrackerComponent.clearHits();
/*  82 */     this.initialY = (int)entity.func_226278_cu_();
/*     */     
/*  84 */     if (this.isFull) {
/*  85 */       this.chargeComponent.startCharging(entity, 40.0F);
/*     */     } else {
/*     */       
/*  88 */       Vector3d speed = entity.func_70040_Z().func_216372_d(7.0D, 0.0D, 7.0D);
/*  89 */       AbilityHelper.setDeltaMovement((Entity)entity, speed.field_72450_a, 0.15D, speed.field_72449_c);
/*  90 */       this.continuousComponent.startContinuity(entity, 10.0F);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void chargeStartsEvent(LivingEntity entity, IAbility ability) {
/*  95 */     this.animationComponent.start(entity, ModAnimations.SPARKLING_DAISY_CHARGE);
/*  96 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 100, 0));
/*     */   }
/*     */ 
/*     */   
/*     */   private void chargeEndsEvent(LivingEntity entity, IAbility ability) {
/* 101 */     Vector3d dir = entity.func_70040_Z().func_216372_d(7.0D, 0.0D, 7.0D);
/* 102 */     AbilityHelper.setDeltaMovement((Entity)entity, dir.field_72450_a, 0.15D, dir.field_72449_c);
/*     */     
/* 104 */     this.animationComponent.stop(entity);
/* 105 */     this.continuousComponent.startContinuity(entity, 20.0F);
/*     */     
/* 107 */     entity.func_195063_d((Effect)ModEffects.MOVEMENT_BLOCKED.get());
/*     */   }
/*     */   
/*     */   private void continuousEndsEvent(LivingEntity entity, IAbility ability) {
/* 111 */     if (this.isFull) {
/* 112 */       if (this.projectiles > 0) {
/* 113 */         for (int i = -this.projectiles; i <= this.projectiles; i++) {
/* 114 */           SparklingDaisyAftereffectProjectile proj = (SparklingDaisyAftereffectProjectile)this.projectileComponent.getNewProjectile(entity);
/* 115 */           proj.func_234612_a_((Entity)entity, entity.field_70125_A, entity.field_70177_z + (i * 55), 0.0F, 0.85F, 1.0F);
/* 116 */           entity.field_70170_p.func_217376_c((Entity)proj);
/*     */         } 
/*     */       }
/* 119 */       this.animationComponent.stop(entity);
/*     */     } 
/* 121 */     this.cooldownComponent.startCooldown(entity, 140.0F);
/*     */   }
/*     */   
/*     */   private void continuousTickEvent(LivingEntity entity, IAbility ability) {
/* 125 */     if (entity.func_70089_S() && !entity.field_70170_p.field_72995_K && entity.func_226278_cu_() >= this.initialY) {
/* 126 */       List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(entity, 1.6F);
/* 127 */       targets.remove(entity);
/*     */       
/* 129 */       for (LivingEntity target : targets) {
/* 130 */         if (this.hitTrackerComponent.canHit((Entity)target)) {
/* 131 */           this.dealDamageComponent.hurtTarget(entity, target, 25.0F);
/*     */         }
/*     */       } 
/*     */       
/* 135 */       List<BlockPos> positions = new ArrayList<>();
/* 136 */       for (BlockPos location : WyHelper.getNearbyBlocks((Entity)entity, 3)) {
/* 137 */         if (location.func_177956_o() >= entity.func_226278_cu_() && 
/* 138 */           AbilityHelper.placeBlockIfAllowed(entity, location, Blocks.field_150350_a.func_176223_P(), DefaultProtectionRules.CORE_FOLIAGE_ORE)) {
/* 139 */           positions.add(location);
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 144 */       if (positions.size() > 0) {
/* 145 */         this.details.setPositions(positions);
/* 146 */         WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.BREAKING_BLOCKS.get(), (Entity)entity, 0.0D, 0.0D, 0.0D, (ParticleEffect.Details)this.details);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setFullForm() {
/* 152 */     this.isFull = true;
/*     */   }
/*     */   
/*     */   public void setProjectilesAmount(int amount) {
/* 156 */     this.projectiles = amount;
/*     */   }
/*     */   
/*     */   private SparklingDaisyAftereffectProjectile createProjectile(LivingEntity entity) {
/* 160 */     SparklingDaisyAftereffectProjectile proj = new SparklingDaisyAftereffectProjectile(entity.field_70170_p, entity, this);
/* 161 */     return proj;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\supa\SparklingDaisyAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */