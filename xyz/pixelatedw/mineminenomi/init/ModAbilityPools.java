/*    */ package xyz.pixelatedw.mineminenomi.init;
/*    */ 
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool2;
/*    */ 
/*    */ public class ModAbilityPools
/*    */ {
/*    */   public static final String IGNORE_COOLDOWN_FLAG = "ignoreCooldown";
/*    */   public static final String SHARE_STACKS_FLAG = "shareStacks";
/*  9 */   public static final AbilityPool2 DODGE_ABILITY = (new AbilityPool2()).addFlag("ignoreCooldown", true);
/* 10 */   public static final AbilityPool2 GRAB_ABILITY = (new AbilityPool2()).addFlag("ignoreCooldown", true);
/* 11 */   public static final AbilityPool2 GEPPO_LIKE = (new AbilityPool2()).addFlag("shareStacks", true);
/* 12 */   public static final AbilityPool2 TEKKAI_LIKE = new AbilityPool2();
/* 13 */   public static final AbilityPool2 WEATHER_BALLS = new AbilityPool2();
/* 14 */   public static final AbilityPool2 BODY_BUSOSHOKU_HAKI = new AbilityPool2();
/* 15 */   public static final AbilityPool2 IMBUING_BUSOSHOKU_HAKI = new AbilityPool2();
/* 16 */   public static final AbilityPool2 ADVANCED_BUSOSHOKU_HAKI = new AbilityPool2();
/* 17 */   public static final AbilityPool2 SIMPLE_HAOSHOKU_HAKI = new AbilityPool2();
/* 18 */   public static final AbilityPool2 ADVANCED_HAOSHOKU_HAKI = new AbilityPool2();
/* 19 */   public static final AbilityPool2 SNIPER_ABILITY = new AbilityPool2();
/* 20 */   public static final AbilityPool2 BARA_ABILITY = (new AbilityPool2()).addFlag("ignoreCooldown", true);
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\init\ModAbilityPools.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */