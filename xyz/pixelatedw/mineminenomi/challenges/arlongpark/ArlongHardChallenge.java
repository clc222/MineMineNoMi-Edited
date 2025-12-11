/*    */ package xyz.pixelatedw.mineminenomi.challenges.arlongpark;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ArenaStyle;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.Challenge;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeArena;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeDifficulty;
/*    */ import xyz.pixelatedw.mineminenomi.challenges.arenas.ArlongParkSimpleArena;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModChallenges;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModNPCGroups;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ public class ArlongHardChallenge extends Challenge {
/* 16 */   private static final String TITLE = WyRegistry.registerName("challenge.mineminenomi.arlong_hard", "Arlong (Hard)");
/* 17 */   public static final ResourceLocation REWARD = new ResourceLocation("mineminenomi", "rewards/arlong_hard");
/*    */   
/* 19 */   public static final ChallengeCore<ArlongHardChallenge> INSTANCE = (new ChallengeCore.Builder("arlong_hard", TITLE, ArlongChallenge.OBJECTIVE, ModNPCGroups.ARLONG_PIRATES.getName(), ArlongHardChallenge::new))
/* 20 */     .setDifficulty(ChallengeDifficulty.HARD)
/* 21 */     .setDifficultyStars(1)
/* 22 */     .setRewardsFactor(2.0F)
/* 23 */     .addArena(ArenaStyle.SIMPLE, (ChallengeArena)ArlongParkSimpleArena.INSTANCE, ArlongParkSimpleArena::getChallengerSpawnPos, ArlongParkSimpleArena::getEnemySpawnPos)
/* 24 */     .setEnemySpawns(ArlongChallenge::setEnemeySpawns)
/* 25 */     .setTargetShowcase(new Supplier[] { (Supplier)ModEntities.ARLONG
/* 26 */       }).setTimeLimit(10)
/* 27 */     .setOrder(ModChallenges.Order.ARLONG)
/* 28 */     .setRewards(REWARD)
/* 29 */     .build();
/*    */   
/*    */   public ArlongHardChallenge(ChallengeCore<ArlongHardChallenge> core) {
/* 32 */     super(core);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\challenges\arlongpark\ArlongHardChallenge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */