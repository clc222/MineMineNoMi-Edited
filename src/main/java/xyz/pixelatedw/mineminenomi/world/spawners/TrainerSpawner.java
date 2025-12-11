/*     */ package xyz.pixelatedw.mineminenomi.world.spawners;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import java.util.function.Predicate;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.SpawnReason;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.Biome;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyDebug;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class TrainerSpawner {
/*     */   static {
/*  29 */     TRAINER_CHECK = (target -> target instanceof xyz.pixelatedw.mineminenomi.api.entities.ITrainer);
/*     */   }
/*  31 */   private static final Predicate<Entity> TRAINER_CHECK; private Random random = new Random();
/*     */   private int cooldown;
/*     */   
/*     */   public void tick(ServerWorld world) {
/*  35 */     world.func_217381_Z().func_76320_a("trainerSpawnerTick");
/*  36 */     if (--this.cooldown <= 0) {
/*  37 */       this.cooldown = CommonConfig.INSTANCE.getTimeBetweenTrainerSpawns();
/*  38 */       if (this.random.nextInt(100) <= CommonConfig.INSTANCE.getChanceForTrainerSpawn()) {
/*  39 */         spawn(world);
/*     */       }
/*     */     } 
/*  42 */     world.func_217381_Z().func_76319_b();
/*     */   }
/*     */   
/*     */   private void spawn(ServerWorld world) {
/*  46 */     world.func_217381_Z().func_76320_a("trainerSpawnerSpawn");
/*     */     
/*  48 */     int listSize = MathHelper.func_76125_a(world.func_217369_A().size() / 3, 1, 10);
/*  49 */     PlayerEntity[] cachedPlayers = new PlayerEntity[listSize];
/*     */     
/*  51 */     for (int i = 0; i < cachedPlayers.length; i++) {
/*  52 */       ServerPlayerEntity serverPlayerEntity = world.func_217472_l_();
/*     */       
/*  54 */       if (serverPlayerEntity != null) {
/*     */ 
/*     */ 
/*     */         
/*  58 */         boolean alreadyCached = Arrays.<PlayerEntity>stream(cachedPlayers).anyMatch(target -> (target == player));
/*  59 */         if (!alreadyCached) {
/*     */ 
/*     */ 
/*     */           
/*  63 */           cachedPlayers[i] = (PlayerEntity)serverPlayerEntity;
/*     */           
/*  65 */           IEntityStats props = EntityStatsCapability.get((LivingEntity)serverPlayerEntity);
/*  66 */           EntityType entityType = null;
/*     */           
/*  68 */           BlockPos targetPos = serverPlayerEntity.func_233580_cy_();
/*  69 */           Biome biome = world.func_226691_t_(targetPos);
/*     */           
/*  71 */           if (props.isSwordsman()) {
/*  72 */             entityType = (EntityType)ModEntities.SWORDSMAN_TRAINER.get();
/*  73 */           } else if (props.isSniper()) {
/*  74 */             entityType = (EntityType)ModEntities.SNIPER_TRAINER.get();
/*  75 */           } else if (props.isWeatherWizard()) {
/*  76 */             entityType = (EntityType)ModEntities.ART_OF_WEATHER_TRAINER.get();
/*  77 */           } else if (props.isDoctor()) {
/*  78 */             entityType = (EntityType)ModEntities.DOCTOR_TRAINER.get();
/*  79 */           } else if (props.isBrawler()) {
/*  80 */             entityType = (EntityType)ModEntities.BRAWLER_TRAINER.get();
/*  81 */           } else if (props.isBlackLeg()) {
/*  82 */             entityType = (EntityType)ModEntities.BLACK_LEG_TRAINER.get();
/*     */           } 
/*     */           
/*  85 */           boolean dugongMasterChance = (world.func_201674_k().nextFloat() <= 0.25D);
/*  86 */           if (dugongMasterChance && (biome.func_201856_r() == Biome.Category.DESERT || biome.func_201856_r() == Biome.Category.BEACH)) {
/*  87 */             entityType = (EntityType)ModEntities.LEGENDARY_MASTER_DUGONG.get();
/*     */           }
/*     */           
/*  90 */           if (entityType == null) {
/*     */             return;
/*     */           }
/*     */           
/*  94 */           BlockPos spawnPos = WyHelper.findOnGroundSpawnLocation((World)world, entityType, targetPos, 20);
/*     */           
/*  96 */           if (spawnPos == null) {
/*     */             return;
/*     */           }
/*     */ 
/*     */           
/* 101 */           List<LivingEntity> trainers = WyHelper.getNearbyEntities(new Vector3d(spawnPos.func_177958_n(), spawnPos.func_177956_o(), spawnPos.func_177952_p()), (IWorld)world, 100.0D, TRAINER_CHECK, new Class[] { LivingEntity.class });
/*     */           
/* 103 */           boolean canSpawnInBiome = (biome.func_201856_r() != Biome.Category.OCEAN);
/*     */           
/* 105 */           if (trainers.size() < 2 && canSpawnInBiome) {
/* 106 */             entityType.func_220342_a(world, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, spawnPos, SpawnReason.EVENT, false, false);
/* 107 */             WyDebug.debug("Trainer (" + entityType + ") spawned at: " + spawnPos);
/*     */           } 
/*     */         } 
/*     */       } 
/* 111 */     }  world.func_217381_Z().func_76319_b();
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\world\spawners\TrainerSpawner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */