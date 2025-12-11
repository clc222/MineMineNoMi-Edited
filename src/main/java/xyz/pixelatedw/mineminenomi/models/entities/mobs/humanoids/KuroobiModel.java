/*    */ package xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids;
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.renderer.model.Model;
/*    */ import net.minecraft.client.renderer.model.ModelRenderer;
/*    */ import net.minecraft.entity.CreatureEntity;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.arlongpirates.KuroobiEntity;
/*    */ 
/*    */ public class KuroobiModel extends HumanoidModel<KuroobiEntity> {
/*    */   private final ModelRenderer hair;
/*    */   
/*    */   public KuroobiModel() {
/* 15 */     super(64, 32);
/*    */     
/* 17 */     this.hair = new ModelRenderer((Model)this);
/* 18 */     this.hair.func_78793_a(0.0F, 0.0F, 0.0F);
/* 19 */     this.field_78116_c.func_78792_a(this.hair);
/* 20 */     this.hair.func_78784_a(0, 0).func_228303_a_(-2.5F, -10.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/* 21 */     this.hair.func_78784_a(0, 0).func_228303_a_(1.5F, -10.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/* 22 */     this.hair.func_78784_a(51, 0).func_228303_a_(-0.5F, 0.0F, 3.0F, 1.0F, 10.0F, 1.0F, 0.0F, false);
/*    */     
/* 24 */     this.rightBlade = new ModelRenderer((Model)this);
/* 25 */     this.rightBlade.func_78793_a(0.0F, 0.0F, 0.0F);
/* 26 */     this.field_178723_h.func_78792_a(this.rightBlade);
/* 27 */     setRotationAngle(this.rightBlade, -0.7854F, 0.0F, 0.0F);
/* 28 */     this.rightBlade.func_78784_a(50, 5).func_228303_a_(-1.5F, -1.25F, 1.0F, 1.0F, 5.0F, 6.0F, 0.0F, false);
/*    */     
/* 30 */     this.leftBlade = new ModelRenderer((Model)this);
/* 31 */     this.leftBlade.func_78793_a(0.0F, 0.0F, 0.0F);
/* 32 */     this.field_178724_i.func_78792_a(this.leftBlade);
/* 33 */     setRotationAngle(this.leftBlade, -0.7854F, 0.0F, 0.0F);
/* 34 */     this.leftBlade.func_78784_a(50, 5).func_228303_a_(0.5F, -1.25F, 1.0F, 1.0F, 5.0F, 6.0F, 0.0F, false);
/*    */   }
/*    */   private final ModelRenderer rightBlade; private final ModelRenderer leftBlade;
/*    */   
/*    */   public void setupAnim(KuroobiEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
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


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\entities\mobs\humanoids\KuroobiModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */