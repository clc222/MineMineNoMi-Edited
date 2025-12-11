/*     */ package xyz.pixelatedw.mineminenomi.abilities.blackleg;
/*     */ import java.util.UUID;
/*     */ import java.util.function.Predicate;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AltModeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChangeStatsComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.SkinOverlayComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ 
/*     */ public class DiableJambeAbility extends Ability {
/*  42 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "diable_jambe", new Pair[] {
/*  43 */         (Pair)ImmutablePair.of("The user heats up their leg, dealing additional damage and shortly setting the target on fire (Enhances all Blackleg attacks)", null)
/*     */       });
/*     */   
/*     */   private static final float HOLD_TIME = 600.0F;
/*     */   private static final float MIN_COOLDOWN = 80.0F;
/*     */   private static final float MAX_COOLDOWN = 680.0F;
/*  49 */   public static final AbilityCore<DiableJambeAbility> INSTANCE = (new AbilityCore.Builder("Diable Jambe", AbilityCategory.STYLE, DiableJambeAbility::new))
/*  50 */     .addDescriptionLine(DESCRIPTION)
/*  51 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(80.0F, 680.0F), ContinuousComponent.getTooltip(600.0F), ChangeStatsComponent.getTooltip()
/*  52 */       }).setUnlockCheck(DiableJambeAbility::canUnlock)
/*  53 */     .setSourceElement(SourceElement.FIRE)
/*  54 */     .build();
/*     */   
/*  56 */   private static final AbilityOverlay OVERLAY = (new AbilityOverlay.Builder()).setTexture(ModResources.HOT_BOILING_SPECIAL_ARM).setColor("#FFBB44AA").build();
/*     */   
/*  58 */   private static final AbilityAttributeModifier DIABLE_JAMBE_STRENGTH_MODIFIER = new AbilityAttributeModifier(UUID.fromString("e3ae074c-40a9-49ff-aa3b-7cc9b98ddc2d"), INSTANCE, "Diable Jambe Attack Multiplier", 4.0D, AttributeModifier.Operation.ADDITION);
/*  59 */   private static final AbilityAttributeModifier DIABLE_JAMBE_ATTACK_SPEED_MODIFIER = new AbilityAttributeModifier(UUID.fromString("a984413a-7459-4989-8362-7c46a2663f0e"), INSTANCE, "Diable Jambe Speed Multiplier", 0.4000000059604645D, AttributeModifier.Operation.ADDITION);
/*     */   
/*  61 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addStartEvent(100, this::onContinuityStartEvent).addTickEvent(100, this::onContinuityTickEvent).addEndEvent(100, this::onContinuityEndEvent);
/*  62 */   private final SkinOverlayComponent skinOverlayComponent = new SkinOverlayComponent((IAbility)this, OVERLAY, new AbilityOverlay[0]);
/*  63 */   private final ChangeStatsComponent statsComponent = new ChangeStatsComponent((IAbility)this);
/*     */   
/*     */   private boolean frozen = false;
/*     */   
/*     */   public DiableJambeAbility(AbilityCore<DiableJambeAbility> core) {
/*  68 */     super(core);
/*     */     
/*  70 */     this.isNew = true;
/*     */     
/*  72 */     Predicate<LivingEntity> isContinuityActive = entity -> this.continuousComponent.isContinuous();
/*  73 */     this.statsComponent.addAttributeModifier(Attributes.field_233825_h_, (AttributeModifier)DIABLE_JAMBE_ATTACK_SPEED_MODIFIER, isContinuityActive);
/*  74 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.PUNCH_DAMAGE, (AttributeModifier)DIABLE_JAMBE_STRENGTH_MODIFIER, isContinuityActive);
/*     */     
/*  76 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.skinOverlayComponent, (AbilityComponent)this.statsComponent });
/*     */     
/*  78 */     addUseEvent(this::onUseEvent);
/*     */   }
/*     */   
/*     */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/*  82 */     this.continuousComponent.triggerContinuity(entity, 600.0F);
/*     */   }
/*     */   
/*     */   private void onContinuityStartEvent(LivingEntity entity, IAbility ability) {
/*  86 */     this.frozen = false;
/*     */     
/*  88 */     IAbilityData abilityProps = AbilityDataCapability.get(entity);
/*  89 */     ExtraHachisAbility extraHachis = (ExtraHachisAbility)abilityProps.getEquippedAbility(ExtraHachisAbility.INSTANCE);
/*  90 */     if (extraHachis != null) {
/*  91 */       extraHachis.getComponent(ModAbilityKeys.ALT_MODE).ifPresent(component -> component.setMode(entity, ExtraHachisAbility.Mode.POELE_A_FRIRE));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void onContinuityTickEvent(LivingEntity entity, IAbility ability) {
/*  98 */     if (entity.func_70644_a((Effect)ModEffects.FROZEN.get())) {
/*  99 */       this.frozen = true;
/* 100 */       AbilityHelper.reduceEffect(entity.func_70660_b((Effect)ModEffects.FROZEN.get()), 1.100000023841858D);
/* 101 */       this.continuousComponent.stopContinuity(entity);
/*     */       
/*     */       return;
/*     */     } 
/* 105 */     if (entity.func_70644_a((Effect)ModEffects.FROSTBITE.get())) {
/* 106 */       this.frozen = true;
/* 107 */       AbilityHelper.reduceEffect(entity.func_70660_b((Effect)ModEffects.FROSTBITE.get()), 1.5D);
/* 108 */       this.continuousComponent.stopContinuity(entity);
/*     */       
/*     */       return;
/*     */     } 
/* 112 */     WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.DIABLE_JAMBE.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/*     */   }
/*     */ 
/*     */   
/*     */   private void onContinuityEndEvent(LivingEntity entity, IAbility ability) {
/* 117 */     this.cooldownComponent.startCooldown(entity, (this.frozen ? 160.0F : 80.0F) + this.continuousComponent.getContinueTime());
/*     */     
/* 119 */     IAbilityData abilityProps = AbilityDataCapability.get(entity);
/* 120 */     ExtraHachisAbility extraHachis = (ExtraHachisAbility)abilityProps.getEquippedAbility(ExtraHachisAbility.INSTANCE);
/* 121 */     if (extraHachis != null) {
/* 122 */       extraHachis.getComponent(ModAbilityKeys.ALT_MODE).ifPresent(component -> component.revertToDefault(entity));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean canUnlock(LivingEntity entity) {
/* 129 */     if (!(entity instanceof PlayerEntity)) {
/* 130 */       return false;
/*     */     }
/*     */     
/* 133 */     PlayerEntity player = (PlayerEntity)entity;
/* 134 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/* 135 */     IQuestData questProps = QuestDataCapability.get(player);
/*     */     
/* 137 */     return (props.isBlackLeg() && questProps.hasFinishedQuest(ModQuests.BLACK_LEG_TRIAL_05));
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\blackleg\DiableJambeAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */