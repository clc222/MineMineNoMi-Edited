/*    */ package xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids;
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.renderer.model.Model;
/*    */ import net.minecraft.client.renderer.model.ModelRenderer;
/*    */ import net.minecraft.entity.CreatureEntity;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.arlongpirates.ArlongEntity;
/*    */ 
/*    */ public class ArlongModel extends HumanoidModel<ArlongEntity> {
/*    */   private final ModelRenderer nose;
/*    */   
/*    */   public ArlongModel() {
/* 15 */     super(64, 32);
/*    */     
/* 17 */     this.nose = new ModelRenderer((Model)this);
/* 18 */     this.nose.func_78793_a(0.0F, -0.25F, 0.0F);
/* 19 */     this.field_78116_c.func_78792_a(this.nose);
/*    */ 
/*    */     
/* 22 */     this.saw = new ModelRenderer((Model)this);
/* 23 */     this.saw.func_78793_a(0.0F, -3.5F, -7.25F);
/* 24 */     this.nose.func_78792_a(this.saw);
/* 25 */     setRotationAngle(this.saw, 0.0F, -0.7854F, 0.0F);
/* 26 */     this.saw.func_78784_a(0, 0).func_228303_a_(-1.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, -0.001F, false);
/* 27 */     this.saw.func_78784_a(0, 0).func_228303_a_(0.0F, -0.5F, 0.0F, 2.0F, 1.0F, 2.0F, 0.0F, true);
/* 28 */     this.saw.func_78784_a(0, 0).func_228303_a_(1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 2.0F, 0.001F, false);
/*    */     
/* 30 */     this.backBlade = new ModelRenderer((Model)this);
/* 31 */     this.backBlade.func_78793_a(-0.5F, -5.0F, -1.5F);
/* 32 */     this.field_78115_e.func_78792_a(this.backBlade);
/* 33 */     setRotationAngle(this.backBlade, -0.7854F, 0.0F, 0.0F);
/* 34 */     this.backBlade.func_78784_a(33, 0).func_228303_a_(0.0F, 0.0F, 5.0F, 1.0F, 7.0F, 8.0F, 0.0F, false);
/*    */   }
/*    */   private final ModelRenderer saw; private final ModelRenderer backBlade;
/*    */   
/*    */   public void setupAnim(ArlongEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 39 */     super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 44 */     super.func_225598_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 49 */     modelRenderer.field_78795_f = x;
/* 50 */     modelRenderer.field_78796_g = y;
/* 51 */     modelRenderer.field_78808_h = z;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\entities\mobs\humanoids\ArlongModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */