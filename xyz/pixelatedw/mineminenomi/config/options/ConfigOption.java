/*    */ package xyz.pixelatedw.mineminenomi.config.options;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.StringTextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import net.minecraftforge.common.ForgeConfigSpec;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class ConfigOption<T>
/*    */ {
/*    */   private ForgeConfigSpec.ConfigValue<T> value;
/*    */   protected T defaultValue;
/*    */   private String defaultTitle;
/*    */   @Nullable
/*    */   private String defaultDescription;
/*    */   private ITextComponent title;
/* 22 */   private ITextComponent description = StringTextComponent.field_240750_d_;
/*    */   
/*    */   public ConfigOption(T defaultValue, String optionName, @Nullable String optionDescription) {
/* 25 */     this.defaultValue = defaultValue;
/*    */     
/* 27 */     this.defaultTitle = optionName;
/* 28 */     optionName = WyHelper.getResourceName(optionName);
/*    */     
/* 30 */     this.title = (ITextComponent)new TranslationTextComponent(WyRegistry.registerName("gui.mineminenomi.config.option." + optionName, this.defaultTitle));
/* 31 */     if (optionDescription != null) {
/* 32 */       this.defaultDescription = optionDescription;
/* 33 */       this.description = (ITextComponent)new TranslationTextComponent(WyRegistry.registerName("gui.mineminenomi.config.option." + optionName + ".tooltip", optionDescription));
/*    */     } 
/*    */   }
/*    */   
/*    */   protected abstract ForgeConfigSpec.ConfigValue<T> buildValue(ForgeConfigSpec.Builder paramBuilder);
/*    */   
/*    */   public ForgeConfigSpec.ConfigValue<T> createValue(ForgeConfigSpec.Builder builder) {
/* 40 */     this.value = buildValue(builder);
/* 41 */     return this.value;
/*    */   }
/*    */   
/*    */   public ForgeConfigSpec.ConfigValue<T> getValue() {
/* 45 */     return this.value;
/*    */   }
/*    */   
/*    */   public T get() {
/* 49 */     return (T)this.value.get();
/*    */   }
/*    */   
/*    */   public T getDefault() {
/* 53 */     return this.defaultValue;
/*    */   }
/*    */   
/*    */   public String getTitle() {
/* 57 */     return this.defaultTitle;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public String getDescription() {
/* 62 */     return this.defaultDescription;
/*    */   }
/*    */   
/*    */   public ITextComponent getTitleComponent() {
/* 66 */     return this.title;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public ITextComponent getDescriptionComponent() {
/* 71 */     return this.description;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\config\options\ConfigOption.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */