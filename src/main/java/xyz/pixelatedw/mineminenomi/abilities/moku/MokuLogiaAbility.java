/*    */ package xyz.pixelatedw.mineminenomi.abilities.moku;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.LogiaInvulnerabilityAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ 
/*    */ public class MokuLogiaAbility extends LogiaInvulnerabilityAbility {
/* 14 */   public static final AbilityCore<MokuLogiaAbility> INSTANCE = (new AbilityCore.Builder("Logia Invulnerability Moku", AbilityCategory.DEVIL_FRUITS, AbilityType.PASSIVE, MokuLogiaAbility::new))
/* 15 */     .addDescriptionLine(LogiaInvulnerabilityAbility.DESCRIPTION)
/* 16 */     .build();
/*    */   
/*    */   public MokuLogiaAbility(AbilityCore ability) {
/* 19 */     super(ability, null, (Supplier)ModParticleEffects.MOKU_LOGIA);
/* 20 */     this.onLogiaEffect = this::sideEffect;
/*    */   }
/*    */   
/*    */   public boolean sideEffect(LivingEntity target, LivingEntity attacker) {
/* 24 */     attacker.func_195064_c(new EffectInstance((Effect)ModEffects.SMOKE.get(), 20, 0));
/* 25 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\moku\MokuLogiaAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */