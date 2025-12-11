/*    */ package xyz.pixelatedw.mineminenomi.screens.extra.buttons;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import java.awt.Color;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.AbstractGui;
/*    */ import net.minecraft.client.gui.widget.button.Button;
/*    */ import net.minecraft.util.Util;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.ITextProperties;
/*    */ import net.minecraft.util.text.StringTextComponent;
/*    */ import net.minecraftforge.fml.client.gui.GuiUtils;
/*    */ 
/*    */ public class TextAnimatedButton
/*    */   extends Button {
/*    */   private boolean isMarked = false;
/*    */   
/*    */   public TextAnimatedButton(int pX, int pY, int pWidth, int pHeight, ITextComponent pMessage, Button.IPressable pOnPress) {
/* 20 */     this(pX, pY, pWidth, pHeight, pMessage, pOnPress, field_238486_s_);
/*    */   }
/*    */ 
/*    */   
/*    */   public TextAnimatedButton(int pX, int pY, int pWidth, int pHeight, ITextComponent pMessage, Button.IPressable pOnPress, Button.ITooltip pOnTooltip) {
/* 25 */     super(pX, pY, pWidth, pHeight, pMessage, pOnPress, pOnTooltip);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_230431_b_(MatrixStack matrixStack, int mouseX, int mouseY, float partial) {
/* 30 */     if (this.field_230694_p_) {
/* 31 */       StringTextComponent stringTextComponent; Minecraft mc = Minecraft.func_71410_x();
/* 32 */       this.field_230692_n_ = (mouseX >= this.field_230690_l_ && mouseY >= this.field_230691_m_ && mouseX < this.field_230690_l_ + this.field_230688_j_ && mouseY < this.field_230691_m_ + this.field_230689_k_);
/* 33 */       int k = func_230989_a_(func_230449_g_());
/* 34 */       GuiUtils.drawContinuousTexturedBox(matrixStack, field_230687_i_, this.field_230690_l_, this.field_230691_m_, 0, 46 + k * 20, this.field_230688_j_, this.field_230689_k_, 200, 20, 2, 3, 2, 2, func_230927_p_());
/* 35 */       func_230441_a_(matrixStack, mc, mouseX, mouseY);
/*    */       
/* 37 */       int scale = 1;
/* 38 */       int color = getFGColor();
/* 39 */       float angle = 0.0F;
/*    */       
/* 41 */       if (func_230449_g_()) {
/* 42 */         func_230443_a_(matrixStack, mouseX, mouseY);
/* 43 */         this.isMarked = false;
/*    */       } 
/*    */       
/* 46 */       if (this.isMarked) {
/* 47 */         scale = 2;
/* 48 */         color = Color.YELLOW.getRGB();
/* 49 */         angle = (float)(Util.func_211177_b() / 40L);
/* 50 */         angle = (float)Math.sin((angle / 10.0F)) / 2.0F;
/*    */       } 
/*    */       
/* 53 */       ITextComponent buttonText = func_230458_i_();
/* 54 */       int strWidth = mc.field_71466_p.func_238414_a_((ITextProperties)buttonText);
/* 55 */       int ellipsisWidth = mc.field_71466_p.func_78256_a("...");
/*    */       
/* 57 */       if (strWidth > this.field_230688_j_ - 6 && strWidth > ellipsisWidth) {
/* 58 */         stringTextComponent = new StringTextComponent(mc.field_71466_p.func_238417_a_((ITextProperties)buttonText, this.field_230688_j_ - 6 - ellipsisWidth).getString() + "...");
/*    */       }
/*    */       
/* 61 */       matrixStack.func_227860_a_();
/* 62 */       matrixStack.func_227861_a_((this.field_230690_l_ + this.field_230688_j_ / 2), (this.field_230691_m_ + this.field_230689_k_ / 2), 1.0D);
/* 63 */       matrixStack.func_227863_a_(Vector3f.field_229183_f_.func_229193_c_(angle));
/* 64 */       matrixStack.func_227862_a_(scale, scale, scale);
/* 65 */       AbstractGui.func_238472_a_(matrixStack, mc.field_71466_p, (ITextComponent)stringTextComponent, 0, -4, color);
/* 66 */       matrixStack.func_227865_b_();
/*    */     } 
/*    */   }
/*    */   
/*    */   public void setMarked() {
/* 71 */     this.isMarked = true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\screens\extra\buttons\TextAnimatedButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */