/*    */ package xyz.pixelatedw.mineminenomi.api.util;
/*    */ 
/*    */ public class IntRange implements INumberRange<Integer> {
/*    */   private int min;
/*    */   private int max;
/*    */   
/*    */   public IntRange(int val) {
/*  8 */     this.min = val;
/*  9 */     this.max = val;
/*    */   }
/*    */   
/*    */   public IntRange(int min, int max) {
/* 13 */     this.min = min;
/* 14 */     this.max = max;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isInfinite() {
/* 19 */     return (this.min == Integer.MAX_VALUE || this.max == Integer.MAX_VALUE);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isRange() {
/* 24 */     return !isFixed();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isFixed() {
/* 29 */     return (this.min == this.max);
/*    */   }
/*    */ 
/*    */   
/*    */   public Integer getMin() {
/* 34 */     return Integer.valueOf(this.min);
/*    */   }
/*    */ 
/*    */   
/*    */   public Integer getMax() {
/* 39 */     return Integer.valueOf(this.max);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\ap\\util\IntRange.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */