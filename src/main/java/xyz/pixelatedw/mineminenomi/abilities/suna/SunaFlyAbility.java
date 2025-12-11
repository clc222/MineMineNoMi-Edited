/*    */ package xyz.pixelatedw.mineminenomi.abilities.suna;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.FlyAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityType;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUseResult;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class SunaFlyAbility
/*    */   extends FlyAbility
/*    */ {
/* 24 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "suna_special_fly", new Pair[] {
/* 25 */         (Pair)ImmutablePair.of("Allows the user to fly, cannot be used while the user is wet", null)
/*    */       });
/* 27 */   public static final AbilityCore<SunaFlyAbility> INSTANCE = (new AbilityCore.Builder("Suna Special Fly", AbilityCategory.DEVIL_FRUITS, AbilityType.PASSIVE, SunaFlyAbility::new))
/* 28 */     .addDescriptionLine(DESCRIPTION)
/* 29 */     .build();
/*    */   
/*    */   public SunaFlyAbility(AbilityCore<SunaFlyAbility> core) {
/* 32 */     super(core);
/*    */   }
/*    */ 
/*    */   
/*    */   public AbilityUseResult canUse(LivingEntity entity) {
/* 37 */     if (SunaHelper.isWet(entity)) {
/* 38 */       return AbilityUseResult.fail(null);
/*    */     }
/*    */     
/* 41 */     return super.canUse(entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public Supplier<ParticleEffect<?>> getParticleEffects() {
/* 46 */     return (Supplier<ParticleEffect<?>>)ModParticleEffects.SUNA_FLY;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHeightDifference(PlayerEntity player) {
/* 51 */     return 30;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getSpeedMultiplier(PlayerEntity player) {
/* 56 */     IAbilityData abilityDataProps = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 58 */     if (abilityDataProps == null) {
/* 59 */       return 0.0F;
/*    */     }
/*    */     
/* 62 */     GrandeSablesAbility grandeSables = (GrandeSablesAbility)abilityDataProps.getEquippedAbility(GrandeSablesAbility.INSTANCE);
/* 63 */     BarjanAbility barjanAbility = (BarjanAbility)abilityDataProps.getEquippedAbility(BarjanAbility.INSTANCE);
/*    */     
/* 65 */     if ((grandeSables != null && grandeSables.isContinuous()) || (barjanAbility != null && barjanAbility.isContinuous())) {
/* 66 */       return 1.0F;
/*    */     }
/*    */     
/* 69 */     float speed = 0.4F;
/*    */     
/* 71 */     if (SunaHelper.isFruitBoosted((LivingEntity)player)) {
/* 72 */       speed = 0.75F;
/*    */     }
/*    */     
/* 75 */     if (!WyHelper.isInCombat((LivingEntity)player)) {
/* 76 */       speed += speed * 0.3F;
/*    */     }
/*    */     
/* 79 */     return speed;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\suna\SunaFlyAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */