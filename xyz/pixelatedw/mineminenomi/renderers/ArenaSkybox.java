/*     */ package xyz.pixelatedw.mineminenomi.renderers;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.platform.GlStateManager;
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import java.awt.Color;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.ActiveRenderInfo;
/*     */ import net.minecraft.client.renderer.BufferBuilder;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.Util;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.vector.Matrix4f;
/*     */ import net.minecraft.util.math.vector.Vector3f;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class ArenaSkybox
/*     */ {
/*     */   private final Minecraft minecraft;
/*     */   private float time;
/*     */   private int seed;
/*  29 */   private Color color = Color.WHITE;
/*     */   
/*  31 */   private ResourceLocation[] textures = new ResourceLocation[] { new ResourceLocation("mineminenomi", "textures/skyboxes/default.png") };
/*  32 */   private int animationSpeed = 10000;
/*  33 */   private int detailLevel = 8;
/*  34 */   private float radius = 1.0F;
/*     */   private boolean isFullWrapping;
/*     */   private boolean isGlobal;
/*     */   private long lastTick;
/*  38 */   private int textureId = 0;
/*     */   
/*     */   public ArenaSkybox() {
/*  41 */     this.minecraft = Minecraft.func_71410_x();
/*     */   }
/*     */   
/*     */   public ArenaSkybox setTexture(boolean isFullWrapping, ResourceLocation... textures) {
/*  45 */     this.textures = textures;
/*  46 */     this.textureId = 0;
/*  47 */     this.isFullWrapping = isFullWrapping;
/*  48 */     return this;
/*     */   }
/*     */   
/*     */   public ArenaSkybox setAnimationSpeed(int animationSpeed) {
/*  52 */     this.animationSpeed = animationSpeed;
/*  53 */     return this;
/*     */   }
/*     */   
/*     */   public ArenaSkybox setColor(Color color) {
/*  57 */     this.color = color;
/*  58 */     return this;
/*     */   }
/*     */   
/*     */   public ArenaSkybox setAlpha(float alpha) {
/*  62 */     this.color = WyHelper.intToRGB(this.color.getRGB(), (int)alpha * 255);
/*  63 */     return this;
/*     */   }
/*     */   
/*     */   public ArenaSkybox setRadius(float radius) {
/*  67 */     this.radius = radius;
/*  68 */     return this;
/*     */   }
/*     */   
/*     */   public ArenaSkybox setDetailLevel(int detail) {
/*  72 */     this.detailLevel = detail;
/*  73 */     return this;
/*     */   }
/*     */   
/*     */   public ArenaSkybox setGlobal() {
/*  77 */     this.isGlobal = true;
/*  78 */     return this;
/*     */   }
/*     */   
/*     */   public void renderSphere(MatrixStack matrixStack) {
/*  82 */     RenderSystem.enableBlend();
/*  83 */     RenderSystem.defaultBlendFunc();
/*  84 */     RenderSystem.depthMask(false);
/*  85 */     RenderSystem.enableDepthTest();
/*     */     
/*  87 */     renderActualSphere(matrixStack, 0.0F, 0.0F, 0.0F, true);
/*     */     
/*  89 */     RenderSystem.disableDepthTest();
/*  90 */     RenderSystem.depthMask(true);
/*  91 */     RenderSystem.disableBlend();
/*     */   }
/*     */   
/*     */   public void renderSphereInWorld(MatrixStack matrixStack, ActiveRenderInfo info, double posX, double posY, double posZ) {
/*  95 */     double x = (info.func_216785_c()).field_72450_a;
/*  96 */     double y = (info.func_216785_c()).field_72448_b;
/*  97 */     double z = (info.func_216785_c()).field_72449_c;
/*     */     
/*  99 */     boolean isInisde = false;
/* 100 */     double distance = (x - posX) * (x - posX) + (z - posZ) * (z - posZ) + (y - posY) * (y - posY);
/* 101 */     if (distance < (this.radius * this.radius)) {
/* 102 */       isInisde = true;
/*     */     }
/*     */     
/* 105 */     if (this.isGlobal) {
/* 106 */       x = -this.radius - x + posX + this.radius;
/* 107 */       y = -this.radius - y + posY + this.radius;
/* 108 */       z = -this.radius - z + posZ + this.radius;
/*     */     } else {
/* 110 */       x = 0.0D;
/* 111 */       y = 0.0D;
/* 112 */       z = 0.0D;
/*     */     } 
/*     */     
/* 115 */     if (Minecraft.func_238218_y_()) {
/* 116 */       (Minecraft.func_71410_x()).field_71438_f.func_239229_r_().func_147610_a(false);
/*     */     }
/*     */     
/* 119 */     RenderSystem.enableBlend();
/* 120 */     RenderSystem.enableDepthTest();
/* 121 */     RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
/*     */ 
/*     */     
/* 124 */     RenderSystem.depthMask(Minecraft.func_238218_y_());
/* 125 */     RenderSystem.pushMatrix();
/* 126 */     RenderSystem.defaultAlphaFunc();
/* 127 */     RenderSystem.enableAlphaTest();
/* 128 */     if (!isInisde) {
/* 129 */       if (Minecraft.func_238218_y_()) {
/* 130 */         RenderSystem.disableCull();
/* 131 */         RenderSystem.depthMask(false);
/*     */       } else {
/* 133 */         RenderSystem.disableCull();
/*     */       } 
/*     */     }
/*     */     
/* 137 */     renderActualSphere(matrixStack, (float)x, (float)y, (float)z, isInisde);
/*     */     
/* 139 */     if (!isInisde) {
/* 140 */       RenderSystem.enableCull();
/*     */     }
/* 142 */     RenderSystem.disableAlphaTest();
/* 143 */     RenderSystem.disableAlphaTest();
/* 144 */     RenderSystem.disableBlend();
/* 145 */     RenderSystem.popMatrix();
/* 146 */     RenderSystem.depthMask(true);
/*     */     
/* 148 */     if (Minecraft.func_238218_y_()) {
/* 149 */       Minecraft.func_71410_x().func_147110_a().func_147610_a(false);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderActualSphere(MatrixStack matrixStack, float x, float y, float z, boolean isInisde) {
/* 159 */     Tessellator tessellator = Tessellator.func_178181_a();
/* 160 */     BufferBuilder vertex = tessellator.func_178180_c();
/*     */     
/* 162 */     if (this.textures.length > 1) {
/* 163 */       long currentTick = Util.func_211177_b();
/* 164 */       if (currentTick > this.lastTick + 100L) {
/* 165 */         this.lastTick = currentTick;
/* 166 */         this.textureId++;
/* 167 */         this.textureId %= this.textures.length;
/*     */       } 
/*     */     } 
/*     */     
/* 171 */     Minecraft.func_71410_x().func_110434_K().func_110577_a(this.textures[this.textureId]);
/* 172 */     vertex.func_181668_a(4, DefaultVertexFormats.field_227852_q_);
/*     */     
/* 174 */     float startU = 0.0F;
/* 175 */     float startV = 0.0F;
/* 176 */     float endU = 6.2831855F;
/* 177 */     float endV = 3.1415927F;
/* 178 */     float stepU = (endU - startU) / this.detailLevel;
/* 179 */     float stepV = (endV - startV) / this.detailLevel;
/*     */     
/* 181 */     float animU = (float)((Util.func_211177_b() + this.seed) % this.animationSpeed) / this.animationSpeed;
/* 182 */     float animV = (float)(Util.func_211177_b() % this.animationSpeed) / this.animationSpeed;
/* 183 */     float uAnim = animU + 1.0F;
/* 184 */     float vAnim = 0.0F;
/*     */     
/* 186 */     int red = this.color.getRed();
/* 187 */     int green = this.color.getGreen();
/* 188 */     int blue = this.color.getBlue();
/* 189 */     int alpha = this.color.getAlpha();
/*     */     
/* 191 */     Matrix4f projection = matrixStack.func_227866_c_().func_227870_a_();
/*     */     
/* 193 */     for (int i = 0; i < this.detailLevel; i++) {
/* 194 */       for (int j = 0; j < this.detailLevel; j++) {
/*     */         
/* 196 */         float u = i * stepU + startU;
/* 197 */         float v = j * stepV + startV;
/* 198 */         float un = (i + 1 == this.detailLevel) ? endU : ((i + 1) * stepU + startU);
/* 199 */         float vn = (j + 1 == this.detailLevel) ? endV : ((j + 1) * stepV + startV);
/*     */         
/* 201 */         Vector3f p0 = parametricSphere(u, v, this.radius);
/* 202 */         Vector3f p1 = parametricSphere(u, vn, this.radius);
/* 203 */         Vector3f p2 = parametricSphere(un, v, this.radius);
/* 204 */         Vector3f p3 = parametricSphere(un, vn, this.radius);
/*     */         
/* 206 */         float textureU = u / endU * (this.isFullWrapping ? 1.0F : this.radius);
/* 207 */         float textureV = v / endV * (this.isFullWrapping ? 1.0F : this.radius);
/* 208 */         float textureUN = un / endU * (this.isFullWrapping ? 1.0F : this.radius);
/* 209 */         float textureVN = vn / endV * (this.isFullWrapping ? 1.0F : this.radius);
/*     */         
/* 211 */         textureU += uAnim;
/* 212 */         textureUN += uAnim;
/*     */         
/* 214 */         if (isInisde) {
/* 215 */           vertexPosColorUVLight((IVertexBuilder)vertex, projection, x - p0.func_195899_a(), y - p0.func_195900_b(), z - p0.func_195902_c(), red, green, blue, alpha, textureU, textureV, 1);
/* 216 */           vertexPosColorUVLight((IVertexBuilder)vertex, projection, x - p2.func_195899_a(), y - p2.func_195900_b(), z - p2.func_195902_c(), red, green, blue, alpha, textureUN, textureV, 1);
/* 217 */           vertexPosColorUVLight((IVertexBuilder)vertex, projection, x - p1.func_195899_a(), y - p1.func_195900_b(), z - p1.func_195902_c(), red, green, blue, alpha, textureU, textureVN, 1);
/*     */           
/* 219 */           vertexPosColorUVLight((IVertexBuilder)vertex, projection, x - p3.func_195899_a(), y - p3.func_195900_b(), z - p3.func_195902_c(), red, green, blue, alpha, textureUN, textureVN, 1);
/* 220 */           vertexPosColorUVLight((IVertexBuilder)vertex, projection, x - p1.func_195899_a(), y - p1.func_195900_b(), z - p1.func_195902_c(), red, green, blue, alpha, textureU, textureVN, 1);
/* 221 */           vertexPosColorUVLight((IVertexBuilder)vertex, projection, x - p2.func_195899_a(), y - p2.func_195900_b(), z - p2.func_195902_c(), red, green, blue, alpha, textureUN, textureV, 1);
/*     */         } else {
/*     */           
/* 224 */           vertexPosColorUVLight((IVertexBuilder)vertex, projection, x + p0.func_195899_a(), y + p0.func_195900_b(), z + p0.func_195902_c(), red, green, blue, alpha, textureU, textureV, 1);
/* 225 */           vertexPosColorUVLight((IVertexBuilder)vertex, projection, x + p2.func_195899_a(), y + p2.func_195900_b(), z + p2.func_195902_c(), red, green, blue, alpha, textureUN, textureV, 1);
/* 226 */           vertexPosColorUVLight((IVertexBuilder)vertex, projection, x + p1.func_195899_a(), y + p1.func_195900_b(), z + p1.func_195902_c(), red, green, blue, alpha, textureU, textureVN, 1);
/*     */           
/* 228 */           vertexPosColorUVLight((IVertexBuilder)vertex, projection, x + p3.func_195899_a(), y + p3.func_195900_b(), z + p3.func_195902_c(), red, green, blue, alpha, textureUN, textureVN, 1);
/* 229 */           vertexPosColorUVLight((IVertexBuilder)vertex, projection, x + p1.func_195899_a(), y + p1.func_195900_b(), z + p1.func_195902_c(), red, green, blue, alpha, textureU, textureVN, 1);
/* 230 */           vertexPosColorUVLight((IVertexBuilder)vertex, projection, x + p2.func_195899_a(), y + p2.func_195900_b(), z + p2.func_195902_c(), red, green, blue, alpha, textureUN, textureV, 1);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 235 */     tessellator.func_78381_a();
/*     */   }
/*     */   
/*     */   private Vector3f parametricSphere(float u, float v, float r) {
/* 239 */     return new Vector3f(MathHelper.func_76134_b(u) * MathHelper.func_76126_a(v) * r, MathHelper.func_76134_b(v) * r, MathHelper.func_76126_a(u) * MathHelper.func_76126_a(v) * r);
/*     */   }
/*     */   
/*     */   private void vertexPosColorUVLight(IVertexBuilder buffer, Matrix4f projection, float x, float y, float z, int r, int g, int b, int a, float u, float v, int light) {
/* 243 */     buffer.func_227888_a_(projection, x, y, z).func_225586_a_(r, g, b, a).func_225583_a_(u, v).func_227886_a_(light).func_181675_d();
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\ArenaSkybox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */