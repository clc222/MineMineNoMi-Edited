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
/*     */ 
/*     */ 
/*     */ public class BicorneModel<T extends LivingEntity>
/*     */   extends BipedModel<T>
/*     */ {
/*     */   private final ModelRenderer hat;
/*     */   private final ModelRenderer topFront;
/*     */   private final ModelRenderer topBack;
/*     */   private final ModelRenderer leftSide;
/*     */   private final ModelRenderer subLeftSide1;
/*     */   
/*     */   public BicorneModel() {
/*  23 */     super(1.0F);
/*  24 */     this.field_78090_t = 64;
/*  25 */     this.field_78089_u = 64;
/*     */     
/*  27 */     this.hat = new ModelRenderer((Model)this);
/*  28 */     this.hat.func_78793_a(0.0F, -2.5F, 0.0F);
/*  29 */     this.hat.func_78784_a(0, 23).func_228303_a_(-9.0F, -8.0F, -4.0F, 18.0F, 2.0F, 8.0F, 0.0F, false);
/*  30 */     this.hat.func_78784_a(0, 11).func_228303_a_(-4.0F, -9.0F, -4.0F, 9.0F, 2.0F, 8.0F, 0.0F, false);
/*  31 */     this.hat.func_78784_a(0, 0).func_228303_a_(-3.0F, -11.0F, -4.0F, 6.0F, 2.0F, 8.0F, 0.0F, false);
/*     */     
/*  33 */     this.topFront = new ModelRenderer((Model)this);
/*  34 */     this.topFront.func_78793_a(0.0F, -6.5F, -0.5F);
/*  35 */     this.hat.func_78792_a(this.topFront);
/*  36 */     this.topFront.func_78784_a(0, 37).func_228303_a_(-4.0F, -5.0F, -4.5F, 8.0F, 6.0F, 1.0F, 0.0F, false);
/*  37 */     this.topFront.func_78784_a(0, 34).func_228303_a_(-4.0F, -6.0F, -4.5F, 8.0F, 1.0F, 1.0F, 0.0F, false);
/*  38 */     this.topFront.func_78784_a(19, 40).func_228303_a_(-6.0F, -2.0F, -4.5F, 2.0F, 3.0F, 1.0F, 0.0F, false);
/*  39 */     this.topFront.func_78784_a(26, 38).func_228303_a_(6.0F, -2.0F, -4.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/*  40 */     this.topFront.func_78784_a(19, 37).func_228303_a_(4.0F, -3.0F, -4.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/*  41 */     this.topFront.func_78784_a(26, 38).func_228303_a_(-8.0F, -2.0F, -4.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/*  42 */     this.topFront.func_78784_a(19, 37).func_228303_a_(-6.0F, -3.0F, -4.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/*  43 */     this.topFront.func_78784_a(19, 40).func_228303_a_(4.0F, -2.0F, -4.5F, 2.0F, 3.0F, 1.0F, 0.0F, false);
/*  44 */     this.topFront.func_78784_a(26, 41).func_228303_a_(-8.0F, -1.0F, -4.5F, 2.0F, 2.0F, 1.0F, 0.0F, false);
/*  45 */     this.topFront.func_78784_a(26, 41).func_228303_a_(6.0F, -1.0F, -4.5F, 2.0F, 2.0F, 1.0F, 0.0F, false);
/*  46 */     this.topFront.func_78784_a(19, 34).func_228303_a_(-5.0F, -5.0F, -4.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*  47 */     this.topFront.func_78784_a(19, 34).func_228303_a_(4.0F, -5.0F, -4.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/*  49 */     this.topBack = new ModelRenderer((Model)this);
/*  50 */     this.topBack.func_78793_a(0.0F, -6.5F, 0.5F);
/*  51 */     this.hat.func_78792_a(this.topBack);
/*  52 */     this.topBack.func_78784_a(0, 37).func_228303_a_(-4.0F, -5.0F, 3.5F, 8.0F, 6.0F, 1.0F, 0.0F, false);
/*  53 */     this.topBack.func_78784_a(19, 40).func_228303_a_(-6.0F, -2.0F, 3.5F, 2.0F, 3.0F, 1.0F, 0.0F, false);
/*  54 */     this.topBack.func_78784_a(19, 40).func_228303_a_(4.0F, -2.0F, 3.5F, 2.0F, 3.0F, 1.0F, 0.0F, false);
/*  55 */     this.topBack.func_78784_a(26, 41).func_228303_a_(-8.0F, -1.0F, 3.5F, 2.0F, 2.0F, 1.0F, 0.0F, false);
/*  56 */     this.topBack.func_78784_a(26, 41).func_228303_a_(6.0F, -1.0F, 3.5F, 2.0F, 2.0F, 1.0F, 0.0F, false);
/*  57 */     this.topBack.func_78784_a(26, 38).func_228303_a_(-8.0F, -2.0F, 3.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/*  58 */     this.topBack.func_78784_a(19, 37).func_228303_a_(-6.0F, -3.0F, 3.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/*  59 */     this.topBack.func_78784_a(0, 34).func_228303_a_(-4.0F, -6.0F, 3.5F, 8.0F, 1.0F, 1.0F, 0.0F, false);
/*  60 */     this.topBack.func_78784_a(19, 37).func_228303_a_(4.0F, -3.0F, 3.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/*  61 */     this.topBack.func_78784_a(19, 34).func_228303_a_(4.0F, -5.0F, 3.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*  62 */     this.topBack.func_78784_a(19, 34).func_228303_a_(-5.0F, -5.0F, 3.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*  63 */     this.topBack.func_78784_a(26, 38).func_228303_a_(6.0F, -2.0F, 3.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/*  65 */     this.leftSide = new ModelRenderer((Model)this);
/*  66 */     this.leftSide.func_78793_a(8.75F, -8.0F, 4.0F);
/*  67 */     this.hat.func_78792_a(this.leftSide);
/*     */ 
/*     */     
/*  70 */     this.subLeftSide1 = new ModelRenderer((Model)this);
/*  71 */     this.subLeftSide1.func_78793_a(0.0F, 0.0F, -8.0F);
/*  72 */     this.leftSide.func_78792_a(this.subLeftSide1);
/*  73 */     setRotationAngle(this.subLeftSide1, 0.0F, -0.7854F, 0.0F);
/*  74 */     this.subLeftSide1.func_78784_a(33, 41).func_228303_a_(-1.35F, 0.5F, -0.2F, 3.0F, 2.0F, 1.0F, 0.0F, false);
/*  75 */     this.subLeftSide1.func_78784_a(33, 38).func_228303_a_(-1.35F, -0.5F, -0.2F, 3.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/*  77 */     this.subLeftSide3 = new ModelRenderer((Model)this);
/*  78 */     this.subLeftSide3.func_78793_a(-0.1061F, 0.0F, -0.3182F);
/*  79 */     this.leftSide.func_78792_a(this.subLeftSide3);
/*  80 */     setRotationAngle(this.subLeftSide3, 0.0F, 0.7854F, 0.0F);
/*  81 */     this.subLeftSide3.func_78784_a(33, 41).func_228303_a_(-1.5F, 0.5F, -0.5F, 3.0F, 2.0F, 1.0F, 0.0F, false);
/*  82 */     this.subLeftSide3.func_78784_a(33, 38).func_228303_a_(-1.5F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/*  84 */     this.subLeftSide2 = new ModelRenderer((Model)this);
/*  85 */     this.subLeftSide2.func_78793_a(0.75F, 0.0F, -1.0F);
/*  86 */     this.leftSide.func_78792_a(this.subLeftSide2);
/*  87 */     setRotationAngle(this.subLeftSide2, 0.0F, 1.5708F, 0.0F);
/*  88 */     this.subLeftSide2.func_78784_a(42, 41).func_228303_a_(-0.05F, 0.5F, -0.55F, 6.0F, 2.0F, 1.0F, 0.0F, false);
/*  89 */     this.subLeftSide2.func_78784_a(42, 38).func_228303_a_(-0.05F, -0.5F, -0.55F, 6.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/*  91 */     this.rightSide = new ModelRenderer((Model)this);
/*  92 */     this.rightSide.func_78793_a(-8.75F, -8.0F, 4.0F);
/*  93 */     this.hat.func_78792_a(this.rightSide);
/*     */ 
/*     */     
/*  96 */     this.rightSide1 = new ModelRenderer((Model)this);
/*  97 */     this.rightSide1.func_78793_a(0.0F, 0.0F, -8.0F);
/*  98 */     this.rightSide.func_78792_a(this.rightSide1);
/*  99 */     setRotationAngle(this.rightSide1, 0.0F, 0.7854F, 0.0F);
/* 100 */     this.rightSide1.func_78784_a(33, 41).func_228303_a_(-1.65F, 0.5F, -0.2F, 3.0F, 2.0F, 1.0F, 0.0F, true);
/* 101 */     this.rightSide1.func_78784_a(33, 38).func_228303_a_(-1.65F, -0.5F, -0.2F, 3.0F, 1.0F, 1.0F, 0.0F, true);
/*     */     
/* 103 */     this.rightSide2 = new ModelRenderer((Model)this);
/* 104 */     this.rightSide2.func_78793_a(0.1061F, 0.0F, -0.3182F);
/* 105 */     this.rightSide.func_78792_a(this.rightSide2);
/* 106 */     setRotationAngle(this.rightSide2, 0.0F, -0.7854F, 0.0F);
/* 107 */     this.rightSide2.func_78784_a(33, 41).func_228303_a_(-1.5F, 0.5F, -0.5F, 3.0F, 2.0F, 1.0F, 0.0F, true);
/* 108 */     this.rightSide2.func_78784_a(33, 38).func_228303_a_(-1.5F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, 0.0F, true);
/*     */     
/* 110 */     this.rightSide3 = new ModelRenderer((Model)this);
/* 111 */     this.rightSide3.func_78793_a(-0.75F, 0.0F, -1.0F);
/* 112 */     this.rightSide.func_78792_a(this.rightSide3);
/* 113 */     setRotationAngle(this.rightSide3, 0.0F, -1.5708F, 0.0F);
/* 114 */     this.rightSide3.func_78784_a(42, 41).func_228303_a_(-5.95F, 0.5F, -0.55F, 6.0F, 2.0F, 1.0F, 0.0F, true);
/* 115 */     this.rightSide3.func_78784_a(42, 38).func_228303_a_(-5.95F, -0.5F, -0.55F, 6.0F, 1.0F, 1.0F, 0.0F, true);
/*     */   }
/*     */   private final ModelRenderer subLeftSide3; private final ModelRenderer subLeftSide2; private final ModelRenderer rightSide;
/*     */   private final ModelRenderer rightSide1;
/*     */   private final ModelRenderer rightSide2;
/*     */   private final ModelRenderer rightSide3;
/*     */   
/*     */   public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 125 */     this.hat.func_217177_a(this.field_78116_c);
/* 126 */     this.hat.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */   }
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 130 */     modelRenderer.field_78795_f = x;
/* 131 */     modelRenderer.field_78796_g = y;
/* 132 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\armors\BicorneModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */