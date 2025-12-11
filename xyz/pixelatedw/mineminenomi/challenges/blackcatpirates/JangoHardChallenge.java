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
/*    */ public class JangoHardChallenge extends Challenge {
/* 16 */   private static final String TITLE = WyRegistry.registerName("challenge.mineminenomi.black_cat_pirates.jango_hard", "Jango (Hard)");
/* 17 */   public static final ResourceLocation REWARD = new ResourceLocation("mineminenomi", "rewards/jango_hard");
/*    */   
/* 19 */   public static final ChallengeCore<JangoHardChallenge> INSTANCE = (new ChallengeCore.Builder("jango_hard", TITLE, JangoChallenge.OBJECTIVE, ModNPCGroups.BLACK_CAT_PIRATES.getName(), JangoHardChallenge::new))
/* 20 */     .setDifficulty(ChallengeDifficulty.HARD)
/* 21 */     .setDifficultyStars(1)
/* 22 */     .addArena(ArenaStyle.SIMPLE, (ChallengeArena)SyrupHillSimpleArena.INSTANCE, SyrupHillSimpleArena::getChallengerSpawnPos, SyrupHillSimpleArena::getEnemySpawnPos)
/* 23 */     .setEnemySpawns(JangoChallenge::setEnemeySpawns)
/* 24 */     .setTargetShowcase(new Supplier[] { (Supplier)ModEntities.JANGO
/* 25 */       }).setTimeLimit(10)
/* 26 */     .setOrder(ModChallenges.Order.JANGO)
/* 27 */     .setRewards(REWARD)
/* 28 */     .build();
/*    */   
/*    */   public JangoHardChallenge(ChallengeCore<JangoHardChallenge> core) {
/* 31 */     super(core);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\challenges\blackcatpirates\JangoHardChallenge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */