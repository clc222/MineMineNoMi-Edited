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
/*    */ public class AbareHimatsuriModel
/*    */   extends EntityModel
/*    */ {
/*    */   public ModelRenderer platform1;
/*    */   public ModelRenderer platform2;
/*    */   
/*    */   public AbareHimatsuriModel() {
/* 20 */     this.field_78090_t = 64;
/* 21 */     this.field_78089_u = 64;
/* 22 */     this.platform2 = new ModelRenderer((Model)this, 0, 0);
/* 23 */     this.platform2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 24 */     this.platform2.func_228301_a_(-10.0F, 10.0F, -10.0F, 20.0F, 10.0F, 20.0F, 0.0F);
/* 25 */     this.platform1 = new ModelRenderer((Model)this, 0, 0);
/* 26 */     this.platform1.func_78793_a(0.0F, 0.0F, 0.0F);
/* 27 */     this.platform1.func_228301_a_(-15.0F, 0.0F, -15.0F, 30.0F, 10.0F, 30.0F, 0.0F);
/* 28 */     this.platform1.func_78792_a(this.platform2);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 34 */     this.platform1.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225597_a_(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
/* 44 */     model.field_78795_f = x;
/* 45 */     model.field_78796_g = y;
/* 46 */     model.field_78808_h = z;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\abilities\AbareHimatsuriModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */