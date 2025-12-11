/*    */ package xyz.pixelatedw.mineminenomi.renderers.layers.morphs;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*    */ import xyz.pixelatedw.mineminenomi.models.morphs.PteranodonAssaultPartialModel;
/*    */ 
/*    */ public class PteranodonAssaultLayer<T extends LivingEntity, M extends EntityModel<T>>
/*    */   extends LayerRenderer<T, M> {
/* 18 */   private PteranodonAssaultPartialModel model = new PteranodonAssaultPartialModel();
/* 19 */   private static final ResourceLocation TEXTURE = new ResourceLocation("mineminenomi", "textures/models/zoanmorph/pteranodon_assault.png");
/*    */   
/*    */   public PteranodonAssaultLayer(IEntityRenderer renderer) {
/* 22 */     super(renderer);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
/* 28 */     if (!entity.func_82150_aj()) {
/* 29 */       RenderType renderType = ModRenderTypes.getZoanRenderType(TEXTURE);
/* 30 */       this.model.field_228270_o_ = entity.func_213453_ef();
/* 31 */       this.model.func_225597_a_((LivingEntity)entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/* 32 */       this.model.func_225598_a_(matrixStack, buffer.getBuffer(renderType), packedLight, OverlayTexture.field_229196_a_, 1.0F, 1.0F, 1.0F, 1.0F);
/*    */     } 
/*    */   }
/*    */   
/*    */   public PteranodonAssaultPartialModel getPartialModel() {
/* 37 */     return this.model;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\layers\morphs\PteranodonAssaultLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */