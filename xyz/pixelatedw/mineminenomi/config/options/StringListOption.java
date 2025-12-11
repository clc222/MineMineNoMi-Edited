/*    */ package xyz.pixelatedw.mineminenomi.config.options;
/*    */ 
/*    */ import com.google.common.base.Strings;
/*    */ import java.util.List;
/*    */ import java.util.function.Predicate;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraftforge.common.ForgeConfigSpec;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class StringListOption
/*    */   extends ListOption<String>
/*    */ {
/* 15 */   private static final Predicate<Object> STRING_VALIDATOR = new Predicate()
/*    */     {
/*    */       
/*    */       public boolean test(Object t)
/*    */       {
/* 20 */         if (!(t instanceof String)) {
/* 21 */           return false;
/*    */         }
/* 23 */         String str = (String)t;
/* 24 */         if (Strings.isNullOrEmpty(str)) {
/* 25 */           return false;
/*    */         }
/* 27 */         return true;
/*    */       }
/*    */     };
/*    */ 
/*    */   
/*    */   public StringListOption(List<String> defaultValue, String optionName, @Nullable String optionDescription) {
/* 33 */     super(defaultValue, STRING_VALIDATOR, optionName, optionDescription);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected ForgeConfigSpec.ConfigValue<List<? extends String>> buildValue(ForgeConfigSpec.Builder builder) {
/* 39 */     if (getDescription() != null) {
/* 40 */       builder.comment(getDescription());
/*    */     }
/* 42 */     return builder.defineList(getTitle(), getDefault(), getValidator());
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\config\options\StringListOption.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */