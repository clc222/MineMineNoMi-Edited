/*    */ package xyz.pixelatedw.mineminenomi.models.entities.projectiles;
/*    */ 
/*    */ import com.google.common.collect.ImmutableList;
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.client.renderer.model.Model;
/*    */ import net.minecraft.client.renderer.model.ModelRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ 
/*    */ 
/*    */ @OnlyIn(Dist.CLIENT)
/*    */ public class CrossModel
/*    */   extends EntityModel
/*    */ {
/*    */   private ModelRenderer shape1;
/*    */   private ModelRenderer shape2;
/*    */   
/*    */   public CrossModel() {
/* 22 */     this.field_78090_t = 64;
/* 23 */     this.field_78089_u = 64;
/*    */     
/* 25 */     this.shape1 = new ModelRenderer((Model)this, 0, 0);
/* 26 */     this.shape1.func_228300_a_(-1.5F, -7.0F, 0.0F, 2.0F, 14.0F, 2.0F);
/* 27 */     this.shape1.func_78793_a(0.0F, 0.0F, 0.0F);
/* 28 */     this.shape1.func_78787_b(64, 64);
/* 29 */     this.shape1.field_78809_i = true;
/* 30 */     setRotation(this.shape1, 0.785F, 0.0F, 0.0F);
/*    */     
/* 32 */     this.shape2 = new ModelRenderer((Model)this, 0, 0);
/* 33 */     this.shape2.func_228300_a_(-1.5F, -8.0F, 0.0F, 2.0F, 14.0F, 2.0F);
/* 34 */     this.shape2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 35 */     this.shape2.func_78787_b(64, 64);
/* 36 */     this.shape2.field_78809_i = true;
/* 37 */     setRotation(this.shape2, -0.785F, 0.0F, 0.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225598_a_(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
/* 43 */     ImmutableList.of(this.shape1, this.shape2).forEach(model -> model.func_228309_a_(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225597_a_(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 55 */     model.field_78795_f = x;
/* 56 */     model.field_78796_g = y;
/* 57 */     model.field_78808_h = z;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\entities\projectiles\CrossModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */