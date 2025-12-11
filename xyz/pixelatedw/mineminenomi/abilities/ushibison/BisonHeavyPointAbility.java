/*    */ package xyz.pixelatedw.mineminenomi.abilities.ushibison;
/*    */ 
/*    */ import java.util.function.Predicate;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.LivingEntity;
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
/*    */ public class BisonHeavyPointAbility
/*    */   extends MorphAbility2 {
/* 28 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "bison_heavy_point", new Pair[] {
/* 29 */         (Pair)ImmutablePair.of("Transforms the user into a half-bison hybrid, which focuses on strength.", null)
/*    */       });
/* 31 */   public static final AbilityCore<BisonHeavyPointAbility> INSTANCE = (new AbilityCore.Builder("Bison Heavy Point", AbilityCategory.DEVIL_FRUITS, BisonHeavyPointAbility::new))
/* 32 */     .addDescriptionLine(DESCRIPTION)
/* 33 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(10.0F), ContinuousComponent.getTooltip(), ChangeStatsComponent.getTooltip()
/* 34 */       }).build();
/*    */   
/* 36 */   private static final AbilityAttributeModifier SPEED_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_MOVEMENT_SPEED_UUID, INSTANCE, "Bison Heavy Point Modifier", 0.3D, AttributeModifier.Operation.MULTIPLY_BASE);
/* 37 */   private static final AbilityAttributeModifier HEALTH_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_HEALTH_UUID, INSTANCE, "Bison Heavy Point Modifier", 10.0D, AttributeModifier.Operation.ADDITION);
/* 38 */   private static final AbilityAttributeModifier ARMOR_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_ARMOR_UUID, INSTANCE, "Bison Heavy Point Modifier", 6.0D, AttributeModifier.Operation.ADDITION);
/* 39 */   private static final AbilityAttributeModifier STRENGTH_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_STRENGTH_UUID, INSTANCE, "Bison Heavy Point Modifier", 4.0D, AttributeModifier.Operation.ADDITION);
/* 40 */   private static final AbilityAttributeModifier ATTACK_SPEED_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_ATTACK_SPEED_UUID, INSTANCE, "Bison Heavy Point Modifier", -0.800000011920929D, AttributeModifier.Operation.ADDITION);
/* 41 */   private static final AbilityAttributeModifier JUMP_BOOST_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_JUMP_BOOST_UUID, INSTANCE, "Bison Heavy Point Modifier", 0.5D, AttributeModifier.Operation.ADDITION);
/* 42 */   private static final AbilityAttributeModifier REACH_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_ATTACK_REACH_UUID, INSTANCE, "Bison Heavy Point Reach Modifier", 0.5D, AttributeModifier.Operation.ADDITION);
/* 43 */   private static final AbilityAttributeModifier TOUGHNESS_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_TOUGHNESS_UUID, INSTANCE, "Bison Heavy Point Toughness Modifier", 2.0D, AttributeModifier.Operation.ADDITION);
/* 44 */   private static final AbilityAttributeModifier FALL_RESISTANCE_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_FALL_RESISTANCE_UUID, INSTANCE, "Bison Heavy Point Fall Resistance Modifier", 0.25D, AttributeModifier.Operation.ADDITION);
/*    */   
/*    */   public BisonHeavyPointAbility(AbilityCore<BisonHeavyPointAbility> core) {
/* 47 */     super(core);
/*    */     
/* 49 */     Predicate<LivingEntity> isMorphed = entity -> this.morphComponent.isMorphed();
/*    */     
/* 51 */     this.statsComponent.addAttributeModifier(Attributes.field_233821_d_, (AttributeModifier)SPEED_MODIFIER, isMorphed);
/* 52 */     this.statsComponent.addAttributeModifier(Attributes.field_233818_a_, (AttributeModifier)HEALTH_MODIFIER, isMorphed);
/* 53 */     this.statsComponent.addAttributeModifier(Attributes.field_233826_i_, (AttributeModifier)ARMOR_MODIFIER, isMorphed);
/* 54 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.PUNCH_DAMAGE, (AttributeModifier)STRENGTH_MODIFIER, isMorphed);
/* 55 */     this.statsComponent.addAttributeModifier(Attributes.field_233825_h_, (AttributeModifier)ATTACK_SPEED_MODIFIER, isMorphed);
/* 56 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.JUMP_HEIGHT, (AttributeModifier)JUMP_BOOST_MODIFIER, isMorphed);
/* 57 */     this.statsComponent.addAttributeModifier((Supplier)ForgeMod.REACH_DISTANCE, (AttributeModifier)REACH_MODIFIER, isMorphed);
/* 58 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.ATTACK_RANGE, (AttributeModifier)REACH_MODIFIER, isMorphed);
/* 59 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.TOUGHNESS, (AttributeModifier)TOUGHNESS_MODIFIER, isMorphed);
/* 60 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.FALL_RESISTANCE, (AttributeModifier)FALL_RESISTANCE_MODIFIER, isMorphed);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public MorphInfo getTransformation() {
/* 76 */     return (MorphInfo)ModMorphs.BISON_HEAVY.get();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilitie\\ushibison\BisonHeavyPointAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */