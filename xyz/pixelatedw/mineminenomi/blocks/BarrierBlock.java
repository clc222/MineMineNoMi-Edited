/*    */ package xyz.pixelatedw.mineminenomi.blocks;
/*    */ 
/*    */ import net.minecraft.block.AbstractBlock;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.util.Direction;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.IBlockReader;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ 
/*    */ public class BarrierBlock
/*    */   extends Block {
/*    */   public BarrierBlock() {
/* 16 */     super(AbstractBlock.Properties.func_200945_a(Material.field_175972_I).func_200948_a(-1.0F, 10000.0F).func_226896_b_().func_222380_e());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_200123_i(BlockState state, IBlockReader reader, BlockPos pos) {
/* 22 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public boolean func_200122_a(BlockState state, BlockState adjacentBlockState, Direction side) {
/* 29 */     return (adjacentBlockState.func_177230_c() == this);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int func_200011_d(BlockState state, IBlockReader worldIn, BlockPos pos) {
/* 35 */     return 0;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\blocks\BarrierBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */