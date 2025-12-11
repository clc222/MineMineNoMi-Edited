/*    */ package xyz.pixelatedw.mineminenomi.blocks;
/*    */ import net.minecraft.block.AbstractBlock;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.block.SaplingBlock;
/*    */ import net.minecraft.block.SoundType;
/*    */ import net.minecraft.block.trees.Tree;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.IBlockReader;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ import xyz.pixelatedw.mineminenomi.world.features.trees.MangroveTree;
/*    */ 
/*    */ public class MangroveSaplingBlock extends SaplingBlock {
/*    */   public MangroveSaplingBlock() {
/* 16 */     super((Tree)new MangroveTree(), AbstractBlock.Properties.func_200945_a(Material.field_151585_k).func_200942_a().func_200944_c().func_200946_b().func_200947_a(SoundType.field_185850_c));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean func_200014_a_(BlockState pState, IBlockReader pLevel, BlockPos pPos) {
/* 22 */     return (pState.func_203425_a(Blocks.field_196658_i) || pState.func_203425_a(Blocks.field_150346_d) || pState.func_203425_a(Blocks.field_196660_k) || pState.func_203425_a(Blocks.field_196661_l) || pState.func_203425_a(Blocks.field_150458_ak) || pState
/* 23 */       .func_203425_a((Block)ModBlocks.MANGROVE_WOOD.get()) || pState.func_203425_a((Block)ModBlocks.MOSS.get()));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\blocks\MangroveSaplingBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */