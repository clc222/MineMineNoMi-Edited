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
/*    */ public class MiniHollowModel
/*    */   extends EntityModel
/*    */ {
/*    */   public ModelRenderer head;
/*    */   public ModelRenderer body;
/*    */   public ModelRenderer rightarm;
/*    */   public ModelRenderer leftarm;
/*    */   public ModelRenderer rightleg;
/*    */   public ModelRenderer rightleg_1;
/*    */   
/*    */   public MiniHollowModel() {
/* 24 */     this.field_78090_t = 64;
/* 25 */     this.field_78089_u = 32;
/* 26 */     this.rightleg_1 = new ModelRenderer((Model)this, 19, 0);
/* 27 */     this.rightleg_1.func_78793_a(0.6F, 10.1F, 0.5F);
/* 28 */     this.rightleg_1.func_228301_a_(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F);
/* 29 */     setRotateAngle(this.rightleg_1, 0.0F, -0.0F, -0.61086524F);
/* 30 */     this.rightarm = new ModelRenderer((Model)this, 14, 0);
/* 31 */     this.rightarm.func_78793_a(0.5F, 7.7F, 0.5F);
/* 32 */     this.rightarm.func_228301_a_(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F);
/* 33 */     setRotateAngle(this.rightarm, 0.0F, -0.0F, 0.9424778F);
/* 34 */     this.body = new ModelRenderer((Model)this, 9, 0);
/* 35 */     this.body.func_78793_a(0.5F, 8.0F, 0.5F);
/* 36 */     this.body.func_228301_a_(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F);
/* 37 */     this.head = new ModelRenderer((Model)this, 0, 0);
/* 38 */     this.head.func_78793_a(0.0F, 6.0F, 0.0F);
/* 39 */     this.head.func_228301_a_(0.0F, 0.0F, 0.0F, 2.0F, 2.0F, 2.0F, 0.0F);
/* 40 */     this.rightleg = new ModelRenderer((Model)this, 19, 0);
/* 41 */     this.rightleg.func_78793_a(0.6F, 9.5F, 0.5F);
/* 42 */     this.rightleg.func_228301_a_(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F);
/* 43 */     setRotateAngle(this.rightleg, 0.0F, -0.0F, 0.61086524F);
/* 44 */     this.leftarm = new ModelRenderer((Model)this, 14, 0);
/* 45 */     this.leftarm.func_78793_a(1.0F, 8.5F, 0.5F);
/* 46 */     this.leftarm.func_228301_a_(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F);
/* 47 */     setRotateAngle(this.leftarm, 0.0F, -0.0F, -0.9424778F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225598_a_(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
/* 53 */     this.rightleg_1.func_228309_a_(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 54 */     this.rightarm.func_228309_a_(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 55 */     this.body.func_228309_a_(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 56 */     this.head.func_228309_a_(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 57 */     this.rightleg.func_228309_a_(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 58 */     this.leftarm.func_228309_a_(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
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
/* 69 */     model.field_78795_f = x;
/* 70 */     model.field_78796_g = y;
/* 71 */     model.field_78808_h = z;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\entities\projectiles\MiniHollowModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */