/*    */ package xyz.pixelatedw.mineminenomi.config.options;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.function.Predicate;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraftforge.common.ForgeConfigSpec;
/*    */ 
/*    */ public class IntegerListOption
/*    */   extends ListOption<Integer> {
/* 10 */   private static final Predicate<Object> VALIDATOR = new Predicate()
/*    */     {
/*    */       public boolean test(Object t) {
/* 13 */         if (!(t instanceof Integer)) {
/* 14 */           return false;
/*    */         }
/*    */         
/* 17 */         Integer i = (Integer)t;
/* 18 */         if (i.intValue() < 0) {
/* 19 */           return false;
/*    */         }
/*    */         
/* 22 */         return true;
/*    */       }
/*    */     };
/*    */   
/*    */   public IntegerListOption(List<Integer> defaultValue, String optionName, @Nullable String optionDescription) {
/* 27 */     super(defaultValue, VALIDATOR, optionName, optionDescription);
/*    */   }
/*    */ 
/*    */   
/*    */   protected ForgeConfigSpec.ConfigValue<List<? extends Integer>> buildValue(ForgeConfigSpec.Builder builder) {
/* 32 */     if (getDescription() != null) {
/* 33 */       builder.comment(getDescription());
/*    */     }
/*    */     
/* 36 */     return builder.defineList(getTitle(), getDefault(), getValidator());
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\config\options\IntegerListOption.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */