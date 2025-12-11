/*     */ package xyz.pixelatedw.mineminenomi.abilities.gasu;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.potion.Effects;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.BonusOperation;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.Interval;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*     */ 
/*     */ public class KarakuniAbility extends Ability {
/*  31 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "karakuni", new Pair[] {
/*  32 */         (Pair)ImmutablePair.of("Removes the oxygen around the user, suffocating and weakening everyone in the vicinity", null)
/*     */       });
/*  34 */   private static final UUID SHINOKUNI_RANGE_BONUS = UUID.fromString("05f3fc04-68e5-4f7b-a513-7237ad6fd643");
/*     */   
/*     */   private static final int COOLDOWN = 600;
/*     */   
/*     */   private static final int HOLD_TIME = 100;
/*     */   private static final int RANGE = 9;
/*  40 */   public static final AbilityCore<KarakuniAbility> INSTANCE = (new AbilityCore.Builder("Karakuni", AbilityCategory.DEVIL_FRUITS, KarakuniAbility::new))
/*  41 */     .addDescriptionLine(DESCRIPTION)
/*  42 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(600.0F), ContinuousComponent.getTooltip(100.0F), RangeComponent.getTooltip(9.0F, RangeComponent.RangeType.AOE)
/*  43 */       }).build();
/*     */   
/*  45 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addStartEvent(100, this::startContinuityEvent).addTickEvent(this::duringContinuityEvent).addEndEvent(this::stopContinuityEvent);
/*  46 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*     */   
/*  48 */   private Interval suffocateInterval = new Interval(2);
/*     */   
/*     */   public KarakuniAbility(AbilityCore<KarakuniAbility> core) {
/*  51 */     super(core);
/*     */     
/*  53 */     this.isNew = true;
/*  54 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.rangeComponent });
/*     */     
/*  56 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  60 */     this.continuousComponent.triggerContinuity(entity, 100.0F);
/*     */   }
/*     */   
/*     */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/*  64 */     this.suffocateInterval.restartIntervalToZero();
/*     */   }
/*     */   
/*     */   private void duringContinuityEvent(LivingEntity entity, IAbility ability) {
/*  68 */     if (this.suffocateInterval.canTick()) {
/*  69 */       this.rangeComponent.getBonusManager().removeBonus(SHINOKUNI_RANGE_BONUS);
/*  70 */       if (((MorphInfo)ModMorphs.SHINOKUNI.get()).isActive(entity)) {
/*  71 */         this.rangeComponent.getBonusManager().addBonus(SHINOKUNI_RANGE_BONUS, "Shinokuni Range Bonus", BonusOperation.ADD, 3.0F);
/*     */       }
/*     */       
/*  74 */       List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(entity, 9.0F);
/*  75 */       List<BlockPos> blocks = WyHelper.getNearbyBlocks((Entity)entity, 9);
/*     */       
/*  77 */       for (LivingEntity target : targets) {
/*  78 */         if (target.func_70648_aU()) {
/*     */           continue;
/*     */         }
/*     */         
/*  82 */         target.func_195064_c(new EffectInstance(Effects.field_76421_d, 500, 2, false, false));
/*  83 */         target.func_195064_c(new EffectInstance(Effects.field_76437_t, 500, 1, false, false));
/*     */         
/*  85 */         target.func_70050_g(target.func_70086_ai() - 50);
/*     */         
/*  87 */         int airLeft = target.func_70086_ai();
/*     */         
/*  89 */         if (airLeft <= 0) {
/*  90 */           if (target.func_110143_aJ() > 8.0F) {
/*  91 */             target.func_70097_a((DamageSource)ModDamageSource.causeAbilityDamage(entity, (IAbility)this, "drown").setInternal(), 8.0F);
/*     */           } else {
/*  93 */             target.func_195064_c(new EffectInstance((Effect)ModEffects.UNCONSCIOUS.get(), 200, 1));
/*     */           } 
/*     */         }
/*     */         
/*  97 */         if (target.func_70027_ad()) {
/*  98 */           target.func_70066_B();
/*     */         }
/*     */       } 
/*     */       
/* 102 */       if (entity.func_70027_ad()) {
/* 103 */         entity.func_70066_B();
/*     */       }
/*     */       
/* 106 */       BlockPos.Mutable blockUp = new BlockPos.Mutable();
/*     */       
/* 108 */       for (BlockPos blockPos : blocks) {
/* 109 */         blockUp.func_181079_c(blockPos.func_177958_n(), blockPos.func_177956_o() + 1, blockPos.func_177952_p());
/*     */         
/* 111 */         if (entity.field_70170_p.func_180495_p(blockPos).func_177230_c() == Blocks.field_150480_ab && entity.field_70170_p.func_180495_p((BlockPos)blockUp).func_177230_c() == Blocks.field_150350_a) {
/* 112 */           entity.field_70170_p.func_217377_a(blockPos, false);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void stopContinuityEvent(LivingEntity entity, IAbility ability) {
/* 119 */     this.cooldownComponent.startCooldown(entity, 600.0F);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\gasu\KarakuniAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */