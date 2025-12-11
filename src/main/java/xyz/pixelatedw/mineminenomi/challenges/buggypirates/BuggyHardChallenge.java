/*    */ package xyz.pixelatedw.mineminenomi.challenges.buggypirates;
/*    */ 
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ArenaStyle;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.Challenge;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeArena;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeDifficulty;
/*    */ import xyz.pixelatedw.mineminenomi.challenges.arenas.CircusArena;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModChallenges;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModNPCGroups;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ public class BuggyHardChallenge extends Challenge {
/* 15 */   private static final String TITLE = WyRegistry.registerName("challenge.mineminenomi.buggy_pirates.buggy_hard", "Buggy (Hard)");
/* 16 */   public static final ResourceLocation REWARD = new ResourceLocation("mineminenomi", "rewards/buggy_hard");
/*    */   
/* 18 */   public static final ChallengeCore<BuggyHardChallenge> INSTANCE = (new ChallengeCore.Builder("buggy_hard", TITLE, BuggyChallenge.OBJECTIVE, ModNPCGroups.BUGGY_PIRATES.getName(), BuggyHardChallenge::new))
/* 19 */     .setDifficulty(ChallengeDifficulty.HARD)
/* 20 */     .setDifficultyStars(1)
/* 21 */     .setRewardsFactor(2.0F)
/* 22 */     .addArena(ArenaStyle.SIMPLE, (ChallengeArena)CircusArena.INSTANCE, CircusArena::getChallengerSpawnPos, CircusArena::getEnemySpawnPos)
/* 23 */     .setEnemySpawns(BuggyChallenge::setEnemeySpawns)
/* 24 */     .setTargetShowcase(new ChallengeCore.ITargetShowcase[] { BuggyChallenge::createShowcase
/* 25 */       }).setTimeLimit(10)
/* 26 */     .setOrder(ModChallenges.Order.BUGGY)
/* 27 */     .setRewards(REWARD)
/* 28 */     .build();
/*    */   
/*    */   public BuggyHardChallenge(ChallengeCore<BuggyHardChallenge> core) {
/* 31 */     super(core);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\challenges\buggypirates\BuggyHardChallenge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */