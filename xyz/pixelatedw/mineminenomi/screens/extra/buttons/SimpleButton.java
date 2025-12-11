/*    */ package xyz.pixelatedw.mineminenomi.screens.extra.buttons;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.FontRenderer;
/*    */ import net.minecraft.client.gui.widget.button.Button;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.ITextProperties;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class SimpleButton extends Button {
/*    */   private boolean isPressed;
/*    */   private boolean textOnly;
/* 14 */   private float fontSize = 1.0F;
/*    */   
/* 16 */   private int defaultTextColor = WyHelper.hexToRGB("#FFFFFF").getRGB();
/* 17 */   private int outlineColor = WyHelper.hexToRGB("#000000").getRGB();
/*    */   
/* 19 */   private int defaultTopGradientColor = WyHelper.hexToRGB("#B3B3B3").getRGB();
/* 20 */   private int defaultBottomGradientColor = WyHelper.hexToRGB("#939393").getRGB();
/*    */   
/* 22 */   private int hoverTextColor = WyHelper.hexToRGB("#00FF55").getRGB();
/* 23 */   private int hoverTopGradientColor = WyHelper.hexToRGB("#B3B3B3").getRGB();
/* 24 */   private int hoverBottomGradientColor = WyHelper.hexToRGB("#505050").getRGB();
/*    */   
/* 26 */   private int pressedTextColor = this.defaultTextColor;
/* 27 */   private int pressedTopGradientColor = WyHelper.hexToRGB("#00CC00").getRGB();
/* 28 */   private int pressedBottomGradientColor = WyHelper.hexToRGB("#005500").getRGB();
/*    */   
/*    */   public SimpleButton(int posX, int posY, int width, int height, ITextComponent string, Button.IPressable onPress) {
/* 31 */     super(posX, posY, width, height, string, onPress);
/*    */   }
/*    */   
/*    */   public void setFontSize(float size) {
/* 35 */     this.fontSize = size;
/*    */   }
/*    */   
/*    */   public void setTextOnly() {
/* 39 */     this.textOnly = true;
/*    */   }
/*    */   
/*    */   public void setHoverTextColor(String color) {
/* 43 */     this.hoverTextColor = WyHelper.hexToRGB(color).getRGB();
/*    */   }
/*    */   
/*    */   public void setPressedTextColor(String color) {
/* 47 */     this.pressedTextColor = WyHelper.hexToRGB(color).getRGB();
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_230430_a_(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
/* 52 */     matrixStack.func_227860_a_();
/* 53 */     if (this.field_230694_p_) {
/* 54 */       this.field_230692_n_ = (mouseX >= this.field_230690_l_ && mouseY >= this.field_230691_m_ && mouseX < this.field_230690_l_ + this.field_230688_j_ && mouseY < this.field_230691_m_ + this.field_230689_k_);
/* 55 */       int rgb = this.defaultTextColor;
/* 56 */       int topGrad = this.defaultTopGradientColor;
/* 57 */       int bottomGrad = this.defaultBottomGradientColor;
/*    */       
/* 59 */       if (this.isPressed) {
/* 60 */         rgb = this.pressedTextColor;
/* 61 */         topGrad = this.pressedTopGradientColor;
/* 62 */         bottomGrad = this.pressedBottomGradientColor;
/*    */       } 
/*    */       
/* 65 */       if (this.field_230692_n_) {
/* 66 */         matrixStack.func_227861_a_(0.0D, 0.5D, 0.0D);
/* 67 */         rgb = this.hoverTextColor;
/* 68 */         topGrad = this.hoverTopGradientColor;
/* 69 */         bottomGrad = this.hoverBottomGradientColor;
/*    */       } 
/*    */       
/* 72 */       int outlineSize = 1;
/*    */       
/* 74 */       if (!this.textOnly) {
/* 75 */         func_238468_a_(matrixStack, this.field_230690_l_ - outlineSize, this.field_230691_m_ - outlineSize, this.field_230688_j_ + this.field_230690_l_ + outlineSize, this.field_230689_k_ + this.field_230691_m_ + outlineSize, this.outlineColor, this.outlineColor);
/* 76 */         func_238468_a_(matrixStack, this.field_230690_l_, this.field_230691_m_, this.field_230688_j_ + this.field_230690_l_, this.field_230689_k_ + this.field_230691_m_, topGrad, bottomGrad);
/*    */       } 
/*    */       
/* 79 */       FontRenderer font = (Minecraft.func_71410_x()).field_71466_p;
/* 80 */       matrixStack.func_227860_a_();
/* 81 */       int x = (int)(this.field_230690_l_ - font.func_238414_a_((ITextProperties)func_230458_i_()) * this.fontSize / 2.0F + (this.field_230688_j_ / 2));
/* 82 */       int y = this.field_230691_m_ + this.field_230689_k_ / 2 - 4;
/* 83 */       matrixStack.func_227861_a_(x, y, 2.0D);
/* 84 */       matrixStack.func_227862_a_(this.fontSize, this.fontSize, this.fontSize);
/* 85 */       WyHelper.drawStringWithBorder(font, matrixStack, func_230458_i_().getString(), 0, 0, rgb);
/* 86 */       matrixStack.func_227865_b_();
/*    */     } 
/* 88 */     matrixStack.func_227865_b_();
/*    */   }
/*    */   
/*    */   public void select() {
/* 92 */     this.isPressed = !this.isPressed;
/*    */   }
/*    */   
/*    */   public void setIsPressed(boolean flag) {
/* 96 */     this.isPressed = flag;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\screens\extra\buttons\SimpleButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */