/*    */ package xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityType;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.TempoAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.WeatherBallKind;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*    */ 
/*    */ public class WeatherCloudTempo extends TempoAbility {
/* 22 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "weather_cloud_tempo", new Pair[] {
/* 23 */         (Pair)ImmutablePair.of("Creates a cloud that can further be used by other Tempos", null)
/*    */       });
/* 25 */   public static final AbilityCore<WeatherCloudTempo> INSTANCE = (new AbilityCore.Builder("Weather Cloud Tempo", AbilityCategory.STYLE, AbilityType.PASSIVE, WeatherCloudTempo::new))
/* 26 */     .addDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { TempoAbility.getTooltip(false, new WeatherBallKind[] { WeatherBallKind.COOL, WeatherBallKind.HEAT
/* 27 */           }) }).addDescriptionLine(DESCRIPTION)
/* 28 */     .setUnlockCheck(WeatherCloudTempo::canUnlock)
/* 29 */     .build();
/*    */   
/*    */   public WeatherCloudTempo(AbilityCore<WeatherCloudTempo> core) {
/* 32 */     super(core);
/*    */   }
/*    */   
/*    */   private static boolean canUnlock(LivingEntity entity) {
/* 36 */     if (!(entity instanceof PlayerEntity)) {
/* 37 */       return false;
/*    */     }
/*    */     
/* 40 */     PlayerEntity player = (PlayerEntity)entity;
/* 41 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/* 42 */     IQuestData questProps = QuestDataCapability.get(player);
/*    */     
/* 44 */     return (props.isWeatherWizard() && questProps.hasFinishedQuest(ModQuests.ART_OF_WEATHER_TRIAL_01));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\artofweather\tempos\WeatherCloudTempo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */