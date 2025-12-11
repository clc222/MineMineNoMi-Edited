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
/*    */ public class NegativeHollowModel
/*    */   extends EntityModel
/*    */ {
/*    */   public ModelRenderer head;
/*    */   public ModelRenderer body;
/*    */   public ModelRenderer rightarm1;
/*    */   public ModelRenderer rightarm2;
/*    */   public ModelRenderer leftarm1;
/*    */   public ModelRenderer leftarm2;
/*    */   public ModelRenderer rightleg;
/*    */   public ModelRenderer leftleg;
/*    */   
/*    */   public NegativeHollowModel() {
/* 26 */     this.field_78090_t = 64;
/* 27 */     this.field_78089_u = 32;
/* 28 */     this.body = new ModelRenderer((Model)this, 35, 0);
/* 29 */     this.body.func_78793_a(0.0F, 5.0F, -6.0F);
/* 30 */     this.body.func_228301_a_(0.0F, 0.0F, 0.0F, 3.0F, 8.0F, 2.0F, 0.0F);
/* 31 */     setRotateAngle(this.body, 1.5707964F, -0.0F, 0.0F);
/* 32 */     this.leftleg = new ModelRenderer((Model)this, 46, 0);
/* 33 */     this.leftleg.func_78793_a(2.0F, 4.5F, 2.0F);
/* 34 */     this.leftleg.func_228301_a_(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F);
/* 35 */     setRotateAngle(this.leftleg, 1.5707964F, -0.0F, 0.0F);
/* 36 */     this.rightarm1 = new ModelRenderer((Model)this, 28, 0);
/* 37 */     this.rightarm1.func_78793_a(-3.0F, 5.0F, -9.0F);
/* 38 */     this.rightarm1.func_228301_a_(0.0F, 0.0F, 0.0F, 1.0F, 6.0F, 2.0F, 0.0F);
/* 39 */     setRotateAngle(this.rightarm1, 1.5707964F, 0.55850536F, 0.0F);
/* 40 */     this.leftarm1 = new ModelRenderer((Model)this, 28, 0);
/* 41 */     this.leftarm1.func_78793_a(5.0F, 5.0F, -9.5F);
/* 42 */     this.leftarm1.func_228301_a_(0.0F, 0.0F, 0.0F, 1.0F, 6.0F, 2.0F, 0.0F);
/* 43 */     setRotateAngle(this.leftarm1, 1.5707964F, -0.55850536F, 0.0F);
/* 44 */     this.leftarm2 = new ModelRenderer((Model)this, 17, 0);
/* 45 */     this.leftarm2.func_78793_a(4.0F, 5.5F, -14.5F);
/* 46 */     this.leftarm2.func_228301_a_(0.0F, 0.0F, 0.0F, 2.0F, 6.0F, 3.0F, 0.0F);
/* 47 */     setRotateAngle(this.leftarm2, 1.5707964F, -0.0F, 0.0F);
/* 48 */     this.rightarm2 = new ModelRenderer((Model)this, 17, 0);
/* 49 */     this.rightarm2.func_78793_a(-3.0F, 5.5F, -14.5F);
/* 50 */     this.rightarm2.func_228301_a_(0.0F, 0.0F, 0.0F, 2.0F, 6.0F, 3.0F, 0.0F);
/* 51 */     setRotateAngle(this.rightarm2, 1.5707964F, -0.0F, 0.0F);
/* 52 */     this.rightleg = new ModelRenderer((Model)this, 46, 0);
/* 53 */     this.rightleg.func_78793_a(0.0F, 4.5F, 2.0F);
/* 54 */     this.rightleg.func_228301_a_(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F);
/* 55 */     setRotateAngle(this.rightleg, 1.5707964F, -0.0F, 0.0F);
/* 56 */     this.head = new ModelRenderer((Model)this, 0, 0);
/* 57 */     this.head.field_78809_i = true;
/* 58 */     this.head.func_78793_a(-0.5F, 2.0F, -10.0F);
/* 59 */     this.head.func_228301_a_(0.0F, 0.0F, 0.0F, 4.0F, 4.0F, 4.0F, 0.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225598_a_(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
/* 65 */     this.body.func_228309_a_(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 66 */     this.leftleg.func_228309_a_(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 67 */     this.rightarm1.func_228309_a_(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 68 */     this.leftarm1.func_228309_a_(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 69 */     this.leftarm2.func_228309_a_(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 70 */     this.rightarm2.func_228309_a_(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 71 */     this.rightleg.func_228309_a_(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 72 */     this.head.func_228309_a_(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225597_a_(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 83 */     modelRenderer.field_78795_f = x;
/* 84 */     modelRenderer.field_78796_g = y;
/* 85 */     modelRenderer.field_78808_h = z;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\entities\projectiles\NegativeHollowModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */