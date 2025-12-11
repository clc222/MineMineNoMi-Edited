/*    */ package xyz.pixelatedw.mineminenomi.models.abilities;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.client.renderer.model.Model;
/*    */ import net.minecraft.client.renderer.model.ModelRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ 
/*    */ public class PhysicalBodyModel
/*    */   extends EntityModel
/*    */ {
/*    */   public ModelRenderer field_178721_j;
/*    */   public ModelRenderer field_78115_e;
/*    */   public ModelRenderer field_178722_k;
/*    */   public ModelRenderer field_78116_c;
/*    */   public ModelRenderer field_178723_h;
/*    */   public ModelRenderer field_178724_i;
/*    */   
/*    */   public PhysicalBodyModel() {
/* 21 */     this.field_78090_t = 64;
/* 22 */     this.field_78089_u = 64;
/* 23 */     this.field_178723_h = new ModelRenderer((Model)this, 40, 16);
/* 24 */     this.field_178723_h.func_78793_a(-5.0F, 2.0F, 0.0F);
/* 25 */     this.field_178723_h.func_228301_a_(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F);
/* 26 */     this.field_178724_i = new ModelRenderer((Model)this, 32, 48);
/* 27 */     this.field_178724_i.func_78793_a(5.0F, 2.0F, 0.0F);
/* 28 */     this.field_178724_i.func_228301_a_(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F);
/* 29 */     this.field_78115_e = new ModelRenderer((Model)this, 16, 16);
/* 30 */     this.field_78115_e.func_78793_a(0.0F, 12.0F, 0.0F);
/* 31 */     this.field_78115_e.func_228301_a_(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F);
/* 32 */     this.field_78116_c = new ModelRenderer((Model)this, 0, 0);
/* 33 */     this.field_78116_c.func_78793_a(0.0F, 0.0F, 0.0F);
/* 34 */     this.field_78116_c.func_228301_a_(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F);
/* 35 */     setRotateAngle(this.field_78116_c, 0.7853982F, 0.0F, 0.0F);
/* 36 */     this.field_178721_j = new ModelRenderer((Model)this, 0, 16);
/* 37 */     this.field_178721_j.func_78793_a(-1.9F, 22.0F, 0.0F);
/* 38 */     this.field_178721_j.func_228301_a_(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F);
/* 39 */     setRotateAngle(this.field_178721_j, -1.5707964F, 0.0F, 0.0F);
/* 40 */     this.field_178722_k = new ModelRenderer((Model)this, 16, 48);
/* 41 */     this.field_178722_k.func_78793_a(1.9F, 22.0F, 0.0F);
/* 42 */     this.field_178722_k.func_228301_a_(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F);
/* 43 */     setRotateAngle(this.field_178722_k, -1.5707964F, 0.0F, 0.0F);
/* 44 */     this.field_78115_e.func_78792_a(this.field_178723_h);
/* 45 */     this.field_78115_e.func_78792_a(this.field_178724_i);
/* 46 */     this.field_78115_e.func_78792_a(this.field_78116_c);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 52 */     this.field_78115_e.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 53 */     this.field_178721_j.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 54 */     this.field_178722_k.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225597_a_(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 64 */     modelRenderer.field_78795_f = x;
/* 65 */     modelRenderer.field_78796_g = y;
/* 66 */     modelRenderer.field_78808_h = z;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\abilities\PhysicalBodyModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */