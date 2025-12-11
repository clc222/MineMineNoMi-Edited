/*    */ package xyz.pixelatedw.mineminenomi.models.armors;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.model.Model;
/*    */ import net.minecraft.client.renderer.model.ModelRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ 
/*    */ public class CabajiScarfModel<T extends LivingEntity>
/*    */   extends BipedModel<T> {
/*    */   private final ModelRenderer scarfbase;
/*    */   
/*    */   public CabajiScarfModel() {
/* 17 */     super(1.0F);
/* 18 */     this.field_78090_t = 64;
/* 19 */     this.field_78089_u = 64;
/*    */     
/* 21 */     this.scarfbase = new ModelRenderer((Model)this);
/* 22 */     this.scarfbase.func_78793_a(0.0F, 0.0F, 0.0F);
/* 23 */     this.scarfbase.func_78784_a(0, 0).func_228303_a_(-5.0F, -3.0F, -5.0F, 10.0F, 4.0F, 10.0F, 0.0F, false);
/*    */     
/* 25 */     this.scarftail = new ModelRenderer((Model)this);
/* 26 */     this.scarftail.func_78793_a(1.75F, -0.75F, 3.75F);
/* 27 */     this.scarfbase.func_78792_a(this.scarftail);
/* 28 */     this.scarftail.func_78784_a(14, 14).func_228303_a_(-3.0F, 0.0F, 0.0F, 6.0F, 11.0F, 1.0F, 0.0F, false);
/*    */     
/* 30 */     this.scarftail2 = new ModelRenderer((Model)this);
/* 31 */     this.scarftail2.func_78793_a(0.0F, 11.0F, 0.0F);
/* 32 */     this.scarftail.func_78792_a(this.scarftail2);
/* 33 */     this.scarftail2.func_78784_a(0, 14).func_228303_a_(-3.0F, 0.0F, 0.0F, 6.0F, 11.0F, 1.0F, 0.0F, false);
/*    */   }
/*    */   private final ModelRenderer scarftail; private final ModelRenderer scarftail2;
/*    */   
/*    */   public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 38 */     double dist = entity.func_70092_e(((LivingEntity)entity).field_70169_q, ((LivingEntity)entity).field_70167_r, ((LivingEntity)entity).field_70166_s);
/* 39 */     if (dist > 0.0D && dist <= 0.02D) {
/* 40 */       dist += 0.02D;
/*    */     }
/* 42 */     double angle = MathHelper.func_151237_a(dist * 1000.0D - 1.0D, 0.0D, 70.0D);
/* 43 */     boolean isMoving = (dist > 0.02D);
/* 44 */     if (isMoving) {
/* 45 */       angle += (MathHelper.func_76126_a((float)MathHelper.func_219803_d(angle, ((LivingEntity)entity).field_70141_P, ((LivingEntity)entity).field_70140_Q)) * 6.0F);
/*    */     }
/*    */     
/* 48 */     if (((LivingEntity)entity).field_70125_A >= 45.0F) {
/* 49 */       float headRotation = 90.0F - ((LivingEntity)entity).field_70125_A;
/* 50 */       angle += (headRotation - 45.0F);
/*    */     }
/* 52 */     else if (((LivingEntity)entity).field_70125_A < -10.0F) {
/* 53 */       float headRotation = 90.0F - ((LivingEntity)entity).field_70125_A;
/* 54 */       angle += (headRotation - 85.0F);
/*    */     } 
/*    */     
/* 57 */     this.scarftail.field_78795_f = (float)Math.toRadians(angle);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 62 */     this.scarfbase.func_217177_a(this.field_78116_c);
/* 63 */     this.scarfbase.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*    */   }
/*    */   
/*    */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 67 */     modelRenderer.field_78795_f = x;
/* 68 */     modelRenderer.field_78796_g = y;
/* 69 */     modelRenderer.field_78808_h = z;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\armors\CabajiScarfModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */