/*    */ package xyz.pixelatedw.mineminenomi.challenges.arlongpark;
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
/*    */ import xyz.pixelatedw.mineminenomi.challenges.arenas.ArlongParkSimpleArena;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.arlongpirates.ChewEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModChallenges;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModNPCGroups;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ public class ChewChallenge
/*    */   extends Challenge {
/* 23 */   private static final String TITLE = WyRegistry.registerName("challenge.mineminenomi.chew", "Chew");
/* 24 */   public static final String OBJECTIVE = WyRegistry.registerName("challenge.mineminenomi.chew.objective", "Defeat Chew");
/* 25 */   public static final ResourceLocation REWARD = new ResourceLocation("mineminenomi", "rewards/chew");
/*    */   
/* 27 */   public static final ChallengeCore<ChewChallenge> INSTANCE = (new ChallengeCore.Builder("chew", TITLE, OBJECTIVE, ModNPCGroups.ARLONG_PIRATES.getName(), ChewChallenge::new))
/* 28 */     .setDifficulty(ChallengeDifficulty.STANDARD)
/* 29 */     .setDifficultyStars(1)
/* 30 */     .addArena(ArenaStyle.SIMPLE, (ChallengeArena)ArlongParkSimpleArena.INSTANCE, ArlongParkSimpleArena::getChallengerSpawnPos, ArlongParkSimpleArena::getEnemySpawnPos)
/* 31 */     .setEnemySpawns(ChewChallenge::setEnemeySpawns)
/* 32 */     .setTargetShowcase(new Supplier[] { (Supplier)ModEntities.CHEW
/* 33 */       }).setTimeLimit(10)
/* 34 */     .setOrder(ModChallenges.Order.CHEW)
/* 35 */     .setRewards(REWARD)
/* 36 */     .build();
/*    */   
/*    */   public ChewChallenge(ChallengeCore<ChewChallenge> core) {
/* 39 */     super(core);
/*    */   }
/*    */   
/*    */   public static Set<ChallengeArena.EnemySpawn> setEnemeySpawns(InProgressChallenge challenge, ChallengeArena.SpawnPosition[] spawns) {
/* 43 */     Set<ChallengeArena.EnemySpawn> set = new HashSet<>();
/*    */     
/* 45 */     ChewEntity boss = new ChewEntity(challenge);
/* 46 */     set.add(new ChallengeArena.EnemySpawn((LivingEntity)boss, spawns[0]));
/*    */     
/* 48 */     return set;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\challenges\arlongpark\ChewChallenge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */