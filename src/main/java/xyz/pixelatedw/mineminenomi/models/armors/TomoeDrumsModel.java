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
/*     */ public class TomoeDrumsModel<T extends LivingEntity>
/*     */   extends BipedModel<T> {
/*     */   private final ModelRenderer tomoe_drums;
/*     */   private final ModelRenderer bars;
/*     */   private final ModelRenderer Bar10_r1;
/*     */   private final ModelRenderer Bar9_r1;
/*     */   private final ModelRenderer Bar8_r1;
/*     */   private final ModelRenderer Bar7_r1;
/*     */   private final ModelRenderer Bar6_r1;
/*     */   private final ModelRenderer Bar5_r1;
/*     */   private final ModelRenderer Bar4_r1;
/*     */   private final ModelRenderer Bar3_r1;
/*     */   private final ModelRenderer Bar2_r1;
/*     */   private final ModelRenderer Bar1_r1;
/*     */   private final ModelRenderer Bar10;
/*     */   private final ModelRenderer Bar8;
/*     */   private final ModelRenderer drums;
/*     */   private final ModelRenderer Drum1;
/*     */   private final ModelRenderer Drum2;
/*     */   private final ModelRenderer Drum3;
/*     */   private final ModelRenderer Drum4;
/*     */   
/*     */   public TomoeDrumsModel() {
/*  34 */     super(1.0F);
/*  35 */     this.field_78090_t = 32;
/*  36 */     this.field_78089_u = 32;
/*     */     
/*  38 */     this.tomoe_drums = new ModelRenderer((Model)this);
/*  39 */     this.tomoe_drums.func_78793_a(0.0F, 1.0F, 3.0F);
/*     */     
/*  41 */     this.bars = new ModelRenderer((Model)this);
/*  42 */     this.bars.func_78793_a(0.0F, 4.0F, -1.0F);
/*  43 */     this.tomoe_drums.func_78792_a(this.bars);
/*  44 */     this.bars.func_78784_a(0, 0).func_228303_a_(-1.5F, -18.1F, 3.0F, 3.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/*  46 */     this.Bar10_r1 = new ModelRenderer((Model)this);
/*  47 */     this.Bar10_r1.func_78793_a(-23.0F, -46.0F, 1.0F);
/*  48 */     this.bars.func_78792_a(this.Bar10_r1);
/*  49 */     setRotationAngle(this.Bar10_r1, 0.0F, 0.0F, 2.7925F);
/*  50 */     this.Bar10_r1.func_78784_a(0, 0).func_228303_a_(-10.7F, -34.6F, 2.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/*  52 */     this.Bar9_r1 = new ModelRenderer((Model)this);
/*  53 */     this.Bar9_r1.func_78793_a(21.0F, -12.75F, 1.0F);
/*  54 */     this.bars.func_78792_a(this.Bar9_r1);
/*  55 */     setRotationAngle(this.Bar9_r1, 0.0F, 0.0F, -2.7925F);
/*  56 */     this.Bar9_r1.func_78784_a(0, 0).func_228303_a_(15.1792F, -2.65F, 2.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/*  58 */     this.Bar8_r1 = new ModelRenderer((Model)this);
/*  59 */     this.Bar8_r1.func_78793_a(-38.0F, -20.75F, 1.0F);
/*  60 */     this.bars.func_78792_a(this.Bar8_r1);
/*  61 */     setRotationAngle(this.Bar8_r1, 0.0F, 0.0F, 2.0944F);
/*  62 */     this.Bar8_r1.func_78784_a(0, 0).func_228303_a_(-12.0F, -30.5F, 2.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/*  64 */     this.Bar7_r1 = new ModelRenderer((Model)this);
/*  65 */     this.Bar7_r1.func_78793_a(38.0F, -20.25F, 1.0F);
/*  66 */     this.bars.func_78792_a(this.Bar7_r1);
/*  67 */     setRotationAngle(this.Bar7_r1, 0.0F, 0.0F, -2.0944F);
/*  68 */     this.Bar7_r1.func_78784_a(0, 0).func_228303_a_(7.7F, -30.5F, 2.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/*  70 */     this.Bar6_r1 = new ModelRenderer((Model)this);
/*  71 */     this.Bar6_r1.func_78793_a(-34.25F, 1.25F, 1.0F);
/*  72 */     this.bars.func_78792_a(this.Bar6_r1);
/*  73 */     setRotationAngle(this.Bar6_r1, 0.0F, 0.0F, 1.5708F);
/*  74 */     this.Bar6_r1.func_78784_a(0, 0).func_228303_a_(-13.25F, -26.25F, 2.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/*  76 */     this.Bar5_r1 = new ModelRenderer((Model)this);
/*  77 */     this.Bar5_r1.func_78793_a(34.75F, -1.0F, 1.0F);
/*  78 */     this.bars.func_78792_a(this.Bar5_r1);
/*  79 */     setRotationAngle(this.Bar5_r1, 0.0F, 0.0F, -1.6581F);
/*  80 */     this.Bar5_r1.func_78784_a(0, 0).func_228303_a_(8.25F, -26.1F, 2.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/*  82 */     this.Bar4_r1 = new ModelRenderer((Model)this);
/*  83 */     this.Bar4_r1.func_78793_a(19.5F, 14.5F, 1.0F);
/*  84 */     this.bars.func_78792_a(this.Bar4_r1);
/*  85 */     setRotationAngle(this.Bar4_r1, 0.0F, 0.0F, -0.8727F);
/*  86 */     this.Bar4_r1.func_78784_a(0, 0).func_228303_a_(5.0F, -23.0F, 2.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/*  88 */     this.Bar3_r1 = new ModelRenderer((Model)this);
/*  89 */     this.Bar3_r1.func_78793_a(-19.5F, 14.75F, 1.0F);
/*  90 */     this.bars.func_78792_a(this.Bar3_r1);
/*  91 */     setRotationAngle(this.Bar3_r1, 0.0F, 0.0F, 0.8727F);
/*  92 */     this.Bar3_r1.func_78784_a(0, 0).func_228303_a_(-10.0F, -23.0F, 2.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/*  94 */     this.Bar2_r1 = new ModelRenderer((Model)this);
/*  95 */     this.Bar2_r1.func_78793_a(-10.0F, 17.25F, 1.0F);
/*  96 */     this.bars.func_78792_a(this.Bar2_r1);
/*  97 */     setRotationAngle(this.Bar2_r1, 0.0F, 0.0873F, 0.4887F);
/*  98 */     this.Bar2_r1.func_78784_a(0, 0).func_228303_a_(-5.9564F, -21.0F, 1.5019F, 5.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 100 */     this.Bar1_r1 = new ModelRenderer((Model)this);
/* 101 */     this.Bar1_r1.func_78793_a(10.0F, 17.0F, 1.0F);
/* 102 */     this.bars.func_78792_a(this.Bar1_r1);
/* 103 */     setRotationAngle(this.Bar1_r1, 0.0F, -0.0873F, -0.4887F);
/* 104 */     this.Bar1_r1.func_78784_a(0, 0).func_228303_a_(0.9564F, -21.0F, 1.5019F, 5.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 106 */     this.Bar10 = new ModelRenderer((Model)this);
/* 107 */     this.Bar10.func_78793_a(-5.7F, -15.4F, 0.0F);
/* 108 */     this.bars.func_78792_a(this.Bar10);
/* 109 */     setRotationAngle(this.Bar10, 0.0F, 0.0F, 2.7925F);
/*     */     
/* 111 */     this.Bar8 = new ModelRenderer((Model)this);
/* 112 */     this.Bar8.func_78793_a(-7.7F, -11.5F, 0.0F);
/* 113 */     this.bars.func_78792_a(this.Bar8);
/* 114 */     setRotationAngle(this.Bar8, 0.0F, 0.0F, 2.0944F);
/*     */     
/* 116 */     this.drums = new ModelRenderer((Model)this);
/* 117 */     this.drums.func_78793_a(0.0F, 2.0F, -1.0F);
/* 118 */     this.tomoe_drums.func_78792_a(this.drums);
/*     */     
/* 120 */     this.Drum1 = new ModelRenderer((Model)this);
/* 121 */     this.Drum1.func_78793_a(-10.0F, -7.0F, -1.0F);
/* 122 */     this.drums.func_78792_a(this.Drum1);
/* 123 */     this.Drum1.func_78784_a(0, 5).func_228303_a_(0.0F, 0.0F, 3.0F, 3.0F, 3.0F, 3.0F, 0.0F, false);
/*     */     
/* 125 */     this.Drum2 = new ModelRenderer((Model)this);
/* 126 */     this.Drum2.func_78793_a(7.0F, -7.0F, -1.0F);
/* 127 */     this.drums.func_78792_a(this.Drum2);
/* 128 */     this.Drum2.func_78784_a(0, 5).func_228303_a_(0.0F, 0.0F, 3.0F, 3.0F, 3.0F, 3.0F, 0.0F, false);
/*     */     
/* 130 */     this.Drum3 = new ModelRenderer((Model)this);
/* 131 */     this.Drum3.func_78793_a(-8.0F, -15.0F, -1.0F);
/* 132 */     this.drums.func_78792_a(this.Drum3);
/* 133 */     this.Drum3.func_78784_a(0, 5).func_228303_a_(0.0F, 0.0F, 3.0F, 3.0F, 3.0F, 3.0F, 0.0F, false);
/*     */     
/* 135 */     this.Drum4 = new ModelRenderer((Model)this);
/* 136 */     this.Drum4.func_78793_a(4.0F, -15.0F, -1.0F);
/* 137 */     this.drums.func_78792_a(this.Drum4);
/* 138 */     this.Drum4.func_78784_a(0, 5).func_228303_a_(0.0F, 0.0F, 3.0F, 3.0F, 3.0F, 3.0F, 0.0F, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 144 */     super.func_225597_a_((LivingEntity)entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 150 */     this.tomoe_drums.func_217177_a(this.field_78115_e);
/* 151 */     this.tomoe_drums.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 156 */     modelRenderer.field_78795_f = x;
/* 157 */     modelRenderer.field_78796_g = y;
/* 158 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\armors\TomoeDrumsModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */