/*    */ package xyz.pixelatedw.mineminenomi.blocks;
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.AbstractBlock;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.Direction;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.shapes.ISelectionContext;
/*    */ import net.minecraft.util.math.shapes.VoxelShape;
/*    */ import net.minecraft.world.IBlockReader;
/*    */ import net.minecraft.world.IWorld;
/*    */ import net.minecraft.world.IWorldReader;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModMaterials;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class PoisonBlock extends Block {
/* 27 */   protected static final VoxelShape SHAPE = Block.func_208617_a(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);
/*    */   private static final int ALIVE_TICKS = 800;
/*    */   private static final int RANDOM_TICKS = 400;
/*    */   
/*    */   public PoisonBlock() {
/* 32 */     super(AbstractBlock.Properties.func_200945_a(ModMaterials.MUCUS).func_200943_b(0.5F).func_222380_e());
/*    */   }
/*    */ 
/*    */   
/*    */   public VoxelShape func_220071_b(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
/* 37 */     return SHAPE;
/*    */   }
/*    */ 
/*    */   
/*    */   public VoxelShape func_220053_a(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
/* 42 */     return SHAPE;
/*    */   }
/*    */   
/*    */   public boolean isFullCube(BlockState state) {
/* 46 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_196260_a(BlockState state, IWorldReader world, BlockPos pos) {
/* 51 */     BlockState below = world.func_180495_p(pos.func_177977_b());
/* 52 */     return (below.func_185904_a().func_76220_a() && !(below.func_177230_c() instanceof PoisonBlock));
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockState func_196271_a(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
/* 57 */     return !stateIn.func_196955_c((IWorldReader)worldIn, currentPos) ? Blocks.field_150350_a.func_176223_P() : stateIn;
/*    */   }
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public boolean func_200122_a(BlockState state, BlockState adjacentBlockState, Direction side) {
/* 63 */     return (adjacentBlockState.func_177230_c() == this);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_196262_a(BlockState state, World world, BlockPos pos, Entity entity) {
/* 68 */     if (entity instanceof LivingEntity && 
/* 69 */       !((LivingEntity)entity).func_70644_a((Effect)ModEffects.DOKU_POISON.get())) {
/* 70 */       ((LivingEntity)entity).func_195064_c(new EffectInstance((Effect)ModEffects.DOKU_POISON.get(), 100, 0));
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_220082_b(BlockState state, World world, BlockPos pos, BlockState oldState, boolean isMoving) {
/* 77 */     if (!func_196260_a(state, (IWorldReader)world, pos)) {
/* 78 */       world.func_217379_c(2001, pos, Block.func_196246_j(state));
/* 79 */       WyHelper.setBlockStateInChunk(world, pos, Blocks.field_150350_a.func_176223_P(), 2);
/*    */       return;
/*    */     } 
/* 82 */     world.func_205220_G_().func_205360_a(pos, this, 800 + world.func_201674_k().nextInt(400));
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_225534_a_(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
/* 87 */     world.func_217379_c(2001, pos, Block.func_196246_j(state));
/* 88 */     WyHelper.setBlockStateInChunk((World)world, pos, Blocks.field_150350_a.func_176223_P(), 2);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\blocks\PoisonBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */