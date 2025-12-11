/*    */ package xyz.pixelatedw.mineminenomi.blocks;
/*    */ 
/*    */ import net.minecraft.block.AbstractBlock;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.util.Direction;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.IBlockReader;
/*    */ import net.minecraftforge.common.ToolType;
/*    */ 
/*    */ public class WaxBlock
/*    */   extends Block {
/*    */   public WaxBlock() {
/* 15 */     super(AbstractBlock.Properties.func_200945_a(Material.field_151571_B).func_200948_a(8.0F, 3.0F).harvestTool(ToolType.PICKAXE));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getFireSpreadSpeed(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
/* 21 */     return 100;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
/* 27 */     return 200;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\blocks\WaxBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */