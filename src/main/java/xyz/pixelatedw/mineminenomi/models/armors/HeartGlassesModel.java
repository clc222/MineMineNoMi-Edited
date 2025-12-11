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
/*    */ public class HeartGlassesModel<T extends LivingEntity> extends BipedModel<T> {
/*    */   private final ModelRenderer glasses;
/*    */   
/*    */   public HeartGlassesModel() {
/* 15 */     super(1.0F);
/* 16 */     this.field_78090_t = 32;
/* 17 */     this.field_78089_u = 32;
/*    */     
/* 19 */     this.glasses = new ModelRenderer((Model)this);
/* 20 */     this.glasses.func_78793_a(0.0F, 22.5F, 0.0F);
/* 21 */     this.glasses.func_78784_a(0, -4).func_228303_a_(-4.25F, -4.0F, -4.5F, 0.0F, 1.0F, 6.0F, 0.0F, false);
/* 22 */     this.glasses.func_78784_a(0, -4).func_228303_a_(4.25F, -4.0F, -4.5F, 0.0F, 1.0F, 6.0F, 0.0F, true);
/* 23 */     this.glasses.func_78784_a(0, 0).func_228303_a_(-5.0F, -4.0F, -4.5F, 10.0F, 1.0F, 0.0F, 0.0F, false);
/*    */     
/* 25 */     this.leftLens = new ModelRenderer((Model)this);
/* 26 */     this.leftLens.func_78793_a(-1.659F, -3.7374F, -5.0F);
/* 27 */     this.glasses.func_78792_a(this.leftLens);
/* 28 */     setRotationAngle(this.leftLens, 0.0F, 0.0F, 0.7854F);
/* 29 */     this.leftLens.func_78784_a(0, 4).func_228303_a_(-1.341F, 0.0F, 0.0F, 2.0F, 1.0F, 1.0F, 0.1F, false);
/* 30 */     this.leftLens.func_78784_a(6, 4).func_228303_a_(-0.341F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.1F, false);
/*    */     
/* 32 */     this.rightLens = new ModelRenderer((Model)this);
/* 33 */     this.rightLens.func_78793_a(2.091F, -3.7374F, -5.0F);
/* 34 */     this.glasses.func_78792_a(this.rightLens);
/* 35 */     setRotationAngle(this.rightLens, 0.0F, 0.0F, 0.7854F);
/* 36 */     this.rightLens.func_78784_a(0, 4).func_228303_a_(-1.341F, 0.0F, 0.0F, 2.0F, 1.0F, 1.0F, 0.1F, false);
/* 37 */     this.rightLens.func_78784_a(6, 4).func_228303_a_(-0.341F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.1F, false);
/*    */   }
/*    */ 
/*    */   
/*    */   private final ModelRenderer leftLens;
/*    */   private final ModelRenderer rightLens;
/*    */   
/*    */   public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*    */   
/*    */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 47 */     this.glasses.func_217177_a(this.field_78116_c);
/* 48 */     this.glasses.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*    */   }
/*    */   
/*    */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 52 */     modelRenderer.field_78795_f = x;
/* 53 */     modelRenderer.field_78796_g = y;
/* 54 */     modelRenderer.field_78808_h = z;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\armors\HeartGlassesModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */