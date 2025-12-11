/*    */ package xyz.pixelatedw.mineminenomi.blocks;
/*    */ 
/*    */ import net.minecraft.block.AbstractBlock;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockRenderType;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.block.SoundType;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.item.BlockItemUseContext;
/*    */ import net.minecraft.state.DirectionProperty;
/*    */ import net.minecraft.state.Property;
/*    */ import net.minecraft.state.StateContainer;
/*    */ import net.minecraft.state.properties.BlockStateProperties;
/*    */ import net.minecraft.util.Direction;
/*    */ import net.minecraft.util.Mirror;
/*    */ import net.minecraft.util.Rotation;
/*    */ 
/*    */ public class DesignBarrelBlock extends Block {
/* 19 */   public static final DirectionProperty FACING = BlockStateProperties.field_208155_H;
/*    */ 
/*    */   
/*    */   public DesignBarrelBlock() {
/* 23 */     super(AbstractBlock.Properties.func_200945_a(Material.field_151575_d).func_200943_b(2.5F).func_200947_a(SoundType.field_185848_a));
/* 24 */     func_180632_j((BlockState)((BlockState)this.field_176227_L.func_177621_b()).func_206870_a((Property)FACING, (Comparable)Direction.NORTH));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public BlockRenderType func_149645_b(BlockState pState) {
/* 30 */     return BlockRenderType.MODEL;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public BlockState func_185499_a(BlockState pState, Rotation pRotation) {
/* 36 */     return (BlockState)pState.func_206870_a((Property)FACING, (Comparable)pRotation.func_185831_a((Direction)pState.func_177229_b((Property)FACING)));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public BlockState func_185471_a(BlockState pState, Mirror pMirror) {
/* 42 */     return pState.func_185907_a(pMirror.func_185800_a((Direction)pState.func_177229_b((Property)FACING)));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void func_206840_a(StateContainer.Builder<Block, BlockState> pBuilder) {
/* 48 */     pBuilder.func_206894_a(new Property[] { (Property)FACING });
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public BlockState func_196258_a(BlockItemUseContext pContext) {
/* 54 */     return (BlockState)func_176223_P().func_206870_a((Property)FACING, (Comparable)pContext.func_196010_d().func_176734_d());
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\blocks\DesignBarrelBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */