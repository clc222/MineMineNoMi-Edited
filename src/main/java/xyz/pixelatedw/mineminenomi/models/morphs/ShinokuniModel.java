/*     */ package xyz.pixelatedw.mineminenomi.models.morphs;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.renderer.entity.model.IHasArm;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.HandSide;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphModel;
/*     */ 
/*     */ public class ShinokuniModel<T extends LivingEntity>
/*     */   extends MorphModel<T> implements IHasArm {
/*     */   private final ModelRenderer body;
/*     */   private final ModelRenderer neck;
/*     */   private final ModelRenderer leftArm;
/*     */   private final ModelRenderer leftArm2;
/*     */   private final ModelRenderer rightArm;
/*     */   private final ModelRenderer rightArm2;
/*     */   
/*     */   public ShinokuniModel() {
/*  25 */     super(1.0F);
/*  26 */     this.field_78090_t = 128;
/*  27 */     this.field_78089_u = 128;
/*     */     
/*  29 */     this.body = new ModelRenderer((Model)this);
/*  30 */     this.body.func_78793_a(1.5F, 17.0F, -2.0F);
/*  31 */     this.body.func_78784_a(46, 0).func_228303_a_(-7.0F, -6.0F, -1.0F, 11.0F, 3.0F, 6.0F, 0.0F, false);
/*  32 */     this.body.func_78784_a(42, 27).func_228303_a_(-8.0F, -11.0F, -2.0F, 13.0F, 5.0F, 8.0F, 0.0F, false);
/*  33 */     this.body.func_78784_a(0, 16).func_228303_a_(-9.0F, -19.0F, -3.0F, 15.0F, 9.0F, 10.0F, 0.0F, false);
/*  34 */     this.body.func_78784_a(40, 16).func_228303_a_(-8.0F, -22.0F, -0.5F, 13.0F, 3.0F, 7.0F, 0.0F, false);
/*  35 */     this.body.func_78784_a(52, 51).func_228303_a_(-7.5F, -21.0F, -2.5F, 12.0F, 2.0F, 2.0F, 0.0F, false);
/*  36 */     this.body.func_78784_a(42, 40).func_228303_a_(-8.0F, -3.0F, -2.0F, 13.0F, 3.0F, 8.0F, 0.0F, false);
/*  37 */     this.body.func_78784_a(0, 35).func_228303_a_(-9.0F, 0.0F, -3.0F, 15.0F, 3.0F, 10.0F, 0.0F, false);
/*  38 */     this.body.func_78784_a(0, 0).func_228303_a_(-10.0F, 3.0F, -4.0F, 17.0F, 4.0F, 12.0F, 0.0F, false);
/*     */     
/*  40 */     this.neck = new ModelRenderer((Model)this);
/*  41 */     this.neck.func_78793_a(-1.0F, -19.0F, 3.0F);
/*  42 */     this.body.func_78792_a(this.neck);
/*  43 */     setRotationAngle(this.neck, 0.48F, 0.0F, 0.0F);
/*  44 */     this.neck.func_78784_a(36, 74).func_228303_a_(-2.5F, -5.0F, -5.5F, 4.0F, 5.0F, 4.0F, 0.0F, false);
/*     */     
/*  46 */     this.leftArm = new ModelRenderer((Model)this);
/*  47 */     this.leftArm.func_78793_a(7.0F, -1.0F, 1.0F);
/*  48 */     setRotationAngle(this.leftArm, 0.0F, 0.0F, 1.2217F);
/*  49 */     this.leftArm.func_78784_a(0, 48).func_228303_a_(-0.4739F, -4.8191F, -5.0F, 7.0F, 7.0F, 8.0F, 0.0F, true);
/*     */     
/*  51 */     this.leftArm2 = new ModelRenderer((Model)this);
/*  52 */     this.leftArm2.func_78793_a(12.3118F, -1.3905F, 0.0F);
/*  53 */     this.leftArm.func_78792_a(this.leftArm2);
/*  54 */     this.leftArm2.func_78784_a(0, 69).func_228303_a_(-5.7857F, -2.4286F, -4.0F, 9.0F, 5.0F, 1.0F, 0.0F, true);
/*  55 */     this.leftArm2.func_78784_a(0, 80).func_228303_a_(-5.7857F, -2.4286F, 1.0F, 11.0F, 4.0F, 1.0F, 0.0F, true);
/*  56 */     this.leftArm2.func_78784_a(0, 86).func_228303_a_(-5.7857F, 1.5714F, -3.0F, 9.0F, 1.0F, 2.0F, 0.0F, true);
/*  57 */     this.leftArm2.func_78784_a(0, 66).func_228303_a_(-5.7857F, 1.5714F, -1.0F, 10.0F, 1.0F, 2.0F, 0.0F, true);
/*  58 */     this.leftArm2.func_78784_a(0, 63).func_228303_a_(-5.7857F, -2.4286F, -3.0F, 10.0F, 1.0F, 2.0F, 0.0F, true);
/*  59 */     this.leftArm2.func_78784_a(0, 76).func_228303_a_(-5.7857F, -2.4286F, -1.0F, 11.0F, 1.0F, 2.0F, 0.0F, true);
/*  60 */     this.leftArm2.func_78784_a(0, 90).func_228303_a_(0.2143F, -1.4286F, -2.5F, 7.0F, 3.0F, 3.0F, 0.0F, true);
/*     */     
/*  62 */     this.rightArm = new ModelRenderer((Model)this);
/*  63 */     this.rightArm.func_78793_a(-7.0F, -1.0F, 1.0F);
/*  64 */     setRotationAngle(this.rightArm, 0.0F, 0.0F, -1.2217F);
/*  65 */     this.rightArm.func_78784_a(0, 48).func_228303_a_(-6.5261F, -4.8191F, -5.0F, 7.0F, 7.0F, 8.0F, 0.0F, false);
/*     */     
/*  67 */     this.rightArm2 = new ModelRenderer((Model)this);
/*  68 */     this.rightArm2.func_78793_a(-12.3118F, -1.3905F, 0.0F);
/*  69 */     this.rightArm.func_78792_a(this.rightArm2);
/*  70 */     this.rightArm2.func_78784_a(0, 69).func_228303_a_(-3.2143F, -2.4286F, -4.0F, 9.0F, 5.0F, 1.0F, 0.0F, false);
/*  71 */     this.rightArm2.func_78784_a(0, 80).func_228303_a_(-5.2143F, -2.4286F, 1.0F, 11.0F, 4.0F, 1.0F, 0.0F, false);
/*  72 */     this.rightArm2.func_78784_a(0, 86).func_228303_a_(-3.2143F, 1.5714F, -3.0F, 9.0F, 1.0F, 2.0F, 0.0F, false);
/*  73 */     this.rightArm2.func_78784_a(0, 66).func_228303_a_(-4.2143F, 1.5714F, -1.0F, 10.0F, 1.0F, 2.0F, 0.0F, false);
/*  74 */     this.rightArm2.func_78784_a(0, 63).func_228303_a_(-4.2143F, -2.4286F, -3.0F, 10.0F, 1.0F, 2.0F, 0.0F, false);
/*  75 */     this.rightArm2.func_78784_a(0, 76).func_228303_a_(-5.2143F, -2.4286F, -1.0F, 11.0F, 1.0F, 2.0F, 0.0F, false);
/*  76 */     this.rightArm2.func_78784_a(0, 90).func_228303_a_(-7.2143F, -1.4286F, -2.5F, 7.0F, 3.0F, 3.0F, 0.0F, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/*  82 */     this.body.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
/*  83 */     this.leftArm.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
/*  84 */     this.rightArm.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/*  91 */     float f = 1.0F;
/*  92 */     this.rightArm.field_78796_g = MathHelper.func_76134_b(limbSwing * 0.6F + 3.1415927F) * 0.5F * limbSwingAmount * 0.5F / f;
/*  93 */     this.leftArm.field_78796_g = -MathHelper.func_76134_b(limbSwing * 0.6F) * 0.5F * limbSwingAmount * 0.5F / f;
/*  94 */     if (!entity.func_184614_ca().func_190926_b()) {
/*  95 */       this.rightArm.field_78795_f += -0.15F;
/*     */     }
/*     */     
/*  98 */     this.field_217112_c = ((LivingEntity)entity).field_70733_aJ;
/*  99 */     if (this.field_217112_c > 0.0F) {
/*     */       
/* 101 */       this.body.field_78796_g = MathHelper.func_76126_a(MathHelper.func_76129_c(this.field_217112_c) * 6.2831855F) * 0.2F;
/* 102 */       this.rightArm.field_78798_e = MathHelper.func_76126_a(this.body.field_78796_g) * 5.0F;
/* 103 */       this.rightArm.field_78800_c = -MathHelper.func_76134_b(this.body.field_78796_g) * 8.0F;
/* 104 */       this.leftArm.field_78798_e = -MathHelper.func_76126_a(this.body.field_78796_g) * 5.0F;
/* 105 */       this.leftArm.field_78800_c = MathHelper.func_76134_b(this.body.field_78796_g) * 5.0F;
/* 106 */       this.rightArm.field_78796_g += this.body.field_78796_g;
/* 107 */       this.leftArm.field_78796_g += this.body.field_78796_g;
/* 108 */       this.leftArm.field_78795_f += this.body.field_78796_g;
/* 109 */       float f1 = 1.0F - this.field_217112_c;
/* 110 */       f1 *= f1;
/* 111 */       f1 *= f1;
/* 112 */       f1 = 1.0F - f1;
/* 113 */       float f2 = MathHelper.func_76126_a(f1 * 3.1415927F);
/* 114 */       float f3 = MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * 0.75F;
/* 115 */       this.rightArm.field_78795_f = (float)(this.rightArm.field_78795_f - f2 * 1.5D + f3);
/* 116 */       this.rightArm.field_78796_g += this.body.field_78796_g * 5.0F;
/* 117 */       this.rightArm.field_78808_h += MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * -0.9F;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 123 */     modelRenderer.field_78795_f = x;
/* 124 */     modelRenderer.field_78796_g = y;
/* 125 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
/* 131 */     if (side == HandSide.RIGHT) {
/*     */       
/* 133 */       matrixStack.func_227861_a_(0.2D, 0.3D, 0.0D);
/* 134 */       this.rightArm.func_228309_a_(matrixStack, vertex, packedLight, OverlayTexture.field_229196_a_, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */     }
/*     */     else {
/*     */       
/* 138 */       matrixStack.func_227861_a_(-0.2D, 0.3D, 0.0D);
/* 139 */       this.leftArm.func_228309_a_(matrixStack, vertex, packedLight, OverlayTexture.field_229196_a_, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225599_a_(HandSide side, MatrixStack matrixStack) {
/* 151 */     super.func_225599_a_(side, matrixStack);
/* 152 */     matrixStack.func_227861_a_((side == HandSide.RIGHT) ? -0.6D : 0.6D, -0.5D, -0.2D);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\morphs\ShinokuniModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */