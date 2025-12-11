/*    */ package xyz.pixelatedw.mineminenomi.abilities;
/*    */ 
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityType;
/*    */ 
/*    */ public class LogiaImmunityAbility
/*    */   extends EffectImmunityAbility {
/*  9 */   public static final AbilityCore<LogiaImmunityAbility> INSTANCE = (new AbilityCore.Builder("Logia Immunities", AbilityCategory.DEVIL_FRUITS, AbilityType.PASSIVE, LogiaImmunityAbility::new)).setHidden().build();
/*    */   
/* 11 */   private static final EffectImmunityAbility.ImmunityInfo IMMUNITY_INFO = (new EffectImmunityAbility.ImmunityInfo()).addLogiaImmunities();
/*    */   
/*    */   public LogiaImmunityAbility(AbilityCore<EffectImmunityAbility> core) {
/* 14 */     super(core, IMMUNITY_INFO);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\LogiaImmunityAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */