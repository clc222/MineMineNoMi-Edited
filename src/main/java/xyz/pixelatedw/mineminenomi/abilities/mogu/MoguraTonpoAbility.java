/*     */ package xyz.pixelatedw.mineminenomi.abilities.mogu;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.IItemProvider;
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
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DamageTakenComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.HitTrackerComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RequireMorphComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.DefaultProtectionRules;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.BreakingBlocksParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class MoguraTonpoAbility extends Ability {
/*  42 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "mogura_tonpo", new Pair[] {
/*  43 */         (Pair)ImmutablePair.of("Digs a massive tunnel forwards while in mole form", null)
/*     */       });
/*     */   
/*     */   private static final float COOLDOWN = 240.0F;
/*     */   private static final float RANGE = 1.6F;
/*     */   private static final float DAMAGE = 15.0F;
/*  49 */   public static final AbilityCore<MoguraTonpoAbility> INSTANCE = (new AbilityCore.Builder("Mogura Tonpo", AbilityCategory.DEVIL_FRUITS, MoguraTonpoAbility::new))
/*  50 */     .addDescriptionLine(DESCRIPTION)
/*  51 */     .addDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, RequireMorphComponent.getTooltip()
/*  52 */       }).addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(240.0F), DealDamageComponent.getTooltip(15.0F), RangeComponent.getTooltip(1.6F, RangeComponent.RangeType.LINE)
/*  53 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/*  54 */     .setSourceType(new SourceType[] { SourceType.FIST
/*  55 */       }).build();
/*     */   
/*  57 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addStartEvent(100, this::onStartContinuityEvent).addTickEvent(100, this::onTickContinuityEvent).addEndEvent(100, this::onEndContinuityEvent);
/*  58 */   private final HitTrackerComponent hitTrackerComponent = new HitTrackerComponent((IAbility)this);
/*  59 */   private final DamageTakenComponent damageTakenComponent = (new DamageTakenComponent((IAbility)this)).addOnAttackEvent(this::onDamageTaken);
/*  60 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*  61 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*  62 */   private final RequireMorphComponent requireMorphComponent = new RequireMorphComponent((IAbility)this, (MorphInfo)ModMorphs.MOGU_HEAVY.get(), new MorphInfo[0]);
/*     */   
/*  64 */   private BreakingBlocksParticleEffect.Details details = new BreakingBlocksParticleEffect.Details(20);
/*     */   private float initialY;
/*     */   private boolean hasFallProt;
/*     */   
/*     */   public MoguraTonpoAbility(AbilityCore<MoguraTonpoAbility> core) {
/*  69 */     super(core);
/*     */     
/*  71 */     this.isNew = true;
/*  72 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.hitTrackerComponent, (AbilityComponent)this.damageTakenComponent, (AbilityComponent)this.dealDamageComponent, (AbilityComponent)this.rangeComponent, (AbilityComponent)this.requireMorphComponent });
/*     */     
/*  74 */     addUseEvent(this::onUseEvent);
/*     */   }
/*     */   
/*     */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/*  78 */     this.continuousComponent.startContinuity(entity, 10.0F);
/*     */   }
/*     */   
/*     */   private void onStartContinuityEvent(LivingEntity entity, IAbility ability) {
/*  82 */     this.hitTrackerComponent.clearHits();
/*     */     
/*  84 */     this.hasFallProt = true;
/*  85 */     this.initialY = (int)entity.func_226278_cu_();
/*     */     
/*  87 */     Vector3d speed = WyHelper.propulsion(entity, 2.0D, 2.0D);
/*  88 */     AbilityHelper.setDeltaMovement((Entity)entity, speed.field_72450_a, 0.1D, speed.field_72449_c);
/*     */   }
/*     */   
/*     */   private void onTickContinuityEvent(LivingEntity entity, IAbility ability) {
/*  92 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/*  96 */     if (!entity.func_70089_S() || entity.func_226278_cu_() < this.initialY) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 101 */     List<LivingEntity> targets = this.rangeComponent.getTargetsInLine(entity, 1.6F, 1.6F);
/* 102 */     for (LivingEntity target : targets) {
/* 103 */       if (this.hitTrackerComponent.canHit((Entity)target)) {
/* 104 */         this.dealDamageComponent.hurtTarget(entity, target, 15.0F);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 109 */     List<BlockPos> positions = new ArrayList<>();
/* 110 */     for (BlockPos location : WyHelper.getNearbyBlocks((Entity)entity, 3)) {
/* 111 */       if (location.func_177956_o() >= this.initialY) {
/* 112 */         BlockState tempBlock = entity.field_70170_p.func_180495_p(location);
/* 113 */         if (AbilityHelper.placeBlockIfAllowed(entity, location, Blocks.field_150350_a.func_176223_P(), DefaultProtectionRules.CORE_FOLIAGE_ORE)) {
/* 114 */           if (entity instanceof PlayerEntity) {
/* 115 */             ((PlayerEntity)entity).field_71071_by.func_70441_a(new ItemStack((IItemProvider)tempBlock.func_177230_c()));
/*     */           }
/* 117 */           positions.add(location);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 123 */     if (positions.size() > 0) {
/* 124 */       this.details.setPositions(positions);
/* 125 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.BREAKING_BLOCKS.get(), (Entity)entity, 0.0D, 0.0D, 0.0D, (ParticleEffect.Details)this.details);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void onEndContinuityEvent(LivingEntity entity, IAbility ability) {
/* 130 */     this.cooldownComponent.startCooldown(entity, 240.0F);
/*     */   }
/*     */   
/*     */   private float onDamageTaken(LivingEntity entity, IAbility ability, DamageSource damageSource, float damage) {
/* 134 */     if (this.hasFallProt && damageSource == DamageSource.field_76379_h) {
/* 135 */       this.hasFallProt = false;
/* 136 */       return 0.0F;
/*     */     } 
/*     */     
/* 139 */     return damage;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\mogu\MoguraTonpoAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */