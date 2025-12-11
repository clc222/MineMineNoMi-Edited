/*     */ package xyz.pixelatedw.mineminenomi.renderers.entities;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import java.awt.Color;
/*     */ import java.util.Random;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*     */ import net.minecraft.client.renderer.culling.ClippingHelper;
/*     */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*     */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*     */ import net.minecraft.client.settings.PointOfView;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.vector.Matrix4f;
/*     */ import net.minecraft.util.math.vector.Vector3f;
/*     */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*     */ import xyz.pixelatedw.mineminenomi.entities.LightningDischargeEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.goro.LightningEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class LightningEntityRenderer extends EntityRenderer<LightningEntity> {
/*     */   public LightningEntityRenderer(EntityRendererManager manager) {
/*  26 */     super(manager);
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(LightningEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer bufferIn, int packedLightIn) {
/*  31 */     matrixStack.func_227860_a_();
/*     */     
/*  33 */     renderLightning(entity, partialTicks, matrixStack, bufferIn, packedLightIn);
/*     */     
/*  35 */     matrixStack.func_227865_b_();
/*     */   }
/*     */   
/*     */   public static void renderLightning(LightningEntity entity, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer bufferIn, int packedLightIn) {
/*  39 */     if (entity.field_70173_aa < 1 || entity.getSegments() < 0) {
/*     */       return;
/*     */     }
/*     */     
/*  43 */     Random random = new Random(entity.seed);
/*     */     
/*  45 */     int angle = entity.getAngle();
/*  46 */     int segments = entity.getSegments();
/*  47 */     int branches = entity.getBranches();
/*     */     
/*  49 */     float lengthFactor = Math.min((entity.field_70173_aa + partialTicks) / 2.0F, 1.0F);
/*  50 */     float length = entity.getLength() * lengthFactor;
/*  51 */     float size = entity.getSize();
/*  52 */     float maxDistance = entity.getLength() / segments;
/*     */     
/*  54 */     Color rgb = new Color(entity.getColor());
/*     */     
/*  56 */     float[] arr = new float[segments];
/*     */     
/*  58 */     int targetNumber = (int)(segments * lengthFactor);
/*  59 */     int renderTime = (entity.getMaxLife() + entity.getMaxLife() - entity.getLife()) / 2;
/*     */     
/*  61 */     float defAlpha = entity.getAlpha() / 255.0F;
/*  62 */     float alpha = (entity.field_70173_aa < renderTime) ? defAlpha : Math.max(defAlpha * (1.0F - ((entity.field_70173_aa - renderTime) + partialTicks) / renderTime), 0.0F);
/*     */     
/*  64 */     for (int segment = 0; segment < segments; segment++) {
/*  65 */       arr[segment] = (segment == targetNumber) ? (length - maxDistance * segment) : maxDistance;
/*     */     }
/*     */     
/*  68 */     float[] offsetsY = new float[segments];
/*  69 */     float[] offsetsX = new float[segments];
/*     */     
/*  71 */     IVertexBuilder vertex = bufferIn.getBuffer(entity.getEnergyEffect() ? ModRenderTypes.ENERGY : ModRenderTypes.TRANSPARENT_COLOR2);
/*     */     
/*  73 */     Matrix4f matrix4f = matrixStack.func_227866_c_().func_227870_a_();
/*     */     
/*  75 */     matrixStack.func_227863_a_(Vector3f.field_229180_c_.func_229187_a_(entity.field_70177_z));
/*  76 */     matrixStack.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(entity.field_70125_A));
/*  77 */     matrixStack.func_227861_a_(0.0D, 0.0D, 0.1D);
/*     */ 
/*     */     
/*  80 */     for (int i = 0; i < branches; i++) {
/*  81 */       float lastOffsetY = 0.0F;
/*  82 */       float lastOffsetX = 0.0F;
/*     */       
/*     */       int j;
/*  85 */       for (j = 0; j < segments; j++) {
/*  86 */         offsetsY[j] = lastOffsetY;
/*  87 */         offsetsX[j] = lastOffsetX;
/*  88 */         lastOffsetY = (float)(lastOffsetY + Math.sin(Math.toRadians((random.nextInt(angle) - angle / 2.0F))));
/*  89 */         lastOffsetX = (float)(lastOffsetX + Math.sin(Math.toRadians((random.nextInt(angle) - angle / 2.0F))));
/*     */       } 
/*     */       
/*  92 */       for (j = 0; j < segments; j++) {
/*  93 */         float y = offsetsY[j];
/*  94 */         float x = offsetsX[j];
/*     */         
/*  96 */         for (int depth = 1; depth < 4; depth++) {
/*  97 */           float depthY = size / 2.0F + depth * size;
/*  98 */           float depthZ = size / 2.0F + depth * size;
/*     */           
/* 100 */           float endY = (j == segments - 1) ? y : offsetsY[j + 1];
/* 101 */           float endX = (j == segments - 1) ? x : offsetsX[j + 1];
/*     */           
/* 103 */           if (j <= targetNumber) {
/* 104 */             drawQuad(matrix4f, vertex, y, x, j, endY, endX, rgb.getRed(), rgb.getGreen(), rgb.getBlue(), alpha, depthY, depthZ, false, false, true, false, maxDistance, arr[j], packedLightIn);
/* 105 */             drawQuad(matrix4f, vertex, y, x, j, endY, endX, rgb.getRed(), rgb.getGreen(), rgb.getBlue(), alpha, depthY, depthZ, true, false, true, true, maxDistance, arr[j], packedLightIn);
/* 106 */             drawQuad(matrix4f, vertex, y, x, j, endY, endX, rgb.getRed(), rgb.getGreen(), rgb.getBlue(), alpha, depthY, depthZ, true, true, false, true, maxDistance, arr[j], packedLightIn);
/* 107 */             drawQuad(matrix4f, vertex, y, x, j, endY, endX, rgb.getRed(), rgb.getGreen(), rgb.getBlue(), alpha, depthY, depthZ, false, true, false, false, maxDistance, arr[j], packedLightIn);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void drawQuad(Matrix4f matrix4f, IVertexBuilder builder, float startY, float startX, int segmentIndex, float endY, float endX, int red, int green, int blue, float alpha, float firstOffset, float secondOffset, boolean negativeOffset, boolean bl2, boolean bl3, boolean bl4, float segmentLength, float segmentLengthAdded, int light) {
/* 115 */     float r = red / 255.0F;
/* 116 */     float g = green / 255.0F;
/* 117 */     float b = blue / 255.0F;
/* 118 */     float a = alpha;
/*     */     
/* 120 */     float x1 = startX + (bl2 ? secondOffset : -secondOffset);
/* 121 */     float y1 = startY + (negativeOffset ? secondOffset : -secondOffset);
/* 122 */     float x2 = endX + (bl2 ? firstOffset : -firstOffset);
/* 123 */     float y2 = endY + (negativeOffset ? firstOffset : -firstOffset);
/* 124 */     float x3 = endX + (bl4 ? firstOffset : -firstOffset);
/* 125 */     float y3 = endY + (bl3 ? firstOffset : -firstOffset);
/* 126 */     float x4 = startX + (bl4 ? secondOffset : -secondOffset);
/* 127 */     float y4 = startY + (bl3 ? secondOffset : -secondOffset);
/* 128 */     float z1 = segmentIndex * segmentLength;
/* 129 */     float z2 = z1 + segmentLengthAdded;
/*     */     
/* 131 */     builder.func_227888_a_(matrix4f, x1, y1, z1).func_227885_a_(r, g, b, a).func_227886_a_(light).func_181675_d();
/* 132 */     builder.func_227888_a_(matrix4f, x2, y2, z2).func_227885_a_(r, g, b, a).func_227886_a_(light).func_181675_d();
/* 133 */     builder.func_227888_a_(matrix4f, x3, y3, z2).func_227885_a_(r, g, b, a).func_227886_a_(light).func_181675_d();
/* 134 */     builder.func_227888_a_(matrix4f, x4, y4, z1).func_227885_a_(r, g, b, a).func_227886_a_(light).func_181675_d();
/*     */   }
/*     */   
/*     */   public static void renderLightningDischarge(LightningDischargeEntity entity, long seed, float len, float pitch, float yaw, Color rgb, float alpha0, Color outlineRGB, float outlineAlpha, MatrixStack matrixStack, IRenderTypeBuffer bufferIn, int packedLightIn) {
/* 138 */     float partialTicks = 1.0F;
/*     */     
/* 140 */     Random random = new Random(seed);
/*     */     
/* 142 */     int maxAngle = 90;
/* 143 */     int segments = entity.getDetails();
/*     */     
/* 145 */     float lengthFactor = Math.min((entity.field_70173_aa + partialTicks) / 2.0F, 1.0F);
/* 146 */     float size = 0.01F;
/* 147 */     float length = len * lengthFactor;
/* 148 */     float maxDistance = length / segments;
/*     */     
/* 150 */     float[] arr = new float[segments];
/*     */     
/* 152 */     int targetNumber = (int)(segments * lengthFactor);
/*     */     
/* 154 */     int renderTime = ((entity.getAliveTicks() > 0) ? entity.getAliveTicks() : 100) / 2;
/*     */     
/* 156 */     Minecraft mc = Minecraft.func_71410_x();
/*     */     
/* 158 */     if (mc.field_71474_y.func_243230_g() == PointOfView.FIRST_PERSON && entity.isRenderingTransparent() && entity.getOwnerId().isPresent() && ((UUID)entity.getOwnerId().get()).equals((Minecraft.func_71410_x()).field_71439_g.func_110124_au())) {
/* 159 */       alpha0 = 0.03F;
/* 160 */       outlineAlpha = 0.03F;
/*     */     } 
/*     */     
/* 163 */     float alpha = (entity.field_70173_aa < renderTime) ? alpha0 : Math.max(alpha0 * (1.0F - ((entity.field_70173_aa - renderTime) + partialTicks) / renderTime), 0.0F);
/* 164 */     float alpha2 = (entity.field_70173_aa < renderTime) ? outlineAlpha : Math.max(outlineAlpha * (1.0F - ((entity.field_70173_aa - renderTime) + partialTicks) / renderTime), 0.0F);
/*     */     
/* 166 */     if (entity.getAliveTicks() <= 0) {
/* 167 */       alpha = alpha0;
/* 168 */       alpha2 = outlineAlpha;
/*     */     } 
/*     */     
/* 171 */     for (int segment = 0; segment < arr.length; segment++) {
/* 172 */       arr[segment] = (segment == targetNumber) ? (length - maxDistance * segment) : maxDistance;
/*     */     }
/*     */     
/* 175 */     float[] offsetsY = new float[segments];
/* 176 */     float[] offsetsX = new float[segments];
/*     */     
/* 178 */     IVertexBuilder vertex = entity.isRenderingTransparent() ? bufferIn.getBuffer(ModRenderTypes.TRANSPARENT_COLOR2) : bufferIn.getBuffer(ModRenderTypes.ENERGY);
/*     */     
/* 180 */     Matrix4f matrix4f = matrixStack.func_227866_c_().func_227870_a_();
/*     */     
/* 182 */     matrixStack.func_227863_a_(Vector3f.field_229180_c_.func_229187_a_(pitch));
/* 183 */     matrixStack.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(yaw));
/*     */     
/* 185 */     size = (float)(size + WyHelper.randomDouble() / 20.0D);
/*     */     
/* 187 */     float lastOffsetY = 0.0F;
/* 188 */     float lastOffsetX = 0.0F;
/*     */     
/*     */     int j;
/* 191 */     for (j = 0; j < segments; j++) {
/* 192 */       offsetsY[j] = lastOffsetY;
/* 193 */       offsetsX[j] = lastOffsetX;
/* 194 */       lastOffsetY = (float)(lastOffsetY + Math.sin(Math.toRadians((random.nextInt(maxAngle) - maxAngle / 2.0F))));
/* 195 */       lastOffsetX = (float)(lastOffsetX + Math.sin(Math.toRadians((random.nextInt(maxAngle) - maxAngle / 2.0F))));
/*     */     } 
/*     */     
/* 198 */     for (j = 0; j < segments; j++) {
/* 199 */       if (!entity.isSplit() || j % WyHelper.randomWithRange(1, 3) != 0.0D)
/*     */       {
/*     */ 
/*     */         
/* 203 */         if (j >= entity.getSkipSegmnets()) {
/*     */ 
/*     */ 
/*     */           
/* 207 */           float y = offsetsY[j];
/* 208 */           float x = offsetsX[j];
/*     */           
/* 210 */           for (int depth = 1; depth < 4; depth++) {
/* 211 */             float depthY = size / 2.0F + depth * size;
/* 212 */             float depthZ = size / 2.0F + depth * size;
/*     */             
/* 214 */             float endY = (j == segments - 1) ? y : offsetsY[j + 1];
/* 215 */             float endX = (j == segments - 1) ? x : offsetsX[j + 1];
/*     */             
/* 217 */             if (j <= targetNumber) {
/* 218 */               drawQuad(matrix4f, vertex, y, x, j, endY, endX, rgb.getRed(), rgb.getGreen(), rgb.getBlue(), alpha, depthY, depthZ, false, false, true, false, maxDistance, arr[j], packedLightIn);
/* 219 */               drawQuad(matrix4f, vertex, y, x, j, endY, endX, rgb.getRed(), rgb.getGreen(), rgb.getBlue(), alpha, depthY, depthZ, true, false, true, true, maxDistance, arr[j], packedLightIn);
/* 220 */               drawQuad(matrix4f, vertex, y, x, j, endY, endX, rgb.getRed(), rgb.getGreen(), rgb.getBlue(), alpha, depthY, depthZ, true, true, false, true, maxDistance, arr[j], packedLightIn);
/* 221 */               drawQuad(matrix4f, vertex, y, x, j, endY, endX, rgb.getRed(), rgb.getGreen(), rgb.getBlue(), alpha, depthY, depthZ, false, true, false, false, maxDistance, arr[j], packedLightIn);
/*     */             } 
/*     */             
/* 224 */             if (entity.getOutlineColor() != 0) {
/* 225 */               depthY = size / 2.0F + depth * (size + 0.01F);
/* 226 */               depthZ = size / 2.0F + depth * (size + 0.01F);
/*     */               
/* 228 */               if (j <= targetNumber) {
/* 229 */                 drawQuad(matrix4f, vertex, y, x, j, endY, endX, outlineRGB.getRed(), outlineRGB.getGreen(), outlineRGB.getBlue(), alpha2, depthY, depthZ, false, false, true, false, maxDistance, arr[j], packedLightIn);
/* 230 */                 drawQuad(matrix4f, vertex, y, x, j, endY, endX, outlineRGB.getRed(), outlineRGB.getGreen(), outlineRGB.getBlue(), alpha2, depthY, depthZ, true, false, true, true, maxDistance, arr[j], packedLightIn);
/* 231 */                 drawQuad(matrix4f, vertex, y, x, j, endY, endX, outlineRGB.getRed(), outlineRGB.getGreen(), outlineRGB.getBlue(), alpha2, depthY, depthZ, true, true, false, true, maxDistance, arr[j], packedLightIn);
/* 232 */                 drawQuad(matrix4f, vertex, y, x, j, endY, endX, outlineRGB.getRed(), outlineRGB.getGreen(), outlineRGB.getBlue(), alpha2, depthY, depthZ, false, true, false, false, maxDistance, arr[j], packedLightIn);
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         }  } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public ResourceLocation getTextureLocation(LightningEntity entity) {
/* 241 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldRender(LightningEntity livingEntityIn, ClippingHelper camera, double camX, double camY, double camZ) {
/* 246 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static class Factory
/*     */     implements IRenderFactory<LightningEntity>
/*     */   {
/*     */     public EntityRenderer<? super LightningEntity> createRenderFor(EntityRendererManager manager) {
/* 255 */       return new LightningEntityRenderer(manager);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\LightningEntityRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */