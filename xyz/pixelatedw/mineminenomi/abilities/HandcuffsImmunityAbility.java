/*    */ package xyz.pixelatedw.mineminenomi.abilities;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.potion.Effect;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ 
/*    */ public class HandcuffsImmunityAbility extends EffectImmunityAbility {
/*  9 */   public static final AbilityCore<HandcuffsImmunityAbility> INSTANCE = (new AbilityCore.Builder("Handcuff Immunities", AbilityCategory.DEVIL_FRUITS, AbilityType.PASSIVE, HandcuffsImmunityAbility::new)).setHidden().build();
/*    */   
/* 11 */   private static final EffectImmunityAbility.ImmunityInfo IMMUNITY_INFO = (new EffectImmunityAbility.ImmunityInfo()).addImmunityEffects((Supplier<Effect>[])new Supplier[] { (Supplier)ModEffects.HANDCUFFED });
/*    */   
/*    */   public HandcuffsImmunityAbility(AbilityCore<EffectImmunityAbility> core) {
/* 14 */     super(core, IMMUNITY_INFO);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\HandcuffsImmunityAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */