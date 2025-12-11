/*    */ package xyz.pixelatedw.mineminenomi.world.features;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.ISeedReader;
/*    */ import net.minecraft.world.gen.ChunkGenerator;
/*    */ import net.minecraft.world.gen.feature.Feature;
/*    */ import net.minecraft.world.gen.feature.IFeatureConfig;
/*    */ import net.minecraft.world.gen.feature.NoFeatureConfig;
/*    */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ 
/*    */ public class PoneglyphFeature
/*    */   extends Feature<NoFeatureConfig> {
/*    */   public PoneglyphFeature() {
/* 18 */     super(NoFeatureConfig.field_236558_a_);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean place(ISeedReader world, ChunkGenerator chunkGen, Random rand, BlockPos pos, NoFeatureConfig config) {
/* 24 */     boolean isChance = (world.func_201674_k().nextDouble() * 100.0D < CommonConfig.INSTANCE.getChanceForPoneglyphSpawn());
/* 25 */     if (!isChance) {
/* 26 */       return false;
/*    */     }
/* 28 */     int spawnChecks = 0;
/*    */     
/*    */     int i;
/* 31 */     for (i = -2; i < 3; i++) {
/*    */       
/* 33 */       for (int j = -2; j < 3; j++) {
/*    */         
/* 35 */         for (int k = -2; k < 3; k++) {
/*    */           
/* 37 */           BlockPos blockpos = pos.func_177982_a(i, j, k);
/* 38 */           Material material = world.func_180495_p(blockpos).func_185904_a();
/* 39 */           if (!material.func_76220_a()) {
/* 40 */             return false;
/*    */           }
/* 42 */           spawnChecks++;
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 47 */     if (spawnChecks >= 9)
/*    */     {
/* 49 */       for (i = -1; i < 2; i++) {
/*    */         
/* 51 */         for (int j = -1; j < 2; j++) {
/*    */           
/* 53 */           for (int k = -1; k < 2; k++) {
/*    */             
/* 55 */             BlockPos pos2 = pos.func_177982_a(i, j, k);
/* 56 */             world.func_180501_a(pos2, ((Block)ModBlocks.PONEGLYPH.get()).func_176223_P(), 2);
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 64 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\world\features\PoneglyphFeature.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */