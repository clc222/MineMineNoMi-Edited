/*    */ package xyz.pixelatedw.mineminenomi.blocks;
/*    */ 
/*    */ import net.minecraft.block.AbstractBlock;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.block.SoundType;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.Direction;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.shapes.ISelectionContext;
/*    */ import net.minecraft.util.math.shapes.VoxelShape;
/*    */ import net.minecraft.util.math.shapes.VoxelShapes;
/*    */ import net.minecraft.world.IBlockReader;
/*    */ import net.minecraftforge.common.ToolType;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModMaterials;
/*    */ 
/*    */ public class HardenedSnowBlock extends Block {
/*    */   public HardenedSnowBlock() {
/* 23 */     super(AbstractBlock.Properties.func_200945_a(ModMaterials.HARDENED_SNOW_BLOCK).func_200942_a().func_200948_a(8.0F, 4.0F).func_200947_a(SoundType.field_185856_i).harvestTool(ToolType.PICKAXE));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public VoxelShape func_220071_b(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
/* 29 */     Entity entity = context.getEntity();
/* 30 */     if (entity instanceof PlayerEntity) {
/*    */       
/* 32 */       PlayerEntity player = (PlayerEntity)entity;
/* 33 */       if (DevilFruitCapability.get((LivingEntity)player).hasDevilFruit(ModAbilities.YUKI_YUKI_NO_MI))
/*    */       {
/* 35 */         return VoxelShapes.func_197880_a();
/*    */       }
/*    */     } 
/*    */     
/* 39 */     return VoxelShapes.func_197868_b();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getFireSpreadSpeed(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
/* 45 */     return 5;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
/* 51 */     return 60;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\blocks\HardenedSnowBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */