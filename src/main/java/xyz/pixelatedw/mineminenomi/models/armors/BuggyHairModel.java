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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BuggyHairModel<T extends LivingEntity>
/*     */   extends BipedModel<T>
/*     */ {
/*     */   private final ModelRenderer hair;
/*     */   private final ModelRenderer leftHair;
/*     */   private final ModelRenderer leftHair2;
/*     */   private final ModelRenderer leftHair4;
/*     */   private final ModelRenderer leftHair9;
/*     */   private final ModelRenderer leftHair8;
/*     */   private final ModelRenderer leftHair6;
/*     */   private final ModelRenderer leftHair7;
/*     */   private final ModelRenderer leftHair5;
/*     */   
/*     */   public BuggyHairModel() {
/*  31 */     super(1.0F);
/*  32 */     this.field_78090_t = 32;
/*  33 */     this.field_78089_u = 32;
/*     */     
/*  35 */     this.hair = new ModelRenderer((Model)this);
/*  36 */     this.hair.func_78793_a(0.0F, -2.5F, 0.0F);
/*     */ 
/*     */     
/*  39 */     this.leftHair = new ModelRenderer((Model)this);
/*  40 */     this.leftHair.func_78793_a(4.5F, -7.5F, 0.0F);
/*  41 */     this.hair.func_78792_a(this.leftHair);
/*  42 */     setRotationAngle(this.leftHair, 0.0F, 0.0F, 0.9599F);
/*  43 */     this.leftHair.func_78784_a(0, 11).func_228303_a_(-2.0F, -5.0F, -2.0F, 3.0F, 6.0F, 4.0F, 0.0F, false);
/*     */     
/*  45 */     this.leftHair2 = new ModelRenderer((Model)this);
/*  46 */     this.leftHair2.func_78793_a(0.25F, -4.5F, 0.0F);
/*  47 */     this.leftHair.func_78792_a(this.leftHair2);
/*  48 */     setRotationAngle(this.leftHair2, 0.0F, 0.0F, 0.5236F);
/*  49 */     this.leftHair2.func_78784_a(9, 2).func_228303_a_(-1.85F, -2.9F, -2.0F, 3.0F, 4.0F, 4.0F, 0.0F, false);
/*     */     
/*  51 */     this.leftHair4 = new ModelRenderer((Model)this);
/*  52 */     this.leftHair4.func_78793_a(-0.5F, -1.5F, -0.25F);
/*  53 */     this.leftHair2.func_78792_a(this.leftHair4);
/*  54 */     setRotationAngle(this.leftHair4, 0.2477F, -0.1198F, 0.2333F);
/*  55 */     this.leftHair4.func_78784_a(0, 4).func_228303_a_(-1.35F, -3.9F, -1.5F, 2.0F, 4.0F, 2.0F, 0.0F, false);
/*     */     
/*  57 */     this.leftHair9 = new ModelRenderer((Model)this);
/*  58 */     this.leftHair9.func_78793_a(0.5F, -1.5F, 1.25F);
/*  59 */     this.leftHair2.func_78792_a(this.leftHair9);
/*  60 */     setRotationAngle(this.leftHair9, -0.3768F, 0.0288F, 0.2172F);
/*  61 */     this.leftHair9.func_78784_a(0, 4).func_228303_a_(-1.35F, -3.9F, -1.5F, 2.0F, 4.0F, 2.0F, 0.0F, false);
/*     */     
/*  63 */     this.leftHair8 = new ModelRenderer((Model)this);
/*  64 */     this.leftHair8.func_78793_a(-0.5F, -1.5F, 1.25F);
/*  65 */     this.leftHair2.func_78792_a(this.leftHair8);
/*  66 */     setRotationAngle(this.leftHair8, -0.289F, -0.0337F, 0.0081F);
/*  67 */     this.leftHair8.func_78784_a(0, 4).func_228303_a_(-1.35F, -3.9F, -1.5F, 2.0F, 4.0F, 2.0F, 0.0F, false);
/*     */     
/*  69 */     this.leftHair6 = new ModelRenderer((Model)this);
/*  70 */     this.leftHair6.func_78793_a(-0.5F, -1.5F, 0.25F);
/*  71 */     this.leftHair2.func_78792_a(this.leftHair6);
/*  72 */     setRotationAngle(this.leftHair6, 0.1135F, -0.0827F, -0.1597F);
/*  73 */     this.leftHair6.func_78784_a(0, 4).func_228303_a_(-1.35F, -3.9F, -1.5F, 2.0F, 4.0F, 2.0F, 0.0F, false);
/*     */     
/*  75 */     this.leftHair7 = new ModelRenderer((Model)this);
/*  76 */     this.leftHair7.func_78793_a(-0.25F, -1.5F, 0.75F);
/*  77 */     this.leftHair2.func_78792_a(this.leftHair7);
/*  78 */     setRotationAngle(this.leftHair7, -0.0577F, -0.1198F, 0.2333F);
/*  79 */     this.leftHair7.func_78784_a(0, 4).func_228303_a_(-1.35F, -3.9F, -1.5F, 2.0F, 4.0F, 2.0F, 0.0F, false);
/*     */     
/*  81 */     this.leftHair5 = new ModelRenderer((Model)this);
/*  82 */     this.leftHair5.func_78793_a(0.5F, -1.5F, 0.25F);
/*  83 */     this.leftHair2.func_78792_a(this.leftHair5);
/*  84 */     setRotationAngle(this.leftHair5, 0.0512F, -0.1307F, 0.4089F);
/*  85 */     this.leftHair5.func_78784_a(0, 4).func_228303_a_(-1.35F, -3.9F, -1.5F, 2.0F, 4.0F, 2.0F, 0.0F, false);
/*     */     
/*  87 */     this.leftHair3 = new ModelRenderer((Model)this);
/*  88 */     this.leftHair3.func_78793_a(0.5F, -1.5F, -0.25F);
/*  89 */     this.leftHair2.func_78792_a(this.leftHair3);
/*  90 */     setRotationAngle(this.leftHair3, 0.4659F, -0.1198F, 0.2333F);
/*  91 */     this.leftHair3.func_78784_a(0, 4).func_228303_a_(-1.35F, -3.9F, -1.5F, 2.0F, 4.0F, 2.0F, 0.0F, false);
/*     */     
/*  93 */     this.rightHair = new ModelRenderer((Model)this);
/*  94 */     this.rightHair.func_78793_a(-4.5F, -7.5F, -1.0F);
/*  95 */     this.hair.func_78792_a(this.rightHair);
/*  96 */     setRotationAngle(this.rightHair, 0.0F, 0.0F, -0.9599F);
/*  97 */     this.rightHair.func_78784_a(0, 11).func_228303_a_(-1.0F, -5.0F, -1.0F, 3.0F, 6.0F, 4.0F, 0.0F, true);
/*     */     
/*  99 */     this.rightHair2 = new ModelRenderer((Model)this);
/* 100 */     this.rightHair2.func_78793_a(-0.25F, -4.5F, 0.0F);
/* 101 */     this.rightHair.func_78792_a(this.rightHair2);
/* 102 */     setRotationAngle(this.rightHair2, 0.0F, 0.0F, -0.5236F);
/* 103 */     this.rightHair2.func_78784_a(9, 2).func_228303_a_(-1.15F, -2.9F, -1.0F, 3.0F, 4.0F, 4.0F, 0.0F, true);
/*     */     
/* 105 */     this.rightHair3 = new ModelRenderer((Model)this);
/* 106 */     this.rightHair3.func_78793_a(0.5F, -1.5F, -0.25F);
/* 107 */     this.rightHair2.func_78792_a(this.rightHair3);
/* 108 */     setRotationAngle(this.rightHair3, 0.2477F, 0.1198F, -0.2333F);
/* 109 */     this.rightHair3.func_78784_a(0, 4).func_228303_a_(-0.65F, -3.9F, -0.5F, 2.0F, 4.0F, 2.0F, 0.0F, true);
/*     */     
/* 111 */     this.rightHair4 = new ModelRenderer((Model)this);
/* 112 */     this.rightHair4.func_78793_a(-0.5F, -1.5F, 1.25F);
/* 113 */     this.rightHair2.func_78792_a(this.rightHair4);
/* 114 */     setRotationAngle(this.rightHair4, -0.3768F, -0.0288F, -0.2172F);
/* 115 */     this.rightHair4.func_78784_a(0, 4).func_228303_a_(-0.65F, -3.9F, -0.5F, 2.0F, 4.0F, 2.0F, 0.0F, true);
/*     */     
/* 117 */     this.rightHair5 = new ModelRenderer((Model)this);
/* 118 */     this.rightHair5.func_78793_a(0.5F, -1.5F, 1.25F);
/* 119 */     this.rightHair2.func_78792_a(this.rightHair5);
/* 120 */     setRotationAngle(this.rightHair5, -0.289F, 0.0337F, -0.0081F);
/* 121 */     this.rightHair5.func_78784_a(0, 4).func_228303_a_(-0.65F, -3.9F, -0.5F, 2.0F, 4.0F, 2.0F, 0.0F, true);
/*     */     
/* 123 */     this.rightHair6 = new ModelRenderer((Model)this);
/* 124 */     this.rightHair6.func_78793_a(0.5F, -1.5F, 0.25F);
/* 125 */     this.rightHair2.func_78792_a(this.rightHair6);
/* 126 */     setRotationAngle(this.rightHair6, 0.1135F, 0.0827F, 0.1597F);
/* 127 */     this.rightHair6.func_78784_a(0, 4).func_228303_a_(-0.65F, -3.9F, -0.5F, 2.0F, 4.0F, 2.0F, 0.0F, true);
/*     */     
/* 129 */     this.rightHair7 = new ModelRenderer((Model)this);
/* 130 */     this.rightHair7.func_78793_a(0.25F, -1.5F, 0.75F);
/* 131 */     this.rightHair2.func_78792_a(this.rightHair7);
/* 132 */     setRotationAngle(this.rightHair7, -0.0577F, 0.1198F, -0.2333F);
/* 133 */     this.rightHair7.func_78784_a(0, 4).func_228303_a_(-0.65F, -3.9F, -0.5F, 2.0F, 4.0F, 2.0F, 0.0F, true);
/*     */     
/* 135 */     this.rightHair8 = new ModelRenderer((Model)this);
/* 136 */     this.rightHair8.func_78793_a(-0.5F, -1.5F, 0.25F);
/* 137 */     this.rightHair2.func_78792_a(this.rightHair8);
/* 138 */     setRotationAngle(this.rightHair8, 0.0512F, 0.1307F, -0.4089F);
/* 139 */     this.rightHair8.func_78784_a(0, 4).func_228303_a_(-0.65F, -3.9F, -0.5F, 2.0F, 4.0F, 2.0F, 0.0F, true);
/*     */     
/* 141 */     this.rightHair9 = new ModelRenderer((Model)this);
/* 142 */     this.rightHair9.func_78793_a(-0.5F, -1.5F, -0.25F);
/* 143 */     this.rightHair2.func_78792_a(this.rightHair9);
/* 144 */     setRotationAngle(this.rightHair9, 0.4659F, 0.1198F, -0.2333F);
/* 145 */     this.rightHair9.func_78784_a(0, 4).func_228303_a_(-0.65F, -3.9F, -0.5F, 2.0F, 4.0F, 2.0F, 0.0F, true);
/*     */   }
/*     */   private final ModelRenderer leftHair3; private final ModelRenderer rightHair; private final ModelRenderer rightHair2; private final ModelRenderer rightHair3; private final ModelRenderer rightHair4; private final ModelRenderer rightHair5; private final ModelRenderer rightHair6;
/*     */   private final ModelRenderer rightHair7;
/*     */   private final ModelRenderer rightHair8;
/*     */   private final ModelRenderer rightHair9;
/*     */   
/*     */   public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 155 */     this.hair.func_217177_a(this.field_78116_c);
/* 156 */     this.hair.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */   }
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 160 */     modelRenderer.field_78795_f = x;
/* 161 */     modelRenderer.field_78796_g = y;
/* 162 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\armors\BuggyHairModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */