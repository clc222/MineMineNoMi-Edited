/*     */ package xyz.pixelatedw.mineminenomi.world.features.structures.smallbase.marine;
/*     */ 
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.state.Property;
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
/*     */ import net.minecraft.world.gen.feature.structure.StructureManager;
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
/*     */ public class MarineSmallBasePiece extends TemplateStructurePiece {
/*     */   private ResourceLocation resourceLocation;
/*  49 */   private Set<UUID> posters = new HashSet<>();
/*     */   private Rotation rotation;
/*     */   
/*     */   public MarineSmallBasePiece(TemplateManager template, CompoundNBT nbt) {
/*  53 */     super(ModStructures.Pieces.MARINE_SMALL_BASE_PIECE, nbt);
/*  54 */     this.resourceLocation = new ResourceLocation(nbt.func_74779_i("Template"));
/*  55 */     this.rotation = Rotation.valueOf(nbt.func_74779_i("Rot"));
/*  56 */     build(template);
/*     */   }
/*     */ 
/*     */   
/*     */   public MarineSmallBasePiece(TemplateManager template, BlockPos pos, Rotation rot) {
/*  61 */     super(ModStructures.Pieces.MARINE_SMALL_BASE_PIECE, 0);
/*  62 */     this.field_186178_c = pos;
/*  63 */     this.rotation = rot;
/*  64 */     this.resourceLocation = new ResourceLocation("mineminenomi", "marine/small_base");
/*  65 */     build(template);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_143011_b(CompoundNBT nbt) {
/*  71 */     super.func_143011_b(nbt);
/*  72 */     nbt.func_74778_a("Template", this.resourceLocation.toString());
/*  73 */     nbt.func_74778_a("Rot", this.rotation.name());
/*     */   }
/*     */ 
/*     */   
/*     */   private void build(TemplateManager templateManager) {
/*  78 */     Template template = templateManager.func_200220_a(this.resourceLocation);
/*  79 */     PlacementSettings placementsettings = (new PlacementSettings()).func_186220_a(this.rotation).func_186214_a(Mirror.NONE).func_215222_a((StructureProcessor)BlockIgnoreStructureProcessor.field_215204_a);
/*  80 */     func_186173_a(template, this.field_186178_c, placementsettings);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_186175_a(String function, BlockPos pos, IServerWorld world, Random rand, MutableBoundingBox sbb) {
/*  87 */     if (function.equals("captain_chest")) {
/*  88 */       StructuresHelper.spawnLoot((IWorld)world, pos, ModLootTables.SMALL_MARINE_BASE_CAPTAIN_CHEST);
/*     */     }
/*  90 */     if (function.equals("dorm_supplies")) {
/*  91 */       StructuresHelper.spawnLoot((IWorld)world, pos, ModLootTables.SMALL_MARINE_BASE_DORM_CHEST);
/*     */     }
/*  93 */     if (function.equals("food_supplies")) {
/*  94 */       StructuresHelper.spawnLoot((IWorld)world, pos, ModLootTables.SMALL_MARINE_BASE_FOOD_CHEST);
/*     */     }
/*     */     
/*  97 */     if (function.equals("desk_spawn")) {
/*  98 */       StructuresHelper.spawnMobs((IWorld)world, pos, StructuresHelper.StructureFaction.MARINE, StructuresHelper.StructureSpawnType.GRUNT, 1);
/*     */     }
/* 100 */     if (function.equals("main_hall_spawn")) {
/* 101 */       StructuresHelper.spawnMobs((IWorld)world, pos, StructuresHelper.StructureFaction.MARINE, StructuresHelper.StructureSpawnType.GRUNT, 5);
/*     */     }
/* 103 */     if (function.equals("bedrooms_spawn")) {
/* 104 */       StructuresHelper.spawnMobs((IWorld)world, pos, StructuresHelper.StructureFaction.MARINE, StructuresHelper.StructureSpawnType.BRUTE, 3);
/*     */     }
/* 106 */     if (function.equals("upstairs_spawn")) {
/* 107 */       StructuresHelper.spawnMobs((IWorld)world, pos, StructuresHelper.StructureFaction.MARINE, StructuresHelper.StructureSpawnType.GRUNT, 5);
/*     */     }
/* 109 */     if (function.equals("captain_room_spawn")) {
/* 110 */       StructuresHelper.spawnMobs((IWorld)world, pos, StructuresHelper.StructureFaction.MARINE, StructuresHelper.StructureSpawnType.CAPTAIN, 1);
/*     */     }
/* 112 */     Rotation rot = this.field_186177_b.func_186215_c();
/*     */ 
/*     */     
/* 115 */     if (function.startsWith("wanted_poster_")) {
/* 116 */       ExtendedWorldData worldData = ExtendedWorldData.get();
/* 117 */       Optional<Map.Entry<UUID, Long>> bounty = worldData.getAllBounties().entrySet().stream().findAny();
/*     */       
/* 119 */       String[] funcSplt = function.split("_");
/* 120 */       int id = -1;
/*     */       try {
/* 122 */         id = Integer.parseInt(funcSplt[2]);
/*     */       }
/* 124 */       catch (Exception ex) {
/* 125 */         ex.printStackTrace();
/*     */       } 
/*     */       
/* 128 */       if (id >= 0) {
/* 129 */         if (id <= 5) {
/* 130 */           generateWantedPoster(world, pos, rot, Direction.NORTH, bounty);
/*     */         }
/* 132 */         else if (id > 5 && id <= 10) {
/* 133 */           generateWantedPoster(world, pos, rot, Direction.SOUTH, bounty);
/*     */         }
/* 135 */         else if (id == 11 || id == 12) {
/* 136 */           generateWantedPoster(world, pos, rot, Direction.SOUTH, bounty);
/*     */         }
/* 138 */         else if (id == 13) {
/* 139 */           generateWantedPoster(world, pos, rot, Direction.WEST, bounty);
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 144 */     if (function.equals("flag")) {
/* 145 */       BlockState state = ((Block)ModBlocks.FLAG.get()).func_176223_P();
/*     */       
/* 147 */       state = (BlockState)state.func_206870_a((Property)WantedPosterBlock.FACING, (Comparable)rot.func_185831_a(Direction.EAST));
/* 148 */       state = (BlockState)state.func_206870_a((Property)FlagBlock.SIZE, (Comparable)CanvasSize.HUGE);
/*     */       
/* 150 */       world.func_180501_a(pos, state, 3);
/* 151 */       FlagBlock.placeSubs((IWorld)world, pos, state, false);
/*     */       
/* 153 */       TileEntity flagTile = world.func_175625_s(pos);
/* 154 */       if (flagTile != null && flagTile instanceof FlagTileEntity) {
/* 155 */         ((FlagTileEntity)flagTile).setFaction(ModValues.MARINE);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void generateWantedPoster(IServerWorld world, BlockPos pos, Rotation rot, Direction dir, Optional<Map.Entry<UUID, Long>> bounty) {
/* 161 */     if (world.func_201670_d()) {
/*     */       return;
/*     */     }
/*     */     
/* 165 */     if (!world.func_201674_k().nextBoolean()) {
/*     */       return;
/*     */     }
/*     */     
/* 169 */     if (bounty.isPresent()) {
/* 170 */       UUID key = (UUID)((Map.Entry)bounty.get()).getKey();
/* 171 */       PlayerEntity player = world.func_217371_b(key);
/*     */       
/* 173 */       if (this.posters.contains(key) || player == null) {
/* 174 */         world.func_180501_a(pos, ((Block)ModBlocks.STRUCTURE_AIR.get()).func_176223_P(), 3);
/*     */         
/*     */         return;
/*     */       } 
/* 178 */       WantedPosterData wantedPosterData = new WantedPosterData((LivingEntity)player, ((Long)((Map.Entry)bounty.get()).getValue()).longValue());
/*     */       
/* 180 */       BlockState state = ((Block)ModBlocks.WANTED_POSTER.get()).func_176223_P();
/* 181 */       state = (BlockState)state.func_206870_a((Property)WantedPosterBlock.FACING, (Comparable)rot.func_185831_a(dir));
/* 182 */       world.func_180501_a(pos, state, 2);
/* 183 */       TileEntity tile = world.func_175625_s(pos);
/* 184 */       if (tile instanceof WantedPosterTileEntity) {
/* 185 */         ((WantedPosterTileEntity)tile).setWantedPosterData(wantedPosterData);
/* 186 */         tile.func_70296_d();
/*     */       } 
/* 188 */       this.posters.add(key);
/*     */     } else {
/*     */       
/* 191 */       world.func_180501_a(pos, ((Block)ModBlocks.STRUCTURE_AIR.get()).func_176223_P(), 3);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_230383_a_(ISeedReader world, StructureManager pStructureManager, ChunkGenerator pChunkGenerator, Random pRandom, MutableBoundingBox pBox, ChunkPos pChunkPos, BlockPos pPos) {
/* 198 */     boolean flag = super.func_230383_a_(world, pStructureManager, pChunkGenerator, pRandom, pBox, pChunkPos, pPos);
/* 199 */     return flag;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\world\features\structures\smallbase\marine\MarineSmallBasePiece.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */