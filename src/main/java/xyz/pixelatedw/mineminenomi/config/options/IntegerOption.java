/*    */ package xyz.pixelatedw.mineminenomi.config.options;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraftforge.common.ForgeConfigSpec;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class IntegerOption
/*    */   extends MinMaxOption<Integer>
/*    */ {
/*    */   public IntegerOption(Integer defaultValue, Integer min, Integer max, String optionName, @Nullable String optionDescription) {
/* 12 */     super(defaultValue, min, max, optionName, optionDescription);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected ForgeConfigSpec.ConfigValue<Integer> buildValue(ForgeConfigSpec.Builder builder) {
/* 18 */     if (getDescription() != null) {
/* 19 */       builder.comment(getDescription());
/*    */     }
/* 21 */     return (ForgeConfigSpec.ConfigValue<Integer>)builder.defineInRange(getTitle(), getDefault().intValue(), getMin().intValue(), getMax().intValue());
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\config\options\IntegerOption.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */