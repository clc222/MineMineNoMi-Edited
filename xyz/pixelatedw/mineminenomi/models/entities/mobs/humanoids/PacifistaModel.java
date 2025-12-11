/*    */ package xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.model.Model;
/*    */ import net.minecraft.client.renderer.model.ModelRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ 
/*    */ public class PacifistaModel<T extends LivingEntity>
/*    */   extends BipedModel<T> {
/*    */   private final ModelRenderer head;
/*    */   private final ModelRenderer rightEar;
/*    */   private final ModelRenderer leftEar;
/*    */   private final ModelRenderer body;
/*    */   private final ModelRenderer rightArm;
/*    */   private final ModelRenderer leftArm;
/*    */   private final ModelRenderer rightLeg;
/*    */   private final ModelRenderer leftLeg;
/*    */   
/*    */   public PacifistaModel() {
/* 23 */     super(1.0F);
/* 24 */     this.field_78090_t = 64;
/* 25 */     this.field_78089_u = 64;
/*    */     
/* 27 */     this.head = new ModelRenderer((Model)this);
/* 28 */     this.head.func_78793_a(0.0F, -3.0F, -0.25F);
/* 29 */     this.head.func_78784_a(0, 0).func_228303_a_(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
/* 30 */     this.head.func_78784_a(32, 0).func_228303_a_(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.5F, false);
/*    */     
/* 32 */     this.rightEar = new ModelRenderer((Model)this);
/* 33 */     this.rightEar.func_78793_a(-3.25F, -7.5F, 0.75F);
/* 34 */     this.head.func_78792_a(this.rightEar);
/* 35 */     setRotationAngle(this.rightEar, 0.0F, 0.0F, -0.5672F);
/* 36 */     this.rightEar.func_78784_a(0, 0).func_228303_a_(-1.0F, -2.0F, 0.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
/*    */     
/* 38 */     this.leftEar = new ModelRenderer((Model)this);
/* 39 */     this.leftEar.func_78793_a(3.25F, -7.5F, 0.75F);
/* 40 */     this.head.func_78792_a(this.leftEar);
/* 41 */     setRotationAngle(this.leftEar, 0.0F, 0.0F, 0.5672F);
/* 42 */     this.leftEar.func_78784_a(0, 0).func_228303_a_(-1.0F, -2.0F, 0.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
/*    */     
/* 44 */     this.body = new ModelRenderer((Model)this);
/* 45 */     this.body.func_78793_a(0.0F, 0.0F, 0.5F);
/* 46 */     this.body.func_78784_a(16, 16).func_228303_a_(-6.0F, -3.0F, -4.0F, 12.0F, 14.0F, 7.0F, 0.0F, false);
/*    */     
/* 48 */     this.rightArm = new ModelRenderer((Model)this);
/* 49 */     this.rightArm.func_78793_a(-6.0F, -1.0F, 0.0F);
/* 50 */     this.rightArm.func_78784_a(32, 44).func_228303_a_(-4.0F, -1.0F, -2.0F, 4.0F, 14.0F, 4.0F, 0.0F, false);
/*    */     
/* 52 */     this.leftArm = new ModelRenderer((Model)this);
/* 53 */     this.leftArm.func_78793_a(6.0F, -1.0F, 0.0F);
/* 54 */     this.leftArm.func_78784_a(32, 44).func_228303_a_(0.0F, -1.0F, -2.0F, 4.0F, 14.0F, 4.0F, 0.0F, false);
/*    */     
/* 56 */     this.rightLeg = new ModelRenderer((Model)this);
/* 57 */     this.rightLeg.func_78793_a(-2.15F, 11.0F, 0.0F);
/* 58 */     this.rightLeg.func_78784_a(0, 35).func_228303_a_(-2.0F, -1.0F, -2.0F, 4.0F, 14.0F, 4.0F, 0.0F, false);
/*    */     
/* 60 */     this.leftLeg = new ModelRenderer((Model)this);
/* 61 */     this.leftLeg.func_78793_a(2.15F, 11.0F, 0.0F);
/* 62 */     this.leftLeg.func_78784_a(0, 35).func_228303_a_(-2.0F, -1.0F, -2.0F, 4.0F, 14.0F, 4.0F, 0.0F, false);
/*    */     
/* 64 */     this.field_78115_e = this.body;
/* 65 */     this.field_78116_c = this.head;
/* 66 */     this.field_178723_h = this.rightArm;
/* 67 */     this.field_178724_i = this.leftArm;
/* 68 */     this.field_178721_j = this.rightLeg;
/* 69 */     this.field_178722_k = this.leftLeg;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 75 */     super.func_225597_a_((LivingEntity)entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/*    */     
/* 77 */     this.head.field_78797_d = -3.0F;
/* 78 */     this.head.field_78798_e = 0.0F;
/* 79 */     this.rightArm.field_78797_d = -3.0F;
/* 80 */     this.leftArm.field_78797_d = -3.0F;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 86 */     this.head.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/* 87 */     this.body.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/* 88 */     this.rightArm.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/* 89 */     this.leftArm.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/* 90 */     this.rightLeg.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/* 91 */     this.leftLeg.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 96 */     modelRenderer.field_78795_f = x;
/* 97 */     modelRenderer.field_78796_g = y;
/* 98 */     modelRenderer.field_78808_h = z;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\entities\mobs\humanoids\PacifistaModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */