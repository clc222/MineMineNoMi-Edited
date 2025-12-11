/*     */ package xyz.pixelatedw.mineminenomi.blocks;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.block.AbstractBlock;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.block.HorizontalBlock;
/*     */ import net.minecraft.block.IWaterLoggable;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.fluid.FluidState;
/*     */ import net.minecraft.fluid.Fluids;
/*     */ import net.minecraft.item.BlockItemUseContext;
/*     */ import net.minecraft.state.BooleanProperty;
/*     */ import net.minecraft.state.DirectionProperty;
/*     */ import net.minecraft.state.IntegerProperty;
/*     */ import net.minecraft.state.Property;
/*     */ import net.minecraft.state.StateContainer;
/*     */ import net.minecraft.state.properties.BlockStateProperties;
/*     */ import net.minecraft.tags.FluidTags;
/*     */ import net.minecraft.tags.ITag;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.Direction;
/*     */ import net.minecraft.util.Mirror;
/*     */ import net.minecraft.util.Rotation;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.shapes.ISelectionContext;
/*     */ import net.minecraft.util.math.shapes.VoxelShape;
/*     */ import net.minecraft.world.IBlockReader;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.IWorldReader;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import xyz.pixelatedw.mineminenomi.blocks.tileentities.DenDenMushiTileEntity;
/*     */ 
/*     */ public class DenDenMushiBlock extends Block implements IWaterLoggable {
/*  35 */   private static final VoxelShape SHAPE = Block.func_208617_a(4.0D, 0.0D, 4.0D, 12.0D, 9.0D, 12.0D);
/*  36 */   public static final DirectionProperty FACING = HorizontalBlock.field_185512_D;
/*  37 */   public static final IntegerProperty TYPE = IntegerProperty.func_177719_a("den_type", 0, 2);
/*  38 */   public static final BooleanProperty WATERLOGGED = BlockStateProperties.field_208198_y;
/*     */ 
/*     */   
/*     */   public DenDenMushiBlock() {
/*  42 */     super(AbstractBlock.Properties.func_200945_a(Material.field_151566_D).func_200943_b(0.4F).func_200942_a());
/*  43 */     func_180632_j((BlockState)((BlockState)((BlockState)((BlockState)this.field_176227_L.func_177621_b()).func_206870_a((Property)FACING, (Comparable)Direction.NORTH)).func_206870_a((Property)TYPE, Integer.valueOf(0))).func_206870_a((Property)WATERLOGGED, Boolean.valueOf(false)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public BlockState func_196258_a(BlockItemUseContext context) {
/*  50 */     BlockState blockstate = func_176223_P();
/*  51 */     BlockPos blockpos = context.func_195995_a();
/*  52 */     Direction[] adirection = context.func_196009_e();
/*  53 */     FluidState fluidstate = context.func_195991_k().func_204610_c(context.func_195995_a());
/*     */     
/*  55 */     int type = context.func_195996_i().func_196082_o().func_74762_e("type");
/*  56 */     blockstate = (BlockState)blockstate.func_206870_a((Property)TYPE, Integer.valueOf(type));
/*     */     
/*  58 */     for (Direction direction : adirection) {
/*     */       
/*  60 */       if (direction.func_176740_k().func_176722_c()) {
/*     */         
/*  62 */         Direction direction1 = direction.func_176734_d();
/*  63 */         blockstate = (BlockState)blockstate.func_206870_a((Property)FACING, (Comparable)direction1);
/*  64 */         blockstate = (BlockState)blockstate.func_206870_a((Property)WATERLOGGED, Boolean.valueOf((fluidstate.func_206884_a((ITag)FluidTags.field_206959_a) && fluidstate.func_206882_g() == 8)));
/*  65 */         if (blockstate.func_196955_c((IWorldReader)context.func_195991_k(), blockpos))
/*     */         {
/*  67 */           return blockstate;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  72 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public VoxelShape func_220071_b(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
/*  78 */     return SHAPE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public VoxelShape func_220053_a(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
/*  84 */     return SHAPE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public FluidState func_204507_t(BlockState state) {
/*  90 */     return ((Boolean)state.func_177229_b((Property)WATERLOGGED)).booleanValue() ? Fluids.field_204546_a.func_207204_a(false) : super.func_204507_t(state);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockState func_196271_a(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
/*  96 */     if (((Boolean)stateIn.func_177229_b((Property)WATERLOGGED)).booleanValue())
/*     */     {
/*  98 */       worldIn.func_205219_F_().func_205360_a(currentPos, Fluids.field_204546_a, Fluids.field_204546_a.func_205569_a((IWorldReader)worldIn));
/*     */     }
/* 100 */     return (facing.func_176734_d() == stateIn.func_177229_b((Property)FACING) && !stateIn.func_196955_c((IWorldReader)worldIn, currentPos)) ? Blocks.field_150350_a.func_176223_P() : stateIn;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockState func_185499_a(BlockState state, Rotation rot) {
/* 106 */     return (BlockState)state.func_206870_a((Property)FACING, (Comparable)rot.func_185831_a((Direction)state.func_177229_b((Property)FACING)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockState func_185471_a(BlockState state, Mirror mirrorIn) {
/* 112 */     return state.func_185907_a(mirrorIn.func_185800_a((Direction)state.func_177229_b((Property)FACING)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_206840_a(StateContainer.Builder<Block, BlockState> builder) {
/* 118 */     builder.func_206894_a(new Property[] { (Property)WATERLOGGED });
/* 119 */     builder.func_206894_a(new Property[] { (Property)FACING });
/* 120 */     builder.func_206894_a(new Property[] { (Property)TYPE });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_200123_i(BlockState state, IBlockReader reader, BlockPos pos) {
/* 126 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public boolean func_200122_a(BlockState state, BlockState adjacentBlockState, Direction side) {
/* 133 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntity createTileEntity(BlockState state, IBlockReader world) {
/* 151 */     return (TileEntity)new DenDenMushiTileEntity();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasTileEntity(BlockState state) {
/* 157 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\blocks\DenDenMushiBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */