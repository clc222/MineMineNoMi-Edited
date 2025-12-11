/*    */ package xyz.pixelatedw.mineminenomi.challenges.ungrouped;
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
/*    */ import xyz.pixelatedw.mineminenomi.challenges.arenas.HigumaSimpleArena;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.bandits.HigumaEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModChallenges;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModNPCGroups;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ public class HigumaChallenge extends Challenge {
/* 22 */   private static final String TITLE = WyRegistry.registerName("challenge.mineminenomi.higuma", "Higuma");
/* 23 */   public static final String OBJECTIVE = WyRegistry.registerName("challenge.mineminenomi.higuma.objective", "Defeat Higuma");
/* 24 */   public static final ResourceLocation REWARD = new ResourceLocation("mineminenomi", "rewards/higuma");
/*    */   
/* 26 */   public static final ChallengeCore<HigumaChallenge> INSTANCE = (new ChallengeCore.Builder("higuma", TITLE, OBJECTIVE, ModNPCGroups.UNGROUPED, HigumaChallenge::new))
/* 27 */     .setDifficulty(ChallengeDifficulty.STANDARD)
/* 28 */     .setDifficultyStars(1)
/* 29 */     .addArena(ArenaStyle.SIMPLE, (ChallengeArena)HigumaSimpleArena.INSTANCE, HigumaSimpleArena::getChallengerSpawnPos, HigumaSimpleArena::getEnemySpawnPos)
/* 30 */     .setEnemySpawns(HigumaChallenge::setEnemeySpawns)
/* 31 */     .setTargetShowcase(new Supplier[] { (Supplier)ModEntities.HIGUMA
/* 32 */       }).setTimeLimit(10)
/* 33 */     .setOrder(ModChallenges.Order.HIGUMA)
/* 34 */     .setRewards(REWARD)
/* 35 */     .build();
/*    */   
/*    */   public HigumaChallenge(ChallengeCore<HigumaChallenge> core) {
/* 38 */     super(core);
/*    */   }
/*    */   
/*    */   public static Set<ChallengeArena.EnemySpawn> setEnemeySpawns(InProgressChallenge challenge, ChallengeArena.SpawnPosition[] spawns) {
/* 42 */     Set<ChallengeArena.EnemySpawn> set = new HashSet<>();
/*    */     
/* 44 */     HigumaEntity boss = new HigumaEntity(challenge);
/* 45 */     set.add(new ChallengeArena.EnemySpawn((LivingEntity)boss, spawns[0]));
/*    */     
/* 47 */     return set;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\challenge\\ungrouped\HigumaChallenge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */