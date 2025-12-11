/*     */ package xyz.pixelatedw.mineminenomi.blocks;
/*     */ import net.minecraft.block.AbstractBlock;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.IWaterLoggable;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.fluid.FluidState;
/*     */ import net.minecraft.fluid.Fluids;
/*     */ import net.minecraft.item.BlockItemUseContext;
/*     */ import net.minecraft.state.BooleanProperty;
/*     */ import net.minecraft.state.Property;
/*     */ import net.minecraft.state.StateContainer;
/*     */ import net.minecraft.state.properties.BlockStateProperties;
/*     */ import net.minecraft.tags.FluidTags;
/*     */ import net.minecraft.tags.ITag;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.Direction;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.shapes.ISelectionContext;
/*     */ import net.minecraft.util.math.shapes.VoxelShape;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.world.IBlockReader;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.IWorldReader;
/*     */ import net.minecraft.world.World;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.blocks.tileentities.WantedPosterPackageTileEntity;
/*     */ 
/*     */ public class WantedPosterPackageBlock extends Block implements IWaterLoggable {
/*  31 */   protected static final VoxelShape SHAPE = Block.func_208617_a(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);
/*  32 */   public static final BooleanProperty WATERLOGGED = BlockStateProperties.field_208198_y;
/*     */ 
/*     */   
/*     */   public WantedPosterPackageBlock() {
/*  36 */     super(AbstractBlock.Properties.func_200945_a(Material.field_215713_z).func_200943_b(0.2F).func_200942_a());
/*  37 */     func_180632_j((BlockState)((BlockState)this.field_176227_L.func_177621_b()).func_206870_a((Property)WATERLOGGED, Boolean.valueOf(false)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_206840_a(StateContainer.Builder<Block, BlockState> builder) {
/*  43 */     builder.func_206894_a(new Property[] { (Property)WATERLOGGED });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockState func_196271_a(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
/*  49 */     if (((Boolean)stateIn.func_177229_b((Property)WATERLOGGED)).booleanValue())
/*     */     {
/*  51 */       worldIn.func_205219_F_().func_205360_a(currentPos, Fluids.field_204546_a, Fluids.field_204546_a.func_205569_a((IWorldReader)worldIn));
/*     */     }
/*     */     
/*  54 */     return super.func_196271_a(stateIn, facing, facingState, worldIn, currentPos, facingPos);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public FluidState func_204507_t(BlockState state) {
/*  60 */     return ((Boolean)state.func_177229_b((Property)WATERLOGGED)).booleanValue() ? Fluids.field_204546_a.func_207204_a(false) : super.func_204507_t(state);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockState func_196258_a(BlockItemUseContext ctx) {
/*  66 */     FluidState fluidstate = ctx.func_195991_k().func_204610_c(ctx.func_195995_a());
/*     */     
/*  68 */     return (BlockState)func_176223_P()
/*  69 */       .func_206870_a((Property)WATERLOGGED, Boolean.valueOf((fluidstate.func_206884_a((ITag)FluidTags.field_206959_a) && fluidstate.func_206882_g() == 8)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public VoxelShape func_220053_a(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
/*  75 */     return SHAPE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_200123_i(BlockState state, IBlockReader reader, BlockPos pos) {
/*  81 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public boolean func_200122_a(BlockState state, BlockState adjacentBlockState, Direction side) {
/*  88 */     return true;
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
/* 106 */     return (TileEntity)new WantedPosterPackageTileEntity();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasTileEntity(BlockState state) {
/* 112 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean removedByPlayer(BlockState state, World world, BlockPos pos, PlayerEntity player, boolean willHarvest, FluidState fluid) {
/* 118 */     if (!player.func_184812_l_()) {
/* 119 */       ItemsHelper.dropWantedPosters(world, new Vector3d(pos.func_177958_n() + 0.5D, pos.func_177956_o() - 0.5D, pos.func_177952_p() + 0.5D));
/*     */     }
/*     */     
/* 122 */     return super.removedByPlayer(state, world, pos, player, willHarvest, fluid);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\blocks\WantedPosterPackageBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */