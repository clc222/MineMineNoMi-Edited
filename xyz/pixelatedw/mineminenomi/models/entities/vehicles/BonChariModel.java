/*    */ package xyz.pixelatedw.mineminenomi.models.entities.vehicles;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.client.renderer.model.Model;
/*    */ import net.minecraft.client.renderer.model.ModelRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ 
/*    */ public class BonChariModel extends EntityModel<Entity> {
/*    */   private final ModelRenderer bubble;
/*    */   private final ModelRenderer seat;
/*    */   private final ModelRenderer seat2;
/*    */   private final ModelRenderer thruster;
/*    */   private final ModelRenderer bone;
/*    */   
/*    */   public BonChariModel() {
/* 18 */     this.field_78090_t = 64;
/* 19 */     this.field_78089_u = 64;
/*    */     
/* 21 */     this.bubble = new ModelRenderer((Model)this);
/* 22 */     this.bubble.func_78793_a(0.0F, 1.0F, 0.0F);
/* 23 */     this.bubble.func_78784_a(0, 0).func_228303_a_(-8.0F, -8.0F, -8.0F, 16.0F, 16.0F, 16.0F, -0.01F, false);
/*    */     
/* 25 */     this.seat = new ModelRenderer((Model)this);
/* 26 */     this.seat.func_78793_a(0.0F, 9.0F, 0.0F);
/* 27 */     this.bubble.func_78792_a(this.seat);
/* 28 */     this.seat.func_78784_a(0, 32).func_228303_a_(-2.0F, -1.0F, -8.0F, 4.0F, 2.0F, 16.0F, 0.0F, false);
/*    */     
/* 30 */     this.seat2 = new ModelRenderer((Model)this);
/* 31 */     this.seat2.func_78793_a(0.0F, -5.5F, 9.0F);
/* 32 */     this.seat.func_78792_a(this.seat2);
/* 33 */     this.seat2.func_78784_a(0, 0).func_228303_a_(-2.0F, -6.5F, -1.0F, 4.0F, 13.0F, 2.0F, 0.0F, false);
/*    */     
/* 35 */     this.thruster = new ModelRenderer((Model)this);
/* 36 */     this.thruster.func_78793_a(0.5F, -2.25F, 3.0F);
/* 37 */     this.seat2.func_78792_a(this.thruster);
/* 38 */     this.thruster.func_78784_a(0, 39).func_228303_a_(-2.0F, -2.0F, -2.0F, 3.0F, 3.0F, 2.0F, 0.0F, false);
/* 39 */     this.thruster.func_78784_a(0, 32).func_228303_a_(-3.0F, -3.0F, -0.75F, 5.0F, 5.0F, 2.0F, 0.0F, false);
/* 40 */     this.thruster.func_78784_a(24, 32).func_228303_a_(-4.5F, -4.75F, 0.5F, 8.0F, 8.0F, 3.0F, 0.0F, false);
/*    */     
/* 42 */     this.bone = new ModelRenderer((Model)this);
/* 43 */     this.bone.func_78793_a(0.0F, 0.0F, 0.0F);
/* 44 */     this.bubble.func_78792_a(this.bone);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225597_a_(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 55 */     this.bubble.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*    */   }
/*    */   
/*    */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 59 */     modelRenderer.field_78795_f = x;
/* 60 */     modelRenderer.field_78796_g = y;
/* 61 */     modelRenderer.field_78808_h = z;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\entities\vehicles\BonChariModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */