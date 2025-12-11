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
/*     */ public class MihawkHatModel<T extends LivingEntity> extends BipedModel<T> {
/*     */   private final ModelRenderer hat;
/*     */   private final ModelRenderer top;
/*     */   private final ModelRenderer top2s;
/*     */   private final ModelRenderer top3s;
/*     */   private final ModelRenderer feather;
/*     */   private final ModelRenderer feather2;
/*     */   private final ModelRenderer feather3;
/*     */   private final ModelRenderer feather4;
/*     */   private final ModelRenderer feather5;
/*     */   private final ModelRenderer feather6;
/*     */   private final ModelRenderer feather7;
/*     */   private final ModelRenderer rightSide;
/*     */   private final ModelRenderer leftSide;
/*     */   
/*     */   public MihawkHatModel() {
/*  27 */     super(1.0F);
/*  28 */     this.field_78090_t = 128;
/*  29 */     this.field_78089_u = 128;
/*     */     
/*  31 */     this.hat = new ModelRenderer((Model)this);
/*  32 */     this.hat.func_78793_a(0.0F, -2.5F, 0.0F);
/*  33 */     this.hat.func_78784_a(0, 0).func_228303_a_(-6.0F, -6.0F, -7.0F, 12.0F, 1.0F, 14.0F, 0.0F, false);
/*  34 */     this.hat.func_78784_a(0, 0).func_228303_a_(-6.0F, -6.5F, -7.0F, 12.0F, 1.0F, 14.0F, -0.001F, false);
/*     */     
/*  36 */     this.top = new ModelRenderer((Model)this);
/*  37 */     this.top.func_78793_a(0.0F, -6.5F, 0.0F);
/*  38 */     this.hat.func_78792_a(this.top);
/*  39 */     this.top.func_78784_a(0, 25).func_228303_a_(-4.0F, -4.0F, -4.0F, 8.0F, 4.0F, 8.0F, 0.502F, false);
/*  40 */     this.top.func_78784_a(15, 15).func_228303_a_(-4.0F, -4.75F, -4.0F, 2.0F, 1.0F, 8.0F, 0.501F, false);
/*  41 */     this.top.func_78784_a(15, 15).func_228303_a_(2.0F, -4.75F, -4.0F, 2.0F, 1.0F, 8.0F, 0.501F, false);
/*     */     
/*  43 */     this.top2s = new ModelRenderer((Model)this);
/*  44 */     this.top2s.func_78793_a(-1.0F, -4.0F, 0.0F);
/*  45 */     this.top.func_78792_a(this.top2s);
/*  46 */     setRotationAngle(this.top2s, 0.0F, 0.0F, 0.7854F);
/*  47 */     this.top2s.func_78784_a(37, 17).func_228303_a_(-0.75F, 0.0F, -4.0F, 1.0F, 1.0F, 8.0F, 0.5F, false);
/*     */     
/*  49 */     this.top3s = new ModelRenderer((Model)this);
/*  50 */     this.top3s.func_78793_a(1.0F, -4.0F, 0.0F);
/*  51 */     this.top.func_78792_a(this.top3s);
/*  52 */     setRotationAngle(this.top3s, 0.0F, 0.0F, 0.7854F);
/*  53 */     this.top3s.func_78784_a(37, 17).func_228303_a_(0.0F, -0.75F, -4.0F, 1.0F, 1.0F, 8.0F, 0.5F, false);
/*     */     
/*  55 */     this.feather = new ModelRenderer((Model)this);
/*  56 */     this.feather.func_78793_a(4.25F, -5.75F, 0.75F);
/*  57 */     this.hat.func_78792_a(this.feather);
/*  58 */     setRotationAngle(this.feather, 0.1745F, 0.0F, 0.0F);
/*  59 */     this.feather.func_78784_a(0, 54).func_228303_a_(-2.0F, -4.0F, 0.0F, 4.0F, 4.0F, 5.0F, 0.0F, false);
/*     */     
/*  61 */     this.feather2 = new ModelRenderer((Model)this);
/*  62 */     this.feather2.func_78793_a(0.0F, 0.0F, 0.0F);
/*  63 */     this.feather.func_78792_a(this.feather2);
/*  64 */     setRotationAngle(this.feather2, 0.2182F, 0.0F, 0.0F);
/*  65 */     this.feather2.func_78784_a(0, 54).func_228303_a_(-2.0F, -4.0F, -1.0F, 4.0F, 4.0F, 5.0F, -0.01F, false);
/*     */     
/*  67 */     this.feather3 = new ModelRenderer((Model)this);
/*  68 */     this.feather3.func_78793_a(0.0F, -0.25F, 0.25F);
/*  69 */     this.feather.func_78792_a(this.feather3);
/*  70 */     setRotationAngle(this.feather3, 0.6109F, 0.0F, 0.0F);
/*  71 */     this.feather3.func_78784_a(0, 62).func_228303_a_(-2.0F, -4.04F, -1.75F, 4.0F, 3.0F, 2.0F, -0.02F, false);
/*     */     
/*  73 */     this.feather4 = new ModelRenderer((Model)this);
/*  74 */     this.feather4.func_78793_a(0.0F, 0.0F, 0.0F);
/*  75 */     this.feather.func_78792_a(this.feather4);
/*  76 */     setRotationAngle(this.feather4, 0.7854F, 0.0F, 0.0F);
/*  77 */     this.feather4.func_78784_a(0, 55).func_228303_a_(-2.0F, -4.2F, -2.25F, 4.0F, 2.0F, 2.0F, -0.03F, false);
/*     */     
/*  79 */     this.feather5 = new ModelRenderer((Model)this);
/*  80 */     this.feather5.func_78793_a(0.0F, 0.25F, 3.75F);
/*  81 */     this.feather.func_78792_a(this.feather5);
/*  82 */     setRotationAngle(this.feather5, -0.2182F, 0.0F, 0.0F);
/*  83 */     this.feather5.func_78784_a(0, 54).func_228303_a_(-2.0F, -4.0F, -1.0F, 4.0F, 4.0F, 5.0F, -0.01F, false);
/*     */     
/*  85 */     this.feather6 = new ModelRenderer((Model)this);
/*  86 */     this.feather6.func_78793_a(0.0F, 0.8602F, 8.2891F);
/*  87 */     this.feather.func_78792_a(this.feather6);
/*  88 */     setRotationAngle(this.feather6, -0.7418F, 0.0F, 0.0F);
/*  89 */     this.feather6.func_78784_a(0, 55).func_228303_a_(-2.0F, -2.25F, -2.0F, 4.0F, 4.0F, 3.0F, 0.0F, false);
/*     */     
/*  91 */     this.feather7 = new ModelRenderer((Model)this);
/*  92 */     this.feather7.func_78793_a(0.0F, 2.6102F, 8.5391F);
/*  93 */     this.feather.func_78792_a(this.feather7);
/*  94 */     setRotationAngle(this.feather7, -1.5708F, 0.0F, 0.0F);
/*  95 */     this.feather7.func_78784_a(0, 55).func_228303_a_(-2.0F, -2.0F, -2.75F, 4.0F, 4.0F, 4.0F, -0.01F, false);
/*     */     
/*  97 */     this.rightSide = new ModelRenderer((Model)this);
/*  98 */     this.rightSide.func_78793_a(7.0F, -5.25F, 0.0F);
/*  99 */     this.hat.func_78792_a(this.rightSide);
/* 100 */     setRotationAngle(this.rightSide, 0.0F, 0.0F, -0.3491F);
/* 101 */     this.rightSide.func_78784_a(38, 1).func_228303_a_(-1.0F, -1.15F, -7.0F, 3.0F, 1.0F, 14.0F, -0.01F, false);
/* 102 */     this.rightSide.func_78784_a(38, 1).func_228303_a_(-1.0F, -1.55F, -7.0F, 3.0F, 1.0F, 14.0F, -0.02F, false);
/*     */     
/* 104 */     this.leftSide = new ModelRenderer((Model)this);
/* 105 */     this.leftSide.func_78793_a(-7.0F, -5.25F, 0.0F);
/* 106 */     this.hat.func_78792_a(this.leftSide);
/* 107 */     setRotationAngle(this.leftSide, 0.0F, 0.0F, 0.3491F);
/* 108 */     this.leftSide.func_78784_a(38, 1).func_228303_a_(-2.0F, -1.15F, -7.0F, 3.0F, 1.0F, 14.0F, -0.01F, false);
/* 109 */     this.leftSide.func_78784_a(38, 1).func_228303_a_(-2.0F, -1.55F, -7.0F, 3.0F, 1.0F, 14.0F, -0.02F, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 121 */     this.hat.func_217177_a(this.field_78116_c);
/* 122 */     this.hat.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 127 */     modelRenderer.field_78795_f = x;
/* 128 */     modelRenderer.field_78796_g = y;
/* 129 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\armors\MihawkHatModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */