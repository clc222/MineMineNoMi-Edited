/*     */ package xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ 
/*     */ 
/*     */ public class CelestialDragonSlaveRideModel<T extends LivingEntity>
/*     */   extends BipedModel<T>
/*     */ {
/*     */   private final ModelRenderer head;
/*     */   private final ModelRenderer nose;
/*     */   private final ModelRenderer headwear;
/*     */   private final ModelRenderer headwear2;
/*     */   private final ModelRenderer body;
/*     */   
/*     */   public CelestialDragonSlaveRideModel() {
/*  23 */     super(1.0F);
/*  24 */     this.field_78090_t = 64;
/*  25 */     this.field_78089_u = 64;
/*     */     
/*  27 */     this.head = new ModelRenderer((Model)this);
/*  28 */     this.head.func_78793_a(0.0F, 17.0F, -9.0F);
/*  29 */     setRotationAngle(this.head, 0.2618F, 0.0F, 0.0F);
/*  30 */     this.head.func_78784_a(0, 0).func_228303_a_(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, 0.0F, false);
/*     */     
/*  32 */     this.nose = new ModelRenderer((Model)this);
/*  33 */     this.nose.func_78793_a(0.0F, 15.0F, -9.5F);
/*  34 */     setRotationAngle(this.nose, 0.2618F, 0.0F, 0.0F);
/*  35 */     this.nose.func_78784_a(24, 0).func_228303_a_(-1.0F, -1.0F, -6.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
/*     */     
/*  37 */     this.headwear = new ModelRenderer((Model)this);
/*  38 */     this.headwear.func_78793_a(0.0F, 17.0F, -9.0F);
/*  39 */     setRotationAngle(this.headwear, 0.2618F, 0.0F, 0.0F);
/*  40 */     this.headwear.func_78784_a(32, 0).func_228303_a_(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, 0.51F, false);
/*     */     
/*  42 */     this.headwear2 = new ModelRenderer((Model)this);
/*  43 */     this.headwear2.func_78793_a(0.0F, 17.0F, -9.0F);
/*  44 */     setRotationAngle(this.headwear2, -1.309F, 0.0F, 0.0F);
/*  45 */     this.headwear2.func_78784_a(30, 47).func_228303_a_(-8.0F, -8.0F, -6.0F, 16.0F, 16.0F, 1.0F, 0.0F, false);
/*     */     
/*  47 */     this.body = new ModelRenderer((Model)this);
/*  48 */     this.body.func_78793_a(0.0F, 17.0F, -7.0F);
/*  49 */     setRotationAngle(this.body, 1.5708F, 0.0F, 0.0F);
/*  50 */     this.body.func_78784_a(16, 20).func_228303_a_(-4.0F, 0.0F, -3.0F, 8.0F, 12.0F, 6.0F, 0.0F, false);
/*     */     
/*  52 */     this.bodywear = new ModelRenderer((Model)this);
/*  53 */     this.bodywear.func_78793_a(0.0F, 17.0F, -7.0F);
/*  54 */     setRotationAngle(this.bodywear, 1.5708F, 0.0F, 0.0F);
/*  55 */     this.bodywear.func_78784_a(0, 38).func_228303_a_(-4.0F, 0.0F, -3.0F, 8.0F, 20.0F, 6.0F, 0.5F, false);
/*     */     
/*  57 */     this.arms = new ModelRenderer((Model)this);
/*  58 */     this.arms.func_78793_a(0.0F, 18.95F, -7.05F);
/*  59 */     setRotationAngle(this.arms, -0.7505F, 0.0F, 0.0F);
/*  60 */     this.arms.func_78784_a(40, 38).func_228303_a_(-4.0F, 2.0F, -2.0F, 8.0F, 4.0F, 4.0F, 0.0F, false);
/*  61 */     this.arms.func_78784_a(44, 22).func_228303_a_(-8.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);
/*     */     
/*  63 */     this.mirrored = new ModelRenderer((Model)this);
/*  64 */     this.mirrored.func_78793_a(0.0F, 21.05F, 1.05F);
/*  65 */     this.arms.func_78792_a(this.mirrored);
/*  66 */     this.mirrored.func_78784_a(44, 22).func_228303_a_(4.0F, -23.05F, -3.05F, 4.0F, 8.0F, 4.0F, 0.0F, true);
/*     */     
/*  68 */     this.right_leg = new ModelRenderer((Model)this);
/*  69 */     this.right_leg.func_78793_a(-3.0F, 22.0F, 1.0F);
/*  70 */     setRotationAngle(this.right_leg, 1.5708F, 0.0F, 0.0F);
/*  71 */     this.right_leg.func_78784_a(0, 22).func_228303_a_(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
/*     */     
/*  73 */     this.left_leg = new ModelRenderer((Model)this);
/*  74 */     this.left_leg.func_78793_a(3.0F, 22.0F, 1.0F);
/*  75 */     setRotationAngle(this.left_leg, 1.5708F, 0.0F, 0.0F);
/*  76 */     this.left_leg.func_78784_a(0, 22).func_228303_a_(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
/*     */   }
/*     */   private final ModelRenderer bodywear; private final ModelRenderer arms; private final ModelRenderer mirrored; private final ModelRenderer right_leg; private final ModelRenderer left_leg;
/*     */   
/*     */   public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/*  81 */     float f = 1.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  87 */     limbSwing *= 2.0F;
/*  88 */     limbSwingAmount *= 2.0F;
/*     */     
/*  90 */     this.arms.field_78798_e = -7.0F + MathHelper.func_76134_b(limbSwing * 0.5F + 3.1415927F) * 1.6F * limbSwingAmount;
/*  91 */     this.right_leg.field_78798_e = MathHelper.func_76134_b(limbSwing * 0.96F + 3.1415927F) * 2.6F * limbSwingAmount;
/*  92 */     this.left_leg.field_78798_e = MathHelper.func_76134_b(limbSwing * 0.96F) * 2.6F * limbSwingAmount;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/*  97 */     this.head.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*  98 */     this.nose.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*  99 */     this.headwear.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 100 */     this.headwear2.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 101 */     this.body.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 102 */     this.bodywear.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 103 */     this.arms.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 104 */     this.right_leg.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 105 */     this.left_leg.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */   }
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 109 */     modelRenderer.field_78795_f = x;
/* 110 */     modelRenderer.field_78796_g = y;
/* 111 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\entities\mobs\humanoids\CelestialDragonSlaveRideModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */