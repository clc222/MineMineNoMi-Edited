/*    */ package xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.world.IWorld;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityType;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.WeatherBallKind;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.artofweather.MirageTempoCloudEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEntityPredicates;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class FogTempo extends ChargedTempoAbility {
/* 28 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "fog_tempo", new Pair[] {
/* 29 */         (Pair)ImmutablePair.of("Creates a thick fog at the user's position, blinding all nearby enemies as well", null)
/*    */       });
/* 31 */   public static final AbilityCore<FogTempo> INSTANCE = (new AbilityCore.Builder("Fog Tempo", AbilityCategory.STYLE, AbilityType.PASSIVE, FogTempo::new))
/* 32 */     .addDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { CHARGED_TEMPO_DESCRIPTION
/* 33 */       }).addDescriptionLine(DESCRIPTION)
/* 34 */     .setUnlockCheck(FogTempo::canUnlock)
/* 35 */     .build();
/*    */   
/*    */   public FogTempo(AbilityCore<FogTempo> core) {
/* 38 */     super(core);
/*    */   }
/*    */ 
/*    */   
/*    */   public WeatherBallKind[] getTempoOrder() {
/* 43 */     return new WeatherBallKind[] { WeatherBallKind.HEAT, WeatherBallKind.HEAT, WeatherBallKind.COOL };
/*    */   }
/*    */ 
/*    */   
/*    */   public void useTempo(LivingEntity entity) {
/* 48 */     MirageTempoCloudEntity smokeCloud = new MirageTempoCloudEntity(entity.field_70170_p);
/* 49 */     smokeCloud.setLife(300);
/* 50 */     smokeCloud.func_70012_b(entity.func_226277_ct_(), entity.func_226278_cu_() + 1.0D, entity.func_226281_cx_(), 0.0F, 0.0F);
/* 51 */     AbilityHelper.setDeltaMovement((Entity)smokeCloud, 0.0D, 0.0D, 0.0D);
/* 52 */     smokeCloud.setThrower(entity);
/* 53 */     entity.field_70170_p.func_217376_c((Entity)smokeCloud);
/*    */     
/* 55 */     List<LivingEntity> targets = WyHelper.getNearbyLiving(entity.func_213303_ch(), (IWorld)entity.field_70170_p, 10.0D, ModEntityPredicates.getEnemyFactions(entity));
/*    */     
/* 57 */     for (LivingEntity target : targets) {
/* 58 */       target.func_195064_c(new EffectInstance(Effects.field_76440_q, 200, 1));
/*    */     }
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


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\artofweather\tempos\FogTempo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */