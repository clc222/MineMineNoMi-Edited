/*     */ package xyz.pixelatedw.mineminenomi.world.features;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.ISeedReader;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.biome.Biome;
/*     */ import net.minecraft.world.biome.provider.BiomeProvider;
/*     */ import net.minecraft.world.gen.ChunkGenerator;
/*     */ import net.minecraft.world.gen.Heightmap;
/*     */ import net.minecraft.world.gen.feature.Feature;
/*     */ import net.minecraft.world.gen.feature.IFeatureConfig;
/*     */ import net.minecraft.world.gen.feature.ProbabilityConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.StructuresWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class SnowMountainFeature extends Feature<ProbabilityConfig> {
/*     */   public SnowMountainFeature() {
/*  21 */     super(ProbabilityConfig.field_236576_b_);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean place(ISeedReader world, ChunkGenerator chunkGen, Random rand, BlockPos pos, ProbabilityConfig config) {
/*  26 */     if (rand.nextFloat() >= config.field_203622_a) {
/*  27 */       return false;
/*     */     }
/*     */     
/*  30 */     StructuresWorldData structData = StructuresWorldData.get();
/*     */     
/*  32 */     int x0 = pos.func_177958_n();
/*  33 */     int z0 = pos.func_177952_p();
/*  34 */     int y0 = world.func_201676_a(Heightmap.Type.WORLD_SURFACE_WG, x0, z0);
/*  35 */     int radius = 1000;
/*  36 */     int check = radius;
/*  37 */     BlockPos pos0 = new BlockPos(x0, y0, z0);
/*     */     
/*  39 */     if (structData.isNearSnowMountain(pos0, radius)) {
/*  40 */       return false;
/*     */     }
/*     */     
/*  43 */     BiomeProvider biomeSource = chunkGen.func_202090_b();
/*  44 */     for (Biome biomeCheck : biomeSource.func_225530_a_(x0, y0, z0, 10)) {
/*  45 */       if (!biomeCheck.getRegistryName().equals(((Biome)ModBiomes.DRUM.get()).getRegistryName())) {
/*  46 */         return false;
/*     */       }
/*     */     } 
/*     */     
/*  50 */     BlockPos.Mutable mutpos = new BlockPos.Mutable(x0, y0, z0);
/*     */     
/*  52 */     int minY = 0;
/*  53 */     int maxY = 0;
/*  54 */     for (int x = 7; x < 64; x += 16) {
/*  55 */       for (int z = 7; z < 64; z += 16) {
/*  56 */         mutpos.func_181079_c(x0 + x, y0, z0 + z);
/*  57 */         int landHeight = chunkGen.func_222531_c(mutpos.func_177958_n(), mutpos.func_177952_p(), Heightmap.Type.WORLD_SURFACE_WG);
/*  58 */         if (minY == 0) {
/*  59 */           minY = landHeight;
/*     */         }
/*  61 */         if (maxY == 0) {
/*  62 */           maxY = landHeight;
/*     */         }
/*     */         
/*  65 */         minY = Math.min(minY, landHeight);
/*  66 */         maxY = Math.max(maxY, landHeight);
/*     */       } 
/*     */     } 
/*     */     
/*  70 */     double heightDiff = Math.abs(maxY - minY);
/*     */     
/*  72 */     mutpos.func_181079_c(x0, y0, z0);
/*     */     
/*  74 */     for (int y = 0; y < 60; y++) {
/*  75 */       for (int i = x0 - radius; i <= x0 + radius; i++) {
/*  76 */         for (int z = z0 - radius; z <= z0 + radius; z++) {
/*  77 */           double distance = ((x0 - i) * (x0 - i) + (z0 - z) * (z0 - z));
/*     */           
/*  79 */           if (y > 20 && y < 40) {
/*  80 */             check = 950;
/*     */           }
/*  82 */           if (y > 40 && y < 60) {
/*  83 */             check = 900;
/*     */           }
/*     */           
/*  86 */           if (distance < check) {
/*  87 */             mutpos.func_189532_c(i, y0 - heightDiff + y, z);
/*  88 */             BlockState blockstate = world.func_180495_p((BlockPos)mutpos);
/*  89 */             Block block = blockstate.func_177230_c();
/*  90 */             if (blockstate.isAir((IBlockReader)world, (BlockPos)mutpos) || func_227250_b_(block) || block == Blocks.field_196604_cC || block == Blocks.field_150433_aE || block == Blocks.field_150432_aD) {
/*  91 */               WyHelper.swapBlockData((IWorld)world, (BlockPos)mutpos, Blocks.field_196604_cC.func_176223_P());
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  99 */     structData.addSnowMountain(pos0);
/*     */ 
/*     */     
/* 102 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\world\features\SnowMountainFeature.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */