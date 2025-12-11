/*    */ package xyz.pixelatedw.mineminenomi.abilities;
/*    */ 
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityType;
/*    */ 
/*    */ public class LogiaHeatImmunityAbility
/*    */   extends EffectImmunityAbility {
/*  9 */   private static final EffectImmunityAbility.ImmunityInfo IMMUNITY_INFO = HEAT_RESISTANCES.clone().addLogiaImmunities();
/* 10 */   public static final AbilityCore<LogiaHeatImmunityAbility> INSTANCE = (new AbilityCore.Builder("Heat Logia Immunities", AbilityCategory.DEVIL_FRUITS, AbilityType.PASSIVE, LogiaHeatImmunityAbility::new))
/* 11 */     .setHidden()
/* 12 */     .build();
/*    */   
/*    */   public LogiaHeatImmunityAbility(AbilityCore<EffectImmunityAbility> core) {
/* 15 */     super(core, IMMUNITY_INFO);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\LogiaHeatImmunityAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */