/*    */ package xyz.pixelatedw.mineminenomi.integrations.curios.renderers;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.ItemRenderer;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import top.theillusivec4.curios.api.type.capability.ICurio;
/*    */ 
/*    */ public class SimpleCurioRenderer implements ICurioRenderer {
/*    */   private final ResourceLocation texture;
/*    */   private final BipedModel<LivingEntity> model;
/*    */   
/*    */   public SimpleCurioRenderer(ResourceLocation texture, BipedModel<LivingEntity> model) {
/* 20 */     this.texture = texture;
/* 21 */     this.model = model;
/*    */   }
/*    */   
/*    */   protected ResourceLocation getTexture() {
/* 25 */     return this.texture;
/*    */   }
/*    */   
/*    */   protected BipedModel<LivingEntity> getModel() {
/* 29 */     return this.model;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(String identifier, int index, MatrixStack matrixStack, IRenderTypeBuffer buffer, int light, LivingEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, ItemStack stack) {
/* 35 */     BipedModel<LivingEntity> model = getModel();
/* 36 */     model.func_225597_a_(entity, limbSwing, limbSwingAmount, partialTicks, netHeadYaw, headPitch);
/* 37 */     model.func_212843_a_(entity, limbSwing, limbSwingAmount, partialTicks);
/* 38 */     ICurio.RenderHelper.followBodyRotations(entity, new BipedModel[] { model });
/*    */     
/* 40 */     boolean hasGlint = stack.func_77962_s();
/* 41 */     RenderType renderType = this.model.func_228282_a_(getTexture());
/* 42 */     IVertexBuilder vertexBuilder = ItemRenderer.func_239386_a_(buffer, renderType, false, hasGlint);
/* 43 */     this.model.func_225598_a_(matrixStack, vertexBuilder, light, OverlayTexture.field_229196_a_, 1.0F, 1.0F, 1.0F, 1.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\integrations\curios\renderers\SimpleCurioRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */