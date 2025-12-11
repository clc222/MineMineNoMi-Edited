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
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.DropHitAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.Interval;
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
/*     */ public class ConcasseAbility extends DropHitAbility {
/*  41 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "concasse", new Pair[] {
/*  42 */         (Pair)ImmutablePair.of("Leaps forward kicking all nearby enemies for moderate damage and knocking them down", null)
/*     */       });
/*     */   
/*     */   private static final int COOLDOWN = 300;
/*     */   private static final float RANGE = 1.7F;
/*     */   private static final float DAMAGE = 15.0F;
/*  48 */   public static final AbilityCore<ConcasseAbility> INSTANCE = (new AbilityCore.Builder("Concasse", AbilityCategory.STYLE, ConcasseAbility::new))
/*  49 */     .addDescriptionLine(DESCRIPTION)
/*  50 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(300.0F), DealDamageComponent.getTooltip(15.0F), RangeComponent.getTooltip(1.7F, RangeComponent.RangeType.AOE)
/*  51 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/*  52 */     .setSourceType(new SourceType[] { SourceType.FIST
/*  53 */       }).setUnlockCheck(ConcasseAbility::canUnlock)
/*  54 */     .build();
/*     */   
/*  56 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*  57 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*  58 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*     */   
/*  60 */   private Interval particleInterval = new Interval(2);
/*     */   
/*     */   public ConcasseAbility(AbilityCore<ConcasseAbility> core) {
/*  63 */     super(core);
/*     */     
/*  65 */     this.isNew = true;
/*     */     
/*  67 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.dealDamageComponent, (AbilityComponent)this.rangeComponent, (AbilityComponent)this.animationComponent });
/*     */     
/*  69 */     this.continuousComponent.addStartEvent(100, this::startContinuityEvent);
/*  70 */     this.continuousComponent.addEndEvent(100, this::onContinuityEnd);
/*  71 */     this.continuousComponent.addTickEvent(100, this::tickContinuityEvent);
/*     */   }
/*     */   
/*     */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/*  75 */     this.hitTrackerComponent.clearHits();
/*     */     
/*  77 */     this.particleInterval.restartIntervalToZero();
/*     */     
/*  79 */     Vector3d speed = entity.func_70040_Z();
/*     */     
/*  81 */     AbilityHelper.setDeltaMovement((Entity)entity, speed.field_72450_a, 1.3D, speed.field_72449_c);
/*     */     
/*  83 */     this.animationComponent.start(entity, ModAnimations.PITCH_SPIN);
/*     */   }
/*     */   
/*     */   private void tickContinuityEvent(LivingEntity entity, IAbility ability) {
/*  87 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/*  91 */     if (entity instanceof PlayerEntity && ((PlayerEntity)entity).field_71075_bZ.field_75100_b) {
/*  92 */       this.continuousComponent.stopContinuity(entity);
/*     */       
/*     */       return;
/*     */     } 
/*  96 */     if (entity.field_70143_R > 0.0F) {
/*  97 */       boolean targetHurt = false;
/*     */       
/*  99 */       List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(entity, 1.7F);
/*     */       
/* 101 */       targets.remove(entity);
/*     */       
/* 103 */       for (LivingEntity target : targets) {
/* 104 */         if (this.hitTrackerComponent.canHit((Entity)target) && entity.func_233580_cy_().func_177956_o() > target.func_233580_cy_().func_177956_o() && 
/* 105 */           this.dealDamageComponent.hurtTarget(entity, target, 15.0F)) {
/* 106 */           target.func_195064_c(new EffectInstance((Effect)ModEffects.UNCONSCIOUS.get(), 20, 0, false, false));
/* 107 */           AbilityHelper.setDeltaMovement((Entity)target, (entity.func_213322_ci()).field_72450_a, -1.5D, (entity.func_213322_ci()).field_72449_c);
/*     */           
/* 109 */           targetHurt = true;
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 114 */       if (targetHurt) {
/* 115 */         if (!entity.field_70170_p.field_72995_K) {
/* 116 */           ((ServerWorld)entity.field_70170_p).func_72863_F().func_217216_a((Entity)entity, (IPacket)new SAnimateHandPacket((Entity)entity, 0));
/*     */         }
/*     */         
/* 119 */         this.continuousComponent.stopContinuity(entity);
/*     */       } 
/*     */     } 
/*     */     
/* 123 */     if (this.particleInterval.canTick()) {
/* 124 */       DiableJambeAbility diableJambeAbility = (DiableJambeAbility)AbilityDataCapability.get(entity).getEquippedAbility(DiableJambeAbility.INSTANCE);
/*     */       
/* 126 */       boolean isAbilityEnabled = (diableJambeAbility != null && diableJambeAbility.isContinuous());
/*     */       
/* 128 */       if (isAbilityEnabled && !entity.func_233570_aj_()) {
/* 129 */         WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.CONCASSE_DIABLE.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_() + 1.5D, entity.func_226281_cx_());
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void onContinuityEnd(LivingEntity entity, IAbility ability) {
/* 135 */     this.animationComponent.stop(entity);
/*     */     
/* 137 */     this.cooldownComponent.startCooldown(entity, 300.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onLanding(LivingEntity entity) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public float getContinueTime(LivingEntity entity) {
/* 147 */     return 60.0F;
/*     */   }
/*     */   
/*     */   private static boolean canUnlock(LivingEntity entity) {
/* 151 */     if (!(entity instanceof PlayerEntity)) {
/* 152 */       return false;
/*     */     }
/*     */     
/* 155 */     PlayerEntity player = (PlayerEntity)entity;
/* 156 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/* 157 */     IQuestData questProps = QuestDataCapability.get(player);
/*     */     
/* 159 */     return (props.isBlackLeg() && questProps.hasFinishedQuest(ModQuests.BLACK_LEG_TRIAL_01));
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\blackleg\ConcasseAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */