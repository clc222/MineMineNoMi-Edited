/*    */ package xyz.pixelatedw.mineminenomi.renderers.morphs;
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.client.renderer.entity.model.PlayerModel;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphModel;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*    */ import xyz.pixelatedw.mineminenomi.models.morphs.NoMorphModel;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.layers.MinkFeaturesLayer;
/*    */ 
/*    */ public class MegaRenderer<T extends AbstractClientPlayerEntity, M extends MorphModel> extends ZoanMorphRenderer<T, M> {
/*    */   public MegaRenderer(EntityRendererManager rendererManager, MorphInfo info, boolean hasSmallHands) {
/* 28 */     super(rendererManager, info, hasSmallHands);
/* 29 */     this.field_77045_g = (EntityModel)new NoMorphModel(hasSmallHands);
/* 30 */     func_177094_a((LayerRenderer)new BipedArmorLayer((IEntityRenderer)this, new BipedModel(0.5F), new BipedModel(1.0F)));
/* 31 */     func_177094_a((LayerRenderer)new MinkFeaturesLayer((IEntityRenderer)this));
/* 32 */     func_177094_a((LayerRenderer)new GomuSmokeLayer((IEntityRenderer)this));
/* 33 */     func_177094_a((LayerRenderer)new GomuDawnWhipLayer((IEntityRenderer)this));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225623_a_(AbstractClientPlayerEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 39 */     super.func_225623_a_(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void renderModel(AbstractClientPlayerEntity entity, MatrixStack matrixStack, int packedLight, IRenderTypeBuffer buffer, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
/* 45 */     ((PlayerModel)this.field_77045_g).func_212843_a_((LivingEntity)entity, limbSwing, limbSwingAmount, partialTicks);
/* 46 */     ((PlayerModel)this.field_77045_g).func_225597_a_((LivingEntity)entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/*    */     
/* 48 */     boolean shouldSit = (entity.func_184218_aH() && entity.func_184187_bx() != null && entity.func_184187_bx().shouldRiderSit());
/* 49 */     if (shouldSit) {
/* 50 */       matrixStack.func_227861_a_(0.0D, -2.5D, 0.0D);
/*    */     }
/* 52 */     boolean flag = func_225622_a_((LivingEntity)entity);
/* 53 */     boolean flag1 = (!flag && !entity.func_98034_c((PlayerEntity)(Minecraft.func_71410_x()).field_71439_g));
/* 54 */     RenderType renderType = ModRenderTypes.getZoanRenderType(func_110775_a(entity));
/* 55 */     if (renderType != null && flag) {
/*    */       
/* 57 */       IVertexBuilder ivertexbuilder = buffer.getBuffer(renderType);
/* 58 */       int i = func_229117_c_((LivingEntity)entity, func_225625_b_((LivingEntity)entity, partialTicks));
/* 59 */       ((PlayerModel)this.field_77045_g).func_225598_a_(matrixStack, ivertexbuilder, packedLight, i, 1.0F, 1.0F, 1.0F, flag1 ? 0.15F : 1.0F);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void func_225620_a_(AbstractClientPlayerEntity entitylivingbase, MatrixStack matrixStack, float partialTickTime) {
/* 66 */     matrixStack.func_227862_a_(4.5F, 4.5F, 4.5F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation func_110775_a(AbstractClientPlayerEntity entity) {
/* 72 */     return entity.func_110306_p();
/*    */   }
/*    */   
/*    */   public static class Factory<T extends PlayerEntity>
/*    */     implements IRenderFactory<T>
/*    */   {
/*    */     private MorphInfo info;
/*    */     private boolean hasSmallHands;
/*    */     
/*    */     public Factory(MorphInfo info, boolean hasSmallHands) {
/* 82 */       this.info = info;
/* 83 */       this.hasSmallHands = hasSmallHands;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public EntityRenderer<? super T> createRenderFor(EntityRendererManager manager) {
/* 89 */       MegaRenderer<AbstractClientPlayerEntity, MorphModel> renderer = new MegaRenderer<>(manager, this.info, this.hasSmallHands);
/* 90 */       return (EntityRenderer)renderer;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\morphs\MegaRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */