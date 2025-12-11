/*    */ package xyz.pixelatedw.mineminenomi.models.armors;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.model.Model;
/*    */ import net.minecraft.client.renderer.model.ModelRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ 
/*    */ public class JangoMushroomModel<T extends LivingEntity>
/*    */   extends BipedModel<T> {
/*    */   public JangoMushroomModel() {
/* 14 */     super(1.0F);
/* 15 */     this.field_78090_t = 64;
/* 16 */     this.field_78089_u = 64;
/*    */     
/* 18 */     this.hair = new ModelRenderer((Model)this);
/* 19 */     this.hair.func_78793_a(0.0F, 0.0F, 0.0F);
/*    */ 
/*    */     
/* 22 */     this.hair2 = new ModelRenderer((Model)this);
/* 23 */     this.hair2.func_78793_a(0.0F, 0.0F, -3.5F);
/* 24 */     this.hair.func_78792_a(this.hair2);
/* 25 */     setRotationAngle(this.hair2, -0.4363F, 0.0F, 0.0F);
/* 26 */     this.hair2.func_78784_a(0, 0).func_228303_a_(-1.0F, -1.0F, -1.0F, 2.0F, 4.0F, 2.0F, -0.2F, false);
/* 27 */     this.hair2.func_78784_a(0, 0).func_228303_a_(-1.0F, 2.5F, -1.0F, 2.0F, 1.0F, 2.0F, -0.3F, false);
/*    */   }
/*    */ 
/*    */   
/*    */   private final ModelRenderer hair;
/*    */   private final ModelRenderer hair2;
/*    */   
/*    */   public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*    */   
/*    */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 37 */     this.hair.func_217177_a(this.field_78116_c);
/* 38 */     this.hair.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*    */   }
/*    */   
/*    */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 42 */     modelRenderer.field_78795_f = x;
/* 43 */     modelRenderer.field_78796_g = y;
/* 44 */     modelRenderer.field_78808_h = z;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\armors\JangoMushroomModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */