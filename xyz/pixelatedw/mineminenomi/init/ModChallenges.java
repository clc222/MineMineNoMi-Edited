/*     */ package xyz.pixelatedw.mineminenomi.init;
/*     */ 
/*     */ import net.minecraftforge.eventbus.api.IEventBus;
/*     */ import net.minecraftforge.fml.RegistryObject;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeDifficulty;
/*     */ import xyz.pixelatedw.mineminenomi.challenges.arlongpark.ArlongChallenge;
/*     */ import xyz.pixelatedw.mineminenomi.challenges.arlongpark.ArlongHardChallenge;
/*     */ import xyz.pixelatedw.mineminenomi.challenges.arlongpark.ChewChallenge;
/*     */ import xyz.pixelatedw.mineminenomi.challenges.arlongpark.ChewHardChallenge;
/*     */ import xyz.pixelatedw.mineminenomi.challenges.arlongpark.KuroobiChallenge;
/*     */ import xyz.pixelatedw.mineminenomi.challenges.arlongpark.KuroobiHardChallenge;
/*     */ import xyz.pixelatedw.mineminenomi.challenges.baroqueworks.Mr0Challenge;
/*     */ import xyz.pixelatedw.mineminenomi.challenges.baroqueworks.Mr0HardChallenge;
/*     */ import xyz.pixelatedw.mineminenomi.challenges.baroqueworks.Mr1Challenge;
/*     */ import xyz.pixelatedw.mineminenomi.challenges.baroqueworks.Mr1HardChallenge;
/*     */ import xyz.pixelatedw.mineminenomi.challenges.baroqueworks.Mr3Challenge;
/*     */ import xyz.pixelatedw.mineminenomi.challenges.baroqueworks.Mr3HardChallenge;
/*     */ import xyz.pixelatedw.mineminenomi.challenges.baroqueworks.Mr5MissValentineChallenge;
/*     */ import xyz.pixelatedw.mineminenomi.challenges.baroqueworks.Mr5MissValentineHardChallenge;
/*     */ import xyz.pixelatedw.mineminenomi.challenges.blackcatpirates.JangoChallenge;
/*     */ import xyz.pixelatedw.mineminenomi.challenges.blackcatpirates.JangoHardChallenge;
/*     */ import xyz.pixelatedw.mineminenomi.challenges.blackcatpirates.KuroChallenge;
/*     */ import xyz.pixelatedw.mineminenomi.challenges.blackcatpirates.KuroHardChallenge;
/*     */ import xyz.pixelatedw.mineminenomi.challenges.blackcatpirates.NyanbanBrothersChallenge;
/*     */ import xyz.pixelatedw.mineminenomi.challenges.blackcatpirates.NyanbanBrothersHardChallenge;
/*     */ import xyz.pixelatedw.mineminenomi.challenges.buggypirates.AlvidaChallenge;
/*     */ import xyz.pixelatedw.mineminenomi.challenges.buggypirates.AlvidaHardChallenge;
/*     */ import xyz.pixelatedw.mineminenomi.challenges.buggypirates.BuggyChallenge;
/*     */ import xyz.pixelatedw.mineminenomi.challenges.buggypirates.BuggyHardChallenge;
/*     */ import xyz.pixelatedw.mineminenomi.challenges.buggypirates.CabajiChallenge;
/*     */ import xyz.pixelatedw.mineminenomi.challenges.buggypirates.CabajiHardChallenge;
/*     */ import xyz.pixelatedw.mineminenomi.challenges.kriegpirates.DonKriegChallenge;
/*     */ import xyz.pixelatedw.mineminenomi.challenges.kriegpirates.DonKriegHardChallenge;
/*     */ import xyz.pixelatedw.mineminenomi.challenges.kriegpirates.GinChallenge;
/*     */ import xyz.pixelatedw.mineminenomi.challenges.kriegpirates.GinHardChallenge;
/*     */ import xyz.pixelatedw.mineminenomi.challenges.kriegpirates.PearlChallenge;
/*     */ import xyz.pixelatedw.mineminenomi.challenges.kriegpirates.PearlHardChallenge;
/*     */ import xyz.pixelatedw.mineminenomi.challenges.shellstown.MorganChallenge;
/*     */ import xyz.pixelatedw.mineminenomi.challenges.shellstown.MorganHardChallenge;
/*     */ import xyz.pixelatedw.mineminenomi.challenges.ungrouped.HigumaChallenge;
/*     */ import xyz.pixelatedw.mineminenomi.challenges.ungrouped.HigumaHardChallenge;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*     */ 
/*     */ 
/*     */ public class ModChallenges
/*     */ {
/*  48 */   public static final RegistryObject<ChallengeCore<HigumaChallenge>> HIGUMA = registerChallenge(HigumaChallenge.INSTANCE);
/*  49 */   public static final RegistryObject<ChallengeCore<HigumaHardChallenge>> HIGUMA_HARD = registerChallenge(HigumaHardChallenge.INSTANCE);
/*     */ 
/*     */   
/*  52 */   public static final RegistryObject<ChallengeCore<MorganChallenge>> MORGAN = registerChallenge(MorganChallenge.INSTANCE);
/*  53 */   public static final RegistryObject<ChallengeCore<MorganHardChallenge>> MORGAN_HARD = registerChallenge(MorganHardChallenge.INSTANCE);
/*     */ 
/*     */   
/*  56 */   public static final RegistryObject<ChallengeCore<BuggyChallenge>> BUGGY = registerChallenge(BuggyChallenge.INSTANCE);
/*  57 */   public static final RegistryObject<ChallengeCore<BuggyHardChallenge>> BUGGY_HARD = registerChallenge(BuggyHardChallenge.INSTANCE);
/*  58 */   public static final RegistryObject<ChallengeCore<CabajiChallenge>> CABAJI = registerChallenge(CabajiChallenge.INSTANCE);
/*  59 */   public static final RegistryObject<ChallengeCore<CabajiHardChallenge>> CABAJI_HARD = registerChallenge(CabajiHardChallenge.INSTANCE);
/*  60 */   public static final RegistryObject<ChallengeCore<AlvidaChallenge>> ALVIDA = registerChallenge(AlvidaChallenge.INSTANCE);
/*  61 */   public static final RegistryObject<ChallengeCore<AlvidaHardChallenge>> ALVIDA_HARD = registerChallenge(AlvidaHardChallenge.INSTANCE);
/*     */ 
/*     */   
/*  64 */   public static final RegistryObject<ChallengeCore<KuroChallenge>> KURO = registerChallenge(KuroChallenge.INSTANCE);
/*  65 */   public static final RegistryObject<ChallengeCore<KuroHardChallenge>> KURO_HARD = registerChallenge(KuroHardChallenge.INSTANCE);
/*  66 */   public static final RegistryObject<ChallengeCore<JangoChallenge>> JANGO = registerChallenge(JangoChallenge.INSTANCE);
/*  67 */   public static final RegistryObject<ChallengeCore<JangoHardChallenge>> JANGO_HARD = registerChallenge(JangoHardChallenge.INSTANCE);
/*  68 */   public static final RegistryObject<ChallengeCore<NyanbanBrothersChallenge>> NYANBAN_BROTHERS = registerChallenge(NyanbanBrothersChallenge.INSTANCE);
/*  69 */   public static final RegistryObject<ChallengeCore<NyanbanBrothersHardChallenge>> NYANBAN_BROTHERS_HARD = registerChallenge(NyanbanBrothersHardChallenge.INSTANCE);
/*     */ 
/*     */   
/*  72 */   public static final RegistryObject<ChallengeCore<DonKriegChallenge>> DON_KRIEG = registerChallenge(DonKriegChallenge.INSTANCE);
/*  73 */   public static final RegistryObject<ChallengeCore<DonKriegHardChallenge>> DON_KRIEG_HARD = registerChallenge(DonKriegHardChallenge.INSTANCE);
/*  74 */   public static final RegistryObject<ChallengeCore<GinChallenge>> GIN = registerChallenge(GinChallenge.INSTANCE);
/*  75 */   public static final RegistryObject<ChallengeCore<GinHardChallenge>> GIN_HARD = registerChallenge(GinHardChallenge.INSTANCE);
/*  76 */   public static final RegistryObject<ChallengeCore<PearlChallenge>> PEARL = registerChallenge(PearlChallenge.INSTANCE);
/*  77 */   public static final RegistryObject<ChallengeCore<PearlHardChallenge>> PEARL_HARD = registerChallenge(PearlHardChallenge.INSTANCE);
/*     */ 
/*     */   
/*  80 */   public static final RegistryObject<ChallengeCore<ArlongChallenge>> ARLONG = registerChallenge(ArlongChallenge.INSTANCE);
/*  81 */   public static final RegistryObject<ChallengeCore<ArlongHardChallenge>> ARLONG_HARD = registerChallenge(ArlongHardChallenge.INSTANCE);
/*  82 */   public static final RegistryObject<ChallengeCore<KuroobiChallenge>> KUROOBI = registerChallenge(KuroobiChallenge.INSTANCE);
/*  83 */   public static final RegistryObject<ChallengeCore<KuroobiHardChallenge>> KUROOBI_HARD = registerChallenge(KuroobiHardChallenge.INSTANCE);
/*  84 */   public static final RegistryObject<ChallengeCore<ChewChallenge>> CHEW = registerChallenge(ChewChallenge.INSTANCE);
/*  85 */   public static final RegistryObject<ChallengeCore<ChewHardChallenge>> CHEW_HARD = registerChallenge(ChewHardChallenge.INSTANCE);
/*     */ 
/*     */ 
/*     */   
/*  89 */   public static final RegistryObject<ChallengeCore<Mr0Challenge>> MR_0 = registerChallenge(Mr0Challenge.INSTANCE);
/*  90 */   public static final RegistryObject<ChallengeCore<Mr0HardChallenge>> MR_0_HARD = registerChallenge(Mr0HardChallenge.INSTANCE);
/*  91 */   public static final RegistryObject<ChallengeCore<Mr1Challenge>> MR_1 = registerChallenge(Mr1Challenge.INSTANCE);
/*  92 */   public static final RegistryObject<ChallengeCore<Mr1HardChallenge>> MR_1_HARD = registerChallenge(Mr1HardChallenge.INSTANCE);
/*     */   
/*  94 */   public static final RegistryObject<ChallengeCore<Mr3Challenge>> MR_3 = registerChallenge(Mr3Challenge.INSTANCE);
/*  95 */   public static final RegistryObject<ChallengeCore<Mr3HardChallenge>> MR_3_HARD = registerChallenge(Mr3HardChallenge.INSTANCE);
/*     */ 
/*     */   
/*  98 */   public static final RegistryObject<ChallengeCore<Mr5MissValentineChallenge>> MR_5_AND_MISS_VALENTINE = registerChallenge(Mr5MissValentineChallenge.INSTANCE);
/*  99 */   public static final RegistryObject<ChallengeCore<Mr5MissValentineHardChallenge>> MR_5_AND_MISS_VALENTINE_HARD = registerChallenge(Mr5MissValentineHardChallenge.INSTANCE);
/*     */   
/*     */   public static <T extends xyz.pixelatedw.mineminenomi.api.challenges.Challenge> RegistryObject<ChallengeCore<T>> registerChallenge(ChallengeCore<T> challenge) {
/* 102 */     RegistryObject<ChallengeCore<T>> reg = WyRegistry.CHALLENGES.register(challenge.getId(), () -> challenge);
/* 103 */     return reg;
/*     */   }
/*     */   
/*     */   public static void register(IEventBus bus) {
/* 107 */     WyRegistry.CHALLENGES.register(bus);
/*     */   }
/*     */   
/*     */   public static class Order
/*     */   {
/* 112 */     private static int idx = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 119 */     public static final int ALVIDA = orderIndex();
/* 120 */     public static final int HIGUMA = orderIndex();
/* 121 */     public static final int MORGAN = orderIndex();
/*     */     
/* 123 */     public static final int MOHJI_RICHIE = orderIndex();
/* 124 */     public static final int CABAJI = orderIndex();
/* 125 */     public static final int BUGGY = orderIndex();
/*     */     
/* 127 */     public static final int SHAM_BUCHI = orderIndex();
/* 128 */     public static final int JANGO = orderIndex();
/* 129 */     public static final int KURO = orderIndex();
/*     */     
/* 131 */     public static final int PEARL = orderIndex();
/* 132 */     public static final int GIN = orderIndex();
/* 133 */     public static final int DON_KRIEG = orderIndex();
/*     */     
/* 135 */     public static final int CHEW = orderIndex();
/* 136 */     public static final int KUROOBI = orderIndex();
/* 137 */     public static final int HATCHAN = orderIndex();
/* 138 */     public static final int ARLONG = orderIndex();
/*     */     
/* 140 */     public static final int MR_5_MISS_VALENTINE = orderIndex();
/* 141 */     public static final int MR_4_MISS_MERRY_CHRISTMAS = orderIndex();
/* 142 */     public static final int MR_3 = orderIndex();
/* 143 */     public static final int MR_2 = orderIndex();
/* 144 */     public static final int MISS_DOUBLEFINGER = orderIndex();
/* 145 */     public static final int MR_1 = orderIndex();
/* 146 */     public static final int MR_0 = orderIndex();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private static int orderIndex() {
/* 154 */       int currentIdx = idx;
/* 155 */       idx += (ChallengeDifficulty.values()).length;
/*     */       
/* 157 */       return currentIdx;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\init\ModChallenges.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */