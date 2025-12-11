/*    */ package xyz.pixelatedw.mineminenomi.abilities.suna;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.LogiaInvulnerabilityAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityType;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ 
/*    */ public class SunaLogiaAbility extends LogiaInvulnerabilityAbility {
/* 17 */   public static final AbilityCore<SunaLogiaAbility> INSTANCE = (new AbilityCore.Builder("Logia Invulnerability Suna", AbilityCategory.DEVIL_FRUITS, AbilityType.PASSIVE, SunaLogiaAbility::new))
/* 18 */     .addDescriptionLine(LogiaInvulnerabilityAbility.DESCRIPTION)
/* 19 */     .build();
/*    */   
/*    */   public SunaLogiaAbility(AbilityCore ability) {
/* 22 */     super(ability, null, (Supplier)ModParticleEffects.SUNA_LOGIA);
/* 23 */     this.onLogiaEffect = this::sideEffect;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isDamageTaken(LivingEntity entity, DamageSource source, float amount) {
/* 29 */     if (source instanceof ModDamageSource && ((ModDamageSource)source).getElement() == SourceElement.WATER) {
/* 30 */       return true;
/*    */     }
/*    */     
/* 33 */     if (SunaHelper.isWet(entity)) {
/* 34 */       return true;
/*    */     }
/*    */     
/* 37 */     return super.isDamageTaken(entity, source, amount);
/*    */   }
/*    */   
/*    */   public boolean sideEffect(LivingEntity target, LivingEntity attacker) {
/* 41 */     attacker.func_195064_c(new EffectInstance(Effects.field_82731_v, 40, 1));
/* 42 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\suna\SunaLogiaAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */