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
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.baroqueworks.MissValentineEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.baroqueworks.Mr5Entity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModChallenges;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModNPCGroups;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ public class Mr5MissValentineChallenge
/*    */   extends Challenge {
/* 24 */   private static final String TITLE = WyRegistry.registerName("challenge.mineminenomi.baroque_works.mr_5_and_miss_valentine", "Mr. 5 & Miss Valentine");
/* 25 */   public static final String OBJECTIVE = WyRegistry.registerName("challenge.mineminenomi.baroque_works.mr_5_and_miss_valentine.objective", "Defeat both Mr. 5 and Miss Valentine");
/* 26 */   public static final ResourceLocation REWARD = new ResourceLocation("mineminenomi", "rewards/mr_5_and_miss_valentine");
/*    */   
/* 28 */   public static final ChallengeCore<Mr5MissValentineChallenge> INSTANCE = (new ChallengeCore.Builder("mr_5_and_miss_valentine", TITLE, OBJECTIVE, ModNPCGroups.BAROQUE_WORKS.getName(), Mr5MissValentineChallenge::new))
/* 29 */     .setDifficulty(ChallengeDifficulty.STANDARD)
/* 30 */     .setDifficultyStars(2)
/* 31 */     .addArena(ArenaStyle.SIMPLE, (ChallengeArena)JungleClearingSimpleArena.INSTANCE, JungleClearingSimpleArena::getChallengerSpawnPos, JungleClearingSimpleArena::getEnemySpawnPos)
/* 32 */     .setEnemySpawns(Mr5MissValentineChallenge::setEnemeySpawns)
/* 33 */     .setTargetShowcase(new Supplier[] { (Supplier)ModEntities.MR5, (Supplier)ModEntities.MISS_VALENTINE
/* 34 */       }).setTimeLimit(10)
/* 35 */     .setOrder(ModChallenges.Order.MR_5_MISS_VALENTINE)
/* 36 */     .setRewards(REWARD)
/* 37 */     .build();
/*    */   
/*    */   public Mr5MissValentineChallenge(ChallengeCore core) {
/* 40 */     super(core);
/*    */   }
/*    */   
/*    */   public static Set<ChallengeArena.EnemySpawn> setEnemeySpawns(InProgressChallenge challenge, ChallengeArena.SpawnPosition[] spawns) {
/* 44 */     Set<ChallengeArena.EnemySpawn> set = new HashSet<>();
/*    */     
/* 46 */     Mr5Entity mr5Boss = new Mr5Entity(challenge);
/* 47 */     set.add(new ChallengeArena.EnemySpawn((LivingEntity)mr5Boss, spawns[0]));
/*    */     
/* 49 */     MissValentineEntity missValBoss = new MissValentineEntity(challenge);
/* 50 */     set.add(new ChallengeArena.EnemySpawn((LivingEntity)missValBoss, spawns[1]));
/*    */     
/* 52 */     return set;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\challenges\baroqueworks\Mr5MissValentineChallenge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */