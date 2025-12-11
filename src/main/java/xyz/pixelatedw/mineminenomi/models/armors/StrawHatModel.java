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
/*    */ public class StrawHatModel<T extends LivingEntity>
/*    */   extends BipedModel<T> {
/*    */   private final ModelRenderer strawhat;
/*    */   
/*    */   public StrawHatModel() {
/* 16 */     super(1.0F);
/* 17 */     this.field_78090_t = 64;
/* 18 */     this.field_78089_u = 64;
/*    */     
/* 20 */     this.strawhat = new ModelRenderer((Model)this);
/* 21 */     this.strawhat.func_78793_a(0.0F, -0.275F, 0.0F);
/* 22 */     this.strawhat.func_78784_a(-17, 47).func_228303_a_(-8.5F, -6.0F, -8.5F, 17.0F, 0.0F, 17.0F, 0.0F, false);
/*    */     
/* 24 */     this.top = new ModelRenderer((Model)this);
/* 25 */     this.top.func_78793_a(0.5F, 25.0F, -0.5F);
/* 26 */     this.strawhat.func_78792_a(this.top);
/* 27 */     this.top.func_78784_a(0, 22).func_228303_a_(-5.0F, -34.155F, -4.0F, 9.0F, 3.0F, 9.0F, 0.15F, false);
/*    */     
/* 29 */     this.band = new ModelRenderer((Model)this);
/* 30 */     this.band.func_78793_a(0.0F, 0.0F, 0.0F);
/* 31 */     this.top.func_78792_a(this.band);
/* 32 */     this.band.func_78784_a(0, 36).func_228303_a_(-5.0F, -32.255F, -4.0F, 9.0F, 1.0F, 9.0F, 0.25F, false);
/*    */   }
/*    */   private final ModelRenderer top; private final ModelRenderer band;
/*    */   
/*    */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 37 */     this.strawhat.func_217177_a(this.field_78116_c);
/* 38 */     this.strawhat.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 43 */     super.func_225597_a_((LivingEntity)entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/*    */   }
/*    */   
/*    */   public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 47 */     modelRenderer.field_78795_f = x;
/* 48 */     modelRenderer.field_78796_g = y;
/* 49 */     modelRenderer.field_78808_h = z;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\armors\StrawHatModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */