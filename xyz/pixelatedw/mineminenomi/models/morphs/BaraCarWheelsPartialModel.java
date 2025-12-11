/*    */ package xyz.pixelatedw.mineminenomi.models.morphs;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.renderer.model.Model;
/*    */ import net.minecraft.client.renderer.model.ModelRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.HandSide;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphModel;
/*    */ 
/*    */ public class BaraCarWheelsPartialModel<T extends LivingEntity>
/*    */   extends MorphModel<T> {
/*    */   private final ModelRenderer frontWheels;
/*    */   private final ModelRenderer frontRightWheel;
/*    */   private final ModelRenderer frontLeftWheel;
/*    */   private final ModelRenderer backWheels;
/*    */   private final ModelRenderer backLeftWheel;
/*    */   private final ModelRenderer backRightWheel;
/*    */   
/*    */   public BaraCarWheelsPartialModel() {
/* 22 */     super(1.0F);
/* 23 */     this.field_78090_t = 128;
/* 24 */     this.field_78089_u = 128;
/*    */     
/* 26 */     this.frontWheels = new ModelRenderer((Model)this);
/* 27 */     this.frontWheels.func_78793_a(0.0F, 23.0F, -16.0F);
/* 28 */     this.frontWheels.func_78784_a(27, 12).func_228303_a_(-13.0F, -1.0F, -1.0F, 26.0F, 2.0F, 2.0F, 0.0F, false);
/*    */     
/* 30 */     this.frontRightWheel = new ModelRenderer((Model)this);
/* 31 */     this.frontRightWheel.func_78793_a(-13.0F, 0.0F, 0.0F);
/* 32 */     this.frontWheels.func_78792_a(this.frontRightWheel);
/* 33 */     this.frontRightWheel.func_78784_a(0, 42).func_228303_a_(-3.0F, -4.0F, -4.0F, 3.0F, 8.0F, 8.0F, 0.0F, false);
/*    */     
/* 35 */     this.frontLeftWheel = new ModelRenderer((Model)this);
/* 36 */     this.frontLeftWheel.func_78793_a(13.0F, 0.0F, 0.0F);
/* 37 */     this.frontWheels.func_78792_a(this.frontLeftWheel);
/* 38 */     this.frontLeftWheel.func_78784_a(0, 42).func_228303_a_(0.0F, -4.0F, -4.0F, 3.0F, 8.0F, 8.0F, 0.0F, false);
/*    */     
/* 40 */     this.backWheels = new ModelRenderer((Model)this);
/* 41 */     this.backWheels.func_78793_a(8.0F, 20.75F, 14.25F);
/* 42 */     this.backWheels.func_78784_a(0, 0).func_228303_a_(-21.0F, -2.75F, -3.25F, 26.0F, 6.0F, 6.0F, 0.0F, false);
/*    */     
/* 44 */     this.backLeftWheel = new ModelRenderer((Model)this);
/* 45 */     this.backLeftWheel.func_78793_a(5.0F, 0.0F, 0.0F);
/* 46 */     this.backWheels.func_78792_a(this.backLeftWheel);
/* 47 */     this.backLeftWheel.func_78784_a(0, 12).func_228303_a_(0.0F, -7.75F, -7.25F, 6.0F, 15.0F, 15.0F, 0.0F, false);
/*    */     
/* 49 */     this.backRightWheel = new ModelRenderer((Model)this);
/* 50 */     this.backRightWheel.func_78793_a(-21.0F, 0.0F, 0.0F);
/* 51 */     this.backWheels.func_78792_a(this.backRightWheel);
/* 52 */     this.backRightWheel.func_78784_a(0, 12).func_228303_a_(-6.0F, -7.75F, -7.25F, 6.0F, 15.0F, 15.0F, 0.0F, false);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 58 */     this.frontRightWheel.field_78795_f = limbSwing * 0.8F;
/* 59 */     this.frontLeftWheel.field_78795_f = limbSwing * 0.8F;
/* 60 */     this.backRightWheel.field_78795_f = limbSwing * 0.8F;
/* 61 */     this.backLeftWheel.field_78795_f = limbSwing * 0.8F;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 67 */     this.frontWheels.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/* 68 */     this.backWheels.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 73 */     modelRenderer.field_78795_f = x;
/* 74 */     modelRenderer.field_78796_g = y;
/* 75 */     modelRenderer.field_78808_h = z;
/*    */   }
/*    */   
/*    */   public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*    */   
/*    */   public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\morphs\BaraCarWheelsPartialModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */