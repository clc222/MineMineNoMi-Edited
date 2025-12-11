/*    */ package xyz.pixelatedw.mineminenomi.api;
/*    */ 
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ 
/*    */ public class TradeEntry
/*    */ {
/*    */   private ItemStack itemStack;
/*    */   
/*    */   public TradeEntry(ItemStack itemStack) {
/* 11 */     this.itemStack = itemStack;
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack getItemStack() {
/* 16 */     return this.itemStack;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getCount() {
/* 21 */     return this.itemStack.func_190916_E();
/*    */   }
/*    */ 
/*    */   
/*    */   public TradeEntry setCount(int count) {
/* 26 */     this.itemStack.func_190920_e(count);
/* 27 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean hasInfiniteStock() {
/* 32 */     return this.itemStack.func_196082_o().func_74767_n("isInfinite");
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPrice() {
/* 37 */     return this.itemStack.func_196082_o().func_74762_e("price");
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\TradeEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */