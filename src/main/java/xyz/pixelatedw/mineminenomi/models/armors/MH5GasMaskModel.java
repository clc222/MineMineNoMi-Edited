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
/*    */ 
/*    */ 
/*    */ public class MH5GasMaskModel<T extends LivingEntity>
/*    */   extends BipedModel<T>
/*    */ {
/*    */   public ModelRenderer base_0;
/*    */   public ModelRenderer base_1;
/*    */   public ModelRenderer eyes;
/*    */   public ModelRenderer right_ear;
/*    */   
/*    */   public MH5GasMaskModel() {
/* 22 */     super(1.0F);
/* 23 */     this.field_78090_t = 32;
/* 24 */     this.field_78089_u = 16;
/* 25 */     this.left_ear = new ModelRenderer((Model)this, 17, 10);
/* 26 */     this.left_ear.func_78793_a(0.0F, 0.0F, 0.0F);
/* 27 */     this.left_ear.func_228301_a_(2.8F, -5.6F, -1.8F, 2.0F, 2.0F, 4.0F, 0.5F);
/* 28 */     this.eye_ear_connector_2 = new ModelRenderer((Model)this, 8, 10);
/* 29 */     this.eye_ear_connector_2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 30 */     this.eye_ear_connector_2.func_228301_a_(3.5F, -5.4F, -4.4F, 1.0F, 1.0F, 3.0F, 0.5F);
/* 31 */     this.base_0 = new ModelRenderer((Model)this, 0, 12);
/* 32 */     this.base_0.func_78793_a(0.0F, 0.0F, 0.0F);
/* 33 */     this.base_0.func_228301_a_(-1.4F, -3.0F, -4.5F, 3.0F, 3.0F, 1.0F, 0.5F);
/* 34 */     this.eyes = new ModelRenderer((Model)this, 0, 0);
/* 35 */     this.eyes.func_78793_a(0.0F, 0.0F, 0.0F);
/* 36 */     this.eyes.func_228301_a_(-3.0F, -5.9F, -5.0F, 6.0F, 3.0F, 2.0F, 0.5F);
/* 37 */     this.eye_ear_connector_3 = new ModelRenderer((Model)this, 9, 14);
/* 38 */     this.eye_ear_connector_3.func_78793_a(0.0F, 0.0F, 0.0F);
/* 39 */     this.eye_ear_connector_3.func_228301_a_(1.5F, -5.4F, -4.4F, 2.0F, 1.0F, 1.0F, 0.5F);
/* 40 */     this.base_1 = new ModelRenderer((Model)this, 0, 8);
/* 41 */     this.base_1.func_78793_a(0.0F, 0.0F, 0.0F);
/* 42 */     this.base_1.func_228301_a_(-1.0F, -2.5F, -5.4F, 2.0F, 2.0F, 2.0F, 0.5F);
/* 43 */     this.eye_ear_connector_1 = new ModelRenderer((Model)this, 9, 14);
/* 44 */     this.eye_ear_connector_1.func_78793_a(0.0F, 0.0F, 0.0F);
/* 45 */     this.eye_ear_connector_1.func_228301_a_(-4.5F, -5.4F, -4.4F, 2.0F, 1.0F, 1.0F, 0.5F);
/* 46 */     this.right_ear = new ModelRenderer((Model)this, 17, 10);
/* 47 */     this.right_ear.func_78793_a(0.0F, 0.0F, 0.0F);
/* 48 */     this.right_ear.func_228301_a_(-5.2F, -5.6F, -1.8F, 2.0F, 2.0F, 4.0F, 0.5F);
/* 49 */     this.eye_ear_connector_0 = new ModelRenderer((Model)this, 8, 10);
/* 50 */     this.eye_ear_connector_0.func_78793_a(0.0F, 0.0F, 0.0F);
/* 51 */     this.eye_ear_connector_0.func_228301_a_(-4.5F, -5.4F, -4.4F, 1.0F, 1.0F, 3.0F, 0.5F);
/* 52 */     this.base_0.func_78792_a(this.left_ear);
/* 53 */     this.left_ear.func_78792_a(this.eye_ear_connector_2);
/* 54 */     this.base_0.func_78792_a(this.eyes);
/* 55 */     this.left_ear.func_78792_a(this.eye_ear_connector_3);
/* 56 */     this.base_0.func_78792_a(this.base_1);
/* 57 */     this.right_ear.func_78792_a(this.eye_ear_connector_1);
/* 58 */     this.base_0.func_78792_a(this.right_ear);
/* 59 */     this.right_ear.func_78792_a(this.eye_ear_connector_0);
/*    */   }
/*    */   public ModelRenderer left_ear; public ModelRenderer eye_ear_connector_0; public ModelRenderer eye_ear_connector_1; public ModelRenderer eye_ear_connector_2; public ModelRenderer eye_ear_connector_3;
/*    */   
/*    */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 64 */     this.base_0.func_217177_a(this.field_78116_c);
/* 65 */     this.base_0.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, 0.5F);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 70 */     super.func_225597_a_((LivingEntity)entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/*    */   }
/*    */   
/*    */   public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
/* 74 */     model.field_78795_f = x;
/* 75 */     model.field_78796_g = y;
/* 76 */     model.field_78808_h = z;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\armors\MH5GasMaskModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */