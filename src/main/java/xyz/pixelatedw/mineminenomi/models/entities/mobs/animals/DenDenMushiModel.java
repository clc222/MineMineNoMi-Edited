/*    */ package xyz.pixelatedw.mineminenomi.models.entities.mobs.animals;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.model.Model;
/*    */ import net.minecraft.client.renderer.model.ModelRenderer;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ 
/*    */ @OnlyIn(Dist.CLIENT)
/*    */ public class DenDenMushiModel
/*    */   extends BipedModel
/*    */ {
/*    */   private final ModelRenderer shape1;
/*    */   private final ModelRenderer shape2;
/*    */   private final ModelRenderer shape3;
/*    */   private final ModelRenderer shape4;
/*    */   private final ModelRenderer shape5;
/*    */   private final ModelRenderer shape6;
/*    */   private final ModelRenderer shape7;
/*    */   
/*    */   public DenDenMushiModel() {
/* 24 */     super(1.0F);
/* 25 */     this.field_78090_t = 64;
/* 26 */     this.field_78089_u = 64;
/*    */     
/* 28 */     this.shape1 = new ModelRenderer((Model)this);
/* 29 */     this.shape1.func_78793_a(0.0F, 23.0F, 0.0F);
/* 30 */     this.shape1.func_78784_a(0, 0).func_228303_a_(-2.0F, 0.0F, -3.0F, 5.0F, 1.0F, 9.0F, 0.0F, true);
/*    */     
/* 32 */     this.shape2 = new ModelRenderer((Model)this);
/* 33 */     this.shape2.func_78793_a(0.0F, 23.0F, 0.0F);
/* 34 */     this.shape2.func_78784_a(21, 11).func_228303_a_(-1.0F, -3.0F, -3.0F, 3.0F, 3.0F, 3.0F, 0.0F, true);
/*    */     
/* 36 */     this.shape3 = new ModelRenderer((Model)this);
/* 37 */     this.shape3.func_78793_a(0.0F, 23.0F, 0.0F);
/* 38 */     this.shape3.func_78784_a(0, 11).func_228303_a_(-2.0F, -5.0F, 0.0F, 5.0F, 5.0F, 5.0F, 0.0F, true);
/*    */     
/* 40 */     this.shape4 = new ModelRenderer((Model)this);
/* 41 */     this.shape4.func_78793_a(0.0F, 23.0F, 0.0F);
/* 42 */     this.shape4.func_78784_a(29, 0).func_228303_a_(-1.0F, -5.0F, -2.0F, 1.0F, 2.0F, 1.0F, 0.0F, true);
/*    */     
/* 44 */     this.shape5 = new ModelRenderer((Model)this);
/* 45 */     this.shape5.func_78793_a(0.0F, 23.0F, 0.0F);
/* 46 */     this.shape5.func_78784_a(34, 3).func_228303_a_(-1.6667F, -7.0F, -2.5F, 2.0F, 2.0F, 2.0F, 0.0F, true);
/*    */     
/* 48 */     this.shape6 = new ModelRenderer((Model)this);
/* 49 */     this.shape6.func_78793_a(0.0F, 23.0F, 0.0F);
/* 50 */     this.shape6.func_78784_a(29, 0).func_228303_a_(1.0F, -5.0F, -2.0F, 1.0F, 2.0F, 1.0F, 0.0F, true);
/*    */     
/* 52 */     this.shape7 = new ModelRenderer((Model)this);
/* 53 */     this.shape7.func_78793_a(0.0F, 23.0F, 0.0F);
/* 54 */     this.shape7.func_78784_a(34, 3).func_228303_a_(0.7F, -7.0F, -2.5F, 2.0F, 2.0F, 2.0F, 0.0F, true);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 60 */     matrixStack.func_227860_a_();
/* 61 */     matrixStack.func_227861_a_(0.0D, 0.0D, -0.1D);
/* 62 */     this.shape1.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 63 */     this.shape2.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 64 */     this.shape3.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 65 */     this.shape4.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 66 */     this.shape5.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 67 */     this.shape6.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 68 */     this.shape7.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 69 */     matrixStack.func_227865_b_();
/*    */   }
/*    */ 
/*    */   
/*    */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 74 */     model.field_78795_f = x;
/* 75 */     model.field_78796_g = y;
/* 76 */     model.field_78808_h = z;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\entities\mobs\animals\DenDenMushiModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */