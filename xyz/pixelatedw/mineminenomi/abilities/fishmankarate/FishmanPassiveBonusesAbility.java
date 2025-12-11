/*    */ package xyz.pixelatedw.mineminenomi.abilities.fishmankarate;
/*    */ import java.util.UUID;
/*    */ import java.util.function.Predicate;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.ai.attributes.Attribute;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.entity.ai.attributes.Attributes;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.tags.FluidTags;
/*    */ import net.minecraft.tags.ITag;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityType;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.PassiveStatBonusAbility;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*    */ 
/*    */ public class FishmanPassiveBonusesAbility extends PassiveStatBonusAbility {
/* 26 */   private static final AttributeModifier SWIM_SPEED_MODIFIER = new AttributeModifier(UUID.fromString("d9a209e7-15c0-490e-a9b6-4470b0bf6d28"), "Fishman Swim Speed Multiplier", 3.0D, AttributeModifier.Operation.MULTIPLY_BASE);
/* 27 */   private static final AttributeModifier DAMAGE_MODIFIER = new AttributeModifier(UUID.fromString("8ac8d8e3-1186-4847-9602-d446f0e63eab"), "Fishman Damage Bonus", 3.0D, AttributeModifier.Operation.ADDITION);
/* 28 */   private static final AttributeModifier TOUGHNESS_MODIFIER = new AttributeModifier(UUID.fromString("4b316acb-dc0a-40e5-a514-909455613b7f"), "Fishman Toughness Bonus", 2.0D, AttributeModifier.Operation.ADDITION); private static final Predicate<LivingEntity> FISHMAN_CHECK;
/*    */   static {
/* 30 */     FISHMAN_CHECK = (entity -> {
/*    */         IDevilFruit fruitData = DevilFruitCapability.get(entity);
/*    */         
/* 33 */         return !(entity.func_70090_H() && fruitData.hasAnyDevilFruit() && !entity.func_70644_a((Effect)ModEffects.BUBBLY_CORAL.get()));
/*    */       });
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 40 */   public static final AbilityCore<FishmanPassiveBonusesAbility> INSTANCE = (new AbilityCore.Builder("Fishman Passive Bonuses", AbilityCategory.RACIAL, AbilityType.PASSIVE, FishmanPassiveBonusesAbility::new))
/* 41 */     .setIcon(ModResources.PERK_ICON)
/* 42 */     .addDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { ChangeStatsComponent.getTooltip()
/* 43 */       }).setUnlockCheck(FishmanPassiveBonusesAbility::canUnlock)
/* 44 */     .build();
/*    */   
/*    */   public FishmanPassiveBonusesAbility(AbilityCore<FishmanPassiveBonusesAbility> core) {
/* 47 */     super(core);
/*    */     
/* 49 */     pushStaticAttribute((Attribute)ForgeMod.SWIM_SPEED.get(), SWIM_SPEED_MODIFIER);
/* 50 */     pushStaticAttribute(Attributes.field_233823_f_, DAMAGE_MODIFIER);
/* 51 */     pushStaticAttribute((Attribute)ModAttributes.TOUGHNESS.get(), TOUGHNESS_MODIFIER);
/*    */     
/* 53 */     addDuringPassiveEvent(this::duringPassiveEvent);
/*    */   }
/*    */   
/*    */   private void duringPassiveEvent(LivingEntity entity) {
/* 57 */     if (entity.func_208600_a((ITag)FluidTags.field_206959_a)) {
/* 58 */       entity.func_70050_g(300);
/* 59 */       entity.func_195064_c(new EffectInstance(Effects.field_76439_r, 250, 0, false, false));
/* 60 */       entity.func_195064_c(new EffectInstance(Effects.field_76422_e, 100, 0, false, false));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public Predicate<LivingEntity> getCheck() {
/* 66 */     return FISHMAN_CHECK;
/*    */   }
/*    */   
/*    */   private static boolean canUnlock(LivingEntity entity) {
/* 70 */     IEntityStats props = EntityStatsCapability.get(entity);
/* 71 */     return props.isFishman();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\fishmankarate\FishmanPassiveBonusesAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */