/*    */ package xyz.pixelatedw.mineminenomi.config.options;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ public abstract class MinMaxOption<T>
/*    */   extends ConfigOption<T>
/*    */ {
/*    */   private T min;
/*    */   private T max;
/*    */   
/*    */   public MinMaxOption(T defaultValue, T min, T max, String optionName, @Nullable String optionDescription) {
/* 12 */     super(defaultValue, optionName, optionDescription);
/* 13 */     this.min = min;
/* 14 */     this.max = max;
/*    */   }
/*    */ 
/*    */   
/*    */   public T getMin() {
/* 19 */     return this.min;
/*    */   }
/*    */ 
/*    */   
/*    */   public T getMax() {
/* 24 */     return this.max;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\config\options\MinMaxOption.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */