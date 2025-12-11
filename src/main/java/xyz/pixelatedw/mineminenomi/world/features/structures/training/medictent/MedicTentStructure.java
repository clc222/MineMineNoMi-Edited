/*     */ package xyz.pixelatedw.mineminenomi.world.features.structures.training.medictent;
/*     */ 
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
/*     */ public class MedicTentStructure
/*     */   extends OPStructure<NoFeatureConfig> {
/*     */   public MedicTentStructure() {
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
/*  49 */     if ((event.getClimate()).field_242461_c <= 0.3D) {
/*  50 */       return false;
/*     */     }
/*     */     
/*  53 */     boolean canSpawn = CommonConfig.INSTANCE.canSpawnTrainingStructures();
/*  54 */     if (!canSpawn) {
/*  55 */       return false;
/*     */     }
/*     */     
/*  58 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public StructuresHelper.StructureFaction getFaction() {
/*  63 */     return StructuresHelper.StructureFaction.NEUTRAL;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeProvider biomeSource, long seed, SharedSeedRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, NoFeatureConfig featureConfig) {
/*  69 */     BlockPos centerOfChunk = new BlockPos((chunkX << 4) + 7, 0, (chunkZ << 4) + 7);
/*  70 */     int landHeight = chunkGenerator.func_222531_c(centerOfChunk.func_177958_n(), centerOfChunk.func_177952_p(), Heightmap.Type.WORLD_SURFACE_WG);
/*     */     
/*  72 */     IBlockReader columnOfBlocks = chunkGenerator.func_230348_a_(centerOfChunk.func_177958_n(), centerOfChunk.func_177952_p());
/*     */     
/*  74 */     boolean superFlag = super.func_230363_a_(chunkGenerator, biomeSource, seed, chunkRandom, chunkX, chunkZ, biome, chunkPos, (IFeatureConfig)featureConfig);
/*  75 */     boolean isNotUnderwater = columnOfBlocks.func_180495_p(centerOfChunk.func_177981_b(landHeight)).func_204520_s().func_206888_e();
/*  76 */     boolean isChance = (chunkRandom.nextDouble() * 100.0D < CommonConfig.INSTANCE.getChanceForTrainingStructureSpawn());
/*     */     
/*  78 */     return (superFlag && isNotUnderwater && isChance);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public GenerationStage.Decoration func_236396_f_() {
/*  84 */     return GenerationStage.Decoration.SURFACE_STRUCTURES;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Structure.IStartFactory<NoFeatureConfig> func_214557_a() {
/*  90 */     return Start::new;
/*     */   }
/*     */   
/*     */   public static class Start
/*     */     extends StructureStart<NoFeatureConfig>
/*     */   {
/*     */     public Start(Structure<NoFeatureConfig> structure, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int reference, long seed) {
/*  97 */       super(structure, chunkX, chunkZ, mutableBoundingBox, reference, seed);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void generatePieces(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkGenerator, TemplateManager templateManager, int chunkX, int chunkZ, Biome biomeIn, NoFeatureConfig config) {
/* 103 */       Rotation rotation = Rotation.func_222466_a((Random)this.field_214631_d);
/*     */       
/* 105 */       int x = (chunkX << 4) + 7;
/* 106 */       int z = (chunkZ << 4) + 7;
/*     */ 
/*     */       
/* 109 */       int surfaceY = chunkGenerator.func_222529_a(x, z, Heightmap.Type.WORLD_SURFACE_WG);
/* 110 */       BlockPos blockpos = new BlockPos(x, surfaceY, z);
/*     */       
/* 112 */       this.field_75075_a.add(new MedicTentPiece(templateManager, blockpos, rotation));
/*     */       
/* 114 */       func_202500_a();
/*     */       
/* 116 */       StructuresHelper.SPAWNED_STRUCTURES.add(blockpos);
/* 117 */       WyDebug.debug("Medic Tent spawned at: /tp " + (((StructurePiece)this.field_75075_a.get(0)).func_74874_b()).field_78897_a + " " + (((StructurePiece)this.field_75075_a.get(0)).func_74874_b()).field_78894_e + " " + (((StructurePiece)this.field_75075_a.get(0)).func_74874_b()).field_78896_c);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\world\features\structures\training\medictent\MedicTentStructure.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */