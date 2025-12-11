/*     */ package xyz.pixelatedw.mineminenomi.renderers.abilities;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.platform.GlStateManager;
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*     */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*     */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*     */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*     */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*     */ import net.minecraft.client.resources.DefaultPlayerSkin;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.vector.Quaternion;
/*     */ import net.minecraft.util.math.vector.Vector3f;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*     */ import xyz.pixelatedw.mineminenomi.entities.PhysicalBodyEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*     */ import xyz.pixelatedw.mineminenomi.models.abilities.PhysicalBodyModel;
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class PhysicalBodyRenderer
/*     */   extends EntityRenderer<PhysicalBodyEntity>
/*     */ {
/*  36 */   private PhysicalBodyModel model = new PhysicalBodyModel();
/*     */ 
/*     */   
/*     */   public PhysicalBodyRenderer(EntityRendererManager renderManager) {
/*  40 */     super(renderManager);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(PhysicalBodyEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/*  46 */     boolean isSkeleton = false;
/*  47 */     if (entity.getOwner() != null) {
/*     */       
/*  49 */       IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)entity.getOwner());
/*  50 */       isSkeleton = (devilFruitProps.hasDevilFruit(ModAbilities.YOMI_YOMI_NO_MI) && ((MorphInfo)ModMorphs.YOMI_SKELETON.get()).isActive((LivingEntity)entity.getOwner()));
/*     */     } 
/*     */     
/*  53 */     matrixStack.func_227860_a_();
/*     */     
/*  55 */     RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  56 */     matrixStack.func_227861_a_(0.0D, 1.5D, 0.0D);
/*  57 */     matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229183_f_, 180.0F, true));
/*  58 */     matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229181_d_, entity.field_70125_A + 180.0F, true));
/*     */     
/*  60 */     RenderSystem.enableBlend();
/*  61 */     RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
/*     */     
/*  63 */     if (entity.field_70737_aN > 0) {
/*     */       
/*  65 */       matrixStack.func_227860_a_();
/*  66 */       RenderSystem.color3f(1.0F, 0.0F, 0.0F);
/*  67 */       matrixStack.func_227865_b_();
/*     */     } 
/*     */     
/*  70 */     ResourceLocation res = getTextureLocation(entity);
/*  71 */     if (isSkeleton) {
/*     */       
/*  73 */       res = new ResourceLocation("mineminenomi", "textures/models/zoanmorph/yomi_skeleton.png");
/*  74 */       Minecraft.func_71410_x().func_110434_K().func_110577_a(res);
/*     */     }
/*     */     else {
/*     */       
/*  78 */       res = getTextureLocation(entity);
/*  79 */       Minecraft.func_71410_x().func_110434_K().func_110577_a(res);
/*     */     } 
/*  81 */     IVertexBuilder ivertexbuilder = buffer.getBuffer(this.model.func_228282_a_(res));
/*  82 */     this.model.func_225598_a_(matrixStack, ivertexbuilder, packedLight, OverlayTexture.field_229196_a_, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/*  84 */     matrixStack.func_227865_b_();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ResourceLocation getTextureLocation(PhysicalBodyEntity entity) {
/*  90 */     PlayerEntity player = entity.getOwner();
/*  91 */     if (player != null) {
/*  92 */       return ((AbstractClientPlayerEntity)player).func_110306_p();
/*     */     }
/*     */     
/*  95 */     UUID uuid = entity.getOwnerUUID();
/*  96 */     if (uuid != null) {
/*  97 */       return DefaultPlayerSkin.func_177334_a(uuid);
/*     */     }
/*  99 */     return DefaultPlayerSkin.func_177335_a();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class Factory
/*     */     implements IRenderFactory<PhysicalBodyEntity>
/*     */   {
/*     */     public EntityRenderer<? super PhysicalBodyEntity> createRenderFor(EntityRendererManager manager) {
/* 112 */       return new PhysicalBodyRenderer(manager);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\abilities\PhysicalBodyRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */