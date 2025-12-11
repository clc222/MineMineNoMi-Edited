/*     */ package xyz.pixelatedw.mineminenomi.world.features.structures.smallbase.marine;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.util.Rotation;
/*     */ import net.minecraft.util.SharedSeedRandom;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.ChunkPos;
/*     */ import net.minecraft.util.math.MathHelper;
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
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class MarineSmallBaseStructure
/*     */   extends OPStructure<NoFeatureConfig> {
/*     */   public MarineSmallBaseStructure() {
/*  34 */     super(NoFeatureConfig.field_236558_a_);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean biomeCheck(BiomeLoadingEvent event) {
/*  39 */     if (event.getCategory() == Biome.Category.OCEAN) {
/*  40 */       return false;
/*     */     }
/*     */     
/*  43 */     if (event.getName() == null) {
/*  44 */       return false;
/*     */     }
/*     */     
/*  47 */     if (event.getName().equals(((Biome)ModBiomes.RAIGO.get()).getRegistryName())) {
/*  48 */       return false;
/*     */     }
/*     */     
/*  51 */     boolean canSpawn = CommonConfig.INSTANCE.canSpawnSmallBases();
/*  52 */     if (!canSpawn) {
/*  53 */       return false;
/*     */     }
/*     */     
/*  56 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public StructuresHelper.StructureFaction getFaction() {
/*  61 */     return StructuresHelper.StructureFaction.MARINE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeProvider biomeSource, long seed, SharedSeedRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, NoFeatureConfig featureConfig) {
/*  67 */     boolean superFlag = super.func_230363_a_(chunkGenerator, biomeSource, seed, chunkRandom, chunkX, chunkZ, biome, chunkPos, (IFeatureConfig)featureConfig);
/*  68 */     boolean isChance = (MathHelper.func_151237_a(WyHelper.randomWithRange((Random)chunkRandom, 0, 100) + WyHelper.randomDouble((Random)chunkRandom), 0.0D, 100.0D) < CommonConfig.INSTANCE.getChanceForSmallBasesSpawn());
/*     */     
/*  70 */     if (!superFlag || !isChance) {
/*  71 */       return false;
/*     */     }
/*  73 */     BlockPos centerOfChunk = new BlockPos((chunkX << 4) + 7, 0, (chunkZ << 4) + 7);
/*  74 */     int landHeight = chunkGenerator.func_222531_c(centerOfChunk.func_177958_n(), centerOfChunk.func_177952_p(), Heightmap.Type.WORLD_SURFACE_WG);
/*     */     
/*  76 */     IBlockReader columnOfBlocks = chunkGenerator.func_230348_a_(centerOfChunk.func_177958_n(), centerOfChunk.func_177952_p());
/*  77 */     boolean isNotUnderwater = columnOfBlocks.func_180495_p(centerOfChunk.func_177981_b(landHeight)).func_204520_s().func_206888_e();
/*     */     
/*  79 */     return isNotUnderwater;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public GenerationStage.Decoration func_236396_f_() {
/*  85 */     return GenerationStage.Decoration.SURFACE_STRUCTURES;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Structure.IStartFactory<NoFeatureConfig> func_214557_a() {
/*  91 */     return Start::new;
/*     */   }
/*     */   
/*     */   public static class Start
/*     */     extends StructureStart<NoFeatureConfig>
/*     */   {
/*     */     public Start(Structure<NoFeatureConfig> structure, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int reference, long seed) {
/*  98 */       super(structure, chunkX, chunkZ, mutableBoundingBox, reference, seed);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void generatePieces(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkGenerator, TemplateManager templateManager, int chunkX, int chunkZ, Biome biomeIn, NoFeatureConfig config) {
/* 104 */       Rotation rotation = Rotation.func_222466_a((Random)this.field_214631_d);
/*     */       
/* 106 */       int x = (chunkX << 4) + 7;
/* 107 */       int z = (chunkZ << 4) + 7;
/*     */ 
/*     */       
/* 110 */       int surfaceY = chunkGenerator.func_222529_a(x, z, Heightmap.Type.WORLD_SURFACE_WG);
/* 111 */       BlockPos blockpos = new BlockPos(x, surfaceY, z);
/*     */       
/* 113 */       this.field_75075_a.add(new MarineSmallBasePiece(templateManager, blockpos, rotation));
/*     */       
/* 115 */       func_202500_a();
/*     */       
/* 117 */       StructuresHelper.SPAWNED_STRUCTURES.add(blockpos);
/* 118 */       WyDebug.debug("Marine Small Base spawned at: /tp " + (((StructurePiece)this.field_75075_a.get(0)).func_74874_b()).field_78897_a + " ~ " + (((StructurePiece)this.field_75075_a.get(0)).func_74874_b()).field_78896_c);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\world\features\structures\smallbase\marine\MarineSmallBaseStructure.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */