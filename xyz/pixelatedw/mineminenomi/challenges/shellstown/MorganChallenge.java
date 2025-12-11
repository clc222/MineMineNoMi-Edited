/*    */ package xyz.pixelatedw.mineminenomi.challenges.shellstown;
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
/*    */ import xyz.pixelatedw.mineminenomi.challenges.arenas.MarineSmallBaseSimpleArena;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.marines.MorganEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModChallenges;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModNPCGroups;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ public class MorganChallenge
/*    */   extends Challenge {
/* 23 */   private static final String TITLE = WyRegistry.registerName("challenge.mineminenomi.marines.morgan", "Morgan");
/* 24 */   public static final String OBJECTIVE = WyRegistry.registerName("challenge.mineminenomi.marines.morgan.objective", "Defeat Morgan");
/* 25 */   public static final ResourceLocation REWARD = new ResourceLocation("mineminenomi", "rewards/morgan");
/*    */   
/* 27 */   public static final ChallengeCore<MorganChallenge> INSTANCE = (new ChallengeCore.Builder("morgan", TITLE, OBJECTIVE, ModNPCGroups.MARINES, MorganChallenge::new))
/* 28 */     .setDifficulty(ChallengeDifficulty.STANDARD)
/* 29 */     .setDifficultyStars(1)
/* 30 */     .addArena(ArenaStyle.SIMPLE, (ChallengeArena)MarineSmallBaseSimpleArena.INSTANCE, MarineSmallBaseSimpleArena::getChallengerSpawnPos, MarineSmallBaseSimpleArena::getEnemySpawnPos)
/* 31 */     .setEnemySpawns(MorganChallenge::setEnemeySpawns)
/* 32 */     .setTargetShowcase(new Supplier[] { (Supplier)ModEntities.MORGAN
/* 33 */       }).setTimeLimit(10)
/* 34 */     .setOrder(ModChallenges.Order.MORGAN)
/* 35 */     .setRewards(REWARD)
/* 36 */     .build();
/*    */   
/*    */   public MorganChallenge(ChallengeCore core) {
/* 39 */     super(core);
/*    */   }
/*    */   
/*    */   public static Set<ChallengeArena.EnemySpawn> setEnemeySpawns(InProgressChallenge challenge, ChallengeArena.SpawnPosition[] spawns) {
/* 43 */     Set<ChallengeArena.EnemySpawn> set = new HashSet<>();
/*    */     
/* 45 */     MorganEntity boss = new MorganEntity(challenge);
/* 46 */     set.add(new ChallengeArena.EnemySpawn((LivingEntity)boss, spawns[0]));
/*    */     
/* 48 */     return set;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\challenges\shellstown\MorganChallenge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */