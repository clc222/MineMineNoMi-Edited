/*    */ package xyz.pixelatedw.mineminenomi.api;
/*    */ 
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.command.CommandSource;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.text.IFormattableTextComponent;
/*    */ import net.minecraft.util.text.ITargetedTextComponent;
/*    */ import net.minecraft.util.text.Style;
/*    */ import net.minecraft.util.text.TextComponent;
/*    */ import net.minecraft.util.text.TextFormatting;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import net.minecraftforge.fml.RegistryObject;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ public class ReferenceTextComponent
/*    */   extends TextComponent
/*    */   implements ITargetedTextComponent
/*    */ {
/* 21 */   private static final IFormattableTextComponent NO_REFERENCE = (new TranslationTextComponent(WyRegistry.registerName("gui.not_valid_reference", "not valid reference"))).func_230530_a_(Style.field_240709_b_.func_240712_a_(TextFormatting.RED));
/*    */   
/*    */   private RegistryObject<?> reg;
/*    */   
/*    */   public ReferenceTextComponent(RegistryObject<?> reg) {
/* 26 */     this.reg = reg;
/*    */   }
/*    */ 
/*    */   
/*    */   public ReferenceTextComponent plainCopy() {
/* 31 */     return new ReferenceTextComponent(this.reg);
/*    */   }
/*    */   
/*    */   public RegistryObject<?> getObject() {
/* 35 */     return this.reg;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object other) {
/* 40 */     if (this == other) {
/* 41 */       return true;
/*    */     }
/* 43 */     if (!(other instanceof ReferenceTextComponent)) {
/* 44 */       return false;
/*    */     }
/*    */     
/* 47 */     ReferenceTextComponent ref = (ReferenceTextComponent)other;
/* 48 */     return (this.reg.equals(ref.getObject()) && super.equals(other));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 54 */     return "ReferenceComponent{reg='" + this.reg + '\'' + ", siblings=" + this.field_150264_a + ", style=" + func_150256_b() + '}';
/*    */   }
/*    */ 
/*    */   
/*    */   public IFormattableTextComponent func_230535_a_(@Nullable CommandSource commandSourceStack, @Nullable Entity entity, int recursionDepth) throws CommandSyntaxException {
/* 59 */     IFormattableTextComponent format = AbilityHelper.tryParseAndMention(this.reg);
/* 60 */     return (format != null) ? format : NO_REFERENCE;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\ReferenceTextComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */