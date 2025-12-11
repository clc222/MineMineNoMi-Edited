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
/*    */ public class AlvidaHardChallenge extends Challenge {
/* 27 */   private static final String TITLE = WyRegistry.registerName("challenge.mineminenomi.buggy_pirates.alvida_hard", "Alvida (Hard)");
/* 28 */   public static final ResourceLocation REWARD = new ResourceLocation("mineminenomi", "rewards/alvida_hard");
/*    */   
/* 30 */   public static final ChallengeCore<AlvidaHardChallenge> INSTANCE = (new ChallengeCore.Builder("alvida_hard", TITLE, AlvidaChallenge.OBJECTIVE, ModNPCGroups.BUGGY_PIRATES.getName(), AlvidaHardChallenge::new))
/* 31 */     .setDifficulty(ChallengeDifficulty.HARD)
/* 32 */     .setDifficultyStars(1)
/* 33 */     .addArena(ArenaStyle.SIMPLE, (ChallengeArena)HigumaSimpleArena.INSTANCE, HigumaSimpleArena::getChallengerSpawnPos, HigumaSimpleArena::getEnemySpawnPos)
/* 34 */     .setEnemySpawns(AlvidaHardChallenge::setEnemeySpawns)
/* 35 */     .setTargetShowcase(new ChallengeCore.ITargetShowcase[] { AlvidaHardChallenge::createShowcase
/* 36 */       }).setTimeLimit(10)
/* 37 */     .setOrder(ModChallenges.Order.ALVIDA)
/* 38 */     .setRewards(REWARD)
/* 39 */     .build();
/*    */   
/*    */   public AlvidaHardChallenge(ChallengeCore<AlvidaHardChallenge> core) {
/* 42 */     super(core);
/*    */   }
/*    */   
/*    */   public static Set<ChallengeArena.EnemySpawn> setEnemeySpawns(InProgressChallenge challenge, ChallengeArena.SpawnPosition[] spawns) {
/* 46 */     Set<ChallengeArena.EnemySpawn> set = new HashSet<>();
/*    */     
/* 48 */     AlvidaEntity boss = new AlvidaEntity((EntityType)ModEntities.ALVIDA_SLIM.get(), challenge);
/* 49 */     set.add(new ChallengeArena.EnemySpawn((LivingEntity)boss, spawns[0]));
/*    */     
/* 51 */     return set;
/*    */   }
/*    */   
/*    */   public static LivingEntity createShowcase(World world) {
/* 55 */     AlvidaEntity entity = (AlvidaEntity)((EntityType)ModEntities.ALVIDA_SLIM.get()).func_200721_a(world);
/*    */     
/* 57 */     ItemStack plumeHeadStack = ((Item)ModArmors.PLUME_HAT.get()).func_190903_i();
/* 58 */     plumeHeadStack.func_190925_c("display").func_74768_a("color", 12788538);
/* 59 */     entity.func_184201_a(EquipmentSlotType.HEAD, plumeHeadStack);
/*    */     
/* 61 */     return (LivingEntity)entity;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\challenges\buggypirates\AlvidaHardChallenge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */