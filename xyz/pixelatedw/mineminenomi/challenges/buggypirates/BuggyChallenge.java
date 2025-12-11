/*    */ package xyz.pixelatedw.mineminenomi.challenges.buggypirates;
/*    */ 
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ArenaStyle;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.Challenge;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeArena;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeDifficulty;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.InProgressChallenge;
/*    */ import xyz.pixelatedw.mineminenomi.challenges.arenas.CircusArena;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.buggypirates.BuggyEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModChallenges;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModNPCGroups;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ public class BuggyChallenge extends Challenge {
/* 27 */   private static final String TITLE = WyRegistry.registerName("challenge.mineminenomi.buggy_pirates.buggy", "Buggy");
/* 28 */   public static final String OBJECTIVE = WyRegistry.registerName("challenge.mineminenomi.buggy_pirates.buggy.objective", "Defeat Buggy");
/* 29 */   public static final ResourceLocation REWARD = new ResourceLocation("mineminenomi", "rewards/buggy");
/*    */   
/* 31 */   public static final ChallengeCore<BuggyChallenge> INSTANCE = (new ChallengeCore.Builder("buggy", TITLE, OBJECTIVE, ModNPCGroups.BUGGY_PIRATES.getName(), BuggyChallenge::new))
/* 32 */     .setDifficulty(ChallengeDifficulty.STANDARD)
/* 33 */     .setDifficultyStars(1)
/* 34 */     .setRewardsFactor(2.0F)
/* 35 */     .addArena(ArenaStyle.SIMPLE, (ChallengeArena)CircusArena.INSTANCE, CircusArena::getChallengerSpawnPos, CircusArena::getEnemySpawnPos)
/* 36 */     .setEnemySpawns(BuggyChallenge::setEnemeySpawns)
/* 37 */     .setTargetShowcase(new ChallengeCore.ITargetShowcase[] { BuggyChallenge::createShowcase
/* 38 */       }).setTimeLimit(10)
/* 39 */     .setOrder(ModChallenges.Order.BUGGY)
/* 40 */     .setRewards(REWARD)
/* 41 */     .build();
/*    */   
/*    */   public BuggyChallenge(ChallengeCore<BuggyChallenge> core) {
/* 44 */     super(core);
/*    */   }
/*    */   
/*    */   public static Set<ChallengeArena.EnemySpawn> setEnemeySpawns(InProgressChallenge challenge, ChallengeArena.SpawnPosition[] spawns) {
/* 48 */     Set<ChallengeArena.EnemySpawn> set = new HashSet<>();
/*    */     
/* 50 */     BuggyEntity boss = new BuggyEntity(challenge);
/* 51 */     set.add(new ChallengeArena.EnemySpawn((LivingEntity)boss, spawns[0]));
/*    */     
/* 53 */     return set;
/*    */   }
/*    */   
/*    */   public static LivingEntity createShowcase(World world) {
/* 57 */     BuggyEntity entity = (BuggyEntity)((EntityType)ModEntities.BUGGY.get()).func_200721_a(world);
/*    */     
/* 59 */     ItemStack bicorneStack = ((Item)ModArmors.BICORNE.get()).func_190903_i();
/* 60 */     bicorneStack.func_190925_c("display").func_74768_a("color", 13459968);
/* 61 */     entity.func_184201_a(EquipmentSlotType.HEAD, bicorneStack);
/*    */     
/* 63 */     return (LivingEntity)entity;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\challenges\buggypirates\BuggyChallenge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */