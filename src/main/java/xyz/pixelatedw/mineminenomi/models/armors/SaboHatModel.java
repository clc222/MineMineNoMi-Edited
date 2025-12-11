/*     */ package xyz.pixelatedw.mineminenomi.models.armors;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ 
/*     */ public class SaboHatModel<T extends LivingEntity>
/*     */   extends BipedModel<T> {
/*     */   private final ModelRenderer hat;
/*     */   private final ModelRenderer band;
/*     */   private final ModelRenderer goggles;
/*     */   private final ModelRenderer leftGoggle;
/*     */   private final ModelRenderer leftGoggleA;
/*     */   private final ModelRenderer leftGoggleB;
/*     */   private final ModelRenderer leftGoggleC;
/*     */   private final ModelRenderer leftGoggleD;
/*     */   private final ModelRenderer rightGoggle;
/*     */   private final ModelRenderer rightGoggleA;
/*     */   private final ModelRenderer rightGoggleB;
/*     */   private final ModelRenderer rightGoggleC;
/*     */   private final ModelRenderer rightGoggleD;
/*     */   
/*     */   public SaboHatModel() {
/*  28 */     super(1.0F);
/*  29 */     this.field_78090_t = 64;
/*  30 */     this.field_78089_u = 64;
/*     */     
/*  32 */     this.hat = new ModelRenderer((Model)this);
/*  33 */     this.hat.func_78793_a(0.0F, -3.0F, 0.0F);
/*  34 */     this.hat.func_78784_a(0, 0).func_228303_a_(-7.0F, -6.0F, -7.0F, 14.0F, 1.0F, 14.0F, 0.0F, false);
/*  35 */     this.hat.func_78784_a(0, 16).func_228303_a_(-4.0F, -12.0F, -4.0F, 8.0F, 6.0F, 8.0F, 0.5F, false);
/*  36 */     this.hat.func_78784_a(0, 16).func_228303_a_(-4.0F, -18.25F, -4.0F, 8.0F, 6.0F, 8.0F, 0.55F, false);
/*     */     
/*  38 */     this.band = new ModelRenderer((Model)this);
/*  39 */     this.band.func_78793_a(-0.5F, -9.75F, -3.875F);
/*  40 */     this.hat.func_78792_a(this.band);
/*  41 */     this.band.func_78784_a(0, 44).func_228303_a_(-5.0F, -0.5F, -0.925F, 11.0F, 2.0F, 10.0F, -0.3F, false);
/*     */     
/*  43 */     this.goggles = new ModelRenderer((Model)this);
/*  44 */     this.goggles.func_78793_a(0.0F, 0.0F, 0.0F);
/*  45 */     this.hat.func_78792_a(this.goggles);
/*     */     
/*  47 */     this.leftGoggle = new ModelRenderer((Model)this);
/*  48 */     this.leftGoggle.func_78793_a(-3.5F, -8.75F, -4.75F);
/*  49 */     this.goggles.func_78792_a(this.leftGoggle);
/*  50 */     this.leftGoggle.func_78784_a(0, 33).func_228303_a_(-1.54F, -1.9F, -0.5F, 1.0F, 3.0F, 1.0F, -0.01F, false);
/*  51 */     this.leftGoggle.func_78784_a(0, 33).func_228303_a_(2.85F, -1.9F, -0.5F, 1.0F, 3.0F, 1.0F, -0.01F, false);
/*  52 */     this.leftGoggle.func_78784_a(0, 38).func_228303_a_(-1.75F, -4.15F, 0.1F, 6.0F, 7.0F, 0.0F, 0.001F, false);
/*     */     
/*  54 */     this.leftGoggleA = new ModelRenderer((Model)this);
/*  55 */     this.leftGoggleA.func_78793_a(0.0305F, -2.1176F, 0.0F);
/*  56 */     this.leftGoggle.func_78792_a(this.leftGoggleA);
/*  57 */     setRotationAngle(this.leftGoggleA, 0.0F, 0.0F, 1.1345F);
/*  58 */     this.leftGoggleA.func_78784_a(0, 33).func_228303_a_(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, -0.01F, false);
/*     */     
/*  60 */     this.leftGoggleB = new ModelRenderer((Model)this);
/*  61 */     this.leftGoggleB.func_78793_a(1.85F, -2.47F, 0.0F);
/*  62 */     this.leftGoggle.func_78792_a(this.leftGoggleB);
/*  63 */     setRotationAngle(this.leftGoggleB, 0.0F, 0.0F, -1.1345F);
/*  64 */     this.leftGoggleB.func_78784_a(0, 33).func_228303_a_(-0.6F, -0.93F, -0.5F, 1.0F, 3.0F, 1.0F, -0.01F, false);
/*     */     
/*  66 */     this.leftGoggleC = new ModelRenderer((Model)this);
/*  67 */     this.leftGoggleC.func_78793_a(-0.2195F, 1.3824F, 0.0F);
/*  68 */     this.leftGoggle.func_78792_a(this.leftGoggleC);
/*  69 */     setRotationAngle(this.leftGoggleC, 0.0F, 0.0F, -1.1345F);
/*  70 */     this.leftGoggleC.func_78784_a(0, 33).func_228303_a_(-0.3105F, -1.2824F, -0.5F, 1.0F, 3.0F, 1.0F, -0.01F, false);
/*     */     
/*  72 */     this.leftGoggleD = new ModelRenderer((Model)this);
/*  73 */     this.leftGoggleD.func_78793_a(2.2514F, 1.4398F, 0.0F);
/*  74 */     this.leftGoggle.func_78792_a(this.leftGoggleD);
/*  75 */     setRotationAngle(this.leftGoggleD, 0.0F, 0.0F, 1.1345F);
/*  76 */     this.leftGoggleD.func_78784_a(0, 33).func_228303_a_(-0.6014F, -1.5898F, -0.5F, 1.0F, 3.0F, 1.0F, -0.01F, false);
/*     */     
/*  78 */     this.rightGoggle = new ModelRenderer((Model)this);
/*  79 */     this.rightGoggle.func_78793_a(1.0F, -8.75F, -4.75F);
/*  80 */     this.goggles.func_78792_a(this.rightGoggle);
/*  81 */     this.rightGoggle.func_78784_a(0, 33).func_228303_a_(-1.54F, -1.9F, -0.5F, 1.0F, 3.0F, 1.0F, -0.011F, false);
/*  82 */     this.rightGoggle.func_78784_a(0, 33).func_228303_a_(2.85F, -1.9F, -0.5F, 1.0F, 3.0F, 1.0F, -0.011F, false);
/*  83 */     this.rightGoggle.func_78784_a(0, 38).func_228303_a_(-1.75F, -4.15F, 0.1F, 6.0F, 7.0F, 0.0F, 0.0F, false);
/*     */     
/*  85 */     this.rightGoggleA = new ModelRenderer((Model)this);
/*  86 */     this.rightGoggleA.func_78793_a(0.0305F, -2.1176F, 0.0F);
/*  87 */     this.rightGoggle.func_78792_a(this.rightGoggleA);
/*  88 */     setRotationAngle(this.rightGoggleA, 0.0F, 0.0F, 1.1345F);
/*  89 */     this.rightGoggleA.func_78784_a(0, 33).func_228303_a_(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, -0.011F, false);
/*     */     
/*  91 */     this.rightGoggleB = new ModelRenderer((Model)this);
/*  92 */     this.rightGoggleB.func_78793_a(1.85F, -2.47F, 0.0F);
/*  93 */     this.rightGoggle.func_78792_a(this.rightGoggleB);
/*  94 */     setRotationAngle(this.rightGoggleB, 0.0F, 0.0F, -1.1345F);
/*  95 */     this.rightGoggleB.func_78784_a(0, 33).func_228303_a_(-0.6F, -0.93F, -0.5F, 1.0F, 3.0F, 1.0F, -0.011F, false);
/*     */     
/*  97 */     this.rightGoggleC = new ModelRenderer((Model)this);
/*  98 */     this.rightGoggleC.func_78793_a(-0.2195F, 1.3824F, 0.0F);
/*  99 */     this.rightGoggle.func_78792_a(this.rightGoggleC);
/* 100 */     setRotationAngle(this.rightGoggleC, 0.0F, 0.0F, -1.1345F);
/* 101 */     this.rightGoggleC.func_78784_a(0, 33).func_228303_a_(-0.3105F, -1.2824F, -0.5F, 1.0F, 3.0F, 1.0F, -0.011F, false);
/*     */     
/* 103 */     this.rightGoggleD = new ModelRenderer((Model)this);
/* 104 */     this.rightGoggleD.func_78793_a(2.2514F, 1.4398F, 0.0F);
/* 105 */     this.rightGoggle.func_78792_a(this.rightGoggleD);
/* 106 */     setRotationAngle(this.rightGoggleD, 0.0F, 0.0F, 1.1345F);
/* 107 */     this.rightGoggleD.func_78784_a(0, 33).func_228303_a_(-0.6014F, -1.5898F, -0.5F, 1.0F, 3.0F, 1.0F, -0.011F, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 118 */     this.hat.func_217177_a(this.field_78116_c);
/* 119 */     this.hat.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 124 */     modelRenderer.field_78795_f = x;
/* 125 */     modelRenderer.field_78796_g = y;
/* 126 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\armors\SaboHatModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */