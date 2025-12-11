/*     */ package xyz.pixelatedw.mineminenomi.world.features.structures.largebase.marine;
/*     */ 
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.util.SharedSeedRandom;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.ChunkPos;
/*     */ import net.minecraft.util.math.MutableBoundingBox;
/*     */ import net.minecraft.util.registry.DynamicRegistries;
/*     */ import net.minecraft.world.IBlockReader;
/*     */ import net.minecraft.world.biome.Biome;
/*     */ import net.minecraft.world.biome.provider.BiomeProvider;
/*     */ import net.minecraft.world.gen.ChunkGenerator;
/*     */ import net.minecraft.world.gen.GenerationStage;
/*     */ import net.minecraft.world.gen.Heightmap;
/*     */ import net.minecraft.world.gen.feature.IFeatureConfig;
/*     */ import net.minecraft.world.gen.feature.NoFeatureConfig;
/*     */ import net.minecraft.world.gen.feature.structure.Structure;
/*     */ import net.minecraft.world.gen.feature.structure.StructurePiece;
/*     */ import net.minecraft.world.gen.feature.structure.StructureStart;
/*     */ import net.minecraft.world.gen.feature.template.TemplateManager;
/*     */ import net.minecraftforge.event.world.BiomeLoadingEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.StructuresHelper;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModBiomes;
/*     */ import xyz.pixelatedw.mineminenomi.world.features.structures.OPStructure;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyDebug;
/*     */ 
/*     */ public class MarineLargeBaseStructure
/*     */   extends OPStructure<NoFeatureConfig>
/*     */ {
/*     */   public MarineLargeBaseStructure() {
/*  32 */     super(NoFeatureConfig.field_236558_a_);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean biomeCheck(BiomeLoadingEvent event) {
/*  37 */     if (event.getCategory() == Biome.Category.OCEAN) {
/*  38 */       return false;
/*     */     }
/*     */     
/*  41 */     if (event.getName() == null) {
/*  42 */       return false;
/*     */     }
/*     */     
/*  45 */     if (event.getName().equals(((Biome)ModBiomes.RAIGO.get()).getRegistryName())) {
/*  46 */       return false;
/*     */     }
/*     */     
/*  49 */     boolean canSpawn = CommonConfig.INSTANCE.canSpawnLargeBases();
/*  50 */     if (!canSpawn) {
/*  51 */       return false;
/*     */     }
/*     */     
/*  54 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public StructuresHelper.StructureFaction getFaction() {
/*  59 */     return StructuresHelper.StructureFaction.MARINE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeProvider biomeSource, long seed, SharedSeedRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, NoFeatureConfig featureConfig) {
/*  65 */     boolean superFlag = super.func_230363_a_(chunkGenerator, biomeSource, seed, chunkRandom, chunkX, chunkZ, biome, chunkPos, (IFeatureConfig)featureConfig);
/*  66 */     boolean isChance = (chunkRandom.nextDouble() * 100.0D < CommonConfig.INSTANCE.getChanceForLargeBasesSpawn());
/*     */     
/*  68 */     if (!superFlag || !isChance) {
/*  69 */       return false;
/*     */     }
/*  71 */     boolean isSolidGround = true;
/*  72 */     int minY = 0;
/*  73 */     int maxY = 0;
/*  74 */     for (int i = 0; i < 3; i++) {
/*     */       
/*  76 */       if (!isSolidGround)
/*     */         break; 
/*  78 */       for (int j = 0; j < 3; j++) {
/*     */         
/*  80 */         BlockPos centerOfChunk = new BlockPos((chunkX + i << 4) + 7, 0, (chunkZ + j << 4) + 7);
/*  81 */         int landHeight = chunkGenerator.func_222531_c(centerOfChunk.func_177958_n(), centerOfChunk.func_177952_p(), Heightmap.Type.WORLD_SURFACE_WG);
/*  82 */         IBlockReader columnOfBlocks = chunkGenerator.func_230348_a_(centerOfChunk.func_177958_n(), centerOfChunk.func_177952_p());
/*  83 */         BlockState state = columnOfBlocks.func_180495_p(centerOfChunk.func_177981_b(landHeight));
/*  84 */         if (state.func_196958_f() || !state.func_204520_s().func_206888_e() || !state.func_185904_a().func_76220_a()) {
/*     */           
/*  86 */           isSolidGround = false;
/*     */           
/*     */           break;
/*     */         } 
/*     */         
/*  91 */         if (minY == 0) minY = landHeight; 
/*  92 */         if (maxY == 0) maxY = landHeight;
/*     */         
/*  94 */         minY = Math.min(minY, landHeight);
/*  95 */         maxY = Math.max(maxY, landHeight);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 100 */     boolean isFlatGround = true;
/*     */     
/* 102 */     return (isFlatGround && isSolidGround);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public GenerationStage.Decoration func_236396_f_() {
/* 108 */     return GenerationStage.Decoration.SURFACE_STRUCTURES;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Structure.IStartFactory<NoFeatureConfig> func_214557_a() {
/* 114 */     return Start::new;
/*     */   }
/*     */   
/*     */   public static class Start
/*     */     extends StructureStart<NoFeatureConfig>
/*     */   {
/*     */     public Start(Structure<NoFeatureConfig> structure, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int reference, long seed) {
/* 121 */       super(structure, chunkX, chunkZ, mutableBoundingBox, reference, seed);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void generatePieces(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkGenerator, TemplateManager templateManager, int chunkX, int chunkZ, Biome biomeIn, NoFeatureConfig config) {
/* 127 */       BlockPos blockpos = null;
/* 128 */       for (int i = 0; i < 4; i++) {
/*     */         
/* 130 */         for (int j = 0; j < 4; j++) {
/*     */           
/* 132 */           BlockPos centerOfChunk = new BlockPos((chunkX + i << 4) - 7, 0, (chunkZ + j << 4) - 7);
/* 133 */           int landHeight = chunkGenerator.func_222529_a(centerOfChunk.func_177958_n(), centerOfChunk.func_177952_p(), Heightmap.Type.WORLD_SURFACE_WG);
/* 134 */           if (blockpos == null || (blockpos != null && blockpos.func_177956_o() > landHeight)) {
/* 135 */             blockpos = new BlockPos(centerOfChunk.func_177958_n(), landHeight, centerOfChunk.func_177952_p());
/*     */           }
/*     */         } 
/*     */       } 
/* 139 */       MarineLargeBasePieces.addComponents(templateManager, blockpos, this.field_75075_a);
/*     */       
/* 141 */       this.field_75075_a.forEach(piece -> piece.func_181138_a(0, -6, 0));
/*     */       
/* 143 */       func_202500_a();
/*     */       
/* 145 */       BlockPos structSize = new BlockPos((func_75071_a()).field_78893_d - (func_75071_a()).field_78897_a, 0, (func_75071_a()).field_78892_f - (func_75071_a()).field_78896_c);
/* 146 */       StructuresHelper.SPAWNED_STRUCTURES.add(blockpos);
/* 147 */       WyDebug.debug("Start of Marine Large Base spawned at: /tp " + (func_75071_a()).field_78897_a + " ~ " + (func_75071_a()).field_78896_c);
/* 148 */       WyDebug.debug("End of Marine Large Base spawned at: /tp " + (func_75071_a()).field_78893_d + " ~ " + (func_75071_a()).field_78892_f);
/* 149 */       WyDebug.debug("Size of Marine Large Base: " + structSize.func_177958_n() + ", " + structSize.func_177956_o() + ", " + structSize.func_177952_p());
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\world\features\structures\largebase\marine\MarineLargeBaseStructure.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */