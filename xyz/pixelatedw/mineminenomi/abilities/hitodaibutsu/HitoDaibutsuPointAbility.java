/*    */ package xyz.pixelatedw.mineminenomi.abilities.hitodaibutsu;
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
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.MorphAbility2;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChangeStatsComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.SkinOverlayComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AttributeHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*    */ 
/*    */ public class HitoDaibutsuPointAbility extends MorphAbility2 {
/* 30 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "daibutsu_point", new Pair[] {
/* 31 */         (Pair)ImmutablePair.of("Allows the user to transforms into a Golden Buddha statue, which focuses on strength and defense.", null)
/*    */       });
/* 33 */   public static final AbilityCore<HitoDaibutsuPointAbility> INSTANCE = (new AbilityCore.Builder("Daibutsu Point", AbilityCategory.DEVIL_FRUITS, HitoDaibutsuPointAbility::new))
/* 34 */     .addDescriptionLine(DESCRIPTION)
/* 35 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(10.0F), ContinuousComponent.getTooltip(), ChangeStatsComponent.getTooltip()
/* 36 */       }).build();
/*    */   
/* 38 */   private static final AbilityAttributeModifier SPEED_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_MOVEMENT_SPEED_UUID, INSTANCE, "Daibutsu Point Speed Modifier", 0.5D, AttributeModifier.Operation.MULTIPLY_BASE);
/* 39 */   private static final AbilityAttributeModifier JUMP_BOOST_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_JUMP_BOOST_UUID, INSTANCE, "Daibutsu Point Jump Modifier", 5.0D, AttributeModifier.Operation.MULTIPLY_BASE);
/* 40 */   private static final AbilityAttributeModifier STRENGTH_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_STRENGTH_UUID, INSTANCE, "Daibutsu Point Strength Modifier", 15.0D, AttributeModifier.Operation.ADDITION);
/* 41 */   private static final AbilityAttributeModifier ATTACK_SPEED_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_ATTACK_SPEED_UUID, INSTANCE, "Daibutsu Point Attack Speed Modifier", -0.5D, AttributeModifier.Operation.MULTIPLY_BASE);
/* 42 */   private static final AbilityAttributeModifier ARMOR_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_ARMOR_UUID, INSTANCE, "Daibutsu Point Armor Modifier", 16.0D, AttributeModifier.Operation.ADDITION);
/* 43 */   private static final AbilityAttributeModifier ARMOR_THOUGNESS_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_ARMOR_TOUGHNESS_UUID, INSTANCE, "Daibutsu Point Armor Thougness Modifier", 10.0D, AttributeModifier.Operation.ADDITION);
/* 44 */   private static final AbilityAttributeModifier KNOCKBACK_RESISTANCE_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_KNOCKBACK_RESISTANCE_UUID, INSTANCE, "Daibutsu Point Knockback Resistance Modifier", 5.0D, AttributeModifier.Operation.ADDITION);
/* 45 */   private static final AbilityAttributeModifier REACH_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_ATTACK_REACH_UUID, INSTANCE, "Daibutsu Point Reach Modifier", 12.0D, AttributeModifier.Operation.ADDITION);
/* 46 */   private static final AbilityAttributeModifier FALL_RESISTENCE_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_FALL_RESISTANCE_UUID, INSTANCE, "Daibutsu Point Jump Resitance Modifier", 30.0D, AttributeModifier.Operation.ADDITION);
/* 47 */   private static final AbilityAttributeModifier HEALTH_BOOST_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_HEALTH_UUID, INSTANCE, "Daibutsu Point Health Modifier", 20.0D, AttributeModifier.Operation.ADDITION);
/* 48 */   private static final AbilityAttributeModifier STEP_HEIGHT_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_STEP_HEIGHT_UUID, INSTANCE, "Daibutsu Point Modifier", 1.5D, AttributeModifier.Operation.ADDITION);
/* 49 */   private static final AbilityAttributeModifier FALL_RESISTANCE_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_FALL_RESISTANCE_UUID, INSTANCE, "Daibutsu Point Fall Resistance Modifier", 7.0D, AttributeModifier.Operation.ADDITION);
/* 50 */   private static final AbilityAttributeModifier TOUGHNESS_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_TOUGHNESS_UUID, INSTANCE, "Daibutsu Point Toughness Modifier", 6.0D, AttributeModifier.Operation.ADDITION);
/*    */   
/* 52 */   private static final AbilityOverlay OVERLAY = (new AbilityOverlay.Builder()).setTexture(ModResources.BUDDHA_COATING).setColor(WyHelper.hexToRGB("#FFFFFFAA")).build();
/*    */   
/* 54 */   private final SkinOverlayComponent skinOverlayComponent = new SkinOverlayComponent((IAbility)this, OVERLAY, new AbilityOverlay[0]);
/*    */   
/*    */   public HitoDaibutsuPointAbility(AbilityCore<HitoDaibutsuPointAbility> core) {
/* 57 */     super(core);
/*    */     
/* 59 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.skinOverlayComponent });
/*    */     
/* 61 */     this.statsComponent.addAttributeModifier(Attributes.field_233821_d_, (AttributeModifier)SPEED_MODIFIER);
/* 62 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.PUNCH_DAMAGE, (AttributeModifier)STRENGTH_MODIFIER);
/* 63 */     this.statsComponent.addAttributeModifier(Attributes.field_233825_h_, (AttributeModifier)ATTACK_SPEED_MODIFIER);
/* 64 */     this.statsComponent.addAttributeModifier(Attributes.field_233826_i_, (AttributeModifier)ARMOR_MODIFIER);
/* 65 */     this.statsComponent.addAttributeModifier(Attributes.field_233827_j_, (AttributeModifier)ARMOR_THOUGNESS_MODIFIER);
/* 66 */     this.statsComponent.addAttributeModifier(Attributes.field_233820_c_, (AttributeModifier)KNOCKBACK_RESISTANCE_MODIFIER);
/* 67 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.JUMP_HEIGHT, (AttributeModifier)JUMP_BOOST_MODIFIER);
/* 68 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.FALL_RESISTANCE, (AttributeModifier)FALL_RESISTENCE_MODIFIER);
/* 69 */     this.statsComponent.addAttributeModifier((Supplier)ForgeMod.REACH_DISTANCE, (AttributeModifier)REACH_MODIFIER);
/* 70 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.ATTACK_RANGE, (AttributeModifier)REACH_MODIFIER);
/* 71 */     this.statsComponent.addAttributeModifier(Attributes.field_233818_a_, (AttributeModifier)HEALTH_BOOST_MODIFIER);
/* 72 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.STEP_HEIGHT, (AttributeModifier)STEP_HEIGHT_MODIFIER);
/* 73 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.FALL_RESISTANCE, (AttributeModifier)FALL_RESISTANCE_MODIFIER);
/* 74 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.TOUGHNESS, (AttributeModifier)TOUGHNESS_MODIFIER);
/*    */     
/* 76 */     this.continuousComponent.addStartEvent(100, this::startContinuityEvent);
/* 77 */     this.continuousComponent.addEndEvent(100, this::endContinuityEvent);
/*    */   }
/*    */   
/*    */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/* 81 */     this.skinOverlayComponent.showAll(entity);
/*    */   }
/*    */   
/*    */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 85 */     this.skinOverlayComponent.hideAll(entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public MorphInfo getTransformation() {
/* 90 */     return (MorphInfo)ModMorphs.DAIBUTSU.get();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\hitodaibutsu\HitoDaibutsuPointAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */