/*    */ package xyz.pixelatedw.mineminenomi.renderers.layers.morphs;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*    */ import xyz.pixelatedw.mineminenomi.models.morphs.AllosaurusHeavyPartialModel;
/*    */ 
/*    */ public class AllosaurusHeavyLayer<T extends LivingEntity, M extends EntityModel<T>>
/*    */   extends LayerRenderer<T, M>
/*    */ {
/* 20 */   private AllosaurusHeavyPartialModel model = new AllosaurusHeavyPartialModel();
/* 21 */   private ResourceLocation texture = new ResourceLocation("mineminenomi", "textures/models/zoanmorph/allosaurus_heavy.png");
/*    */ 
/*    */   
/*    */   public AllosaurusHeavyLayer(IEntityRenderer renderer) {
/* 25 */     super(renderer);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
/* 31 */     if (entity.func_82150_aj()) {
/*    */       return;
/*    */     }
/* 34 */     BipedModel ogModel = (BipedModel)func_215332_c();
/*    */     
/* 36 */     this.model.bodyScales.func_217177_a(ogModel.field_78115_e);
/* 37 */     this.model.headScales.func_217177_a(ogModel.field_78116_c);
/* 38 */     this.model.rightArmScales.func_217177_a(ogModel.field_178723_h);
/* 39 */     this.model.leftArmScales.func_217177_a(ogModel.field_178724_i);
/*    */     
/* 41 */     RenderType renderType = ModRenderTypes.getZoanWithCullingRenderType(this.texture);
/* 42 */     this.model.field_228270_o_ = entity.func_213453_ef();
/* 43 */     this.model.func_225597_a_((LivingEntity)entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/* 44 */     this.model.func_225598_a_(matrixStack, buffer.getBuffer(renderType), packedLight, OverlayTexture.field_229196_a_, 1.0F, 1.0F, 1.0F, 1.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public AllosaurusHeavyPartialModel getPartialModel() {
/* 49 */     return this.model;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\layers\morphs\AllosaurusHeavyLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */