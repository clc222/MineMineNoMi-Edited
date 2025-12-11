/*    */ package xyz.pixelatedw.mineminenomi.abilities.mogu;
/*    */ 
/*    */ import java.util.function.Predicate;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.entity.ai.attributes.Attributes;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraftforge.common.ForgeMod;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
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
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*    */ 
/*    */ public class MoguHeavyPointAbility
/*    */   extends MorphAbility2 {
/* 32 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "mogu_heavy_point", new Pair[] {
/* 33 */         (Pair)ImmutablePair.of("Transforms the user into a mole, which focuses on strength and digging speed", null)
/*    */       });
/* 35 */   public static final AbilityCore<MoguHeavyPointAbility> INSTANCE = (new AbilityCore.Builder("Mogu Heavy Point", AbilityCategory.DEVIL_FRUITS, MoguHeavyPointAbility::new))
/* 36 */     .addDescriptionLine(DESCRIPTION)
/* 37 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(10.0F), ContinuousComponent.getTooltip(), ChangeStatsComponent.getTooltip()
/* 38 */       }).build();
/*    */   
/* 40 */   private static final AbilityAttributeModifier ARMOR_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_ARMOR_UUID, INSTANCE, "Mogu Heavy Point Modifier", 5.0D, AttributeModifier.Operation.ADDITION);
/* 41 */   private static final AbilityAttributeModifier STRENGTH_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_STRENGTH_UUID, INSTANCE, "Mogu Heavy Point Modifier", 3.0D, AttributeModifier.Operation.ADDITION);
/* 42 */   private static final AbilityAttributeModifier ATTACK_SPEED_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_ATTACK_SPEED_UUID, INSTANCE, "Mogu Heavy Point Modifier", 0.15000000596046448D, AttributeModifier.Operation.ADDITION);
/* 43 */   private static final AbilityAttributeModifier REACH_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_ATTACK_REACH_UUID, INSTANCE, "Mogu Heavy Reach Modifier", -0.5D, AttributeModifier.Operation.ADDITION);
/* 44 */   private static final AbilityAttributeModifier TOUGHNESS_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_TOUGHNESS_UUID, INSTANCE, "Mogu Heavy Point Toughness Modifier", 1.0D, AttributeModifier.Operation.ADDITION);
/* 45 */   private static final AbilityAttributeModifier MINING_SPEED_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_MINING_SPEED_UUID, INSTANCE, "Mogu Heavy Point Mining Speed Modifier", 5.0D, AttributeModifier.Operation.ADDITION);
/*    */   
/*    */   public MoguHeavyPointAbility(AbilityCore<MoguHeavyPointAbility> core) {
/* 48 */     super(core);
/*    */     
/* 50 */     Predicate<LivingEntity> isContinuityActive = entity -> this.continuousComponent.isContinuous();
/* 51 */     this.statsComponent.addAttributeModifier(Attributes.field_233826_i_, (AttributeModifier)ARMOR_MODIFIER, isContinuityActive);
/* 52 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.PUNCH_DAMAGE, (AttributeModifier)STRENGTH_MODIFIER, isContinuityActive);
/* 53 */     this.statsComponent.addAttributeModifier(Attributes.field_233825_h_, (AttributeModifier)ATTACK_SPEED_MODIFIER, isContinuityActive);
/* 54 */     this.statsComponent.addAttributeModifier((Supplier)ForgeMod.REACH_DISTANCE, (AttributeModifier)REACH_MODIFIER, isContinuityActive);
/* 55 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.ATTACK_RANGE, (AttributeModifier)REACH_MODIFIER, isContinuityActive);
/* 56 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.TOUGHNESS, (AttributeModifier)TOUGHNESS_MODIFIER, isContinuityActive);
/* 57 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.MINING_SPEED, (AttributeModifier)MINING_SPEED_MODIFIER, isContinuityActive);
/*    */     
/* 59 */     this.continuousComponent.addEndEvent(100, this::onEndContinuityEvent);
/*    */   }
/*    */   
/*    */   public void duringContinuityEvent(PlayerEntity player, int time) {
/* 63 */     player.func_195064_c(new EffectInstance(Effects.field_76422_e, 5, 2, false, false));
/* 64 */     if (!player.func_70644_a(Effects.field_76439_r) || player.func_70660_b(Effects.field_76439_r).func_76459_b() < 500) {
/* 65 */       player.func_195064_c(new EffectInstance(Effects.field_76439_r, 500, 0, false, false));
/*    */     }
/*    */   }
/*    */   
/*    */   protected void onEndContinuityEvent(LivingEntity entity, IAbility ability) {
/* 70 */     entity.func_195063_d(Effects.field_76424_c);
/* 71 */     entity.func_195063_d(Effects.field_76439_r);
/*    */   }
/*    */ 
/*    */   
/*    */   public MorphInfo getTransformation() {
/* 76 */     return (MorphInfo)ModMorphs.MOGU_HEAVY.get();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\mogu\MoguHeavyPointAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */