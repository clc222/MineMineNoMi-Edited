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
/*    */ public class WideBrimHatModel<T extends LivingEntity> extends BipedModel<T> {
/*    */   private final ModelRenderer hat;
/*    */   private final ModelRenderer top;
/*    */   
/*    */   public WideBrimHatModel() {
/* 16 */     super(1.0F);
/* 17 */     this.field_78090_t = 128;
/* 18 */     this.field_78089_u = 128;
/*    */     
/* 20 */     this.hat = new ModelRenderer((Model)this);
/* 21 */     this.hat.func_78793_a(0.0F, -2.5F, 0.0F);
/* 22 */     this.hat.func_78784_a(7, 4).func_228303_a_(-8.0F, -6.0F, -8.0F, 16.0F, 1.0F, 16.0F, 0.0F, false);
/* 23 */     this.hat.func_78784_a(7, 4).func_228303_a_(-8.0F, -6.75F, -8.0F, 16.0F, 1.0F, 16.0F, -0.2F, false);
/*    */     
/* 25 */     this.top = new ModelRenderer((Model)this);
/* 26 */     this.top.func_78793_a(0.0F, -6.5F, 0.0F);
/* 27 */     this.hat.func_78792_a(this.top);
/* 28 */     this.top.func_78784_a(0, 35).func_228303_a_(-5.0F, -3.0F, -5.0F, 10.0F, 3.0F, 10.0F, 0.0F, false);
/* 29 */     this.top.func_78784_a(29, 23).func_228303_a_(-5.0F, -0.5F, -5.5F, 10.0F, 1.0F, 1.0F, 0.0F, false);
/* 30 */     this.top.func_78784_a(29, 23).func_228303_a_(-5.0F, -0.5F, 4.5F, 10.0F, 1.0F, 1.0F, 0.0F, false);
/* 31 */     this.top.func_78784_a(32, 27).func_228303_a_(4.5F, -0.5F, -5.5F, 1.0F, 1.0F, 11.0F, 0.01F, false);
/* 32 */     this.top.func_78784_a(32, 27).func_228303_a_(-5.5F, -0.5F, -5.5F, 1.0F, 1.0F, 11.0F, 0.01F, false);
/* 33 */     this.top.func_78784_a(0, 23).func_228303_a_(-4.5F, -3.5F, -4.5F, 9.0F, 1.0F, 9.0F, 0.0F, false);
/*    */     
/* 35 */     this.leftSide = new ModelRenderer((Model)this);
/* 36 */     this.leftSide.func_78793_a(-5.0F, -5.25F, -1.0F);
/* 37 */     this.hat.func_78792_a(this.leftSide);
/*    */     
/* 39 */     this.rightSide = new ModelRenderer((Model)this);
/* 40 */     this.rightSide.func_78793_a(5.0F, -5.25F, -1.0F);
/* 41 */     this.hat.func_78792_a(this.rightSide);
/*    */   }
/*    */ 
/*    */   
/*    */   private final ModelRenderer leftSide;
/*    */   private final ModelRenderer rightSide;
/*    */   
/*    */   public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*    */   
/*    */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 51 */     this.hat.func_217177_a(this.field_78116_c);
/* 52 */     this.hat.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*    */   }
/*    */   
/*    */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 56 */     modelRenderer.field_78795_f = x;
/* 57 */     modelRenderer.field_78796_g = y;
/* 58 */     modelRenderer.field_78808_h = z;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\armors\WideBrimHatModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */