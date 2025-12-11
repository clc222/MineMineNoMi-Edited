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
/*    */ public class SphereModel<T extends Entity>
/*    */   extends EntityModel<T>
/*    */ {
/*    */   public final ModelRenderer shape1;
/*    */   private final ModelRenderer shape4;
/*    */   private final ModelRenderer shape2;
/*    */   private final ModelRenderer shape6;
/*    */   
/*    */   public SphereModel() {
/* 22 */     this.field_78090_t = 64;
/* 23 */     this.field_78089_u = 64;
/*    */     
/* 25 */     this.shape1 = new ModelRenderer((Model)this);
/* 26 */     this.shape1.func_78793_a(0.0F, 0.0F, 0.0F);
/* 27 */     this.shape1.func_78784_a(19, 0).func_228303_a_(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);
/*    */     
/* 29 */     this.shape4 = new ModelRenderer((Model)this);
/* 30 */     this.shape4.func_78793_a(0.0F, 0.0F, 0.25F);
/* 31 */     this.shape1.func_78792_a(this.shape4);
/* 32 */     this.shape4.func_78784_a(0, 0).func_228303_a_(-2.0F, -2.0F, -2.75F, 4.0F, 4.0F, 5.0F, -0.3F, false);
/*    */     
/* 34 */     this.shape2 = new ModelRenderer((Model)this);
/* 35 */     this.shape2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 36 */     this.shape1.func_78792_a(this.shape2);
/* 37 */     this.shape2.func_78784_a(15, 15).func_228303_a_(-2.0F, -2.5F, -2.0F, 4.0F, 5.0F, 4.0F, -0.3F, false);
/*    */     
/* 39 */     this.shape6 = new ModelRenderer((Model)this);
/* 40 */     this.shape6.func_78793_a(0.0F, 0.0F, 0.0F);
/* 41 */     this.shape1.func_78792_a(this.shape6);
/* 42 */     this.shape6.func_78784_a(0, 10).func_228303_a_(-2.5F, -2.0F, -2.0F, 5.0F, 4.0F, 4.0F, -0.3F, false);
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
/*    */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
/* 54 */     this.shape1.func_228309_a_(matrixStack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
/* 59 */     model.field_78795_f = x;
/* 60 */     model.field_78796_g = y;
/* 61 */     model.field_78808_h = z;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\abilities\SphereModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */