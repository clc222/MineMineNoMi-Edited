/*    */ package xyz.pixelatedw.mineminenomi.world.features;
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.world.IBlockReader;
/*    */ import net.minecraft.world.ISeedReader;
/*    */ import net.minecraft.world.IWorldWriter;
/*    */ import net.minecraft.world.gen.ChunkGenerator;
/*    */ import net.minecraft.world.gen.feature.IFeatureConfig;
/*    */ import net.minecraft.world.gen.feature.ProbabilityConfig;
/*    */ 
/*    */ public class LargeIceSpikeFeature extends Feature<ProbabilityConfig> {
/*    */   public LargeIceSpikeFeature() {
/* 17 */     super(ProbabilityConfig.field_236576_b_);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean place(ISeedReader world, ChunkGenerator chunkGen, Random rand, BlockPos pos, ProbabilityConfig config) {
/* 22 */     if (rand.nextFloat() >= config.field_203622_a) {
/* 23 */       return false;
/*    */     }
/*    */     
/* 26 */     int x = pos.func_177958_n() + rand.nextInt(64);
/* 27 */     int z = pos.func_177952_p() + rand.nextInt(64);
/* 28 */     int y = world.func_201676_a(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, x, z);
/*    */     
/* 30 */     pos = new BlockPos(x, y, z);
/*    */     
/* 32 */     int i = rand.nextInt(20) + 12;
/* 33 */     int j = i / 4 + rand.nextInt(2);
/*    */     
/* 35 */     for (int k = 0; k < i; k++) {
/* 36 */       float f = (1.0F - k / i) * j;
/* 37 */       int l = MathHelper.func_76123_f(f);
/*    */       
/* 39 */       for (int i1 = -l; i1 <= l; i1++) {
/* 40 */         float f1 = MathHelper.func_76130_a(i1) - 0.25F;
/*    */         
/* 42 */         for (int j1 = -l; j1 <= l; j1++) {
/* 43 */           float f2 = MathHelper.func_76130_a(j1) - 0.25F;
/* 44 */           if (((i1 == 0 && j1 == 0) || f1 * f1 + f2 * f2 <= f * f) && ((i1 != -l && i1 != l && j1 != -l && j1 != l) || rand.nextFloat() <= 0.75F)) {
/* 45 */             BlockState blockstate = world.func_180495_p(pos.func_177982_a(i1, k, j1));
/* 46 */             Block block = blockstate.func_177230_c();
/* 47 */             if (blockstate.isAir((IBlockReader)world, pos.func_177982_a(i1, k, j1)) || func_227250_b_(block) || block == Blocks.field_196604_cC || block == Blocks.field_150432_aD) {
/* 48 */               func_230367_a_((IWorldWriter)world, pos.func_177982_a(i1, k, j1), Blocks.field_150403_cj.func_176223_P());
/*    */             }
/*    */             
/* 51 */             if (k != 0 && l > 1) {
/* 52 */               blockstate = world.func_180495_p(pos.func_177982_a(i1, -k, j1));
/* 53 */               block = blockstate.func_177230_c();
/* 54 */               if (blockstate.isAir((IBlockReader)world, pos.func_177982_a(i1, -k, j1)) || func_227250_b_(block) || block == Blocks.field_196604_cC || block == Blocks.field_150432_aD) {
/* 55 */                 func_230367_a_((IWorldWriter)world, pos.func_177982_a(i1, -k, j1), Blocks.field_150403_cj.func_176223_P());
/*    */               }
/*    */             } 
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 63 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\world\features\LargeIceSpikeFeature.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */