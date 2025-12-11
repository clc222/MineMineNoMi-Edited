/*    */ package xyz.pixelatedw.mineminenomi.api.util;
/*    */ 
/*    */ public class FloatRange implements INumberRange<Float> {
/*    */   private float min;
/*    */   private float max;
/*    */   
/*    */   public FloatRange(float val) {
/*  8 */     this.min = val;
/*  9 */     this.max = val;
/*    */   }
/*    */   
/*    */   public FloatRange(float min, float max) {
/* 13 */     this.min = min;
/* 14 */     this.max = max;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isInfinite() {
/* 19 */     return (this.min == Float.POSITIVE_INFINITY || this.max == Float.POSITIVE_INFINITY);
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
/*    */   public Float getMin() {
/* 34 */     return Float.valueOf(this.min);
/*    */   }
/*    */ 
/*    */   
/*    */   public Float getMax() {
/* 39 */     return Float.valueOf(this.max);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\ap\\util\FloatRange.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */