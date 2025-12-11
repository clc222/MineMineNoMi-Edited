/*     */ package xyz.pixelatedw.mineminenomi.world.features.structures.largebase.bandit;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.util.Rotation;
/*     */ import net.minecraft.util.SharedSeedRandom;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.ChunkPos;
/*     */ import net.minecraft.util.math.MutableBoundingBox;
/*     */ import net.minecraft.util.math.vector.Vector3i;
/*     */ import net.minecraft.util.registry.DynamicRegistries;
/*     */ import net.minecraft.world.ISeedReader;
/*     */ import net.minecraft.world.biome.Biome;
/*     */ import net.minecraft.world.biome.provider.BiomeProvider;
/*     */ import net.minecraft.world.gen.ChunkGenerator;
/*     */ import net.minecraft.world.gen.GenerationStage;
/*     */ import net.minecraft.world.gen.Heightmap;
/*     */ import net.minecraft.world.gen.feature.IFeatureConfig;
/*     */ import net.minecraft.world.gen.feature.NoFeatureConfig;
/*     */ import net.minecraft.world.gen.feature.structure.Structure;
/*     */ import net.minecraft.world.gen.feature.structure.StructureManager;
/*     */ import net.minecraft.world.gen.feature.structure.StructurePiece;
/*     */ import net.minecraft.world.gen.feature.structure.StructureStart;
/*     */ import net.minecraft.world.gen.feature.template.TemplateManager;
/*     */ import net.minecraftforge.event.world.BiomeLoadingEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.StructuresHelper;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.world.features.structures.OPStructure;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyDebug;
/*     */ 
/*     */ public class BanditLargeBaseStructure
/*     */   extends OPStructure<NoFeatureConfig>
/*     */ {
/*     */   public BanditLargeBaseStructure() {
/*  35 */     super(NoFeatureConfig.field_236558_a_);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean biomeCheck(BiomeLoadingEvent event) {
/*  40 */     if (event.getCategory() == Biome.Category.OCEAN) {
/*  41 */       return false;
/*     */     }
/*     */     
/*  44 */     if (event.getName() == null) {
/*  45 */       return false;
/*     */     }
/*     */     
/*  48 */     boolean canSpawn = CommonConfig.INSTANCE.canSpawnLargeBases();
/*  49 */     if (!canSpawn) {
/*  50 */       return false;
/*     */     }
/*     */     
/*  53 */     return (event.getCategory() == Biome.Category.FOREST || event.getCategory() == Biome.Category.JUNGLE || event.getCategory() == Biome.Category.TAIGA);
/*     */   }
/*     */ 
/*     */   
/*     */   public StructuresHelper.StructureFaction getFaction() {
/*  58 */     return StructuresHelper.StructureFaction.BANDIT;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeProvider biomeSource, long seed, SharedSeedRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, NoFeatureConfig featureConfig) {
/*  64 */     boolean superFlag = super.func_230363_a_(chunkGenerator, biomeSource, seed, chunkRandom, chunkX, chunkZ, biome, chunkPos, (IFeatureConfig)featureConfig);
/*  65 */     boolean isChance = (chunkRandom.nextDouble() * 100.0D < CommonConfig.INSTANCE.getChanceForLargeBasesSpawn());
/*     */     
/*  67 */     if (!superFlag || !isChance) {
/*  68 */       return false;
/*     */     }
/*  70 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public GenerationStage.Decoration func_236396_f_() {
/*  76 */     return GenerationStage.Decoration.SURFACE_STRUCTURES;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Structure.IStartFactory<NoFeatureConfig> func_214557_a() {
/*  82 */     return Start::new;
/*     */   }
/*     */   
/*     */   public static class Start
/*     */     extends StructureStart<NoFeatureConfig>
/*     */   {
/*     */     public Start(Structure<NoFeatureConfig> structure, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int reference, long seed) {
/*  89 */       super(structure, chunkX, chunkZ, mutableBoundingBox, reference, seed);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void generatePieces(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkGenerator, TemplateManager templateManager, int chunkX, int chunkZ, Biome biomeIn, NoFeatureConfig config) {
/*  95 */       Rotation rotation = Rotation.func_222466_a((Random)this.field_214631_d);
/*     */       
/*  97 */       int x0 = (chunkX << 4) + 7;
/*  98 */       int z0 = (chunkZ << 4) + 7;
/*  99 */       int x1 = (chunkX + 1 << 4) + 7;
/* 100 */       int z1 = (chunkZ + 2 << 4) + 7;
/*     */ 
/*     */       
/* 103 */       int surfaceY = chunkGenerator.func_222529_a(x1, z1, Heightmap.Type.WORLD_SURFACE_WG);
/* 104 */       BlockPos blockpos = new BlockPos(x0, surfaceY, z0);
/*     */       
/* 106 */       this.field_75075_a.add(new BanditLargeBasePiece(templateManager, blockpos, rotation));
/*     */       
/* 108 */       func_202500_a();
/*     */       
/* 110 */       StructuresHelper.SPAWNED_STRUCTURES.add(blockpos);
/* 111 */       WyDebug.debug("Bandit Large Base spawned at: /tp " + (((StructurePiece)this.field_75075_a.get(0)).func_74874_b()).field_78897_a + " ~ " + (((StructurePiece)this.field_75075_a.get(0)).func_74874_b()).field_78896_c);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void func_230366_a_(ISeedReader pLevel, StructureManager pStructureManager, ChunkGenerator pChunkGenerator, Random pRandom, MutableBoundingBox pBox, ChunkPos pChunkPos) {
/* 117 */       super.func_230366_a_(pLevel, pStructureManager, pChunkGenerator, pRandom, pBox, pChunkPos);
/* 118 */       int i = this.field_75074_b.field_78895_b;
/*     */       
/* 120 */       for (int j = pBox.field_78897_a; j <= pBox.field_78893_d; j++) {
/*     */         
/* 122 */         for (int k = pBox.field_78896_c; k <= pBox.field_78892_f; k++) {
/*     */           
/* 124 */           BlockPos blockpos = new BlockPos(j, i, k);
/* 125 */           if (!pLevel.func_175623_d(blockpos) && this.field_75074_b.func_175898_b((Vector3i)blockpos)) {
/*     */             
/* 127 */             boolean flag = false;
/*     */             
/* 129 */             for (StructurePiece structurepiece : this.field_75075_a) {
/*     */               
/* 131 */               if (structurepiece.func_74874_b().func_175898_b((Vector3i)blockpos)) {
/*     */                 
/* 133 */                 flag = true;
/*     */                 
/*     */                 break;
/*     */               } 
/*     */             } 
/* 138 */             if (flag)
/*     */             {
/* 140 */               for (int l = i - 1; l > 1; l--) {
/*     */                 
/* 142 */                 BlockPos blockpos1 = new BlockPos(j, l, k);
/* 143 */                 if (!pLevel.func_175623_d(blockpos1) && !pLevel.func_180495_p(blockpos1).func_185904_a().func_76224_d()) {
/*     */                   break;
/*     */                 }
/* 146 */                 pLevel.func_180501_a(blockpos1, Blocks.field_150346_d.func_176223_P(), 2);
/*     */               } 
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\world\features\structures\largebase\bandit\BanditLargeBaseStructure.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */