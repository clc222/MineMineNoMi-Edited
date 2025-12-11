/*    */ package xyz.pixelatedw.mineminenomi.challenges.baroqueworks;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ArenaStyle;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.Challenge;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeArena;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeDifficulty;
/*    */ import xyz.pixelatedw.mineminenomi.challenges.arenas.JungleClearingSimpleArena;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModChallenges;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModNPCGroups;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ public class Mr5MissValentineHardChallenge extends Challenge {
/* 16 */   private static final String TITLE = WyRegistry.registerName("challenge.mineminenomi.baroque_works.mr_5_and_miss_valentine_hard", "Mr. 5 & Miss Valentine (Hard)");
/* 17 */   public static final ResourceLocation REWARD = new ResourceLocation("mineminenomi", "rewards/mr_5_and_miss_valentine_hard");
/*    */   
/* 19 */   public static final ChallengeCore<Mr5MissValentineHardChallenge> INSTANCE = (new ChallengeCore.Builder("mr_5_and_miss_valentine_hard", TITLE, Mr5MissValentineChallenge.OBJECTIVE, ModNPCGroups.BAROQUE_WORKS.getName(), Mr5MissValentineHardChallenge::new))
/* 20 */     .setDifficulty(ChallengeDifficulty.HARD)
/* 21 */     .setDifficultyStars(2)
/* 22 */     .addArena(ArenaStyle.SIMPLE, (ChallengeArena)JungleClearingSimpleArena.INSTANCE, JungleClearingSimpleArena::getChallengerSpawnPos, JungleClearingSimpleArena::getEnemySpawnPos)
/* 23 */     .setEnemySpawns(Mr5MissValentineChallenge::setEnemeySpawns)
/* 24 */     .setTargetShowcase(new Supplier[] { (Supplier)ModEntities.MR5, (Supplier)ModEntities.MISS_VALENTINE
/* 25 */       }).setTimeLimit(10)
/* 26 */     .setOrder(ModChallenges.Order.MR_5_MISS_VALENTINE)
/* 27 */     .setRewards(REWARD)
/* 28 */     .build();
/*    */   
/*    */   public Mr5MissValentineHardChallenge(ChallengeCore core) {
/* 31 */     super(core);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\challenges\baroqueworks\Mr5MissValentineHardChallenge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */