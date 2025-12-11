/*     */ package xyz.pixelatedw.mineminenomi.world.features.structures.largebase.bandit;
/*     */ 
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
/*     */ import net.minecraft.world.gen.feature.structure.StructureManager;
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
/*     */ public class BanditLargeBasePiece
/*     */   extends TemplateStructurePiece
/*     */ {
/*     */   private ResourceLocation resourceLocation;
/*     */   private Rotation rotation;
/*     */   
/*     */   public BanditLargeBasePiece(TemplateManager template, CompoundNBT nbt) {
/*  35 */     super(ModStructures.Pieces.BANDIT_LARGE_BASE_PIECE, nbt);
/*  36 */     this.resourceLocation = new ResourceLocation(nbt.func_74779_i("Template"));
/*  37 */     this.rotation = Rotation.valueOf(nbt.func_74779_i("Rot"));
/*  38 */     build(template);
/*     */   }
/*     */ 
/*     */   
/*     */   public BanditLargeBasePiece(TemplateManager template, BlockPos pos, Rotation rot) {
/*  43 */     super(ModStructures.Pieces.BANDIT_LARGE_BASE_PIECE, 0);
/*  44 */     this.field_186178_c = pos;
/*  45 */     this.rotation = rot;
/*  46 */     this.resourceLocation = new ResourceLocation("mineminenomi", "bandit/large_base");
/*  47 */     build(template);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_143011_b(CompoundNBT nbt) {
/*  53 */     super.func_143011_b(nbt);
/*  54 */     nbt.func_74778_a("Template", this.resourceLocation.toString());
/*  55 */     nbt.func_74778_a("Rot", this.rotation.name());
/*     */   }
/*     */ 
/*     */   
/*     */   private void build(TemplateManager templateManager) {
/*  60 */     Template template = templateManager.func_200220_a(this.resourceLocation);
/*  61 */     PlacementSettings placementsettings = (new PlacementSettings()).func_186220_a(this.rotation).func_186214_a(Mirror.NONE).func_215222_a((StructureProcessor)BlockIgnoreStructureProcessor.field_215204_a);
/*  62 */     func_186173_a(template, this.field_186178_c, placementsettings);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_186175_a(String function, BlockPos pos, IServerWorld world, Random rand, MutableBoundingBox sbb) {
/*  69 */     if (function.equals("tower_supplies")) {
/*  70 */       StructuresHelper.spawnLoot((IWorld)world, pos, ModLootTables.LARGE_BANDIT_BASE_TOWER_CHEST);
/*     */     }
/*  72 */     if (function.equals("tent_supplies")) {
/*  73 */       StructuresHelper.spawnLoot((IWorld)world, pos, ModLootTables.LARGE_BANDIT_BASE_TENT_CHEST);
/*     */     }
/*  75 */     if (function.equals("secret_stash")) {
/*  76 */       StructuresHelper.spawnLoot((IWorld)world, pos, ModLootTables.LARGE_BANDIT_BASE_SECRET_STASH_CHEST);
/*     */     }
/*  78 */     if (function.equals("kitchen_supplies")) {
/*  79 */       StructuresHelper.spawnLoot((IWorld)world, pos, ModLootTables.LARGE_BANDIT_BASE_FOOD_CHEST);
/*     */     }
/*  81 */     if (function.equals("dorm_supplies")) {
/*  82 */       StructuresHelper.spawnLoot((IWorld)world, pos, ModLootTables.LARGE_BANDIT_BASE_DORM_CHEST);
/*     */     }
/*     */     
/*  85 */     if (function.equals("tent_spawn")) {
/*  86 */       StructuresHelper.spawnMobs((IWorld)world, pos, StructuresHelper.StructureFaction.BANDIT, StructuresHelper.StructureSpawnType.GRUNT, 2);
/*     */     }
/*  88 */     if (function.equals("back_spawn")) {
/*  89 */       StructuresHelper.spawnMobs((IWorld)world, pos, StructuresHelper.StructureFaction.BANDIT, StructuresHelper.StructureSpawnType.GRUNT, 6);
/*     */     }
/*  91 */     if (function.equals("tower_spawn")) {
/*  92 */       StructuresHelper.spawnMobs((IWorld)world, pos, StructuresHelper.StructureFaction.BANDIT, StructuresHelper.StructureSpawnType.SNIPER, 1);
/*     */     }
/*  94 */     if (function.equals("main_tower_spawn")) {
/*  95 */       StructuresHelper.spawnMobs((IWorld)world, pos, StructuresHelper.StructureFaction.BANDIT, StructuresHelper.StructureSpawnType.SNIPER, 3);
/*     */     }
/*  97 */     if (function.equals("court_spawn")) {
/*  98 */       StructuresHelper.spawnMobs((IWorld)world, pos, StructuresHelper.StructureFaction.BANDIT, StructuresHelper.StructureSpawnType.GRUNT, 10);
/*     */     }
/* 100 */     if (function.equals("storage_spawn")) {
/* 101 */       StructuresHelper.spawnMobs((IWorld)world, pos, StructuresHelper.StructureFaction.BANDIT, StructuresHelper.StructureSpawnType.CAPTAIN, 3);
/*     */     }
/* 103 */     if (function.equals("kitchen_spawn")) {
/* 104 */       StructuresHelper.spawnMobs((IWorld)world, pos, StructuresHelper.StructureFaction.BANDIT, StructuresHelper.StructureSpawnType.BRUTE, 3);
/*     */     }
/* 106 */     if (function.equals("bedroom_spawn")) {
/* 107 */       StructuresHelper.spawnMobs((IWorld)world, pos, StructuresHelper.StructureFaction.BANDIT, StructuresHelper.StructureSpawnType.BRUTE, 3);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_230383_a_(ISeedReader world, StructureManager pStructureManager, ChunkGenerator pChunkGenerator, Random pRandom, MutableBoundingBox pBox, ChunkPos pChunkPos, BlockPos pPos) {
/* 113 */     BlockPos ogPos = this.field_186178_c;
/* 114 */     this.field_186178_c = this.field_186178_c.func_177982_a(0, -1, 0);
/* 115 */     boolean flag = super.func_230383_a_(world, pStructureManager, pChunkGenerator, pRandom, pBox, pChunkPos, pPos);
/* 116 */     this.field_186178_c = ogPos;
/* 117 */     return flag;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\world\features\structures\largebase\bandit\BanditLargeBasePiece.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */