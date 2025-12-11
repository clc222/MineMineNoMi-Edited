/*    */ package xyz.pixelatedw.mineminenomi.blocks;
/*    */ 
/*    */ import net.minecraft.block.AbstractBlock;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.block.PaneBlock;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.shapes.ISelectionContext;
/*    */ import net.minecraft.util.math.shapes.VoxelShape;
/*    */ import net.minecraft.util.math.shapes.VoxelShapes;
/*    */ import net.minecraft.world.IBlockReader;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*    */ 
/*    */ public class OriBarsBlock extends PaneBlock {
/*    */   public OriBarsBlock() {
/* 20 */     super(AbstractBlock.Properties.func_200945_a(Material.field_151573_f).func_200943_b(50.0F));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public VoxelShape func_220071_b(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
/* 26 */     Entity entity = context.getEntity();
/* 27 */     if (entity instanceof PlayerEntity) {
/*    */       
/* 29 */       PlayerEntity player = (PlayerEntity)entity;
/* 30 */       if (DevilFruitCapability.get((LivingEntity)player).hasDevilFruit(ModAbilities.ORI_ORI_NO_MI))
/*    */       {
/* 32 */         return VoxelShapes.func_197880_a();
/*    */       }
/*    */     } 
/*    */     
/* 36 */     if (func_196406_i(state) == 15) {
/* 37 */       return VoxelShapes.func_197868_b();
/*    */     }
/* 39 */     return this.field_196410_A[func_196406_i(state)];
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\blocks\OriBarsBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */