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
/*    */ public class EntityLegModel
/*    */   extends EntityModel
/*    */ {
/*    */   private final ModelRenderer entityLeg;
/*    */   
/*    */   public EntityLegModel() {
/* 19 */     this.field_78090_t = 64;
/* 20 */     this.field_78089_u = 64;
/*    */     
/* 22 */     this.entityLeg = new ModelRenderer((Model)this);
/* 23 */     this.entityLeg.func_78793_a(-1.9F, 12.0F, 0.0F);
/* 24 */     setRotationAngle(this.entityLeg, -1.5708F, 0.0F, 0.0F);
/* 25 */     this.entityLeg.func_78784_a(0, 16).func_228303_a_(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 31 */     this.entityLeg.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225597_a_(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 41 */     modelRenderer.field_78795_f = x;
/* 42 */     modelRenderer.field_78796_g = y;
/* 43 */     modelRenderer.field_78808_h = z;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\abilities\EntityLegModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */