/*     */ package xyz.pixelatedw.mineminenomi.blocks;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.AbstractBlock;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.block.DoublePlantBlock;
/*     */ import net.minecraft.block.IGrowable;
/*     */ import net.minecraft.block.SoundType;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.state.IntegerProperty;
/*     */ import net.minecraft.state.Property;
/*     */ import net.minecraft.state.StateContainer;
/*     */ import net.minecraft.util.ActionResultType;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.SoundEvents;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.BlockRayTraceResult;
/*     */ import net.minecraft.world.IBlockReader;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import net.minecraftforge.common.ForgeHooks;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*     */ 
/*     */ public class TangerineCropsBlock extends DoublePlantBlock implements IGrowable {
/*  32 */   public static final IntegerProperty AGE = BlocksHelper.AGE_4;
/*     */   
/*     */   public TangerineCropsBlock() {
/*  35 */     super(AbstractBlock.Properties.func_200945_a(Material.field_151585_k).func_200942_a().func_200944_c().func_200946_b().func_200947_a(SoundType.field_185850_c));
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player) {
/*  40 */     return getBlock().func_185473_a(world, pos, state);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack func_185473_a(IBlockReader level, BlockPos pos, BlockState state) {
/*  45 */     return new ItemStack((IItemProvider)ModItems.TANGERINE.get());
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean func_200014_a_(BlockState state, IBlockReader level, BlockPos pos) {
/*  50 */     return state.func_203425_a(Blocks.field_150458_ak);
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionResultType func_225533_a_(BlockState state, World level, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
/*  55 */     if (hand == Hand.OFF_HAND) {
/*  56 */       return ActionResultType.PASS;
/*     */     }
/*     */     
/*  59 */     int age = ((Integer)state.func_177229_b((Property)AGE)).intValue();
/*  60 */     boolean isFullyGrown = (age == getMaxAge());
/*  61 */     ItemStack handItemStack = player.func_184586_b(hand);
/*     */     
/*  63 */     if (!isFullyGrown && handItemStack.func_77973_b().equals(Items.field_196106_bc)) {
/*  64 */       return ActionResultType.PASS;
/*     */     }
/*  66 */     if (isFullyGrown && handItemStack.func_190926_b()) {
/*  67 */       BlockState bottomState = level.func_180495_p(pos.func_177977_b());
/*  68 */       BlockState aboveState = level.func_180495_p(pos.func_177984_a());
/*     */       
/*  70 */       dropFruit(level, pos, state);
/*  71 */       if (!bottomState.func_196958_f() && bottomState.func_177230_c() == ModBlocks.TANGERINE_CROP.get()) {
/*  72 */         dropFruit(level, pos.func_177977_b(), bottomState);
/*     */       }
/*  74 */       else if (!aboveState.func_196958_f() && aboveState.func_177230_c() == ModBlocks.TANGERINE_CROP.get()) {
/*  75 */         dropFruit(level, pos.func_177984_a(), aboveState);
/*     */       } 
/*  77 */       return ActionResultType.func_233537_a_(level.field_72995_K);
/*     */     } 
/*     */     
/*  80 */     return super.func_225533_a_(state, level, pos, player, hand, hit);
/*     */   }
/*     */ 
/*     */   
/*     */   private void dropFruit(World level, BlockPos pos, BlockState state) {
/*  85 */     int fruits = 1 + level.field_73012_v.nextInt(2);
/*  86 */     func_180635_a(level, pos, new ItemStack((IItemProvider)ModItems.TANGERINE.get(), fruits));
/*  87 */     level.func_184133_a((PlayerEntity)null, pos, SoundEvents.field_219693_lB, SoundCategory.BLOCKS, 1.0F, 1.2F + level.field_73012_v.nextFloat() * 0.4F);
/*  88 */     level.func_180501_a(pos, (BlockState)state.func_206870_a((Property)AGE, Integer.valueOf(2)), 2);
/*     */   }
/*     */   
/*     */   public int getMaxAge() {
/*  92 */     return 4;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_149653_t(BlockState state) {
/*  97 */     return (((Integer)state.func_177229_b((Property)AGE)).intValue() < getMaxAge());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225542_b_(BlockState state, ServerWorld level, BlockPos pos, Random random) {
/* 105 */     if (!level.isAreaLoaded(pos, 1)) {
/*     */       return;
/*     */     }
/* 108 */     int age = ((Integer)state.func_177229_b((Property)AGE)).intValue();
/* 109 */     boolean canGrowThisTick = (random.nextInt(25) == 0);
/* 110 */     if (age < getMaxAge() && level.func_226659_b_(pos.func_177984_a(), 0) >= 9 && ForgeHooks.onCropsGrowPre((World)level, pos, state, canGrowThisTick)) {
/* 111 */       BlockState bottomState = level.func_180495_p(pos.func_177977_b());
/* 112 */       BlockState aboveState = level.func_180495_p(pos.func_177984_a());
/*     */       
/* 114 */       grow((World)level, pos, state);
/* 115 */       if (!bottomState.func_196958_f() && bottomState.func_177230_c() == ModBlocks.TANGERINE_CROP.get()) {
/* 116 */         grow((World)level, pos.func_177977_b(), bottomState);
/*     */       }
/* 118 */       else if (!aboveState.func_196958_f() && aboveState.func_177230_c() == ModBlocks.TANGERINE_CROP.get()) {
/* 119 */         grow((World)level, pos.func_177984_a(), aboveState);
/*     */       } 
/* 121 */       ForgeHooks.onCropsGrowPost((World)level, pos, state);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_206840_a(StateContainer.Builder<Block, BlockState> builder) {
/* 127 */     super.func_206840_a(builder);
/* 128 */     builder.func_206894_a(new Property[] { (Property)AGE });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_176473_a(IBlockReader pLevel, BlockPos pPos, BlockState pState, boolean pIsClient) {
/* 136 */     return (((Integer)pState.func_177229_b((Property)AGE)).intValue() < getMaxAge());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_180670_a(World pLevel, Random pRand, BlockPos pPos, BlockState pState) {
/* 141 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_225535_a_(ServerWorld level, Random rand, BlockPos pos, BlockState state) {
/* 146 */     BlockState bottomState = level.func_180495_p(pos.func_177977_b());
/* 147 */     BlockState aboveState = level.func_180495_p(pos.func_177984_a());
/*     */     
/* 149 */     grow((World)level, pos, state);
/* 150 */     if (!bottomState.func_196958_f() && bottomState.func_177230_c() == ModBlocks.TANGERINE_CROP.get()) {
/* 151 */       grow((World)level, pos.func_177977_b(), bottomState);
/*     */     }
/* 153 */     else if (!aboveState.func_196958_f() && aboveState.func_177230_c() == ModBlocks.TANGERINE_CROP.get()) {
/* 154 */       grow((World)level, pos.func_177984_a(), aboveState);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void grow(World level, BlockPos pos, BlockState state) {
/* 159 */     int age = Math.min(getMaxAge(), ((Integer)state.func_177229_b((Property)AGE)).intValue() + 1);
/* 160 */     level.func_180501_a(pos, (BlockState)state.func_206870_a((Property)AGE, Integer.valueOf(age)), 2);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\blocks\TangerineCropsBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */