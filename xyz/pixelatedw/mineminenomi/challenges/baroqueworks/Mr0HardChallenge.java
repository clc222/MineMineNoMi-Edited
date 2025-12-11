/*    */ package xyz.pixelatedw.mineminenomi.challenges.baroqueworks;
/*    */ 
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ArenaStyle;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.Challenge;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeArena;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeDifficulty;
/*    */ import xyz.pixelatedw.mineminenomi.challenges.arenas.AlabastaDesertSimpleArena;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModChallenges;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModNPCGroups;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ public class Mr0HardChallenge extends Challenge {
/* 15 */   private static final String TITLE = WyRegistry.registerName("challenge.mineminenomi.baroque_works.mr_0_hard", "Mr. 0 (Hard)");
/* 16 */   public static final ResourceLocation REWARD = new ResourceLocation("mineminenomi", "rewards/mr_0_hard");
/*    */   
/* 18 */   public static final ChallengeCore<Mr0HardChallenge> INSTANCE = (new ChallengeCore.Builder("mr_0_hard", TITLE, Mr0Challenge.OBJECTIVE, ModNPCGroups.BAROQUE_WORKS.getName(), Mr0HardChallenge::new))
/* 19 */     .setDifficulty(ChallengeDifficulty.HARD)
/* 20 */     .setDifficultyStars(3)
/* 21 */     .setRewardsFactor(3.0F)
/* 22 */     .addArena(ArenaStyle.SIMPLE, (ChallengeArena)AlabastaDesertSimpleArena.INSTANCE, AlabastaDesertSimpleArena::getChallengerSpawnPos, AlabastaDesertSimpleArena::getEnemySpawnPos)
/* 23 */     .setEnemySpawns(Mr0Challenge::setEnemeySpawns)
/* 24 */     .setTargetShowcase(new ChallengeCore.ITargetShowcase[] { Mr0Challenge::createMr0Showcase
/* 25 */       }).setTimeLimit(10)
/* 26 */     .setOrder(ModChallenges.Order.MR_0)
/* 27 */     .setRewards(REWARD)
/* 28 */     .build();
/*    */   
/*    */   public Mr0HardChallenge(ChallengeCore core) {
/* 31 */     super(core);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\challenges\baroqueworks\Mr0HardChallenge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */