/*     */ package xyz.pixelatedw.mineminenomi.abilities.toriphoenix;
/*     */ import java.util.function.Predicate;
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.PropelledFlightAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.MorphAbility2;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChangeStatsComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AttributeHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*     */ 
/*     */ public class PhoenixAssaultPointAbility extends MorphAbility2 {
/*  35 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "phoenix_assault_point", new Pair[] {
/*  36 */         (Pair)ImmutablePair.of("Transforms the user into a half-phoenix hybrid, which focuses on speed and healing.", null)
/*     */       });
/*  38 */   public static final AbilityCore<PhoenixAssaultPointAbility> INSTANCE = (new AbilityCore.Builder("Phoenix Assault Point", AbilityCategory.DEVIL_FRUITS, PhoenixAssaultPointAbility::new))
/*  39 */     .addDescriptionLine(DESCRIPTION)
/*  40 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(10.0F), ContinuousComponent.getTooltip(), ChangeStatsComponent.getTooltip()
/*  41 */       }).build();
/*     */   
/*  43 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*     */   
/*  45 */   private static final AbilityAttributeModifier REGEN_RATE_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_REGEN_RATE_UUID, INSTANCE, "Phoenix Assault Point Health Regeneration Speed Modifier", 0.6000000238418579D, AttributeModifier.Operation.ADDITION);
/*  46 */   private static final AbilityAttributeModifier STRENGTH_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_STRENGTH_UUID, INSTANCE, "Phoenix Assault Point Strength Modifier", 3.0D, AttributeModifier.Operation.ADDITION);
/*  47 */   private static final AbilityAttributeModifier TOUGHNESS_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_TOUGHNESS_UUID, INSTANCE, "Phoenix Assault Point Toughness Modifier", 1.0D, AttributeModifier.Operation.ADDITION);
/*     */   
/*     */   public PhoenixAssaultPointAbility(AbilityCore<PhoenixAssaultPointAbility> core) {
/*  50 */     super(core);
/*     */     
/*  52 */     Predicate<LivingEntity> isMorphed = entity -> this.morphComponent.isMorphed();
/*     */     
/*  54 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.REGEN_RATE, (AttributeModifier)REGEN_RATE_MODIFIER, isMorphed);
/*  55 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.PUNCH_DAMAGE, (AttributeModifier)STRENGTH_MODIFIER, isMorphed);
/*  56 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.TOUGHNESS, (AttributeModifier)TOUGHNESS_MODIFIER, isMorphed);
/*     */     
/*  58 */     this.continuousComponent.addStartEvent(this::onContinuityStart).addTickEvent(100, this::onContinuityTick).addEndEvent(this::onContinuityEnd);
/*     */     
/*  60 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.animationComponent });
/*     */   }
/*     */   
/*     */   private void onContinuityStart(LivingEntity entity, IAbility ability) {
/*  64 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/*  68 */     IAbilityData abilityDataProps = AbilityDataCapability.get(entity);
/*     */     
/*  70 */     if (abilityDataProps == null) {
/*     */       return;
/*     */     }
/*     */     
/*  74 */     PropelledFlightAbility flightAbility = (PropelledFlightAbility)abilityDataProps.getPassiveAbility(PhoenixFlightAbility.INSTANCE);
/*     */     
/*  76 */     if (flightAbility != null && !flightAbility.isPaused()) {
/*  77 */       PropelledFlightAbility.enableFlight((PlayerEntity)entity);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void onContinuityTick(LivingEntity entity, IAbility ability) {
/*  84 */     boolean isFlying = (!entity.func_233570_aj_() && DevilFruitHelper.getDifferenceToFloor((Entity)entity) > 1.0D);
/*     */     
/*  86 */     if (entity instanceof PlayerEntity) {
/*  87 */       isFlying |= ((PlayerEntity)entity).field_71075_bZ.field_75100_b;
/*     */     }
/*     */     
/*  90 */     if (isFlying) {
/*  91 */       if (this.animationComponent.isStopped()) {
/*  92 */         this.animationComponent.start(entity, ModAnimations.PHOENIX_ASSAULT_FLY, -1, e -> {
/*     */               BlueBirdAbility blueBirdAbility = (BlueBirdAbility)AbilityDataCapability.get(entity).getEquippedAbility(BlueBirdAbility.INSTANCE);
/*     */               
/*  95 */               return Boolean.valueOf((blueBirdAbility != null && ((ContinuousComponent)blueBirdAbility.getComponent(ModAbilityKeys.CONTINUOUS).get()).isContinuous()));
/*     */             });
/*     */       }
/*     */     } else {
/*  99 */       this.animationComponent.stop(entity);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void onContinuityEnd(LivingEntity entity, IAbility ability) {
/* 104 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 108 */     this.animationComponent.stop(entity);
/*     */     
/* 110 */     IAbilityData abilityDataProps = AbilityDataCapability.get(entity);
/*     */     
/* 112 */     if (abilityDataProps == null) {
/*     */       return;
/*     */     }
/*     */     
/* 116 */     PropelledFlightAbility flightAbility = (PropelledFlightAbility)abilityDataProps.getPassiveAbility(PhoenixFlightAbility.INSTANCE);
/*     */     
/* 118 */     if (flightAbility != null) {
/* 119 */       PropelledFlightAbility.disableFlight((PlayerEntity)entity);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public MorphInfo getTransformation() {
/* 125 */     return (MorphInfo)ModMorphs.PHOENIX_ASSAULT.get();
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\toriphoenix\PhoenixAssaultPointAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */