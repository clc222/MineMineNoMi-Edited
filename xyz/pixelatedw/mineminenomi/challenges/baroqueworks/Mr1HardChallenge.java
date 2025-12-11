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
/*    */ public class Mr1HardChallenge extends Challenge {
/* 16 */   private static final String TITLE = WyRegistry.registerName("challenge.mineminenomi.baroque_works.mr_1_hard", "Mr. 1 (Hard)");
/* 17 */   public static final ResourceLocation REWARD = new ResourceLocation("mineminenomi", "rewards/mr_1_hard");
/*    */   
/* 19 */   public static final ChallengeCore<Mr1HardChallenge> INSTANCE = (new ChallengeCore.Builder("mr_1_hard", TITLE, Mr1Challenge.OBJECTIVE, ModNPCGroups.BAROQUE_WORKS.getName(), Mr1HardChallenge::new))
/* 20 */     .setDifficulty(ChallengeDifficulty.HARD)
/* 21 */     .setDifficultyStars(3)
/* 22 */     .setRewardsFactor(2.0F)
/* 23 */     .addArena(ArenaStyle.SIMPLE, (ChallengeArena)AlabastaDesertSimpleArena.INSTANCE, AlabastaDesertSimpleArena::getChallengerSpawnPos, AlabastaDesertSimpleArena::getEnemySpawnPos)
/* 24 */     .setEnemySpawns(Mr1Challenge::setEnemeySpawns)
/* 25 */     .setTargetShowcase(new Supplier[] { (Supplier)ModEntities.MR1
/* 26 */       }).setTimeLimit(10)
/* 27 */     .setOrder(ModChallenges.Order.MR_1)
/* 28 */     .setRewards(REWARD)
/* 29 */     .build();
/*    */   
/*    */   public Mr1HardChallenge(ChallengeCore core) {
/* 32 */     super(core);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\challenges\baroqueworks\Mr1HardChallenge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */