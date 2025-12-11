/*    */ package xyz.pixelatedw.mineminenomi.challenges.blackcatpirates;
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
/*    */ import xyz.pixelatedw.mineminenomi.challenges.arenas.SyrupHillSimpleArena;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.blackcatpirates.KuroEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModChallenges;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModNPCGroups;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ public class KuroChallenge extends Challenge {
/* 22 */   private static final String TITLE = WyRegistry.registerName("challenge.mineminenomi.black_cat_pirates.kuro", "Kuro");
/* 23 */   public static final String OBJECTIVE = WyRegistry.registerName("challenge.mineminenomi.black_cat_pirates.kuro.objective", "Defeat Kuro");
/* 24 */   public static final ResourceLocation REWARD = new ResourceLocation("mineminenomi", "rewards/kuro");
/*    */   
/* 26 */   public static final ChallengeCore<KuroChallenge> INSTANCE = (new ChallengeCore.Builder("kuro", TITLE, OBJECTIVE, ModNPCGroups.BLACK_CAT_PIRATES.getName(), KuroChallenge::new))
/* 27 */     .setDifficulty(ChallengeDifficulty.STANDARD)
/* 28 */     .setDifficultyStars(1)
/* 29 */     .setRewardsFactor(2.0F)
/* 30 */     .addArena(ArenaStyle.SIMPLE, (ChallengeArena)SyrupHillSimpleArena.INSTANCE, SyrupHillSimpleArena::getChallengerSpawnPos, SyrupHillSimpleArena::getEnemySpawnPos)
/* 31 */     .setEnemySpawns(KuroChallenge::setEnemeySpawns)
/* 32 */     .setTargetShowcase(new Supplier[] { (Supplier)ModEntities.KURO
/* 33 */       }).setTimeLimit(10)
/* 34 */     .setOrder(ModChallenges.Order.KURO)
/* 35 */     .setRewards(REWARD)
/* 36 */     .build();
/*    */   
/*    */   public KuroChallenge(ChallengeCore<KuroChallenge> core) {
/* 39 */     super(core);
/*    */   }
/*    */   
/*    */   public static Set<ChallengeArena.EnemySpawn> setEnemeySpawns(InProgressChallenge challenge, ChallengeArena.SpawnPosition[] spawns) {
/* 43 */     Set<ChallengeArena.EnemySpawn> set = new HashSet<>();
/*    */     
/* 45 */     KuroEntity boss = new KuroEntity(challenge);
/* 46 */     set.add(new ChallengeArena.EnemySpawn((LivingEntity)boss, spawns[0]));
/*    */     
/* 48 */     return set;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\challenges\blackcatpirates\KuroChallenge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */