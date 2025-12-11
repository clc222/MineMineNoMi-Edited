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
/*    */ public class LawHatModel<T extends LivingEntity>
/*    */   extends BipedModel<T> {
/*    */   private final ModelRenderer hat;
/*    */   
/*    */   public LawHatModel() {
/* 16 */     super(1.0F);
/* 17 */     this.field_78090_t = 64;
/* 18 */     this.field_78089_u = 64;
/*    */     
/* 20 */     this.hat = new ModelRenderer((Model)this);
/* 21 */     this.hat.func_78793_a(0.0F, 0.0F, 0.0F);
/* 22 */     this.hat.func_78784_a(2, 20).func_228303_a_(-5.0F, -8.0F, -5.0F, 10.0F, 3.0F, 10.0F, 0.5F, false);
/* 23 */     this.hat.func_78784_a(2, 3).func_228303_a_(-4.0F, -12.0F, -4.0F, 8.0F, 5.0F, 8.0F, 0.5F, false);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 34 */     this.hat.func_217177_a(this.field_78116_c);
/* 35 */     this.hat.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 40 */     modelRenderer.field_78795_f = x;
/* 41 */     modelRenderer.field_78796_g = y;
/* 42 */     modelRenderer.field_78808_h = z;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\armors\LawHatModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */