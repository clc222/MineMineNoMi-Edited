/*    */ package xyz.pixelatedw.mineminenomi.blocks;
/*    */ 
/*    */ import net.minecraft.block.AbstractBlock;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.util.Direction;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.IBlockReader;
/*    */ 
/*    */ public class MangrovePlanksBlock extends Block {
/*    */   public MangrovePlanksBlock() {
/* 13 */     super(AbstractBlock.Properties.func_200950_a((AbstractBlock)Blocks.field_196662_n));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getFireSpreadSpeed(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
/* 18 */     return 5;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
/* 23 */     return 20;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\blocks\MangrovePlanksBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */