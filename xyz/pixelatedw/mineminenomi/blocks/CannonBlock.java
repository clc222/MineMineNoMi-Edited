/*     */ package xyz.pixelatedw.mineminenomi.blocks;
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.block.AbstractBlock;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.block.HorizontalBlock;
/*     */ import net.minecraft.block.IWaterLoggable;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.fluid.FluidState;
/*     */ import net.minecraft.fluid.Fluids;
/*     */ import net.minecraft.item.BlockItemUseContext;
/*     */ import net.minecraft.state.BooleanProperty;
/*     */ import net.minecraft.state.DirectionProperty;
/*     */ import net.minecraft.state.Property;
/*     */ import net.minecraft.state.StateContainer;
/*     */ import net.minecraft.state.properties.BlockStateProperties;
/*     */ import net.minecraft.tags.FluidTags;
/*     */ import net.minecraft.tags.ITag;
/*     */ import net.minecraft.util.ActionResultType;
/*     */ import net.minecraft.util.Direction;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.Mirror;
/*     */ import net.minecraft.util.Rotation;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.BlockRayTraceResult;
/*     */ import net.minecraft.world.IBlockReader;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.IWorldReader;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import xyz.pixelatedw.mineminenomi.entities.CannonEntity;
/*     */ 
/*     */ @Deprecated
/*     */ public class CannonBlock extends Block implements IWaterLoggable {
/*  40 */   public static final DirectionProperty FACING = HorizontalBlock.field_185512_D;
/*  41 */   public static final BooleanProperty WATERLOGGED = BlockStateProperties.field_208198_y;
/*  42 */   public static final BooleanProperty TRIGGERED = BlockStateProperties.field_208197_x;
/*     */ 
/*     */   
/*     */   public CannonBlock() {
/*  46 */     super(AbstractBlock.Properties.func_200945_a(Material.field_151573_f).func_226896_b_().func_200943_b(1.5F).func_200944_c());
/*  47 */     func_180632_j((BlockState)((BlockState)((BlockState)this.field_176227_L.func_177621_b()).func_206870_a((Property)FACING, (Comparable)Direction.NORTH)).func_206870_a((Property)WATERLOGGED, Boolean.valueOf(false)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public BlockState func_196258_a(BlockItemUseContext context) {
/*  54 */     BlockState blockstate = func_176223_P();
/*  55 */     BlockPos blockpos = context.func_195995_a();
/*  56 */     Direction[] adirection = context.func_196009_e();
/*  57 */     FluidState fluidstate = context.func_195991_k().func_204610_c(context.func_195995_a());
/*     */     
/*  59 */     for (Direction direction : adirection) {
/*     */       
/*  61 */       if (direction.func_176740_k().func_176722_c()) {
/*     */         
/*  63 */         blockstate = (BlockState)blockstate.func_206870_a((Property)FACING, (Comparable)direction);
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
/*     */   public ActionResultType func_225533_a_(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
/*  78 */     return ActionResultType.PASS;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean removedByPlayer(BlockState state, World world, BlockPos pos, PlayerEntity player, boolean willHarvest, FluidState fluid) {
/*  84 */     if (!world.field_72995_K);
/*     */ 
/*     */ 
/*     */     
/*  88 */     return super.removedByPlayer(state, world, pos, player, willHarvest, fluid);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public FluidState func_204507_t(BlockState state) {
/*  94 */     return ((Boolean)state.func_177229_b((Property)WATERLOGGED)).booleanValue() ? Fluids.field_204546_a.func_207204_a(false) : super.func_204507_t(state);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockState func_196271_a(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos currentPos, BlockPos facingPos) {
/* 100 */     if (((Boolean)state.func_177229_b((Property)WATERLOGGED)).booleanValue())
/*     */     {
/* 102 */       world.func_205219_F_().func_205360_a(currentPos, Fluids.field_204546_a, Fluids.field_204546_a.func_205569_a((IWorldReader)world));
/*     */     }
/* 104 */     return (facing.func_176734_d() == state.func_177229_b((Property)FACING) && !state.func_196955_c((IWorldReader)world, currentPos)) ? Blocks.field_150350_a.func_176223_P() : state;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_220069_a(BlockState pState, World pLevel, BlockPos pPos, Block pBlock, BlockPos pFromPos, boolean pIsMoving) {
/* 110 */     boolean flag = (pLevel.func_175640_z(pPos) || pLevel.func_175640_z(pPos.func_177984_a()));
/* 111 */     boolean flag1 = ((Boolean)pState.func_177229_b((Property)TRIGGERED)).booleanValue();
/* 112 */     if (flag && !flag1) {
/*     */       
/* 114 */       pLevel.func_205220_G_().func_205360_a(pPos, this, 4);
/* 115 */       pLevel.func_180501_a(pPos, (BlockState)pState.func_206870_a((Property)TRIGGERED, Boolean.valueOf(true)), 4);
/*     */     }
/* 117 */     else if (!flag && flag1) {
/*     */       
/* 119 */       pLevel.func_180501_a(pPos, (BlockState)pState.func_206870_a((Property)TRIGGERED, Boolean.valueOf(false)), 4);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_225534_a_(BlockState state, ServerWorld level, BlockPos pos, Random pRand) {
/* 125 */     level.func_180501_a(pos, Blocks.field_150350_a.func_176223_P(), 3);
/*     */     
/* 127 */     CannonEntity cannonEntity = new CannonEntity((World)level);
/* 128 */     cannonEntity.func_70107_b((pos.func_177958_n() + 0.5F), pos.func_177956_o(), (pos.func_177952_p() + 0.5F));
/* 129 */     cannonEntity.field_70177_z = ((Direction)state.func_177229_b((Property)FACING)).func_185119_l();
/* 130 */     level.func_217376_c((Entity)cannonEntity);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockState func_185499_a(BlockState state, Rotation rot) {
/* 136 */     return (BlockState)state.func_206870_a((Property)FACING, (Comparable)rot.func_185831_a((Direction)state.func_177229_b((Property)FACING)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockState func_185471_a(BlockState state, Mirror mirrorIn) {
/* 142 */     return state.func_185907_a(mirrorIn.func_185800_a((Direction)state.func_177229_b((Property)FACING)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_206840_a(StateContainer.Builder<Block, BlockState> builder) {
/* 148 */     builder.func_206894_a(new Property[] { (Property)WATERLOGGED, (Property)FACING, (Property)TRIGGERED });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_200123_i(BlockState state, IBlockReader reader, BlockPos pos) {
/* 154 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public boolean func_200122_a(BlockState state, BlockState adjacentBlockState, Direction side) {
/* 161 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\blocks\CannonBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */