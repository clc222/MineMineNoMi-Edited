/*    */ package xyz.pixelatedw.mineminenomi.api.helpers;
/*    */ 
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.state.EnumProperty;
/*    */ import net.minecraft.state.IntegerProperty;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.IBlockReader;
/*    */ import xyz.pixelatedw.mineminenomi.api.enums.CanvasSize;
/*    */ 
/*    */ public class BlocksHelper
/*    */ {
/* 13 */   public static final EnumProperty<CanvasSize> SIZE = EnumProperty.func_177709_a("canvas_size", CanvasSize.class);
/* 14 */   public static final IntegerProperty AGE_4 = IntegerProperty.func_177719_a("age", 0, 4);
/*    */   
/*    */   public static boolean always(BlockState state, IBlockReader reader, BlockPos pos) {
/* 17 */     return true;
/*    */   }
/*    */   
/*    */   public static boolean never(BlockState state, IBlockReader reader, BlockPos pos) {
/* 21 */     return false;
/*    */   }
/*    */   
/*    */   public static boolean never(BlockState state, IBlockReader reader, BlockPos pos, EntityType<?> entityType) {
/* 25 */     return false;
/*    */   }
/*    */   
/*    */   public static boolean always(BlockState state, IBlockReader reader, BlockPos pos, EntityType<?> entityType) {
/* 29 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\helpers\BlocksHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */