/*    */ package xyz.pixelatedw.mineminenomi.abilities.gasu;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.LogiaInvulnerabilityAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityType;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ 
/*    */ public class GasuLogiaAbility extends LogiaInvulnerabilityAbility {
/* 13 */   public static final AbilityCore<GasuLogiaAbility> INSTANCE = (new AbilityCore.Builder("Logia Invulnerability Gasu", AbilityCategory.DEVIL_FRUITS, AbilityType.PASSIVE, GasuLogiaAbility::new))
/* 14 */     .addDescriptionLine(LogiaInvulnerabilityAbility.DESCRIPTION)
/* 15 */     .build();
/*    */   
/*    */   public GasuLogiaAbility(AbilityCore ability) {
/* 18 */     super(ability, null, (Supplier)ModParticleEffects.GASU_LOGIA);
/* 19 */     this.onLogiaEffect = this::sideEffect;
/*    */   }
/*    */   
/*    */   public boolean sideEffect(LivingEntity target, LivingEntity attacker) {
/* 23 */     attacker.func_195064_c(new EffectInstance(Effects.field_76436_u, 40, 0));
/* 24 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\gasu\GasuLogiaAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */