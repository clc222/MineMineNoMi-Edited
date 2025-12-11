/*     */ package xyz.pixelatedw.mineminenomi.blocks;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.Map;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.block.AbstractBlock;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.block.HorizontalBlock;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.BlockItemUseContext;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.nbt.INBT;
/*     */ import net.minecraft.state.DirectionProperty;
/*     */ import net.minecraft.state.EnumProperty;
/*     */ import net.minecraft.state.Property;
/*     */ import net.minecraft.state.StateContainer;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.ActionResultType;
/*     */ import net.minecraft.util.Direction;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.Mirror;
/*     */ import net.minecraft.util.Rotation;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.BlockRayTraceResult;
/*     */ import net.minecraft.util.math.shapes.ISelectionContext;
/*     */ import net.minecraft.util.math.shapes.VoxelShape;
/*     */ import net.minecraft.world.IBlockReader;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.IWorldReader;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.CanvasSize;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.BlocksHelper;
/*     */ import xyz.pixelatedw.mineminenomi.blocks.tileentities.WantedPosterTileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*     */ import xyz.pixelatedw.mineminenomi.items.WantedPosterItem;
/*     */ 
/*     */ public class WantedPosterBlock extends Block {
/*  42 */   public static final DirectionProperty FACING = HorizontalBlock.field_185512_D;
/*  43 */   public static final EnumProperty<CanvasSize> SIZE = BlocksHelper.SIZE;
/*  44 */   private static final Map<Direction, VoxelShape> SHAPES = Maps.newEnumMap(
/*  45 */       (Map)ImmutableMap.of(Direction.NORTH, 
/*  46 */         Block.func_208617_a(0.0D, -2.5D, 14.0D, 16.0D, 18.5D, 16.0D), Direction.SOUTH, 
/*  47 */         Block.func_208617_a(0.0D, -2.5D, 0.0D, 16.0D, 18.5D, 2.0D), Direction.EAST, 
/*  48 */         Block.func_208617_a(0.0D, -2.5D, 0.0D, 2.0D, 18.5D, 16.0D), Direction.WEST, 
/*  49 */         Block.func_208617_a(14.0D, -2.5D, 0.0D, 16.0D, 18.5D, 16.0D)));
/*     */ 
/*     */   
/*     */   public WantedPosterBlock() {
/*  53 */     super(AbstractBlock.Properties.func_200945_a(Material.field_151580_n).func_200943_b(0.2F).func_200942_a().func_235842_b_(BlocksHelper::never));
/*  54 */     func_180632_j((BlockState)((BlockState)((BlockState)this.field_176227_L.func_177621_b()).func_206870_a((Property)FACING, (Comparable)Direction.NORTH)).func_206870_a((Property)SIZE, (Comparable)CanvasSize.SMALL));
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape func_220053_a(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
/*  59 */     return SHAPES.get(state.func_177229_b((Property)FACING));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_196260_a(BlockState state, IWorldReader worldIn, BlockPos pos) {
/*  64 */     return worldIn.func_180495_p(pos.func_177971_a(((Direction)state.func_177229_b((Property)FACING)).func_176734_d().func_176730_m())).func_185904_a().func_76218_k();
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public BlockState func_196258_a(BlockItemUseContext context) {
/*  70 */     BlockState blockstate = func_176223_P();
/*  71 */     BlockPos blockpos = context.func_195995_a();
/*  72 */     Direction[] adirection = context.func_196009_e();
/*  73 */     CanvasSize size = CanvasSize.values()[context.func_195996_i().func_196082_o().func_74762_e("canvasSize")];
/*  74 */     blockstate = (BlockState)blockstate.func_206870_a((Property)SIZE, (Comparable)size);
/*     */     
/*  76 */     for (Direction direction : adirection) {
/*  77 */       if (direction.func_176740_k().func_176722_c()) {
/*  78 */         Direction direction1 = direction.func_176734_d();
/*  79 */         blockstate = (BlockState)blockstate.func_206870_a((Property)FACING, (Comparable)direction1);
/*  80 */         if (blockstate.func_196955_c((IWorldReader)context.func_195991_k(), blockpos)) {
/*  81 */           return blockstate;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  86 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockState func_196271_a(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
/*  91 */     return (facing.func_176734_d() == stateIn.func_177229_b((Property)FACING) && !stateIn.func_196955_c((IWorldReader)worldIn, currentPos)) ? Blocks.field_150350_a.func_176223_P() : stateIn;
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockState func_185499_a(BlockState state, Rotation rot) {
/*  96 */     return (BlockState)state.func_206870_a((Property)FACING, (Comparable)rot.func_185831_a((Direction)state.func_177229_b((Property)FACING)));
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockState func_185471_a(BlockState state, Mirror mirrorIn) {
/* 101 */     return state.func_185907_a(mirrorIn.func_185800_a((Direction)state.func_177229_b((Property)FACING)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_206840_a(StateContainer.Builder<Block, BlockState> builder) {
/* 106 */     builder.func_206894_a(new Property[] { (Property)FACING });
/* 107 */     builder.func_206894_a(new Property[] { (Property)SIZE });
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_200123_i(BlockState state, IBlockReader reader, BlockPos pos) {
/* 112 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public boolean func_200122_a(BlockState state, BlockState adjacentBlockState, Direction side) {
/* 118 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180657_a(World level, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity tile, ItemStack stack) {
/* 123 */     ItemStack poster = ((Block)ModBlocks.WANTED_POSTER.get()).func_199767_j().func_190903_i();
/* 124 */     CompoundNBT nbt = new CompoundNBT();
/* 125 */     if (tile != null) {
/* 126 */       WantedPosterTileEntity posterTile = (WantedPosterTileEntity)tile;
/*     */       
/* 128 */       nbt.func_218657_a("WPData", (INBT)posterTile.getWantedPosterData().write());
/*     */     } 
/*     */     
/* 131 */     poster.func_77982_d(nbt);
/* 132 */     WantedPosterItem.setCanvasSize(poster, (CanvasSize)state.func_177229_b((Property)SIZE));
/* 133 */     Block.func_180635_a(level, pos, poster);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionResultType func_225533_a_(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
/* 142 */     return ActionResultType.FAIL;
/*     */   }
/*     */ 
/*     */   
/*     */   public TileEntity createTileEntity(BlockState state, IBlockReader world) {
/* 147 */     return (TileEntity)new WantedPosterTileEntity();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasTileEntity(BlockState state) {
/* 152 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\blocks\WantedPosterBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */