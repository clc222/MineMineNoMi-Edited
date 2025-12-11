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
/*    */ public class CabajiHardChallenge extends Challenge {
/* 15 */   private static final String TITLE = WyRegistry.registerName("challenge.mineminenomi.buggy_pirates.cabaji_hard", "Cabaji (Hard)");
/* 16 */   public static final ResourceLocation REWARD = new ResourceLocation("mineminenomi", "rewards/cabaji_hard");
/*    */   
/* 18 */   public static final ChallengeCore<CabajiHardChallenge> INSTANCE = (new ChallengeCore.Builder("cabaji_hard", TITLE, CabajiChallenge.OBJECTIVE, ModNPCGroups.BUGGY_PIRATES.getName(), CabajiHardChallenge::new))
/* 19 */     .setDifficulty(ChallengeDifficulty.HARD)
/* 20 */     .setDifficultyStars(1)
/* 21 */     .addArena(ArenaStyle.SIMPLE, (ChallengeArena)CircusArena.INSTANCE, CircusArena::getChallengerSpawnPos, CircusArena::getEnemySpawnPos)
/* 22 */     .setEnemySpawns(CabajiChallenge::setEnemeySpawns)
/* 23 */     .setTargetShowcase(new ChallengeCore.ITargetShowcase[] { CabajiChallenge::createShowcase
/* 24 */       }).setTimeLimit(10)
/* 25 */     .setOrder(ModChallenges.Order.CABAJI)
/* 26 */     .setRewards(REWARD)
/* 27 */     .build();
/*    */   
/*    */   public CabajiHardChallenge(ChallengeCore<CabajiHardChallenge> core) {
/* 30 */     super(core);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\challenges\buggypirates\CabajiHardChallenge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */