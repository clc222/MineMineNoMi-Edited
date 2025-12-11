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
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.blackcatpirates.BuchiEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.blackcatpirates.ShamEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModChallenges;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModNPCGroups;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ public class NyanbanBrothersChallenge
/*    */   extends Challenge {
/* 24 */   private static final String TITLE = WyRegistry.registerName("challenge.mineminenomi.black_cat_pirates.nyanban_brothers", "Nyanban Brothers");
/* 25 */   public static final String OBJECTIVE = WyRegistry.registerName("challenge.mineminenomi.black_cat_pirates.nyanban_brothers.objective", "Defeat Sham and Buchi");
/* 26 */   public static final ResourceLocation REWARD = new ResourceLocation("mineminenomi", "rewards/nyanban_brothers");
/*    */   
/* 28 */   public static final ChallengeCore<NyanbanBrothersChallenge> INSTANCE = (new ChallengeCore.Builder("nyanban_brothers", TITLE, OBJECTIVE, ModNPCGroups.BLACK_CAT_PIRATES.getName(), NyanbanBrothersChallenge::new))
/* 29 */     .setDifficulty(ChallengeDifficulty.STANDARD)
/* 30 */     .setDifficultyStars(1)
/* 31 */     .addArena(ArenaStyle.SIMPLE, (ChallengeArena)SyrupHillSimpleArena.INSTANCE, SyrupHillSimpleArena::getChallengerSpawnPos, SyrupHillSimpleArena::getEnemySpawnPos)
/* 32 */     .setEnemySpawns(NyanbanBrothersChallenge::setEnemeySpawns)
/* 33 */     .setTargetShowcase(new Supplier[] { (Supplier)ModEntities.SHAM, (Supplier)ModEntities.BUCHI
/* 34 */       }).setTimeLimit(10)
/* 35 */     .setOrder(ModChallenges.Order.SHAM_BUCHI)
/* 36 */     .setRewards(REWARD)
/* 37 */     .build();
/*    */   
/*    */   public NyanbanBrothersChallenge(ChallengeCore<NyanbanBrothersChallenge> core) {
/* 40 */     super(core);
/*    */   }
/*    */   
/*    */   public static Set<ChallengeArena.EnemySpawn> setEnemeySpawns(InProgressChallenge challenge, ChallengeArena.SpawnPosition[] spawns) {
/* 44 */     Set<ChallengeArena.EnemySpawn> set = new HashSet<>();
/*    */     
/* 46 */     ShamEntity sham = new ShamEntity(challenge);
/* 47 */     set.add(new ChallengeArena.EnemySpawn((LivingEntity)sham, spawns[0]));
/*    */     
/* 49 */     BuchiEntity buchi = new BuchiEntity(challenge);
/* 50 */     set.add(new ChallengeArena.EnemySpawn((LivingEntity)buchi, spawns[1]));
/*    */     
/* 52 */     return set;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\challenges\blackcatpirates\NyanbanBrothersChallenge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */