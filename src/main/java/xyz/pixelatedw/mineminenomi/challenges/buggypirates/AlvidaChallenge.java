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
/*    */ import xyz.pixelatedw.mineminenomi.challenges.arenas.HigumaSimpleArena;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.AlvidaEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModChallenges;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModNPCGroups;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ public class AlvidaChallenge extends Challenge {
/* 27 */   private static final String TITLE = WyRegistry.registerName("challenge.mineminenomi.buggy_pirates.alvida", "Alvida");
/* 28 */   public static final String OBJECTIVE = WyRegistry.registerName("challenge.mineminenomi.buggy_pirates.alvida.objective", "Defeat Alvida");
/* 29 */   public static final ResourceLocation REWARD = new ResourceLocation("mineminenomi", "rewards/alvida");
/*    */   
/* 31 */   public static final ChallengeCore<AlvidaChallenge> INSTANCE = (new ChallengeCore.Builder("alvida", TITLE, OBJECTIVE, ModNPCGroups.BUGGY_PIRATES.getName(), AlvidaChallenge::new))
/* 32 */     .setDifficulty(ChallengeDifficulty.STANDARD)
/* 33 */     .setDifficultyStars(1)
/* 34 */     .addArena(ArenaStyle.SIMPLE, (ChallengeArena)HigumaSimpleArena.INSTANCE, HigumaSimpleArena::getChallengerSpawnPos, HigumaSimpleArena::getEnemySpawnPos)
/* 35 */     .setEnemySpawns(AlvidaChallenge::setEnemeySpawns)
/* 36 */     .setTargetShowcase(new ChallengeCore.ITargetShowcase[] { AlvidaChallenge::createShowcase
/* 37 */       }).setTimeLimit(10)
/* 38 */     .setOrder(ModChallenges.Order.ALVIDA)
/* 39 */     .setRewards(REWARD)
/* 40 */     .build();
/*    */   
/*    */   public AlvidaChallenge(ChallengeCore<AlvidaChallenge> core) {
/* 43 */     super(core);
/*    */   }
/*    */   
/*    */   public static Set<ChallengeArena.EnemySpawn> setEnemeySpawns(InProgressChallenge challenge, ChallengeArena.SpawnPosition[] spawns) {
/* 47 */     Set<ChallengeArena.EnemySpawn> set = new HashSet<>();
/*    */     
/* 49 */     AlvidaEntity boss = new AlvidaEntity((EntityType)ModEntities.ALVIDA.get(), challenge);
/* 50 */     set.add(new ChallengeArena.EnemySpawn((LivingEntity)boss, spawns[0]));
/*    */     
/* 52 */     return set;
/*    */   }
/*    */   
/*    */   public static LivingEntity createShowcase(World world) {
/* 56 */     AlvidaEntity entity = (AlvidaEntity)((EntityType)ModEntities.ALVIDA.get()).func_200721_a(world);
/*    */     
/* 58 */     ItemStack plumeHeadStack = ((Item)ModArmors.PLUME_HAT.get()).func_190903_i();
/* 59 */     plumeHeadStack.func_190925_c("display").func_74768_a("color", 12788538);
/* 60 */     entity.func_184201_a(EquipmentSlotType.HEAD, plumeHeadStack);
/*    */     
/* 62 */     return (LivingEntity)entity;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\challenges\buggypirates\AlvidaChallenge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */