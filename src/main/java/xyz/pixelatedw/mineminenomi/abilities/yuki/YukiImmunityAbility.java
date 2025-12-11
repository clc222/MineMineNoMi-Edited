/*    */ package xyz.pixelatedw.mineminenomi.abilities.yuki;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.EffectImmunityAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityType;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ 
/*    */ public class YukiImmunityAbility extends EffectImmunityAbility {
/* 11 */   public static final AbilityCore<YukiImmunityAbility> INSTANCE = (new AbilityCore.Builder("Yuki Immunities", AbilityCategory.DEVIL_FRUITS, AbilityType.PASSIVE, YukiImmunityAbility::new)).setHidden().build();
/*    */   
/* 13 */   private static final EffectImmunityAbility.ImmunityInfo IMMUNITY_INFO = (new EffectImmunityAbility.ImmunityInfo()).addLogiaImmunities().addImmunityEffects(new Supplier[] { (Supplier)ModEffects.FROSTBITE });
/*    */   
/*    */   public YukiImmunityAbility(AbilityCore core) {
/* 16 */     super(core, IMMUNITY_INFO);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\yuki\YukiImmunityAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */