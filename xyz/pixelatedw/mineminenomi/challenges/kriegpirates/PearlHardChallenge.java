/*    */ package xyz.pixelatedw.mineminenomi.challenges.kriegpirates;
/*    */ 
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ArenaStyle;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.Challenge;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeArena;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeDifficulty;
/*    */ import xyz.pixelatedw.mineminenomi.challenges.arenas.BaratieSimpleArena;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModChallenges;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModNPCGroups;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ public class PearlHardChallenge extends Challenge {
/* 15 */   private static final String TITLE = WyRegistry.registerName("challenge.mineminenomi.krieg_pirates.pearl_hard", "Pearl (Hard)");
/* 16 */   public static final ResourceLocation REWARD = new ResourceLocation("mineminenomi", "rewards/pearl_hard");
/*    */   
/* 18 */   public static final ChallengeCore<PearlHardChallenge> INSTANCE = (new ChallengeCore.Builder("pearl_hard", TITLE, PearlChallenge.OBJECTIVE, ModNPCGroups.KRIEG_PIRATES.getName(), PearlHardChallenge::new))
/* 19 */     .setDifficulty(ChallengeDifficulty.HARD)
/* 20 */     .setDifficultyStars(1)
/* 21 */     .addArena(ArenaStyle.SIMPLE, (ChallengeArena)BaratieSimpleArena.INSTANCE, BaratieSimpleArena::getChallengerSpawnPos, BaratieSimpleArena::getEnemySpawnPos)
/* 22 */     .setEnemySpawns(PearlChallenge::setEnemeySpawns)
/* 23 */     .setTargetShowcase(new ChallengeCore.ITargetShowcase[] { PearlChallenge::createPearlShowcase
/* 24 */       }).setTimeLimit(10)
/* 25 */     .setOrder(ModChallenges.Order.PEARL)
/* 26 */     .setRewards(REWARD)
/* 27 */     .build();
/*    */   
/*    */   public PearlHardChallenge(ChallengeCore<PearlHardChallenge> core) {
/* 30 */     super(core);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\challenges\kriegpirates\PearlHardChallenge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */