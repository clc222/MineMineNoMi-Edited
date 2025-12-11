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
/*    */ public class DonKriegHardChallenge extends Challenge {
/* 15 */   private static final String TITLE = WyRegistry.registerName("challenge.mineminenomi.krieg_pirates.don_krieg_hard", "Don Krieg (Hard)");
/* 16 */   public static final ResourceLocation REWARD = new ResourceLocation("mineminenomi", "rewards/don_krieg_hard");
/*    */   
/* 18 */   public static final ChallengeCore<DonKriegHardChallenge> INSTANCE = (new ChallengeCore.Builder("don_krieg_hard", TITLE, DonKriegChallenge.OBJECTIVE, ModNPCGroups.KRIEG_PIRATES.getName(), DonKriegHardChallenge::new))
/* 19 */     .setDifficulty(ChallengeDifficulty.HARD)
/* 20 */     .setDifficultyStars(1)
/* 21 */     .setRewardsFactor(2.0F)
/* 22 */     .addArena(ArenaStyle.SIMPLE, (ChallengeArena)BaratieSimpleArena.INSTANCE, BaratieSimpleArena::getChallengerSpawnPos, BaratieSimpleArena::getEnemySpawnPos)
/* 23 */     .setEnemySpawns(DonKriegChallenge::setEnemeySpawns)
/* 24 */     .setTargetShowcase(new ChallengeCore.ITargetShowcase[] { DonKriegChallenge::createKriegShowcase
/* 25 */       }).setTimeLimit(10)
/* 26 */     .setOrder(ModChallenges.Order.DON_KRIEG)
/* 27 */     .setRewards(REWARD)
/* 28 */     .build();
/*    */   
/*    */   public DonKriegHardChallenge(ChallengeCore<DonKriegHardChallenge> core) {
/* 31 */     super(core);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\challenges\kriegpirates\DonKriegHardChallenge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */