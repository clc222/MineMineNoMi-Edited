/*    */ package xyz.pixelatedw.mineminenomi.models.entities.projectiles;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.client.renderer.model.Model;
/*    */ import net.minecraft.client.renderer.model.ModelRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ 
/*    */ @OnlyIn(Dist.CLIENT)
/*    */ public class ArrowModel
/*    */   extends EntityModel
/*    */ {
/*    */   public ModelRenderer body;
/*    */   public ModelRenderer tail1;
/*    */   public ModelRenderer tail2;
/*    */   public ModelRenderer head1;
/*    */   public ModelRenderer head2;
/*    */   
/*    */   public ArrowModel() {
/* 23 */     this.field_78090_t = 64;
/* 24 */     this.field_78089_u = 64;
/* 25 */     this.body = new ModelRenderer((Model)this, 0, 0);
/* 26 */     this.body.func_78793_a(0.0F, 0.0F, 0.0F);
/* 27 */     this.body.func_228301_a_(-0.5F, 0.0F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F);
/* 28 */     this.tail1 = new ModelRenderer((Model)this, 15, 0);
/* 29 */     this.tail1.func_78793_a(0.0F, 0.0F, 0.0F);
/* 30 */     this.tail1.func_228301_a_(-5.0F, -1.7F, 0.4F, 4.0F, 4.0F, 0.0F, 0.0F);
/* 31 */     setRotateAngle(this.tail1, 0.0F, 1.5707964F, 0.7853982F);
/* 32 */     this.tail2 = new ModelRenderer((Model)this, 15, 5);
/* 33 */     this.tail2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 34 */     this.tail2.func_228301_a_(-5.0F, -1.8F, -0.3F, 4.0F, 4.0F, 0.0F, 0.0F);
/* 35 */     setRotateAngle(this.tail2, 0.0F, 1.5707964F, -0.7853982F);
/* 36 */     this.head2 = new ModelRenderer((Model)this, 0, 4);
/* 37 */     this.head2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 38 */     this.head2.func_228301_a_(-0.5F, 0.0F, -7.0F, 1.0F, 1.0F, 1.0F, 0.0F);
/* 39 */     this.head1 = new ModelRenderer((Model)this, 0, 0);
/* 40 */     this.head1.func_78793_a(0.0F, 0.0F, 0.0F);
/* 41 */     this.head1.func_228301_a_(-1.0F, -0.5F, -6.0F, 2.0F, 2.0F, 1.0F, 0.0F);
/* 42 */     this.body.func_78792_a(this.tail1);
/* 43 */     this.body.func_78792_a(this.tail2);
/* 44 */     this.head1.func_78792_a(this.head2);
/* 45 */     this.body.func_78792_a(this.head1);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225598_a_(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
/* 51 */     this.body.func_228309_a_(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225597_a_(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
/* 62 */     model.field_78795_f = x;
/* 63 */     model.field_78796_g = y;
/* 64 */     model.field_78808_h = z;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\entities\projectiles\ArrowModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */