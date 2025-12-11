/*    */ package xyz.pixelatedw.mineminenomi.abilities.magu;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.LogiaInvulnerabilityAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.SourceImmunityAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityType;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.AbilityDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ 
/*    */ public class MaguLogiaAbility extends LogiaInvulnerabilityAbility {
/* 17 */   public static final AbilityCore<MaguLogiaAbility> INSTANCE = (new AbilityCore.Builder("Logia Invulnerability Magu", AbilityCategory.DEVIL_FRUITS, AbilityType.PASSIVE, MaguLogiaAbility::new))
/* 18 */     .addDescriptionLine(LogiaInvulnerabilityAbility.DESCRIPTION)
/* 19 */     .build();
/*    */   
/*    */   public MaguLogiaAbility(AbilityCore ability) {
/* 22 */     super(ability, SourceImmunityAbility.MAGMA_IMMUNITY, (Supplier)ModParticleEffects.MAGU_LOGIA);
/* 23 */     this.onLogiaEffect = this::sideEffect;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isDamageTaken(LivingEntity entity, DamageSource source, float amount) {
/* 28 */     if (source.equals(DamageSource.field_76371_c)) {
/* 29 */       entity.func_70066_B();
/* 30 */       return false;
/*    */     } 
/* 32 */     return super.isDamageTaken(entity, source, amount);
/*    */   }
/*    */   
/*    */   public boolean sideEffect(LivingEntity target, LivingEntity attacker) {
/* 36 */     AbilityHelper.setSecondsOnFireBy((Entity)target, 5, attacker);
/*    */     
/* 38 */     attacker.func_70097_a(AbilityDamageSource.causeAbilityDamage(target, (IAbility)this).setSourceElement(SourceElement.MAGMA).func_76361_j(), 10.0F);
/*    */     
/* 40 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\magu\MaguLogiaAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */