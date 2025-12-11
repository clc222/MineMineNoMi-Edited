/*    */ package xyz.pixelatedw.mineminenomi.screens.widgets;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.platform.GlStateManager;
/*    */ import com.mojang.blaze3d.systems.RenderSystem;
/*    */ import java.awt.Color;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.FontRenderer;
/*    */ import net.minecraft.client.gui.widget.button.CheckboxButton;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class NewCheckboxButton
/*    */   extends CheckboxButton
/*    */ {
/* 20 */   private static final ResourceLocation TEXTURE = new ResourceLocation("textures/gui/checkbox.png");
/*    */   private final boolean showLabel;
/* 22 */   private Color iconColor = Color.WHITE;
/* 23 */   private Color textColor = Color.WHITE;
/*    */   private ICheckEvent event;
/*    */   protected ITooltip onTooltip;
/*    */   
/*    */   public NewCheckboxButton(int pX, int pY, int pWidth, int pHeight, ITextComponent pMessage, boolean pSelected) {
/* 28 */     super(pX, pY, pWidth, pHeight, pMessage, pSelected);
/* 29 */     this.showLabel = true;
/*    */   }
/*    */   
/*    */   public void setTooltip(ITooltip tooltip) {
/* 33 */     this.onTooltip = tooltip;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_230930_b_() {
/* 38 */     if (isActive()) {
/* 39 */       super.func_230930_b_();
/* 40 */       if (this.event != null) {
/* 41 */         this.event.onCheck(this);
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   public boolean isActive() {
/* 47 */     return this.field_230693_o_;
/*    */   }
/*    */   
/*    */   public void setActive(boolean flag) {
/* 51 */     this.field_230693_o_ = flag;
/*    */   }
/*    */   
/*    */   public void setIconColor(Color color) {
/* 55 */     this.iconColor = color;
/*    */   }
/*    */   
/*    */   public void setCheckEvent(ICheckEvent event) {
/* 59 */     this.event = event;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_230443_a_(MatrixStack poseStack, int mouseX, int mouseY) {
/* 64 */     this.onTooltip.onTooltip(this, poseStack, mouseX, mouseY);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_230431_b_(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
/* 69 */     if (func_230449_g_()) {
/* 70 */       func_230443_a_(matrixStack, mouseX, mouseY);
/*    */     }
/*    */     
/* 73 */     Minecraft minecraft = Minecraft.func_71410_x();
/* 74 */     minecraft.func_110434_K().func_110577_a(TEXTURE);
/* 75 */     RenderSystem.enableDepthTest();
/* 76 */     FontRenderer fontrenderer = minecraft.field_71466_p;
/* 77 */     RenderSystem.color4f(this.iconColor.getRed() / 255.0F, this.iconColor.getGreen() / 255.0F, this.iconColor.getBlue() / 255.0F, this.field_230695_q_);
/* 78 */     RenderSystem.enableBlend();
/* 79 */     RenderSystem.defaultBlendFunc();
/* 80 */     RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
/* 81 */     func_238463_a_(matrixStack, this.field_230690_l_, this.field_230691_m_, func_230999_j_() ? 20.0F : 0.0F, func_212942_a() ? 20.0F : 0.0F, 20, this.field_230689_k_, 64, 64);
/* 82 */     func_230441_a_(matrixStack, minecraft, mouseX, mouseY);
/* 83 */     if (this.showLabel)
/* 84 */       WyHelper.drawStringWithBorder(fontrenderer, matrixStack, func_230458_i_(), this.field_230690_l_ + 24, this.field_230691_m_ + (this.field_230689_k_ - 8) / 2, 0xE0E0E0 | MathHelper.func_76123_f(this.field_230695_q_ * 255.0F) << 24); 
/*    */   }
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public static interface ITooltip {
/*    */     void onTooltip(NewCheckboxButton param1NewCheckboxButton, MatrixStack param1MatrixStack, int param1Int1, int param1Int2);
/*    */   }
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public static interface ICheckEvent {
/*    */     void onCheck(NewCheckboxButton param1NewCheckboxButton);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\screens\widgets\NewCheckboxButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */