/*    */ package xyz.pixelatedw.mineminenomi.abilities.kame;
/*    */ 
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
/*    */ public class KameWalkPointAbility extends MorphAbility2 {
/* 25 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "kame_walk_point", new Pair[] {
/* 26 */         (Pair)ImmutablePair.of("Transforms the user into a turtle hybrid, which focuses on defense.", null)
/*    */       });
/* 28 */   public static final AbilityCore<KameWalkPointAbility> INSTANCE = (new AbilityCore.Builder("Kame Walk Point", AbilityCategory.DEVIL_FRUITS, KameWalkPointAbility::new))
/* 29 */     .addDescriptionLine(DESCRIPTION)
/* 30 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(10.0F), ContinuousComponent.getTooltip(), ChangeStatsComponent.getTooltip()
/* 31 */       }).build();
/*    */   
/* 33 */   private static final AbilityAttributeModifier SPEED_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_MOVEMENT_SPEED_UUID, INSTANCE, "Kame Walk Point Modifier", -0.4000000059604645D, AttributeModifier.Operation.MULTIPLY_BASE);
/* 34 */   private static final AbilityAttributeModifier ARMOR_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_ARMOR_UUID, INSTANCE, "Kame Walk Point Modifier", 15.0D, AttributeModifier.Operation.ADDITION);
/* 35 */   private static final AbilityAttributeModifier KNOCKBACK_RESISTANCE_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_KNOCKBACK_RESISTANCE_UUID, INSTANCE, "Kame Walk Point Knockback Resistance Modifier", 1.0D, AttributeModifier.Operation.ADDITION);
/* 36 */   private static final AbilityAttributeModifier JUMP_BOOST_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_JUMP_BOOST_UUID, INSTANCE, "Kame Walk Point Jump Modifier", -0.25D, AttributeModifier.Operation.ADDITION);
/* 37 */   private static final AbilityAttributeModifier WATER_SPEED_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_SWIM_SPEED_UUID, INSTANCE, "Kame Walk Point Water Speed Modifier", 1.0D, AttributeModifier.Operation.ADDITION);
/* 38 */   private static final AbilityAttributeModifier TOUGHNESS_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_TOUGHNESS_UUID, INSTANCE, "Kame Walk Point Toughness Modifier", 10.0D, AttributeModifier.Operation.ADDITION);
/*    */   
/*    */   public KameWalkPointAbility(AbilityCore<KameWalkPointAbility> core) {
/* 41 */     super(core);
/*    */     
/* 43 */     this.statsComponent.addAttributeModifier(Attributes.field_233821_d_, (AttributeModifier)SPEED_MODIFIER);
/* 44 */     this.statsComponent.addAttributeModifier(Attributes.field_233826_i_, (AttributeModifier)ARMOR_MODIFIER);
/* 45 */     this.statsComponent.addAttributeModifier(Attributes.field_233820_c_, (AttributeModifier)KNOCKBACK_RESISTANCE_MODIFIER);
/* 46 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.JUMP_HEIGHT, (AttributeModifier)JUMP_BOOST_MODIFIER);
/* 47 */     this.statsComponent.addAttributeModifier((Supplier)ForgeMod.SWIM_SPEED, (AttributeModifier)WATER_SPEED_MODIFIER);
/* 48 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.TOUGHNESS, (AttributeModifier)TOUGHNESS_MODIFIER);
/*    */   }
/*    */ 
/*    */   
/*    */   public MorphInfo getTransformation() {
/* 53 */     return (MorphInfo)ModMorphs.KAME_WALK.get();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\kame\KameWalkPointAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */