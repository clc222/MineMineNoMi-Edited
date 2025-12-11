/*    */ package xyz.pixelatedw.mineminenomi.abilities.kame;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.entity.ai.attributes.Attributes;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
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
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*    */ 
/*    */ public class KameGuardPointAbility extends MorphAbility2 {
/* 30 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "kame_guard_point", new Pair[] {
/* 31 */         (Pair)ImmutablePair.of("Transforms the user into a turtle, which focuses on defense.", null), 
/* 32 */         (Pair)ImmutablePair.of("Sneaking allows you to retract into your shell.", null)
/*    */       });
/* 34 */   public static final AbilityCore<KameGuardPointAbility> INSTANCE = (new AbilityCore.Builder("Kame Guard Point", AbilityCategory.DEVIL_FRUITS, KameGuardPointAbility::new))
/* 35 */     .addDescriptionLine(DESCRIPTION)
/* 36 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(10.0F), ContinuousComponent.getTooltip(), ChangeStatsComponent.getTooltip()
/* 37 */       }).build();
/*    */   
/* 39 */   private static final AbilityAttributeModifier HEALTH_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_HEALTH_UUID, INSTANCE, "Kame Guard Point Modifier", 10.0D, AttributeModifier.Operation.ADDITION);
/* 40 */   private static final AbilityAttributeModifier ARMOR_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_ARMOR_UUID, INSTANCE, "Kame Guard Point Modifier", 25.0D, AttributeModifier.Operation.ADDITION);
/* 41 */   private static final AbilityAttributeModifier ATTACK_SPEED_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_ATTACK_SPEED_UUID, INSTANCE, "Kame Guard Point Modifier", -0.15000000596046448D, AttributeModifier.Operation.ADDITION);
/* 42 */   private static final AbilityAttributeModifier KNOCKBACK_RESISTANCE_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_KNOCKBACK_RESISTANCE_UUID, INSTANCE, "Kame Guard Point Knockback Resistance Modifier", 2.0D, AttributeModifier.Operation.ADDITION);
/* 43 */   private static final AbilityAttributeModifier JUMP_BOOST_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_JUMP_BOOST_UUID, INSTANCE, "Kame Guard Point Jump Modifier", -10.0D, AttributeModifier.Operation.ADDITION);
/* 44 */   private static final AbilityAttributeModifier SPEED_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_MOVEMENT_SPEED_UUID, INSTANCE, "Kame Guard Point Modifier", -0.85D, AttributeModifier.Operation.MULTIPLY_BASE);
/* 45 */   private static final AbilityAttributeModifier SWIM_SPEED_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_SWIM_SPEED_UUID, INSTANCE, "Kame Guard Point Water Speed Modifier", 1.0D, AttributeModifier.Operation.ADDITION);
/* 46 */   private static final AbilityAttributeModifier TOUGHNESS_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_TOUGHNESS_UUID, INSTANCE, "Kame Guard Point Toughness Modifier", 10.0D, AttributeModifier.Operation.ADDITION);
/*    */   
/*    */   public KameGuardPointAbility(AbilityCore<KameGuardPointAbility> core) {
/* 49 */     super(core);
/*    */     
/* 51 */     this.statsComponent.addAttributeModifier(Attributes.field_233826_i_, (AttributeModifier)ARMOR_MODIFIER);
/* 52 */     this.statsComponent.addAttributeModifier(Attributes.field_233818_a_, (AttributeModifier)HEALTH_MODIFIER);
/* 53 */     this.statsComponent.addAttributeModifier(Attributes.field_233825_h_, (AttributeModifier)ATTACK_SPEED_MODIFIER);
/* 54 */     this.statsComponent.addAttributeModifier(Attributes.field_233820_c_, (AttributeModifier)KNOCKBACK_RESISTANCE_MODIFIER);
/* 55 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.JUMP_HEIGHT, (AttributeModifier)JUMP_BOOST_MODIFIER);
/* 56 */     this.statsComponent.addAttributeModifier(Attributes.field_233821_d_, (AttributeModifier)SPEED_MODIFIER);
/* 57 */     this.statsComponent.addAttributeModifier((Supplier)ForgeMod.SWIM_SPEED, (AttributeModifier)SWIM_SPEED_MODIFIER);
/* 58 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.TOUGHNESS, (AttributeModifier)TOUGHNESS_MODIFIER);
/*    */ 
/*    */ 
/*    */     
/* 62 */     this.continuousComponent.addTickEvent(100, this::tickContinuityEvent);
/*    */   }
/*    */   
/*    */   public void tickContinuityEvent(LivingEntity entity, IAbility ability) {
/* 66 */     if (entity.func_213453_ef()) {
/* 67 */       if (!entity.field_70170_p.field_72995_K) {
/* 68 */         entity.func_195064_c(new EffectInstance((Effect)ModEffects.GUARDING.get(), 2, 3, false, false));
/*    */       
/*    */       }
/*    */     }
/* 72 */     else if (entity.func_233570_aj_() && (entity.field_70170_p.func_180495_p(entity.func_233580_cy_().func_177977_b()).func_185904_a() == Material.field_151588_w || entity.field_70170_p
/* 73 */       .func_180495_p(entity.func_233580_cy_().func_177977_b()).func_185904_a() == Material.field_151598_x) && (
/* 74 */       Math.abs(entity.func_213322_ci().func_82615_a()) < 0.2D || Math.abs(entity.func_213322_ci().func_82616_c()) < 0.2D)) {
/* 75 */       AbilityHelper.setDeltaMovement((Entity)entity, entity.func_213322_ci().func_82615_a() * 1.12D, entity.func_213322_ci().func_82617_b(), entity.func_213322_ci().func_82616_c() * 1.12D);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public MorphInfo getTransformation() {
/* 83 */     return (MorphInfo)ModMorphs.KAME_GUARD.get();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\kame\KameGuardPointAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */