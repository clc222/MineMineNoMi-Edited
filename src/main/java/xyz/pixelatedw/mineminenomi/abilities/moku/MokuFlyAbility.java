/*    */ package xyz.pixelatedw.mineminenomi.abilities.moku;
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
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class MokuFlyAbility extends FlyAbility {
/* 23 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "moku_special_fly", new Pair[] {
/* 24 */         (Pair)ImmutablePair.of("Allows the user to fly", null)
/*    */       });
/* 26 */   public static final AbilityCore<MokuFlyAbility> INSTANCE = (new AbilityCore.Builder("Moku Special Fly", AbilityCategory.DEVIL_FRUITS, AbilityType.PASSIVE, MokuFlyAbility::new))
/* 27 */     .addDescriptionLine(DESCRIPTION)
/* 28 */     .build();
/*    */   
/*    */   public MokuFlyAbility(AbilityCore<MokuFlyAbility> core) {
/* 31 */     super(core);
/*    */   }
/*    */ 
/*    */   
/*    */   public Supplier<ParticleEffect<?>> getParticleEffects() {
/* 36 */     return (Supplier<ParticleEffect<?>>)ModParticleEffects.MOKU_FLY;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHeightDifference(PlayerEntity player) {
/* 41 */     return 30;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getSpeedMultiplier(PlayerEntity player) {
/* 46 */     IAbilityData abilityDataProps = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 48 */     if (abilityDataProps == null) {
/* 49 */       return 0.0F;
/*    */     }
/*    */     
/* 52 */     WhiteLauncherAbility whiteLauncher = (WhiteLauncherAbility)abilityDataProps.getEquippedAbility(WhiteLauncherAbility.INSTANCE);
/*    */     
/* 54 */     boolean whiteLauncherInUse = (whiteLauncher != null && ((Boolean)whiteLauncher.getComponent(ModAbilityKeys.CONTINUOUS).map(comp -> Boolean.valueOf((comp.isContinuous() && comp.getContinueTime() <= 15.0F))).orElse(Boolean.valueOf(false))).booleanValue());
/*    */     
/* 56 */     if (whiteLauncherInUse) {
/* 57 */       return 1.0F;
/*    */     }
/*    */     
/* 60 */     float speed = 0.5F;
/* 61 */     if (!WyHelper.isInCombat((LivingEntity)player)) {
/* 62 */       speed += speed * 0.3F;
/*    */     }
/*    */     
/* 65 */     return speed;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\moku\MokuFlyAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */