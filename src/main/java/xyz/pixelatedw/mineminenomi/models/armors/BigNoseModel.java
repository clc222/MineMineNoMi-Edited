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
/*    */ public class BigNoseModel<T extends LivingEntity> extends BipedModel<T> {
/*    */   public BigNoseModel() {
/* 13 */     super(1.0F);
/* 14 */     this.field_78090_t = 8;
/* 15 */     this.field_78089_u = 8;
/*    */     
/* 17 */     this.bigNose = new ModelRenderer((Model)this);
/* 18 */     this.bigNose.func_78793_a(0.0F, 24.0F, 0.0F);
/* 19 */     this.bigNose.func_78784_a(0, 0).func_228303_a_(-1.0F, -3.5F, -6.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
/*    */   }
/*    */ 
/*    */   
/*    */   private final ModelRenderer bigNose;
/*    */   
/*    */   public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*    */   
/*    */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 28 */     this.bigNose.func_217177_a(this.field_78116_c);
/* 29 */     this.bigNose.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*    */   }
/*    */   
/*    */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 33 */     modelRenderer.field_78795_f = x;
/* 34 */     modelRenderer.field_78796_g = y;
/* 35 */     modelRenderer.field_78808_h = z;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\armors\BigNoseModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */