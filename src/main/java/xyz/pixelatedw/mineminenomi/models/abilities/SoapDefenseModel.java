/*    */ package xyz.pixelatedw.mineminenomi.models.abilities;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.client.renderer.model.Model;
/*    */ import net.minecraft.client.renderer.model.ModelRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ 
/*    */ public class SoapDefenseModel
/*    */   extends EntityModel
/*    */ {
/*    */   public ModelRenderer shape1;
/*    */   public ModelRenderer shape2;
/*    */   public ModelRenderer shape3;
/*    */   
/*    */   public SoapDefenseModel() {
/* 18 */     this.field_78090_t = 128;
/* 19 */     this.field_78089_u = 128;
/* 20 */     this.shape1 = new ModelRenderer((Model)this, 0, 0);
/* 21 */     this.shape1.func_78793_a(0.0F, 9.0F, 0.0F);
/* 22 */     this.shape1.func_228301_a_(-10.0F, -15.0F, -7.0F, 20.0F, 30.0F, 15.0F, 0.0F);
/* 23 */     this.shape2 = new ModelRenderer((Model)this, 0, 83);
/* 24 */     this.shape2.func_78793_a(0.0F, -1.0F, 0.0F);
/* 25 */     this.shape2.func_228301_a_(-9.0F, -15.0F, -6.0F, 18.0F, 32.0F, 13.0F, 0.0F);
/* 26 */     this.shape3 = new ModelRenderer((Model)this, 56, 47);
/* 27 */     this.shape3.func_78793_a(0.0F, 0.0F, 0.0F);
/* 28 */     this.shape3.func_228301_a_(-11.0F, -14.0F, -6.0F, 22.0F, 28.0F, 13.0F, 0.0F);
/* 29 */     this.shape1.func_78792_a(this.shape2);
/* 30 */     this.shape1.func_78792_a(this.shape3);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 36 */     matrixStack.func_227860_a_();
/* 37 */     matrixStack.func_227862_a_(1.2F, 1.2F, 1.2F);
/* 38 */     this.shape1.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 39 */     matrixStack.func_227865_b_();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225597_a_(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
/* 49 */     model.field_78795_f = x;
/* 50 */     model.field_78796_g = y;
/* 51 */     model.field_78808_h = z;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\abilities\SoapDefenseModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */