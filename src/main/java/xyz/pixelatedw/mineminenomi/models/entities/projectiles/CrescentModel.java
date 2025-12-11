/*    */ package xyz.pixelatedw.mineminenomi.models.entities.projectiles;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.client.renderer.model.Model;
/*    */ import net.minecraft.client.renderer.model.ModelRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ 
/*    */ public class CrescentModel extends EntityModel<Entity> {
/*    */   private final ModelRenderer slash;
/*    */   
/*    */   public CrescentModel() {
/* 14 */     this.field_78090_t = 16;
/* 15 */     this.field_78089_u = 16;
/*    */     
/* 17 */     this.slash = new ModelRenderer((Model)this);
/* 18 */     this.slash.func_78793_a(0.0526F, -1.5F, -5.0F);
/* 19 */     this.slash.func_78784_a(1, 1).func_228303_a_(-0.5F, 24.5F, 6.5526F, 1.0F, 1.0F, 8.0F, 0.0F, false);
/* 20 */     this.slash.func_78784_a(1, 1).func_228303_a_(-0.5F, 23.5F, 4.5526F, 1.0F, 1.0F, 8.0F, 0.0F, false);
/* 21 */     this.slash.func_78784_a(1, 1).func_228303_a_(-0.5F, 22.4F, 3.5526F, 1.0F, 1.0F, 7.0F, 0.1F, false);
/* 22 */     this.slash.func_78784_a(1, 1).func_228303_a_(-0.5F, 21.2F, 2.5526F, 1.0F, 1.0F, 7.0F, 0.1F, false);
/* 23 */     this.slash.func_78784_a(1, 1).func_228303_a_(-0.5F, 20.0F, 1.5526F, 1.0F, 1.0F, 7.0F, 0.1F, false);
/* 24 */     this.slash.func_78784_a(1, 1).func_228303_a_(-0.5F, 17.7F, 0.5526F, 1.0F, 2.0F, 7.0F, 0.2F, false);
/* 25 */     this.slash.func_78784_a(1, 1).func_228303_a_(-0.5F, 14.3F, -0.4474F, 1.0F, 3.0F, 7.0F, 0.2F, false);
/* 26 */     this.slash.func_78784_a(1, 1).func_228303_a_(-0.5F, 10.8F, -1.4474F, 1.0F, 3.0F, 7.0F, 0.3F, false);
/* 27 */     this.slash.func_78784_a(1, 1).func_228303_a_(-0.5F, 6.2F, -2.4474F, 1.0F, 4.0F, 7.0F, 0.3F, false);
/* 28 */     this.slash.func_78784_a(1, 1).func_228303_a_(-0.5F, -5.5F, -3.4474F, 1.0F, 11.0F, 7.0F, 0.4F, false);
/* 29 */     this.slash.func_78784_a(1, 1).func_228303_a_(-0.5F, -10.2F, -2.4474F, 1.0F, 4.0F, 7.0F, 0.3F, false);
/* 30 */     this.slash.func_78784_a(1, 1).func_228303_a_(-0.5F, -13.8F, -1.4474F, 1.0F, 3.0F, 7.0F, 0.3F, false);
/* 31 */     this.slash.func_78784_a(1, 1).func_228303_a_(-0.5F, -17.3F, -0.4474F, 1.0F, 3.0F, 7.0F, 0.2F, false);
/* 32 */     this.slash.func_78784_a(1, 1).func_228303_a_(-0.5F, -19.7F, 0.5526F, 1.0F, 2.0F, 7.0F, 0.2F, false);
/* 33 */     this.slash.func_78784_a(1, 1).func_228303_a_(-0.5F, -21.0F, 1.5526F, 1.0F, 1.0F, 7.0F, 0.1F, false);
/* 34 */     this.slash.func_78784_a(1, 1).func_228303_a_(-0.5F, -22.2F, 2.5526F, 1.0F, 1.0F, 7.0F, 0.1F, false);
/* 35 */     this.slash.func_78784_a(1, 1).func_228303_a_(-0.5F, -23.4F, 3.5526F, 1.0F, 1.0F, 7.0F, 0.1F, false);
/* 36 */     this.slash.func_78784_a(1, 1).func_228303_a_(-0.5F, -24.5F, 4.5526F, 1.0F, 1.0F, 8.0F, 0.0F, false);
/* 37 */     this.slash.func_78784_a(1, 1).func_228303_a_(-0.5F, -25.5F, 6.5526F, 1.0F, 1.0F, 8.0F, 0.0F, false);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_225597_a_(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*    */ 
/*    */   
/*    */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 45 */     this.slash.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*    */   }
/*    */   
/*    */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 49 */     modelRenderer.field_78795_f = x;
/* 50 */     modelRenderer.field_78796_g = y;
/* 51 */     modelRenderer.field_78808_h = z;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\entities\projectiles\CrescentModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */