/*     */ package xyz.pixelatedw.mineminenomi.api.helpers;
/*     */ 
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ 
/*     */ public class FightingStyleHelper {
/*  12 */   private static final UUID BRAWLER_ATTACK_BONUS_UUID = UUID.fromString("4e4d55e7-774b-4010-8722-a15f9da99807");
/*  13 */   private static final UUID BLACK_LEG_ATTACK_BONUS_UUID = UUID.fromString("be5937cc-c1da-4891-9583-cebbab2134d3");
/*  14 */   public static final UUID SWORDSMAN_ATTACK_BONUS_ID = UUID.fromString("4b706e44-2567-4019-9b9c-747fa53bb05d");
/*     */   
/*  16 */   private static final AttributeModifier BLACK_LEG_ATTACK_SPEED_MODIFIER = new AttributeModifier(UUID.fromString("aa257b39-effd-43b3-9034-615b21e49038"), "Black Leg Speed Multiplier", 0.30000001192092896D, AttributeModifier.Operation.ADDITION);
/*  17 */   private static final AttributeModifier BLACK_LEG_ATTACK_RANGE_MODIFIER = new AttributeModifier(UUID.fromString("cd7d0526-005b-4ef2-a61f-0e941b0d6e1a"), "Black Leg Range Multiplier", 0.5D, AttributeModifier.Operation.ADDITION);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static boolean hasBlackLegModifiers(LivingEntity entity) {
/*  29 */     boolean flag1 = (entity.func_110148_a((Attribute)ModAttributes.PUNCH_DAMAGE.get()).func_111127_a(BLACK_LEG_ATTACK_BONUS_UUID) != null);
/*  30 */     boolean flag2 = entity.func_110148_a(Attributes.field_233825_h_).func_180374_a(BLACK_LEG_ATTACK_SPEED_MODIFIER);
/*  31 */     boolean flag3 = entity.func_110148_a((Attribute)ModAttributes.ATTACK_RANGE.get()).func_180374_a(BLACK_LEG_ATTACK_RANGE_MODIFIER);
/*     */     
/*  33 */     return (flag1 || flag2 || flag3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static void removeBlackLegModifiers(LivingEntity entity) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static void applyBlackLegModifiers(LivingEntity entity) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static boolean hasBrawlerModifiers(LivingEntity entity) {
/*  71 */     return (entity.func_110148_a((Attribute)ModAttributes.PUNCH_DAMAGE.get()).func_111127_a(BRAWLER_ATTACK_BONUS_UUID) != null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static void removeBrawlerModifiers(LivingEntity entity) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static void applyBrawlerModifiers(LivingEntity entity) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static double getBrawlerBonus(LivingEntity entity) {
/*  98 */     return 2.0D + EntityStatsCapability.get(entity).getDoriki() * 5.0E-4D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static double getBlackLegBonus(LivingEntity entity) {
/* 106 */     return 2.0D + EntityStatsCapability.get(entity).getDoriki() * 4.0E-4D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static double getAppliedBrawlerBonus(LivingEntity entity) {
/* 114 */     AttributeModifier mod = entity.func_110148_a((Attribute)ModAttributes.PUNCH_DAMAGE.get()).func_111127_a(BRAWLER_ATTACK_BONUS_UUID);
/* 115 */     if (mod == null) {
/* 116 */       return 0.0D;
/*     */     }
/* 118 */     return mod.func_111164_d();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static double getAppliedBlackLegBonus(LivingEntity entity) {
/* 126 */     AttributeModifier mod = entity.func_110148_a((Attribute)ModAttributes.PUNCH_DAMAGE.get()).func_111127_a(BLACK_LEG_ATTACK_BONUS_UUID);
/* 127 */     if (mod == null) {
/* 128 */       return 0.0D;
/*     */     }
/* 130 */     return mod.func_111164_d();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   private static AttributeModifier getBrawlerAttackModifier(LivingEntity entity) {
/* 139 */     return new AttributeModifier(BRAWLER_ATTACK_BONUS_UUID, "Brawler Attack Multiplier", getBrawlerBonus(entity), AttributeModifier.Operation.ADDITION);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   private static AttributeModifier getBlackLegAttackModifier(LivingEntity entity) {
/* 148 */     return new AttributeModifier(BLACK_LEG_ATTACK_BONUS_UUID, "Black Leg Attack Multiplier", getBlackLegBonus(entity), AttributeModifier.Operation.ADDITION);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\helpers\FightingStyleHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */