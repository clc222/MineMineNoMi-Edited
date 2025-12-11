/*    */ package xyz.pixelatedw.mineminenomi.challenges.shellstown;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ArenaStyle;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.Challenge;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeArena;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeDifficulty;
/*    */ import xyz.pixelatedw.mineminenomi.challenges.arenas.MarineSmallBaseSimpleArena;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModChallenges;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModNPCGroups;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ public class MorganHardChallenge extends Challenge {
/* 16 */   private static final String TITLE = WyRegistry.registerName("challenge.mineminenomi.marines.morgan_hard", "Morgan (Hard)");
/* 17 */   public static final ResourceLocation REWARD = new ResourceLocation("mineminenomi", "rewards/morgan_hard");
/*    */   
/* 19 */   public static final ChallengeCore<MorganHardChallenge> INSTANCE = (new ChallengeCore.Builder("morgan_hard", TITLE, MorganChallenge.OBJECTIVE, ModNPCGroups.MARINES, MorganHardChallenge::new))
/* 20 */     .setDifficulty(ChallengeDifficulty.HARD)
/* 21 */     .setDifficultyStars(1)
/* 22 */     .addArena(ArenaStyle.SIMPLE, (ChallengeArena)MarineSmallBaseSimpleArena.INSTANCE, MarineSmallBaseSimpleArena::getChallengerSpawnPos, MarineSmallBaseSimpleArena::getEnemySpawnPos)
/* 23 */     .setEnemySpawns(MorganChallenge::setEnemeySpawns)
/* 24 */     .setTargetShowcase(new Supplier[] { (Supplier)ModEntities.MORGAN
/* 25 */       }).setTimeLimit(10)
/* 26 */     .setOrder(ModChallenges.Order.MORGAN)
/* 27 */     .setRewards(REWARD)
/* 28 */     .build();
/*    */   
/*    */   public MorganHardChallenge(ChallengeCore core) {
/* 31 */     super(core);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\challenges\shellstown\MorganHardChallenge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */