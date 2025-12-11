/*     */ package xyz.pixelatedw.mineminenomi.world.features.structures.camp.bandit;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.util.Mirror;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.Rotation;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MutableBoundingBox;
/*     */ import net.minecraft.util.math.vector.Vector3i;
/*     */ import net.minecraft.world.IServerWorld;
/*     */ import net.minecraft.world.IWorld;
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
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BanditCampPieces
/*     */ {
/*  34 */   private static final ResourceLocation SMALL_TENT = new ResourceLocation("mineminenomi", "bandit/small_tent");
/*  35 */   private static final ResourceLocation LARGE_TENT = new ResourceLocation("mineminenomi", "bandit/large_tent");
/*  36 */   private static final ResourceLocation FIRE_PLACE = new ResourceLocation("mineminenomi", "props/fire_place");
/*     */   
/*  38 */   private static final Map<ResourceLocation, BlockPos> CENTER_OFFSET = (Map<ResourceLocation, BlockPos>)ImmutableMap.of(SMALL_TENT, new BlockPos(3, 0, 3), LARGE_TENT, new BlockPos(0, 0, 0), FIRE_PLACE, new BlockPos(0, 0, 0));
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void addComponents(TemplateManager templateManager, BlockPos pos, Random rand, List<StructurePiece> components) {
/*  44 */     List<BlockPos> list = new ArrayList<>();
/*  45 */     boolean hasExtraTents = false;
/*     */     
/*  47 */     if (WyHelper.randomDouble() < 0.1D) {
/*  48 */       hasExtraTents = true;
/*     */     }
/*  50 */     components.add(new Piece(templateManager, FIRE_PLACE, pos.func_177982_a(0, 0, 0), Rotation.CLOCKWISE_90, (StructureProcessor)BlockIgnoreStructureProcessor.field_215204_a));
/*     */     
/*  52 */     components.add(new Piece(templateManager, LARGE_TENT, pos.func_177982_a(16, 0, -3), Rotation.CLOCKWISE_90, (StructureProcessor)BlockIgnoreStructureProcessor.field_215204_a));
/*  53 */     list.add(pos.func_177982_a(16, 0, -3));
/*  54 */     if (hasExtraTents) {
/*     */       
/*  56 */       components.add(new Piece(templateManager, LARGE_TENT, pos.func_177982_a(-22, 0, 9), Rotation.COUNTERCLOCKWISE_90, (StructureProcessor)BlockIgnoreStructureProcessor.field_215204_a));
/*  57 */       list.add(pos.func_177982_a(-22, 0, 9));
/*     */     } 
/*     */     
/*  60 */     for (int i = 0; i < (hasExtraTents ? 6 : 4); i++) {
/*     */       
/*  62 */       BlockPos piecePos = trySpawnTent(pos, 0, list);
/*  63 */       if (piecePos != null) {
/*     */         
/*  65 */         components.add(new Piece(templateManager, SMALL_TENT, piecePos, Rotation.func_222466_a(rand), (StructureProcessor)BlockIgnoreStructureProcessor.field_215204_a));
/*  66 */         list.add(piecePos);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static BlockPos trySpawnTent(BlockPos pos, int attempts, List<BlockPos> list) {
/*  72 */     if (attempts > 20)
/*  73 */       return null; 
/*  74 */     BlockPos random = new BlockPos(WyHelper.randomWithRange(-40, 40), 0.0D, WyHelper.randomWithRange(-40, 40));
/*  75 */     BlockPos piecePos = pos.func_177982_a(random.func_177958_n(), 0, random.func_177952_p());
/*  76 */     for (BlockPos tentPos : list) {
/*     */       
/*  78 */       if (Math.sqrt(tentPos.func_177951_i((Vector3i)piecePos)) < 15.0D)
/*  79 */         return trySpawnTent(piecePos, ++attempts, list); 
/*     */     } 
/*  81 */     return piecePos;
/*     */   }
/*     */   
/*     */   public static class Piece
/*     */     extends TemplateStructurePiece
/*     */   {
/*     */     private ResourceLocation resourceLocation;
/*     */     private Rotation rotation;
/*     */     private StructureProcessor processor;
/*     */     
/*     */     public Piece(TemplateManager template, CompoundNBT nbt) {
/*  92 */       super(ModStructures.Pieces.BANDIT_CAMP_PIECE, nbt);
/*  93 */       this.resourceLocation = new ResourceLocation(nbt.func_74779_i("Template"));
/*  94 */       this.rotation = Rotation.valueOf(nbt.func_74779_i("Rot"));
/*  95 */       this.processor = (StructureProcessor)BlockIgnoreStructureProcessor.field_215204_a;
/*  96 */       build(template);
/*     */     }
/*     */ 
/*     */     
/*     */     public Piece(TemplateManager template, ResourceLocation res, BlockPos pos, Rotation rot, StructureProcessor proc) {
/* 101 */       super(ModStructures.Pieces.BANDIT_CAMP_PIECE, 0);
/* 102 */       this.rotation = rot;
/* 103 */       this.resourceLocation = res;
/* 104 */       this.field_186178_c = pos;
/* 105 */       this.processor = proc;
/* 106 */       build(template);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected void func_143011_b(CompoundNBT nbt) {
/* 112 */       super.func_143011_b(nbt);
/* 113 */       nbt.func_74778_a("Template", this.resourceLocation.toString());
/* 114 */       nbt.func_74778_a("Rot", this.rotation.name());
/*     */     }
/*     */ 
/*     */     
/*     */     private void build(TemplateManager templateManager) {
/* 119 */       Template template = templateManager.func_200220_a(this.resourceLocation);
/* 120 */       PlacementSettings placementsettings = (new PlacementSettings()).func_186220_a(this.rotation).func_186214_a(Mirror.NONE).func_207665_a((BlockPos)BanditCampPieces.CENTER_OFFSET.get(this.resourceLocation)).func_215222_a(this.processor);
/* 121 */       func_186173_a(template, this.field_186178_c, placementsettings);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void func_186175_a(String function, BlockPos pos, IServerWorld world, Random rand, MutableBoundingBox sbb) {
/* 128 */       if (function.equals("small_tent_chest")) {
/* 129 */         StructuresHelper.spawnLoot((IWorld)world, pos, ModLootTables.BANDIT_CAMP_SMALL_TENT_CHEST);
/*     */       }
/* 131 */       if (function.equals("large_tent_chest")) {
/* 132 */         StructuresHelper.spawnLoot((IWorld)world, pos, ModLootTables.BANDIT_CAMP_LARGE_TENT_CHEST);
/*     */       }
/*     */       
/* 135 */       if (function.equals("small_tent_spawn")) {
/* 136 */         StructuresHelper.spawnMobs((IWorld)world, pos, StructuresHelper.StructureFaction.BANDIT, StructuresHelper.StructureSpawnType.GRUNT, 2);
/*     */       }
/* 138 */       if (function.equals("large_tent_spawn"))
/* 139 */         StructuresHelper.spawnMobs((IWorld)world, pos, StructuresHelper.StructureFaction.BANDIT, StructuresHelper.StructureSpawnType.CAPTAIN, 2); 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\world\features\structures\camp\bandit\BanditCampPieces.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */