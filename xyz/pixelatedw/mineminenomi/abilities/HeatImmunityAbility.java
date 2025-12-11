/*    */ package xyz.pixelatedw.mineminenomi.abilities;
/*    */ 
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityType;
/*    */ 
/*    */ public class HeatImmunityAbility
/*    */   extends EffectImmunityAbility {
/*  9 */   public static final AbilityCore<HeatImmunityAbility> INSTANCE = (new AbilityCore.Builder("Heat Immunities", AbilityCategory.DEVIL_FRUITS, AbilityType.PASSIVE, HeatImmunityAbility::new)).setHidden().build();
/*    */   
/*    */   public HeatImmunityAbility(AbilityCore<EffectImmunityAbility> core) {
/* 12 */     super(core, EffectImmunityAbility.HEAT_RESISTANCES);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\HeatImmunityAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */