/*    */ package xyz.pixelatedw.mineminenomi.models.entities.projectiles;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.client.renderer.model.Model;
/*    */ import net.minecraft.client.renderer.model.ModelRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.bara.BaraFestivalEntity;
/*    */ 
/*    */ public class BaraFestivalModel<T extends BaraFestivalEntity>
/*    */   extends EntityModel<T> {
/*    */   private final ModelRenderer limb1;
/*    */   private final ModelRenderer limb2;
/*    */   private final ModelRenderer limb3;
/*    */   private final ModelRenderer limb4;
/*    */   
/*    */   public BaraFestivalModel() {
/* 19 */     this.field_78090_t = 64;
/* 20 */     this.field_78089_u = 64;
/*    */     
/* 22 */     this.limb1 = new ModelRenderer((Model)this);
/* 23 */     this.limb1.func_78793_a(0.0F, 24.0F, 0.0F);
/* 24 */     this.limb1.func_78784_a(0, 16).func_228303_a_(-18.0F, -12.0F, -6.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
/*    */     
/* 26 */     this.limb2 = new ModelRenderer((Model)this);
/* 27 */     this.limb2.func_78793_a(0.0F, 24.0F, 0.0F);
/* 28 */     this.limb2.func_78784_a(0, 16).func_228303_a_(4.0F, -12.0F, 12.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
/*    */     
/* 30 */     this.limb3 = new ModelRenderer((Model)this);
/* 31 */     this.limb3.func_78793_a(0.0F, 24.0F, 0.0F);
/* 32 */     this.limb3.func_78784_a(40, 16).func_228303_a_(-4.0F, -12.0F, -20.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
/*    */     
/* 34 */     this.limb4 = new ModelRenderer((Model)this);
/* 35 */     this.limb4.func_78793_a(0.0F, 24.0F, 0.0F);
/* 36 */     this.limb4.func_78784_a(40, 16).func_228303_a_(16.0F, -12.0F, 2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 42 */     this.limb1.field_78796_g = (float)(this.limb1.field_78796_g + Math.sin(ageInTicks * 0.02D) / 10.0D);
/* 43 */     this.limb1.field_78808_h = (float)(this.limb1.field_78808_h + Math.sin(ageInTicks * 0.1D) / 3.0D);
/*    */     
/* 45 */     this.limb2.field_78796_g = (float)(this.limb2.field_78796_g + Math.cos(ageInTicks * 0.2D) / 10.0D);
/* 46 */     this.limb2.field_78795_f = (float)(this.limb2.field_78795_f + Math.sin(ageInTicks * 0.1D) / 3.0D);
/*    */     
/* 48 */     this.limb3.field_78796_g = (float)(this.limb3.field_78796_g - Math.cos(ageInTicks * 0.02D) / 10.0D);
/* 49 */     this.limb3.field_78808_h = (float)(this.limb3.field_78808_h + Math.sin(ageInTicks * 0.1D) / 3.0D);
/*    */     
/* 51 */     this.limb4.field_78796_g = (float)(this.limb4.field_78796_g - Math.sin(ageInTicks * 0.5D) / 10.0D);
/* 52 */     this.limb4.field_78795_f = (float)(this.limb4.field_78795_f - Math.sin(ageInTicks * 0.1D) / 3.0D);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 58 */     this.limb1.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/* 59 */     this.limb2.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/* 60 */     this.limb3.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/* 61 */     this.limb4.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 66 */     modelRenderer.field_78795_f = x;
/* 67 */     modelRenderer.field_78796_g = y;
/* 68 */     modelRenderer.field_78808_h = z;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\entities\projectiles\BaraFestivalModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */