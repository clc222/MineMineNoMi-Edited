/*     */ package xyz.pixelatedw.mineminenomi.abilities.cyborg;
/*     */ 
/*     */ import java.util.UUID;
/*     */ import java.util.function.Predicate;
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.potion.Effects;
/*     */ import net.minecraft.util.Util;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraftforge.common.ForgeMod;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityStat;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUseResult;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChangeStatsComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.entities.SUpdateColaAmountPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ public class ColaOverdriveAbility extends Ability {
/*  38 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "cola_overdrive", new Pair[] {
/*  39 */         (Pair)ImmutablePair.of("The user absorbs cola from their reserves to boost their physical abilities.", null) });
/*     */   private static final int DRAIN_PER_TICK = 2;
/*     */   private static final float HOLD_TIME = 300.0F;
/*     */   private static final float BASE_COOLDOWN = 100.0F;
/*     */   private static final float COOLDOWN_PER_TICK = 1.0F;
/*     */   private static final AbilityDescriptionLine.IDescriptionLine COLA_TOOLTIP;
/*     */   
/*     */   static {
/*  47 */     COLA_TOOLTIP = ((e, a) -> {
/*     */         IEntityStats props = EntityStatsCapability.get(e);
/*     */         int half = (int)Math.floor((props.getMaxCola() / 2.0F));
/*     */         AbilityStat.Builder statBuilder = new AbilityStat.Builder((ITextComponent)ModI18n.ABILITY_DESCRIPTION_STAT_NAME_COLA, half);
/*     */         return statBuilder.build().getStatDescription();
/*     */       });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  57 */   public static final AbilityCore<ColaOverdriveAbility> INSTANCE = (new AbilityCore.Builder("Cola Overdrive", AbilityCategory.RACIAL, ColaOverdriveAbility::new))
/*  58 */     .addDescriptionLine(DESCRIPTION)
/*  59 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(100.0F), COLA_TOOLTIP
/*  60 */       }).setUnlockCheck(ColaOverdriveAbility::canUnlock)
/*  61 */     .build();
/*     */   
/*  63 */   private static final AbilityAttributeModifier MOVEMENT_SPEED_MODIFIER = new AbilityAttributeModifier(UUID.fromString("876d97f9-d8a1-487c-af05-ca97d90deb90"), INSTANCE, "Cola Overdrive Movement Speed Multiplier", 0.25D, AttributeModifier.Operation.ADDITION);
/*  64 */   private static final AbilityAttributeModifier SWIM_SPEED_MODIFIER = new AbilityAttributeModifier(UUID.fromString("876d97f9-d8a1-487c-af05-ca97d90deb90"), INSTANCE, "Cola Overdrive Swim Speed Multiplier", 1.0D, AttributeModifier.Operation.ADDITION);
/*  65 */   private static final AbilityAttributeModifier JUMP_HEIGHT_MODIFIER = new AbilityAttributeModifier(UUID.fromString("74ddb8cc-1d53-47be-9ed8-ff1d26e8b665"), INSTANCE, "Cola Overdrive Jump Height Multiplier", 2.55D, AttributeModifier.Operation.ADDITION);
/*  66 */   private static final AbilityAttributeModifier FALL_RESISTANCE_MODIFIER = new AbilityAttributeModifier(UUID.fromString("9120f731-0f9e-4c12-908c-1d4142daacce"), INSTANCE, "Cola Overdrive Fall Resistance Multiplier", 5.75D, AttributeModifier.Operation.ADDITION);
/*  67 */   private static final AbilityAttributeModifier STEP_HEIGHT_MODIFIER = new AbilityAttributeModifier(UUID.fromString("43f604bd-1ab3-4a27-9b10-bac40ade0e29"), INSTANCE, "Cola Overdrive Step Height Modifier", 1.0D, AttributeModifier.Operation.ADDITION);
/*     */   
/*  69 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addTickEvent(this::onContinuityTick).addEndEvent(this::onContinuityEnd);
/*  70 */   private final ChangeStatsComponent changeStatsComponent = new ChangeStatsComponent((IAbility)this);
/*     */   
/*     */   public ColaOverdriveAbility(AbilityCore<ColaOverdriveAbility> core) {
/*  73 */     super(core);
/*     */     
/*  75 */     this.isNew = true;
/*     */     
/*  77 */     Predicate<LivingEntity> isContinuityActive = entity -> this.continuousComponent.isContinuous();
/*     */     
/*  79 */     this.changeStatsComponent.addAttributeModifier(Attributes.field_233821_d_, (AttributeModifier)MOVEMENT_SPEED_MODIFIER, isContinuityActive);
/*  80 */     this.changeStatsComponent.addAttributeModifier((Supplier)ForgeMod.SWIM_SPEED, (AttributeModifier)SWIM_SPEED_MODIFIER, isContinuityActive);
/*  81 */     this.changeStatsComponent.addAttributeModifier((Supplier)ModAttributes.JUMP_HEIGHT, (AttributeModifier)JUMP_HEIGHT_MODIFIER, isContinuityActive);
/*  82 */     this.changeStatsComponent.addAttributeModifier((Supplier)ModAttributes.FALL_RESISTANCE, (AttributeModifier)FALL_RESISTANCE_MODIFIER, isContinuityActive);
/*  83 */     this.changeStatsComponent.addAttributeModifier((Supplier)ModAttributes.STEP_HEIGHT, (AttributeModifier)STEP_HEIGHT_MODIFIER, isContinuityActive);
/*     */     
/*  85 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.changeStatsComponent });
/*  86 */     addCanUseCheck(this::canUseAbility);
/*  87 */     addUseEvent(this::onUse);
/*     */   }
/*     */   
/*     */   private void onUse(LivingEntity entity, IAbility ability) {
/*  91 */     this.continuousComponent.triggerContinuity(entity, 300.0F);
/*     */   }
/*     */   
/*     */   private void onContinuityTick(LivingEntity entity, IAbility ability) {
/*  95 */     IEntityStats props = EntityStatsCapability.get(entity);
/*     */     
/*  97 */     props.alterCola(-2);
/*     */     
/*  99 */     if (entity instanceof net.minecraft.entity.player.ServerPlayerEntity) {
/* 100 */       WyNetwork.sendTo(new SUpdateColaAmountPacket(entity), (PlayerEntity)entity);
/*     */     }
/*     */     
/* 103 */     entity.func_195064_c(new EffectInstance(Effects.field_76429_m, 5, 0));
/*     */     
/* 105 */     AbilityUseResult result = canUseAbility(entity, (IAbility)this);
/*     */     
/* 107 */     if (result.isFail()) {
/* 108 */       entity.func_145747_a(result.getMessage(), Util.field_240973_b_);
/*     */       
/* 110 */       this.continuousComponent.stopContinuity(entity);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void onContinuityEnd(LivingEntity entity, IAbility ability) {
/* 115 */     this.cooldownComponent.startCooldown(entity, 100.0F + 1.0F * this.continuousComponent.getContinueTime());
/*     */   }
/*     */   
/*     */   private AbilityUseResult canUseAbility(LivingEntity entity, IAbility ability) {
/* 119 */     IEntityStats props = EntityStatsCapability.get(entity);
/*     */     
/* 121 */     if (props.getCola() <= 0) {
/* 122 */       return AbilityUseResult.fail((ITextComponent)ModI18n.ABILITY_MESSAGE_NOT_ENOUGH_COLA);
/*     */     }
/*     */     
/* 125 */     return AbilityUseResult.success();
/*     */   }
/*     */   
/*     */   private static boolean canUnlock(LivingEntity user) {
/* 129 */     IEntityStats props = EntityStatsCapability.get(user);
/*     */     
/* 131 */     return props.isCyborg();
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\cyborg\ColaOverdriveAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */