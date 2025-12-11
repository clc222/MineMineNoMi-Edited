/*    */ package xyz.pixelatedw.mineminenomi.abilities.ryupteranodon;
/*    */ 
/*    */ import java.util.function.Predicate;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.entity.ai.attributes.Attributes;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.PropelledFlightAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.MorphAbility2;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChangeStatsComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AttributeHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*    */ 
/*    */ public class PteranodonFlyPointAbility
/*    */   extends MorphAbility2 {
/* 32 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "pteranodon_fly_point", new Pair[] {
/* 33 */         (Pair)ImmutablePair.of("Transforms the user into an ancient pteranodon.", null)
/*    */       });
/* 35 */   public static final AbilityCore<PteranodonFlyPointAbility> INSTANCE = (new AbilityCore.Builder("Pteranodon Fly Point", AbilityCategory.DEVIL_FRUITS, PteranodonFlyPointAbility::new))
/* 36 */     .addDescriptionLine(DESCRIPTION)
/* 37 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(10.0F), ContinuousComponent.getTooltip(), ChangeStatsComponent.getTooltip()
/* 38 */       }).build();
/*    */   
/* 40 */   private static final AbilityAttributeModifier SPEED_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_MOVEMENT_SPEED_UUID, INSTANCE, "Pteranodon Fly Point Speed Modifier", 1.0D, AttributeModifier.Operation.ADDITION);
/* 41 */   private static final AbilityAttributeModifier ARMOR_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_ARMOR_UUID, INSTANCE, "Pteranodon Fly Point Armor Modifier", 2.0D, AttributeModifier.Operation.ADDITION);
/* 42 */   private static final AbilityAttributeModifier STRENGTH_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_STRENGTH_UUID, INSTANCE, "Pteranodon Fly Point Strength Modifier", 12.0D, AttributeModifier.Operation.ADDITION);
/* 43 */   private static final AbilityAttributeModifier TOUGHNESS_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_TOUGHNESS_UUID, INSTANCE, "Pteranodon Fly Point Toughness Modifier", 2.0D, AttributeModifier.Operation.ADDITION);
/*    */   
/*    */   public PteranodonFlyPointAbility(AbilityCore<PteranodonFlyPointAbility> core) {
/* 46 */     super(core);
/*    */     
/* 48 */     Predicate<LivingEntity> isMorphed = entity -> this.morphComponent.isMorphed();
/*    */     
/* 50 */     this.statsComponent.addAttributeModifier(Attributes.field_233825_h_, (AttributeModifier)SPEED_MODIFIER, isMorphed);
/* 51 */     this.statsComponent.addAttributeModifier(Attributes.field_233826_i_, (AttributeModifier)ARMOR_MODIFIER, isMorphed);
/* 52 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.PUNCH_DAMAGE, (AttributeModifier)STRENGTH_MODIFIER, isMorphed);
/* 53 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.TOUGHNESS, (AttributeModifier)TOUGHNESS_MODIFIER, isMorphed);
/*    */     
/* 55 */     this.continuousComponent.addStartEvent(this::onContinuityStart).addEndEvent(this::onContinuityEnd);
/*    */   }
/*    */   
/*    */   private void onContinuityStart(LivingEntity entity, IAbility ability) {
/* 59 */     if (entity.field_70170_p.field_72995_K) {
/*    */       return;
/*    */     }
/*    */     
/* 63 */     IAbilityData abilityDataProps = AbilityDataCapability.get(entity);
/*    */     
/* 65 */     if (abilityDataProps == null) {
/*    */       return;
/*    */     }
/*    */     
/* 69 */     PropelledFlightAbility pteranodonFlightAbility = (PropelledFlightAbility)abilityDataProps.getPassiveAbility(PteranodonFlightAbility.INSTANCE);
/*    */     
/* 71 */     if (pteranodonFlightAbility != null && !pteranodonFlightAbility.isPaused()) {
/* 72 */       PropelledFlightAbility.enableFlight((PlayerEntity)entity);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private void onContinuityEnd(LivingEntity entity, IAbility ability) {
/* 79 */     if (entity.field_70170_p.field_72995_K) {
/*    */       return;
/*    */     }
/*    */     
/* 83 */     IAbilityData abilityDataProps = AbilityDataCapability.get(entity);
/*    */     
/* 85 */     if (abilityDataProps == null) {
/*    */       return;
/*    */     }
/*    */     
/* 89 */     PropelledFlightAbility pteranodonFlightAbility = (PropelledFlightAbility)abilityDataProps.getPassiveAbility(PteranodonFlightAbility.INSTANCE);
/*    */     
/* 91 */     if (pteranodonFlightAbility != null) {
/* 92 */       PropelledFlightAbility.disableFlight((PlayerEntity)entity);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public MorphInfo getTransformation() {
/* 98 */     return (MorphInfo)ModMorphs.PTERA_FLY.get();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\ryupteranodon\PteranodonFlyPointAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */