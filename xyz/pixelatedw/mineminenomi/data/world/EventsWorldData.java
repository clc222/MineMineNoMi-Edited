/*     */ package xyz.pixelatedw.mineminenomi.data.world;
/*     */ import com.google.common.collect.Iterators;
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.SpawnReason;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.nbt.INBT;
/*     */ import net.minecraft.nbt.ListNBT;
/*     */ import net.minecraft.state.properties.StructureMode;
/*     */ import net.minecraft.util.Mirror;
/*     */ import net.minecraft.util.Rotation;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.SoundEvents;
/*     */ import net.minecraft.util.Util;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.ChunkPos;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.world.IServerWorld;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.Biome;
/*     */ import net.minecraft.world.gen.Heightmap;
/*     */ import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
/*     */ import net.minecraft.world.gen.feature.template.PlacementSettings;
/*     */ import net.minecraft.world.gen.feature.template.StructureProcessor;
/*     */ import net.minecraft.world.gen.feature.template.Template;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import net.minecraft.world.storage.WorldSavedData;
/*     */ import net.minecraftforge.fml.server.ServerLifecycleHooks;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.StructuresHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.poi.CountdownEventTarget;
/*     */ import xyz.pixelatedw.mineminenomi.api.poi.NTEventTarget;
/*     */ import xyz.pixelatedw.mineminenomi.api.poi.POIEventTarget;
/*     */ import xyz.pixelatedw.mineminenomi.api.poi.TrackedNPC;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.CaptainEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.GruntEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.NotoriousEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.marines.PacifistaEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.worldgov.CelestialDragonEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModLootTables;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class EventsWorldData extends WorldSavedData {
/*     */   private static final String IDENTIFIER = "mineminenomi-events";
/*     */   private static final int MAX_NOTORIOUS_TARGETS = 5;
/*  68 */   private Set<NTEventTarget> notoriousTarget = new HashSet<>(); private static final int MAX_CARAVANS = 25; private static final int MAX_CELESTIAL_VISITS = 10;
/*  69 */   private Set<POIEventTarget> caravans = new HashSet<>();
/*  70 */   private Set<CountdownEventTarget> busterCalls = new HashSet<>();
/*  71 */   private Set<POIEventTarget> celestialVisits = new HashSet<>();
/*     */   
/*  73 */   private int tick = 0;
/*     */   
/*     */   public EventsWorldData() {
/*  76 */     super("mineminenomi-events");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static EventsWorldData get() {
/*  85 */     if (ServerLifecycleHooks.getCurrentServer() != null) {
/*  86 */       return (EventsWorldData)ServerLifecycleHooks.getCurrentServer().func_241755_D_().func_217481_x().func_215752_a(EventsWorldData::new, "mineminenomi-events");
/*     */     }
/*  88 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public CompoundNBT func_189551_b(CompoundNBT nbt) {
/*  93 */     ListNBT notoriousTargets = new ListNBT();
/*  94 */     for (POIEventTarget poi : this.notoriousTarget) {
/*  95 */       notoriousTargets.add(poi.save());
/*     */     }
/*  97 */     nbt.func_218657_a("notoriousTargets", (INBT)notoriousTargets);
/*     */     
/*  99 */     ListNBT caravans = new ListNBT();
/* 100 */     for (POIEventTarget poi : this.caravans) {
/* 101 */       caravans.add(poi.save());
/*     */     }
/* 103 */     nbt.func_218657_a("caravans", (INBT)caravans);
/*     */     
/* 105 */     ListNBT busterCalls = new ListNBT();
/* 106 */     for (CountdownEventTarget poi : this.busterCalls) {
/* 107 */       busterCalls.add(poi.save());
/*     */     }
/* 109 */     nbt.func_218657_a("busterCalls", (INBT)busterCalls);
/*     */     
/* 111 */     ListNBT visits = new ListNBT();
/* 112 */     for (POIEventTarget poi : this.celestialVisits) {
/* 113 */       visits.add(poi.save());
/*     */     }
/* 115 */     nbt.func_218657_a("celestialVisits", (INBT)visits);
/*     */     
/* 117 */     return nbt;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_76184_a(CompoundNBT nbt) {
/* 122 */     ListNBT notoriousTargets = nbt.func_150295_c("notoriousTargets", 10);
/* 123 */     for (int i = 0; i < notoriousTargets.size(); i++) {
/* 124 */       CompoundNBT entryNBT = notoriousTargets.func_150305_b(i);
/* 125 */       if (!entryNBT.isEmpty()) {
/* 126 */         NTEventTarget poi = new NTEventTarget();
/* 127 */         poi.load(entryNBT);
/* 128 */         this.notoriousTarget.add(poi);
/*     */       } 
/*     */     } 
/*     */     
/* 132 */     ListNBT caravans = nbt.func_150295_c("caravans", 10);
/* 133 */     for (int j = 0; j < caravans.size(); j++) {
/* 134 */       CompoundNBT entryNBT = caravans.func_150305_b(j);
/* 135 */       if (!entryNBT.isEmpty()) {
/* 136 */         POIEventTarget poi = new POIEventTarget();
/* 137 */         setupCaravan(poi);
/* 138 */         poi.load(entryNBT);
/* 139 */         this.caravans.add(poi);
/*     */       } 
/*     */     } 
/*     */     
/* 143 */     ListNBT busterCalls = nbt.func_150295_c("busterCalls", 10);
/* 144 */     for (int k = 0; k < busterCalls.size(); k++) {
/* 145 */       CompoundNBT entryNBT = busterCalls.func_150305_b(k);
/* 146 */       if (!entryNBT.isEmpty()) {
/* 147 */         CountdownEventTarget poi = new CountdownEventTarget();
/* 148 */         setupSpecialBusterCall(poi, poi.getTargetId());
/* 149 */         poi.load(entryNBT);
/* 150 */         this.busterCalls.add(poi);
/*     */       } 
/*     */     } 
/*     */     
/* 154 */     ListNBT visits = nbt.func_150295_c("celestialVisits", 10);
/* 155 */     for (int m = 0; m < visits.size(); m++) {
/* 156 */       CompoundNBT entryNBT = visits.func_150305_b(m);
/* 157 */       if (!entryNBT.isEmpty()) {
/* 158 */         POIEventTarget poi = new POIEventTarget();
/* 159 */         setupCelestialVisit(poi);
/* 160 */         poi.load(entryNBT);
/* 161 */         this.celestialVisits.add(poi);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick(ServerWorld world) {
/* 170 */     this.tick++;
/* 171 */     tickEvents(world);
/* 172 */     startNewEvents(world);
/*     */   }
/*     */   
/*     */   public void startNewEvents(ServerWorld world) {
/* 176 */     if (this.tick % 300 == 0) {
/*     */       
/* 178 */       List<ServerPlayerEntity> players = world.func_217369_A();
/* 179 */       Collections.shuffle(players);
/*     */       
/* 181 */       if (CommonConfig.INSTANCE.canSpawnCelestialVisits()) {
/* 182 */         int newVisits = 0;
/* 183 */         boolean canSpawnCelestialVisit = (world.field_73012_v.nextInt(3) == 0);
/* 184 */         if (canSpawnCelestialVisit && this.celestialVisits.size() < 10) {
/* 185 */           for (ServerPlayerEntity player : players) {
/* 186 */             if (newVisits > 1) {
/*     */               break;
/*     */             }
/*     */             
/* 190 */             Optional<Vector3d> opt = findEventPositionAround(world, player.func_213303_ch(), 100, 1000, false);
/* 191 */             if (opt.isPresent() && canSpawnCVPoint(opt.get()) && 
/* 192 */               addCelestialVisit(world, opt.get())) {
/* 193 */               newVisits++;
/*     */             }
/*     */           } 
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 200 */       if (CommonConfig.INSTANCE.canSpawnCaravans()) {
/* 201 */         int newCaravans = 0;
/* 202 */         if (this.caravans.size() < 25) {
/* 203 */           for (ServerPlayerEntity player : players) {
/* 204 */             if (newCaravans > 3) {
/*     */               break;
/*     */             }
/*     */             
/* 208 */             IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/*     */             
/* 210 */             if (!props.isMarine() && !props.isBountyHunter()) {
/* 211 */               Optional<Vector3d> opt = findEventPositionAround(world, player.func_213303_ch(), 500, 2500, false);
/* 212 */               if (opt.isPresent() && 
/* 213 */                 addCaravan(world, opt.get(), 72000L)) {
/* 214 */                 newCaravans++;
/*     */               }
/*     */             } 
/*     */           } 
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 222 */       if (CommonConfig.INSTANCE.canSpawnNotoriousTargets()) {
/* 223 */         NPCWorldData npcWorldData = NPCWorldData.get();
/* 224 */         int newTargets = 0;
/* 225 */         if (this.notoriousTarget.size() < 5) {
/* 226 */           for (ServerPlayerEntity player : players) {
/* 227 */             if (newTargets > 2) {
/*     */               break;
/*     */             }
/*     */             
/* 231 */             Optional<Vector3d> opt = findEventPositionAround(world, player.func_213303_ch(), 500, 2500, false);
/* 232 */             if (opt.isPresent()) {
/* 233 */               boolean isMarine = world.func_201674_k().nextBoolean();
/* 234 */               Optional<TrackedNPC> npc = npcWorldData.getRandomTrackedMob(isMarine ? ModValues.MARINE : ModValues.PIRATE);
/* 235 */               if (npc.isPresent() && !hasNTEventFor(npc.get())) {
/* 236 */                 addNotoriousTarget(world, opt.get(), 72000L, npc.get());
/* 237 */                 newTargets++;
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void tickEvents(ServerWorld world) {
/* 247 */     boolean hasUpdates = false;
/*     */     
/* 249 */     Iterator<NTEventTarget> ntIter = this.notoriousTarget.iterator();
/* 250 */     while (ntIter.hasNext()) {
/* 251 */       NTEventTarget poi = ntIter.next();
/*     */       
/* 253 */       if (poi.getTrackedNPC() == null) {
/* 254 */         ntIter.remove();
/* 255 */         hasUpdates = true;
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 264 */     Iterator<POIEventTarget> eventsIter = Iterators.concat(new Iterator[] { this.busterCalls.iterator() });
/* 265 */     if (CommonConfig.INSTANCE.canSpawnNotoriousTargets()) {
/* 266 */       eventsIter = Iterators.concat(eventsIter, this.notoriousTarget.iterator());
/*     */     }
/*     */     
/* 269 */     if (CommonConfig.INSTANCE.canSpawnCelestialVisits()) {
/* 270 */       eventsIter = Iterators.concat(eventsIter, this.celestialVisits.iterator());
/*     */     }
/*     */     
/* 273 */     if (CommonConfig.INSTANCE.canSpawnCaravans()) {
/* 274 */       eventsIter = Iterators.concat(eventsIter, this.caravans.iterator());
/*     */     }
/*     */     
/* 277 */     while (eventsIter.hasNext()) {
/* 278 */       POIEventTarget poi = eventsIter.next();
/*     */       
/* 280 */       if (poi.hasExpired(world)) {
/* 281 */         eventsIter.remove();
/* 282 */         hasUpdates = true;
/*     */         
/*     */         continue;
/*     */       } 
/* 286 */       for (ServerPlayerEntity player : world.func_217369_A()) {
/* 287 */         if (poi.shouldTrigger(player)) {
/* 288 */           poi.getTriggerAction().trigger(world, poi);
/* 289 */           eventsIter.remove();
/* 290 */           hasUpdates = true;
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/* 295 */       poi.tick();
/*     */     } 
/*     */     
/* 298 */     if (hasUpdates) {
/* 299 */       func_76185_a();
/*     */     }
/*     */   }
/*     */   
/*     */   public Optional<Vector3d> findEventPositionAround(ServerWorld world, Vector3d pos, int offsetXZ, int bonusOffsetXZ, boolean allowOceans) {
/* 304 */     Optional<Vector3d> pos2 = Optional.empty();
/* 305 */     for (int j = 0; j < 5; ) {
/* 306 */       int extraX = offsetXZ + world.func_201674_k().nextInt(bonusOffsetXZ);
/* 307 */       if (world.func_201674_k().nextBoolean()) {
/* 308 */         extraX *= -1;
/*     */       }
/* 310 */       int extraZ = offsetXZ + world.func_201674_k().nextInt(bonusOffsetXZ);
/* 311 */       if (world.func_201674_k().nextBoolean()) {
/* 312 */         extraZ *= -1;
/*     */       }
/*     */       
/* 315 */       Vector3d vecPos = pos.func_72441_c(extraX, 1.0D, extraZ);
/* 316 */       BlockPos check = new BlockPos(vecPos.field_72450_a, vecPos.field_72448_b, vecPos.field_72449_c);
/*     */       
/* 318 */       if (!allowOceans) {
/* 319 */         Biome.Category category = world.func_226691_t_(check).func_201856_r();
/* 320 */         if (category == Biome.Category.OCEAN) {
/*     */           j++;
/*     */           continue;
/*     */         } 
/*     */       } 
/* 325 */       pos2 = Optional.of(vecPos);
/*     */     } 
/*     */ 
/*     */     
/* 329 */     return pos2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean addCelestialVisit(ServerWorld world, Vector3d pos) {
/* 337 */     POIEventTarget poi = new POIEventTarget(world, pos, 72000L);
/* 338 */     setupCelestialVisit(poi);
/* 339 */     this.celestialVisits.add(poi);
/* 340 */     func_76185_a();
/* 341 */     return true;
/*     */   }
/*     */   
/*     */   private void setupCelestialVisit(POIEventTarget poi) {
/* 345 */     poi.setTriggerAction((world, event) -> {
/*     */           int y = world.func_201676_a(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, (int)event.getPosition().func_82615_a(), (int)event.getPosition().func_82616_c());
/*     */           BlockPos blockPos = new BlockPos(event.getPosition().func_82615_a(), y, event.getPosition().func_82616_c());
/*     */           EntityType<CelestialDragonEntity> dragonEntity = (EntityType<CelestialDragonEntity>)ModEntities.CELESTIAL_DRAGON.get();
/*     */           EntityType<GruntEntity> gruntEntity = (EntityType<GruntEntity>)ModEntities.MARINE_GRUNT.get();
/*     */           dragonEntity.func_220342_a(world, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, blockPos, SpawnReason.EVENT, false, false);
/*     */           for (int i = 0; i < 6; i++) {
/*     */             gruntEntity.func_220342_a(world, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, blockPos, SpawnReason.EVENT, false, false);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean canSpawnCVPoint(Vector3d pos) {
/* 361 */     for (POIEventTarget poi : this.celestialVisits) {
/* 362 */       if (Math.abs(poi.getPosition().func_72436_e(pos)) < 22500.0D) {
/* 363 */         return false;
/*     */       }
/*     */     } 
/* 366 */     return true;
/*     */   }
/*     */   
/*     */   public Set<POIEventTarget> getCelestialVisitsPOIs() {
/* 370 */     return new LinkedHashSet<>(this.celestialVisits);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addSpecialBusterCall(ServerWorld world, LivingEntity target) {
/* 378 */     CountdownEventTarget poi = new CountdownEventTarget(world, target, 72000L, 10);
/* 379 */     setupSpecialBusterCall(poi, target.func_110124_au());
/* 380 */     this.busterCalls.add(poi);
/* 381 */     func_76185_a();
/*     */   }
/*     */   
/*     */   private void setupSpecialBusterCall(CountdownEventTarget poi, UUID targetId) {
/* 385 */     poi.setTriggerAction((world, event) -> {
/*     */           Vector3d targetVec = event.getPosition();
/*     */           BlockPos targetPos = new BlockPos(targetVec);
/*     */           boolean canSpawnInBiome = (world.func_226691_t_(targetPos).func_201856_r() != Biome.Category.OCEAN);
/*     */           if (!canSpawnInBiome) {
/*     */             return;
/*     */           }
/*     */           Optional<TrackedNPC> viceAdmiralEntity = NPCWorldData.get().getRandomTrackedMob(ModValues.MARINE);
/*     */           EntityType<CaptainEntity> captainEntity = (EntityType<CaptainEntity>)ModEntities.MARINE_CAPTAIN.get();
/*     */           EntityType<PacifistaEntity> pacifistaEntity = (EntityType<PacifistaEntity>)ModEntities.PACIFISTA.get();
/*     */           EntityType<GruntEntity> gruntEntity = (EntityType<GruntEntity>)ModEntities.MARINE_GRUNT.get();
/*     */           boolean hasPacifistas = (world.field_73012_v.nextInt(3) == 0);
/*     */           LivingEntity target = poi.getTarget(world).orElse(null);
/*     */           BlockPos spawnPos = WyHelper.findOnGroundSpawnLocation((World)world, captainEntity, targetPos, 10);
/*     */           if (spawnPos == null) {
/*     */             int y = world.func_201676_a(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, (int)event.getPosition().func_82615_a(), (int)event.getPosition().func_82616_c());
/*     */             spawnPos = new BlockPos(event.getPosition().func_82615_a(), y, event.getPosition().func_82616_c());
/*     */           } 
/*     */           if (viceAdmiralEntity.isPresent()) {
/*     */             Vector3d spawnVec = new Vector3d(spawnPos.func_177958_n(), spawnPos.func_177956_o(), spawnPos.func_177952_p());
/*     */             NotoriousEntity viceAdmiral = ((TrackedNPC)viceAdmiralEntity.get()).spawnTrackedMob(world, spawnVec);
/*     */             if (viceAdmiral != null) {
/*     */               viceAdmiral.func_70624_b(target);
/*     */             }
/*     */           } 
/*     */           int i;
/*     */           for (i = 0; i < 4; i++) {
/*     */             spawnPos = WyHelper.findOnGroundSpawnLocation((World)world, gruntEntity, targetPos, 20);
/*     */             if (spawnPos != null) {
/*     */               if (hasPacifistas) {
/*     */                 PacifistaEntity pacifista = (PacifistaEntity)pacifistaEntity.func_220342_a(world, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, spawnPos, SpawnReason.EVENT, false, false);
/*     */                 if (pacifista != null) {
/*     */                   pacifista.func_70624_b(target);
/*     */                 }
/*     */               } else {
/*     */                 CaptainEntity captain = (CaptainEntity)captainEntity.func_220342_a(world, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, spawnPos, SpawnReason.EVENT, false, false);
/*     */                 if (captain != null) {
/*     */                   captain.func_70624_b(target);
/*     */                 }
/*     */               } 
/*     */             }
/*     */           } 
/*     */           for (i = 0; i < 20; i++) {
/*     */             spawnPos = WyHelper.findOnGroundSpawnLocation((World)world, gruntEntity, targetPos, 20);
/*     */             if (spawnPos != null) {
/*     */               GruntEntity grunt = (GruntEntity)gruntEntity.func_220342_a(world, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, spawnPos, SpawnReason.EVENT, false, false);
/*     */               if (grunt != null) {
/*     */                 grunt.setFear(false);
/*     */                 grunt.func_70624_b(target);
/*     */               } 
/*     */             } 
/*     */           } 
/*     */           world.func_184133_a(null, targetPos, SoundEvents.field_219603_Y, SoundCategory.HOSTILE, 1.0F, 1.0F);
/*     */           world.func_184133_a(null, targetPos, (SoundEvent)ModSounds.GENERIC_EXPLOSION.get(), SoundCategory.HOSTILE, 1.0F, 1.0F);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean addCaravan(ServerWorld world, Vector3d pos, long openTime) {
/* 456 */     BlockPos blockPos = new BlockPos(pos);
/* 457 */     Biome.Category category = world.func_226691_t_(blockPos).func_201856_r();
/* 458 */     if (category != Biome.Category.PLAINS && category != Biome.Category.FOREST) {
/* 459 */       return false;
/*     */     }
/*     */     
/* 462 */     if (!WyHelper.isSurfaceFlat((IWorld)world, blockPos.func_177958_n(), blockPos.func_177952_p(), 1)) {
/* 463 */       return false;
/*     */     }
/*     */     
/* 466 */     for (POIEventTarget pOIEventTarget : this.caravans) {
/* 467 */       if (pOIEventTarget.getPosition().func_72436_e(pos) < 2500.0D) {
/* 468 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 472 */     POIEventTarget poi = new POIEventTarget(world, pos, openTime);
/* 473 */     setupCaravan(poi);
/* 474 */     this.caravans.add(poi);
/* 475 */     func_76185_a();
/* 476 */     return true;
/*     */   }
/*     */   
/*     */   private void setupCaravan(POIEventTarget poi) {
/* 480 */     poi.setTriggerAction((world, event) -> {
/*     */           int y = world.func_201676_a(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, (int)event.getPosition().func_82615_a(), (int)event.getPosition().func_82616_c());
/*     */           BlockPos blockPos = new BlockPos(event.getPosition().func_82615_a(), y, event.getPosition().func_82616_c());
/*     */           BlockPos enemiesSpawn = blockPos;
/*     */           int grunts = 2 + world.func_201674_k().nextInt(5);
/*     */           boolean hasViceAdmiral = (world.func_201674_k().nextInt(3) == 0);
/*     */           boolean hasPacifista = false;
/*     */           if (hasViceAdmiral) {
/*     */             grunts += 5;
/*     */             if (world.func_201674_k().nextInt(3) == 0) {
/*     */               hasPacifista = true;
/*     */             }
/*     */           } 
/*     */           Optional<TrackedNPC> trackedNpc = NPCWorldData.get().getRandomTrackedMob(ModValues.MARINE);
/*     */           if (!trackedNpc.isPresent()) {
/*     */             hasViceAdmiral = false;
/*     */           }
/*     */           PlacementSettings placement = (new PlacementSettings()).func_186214_a(Mirror.NONE).func_186220_a(Rotation.NONE).func_186222_a(false).func_186218_a((ChunkPos)null);
/*     */           placement.func_215219_b().func_215222_a((StructureProcessor)BlockIgnoreStructureProcessor.field_215206_c).func_189950_a(new Random(Util.func_211177_b()));
/*     */           Optional<Template> template = WyHelper.getNBTStructure(world, "unaligned/caravan", placement);
/*     */           if (template.isPresent()) {
/*     */             BlockPos templatePos = blockPos.func_177977_b();
/*     */             ((Template)template.get()).func_237144_a_((IServerWorld)world, templatePos, placement, new Random(Util.func_211177_b()));
/*     */             for (Template.BlockInfo blockInfo : ((Template)template.get()).func_215381_a(templatePos, placement, Blocks.field_185779_df)) {
/*     */               if (blockInfo.field_186244_c != null) {
/*     */                 StructureMode structuremode = StructureMode.valueOf(blockInfo.field_186244_c.func_74779_i("mode"));
/*     */                 if (structuremode == StructureMode.DATA) {
/*     */                   String function = blockInfo.field_186244_c.func_74779_i("metadata");
/*     */                   BlockPos functionPos = blockInfo.field_186242_a;
/*     */                   if (function.equals("caravan_loot")) {
/*     */                     StructuresHelper.spawnLoot((IWorld)world, functionPos, hasViceAdmiral ? ModLootTables.HARD_CARAVAN_CHEST : ModLootTables.EASY_CARAVAN_CHEST);
/*     */                     continue;
/*     */                   } 
/*     */                   if (function.equals("caravan_spawn")) {
/*     */                     enemiesSpawn = functionPos;
/*     */                   }
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */           int i;
/*     */           for (i = 0; i < grunts; i++) {
/*     */             GruntEntity entity = (GruntEntity)((EntityType)ModEntities.MARINE_GRUNT.get()).func_220342_a(world, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, enemiesSpawn, SpawnReason.EVENT, false, false);
/*     */             if (entity != null) {
/*     */               entity.setFear(false);
/*     */             }
/*     */           } 
/*     */           if (hasViceAdmiral) {
/*     */             NotoriousEntity entity = ((TrackedNPC)trackedNpc.get()).createTrackedMob((World)world);
/*     */             entity.func_70107_b(enemiesSpawn.func_177958_n(), enemiesSpawn.func_177956_o(), enemiesSpawn.func_177952_p());
/*     */             world.func_217376_c((Entity)entity);
/*     */           } else {
/*     */             CaptainEntity entity = (CaptainEntity)((EntityType)ModEntities.MARINE_CAPTAIN.get()).func_220342_a(world, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, enemiesSpawn, SpawnReason.EVENT, false, false);
/*     */             if (entity != null) {
/*     */               entity.setFear(false);
/*     */             }
/*     */           } 
/*     */           if (hasPacifista) {
/*     */             PacifistaEntity entity = (PacifistaEntity)((EntityType)ModEntities.PACIFISTA.get()).func_220342_a(world, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, enemiesSpawn, SpawnReason.EVENT, false, false);
/*     */             if (entity != null) {
/*     */               entity.setFear(false);
/*     */             }
/*     */           } 
/*     */           for (i = 0; i < 3; i++) {
/*     */             EntityType.field_200756_av.func_220342_a(world, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, enemiesSpawn, SpawnReason.EVENT, false, false);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set<POIEventTarget> getCaravanPOIs() {
/* 568 */     return new LinkedHashSet<>(this.caravans);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addNotoriousTarget(ServerWorld world, Vector3d pos, long openTime, TrackedNPC tracked) {
/* 576 */     NPCWorldData.get().updateTrackedMob(world, tracked);
/*     */     
/* 578 */     NTEventTarget poi = new NTEventTarget(world, pos, openTime, tracked);
/* 579 */     this.notoriousTarget.add(poi);
/* 580 */     func_76185_a();
/*     */   }
/*     */   
/*     */   public Set<NTEventTarget> getNotoriousTargets() {
/* 584 */     return new LinkedHashSet<>(this.notoriousTarget);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasNTEventAt(Vector3d pos) {
/* 592 */     return this.notoriousTarget.stream().anyMatch(event -> event.getPosition().equals(pos));
/*     */   }
/*     */   
/*     */   public boolean hasNTEventFor(TrackedNPC npc) {
/* 596 */     return this.notoriousTarget.stream().anyMatch(event -> event.getTrackedNPC().equals(npc));
/*     */   }
/*     */   
/*     */   public boolean hasNTEventFor(UUID id) {
/* 600 */     return this.notoriousTarget.stream().filter(event -> (event.getTrackedNPC() != null)).anyMatch(event -> event.getTrackedNPC().getUUID().equals(id));
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\world\EventsWorldData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */