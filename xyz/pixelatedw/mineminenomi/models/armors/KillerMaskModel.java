/*    */ package xyz.pixelatedw.mineminenomi.models.armors;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.model.Model;
/*    */ import net.minecraft.client.renderer.model.ModelRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ 
/*    */ public class KillerMaskModel<T extends LivingEntity>
/*    */   extends BipedModel<T> {
/*    */   private final ModelRenderer mask;
/*    */   private final ModelRenderer rightEar;
/*    */   private final ModelRenderer leftEar;
/*    */   private final ModelRenderer bar;
/*    */   private final ModelRenderer bar2;
/*    */   private final ModelRenderer bar2_r1;
/*    */   private final ModelRenderer bar6;
/*    */   private final ModelRenderer bar6_r1;
/*    */   
/*    */   public KillerMaskModel() {
/* 23 */     super(1.0F);
/* 24 */     this.field_78090_t = 32;
/* 25 */     this.field_78089_u = 32;
/*    */     
/* 27 */     this.mask = new ModelRenderer((Model)this);
/* 28 */     this.mask.func_78793_a(0.0F, 0.0F, 0.0F);
/* 29 */     this.mask.func_78784_a(0, 0).func_228303_a_(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.5F, false);
/*    */     
/* 31 */     this.rightEar = new ModelRenderer((Model)this);
/* 32 */     this.rightEar.func_78793_a(-5.0F, -3.5F, 0.25F);
/* 33 */     this.mask.func_78792_a(this.rightEar);
/* 34 */     this.rightEar.func_78784_a(0, 0).func_228303_a_(-0.25F, -1.75F, -1.0F, 1.0F, 3.0F, 3.0F, 0.0F, false);
/* 35 */     this.rightEar.func_78784_a(0, 16).func_228303_a_(-0.75F, -1.25F, -0.5F, 1.0F, 2.0F, 2.0F, 0.0F, false);
/*    */     
/* 37 */     this.leftEar = new ModelRenderer((Model)this);
/* 38 */     this.leftEar.func_78793_a(4.5F, -3.5F, 0.25F);
/* 39 */     this.mask.func_78792_a(this.leftEar);
/* 40 */     this.leftEar.func_78784_a(0, 0).func_228303_a_(-0.25F, -1.75F, -1.0F, 1.0F, 3.0F, 3.0F, 0.0F, false);
/* 41 */     this.leftEar.func_78784_a(0, 16).func_228303_a_(0.25F, -1.25F, -0.5F, 1.0F, 2.0F, 2.0F, 0.0F, false);
/*    */     
/* 43 */     this.bar = new ModelRenderer((Model)this);
/* 44 */     this.bar.func_78793_a(0.25F, -1.0F, -0.5F);
/* 45 */     this.mask.func_78792_a(this.bar);
/* 46 */     this.bar.func_78784_a(0, 30).func_228303_a_(-7.25F, -3.25F, 0.75F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/* 47 */     this.bar.func_78784_a(0, 30).func_228303_a_(4.75F, -3.25F, 0.75F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/* 48 */     this.bar.func_78784_a(0, 27).func_228303_a_(-7.25F, -2.0F, 1.7F, 1.0F, 1.0F, 4.0F, 0.02F, false);
/* 49 */     this.bar.func_78784_a(0, 28).func_228303_a_(5.75F, -2.0F, 1.7F, 1.0F, 1.0F, 4.0F, 0.02F, false);
/* 50 */     this.bar.func_78784_a(0, 26).func_228303_a_(-7.25F, -2.0F, 5.7F, 14.0F, 1.0F, 1.0F, 0.01F, false);
/*    */     
/* 52 */     this.bar2 = new ModelRenderer((Model)this);
/* 53 */     this.bar2.func_78793_a(-6.75F, -1.7932F, 1.5548F);
/* 54 */     this.bar.func_78792_a(this.bar2);
/*    */     
/* 56 */     this.bar2_r1 = new ModelRenderer((Model)this);
/* 57 */     this.bar2_r1.func_78793_a(0.0F, 0.0F, 0.0F);
/* 58 */     this.bar2.func_78792_a(this.bar2_r1);
/* 59 */     setRotationAngle(this.bar2_r1, 0.4363F, 0.0F, 0.0F);
/* 60 */     this.bar2_r1.func_78784_a(0, 28).func_228303_a_(-0.5F, -1.25F, -0.25F, 1.0F, 2.0F, 1.0F, 0.01F, false);
/*    */     
/* 62 */     this.bar6 = new ModelRenderer((Model)this);
/* 63 */     this.bar6.func_78793_a(0.0F, 0.0F, 0.0F);
/* 64 */     this.bar.func_78792_a(this.bar6);
/*    */     
/* 66 */     this.bar6_r1 = new ModelRenderer((Model)this);
/* 67 */     this.bar6_r1.func_78793_a(-6.75F, -1.7932F, 1.5548F);
/* 68 */     this.bar6.func_78792_a(this.bar6_r1);
/* 69 */     setRotationAngle(this.bar6_r1, 0.4363F, 0.0F, 0.0F);
/* 70 */     this.bar6_r1.func_78784_a(0, 28).func_228303_a_(12.5F, -1.25F, -0.25F, 1.0F, 2.0F, 1.0F, 0.01F, false);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 76 */     this.mask.func_217177_a(this.field_78116_c);
/* 77 */     this.mask.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 87 */     modelRenderer.field_78795_f = x;
/* 88 */     modelRenderer.field_78796_g = y;
/* 89 */     modelRenderer.field_78808_h = z;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\armors\KillerMaskModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */