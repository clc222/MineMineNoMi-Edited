/*     */ package xyz.pixelatedw.mineminenomi.api.helpers;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.function.Function;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.tileentity.ChestTileEntity;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MutableBoundingBox;
/*     */ import net.minecraft.util.math.vector.Vector3i;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.gen.feature.NoFeatureConfig;
/*     */ import net.minecraft.world.gen.feature.structure.Structure;
/*     */ import net.minecraft.world.gen.feature.structure.StructureStart;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import net.minecraftforge.fml.RegistryObject;
/*     */ import net.minecraftforge.registries.ForgeRegistries;
/*     */ import xyz.pixelatedw.mineminenomi.api.BlockQueue;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.WeightedList;
/*     */ import xyz.pixelatedw.mineminenomi.blocks.tileentities.CustomSpawnerTileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModStructures;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ import xyz.pixelatedw.mineminenomi.world.features.structures.OPStructure;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StructuresHelper
/*     */ {
/*  46 */   public static final List<BlockPos> SPAWNED_STRUCTURES = new ArrayList<>();
/*  47 */   public static final int[] STRUCTURES_COUNT = new int[15];
/*     */   
/*  49 */   private static final ArrayList<RegistryObject<Structure<NoFeatureConfig>>> SHIP_STRUCTURES = Lists.newArrayList((Object[])new RegistryObject[] { ModStructures.GHOST_SHIP, ModStructures.MARINE_BATTLESHIP, ModStructures.PIRATE_LARGE_SHIP, ModStructures.PIRATE_MEDIUM_SHIP, ModStructures.PIRATE_SMALL_SHIP });
/*  50 */   private static final HashMap<StructureSpawnType, Map<StructureFaction, List<EntityType>>> SPAWN_TYPES = new HashMap<>();
/*     */   
/*     */   static {
/*  53 */     SPAWN_TYPES.put(StructureSpawnType.GRUNT, 
/*  54 */         ImmutableMap.of(StructureFaction.MARINE, 
/*  55 */           Lists.newArrayList((Object[])new EntityType[] { (EntityType)ModEntities.MARINE_GRUNT.get() }, ), StructureFaction.PIRATE, 
/*  56 */           Lists.newArrayList((Object[])new EntityType[] { (EntityType)ModEntities.PIRATE_GRUNT.get() }, ), StructureFaction.BANDIT, 
/*  57 */           Lists.newArrayList((Object[])new EntityType[] { (EntityType)ModEntities.BANDIT_GRUNT.get() }, ), StructureFaction.REVOLUTIONARY, 
/*  58 */           Lists.newArrayList()));
/*     */ 
/*     */     
/*  61 */     SPAWN_TYPES.put(StructureSpawnType.CAPTAIN, 
/*  62 */         ImmutableMap.of(StructureFaction.MARINE, 
/*  63 */           Lists.newArrayList((Object[])new EntityType[] { (EntityType)ModEntities.MARINE_CAPTAIN.get() }, ), StructureFaction.PIRATE, 
/*  64 */           Lists.newArrayList((Object[])new EntityType[] { (EntityType)ModEntities.PIRATE_CAPTAIN.get() }, ), StructureFaction.BANDIT, 
/*  65 */           Lists.newArrayList(), StructureFaction.REVOLUTIONARY, 
/*  66 */           Lists.newArrayList()));
/*     */ 
/*     */     
/*  69 */     SPAWN_TYPES.put(StructureSpawnType.BRUTE, 
/*  70 */         ImmutableMap.of(StructureFaction.MARINE, 
/*  71 */           Lists.newArrayList((Object[])new EntityType[] { (EntityType)ModEntities.MARINE_BRUTE.get() }, ), StructureFaction.PIRATE, 
/*  72 */           Lists.newArrayList((Object[])new EntityType[] { (EntityType)ModEntities.PIRATE_BRUTE.get() }, ), StructureFaction.BANDIT, 
/*  73 */           Lists.newArrayList((Object[])new EntityType[] { (EntityType)ModEntities.BANDIT_BRUTE.get() }, ), StructureFaction.REVOLUTIONARY, 
/*  74 */           Lists.newArrayList()));
/*     */ 
/*     */     
/*  77 */     SPAWN_TYPES.put(StructureSpawnType.SNIPER, 
/*  78 */         ImmutableMap.of(StructureFaction.MARINE, 
/*  79 */           Lists.newArrayList((Object[])new EntityType[] { (EntityType)ModEntities.MARINE_SNIPER.get() }, ), StructureFaction.PIRATE, 
/*  80 */           Lists.newArrayList(), StructureFaction.BANDIT, 
/*  81 */           Lists.newArrayList((Object[])new EntityType[] { (EntityType)ModEntities.BANDIT_SNIPER.get() }, ), StructureFaction.REVOLUTIONARY, 
/*  82 */           Lists.newArrayList()));
/*     */ 
/*     */     
/*  85 */     SPAWN_TYPES.put(StructureSpawnType.TRADER, 
/*  86 */         ImmutableMap.of(StructureFaction.MARINE, 
/*  87 */           Lists.newArrayList((Object[])new EntityType[] { (EntityType)ModEntities.MARINE_TRADER.get() }, ), StructureFaction.PIRATE, 
/*  88 */           Lists.newArrayList((Object[])new EntityType[] { (EntityType)ModEntities.PIRATE_TRADER.get() }, ), StructureFaction.SKYPIEAN, 
/*  89 */           Lists.newArrayList((Object[])new EntityType[] { (EntityType)ModEntities.SKYPIEAN_TRADER.get() })));
/*     */ 
/*     */     
/*  92 */     SPAWN_TYPES.put(StructureSpawnType.CIVILIAN, 
/*  93 */         ImmutableMap.of(StructureFaction.SKYPIEAN, 
/*  94 */           Lists.newArrayList((Object[])new EntityType[] { (EntityType)ModEntities.SKYPIEAN_CIVILIAN.get() })));
/*     */   }
/*     */ 
/*     */   
/*     */   public static void spawnMobs(IWorld world, BlockPos pos, StructureFaction faction, StructureSpawnType type, int min, int max) {
/*  99 */     spawnMobs(world, pos, faction, type, (int)WyHelper.randomWithRange(min, max));
/*     */   }
/*     */   
/*     */   public static void spawnMobs(IWorld world, BlockPos pos, StructureFaction faction, StructureSpawnType type, int amount) {
/* 103 */     if (world == null) {
/*     */       return;
/*     */     }
/* 106 */     List<EntityType> factionList = (List<EntityType>)((Map)SPAWN_TYPES.get(type)).get(faction);
/*     */     
/* 108 */     if (factionList == null || factionList.size() <= 0) {
/*     */       return;
/*     */     }
/* 111 */     EntityType spawnType = factionList.get((int)WyHelper.randomWithRange(0, factionList.size() - 1));
/*     */     
/* 113 */     if (spawnType == null) {
/*     */       return;
/*     */     }
/* 116 */     world.func_180501_a(pos, ((Block)ModBlocks.CUSTOM_SPAWNER.get()).func_176223_P(), 3);
/* 117 */     if (world.func_175625_s(pos) instanceof CustomSpawnerTileEntity) {
/* 118 */       CustomSpawnerTileEntity spawner = (CustomSpawnerTileEntity)world.func_175625_s(pos);
/* 119 */       if (spawner != null) {
/* 120 */         spawner.setSpawnerLimit(amount);
/* 121 */         spawner.setSpawnerMob(spawnType);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void spawnLoot(IWorld world, BlockPos pos, ResourceLocation lootTable) {
/* 127 */     world.func_180501_a(pos, Blocks.field_150350_a.func_176223_P(), 3);
/* 128 */     TileEntity tile = world.func_175625_s(pos.func_177977_b());
/* 129 */     if (tile instanceof ChestTileEntity) {
/* 130 */       ((ChestTileEntity)tile).func_189404_a(lootTable, world.func_201674_k().nextLong());
/*     */     }
/*     */   }
/*     */   
/*     */   public static boolean isInsideShip(ServerWorld world, BlockPos pos) {
/* 135 */     boolean flag = false;
/* 136 */     for (RegistryObject<Structure<NoFeatureConfig>> reg : SHIP_STRUCTURES) {
/* 137 */       StructureStart struct = world.func_241112_a_().func_235010_a_(pos, false, (Structure)reg.get());
/* 138 */       if (struct != StructureStart.field_214630_a) {
/* 139 */         flag = true;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 144 */     return flag;
/*     */   }
/*     */   
/*     */   public static boolean isInsideModStructure(ServerWorld world, BlockPos pos, @Nullable StructureFaction faction) {
/* 148 */     boolean flag = false;
/* 149 */     for (Structure<?> reg : (Iterable<Structure<?>>)ModStructures.REGISTERED_STRUCTURES.keySet()) {
/* 150 */       StructureStart<?> struct = world.func_241112_a_().func_235010_a_(pos, false, reg);
/* 151 */       if (struct != StructureStart.field_214630_a && (
/* 152 */         faction == null || (reg instanceof OPStructure && ((OPStructure)reg).getFaction().equals(faction)))) {
/* 153 */         flag = true;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/*     */     
/* 159 */     return flag;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static StructureStart getStructureAt(ServerWorld world, BlockPos pos) {
/* 168 */     for (Structure<?> reg : (Iterable<Structure<?>>)ForgeRegistries.STRUCTURE_FEATURES) {
/* 169 */       StructureStart<?> struct = world.func_241112_a_().func_235010_a_(pos, false, reg);
/* 170 */       if (struct != StructureStart.field_214630_a) {
/* 171 */         return struct;
/*     */       }
/*     */     } 
/* 174 */     return null;
/*     */   }
/*     */   
/*     */   public enum StructureFaction {
/* 178 */     MARINE,
/* 179 */     PIRATE,
/* 180 */     BANDIT,
/* 181 */     REVOLUTIONARY,
/* 182 */     SKYPIEAN,
/* 183 */     NEUTRAL;
/*     */ 
/*     */     
/*     */     @Nullable
/*     */     public static StructureFaction from(ResourceLocation faction) {
/* 188 */       if (faction.equals(ModValues.MARINE))
/* 189 */         return MARINE; 
/* 190 */       if (faction.equals(ModValues.PIRATE))
/* 191 */         return PIRATE; 
/* 192 */       if (faction.equals(ModValues.BANDIT))
/* 193 */         return BANDIT; 
/* 194 */       if (faction.equals(ModValues.REVOLUTIONARY)) {
/* 195 */         return REVOLUTIONARY;
/*     */       }
/* 197 */       return NEUTRAL;
/*     */     }
/*     */   }
/*     */   
/*     */   public enum StructureSpawnType {
/* 202 */     GRUNT,
/* 203 */     CAPTAIN,
/* 204 */     SNIPER,
/* 205 */     BRUTE,
/* 206 */     CIVILIAN,
/* 207 */     TRADER;
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<BlockPos> createFilledSphere(World world, BlockPos origin, int radiusXZ, int radiusY, BlockState state, int flags, @Nullable BlockProtectionRule rule) {
/* 212 */     int x0 = origin.func_177958_n();
/* 213 */     int y0 = origin.func_177956_o();
/* 214 */     int z0 = origin.func_177952_p();
/*     */     
/* 216 */     BlockPos.Mutable mutpos = new BlockPos.Mutable();
/* 217 */     List<BlockPos> blockPositions = new ArrayList<>(); double y;
/* 218 */     for (y = (y0 - radiusY); y < (y0 + radiusY); y++) {
/* 219 */       double x; for (x = (x0 - radiusXZ); x < (x0 + radiusXZ); x++) {
/* 220 */         double z; for (z = (z0 - radiusXZ); z < (z0 + radiusXZ); z++) {
/* 221 */           double distance = (x0 - x) * (x0 - x) + (z0 - z) * (z0 - z) + (y0 - y) * (y0 - y);
/* 222 */           if (distance < (radiusXZ * radiusY)) {
/* 223 */             mutpos.func_189532_c(x, y, z);
/* 224 */             if (AbilityHelper.placeBlockIfAllowed(world, (BlockPos)mutpos, state, flags, rule)) {
/* 225 */               blockPositions.add(mutpos.func_185334_h());
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 232 */     return blockPositions;
/*     */   }
/*     */   
/*     */   public static List<BlockPos> createHollowSphere(World world, BlockPos origin, int radiusXZ, int radiusY, int thickness, BlockState state, int flags, @Nullable BlockProtectionRule rule) {
/* 236 */     int x0 = origin.func_177958_n();
/* 237 */     int y0 = origin.func_177956_o();
/* 238 */     int z0 = origin.func_177952_p();
/*     */     
/* 240 */     BlockPos.Mutable mutpos = new BlockPos.Mutable();
/* 241 */     List<BlockPos> blockPositions = new ArrayList<>();
/* 242 */     for (int y = y0 - radiusY; y < y0 + radiusY; y++) {
/* 243 */       for (int x = x0 - radiusXZ; x < x0 + radiusXZ; x++) {
/* 244 */         for (int z = z0 - radiusXZ; z < z0 + radiusXZ; z++) {
/* 245 */           double distance = ((x0 - x) * (x0 - x) + (z0 - z) * (z0 - z) + (y0 - y) * (y0 - y));
/*     */           
/* 247 */           if (distance < (radiusXZ * radiusY) && distance >= ((radiusXZ - thickness) * (radiusXZ - thickness))) {
/* 248 */             mutpos.func_181079_c(x, y, z);
/* 249 */             if (AbilityHelper.placeBlockIfAllowed(world, (BlockPos)mutpos, state, flags, rule)) {
/* 250 */               blockPositions.add(mutpos.func_185334_h());
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 257 */     return blockPositions;
/*     */   }
/*     */   
/*     */   public static void calcHollowSphere(World world, BlockPos origin, int radiusXZ, int radiusY, int thickness, BlockState state, BlockQueue queue) {
/* 261 */     int x0 = origin.func_177958_n();
/* 262 */     int y0 = origin.func_177956_o();
/* 263 */     int z0 = origin.func_177952_p();
/*     */     
/* 265 */     BlockPos.Mutable mutpos = new BlockPos.Mutable();
/* 266 */     for (int y = y0 - radiusY; y < y0 + radiusY; y++) {
/* 267 */       for (int x = x0 - radiusXZ; x < x0 + radiusXZ; x++) {
/* 268 */         for (int z = z0 - radiusXZ; z < z0 + radiusXZ; z++) {
/* 269 */           double distance = ((x0 - x) * (x0 - x) + (z0 - z) * (z0 - z) + (y0 - y) * (y0 - y));
/*     */           
/* 271 */           if (distance < (radiusXZ * radiusY) && distance >= ((radiusXZ - thickness) * (radiusXZ - thickness))) {
/* 272 */             mutpos.func_181079_c(x, y, z);
/* 273 */             if (AbilityHelper.canPlaceBlock(world, mutpos.func_177958_n(), mutpos.func_177956_o(), mutpos.func_177952_p(), state, 0, null)) {
/* 274 */               queue.add(mutpos.func_185334_h(), state);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void calcHollowSphere(World world, BlockPos origin, int radiusXZ, int radiusY, int thickness, Function<BlockPos, BlockState> state, BlockQueue queue) {
/* 283 */     int x0 = origin.func_177958_n();
/* 284 */     int y0 = origin.func_177956_o();
/* 285 */     int z0 = origin.func_177952_p();
/*     */     
/* 287 */     BlockPos.Mutable mutpos = new BlockPos.Mutable();
/* 288 */     for (int y = y0 - radiusY; y < y0 + radiusY; y++) {
/* 289 */       for (int x = x0 - radiusXZ; x < x0 + radiusXZ; x++) {
/* 290 */         for (int z = z0 - radiusXZ; z < z0 + radiusXZ; z++) {
/* 291 */           double distance = ((x0 - x) * (x0 - x) + (z0 - z) * (z0 - z) + (y0 - y) * (y0 - y));
/*     */           
/* 293 */           if (distance < (radiusXZ * radiusY) && distance >= ((radiusXZ - thickness) * (radiusXZ - thickness))) {
/* 294 */             mutpos.func_181079_c(x, y, z);
/* 295 */             BlockPos pos = mutpos.func_185334_h();
/* 296 */             BlockState blockState = state.apply(pos);
/* 297 */             if (AbilityHelper.canPlaceBlock(world, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), blockState, 0, null)) {
/* 298 */               queue.add(pos, blockState);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static List<BlockPos> createCenteredFilledCircle(World world, BlockPos origin, int radiusXZ, int height, BlockState state, int flags, @Nullable BlockProtectionRule rule) {
/* 307 */     WeightedList<BlockState> list = new WeightedList(new Object[] { state });
/* 308 */     return createCenteredFilledCircle(world, origin, radiusXZ, height, list, flags, rule);
/*     */   }
/*     */   
/*     */   public static void calcCenteredFilledCircle(World world, BlockPos origin, int radiusXZ, int height, BlockState state, BlockQueue queue) {
/* 312 */     WeightedList<BlockState> list = new WeightedList(new Object[] { state });
/* 313 */     calcCenteredFilledCircle(world, origin, radiusXZ, height, list, queue);
/*     */   }
/*     */   
/*     */   public static List<BlockPos> createCenteredFilledCircle(World world, BlockPos origin, int radiusXZ, int height, WeightedList<BlockState> list, int flags, @Nullable BlockProtectionRule rule) {
/* 317 */     int x0 = origin.func_177958_n();
/* 318 */     int y0 = origin.func_177956_o();
/* 319 */     int z0 = origin.func_177952_p();
/*     */     
/* 321 */     BlockPos.Mutable mutpos = new BlockPos.Mutable();
/* 322 */     List<BlockPos> blockPositions = new ArrayList<>(); double y;
/* 323 */     for (y = 0.0D; y < height; y++) {
/* 324 */       double x; for (x = (x0 - radiusXZ); x < (x0 + radiusXZ); x++) {
/* 325 */         double z; for (z = (z0 - radiusXZ); z < (z0 + radiusXZ); z++) {
/* 326 */           double distance = (x0 - x) * (x0 - x) + (z0 - z) * (z0 - z);
/* 327 */           if (distance < (radiusXZ * radiusXZ)) {
/* 328 */             mutpos.func_189532_c(x, y0 + y, z);
/* 329 */             BlockState state = (BlockState)list.pick(world.func_201674_k());
/* 330 */             if (AbilityHelper.placeBlockIfAllowed(world, (BlockPos)mutpos, state, flags, rule)) {
/* 331 */               blockPositions.add(mutpos.func_185334_h());
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 338 */     return blockPositions;
/*     */   }
/*     */   
/*     */   public static void calcCenteredFilledCircle(World world, BlockPos origin, int radiusXZ, int height, WeightedList<BlockState> list, BlockQueue queue) {
/* 342 */     int x0 = origin.func_177958_n();
/* 343 */     int y0 = origin.func_177956_o();
/* 344 */     int z0 = origin.func_177952_p();
/*     */     
/* 346 */     BlockPos.Mutable mutpos = new BlockPos.Mutable();
/*     */     double y;
/* 348 */     for (y = (height - 1); y >= 0.0D; y--) {
/* 349 */       double x; for (x = (x0 - radiusXZ); x < (x0 + radiusXZ); x++) {
/* 350 */         double z; for (z = (z0 - radiusXZ); z < (z0 + radiusXZ); z++) {
/* 351 */           double distance = (x0 - x) * (x0 - x) + (z0 - z) * (z0 - z);
/* 352 */           if (distance < (radiusXZ * radiusXZ)) {
/* 353 */             mutpos.func_189532_c(x, y0 + y, z);
/* 354 */             BlockState state = (BlockState)list.pick(world.func_201674_k());
/* 355 */             if (AbilityHelper.canPlaceBlock(world, mutpos.func_177958_n(), mutpos.func_177956_o(), mutpos.func_177952_p(), state, 0, null)) {
/* 356 */               queue.add(mutpos.func_185334_h(), state);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List<BlockPos> fillCube(World world, BlockPos from, BlockPos to, BlockState state, int flags, @Nullable BlockProtectionRule rule) {
/* 368 */     MutableBoundingBox bb = new MutableBoundingBox((Vector3i)from, (Vector3i)to);
/* 369 */     List<BlockPos> blockPositions = new ArrayList<>();
/* 370 */     for (BlockPos blockpos : BlockPos.func_191531_b(bb.field_78897_a, bb.field_78895_b, bb.field_78896_c, bb.field_78893_d, bb.field_78894_e, bb.field_78892_f)) {
/* 371 */       if (AbilityHelper.placeBlockIfAllowed(world, blockpos, state, flags, rule)) {
/* 372 */         blockPositions.add(blockpos.func_185334_h());
/*     */       }
/*     */     } 
/* 375 */     return blockPositions;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void calcFillCube(World world, BlockPos from, BlockPos to, BlockState state, BlockQueue queue) {
/* 382 */     MutableBoundingBox bb = new MutableBoundingBox((Vector3i)from, (Vector3i)to);
/*     */     
/* 384 */     BlockPos.Mutable mutpos = new BlockPos.Mutable(); double y;
/* 385 */     for (y = bb.field_78894_e; y >= bb.field_78895_b; y--) {
/* 386 */       double x; for (x = bb.field_78897_a; x <= bb.field_78893_d; x++) {
/* 387 */         double z; for (z = bb.field_78896_c; z <= bb.field_78892_f; z++) {
/* 388 */           mutpos.func_189532_c(x, y, z);
/* 389 */           if (AbilityHelper.canPlaceBlock(world, mutpos.func_177958_n(), mutpos.func_177956_o(), mutpos.func_177952_p(), state, 0, null)) {
/* 390 */             queue.add(mutpos.func_185334_h(), state);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List<BlockPos> hollowCube(World world, BlockPos from, BlockPos to, int thickness, BlockState state, int flags, @Nullable BlockProtectionRule rule) {
/* 401 */     MutableBoundingBox bb = new MutableBoundingBox((Vector3i)from, (Vector3i)to);
/* 402 */     return hollowCube(world, bb, thickness, state, flags, rule);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void calcHollowCube(World world, BlockPos from, BlockPos to, int thickness, BlockState state, BlockQueue queue) {
/* 409 */     MutableBoundingBox bb = new MutableBoundingBox((Vector3i)from, (Vector3i)to);
/* 410 */     calcHollowCube(world, bb, thickness, state, queue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List<BlockPos> hollowCube(World world, MutableBoundingBox bb, int thickness, BlockState state, int flags, @Nullable BlockProtectionRule rule) {
/* 417 */     List<BlockPos> blockPositions = new ArrayList<>();
/*     */     
/* 419 */     for (BlockPos blockpos : BlockPos.func_191531_b(bb.field_78897_a, bb.field_78895_b, bb.field_78896_c, bb.field_78893_d, bb.field_78894_e, bb.field_78892_f)) {
/*     */       
/* 421 */       boolean isEdge = (blockpos.func_177958_n() < bb.field_78897_a + thickness || blockpos.func_177958_n() > bb.field_78893_d - thickness || blockpos.func_177956_o() < bb.field_78895_b + thickness || blockpos.func_177956_o() > bb.field_78894_e - thickness || blockpos.func_177952_p() < bb.field_78896_c + thickness || blockpos.func_177952_p() > bb.field_78892_f - thickness);
/* 422 */       if (isEdge && AbilityHelper.placeBlockIfAllowed(world, blockpos, state, flags, rule)) {
/* 423 */         blockPositions.add(blockpos.func_185334_h());
/*     */       }
/*     */     } 
/* 426 */     return blockPositions;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void calcHollowCube(World world, MutableBoundingBox bb, int thickness, BlockState state, BlockQueue queue) {
/* 433 */     BlockPos.Mutable mutpos = new BlockPos.Mutable(); double y;
/* 434 */     for (y = bb.field_78894_e; y >= bb.field_78895_b; y--) {
/* 435 */       double x; for (x = bb.field_78897_a; x <= bb.field_78893_d; x++) {
/* 436 */         double z; for (z = bb.field_78896_c; z <= bb.field_78892_f; z++) {
/* 437 */           mutpos.func_189532_c(x, y, z);
/*     */           
/* 439 */           boolean isEdge = (mutpos.func_177958_n() < bb.field_78897_a + thickness || mutpos.func_177958_n() > bb.field_78893_d - thickness || mutpos.func_177956_o() < bb.field_78895_b + thickness || mutpos.func_177956_o() > bb.field_78894_e - thickness || mutpos.func_177952_p() < bb.field_78896_c + thickness || mutpos.func_177952_p() > bb.field_78892_f - thickness);
/* 440 */           if (isEdge && 
/* 441 */             AbilityHelper.canPlaceBlock(world, mutpos.func_177958_n(), mutpos.func_177956_o(), mutpos.func_177952_p(), state, 0, null)) {
/* 442 */             queue.add(mutpos.func_185334_h(), state);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
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
/*     */   public static List<BlockPos> createHollowCube(World world, BlockPos origin, int sizeX, int sizeY, int sizeZ, int thickness, BlockState state, int flags, @Nullable BlockProtectionRule rule) {
/* 459 */     int x0 = origin.func_177958_n();
/* 460 */     int y0 = origin.func_177956_o();
/* 461 */     int z0 = origin.func_177952_p();
/*     */     
/* 463 */     int actualXSize = (int)Math.ceil(sizeX / 2.0D);
/* 464 */     int actualYSize = (int)Math.ceil(sizeY / 2.0D);
/* 465 */     int actualZSize = (int)Math.ceil(sizeZ / 2.0D);
/*     */     
/* 467 */     BlockPos.Mutable mutpos = new BlockPos.Mutable();
/* 468 */     List<BlockPos> blockPositions = new ArrayList<>();
/* 469 */     for (int x = -actualXSize; x < actualXSize; x++) {
/* 470 */       for (int y = -actualYSize; y < actualYSize; y++) {
/* 471 */         for (int z = -actualZSize; z < actualZSize; z++) {
/* 472 */           if (x < -actualXSize + thickness || x >= actualXSize - thickness || y < -actualYSize + thickness || y >= actualYSize - thickness || z < -actualZSize + thickness || z >= actualZSize - thickness) {
/* 473 */             mutpos.func_181079_c(x0 + x, y0 + y, z0 + z);
/* 474 */             if (AbilityHelper.placeBlockIfAllowed(world, (BlockPos)mutpos, state, flags, rule)) {
/* 475 */               blockPositions.add(mutpos.func_185334_h());
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 481 */     return blockPositions;
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
/*     */   public static List<BlockPos> createCenteredFilledCube(World world, BlockPos origin, int sizeX, int sizeY, int sizeZ, BlockState state, int flag, @Nullable BlockProtectionRule rule) {
/* 494 */     int x0 = origin.func_177958_n();
/* 495 */     int y0 = origin.func_177956_o();
/* 496 */     int z0 = origin.func_177952_p();
/*     */     
/* 498 */     boolean isNegative = (sizeY < 0);
/* 499 */     sizeY = Math.abs(sizeY);
/*     */     
/* 501 */     int actualXSize = (int)Math.ceil(sizeX / 2.0D);
/* 502 */     int actualZSize = (int)Math.ceil(sizeZ / 2.0D);
/*     */     
/* 504 */     BlockPos.Mutable mutpos = new BlockPos.Mutable();
/* 505 */     List<BlockPos> blockPositions = new ArrayList<>();
/* 506 */     for (int x = -actualXSize; x < actualXSize; x++) {
/* 507 */       for (int y = 0; y < sizeY; y++) {
/* 508 */         for (int z = -actualZSize; z < actualZSize; z++) {
/* 509 */           mutpos.func_181079_c(x0 + x, isNegative ? (y0 - y) : (y0 + y), z0 + z);
/* 510 */           if (AbilityHelper.placeBlockIfAllowed(world, (BlockPos)mutpos, state, flag, rule)) {
/* 511 */             blockPositions.add(mutpos.func_185334_h());
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 517 */     return blockPositions;
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
/*     */   public static List<BlockPos> calcCenteredFilledCube(World world, BlockPos origin, int sizeX, int sizeY, int sizeZ, BlockState state, BlockQueue queue) {
/* 530 */     int x0 = origin.func_177958_n();
/* 531 */     int y0 = origin.func_177956_o();
/* 532 */     int z0 = origin.func_177952_p();
/*     */     
/* 534 */     boolean isNegative = (sizeY < 0);
/* 535 */     sizeY = Math.abs(sizeY);
/*     */     
/* 537 */     int actualXSize = (int)Math.ceil(sizeX / 2.0D);
/* 538 */     int actualZSize = (int)Math.ceil(sizeZ / 2.0D);
/*     */     
/* 540 */     BlockPos.Mutable mutpos = new BlockPos.Mutable();
/* 541 */     List<BlockPos> blockPositions = new ArrayList<>();
/* 542 */     for (int y = sizeY - 1; y >= 0; y--) {
/* 543 */       for (int x = -actualXSize; x < actualXSize; x++) {
/* 544 */         for (int z = -actualZSize; z < actualZSize; z++) {
/* 545 */           mutpos.func_181079_c(x0 + x, isNegative ? (y0 - y) : (y0 + y), z0 + z);
/* 546 */           if (AbilityHelper.canPlaceBlock(world, mutpos.func_177958_n(), mutpos.func_177956_o(), mutpos.func_177952_p(), state, 0, null)) {
/* 547 */             queue.add(mutpos.func_185334_h(), state);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 553 */     return blockPositions;
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
/*     */   public static List<BlockPos> createFilledCube(World world, BlockPos origin, int sizeX, int sizeY, int sizeZ, BlockState state, int flag, @Nullable BlockProtectionRule rule) {
/* 565 */     int x0 = origin.func_177958_n();
/* 566 */     int y0 = origin.func_177956_o();
/* 567 */     int z0 = origin.func_177952_p();
/*     */     
/* 569 */     int actualXSize = (int)Math.ceil(sizeX / 2.0D);
/* 570 */     int actualYSize = (int)Math.ceil(sizeY / 2.0D);
/* 571 */     int actualZSize = (int)Math.ceil(sizeZ / 2.0D);
/*     */     
/* 573 */     BlockPos.Mutable mutpos = new BlockPos.Mutable();
/* 574 */     List<BlockPos> blockPositions = new ArrayList<>();
/* 575 */     for (int x = -actualXSize; x < actualXSize; x++) {
/* 576 */       for (int y = -actualYSize; y < actualYSize; y++) {
/* 577 */         for (int z = -actualZSize; z < actualZSize; z++) {
/* 578 */           mutpos.func_181079_c(x0 + x, y0 + y, z0 + z);
/* 579 */           if (AbilityHelper.placeBlockIfAllowed(world, (BlockPos)mutpos, state, flag, rule)) {
/* 580 */             blockPositions.add(mutpos.func_185334_h());
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 586 */     return blockPositions;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\helpers\StructuresHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */