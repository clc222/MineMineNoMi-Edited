/*    */ package xyz.pixelatedw.mineminenomi.challenges.baroqueworks;
/*    */ 
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ArenaStyle;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.Challenge;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeArena;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeDifficulty;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.InProgressChallenge;
/*    */ import xyz.pixelatedw.mineminenomi.challenges.arenas.JungleClearingSimpleArena;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.baroqueworks.Mr3Entity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModChallenges;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModNPCGroups;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ public class Mr3Challenge extends Challenge {
/* 22 */   private static final String TITLE = WyRegistry.registerName("challenge.mineminenomi.baroque_works.mr_3", "Mr. 3");
/* 23 */   public static final String OBJECTIVE = WyRegistry.registerName("challenge.mineminenomi.baroque_works.mr_3.objective", "Defeat Mr. 3");
/* 24 */   public static final ResourceLocation REWARD = new ResourceLocation("mineminenomi", "rewards/mr_3");
/*    */   
/* 26 */   public static final ChallengeCore<Mr3Challenge> INSTANCE = (new ChallengeCore.Builder("mr_3", TITLE, OBJECTIVE, ModNPCGroups.BAROQUE_WORKS.getName(), Mr3Challenge::new))
/* 27 */     .setDifficulty(ChallengeDifficulty.STANDARD)
/* 28 */     .setDifficultyStars(2)
/* 29 */     .setRewardsFactor(2.0F)
/* 30 */     .addArena(ArenaStyle.SIMPLE, (ChallengeArena)JungleClearingSimpleArena.INSTANCE, JungleClearingSimpleArena::getChallengerSpawnPos, JungleClearingSimpleArena::getEnemySpawnPos)
/* 31 */     .setEnemySpawns(Mr3Challenge::setEnemeySpawns)
/* 32 */     .setTargetShowcase(new Supplier[] { (Supplier)ModEntities.MR3
/* 33 */       }).setTimeLimit(10)
/* 34 */     .setOrder(ModChallenges.Order.MR_3)
/* 35 */     .setRewards(REWARD)
/* 36 */     .build();
/*    */   
/*    */   public Mr3Challenge(ChallengeCore core) {
/* 39 */     super(core);
/*    */   }
/*    */   
/*    */   public static Set<ChallengeArena.EnemySpawn> setEnemeySpawns(InProgressChallenge challenge, ChallengeArena.SpawnPosition[] spawns) {
/* 43 */     Set<ChallengeArena.EnemySpawn> set = new HashSet<>();
/*    */     
/* 45 */     Mr3Entity boss = new Mr3Entity(challenge);
/* 46 */     set.add(new ChallengeArena.EnemySpawn((LivingEntity)boss, spawns[0]));
/*    */     
/* 48 */     return set;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\challenges\baroqueworks\Mr3Challenge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */