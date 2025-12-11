/*    */ package xyz.pixelatedw.mineminenomi.config.options;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraftforge.common.ForgeConfigSpec;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BooleanOption
/*    */   extends ConfigOption<Boolean>
/*    */ {
/*    */   public BooleanOption(Boolean defaultValue, String optionName, @Nullable String optionDescription) {
/* 12 */     super(defaultValue, optionName, optionDescription);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected ForgeConfigSpec.ConfigValue<Boolean> buildValue(ForgeConfigSpec.Builder builder) {
/* 18 */     if (getDescription() != null) {
/* 19 */       builder.comment(getDescription());
/*    */     }
/* 21 */     return builder.define(getTitle(), getDefault());
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\config\options\BooleanOption.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */