/*     */ package xyz.pixelatedw.mineminenomi.world.features.structures.smallbase.bandit;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import net.minecraft.nbt.CompoundNBT;
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
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.StructuresHelper;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModLootTables;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModStructures;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BanditSmallBasePieces
/*     */ {
/*  36 */   private static final ResourceLocation HOUSE = new ResourceLocation("mineminenomi", "bandit/small_base_house");
/*  37 */   private static final ResourceLocation UNDERGROUND = new ResourceLocation("mineminenomi", "bandit/small_base_underground");
/*     */   
/*  39 */   private static final Map<ResourceLocation, BlockPos> POSITION_OFFSET = (Map<ResourceLocation, BlockPos>)ImmutableMap.builder()
/*  40 */     .put(HOUSE, new BlockPos(20, 26, 24))
/*  41 */     .put(UNDERGROUND, new BlockPos(0, 0, 0))
/*  42 */     .build();
/*     */ 
/*     */   
/*     */   public static void addComponents(TemplateManager templateManager, BlockPos pos, List<StructurePiece> components) {
/*  46 */     components.add(new HousePiece(templateManager, pos));
/*  47 */     components.add(new UndergroundPiece(templateManager, pos));
/*     */   }
/*     */   
/*     */   public static class UndergroundPiece
/*     */     extends Piece
/*     */   {
/*     */     public UndergroundPiece(TemplateManager template, CompoundNBT nbt) {
/*  54 */       super(template, ModStructures.Pieces.BANDIT_SMALL_BASE_UNDERGROUND_PIECE, nbt);
/*  55 */       build(template);
/*     */     }
/*     */ 
/*     */     
/*     */     public UndergroundPiece(TemplateManager template, BlockPos pos) {
/*  60 */       super(template, ModStructures.Pieces.BANDIT_SMALL_BASE_UNDERGROUND_PIECE, BanditSmallBasePieces.UNDERGROUND, pos);
/*  61 */       build(template);
/*     */     }
/*     */ 
/*     */     
/*     */     private void build(TemplateManager templateManager) {
/*  66 */       Template template = templateManager.func_200220_a(this.resourceLocation);
/*  67 */       PlacementSettings placementsettings = (new PlacementSettings()).func_186220_a(this.field_186169_c).func_186214_a(Mirror.NONE).func_215222_a((StructureProcessor)BlockIgnoreStructureProcessor.field_215206_c);
/*  68 */       func_186173_a(template, this.field_186178_c, placementsettings);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class HousePiece
/*     */     extends Piece
/*     */   {
/*     */     public HousePiece(TemplateManager template, CompoundNBT nbt) {
/*  76 */       super(template, ModStructures.Pieces.BANDIT_SMALL_BASE_HOUSE_PIECE, nbt);
/*  77 */       build(template);
/*     */     }
/*     */ 
/*     */     
/*     */     public HousePiece(TemplateManager template, BlockPos pos) {
/*  82 */       super(template, ModStructures.Pieces.BANDIT_SMALL_BASE_HOUSE_PIECE, BanditSmallBasePieces.HOUSE, pos);
/*  83 */       build(template);
/*     */     }
/*     */ 
/*     */     
/*     */     private void build(TemplateManager templateManager) {
/*  88 */       Template template = templateManager.func_200220_a(this.resourceLocation);
/*  89 */       PlacementSettings placementsettings = (new PlacementSettings()).func_186220_a(this.field_186169_c).func_186214_a(Mirror.NONE).func_215222_a((StructureProcessor)BlockIgnoreStructureProcessor.field_215206_c);
/*  90 */       func_186173_a(template, this.field_186178_c, placementsettings);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Piece
/*     */     extends TemplateStructurePiece
/*     */   {
/*     */     protected ResourceLocation resourceLocation;
/*     */     protected Rotation field_186169_c;
/*     */     
/*     */     public Piece(TemplateManager template, IStructurePieceType type, CompoundNBT nbt) {
/* 101 */       super(type, nbt);
/* 102 */       this.resourceLocation = new ResourceLocation(nbt.func_74779_i("Template"));
/* 103 */       this.field_186169_c = Rotation.valueOf(nbt.func_74779_i("Rot"));
/*     */     }
/*     */ 
/*     */     
/*     */     public Piece(TemplateManager template, IStructurePieceType type, ResourceLocation res, BlockPos pos) {
/* 108 */       super(type, 0);
/* 109 */       this.resourceLocation = res;
/* 110 */       BlockPos blockpos = (BlockPos)BanditSmallBasePieces.POSITION_OFFSET.get(this.resourceLocation);
/* 111 */       this.field_186178_c = pos.func_177982_a(blockpos.func_177958_n(), blockpos.func_177956_o(), blockpos.func_177952_p());
/* 112 */       this.field_186169_c = Rotation.NONE;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected void func_143011_b(CompoundNBT nbt) {
/* 118 */       super.func_143011_b(nbt);
/* 119 */       nbt.func_74778_a("Template", this.resourceLocation.toString());
/* 120 */       nbt.func_74778_a("Rot", this.field_186169_c.name());
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void func_186175_a(String function, BlockPos pos, IServerWorld world, Random rand, MutableBoundingBox sbb) {
/* 127 */       if (function.equals("equipment_chest")) {
/* 128 */         StructuresHelper.spawnLoot((IWorld)world, pos, ModLootTables.SMALL_BANDIT_BASE_MINE_CHEST);
/*     */       }
/* 130 */       if (function.equals("gem_chest")) {
/* 131 */         StructuresHelper.spawnLoot((IWorld)world, pos, ModLootTables.SMALL_BANDIT_BASE_ORES_CHEST);
/*     */       }
/* 133 */       if (function.equals("lab_chest")) {
/* 134 */         StructuresHelper.spawnLoot((IWorld)world, pos, ModLootTables.SMALL_BANDIT_BASE_LAB_CHEST);
/*     */       }
/* 136 */       if (function.equals("gold_chest")) {
/* 137 */         StructuresHelper.spawnLoot((IWorld)world, pos, ModLootTables.SMALL_BANDIT_BASE_GOLD_CHEST);
/*     */       }
/*     */ 
/*     */       
/* 141 */       if (function.equals("lounge_spawn")) {
/* 142 */         StructuresHelper.spawnMobs((IWorld)world, pos, StructuresHelper.StructureFaction.BANDIT, StructuresHelper.StructureSpawnType.GRUNT, 5);
/*     */       }
/* 144 */       if (function.equals("hall_spawn")) {
/* 145 */         StructuresHelper.spawnMobs((IWorld)world, pos, StructuresHelper.StructureFaction.BANDIT, StructuresHelper.StructureSpawnType.BRUTE, 3);
/*     */       }
/* 147 */       if (function.equals("bedroom_spawn_1")) {
/* 148 */         StructuresHelper.spawnMobs((IWorld)world, pos, StructuresHelper.StructureFaction.BANDIT, StructuresHelper.StructureSpawnType.GRUNT, 5);
/*     */       }
/* 150 */       if (function.equals("bedroom_spawn_2")) {
/* 151 */         StructuresHelper.spawnMobs((IWorld)world, pos, StructuresHelper.StructureFaction.BANDIT, StructuresHelper.StructureSpawnType.CAPTAIN, 1);
/*     */       }
/* 153 */       if (function.equals("lab_spawn")) {
/* 154 */         StructuresHelper.spawnMobs((IWorld)world, pos, StructuresHelper.StructureFaction.BANDIT, StructuresHelper.StructureSpawnType.BRUTE, 3);
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean func_230383_a_(ISeedReader world, StructureManager pStructureManager, ChunkGenerator pChunkGenerator, Random pRandom, MutableBoundingBox pBox, ChunkPos pChunkPos, BlockPos pPos) {
/* 160 */       BlockPos ogPos = this.field_186178_c;
/* 161 */       this.field_186178_c = this.field_186178_c.func_177982_a(0, -28, 0);
/* 162 */       boolean flag = super.func_230383_a_(world, pStructureManager, pChunkGenerator, pRandom, pBox, pChunkPos, pPos);
/* 163 */       this.field_186178_c = ogPos;
/* 164 */       return flag;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\world\features\structures\smallbase\bandit\BanditSmallBasePieces.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */