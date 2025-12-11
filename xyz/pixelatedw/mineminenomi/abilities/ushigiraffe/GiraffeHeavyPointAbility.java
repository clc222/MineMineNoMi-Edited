/*    */ package xyz.pixelatedw.mineminenomi.abilities.ushigiraffe;
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
/*    */ public class GiraffeHeavyPointAbility extends MorphAbility2 {
/* 25 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "giraffe_heavy_point", new Pair[] {
/* 26 */         (Pair)ImmutablePair.of("Transforms the user into a half-giraffe hybrid, which focuses on strength.", null)
/*    */       });
/* 28 */   public static final AbilityCore INSTANCE = (new AbilityCore.Builder("Giraffe Heavy Point", AbilityCategory.DEVIL_FRUITS, GiraffeHeavyPointAbility::new))
/* 29 */     .addDescriptionLine(DESCRIPTION)
/* 30 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(10.0F), ContinuousComponent.getTooltip(), ChangeStatsComponent.getTooltip()
/* 31 */       }).build();
/*    */   
/* 33 */   private static final AbilityAttributeModifier SPEED_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_MOVEMENT_SPEED_UUID, INSTANCE, "Giraffe Heavy Point Speed Modifier", 0.30000001192092896D, AttributeModifier.Operation.MULTIPLY_BASE);
/* 34 */   private static final AbilityAttributeModifier JUMP_BOOST_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_JUMP_BOOST_UUID, INSTANCE, "Giraffe Heavy Point Jump Modifier", 1.0D, AttributeModifier.Operation.ADDITION);
/* 35 */   private static final AbilityAttributeModifier STRENGTH_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_STRENGTH_UUID, INSTANCE, "Giraffe Heavy Point Modifier", 1.0D, AttributeModifier.Operation.ADDITION);
/* 36 */   private static final AbilityAttributeModifier ATTACK_SPEED_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_ATTACK_SPEED_UUID, INSTANCE, "Giraffe Heavy Point Modifier", 0.15000000596046448D, AttributeModifier.Operation.ADDITION);
/* 37 */   private static final AbilityAttributeModifier REACH_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_ATTACK_REACH_UUID, INSTANCE, "Giraffe Heavy Point Reach Modifier", 2.0D, AttributeModifier.Operation.ADDITION);
/* 38 */   private static final AbilityAttributeModifier ARMOR_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_ARMOR_UUID, INSTANCE, "Giraffe Heavy Point Modifier", 10.0D, AttributeModifier.Operation.ADDITION);
/* 39 */   private static final AbilityAttributeModifier TOUGHNESS_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_TOUGHNESS_UUID, INSTANCE, "Giraffe Heavy Point Toughness Modifier", 5.0D, AttributeModifier.Operation.ADDITION);
/* 40 */   private static final AbilityAttributeModifier FALL_RESISTANCE_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_FALL_RESISTANCE_UUID, INSTANCE, "Giraffe Heavy Point Fall Resistance Modifier", 0.75D, AttributeModifier.Operation.ADDITION);
/*    */   
/*    */   public GiraffeHeavyPointAbility(AbilityCore core) {
/* 43 */     super(core);
/*    */     
/* 45 */     this.statsComponent.addAttributeModifier(Attributes.field_233821_d_, (AttributeModifier)SPEED_MODIFIER);
/* 46 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.PUNCH_DAMAGE, (AttributeModifier)STRENGTH_MODIFIER);
/* 47 */     this.statsComponent.addAttributeModifier(Attributes.field_233825_h_, (AttributeModifier)ATTACK_SPEED_MODIFIER);
/* 48 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.JUMP_HEIGHT, (AttributeModifier)JUMP_BOOST_MODIFIER);
/* 49 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.FALL_RESISTANCE, (AttributeModifier)JUMP_BOOST_MODIFIER);
/* 50 */     this.statsComponent.addAttributeModifier((Supplier)ForgeMod.REACH_DISTANCE, (AttributeModifier)REACH_MODIFIER);
/* 51 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.ATTACK_RANGE, (AttributeModifier)REACH_MODIFIER);
/* 52 */     this.statsComponent.addAttributeModifier(Attributes.field_233826_i_, (AttributeModifier)ARMOR_MODIFIER);
/* 53 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.TOUGHNESS, (AttributeModifier)TOUGHNESS_MODIFIER);
/* 54 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.FALL_RESISTANCE, (AttributeModifier)FALL_RESISTANCE_MODIFIER);
/*    */   }
/*    */ 
/*    */   
/*    */   public MorphInfo getTransformation() {
/* 59 */     return (MorphInfo)ModMorphs.GIRAFFE_HEAVY.get();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilitie\\ushigiraffe\GiraffeHeavyPointAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */