/*    */ package xyz.pixelatedw.mineminenomi.config.options;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.function.Predicate;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ public abstract class ListOption<T>
/*    */   extends ConfigOption<List<? extends T>>
/*    */ {
/*    */   private Predicate<Object> validator;
/*    */   
/*    */   public ListOption(List<T> defaultValue, Predicate<Object> validator, String optionName, @Nullable String optionDescription) {
/* 14 */     super(defaultValue, optionName, optionDescription);
/* 15 */     this.validator = validator;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<T> getDefault() {
/* 21 */     return (List)this.defaultValue;
/*    */   }
/*    */ 
/*    */   
/*    */   public Predicate<Object> getValidator() {
/* 26 */     return this.validator;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\config\options\ListOption.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */