/*    */ package xyz.pixelatedw.mineminenomi.screens.extra.buttons;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.systems.RenderSystem;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.widget.button.Button;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TextFormatting;
/*    */ import net.minecraftforge.fml.client.gui.GuiUtils;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public class ChallengeButton
/*    */   extends Button
/*    */ {
/*    */   private Minecraft minecraft;
/*    */   private boolean isSelected;
/*    */   private int unfinished;
/*    */   private static final float HOVER_SCALE = 0.4F;
/*    */   
/*    */   public ChallengeButton(int posX, int posY, int width, int height, ITextComponent string, int unfinished, Button.IPressable onPress) {
/* 26 */     super(posX, posY, width, height, string, onPress);
/* 27 */     this.minecraft = Minecraft.func_71410_x();
/* 28 */     this.unfinished = unfinished;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_230430_a_(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
/* 34 */     matrixStack.func_227860_a_();
/*    */     
/* 36 */     if (this.field_230694_p_) {
/*    */       
/* 38 */       this.field_230692_n_ = (mouseX >= this.field_230690_l_ && mouseY >= this.field_230691_m_ && mouseX < this.field_230690_l_ + this.field_230688_j_ && mouseY < this.field_230691_m_ + this.field_230689_k_);
/*    */       
/* 40 */       if (this.field_230692_n_) {
/* 41 */         matrixStack.func_227861_a_(0.0D, -2.0D, 0.0D);
/*    */       }
/* 43 */       matrixStack.func_227860_a_();
/*    */       
/* 45 */       matrixStack.func_227861_a_((this.field_230690_l_ - 128), (this.field_230691_m_ - 128), 0.0D);
/* 46 */       matrixStack.func_227861_a_(128.0D, 128.0D, 0.0D);
/* 47 */       matrixStack.func_227862_a_(0.4F, 0.4F, 0.4F);
/*    */       
/* 49 */       Minecraft.func_71410_x().func_110434_K().func_110577_a(ModResources.CHALLENGE_BLANK);
/* 50 */       if (this.unfinished == 0) {
/*    */         
/* 52 */         matrixStack.func_227860_a_();
/*    */         
/* 54 */         RenderSystem.color4f(1.0F, 1.0F, 0.0F, 1.0F);
/* 55 */         matrixStack.func_227861_a_(-12.0D, -12.0D, 0.0D);
/* 56 */         matrixStack.func_227862_a_(1.1F, 1.1F, 1.1F);
/* 57 */         GuiUtils.drawTexturedModalRect(matrixStack, 0, 0, 0, 0, 256, 256, 0.0F);
/* 58 */         RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
/*    */         
/* 60 */         matrixStack.func_227865_b_();
/*    */       } 
/* 62 */       GuiUtils.drawTexturedModalRect(matrixStack, 0, 0, 0, 0, 256, 256, 0.0F);
/*    */       
/* 64 */       RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 65 */       Minecraft.func_71410_x().func_110434_K().func_110577_a(new ResourceLocation("mineminenomi", "textures/gui/challenges/" + WyHelper.getResourceName(func_230458_i_().getString()) + ".png"));
/* 66 */       GuiUtils.drawTexturedModalRect(matrixStack, 0, 0, 0, 0, 256, 256, 2.0F);
/*    */       
/* 68 */       matrixStack.func_227861_a_(-128.0D, -128.0D, 0.0D);
/*    */       
/* 70 */       matrixStack.func_227865_b_();
/*    */       
/* 72 */       matrixStack.func_227860_a_();
/*    */       
/* 74 */       int color = -1;
/* 75 */       if (this.unfinished == 0)
/* 76 */         color = WyHelper.hexToRGB("#FFBB11").getRGB(); 
/* 77 */       RenderSystem.translated(0.0D, 0.0D, 1.0D);
/* 78 */       WyHelper.drawStringWithBorder(this.minecraft.field_71466_p, matrixStack, TextFormatting.BOLD + "" + func_230458_i_().getString(), this.field_230690_l_ + 46 - this.minecraft.field_71466_p.func_78256_a(func_230458_i_().getString()) / 2, this.field_230691_m_ + 83, color);
/* 79 */       RenderSystem.disableBlend();
/*    */       
/* 81 */       matrixStack.func_227865_b_();
/*    */     } 
/*    */     
/* 84 */     matrixStack.func_227865_b_();
/*    */   }
/*    */ 
/*    */   
/*    */   public void select() {
/* 89 */     this.isSelected = !this.isSelected;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\screens\extra\buttons\ChallengeButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */