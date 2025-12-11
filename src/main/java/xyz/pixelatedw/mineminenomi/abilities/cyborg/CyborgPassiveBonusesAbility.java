/*    */ package xyz.pixelatedw.mineminenomi.abilities.cyborg;
/*    */ import java.util.UUID;
/*    */ import java.util.function.Predicate;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.ai.attributes.Attribute;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.entity.ai.attributes.Attributes;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityType;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.PassiveStatBonusAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChangeStatsComponent;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*    */ 
/*    */ public class CyborgPassiveBonusesAbility extends PassiveStatBonusAbility {
/* 19 */   private static final AttributeModifier CYBORG_ARMOR = new AttributeModifier(UUID.fromString("01344b52-e35e-44a3-9895-6fba1c10fc20"), "Cyborg Armor Bonus", 10.0D, AttributeModifier.Operation.ADDITION);
/* 20 */   private static final AttributeModifier CYBORG_ARMOR_TOUGHNESS = new AttributeModifier(UUID.fromString("f2443845-6f63-4916-b57e-a6805cfa47ae"), "Cyborg Armor Toughness Bonus", 4.0D, AttributeModifier.Operation.ADDITION); private static final Predicate<LivingEntity> CYBORG_CHECK;
/* 21 */   private static final AttributeModifier CYBORG_DAMAGE = new AttributeModifier(UUID.fromString("81e152e3-46e7-4b03-bf0d-f5d8a7a870df"), "Cyborg Damage Bonus", 2.0D, AttributeModifier.Operation.ADDITION);
/*    */   static {
/* 23 */     CYBORG_CHECK = (entity -> EntityStatsCapability.get(entity).isCyborg());
/*    */   }
/* 25 */   public static final AbilityCore<CyborgPassiveBonusesAbility> INSTANCE = (new AbilityCore.Builder("Cyborg Passive Bonuses", AbilityCategory.RACIAL, AbilityType.PASSIVE, CyborgPassiveBonusesAbility::new))
/* 26 */     .setIcon(ModResources.PERK_ICON)
/* 27 */     .addDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { ChangeStatsComponent.getTooltip()
/* 28 */       }).setUnlockCheck(CyborgPassiveBonusesAbility::canUnlock)
/* 29 */     .build();
/*    */   
/*    */   public CyborgPassiveBonusesAbility(AbilityCore<CyborgPassiveBonusesAbility> core) {
/* 32 */     super(core);
/*    */     
/* 34 */     pushStaticAttribute(Attributes.field_233826_i_, CYBORG_ARMOR);
/* 35 */     pushStaticAttribute(Attributes.field_233827_j_, CYBORG_ARMOR_TOUGHNESS);
/* 36 */     pushStaticAttribute((Attribute)ModAttributes.PUNCH_DAMAGE.get(), CYBORG_DAMAGE);
/*    */   }
/*    */ 
/*    */   
/*    */   public Predicate<LivingEntity> getCheck() {
/* 41 */     return CYBORG_CHECK;
/*    */   }
/*    */   
/*    */   private static boolean canUnlock(LivingEntity entity) {
/* 45 */     return EntityStatsCapability.get(entity).isCyborg();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\cyborg\CyborgPassiveBonusesAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */