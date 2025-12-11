/*    */ package xyz.pixelatedw.mineminenomi.challenges.blackcatpirates;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ArenaStyle;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.Challenge;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeArena;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeDifficulty;
/*    */ import xyz.pixelatedw.mineminenomi.challenges.arenas.SyrupHillSimpleArena;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModChallenges;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModNPCGroups;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ public class NyanbanBrothersHardChallenge extends Challenge {
/* 16 */   private static final String TITLE = WyRegistry.registerName("challenge.mineminenomi.black_cat_pirates.nyanban_brothers_hard", "Nyanban Brothers (Hard)");
/* 17 */   public static final ResourceLocation REWARD = new ResourceLocation("mineminenomi", "rewards/nyanban_brothers_hard");
/*    */   
/* 19 */   public static final ChallengeCore<NyanbanBrothersHardChallenge> INSTANCE = (new ChallengeCore.Builder("nyanban_brothers_hard", TITLE, NyanbanBrothersChallenge.OBJECTIVE, ModNPCGroups.BLACK_CAT_PIRATES.getName(), NyanbanBrothersHardChallenge::new))
/* 20 */     .setDifficulty(ChallengeDifficulty.HARD)
/* 21 */     .setDifficultyStars(1)
/* 22 */     .addArena(ArenaStyle.SIMPLE, (ChallengeArena)SyrupHillSimpleArena.INSTANCE, SyrupHillSimpleArena::getChallengerSpawnPos, SyrupHillSimpleArena::getEnemySpawnPos)
/* 23 */     .setEnemySpawns(NyanbanBrothersChallenge::setEnemeySpawns)
/* 24 */     .setTargetShowcase(new Supplier[] { (Supplier)ModEntities.SHAM, (Supplier)ModEntities.BUCHI
/* 25 */       }).setTimeLimit(10)
/* 26 */     .setOrder(ModChallenges.Order.SHAM_BUCHI)
/* 27 */     .setRewards(REWARD)
/* 28 */     .build();
/*    */   
/*    */   public NyanbanBrothersHardChallenge(ChallengeCore<NyanbanBrothersHardChallenge> core) {
/* 31 */     super(core);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\challenges\blackcatpirates\NyanbanBrothersHardChallenge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */