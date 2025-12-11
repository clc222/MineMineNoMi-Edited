/*    */ package xyz.pixelatedw.mineminenomi.blocks;
/*    */ import net.minecraft.block.AbstractBlock;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.block.CakeBlock;
/*    */ import net.minecraft.block.SoundType;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.state.Property;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.shapes.ISelectionContext;
/*    */ import net.minecraft.util.math.shapes.VoxelShape;
/*    */ import net.minecraft.world.IBlockReader;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.BlocksHelper;
/*    */ 
/*    */ public class SpongeCakeBlock extends CakeBlock {
/* 18 */   protected static final VoxelShape[] SHAPES = new VoxelShape[] { Block.func_208617_a(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D), Block.func_208617_a(3.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D), 
/* 19 */       Block.func_208617_a(5.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D), Block.func_208617_a(7.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D), Block.func_208617_a(9.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D), 
/* 20 */       Block.func_208617_a(11.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D), Block.func_208617_a(13.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D) };
/*    */ 
/*    */   
/*    */   public SpongeCakeBlock() {
/* 24 */     super(AbstractBlock.Properties.func_200945_a(Material.field_151568_F).func_235838_a_(state -> 0).func_200943_b(0.7F).func_200947_a(SoundType.field_185854_g).func_235827_a_(BlocksHelper::never));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public VoxelShape func_220053_a(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
/* 30 */     return SHAPES[((Integer)state.func_177229_b((Property)field_176589_a)).intValue()];
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_200123_i(BlockState state, IBlockReader reader, BlockPos pos) {
/* 36 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_180658_a(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
/* 42 */     entityIn.func_225503_b_(fallDistance, 0.01F);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\blocks\SpongeCakeBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */