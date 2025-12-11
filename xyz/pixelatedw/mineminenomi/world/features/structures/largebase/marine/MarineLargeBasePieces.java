/*     */ package xyz.pixelatedw.mineminenomi.world.features.structures.largebase.marine;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.state.Property;
/*     */ import net.minecraft.state.properties.BlockStateProperties;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.Direction;
/*     */ import net.minecraft.util.Mirror;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.Rotation;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.ChunkPos;
/*     */ import net.minecraft.util.math.MutableBoundingBox;
/*     */ import net.minecraft.world.ISeedReader;
/*     */ import net.minecraft.world.IServerWorld;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.gen.ChunkGenerator;
/*     */ import net.minecraft.world.gen.feature.structure.IStructurePieceType;
/*     */ import net.minecraft.world.gen.feature.structure.StructureManager;
/*     */ import net.minecraft.world.gen.feature.structure.StructurePiece;
/*     */ import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
/*     */ import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
/*     */ import net.minecraft.world.gen.feature.template.PlacementSettings;
/*     */ import net.minecraft.world.gen.feature.template.StructureProcessor;
/*     */ import net.minecraft.world.gen.feature.template.Template;
/*     */ import net.minecraft.world.gen.feature.template.TemplateManager;
/*     */ import xyz.pixelatedw.mineminenomi.api.WantedPosterData;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.CanvasSize;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.StructuresHelper;
/*     */ import xyz.pixelatedw.mineminenomi.blocks.FlagBlock;
/*     */ import xyz.pixelatedw.mineminenomi.blocks.WantedPosterBlock;
/*     */ import xyz.pixelatedw.mineminenomi.blocks.tileentities.FlagTileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.blocks.tileentities.WantedPosterTileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModLootTables;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModStructures;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ 
/*     */ 
/*     */ public class MarineLargeBasePieces
/*     */ {
/*  57 */   private static final ResourceLocation STRUCTURE = new ResourceLocation("mineminenomi", "marine/large_base");
/*  58 */   private static final ResourceLocation PRISON = new ResourceLocation("mineminenomi", "marine/large_base_prison");
/*     */   
/*  60 */   private static final Map<ResourceLocation, BlockPos> POSITION_OFFSET = (Map<ResourceLocation, BlockPos>)ImmutableMap.builder()
/*  61 */     .put(STRUCTURE, new BlockPos(0, 0, 0))
/*  62 */     .put(PRISON, new BlockPos(10, -13, 14))
/*  63 */     .build();
/*     */   
/*  65 */   private static final Map<ResourceLocation, BlockPos> CENTER_OFFSET = (Map<ResourceLocation, BlockPos>)ImmutableMap.builder()
/*  66 */     .put(STRUCTURE, new BlockPos(0, 0, 0))
/*  67 */     .put(PRISON, new BlockPos(0, 0, 0))
/*  68 */     .build();
/*     */ 
/*     */   
/*     */   public static void addComponents(TemplateManager templateManager, BlockPos pos, List<StructurePiece> components) {
/*  72 */     components.add(new MainBodyPiece(templateManager, pos));
/*  73 */     components.add(new PrisonPiece(templateManager, pos));
/*     */   }
/*     */   
/*     */   public static class MainBodyPiece
/*     */     extends Piece
/*     */   {
/*     */     public MainBodyPiece(TemplateManager template, CompoundNBT nbt) {
/*  80 */       super(template, ModStructures.Pieces.MARINE_LARGE_BASE_BODY_PIECE, nbt);
/*     */     }
/*     */ 
/*     */     
/*     */     public MainBodyPiece(TemplateManager template, BlockPos pos) {
/*  85 */       super(template, ModStructures.Pieces.MARINE_LARGE_BASE_BODY_PIECE, MarineLargeBasePieces.STRUCTURE, pos, (StructureProcessor)BlockIgnoreStructureProcessor.field_215204_a);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean func_230383_a_(ISeedReader world, StructureManager pStructureManager, ChunkGenerator pChunkGenerator, Random pRandom, MutableBoundingBox pBox, ChunkPos pChunkPos, BlockPos pPos) {
/*  91 */       boolean flag = super.func_230383_a_(world, pStructureManager, pChunkGenerator, pRandom, pBox, pChunkPos, pPos);
/*     */       
/*  93 */       List<Template.BlockInfo> infos = new ArrayList<>();
/*  94 */       infos.addAll(this.field_186176_a.func_215381_a(this.field_186178_c, this.field_186177_b, Blocks.field_150481_bH));
/*     */       
/*  96 */       for (Template.BlockInfo info : infos) {
/*  97 */         world.func_180501_a(info.field_186242_a, (BlockState)info.field_186243_b.func_206870_a((Property)BlockStateProperties.field_208198_y, Boolean.valueOf(false)), 3);
/*     */       }
/*  99 */       return flag;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class PrisonPiece
/*     */     extends Piece
/*     */   {
/*     */     public PrisonPiece(TemplateManager template, CompoundNBT nbt) {
/* 107 */       super(template, ModStructures.Pieces.MARINE_LARGE_BASE_PRISON_PIECE, nbt);
/*     */     }
/*     */ 
/*     */     
/*     */     public PrisonPiece(TemplateManager template, BlockPos pos) {
/* 112 */       super(template, ModStructures.Pieces.MARINE_LARGE_BASE_PRISON_PIECE, MarineLargeBasePieces.PRISON, pos, (StructureProcessor)BlockIgnoreStructureProcessor.field_215204_a);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean func_230383_a_(ISeedReader world, StructureManager pStructureManager, ChunkGenerator pChunkGenerator, Random pRandom, MutableBoundingBox pBox, ChunkPos pChunkPos, BlockPos pPos) {
/* 118 */       boolean flag = super.func_230383_a_(world, pStructureManager, pChunkGenerator, pRandom, pBox, pChunkPos, pPos);
/*     */       
/* 120 */       List<Template.BlockInfo> infos = new ArrayList<>();
/* 121 */       infos.addAll(this.field_186176_a.func_215381_a(this.field_186178_c, this.field_186177_b, Blocks.field_150463_bK));
/* 122 */       infos.addAll(this.field_186176_a.func_215381_a(this.field_186178_c, this.field_186177_b, Blocks.field_196659_cl));
/* 123 */       infos.addAll(this.field_186176_a.func_215381_a(this.field_186178_c, this.field_186177_b, Blocks.field_150411_aY));
/* 124 */       infos.addAll(this.field_186176_a.func_215381_a(this.field_186178_c, this.field_186177_b, Blocks.field_222432_lU));
/*     */       
/* 126 */       for (Template.BlockInfo info : infos) {
/* 127 */         world.func_180501_a(info.field_186242_a, (BlockState)info.field_186243_b.func_206870_a((Property)BlockStateProperties.field_208198_y, Boolean.valueOf(false)), 3);
/*     */       }
/* 129 */       return flag;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Piece
/*     */     extends TemplateStructurePiece {
/*     */     protected ResourceLocation resourceLocation;
/*     */     protected Rotation field_186169_c;
/*     */     protected StructureProcessor processor;
/* 138 */     private Set<UUID> posters = new HashSet<>();
/*     */ 
/*     */     
/*     */     public Piece(TemplateManager template, IStructurePieceType type, CompoundNBT nbt) {
/* 142 */       super(type, nbt);
/* 143 */       this.resourceLocation = new ResourceLocation(nbt.func_74779_i("Template"));
/* 144 */       this.field_186169_c = Rotation.valueOf(nbt.func_74779_i("Rot"));
/* 145 */       this.processor = (StructureProcessor)BlockIgnoreStructureProcessor.field_215206_c;
/* 146 */       build(template);
/*     */     }
/*     */ 
/*     */     
/*     */     public Piece(TemplateManager template, IStructurePieceType type, ResourceLocation res, BlockPos pos, StructureProcessor proc) {
/* 151 */       super(type, 0);
/* 152 */       this.field_186169_c = Rotation.NONE;
/* 153 */       this.resourceLocation = res;
/* 154 */       BlockPos blockpos = (BlockPos)MarineLargeBasePieces.POSITION_OFFSET.get(this.resourceLocation);
/* 155 */       this.field_186178_c = pos.func_177982_a(blockpos.func_177958_n(), blockpos.func_177956_o(), blockpos.func_177952_p());
/* 156 */       this.processor = (StructureProcessor)BlockIgnoreStructureProcessor.field_215206_c;
/* 157 */       build(template);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected void func_143011_b(CompoundNBT nbt) {
/* 163 */       super.func_143011_b(nbt);
/* 164 */       nbt.func_74778_a("Template", this.resourceLocation.toString());
/* 165 */       nbt.func_74778_a("Rot", this.field_186169_c.name());
/*     */     }
/*     */ 
/*     */     
/*     */     private void build(TemplateManager templateManager) {
/* 170 */       Template template = templateManager.func_200220_a(this.resourceLocation);
/* 171 */       PlacementSettings placementsettings = (new PlacementSettings()).func_186220_a(this.field_186169_c).func_186214_a(Mirror.NONE).func_207665_a((BlockPos)MarineLargeBasePieces.CENTER_OFFSET.get(this.resourceLocation)).func_215222_a(this.processor);
/* 172 */       func_186173_a(template, this.field_186178_c, placementsettings);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void func_186175_a(String function, BlockPos pos, IServerWorld world, Random rand, MutableBoundingBox pSbb) {
/* 179 */       if (function.equals("food_storage_chest")) {
/* 180 */         StructuresHelper.spawnLoot((IWorld)world, pos, ModLootTables.LARGE_MARINE_BASE_FOOD_CHEST);
/*     */       }
/* 182 */       if (function.equals("enchantment_chest")) {
/* 183 */         StructuresHelper.spawnLoot((IWorld)world, pos, ModLootTables.LARGE_MARINE_BASE_LAB_CHEST);
/*     */       }
/* 185 */       if (function.equals("supplies_chest")) {
/* 186 */         StructuresHelper.spawnLoot((IWorld)world, pos, ModLootTables.LARGE_MARINE_BASE_GENERIC_CHEST);
/*     */       }
/* 188 */       if (function.equals("captain_room_chest")) {
/* 189 */         StructuresHelper.spawnLoot((IWorld)world, pos, ModLootTables.LARGE_MARINE_BASE_CAPTAIN_CHEST);
/*     */       }
/* 191 */       boolean chest = rand.nextBoolean();
/*     */       
/* 193 */       if (chest) {
/*     */         
/* 195 */         if (function.equals("office_chest_1")) {
/* 196 */           StructuresHelper.spawnLoot((IWorld)world, pos, ModLootTables.LARGE_MARINE_BASE_CAPTAIN_CHEST);
/*     */         
/*     */         }
/*     */       }
/* 200 */       else if (function.equals("office_chest_2")) {
/* 201 */         StructuresHelper.spawnLoot((IWorld)world, pos, ModLootTables.LARGE_MARINE_BASE_CAPTAIN_CHEST);
/*     */       } 
/*     */ 
/*     */       
/* 205 */       StructuresHelper.StructureFaction prisoners1 = world.func_201674_k().nextBoolean() ? StructuresHelper.StructureFaction.BANDIT : StructuresHelper.StructureFaction.PIRATE;
/* 206 */       StructuresHelper.StructureFaction prisoners2 = world.func_201674_k().nextBoolean() ? StructuresHelper.StructureFaction.BANDIT : StructuresHelper.StructureFaction.PIRATE;
/* 207 */       StructuresHelper.StructureFaction prisoners3 = world.func_201674_k().nextBoolean() ? StructuresHelper.StructureFaction.BANDIT : StructuresHelper.StructureFaction.PIRATE;
/* 208 */       StructuresHelper.StructureFaction prisoners4 = world.func_201674_k().nextBoolean() ? StructuresHelper.StructureFaction.BANDIT : StructuresHelper.StructureFaction.PIRATE;
/*     */       
/* 210 */       boolean extraSpawns = rand.nextBoolean();
/*     */       
/* 212 */       if (function.equals("prison_spawn_1")) {
/* 213 */         StructuresHelper.spawnMobs((IWorld)world, pos, prisoners1, StructuresHelper.StructureSpawnType.GRUNT, 3);
/*     */       }
/* 215 */       if (extraSpawns && function.equals("prison_spawn_2")) {
/* 216 */         StructuresHelper.spawnMobs((IWorld)world, pos, prisoners2, StructuresHelper.StructureSpawnType.GRUNT, 3);
/* 217 */       } else if (!extraSpawns) {
/* 218 */         world.func_180501_a(pos, ((Block)ModBlocks.STRUCTURE_AIR.get()).func_176223_P(), 3);
/*     */       } 
/* 220 */       if (function.equals("prison_spawn_3")) {
/* 221 */         StructuresHelper.spawnMobs((IWorld)world, pos, prisoners3, StructuresHelper.StructureSpawnType.GRUNT, 2);
/*     */       }
/* 223 */       if (extraSpawns && function.equals("prison_spawn_4")) {
/* 224 */         StructuresHelper.spawnMobs((IWorld)world, pos, prisoners4, StructuresHelper.StructureSpawnType.GRUNT, 2);
/* 225 */       } else if (!extraSpawns) {
/* 226 */         world.func_180501_a(pos, ((Block)ModBlocks.STRUCTURE_AIR.get()).func_176223_P(), 3);
/*     */       } 
/* 228 */       if (function.equals("front_desk_spawn")) {
/* 229 */         StructuresHelper.spawnMobs((IWorld)world, pos, StructuresHelper.StructureFaction.MARINE, StructuresHelper.StructureSpawnType.GRUNT, 5);
/*     */       }
/* 231 */       if (function.equals("prison_guard_spawn")) {
/* 232 */         StructuresHelper.spawnMobs((IWorld)world, pos, StructuresHelper.StructureFaction.MARINE, StructuresHelper.StructureSpawnType.GRUNT, 3);
/*     */       }
/* 234 */       if (function.equals("dinner_spawn")) {
/* 235 */         StructuresHelper.spawnMobs((IWorld)world, pos, StructuresHelper.StructureFaction.MARINE, StructuresHelper.StructureSpawnType.GRUNT, 5);
/*     */       }
/* 237 */       if (function.equals("bedroom_spawn")) {
/* 238 */         StructuresHelper.spawnMobs((IWorld)world, pos, StructuresHelper.StructureFaction.MARINE, StructuresHelper.StructureSpawnType.GRUNT, 3);
/*     */       }
/* 240 */       if (function.equals("lounge_spawn_1")) {
/* 241 */         StructuresHelper.spawnMobs((IWorld)world, pos, StructuresHelper.StructureFaction.MARINE, StructuresHelper.StructureSpawnType.GRUNT, 5);
/*     */       }
/* 243 */       if (function.equals("lounge_spawn_2")) {
/* 244 */         StructuresHelper.spawnMobs((IWorld)world, pos, StructuresHelper.StructureFaction.MARINE, StructuresHelper.StructureSpawnType.BRUTE, 5);
/*     */       }
/* 246 */       if (function.equals("captain_room_spawn")) {
/* 247 */         StructuresHelper.spawnMobs((IWorld)world, pos, StructuresHelper.StructureFaction.MARINE, StructuresHelper.StructureSpawnType.CAPTAIN, 1);
/*     */       }
/* 249 */       if (function.equals("office_spawn")) {
/* 250 */         StructuresHelper.spawnMobs((IWorld)world, pos, StructuresHelper.StructureFaction.MARINE, StructuresHelper.StructureSpawnType.CAPTAIN, 1);
/*     */       }
/* 252 */       if (function.equals("rooftop_spawn")) {
/* 253 */         StructuresHelper.spawnMobs((IWorld)world, pos, StructuresHelper.StructureFaction.MARINE, StructuresHelper.StructureSpawnType.SNIPER, 5);
/*     */       }
/* 255 */       if (function.equals("balcony_spawn")) {
/* 256 */         StructuresHelper.spawnMobs((IWorld)world, pos, StructuresHelper.StructureFaction.MARINE, StructuresHelper.StructureSpawnType.SNIPER, 1);
/*     */       }
/*     */       
/* 259 */       if (function.equals("special_prison")) {
/* 260 */         StructuresHelper.spawnMobs((IWorld)world, pos, prisoners4, StructuresHelper.StructureSpawnType.GRUNT, 4);
/*     */       }
/* 262 */       Rotation rot = this.field_186177_b.func_186215_c();
/*     */       
/* 264 */       if (function.startsWith("wanted_poster_")) {
/* 265 */         ExtendedWorldData worldData = ExtendedWorldData.get();
/* 266 */         Optional<Map.Entry<UUID, Long>> bounty = worldData.getAllBounties().entrySet().stream().findAny();
/*     */         
/* 268 */         String[] funcSplt = function.split("_");
/* 269 */         int id = -1;
/*     */         try {
/* 271 */           id = Integer.parseInt(funcSplt[2]);
/*     */         }
/* 273 */         catch (Exception ex) {
/* 274 */           ex.printStackTrace();
/*     */         } 
/*     */         
/* 277 */         if (id >= 0) {
/* 278 */           if (id <= 5) {
/* 279 */             generateWantedPoster(world, pos, rot, Direction.NORTH, bounty);
/*     */           }
/* 281 */           else if (id > 5 && id <= 10) {
/* 282 */             generateWantedPoster(world, pos, rot, Direction.SOUTH, bounty);
/*     */           }
/* 284 */           else if (id == 11 || id == 12) {
/* 285 */             generateWantedPoster(world, pos, rot, Direction.SOUTH, bounty);
/*     */           }
/* 287 */           else if (id == 13) {
/* 288 */             generateWantedPoster(world, pos, rot, Direction.WEST, bounty);
/*     */           } 
/*     */         }
/*     */       } 
/*     */       
/* 293 */       if (function.equals("flag")) {
/* 294 */         BlockState state = ((Block)ModBlocks.FLAG.get()).func_176223_P();
/*     */         
/* 296 */         state = (BlockState)state.func_206870_a((Property)WantedPosterBlock.FACING, (Comparable)rot.func_185831_a(Direction.NORTH));
/* 297 */         state = (BlockState)state.func_206870_a((Property)FlagBlock.SIZE, (Comparable)CanvasSize.HUGE);
/*     */         
/* 299 */         world.func_180501_a(pos, state, 3);
/* 300 */         FlagBlock.placeSubs((IWorld)world, pos, state, false);
/*     */         
/* 302 */         TileEntity flagTile = world.func_175625_s(pos);
/* 303 */         if (flagTile != null && flagTile instanceof FlagTileEntity) {
/* 304 */           ((FlagTileEntity)flagTile).setFaction(ModValues.MARINE);
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/*     */     private void generateWantedPoster(IServerWorld world, BlockPos pos, Rotation rot, Direction dir, Optional<Map.Entry<UUID, Long>> bounty) {
/* 310 */       if (world.func_201670_d()) {
/*     */         return;
/*     */       }
/*     */       
/* 314 */       if (!world.func_201674_k().nextBoolean()) {
/*     */         return;
/*     */       }
/*     */       
/* 318 */       if (bounty.isPresent()) {
/* 319 */         UUID key = (UUID)((Map.Entry)bounty.get()).getKey();
/* 320 */         PlayerEntity player = world.func_217371_b(key);
/*     */         
/* 322 */         if (this.posters.contains(key) || player == null) {
/* 323 */           world.func_180501_a(pos, ((Block)ModBlocks.STRUCTURE_AIR.get()).func_176223_P(), 3);
/*     */           
/*     */           return;
/*     */         } 
/* 327 */         WantedPosterData wantedPosterData = new WantedPosterData((LivingEntity)player, ((Long)((Map.Entry)bounty.get()).getValue()).longValue());
/*     */         
/* 329 */         BlockState state = ((Block)ModBlocks.WANTED_POSTER.get()).func_176223_P();
/* 330 */         state = (BlockState)state.func_206870_a((Property)WantedPosterBlock.FACING, (Comparable)rot.func_185831_a(dir));
/* 331 */         world.func_180501_a(pos, state, 2);
/* 332 */         TileEntity tile = world.func_175625_s(pos);
/* 333 */         if (tile instanceof WantedPosterTileEntity) {
/* 334 */           ((WantedPosterTileEntity)tile).setWantedPosterData(wantedPosterData);
/* 335 */           tile.func_70296_d();
/*     */         } 
/* 337 */         this.posters.add(key);
/*     */       } else {
/*     */         
/* 340 */         world.func_180501_a(pos, ((Block)ModBlocks.STRUCTURE_AIR.get()).func_176223_P(), 3);
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean func_230383_a_(ISeedReader world, StructureManager pStructureManager, ChunkGenerator pChunkGenerator, Random pRandom, MutableBoundingBox pBox, ChunkPos pChunkPos, BlockPos pPos) {
/* 347 */       boolean flag = super.func_230383_a_(world, pStructureManager, pChunkGenerator, pRandom, pBox, pChunkPos, pPos);
/* 348 */       return flag;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\world\features\structures\largebase\marine\MarineLargeBasePieces.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */