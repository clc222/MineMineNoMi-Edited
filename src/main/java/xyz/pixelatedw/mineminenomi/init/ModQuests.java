/*     */ package xyz.pixelatedw.mineminenomi.init;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import net.minecraftforge.eventbus.api.IEventBus;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.QuestId;
/*     */ import xyz.pixelatedw.mineminenomi.quests.CommandTrialQuest;
/*     */ import xyz.pixelatedw.mineminenomi.quests.EmptyHandsTrialQuest;
/*     */ import xyz.pixelatedw.mineminenomi.quests.KnockdownTrialQuest;
/*     */ import xyz.pixelatedw.mineminenomi.quests.TakedownTrialQuest;
/*     */ import xyz.pixelatedw.mineminenomi.quests.artofweather.ArtOfWeatherTrial01Quest;
/*     */ import xyz.pixelatedw.mineminenomi.quests.artofweather.ArtOfWeatherTrial02Quest;
/*     */ import xyz.pixelatedw.mineminenomi.quests.artofweather.ArtOfWeatherTrial03Quest;
/*     */ import xyz.pixelatedw.mineminenomi.quests.artofweather.ArtOfWeatherTrial04Quest;
/*     */ import xyz.pixelatedw.mineminenomi.quests.blackleg.BlackLegTrial01Quest;
/*     */ import xyz.pixelatedw.mineminenomi.quests.blackleg.BlackLegTrial02Quest;
/*     */ import xyz.pixelatedw.mineminenomi.quests.blackleg.BlackLegTrial03Quest;
/*     */ import xyz.pixelatedw.mineminenomi.quests.blackleg.BlackLegTrial04Quest;
/*     */ import xyz.pixelatedw.mineminenomi.quests.blackleg.BlackLegTrial05Quest;
/*     */ import xyz.pixelatedw.mineminenomi.quests.brawler.BrawlerTrial01Quest;
/*     */ import xyz.pixelatedw.mineminenomi.quests.brawler.BrawlerTrial02Quest;
/*     */ import xyz.pixelatedw.mineminenomi.quests.brawler.BrawlerTrial03Quest;
/*     */ import xyz.pixelatedw.mineminenomi.quests.brawler.BrawlerTrial04Quest;
/*     */ import xyz.pixelatedw.mineminenomi.quests.brawler.BrawlerTrial05Quest;
/*     */ import xyz.pixelatedw.mineminenomi.quests.brawler.BrawlerTrial06Quest;
/*     */ import xyz.pixelatedw.mineminenomi.quests.doctor.DoctorTrial01Quest;
/*     */ import xyz.pixelatedw.mineminenomi.quests.doctor.DoctorTrial02Quest;
/*     */ import xyz.pixelatedw.mineminenomi.quests.doctor.DoctorTrial03Quest;
/*     */ import xyz.pixelatedw.mineminenomi.quests.doctor.DoctorTrial04Quest;
/*     */ import xyz.pixelatedw.mineminenomi.quests.sniper.SniperTrial01Quest;
/*     */ import xyz.pixelatedw.mineminenomi.quests.sniper.SniperTrial02Quest;
/*     */ import xyz.pixelatedw.mineminenomi.quests.sniper.SniperTrial03Quest;
/*     */ import xyz.pixelatedw.mineminenomi.quests.sniper.SniperTrial04Quest;
/*     */ import xyz.pixelatedw.mineminenomi.quests.sniper.SniperTrial05Quest;
/*     */ import xyz.pixelatedw.mineminenomi.quests.sniper.SniperTrial06Quest;
/*     */ import xyz.pixelatedw.mineminenomi.quests.swordsman.SwordsmanTrial01Quest;
/*     */ import xyz.pixelatedw.mineminenomi.quests.swordsman.SwordsmanTrial02Quest;
/*     */ import xyz.pixelatedw.mineminenomi.quests.swordsman.SwordsmanTrial03Quest;
/*     */ import xyz.pixelatedw.mineminenomi.quests.swordsman.SwordsmanTrial04Quest;
/*     */ import xyz.pixelatedw.mineminenomi.quests.swordsman.SwordsmanTrial05Quest;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ModQuests
/*     */ {
/*  47 */   public static final QuestId SWORDSMAN_TRIAL_01 = WyRegistry.registerQuest(SwordsmanTrial01Quest.INSTANCE);
/*  48 */   public static final QuestId SWORDSMAN_TRIAL_02 = WyRegistry.registerQuest(SwordsmanTrial02Quest.INSTANCE);
/*  49 */   public static final QuestId SWORDSMAN_TRIAL_03 = WyRegistry.registerQuest(SwordsmanTrial03Quest.INSTANCE);
/*  50 */   public static final QuestId SWORDSMAN_TRIAL_04 = WyRegistry.registerQuest(SwordsmanTrial04Quest.INSTANCE);
/*  51 */   public static final QuestId SWORDSMAN_TRIAL_05 = WyRegistry.registerQuest(SwordsmanTrial05Quest.INSTANCE);
/*  52 */   public static final List<QuestId> SWORDSMAN_TRIALS = Arrays.asList(new QuestId[] { SWORDSMAN_TRIAL_01, SWORDSMAN_TRIAL_02, SWORDSMAN_TRIAL_03, SWORDSMAN_TRIAL_04, SWORDSMAN_TRIAL_05 });
/*     */ 
/*     */   
/*  55 */   public static final QuestId SNIPER_TRIAL_01 = WyRegistry.registerQuest(SniperTrial01Quest.INSTANCE);
/*  56 */   public static final QuestId SNIPER_TRIAL_02 = WyRegistry.registerQuest(SniperTrial02Quest.INSTANCE);
/*  57 */   public static final QuestId SNIPER_TRIAL_03 = WyRegistry.registerQuest(SniperTrial03Quest.INSTANCE);
/*  58 */   public static final QuestId SNIPER_TRIAL_04 = WyRegistry.registerQuest(SniperTrial04Quest.INSTANCE);
/*  59 */   public static final QuestId SNIPER_TRIAL_05 = WyRegistry.registerQuest(SniperTrial05Quest.INSTANCE);
/*  60 */   public static final QuestId SNIPER_TRIAL_06 = WyRegistry.registerQuest(SniperTrial06Quest.INSTANCE);
/*  61 */   public static final List<QuestId> SNIPER_TRIALS = Arrays.asList(new QuestId[] { SNIPER_TRIAL_01, SNIPER_TRIAL_02, SNIPER_TRIAL_03, SNIPER_TRIAL_04, SNIPER_TRIAL_05, SNIPER_TRIAL_06 });
/*     */ 
/*     */   
/*  64 */   public static final QuestId DOCTOR_TRIAL_01 = WyRegistry.registerQuest(DoctorTrial01Quest.INSTANCE);
/*  65 */   public static final QuestId DOCTOR_TRIAL_02 = WyRegistry.registerQuest(DoctorTrial02Quest.INSTANCE);
/*  66 */   public static final QuestId DOCTOR_TRIAL_03 = WyRegistry.registerQuest(DoctorTrial03Quest.INSTANCE);
/*  67 */   public static final QuestId DOCTOR_TRIAL_04 = WyRegistry.registerQuest(DoctorTrial04Quest.INSTANCE);
/*  68 */   public static final List<QuestId> DOCTOR_TRIALS = Arrays.asList(new QuestId[] { DOCTOR_TRIAL_01, DOCTOR_TRIAL_02, DOCTOR_TRIAL_03, DOCTOR_TRIAL_04 });
/*     */ 
/*     */   
/*  71 */   public static final QuestId ART_OF_WEATHER_TRIAL_01 = WyRegistry.registerQuest(ArtOfWeatherTrial01Quest.INSTANCE);
/*  72 */   public static final QuestId ART_OF_WEATHER_TRIAL_02 = WyRegistry.registerQuest(ArtOfWeatherTrial02Quest.INSTANCE);
/*  73 */   public static final QuestId ART_OF_WEATHER_TRIAL_03 = WyRegistry.registerQuest(ArtOfWeatherTrial03Quest.INSTANCE);
/*  74 */   public static final QuestId ART_OF_WEATHER_TRIAL_04 = WyRegistry.registerQuest(ArtOfWeatherTrial04Quest.INSTANCE);
/*  75 */   public static final List<QuestId> ART_OF_WEATHER_TRIALS = Arrays.asList(new QuestId[] { ART_OF_WEATHER_TRIAL_01, ART_OF_WEATHER_TRIAL_02, ART_OF_WEATHER_TRIAL_03, ART_OF_WEATHER_TRIAL_04 });
/*     */ 
/*     */   
/*  78 */   public static final QuestId BRAWLER_TRIAL_01 = WyRegistry.registerQuest(BrawlerTrial01Quest.INSTANCE);
/*  79 */   public static final QuestId BRAWLER_TRIAL_02 = WyRegistry.registerQuest(BrawlerTrial02Quest.INSTANCE);
/*  80 */   public static final QuestId BRAWLER_TRIAL_03 = WyRegistry.registerQuest(BrawlerTrial03Quest.INSTANCE);
/*  81 */   public static final QuestId BRAWLER_TRIAL_04 = WyRegistry.registerQuest(BrawlerTrial04Quest.INSTANCE);
/*  82 */   public static final QuestId BRAWLER_TRIAL_05 = WyRegistry.registerQuest(BrawlerTrial05Quest.INSTANCE);
/*  83 */   public static final QuestId BRAWLER_TRIAL_06 = WyRegistry.registerQuest(BrawlerTrial06Quest.INSTANCE);
/*  84 */   public static final List<QuestId> BRAWLER_TRIALS = Arrays.asList(new QuestId[] { BRAWLER_TRIAL_01, BRAWLER_TRIAL_02, BRAWLER_TRIAL_03, BRAWLER_TRIAL_04, BRAWLER_TRIAL_05, BRAWLER_TRIAL_06 });
/*     */ 
/*     */   
/*  87 */   public static final QuestId BLACK_LEG_TRIAL_01 = WyRegistry.registerQuest(BlackLegTrial01Quest.INSTANCE);
/*  88 */   public static final QuestId BLACK_LEG_TRIAL_02 = WyRegistry.registerQuest(BlackLegTrial02Quest.INSTANCE);
/*  89 */   public static final QuestId BLACK_LEG_TRIAL_03 = WyRegistry.registerQuest(BlackLegTrial03Quest.INSTANCE);
/*  90 */   public static final QuestId BLACK_LEG_TRIAL_04 = WyRegistry.registerQuest(BlackLegTrial04Quest.INSTANCE);
/*  91 */   public static final QuestId BLACK_LEG_TRIAL_05 = WyRegistry.registerQuest(BlackLegTrial05Quest.INSTANCE);
/*  92 */   public static final List<QuestId> BLACK_LEG_TRIALS = Arrays.asList(new QuestId[] { BLACK_LEG_TRIAL_01, BLACK_LEG_TRIAL_02, BLACK_LEG_TRIAL_03, BLACK_LEG_TRIAL_04, BLACK_LEG_TRIAL_05 });
/*     */ 
/*     */   
/*  95 */   public static final QuestId EMPTY_HANDS_TRIAL = WyRegistry.registerQuest(EmptyHandsTrialQuest.INSTANCE);
/*  96 */   public static final QuestId COMMAND_TRIAL = WyRegistry.registerQuest(CommandTrialQuest.INSTANCE);
/*  97 */   public static final QuestId KNOCKDOWN_TRIAL = WyRegistry.registerQuest(KnockdownTrialQuest.INSTANCE);
/*  98 */   public static final QuestId TAKEDOWN_TRIAL = WyRegistry.registerQuest(TakedownTrialQuest.INSTANCE);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void register(IEventBus eventBus) {
/* 104 */     WyRegistry.QUESTS.register(eventBus);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\init\ModQuests.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */