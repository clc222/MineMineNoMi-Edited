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
/*     */ public class ZouGuardModel<T extends LivingEntity>
/*     */   extends MorphModel<T> implements IHasArm {
/*     */   private final ModelRenderer head;
/*     */   private final ModelRenderer snout;
/*     */   private final ModelRenderer snout2;
/*     */   private final ModelRenderer snout3;
/*     */   private final ModelRenderer rightTusk;
/*     */   private final ModelRenderer rightTusk2;
/*     */   private final ModelRenderer leftTusk;
/*     */   private final ModelRenderer leftTusk2;
/*     */   private final ModelRenderer leftEar;
/*     */   private final ModelRenderer rightEar;
/*     */   private final ModelRenderer body;
/*     */   private final ModelRenderer tail;
/*     */   private final ModelRenderer tail2;
/*     */   private final ModelRenderer body2;
/*     */   private final ModelRenderer rightRearLeg;
/*     */   private final ModelRenderer leftRearLeg;
/*     */   private final ModelRenderer rightFrontLeg;
/*     */   private final ModelRenderer leftFrontLeg;
/*     */   
/*     */   public ZouGuardModel() {
/*  37 */     super(1.0F);
/*  38 */     this.field_78090_t = 128;
/*  39 */     this.field_78089_u = 64;
/*     */     
/*  41 */     this.head = new ModelRenderer((Model)this);
/*  42 */     this.head.func_78793_a(0.0F, -1.0F, -14.0F);
/*  43 */     this.head.func_78784_a(0, 0).func_228303_a_(-4.0F, -4.0F, -6.0F, 8.0F, 11.0F, 9.0F, 0.0F, false);
/*     */     
/*  45 */     this.snout = new ModelRenderer((Model)this);
/*  46 */     this.snout.func_78793_a(0.0F, 6.0F, -4.5F);
/*  47 */     this.head.func_78792_a(this.snout);
/*  48 */     setRotationAngle(this.snout, -0.1745F, 0.0F, 0.0F);
/*  49 */     this.snout.func_78784_a(108, 8).func_228303_a_(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, 0.0F, false);
/*     */     
/*  51 */     this.snout2 = new ModelRenderer((Model)this);
/*  52 */     this.snout2.func_78793_a(0.0F, 6.0F, 0.0F);
/*  53 */     this.snout.func_78792_a(this.snout2);
/*  54 */     setRotationAngle(this.snout2, 0.1745F, 0.0F, 0.0F);
/*  55 */     this.snout2.func_78784_a(108, 20).func_228303_a_(-1.5F, 0.0F, -1.5F, 3.0F, 6.0F, 3.0F, 0.0F, false);
/*     */     
/*  57 */     this.snout3 = new ModelRenderer((Model)this);
/*  58 */     this.snout3.func_78793_a(0.0F, 5.5F, 0.5F);
/*  59 */     this.snout2.func_78792_a(this.snout3);
/*  60 */     setRotationAngle(this.snout3, 0.1745F, 0.0F, 0.0F);
/*  61 */     this.snout3.func_78784_a(108, 30).func_228303_a_(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);
/*     */     
/*  63 */     this.rightTusk = new ModelRenderer((Model)this);
/*  64 */     this.rightTusk.func_78793_a(2.3F, 5.0F, -5.0F);
/*  65 */     this.head.func_78792_a(this.rightTusk);
/*  66 */     setRotationAngle(this.rightTusk, -0.3491F, -0.2094F, 0.0F);
/*  67 */     this.rightTusk.func_78784_a(15, 21).func_228303_a_(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);
/*     */     
/*  69 */     this.rightTusk2 = new ModelRenderer((Model)this);
/*  70 */     this.rightTusk2.func_78793_a(0.0F, 3.8F, 0.0F);
/*  71 */     this.rightTusk.func_78792_a(this.rightTusk2);
/*  72 */     setRotationAngle(this.rightTusk2, -0.1745F, 0.0F, 0.0F);
/*  73 */     this.rightTusk2.func_78784_a(15, 27).func_228303_a_(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);
/*     */     
/*  75 */     this.leftTusk = new ModelRenderer((Model)this);
/*  76 */     this.leftTusk.func_78793_a(-2.3F, 5.0F, -5.0F);
/*  77 */     this.head.func_78792_a(this.leftTusk);
/*  78 */     setRotationAngle(this.leftTusk, -0.3491F, 0.2094F, 0.0F);
/*  79 */     this.leftTusk.func_78784_a(15, 21).func_228303_a_(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);
/*     */     
/*  81 */     this.leftTusk2 = new ModelRenderer((Model)this);
/*  82 */     this.leftTusk2.func_78793_a(-0.039F, 5.5022F, 0.3754F);
/*  83 */     this.leftTusk.func_78792_a(this.leftTusk2);
/*  84 */     setRotationAngle(this.leftTusk2, -0.1745F, 0.0F, 0.0F);
/*  85 */     this.leftTusk2.func_78784_a(15, 27).func_228303_a_(-0.461F, -1.6998F, -1.2926F, 1.0F, 4.0F, 1.0F, 0.0F, false);
/*     */     
/*  87 */     this.leftEar = new ModelRenderer((Model)this);
/*  88 */     this.leftEar.func_78793_a(3.0F, -0.5F, -2.5F);
/*  89 */     this.head.func_78792_a(this.leftEar);
/*  90 */     setRotationAngle(this.leftEar, -0.1368F, -0.4707F, 0.2946F);
/*  91 */     this.leftEar.func_78784_a(0, 21).func_228303_a_(0.0F, -4.5F, -0.5F, 6.0F, 9.0F, 1.0F, 0.0F, true);
/*     */     
/*  93 */     this.rightEar = new ModelRenderer((Model)this);
/*  94 */     this.rightEar.func_78793_a(-3.0F, -0.5F, -2.5F);
/*  95 */     this.head.func_78792_a(this.rightEar);
/*  96 */     setRotationAngle(this.rightEar, -0.1368F, 0.4707F, -0.2946F);
/*  97 */     this.rightEar.func_78784_a(0, 21).func_228303_a_(-6.0F, -4.5F, -0.5F, 6.0F, 9.0F, 1.0F, 0.0F, false);
/*     */     
/*  99 */     this.body = new ModelRenderer((Model)this);
/* 100 */     this.body.func_78793_a(0.0F, 9.0F, -4.0F);
/* 101 */     this.body.func_78784_a(36, 25).func_228303_a_(-7.5F, -12.0F, -8.0F, 15.0F, 15.0F, 24.0F, 0.0F, false);
/*     */     
/* 103 */     this.tail = new ModelRenderer((Model)this);
/* 104 */     this.tail.func_78793_a(0.0F, -7.0F, 15.5F);
/* 105 */     this.body.func_78792_a(this.tail);
/* 106 */     setRotationAngle(this.tail, 0.3491F, 0.0F, 0.0F);
/* 107 */     this.tail.func_78784_a(108, 0).func_228303_a_(-0.5F, 0.0F, -0.5F, 1.0F, 6.0F, 1.0F, 0.0F, false);
/*     */     
/* 109 */     this.tail2 = new ModelRenderer((Model)this);
/* 110 */     this.tail2.func_78793_a(0.0F, 5.5F, 0.0F);
/* 111 */     this.tail.func_78792_a(this.tail2);
/* 112 */     this.tail2.func_78784_a(113, 0).func_228303_a_(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);
/*     */     
/* 114 */     this.body2 = new ModelRenderer((Model)this);
/* 115 */     this.body2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 116 */     this.body.func_78792_a(this.body2);
/* 117 */     this.body2.func_78784_a(36, 0).func_228303_a_(-6.5F, -13.0F, -7.0F, 13.0F, 2.0F, 22.0F, 0.0F, false);
/*     */     
/* 119 */     this.rightRearLeg = new ModelRenderer((Model)this);
/* 120 */     this.rightRearLeg.func_78793_a(-5.0F, 11.0F, 9.5F);
/* 121 */     this.rightRearLeg.func_78784_a(0, 46).func_228303_a_(-2.25F, 0.0F, -2.75F, 5.0F, 13.0F, 5.0F, 0.0F, false);
/*     */     
/* 123 */     this.leftRearLeg = new ModelRenderer((Model)this);
/* 124 */     this.leftRearLeg.func_78793_a(5.0F, 11.0F, 9.5F);
/* 125 */     this.leftRearLeg.func_78784_a(0, 46).func_228303_a_(-2.75F, 0.0F, -2.75F, 5.0F, 13.0F, 5.0F, 0.0F, false);
/*     */     
/* 127 */     this.rightFrontLeg = new ModelRenderer((Model)this);
/* 128 */     this.rightFrontLeg.func_78793_a(-5.0F, 11.0F, -9.5F);
/* 129 */     this.rightFrontLeg.func_78784_a(0, 46).func_228303_a_(-2.25F, 0.0F, -2.25F, 5.0F, 13.0F, 5.0F, 0.0F, false);
/*     */     
/* 131 */     this.leftFrontLeg = new ModelRenderer((Model)this);
/* 132 */     this.leftFrontLeg.func_78793_a(5.0F, 11.0F, -9.5F);
/* 133 */     this.leftFrontLeg.func_78784_a(0, 46).func_228303_a_(-2.75F, 0.0F, -2.25F, 5.0F, 13.0F, 5.0F, 0.0F, false);
/*     */     
/* 135 */     this.field_78116_c = this.head;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 141 */     this.head.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 142 */     this.body.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 143 */     this.rightRearLeg.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 144 */     this.leftRearLeg.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 145 */     this.rightFrontLeg.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 146 */     this.leftFrontLeg.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 153 */     boolean flag1 = entity.func_213314_bj();
/* 154 */     this.head.field_78796_g = netHeadYaw * 0.017453292F;
/* 155 */     if (this.field_205061_a > 0.0F) {
/*     */       
/* 157 */       if (flag1) {
/* 158 */         this.head.field_78795_f = func_205060_a(this.head.field_78795_f, -0.7853982F, this.field_205061_a);
/*     */       } else {
/* 160 */         this.head.field_78795_f = func_205060_a(this.head.field_78795_f, headPitch * 0.017453292F, this.field_205061_a);
/*     */       } 
/*     */     } else {
/*     */       
/* 164 */       this.head.field_78795_f = headPitch * 0.017453292F;
/* 165 */       if (this.head.field_78795_f > 0.6D) {
/* 166 */         this.head.field_78795_f = 0.6F;
/*     */       }
/*     */     } 
/*     */     
/* 170 */     this.rightFrontLeg.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.6662F) * 0.3F * limbSwingAmount;
/* 171 */     this.leftFrontLeg.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.6662F + 3.1415927F) * 0.3F * limbSwingAmount;
/* 172 */     this.rightRearLeg.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.6662F) * 0.4F * limbSwingAmount;
/* 173 */     this.leftRearLeg.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.6662F + 3.1415927F) * 0.4F * limbSwingAmount;
/* 174 */     if (entity.func_70051_ag()) {
/*     */       
/* 176 */       this.tail.field_78795_f = 0.6F + MathHelper.func_76134_b(limbSwing * 0.6662F) * 0.2F * limbSwingAmount;
/* 177 */       this.leftEar.field_78796_g = -0.3F - MathHelper.func_76134_b(limbSwing * 0.6662F) * 0.2F * limbSwingAmount;
/* 178 */       this.rightEar.field_78796_g = 0.3F + MathHelper.func_76134_b(limbSwing * 0.6662F) * 0.2F * limbSwingAmount;
/*     */     } 
/*     */ 
/*     */     
/* 182 */     this.field_217112_c = ((LivingEntity)entity).field_70733_aJ;
/* 183 */     if (this.field_217112_c > 0.0F) {
/*     */       
/* 185 */       this.head.field_78796_g = MathHelper.func_76126_a(MathHelper.func_76129_c(this.field_217112_c) * 6.2831855F) * 0.2F;
/* 186 */       this.head.field_78796_g = MathHelper.func_76126_a(MathHelper.func_76129_c(this.field_217112_c) * 6.2831855F) * 0.2F;
/* 187 */       this.snout.field_78796_g += this.body2.field_78796_g;
/* 188 */       float f1 = 1.0F - this.field_217112_c;
/* 189 */       f1 *= f1;
/* 190 */       f1 *= f1;
/* 191 */       f1 = 1.0F - f1;
/* 192 */       float f2 = MathHelper.func_76126_a(f1 * 3.1415927F);
/* 193 */       float f3 = MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * -(this.head.field_78795_f - 0.7F) * 0.75F;
/* 194 */       this.snout.field_78795_f = (float)(this.snout.field_78795_f - f2 * 1.2D + f3);
/* 195 */       this.snout.field_78796_g += this.body.field_78796_g * 2.0F;
/* 196 */       this.snout.field_78808_h += MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * -0.4F;
/*     */     } 
/*     */ 
/*     */     
/* 200 */     if (entity.func_184613_cA()) {
/*     */       
/* 202 */       this.rightEar.field_78795_f = 1.2F;
/* 203 */       this.rightEar.field_78796_g = 0.1F;
/* 204 */       this.rightEar.field_78808_h = MathHelper.func_76134_b(ageInTicks) * 0.6F;
/*     */       
/* 206 */       this.leftEar.field_78795_f = 1.2F;
/* 207 */       this.leftEar.field_78796_g = 0.1F;
/* 208 */       this.leftEar.field_78808_h = -MathHelper.func_76134_b(ageInTicks) * 0.6F;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 214 */     modelRenderer.field_78795_f = x;
/* 215 */     modelRenderer.field_78796_g = y;
/* 216 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225599_a_(HandSide side, MatrixStack matrixStack) {
/* 232 */     this.head.func_228307_a_(matrixStack);
/* 233 */     this.snout.func_228307_a_(matrixStack);
/* 234 */     matrixStack.func_227863_a_(Vector3f.field_229183_f_.func_229187_a_(90.0F));
/* 235 */     matrixStack.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(260.0F));
/* 236 */     matrixStack.func_227861_a_(0.8D, -0.6D, 0.0D);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\morphs\ZouGuardModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */