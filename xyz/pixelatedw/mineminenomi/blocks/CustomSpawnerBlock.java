/*    */ package xyz.pixelatedw.mineminenomi.blocks;
/*    */ 
/*    */ import net.minecraft.block.AbstractBlock;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.Direction;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.IBlockReader;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import xyz.pixelatedw.mineminenomi.blocks.tileentities.CustomSpawnerTileEntity;
/*    */ 
/*    */ public class CustomSpawnerBlock
/*    */   extends Block {
/*    */   public CustomSpawnerBlock() {
/* 18 */     super(AbstractBlock.Properties.func_200945_a(Material.field_175972_I).func_200943_b(Float.MAX_VALUE).func_200942_a().func_222380_e());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_200123_i(BlockState state, IBlockReader reader, BlockPos pos) {
/* 24 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public boolean func_200122_a(BlockState state, BlockState adjacentBlockState, Direction side) {
/* 31 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int func_200011_d(BlockState state, IBlockReader worldIn, BlockPos pos) {
/* 37 */     return 0;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public TileEntity createTileEntity(BlockState state, IBlockReader world) {
/* 43 */     return (TileEntity)new CustomSpawnerTileEntity();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean hasTileEntity(BlockState state) {
/* 49 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\blocks\CustomSpawnerBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */