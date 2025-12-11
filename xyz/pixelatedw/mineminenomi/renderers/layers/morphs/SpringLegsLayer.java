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
/*    */ import xyz.pixelatedw.mineminenomi.init.ModKeybindings;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*    */ import xyz.pixelatedw.mineminenomi.models.morphs.SpringLegsPartialModel;
/*    */ 
/*    */ public class SpringLegsLayer<T extends LivingEntity, M extends EntityModel<T>>
/*    */   extends LayerRenderer<T, M>
/*    */ {
/* 20 */   private SpringLegsPartialModel model = new SpringLegsPartialModel();
/* 21 */   private ResourceLocation texture = new ResourceLocation("mineminenomi", "textures/models/zoanmorph/spring_legs.png");
/*    */   
/*    */   private float renderingTicks;
/*    */   
/*    */   public SpringLegsLayer(IEntityRenderer renderer) {
/* 26 */     super(renderer);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
/* 32 */     if (entity.func_82150_aj()) {
/*    */       return;
/*    */     }
/*    */     
/* 36 */     RenderType renderType = ModRenderTypes.getZoanRenderType(this.texture);
/* 37 */     this.model.field_228270_o_ = entity.func_213453_ef();
/*    */     
/* 39 */     int sum = 0;
/* 40 */     for (int i = 0; i < 5; i++) {
/*    */       
/* 42 */       if (!((LivingEntity)entity).field_70170_p.func_175623_d(entity.func_233580_cy_().func_177979_c(i)))
/* 43 */         sum++; 
/*    */     } 
/* 45 */     boolean isSolid = (sum >= 2);
/*    */     
/* 47 */     matrixStack.func_227860_a_();
/* 48 */     if (!entity.func_233570_aj_()) {
/*    */       
/* 50 */       limbSwing /= 4.0F;
/* 51 */       limbSwingAmount /= 4.0F;
/* 52 */       if (ModKeybindings.isSpaceKeyDown() && (entity.func_213322_ci()).field_72448_b > 0.3D && isSolid) {
/*    */         
/* 54 */         matrixStack.func_227861_a_(0.0D, -0.7300000190734863D, 0.0D);
/* 55 */         matrixStack.func_227862_a_(1.0F, 2.0F, 1.0F);
/*    */       } 
/*    */     } 
/*    */     
/* 59 */     this.model.func_225597_a_((LivingEntity)entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/* 60 */     this.model.func_225598_a_(matrixStack, buffer.getBuffer(renderType), packedLight, OverlayTexture.field_229196_a_, 1.0F, 1.0F, 1.0F, 1.0F);
/* 61 */     matrixStack.func_227865_b_();
/*    */   }
/*    */ 
/*    */   
/*    */   public SpringLegsPartialModel getPartialModel() {
/* 66 */     return this.model;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\layers\morphs\SpringLegsLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */