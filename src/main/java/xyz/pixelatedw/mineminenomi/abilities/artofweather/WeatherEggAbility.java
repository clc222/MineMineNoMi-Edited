/*    */ package xyz.pixelatedw.mineminenomi.abilities.artofweather;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.math.EntityRayTraceResult;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.artofweather.WeatherEggProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class WeatherEggAbility extends Ability {
/* 25 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "weather_egg", new Pair[] {
/* 26 */         (Pair)ImmutablePair.of("Instantly creates a Weather Cloud as if combining a Cool Ball and a Heat Ball", null)
/*    */       });
/*    */   
/*    */   private static final float COOLDOWN = 320.0F;
/* 30 */   public static final AbilityCore<WeatherEggAbility> INSTANCE = (new AbilityCore.Builder("Weather Egg", AbilityCategory.STYLE, WeatherEggAbility::new))
/* 31 */     .addDescriptionLine(DESCRIPTION)
/* 32 */     .setSourceHakiNature(SourceHakiNature.IMBUING)
/* 33 */     .setUnlockCheck(WeatherEggAbility::canUnlock)
/* 34 */     .build();
/*    */   
/*    */   public WeatherEggAbility(AbilityCore<WeatherEggAbility> core) {
/* 37 */     super(core);
/*    */     
/* 39 */     this.isNew = true;
/*    */     
/* 41 */     addCanUseCheck(ArtOfWeatherHelper::needsSorceryClimaTact);
/* 42 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 46 */     EntityRayTraceResult trace = WyHelper.rayTraceEntities((Entity)entity, 1.5D);
/* 47 */     WeatherEggProjectile proj = new WeatherEggProjectile(entity.field_70170_p, entity, INSTANCE);
/* 48 */     proj.func_70012_b(trace.func_216347_e().func_82615_a(), entity.func_226278_cu_() + entity.func_70047_e() - 0.5D, trace.func_216347_e().func_82616_c(), entity.field_70177_z, entity.field_70125_A);
/* 49 */     AbilityHelper.setDeltaMovement((Entity)proj, 0.0D, 0.3D, 0.0D);
/* 50 */     entity.field_70170_p.func_217376_c((Entity)proj);
/* 51 */     this.cooldownComponent.startCooldown(entity, 320.0F);
/*    */   }
/*    */   
/*    */   private static boolean canUnlock(LivingEntity entity) {
/* 55 */     if (!(entity instanceof PlayerEntity)) {
/* 56 */       return false;
/*    */     }
/*    */     
/* 59 */     PlayerEntity player = (PlayerEntity)entity;
/* 60 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/* 61 */     IQuestData questProps = QuestDataCapability.get(player);
/*    */     
/* 63 */     return (props.isWeatherWizard() && questProps.hasFinishedQuest(ModQuests.ART_OF_WEATHER_TRIAL_04));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\artofweather\WeatherEggAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */