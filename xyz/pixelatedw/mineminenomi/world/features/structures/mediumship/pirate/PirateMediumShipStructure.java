/*     */ package xyz.pixelatedw.mineminenomi.world.features.structures.mediumship.pirate;
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
/*     */ import net.minecraft.world.gen.feature.IFeatureConfig;
/*     */ import net.minecraft.world.gen.feature.NoFeatureConfig;
/*     */ import net.minecraft.world.gen.feature.structure.Structure;
/*     */ import net.minecraft.world.gen.feature.structure.StructurePiece;
/*     */ import net.minecraft.world.gen.feature.structure.StructureStart;
/*     */ import net.minecraft.world.gen.feature.template.TemplateManager;
/*     */ import net.minecraftforge.event.world.BiomeLoadingEvent;
/*     */ import xyz.pixelatedw.mineminenomi.ModMain;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.StructuresHelper;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.world.features.structures.OPStructure;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyDebug;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class PirateMediumShipStructure
/*     */   extends OPStructure<NoFeatureConfig> {
/*     */   public PirateMediumShipStructure() {
/*  33 */     super(NoFeatureConfig.field_236558_a_);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean biomeCheck(BiomeLoadingEvent event) {
/*  39 */     boolean canSpawn = CommonConfig.INSTANCE.canSpawnMediumShips();
/*  40 */     return (canSpawn && event.getCategory() == Biome.Category.OCEAN);
/*     */   }
/*     */ 
/*     */   
/*     */   public StructuresHelper.StructureFaction getFaction() {
/*  45 */     return StructuresHelper.StructureFaction.PIRATE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeProvider biomeSource, long seed, SharedSeedRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, NoFeatureConfig featureConfig) {
/*  51 */     if (ModMain.hasTerraforgedInstalled()) {
/*  52 */       boolean bool = (MathHelper.func_151237_a(WyHelper.randomWithRange((Random)chunkRandom, 0, 100) + WyHelper.randomDouble((Random)chunkRandom), 0.0D, 100.0D) < CommonConfig.INSTANCE.getChanceForSmallShipsSpawn());
/*  53 */       return bool;
/*     */     } 
/*     */     
/*  56 */     boolean superFlag = super.func_230363_a_(chunkGenerator, biomeSource, seed, chunkRandom, chunkX, chunkZ, biome, chunkPos, (IFeatureConfig)featureConfig);
/*  57 */     boolean isChance = (MathHelper.func_151237_a(WyHelper.randomWithRange((Random)chunkRandom, 0, 100) + WyHelper.randomDouble((Random)chunkRandom), 0.0D, 100.0D) < CommonConfig.INSTANCE.getChanceForMediumShipsSpawn());
/*     */     
/*  59 */     if (!superFlag || !isChance) {
/*  60 */       return false;
/*     */     }
/*     */     
/*  63 */     BlockPos centerOfChunk = new BlockPos((chunkX << 4) + 7, 0, (chunkZ << 4) + 7);
/*  64 */     int oceanLevel = chunkGenerator.func_230356_f_();
/*     */     
/*  66 */     IBlockReader columnOfBlocks = chunkGenerator.func_230348_a_(centerOfChunk.func_177958_n(), centerOfChunk.func_177952_p());
/*  67 */     boolean isNotUnderwater = columnOfBlocks.func_180495_p(centerOfChunk.func_177981_b(oceanLevel)).func_204520_s().func_206888_e();
/*     */     
/*  69 */     return isNotUnderwater;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public GenerationStage.Decoration func_236396_f_() {
/*  75 */     return GenerationStage.Decoration.SURFACE_STRUCTURES;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Structure.IStartFactory<NoFeatureConfig> func_214557_a() {
/*  81 */     return Start::new;
/*     */   }
/*     */   
/*     */   public static class Start
/*     */     extends StructureStart<NoFeatureConfig>
/*     */   {
/*     */     public Start(Structure<NoFeatureConfig> structure, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int reference, long seed) {
/*  88 */       super(structure, chunkX, chunkZ, mutableBoundingBox, reference, seed);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void generatePieces(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkGenerator, TemplateManager templateManager, int chunkX, int chunkZ, Biome biomeIn, NoFeatureConfig config) {
/*  94 */       Rotation rotation = Rotation.func_222466_a((Random)this.field_214631_d);
/*     */       
/*  96 */       int x = (chunkX << 4) + 7;
/*  97 */       int z = (chunkZ << 4) + 7;
/*     */ 
/*     */       
/* 100 */       int surfaceY = chunkGenerator.func_230356_f_();
/* 101 */       BlockPos blockpos = new BlockPos(x, surfaceY, z);
/*     */       
/* 103 */       this.field_75075_a.add(new PirateMediumShipPiece(templateManager, blockpos, rotation));
/*     */       
/* 105 */       this.field_75075_a.forEach(piece -> piece.func_181138_a(0, -4, 0));
/* 106 */       this.field_75075_a.forEach(piece -> (piece.func_74874_b()).field_78895_b -= 4);
/*     */       
/* 108 */       func_202500_a();
/*     */       
/* 110 */       StructuresHelper.SPAWNED_STRUCTURES.add(blockpos);
/* 111 */       WyDebug.debug("Pirate Medium Ship spawned at: /tp " + (((StructurePiece)this.field_75075_a.get(0)).func_74874_b()).field_78897_a + " ~ " + (((StructurePiece)this.field_75075_a.get(0)).func_74874_b()).field_78896_c);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\world\features\structures\mediumship\pirate\PirateMediumShipStructure.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */