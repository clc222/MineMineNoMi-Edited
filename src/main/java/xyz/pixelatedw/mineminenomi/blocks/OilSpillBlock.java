/*     */ package xyz.pixelatedw.mineminenomi.blocks;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.AbstractBlock;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.block.SoundType;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.Direction;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.shapes.ISelectionContext;
/*     */ import net.minecraft.util.math.shapes.VoxelShape;
/*     */ import net.minecraft.world.Explosion;
/*     */ import net.minecraft.world.IBlockReader;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.IWorldReader;
/*     */ import net.minecraft.world.TickPriority;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ 
/*     */ public class OilSpillBlock
/*     */   extends Block {
/*  29 */   protected static final VoxelShape SHAPE = Block.func_208617_a(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);
/*  30 */   private int ticks = 400;
/*     */ 
/*     */   
/*     */   public OilSpillBlock() {
/*  34 */     super(AbstractBlock.Properties.func_200945_a(Material.field_151588_w).func_200941_a(0.98F).func_200943_b(0.5F).func_200947_a(SoundType.field_185859_l).func_200944_c());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public VoxelShape func_220071_b(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
/*  40 */     return SHAPE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public VoxelShape func_220053_a(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
/*  46 */     return SHAPE;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFullCube(BlockState state) {
/*  51 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_196260_a(BlockState state, IWorldReader world, BlockPos pos) {
/*  57 */     return Block.func_208061_a(state.func_196952_d((IBlockReader)world, pos.func_177977_b()), Direction.UP);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockState func_196271_a(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
/*  63 */     return !stateIn.func_196955_c((IWorldReader)worldIn, currentPos) ? Blocks.field_150350_a.func_176223_P() : stateIn;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public boolean func_200122_a(BlockState state, BlockState adjacentBlockState, Direction side) {
/*  70 */     return (adjacentBlockState.func_177230_c() == this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_220082_b(BlockState state, World world, BlockPos pos, BlockState oldState, boolean isMoving) {
/*  76 */     world.func_205220_G_().func_205360_a(pos, this, 1);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_196262_a(BlockState state, World worldIn, BlockPos pos, Entity entity) {
/*  82 */     float x = (float)Math.min(1.5D, (entity.func_213322_ci()).field_72450_a * 1.149999976158142D);
/*  83 */     float z = (float)Math.min(1.5D, (entity.func_213322_ci()).field_72449_c * 1.149999976158142D);
/*  84 */     AbilityHelper.setDeltaMovement(entity, x, (entity.func_213322_ci()).field_72448_b, z);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225534_a_(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
/*  90 */     if (world.field_72995_K) {
/*     */       return;
/*     */     }
/*  93 */     if (this.ticks > 0) {
/*     */       
/*  95 */       this.ticks--;
/*     */       
/*  97 */       if (areFlamesClose((World)world, pos))
/*     */       {
/*  99 */         world.func_217398_a(null, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), 3.0F, true, CommonConfig.INSTANCE.isAbilityGriefingEnabled() ? Explosion.Mode.DESTROY : Explosion.Mode.NONE);
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 104 */       world.func_175656_a(pos, Blocks.field_150350_a.func_176223_P());
/* 105 */       this.ticks = 400 + rand.nextInt(10);
/*     */     } 
/*     */     
/* 108 */     world.func_205220_G_().func_205362_a(pos, this, 10, TickPriority.EXTREMELY_HIGH);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean areFlamesClose(World world, BlockPos pos) {
/* 113 */     int range = 2;
/*     */     
/* 115 */     for (int i = -range; i < range; i++) {
/*     */       
/* 117 */       for (int j = -range; j < range; j++) {
/*     */         
/* 119 */         for (int k = -range; k < range; k++) {
/*     */           
/* 121 */           double posX = (pos.func_177958_n() + i);
/* 122 */           double posY = (pos.func_177956_o() + j);
/* 123 */           double posZ = (pos.func_177952_p() + k);
/*     */           
/* 125 */           Block currentBlock = world.func_180495_p(new BlockPos(posX, posY, posZ)).func_177230_c();
/* 126 */           if (currentBlock == Blocks.field_150480_ab)
/* 127 */             return true; 
/*     */         } 
/*     */       } 
/*     */     } 
/* 131 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\blocks\OilSpillBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */