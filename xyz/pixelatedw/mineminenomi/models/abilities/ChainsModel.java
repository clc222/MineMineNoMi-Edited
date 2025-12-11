/*    */ package xyz.pixelatedw.mineminenomi.models.abilities;
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
/*    */ public class ChainsModel
/*    */   extends EntityModel
/*    */ {
/*    */   public ModelRenderer shape15;
/*    */   
/*    */   public ChainsModel() {
/* 19 */     this.field_78090_t = 64;
/* 20 */     this.field_78089_u = 64;
/* 21 */     this.shape15 = new ModelRenderer((Model)this, 0, 0);
/* 22 */     this.shape15.func_78793_a(0.0F, 5.8F, 0.0F);
/* 23 */     this.shape15.func_228301_a_(-9.0F, 0.0F, -6.0F, 18.0F, 5.0F, 12.0F, 0.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 29 */     this.shape15.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225597_a_(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
/* 40 */     model.field_78795_f = x;
/* 41 */     model.field_78796_g = y;
/* 42 */     model.field_78808_h = z;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\abilities\ChainsModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */