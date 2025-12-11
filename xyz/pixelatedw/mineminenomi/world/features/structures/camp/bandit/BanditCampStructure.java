/*     */ package xyz.pixelatedw.mineminenomi.world.features.structures.camp.bandit;
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
/*     */ public class BanditCampStructure
/*     */   extends OPStructure<NoFeatureConfig> {
/*     */   public BanditCampStructure() {
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
/*  47 */     if (event.getName().equals(((Biome)ModBiomes.RAIGO.get()).getRegistryName()) || event.getName().equals(((Biome)ModBiomes.DRUM.get()).getRegistryName())) {
/*  48 */       return false;
/*     */     }
/*     */     
/*  51 */     if ((event.getClimate()).field_242461_c <= 0.3D) {
/*  52 */       return false;
/*     */     }
/*     */     
/*  55 */     boolean canSpawn = CommonConfig.INSTANCE.canSpawnCamps();
/*  56 */     if (!canSpawn) {
/*  57 */       return false;
/*     */     }
/*     */     
/*  60 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public StructuresHelper.StructureFaction getFaction() {
/*  65 */     return StructuresHelper.StructureFaction.BANDIT;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeProvider biomeSource, long seed, SharedSeedRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, NoFeatureConfig featureConfig) {
/*  71 */     boolean superFlag = super.func_230363_a_(chunkGenerator, biomeSource, seed, chunkRandom, chunkX, chunkZ, biome, chunkPos, (IFeatureConfig)featureConfig);
/*  72 */     boolean isChance = (MathHelper.func_151237_a(WyHelper.randomWithRange((Random)chunkRandom, 0, 100) + WyHelper.randomDouble((Random)chunkRandom), 0.0D, 100.0D) < CommonConfig.INSTANCE.getChanceForCampsSpawn());
/*     */     
/*  74 */     if (!superFlag || !isChance) {
/*  75 */       return false;
/*     */     }
/*  77 */     BlockPos centerOfChunk = new BlockPos((chunkX << 4) + 7, 0, (chunkZ << 4) + 7);
/*  78 */     int landHeight = chunkGenerator.func_222531_c(centerOfChunk.func_177958_n(), centerOfChunk.func_177952_p(), Heightmap.Type.WORLD_SURFACE_WG);
/*     */     
/*  80 */     IBlockReader columnOfBlocks = chunkGenerator.func_230348_a_(centerOfChunk.func_177958_n(), centerOfChunk.func_177952_p());
/*  81 */     boolean isNotUnderwater = columnOfBlocks.func_180495_p(centerOfChunk.func_177981_b(landHeight)).func_204520_s().func_206888_e();
/*     */     
/*  83 */     return isNotUnderwater;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public GenerationStage.Decoration func_236396_f_() {
/*  89 */     return GenerationStage.Decoration.SURFACE_STRUCTURES;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Structure.IStartFactory<NoFeatureConfig> func_214557_a() {
/*  95 */     return Start::new;
/*     */   }
/*     */   
/*     */   public static class Start
/*     */     extends StructureStart<NoFeatureConfig>
/*     */   {
/*     */     public Start(Structure<NoFeatureConfig> structure, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int reference, long seed) {
/* 102 */       super(structure, chunkX, chunkZ, mutableBoundingBox, reference, seed);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void generatePieces(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkGenerator, TemplateManager templateManager, int chunkX, int chunkZ, Biome biomeIn, NoFeatureConfig config) {
/* 108 */       Rotation rotation = Rotation.func_222466_a((Random)this.field_214631_d);
/*     */       
/* 110 */       int x = (chunkX << 4) + 7;
/* 111 */       int z = (chunkZ << 4) + 7;
/*     */ 
/*     */       
/* 114 */       int surfaceY = chunkGenerator.func_222529_a(x, z, Heightmap.Type.WORLD_SURFACE_WG);
/* 115 */       BlockPos blockpos = new BlockPos(x, surfaceY, z);
/*     */       
/* 117 */       BanditCampPieces.addComponents(templateManager, blockpos, (Random)this.field_214631_d, this.field_75075_a);
/*     */       
/* 119 */       func_202500_a();
/*     */       
/* 121 */       StructuresHelper.SPAWNED_STRUCTURES.add(blockpos);
/* 122 */       WyDebug.debug("Bandit Camp spawned at: /tp " + (((StructurePiece)this.field_75075_a.get(0)).func_74874_b()).field_78897_a + " ~ " + (((StructurePiece)this.field_75075_a.get(0)).func_74874_b()).field_78896_c);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\world\features\structures\camp\bandit\BanditCampStructure.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */