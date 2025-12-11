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
/*    */ public class BaraCarPartialModel<T extends LivingEntity> extends MorphModel<T> {
/*    */   private final ModelRenderer Head;
/*    */   private final ModelRenderer Body;
/*    */   private final ModelRenderer RightArm;
/*    */   private final ModelRenderer LeftArm;
/*    */   private final ModelRenderer RightLeg;
/*    */   private final ModelRenderer LeftLeg;
/*    */   
/*    */   public BaraCarPartialModel() {
/* 21 */     super(1.0F);
/* 22 */     this.field_78090_t = 64;
/* 23 */     this.field_78089_u = 64;
/*    */     
/* 25 */     this.Head = new ModelRenderer((Model)this);
/* 26 */     this.Head.func_78793_a(0.0F, 19.0F, 0.0F);
/* 27 */     this.Head.func_78784_a(0, 0).func_228303_a_(-4.0F, -9.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
/* 28 */     this.Head.func_78784_a(32, 0).func_228303_a_(-4.0F, -9.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.5F, false);
/*    */     
/* 30 */     this.Body = new ModelRenderer((Model)this);
/* 31 */     this.Body.func_78793_a(0.0F, 21.0F, -1.0F);
/* 32 */     setRotationAngle(this.Body, 1.5708F, 0.0F, 0.0F);
/* 33 */     this.Body.func_78784_a(16, 16).func_228303_a_(-4.0F, -6.0F, -1.0F, 8.0F, 12.0F, 4.0F, 0.0F, false);
/* 34 */     this.Body.func_78784_a(16, 32).func_228303_a_(-4.0F, -6.0F, -1.0F, 8.0F, 12.0F, 4.0F, 0.25F, false);
/*    */     
/* 36 */     this.RightArm = new ModelRenderer((Model)this);
/* 37 */     this.RightArm.func_78793_a(-5.0F, 21.0F, -6.0F);
/* 38 */     setRotationAngle(this.RightArm, -1.309F, 0.0F, 0.0F);
/* 39 */     this.RightArm.func_78784_a(40, 16).func_228303_a_(-3.0F, -2.1736F, -2.9848F, 4.0F, 12.0F, 4.0F, 0.0F, false);
/* 40 */     this.RightArm.func_78784_a(40, 32).func_228303_a_(-3.0F, -2.1736F, -2.9848F, 4.0F, 12.0F, 4.0F, 0.25F, false);
/*    */     
/* 42 */     this.LeftArm = new ModelRenderer((Model)this);
/* 43 */     this.LeftArm.func_78793_a(5.0F, 22.0F, -6.0F);
/* 44 */     setRotationAngle(this.LeftArm, -1.309F, 0.0F, 0.0F);
/* 45 */     this.LeftArm.func_78784_a(40, 16).func_228303_a_(-1.0F, -2.1736F, -3.9848F, 4.0F, 12.0F, 4.0F, 0.0F, false);
/* 46 */     this.LeftArm.func_78784_a(40, 32).func_228303_a_(-1.0F, -2.1736F, -3.9848F, 4.0F, 12.0F, 4.0F, 0.25F, false);
/*    */ 
/*    */ 
/*    */     
/* 50 */     this.RightLeg = new ModelRenderer((Model)this);
/* 51 */     this.RightLeg.func_78793_a(-3.9F, 18.0F, 4.0F);
/* 52 */     setRotationAngle(this.RightLeg, 1.7453F, -0.1745F, 0.1745F);
/* 53 */     this.RightLeg.func_78784_a(0, 16).func_228303_a_(-1.271F, 0.2007F, -4.0354F, 4.0F, 12.0F, 4.0F, 0.0F, false);
/* 54 */     this.RightLeg.func_78784_a(0, 32).func_228303_a_(-1.271F, 0.2007F, -4.0354F, 4.0F, 12.0F, 4.0F, 0.25F, false);
/*    */     
/* 56 */     this.LeftLeg = new ModelRenderer((Model)this);
/* 57 */     this.LeftLeg.func_78793_a(3.9F, 18.0F, 4.0F);
/* 58 */     setRotationAngle(this.LeftLeg, 1.7453F, 0.2618F, -0.1745F);
/* 59 */     this.LeftLeg.func_78784_a(0, 16).func_228303_a_(-2.7323F, 0.2153F, -4.038F, 4.0F, 12.0F, 4.0F, 0.0F, false);
/* 60 */     this.LeftLeg.func_78784_a(0, 32).func_228303_a_(-2.7323F, 0.2153F, -4.038F, 4.0F, 12.0F, 4.0F, 0.25F, false);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 74 */     this.Head.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/* 75 */     this.Body.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/* 76 */     this.RightArm.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/* 77 */     this.RightLeg.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/* 78 */     this.LeftArm.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/* 79 */     this.LeftLeg.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 84 */     modelRenderer.field_78795_f = x;
/* 85 */     modelRenderer.field_78796_g = y;
/* 86 */     modelRenderer.field_78808_h = z;
/*    */   }
/*    */   
/*    */   public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*    */   
/*    */   public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\morphs\BaraCarPartialModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */