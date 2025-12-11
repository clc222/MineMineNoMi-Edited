/*    */ package xyz.pixelatedw.mineminenomi.models.abilities;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.client.renderer.model.Model;
/*    */ import net.minecraft.client.renderer.model.ModelRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.TornadoEntity;
/*    */ 
/*    */ public class TornadoModel
/*    */   extends EntityModel<TornadoEntity> {
/*    */   private final ModelRenderer base;
/*    */   private final ModelRenderer funnel1;
/*    */   private final ModelRenderer funnel2;
/*    */   private final ModelRenderer funnel3;
/*    */   private final ModelRenderer top;
/*    */   
/*    */   public TornadoModel() {
/* 20 */     this.field_78090_t = 64;
/* 21 */     this.field_78089_u = 64;
/*    */     
/* 23 */     this.base = new ModelRenderer((Model)this);
/* 24 */     this.base.func_78793_a(0.5F, 21.5F, 0.5F);
/* 25 */     this.base.func_78784_a(0, 42).func_228303_a_(-1.5F, -2.5F, -1.5F, 3.0F, 5.0F, 3.0F, 0.0F, false);
/*    */     
/* 27 */     this.funnel1 = new ModelRenderer((Model)this);
/* 28 */     this.funnel1.func_78793_a(0.0F, -4.5F, 0.0F);
/* 29 */     this.base.func_78792_a(this.funnel1);
/* 30 */     this.funnel1.func_78784_a(0, 33).func_228303_a_(-2.5F, -2.0F, -2.5F, 5.0F, 4.0F, 5.0F, 0.0F, false);
/*    */     
/* 32 */     this.funnel2 = new ModelRenderer((Model)this);
/* 33 */     this.funnel2.func_78793_a(0.0F, -5.0F, 0.0F);
/* 34 */     this.funnel1.func_78792_a(this.funnel2);
/* 35 */     this.funnel2.func_78784_a(0, 18).func_228303_a_(-4.5F, -3.0F, -4.5F, 9.0F, 6.0F, 9.0F, 0.0F, false);
/*    */     
/* 37 */     this.funnel3 = new ModelRenderer((Model)this);
/* 38 */     this.funnel3.func_78793_a(0.0F, -7.5F, 0.0F);
/* 39 */     this.funnel2.func_78792_a(this.funnel3);
/* 40 */     this.funnel3.func_78784_a(20, 33).func_228303_a_(-5.5F, -4.5F, -5.5F, 11.0F, 9.0F, 11.0F, 0.0F, false);
/*    */     
/* 42 */     this.top = new ModelRenderer((Model)this);
/* 43 */     this.top.func_78793_a(0.0F, -6.0F, 0.0F);
/* 44 */     this.funnel3.func_78792_a(this.top);
/* 45 */     this.top.func_78784_a(0, 0).func_228303_a_(-7.5F, -1.5F, -7.5F, 15.0F, 3.0F, 15.0F, 0.0F, false);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setupAnim(TornadoEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 51 */     this.base.field_78796_g = ageInTicks * 0.1F * entity.getSpeed() % 360.0F;
/* 52 */     this.funnel1.field_78796_g = ageInTicks * -0.2F * entity.getSpeed() % 360.0F;
/* 53 */     this.funnel2.field_78796_g = ageInTicks * 0.2F * entity.getSpeed() % 360.0F;
/* 54 */     this.funnel3.field_78796_g = ageInTicks * -0.2F * entity.getSpeed() % 360.0F;
/* 55 */     this.top.field_78796_g = ageInTicks * 0.2F * entity.getSpeed() % 360.0F;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 61 */     this.base.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 66 */     modelRenderer.field_78795_f = x;
/* 67 */     modelRenderer.field_78796_g = y;
/* 68 */     modelRenderer.field_78808_h = z;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\abilities\TornadoModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */