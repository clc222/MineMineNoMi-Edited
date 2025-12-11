/*     */ package xyz.pixelatedw.mineminenomi.abilities.blackleg;
/*     */ 
/*     */ import java.util.UUID;
/*     */ import java.util.function.Predicate;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityStat;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityType;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.PassiveStatBonusAbility;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*     */ 
/*     */ public class BlackLegPassiveBonusesAbility extends PassiveStatBonusAbility {
/*  25 */   private static final UUID BLACK_LEG_ATTACK_BONUS_UUID = UUID.fromString("be5937cc-c1da-4891-9583-cebbab2134d3");
/*     */   
/*  27 */   private static final AttributeModifier BLACK_LEG_ATTACK_SPEED_MODIFIER = new AttributeModifier(UUID.fromString("aa257b39-effd-43b3-9034-615b21e49038"), "Black Leg Speed Multiplier", 0.30000001192092896D, AttributeModifier.Operation.ADDITION);
/*  28 */   private static final AttributeModifier BLACK_LEG_ATTACK_RANGE_MODIFIER = new AttributeModifier(UUID.fromString("cd7d0526-005b-4ef2-a61f-0e941b0d6e1a"), "Black Leg Range Multiplier", 0.5D, AttributeModifier.Operation.ADDITION);
/*     */   static {
/*  30 */     BLACK_LEG_CHECK = (entity -> {
/*     */         ItemStack heldItem = entity.func_184614_ca();
/*     */ 
/*     */ 
/*     */         
/*     */         return !!heldItem.func_190926_b();
/*     */       });
/*     */ 
/*     */     
/*  39 */     TOOLTIP = ((entity, ability) -> {
/*     */         ITextComponent[] lines = new ITextComponent[4];
/*     */         lines[0] = (ITextComponent)new StringTextComponent("§a" + ModI18n.ABILITY_DESCRIPTION_STAT_NAME_STATS.getString() + "§r");
/*     */         TranslationTextComponent translationTextComponent = new TranslationTextComponent(((Attribute)ModAttributes.PUNCH_DAMAGE.get()).func_233754_c_());
/*     */         float value = (float)ability.getBonus(entity);
/*     */         ITextComponent punchStatText = (new AbilityStat.Builder((ITextComponent)translationTextComponent, value)).withSign("+").build().getStatDescription(2);
/*     */         lines[1] = punchStatText;
/*     */         translationTextComponent = new TranslationTextComponent(Attributes.field_233825_h_.func_233754_c_());
/*     */         value = (float)BLACK_LEG_ATTACK_SPEED_MODIFIER.func_111164_d();
/*     */         ITextComponent speedStatText = (new AbilityStat.Builder((ITextComponent)translationTextComponent, value)).withSign("+").build().getStatDescription(2);
/*     */         lines[2] = speedStatText;
/*     */         translationTextComponent = new TranslationTextComponent(((Attribute)ModAttributes.ATTACK_RANGE.get()).func_233754_c_());
/*     */         value = (float)BLACK_LEG_ATTACK_RANGE_MODIFIER.func_111164_d();
/*     */         ITextComponent rangeStatText = (new AbilityStat.Builder((ITextComponent)translationTextComponent, value)).withSign("+").build().getStatDescription(2);
/*     */         lines[3] = rangeStatText;
/*     */         StringBuilder sb = new StringBuilder();
/*     */         int lineId = 0;
/*     */         for (ITextComponent text : lines) {
/*     */           boolean hasFollowingLine = (lineId++ < lines.length - 1);
/*     */           sb.append(text.getString() + (hasFollowingLine ? "\n" : ""));
/*     */         } 
/*     */         return (ITextComponent)new StringTextComponent(sb.toString());
/*     */       });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static final Predicate<LivingEntity> BLACK_LEG_CHECK;
/*     */ 
/*     */   
/*     */   private static final AbilityDescriptionLine.IDescriptionLine<BlackLegPassiveBonusesAbility> TOOLTIP;
/*     */ 
/*     */   
/*  72 */   public static final AbilityCore<BlackLegPassiveBonusesAbility> INSTANCE = (new AbilityCore.Builder("Black Leg Passive Bonuses", AbilityCategory.STYLE, AbilityType.PASSIVE, BlackLegPassiveBonusesAbility::new))
/*  73 */     .setIcon(ModResources.PERK_ICON)
/*  74 */     .addDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { TOOLTIP
/*  75 */       }).setUnlockCheck(BlackLegPassiveBonusesAbility::canUnlock)
/*  76 */     .build();
/*     */   
/*     */   public BlackLegPassiveBonusesAbility(AbilityCore<BlackLegPassiveBonusesAbility> core) {
/*  79 */     super(core);
/*     */     
/*  81 */     pushDynamicAttribute((Attribute)ModAttributes.PUNCH_DAMAGE.get(), entity -> getModifier(entity));
/*  82 */     pushStaticAttribute(Attributes.field_233825_h_, BLACK_LEG_ATTACK_SPEED_MODIFIER);
/*  83 */     pushStaticAttribute((Attribute)ModAttributes.ATTACK_RANGE.get(), BLACK_LEG_ATTACK_RANGE_MODIFIER);
/*     */   }
/*     */   
/*     */   private AttributeModifier getModifier(LivingEntity entity) {
/*  87 */     double bonus = getBonus(entity);
/*  88 */     return new AttributeModifier(BLACK_LEG_ATTACK_BONUS_UUID, "Black Leg Attack Bonus", bonus, AttributeModifier.Operation.ADDITION);
/*     */   }
/*     */   
/*     */   private double getBonus(LivingEntity entity) {
/*  92 */     return 2.0D + EntityStatsCapability.get(entity).getDoriki() * 4.0E-4D;
/*     */   }
/*     */   
/*     */   private static boolean canUnlock(LivingEntity entity) {
/*  96 */     return EntityStatsCapability.get(entity).isBlackLeg();
/*     */   }
/*     */ 
/*     */   
/*     */   public Predicate<LivingEntity> getCheck() {
/* 101 */     return BLACK_LEG_CHECK;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\blackleg\BlackLegPassiveBonusesAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */