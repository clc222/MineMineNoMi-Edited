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
/*     */ public class PlumeHatModel<T extends LivingEntity>
/*     */   extends BipedModel<T>
/*     */ {
/*     */   private final ModelRenderer hat;
/*     */   private final ModelRenderer top;
/*     */   private final ModelRenderer feather;
/*     */   private final ModelRenderer feather2;
/*     */   private final ModelRenderer feather3;
/*     */   private final ModelRenderer feather4;
/*     */   
/*     */   public PlumeHatModel() {
/*  24 */     super(1.0F);
/*  25 */     this.field_78090_t = 128;
/*  26 */     this.field_78089_u = 128;
/*     */     
/*  28 */     this.hat = new ModelRenderer((Model)this);
/*  29 */     this.hat.func_78793_a(0.0F, -2.5F, 0.0F);
/*  30 */     this.hat.func_78784_a(0, 26).func_228303_a_(-3.0F, -6.5F, -6.0F, 6.0F, 1.0F, 12.0F, -0.001F, false);
/*     */     
/*  32 */     this.top = new ModelRenderer((Model)this);
/*  33 */     this.top.func_78793_a(0.0F, -6.5F, 0.0F);
/*  34 */     this.hat.func_78792_a(this.top);
/*  35 */     this.top.func_78784_a(0, 50).func_228303_a_(-4.0F, -5.0F, -4.0F, 8.0F, 5.0F, 8.0F, 0.502F, false);
/*  36 */     this.top.func_78784_a(4, 41).func_228303_a_(-3.0F, -5.75F, -3.0F, 6.0F, 1.0F, 6.0F, 1.0F, false);
/*     */     
/*  38 */     this.feather = new ModelRenderer((Model)this);
/*  39 */     this.feather.func_78793_a(4.25F, -6.0F, 0.75F);
/*  40 */     this.hat.func_78792_a(this.feather);
/*  41 */     setRotationAngle(this.feather, 0.1745F, 0.0F, 0.0F);
/*  42 */     this.feather.func_78784_a(38, 26).func_228303_a_(0.0F, -4.0F, 0.0F, 2.0F, 3.0F, 5.0F, 0.0F, false);
/*     */     
/*  44 */     this.feather2 = new ModelRenderer((Model)this);
/*  45 */     this.feather2.func_78793_a(0.0F, 0.0F, 0.0F);
/*  46 */     this.feather.func_78792_a(this.feather2);
/*  47 */     setRotationAngle(this.feather2, 0.2182F, 0.0F, 0.0F);
/*  48 */     this.feather2.func_78784_a(39, 36).func_228303_a_(0.0F, -4.0F, -1.0F, 2.0F, 3.0F, 5.0F, -0.01F, false);
/*     */     
/*  50 */     this.feather3 = new ModelRenderer((Model)this);
/*  51 */     this.feather3.func_78793_a(0.0F, -0.25F, 0.25F);
/*  52 */     this.feather.func_78792_a(this.feather3);
/*  53 */     setRotationAngle(this.feather3, 0.6109F, 0.0F, 0.0F);
/*  54 */     this.feather3.func_78784_a(40, 46).func_228303_a_(0.0F, -4.04F, -1.75F, 2.0F, 2.0F, 2.0F, -0.02F, false);
/*     */     
/*  56 */     this.feather4 = new ModelRenderer((Model)this);
/*  57 */     this.feather4.func_78793_a(0.0F, 0.0F, 0.0F);
/*  58 */     this.feather.func_78792_a(this.feather4);
/*  59 */     setRotationAngle(this.feather4, 0.7854F, 0.0F, 0.0F);
/*  60 */     this.feather4.func_78784_a(39, 52).func_228303_a_(0.0F, -4.2F, -2.25F, 2.0F, 1.0F, 2.0F, -0.03F, false);
/*     */     
/*  62 */     this.feather5 = new ModelRenderer((Model)this);
/*  63 */     this.feather5.func_78793_a(0.0F, 0.25F, 3.75F);
/*  64 */     this.feather.func_78792_a(this.feather5);
/*  65 */     setRotationAngle(this.feather5, -0.2182F, 0.0F, 0.0F);
/*  66 */     this.feather5.func_78784_a(39, 16).func_228303_a_(0.0F, -4.0F, -1.0F, 2.0F, 3.0F, 5.0F, -0.01F, false);
/*     */     
/*  68 */     this.feather6 = new ModelRenderer((Model)this);
/*  69 */     this.feather6.func_78793_a(0.0F, 0.8602F, 8.2891F);
/*  70 */     this.feather.func_78792_a(this.feather6);
/*  71 */     setRotationAngle(this.feather6, -0.7418F, 0.0F, 0.0F);
/*  72 */     this.feather6.func_78784_a(40, 8).func_228303_a_(0.0F, -2.25F, -2.0F, 2.0F, 3.0F, 3.0F, 0.0F, false);
/*     */     
/*  74 */     this.leftSide = new ModelRenderer((Model)this);
/*  75 */     this.leftSide.func_78793_a(-5.0F, -5.25F, -1.0F);
/*  76 */     this.hat.func_78792_a(this.leftSide);
/*  77 */     setRotationAngle(this.leftSide, -0.1745F, -0.4363F, 0.3491F);
/*  78 */     this.leftSide.func_78784_a(0, 13).func_228303_a_(-4.0F, -1.15F, -8.0F, 6.0F, 1.0F, 11.0F, -0.01F, false);
/*  79 */     this.leftSide.func_78784_a(0, 0).func_228303_a_(-4.0F, -1.55F, -8.0F, 6.0F, 1.0F, 11.0F, -0.1F, false);
/*     */     
/*  81 */     this.rightSide = new ModelRenderer((Model)this);
/*  82 */     this.rightSide.func_78793_a(5.0F, -5.25F, -1.0F);
/*  83 */     this.hat.func_78792_a(this.rightSide);
/*  84 */     setRotationAngle(this.rightSide, -0.1745F, 0.4363F, -0.3491F);
/*  85 */     this.rightSide.func_78784_a(0, 13).func_228303_a_(-2.0F, -1.15F, -8.0F, 6.0F, 1.0F, 11.0F, -0.01F, false);
/*  86 */     this.rightSide.func_78784_a(0, 0).func_228303_a_(-2.0F, -1.55F, -8.0F, 6.0F, 1.0F, 11.0F, -0.1F, false);
/*     */     
/*  88 */     this.leftSide2 = new ModelRenderer((Model)this);
/*  89 */     this.leftSide2.func_78793_a(-2.25F, -5.25F, 6.5F);
/*  90 */     this.hat.func_78792_a(this.leftSide2);
/*  91 */     setRotationAngle(this.leftSide2, 0.1745F, 0.4363F, 0.3491F);
/*  92 */     this.leftSide2.func_78784_a(0, 13).func_228303_a_(-4.0F, -1.15F, -9.0F, 6.0F, 1.0F, 11.0F, -0.01F, false);
/*  93 */     this.leftSide2.func_78784_a(0, 0).func_228303_a_(-4.0F, -1.55F, -9.0F, 6.0F, 1.0F, 11.0F, -0.1F, false);
/*     */     
/*  95 */     this.rightSide2 = new ModelRenderer((Model)this);
/*  96 */     this.rightSide2.func_78793_a(2.25F, -5.25F, 6.5F);
/*  97 */     this.hat.func_78792_a(this.rightSide2);
/*  98 */     setRotationAngle(this.rightSide2, 0.1745F, -0.4363F, -0.3491F);
/*  99 */     this.rightSide2.func_78784_a(0, 13).func_228303_a_(-2.0F, -1.15F, -9.0F, 6.0F, 1.0F, 11.0F, -0.01F, false);
/* 100 */     this.rightSide2.func_78784_a(0, 0).func_228303_a_(-2.0F, -1.55F, -9.0F, 6.0F, 1.0F, 11.0F, -0.1F, false);
/*     */   }
/*     */   private final ModelRenderer feather5; private final ModelRenderer feather6; private final ModelRenderer leftSide;
/*     */   private final ModelRenderer rightSide;
/*     */   private final ModelRenderer leftSide2;
/*     */   private final ModelRenderer rightSide2;
/*     */   
/*     */   public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 110 */     this.hat.func_217177_a(this.field_78116_c);
/* 111 */     this.hat.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */   }
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 115 */     modelRenderer.field_78795_f = x;
/* 116 */     modelRenderer.field_78796_g = y;
/* 117 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\armors\PlumeHatModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */