/*    */ package xyz.pixelatedw.mineminenomi.challenges.kriegpirates;
/*    */ 
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ArenaStyle;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.Challenge;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeArena;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeDifficulty;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.InProgressChallenge;
/*    */ import xyz.pixelatedw.mineminenomi.challenges.arenas.BaratieSimpleArena;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.kriegpirates.PearlEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModChallenges;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModNPCGroups;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ public class PearlChallenge
/*    */   extends Challenge {
/* 28 */   private static final String TITLE = WyRegistry.registerName("challenge.mineminenomi.krieg_pirates.pearl", "Pearl");
/* 29 */   public static final String OBJECTIVE = WyRegistry.registerName("challenge.mineminenomi.krieg_pirates.pearl.objective", "Defeat Pearl");
/* 30 */   public static final ResourceLocation REWARD = new ResourceLocation("mineminenomi", "rewards/pearl");
/*    */   
/* 32 */   public static final ChallengeCore<PearlChallenge> INSTANCE = (new ChallengeCore.Builder("pearl", TITLE, OBJECTIVE, ModNPCGroups.KRIEG_PIRATES.getName(), PearlChallenge::new))
/* 33 */     .setDifficulty(ChallengeDifficulty.STANDARD)
/* 34 */     .setDifficultyStars(1)
/* 35 */     .addArena(ArenaStyle.SIMPLE, (ChallengeArena)BaratieSimpleArena.INSTANCE, BaratieSimpleArena::getChallengerSpawnPos, BaratieSimpleArena::getEnemySpawnPos)
/* 36 */     .setEnemySpawns(PearlChallenge::setEnemeySpawns)
/* 37 */     .setTargetShowcase(new ChallengeCore.ITargetShowcase[] { PearlChallenge::createPearlShowcase
/* 38 */       }).setTimeLimit(10)
/* 39 */     .setOrder(ModChallenges.Order.PEARL)
/* 40 */     .setRewards(REWARD)
/* 41 */     .build();
/*    */   
/*    */   public PearlChallenge(ChallengeCore<PearlChallenge> core) {
/* 44 */     super(core);
/*    */   }
/*    */   
/*    */   public static Set<ChallengeArena.EnemySpawn> setEnemeySpawns(InProgressChallenge challenge, ChallengeArena.SpawnPosition[] spawns) {
/* 48 */     Set<ChallengeArena.EnemySpawn> set = new HashSet<>();
/*    */     
/* 50 */     PearlEntity boss = new PearlEntity(challenge);
/* 51 */     set.add(new ChallengeArena.EnemySpawn((LivingEntity)boss, spawns[0]));
/*    */     
/* 53 */     return set;
/*    */   }
/*    */   
/*    */   public static LivingEntity createPearlShowcase(World world) {
/* 57 */     LivingEntity entity = (LivingEntity)((EntityType)ModEntities.PEARL.get()).func_200721_a(world);
/*    */     
/* 59 */     entity.func_184201_a(EquipmentSlotType.HEAD, new ItemStack((IItemProvider)ModArmors.PEARL_HAT.get()));
/* 60 */     entity.func_184201_a(EquipmentSlotType.CHEST, new ItemStack((IItemProvider)ModArmors.PEARL_ARMOR.get()));
/* 61 */     entity.func_184201_a(EquipmentSlotType.LEGS, new ItemStack((IItemProvider)ModArmors.PEARL_LEGS.get()));
/*    */     
/* 63 */     return entity;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\challenges\kriegpirates\PearlChallenge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */