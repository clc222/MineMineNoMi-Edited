/*    */ package xyz.pixelatedw.mineminenomi.abilities.brawler;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import java.util.function.Predicate;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.ai.attributes.Attribute;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.StringTextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityStat;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityType;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.PassiveStatBonusAbility;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*    */ 
/*    */ public class BrawlerPassiveBonusesAbility extends PassiveStatBonusAbility {
/* 24 */   private static final UUID BRAWLER_ATTACK_BONUS_UUID = UUID.fromString("4e4d55e7-774b-4010-8722-a15f9da99807");
/*    */   static {
/* 26 */     BRAWLER_CHECK = (entity -> {
/*    */         ItemStack heldItem = entity.func_184614_ca();
/*    */ 
/*    */ 
/*    */         
/*    */         return !!heldItem.func_190926_b();
/*    */       });
/*    */ 
/*    */     
/* 35 */     TOOLTIP = ((entity, ability) -> {
/*    */         ITextComponent[] lines = new ITextComponent[2];
/*    */         lines[0] = (ITextComponent)new StringTextComponent("§a" + ModI18n.ABILITY_DESCRIPTION_STAT_NAME_STATS.getString() + "§r");
/*    */         TranslationTextComponent translationTextComponent = new TranslationTextComponent(((Attribute)ModAttributes.PUNCH_DAMAGE.get()).func_233754_c_());
/*    */         float value = (float)ability.getBonus(entity);
/*    */         ITextComponent statText = (new AbilityStat.Builder((ITextComponent)translationTextComponent, value)).withSign("+").build().getStatDescription(2);
/*    */         lines[1] = statText;
/*    */         StringBuilder sb = new StringBuilder();
/*    */         int lineId = 0;
/*    */         for (ITextComponent text : lines) {
/*    */           boolean hasFollowingLine = (lineId++ < lines.length - 1);
/*    */           sb.append(text.getString() + (hasFollowingLine ? "\n" : ""));
/*    */         } 
/*    */         return (ITextComponent)new StringTextComponent(sb.toString());
/*    */       });
/*    */   }
/*    */ 
/*    */   
/*    */   private static final Predicate<LivingEntity> BRAWLER_CHECK;
/*    */   
/*    */   private static final AbilityDescriptionLine.IDescriptionLine<BrawlerPassiveBonusesAbility> TOOLTIP;
/* 56 */   public static final AbilityCore<BrawlerPassiveBonusesAbility> INSTANCE = (new AbilityCore.Builder("Brawler Passive Bonuses", AbilityCategory.STYLE, AbilityType.PASSIVE, BrawlerPassiveBonusesAbility::new))
/* 57 */     .setIcon(ModResources.PERK_ICON)
/* 58 */     .addDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { TOOLTIP
/* 59 */       }).setUnlockCheck(BrawlerPassiveBonusesAbility::canUnlock)
/* 60 */     .build();
/*    */   
/*    */   public BrawlerPassiveBonusesAbility(AbilityCore<BrawlerPassiveBonusesAbility> core) {
/* 63 */     super(core);
/*    */     
/* 65 */     pushDynamicAttribute((Attribute)ModAttributes.PUNCH_DAMAGE.get(), entity -> getModifier(entity));
/*    */   }
/*    */   
/*    */   private AttributeModifier getModifier(LivingEntity entity) {
/* 69 */     double bonus = getBonus(entity);
/* 70 */     return new AttributeModifier(BRAWLER_ATTACK_BONUS_UUID, "Brawler Punch Bonus", bonus, AttributeModifier.Operation.ADDITION);
/*    */   }
/*    */   
/*    */   private double getBonus(LivingEntity entity) {
/* 74 */     return 2.0D + EntityStatsCapability.get(entity).getDoriki() * 5.0E-4D;
/*    */   }
/*    */   
/*    */   private static boolean canUnlock(LivingEntity entity) {
/* 78 */     return EntityStatsCapability.get(entity).isBrawler();
/*    */   }
/*    */ 
/*    */   
/*    */   public Predicate<LivingEntity> getCheck() {
/* 83 */     return BRAWLER_CHECK;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\brawler\BrawlerPassiveBonusesAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */