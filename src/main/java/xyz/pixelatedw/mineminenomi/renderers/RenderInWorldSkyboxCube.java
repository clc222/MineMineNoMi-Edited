/*     */ package xyz.pixelatedw.mineminenomi.renderers;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import java.util.Random;
/*     */ import java.util.concurrent.CompletableFuture;
/*     */ import java.util.concurrent.Executor;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.ActiveRenderInfo;
/*     */ import net.minecraft.client.renderer.BufferBuilder;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.Util;
/*     */ import net.minecraft.util.math.vector.Matrix4f;
/*     */ 
/*     */ public class RenderInWorldSkyboxCube {
/*     */   private ResourceLocation[] images;
/*  20 */   private float animationSpeed = 1.0F;
/*     */   private Random rand;
/*     */   private long seed;
/*     */   
/*     */   public static RenderInWorldSkyboxCube solid(ResourceLocation textures) {
/*  25 */     RenderInWorldSkyboxCube skybox = new RenderInWorldSkyboxCube();
/*     */     
/*  27 */     skybox.images = new ResourceLocation[1];
/*  28 */     skybox.images[0] = textures;
/*     */     
/*  30 */     skybox.rand = new Random();
/*  31 */     skybox.seed = skybox.rand.nextInt(1000);
/*     */     
/*  33 */     return skybox;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void render2(Minecraft mc, MatrixStack matrixStack, ActiveRenderInfo info, double posX, double posY, double posZ, float sizeX, float sizeY, float sizeZ, float alpha) {
/*  60 */     Tessellator tessellator = Tessellator.func_178181_a();
/*  61 */     BufferBuilder buffer = tessellator.func_178180_c();
/*  62 */     Matrix4f matrix = matrixStack.func_227866_c_().func_227870_a_();
/*     */     
/*  64 */     float x = (float)(info.func_216785_c()).field_72450_a;
/*  65 */     float y = (float)(info.func_216785_c()).field_72448_b;
/*  66 */     float z = (float)(info.func_216785_c()).field_72449_c;
/*     */     
/*  68 */     float minY = (float)((-sizeY - y) + posY);
/*  69 */     float maxY = (float)((sizeY + 1.0F - y) + posY);
/*     */     
/*  71 */     float minX = (float)((-sizeX - x) + posX);
/*  72 */     float maxX = (float)((sizeX + 1.0F - x) + posX);
/*  73 */     float minZ = (float)((-sizeZ - z) + posZ);
/*  74 */     float maxZ = (float)((sizeZ + 1.0F - z) + posZ);
/*     */     
/*  76 */     RenderSystem.enableBlend();
/*  77 */     RenderSystem.enableDepthTest();
/*  78 */     RenderSystem.pushMatrix();
/*  79 */     RenderSystem.polygonOffset(-3.0F, -3.0F);
/*  80 */     RenderSystem.enablePolygonOffset();
/*  81 */     RenderSystem.defaultAlphaFunc();
/*  82 */     RenderSystem.enableAlphaTest();
/*  83 */     RenderSystem.disableCull();
/*     */     
/*  85 */     float animU = (float)(Util.func_211177_b() + this.seed) % this.animationSpeed / this.animationSpeed;
/*  86 */     float animV = (float)Util.func_211177_b() % this.animationSpeed / this.animationSpeed;
/*  87 */     float u = animU + 1.0F;
/*  88 */     float v = 0.0F + (float)this.seed / 1000.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  93 */     for (int k = 0; k < this.images.length; k++) {
/*  94 */       mc.func_110434_K().func_110577_a(this.images[k]);
/*  95 */       buffer.func_181668_a(7, DefaultVertexFormats.field_227851_o_);
/*  96 */       int l = (int)(alpha * 255.0F);
/*  97 */       if (k == 0) {
/*  98 */         buffer.func_227888_a_(matrix, minX, maxY, maxZ).func_225586_a_(255, 255, 255, l).func_225583_a_(0.0F + u, 0.0F + v).func_181675_d();
/*  99 */         buffer.func_227888_a_(matrix, minX, minY, maxZ).func_225586_a_(255, 255, 255, l).func_225583_a_(0.0F + u, 1.0F + v).func_181675_d();
/* 100 */         buffer.func_227888_a_(matrix, maxX, minY, maxZ).func_225586_a_(255, 255, 255, l).func_225583_a_(1.0F + u, 1.0F + v).func_181675_d();
/* 101 */         buffer.func_227888_a_(matrix, maxX, maxY, maxZ).func_225586_a_(255, 255, 255, l).func_225583_a_(1.0F + u, 0.0F + v).func_181675_d();
/*     */       } 
/*     */       
/* 104 */       if (k == 1) {
/* 105 */         buffer.func_227888_a_(matrix, maxX, maxY, maxZ).func_225586_a_(255, 255, 255, l).func_225583_a_(0.0F + u, 0.0F + v).func_181675_d();
/* 106 */         buffer.func_227888_a_(matrix, maxX, minY, maxZ).func_225586_a_(255, 255, 255, l).func_225583_a_(0.0F + u, 1.0F + v).func_181675_d();
/* 107 */         buffer.func_227888_a_(matrix, maxX, minY, minZ).func_225586_a_(255, 255, 255, l).func_225583_a_(1.0F + u, 1.0F + v).func_181675_d();
/* 108 */         buffer.func_227888_a_(matrix, maxX, maxY, minZ).func_225586_a_(255, 255, 255, l).func_225583_a_(1.0F + u, 0.0F + v).func_181675_d();
/*     */       } 
/*     */       
/* 111 */       if (k == 2) {
/* 112 */         buffer.func_227888_a_(matrix, maxX, maxY, minZ).func_225586_a_(255, 255, 255, l).func_225583_a_(0.0F + u, 0.0F + v).func_181675_d();
/* 113 */         buffer.func_227888_a_(matrix, maxX, minY, minZ).func_225586_a_(255, 255, 255, l).func_225583_a_(0.0F + u, 1.0F + v).func_181675_d();
/* 114 */         buffer.func_227888_a_(matrix, minX, minY, minZ).func_225586_a_(255, 255, 255, l).func_225583_a_(1.0F + u, 1.0F + v).func_181675_d();
/* 115 */         buffer.func_227888_a_(matrix, minX, maxY, minZ).func_225586_a_(255, 255, 255, l).func_225583_a_(1.0F + u, 0.0F + v).func_181675_d();
/*     */       } 
/*     */       
/* 118 */       if (k == 3) {
/* 119 */         buffer.func_227888_a_(matrix, minX, maxY, minZ).func_225586_a_(255, 255, 255, l).func_225583_a_(0.0F + u, 0.0F + v).func_181675_d();
/* 120 */         buffer.func_227888_a_(matrix, minX, minY, minZ).func_225586_a_(255, 255, 255, l).func_225583_a_(0.0F + u, 1.0F + v).func_181675_d();
/* 121 */         buffer.func_227888_a_(matrix, minX, minY, maxZ).func_225586_a_(255, 255, 255, l).func_225583_a_(1.0F + u, 1.0F + v).func_181675_d();
/* 122 */         buffer.func_227888_a_(matrix, minX, maxY, maxZ).func_225586_a_(255, 255, 255, l).func_225583_a_(1.0F + u, 0.0F + v).func_181675_d();
/*     */       } 
/*     */       
/* 125 */       if (k == 4) {
/* 126 */         buffer.func_227888_a_(matrix, minX, maxY, minZ).func_225586_a_(255, 255, 255, l).func_225583_a_(0.0F + u, 0.0F).func_181675_d();
/* 127 */         buffer.func_227888_a_(matrix, minX, maxY, maxZ).func_225586_a_(255, 255, 255, l).func_225583_a_(0.0F + u, 1.0F).func_181675_d();
/* 128 */         buffer.func_227888_a_(matrix, maxX, maxY, maxZ).func_225586_a_(255, 255, 255, l).func_225583_a_(1.0F + u, 1.0F).func_181675_d();
/* 129 */         buffer.func_227888_a_(matrix, maxX, maxY, minZ).func_225586_a_(255, 255, 255, l).func_225583_a_(1.0F + u, 0.0F).func_181675_d();
/*     */       } 
/*     */       
/* 132 */       if (k == 5) {
/* 133 */         buffer.func_227888_a_(matrix, minX, minY, maxZ).func_225586_a_(255, 255, 255, l).func_225583_a_(0.0F + u, 0.0F).func_181675_d();
/* 134 */         buffer.func_227888_a_(matrix, minX, minY, minZ).func_225586_a_(255, 255, 255, l).func_225583_a_(0.0F + u, 1.0F).func_181675_d();
/* 135 */         buffer.func_227888_a_(matrix, maxX, minY, minZ).func_225586_a_(255, 255, 255, l).func_225583_a_(1.0F + u, 1.0F).func_181675_d();
/* 136 */         buffer.func_227888_a_(matrix, maxX, minY, maxZ).func_225586_a_(255, 255, 255, l).func_225583_a_(1.0F + u, 0.0F).func_181675_d();
/*     */       } 
/*     */       
/* 139 */       tessellator.func_78381_a();
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 146 */     RenderSystem.colorMask(true, true, true, true);
/* 147 */     RenderSystem.enableCull();
/* 148 */     RenderSystem.disableAlphaTest();
/* 149 */     RenderSystem.polygonOffset(0.0F, 0.0F);
/* 150 */     RenderSystem.disablePolygonOffset();
/* 151 */     RenderSystem.enableAlphaTest();
/* 152 */     RenderSystem.disableBlend();
/* 153 */     RenderSystem.popMatrix();
/* 154 */     RenderSystem.depthMask(true);
/*     */   }
/*     */   
/*     */   public void render(Minecraft mc, float pitch, float yaw, float alpha) {
/* 158 */     Tessellator tessellator = Tessellator.func_178181_a();
/* 159 */     BufferBuilder bufferbuilder = tessellator.func_178180_c();
/* 160 */     RenderSystem.matrixMode(5889);
/* 161 */     RenderSystem.pushMatrix();
/* 162 */     RenderSystem.loadIdentity();
/* 163 */     RenderSystem.multMatrix(Matrix4f.func_195876_a(85.0D, mc.func_228018_at_().func_198109_k() / mc.func_228018_at_().func_198091_l(), 0.001F, 10.0F));
/* 164 */     RenderSystem.matrixMode(5888);
/* 165 */     RenderSystem.pushMatrix();
/* 166 */     RenderSystem.loadIdentity();
/* 167 */     RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 168 */     RenderSystem.rotatef(180.0F, 1.0F, 0.0F, 0.0F);
/* 169 */     RenderSystem.enableBlend();
/* 170 */     RenderSystem.disableAlphaTest();
/* 171 */     RenderSystem.disableCull();
/* 172 */     RenderSystem.depthMask(false);
/* 173 */     RenderSystem.defaultBlendFunc();
/* 174 */     int i = 2;
/*     */     
/* 176 */     for (int j = 0; j < 4; j++) {
/* 177 */       RenderSystem.pushMatrix();
/* 178 */       float f = ((j % 2) / 2.0F - 0.5F) / 256.0F;
/* 179 */       float f1 = ((j / 2) / 2.0F - 0.5F) / 256.0F;
/* 180 */       float f2 = 0.0F;
/* 181 */       RenderSystem.translatef(f, f1, 0.0F);
/* 182 */       RenderSystem.rotatef(pitch, 1.0F, 0.0F, 0.0F);
/* 183 */       RenderSystem.rotatef(yaw, 0.0F, 1.0F, 0.0F);
/*     */       
/* 185 */       for (int k = 0; k < 6; k++) {
/* 186 */         mc.func_110434_K().func_110577_a(this.images[k]);
/* 187 */         bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_227851_o_);
/* 188 */         int l = Math.round(255.0F * alpha) / (j + 1);
/* 189 */         if (k == 0) {
/* 190 */           bufferbuilder.func_225582_a_(-1.0D, -1.0D, 1.0D).func_225586_a_(255, 255, 255, l).func_225583_a_(0.0F, 0.0F).func_181675_d();
/* 191 */           bufferbuilder.func_225582_a_(-1.0D, 1.0D, 1.0D).func_225586_a_(255, 255, 255, l).func_225583_a_(0.0F, 1.0F).func_181675_d();
/* 192 */           bufferbuilder.func_225582_a_(1.0D, 1.0D, 1.0D).func_225586_a_(255, 255, 255, l).func_225583_a_(1.0F, 1.0F).func_181675_d();
/* 193 */           bufferbuilder.func_225582_a_(1.0D, -1.0D, 1.0D).func_225586_a_(255, 255, 255, l).func_225583_a_(1.0F, 0.0F).func_181675_d();
/*     */         } 
/*     */         
/* 196 */         if (k == 1) {
/* 197 */           bufferbuilder.func_225582_a_(1.0D, -1.0D, 1.0D).func_225586_a_(255, 255, 255, l).func_225583_a_(0.0F, 0.0F).func_181675_d();
/* 198 */           bufferbuilder.func_225582_a_(1.0D, 1.0D, 1.0D).func_225586_a_(255, 255, 255, l).func_225583_a_(0.0F, 1.0F).func_181675_d();
/* 199 */           bufferbuilder.func_225582_a_(1.0D, 1.0D, -1.0D).func_225586_a_(255, 255, 255, l).func_225583_a_(1.0F, 1.0F).func_181675_d();
/* 200 */           bufferbuilder.func_225582_a_(1.0D, -1.0D, -1.0D).func_225586_a_(255, 255, 255, l).func_225583_a_(1.0F, 0.0F).func_181675_d();
/*     */         } 
/*     */         
/* 203 */         if (k == 2) {
/* 204 */           bufferbuilder.func_225582_a_(1.0D, -1.0D, -1.0D).func_225586_a_(255, 255, 255, l).func_225583_a_(0.0F, 0.0F).func_181675_d();
/* 205 */           bufferbuilder.func_225582_a_(1.0D, 1.0D, -1.0D).func_225586_a_(255, 255, 255, l).func_225583_a_(0.0F, 1.0F).func_181675_d();
/* 206 */           bufferbuilder.func_225582_a_(-1.0D, 1.0D, -1.0D).func_225586_a_(255, 255, 255, l).func_225583_a_(1.0F, 1.0F).func_181675_d();
/* 207 */           bufferbuilder.func_225582_a_(-1.0D, -1.0D, -1.0D).func_225586_a_(255, 255, 255, l).func_225583_a_(1.0F, 0.0F).func_181675_d();
/*     */         } 
/*     */         
/* 210 */         if (k == 3) {
/* 211 */           bufferbuilder.func_225582_a_(-1.0D, -1.0D, -1.0D).func_225586_a_(255, 255, 255, l).func_225583_a_(0.0F, 0.0F).func_181675_d();
/* 212 */           bufferbuilder.func_225582_a_(-1.0D, 1.0D, -1.0D).func_225586_a_(255, 255, 255, l).func_225583_a_(0.0F, 1.0F).func_181675_d();
/* 213 */           bufferbuilder.func_225582_a_(-1.0D, 1.0D, 1.0D).func_225586_a_(255, 255, 255, l).func_225583_a_(1.0F, 1.0F).func_181675_d();
/* 214 */           bufferbuilder.func_225582_a_(-1.0D, -1.0D, 1.0D).func_225586_a_(255, 255, 255, l).func_225583_a_(1.0F, 0.0F).func_181675_d();
/*     */         } 
/*     */         
/* 217 */         if (k == 4) {
/* 218 */           bufferbuilder.func_225582_a_(-1.0D, -1.0D, -1.0D).func_225586_a_(255, 255, 255, l).func_225583_a_(0.0F, 0.0F).func_181675_d();
/* 219 */           bufferbuilder.func_225582_a_(-1.0D, -1.0D, 1.0D).func_225586_a_(255, 255, 255, l).func_225583_a_(0.0F, 1.0F).func_181675_d();
/* 220 */           bufferbuilder.func_225582_a_(1.0D, -1.0D, 1.0D).func_225586_a_(255, 255, 255, l).func_225583_a_(1.0F, 1.0F).func_181675_d();
/* 221 */           bufferbuilder.func_225582_a_(1.0D, -1.0D, -1.0D).func_225586_a_(255, 255, 255, l).func_225583_a_(1.0F, 0.0F).func_181675_d();
/*     */         } 
/*     */         
/* 224 */         if (k == 5) {
/* 225 */           bufferbuilder.func_225582_a_(-1.0D, 1.0D, 1.0D).func_225586_a_(255, 255, 255, l).func_225583_a_(0.0F, 0.0F).func_181675_d();
/* 226 */           bufferbuilder.func_225582_a_(-1.0D, 1.0D, -1.0D).func_225586_a_(255, 255, 255, l).func_225583_a_(0.0F, 1.0F).func_181675_d();
/* 227 */           bufferbuilder.func_225582_a_(1.0D, 1.0D, -1.0D).func_225586_a_(255, 255, 255, l).func_225583_a_(1.0F, 1.0F).func_181675_d();
/* 228 */           bufferbuilder.func_225582_a_(1.0D, 1.0D, 1.0D).func_225586_a_(255, 255, 255, l).func_225583_a_(1.0F, 0.0F).func_181675_d();
/*     */         } 
/*     */         
/* 231 */         tessellator.func_78381_a();
/*     */       } 
/*     */       
/* 234 */       RenderSystem.popMatrix();
/* 235 */       RenderSystem.colorMask(true, true, true, false);
/*     */     } 
/*     */     
/* 238 */     RenderSystem.colorMask(true, true, true, true);
/* 239 */     RenderSystem.matrixMode(5889);
/* 240 */     RenderSystem.popMatrix();
/* 241 */     RenderSystem.matrixMode(5888);
/* 242 */     RenderSystem.popMatrix();
/* 243 */     RenderSystem.depthMask(true);
/* 244 */     RenderSystem.enableCull();
/* 245 */     RenderSystem.enableDepthTest();
/*     */   }
/*     */   
/*     */   public CompletableFuture<Void> preload(TextureManager pTexMngr, Executor pBackgroundExecutor) {
/* 249 */     CompletableFuture[] arrayOfCompletableFuture = new CompletableFuture[6];
/*     */     
/* 251 */     for (int i = 0; i < arrayOfCompletableFuture.length; i++) {
/* 252 */       arrayOfCompletableFuture[i] = pTexMngr.func_215268_a(this.images[i], pBackgroundExecutor);
/*     */     }
/*     */     
/* 255 */     return CompletableFuture.allOf((CompletableFuture<?>[])arrayOfCompletableFuture);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\RenderInWorldSkyboxCube.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */