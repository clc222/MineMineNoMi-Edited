/*    */ package xyz.pixelatedw.mineminenomi.abilities.mera;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
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
/*    */ public class MeraLogiaAbility extends LogiaInvulnerabilityAbility {
/* 15 */   public static final AbilityCore<MeraLogiaAbility> INSTANCE = (new AbilityCore.Builder("Logia Invulnerability Mera", AbilityCategory.DEVIL_FRUITS, AbilityType.PASSIVE, MeraLogiaAbility::new))
/* 16 */     .addDescriptionLine(LogiaInvulnerabilityAbility.DESCRIPTION)
/* 17 */     .build();
/*    */   
/*    */   public MeraLogiaAbility(AbilityCore ability) {
/* 20 */     super(ability, SourceImmunityAbility.FIRE_IMMUNITY, (Supplier)ModParticleEffects.MERA_LOGIA);
/* 21 */     this.onLogiaEffect = this::sideEffect;
/*    */   }
/*    */   
/*    */   public boolean sideEffect(LivingEntity target, LivingEntity attacker) {
/* 25 */     AbilityHelper.setSecondsOnFireBy((Entity)target, 3, attacker);
/*    */     
/* 27 */     attacker.func_70097_a(AbilityDamageSource.causeAbilityDamage(target, (IAbility)this).setSourceElement(SourceElement.FIRE).func_76361_j(), 5.0F);
/*    */     
/* 29 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\mera\MeraLogiaAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */