/*    */ package xyz.pixelatedw.mineminenomi.abilities.artofweather;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.math.EntityRayTraceResult;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.WeatherBallKind;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.artofweather.ThunderBallProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.artofweather.WeatherBallProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ 
/*    */ public class ThunderBallAbility extends WeatherBallAbility {
/* 27 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "thunder_ball", new Pair[] {
/* 28 */         (Pair)ImmutablePair.of("Launch a Thunder Ball from your Clima Tact to use for different Tempos", null), 
/* 29 */         (Pair)ImmutablePair.of("§aSHIFT-USE§r: Loads the ball into the clima tact", null)
/*    */       });
/* 31 */   public static final AbilityCore<ThunderBallAbility> INSTANCE = (new AbilityCore.Builder("Thunder Ball", AbilityCategory.STYLE, ThunderBallAbility::new))
/* 32 */     .addDescriptionLine(DESCRIPTION)
/* 33 */     .setSourceElement(SourceElement.LIGHTNING)
/* 34 */     .setSourceHakiNature(SourceHakiNature.IMBUING)
/* 35 */     .setUnlockCheck(ThunderBallAbility::canUnlock)
/* 36 */     .build();
/*    */   
/*    */   public ThunderBallAbility(AbilityCore<ThunderBallAbility> core) {
/* 39 */     super(core);
/*    */     
/* 41 */     this.isNew = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public WeatherBallKind getKind() {
/* 46 */     return WeatherBallKind.THUNDER;
/*    */   }
/*    */ 
/*    */   
/*    */   public ParticleEffect<?> getParticleEffect() {
/* 51 */     return (ParticleEffect)ModParticleEffects.THUNDER_BALL_CHARGE.get();
/*    */   }
/*    */ 
/*    */   
/*    */   public WeatherBallProjectile getWeatherBallEntity(LivingEntity entity, EntityRayTraceResult lookTrace) {
/* 56 */     ThunderBallProjectile proj = new ThunderBallProjectile(entity.field_70170_p, entity, INSTANCE);
/* 57 */     proj.func_70012_b(lookTrace.func_216347_e().func_82615_a(), entity.func_226278_cu_() + entity.func_70047_e() - 0.5D, lookTrace.func_216347_e().func_82616_c(), entity.field_70177_z, entity.field_70125_A);
/* 58 */     AbilityHelper.setDeltaMovement((Entity)proj, 0.0D, 0.3D, 0.0D);
/* 59 */     return (WeatherBallProjectile)proj;
/*    */   }
/*    */   
/*    */   private static boolean canUnlock(LivingEntity entity) {
/* 63 */     if (!(entity instanceof PlayerEntity)) {
/* 64 */       return false;
/*    */     }
/*    */     
/* 67 */     PlayerEntity player = (PlayerEntity)entity;
/* 68 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/* 69 */     IQuestData questProps = QuestDataCapability.get(player);
/*    */     
/* 71 */     return (props.isWeatherWizard() && questProps.hasFinishedQuest(ModQuests.ART_OF_WEATHER_TRIAL_02));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\artofweather\ThunderBallAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */