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
/*    */ public class TridentModel
/*    */   extends EntityModel
/*    */ {
/*    */   private final ModelRenderer spear;
/*    */   private final ModelRenderer tip;
/*    */   private final ModelRenderer spearTip1;
/*    */   private final ModelRenderer spearTip2;
/*    */   private final ModelRenderer spearTip3;
/*    */   private final ModelRenderer spearTip4;
/*    */   private final ModelRenderer spearTip5;
/*    */   private final ModelRenderer spearTip6;
/*    */   private final ModelRenderer spearTip7;
/*    */   
/*    */   public TridentModel() {
/* 27 */     this.field_78090_t = 128;
/* 28 */     this.field_78089_u = 32;
/* 29 */     this.spear = new ModelRenderer((Model)this);
/* 30 */     this.spear.func_78793_a(0.0F, 0.0F, 0.0F);
/* 31 */     this.spear.func_78784_a(0, 0).func_228303_a_(-0.5F, -0.5F, -10.0F, 1.0F, 1.0F, 30.0F, 0.0F, false);
/* 32 */     this.spear.func_78784_a(0, 0).func_228303_a_(-1.0F, -1.0F, -8.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
/* 33 */     this.spear.func_78784_a(0, 5).func_228303_a_(-3.0F, -0.5F, -12.0F, 6.0F, 1.0F, 2.0F, 0.0F, false);
/*    */     
/* 35 */     this.tip = new ModelRenderer((Model)this);
/* 36 */     this.tip.func_78793_a(0.0F, 14.0F, -5.0F);
/* 37 */     this.spear.func_78792_a(this.tip);
/*    */     
/* 39 */     this.spearTip1 = new ModelRenderer((Model)this);
/* 40 */     this.spearTip1.func_78793_a(0.0F, -14.0F, -9.0F);
/* 41 */     this.tip.func_78792_a(this.spearTip1);
/* 42 */     this.spearTip1.func_78784_a(0, 9).func_228303_a_(-0.5F, -0.5F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
/*    */     
/* 44 */     this.spearTip2 = new ModelRenderer((Model)this);
/* 45 */     this.spearTip2.func_78793_a(0.4659F, -14.0F, -8.462F);
/* 46 */     this.tip.func_78792_a(this.spearTip2);
/* 47 */     setRotationAngle(this.spearTip2, 0.0F, 0.1745F, 0.0F);
/* 48 */     this.spearTip2.func_78784_a(0, 15).func_228303_a_(-0.5F, -0.5F, -2.5F, 1.0F, 1.0F, 5.0F, 0.01F, false);
/*    */     
/* 50 */     this.spearTip3 = new ModelRenderer((Model)this);
/* 51 */     this.spearTip3.func_78793_a(-0.4659F, -14.0F, -8.462F);
/* 52 */     this.tip.func_78792_a(this.spearTip3);
/* 53 */     setRotationAngle(this.spearTip3, 0.0F, -0.1745F, 0.0F);
/* 54 */     this.spearTip3.func_78784_a(0, 22).func_228303_a_(-0.5F, -0.5F, -2.5F, 1.0F, 1.0F, 5.0F, 0.02F, false);
/*    */     
/* 56 */     this.spearTip4 = new ModelRenderer((Model)this);
/* 57 */     this.spearTip4.func_78793_a(-2.5F, -14.0F, -7.5F);
/* 58 */     this.tip.func_78792_a(this.spearTip4);
/* 59 */     setRotationAngle(this.spearTip4, 3.1416F, 1.5708F, 3.1416F);
/* 60 */     this.spearTip4.func_78784_a(13, 19).func_228303_a_(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, 0.01F, false);
/*    */     
/* 62 */     this.spearTip5 = new ModelRenderer((Model)this);
/* 63 */     this.spearTip5.func_78793_a(-2.1881F, -14.0F, -8.7687F);
/* 64 */     this.tip.func_78792_a(this.spearTip5);
/* 65 */     setRotationAngle(this.spearTip5, 0.0F, 1.2217F, 0.0F);
/* 66 */     this.spearTip5.func_78784_a(18, 19).func_228303_a_(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/*    */     
/* 68 */     this.spearTip6 = new ModelRenderer((Model)this);
/* 69 */     this.spearTip6.func_78793_a(2.5F, -14.0F, -7.5F);
/* 70 */     this.tip.func_78792_a(this.spearTip6);
/* 71 */     setRotationAngle(this.spearTip6, 3.1416F, 1.5708F, 3.1416F);
/* 72 */     this.spearTip6.func_78784_a(13, 22).func_228303_a_(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, 0.01F, false);
/*    */     
/* 74 */     this.spearTip7 = new ModelRenderer((Model)this);
/* 75 */     this.spearTip7.func_78793_a(2.1881F, -14.0F, -8.7687F);
/* 76 */     this.tip.func_78792_a(this.spearTip7);
/* 77 */     setRotationAngle(this.spearTip7, 0.0F, -1.2217F, 0.0F);
/* 78 */     this.spearTip7.func_78784_a(18, 22).func_228303_a_(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225597_a_(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRotationAngle(ModelRenderer model, float x, float y, float z) {
/* 89 */     model.field_78795_f = x;
/* 90 */     model.field_78796_g = y;
/* 91 */     model.field_78808_h = z;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
/* 97 */     this.spear.func_228309_a_(matrixStack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\entities\projectiles\TridentModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */