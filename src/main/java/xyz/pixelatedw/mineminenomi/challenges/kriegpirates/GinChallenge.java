/*    */ package xyz.pixelatedw.mineminenomi.challenges.kriegpirates;
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
/*    */ import xyz.pixelatedw.mineminenomi.challenges.arenas.BaratieSimpleArena;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.kriegpirates.GinEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModChallenges;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModNPCGroups;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ public class GinChallenge
/*    */   extends Challenge {
/* 23 */   private static final String TITLE = WyRegistry.registerName("challenge.mineminenomi.krieg_pirates.gin", "Gin");
/* 24 */   public static final String OBJECTIVE = WyRegistry.registerName("challenge.mineminenomi.krieg_pirates.gin.objective", "Defeat Gin");
/* 25 */   public static final ResourceLocation REWARD = new ResourceLocation("mineminenomi", "rewards/gin");
/*    */   
/* 27 */   public static final ChallengeCore<GinChallenge> INSTANCE = (new ChallengeCore.Builder("gin", TITLE, OBJECTIVE, ModNPCGroups.KRIEG_PIRATES.getName(), GinChallenge::new))
/* 28 */     .setDifficulty(ChallengeDifficulty.STANDARD)
/* 29 */     .setDifficultyStars(1)
/* 30 */     .addArena(ArenaStyle.SIMPLE, (ChallengeArena)BaratieSimpleArena.INSTANCE, BaratieSimpleArena::getChallengerSpawnPos, BaratieSimpleArena::getEnemySpawnPos)
/* 31 */     .setEnemySpawns(GinChallenge::setEnemeySpawns)
/* 32 */     .setTargetShowcase(new Supplier[] { (Supplier)ModEntities.GIN
/* 33 */       }).setTimeLimit(10)
/* 34 */     .setOrder(ModChallenges.Order.GIN)
/* 35 */     .setRewards(REWARD)
/* 36 */     .build();
/*    */   
/*    */   public GinChallenge(ChallengeCore<GinChallenge> core) {
/* 39 */     super(core);
/*    */   }
/*    */   
/*    */   public static Set<ChallengeArena.EnemySpawn> setEnemeySpawns(InProgressChallenge challenge, ChallengeArena.SpawnPosition[] spawns) {
/* 43 */     Set<ChallengeArena.EnemySpawn> set = new HashSet<>();
/*    */     
/* 45 */     GinEntity boss = new GinEntity(challenge);
/* 46 */     set.add(new ChallengeArena.EnemySpawn((LivingEntity)boss, spawns[0]));
/*    */     
/* 48 */     return set;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\challenges\kriegpirates\GinChallenge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */