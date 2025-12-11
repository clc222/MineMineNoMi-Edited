/*     */ package xyz.pixelatedw.mineminenomi.renderers.entities;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import java.awt.Color;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*     */ import net.minecraft.client.renderer.culling.ClippingHelper;
/*     */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*     */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*     */ import xyz.pixelatedw.mineminenomi.entities.LightningDischargeEntity;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LightningBallEntityRenderer
/*     */   extends EntityRenderer<LightningDischargeEntity>
/*     */ {
/*     */   public LightningBallEntityRenderer(EntityRendererManager manager) {
/*  23 */     super(manager);
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(LightningDischargeEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer bufferIn, int packedLightIn) {
/*  28 */     List<LightningRendererPropieties> entities = entity.entities;
/*     */     
/*  30 */     if (!Minecraft.func_71410_x().func_147113_T()) {
/*  31 */       if (entity.field_70173_aa % entity.getUpdateRate() == 0) {
/*  32 */         entities.clear();
/*     */       }
/*     */       
/*  35 */       int lightnings = entity.getDensity();
/*     */       
/*  37 */       if (entities.isEmpty()) {
/*  38 */         for (int i = 0; i < lightnings; i++) {
/*  39 */           LightningRendererPropieties e = new LightningRendererPropieties((Entity)entity, entity.getSeed(), (float)WyHelper.randomWithRange(0, 360), (float)WyHelper.randomWithRange(0, 180), entity.getLightningLength());
/*     */           
/*  41 */           entities.add(e);
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/*  46 */     Color color = new Color(entity.getColor());
/*     */     
/*  48 */     float alpha = entity.getAlpha() / 255.0F;
/*     */     
/*  50 */     Color outlineColor = new Color(entity.getOutlineColor());
/*     */     
/*  52 */     float outlineAlpha = entity.getOutlineAlpha() / 255.0F;
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
/*  70 */     matrixStack.func_227860_a_();
/*  71 */     matrixStack.func_227862_a_(entity.getSize() / 2.0F, entity.getSize() / 2.0F, entity.getSize() / 2.0F);
/*     */     
/*  73 */     entities.forEach(e -> LightningEntityRenderer.renderLightningDischarge(entity, e.random, e.length, e.xRot, e.yRot, color, alpha, outlineColor, outlineAlpha, matrixStack, bufferIn, packedLightIn));
/*     */     
/*  75 */     matrixStack.func_227865_b_();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ResourceLocation getTextureLocation(LightningDischargeEntity entity) {
/*  81 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldRender(LightningDischargeEntity livingEntityIn, ClippingHelper camera, double camX, double camY, double camZ) {
/*  87 */     return true;
/*     */   }
/*     */   
/*     */   public static class LightningRendererPropieties
/*     */   {
/*  92 */     private Entity entity = null; float xRot;
/*  93 */     float length = 0.0F; float yRot;
/*  94 */     long random = 0L;
/*     */ 
/*     */     
/*     */     public LightningRendererPropieties(Entity entity, long random, float xRot, float yRot, float length) {
/*  98 */       this.entity = entity;
/*  99 */       this.random = random;
/* 100 */       this.yRot = yRot;
/* 101 */       this.xRot = xRot;
/* 102 */       this.length = length;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class Factory
/*     */     implements IRenderFactory<LightningDischargeEntity>
/*     */   {
/*     */     public EntityRenderer<? super LightningDischargeEntity> createRenderFor(EntityRendererManager manager) {
/* 116 */       return new LightningBallEntityRenderer(manager);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\LightningBallEntityRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */