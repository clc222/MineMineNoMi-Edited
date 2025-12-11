/*    */ package xyz.pixelatedw.mineminenomi.abilities.goro;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.LogiaInvulnerabilityAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityType;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.PassiveAbility2;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.AbilityDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*    */ import xyz.pixelatedw.mineminenomi.config.ClientConfig;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ 
/*    */ public class GoroLogiaAbility extends LogiaInvulnerabilityAbility {
/* 21 */   private static final ResourceLocation DEFAULT_ICON = new ResourceLocation("mineminenomi", "textures/abilities/logia_invulnerability_goro.png");
/* 22 */   private static final ResourceLocation ALT_ICON = new ResourceLocation("mineminenomi", "textures/abilities/alts/logia_invulnerability_goro.png");
/*    */   
/* 24 */   public static final AbilityCore<GoroLogiaAbility> INSTANCE = (new AbilityCore.Builder("Logia Invulnerability Goro", AbilityCategory.DEVIL_FRUITS, AbilityType.PASSIVE, GoroLogiaAbility::new))
/* 25 */     .addDescriptionLine(LogiaInvulnerabilityAbility.DESCRIPTION)
/* 26 */     .setIcon(DEFAULT_ICON)
/* 27 */     .build();
/*    */   
/*    */   public GoroLogiaAbility(AbilityCore<GoroLogiaAbility> ability) {
/* 30 */     super(ability, null, (Supplier)ModParticleEffects.GORO_LOGIA);
/*    */     
/* 32 */     this.onLogiaEffect = this::sideEffect;
/* 33 */     addEquipEvent(this::equipEvent);
/*    */   }
/*    */   
/*    */   public void equipEvent(LivingEntity entity, PassiveAbility2 ability) {
/* 37 */     setDisplayIcon(DEFAULT_ICON);
/* 38 */     if (ClientConfig.INSTANCE.isGoroBlue()) {
/* 39 */       setDisplayIcon(ALT_ICON);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isDamageTaken(LivingEntity entity, DamageSource source, float amount) {
/* 45 */     if (source instanceof AbilityDamageSource && ((AbilityDamageSource)source).getElement() == SourceElement.RUBBER) {
/* 46 */       return true;
/*    */     }
/*    */     
/* 49 */     return super.isDamageTaken(entity, source, amount);
/*    */   }
/*    */   
/*    */   public boolean sideEffect(LivingEntity target, LivingEntity attacker) {
/* 53 */     IDevilFruit props = DevilFruitCapability.get(attacker);
/*    */     
/* 55 */     boolean attackerHasGomu = (props.hasDevilFruit(ModAbilities.GOMU_GOMU_NO_MI) && attacker.func_184614_ca().func_190926_b());
/*    */     
/* 57 */     if (!attackerHasGomu) {
/* 58 */       attacker.func_70097_a((DamageSource)AbilityDamageSource.causeAbilityDamage(target, (IAbility)this).setSourceElement(SourceElement.LIGHTNING), 5.0F);
/*    */       
/* 60 */       return false;
/*    */     } 
/*    */     
/* 63 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\goro\GoroLogiaAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */