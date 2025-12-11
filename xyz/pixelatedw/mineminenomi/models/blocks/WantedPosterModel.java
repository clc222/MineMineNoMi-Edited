/*    */ package xyz.pixelatedw.mineminenomi.models.blocks;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.client.renderer.model.Model;
/*    */ import net.minecraft.client.renderer.model.ModelRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ 
/*    */ @OnlyIn(Dist.CLIENT)
/*    */ public class WantedPosterModel
/*    */   extends EntityModel
/*    */ {
/*    */   public ModelRenderer poster;
/*    */   
/*    */   public WantedPosterModel() {
/* 19 */     this.field_78090_t = 64;
/* 20 */     this.field_78089_u = 64;
/* 21 */     this.poster = new ModelRenderer((Model)this, 0, 0);
/* 22 */     this.poster.func_78793_a(10.0F, 15.0F, 0.0F);
/* 23 */     this.poster.func_228301_a_(-10.0F, -15.0F, 0.0F, 20.0F, 30.0F, 0.0F, 1.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225598_a_(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
/* 29 */     this.poster.func_228309_a_(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225597_a_(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 40 */     modelRenderer.field_78795_f = x;
/* 41 */     modelRenderer.field_78796_g = y;
/* 42 */     modelRenderer.field_78808_h = z;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\blocks\WantedPosterModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */