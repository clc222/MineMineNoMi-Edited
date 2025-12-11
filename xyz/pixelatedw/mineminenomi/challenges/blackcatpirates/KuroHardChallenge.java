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
/*    */ public class KuroHardChallenge extends Challenge {
/* 16 */   private static final String TITLE = WyRegistry.registerName("challenge.mineminenomi.black_cat_pirates.kuro_hard", "Kuro (Hard)");
/* 17 */   public static final ResourceLocation REWARD = new ResourceLocation("mineminenomi", "rewards/kuro_hard");
/*    */   
/* 19 */   public static final ChallengeCore<KuroHardChallenge> INSTANCE = (new ChallengeCore.Builder("kuro_hard", TITLE, KuroChallenge.OBJECTIVE, ModNPCGroups.BLACK_CAT_PIRATES.getName(), KuroHardChallenge::new))
/* 20 */     .setDifficulty(ChallengeDifficulty.HARD)
/* 21 */     .setDifficultyStars(1)
/* 22 */     .setRewardsFactor(2.0F)
/* 23 */     .addArena(ArenaStyle.SIMPLE, (ChallengeArena)SyrupHillSimpleArena.INSTANCE, SyrupHillSimpleArena::getChallengerSpawnPos, SyrupHillSimpleArena::getEnemySpawnPos)
/* 24 */     .setEnemySpawns(KuroChallenge::setEnemeySpawns)
/* 25 */     .setTargetShowcase(new Supplier[] { (Supplier)ModEntities.KURO
/* 26 */       }).setTimeLimit(10)
/* 27 */     .setOrder(ModChallenges.Order.KURO)
/* 28 */     .setRewards(REWARD)
/* 29 */     .build();
/*    */   
/*    */   public KuroHardChallenge(ChallengeCore<KuroHardChallenge> core) {
/* 32 */     super(core);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\challenges\blackcatpirates\KuroHardChallenge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */