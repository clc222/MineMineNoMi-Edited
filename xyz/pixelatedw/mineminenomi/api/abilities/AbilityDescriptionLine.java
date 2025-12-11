/*    */ package xyz.pixelatedw.mineminenomi.api.abilities;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.StringTextComponent;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AbilityDescriptionLine
/*    */ {
/*    */   public static final IDescriptionLine NEW_LINE = (e, a) -> new StringTextComponent(" ");
/*    */   public static final IDescriptionLine DOUBLE_NEW_LINE = (e, a) -> new StringTextComponent(System.getProperty("line.separator"));
/*    */   @Nullable
/*    */   private ITextComponent staticLine;
/*    */   @Nullable
/*    */   private IDescriptionLine dynamicLine;
/*    */   private boolean isAdvanced;
/*    */   
/*    */   private AbilityDescriptionLine(IDescriptionLine line, boolean isAdvanced) {
/* 21 */     this.dynamicLine = line;
/* 22 */     this.isAdvanced = isAdvanced;
/*    */   }
/*    */   
/*    */   private AbilityDescriptionLine(ITextComponent line, boolean isAdvanced) {
/* 26 */     this.staticLine = line;
/* 27 */     this.isAdvanced = isAdvanced;
/*    */   }
/*    */   
/*    */   public static AbilityDescriptionLine of(IDescriptionLine line) {
/* 31 */     return new AbilityDescriptionLine(line, false);
/*    */   }
/*    */   
/*    */   public static AbilityDescriptionLine of(IDescriptionLine line, boolean isAdvanced) {
/* 35 */     return new AbilityDescriptionLine(line, isAdvanced);
/*    */   }
/*    */   
/*    */   public static AbilityDescriptionLine of(ITextComponent text) {
/* 39 */     return new AbilityDescriptionLine(text, false);
/*    */   }
/*    */   
/*    */   public static AbilityDescriptionLine of(ITextComponent text, boolean isAdvanced) {
/* 43 */     return new AbilityDescriptionLine(text, isAdvanced);
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public ITextComponent getTextComponent(LivingEntity entity, IAbility ability) {
/* 48 */     if (this.staticLine != null) {
/* 49 */       return this.staticLine;
/*    */     }
/* 51 */     return this.dynamicLine.expand(entity, ability);
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public ITextComponent getStaticTextComponent() {
/* 56 */     return this.staticLine;
/*    */   }
/*    */   
/*    */   public boolean isStatic() {
/* 60 */     return (this.staticLine != null);
/*    */   }
/*    */   
/*    */   public boolean isDynamic() {
/* 64 */     return (this.dynamicLine != null);
/*    */   }
/*    */   
/*    */   public boolean isAdvanced() {
/* 68 */     return this.isAdvanced;
/*    */   }
/*    */   
/*    */   @FunctionalInterface
/*    */   public static interface IDescriptionLine<A extends IAbility> {
/*    */     ITextComponent expand(LivingEntity param1LivingEntity, A param1A);
/*    */     
/*    */     static IDescriptionLine of(ITextComponent text) {
/* 76 */       return (e, a) -> text;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\AbilityDescriptionLine.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */