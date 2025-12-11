/*     */ package xyz.pixelatedw.mineminenomi.models.entities.projectiles;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class PawModel
/*     */   extends EntityModel
/*     */ {
/*     */   public ModelRenderer pawA1;
/*     */   public ModelRenderer pawA2;
/*     */   public ModelRenderer pawA3;
/*     */   public ModelRenderer pawA4;
/*     */   public ModelRenderer pawA5;
/*     */   public ModelRenderer pawB1;
/*     */   public ModelRenderer pawB2;
/*     */   public ModelRenderer pawB3;
/*     */   public ModelRenderer pawB4;
/*     */   public ModelRenderer pawC1;
/*     */   public ModelRenderer pawC2;
/*     */   public ModelRenderer pawC3;
/*     */   public ModelRenderer pawC4;
/*     */   public ModelRenderer pawD1;
/*     */   public ModelRenderer pawD2;
/*     */   public ModelRenderer pawD3;
/*     */   public ModelRenderer pawD4;
/*     */   public ModelRenderer pawE1;
/*     */   public ModelRenderer pawE2;
/*     */   public ModelRenderer pawE3;
/*     */   public ModelRenderer pawE4;
/*     */   
/*     */   public PawModel() {
/*  40 */     this.field_78090_t = 160;
/*  41 */     this.field_78089_u = 80;
/*  42 */     this.pawE2 = new ModelRenderer((Model)this, 94, 34);
/*  43 */     this.pawE2.func_78793_a(9.5F, -10.0F, 0.0F);
/*  44 */     this.pawE2.func_228301_a_(-1.5F, -1.5F, -2.5F, 3.0F, 3.0F, 5.0F, 0.0F);
/*  45 */     setRotateAngle(this.pawE2, 0.0F, -0.0F, 0.7853982F);
/*  46 */     this.pawA4 = new ModelRenderer((Model)this, 0, 25);
/*  47 */     this.pawA4.func_78793_a(0.0F, 0.0F, 0.0F);
/*  48 */     this.pawA4.func_228301_a_(-8.0F, -7.0F, -4.0F, 16.0F, 14.0F, 8.0F, 0.0F);
/*  49 */     this.pawC3 = new ModelRenderer((Model)this, 111, 25);
/*  50 */     this.pawC3.func_78793_a(-3.0F, -12.0F, 0.0F);
/*  51 */     this.pawC3.func_228301_a_(-2.5F, -1.5F, -1.5F, 5.0F, 3.0F, 3.0F, 0.0F);
/*  52 */     setRotateAngle(this.pawC3, 0.0F, -0.0F, -0.08726646F);
/*  53 */     this.pawD1 = new ModelRenderer((Model)this, 94, 25);
/*  54 */     this.pawD1.func_78793_a(3.0F, -12.0F, 0.0F);
/*  55 */     this.pawD1.func_228301_a_(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F);
/*  56 */     setRotateAngle(this.pawD1, 0.0F, -0.0F, 0.08726646F);
/*  57 */     this.pawB1 = new ModelRenderer((Model)this, 94, 25);
/*  58 */     this.pawB1.func_78793_a(-9.5F, -10.0F, 0.0F);
/*  59 */     this.pawB1.func_228301_a_(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F);
/*  60 */     setRotateAngle(this.pawB1, 0.0F, -0.0F, -0.7853982F);
/*  61 */     this.pawA5 = new ModelRenderer((Model)this, 49, 25);
/*  62 */     this.pawA5.func_78793_a(0.0F, 0.0F, 0.0F);
/*  63 */     this.pawA5.func_228301_a_(-7.0F, -8.0F, -4.0F, 14.0F, 16.0F, 8.0F, 0.0F);
/*  64 */     this.pawD2 = new ModelRenderer((Model)this, 94, 34);
/*  65 */     this.pawD2.func_78793_a(3.0F, -12.0F, 0.0F);
/*  66 */     this.pawD2.func_228301_a_(-1.5F, -1.5F, -2.5F, 3.0F, 3.0F, 5.0F, 0.0F);
/*  67 */     setRotateAngle(this.pawD2, 0.0F, -0.0F, 0.08726646F);
/*  68 */     this.pawD4 = new ModelRenderer((Model)this, 111, 34);
/*  69 */     this.pawD4.func_78793_a(3.0F, -12.0F, 0.0F);
/*  70 */     this.pawD4.func_228301_a_(-1.5F, -2.5F, -1.5F, 3.0F, 5.0F, 3.0F, 0.0F);
/*  71 */     setRotateAngle(this.pawD4, 0.0F, -0.0F, 0.08726646F);
/*  72 */     this.pawC1 = new ModelRenderer((Model)this, 94, 25);
/*  73 */     this.pawC1.func_78793_a(-3.0F, -12.0F, 0.0F);
/*  74 */     this.pawC1.func_228301_a_(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F);
/*  75 */     setRotateAngle(this.pawC1, 0.0F, -0.0F, -0.08726646F);
/*  76 */     this.pawA1 = new ModelRenderer((Model)this, 0, 0);
/*  77 */     this.pawA1.func_78793_a(0.0F, 0.0F, 0.0F);
/*  78 */     this.pawA1.func_228301_a_(-7.5F, -7.5F, -4.5F, 15.0F, 15.0F, 9.0F, 0.0F);
/*  79 */     this.pawA2 = new ModelRenderer((Model)this, 49, 0);
/*  80 */     this.pawA2.func_78793_a(0.0F, 0.0F, 0.0F);
/*  81 */     this.pawA2.func_228301_a_(-7.0F, -7.0F, -5.0F, 14.0F, 14.0F, 10.0F, 0.0F);
/*  82 */     this.pawE3 = new ModelRenderer((Model)this, 111, 25);
/*  83 */     this.pawE3.func_78793_a(9.5F, -10.0F, 0.0F);
/*  84 */     this.pawE3.func_228301_a_(-2.5F, -1.5F, -1.5F, 5.0F, 3.0F, 3.0F, 0.0F);
/*  85 */     setRotateAngle(this.pawE3, 0.0F, -0.0F, 0.7853982F);
/*  86 */     this.pawC2 = new ModelRenderer((Model)this, 94, 34);
/*  87 */     this.pawC2.func_78793_a(-3.0F, -12.0F, 0.0F);
/*  88 */     this.pawC2.func_228301_a_(-1.5F, -1.5F, -2.5F, 3.0F, 3.0F, 5.0F, 0.0F);
/*  89 */     setRotateAngle(this.pawC2, 0.0F, -0.0F, -0.08726646F);
/*  90 */     this.pawB4 = new ModelRenderer((Model)this, 111, 34);
/*  91 */     this.pawB4.func_78793_a(-9.5F, -10.0F, 0.0F);
/*  92 */     this.pawB4.func_228301_a_(-1.5F, -2.5F, -1.5F, 3.0F, 5.0F, 3.0F, 0.0F);
/*  93 */     setRotateAngle(this.pawB4, 0.0F, -0.0F, -0.7853982F);
/*  94 */     this.pawD3 = new ModelRenderer((Model)this, 111, 25);
/*  95 */     this.pawD3.func_78793_a(3.0F, -12.0F, 0.0F);
/*  96 */     this.pawD3.func_228301_a_(-2.5F, -1.5F, -1.5F, 5.0F, 3.0F, 3.0F, 0.0F);
/*  97 */     setRotateAngle(this.pawD3, 0.0F, -0.0F, 0.08726646F);
/*  98 */     this.pawC4 = new ModelRenderer((Model)this, 111, 34);
/*  99 */     this.pawC4.func_78793_a(-3.0F, -12.0F, 0.0F);
/* 100 */     this.pawC4.func_228301_a_(-1.5F, -2.5F, -1.5F, 3.0F, 5.0F, 3.0F, 0.0F);
/* 101 */     setRotateAngle(this.pawC4, 0.0F, -0.0F, -0.08726646F);
/* 102 */     this.pawB3 = new ModelRenderer((Model)this, 111, 25);
/* 103 */     this.pawB3.func_78793_a(-9.5F, -10.0F, 0.0F);
/* 104 */     this.pawB3.func_228301_a_(-2.5F, -1.5F, -1.5F, 5.0F, 3.0F, 3.0F, 0.0F);
/* 105 */     setRotateAngle(this.pawB3, 0.0F, -0.0F, -0.7853982F);
/* 106 */     this.pawB2 = new ModelRenderer((Model)this, 94, 34);
/* 107 */     this.pawB2.func_78793_a(-9.5F, -10.0F, 0.0F);
/* 108 */     this.pawB2.func_228301_a_(-1.5F, -1.5F, -2.5F, 3.0F, 3.0F, 5.0F, 0.0F);
/* 109 */     setRotateAngle(this.pawB2, 0.0F, -0.0F, -0.7853982F);
/* 110 */     this.pawE1 = new ModelRenderer((Model)this, 94, 25);
/* 111 */     this.pawE1.func_78793_a(9.5F, -10.0F, 0.0F);
/* 112 */     this.pawE1.func_228301_a_(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F);
/* 113 */     setRotateAngle(this.pawE1, 0.0F, -0.0F, 0.7853982F);
/* 114 */     this.pawA3 = new ModelRenderer((Model)this, 98, 0);
/* 115 */     this.pawA3.func_78793_a(0.0F, 0.0F, 0.0F);
/* 116 */     this.pawA3.func_228301_a_(-6.5F, -6.5F, -5.5F, 13.0F, 13.0F, 11.0F, 0.0F);
/* 117 */     this.pawE4 = new ModelRenderer((Model)this, 111, 34);
/* 118 */     this.pawE4.func_78793_a(9.5F, -10.0F, 0.0F);
/* 119 */     this.pawE4.func_228301_a_(-1.5F, -2.5F, -1.5F, 3.0F, 5.0F, 3.0F, 0.0F);
/* 120 */     setRotateAngle(this.pawE4, 0.0F, -0.0F, 0.7853982F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225597_a_(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 131 */     ImmutableList.of(this.pawE2, this.pawA4, this.pawC3, this.pawD1, this.pawB1, this.pawA5, this.pawD2, this.pawD4, this.pawC1, this.pawA1, this.pawA2, this.pawE3, (Object[])new ModelRenderer[] { this.pawC2, this.pawB4, this.pawD3, this.pawC4, this.pawB3, this.pawB2, this.pawE1, this.pawA3, this.pawE4 }).forEach(model -> model.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
/* 139 */     model.field_78795_f = x;
/* 140 */     model.field_78796_g = y;
/* 141 */     model.field_78808_h = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\entities\projectiles\PawModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */