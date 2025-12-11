/*    */ package xyz.pixelatedw.mineminenomi.challenges.baroqueworks;
/*    */ 
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ArenaStyle;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.Challenge;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeArena;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeDifficulty;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.InProgressChallenge;
/*    */ import xyz.pixelatedw.mineminenomi.challenges.arenas.AlabastaDesertSimpleArena;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.baroqueworks.MissMerryChristmasEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.baroqueworks.Mr4Entity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModChallenges;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModNPCGroups;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ public class Mr4MissMerryChristmasChallenge
/*    */   extends Challenge {
/* 24 */   private static final String TITLE = WyRegistry.registerName("challenge.mineminenomi.baroque_works.mr_4_and_miss_merry_christmas", "Mr. 4 & Miss Merry Christmas");
/* 25 */   public static final String OBJECTIVE = WyRegistry.registerName("challenge.mineminenomi.baroque_works.mr_4_and_miss_merry_christmas.objective", "Defeat both Mr. 4 and Miss Merry Christmas");
/* 26 */   public static final ResourceLocation REWARD = new ResourceLocation("mineminenomi", "rewards/mr_4_and_miss_merry_christmas");
/*    */   
/* 28 */   public static final ChallengeCore<Mr4MissMerryChristmasChallenge> INSTANCE = (new ChallengeCore.Builder("mr_4_and_miss_merry_christmas", TITLE, OBJECTIVE, ModNPCGroups.BAROQUE_WORKS.getName(), Mr4MissMerryChristmasChallenge::new))
/* 29 */     .setDifficulty(ChallengeDifficulty.STANDARD)
/* 30 */     .setDifficultyStars(3)
/* 31 */     .addArena(ArenaStyle.SIMPLE, (ChallengeArena)AlabastaDesertSimpleArena.INSTANCE, AlabastaDesertSimpleArena::getChallengerSpawnPos, AlabastaDesertSimpleArena::getEnemySpawnPos)
/* 32 */     .setTargetShowcase(new Supplier[] { (Supplier)ModEntities.MR4, (Supplier)ModEntities.MISS_MERRY_CHRISTMAS
/* 33 */       }).setTimeLimit(10)
/* 34 */     .setOrder(ModChallenges.Order.MR_4_MISS_MERRY_CHRISTMAS)
/* 35 */     .setRewards(REWARD)
/* 36 */     .build();
/*    */   
/*    */   public Mr4MissMerryChristmasChallenge(ChallengeCore core) {
/* 39 */     super(core);
/*    */   }
/*    */ 
/*    */   
/*    */   public static Set<ChallengeArena.SpawnPosition> getEnemySpawnPos(InProgressChallenge challenge) {
/* 44 */     Set<ChallengeArena.SpawnPosition> set = new HashSet<>();
/*    */     
/* 46 */     BlockPos mr5Pos = new BlockPos(challenge.getArenaPos().func_177958_n() - 15, challenge.getArenaPos().func_177956_o() - 30, challenge.getArenaPos().func_177952_p() - 15);
/* 47 */     Mr4Entity mr5Boss = new Mr4Entity(challenge);
/* 48 */     mr5Boss.field_70177_z = 140.0F;
/*    */ 
/*    */     
/* 51 */     BlockPos missValPos = new BlockPos(challenge.getArenaPos().func_177958_n() - 14, challenge.getArenaPos().func_177956_o() - 30, challenge.getArenaPos().func_177952_p() - 15);
/* 52 */     MissMerryChristmasEntity missValBoss = new MissMerryChristmasEntity(challenge);
/* 53 */     missValBoss.field_70177_z = 140.0F;
/*    */ 
/*    */     
/* 56 */     return set;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\challenges\baroqueworks\Mr4MissMerryChristmasChallenge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */