/*    */ package xyz.pixelatedw.mineminenomi.config.options;
/*    */ 
/*    */ import net.minecraftforge.common.ForgeConfigSpec;
/*    */ 
/*    */ 
/*    */ public class EnumOption<T extends Enum<T>>
/*    */   extends ConfigOption<T>
/*    */ {
/*    */   private Class<T> enumClass;
/*    */   private T[] enumValues;
/*    */   
/*    */   public EnumOption(T defaultValue, T[] values, String optionName, String optionDescription) {
/* 13 */     super(defaultValue, optionName, optionDescription);
/* 14 */     this.enumClass = (Class)defaultValue.getClass();
/* 15 */     this.enumValues = values;
/*    */   }
/*    */ 
/*    */   
/*    */   public Class<T> getEnumClass() {
/* 20 */     return this.enumClass;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected ForgeConfigSpec.ConfigValue<T> buildValue(ForgeConfigSpec.Builder builder) {
/* 26 */     if (getDescription() != null) {
/* 27 */       builder.comment(getDescription());
/*    */     }
/* 29 */     return (ForgeConfigSpec.ConfigValue<T>)builder.defineEnum(getTitle(), (Enum)getDefault(), (Enum[])this.enumValues);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\config\options\EnumOption.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */