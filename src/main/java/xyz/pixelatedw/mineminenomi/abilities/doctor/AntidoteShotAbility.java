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
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.HitTriggerComponent;
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
/*     */ 
/*     */ public class AntidoteShotAbility extends Ability {
/*  30 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "antidote_shot", new Pair[] {
/*  31 */         (Pair)ImmutablePair.of("Injects the target with an antidote making them immune to certain negative effects.", null), 
/*  32 */         (Pair)ImmutablePair.of("§aSHIFT-USE§r: User injects themselves", null)
/*     */       });
/*     */   
/*     */   private static final int COOLDOWN = 300;
/*     */   private static final int EFFECT_TIME = 200;
/*  37 */   public static final AbilityCore<AntidoteShotAbility> INSTANCE = (new AbilityCore.Builder("Antidote Shot", AbilityCategory.STYLE, AntidoteShotAbility::new))
/*  38 */     .addDescriptionLine(DESCRIPTION)
/*  39 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(300.0F)
/*  40 */       }).setUnlockCheck(AntidoteShotAbility::canUnlock)
/*  41 */     .build();
/*     */   
/*  43 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addEndEvent(this::endContinuityEvent);
/*  44 */   private final HitTriggerComponent hitTriggerComponent = (new HitTriggerComponent((IAbility)this)).addTryHitEvent(this::tryHitEvent).addOnHitEvent(this::onHitEvent);
/*     */   
/*     */   public AntidoteShotAbility(AbilityCore<AntidoteShotAbility> core) {
/*  47 */     super(core);
/*     */     
/*  49 */     this.isNew = true;
/*     */     
/*  51 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.hitTriggerComponent });
/*     */     
/*  53 */     this.hitTriggerComponent.setBypassSameGroupProtection();
/*     */     
/*  55 */     addCanUseCheck(AbilityHelper::requiresMedicBag);
/*  56 */     addUseEvent(this::onUseEvent);
/*     */   }
/*     */   
/*     */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/*  60 */     if (this.continuousComponent.isContinuous()) {
/*  61 */       this.continuousComponent.stopContinuity(entity);
/*     */       
/*     */       return;
/*     */     } 
/*  65 */     this.continuousComponent.triggerContinuity(entity);
/*     */     
/*  67 */     if (entity.func_213453_ef()) {
/*  68 */       applyAntidoteEffect(entity, entity);
/*     */       return;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/*  74 */     this.cooldownComponent.startCooldown(entity, 300.0F);
/*     */   }
/*     */   
/*     */   private HitTriggerComponent.HitResult tryHitEvent(LivingEntity entity, LivingEntity target, ModDamageSource source, IAbility ability) {
/*  78 */     if (this.continuousComponent.isContinuous()) {
/*  79 */       return HitTriggerComponent.HitResult.HIT;
/*     */     }
/*     */     
/*  82 */     return HitTriggerComponent.HitResult.PASS;
/*     */   }
/*     */   
/*     */   private boolean onHitEvent(LivingEntity entity, LivingEntity target, ModDamageSource source, IAbility ability) {
/*  86 */     applyAntidoteEffect(entity, target);
/*     */     
/*  88 */     return true;
/*     */   }
/*     */   
/*     */   private void applyAntidoteEffect(LivingEntity entity, LivingEntity target) {
/*  92 */     target.func_195064_c(new EffectInstance((Effect)ModEffects.ANTIDOTE.get(), 200, 0));
/*  93 */     WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.FIRST_AID.get(), (Entity)entity, target.func_226277_ct_(), target.func_226278_cu_(), target.func_226281_cx_());
/*  94 */     this.continuousComponent.stopContinuity(entity);
/*     */   }
/*     */   
/*     */   private static boolean canUnlock(LivingEntity entity) {
/*  98 */     if (!(entity instanceof PlayerEntity)) {
/*  99 */       return false;
/*     */     }
/*     */     
/* 102 */     PlayerEntity player = (PlayerEntity)entity;
/* 103 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/* 104 */     IQuestData questProps = QuestDataCapability.get(player);
/*     */     
/* 106 */     return (props.isDoctor() && questProps.hasFinishedQuest(ModQuests.DOCTOR_TRIAL_04));
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\doctor\AntidoteShotAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */