/*    */ package xyz.pixelatedw.mineminenomi.world.features.ocean;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.ISeedReader;
/*    */ import net.minecraft.world.gen.ChunkGenerator;
/*    */ import net.minecraft.world.gen.Heightmap;
/*    */ import net.minecraft.world.gen.feature.Feature;
/*    */ import net.minecraft.world.gen.feature.IFeatureConfig;
/*    */ import net.minecraft.world.gen.feature.NoFeatureConfig;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class OceanFloorModifierFeature
/*    */   extends Feature<NoFeatureConfig>
/*    */ {
/*    */   public OceanFloorModifierFeature() {
/* 20 */     super(NoFeatureConfig.field_236558_a_);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean place(ISeedReader world, ChunkGenerator chunkGenerator, Random random, BlockPos pos, NoFeatureConfig config) {
/* 25 */     for (int i = 0; i < 16; i++) {
/*    */       
/* 27 */       for (int j = 0; j < 16; j++) {
/*    */         
/* 29 */         for (int k = 0; k < 16; k++) {
/*    */           
/* 31 */           int x = pos.func_177958_n() + i;
/* 32 */           int z = pos.func_177952_p() + j;
/* 33 */           int y = world.func_201676_a(Heightmap.Type.OCEAN_FLOOR_WG, x, z) - k;
/* 34 */           if (world.func_180495_p(new BlockPos(x, y, z)).equals(Blocks.field_150354_m.func_176223_P()) || world.func_180495_p(new BlockPos(x, y, z)).equals(Blocks.field_150351_n.func_176223_P())) {
/*    */             
/* 36 */             world.func_180501_a(new BlockPos(x, y, z), Blocks.field_150355_j.func_176223_P(), 2);
/*    */           }
/* 38 */           else if (world.func_180495_p(new BlockPos(x, y, z)).equals(Blocks.field_150348_b.func_176223_P()) && k < 3) {
/*    */             
/* 40 */             world.func_180501_a(new BlockPos(x, y, z), Blocks.field_150355_j.func_176223_P(), 2);
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 46 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\world\features\ocean\OceanFloorModifierFeature.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */