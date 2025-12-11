/*     */ package xyz.pixelatedw.mineminenomi.models.morphs;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.renderer.entity.model.IHasArm;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.HandSide;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.vector.Vector3f;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphModel;
/*     */ 
/*     */ public class AxolotlWalkModel<T extends LivingEntity>
/*     */   extends MorphModel<T> implements IHasArm {
/*     */   private final ModelRenderer head;
/*     */   private final ModelRenderer top_gills;
/*     */   private final ModelRenderer left_gills;
/*     */   private final ModelRenderer right_gills;
/*     */   private final ModelRenderer body;
/*     */   private final ModelRenderer leg4;
/*     */   private final ModelRenderer leg2;
/*     */   private final ModelRenderer leg3;
/*     */   private final ModelRenderer leg1;
/*     */   private final ModelRenderer tail;
/*     */   
/*     */   public AxolotlWalkModel() {
/*  29 */     super(1.0F);
/*  30 */     this.field_78090_t = 64;
/*  31 */     this.field_78089_u = 64;
/*     */     
/*  33 */     this.head = new ModelRenderer((Model)this);
/*  34 */     this.head.func_78793_a(0.0F, 18.0F, -5.0F);
/*  35 */     this.head.func_78784_a(0, 1).func_228303_a_(-4.0F, -3.0F, -5.0F, 8.0F, 5.0F, 5.0F, 0.0F, false);
/*     */     
/*  37 */     this.top_gills = new ModelRenderer((Model)this);
/*  38 */     this.top_gills.func_78793_a(0.0F, -3.0F, -1.0F);
/*  39 */     this.head.func_78792_a(this.top_gills);
/*  40 */     this.top_gills.func_78784_a(3, 37).func_228303_a_(-4.0F, -3.0F, 0.0F, 8.0F, 3.0F, 0.0F, 0.0F, false);
/*     */     
/*  42 */     this.left_gills = new ModelRenderer((Model)this);
/*  43 */     this.left_gills.func_78793_a(4.0F, 0.0F, -1.0F);
/*  44 */     this.head.func_78792_a(this.left_gills);
/*  45 */     this.left_gills.func_78784_a(0, 40).func_228303_a_(-11.0F, -5.0F, 0.0F, 3.0F, 7.0F, 0.0F, 0.0F, false);
/*     */     
/*  47 */     this.right_gills = new ModelRenderer((Model)this);
/*  48 */     this.right_gills.func_78793_a(-4.0F, 0.0F, -1.0F);
/*  49 */     this.head.func_78792_a(this.right_gills);
/*  50 */     this.right_gills.func_78784_a(11, 40).func_228303_a_(8.0F, -5.0F, 0.0F, 3.0F, 7.0F, 0.0F, 0.0F, false);
/*     */     
/*  52 */     this.body = new ModelRenderer((Model)this);
/*  53 */     this.body.func_78793_a(0.0F, 18.0F, 4.0F);
/*  54 */     this.body.func_78784_a(0, 11).func_228303_a_(-4.0F, -2.0F, -9.0F, 8.0F, 4.0F, 10.0F, 0.0F, false);
/*  55 */     this.body.func_78784_a(2, 17).func_228303_a_(0.0F, -3.0F, -8.0F, 0.0F, 5.0F, 9.0F, 0.0F, false);
/*     */     
/*  57 */     this.leg4 = new ModelRenderer((Model)this);
/*  58 */     this.leg4.func_78793_a(-3.5F, 19.0F, -4.0F);
/*  59 */     this.leg4.func_78784_a(2, 13).func_228303_a_(6.0F, 0.0F, 0.0F, 3.0F, 5.0F, 0.0F, 0.0F, false);
/*     */     
/*  61 */     this.leg2 = new ModelRenderer((Model)this);
/*  62 */     this.leg2.func_78793_a(-3.5F, 19.0F, 3.0F);
/*  63 */     this.leg2.func_78784_a(2, 13).func_228303_a_(6.0F, 0.0F, 0.0F, 3.0F, 5.0F, 0.0F, 0.0F, false);
/*     */     
/*  65 */     this.leg3 = new ModelRenderer((Model)this);
/*  66 */     this.leg3.func_78793_a(3.5F, 19.0F, -4.0F);
/*  67 */     this.leg3.func_78784_a(2, 13).func_228303_a_(-9.0F, 0.0F, 0.0F, 3.0F, 5.0F, 0.0F, 0.0F, false);
/*     */     
/*  69 */     this.leg1 = new ModelRenderer((Model)this);
/*  70 */     this.leg1.func_78793_a(3.5F, 19.0F, 3.0F);
/*  71 */     this.leg1.func_78784_a(2, 13).func_228303_a_(-9.0F, 0.0F, 0.0F, 3.0F, 5.0F, 0.0F, 0.0F, false);
/*     */     
/*  73 */     this.tail = new ModelRenderer((Model)this);
/*  74 */     this.tail.func_78793_a(0.0F, 18.0F, 5.0F);
/*  75 */     this.tail.func_78784_a(2, 19).func_228303_a_(0.0F, -3.0F, 0.0F, 0.0F, 5.0F, 12.0F, 0.0F, false);
/*     */     
/*  77 */     this.field_78115_e = this.body;
/*  78 */     this.field_78116_c = this.head;
/*  79 */     this.field_178723_h = this.leg1;
/*  80 */     this.field_178724_i = this.leg2;
/*  81 */     this.field_178721_j = this.leg3;
/*  82 */     this.field_178722_k = this.leg4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/*  89 */     boolean flag = (entity.func_184599_cB() > 4);
/*  90 */     boolean flag1 = entity.func_213314_bj();
/*  91 */     this.head.field_78796_g = netHeadYaw * 0.017453292F;
/*  92 */     if (flag) {
/*  93 */       this.head.field_78795_f = -0.7853982F;
/*  94 */     } else if (this.field_205061_a > 0.0F) {
/*     */       
/*  96 */       if (flag1) {
/*  97 */         this.head.field_78795_f = func_205060_a(this.head.field_78795_f, -0.7853982F, this.field_205061_a);
/*     */       } else {
/*  99 */         this.head.field_78795_f = func_205060_a(this.head.field_78795_f, headPitch * 0.017453292F, this.field_205061_a);
/*     */       } 
/*     */     } else {
/*     */       
/* 103 */       this.head.field_78795_f = headPitch * 0.017453292F;
/* 104 */       if (this.head.field_78795_f > 0.4D) {
/* 105 */         this.head.field_78795_f = 0.4F;
/* 106 */       } else if (this.head.field_78795_f < -0.4D) {
/* 107 */         this.head.field_78795_f = -0.4F;
/*     */       } 
/*     */     } 
/*     */     
/* 111 */     float f = 1.0F;
/* 112 */     this.leg1.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.6662F + 3.1415927F) * 0.8F * limbSwingAmount * 0.5F / f;
/* 113 */     this.leg2.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.6662F + 3.1415927F) * 0.8F * limbSwingAmount * 0.5F / f;
/* 114 */     this.leg3.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.6662F) * 0.7F * limbSwingAmount / f;
/* 115 */     this.leg4.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.6662F) * 0.7F * limbSwingAmount / f;
/*     */     
/* 117 */     this.tail.field_78796_g = (float)(this.tail.field_78796_g + Math.sin(ageInTicks * 0.06D) / 10.0D);
/* 118 */     this.tail.field_78795_f = (float)(this.tail.field_78795_f + Math.sin(ageInTicks * 0.02D) / 10.0D);
/*     */ 
/*     */     
/* 121 */     this.field_217112_c = ((LivingEntity)entity).field_70733_aJ;
/* 122 */     if (this.field_217112_c > 0.0F) {
/*     */       
/* 124 */       this.head.field_78796_g += this.body.field_78796_g;
/* 125 */       float f1 = 1.0F - this.field_217112_c;
/* 126 */       f1 *= f1;
/* 127 */       f1 *= f1;
/* 128 */       f1 = 1.0F - f1;
/* 129 */       float f2 = MathHelper.func_76126_a(f1 * 3.1415927F);
/* 130 */       float f3 = MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * -(this.head.field_78795_f - 0.1F) * 0.15F;
/* 131 */       this.head.field_78795_f = (float)(this.head.field_78795_f - f2 * 1.5D + f3);
/* 132 */       this.head.field_78808_h = MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * -0.4F;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 139 */     this.head.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 140 */     this.body.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 141 */     this.leg4.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 142 */     this.leg2.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 143 */     this.leg3.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 144 */     this.leg1.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 145 */     this.tail.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 150 */     modelRenderer.field_78795_f = x;
/* 151 */     modelRenderer.field_78796_g = y;
/* 152 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225599_a_(HandSide side, MatrixStack matrixStack) {
/* 158 */     this.head.func_228307_a_(matrixStack);
/* 159 */     matrixStack.func_227863_a_(Vector3f.field_229183_f_.func_229187_a_(90.0F));
/* 160 */     matrixStack.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(260.0F));
/* 161 */     matrixStack.func_227861_a_(0.1D, -0.2D, -0.03D);
/*     */   }
/*     */   
/*     */   public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*     */   
/*     */   public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\morphs\AxolotlWalkModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */