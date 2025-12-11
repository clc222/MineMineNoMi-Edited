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
/*    */ public class TokuHollowModel
/*    */   extends EntityModel
/*    */ {
/*    */   public ModelRenderer head;
/*    */   public ModelRenderer head2;
/*    */   public ModelRenderer head3;
/*    */   public ModelRenderer head4;
/*    */   public ModelRenderer body;
/*    */   public ModelRenderer rightarm;
/*    */   public ModelRenderer leftarm;
/*    */   public ModelRenderer rightleg;
/*    */   public ModelRenderer leftleg;
/*    */   
/*    */   public TokuHollowModel() {
/* 27 */     this.field_78090_t = 256;
/* 28 */     this.field_78089_u = 128;
/* 29 */     this.head2 = new ModelRenderer((Model)this, 0, 50);
/* 30 */     this.head2.func_78793_a(-11.0F, -7.0F, -5.5F);
/* 31 */     this.head2.func_228301_a_(0.0F, 0.0F, 0.0F, 22.0F, 22.0F, 25.0F, 0.0F);
/* 32 */     this.head3 = new ModelRenderer((Model)this, 98, 50);
/* 33 */     this.head3.func_78793_a(-12.5F, -7.0F, -4.0F);
/* 34 */     this.head3.func_228301_a_(0.0F, 0.0F, 0.0F, 25.0F, 22.0F, 22.0F, 0.0F);
/* 35 */     this.head4 = new ModelRenderer((Model)this, 98, 0);
/* 36 */     this.head4.func_78793_a(-11.0F, -8.5F, -4.0F);
/* 37 */     this.head4.func_228301_a_(0.0F, 0.0F, 0.0F, 22.0F, 25.0F, 22.0F, 0.0F);
/* 38 */     this.rightarm = new ModelRenderer((Model)this, 188, 22);
/* 39 */     this.rightarm.func_78793_a(-17.0F, 8.0F, -9.0F);
/* 40 */     this.rightarm.func_228301_a_(0.0F, 0.0F, 0.0F, 3.0F, 3.0F, 12.0F, 0.0F);
/* 41 */     setRotateAngle(this.rightarm, 0.13962634F, 0.41887903F, 0.0F);
/* 42 */     this.head = new ModelRenderer((Model)this, 0, 0);
/* 43 */     this.head.func_78793_a(-12.0F, -8.0F, -5.0F);
/* 44 */     this.head.func_228301_a_(0.0F, 0.0F, 0.0F, 24.0F, 24.0F, 24.0F, 0.0F);
/* 45 */     this.rightleg = new ModelRenderer((Model)this, 188, 14);
/* 46 */     this.rightleg.func_78793_a(-4.0F, 5.0F, 28.5F);
/* 47 */     this.rightleg.func_228301_a_(0.0F, 0.0F, 0.0F, 2.0F, 2.0F, 4.0F, 0.0F);
/* 48 */     this.body = new ModelRenderer((Model)this, 188, 0);
/* 49 */     this.body.func_78793_a(-4.0F, 4.0F, 19.5F);
/* 50 */     this.body.func_228301_a_(0.0F, 0.0F, 0.0F, 8.0F, 4.0F, 9.0F, 0.0F);
/* 51 */     this.leftarm = new ModelRenderer((Model)this, 188, 22);
/* 52 */     this.leftarm.func_78793_a(14.2F, 8.0F, -10.0F);
/* 53 */     this.leftarm.func_228301_a_(0.0F, 0.0F, 0.0F, 3.0F, 3.0F, 12.0F, 0.0F);
/* 54 */     setRotateAngle(this.leftarm, 0.13962634F, -0.41887903F, 0.0F);
/* 55 */     this.leftleg = new ModelRenderer((Model)this, 188, 14);
/* 56 */     this.leftleg.func_78793_a(2.0F, 5.0F, 28.5F);
/* 57 */     this.leftleg.func_228301_a_(0.0F, 0.0F, 0.0F, 2.0F, 2.0F, 4.0F, 0.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225598_a_(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
/* 63 */     this.head2.func_228309_a_(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 64 */     this.head3.func_228309_a_(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 65 */     this.head4.func_228309_a_(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 66 */     this.rightarm.func_228309_a_(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 67 */     this.head.func_228309_a_(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 68 */     this.rightleg.func_228309_a_(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 69 */     this.body.func_228309_a_(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 70 */     this.leftarm.func_228309_a_(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 71 */     this.leftleg.func_228309_a_(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
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
/* 82 */     model.field_78795_f = x;
/* 83 */     model.field_78796_g = y;
/* 84 */     model.field_78808_h = z;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\entities\projectiles\TokuHollowModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */