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
/*    */ public class ChewHardChallenge extends Challenge {
/* 16 */   private static final String TITLE = WyRegistry.registerName("challenge.mineminenomi.chew_hard", "Chew (Hard)");
/* 17 */   public static final ResourceLocation REWARD = new ResourceLocation("mineminenomi", "rewards/chew_hard");
/*    */   
/* 19 */   public static final ChallengeCore<ChewHardChallenge> INSTANCE = (new ChallengeCore.Builder("chew_hard", TITLE, ChewChallenge.OBJECTIVE, ModNPCGroups.ARLONG_PIRATES.getName(), ChewHardChallenge::new))
/* 20 */     .setDifficulty(ChallengeDifficulty.HARD)
/* 21 */     .setDifficultyStars(1)
/* 22 */     .addArena(ArenaStyle.SIMPLE, (ChallengeArena)ArlongParkSimpleArena.INSTANCE, ArlongParkSimpleArena::getChallengerSpawnPos, ArlongParkSimpleArena::getEnemySpawnPos)
/* 23 */     .setEnemySpawns(ChewChallenge::setEnemeySpawns)
/* 24 */     .setTargetShowcase(new Supplier[] { (Supplier)ModEntities.CHEW
/* 25 */       }).setTimeLimit(10)
/* 26 */     .setOrder(ModChallenges.Order.CHEW)
/* 27 */     .setRewards(REWARD)
/* 28 */     .build();
/*    */   
/*    */   public ChewHardChallenge(ChallengeCore<ChewHardChallenge> core) {
/* 31 */     super(core);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\challenges\arlongpark\ChewHardChallenge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */