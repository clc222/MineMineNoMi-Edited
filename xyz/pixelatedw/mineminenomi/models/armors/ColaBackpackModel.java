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
/*    */ public class ColaBackpackModel<T extends LivingEntity>
/*    */   extends BipedModel<T> {
/*    */   public ModelRenderer base;
/*    */   public ModelRenderer leftCola;
/*    */   public ModelRenderer rightCola;
/*    */   public ModelRenderer leftColaCap;
/*    */   public ModelRenderer rightColaCap;
/*    */   
/*    */   public ColaBackpackModel() {
/* 20 */     super(1.0F);
/* 21 */     this.field_78090_t = 16;
/* 22 */     this.field_78089_u = 16;
/* 23 */     this.leftColaCap = new ModelRenderer((Model)this, 0, 14);
/* 24 */     this.leftColaCap.func_78793_a(0.0F, 0.0F, 0.0F);
/* 25 */     this.leftColaCap.func_228301_a_(1.5F, 1.0F, 3.0F, 1.0F, 1.0F, 1.0F, 0.0F);
/* 26 */     this.rightCola = new ModelRenderer((Model)this, 0, 0);
/* 27 */     this.rightCola.func_78793_a(0.0F, 0.0F, 0.0F);
/* 28 */     this.rightCola.func_228301_a_(-3.5F, 1.5F, 2.0F, 3.0F, 7.0F, 3.0F, 0.0F);
/* 29 */     this.leftCola = new ModelRenderer((Model)this, 0, 0);
/* 30 */     this.leftCola.func_78793_a(0.0F, 0.0F, 0.0F);
/* 31 */     this.leftCola.func_228301_a_(0.5F, 1.5F, 2.0F, 3.0F, 7.0F, 3.0F, 0.0F);
/* 32 */     this.rightColaCap = new ModelRenderer((Model)this, 0, 14);
/* 33 */     this.rightColaCap.func_78793_a(0.0F, 0.0F, 0.0F);
/* 34 */     this.rightColaCap.func_228301_a_(-2.5F, 1.0F, 3.0F, 1.0F, 1.0F, 1.0F, 0.0F);
/* 35 */     this.base = new ModelRenderer((Model)this, 5, 12);
/* 36 */     this.base.func_78793_a(0.0F, 0.0F, 0.0F);
/* 37 */     this.base.func_228301_a_(-0.5F, 3.0F, 2.0F, 1.0F, 2.0F, 2.0F, 0.0F);
/* 38 */     this.leftCola.func_78792_a(this.leftColaCap);
/* 39 */     this.base.func_78792_a(this.rightCola);
/* 40 */     this.base.func_78792_a(this.leftCola);
/* 41 */     this.rightCola.func_78792_a(this.rightColaCap);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 47 */     this.base.func_217177_a(this.field_78115_e);
/* 48 */     this.base.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 54 */     super.func_225597_a_((LivingEntity)entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
/* 59 */     model.field_78795_f = x;
/* 60 */     model.field_78796_g = y;
/* 61 */     model.field_78808_h = z;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\armors\ColaBackpackModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */