/*    */ package xyz.pixelatedw.mineminenomi.abilities.gasu;
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
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class GasuFlyAbility
/*    */   extends FlyAbility {
/* 20 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "gasu_special_fly", new Pair[] {
/* 21 */         (Pair)ImmutablePair.of("Allows the user to fly", null)
/*    */       });
/* 23 */   public static final AbilityCore<GasuFlyAbility> INSTANCE = (new AbilityCore.Builder("Gasu Special Fly", AbilityCategory.DEVIL_FRUITS, AbilityType.PASSIVE, GasuFlyAbility::new))
/* 24 */     .addDescriptionLine(DESCRIPTION)
/* 25 */     .build();
/*    */   
/*    */   public GasuFlyAbility(AbilityCore<GasuFlyAbility> core) {
/* 28 */     super(core);
/*    */   }
/*    */ 
/*    */   
/*    */   public Supplier<ParticleEffect<?>> getParticleEffects() {
/* 33 */     return (Supplier<ParticleEffect<?>>)ModParticleEffects.GASU_FLY;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHeightDifference(PlayerEntity player) {
/* 38 */     return 40;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getSpeedMultiplier(PlayerEntity player) {
/* 43 */     float speed = 0.75F;
/* 44 */     if (!WyHelper.isInCombat((LivingEntity)player)) {
/* 45 */       speed += speed * 0.3F;
/*    */     }
/* 47 */     return speed;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\gasu\GasuFlyAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */