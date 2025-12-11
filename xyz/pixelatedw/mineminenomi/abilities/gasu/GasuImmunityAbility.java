/*    */ package xyz.pixelatedw.mineminenomi.abilities.gasu;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.Effects;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.EffectImmunityAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityType;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ 
/*    */ public class GasuImmunityAbility extends EffectImmunityAbility {
/* 12 */   public static final AbilityCore<GasuImmunityAbility> INSTANCE = (new AbilityCore.Builder("Gasu Immunities", AbilityCategory.DEVIL_FRUITS, AbilityType.PASSIVE, GasuImmunityAbility::new)).setHidden().build();
/*    */   
/* 14 */   private static final EffectImmunityAbility.ImmunityInfo IMMUNITY_INFO = (new EffectImmunityAbility.ImmunityInfo()).addImmunityEffects(new Supplier[] { () -> Effects.field_76436_u, (Supplier)ModEffects.DOKU_POISON }).addLogiaImmunities();
/*    */   
/*    */   public GasuImmunityAbility(AbilityCore core) {
/* 17 */     super(core, IMMUNITY_INFO);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\gasu\GasuImmunityAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */