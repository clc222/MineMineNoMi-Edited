/*    */ package xyz.pixelatedw.mineminenomi.renderers.layers;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*    */ import net.minecraft.entity.Entity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.buggypirates.CabajiEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids.HumanoidModel;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.vehicles.UnicycleModel;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.entities.UnicycleRenderer;
/*    */ 
/*    */ public class CabajiUnicycleLayer<E extends CabajiEntity, M extends HumanoidModel<E>> extends LayerRenderer<E, M> {
/* 17 */   private UnicycleModel unicycle = new UnicycleModel();
/*    */   
/*    */   public CabajiUnicycleLayer(IEntityRenderer renderer) {
/* 20 */     super(renderer);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, E entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
/* 27 */     if (entity.hasUnicycle()) {
/* 28 */       matrixStack.func_227860_a_();
/* 29 */       matrixStack.func_227861_a_(0.0D, 0.3D, 0.0D);
/* 30 */       RenderType renderType = ModRenderTypes.getZoanRenderType(UnicycleRenderer.TEXTURE);
/* 31 */       this.unicycle.func_225597_a_((Entity)entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/* 32 */       this.unicycle.func_225598_a_(matrixStack, buffer.getBuffer(renderType), packedLight, OverlayTexture.field_229196_a_, 1.0F, 1.0F, 1.0F, 1.0F);
/* 33 */       matrixStack.func_227865_b_();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\layers\CabajiUnicycleLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */