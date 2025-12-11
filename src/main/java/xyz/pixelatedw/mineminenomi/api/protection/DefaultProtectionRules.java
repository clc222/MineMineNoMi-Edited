/*    */ package xyz.pixelatedw.mineminenomi.api.protection;
/*    */ 
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.CoreBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.LiquidBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.OreBlockProtectionRule;
/*    */ 
/*    */ public class DefaultProtectionRules {
/* 10 */   public static final BlockProtectionRule AIR_FOLIAGE = (new BlockProtectionRule.Builder(new BlockProtectionRule[] { AirBlockProtectionRule.INSTANCE, FoliageBlockProtectionRule.INSTANCE })).build();
/* 11 */   public static final BlockProtectionRule CORE_FOLIAGE = (new BlockProtectionRule.Builder(new BlockProtectionRule[] { CoreBlockProtectionRule.INSTANCE, FoliageBlockProtectionRule.INSTANCE })).build();
/* 12 */   public static final BlockProtectionRule CORE_FOLIAGE_ORE = (new BlockProtectionRule.Builder(new BlockProtectionRule[] { CoreBlockProtectionRule.INSTANCE, FoliageBlockProtectionRule.INSTANCE, OreBlockProtectionRule.INSTANCE })).build();
/* 13 */   public static final BlockProtectionRule AIR_CORE_FOLIAGE_ORE = (new BlockProtectionRule.Builder(new BlockProtectionRule[] { AirBlockProtectionRule.INSTANCE, CoreBlockProtectionRule.INSTANCE, FoliageBlockProtectionRule.INSTANCE, OreBlockProtectionRule.INSTANCE })).build();
/* 14 */   public static final BlockProtectionRule CORE_FOLIAGE_ORE_LIQUID = (new BlockProtectionRule.Builder(new BlockProtectionRule[] { CoreBlockProtectionRule.INSTANCE, FoliageBlockProtectionRule.INSTANCE, OreBlockProtectionRule.INSTANCE, LiquidBlockProtectionRule.INSTANCE })).build();
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\protection\DefaultProtectionRules.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */