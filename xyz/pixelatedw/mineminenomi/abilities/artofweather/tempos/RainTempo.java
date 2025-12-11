/*    */ package xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.world.server.ServerWorld;
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
/*    */ public class RainTempo extends TempoAbility {
/* 23 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "rain_tempo", new Pair[] {
/* 24 */         (Pair)ImmutablePair.of("Creates rain", null)
/*    */       });
/* 26 */   public static final AbilityCore<RainTempo> INSTANCE = (new AbilityCore.Builder("Rain Tempo", AbilityCategory.STYLE, AbilityType.PASSIVE, RainTempo::new))
/* 27 */     .addDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { TempoAbility.getTooltip(true, 3, WeatherBallKind.COOL)
/* 28 */       }).addDescriptionLine(DESCRIPTION)
/* 29 */     .setUnlockCheck(RainTempo::canUnlock)
/* 30 */     .build();
/*    */   
/*    */   public RainTempo(AbilityCore<RainTempo> core) {
/* 33 */     super(core);
/*    */   }
/*    */ 
/*    */   
/*    */   public void useTempo(LivingEntity entity) {
/* 38 */     ((ServerWorld)entity.field_70170_p).func_241113_a_(0, 0, true, true);
/*    */   }
/*    */   
/*    */   private static boolean canUnlock(LivingEntity entity) {
/* 42 */     if (!(entity instanceof PlayerEntity)) {
/* 43 */       return false;
/*    */     }
/*    */     
/* 46 */     PlayerEntity player = (PlayerEntity)entity;
/* 47 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/* 48 */     IQuestData questProps = QuestDataCapability.get(player);
/*    */     
/* 50 */     return (props.isWeatherWizard() && questProps.hasFinishedQuest(ModQuests.ART_OF_WEATHER_TRIAL_02));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\artofweather\tempos\RainTempo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */