/*    */ package xyz.pixelatedw.mineminenomi.renderers.layers.armor;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*    */ import xyz.pixelatedw.mineminenomi.models.armors.Mr3HairModel;
/*    */ 
/*    */ public class Mr3HairLayer<T extends LivingEntity, M extends BipedModel<T>>
/*    */   extends LayerRenderer<T, M> {
/* 18 */   private final Mr3HairModel hairModel = new Mr3HairModel();
/* 19 */   private static final ResourceLocation TEXTURE = new ResourceLocation("mineminenomi", "textures/models/armor/mr3_hair.png");
/*    */   
/*    */   public Mr3HairLayer(IEntityRenderer<T, M> renderer) {
/* 22 */     super(renderer);
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
/* 27 */     RenderType renderType = ModRenderTypes.getZoanRenderType(TEXTURE);
/* 28 */     ((BipedModel)func_215332_c()).func_217148_a((BipedModel)this.hairModel);
/* 29 */     this.hairModel.func_225597_a_((LivingEntity)entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/* 30 */     this.hairModel.func_225598_a_(matrixStack, buffer.getBuffer(renderType), packedLight, OverlayTexture.field_229196_a_, 1.0F, 1.0F, 1.0F, 1.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\layers\armor\Mr3HairLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */