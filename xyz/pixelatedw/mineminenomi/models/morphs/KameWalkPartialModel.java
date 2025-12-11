/*    */ package xyz.pixelatedw.mineminenomi.models.morphs;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.renderer.model.Model;
/*    */ import net.minecraft.client.renderer.model.ModelRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.HandSide;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphModel;
/*    */ 
/*    */ public class KameWalkPartialModel<T extends LivingEntity>
/*    */   extends MorphModel<T> {
/*    */   private final ModelRenderer shell;
/*    */   
/*    */   public KameWalkPartialModel() {
/* 17 */     super(1.0F);
/* 18 */     this.field_78090_t = 64;
/* 19 */     this.field_78089_u = 64;
/*    */     
/* 21 */     this.shell = new ModelRenderer((Model)this);
/* 22 */     this.shell.func_78793_a(0.0F, 9.5F, 1.0F);
/* 23 */     this.shell.func_78784_a(0, 18).func_228303_a_(-5.5F, -2.5F, 0.0F, 11.0F, 3.0F, 2.0F, 0.0F, false);
/* 24 */     this.shell.func_78784_a(0, 13).func_228303_a_(-5.5F, 8.5F, 0.0F, 11.0F, 3.0F, 2.0F, 0.0F, false);
/* 25 */     this.shell.func_78784_a(10, 23).func_228303_a_(-5.5F, 0.5F, 0.0F, 3.0F, 8.0F, 2.0F, 0.0F, false);
/* 26 */     this.shell.func_78784_a(0, 23).func_228303_a_(2.5F, 0.5F, 0.0F, 3.0F, 8.0F, 2.0F, 0.0F, false);
/* 27 */     this.shell.func_78784_a(0, 0).func_228303_a_(-4.5F, -1.5F, 2.0F, 9.0F, 12.0F, 1.0F, 0.0F, false);
/* 28 */     this.shell.func_78784_a(20, 0).func_228303_a_(-3.5F, -0.5F, 3.0F, 7.0F, 10.0F, 1.0F, 0.0F, false);
/*    */     
/* 30 */     this.field_78115_e = this.shell;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 36 */     matrixStack.func_227860_a_();
/* 37 */     float scale = 1.25F;
/* 38 */     matrixStack.func_227862_a_(scale, scale, scale);
/* 39 */     this.shell.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 40 */     matrixStack.func_227865_b_();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 46 */     super.func_225597_a_((LivingEntity)entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 51 */     modelRenderer.field_78795_f = x;
/* 52 */     modelRenderer.field_78796_g = y;
/* 53 */     modelRenderer.field_78808_h = z;
/*    */   }
/*    */   
/*    */   public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*    */   
/*    */   public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\morphs\KameWalkPartialModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */