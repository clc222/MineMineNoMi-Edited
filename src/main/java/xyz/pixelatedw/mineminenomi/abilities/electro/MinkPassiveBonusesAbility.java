/*    */ package xyz.pixelatedw.mineminenomi.abilities.electro;
/*    */ import java.util.UUID;
/*    */ import java.util.function.Predicate;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.ai.attributes.Attribute;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.entity.ai.attributes.Attributes;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.biome.Biome;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityType;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.PassiveStatBonusAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChangeStatsComponent;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*    */ 
/*    */ public class MinkPassiveBonusesAbility extends PassiveStatBonusAbility {
/* 22 */   private static final AttributeModifier SPEED_MODIFIER = new AttributeModifier(UUID.fromString("d70f4c72-ba2e-4aaf-8461-8c04d5053367"), "Mink Speed Multiplier", 0.5D, AttributeModifier.Operation.MULTIPLY_BASE);
/* 23 */   private static final AttributeModifier JUMP_MODIFIER = new AttributeModifier(UUID.fromString("592e8290-5c83-4467-a3ec-0ae748d9cdc4"), "Mink Jump Boost Addition", 1.6D, AttributeModifier.Operation.ADDITION);
/* 24 */   private static final AttributeModifier JUMP_RESISTANCE_MODIFIER = new AttributeModifier(UUID.fromString("d8b7e977-414a-4ca7-b538-063440e503b0"), "Mink Jump Resistance", 2.25D, AttributeModifier.Operation.ADDITION);
/*    */   static {
/* 26 */     TEMPERATURE_CHECK = (entity -> {
/*    */         BlockPos position = entity.func_233580_cy_();
/*    */         
/*    */         float temperature = Math.round(entity.field_70170_p.func_226691_t_(position).func_225486_c(position) * 10.0F) / 10.0F;
/*    */         
/*    */         if (entity.field_70170_p.func_226691_t_(position).func_242445_k() - 0.1F > temperature) {
/*    */           temperature = entity.field_70170_p.func_226691_t_(position).func_242445_k() - 0.1F;
/*    */         }
/*    */         
/*    */         boolean temperatureCheck = (temperature < 2.0D);
/* 36 */         return (temperatureCheck && entity.field_70170_p.func_226691_t_(position).func_201856_r() != Biome.Category.OCEAN);
/*    */       });
/*    */   }
/*    */ 
/*    */   
/*    */   private static final Predicate<LivingEntity> TEMPERATURE_CHECK;
/*    */   
/* 43 */   public static final AbilityCore<MinkPassiveBonusesAbility> INSTANCE = (new AbilityCore.Builder("Mink Passive Bonuses", AbilityCategory.RACIAL, AbilityType.PASSIVE, MinkPassiveBonusesAbility::new))
/* 44 */     .setIcon(ModResources.PERK_ICON)
/* 45 */     .addDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { ChangeStatsComponent.getTooltip()
/* 46 */       }).setUnlockCheck(MinkPassiveBonusesAbility::canUnlock)
/* 47 */     .build();
/*    */   
/*    */   public MinkPassiveBonusesAbility(AbilityCore<MinkPassiveBonusesAbility> core) {
/* 50 */     super(core);
/*    */     
/* 52 */     pushStaticAttribute(Attributes.field_233821_d_, SPEED_MODIFIER);
/* 53 */     pushStaticAttribute((Attribute)ModAttributes.JUMP_HEIGHT.get(), JUMP_MODIFIER);
/* 54 */     pushStaticAttribute((Attribute)ModAttributes.FALL_RESISTANCE.get(), JUMP_RESISTANCE_MODIFIER);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Predicate<LivingEntity> getCheck() {
/* 60 */     return TEMPERATURE_CHECK;
/*    */   }
/*    */   
/*    */   private static boolean canUnlock(LivingEntity entity) {
/* 64 */     IEntityStats props = EntityStatsCapability.get(entity);
/* 65 */     return props.isMink();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\electro\MinkPassiveBonusesAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */