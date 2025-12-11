/*     */ package xyz.pixelatedw.mineminenomi.abilities.doctor;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.HitTriggerComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class DopingAbility extends Ability {
/*  30 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "doping", new Pair[] {
/*  31 */         (Pair)ImmutablePair.of("Injects the target with special medicine that boosts their physical powers.", null), 
/*  32 */         (Pair)ImmutablePair.of("§aSHIFT-USE§r: User injects themselves", null)
/*     */       });
/*     */   
/*     */   private static final int COOLDOWN = 500;
/*     */   private static final int EFFECT_TIME = 200;
/*  37 */   public static final AbilityCore<DopingAbility> INSTANCE = (new AbilityCore.Builder("Doping", AbilityCategory.STYLE, DopingAbility::new))
/*  38 */     .addDescriptionLine(DESCRIPTION)
/*  39 */     .setSourceType(new SourceType[] { SourceType.FIST
/*  40 */       }).setUnlockCheck(DopingAbility::canUnlock)
/*  41 */     .build();
/*     */   
/*  43 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addStartEvent(this::startContinuityEvent).addEndEvent(this::endContinuityEvent);
/*  44 */   private final HitTriggerComponent hitTriggerComponent = (new HitTriggerComponent((IAbility)this)).addOnHitEvent(this::hitEvent).addTryHitEvent(this::tryHitEvent);
/*     */   
/*     */   public DopingAbility(AbilityCore<DopingAbility> core) {
/*  47 */     super(core);
/*     */     
/*  49 */     this.isNew = true;
/*  50 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.hitTriggerComponent });
/*     */     
/*  52 */     this.hitTriggerComponent.setBypassSameGroupProtection();
/*     */     
/*  54 */     addCanUseCheck(AbilityHelper::requiresMedicBag);
/*  55 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  59 */     this.continuousComponent.triggerContinuity(entity);
/*     */   }
/*     */   
/*     */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/*  63 */     if (entity.func_213453_ef()) {
/*  64 */       applyEffect(entity, entity);
/*     */       return;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/*  70 */     this.cooldownComponent.startCooldown(entity, 500.0F);
/*     */   }
/*     */   
/*     */   private HitTriggerComponent.HitResult tryHitEvent(LivingEntity entity, LivingEntity target, ModDamageSource source, IAbility ability) {
/*  74 */     if (!this.continuousComponent.isContinuous()) {
/*  75 */       return HitTriggerComponent.HitResult.PASS;
/*     */     }
/*     */     
/*  78 */     return HitTriggerComponent.HitResult.HIT;
/*     */   }
/*     */   
/*     */   private boolean hitEvent(LivingEntity entity, LivingEntity target, ModDamageSource source, IAbility ability) {
/*  82 */     source.setBypassFriendlyDamage();
/*     */     
/*  84 */     applyEffect(entity, target);
/*     */     
/*  86 */     return true;
/*     */   }
/*     */   
/*     */   private void applyEffect(LivingEntity entity, LivingEntity target) {
/*  90 */     target.func_195064_c(new EffectInstance((Effect)ModEffects.TENSION_HORMONE.get(), 200, 1));
/*  91 */     WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.FIRST_AID.get(), (Entity)entity, target.func_226277_ct_(), target.func_226278_cu_(), target.func_226281_cx_());
/*  92 */     this.continuousComponent.stopContinuity(entity);
/*     */   }
/*     */   
/*     */   private static boolean canUnlock(LivingEntity entity) {
/*  96 */     if (!(entity instanceof PlayerEntity)) {
/*  97 */       return false;
/*     */     }
/*     */     
/* 100 */     PlayerEntity player = (PlayerEntity)entity;
/* 101 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/* 102 */     IQuestData questProps = QuestDataCapability.get(player);
/*     */     
/* 104 */     return (props.isDoctor() && questProps.hasFinishedQuest(ModQuests.DOCTOR_TRIAL_03));
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\doctor\DopingAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */