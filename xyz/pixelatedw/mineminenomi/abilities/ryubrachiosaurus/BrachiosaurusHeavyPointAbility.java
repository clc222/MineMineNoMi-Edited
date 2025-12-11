/*    */ package xyz.pixelatedw.mineminenomi.abilities.ryubrachiosaurus;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.entity.ai.attributes.Attributes;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraftforge.common.ForgeMod;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.MorphAbility2;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChangeStatsComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AttributeHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*    */ 
/*    */ public class BrachiosaurusHeavyPointAbility extends MorphAbility2 {
/* 24 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "brachiosaurus_heavy_point", new Pair[] {
/* 25 */         (Pair)ImmutablePair.of("Transforms the user into an ancient half-brachiosaurus, which focuses on strength.", null)
/*    */       });
/* 27 */   public static final AbilityCore<BrachiosaurusHeavyPointAbility> INSTANCE = (new AbilityCore.Builder("Brachiosaurus Heavy Point", AbilityCategory.DEVIL_FRUITS, BrachiosaurusHeavyPointAbility::new))
/* 28 */     .addDescriptionLine(DESCRIPTION)
/* 29 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(10.0F), ContinuousComponent.getTooltip(), ChangeStatsComponent.getTooltip()
/* 30 */       }).build();
/*    */   
/* 32 */   private static final AbilityAttributeModifier HEALTH_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_HEALTH_UUID, INSTANCE, "Brachiosaurus Heavy Point Health Modifier", 15.0D, AttributeModifier.Operation.ADDITION);
/* 33 */   private static final AbilityAttributeModifier SPEED_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_MOVEMENT_SPEED_UUID, INSTANCE, "Brachiosaurus Heavy Point Speed Modifier", -0.05D, AttributeModifier.Operation.MULTIPLY_BASE);
/* 34 */   private static final AbilityAttributeModifier ARMOR_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_ARMOR_UUID, INSTANCE, "Brachiosaurus Heavy Point Armor Modifier", 15.0D, AttributeModifier.Operation.ADDITION);
/* 35 */   private static final AbilityAttributeModifier STRENGTH_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_STRENGTH_UUID, INSTANCE, "Brachiosaurus Heavy Point Strength Modifier", 8.0D, AttributeModifier.Operation.ADDITION);
/* 36 */   private static final AbilityAttributeModifier ATTACK_SPEED_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_ATTACK_SPEED_UUID, INSTANCE, "Brachiosaurus Heavy Point Attack Speed Modifier", -0.20000000298023224D, AttributeModifier.Operation.ADDITION);
/* 37 */   private static final AbilityAttributeModifier REACH_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_ATTACK_REACH_UUID, INSTANCE, "Brachiosaurus Heavy Point Reach Modifier", 1.0D, AttributeModifier.Operation.ADDITION);
/* 38 */   private static final AbilityAttributeModifier STEP_HEIGHT_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_STEP_HEIGHT_UUID, INSTANCE, "Brachiosaurus Heavy Point Step Height Modifier", 1.5D, AttributeModifier.Operation.ADDITION);
/* 39 */   private static final AbilityAttributeModifier KNOCKBACK_RESISTANCE_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_KNOCKBACK_RESISTANCE_UUID, INSTANCE, "Brachiosaurus Heavy Point Knockback Resistance Modifier", 0.5D, AttributeModifier.Operation.ADDITION);
/* 40 */   private static final AbilityAttributeModifier TOUGHNESS_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_TOUGHNESS_UUID, INSTANCE, "Brachiosaurus Heavy Point Toughness Modifier", 4.0D, AttributeModifier.Operation.ADDITION);
/* 41 */   private static final AbilityAttributeModifier FALL_RESISTANCE_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_FALL_RESISTANCE_UUID, INSTANCE, "Brachiosaurus Heavy Point Fall Resistance Modifier", 5.0D, AttributeModifier.Operation.ADDITION);
/*    */   
/*    */   public BrachiosaurusHeavyPointAbility(AbilityCore<BrachiosaurusHeavyPointAbility> core) {
/* 44 */     super(core);
/*    */     
/* 46 */     this.statsComponent.addAttributeModifier(Attributes.field_233821_d_, (AttributeModifier)SPEED_MODIFIER);
/* 47 */     this.statsComponent.addAttributeModifier(Attributes.field_233818_a_, (AttributeModifier)HEALTH_MODIFIER);
/* 48 */     this.statsComponent.addAttributeModifier(Attributes.field_233826_i_, (AttributeModifier)ARMOR_MODIFIER);
/* 49 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.PUNCH_DAMAGE, (AttributeModifier)STRENGTH_MODIFIER);
/* 50 */     this.statsComponent.addAttributeModifier(Attributes.field_233825_h_, (AttributeModifier)ATTACK_SPEED_MODIFIER);
/* 51 */     this.statsComponent.addAttributeModifier((Supplier)ForgeMod.REACH_DISTANCE, (AttributeModifier)REACH_MODIFIER);
/* 52 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.ATTACK_RANGE, (AttributeModifier)REACH_MODIFIER);
/* 53 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.STEP_HEIGHT, (AttributeModifier)STEP_HEIGHT_MODIFIER);
/* 54 */     this.statsComponent.addAttributeModifier(Attributes.field_233820_c_, (AttributeModifier)KNOCKBACK_RESISTANCE_MODIFIER);
/* 55 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.TOUGHNESS, (AttributeModifier)TOUGHNESS_MODIFIER);
/* 56 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.FALL_RESISTANCE, (AttributeModifier)FALL_RESISTANCE_MODIFIER);
/*    */   }
/*    */ 
/*    */   
/*    */   public MorphInfo getTransformation() {
/* 61 */     return (MorphInfo)ModMorphs.BRACHIO_HEAVY.get();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\ryubrachiosaurus\BrachiosaurusHeavyPointAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */