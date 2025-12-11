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
/*    */ public class EntityArmModel
/*    */   extends EntityModel
/*    */ {
/*    */   public final ModelRenderer entityArm;
/*    */   
/*    */   public EntityArmModel() {
/* 19 */     this(false);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityArmModel(boolean smallArms) {
/* 24 */     this.field_78090_t = 64;
/* 25 */     this.field_78089_u = 64;
/*    */     
/* 27 */     this.entityArm = new ModelRenderer((Model)this);
/* 28 */     this.entityArm.func_78793_a(0.0F, 0.0F, 0.0F);
/* 29 */     setRotationAngle(this.entityArm, -1.5708F, 0.0F, 0.0F);
/* 30 */     if (smallArms) {
/* 31 */       this.entityArm.func_78784_a(40, 16).func_228303_a_(-1.0F, -6.0F, -2.0F, 3.0F, 12.0F, 4.0F, 0.0F, true);
/*    */     } else {
/* 33 */       this.entityArm.func_78784_a(40, 16).func_228303_a_(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, true);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 39 */     this.entityArm.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225597_a_(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 49 */     modelRenderer.field_78795_f = x;
/* 50 */     modelRenderer.field_78796_g = y;
/* 51 */     modelRenderer.field_78808_h = z;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\abilities\EntityArmModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */