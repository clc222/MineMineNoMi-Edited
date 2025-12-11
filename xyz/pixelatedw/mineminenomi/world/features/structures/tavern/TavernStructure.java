/*     */ package xyz.pixelatedw.mineminenomi.world.features.structures.tavern;
/*     */ import java.util.Random;
/*     */ import net.minecraft.util.Rotation;
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
/*     */ public class TavernStructure extends OPStructure<NoFeatureConfig> {
/*     */   public TavernStructure() {
/*  30 */     super(NoFeatureConfig.field_236558_a_);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean biomeCheck(BiomeLoadingEvent event) {
/*  35 */     if (event.getCategory() == Biome.Category.OCEAN) {
/*  36 */       return false;
/*     */     }
/*     */     
/*  39 */     if (event.getName() == null) {
/*  40 */       return false;
/*     */     }
/*     */     
/*  43 */     if (event.getName().equals(((Biome)ModBiomes.RAIGO.get()).getRegistryName())) {
/*  44 */       return false;
/*     */     }
/*     */     
/*  47 */     if (event.getCategory() != Biome.Category.PLAINS && event.getCategory() != Biome.Category.FOREST) {
/*  48 */       return false;
/*     */     }
/*     */     
/*  51 */     boolean canSpawn = CommonConfig.INSTANCE.canSpawnTrainingStructures();
/*  52 */     if (!canSpawn) {
/*  53 */       return false;
/*     */     }
/*     */     
/*  56 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public StructuresHelper.StructureFaction getFaction() {
/*  61 */     return StructuresHelper.StructureFaction.NEUTRAL;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeProvider biomeSource, long seed, SharedSeedRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, NoFeatureConfig featureConfig) {
/*  66 */     BlockPos centerOfChunk = new BlockPos((chunkX << 4) + 7, 0, (chunkZ << 4) + 7);
/*  67 */     int landHeight = chunkGenerator.func_222531_c(centerOfChunk.func_177958_n(), centerOfChunk.func_177952_p(), Heightmap.Type.WORLD_SURFACE_WG);
/*     */     
/*  69 */     IBlockReader columnOfBlocks = chunkGenerator.func_230348_a_(centerOfChunk.func_177958_n(), centerOfChunk.func_177952_p());
/*     */     
/*  71 */     boolean superFlag = super.func_230363_a_(chunkGenerator, biomeSource, seed, chunkRandom, chunkX, chunkZ, biome, chunkPos, (IFeatureConfig)featureConfig);
/*  72 */     boolean isNotUnderwater = columnOfBlocks.func_180495_p(centerOfChunk.func_177981_b(landHeight)).func_204520_s().func_206888_e();
/*  73 */     boolean isChance = (chunkRandom.nextDouble() * 100.0D < CommonConfig.INSTANCE.getChanceForTrainingStructureSpawn());
/*     */     
/*  75 */     return (superFlag && isNotUnderwater && isChance);
/*     */   }
/*     */ 
/*     */   
/*     */   public GenerationStage.Decoration func_236396_f_() {
/*  80 */     return GenerationStage.Decoration.SURFACE_STRUCTURES;
/*     */   }
/*     */ 
/*     */   
/*     */   public Structure.IStartFactory<NoFeatureConfig> func_214557_a() {
/*  85 */     return Start::new;
/*     */   }
/*     */   
/*     */   public static class Start extends StructureStart<NoFeatureConfig> {
/*     */     public Start(Structure<NoFeatureConfig> structure, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int reference, long seed) {
/*  90 */       super(structure, chunkX, chunkZ, mutableBoundingBox, reference, seed);
/*     */     }
/*     */ 
/*     */     
/*     */     public void generatePieces(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkGenerator, TemplateManager templateManager, int chunkX, int chunkZ, Biome biomeIn, NoFeatureConfig config) {
/*  95 */       Rotation rotation = Rotation.func_222466_a((Random)this.field_214631_d);
/*     */       
/*  97 */       int x = (chunkX << 4) + 7;
/*  98 */       int z = (chunkZ << 4) + 7;
/*     */ 
/*     */       
/* 101 */       int surfaceY = chunkGenerator.func_222529_a(x, z, Heightmap.Type.WORLD_SURFACE_WG);
/* 102 */       BlockPos blockpos = new BlockPos(x, surfaceY - 1, z);
/*     */       
/* 104 */       this.field_75075_a.add(new TavernPiece(templateManager, blockpos, rotation));
/*     */       
/* 106 */       func_202500_a();
/*     */       
/* 108 */       StructuresHelper.SPAWNED_STRUCTURES.add(blockpos);
/* 109 */       WyDebug.debug("Tavern spawned at: /tp " + (((StructurePiece)this.field_75075_a.get(0)).func_74874_b()).field_78897_a + " " + (((StructurePiece)this.field_75075_a.get(0)).func_74874_b()).field_78894_e + " " + (((StructurePiece)this.field_75075_a.get(0)).func_74874_b()).field_78896_c);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\world\features\structures\tavern\TavernStructure.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */