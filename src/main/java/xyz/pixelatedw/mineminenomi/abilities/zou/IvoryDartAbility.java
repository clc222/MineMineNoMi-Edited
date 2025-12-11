/*     */ package xyz.pixelatedw.mineminenomi.abilities.zou;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
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
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.HitTrackerComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RequireMorphComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.DefaultProtectionRules;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.BreakingBlocksParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class IvoryDartAbility extends Ability {
/*  35 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "ivory_dart", new Pair[] {
/*  36 */         (Pair)ImmutablePair.of("Launches the user forward in their elephant form, causing damage and destruction.", null)
/*     */       });
/*     */   
/*     */   private static final int COOLDOWN = 280;
/*     */   private static final int CONTINUITY = 20;
/*     */   private static final float AREA_SIZE = 4.0F;
/*     */   private static final float DAMAGE = 20.0F;
/*  43 */   public static final AbilityCore<IvoryDartAbility> INSTANCE = (new AbilityCore.Builder("Ivory Dart", AbilityCategory.DEVIL_FRUITS, IvoryDartAbility::new))
/*  44 */     .addDescriptionLine(DESCRIPTION)
/*  45 */     .addDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, RequireMorphComponent.getTooltip()
/*  46 */       }).addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(280.0F), ContinuousComponent.getTooltip(20.0F), RangeComponent.getTooltip(4.0F, RangeComponent.RangeType.AOE), DealDamageComponent.getTooltip(20.0F)
/*  47 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/*  48 */     .build();
/*     */   
/*  50 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addEndEvent(100, this::stopContinuityEvent).addTickEvent(100, this::tickContinuityEvent);
/*  51 */   private final HitTrackerComponent hitTrackerComponent = new HitTrackerComponent((IAbility)this);
/*  52 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*  53 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*  54 */   private final RequireMorphComponent requireMorphComponent = new RequireMorphComponent((IAbility)this, (MorphInfo)ModMorphs.ZOU_GUARD.get(), new MorphInfo[0]);
/*     */   
/*  56 */   private BreakingBlocksParticleEffect.Details details = new BreakingBlocksParticleEffect.Details(20);
/*     */   private int initialY;
/*     */   
/*     */   public IvoryDartAbility(AbilityCore<IvoryDartAbility> core) {
/*  60 */     super(core);
/*     */     
/*  62 */     this.isNew = true;
/*  63 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.dealDamageComponent, (AbilityComponent)this.hitTrackerComponent, (AbilityComponent)this.rangeComponent, (AbilityComponent)this.requireMorphComponent });
/*     */     
/*  65 */     addCanUseCheck(AbilityHelper::canUseMomentumAbilities);
/*     */     
/*  67 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  71 */     this.hitTrackerComponent.clearHits();
/*     */     
/*  73 */     this.initialY = (int)entity.func_226278_cu_();
/*  74 */     Vector3d speed = WyHelper.propulsion(entity, 4.0D, 4.0D);
/*     */     
/*  76 */     AbilityHelper.setDeltaMovement((Entity)entity, speed.field_72450_a, entity.func_213322_ci().func_82617_b(), speed.field_72449_c);
/*     */     
/*  78 */     this.continuousComponent.startContinuity(entity, 20.0F);
/*     */   }
/*     */   
/*     */   private void stopContinuityEvent(LivingEntity entity, IAbility ability) {
/*  82 */     this.cooldownComponent.startCooldown(entity, 280.0F);
/*     */   }
/*     */   
/*     */   private void tickContinuityEvent(LivingEntity entity, IAbility ability) {
/*  86 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/*  90 */     List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(entity, 4.0F);
/*  91 */     for (LivingEntity target : targets) {
/*  92 */       if (this.hitTrackerComponent.canHit((Entity)target)) {
/*  93 */         this.dealDamageComponent.hurtTarget(entity, target, 20.0F);
/*     */       }
/*     */     } 
/*     */     
/*  97 */     if (entity.func_226278_cu_() >= this.initialY) {
/*  98 */       List<BlockPos> positions = new ArrayList<>();
/*  99 */       for (BlockPos location : WyHelper.getNearbyBlocks((Entity)entity, 4)) {
/* 100 */         if (location.func_177956_o() >= entity.func_226278_cu_() && 
/* 101 */           AbilityHelper.placeBlockIfAllowed(entity, location, Blocks.field_150350_a.func_176223_P(), DefaultProtectionRules.CORE_FOLIAGE_ORE)) {
/* 102 */           positions.add(location);
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 107 */       if (positions.size() > 0) {
/* 108 */         this.details.setPositions(positions);
/* 109 */         WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.BREAKING_BLOCKS.get(), (Entity)entity, 0.0D, 0.0D, 0.0D, (ParticleEffect.Details)this.details);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\zou\IvoryDartAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */