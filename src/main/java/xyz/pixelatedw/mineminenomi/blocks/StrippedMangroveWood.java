/*    */ package xyz.pixelatedw.mineminenomi.blocks;
/*    */ import net.minecraft.block.AbstractBlock;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.block.RotatedPillarBlock;
/*    */ import net.minecraft.util.Direction;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.IBlockReader;
/*    */ 
/*    */ public class StrippedMangroveWood extends RotatedPillarBlock {
/*    */   public StrippedMangroveWood() {
/* 12 */     super(AbstractBlock.Properties.func_200950_a((AbstractBlock)Blocks.field_209389_ab));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getFireSpreadSpeed(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
/* 17 */     return 5;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
/* 22 */     return 20;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\blocks\StrippedMangroveWood.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */