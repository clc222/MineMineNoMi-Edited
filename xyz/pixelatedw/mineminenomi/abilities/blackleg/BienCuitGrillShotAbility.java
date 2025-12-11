/*     */ package xyz.pixelatedw.mineminenomi.abilities.blackleg;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUseResult;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.HitTrackerComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class BienCuitGrillShotAbility extends Ability {
/*  40 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "bien_cuit_grill_shot", new Pair[] {
/*  41 */         (Pair)ImmutablePair.of("A strong kick that launches the user forwards and creates a grill-patterened particle to appear, which sets anyone touching it on fire", null)
/*     */       });
/*     */   
/*     */   private static final float COOLDOWN = 400.0F;
/*     */   private static final float RANGE = 1.8F;
/*     */   private static final float DAMAGE = 30.0F;
/*  47 */   public static final AbilityCore<BienCuitGrillShotAbility> INSTANCE = (new AbilityCore.Builder("Bien Cuit: Grill Shot", AbilityCategory.STYLE, BienCuitGrillShotAbility::new))
/*  48 */     .addDescriptionLine(DESCRIPTION)
/*  49 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(400.0F), RangeComponent.getTooltip(1.8F, RangeComponent.RangeType.AOE), DealDamageComponent.getTooltip(30.0F)
/*  50 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/*  51 */     .setSourceType(new SourceType[] { SourceType.FIST
/*  52 */       }).setUnlockCheck(BienCuitGrillShotAbility::canUnlock)
/*  53 */     .build();
/*     */   
/*  55 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*  56 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*  57 */   private final HitTrackerComponent hitTrackerComponent = new HitTrackerComponent((IAbility)this);
/*  58 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addStartEvent(100, this::startContinuityEvent).addTickEvent(this::continuousTickEvent).addEndEvent(this::continuousEndsEvent);
/*     */   
/*     */   public BienCuitGrillShotAbility(AbilityCore<BienCuitGrillShotAbility> core) {
/*  61 */     super(core);
/*     */     
/*  63 */     this.isNew = true;
/*  64 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.dealDamageComponent, (AbilityComponent)this.rangeComponent, (AbilityComponent)this.hitTrackerComponent, (AbilityComponent)this.continuousComponent });
/*     */     
/*  66 */     addCanUseCheck(AbilityHelper::canUseMomentumAbilities);
/*  67 */     addCanUseCheck(this::hasDiableJambeActive);
/*  68 */     addUseEvent(this::onUseEvent);
/*     */   }
/*     */   
/*     */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/*  72 */     this.continuousComponent.startContinuity(entity, 10.0F);
/*     */   }
/*     */   
/*     */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/*  76 */     this.hitTrackerComponent.clearHits();
/*     */     
/*  78 */     Vector3d speed = entity.func_70040_Z().func_216372_d(3.0D, 0.0D, 3.0D);
/*  79 */     AbilityHelper.setDeltaMovement((Entity)entity, speed.field_72450_a, 0.3D, speed.field_72449_c);
/*     */     
/*  81 */     if (!entity.field_70170_p.field_72995_K) {
/*  82 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.BIEN_CUIT_GRILL_SHOT.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/*     */     }
/*     */   }
/*     */   
/*     */   private void continuousTickEvent(LivingEntity entity, IAbility ability) {
/*  87 */     if (entity.func_70089_S()) {
/*  88 */       List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(entity, 1.8F);
/*  89 */       targets.remove(entity);
/*     */       
/*  91 */       Vector3d pushSpeed = entity.func_70040_Z().func_216372_d(2.0D, 0.0D, 2.0D);
/*  92 */       for (LivingEntity target : targets) {
/*  93 */         if (this.hitTrackerComponent.canHit((Entity)target) && this.dealDamageComponent.hurtTarget(entity, target, 30.0F)) {
/*  94 */           AbilityHelper.setDeltaMovement((Entity)target, pushSpeed.field_72450_a, 0.2D, pushSpeed.field_72449_c);
/*  95 */           AbilityHelper.setSecondsOnFireBy((Entity)target, 2, entity);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void continuousEndsEvent(LivingEntity entity, IAbility ability) {
/* 102 */     this.cooldownComponent.startCooldown(entity, 400.0F);
/*     */   }
/*     */   
/*     */   public AbilityUseResult hasDiableJambeActive(LivingEntity entity, IAbility ability) {
/* 106 */     IAbilityData props = AbilityDataCapability.get(entity);
/* 107 */     DiableJambeAbility diableJambeAbility = (DiableJambeAbility)props.getEquippedAbility(DiableJambeAbility.INSTANCE);
/* 108 */     if (diableJambeAbility == null || !diableJambeAbility.isContinuous()) {
/* 109 */       return AbilityUseResult.fail((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_DIABLE_JAMBE));
/*     */     }
/* 111 */     return AbilityUseResult.success();
/*     */   }
/*     */   
/*     */   private static boolean canUnlock(LivingEntity entity) {
/* 115 */     if (!(entity instanceof PlayerEntity)) {
/* 116 */       return false;
/*     */     }
/*     */     
/* 119 */     PlayerEntity player = (PlayerEntity)entity;
/* 120 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/* 121 */     IQuestData questProps = QuestDataCapability.get(player);
/*     */     
/* 123 */     return (props.isBlackLeg() && questProps.hasFinishedQuest(ModQuests.BLACK_LEG_TRIAL_05));
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\blackleg\BienCuitGrillShotAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */