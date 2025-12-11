/*    */ package xyz.pixelatedw.mineminenomi.renderers.layers.morphs;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*    */ import xyz.pixelatedw.mineminenomi.models.abilities.DamnedPunkModel;
/*    */ 
/*    */ public class DamnedPunkLayer<T extends LivingEntity, M extends EntityModel<T>>
/*    */   extends LayerRenderer<T, M>
/*    */ {
/* 19 */   private DamnedPunkModel model = new DamnedPunkModel();
/* 20 */   public static final ResourceLocation TEXTURE = new ResourceLocation("mineminenomi", "textures/models/zoanmorph/damned_punk.png");
/*    */ 
/*    */   
/*    */   public DamnedPunkLayer(IEntityRenderer renderer) {
/* 24 */     super(renderer);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
/* 30 */     if (entity.func_82150_aj()) {
/*    */       return;
/*    */     }
/*    */     
/* 34 */     BipedModel ogModel = (BipedModel)func_215332_c();
/*    */     
/* 36 */     matrixStack.func_227860_a_();
/*    */     
/* 38 */     matrixStack.func_227861_a_(0.0D, 0.0D, -0.1D);
/* 39 */     this.model.rightArm.func_217177_a(ogModel.field_178723_h);
/* 40 */     RenderType renderType = ModRenderTypes.getZoanWithCullingRenderType(TEXTURE);
/* 41 */     this.model.func_225598_a_(matrixStack, buffer.getBuffer(renderType), packedLight, packedLight, packedLight, ((LivingEntity)entity).field_70177_z, partialTicks, packedLight);
/*    */     
/* 43 */     matrixStack.func_227865_b_();
/*    */   }
/*    */ 
/*    */   
/*    */   public DamnedPunkModel getPartialModel() {
/* 48 */     return this.model;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\layers\morphs\DamnedPunkLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */