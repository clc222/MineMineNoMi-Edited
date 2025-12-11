/*    */ package xyz.pixelatedw.mineminenomi.api;
/*    */ 
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ 
/*    */ 
/*    */ public interface IFruitColor
/*    */ {
/*    */   default boolean hasBaseColor(ItemStack stack) {
/* 10 */     CompoundNBT compoundnbt = stack.func_179543_a("display");
/* 11 */     return (compoundnbt != null && compoundnbt.func_150297_b("color", 99));
/*    */   }
/*    */ 
/*    */   
/*    */   default int getBaseColor(ItemStack stack) {
/* 16 */     CompoundNBT compoundnbt = stack.func_179543_a("display");
/* 17 */     return (compoundnbt != null && compoundnbt.func_150297_b("color", 99)) ? compoundnbt.func_74762_e("color") : -1;
/*    */   }
/*    */ 
/*    */   
/*    */   default void removeBaseColor(ItemStack stack) {
/* 22 */     CompoundNBT compoundnbt = stack.func_179543_a("display");
/* 23 */     if (compoundnbt != null && compoundnbt.func_74764_b("color"))
/*    */     {
/* 25 */       compoundnbt.func_82580_o("color");
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   default void setBaseColor(ItemStack stack, int color) {
/* 31 */     stack.func_190925_c("display").func_74768_a("color", color);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   default boolean hasStemColor(ItemStack stack) {
/* 37 */     CompoundNBT compoundnbt = stack.func_179543_a("display");
/* 38 */     return (compoundnbt != null && compoundnbt.func_150297_b("stem_color", 99));
/*    */   }
/*    */ 
/*    */   
/*    */   default int getStemColor(ItemStack stack) {
/* 43 */     CompoundNBT compoundnbt = stack.func_179543_a("display");
/* 44 */     return (compoundnbt != null && compoundnbt.func_150297_b("stem_color", 99)) ? compoundnbt.func_74762_e("stem_color") : 10511680;
/*    */   }
/*    */ 
/*    */   
/*    */   default void removeStemColor(ItemStack stack) {
/* 49 */     CompoundNBT compoundnbt = stack.func_179543_a("display");
/* 50 */     if (compoundnbt != null && compoundnbt.func_74764_b("stem_color"))
/*    */     {
/* 52 */       compoundnbt.func_82580_o("stem_color");
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   default void setStemColor(ItemStack stack, int color) {
/* 58 */     stack.func_190925_c("display").func_74768_a("stem_color", color);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\IFruitColor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */