/*    */ package xyz.pixelatedw.mineminenomi.abilities.gura;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.EffectImmunityAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityType;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ 
/*    */ public class GuraImmunityAbility extends EffectImmunityAbility {
/* 11 */   public static final AbilityCore<GuraImmunityAbility> INSTANCE = (new AbilityCore.Builder("Gura Immunities", AbilityCategory.DEVIL_FRUITS, AbilityType.PASSIVE, GuraImmunityAbility::new)).setHidden().build();
/*    */   
/* 13 */   private static final EffectImmunityAbility.ImmunityInfo IMMUNITY_INFO = (new EffectImmunityAbility.ImmunityInfo()).addResistanceEffect((Supplier)ModEffects.CANDLE_LOCK, 5).addResistanceEffect((Supplier)ModEffects.CANDY_STUCK, 8).addResistanceEffect((Supplier)ModEffects.FROZEN, 8);
/*    */   
/*    */   public GuraImmunityAbility(AbilityCore core) {
/* 16 */     super(core, IMMUNITY_INFO);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\gura\GuraImmunityAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */