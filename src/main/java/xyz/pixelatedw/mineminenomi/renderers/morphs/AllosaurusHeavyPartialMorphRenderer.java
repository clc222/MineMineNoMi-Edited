/*    */ package xyz.pixelatedw.mineminenomi.renderers.morphs;
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.client.renderer.entity.model.PlayerModel;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphModel;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.layers.AuraLayer;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.layers.morphs.AllosaurusHeavyLayer;
/*    */ 
/*    */ @OnlyIn(Dist.CLIENT)
/*    */ public class AllosaurusHeavyPartialMorphRenderer<T extends AbstractClientPlayerEntity, M extends MorphModel> extends ZoanMorphRenderer<T, M> {
/*    */   public AllosaurusHeavyPartialMorphRenderer(EntityRendererManager renderManager, MorphInfo info, boolean hasSmallHands) {
/* 24 */     super(renderManager, info, hasSmallHands);
/* 25 */     removeLayer((Class)HeldItemLayer.class);
/* 26 */     AllosaurusHeavyLayer partialRenderer = new AllosaurusHeavyLayer((IEntityRenderer)this);
/* 27 */     func_177094_a((LayerRenderer)partialRenderer);
/* 28 */     func_177094_a((LayerRenderer)new HeldItemLayer((IEntityRenderer)this));
/* 29 */     func_177094_a((LayerRenderer)new AuraLayer((IEntityRenderer)this));
/* 30 */     removeLayer((Class)BodyCoatingLayer.class);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225623_a_(AbstractClientPlayerEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 36 */     ((PlayerModel)func_217764_d()).field_178721_j.field_78806_j = false;
/* 37 */     ((PlayerModel)func_217764_d()).field_178722_k.field_78806_j = false;
/*    */     
/* 39 */     ((PlayerModel)func_217764_d()).field_178733_c.field_78806_j = false;
/* 40 */     ((PlayerModel)func_217764_d()).field_178731_d.field_78806_j = false;
/*    */     
/* 42 */     super.func_225623_a_(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void renderModel(AbstractClientPlayerEntity entity, MatrixStack matrixStack, int packedLight, IRenderTypeBuffer buffer, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
/* 48 */     matrixStack.func_227860_a_();
/* 49 */     float scale = 1.1F;
/* 50 */     matrixStack.func_227862_a_(scale, scale, scale);
/* 51 */     matrixStack.func_227861_a_(0.0D, -0.18D, 0.0D);
/* 52 */     super.renderModel(entity, matrixStack, packedLight, buffer, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch);
/* 53 */     matrixStack.func_227865_b_();
/*    */   }
/*    */   
/*    */   public static class Factory<T extends PlayerEntity>
/*    */     implements IRenderFactory<T>
/*    */   {
/*    */     private MorphInfo info;
/*    */     private boolean hasSmallHands;
/*    */     
/*    */     public Factory(MorphInfo info, boolean hasSmallHands) {
/* 63 */       this.info = info;
/* 64 */       this.hasSmallHands = hasSmallHands;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public EntityRenderer<? super T> createRenderFor(EntityRendererManager manager) {
/* 70 */       return (EntityRenderer)new AllosaurusHeavyPartialMorphRenderer<>(manager, this.info, this.hasSmallHands);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\morphs\AllosaurusHeavyPartialMorphRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */