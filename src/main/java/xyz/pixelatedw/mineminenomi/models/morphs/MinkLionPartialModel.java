/*    */ package xyz.pixelatedw.mineminenomi.models.morphs;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.renderer.model.Model;
/*    */ import net.minecraft.client.renderer.model.ModelRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.HandSide;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphModel;
/*    */ 
/*    */ public class MinkLionPartialModel<T extends LivingEntity>
/*    */   extends MorphModel<T> {
/*    */   private final ModelRenderer mane;
/*    */   private final ModelRenderer mane2;
/*    */   private final ModelRenderer tailBase;
/*    */   private final ModelRenderer tail;
/*    */   private final ModelRenderer tail2;
/*    */   private final ModelRenderer tailTip;
/*    */   
/*    */   public MinkLionPartialModel() {
/* 23 */     super(1.0F);
/* 24 */     this.field_78090_t = 64;
/* 25 */     this.field_78089_u = 64;
/*    */     
/* 27 */     this.mane = new ModelRenderer((Model)this);
/* 28 */     this.mane.func_78793_a(0.0F, 6.5F, 1.0F);
/* 29 */     this.mane.func_78784_a(0, 15).func_228303_a_(-6.5F, -10.5F, -2.0F, 13.0F, 13.0F, 4.0F, 0.0F, false);
/*    */     
/* 31 */     this.mane2 = new ModelRenderer((Model)this);
/* 32 */     this.mane2.func_78793_a(4.5F, -0.5F, -1.0F);
/* 33 */     this.mane.func_78792_a(this.mane2);
/* 34 */     this.mane2.func_78784_a(0, 15).func_228303_a_(-10.0F, -9.0F, -2.0F, 11.0F, 11.0F, 6.0F, 0.0F, false);
/*    */     
/* 36 */     this.tail = new ModelRenderer((Model)this);
/* 37 */     this.tail.func_78793_a(0.0F, 11.0F, 1.0F);
/* 38 */     setRotationAngle(this.tail, 0.3054F, 0.0F, 0.0F);
/* 39 */     this.tail.func_78784_a(0, 0).func_228303_a_(-0.5F, -0.2456F, 0.653F, 1.0F, 1.0F, 3.0F, 0.0F, false);
/*    */     
/* 41 */     this.tail2 = new ModelRenderer((Model)this);
/* 42 */     this.tail2.func_78793_a(0.0F, 1.3592F, 3.0808F);
/* 43 */     this.tail.func_78792_a(this.tail2);
/* 44 */     setRotationAngle(this.tail2, -0.7417F, 0.0F, 0.0F);
/* 45 */     this.tail2.func_78784_a(0, 6).func_228303_a_(-0.5F, -1.5592F, -0.5808F, 1.0F, 1.0F, 4.0F, -0.01F, false);
/*    */     
/* 47 */     this.tailTip = new ModelRenderer((Model)this);
/* 48 */     this.tailTip.func_78793_a(0.0F, -0.135F, 3.2472F);
/* 49 */     this.tail2.func_78792_a(this.tailTip);
/* 50 */     setRotationAngle(this.tailTip, -0.1309F, 0.0F, 0.0F);
/* 51 */     this.tailTip.func_78784_a(11, 0).func_228303_a_(-1.0F, -1.8743F, 0.0F, 2.0F, 2.0F, 3.0F, 0.0F, false);
/*    */     
/* 53 */     this.tailBase = new ModelRenderer((Model)this);
/* 54 */     this.tailBase.func_78793_a(0.0F, 11.0F, 1.0F);
/* 55 */     this.tailBase.func_78792_a(this.tail);
/* 56 */     setRotationAngle(this.tailBase, 0.3054F, 0.0F, 0.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 62 */     this.mane.func_217177_a(this.field_78116_c);
/* 63 */     this.mane.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/* 64 */     this.tailBase.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 70 */     super.func_225597_a_((LivingEntity)entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/*    */     
/* 72 */     this.tailBase.func_217177_a(this.field_78115_e);
/*    */     
/* 74 */     this.tail.field_78796_g = (float)(Math.sin(ageInTicks * 0.06D) / 10.0D);
/* 75 */     this.tail.field_78795_f = (float)(-this.field_78115_e.field_78795_f + Math.sin(ageInTicks * 0.02D) / 5.0D);
/*    */     
/* 77 */     if (entity.func_213453_ef()) {
/* 78 */       this.tail.field_78795_f = -0.2F;
/* 79 */       this.tail.field_78797_d = 9.5F;
/*    */     } 
/* 81 */     if (entity instanceof PlayerEntity && ((PlayerEntity)entity).field_71075_bZ.field_75100_b) {
/* 82 */       double posXDiff = Math.abs(((LivingEntity)entity).field_70169_q - entity.func_226277_ct_());
/* 83 */       double posZDiff = Math.abs(((LivingEntity)entity).field_70166_s - entity.func_226281_cx_());
/* 84 */       if (posXDiff >= 0.2D || posZDiff >= 0.2D) {
/* 85 */         this.tail.field_78795_f = -0.2F;
/*    */       }
/*    */     } else {
/*    */       
/* 89 */       this.tail.field_78797_d = 10.0F;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 95 */     modelRenderer.field_78795_f = x;
/* 96 */     modelRenderer.field_78796_g = y;
/* 97 */     modelRenderer.field_78808_h = z;
/*    */   }
/*    */   
/*    */   public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*    */   
/*    */   public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\morphs\MinkLionPartialModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */