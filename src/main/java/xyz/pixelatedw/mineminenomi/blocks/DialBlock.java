/*    */ package xyz.pixelatedw.mineminenomi.blocks;
/*    */ import net.minecraft.block.AbstractBlock;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.block.HorizontalBlock;
/*    */ import net.minecraft.block.IWaterLoggable;
/*    */ import net.minecraft.fluid.FluidState;
/*    */ import net.minecraft.fluid.Fluids;
/*    */ import net.minecraft.item.BlockItemUseContext;
/*    */ import net.minecraft.state.BooleanProperty;
/*    */ import net.minecraft.state.Property;
/*    */ import net.minecraft.state.StateContainer;
/*    */ import net.minecraft.state.properties.BlockStateProperties;
/*    */ import net.minecraft.tags.ITag;
/*    */ import net.minecraft.util.Direction;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.shapes.ISelectionContext;
/*    */ import net.minecraft.util.math.shapes.VoxelShape;
/*    */ import net.minecraft.world.IBlockReader;
/*    */ import net.minecraft.world.IWorld;
/*    */ import net.minecraft.world.IWorldReader;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ 
/*    */ public class DialBlock extends HorizontalBlock implements IWaterLoggable {
/* 26 */   protected static final VoxelShape SHAPE = Block.func_208617_a(4.0D, 0.0D, 4.0D, 12.0D, 4.0D, 12.0D);
/* 27 */   public static final BooleanProperty WATERLOGGED = BlockStateProperties.field_208198_y;
/*    */   
/*    */   public DialBlock() {
/* 30 */     super(AbstractBlock.Properties.func_200945_a(Material.field_151571_B).func_200943_b(0.2F));
/* 31 */     func_180632_j((BlockState)((BlockState)this.field_176227_L.func_177621_b()).func_206870_a((Property)WATERLOGGED, Boolean.valueOf(false)));
/*    */   }
/*    */ 
/*    */   
/*    */   public DialBlock(AbstractBlock.Properties props) {
/* 36 */     super(props);
/* 37 */     func_180632_j((BlockState)((BlockState)this.field_176227_L.func_177621_b()).func_206870_a((Property)WATERLOGGED, Boolean.valueOf(false)));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public VoxelShape func_220053_a(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
/* 43 */     return SHAPE;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_200123_i(BlockState state, IBlockReader reader, BlockPos pos) {
/* 49 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public boolean func_200122_a(BlockState state, BlockState adjacentBlockState, Direction side) {
/* 56 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int func_200011_d(BlockState state, IBlockReader worldIn, BlockPos pos) {
/* 62 */     return 0;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void func_206840_a(StateContainer.Builder<Block, BlockState> builder) {
/* 68 */     builder.func_206894_a(new Property[] { (Property)WATERLOGGED });
/* 69 */     builder.func_206894_a(new Property[] { (Property)field_185512_D });
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public BlockState func_196271_a(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
/* 75 */     if (((Boolean)stateIn.func_177229_b((Property)WATERLOGGED)).booleanValue())
/*    */     {
/* 77 */       worldIn.func_205219_F_().func_205360_a(currentPos, Fluids.field_204546_a, Fluids.field_204546_a.func_205569_a((IWorldReader)worldIn));
/*    */     }
/*    */     
/* 80 */     return super.func_196271_a(stateIn, facing, facingState, worldIn, currentPos, facingPos);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public FluidState func_204507_t(BlockState state) {
/* 86 */     return ((Boolean)state.func_177229_b((Property)WATERLOGGED)).booleanValue() ? Fluids.field_204546_a.func_207204_a(false) : super.func_204507_t(state);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public BlockState func_196258_a(BlockItemUseContext ctx) {
/* 92 */     FluidState fluidstate = ctx.func_195991_k().func_204610_c(ctx.func_195995_a());
/*    */     
/* 94 */     return (BlockState)((BlockState)func_176223_P()
/* 95 */       .func_206870_a((Property)field_185512_D, (Comparable)ctx.func_195992_f()))
/* 96 */       .func_206870_a((Property)WATERLOGGED, Boolean.valueOf((fluidstate.func_206884_a((ITag)FluidTags.field_206959_a) && fluidstate.func_206882_g() == 8)));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\blocks\DialBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */