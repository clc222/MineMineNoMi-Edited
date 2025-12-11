/*     */ package xyz.pixelatedw.mineminenomi.events;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import java.util.function.Predicate;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityClassification;
/*     */ import net.minecraft.entity.EntitySpawnPlacementRegistry;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.ILivingEntityData;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.SpawnReason;
/*     */ import net.minecraft.entity.effect.LightningBoltEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.pathfinding.PathType;
/*     */ import net.minecraft.util.Direction;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.WeightedRandom;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.registry.Registry;
/*     */ import net.minecraft.world.GameRules;
/*     */ import net.minecraft.world.IBlockReader;
/*     */ import net.minecraft.world.IServerWorld;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.IWorldReader;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.Biome;
/*     */ import net.minecraft.world.biome.MobSpawnInfo;
/*     */ import net.minecraft.world.gen.Heightmap;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import net.minecraft.world.spawner.WorldEntitySpawner;
/*     */ import net.minecraftforge.common.ForgeHooks;
/*     */ import net.minecraftforge.event.TickEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import xyz.pixelatedw.mineminenomi.api.ILocalLightningBoltEntity;
/*     */ import xyz.pixelatedw.mineminenomi.api.crew.Crew;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.TPSDelta;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ChallengesWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.EventsWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.NPCWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModBiomes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*     */ import xyz.pixelatedw.mineminenomi.world.spawners.AmbushSpawner;
/*     */ import xyz.pixelatedw.mineminenomi.world.spawners.TraderSpawner;
/*     */ import xyz.pixelatedw.mineminenomi.world.spawners.TrainerSpawner;
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi")
/*     */ public class WorldEvents {
/*  54 */   private static final TraderSpawner TRADER_SPAWNER = new TraderSpawner();
/*  55 */   private static final TrainerSpawner TRAINER_SPAWNER = new TrainerSpawner();
/*  56 */   private static final AmbushSpawner AMBUSH_SPAWNER = new AmbushSpawner(); private static final Predicate<ServerPlayerEntity> THUNDER_PLAINS_BIOME_CHECK;
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onClientTick(TickEvent.ClientTickEvent event) {
/*  60 */     if (event.phase == TickEvent.Phase.END) {
/*  61 */       TPSDelta.INSTANCE.tick();
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onServerTick(TickEvent.WorldTickEvent event) {
/*  67 */     if (event.phase == TickEvent.Phase.END) {
/*  68 */       TPSDelta.INSTANCE.tick();
/*     */       
/*  70 */       if (event.world.func_234923_W_() == World.field_234918_g_) {
/*  71 */         if (event.world.func_82737_E() % 20L == 0L) {
/*  72 */           event.world.func_217381_Z().func_76320_a("crews");
/*  73 */           ExtendedWorldData.get().getCrews().forEach(Crew::tick);
/*  74 */           event.world.func_217381_Z().func_76319_b();
/*     */           
/*  76 */           event.world.func_217381_Z().func_76320_a("worldEvents");
/*  77 */           EventsWorldData.get().tick((ServerWorld)event.world);
/*  78 */           event.world.func_217381_Z().func_76319_b();
/*     */           
/*  80 */           event.world.func_217381_Z().func_76320_a("trackedNPCs");
/*  81 */           NPCWorldData.get().tick((ServerWorld)event.world);
/*  82 */           event.world.func_217381_Z().func_76319_b();
/*     */         } 
/*     */         
/*  85 */         if (event.world.func_82736_K().func_223586_b(GameRules.field_223601_d)) {
/*  86 */           event.world.func_217381_Z().func_76320_a("worldSpawners");
/*     */           
/*  88 */           if (CommonConfig.INSTANCE.canSpawnTraders()) {
/*  89 */             TRADER_SPAWNER.tick((ServerWorld)event.world);
/*     */           }
/*  91 */           if (CommonConfig.INSTANCE.canSpawnTrainers()) {
/*  92 */             TRAINER_SPAWNER.tick((ServerWorld)event.world);
/*     */           }
/*  94 */           if (CommonConfig.INSTANCE.canSpawnAmbushes()) {
/*  95 */             AMBUSH_SPAWNER.tick((ServerWorld)event.world);
/*     */           }
/*     */           
/*  98 */           event.world.func_217381_Z().func_76319_b();
/*     */         } 
/*     */         
/* 101 */         thunderPlains((ServerWorld)event.world);
/* 102 */       } else if (event.world.func_234923_W_().func_240901_a_().toString().contains("challenges_")) {
/* 103 */         event.world.func_217381_Z().func_76320_a("challengesManager");
/*     */         
/* 105 */         ChallengesWorldData.get().tick((ServerWorld)event.world);
/*     */         
/* 107 */         event.world.func_217381_Z().func_76319_b();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static final void spawnOPChunkEntities(IServerWorld world, Biome biome, int chunkX, int chunkZ, Random rand) {
/* 113 */     spawnMobsForChunkGeneration(world, biome, chunkX, chunkZ, rand, ModEntities.MARINES);
/* 114 */     spawnMobsForChunkGeneration(world, biome, chunkX, chunkZ, rand, ModEntities.PIRATES);
/* 115 */     spawnMobsForChunkGeneration(world, biome, chunkX, chunkZ, rand, ModEntities.BANDITS);
/*     */   }
/*     */   static {
/* 118 */     THUNDER_PLAINS_BIOME_CHECK = (player -> {
/*     */         ResourceLocation biome = player.field_70170_p.func_241828_r().func_243612_b(Registry.field_239720_u_).func_177774_c(player.field_70170_p.func_226691_t_(player.func_233580_cy_()));
/* 120 */         return (biome.equals(ModBiomes.RAIGO.getId()) && player.field_70170_p.func_226660_f_(player.func_233580_cy_()));
/*     */       });
/*     */   }
/*     */   private static final void thunderPlains(ServerWorld world) {
/* 124 */     if (world.func_82737_E() % (20 + world.func_201674_k().nextInt(10)) != 0L) {
/*     */       return;
/*     */     }
/*     */     
/* 128 */     int limit = MathHelper.func_76125_a(world.func_217369_A().size() / 3, 1, 10);
/* 129 */     world.func_217369_A().stream().filter(THUNDER_PLAINS_BIOME_CHECK).limit(limit).forEach(player -> {
/*     */           if (player.func_226278_cu_() > 128.0D) {
/*     */             return;
/*     */           }
/*     */           int strikes = 2 + world.func_201674_k().nextInt(5);
/*     */           for (int i = 0; i < strikes; i++) {
/*     */             int x = (int)((player.func_70681_au().nextInt(151) - 50) + player.func_226277_ct_());
/*     */             int z = (int)((player.func_70681_au().nextInt(151) - 50) + player.func_226281_cx_());
/*     */             int y = world.func_201676_a(Heightmap.Type.WORLD_SURFACE, x, z);
/*     */             BlockPos strikePos = new BlockPos(x, y, z);
/*     */             boolean strikePlayer = (world.func_201674_k().nextInt(512) == 0);
/*     */             if (strikePlayer) {
/*     */               strikePos = player.func_233580_cy_();
/*     */             }
/*     */             ResourceLocation biome = player.field_70170_p.func_241828_r().func_243612_b(Registry.field_239720_u_).func_177774_c(player.field_70170_p.func_226691_t_(strikePos));
/*     */             if (biome.equals(ModBiomes.RAIGO.getId())) {
/*     */               LightningBoltEntity bolt = (LightningBoltEntity)EntityType.field_200728_aG.func_200721_a((World)world);
/*     */               ((ILocalLightningBoltEntity)bolt).setLocal();
/*     */               bolt.func_174828_a(strikePos, 0.0F, 0.0F);
/*     */               world.func_217376_c((Entity)bolt);
/*     */             } 
/*     */           } 
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void spawnMobsForChunkGeneration(IServerWorld world, Biome biome, int chunkX, int chunkZ, Random rand, EntityClassification category) {
/* 162 */     MobSpawnInfo mobspawninfo = biome.func_242433_b();
/* 163 */     List<MobSpawnInfo.Spawners> list = mobspawninfo.func_242559_a(category);
/* 164 */     if (!list.isEmpty()) {
/* 165 */       int x = chunkX << 4;
/* 166 */       int z = chunkZ << 4;
/*     */       
/* 168 */       while (rand.nextFloat() < mobspawninfo.func_242557_a()) {
/* 169 */         MobSpawnInfo.Spawners spawners = (MobSpawnInfo.Spawners)WeightedRandom.func_76271_a(rand, list);
/* 170 */         int k = spawners.field_242589_d + rand.nextInt(1 + spawners.field_242590_e - spawners.field_242589_d);
/* 171 */         ILivingEntityData ilivingentitydata = null;
/* 172 */         int l = x + rand.nextInt(16);
/* 173 */         int i1 = z + rand.nextInt(16);
/* 174 */         int j1 = l;
/* 175 */         int k1 = i1;
/*     */         
/* 177 */         for (int l1 = 0; l1 < k; l1++) {
/* 178 */           boolean flag = false;
/*     */           
/* 180 */           for (int i2 = 0; !flag && i2 < 4; i2++) {
/* 181 */             BlockPos blockpos = getTopNonCollidingPos((IWorldReader)world, spawners.field_242588_c, l, i1);
/* 182 */             if (spawners.field_242588_c.func_200720_b() && 
/* 183 */               WorldEntitySpawner.func_209382_a(EntitySpawnPlacementRegistry.func_209344_a(spawners.field_242588_c), (IWorldReader)world, blockpos, spawners.field_242588_c)) {
/* 184 */               float f = spawners.field_242588_c.func_220333_h();
/* 185 */               double d0 = MathHelper.func_151237_a(l, x + f, x + 16.0D - f);
/* 186 */               double d1 = MathHelper.func_151237_a(i1, z + f, z + 16.0D - f);
/* 187 */               if (world.func_226664_a_(spawners.field_242588_c.func_220328_a(d0, blockpos.func_177956_o(), d1))) { Entity entity; if (!EntitySpawnPlacementRegistry.func_223515_a(spawners.field_242588_c, world, SpawnReason.CHUNK_GENERATION, new BlockPos(d0, blockpos
/* 188 */                       .func_177956_o(), d1), world.func_201674_k())) {
/*     */                   continue;
/*     */                 }
/*     */ 
/*     */                 
/*     */                 try {
/* 194 */                   entity = spawners.field_242588_c.func_200721_a((World)world.func_201672_e());
/* 195 */                 } catch (Exception exception) {
/*     */                   continue;
/*     */                 } 
/*     */                 
/* 199 */                 entity.func_70012_b(d0, blockpos.func_177956_o(), d1, rand.nextFloat() * 360.0F, 0.0F);
/* 200 */                 if (entity instanceof MobEntity) {
/* 201 */                   MobEntity mobentity = (MobEntity)entity;
/* 202 */                   if (ForgeHooks.canEntitySpawn(mobentity, (IWorld)world, d0, blockpos.func_177956_o(), d1, null, SpawnReason.CHUNK_GENERATION) == -1) {
/*     */                     continue;
/*     */                   }
/*     */                   
/* 206 */                   if (mobentity.func_213380_a((IWorld)world, SpawnReason.CHUNK_GENERATION) && mobentity.func_205019_a((IWorldReader)world)) {
/* 207 */                     ilivingentitydata = mobentity.func_213386_a(world, world.func_175649_E(mobentity.func_233580_cy_()), SpawnReason.CHUNK_GENERATION, ilivingentitydata, (CompoundNBT)null);
/*     */                     
/* 209 */                     world.func_242417_l((Entity)mobentity);
/* 210 */                     flag = true;
/*     */                   } 
/*     */                 }  }
/*     */               else { continue; }
/*     */             
/* 215 */             }  l += rand.nextInt(5) - rand.nextInt(5);
/*     */             
/* 217 */             for (i1 += rand.nextInt(5) - rand.nextInt(5); l < x || l >= x + 16 || i1 < z || i1 >= z + 16; i1 = k1 + rand.nextInt(5) - rand.nextInt(5))
/* 218 */               l = j1 + rand.nextInt(5) - rand.nextInt(5); 
/*     */             continue;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static BlockPos getTopNonCollidingPos(IWorldReader level, EntityType<?> entityType, int pX, int pZ) {
/* 227 */     int i = level.func_201676_a(EntitySpawnPlacementRegistry.func_209342_b(entityType), pX, pZ);
/* 228 */     BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable(pX, i, pZ);
/* 229 */     if (level.func_230315_m_().func_236037_d_()) {
/*     */       do {
/* 231 */         blockpos$mutable.func_189536_c(Direction.DOWN);
/* 232 */       } while (!level.func_180495_p((BlockPos)blockpos$mutable).func_196958_f());
/*     */       
/*     */       do {
/* 235 */         blockpos$mutable.func_189536_c(Direction.DOWN);
/* 236 */       } while (level.func_180495_p((BlockPos)blockpos$mutable).func_196958_f() && blockpos$mutable.func_177956_o() > 0);
/*     */     } 
/*     */     
/* 239 */     if (EntitySpawnPlacementRegistry.func_209344_a(entityType) == EntitySpawnPlacementRegistry.PlacementType.ON_GROUND) {
/* 240 */       BlockPos blockpos = blockpos$mutable.func_177977_b();
/* 241 */       if (level.func_180495_p(blockpos).func_196957_g((IBlockReader)level, blockpos, PathType.LAND)) {
/* 242 */         return blockpos;
/*     */       }
/*     */     } 
/*     */     
/* 246 */     return blockpos$mutable.func_185334_h();
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\WorldEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */