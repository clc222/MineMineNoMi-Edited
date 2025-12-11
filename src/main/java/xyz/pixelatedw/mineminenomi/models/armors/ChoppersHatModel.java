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
/*    */ public class ChoppersHatModel<T extends LivingEntity>
/*    */   extends BipedModel<T> {
/*    */   public ModelRenderer base;
/*    */   public ModelRenderer base_1;
/*    */   
/*    */   public ChoppersHatModel() {
/* 17 */     super(1.0F);
/* 18 */     this.field_78090_t = 64;
/* 19 */     this.field_78089_u = 32;
/*    */     
/* 21 */     this.base = new ModelRenderer((Model)this);
/* 22 */     this.base.func_78793_a(0.0F, -1.0F, 0.0F);
/* 23 */     this.base.func_78784_a(0, 18).func_228303_a_(-5.5F, -8.0F, -5.5F, 11.0F, 1.0F, 11.0F, 0.5F, false);
/*    */     
/* 25 */     this.base_1 = new ModelRenderer((Model)this);
/* 26 */     this.base_1.func_78793_a(0.0F, -8.0F, 0.0F);
/* 27 */     this.base.func_78792_a(this.base_1);
/* 28 */     this.base_1.func_78784_a(0, 0).func_228303_a_(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.5F, false);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 34 */     this.base.func_217177_a(this.field_78116_c);
/* 35 */     this.base.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 41 */     super.func_225597_a_((LivingEntity)entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
/* 46 */     model.field_78795_f = x;
/* 47 */     model.field_78796_g = y;
/* 48 */     model.field_78808_h = z;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\armors\ChoppersHatModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */