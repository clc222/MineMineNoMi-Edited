/*    */ package xyz.pixelatedw.mineminenomi.challenges.buggypirates;
/*    */ 
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ArenaStyle;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.Challenge;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeArena;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeDifficulty;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.InProgressChallenge;
/*    */ import xyz.pixelatedw.mineminenomi.challenges.arenas.CircusArena;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.buggypirates.CabajiEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModChallenges;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModNPCGroups;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ public class CabajiChallenge extends Challenge {
/* 26 */   private static final String TITLE = WyRegistry.registerName("challenge.mineminenomi.buggy_pirates.cabaji", "Cabaji");
/* 27 */   public static final String OBJECTIVE = WyRegistry.registerName("challenge.mineminenomi.buggy_pirates.cabaji.objective", "Defeat Cabaji");
/* 28 */   public static final ResourceLocation REWARD = new ResourceLocation("mineminenomi", "rewards/cabaji");
/*    */   
/* 30 */   public static final ChallengeCore<CabajiChallenge> INSTANCE = (new ChallengeCore.Builder("cabaji", TITLE, OBJECTIVE, ModNPCGroups.BUGGY_PIRATES.getName(), CabajiChallenge::new))
/* 31 */     .setDifficulty(ChallengeDifficulty.STANDARD)
/* 32 */     .setDifficultyStars(1)
/* 33 */     .addArena(ArenaStyle.SIMPLE, (ChallengeArena)CircusArena.INSTANCE, CircusArena::getChallengerSpawnPos, CircusArena::getEnemySpawnPos)
/* 34 */     .setEnemySpawns(CabajiChallenge::setEnemeySpawns)
/* 35 */     .setTargetShowcase(new ChallengeCore.ITargetShowcase[] { CabajiChallenge::createShowcase
/* 36 */       }).setTimeLimit(10)
/* 37 */     .setOrder(ModChallenges.Order.CABAJI)
/* 38 */     .setRewards(REWARD)
/* 39 */     .build();
/*    */   
/*    */   public CabajiChallenge(ChallengeCore<CabajiChallenge> core) {
/* 42 */     super(core);
/*    */   }
/*    */   
/*    */   public static Set<ChallengeArena.EnemySpawn> setEnemeySpawns(InProgressChallenge challenge, ChallengeArena.SpawnPosition[] spawns) {
/* 46 */     Set<ChallengeArena.EnemySpawn> set = new HashSet<>();
/*    */     
/* 48 */     CabajiEntity boss = new CabajiEntity(challenge);
/* 49 */     set.add(new ChallengeArena.EnemySpawn((LivingEntity)boss, spawns[0]));
/*    */     
/* 51 */     return set;
/*    */   }
/*    */   
/*    */   public static LivingEntity createShowcase(World world) {
/* 55 */     CabajiEntity entity = (CabajiEntity)((EntityType)ModEntities.CABAJI.get()).func_200721_a(world);
/*    */     
/* 57 */     entity.func_184201_a(EquipmentSlotType.HEAD, ((Item)ModArmors.CABAJI_SCARF.get()).func_190903_i());
/*    */     
/* 59 */     return (LivingEntity)entity;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\challenges\buggypirates\CabajiChallenge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */