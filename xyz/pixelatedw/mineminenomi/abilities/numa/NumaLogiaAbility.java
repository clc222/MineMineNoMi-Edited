/*    */ package xyz.pixelatedw.mineminenomi.abilities.numa;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.LogiaInvulnerabilityAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityType;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ 
/*    */ public class NumaLogiaAbility extends LogiaInvulnerabilityAbility {
/* 14 */   public static final AbilityCore<NumaLogiaAbility> INSTANCE = (new AbilityCore.Builder("Logia Invulnerability Numa", AbilityCategory.DEVIL_FRUITS, AbilityType.PASSIVE, NumaLogiaAbility::new))
/* 15 */     .addDescriptionLine(LogiaInvulnerabilityAbility.DESCRIPTION)
/* 16 */     .build();
/*    */   
/*    */   public NumaLogiaAbility(AbilityCore ability) {
/* 19 */     super(ability, null, (Supplier)ModParticleEffects.NUMA_LOGIA);
/* 20 */     this.onLogiaEffect = this::sideEffect;
/*    */   }
/*    */   
/*    */   public boolean sideEffect(LivingEntity target, LivingEntity attacker) {
/* 24 */     attacker.func_195064_c(new EffectInstance(Effects.field_76437_t, 50, 2));
/* 25 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\numa\NumaLogiaAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */