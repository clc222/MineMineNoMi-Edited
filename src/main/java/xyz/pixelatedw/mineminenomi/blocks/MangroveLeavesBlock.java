/*    */ package xyz.pixelatedw.mineminenomi.blocks;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.AbstractBlock;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.block.LeavesBlock;
/*    */ import net.minecraft.block.SoundType;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.state.Property;
/*    */ import net.minecraft.tags.BlockTags;
/*    */ import net.minecraft.util.Direction;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.IBlockReader;
/*    */ import net.minecraft.world.IWorld;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ 
/*    */ public class MangroveLeavesBlock extends LeavesBlock {
/*    */   public MangroveLeavesBlock() {
/* 20 */     super(AbstractBlock.Properties.func_200945_a(Material.field_151584_j).func_200943_b(0.2F).func_200947_a(SoundType.field_185850_c).func_200944_c().func_226896_b_());
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockState func_196271_a(BlockState state, Direction facing, BlockState facingState, IWorld level, BlockPos currentPos, BlockPos facingPos) {
/* 25 */     int i = getDistanceAt(facingState) + 1;
/* 26 */     if (i != 1 || ((Integer)state.func_177229_b((Property)field_208494_a)).intValue() != i) {
/* 27 */       level.func_205220_G_().func_205360_a(currentPos, this, 1);
/*    */     }
/*    */     
/* 30 */     return state;
/*    */   }
/*    */   private static final int DISTANCE_VALUE = 15;
/*    */   private static int getDistanceAt(BlockState neighbor) {
/* 34 */     if (BlockTags.field_200031_h.func_230235_a_(neighbor.func_177230_c())) {
/* 35 */       return 0;
/*    */     }
/* 37 */     return (neighbor.func_177230_c() instanceof LeavesBlock) ? ((Integer)neighbor.func_177229_b((Property)field_208494_a)).intValue() : 15;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225542_b_(BlockState state, ServerWorld level, BlockPos pos, Random random) {
/* 43 */     if (!((Boolean)state.func_177229_b((Property)field_208495_b)).booleanValue() && ((Integer)state.func_177229_b((Property)field_208494_a)).intValue() == 15) {
/* 44 */       func_220075_c(state, (World)level, pos);
/* 45 */       level.func_217377_a(pos, false);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_149653_t(BlockState state) {
/* 52 */     return (((Integer)state.func_177229_b((Property)field_208494_a)).intValue() == 15 && !((Boolean)state.func_177229_b((Property)field_208495_b)).booleanValue());
/*    */   }
/*    */ 
/*    */   
/*    */   public int getFireSpreadSpeed(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
/* 57 */     return 30;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
/* 62 */     return 60;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\blocks\MangroveLeavesBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */