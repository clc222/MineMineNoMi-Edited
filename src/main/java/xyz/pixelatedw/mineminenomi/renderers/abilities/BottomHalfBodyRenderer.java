/*     */ package xyz.pixelatedw.mineminenomi.renderers.abilities;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.platform.GlStateManager;
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*     */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*     */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*     */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*     */ import net.minecraft.client.renderer.entity.model.PlayerModel;
/*     */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*     */ import net.minecraft.client.resources.DefaultPlayerSkin;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.vector.Quaternion;
/*     */ import net.minecraft.util.math.vector.Vector3f;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*     */ import xyz.pixelatedw.mineminenomi.entities.BottomHalfBodyEntity;
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class BottomHalfBodyRenderer
/*     */   extends EntityRenderer<BottomHalfBodyEntity> {
/*  30 */   private PlayerModel model = new PlayerModel(1.0F, false);
/*     */ 
/*     */   
/*     */   public BottomHalfBodyRenderer(EntityRendererManager renderManager) {
/*  34 */     super(renderManager);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(BottomHalfBodyEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/*  40 */     matrixStack.func_227860_a_();
/*     */     
/*  42 */     RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  43 */     matrixStack.func_227861_a_(0.0D, 1.5D, 0.0D);
/*  44 */     matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229183_f_, 180.0F, true));
/*  45 */     matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229181_d_, entity.field_70177_z + 180.0F, true));
/*     */     
/*  47 */     RenderSystem.enableBlend();
/*  48 */     RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
/*     */     
/*  50 */     if (entity.field_70737_aN > 0) {
/*     */       
/*  52 */       matrixStack.func_227860_a_();
/*  53 */       RenderSystem.color3f(1.0F, 0.0F, 0.0F);
/*  54 */       matrixStack.func_227865_b_();
/*     */     } 
/*     */     
/*  57 */     float headYawOffset = MathHelper.func_219805_h(partialTicks, entity.field_70760_ar, entity.field_70761_aq);
/*  58 */     float headYawRotation = MathHelper.func_219805_h(partialTicks, entity.field_70758_at, entity.field_70759_as);
/*  59 */     float netHeadYaw = headYawRotation - headYawOffset;
/*     */     
/*  61 */     float headPitch = MathHelper.func_219799_g(partialTicks, entity.field_70127_C, entity.field_70125_A);
/*  62 */     float f7 = entity.field_70173_aa + partialTicks;
/*     */     
/*  64 */     float limbSwingAmount = 0.0F;
/*  65 */     float limbSwing = 0.0F;
/*  66 */     if (entity.func_70089_S()) {
/*     */       
/*  68 */       limbSwingAmount = MathHelper.func_219799_g(partialTicks, entity.field_184618_aE, entity.field_70721_aZ);
/*  69 */       limbSwing = entity.field_184619_aG - entity.field_70721_aZ * (1.0F - partialTicks);
/*  70 */       if (entity.func_70631_g_())
/*     */       {
/*  72 */         limbSwing *= 3.0F;
/*     */       }
/*     */       
/*  75 */       if (limbSwingAmount > 1.0F)
/*     */       {
/*  77 */         limbSwingAmount = 1.0F;
/*     */       }
/*     */     } 
/*     */     
/*  81 */     ResourceLocation res = getTextureLocation(entity);
/*  82 */     Minecraft.func_71410_x().func_110434_K().func_110577_a(res);
/*  83 */     IVertexBuilder ivertexbuilder = buffer.getBuffer(this.model.func_228282_a_(res));
/*  84 */     this.model.field_217114_e = false;
/*  85 */     this.model.func_212843_a_((LivingEntity)entity, limbSwing, limbSwingAmount, partialTicks);
/*  86 */     this.model.func_225597_a_((LivingEntity)entity, limbSwing, limbSwingAmount, f7, netHeadYaw, headPitch);
/*  87 */     this.model.func_225598_a_(matrixStack, ivertexbuilder, packedLight, OverlayTexture.field_229196_a_, 1.0F, 1.0F, 1.0F, 1.0F);
/*  88 */     this.model.func_178719_a(false);
/*  89 */     this.model.field_178722_k.field_78806_j = true;
/*  90 */     this.model.field_178721_j.field_78806_j = true;
/*     */     
/*  92 */     matrixStack.func_227865_b_();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ResourceLocation getTextureLocation(BottomHalfBodyEntity entity) {
/*  98 */     if (entity.getOwnerUUID() == null)
/*  99 */       return DefaultPlayerSkin.func_177335_a(); 
/* 100 */     PlayerEntity player = entity.field_70170_p.func_217371_b(entity.getOwnerUUID());
/* 101 */     if (player != null) {
/* 102 */       return ((AbstractClientPlayerEntity)player).func_110306_p();
/*     */     }
/* 104 */     return DefaultPlayerSkin.func_177334_a(entity.getOwnerUUID());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class Factory
/*     */     implements IRenderFactory<BottomHalfBodyEntity>
/*     */   {
/*     */     public EntityRenderer<? super BottomHalfBodyEntity> createRenderFor(EntityRendererManager manager) {
/* 116 */       return new BottomHalfBodyRenderer(manager);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\abilities\BottomHalfBodyRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */