/*    */ package xyz.pixelatedw.mineminenomi.models;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.client.renderer.model.Model;
/*    */ import net.minecraft.client.renderer.model.ModelRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ 
/*    */ public class HandcuffModel
/*    */   extends EntityModel<Entity>
/*    */ {
/*    */   private final ModelRenderer handcuffs;
/*    */   private final ModelRenderer rightCuff;
/*    */   private final ModelRenderer leftCuff;
/*    */   private final ModelRenderer chain;
/*    */   
/*    */   public HandcuffModel() {
/* 19 */     this.field_78090_t = 32;
/* 20 */     this.field_78089_u = 32;
/*    */     
/* 22 */     this.handcuffs = new ModelRenderer((Model)this);
/* 23 */     this.handcuffs.func_78793_a(1.0F, 12.0F, -10.0F);
/* 24 */     setRotationAngle(this.handcuffs, 0.7854F, 0.0F, 0.0F);
/*    */     
/* 26 */     this.rightCuff = new ModelRenderer((Model)this);
/* 27 */     this.rightCuff.func_78793_a(-7.0F, 0.75F, 7.0F);
/* 28 */     this.handcuffs.func_78792_a(this.rightCuff);
/* 29 */     this.rightCuff.func_78784_a(0, 12).func_228303_a_(2.0F, -2.0F, -2.0F, 1.0F, 4.0F, 3.0F, 0.0F, false);
/* 30 */     this.rightCuff.func_78784_a(0, 0).func_228303_a_(-2.0F, -3.0F, -2.0F, 4.0F, 1.0F, 3.0F, 0.0F, false);
/* 31 */     this.rightCuff.func_78784_a(0, 0).func_228303_a_(-2.0F, 2.0F, -2.0F, 4.0F, 1.0F, 3.0F, 0.0F, false);
/* 32 */     this.rightCuff.func_78784_a(0, 12).func_228303_a_(-3.0F, -2.0F, -2.0F, 1.0F, 4.0F, 3.0F, 0.0F, false);
/*    */     
/* 34 */     this.leftCuff = new ModelRenderer((Model)this);
/* 35 */     this.leftCuff.func_78793_a(5.0F, 0.75F, 7.0F);
/* 36 */     this.handcuffs.func_78792_a(this.leftCuff);
/* 37 */     this.leftCuff.func_78784_a(0, 12).func_228303_a_(2.0F, -2.0F, -2.0F, 1.0F, 4.0F, 3.0F, 0.0F, false);
/* 38 */     this.leftCuff.func_78784_a(0, 0).func_228303_a_(-2.0F, -3.0F, -2.0F, 4.0F, 1.0F, 3.0F, 0.0F, false);
/* 39 */     this.leftCuff.func_78784_a(0, 0).func_228303_a_(-2.0F, 2.0F, -2.0F, 4.0F, 1.0F, 3.0F, 0.0F, false);
/* 40 */     this.leftCuff.func_78784_a(0, 12).func_228303_a_(-3.0F, -2.0F, -2.0F, 1.0F, 4.0F, 3.0F, 0.0F, false);
/*    */     
/* 42 */     this.chain = new ModelRenderer((Model)this);
/* 43 */     this.chain.func_78793_a(-1.0F, 0.25F, 6.0F);
/* 44 */     this.handcuffs.func_78792_a(this.chain);
/* 45 */     this.chain.func_78784_a(0, 8).func_228303_a_(-2.0F, 1.0F, 0.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
/* 46 */     this.chain.func_78784_a(0, 8).func_228303_a_(-2.0F, -1.0F, 0.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
/* 47 */     this.chain.func_78784_a(0, 5).func_228303_a_(-3.0F, 0.0F, 0.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/* 48 */     this.chain.func_78784_a(0, 5).func_228303_a_(1.0F, 0.0F, 0.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225597_a_(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 59 */     this.handcuffs.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 64 */     modelRenderer.field_78795_f = x;
/* 65 */     modelRenderer.field_78796_g = y;
/* 66 */     modelRenderer.field_78808_h = z;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\HandcuffModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */