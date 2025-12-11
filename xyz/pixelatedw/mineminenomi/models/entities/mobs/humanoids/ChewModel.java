/*    */ package xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.renderer.model.Model;
/*    */ import net.minecraft.client.renderer.model.ModelRenderer;
/*    */ import net.minecraft.entity.CreatureEntity;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.arlongpirates.ChewEntity;
/*    */ 
/*    */ public class ChewModel extends HumanoidModel<ChewEntity> {
/*    */   public ChewModel() {
/* 14 */     this.lips = new ModelRenderer((Model)this);
/* 15 */     this.lips.func_78793_a(0.0F, -0.75F, 0.0F);
/* 16 */     setRotationAngle(this.lips, 0.1745F, 0.0F, 0.0F);
/* 17 */     this.lips.func_78784_a(16, 0).func_228303_a_(-0.5F, -1.5F, -5.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/* 18 */     this.lips.func_78784_a(1, 1).func_228303_a_(-1.0F, -2.0F, -6.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
/* 19 */     this.field_78116_c.func_78792_a(this.lips);
/*    */     
/* 21 */     this.field_178724_i = new ModelRenderer((Model)this, 32, 48);
/* 22 */     this.field_178724_i.field_78809_i = true;
/* 23 */     this.field_178724_i.func_228303_a_(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, true);
/* 24 */     this.field_178724_i.func_78793_a(5.0F, 2.0F, 0.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setupAnim(ChewEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 29 */     super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/*    */   }
/*    */   private final ModelRenderer lips;
/*    */   
/*    */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 34 */     super.func_225598_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 39 */     modelRenderer.field_78795_f = x;
/* 40 */     modelRenderer.field_78796_g = y;
/* 41 */     modelRenderer.field_78808_h = z;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\entities\mobs\humanoids\ChewModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */