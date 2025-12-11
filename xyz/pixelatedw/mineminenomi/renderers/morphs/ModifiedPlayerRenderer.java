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
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.entity.model.PlayerModel;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphModel;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*    */ 
/*    */ public class ModifiedPlayerRenderer<T extends AbstractClientPlayerEntity, M extends MorphModel> extends ZoanMorphRenderer<T, M> {
/* 23 */   private double size = 1.0D;
/*    */ 
/*    */   
/*    */   public ModifiedPlayerRenderer(EntityRendererManager rendererManager, MorphInfo info, boolean hasSmallHands, double size) {
/* 27 */     super(rendererManager, info, hasSmallHands);
/* 28 */     this.size = size;
/* 29 */     if (this.size == 0.0D)
/* 30 */       this.size = 1.0D; 
/* 31 */     func_177094_a((LayerRenderer)new BipedArmorLayer((IEntityRenderer)this, new BipedModel((float)size), new BipedModel((float)size)));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225623_a_(AbstractClientPlayerEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 37 */     super.func_225623_a_(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void renderModel(AbstractClientPlayerEntity entity, MatrixStack matrixStack, int packedLight, IRenderTypeBuffer buffer, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
/* 43 */     ((PlayerModel)this.field_77045_g).func_212843_a_((LivingEntity)entity, limbSwing, limbSwingAmount, partialTicks);
/* 44 */     ((PlayerModel)this.field_77045_g).func_225597_a_((LivingEntity)entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/*    */     
/* 46 */     boolean shouldSit = (entity.func_184218_aH() && entity.func_184187_bx() != null && entity.func_184187_bx().shouldRiderSit());
/* 47 */     if (shouldSit) {
/* 48 */       matrixStack.func_227861_a_(0.0D, -2.5D, 0.0D);
/*    */     }
/* 50 */     boolean flag = func_225622_a_((LivingEntity)entity);
/* 51 */     boolean flag1 = (!flag && !entity.func_98034_c((PlayerEntity)(Minecraft.func_71410_x()).field_71439_g));
/* 52 */     RenderType renderType = ModRenderTypes.getZoanRenderType(func_110775_a(entity));
/* 53 */     if (renderType != null && flag) {
/*    */       
/* 55 */       IVertexBuilder ivertexbuilder = buffer.getBuffer(renderType);
/* 56 */       int i = func_229117_c_((LivingEntity)entity, func_225625_b_((LivingEntity)entity, partialTicks));
/* 57 */       ((PlayerModel)this.field_77045_g).func_225598_a_(matrixStack, ivertexbuilder, packedLight, i, 1.0F, 1.0F, 1.0F, flag1 ? 0.15F : 1.0F);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void func_225620_a_(AbstractClientPlayerEntity entitylivingbase, MatrixStack matrixStack, float partialTickTime) {
/* 64 */     matrixStack.func_227862_a_((float)this.size, (float)this.size, (float)this.size);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation func_110775_a(AbstractClientPlayerEntity entity) {
/* 70 */     return entity.func_110306_p();
/*    */   }
/*    */ 
/*    */   
/*    */   public static class Factory<T extends PlayerEntity>
/*    */     implements IRenderFactory<T>
/*    */   {
/*    */     private MorphInfo info;
/*    */     private boolean hasSmallHands;
/*    */     private double size;
/*    */     
/*    */     public Factory(MorphInfo info, boolean hasSmallHands, double size) {
/* 82 */       this.size = size;
/* 83 */       this.info = info;
/* 84 */       this.hasSmallHands = hasSmallHands;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public EntityRenderer<? super T> createRenderFor(EntityRendererManager manager) {
/* 90 */       ModifiedPlayerRenderer<AbstractClientPlayerEntity, MorphModel> renderer = new ModifiedPlayerRenderer<>(manager, this.info, this.hasSmallHands, this.size);
/* 91 */       return (EntityRenderer)renderer;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\morphs\ModifiedPlayerRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */