/*     */ package xyz.pixelatedw.mineminenomi.abilities.blackleg;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class PartyTableKickCourseAbility extends Ability {
/*  41 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "party_table_kick_course", new Pair[] {
/*  42 */         (Pair)ImmutablePair.of("The user does a hand stand on the ground, legs spread out spinning and dealing damage to all nearby enemies", null)
/*     */       });
/*     */   
/*     */   private static final float COOLDOWN = 240.0F;
/*     */   private static final float RANGE = 2.5F;
/*     */   private static final float DAMAGE = 25.0F;
/*  48 */   public static final AbilityCore<PartyTableKickCourseAbility> INSTANCE = (new AbilityCore.Builder("Party-Table Kick Course", AbilityCategory.STYLE, PartyTableKickCourseAbility::new))
/*  49 */     .addDescriptionLine(DESCRIPTION)
/*  50 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(240.0F), DealDamageComponent.getTooltip(25.0F), RangeComponent.getTooltip(2.5F, RangeComponent.RangeType.AOE)
/*  51 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/*  52 */     .setSourceType(new SourceType[] { SourceType.FIST
/*  53 */       }).setUnlockCheck(PartyTableKickCourseAbility::canUnlock)
/*  54 */     .build();
/*     */   
/*  56 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addStartEvent(this::onStartContinuityEvent).addEndEvent(this::onStopContinuityEvent).addTickEvent(this::duringContinuityEvent);
/*  57 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*  58 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*  59 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*     */   
/*     */   public PartyTableKickCourseAbility(AbilityCore<PartyTableKickCourseAbility> core) {
/*  62 */     super(core);
/*     */     
/*  64 */     this.isNew = true;
/*  65 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.animationComponent, (AbilityComponent)this.dealDamageComponent, (AbilityComponent)this.rangeComponent });
/*     */     
/*  67 */     addUseEvent(this::onUseEvent);
/*     */   }
/*     */   
/*     */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/*  71 */     this.continuousComponent.triggerContinuity(entity, 20.0F);
/*     */   }
/*     */   
/*     */   private boolean onStartContinuityEvent(LivingEntity entity, IAbility ability) {
/*  75 */     this.animationComponent.start(entity, ModAnimations.HAND_STAND_SPIN);
/*  76 */     return true;
/*     */   }
/*     */   
/*     */   private boolean onStopContinuityEvent(LivingEntity entity, IAbility ability) {
/*  80 */     this.animationComponent.stop(entity);
/*  81 */     this.cooldownComponent.startCooldown(entity, 240.0F);
/*  82 */     return true;
/*     */   }
/*     */   
/*     */   private boolean duringContinuityEvent(LivingEntity entity, IAbility ability) {
/*  86 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 5, 1));
/*     */     
/*  88 */     List<LivingEntity> list = this.rangeComponent.getTargetsInArea(entity, 2.5F);
/*  89 */     for (LivingEntity target : list) {
/*  90 */       if (this.dealDamageComponent.hurtTarget(entity, target, 25.0F)) {
/*  91 */         Vector3d speed = WyHelper.propulsion(entity, 1.5D, 1.5D);
/*  92 */         AbilityHelper.setDeltaMovement((Entity)target, speed.field_72450_a, 1.5D, speed.field_72449_c);
/*     */       } 
/*     */     } 
/*     */     
/*  96 */     if (!entity.field_70170_p.field_72995_K && list.size() > 0) {
/*  97 */       ((ServerWorld)entity.field_70170_p).func_72863_F().func_217216_a((Entity)entity, (IPacket)new SAnimateHandPacket((Entity)entity, 0));
/*     */     }
/*     */     
/* 100 */     DiableJambeAbility diableJambeAbility = (DiableJambeAbility)AbilityDataCapability.get(entity).getEquippedAbility(DiableJambeAbility.INSTANCE);
/* 101 */     boolean isAbilityEnabled = (diableJambeAbility != null && diableJambeAbility.isContinuous());
/* 102 */     if (isAbilityEnabled) {
/* 103 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.PARTY_TABLE_KICK.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/*     */     }
/*     */     
/* 106 */     return true;
/*     */   }
/*     */   
/*     */   private static boolean canUnlock(LivingEntity entity) {
/* 110 */     if (!(entity instanceof PlayerEntity)) {
/* 111 */       return false;
/*     */     }
/*     */     
/* 114 */     PlayerEntity player = (PlayerEntity)entity;
/* 115 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/* 116 */     IQuestData questProps = QuestDataCapability.get(player);
/*     */     
/* 118 */     return (props.isBlackLeg() && questProps.hasFinishedQuest(ModQuests.BLACK_LEG_TRIAL_03));
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\blackleg\PartyTableKickCourseAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */