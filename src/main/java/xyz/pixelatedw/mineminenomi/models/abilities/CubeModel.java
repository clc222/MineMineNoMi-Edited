/*    */ package xyz.pixelatedw.mineminenomi.models.abilities;
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
/*    */ public class CubeModel
/*    */   extends EntityModel
/*    */ {
/*    */   public ModelRenderer shape1;
/*    */   
/*    */   public CubeModel() {
/* 19 */     this.field_78090_t = 64;
/* 20 */     this.field_78089_u = 64;
/*    */     
/* 22 */     this.shape1 = new ModelRenderer((Model)this);
/* 23 */     this.shape1.func_78793_a(0.0F, 0.0F, 0.0F);
/* 24 */     this.shape1.func_78784_a(0, 0).func_228303_a_(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, true);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225597_a_(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 36 */     this.shape1.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*    */   }
/*    */ 
/*    */   
/*    */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 41 */     model.field_78795_f = x;
/* 42 */     model.field_78796_g = y;
/* 43 */     model.field_78808_h = z;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\abilities\CubeModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */