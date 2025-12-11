/*    */ package xyz.pixelatedw.mineminenomi.world.features;
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.block.SnowyDirtBlock;
/*    */ import net.minecraft.state.Property;
/*    */ import net.minecraft.util.Direction;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.ISeedReader;
/*    */ import net.minecraft.world.IWorldReader;
/*    */ import net.minecraft.world.biome.Biome;
/*    */ import net.minecraft.world.gen.ChunkGenerator;
/*    */ import net.minecraft.world.gen.Heightmap;
/*    */ import net.minecraft.world.gen.feature.IFeatureConfig;
/*    */ import net.minecraft.world.gen.feature.NoFeatureConfig;
/*    */ 
/*    */ public class SnowFloorFeature extends Feature<NoFeatureConfig> {
/*    */   public SnowFloorFeature() {
/* 19 */     super(NoFeatureConfig.field_236558_a_);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean place(ISeedReader world, ChunkGenerator chunkGen, Random rand, BlockPos pos, NoFeatureConfig config) {
/* 24 */     BlockPos.Mutable mutpos1 = new BlockPos.Mutable();
/* 25 */     BlockPos.Mutable mutpos2 = new BlockPos.Mutable();
/*    */     
/* 27 */     for (int i = 0; i < 16; i++) {
/* 28 */       for (int j = 0; j < 16; j++) {
/* 29 */         int x = pos.func_177958_n() + i;
/* 30 */         int z = pos.func_177952_p() + j;
/* 31 */         int y = world.func_201676_a(Heightmap.Type.MOTION_BLOCKING, x, z);
/* 32 */         mutpos1.func_181079_c(x, y, z);
/* 33 */         mutpos2.func_189533_g((Vector3i)mutpos1).func_189534_c(Direction.DOWN, 1);
/* 34 */         Biome biome = world.func_226691_t_((BlockPos)mutpos1);
/* 35 */         if (biome.func_201854_a((IWorldReader)world, (BlockPos)mutpos2, false)) {
/* 36 */           world.func_180501_a((BlockPos)mutpos2, Blocks.field_150432_aD.func_176223_P(), 2);
/*    */         }
/*    */         
/* 39 */         if (biome.func_201850_b((IWorldReader)world, (BlockPos)mutpos1)) {
/* 40 */           world.func_180501_a((BlockPos)mutpos1, Blocks.field_196604_cC.func_176223_P(), 2);
/* 41 */           world.func_180501_a(mutpos1.func_177984_a(), Blocks.field_150433_aE.func_176223_P(), 2);
/*    */           
/* 43 */           BlockState blockstate = world.func_180495_p((BlockPos)mutpos2);
/* 44 */           if (blockstate.func_235901_b_((Property)SnowyDirtBlock.field_196382_a)) {
/* 45 */             world.func_180501_a((BlockPos)mutpos2, (BlockState)blockstate.func_206870_a((Property)SnowyDirtBlock.field_196382_a, Boolean.valueOf(true)), 2);
/*    */           }
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 51 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\world\features\SnowFloorFeature.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */