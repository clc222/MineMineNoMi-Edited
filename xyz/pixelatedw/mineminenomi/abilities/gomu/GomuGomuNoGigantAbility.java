/*    */ package xyz.pixelatedw.mineminenomi.abilities.gomu;
/*    */ 
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
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUseResult;
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
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*    */ 
/*    */ public class GomuGomuNoGigantAbility extends MorphAbility2 {
/* 31 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "gomu_gomu_no_gigant", new Pair[] {
/* 32 */         (Pair)ImmutablePair.of("Allows the user to increase their size to that of a giant.", null)
/*    */       });
/*    */   
/*    */   private static final int HOLD_TIME = 1200;
/*    */   private static final int MIN_COOLDOWN = 100;
/*    */   private static final int MAX_COOLDOWN = 1200;
/* 38 */   public static final AbilityCore<GomuGomuNoGigantAbility> INSTANCE = (new AbilityCore.Builder("Gomu Gomu no Gigant", AbilityCategory.DEVIL_FRUITS, GomuGomuNoGigantAbility::new))
/* 39 */     .addDescriptionLine(DESCRIPTION)
/* 40 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(100.0F, 1200.0F), ContinuousComponent.getTooltip(1200.0F), ChangeStatsComponent.getTooltip()
/* 41 */       }).setUnlockCheck(GomuGomuNoGigantAbility::canUnlock)
/* 42 */     .build();
/*    */   
/* 44 */   private static final AbilityAttributeModifier SPEED_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_MOVEMENT_SPEED_UUID, INSTANCE, "Gomu Gomu no Gigant Speed Modifier", 1.0199999809265137D, AttributeModifier.Operation.MULTIPLY_BASE);
/* 45 */   private static final AbilityAttributeModifier JUMP_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_JUMP_BOOST_UUID, INSTANCE, "Gomu Gomu no Gigant Jump Modifier", 2.0D, AttributeModifier.Operation.ADDITION);
/* 46 */   private static final AbilityAttributeModifier ARMOR_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_ARMOR_UUID, INSTANCE, "Gomu Gomu no Gigant Armor Modifier", 5.0D, AttributeModifier.Operation.ADDITION);
/* 47 */   private static final AbilityAttributeModifier STRENGTH_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_STRENGTH_UUID, INSTANCE, "Gomu Gomu no Gigant Strength Modifier", 3.0D, AttributeModifier.Operation.ADDITION);
/* 48 */   private static final AbilityAttributeModifier REACH_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_ATTACK_REACH_UUID, INSTANCE, "Gomu Gomu no Gigant Reach Modifier", 5.0D, AttributeModifier.Operation.ADDITION);
/* 49 */   private static final AbilityAttributeModifier STEP_HEIGHT = new AbilityAttributeModifier(AttributeHelper.MORPH_STEP_HEIGHT_UUID, INSTANCE, "Gomu Gomu no Gigant Step Height Modifier", 1.5D, AttributeModifier.Operation.ADDITION);
/* 50 */   private static final AbilityAttributeModifier KNOCKBACK_RESISTANCE = new AbilityAttributeModifier(AttributeHelper.MORPH_KNOCKBACK_RESISTANCE_UUID, INSTANCE, "Gomu Gomu no Gigant Knockback Resistance Modifier", 1.0D, AttributeModifier.Operation.ADDITION);
/* 51 */   private static final AbilityAttributeModifier FALL_RESISTANCE_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_FALL_RESISTANCE_UUID, INSTANCE, "Gomu Gomu no Gigant Fall Resistance Modifier", 10.0D, AttributeModifier.Operation.ADDITION);
/* 52 */   private static final AbilityAttributeModifier TOUGHNESS_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_TOUGHNESS_UUID, INSTANCE, "Gomu Gomu no Gigant Toughness Modifier", 2.0D, AttributeModifier.Operation.ADDITION);
/*    */   
/*    */   public GomuGomuNoGigantAbility(AbilityCore core) {
/* 55 */     super(core);
/*    */     
/* 57 */     this.statsComponent.addAttributeModifier(Attributes.field_233821_d_, (AttributeModifier)SPEED_MODIFIER);
/* 58 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.JUMP_HEIGHT, (AttributeModifier)JUMP_MODIFIER);
/* 59 */     this.statsComponent.addAttributeModifier(Attributes.field_233826_i_, (AttributeModifier)ARMOR_MODIFIER);
/* 60 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.PUNCH_DAMAGE, (AttributeModifier)STRENGTH_MODIFIER);
/* 61 */     this.statsComponent.addAttributeModifier((Supplier)ForgeMod.REACH_DISTANCE, (AttributeModifier)REACH_MODIFIER);
/* 62 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.ATTACK_RANGE, (AttributeModifier)REACH_MODIFIER);
/* 63 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.STEP_HEIGHT, (AttributeModifier)STEP_HEIGHT);
/* 64 */     this.statsComponent.addAttributeModifier(Attributes.field_233820_c_, (AttributeModifier)KNOCKBACK_RESISTANCE);
/* 65 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.FALL_RESISTANCE, (AttributeModifier)FALL_RESISTANCE_MODIFIER);
/* 66 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.TOUGHNESS, (AttributeModifier)TOUGHNESS_MODIFIER);
/*    */     
/* 68 */     addCanUseCheck(this::canUse);
/*    */     
/* 70 */     this.continuousComponent.addEndEvent(100, this::endContinuityEvent);
/*    */   }
/*    */   
/*    */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 74 */     float cooldown = Math.max(100.0F, this.continuousComponent.getContinueTime());
/* 75 */     this.cooldownComponent.startCooldown(entity, cooldown);
/*    */   }
/*    */ 
/*    */   
/*    */   public MorphInfo getTransformation() {
/* 80 */     return (MorphInfo)ModMorphs.GOMU_GIGANT.get();
/*    */   }
/*    */ 
/*    */   
/*    */   public float getContinuityHoldTime() {
/* 85 */     return 1200.0F;
/*    */   }
/*    */   
/*    */   private AbilityUseResult canUse(LivingEntity entity, IAbility ability) {
/* 89 */     IAbilityData props = AbilityDataCapability.get(entity);
/* 90 */     GearFifthAbility gearFifth = (GearFifthAbility)props.getEquippedAbility(GearFifthAbility.INSTANCE);
/* 91 */     if (gearFifth == null || !gearFifth.isContinuous()) {
/* 92 */       return AbilityUseResult.fail(null);
/*    */     }
/*    */     
/* 95 */     return AbilityUseResult.success();
/*    */   }
/*    */   
/*    */   private static boolean canUnlock(LivingEntity user) {
/* 99 */     return DevilFruitCapability.get(user).hasAwakenedFruit();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\gomu\GomuGomuNoGigantAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */