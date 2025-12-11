/*    */ package xyz.pixelatedw.mineminenomi.screens.extra.buttons;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.systems.RenderSystem;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.FontRenderer;
/*    */ import net.minecraft.client.gui.widget.button.Button;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.ITextProperties;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class PlankButton
/*    */   extends Button {
/*    */   public PlankButton(int posX, int posY, int width, int height, ITextComponent string, Button.IPressable onPress) {
/* 17 */     super(posX, posY, width, height, string, onPress);
/*    */   }
/*    */   
/*    */   public PlankButton(int posX, int posY, int width, int height, ITextComponent string, Button.IPressable onPress, Button.ITooltip onTooltip) {
/* 21 */     super(posX, posY, width, height, string, onPress, onTooltip);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_230431_b_(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
/* 27 */     RenderSystem.enableDepthTest();
/* 28 */     this.field_230692_n_ = (mouseX >= this.field_230690_l_ && mouseY >= this.field_230691_m_ && mouseX < this.field_230690_l_ + this.field_230688_j_ && mouseY < this.field_230691_m_ + this.field_230689_k_);
/* 29 */     if (this.field_230692_n_) {
/* 30 */       func_230443_a_(matrixStack, mouseX, mouseY);
/*    */     }
/*    */     
/* 33 */     matrixStack.func_227860_a_();
/*    */     
/* 35 */     float red = 1.0F;
/* 36 */     float green = 1.0F;
/* 37 */     float blue = 1.0F;
/*    */     
/* 39 */     if (!this.field_230693_o_) {
/* 40 */       red = green = blue = 0.4F;
/*    */     }
/* 42 */     else if (this.field_230692_n_) {
/* 43 */       matrixStack.func_227861_a_(0.0D, 0.5D, 1.0D);
/* 44 */       red = green = blue = 0.6F;
/*    */     } 
/*    */     
/* 47 */     Minecraft minecraft = Minecraft.func_71410_x();
/* 48 */     FontRenderer fontrenderer = minecraft.field_71466_p;
/* 49 */     minecraft.func_110434_K().func_110577_a(ModResources.WIDGETS);
/* 50 */     RenderSystem.color4f(red, green, blue, this.field_230695_q_);
/* 51 */     int i = func_230989_a_(func_230449_g_());
/* 52 */     RenderSystem.enableBlend();
/* 53 */     RenderSystem.defaultBlendFunc();
/* 54 */     RenderSystem.enableDepthTest();
/*    */     
/* 56 */     func_238474_b_(matrixStack, this.field_230690_l_, this.field_230691_m_, 120, 0 + i * 30, this.field_230688_j_ / 2, this.field_230689_k_ / 2);
/* 57 */     func_238474_b_(matrixStack, this.field_230690_l_, this.field_230691_m_ + this.field_230689_k_ / 2, 120, 29 - this.field_230689_k_ / 2 + i * 30, this.field_230688_j_ / 2, this.field_230689_k_ / 2);
/* 58 */     func_238474_b_(matrixStack, this.field_230690_l_ + this.field_230688_j_ / 2, this.field_230691_m_, 256 - this.field_230688_j_ / 2, 0 + i * 30, this.field_230688_j_ / 2, this.field_230689_k_ / 2);
/* 59 */     func_238474_b_(matrixStack, this.field_230690_l_ + this.field_230688_j_ / 2, this.field_230691_m_ + this.field_230689_k_ / 2, 256 - this.field_230688_j_ / 2, 29 - this.field_230689_k_ / 2 + i * 30, this.field_230688_j_ / 2, this.field_230689_k_ / 2);
/* 60 */     func_230441_a_(matrixStack, minecraft, mouseX, mouseY);
/*    */     
/* 62 */     int color = getFGColor() | MathHelper.func_76123_f(this.field_230695_q_ * 255.0F) << 24;
/* 63 */     int texLen = fontrenderer.func_238414_a_((ITextProperties)func_230458_i_()) / 2;
/* 64 */     WyHelper.drawStringWithBorder(fontrenderer, matrixStack, func_230458_i_(), this.field_230690_l_ + this.field_230688_j_ / 2 - texLen, this.field_230691_m_ + (this.field_230689_k_ - 8) / 2, color);
/*    */     
/* 66 */     matrixStack.func_227865_b_();
/* 67 */     RenderSystem.disableDepthTest();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\screens\extra\buttons\PlankButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */