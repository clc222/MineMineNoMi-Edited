/*    */ package xyz.pixelatedw.mineminenomi.models.entities.projectiles;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.client.renderer.model.Model;
/*    */ import net.minecraft.client.renderer.model.ModelRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.TopEntity;
/*    */ 
/*    */ public class TopModel
/*    */   extends EntityModel<TopEntity> {
/*    */   public TopModel() {
/* 14 */     this.field_78090_t = 16;
/* 15 */     this.field_78089_u = 16;
/*    */     
/* 17 */     this.top = new ModelRenderer((Model)this);
/* 18 */     this.top.func_78793_a(0.0F, 0.0F, 0.0F);
/* 19 */     this.top.func_78784_a(0, 0).func_228303_a_(-1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
/* 20 */     this.top.func_78784_a(0, 3).func_228303_a_(-0.5F, -1.25F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*    */   }
/*    */   private final ModelRenderer top;
/*    */   
/*    */   public void setupAnim(TopEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 25 */     this.top.field_78796_g += (entity.field_70173_aa % 365);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 30 */     this.top.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*    */   }
/*    */   
/*    */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 34 */     modelRenderer.field_78795_f = x;
/* 35 */     modelRenderer.field_78796_g = y;
/* 36 */     modelRenderer.field_78808_h = z;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\entities\projectiles\TopModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */