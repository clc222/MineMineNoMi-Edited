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
/*    */ public class MinkBunnyPartialModel<T extends LivingEntity>
/*    */   extends MorphModel<T> {
/*    */   private final ModelRenderer ears;
/*    */   private final ModelRenderer rightEar1;
/*    */   private final ModelRenderer rightEar2;
/*    */   private final ModelRenderer leftEar1;
/*    */   private final ModelRenderer leftEar2;
/*    */   private final ModelRenderer tail;
/*    */   
/*    */   public MinkBunnyPartialModel() {
/* 22 */     super(1.0F);
/* 23 */     this.field_78090_t = 32;
/* 24 */     this.field_78089_u = 32;
/*    */     
/* 26 */     this.ears = new ModelRenderer((Model)this);
/* 27 */     this.ears.func_78793_a(0.0F, 8.0F, 0.0F);
/*    */ 
/*    */     
/* 30 */     this.rightEar1 = new ModelRenderer((Model)this);
/* 31 */     this.rightEar1.func_78793_a(-2.0F, -8.0F, 0.0F);
/* 32 */     this.ears.func_78792_a(this.rightEar1);
/* 33 */     setRotationAngle(this.rightEar1, 0.0F, -0.2182F, -0.2182F);
/* 34 */     this.rightEar1.func_78784_a(0, 25).func_228303_a_(-2.0F, -5.0F, 0.0F, 3.0F, 5.0F, 1.0F, 0.0F, false);
/*    */     
/* 36 */     this.rightEar2 = new ModelRenderer((Model)this);
/* 37 */     this.rightEar2.func_78793_a(-0.5315F, -4.4739F, 0.1179F);
/* 38 */     this.rightEar1.func_78792_a(this.rightEar2);
/* 39 */     setRotationAngle(this.rightEar2, 0.2967F, 0.0F, 0.0F);
/* 40 */     this.rightEar2.func_78784_a(0, 16).func_228303_a_(-1.4685F, -5.2761F, -0.1179F, 3.0F, 5.0F, 1.0F, 0.0F, false);
/*    */     
/* 42 */     this.leftEar1 = new ModelRenderer((Model)this);
/* 43 */     this.leftEar1.func_78793_a(2.0F, -8.0F, 0.0F);
/* 44 */     this.ears.func_78792_a(this.leftEar1);
/* 45 */     setRotationAngle(this.leftEar1, 0.0F, 0.2182F, 0.2182F);
/* 46 */     this.leftEar1.func_78784_a(0, 25).func_228303_a_(-1.0F, -5.0F, 0.0F, 3.0F, 5.0F, 1.0F, 0.0F, false);
/*    */     
/* 48 */     this.leftEar2 = new ModelRenderer((Model)this);
/* 49 */     this.leftEar2.func_78793_a(0.717F, -4.7721F, 0.159F);
/* 50 */     this.leftEar1.func_78792_a(this.leftEar2);
/* 51 */     setRotationAngle(this.leftEar2, 0.2967F, 0.0F, 0.0F);
/* 52 */     this.leftEar2.func_78784_a(0, 16).func_228303_a_(-1.717F, -4.9779F, -0.159F, 3.0F, 5.0F, 1.0F, 0.0F, false);
/*    */     
/* 54 */     this.tail = new ModelRenderer((Model)this);
/* 55 */     this.tail.func_78793_a(0.0F, 3.0F, 1.0F);
/* 56 */     this.tail.func_78784_a(0, 0).func_228303_a_(-3.0F, 7.0F, 2.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 62 */     this.ears.func_217177_a(this.field_78116_c);
/* 63 */     this.tail.func_217177_a(this.field_78115_e);
/* 64 */     this.ears.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
/* 65 */     this.tail.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 71 */     super.func_225597_a_((LivingEntity)entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/*    */   }
/*    */ 
/*    */   
/*    */   public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*    */ 
/*    */   
/*    */   public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*    */ 
/*    */   
/*    */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 82 */     modelRenderer.field_78795_f = x;
/* 83 */     modelRenderer.field_78796_g = y;
/* 84 */     modelRenderer.field_78808_h = z;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\morphs\MinkBunnyPartialModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */