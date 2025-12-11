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
/*    */ public class Mr3HairModel<T extends LivingEntity>
/*    */   extends BipedModel<T> {
/*    */   public Mr3HairModel() {
/* 14 */     super(1.0F);
/* 15 */     this.field_78090_t = 16;
/* 16 */     this.field_78089_u = 16;
/*    */     
/* 18 */     this.hair = new ModelRenderer((Model)this);
/* 19 */     this.hair.func_78793_a(0.0F, 0.0F, 0.0F);
/* 20 */     this.hair.func_78784_a(0, 2).func_228303_a_(-1.0F, -9.0F, 0.0F, 3.0F, 1.0F, 1.0F, 0.0F, false);
/* 21 */     this.hair.func_78784_a(0, 0).func_228303_a_(-2.0F, -17.0F, 0.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
/* 22 */     this.hair.func_78784_a(7, 3).func_228303_a_(0.0F, -13.0F, 0.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/* 23 */     this.hair.func_78784_a(9, 1).func_228303_a_(-1.0F, -13.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.2F, false);
/* 24 */     this.hair.func_78784_a(4, 4).func_228303_a_(2.0F, -12.25F, 0.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
/* 25 */     this.hair.func_78784_a(0, 4).func_228303_a_(2.0F, -16.75F, 0.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
/* 26 */     this.hair.func_78784_a(8, 5).func_228303_a_(-3.0F, -9.25F, 0.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/* 27 */     this.hair.func_78784_a(0, 9).func_228303_a_(-4.0F, -9.5F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
/* 28 */     this.hair.func_78784_a(7, 8).func_228303_a_(-3.0F, -16.5F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*    */   }
/*    */ 
/*    */   
/*    */   private final ModelRenderer hair;
/*    */ 
/*    */   
/*    */   public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*    */ 
/*    */   
/*    */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 39 */     this.hair.func_217177_a(this.field_78116_c);
/* 40 */     this.hair.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/*    */   }
/*    */   
/*    */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 44 */     modelRenderer.field_78795_f = x;
/* 45 */     modelRenderer.field_78796_g = y;
/* 46 */     modelRenderer.field_78808_h = z;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\armors\Mr3HairModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */