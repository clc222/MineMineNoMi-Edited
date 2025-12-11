/*    */ package xyz.pixelatedw.mineminenomi.challenges.baroqueworks;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ArenaStyle;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.Challenge;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeArena;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeDifficulty;
/*    */ import xyz.pixelatedw.mineminenomi.challenges.arenas.AlabastaDesertSimpleArena;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModChallenges;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModNPCGroups;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ public class Mr4MissMerryChristmasHardChallenge extends Challenge {
/* 16 */   private static final String TITLE = WyRegistry.registerName("challenge.mineminenomi.baroque_works.mr_4_and_miss_merry_christmas_hard", "Mr. 4 & Miss Merry Christmas (Hard)");
/* 17 */   public static final ResourceLocation REWARD = new ResourceLocation("mineminenomi", "rewards/mr_4_and_miss_merry_christmas_hard");
/*    */   
/* 19 */   public static final ChallengeCore<Mr4MissMerryChristmasHardChallenge> INSTANCE = (new ChallengeCore.Builder("mr_4_and_miss_merry_christmas_hard", TITLE, Mr4MissMerryChristmasChallenge.OBJECTIVE, ModNPCGroups.BAROQUE_WORKS.getName(), Mr4MissMerryChristmasHardChallenge::new))
/* 20 */     .setDifficulty(ChallengeDifficulty.HARD)
/* 21 */     .setDifficultyStars(7)
/* 22 */     .addArena(ArenaStyle.SIMPLE, (ChallengeArena)AlabastaDesertSimpleArena.INSTANCE, AlabastaDesertSimpleArena::getChallengerSpawnPos, AlabastaDesertSimpleArena::getEnemySpawnPos)
/* 23 */     .setTargetShowcase(new Supplier[] { (Supplier)ModEntities.MR4, (Supplier)ModEntities.MISS_MERRY_CHRISTMAS
/* 24 */       }).setTimeLimit(10)
/* 25 */     .setOrder(ModChallenges.Order.MR_4_MISS_MERRY_CHRISTMAS)
/* 26 */     .setRewards(REWARD)
/* 27 */     .build();
/*    */   
/*    */   public Mr4MissMerryChristmasHardChallenge(ChallengeCore core) {
/* 30 */     super(core);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\challenges\baroqueworks\Mr4MissMerryChristmasHardChallenge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */