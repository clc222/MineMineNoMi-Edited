/*    */ package xyz.pixelatedw.mineminenomi.world.features;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.SectionPos;
/*    */ import net.minecraft.world.ISeedReader;
/*    */ import net.minecraft.world.gen.ChunkGenerator;
/*    */ import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
/*    */ import net.minecraft.world.gen.feature.Feature;
/*    */ import net.minecraft.world.gen.feature.IFeatureConfig;
/*    */ import net.minecraft.world.gen.feature.structure.Structure;
/*    */ 
/*    */ public class LargeLakesFeature extends Feature<BlockStateFeatureConfig> {
/*    */   public LargeLakesFeature() {
/* 15 */     super(BlockStateFeatureConfig.field_236455_a_);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean place(ISeedReader world, ChunkGenerator chunkGen, Random random, BlockPos pos, BlockStateFeatureConfig config) {
/* 20 */     while (pos.func_177956_o() > 9 && world.func_175623_d(pos)) {
/* 21 */       pos = pos.func_177977_b();
/*    */     }
/*    */     
/* 24 */     if (pos.func_177956_o() <= 8) {
/* 25 */       return false;
/*    */     }
/* 27 */     if (world.func_241827_a(SectionPos.func_218167_a(pos), Structure.field_236381_q_).findAny().isPresent()) {
/* 28 */       return false;
/*    */     }
/*    */     
/* 31 */     int x0 = pos.func_177958_n();
/* 32 */     int y0 = pos.func_177956_o();
/* 33 */     int z0 = pos.func_177952_p();
/*    */     
/* 35 */     int size = 4 + random.nextInt(4);
/*    */     
/* 37 */     int radiusXZ = size;
/* 38 */     int radiusY = Math.max(1, size / 2);
/*    */     
/* 40 */     BlockPos.Mutable mutpos = new BlockPos.Mutable(); double y;
/* 41 */     for (y = (y0 - radiusY); y < (y0 + radiusY); y++) {
/* 42 */       double x; for (x = (x0 - radiusXZ); x < (x0 + radiusXZ); x++) {
/* 43 */         double z; for (z = (z0 - radiusXZ); z < (z0 + radiusXZ); z++) {
/* 44 */           double distance = (x0 - x) * (x0 - x) + (z0 - z) * (z0 - z) + (y0 - y) * (y0 - y);
/* 45 */           if (distance < (radiusXZ * radiusY)) {
/* 46 */             mutpos.func_189532_c(x, y, z);
/* 47 */             if (mutpos.func_177956_o() <= pos.func_177956_o()) {
/* 48 */               world.func_180501_a((BlockPos)mutpos, config.field_227270_a_, 2);
/*    */             }
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 55 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\world\features\LargeLakesFeature.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */