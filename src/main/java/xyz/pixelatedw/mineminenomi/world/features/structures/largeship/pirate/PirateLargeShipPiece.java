/*     */ package xyz.pixelatedw.mineminenomi.world.features.structures.largeship.pirate;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.Blocks;
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
/*     */ import net.minecraft.world.gen.feature.structure.StructureManager;
/*     */ import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
/*     */ import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
/*     */ import net.minecraft.world.gen.feature.template.PlacementSettings;
/*     */ import net.minecraft.world.gen.feature.template.StructureProcessor;
/*     */ import net.minecraft.world.gen.feature.template.Template;
/*     */ import net.minecraft.world.gen.feature.template.TemplateManager;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.CanvasSize;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.StructuresHelper;
/*     */ import xyz.pixelatedw.mineminenomi.blocks.FlagBlock;
/*     */ import xyz.pixelatedw.mineminenomi.blocks.WantedPosterBlock;
/*     */ import xyz.pixelatedw.mineminenomi.blocks.tileentities.FlagTileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModLootTables;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModStructures;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ 
/*     */ 
/*     */ public class PirateLargeShipPiece
/*     */   extends TemplateStructurePiece
/*     */ {
/*     */   private ResourceLocation resourceLocation;
/*     */   private Rotation rotation;
/*     */   
/*     */   public PirateLargeShipPiece(TemplateManager template, CompoundNBT nbt) {
/*  49 */     super(ModStructures.Pieces.PIRATE_LARGE_SHIP_PIECE, nbt);
/*  50 */     this.resourceLocation = new ResourceLocation(nbt.func_74779_i("Template"));
/*  51 */     this.rotation = Rotation.valueOf(nbt.func_74779_i("Rot"));
/*  52 */     build(template);
/*     */   }
/*     */ 
/*     */   
/*     */   public PirateLargeShipPiece(TemplateManager template, BlockPos pos, Rotation rot) {
/*  57 */     super(ModStructures.Pieces.PIRATE_LARGE_SHIP_PIECE, 0);
/*  58 */     this.rotation = rot;
/*  59 */     this.field_186178_c = pos;
/*  60 */     this.resourceLocation = new ResourceLocation("mineminenomi", "pirate/large_ship");
/*  61 */     build(template);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_143011_b(CompoundNBT nbt) {
/*  67 */     super.func_143011_b(nbt);
/*  68 */     nbt.func_74778_a("Template", this.resourceLocation.toString());
/*  69 */     nbt.func_74778_a("Rot", this.rotation.name());
/*     */   }
/*     */ 
/*     */   
/*     */   private void build(TemplateManager templateManager) {
/*  74 */     Template template = templateManager.func_200220_a(this.resourceLocation);
/*  75 */     PlacementSettings placementsettings = (new PlacementSettings()).func_186220_a(this.rotation).func_186214_a(Mirror.NONE).func_215222_a((StructureProcessor)BlockIgnoreStructureProcessor.field_215206_c);
/*  76 */     func_186173_a(template, this.field_186178_c, placementsettings);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_186175_a(String function, BlockPos pos, IServerWorld world, Random rand, MutableBoundingBox sbb) {
/*  83 */     if (function.equals("captain_chest")) {
/*  84 */       StructuresHelper.spawnLoot((IWorld)world, pos, ModLootTables.LARGE_PIRATE_SHIP_CAPTAIN_CHEST);
/*     */     }
/*  86 */     if (function.equals("supplies_chest")) {
/*  87 */       StructuresHelper.spawnLoot((IWorld)world, pos, ModLootTables.LARGE_PIRATE_SHIP_SUPPLIES_CHEST);
/*     */     }
/*  89 */     if (function.equals("treasure_chest")) {
/*  90 */       StructuresHelper.spawnLoot((IWorld)world, pos, ModLootTables.LARGE_PIRATE_SHIP_TREASURE_CHEST);
/*     */     }
/*  92 */     if (function.equals("weapons_chest")) {
/*  93 */       StructuresHelper.spawnLoot((IWorld)world, pos, ModLootTables.LARGE_PIRATE_SHIP_WEAPONS_CHEST);
/*     */     }
/*     */     
/*  96 */     if (function.equals("deck_spawn")) {
/*  97 */       StructuresHelper.spawnMobs((IWorld)world, pos, StructuresHelper.StructureFaction.PIRATE, StructuresHelper.StructureSpawnType.GRUNT, 5);
/*     */     }
/*  99 */     if (function.equals("wheel_spawn")) {
/* 100 */       StructuresHelper.spawnMobs((IWorld)world, pos, StructuresHelper.StructureFaction.PIRATE, StructuresHelper.StructureSpawnType.BRUTE, 3);
/*     */     }
/* 102 */     if (function.equals("storage_spawn_1")) {
/* 103 */       StructuresHelper.spawnMobs((IWorld)world, pos, StructuresHelper.StructureFaction.PIRATE, StructuresHelper.StructureSpawnType.BRUTE, 3);
/*     */     }
/* 105 */     if (function.equals("storage_spawn_2")) {
/* 106 */       StructuresHelper.spawnMobs((IWorld)world, pos, StructuresHelper.StructureFaction.PIRATE, StructuresHelper.StructureSpawnType.GRUNT, 10);
/*     */     }
/* 108 */     if (function.equals("bedroom_spawn")) {
/* 109 */       StructuresHelper.spawnMobs((IWorld)world, pos, StructuresHelper.StructureFaction.PIRATE, StructuresHelper.StructureSpawnType.GRUNT, 5);
/*     */     }
/* 111 */     if (function.equals("captain_room_spawn_1")) {
/* 112 */       StructuresHelper.spawnMobs((IWorld)world, pos, StructuresHelper.StructureFaction.PIRATE, StructuresHelper.StructureSpawnType.GRUNT, 5);
/*     */     }
/* 114 */     if (function.equals("captain_room_spawn_2")) {
/* 115 */       StructuresHelper.spawnMobs((IWorld)world, pos, StructuresHelper.StructureFaction.PIRATE, StructuresHelper.StructureSpawnType.CAPTAIN, 1);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 120 */     Rotation rot = this.field_186177_b.func_186215_c();
/*     */     
/* 122 */     if (function.equals("flag")) {
/* 123 */       BlockState state = ((Block)ModBlocks.FLAG.get()).func_176223_P();
/*     */       
/* 125 */       state = (BlockState)state.func_206870_a((Property)WantedPosterBlock.FACING, (Comparable)rot.func_185831_a(Direction.EAST));
/* 126 */       state = (BlockState)state.func_206870_a((Property)FlagBlock.SIZE, (Comparable)CanvasSize.HUGE);
/*     */       
/* 128 */       world.func_180501_a(pos, state, 3);
/* 129 */       FlagBlock.placeSubs((IWorld)world, pos, state, false);
/*     */       
/* 131 */       TileEntity flagTile = world.func_175625_s(pos);
/* 132 */       if (flagTile != null && flagTile instanceof FlagTileEntity) {
/* 133 */         ((FlagTileEntity)flagTile).setFaction(ModValues.PIRATE);
/*     */       }
/*     */     } 
/*     */     
/* 137 */     if (function.equals("flag2")) {
/* 138 */       BlockState state = ((Block)ModBlocks.FLAG.get()).func_176223_P();
/*     */       
/* 140 */       state = (BlockState)state.func_206870_a((Property)WantedPosterBlock.FACING, (Comparable)rot.func_185831_a(Direction.EAST));
/* 141 */       state = (BlockState)state.func_206870_a((Property)FlagBlock.SIZE, (Comparable)CanvasSize.LARGE);
/*     */       
/* 143 */       world.func_180501_a(pos, state, 3);
/* 144 */       FlagBlock.placeSubs((IWorld)world, pos, state, false);
/*     */       
/* 146 */       TileEntity flagTile = world.func_175625_s(pos);
/* 147 */       if (flagTile != null && flagTile instanceof FlagTileEntity) {
/* 148 */         ((FlagTileEntity)flagTile).setFaction(ModValues.PIRATE);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_230383_a_(ISeedReader world, StructureManager pStructureManager, ChunkGenerator pChunkGenerator, Random pRandom, MutableBoundingBox pBox, ChunkPos pChunkPos, BlockPos pPos) {
/* 156 */     boolean flag = super.func_230383_a_(world, pStructureManager, pChunkGenerator, pRandom, pBox, pChunkPos, pPos);
/*     */     
/* 158 */     List<Template.BlockInfo> infos = new ArrayList<>();
/* 159 */     infos.addAll(this.field_186176_a.func_215381_a(this.field_186178_c, this.field_186177_b, Blocks.field_150486_ae));
/* 160 */     infos.addAll(this.field_186176_a.func_215381_a(this.field_186178_c, this.field_186177_b, Blocks.field_222432_lU));
/* 161 */     infos.addAll(this.field_186176_a.func_215381_a(this.field_186178_c, this.field_186177_b, Blocks.field_150401_cl));
/* 162 */     infos.addAll(this.field_186176_a.func_215381_a(this.field_186178_c, this.field_186177_b, Blocks.field_196684_db));
/* 163 */     infos.addAll(this.field_186176_a.func_215381_a(this.field_186178_c, this.field_186177_b, Blocks.field_196635_bv));
/* 164 */     infos.addAll(this.field_186176_a.func_215381_a(this.field_186178_c, this.field_186177_b, Blocks.field_180404_aQ));
/* 165 */     infos.addAll(this.field_186176_a.func_215381_a(this.field_186178_c, this.field_186177_b, Blocks.field_196638_cX));
/*     */     
/* 167 */     for (Template.BlockInfo info : infos) {
/* 168 */       world.func_180501_a(info.field_186242_a, (BlockState)info.field_186243_b.func_206870_a((Property)BlockStateProperties.field_208198_y, Boolean.valueOf(false)), 3);
/*     */     }
/* 170 */     return flag;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\world\features\structures\largeship\pirate\PirateLargeShipPiece.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */