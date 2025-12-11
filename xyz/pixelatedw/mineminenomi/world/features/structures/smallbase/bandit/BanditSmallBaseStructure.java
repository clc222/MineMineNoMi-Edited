/*     */ package xyz.pixelatedw.mineminenomi.world.features.structures.smallbase.bandit;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.util.Rotation;
/*     */ import net.minecraft.util.SharedSeedRandom;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.ChunkPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.MutableBoundingBox;
/*     */ import net.minecraft.util.registry.DynamicRegistries;
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
/*     */ public class BanditSmallBaseStructure
/*     */   extends OPStructure<NoFeatureConfig> {
/*     */   public BanditSmallBaseStructure() {
/*  33 */     super(NoFeatureConfig.field_236558_a_);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean biomeCheck(BiomeLoadingEvent event) {
/*  38 */     if (event.getCategory() == Biome.Category.OCEAN) {
/*  39 */       return false;
/*     */     }
/*     */     
/*  42 */     if (event.getName() == null) {
/*  43 */       return false;
/*     */     }
/*     */     
/*  46 */     if (event.getName().equals(((Biome)ModBiomes.RAIGO.get()).getRegistryName())) {
/*  47 */       return false;
/*     */     }
/*     */     
/*  50 */     boolean canSpawn = CommonConfig.INSTANCE.canSpawnSmallBases();
/*  51 */     if (!canSpawn) {
/*  52 */       return false;
/*     */     }
/*     */     
/*  55 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public StructuresHelper.StructureFaction getFaction() {
/*  60 */     return StructuresHelper.StructureFaction.NEUTRAL;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeProvider biomeSource, long seed, SharedSeedRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, NoFeatureConfig featureConfig) {
/*  66 */     boolean superFlag = super.func_230363_a_(chunkGenerator, biomeSource, seed, chunkRandom, chunkX, chunkZ, biome, chunkPos, (IFeatureConfig)featureConfig);
/*  67 */     boolean isChance = (MathHelper.func_151237_a(WyHelper.randomWithRange((Random)chunkRandom, 0, 100) + WyHelper.randomDouble((Random)chunkRandom), 0.0D, 100.0D) < CommonConfig.INSTANCE.getChanceForLargeBasesSpawn());
/*     */     
/*  69 */     if (!superFlag || !isChance) {
/*  70 */       return false;
/*     */     }
/*  72 */     boolean isFlatGround = WyHelper.isSurfaceFlat(chunkGenerator, chunkX + 1, chunkZ + 2, 2);
/*     */     
/*  74 */     return isFlatGround;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public GenerationStage.Decoration func_236396_f_() {
/*  80 */     return GenerationStage.Decoration.SURFACE_STRUCTURES;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Structure.IStartFactory<NoFeatureConfig> func_214557_a() {
/*  86 */     return Start::new;
/*     */   }
/*     */   
/*     */   public static class Start
/*     */     extends StructureStart<NoFeatureConfig>
/*     */   {
/*     */     public Start(Structure<NoFeatureConfig> structure, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int reference, long seed) {
/*  93 */       super(structure, chunkX, chunkZ, mutableBoundingBox, reference, seed);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void generatePieces(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkGenerator, TemplateManager templateManager, int chunkX, int chunkZ, Biome biomeIn, NoFeatureConfig config) {
/*  99 */       Rotation rotation = Rotation.func_222466_a((Random)this.field_214631_d);
/*     */       
/* 101 */       int x0 = (chunkX << 4) + 7;
/* 102 */       int z0 = (chunkZ << 4) + 7;
/* 103 */       int x1 = (chunkX + 1 << 4) + 7;
/* 104 */       int z1 = (chunkZ + 2 << 4) + 7;
/*     */ 
/*     */       
/* 107 */       int surfaceY = chunkGenerator.func_222529_a(x1, z1, Heightmap.Type.WORLD_SURFACE_WG);
/* 108 */       BlockPos blockpos = new BlockPos(x0, surfaceY, z0);
/*     */       
/* 110 */       BanditSmallBasePieces.addComponents(templateManager, blockpos, this.field_75075_a);
/*     */       
/* 112 */       func_202500_a();
/*     */       
/* 114 */       StructuresHelper.SPAWNED_STRUCTURES.add(blockpos);
/* 115 */       WyDebug.debug("Bandit Small Base spawned at: /tp " + (((StructurePiece)this.field_75075_a.get(0)).func_74874_b()).field_78897_a + " ~ " + (((StructurePiece)this.field_75075_a.get(0)).func_74874_b()).field_78896_c);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\world\features\structures\smallbase\bandit\BanditSmallBaseStructure.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */