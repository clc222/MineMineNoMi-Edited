/*    */ package xyz.pixelatedw.mineminenomi.challenges.baroqueworks;
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.Challenge;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeArena;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeDifficulty;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.InProgressChallenge;
/*    */ import xyz.pixelatedw.mineminenomi.challenges.arenas.AlabastaDesertSimpleArena;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.baroqueworks.Mr0Entity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModChallenges;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModNPCGroups;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*    */ import xyz.pixelatedw.mineminenomi.items.weapons.ModSwordItem;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ public class Mr0Challenge extends Challenge {
/* 30 */   private static final String TITLE = WyRegistry.registerName("challenge.mineminenomi.baroque_works.mr_0", "Mr. 0");
/* 31 */   public static final String OBJECTIVE = WyRegistry.registerName("challenge.mineminenomi.baroque_works.mr_0.objective", "Defeat Mr. 0");
/* 32 */   public static final ResourceLocation REWARD = new ResourceLocation("mineminenomi", "rewards/mr_0");
/*    */   
/* 34 */   public static final ChallengeCore<Mr0Challenge> INSTANCE = (new ChallengeCore.Builder("mr_0", TITLE, OBJECTIVE, ModNPCGroups.BAROQUE_WORKS.getName(), Mr0Challenge::new))
/* 35 */     .setDifficulty(ChallengeDifficulty.STANDARD)
/* 36 */     .setDifficultyStars(3)
/* 37 */     .setRewardsFactor(3.0F)
/* 38 */     .addArena(ArenaStyle.SIMPLE, (ChallengeArena)AlabastaDesertSimpleArena.INSTANCE, AlabastaDesertSimpleArena::getChallengerSpawnPos, AlabastaDesertSimpleArena::getEnemySpawnPos)
/* 39 */     .setEnemySpawns(Mr0Challenge::setEnemeySpawns)
/* 40 */     .setTargetShowcase(new ChallengeCore.ITargetShowcase[] { Mr0Challenge::createMr0Showcase
/* 41 */       }).setTimeLimit(10)
/* 42 */     .setOrder(ModChallenges.Order.MR_0)
/* 43 */     .setRewards(REWARD)
/* 44 */     .build();
/*    */   
/*    */   public Mr0Challenge(ChallengeCore core) {
/* 47 */     super(core);
/*    */   }
/*    */   
/*    */   public static Set<ChallengeArena.EnemySpawn> setEnemeySpawns(InProgressChallenge challenge, ChallengeArena.SpawnPosition[] spawns) {
/* 51 */     Set<ChallengeArena.EnemySpawn> set = new HashSet<>();
/*    */     
/* 53 */     Mr0Entity boss = new Mr0Entity(challenge);
/* 54 */     set.add(new ChallengeArena.EnemySpawn((LivingEntity)boss, spawns[0]));
/*    */     
/* 56 */     return set;
/*    */   }
/*    */   
/*    */   public static LivingEntity createMr0Showcase(World world) {
/* 60 */     Mr0Entity entity = (Mr0Entity)((EntityType)ModEntities.MR0.get()).func_200721_a(world);
/*    */     
/* 62 */     entity.func_184641_n(false);
/*    */     
/* 64 */     ItemStack capeStack = new ItemStack((IItemProvider)ModArmors.FLUFFY_CAPE.get());
/* 65 */     capeStack.func_190925_c("display").func_74768_a("color", WyHelper.hexToRGB("#1F2121").getRGB());
/* 66 */     entity.func_184201_a(EquipmentSlotType.CHEST, capeStack);
/* 67 */     entity.func_184201_a(EquipmentSlotType.OFFHAND, ((ModSwordItem)ModWeapons.HOOK.get()).func_190903_i());
/* 68 */     entity.func_184201_a(EquipmentSlotType.HEAD, ((Item)ModItems.CIGAR.get()).func_190903_i());
/*    */     
/* 70 */     return (LivingEntity)entity;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\challenges\baroqueworks\Mr0Challenge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */