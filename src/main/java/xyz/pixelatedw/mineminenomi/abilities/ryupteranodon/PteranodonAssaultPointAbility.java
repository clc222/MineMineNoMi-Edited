/*     */ package xyz.pixelatedw.mineminenomi.abilities.ryupteranodon;
/*     */ import java.util.function.Predicate;
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
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
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*     */ 
/*     */ public class PteranodonAssaultPointAbility extends MorphAbility2 {
/*  35 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "pteranodon_assault_point", new Pair[] {
/*  36 */         (Pair)ImmutablePair.of("Transforms the user into a half-pteranodon hybrid.", null)
/*     */       });
/*  38 */   public static final AbilityCore<PteranodonAssaultPointAbility> INSTANCE = (new AbilityCore.Builder("Pteranodon Assault Point", AbilityCategory.DEVIL_FRUITS, PteranodonAssaultPointAbility::new))
/*  39 */     .addDescriptionLine(DESCRIPTION)
/*  40 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(10.0F), ContinuousComponent.getTooltip(), ChangeStatsComponent.getTooltip()
/*  41 */       }).build();
/*     */   
/*  43 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*     */   
/*  45 */   private static final AbilityAttributeModifier MOVEMENT_SPEED_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_MOVEMENT_SPEED_UUID, INSTANCE, "Pteranodon Assault Point Movement Speed Modifier", 0.05D, AttributeModifier.Operation.ADDITION);
/*  46 */   private static final AbilityAttributeModifier ATTACK_SPEED_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_ATTACK_SPEED_UUID, INSTANCE, "Pteranodon Assault Point Attack Speed Modifier", 0.1D, AttributeModifier.Operation.ADDITION);
/*  47 */   private static final AbilityAttributeModifier STRENGTH_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_STRENGTH_UUID, INSTANCE, "Pteranodon Assault Point Strength Modifier", 3.0D, AttributeModifier.Operation.ADDITION);
/*  48 */   private static final AbilityAttributeModifier TOUGHNESS_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_TOUGHNESS_UUID, INSTANCE, "Pteranodon Assault Point Toughness Modifier", 1.0D, AttributeModifier.Operation.ADDITION);
/*     */   
/*     */   public PteranodonAssaultPointAbility(AbilityCore<PteranodonAssaultPointAbility> core) {
/*  51 */     super(core);
/*     */     
/*  53 */     Predicate<LivingEntity> isMorphed = entity -> this.morphComponent.isMorphed();
/*     */     
/*  55 */     this.statsComponent.addAttributeModifier(Attributes.field_233821_d_, (AttributeModifier)MOVEMENT_SPEED_MODIFIER, isMorphed);
/*  56 */     this.statsComponent.addAttributeModifier(Attributes.field_233825_h_, (AttributeModifier)ATTACK_SPEED_MODIFIER, isMorphed);
/*  57 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.PUNCH_DAMAGE, (AttributeModifier)STRENGTH_MODIFIER, isMorphed);
/*  58 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.TOUGHNESS, (AttributeModifier)TOUGHNESS_MODIFIER, isMorphed);
/*     */     
/*  60 */     this.continuousComponent.addStartEvent(this::onContinuityStart).addTickEvent(100, this::onContinuityTick).addEndEvent(this::onContinuityEnd);
/*     */     
/*  62 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.animationComponent });
/*     */   }
/*     */   
/*     */   private void onContinuityStart(LivingEntity entity, IAbility ability) {
/*  66 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/*  70 */     IAbilityData abilityDataProps = AbilityDataCapability.get(entity);
/*     */     
/*  72 */     if (abilityDataProps == null) {
/*     */       return;
/*     */     }
/*     */     
/*  76 */     PropelledFlightAbility pteranodonFlightAbility = (PropelledFlightAbility)abilityDataProps.getPassiveAbility(PteranodonFlightAbility.INSTANCE);
/*     */     
/*  78 */     if (pteranodonFlightAbility != null && !pteranodonFlightAbility.isPaused()) {
/*  79 */       PropelledFlightAbility.enableFlight((PlayerEntity)entity);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void onContinuityTick(LivingEntity entity, IAbility ability) {
/*  86 */     boolean isFlying = (!entity.func_233570_aj_() && DevilFruitHelper.getDifferenceToFloor((Entity)entity) > 1.0D);
/*     */     
/*  88 */     if (entity instanceof PlayerEntity) {
/*  89 */       isFlying |= ((PlayerEntity)entity).field_71075_bZ.field_75100_b;
/*     */     }
/*     */     
/*  92 */     if (isFlying) {
/*  93 */       if (this.animationComponent.isStopped()) {
/*  94 */         this.animationComponent.start(entity, ModAnimations.PTERA_ASSAULT_FLY);
/*     */       }
/*     */     } else {
/*     */       
/*  98 */       this.animationComponent.stop(entity);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void onContinuityEnd(LivingEntity entity, IAbility ability) {
/* 103 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 107 */     this.animationComponent.stop(entity);
/*     */     
/* 109 */     IAbilityData abilityDataProps = AbilityDataCapability.get(entity);
/*     */     
/* 111 */     if (abilityDataProps == null) {
/*     */       return;
/*     */     }
/*     */     
/* 115 */     PropelledFlightAbility pteranodonFlightAbility = (PropelledFlightAbility)abilityDataProps.getPassiveAbility(PteranodonFlightAbility.INSTANCE);
/*     */     
/* 117 */     if (pteranodonFlightAbility != null) {
/* 118 */       PropelledFlightAbility.disableFlight((PlayerEntity)entity);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public MorphInfo getTransformation() {
/* 124 */     return (MorphInfo)ModMorphs.PTERA_ASSAULT.get();
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\ryupteranodon\PteranodonAssaultPointAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */