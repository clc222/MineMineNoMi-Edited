/*     */ package xyz.pixelatedw.mineminenomi.screens.extra.buttons;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.FontRenderer;
/*     */ import net.minecraft.client.gui.widget.button.Button;
/*     */ import net.minecraft.util.IReorderingProcessor;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.ITextProperties;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ @Deprecated
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class TexturedIconButton
/*     */   extends Button
/*     */ {
/*     */   private ResourceLocation texture;
/*     */   private int textureWidth;
/*     */   private int textureHeight;
/*     */   private int texturePosX;
/*     */   private int texturePosY;
/*     */   private int textPosX;
/*     */   private int textPosY;
/*  31 */   private double textScale = 1.0D;
/*     */   private ResourceLocation iconTexture;
/*  33 */   private double iconScale = 1.0D;
/*     */   
/*     */   private int iconPosX;
/*     */   private int iconPosY;
/*     */   private boolean isPressed;
/*     */   private boolean isFlipped;
/*     */   
/*     */   public TexturedIconButton(ResourceLocation loc, int posX, int posY, int width, int height, ITextComponent text, Button.IPressable onPress) {
/*  41 */     super(posX, posY, width, height, text, onPress);
/*     */     
/*  43 */     this.texture = loc;
/*  44 */     this.texturePosX = posX;
/*  45 */     this.texturePosY = posY;
/*  46 */     this.textureWidth = width;
/*  47 */     this.textureHeight = height;
/*     */   }
/*     */ 
/*     */   
/*     */   public TexturedIconButton setTextureInfo(int texturePosX, int texturePosY, int textureWidth, int textureHeight) {
/*  52 */     this.texturePosX = texturePosX;
/*  53 */     this.texturePosY = texturePosY;
/*  54 */     this.textureWidth = textureWidth;
/*  55 */     this.textureHeight = textureHeight;
/*  56 */     return this;
/*     */   }
/*     */   
/*     */   public TexturedIconButton setFlipped(boolean flag) {
/*  60 */     this.isFlipped = flag;
/*  61 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public TexturedIconButton setTextInfo(int textPosX, int textPosY, double scale) {
/*  66 */     this.textPosX = textPosX;
/*  67 */     this.textPosY = textPosY - 7;
/*  68 */     this.textScale = scale;
/*  69 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public TexturedIconButton setIconInfo(ResourceLocation loc, int iconPosX, int iconPosY, double scale) {
/*  74 */     this.iconTexture = loc;
/*  75 */     this.iconPosX = iconPosX;
/*  76 */     this.iconPosY = iconPosY;
/*  77 */     this.iconScale = scale;
/*  78 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_230430_a_(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
/*  84 */     if (!this.field_230694_p_) {
/*     */       return;
/*     */     }
/*  87 */     RenderSystem.enableDepthTest();
/*  88 */     this.field_230692_n_ = (this.field_230693_o_ && mouseX >= this.field_230690_l_ && mouseY >= this.field_230691_m_ && mouseX < this.field_230690_l_ + this.field_230688_j_ && mouseY < this.field_230691_m_ + this.field_230689_k_);
/*     */     
/*  90 */     matrixStack.func_227860_a_();
/*     */     
/*  92 */     float red = 1.0F;
/*  93 */     float green = 1.0F;
/*  94 */     float blue = 1.0F;
/*     */     
/*  96 */     if (this.field_230692_n_ || this.isPressed) {
/*     */       
/*  98 */       matrixStack.func_227861_a_(0.0D, 0.5D, 1.0D);
/*  99 */       red = green = blue = 0.6F;
/*     */     } 
/*     */     
/* 102 */     if (!this.field_230693_o_) {
/* 103 */       red = green = blue = 0.4F;
/*     */     }
/* 105 */     RendererHelper.drawIcon(this.texture, matrixStack, this.texturePosX, this.texturePosY, 150.0F, this.textureWidth, this.textureHeight, red, green, blue, 1.0F, this.isFlipped);
/*     */ 
/*     */     
/* 108 */     if (this.iconTexture != null) {
/*     */       
/* 110 */       matrixStack.func_227860_a_();
/*     */       
/* 112 */       RenderSystem.enableBlend();
/* 113 */       matrixStack.func_227861_a_(this.iconPosX, this.iconPosY, 151.0D);
/* 114 */       matrixStack.func_227862_a_((float)this.iconScale, (float)this.iconScale, (float)this.iconScale);
/* 115 */       RendererHelper.drawIcon(this.iconTexture, matrixStack, 0.0F, 0.0F, 1.0F, 16.0F, 16.0F);
/*     */       
/* 117 */       matrixStack.func_227865_b_();
/*     */     } 
/*     */ 
/*     */     
/* 121 */     matrixStack.func_227861_a_(0.0D, 0.0D, 250.0D);
/* 122 */     matrixStack.func_227860_a_();
/*     */     
/* 124 */     FontRenderer font = (Minecraft.func_71410_x()).field_71466_p;
/* 125 */     List<String> strings = Arrays.asList(new String[] { func_230458_i_().getString() });
/* 126 */     List<IReorderingProcessor> splitProc = null;
/*     */     
/* 128 */     int splits = (func_230458_i_().getString().split(" ")).length;
/* 129 */     if (splits > 1) {
/* 130 */       splitProc = font.func_238425_b_((ITextProperties)func_230458_i_(), this.field_230688_j_ / splits + 10);
/*     */     }
/*     */     
/* 133 */     if (splitProc != null) {
/*     */       
/* 135 */       matrixStack.func_227861_a_(this.textPosX, (this.textPosY - ((splitProc.size() > 1) ? (splitProc.size() * 3) : 0)), 2.0D);
/* 136 */       matrixStack.func_227861_a_(128.0D, 128.0D, 0.0D);
/* 137 */       matrixStack.func_227862_a_((float)this.textScale, (float)this.textScale, (float)this.textScale);
/* 138 */       matrixStack.func_227861_a_(-128.0D, -128.0D, 1.0D);
/* 139 */       for (int b = 0; b < splitProc.size(); b++)
/*     */       {
/* 141 */         WyHelper.drawStringWithBorder(font, matrixStack, splitProc.get(b), 0, 7 + b * 9, WyHelper.hexToRGB("#FFFFFF").getRGB());
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 146 */       matrixStack.func_227861_a_(this.textPosX, (this.textPosY - ((strings.size() > 1) ? (strings.size() * 3) : 0)), 2.0D);
/* 147 */       matrixStack.func_227861_a_(128.0D, 128.0D, 0.0D);
/* 148 */       matrixStack.func_227862_a_((float)this.textScale, (float)this.textScale, (float)this.textScale);
/* 149 */       matrixStack.func_227861_a_(-128.0D, -128.0D, 1.0D);
/* 150 */       for (int b = 0; b < strings.size(); b++)
/*     */       {
/* 152 */         WyHelper.drawStringWithBorder(font, matrixStack, strings.get(b), 0, 7 + b * 9, WyHelper.hexToRGB("#FFFFFF").getRGB());
/*     */       }
/*     */     } 
/*     */     
/* 156 */     matrixStack.func_227865_b_();
/*     */     
/* 158 */     RenderSystem.color3f(1.0F, 1.0F, 1.0F);
/* 159 */     RenderSystem.disableDepthTest();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 164 */     matrixStack.func_227865_b_();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIsPressed(boolean flag) {
/* 169 */     this.isPressed = flag;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\screens\extra\buttons\TexturedIconButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */