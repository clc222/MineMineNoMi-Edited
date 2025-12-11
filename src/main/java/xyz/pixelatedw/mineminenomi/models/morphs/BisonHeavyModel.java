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
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ 
/*     */ public class BisonHeavyModel<T extends LivingEntity>
/*     */   extends MorphModel<T> implements IHasArm {
/*     */   private final ModelRenderer rightArm;
/*     */   private final ModelRenderer rightArm2;
/*     */   private final ModelRenderer rightHand;
/*     */   private final ModelRenderer leftArm;
/*     */   private final ModelRenderer leftArm2;
/*     */   private final ModelRenderer leftHand;
/*     */   private final ModelRenderer rightLeg;
/*     */   private final ModelRenderer rightLeg2;
/*     */   private final ModelRenderer rightLeg3;
/*     */   private final ModelRenderer rightFeet;
/*     */   private final ModelRenderer leftLeg;
/*     */   private final ModelRenderer leftLeg2;
/*     */   private final ModelRenderer leftLeg3;
/*     */   private final ModelRenderer leftFeet;
/*     */   private final ModelRenderer upperBody;
/*     */   private final ModelRenderer lowerBody;
/*     */   private final ModelRenderer hunch;
/*     */   private final ModelRenderer rightShoulder;
/*     */   private final ModelRenderer leftShoulder;
/*     */   private final ModelRenderer neck;
/*     */   private final ModelRenderer head;
/*     */   private final ModelRenderer leftHorn1;
/*     */   private final ModelRenderer leftHorn2;
/*     */   private final ModelRenderer rightHorn1;
/*     */   private final ModelRenderer rightHorn2;
/*     */   
/*     */   public BisonHeavyModel() {
/*  45 */     super(1.0F);
/*  46 */     this.field_78090_t = 128;
/*  47 */     this.field_78089_u = 64;
/*     */     
/*  49 */     this.rightArm = new ModelRenderer((Model)this);
/*  50 */     this.rightArm.func_78793_a(-5.0F, -2.6F, 0.0F);
/*  51 */     setRotationAngle(this.rightArm, 0.0F, 0.0F, 0.2094F);
/*  52 */     this.rightArm.func_78784_a(23, 30).func_228303_a_(-4.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, -0.01F, false);
/*     */     
/*  54 */     this.rightArm2 = new ModelRenderer((Model)this);
/*  55 */     this.rightArm2.func_78793_a(-0.2445F, 0.052F, 0.0F);
/*  56 */     this.rightArm.func_78792_a(this.rightArm2);
/*  57 */     setRotationAngle(this.rightArm2, 0.0F, 0.0F, -0.2793F);
/*  58 */     this.rightArm2.func_78784_a(23, 42).func_228303_a_(-4.5F, 5.8F, -1.5F, 3.0F, 7.0F, 3.0F, 0.0F, false);
/*     */     
/*  60 */     this.rightHand = new ModelRenderer((Model)this);
/*  61 */     this.rightHand.func_78793_a(-2.5201F, 12.2906F, 0.25F);
/*  62 */     this.rightArm2.func_78792_a(this.rightHand);
/*  63 */     setRotationAngle(this.rightHand, 0.1745F, 1.5708F, 0.0175F);
/*  64 */     this.rightHand.func_78784_a(0, 25).func_228303_a_(-1.2353F, 0.3094F, -1.0712F, 3.0F, 3.0F, 1.0F, 0.2F, false);
/*     */     
/*  66 */     this.leftArm = new ModelRenderer((Model)this);
/*  67 */     this.leftArm.func_78793_a(5.0F, -2.6F, 0.0F);
/*  68 */     setRotationAngle(this.leftArm, 0.0F, 0.0F, -0.2094F);
/*  69 */     this.leftArm.func_78784_a(23, 30).func_228303_a_(0.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, -0.01F, false);
/*     */     
/*  71 */     this.leftArm2 = new ModelRenderer((Model)this);
/*  72 */     this.leftArm2.func_78793_a(0.0F, 0.0F, 0.0F);
/*  73 */     this.leftArm.func_78792_a(this.leftArm2);
/*  74 */     setRotationAngle(this.leftArm2, 0.0F, 0.0F, 0.2793F);
/*  75 */     this.leftArm2.func_78784_a(23, 42).func_228303_a_(2.0F, 5.8F, -1.5F, 3.0F, 7.0F, 3.0F, 0.0F, false);
/*     */     
/*  77 */     this.leftHand = new ModelRenderer((Model)this);
/*  78 */     this.leftHand.func_78793_a(3.9781F, 1.9722F, 0.0F);
/*  79 */     this.leftArm2.func_78792_a(this.leftHand);
/*  80 */     setRotationAngle(this.leftHand, 0.1745F, -1.5708F, 0.0F);
/*  81 */     this.leftHand.func_78784_a(0, 25).func_228303_a_(-1.4781F, 10.6278F, -1.8489F, 3.0F, 3.0F, 1.0F, 0.2F, false);
/*     */     
/*  83 */     this.rightLeg = new ModelRenderer((Model)this);
/*  84 */     this.rightLeg.func_78793_a(-3.0F, 11.6F, 0.5F);
/*  85 */     setRotationAngle(this.rightLeg, -0.3491F, 0.0F, 0.0F);
/*  86 */     this.rightLeg.func_78784_a(9, 30).func_228303_a_(-1.5F, 0.0F, -1.5F, 3.0F, 7.0F, 3.0F, 0.0F, false);
/*     */     
/*  88 */     this.rightLeg2 = new ModelRenderer((Model)this);
/*  89 */     this.rightLeg2.func_78793_a(0.0F, 5.9802F, 1.1124F);
/*  90 */     this.rightLeg.func_78792_a(this.rightLeg2);
/*  91 */     setRotationAngle(this.rightLeg2, 1.8151F, 0.0F, 0.0F);
/*  92 */     this.rightLeg2.func_78784_a(9, 41).func_228303_a_(-1.0F, -0.5326F, -0.7283F, 2.0F, 4.0F, 2.0F, 0.0F, false);
/*     */     
/*  94 */     this.rightLeg3 = new ModelRenderer((Model)this);
/*  95 */     this.rightLeg3.func_78793_a(0.0F, 0.2827F, 6.0278F);
/*  96 */     this.rightLeg2.func_78792_a(this.rightLeg3);
/*  97 */     setRotationAngle(this.rightLeg3, -2.0071F, 0.0F, 0.0F);
/*  98 */     this.rightLeg3.func_78784_a(0, 30).func_228303_a_(-1.0F, 3.1F, 4.8F, 2.0F, 6.0F, 2.0F, -0.01F, false);
/*     */     
/* 100 */     this.rightFeet = new ModelRenderer((Model)this);
/* 101 */     this.rightFeet.func_78793_a(0.0F, 0.0F, 0.0F);
/* 102 */     this.rightLeg3.func_78792_a(this.rightFeet);
/* 103 */     setRotationAngle(this.rightFeet, 0.5236F, 0.0F, 0.0F);
/* 104 */     this.rightFeet.func_78784_a(0, 39).func_228303_a_(-1.0F, 10.25F, -0.5044F, 2.0F, 2.0F, 2.0F, 0.01F, false);
/*     */     
/* 106 */     this.leftLeg = new ModelRenderer((Model)this);
/* 107 */     this.leftLeg.func_78793_a(3.5F, 11.6F, 1.0F);
/* 108 */     setRotationAngle(this.leftLeg, -0.3491F, 0.0F, 0.0F);
/* 109 */     this.leftLeg.func_78784_a(9, 30).func_228303_a_(-2.0F, 0.0F, -2.0F, 3.0F, 7.0F, 3.0F, 0.0F, false);
/*     */     
/* 111 */     this.leftLeg2 = new ModelRenderer((Model)this);
/* 112 */     this.leftLeg2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 113 */     this.leftLeg.func_78792_a(this.leftLeg2);
/* 114 */     setRotationAngle(this.leftLeg2, 1.8151F, 0.0F, 0.0F);
/* 115 */     this.leftLeg2.func_78784_a(9, 41).func_228303_a_(-1.5F, -1.5F, -6.8F, 2.0F, 4.0F, 2.0F, 0.0F, false);
/*     */     
/* 117 */     this.leftLeg3 = new ModelRenderer((Model)this);
/* 118 */     this.leftLeg3.func_78793_a(0.0F, -0.163F, 0.2021F);
/* 119 */     this.leftLeg2.func_78792_a(this.leftLeg3);
/* 120 */     setRotationAngle(this.leftLeg3, -2.0071F, 0.0F, 0.0F);
/* 121 */     this.leftLeg3.func_78784_a(0, 30).func_228303_a_(-1.5F, 3.5143F, 4.5288F, 2.0F, 6.0F, 2.0F, -0.01F, false);
/*     */     
/* 123 */     this.leftFeet = new ModelRenderer((Model)this);
/* 124 */     this.leftFeet.func_78793_a(0.0F, 0.0F, 0.0F);
/* 125 */     this.leftLeg3.func_78792_a(this.leftFeet);
/* 126 */     setRotationAngle(this.leftFeet, 0.5236F, 0.0F, 0.0F);
/* 127 */     this.leftFeet.func_78784_a(0, 39).func_228303_a_(-1.5F, 10.5F, -1.0F, 2.0F, 2.0F, 2.0F, 0.01F, false);
/*     */     
/* 129 */     this.upperBody = new ModelRenderer((Model)this);
/* 130 */     this.upperBody.func_78793_a(0.0F, -5.0F, 0.0F);
/* 131 */     this.upperBody.func_78784_a(48, 0).func_228303_a_(-5.5F, 0.0F, -2.5F, 11.0F, 10.0F, 6.0F, 0.0F, false);
/*     */     
/* 133 */     this.lowerBody = new ModelRenderer((Model)this);
/* 134 */     this.lowerBody.func_78793_a(0.0F, 0.0F, 0.0F);
/* 135 */     this.upperBody.func_78792_a(this.lowerBody);
/* 136 */     this.lowerBody.func_78784_a(17, 0).func_228303_a_(-5.0F, 9.9F, -2.0F, 10.0F, 8.0F, 5.0F, 0.0F, false);
/*     */     
/* 138 */     this.hunch = new ModelRenderer((Model)this);
/* 139 */     this.hunch.func_78793_a(0.0F, 0.0F, 0.0F);
/* 140 */     this.upperBody.func_78792_a(this.hunch);
/* 141 */     setRotationAngle(this.hunch, 0.4189F, 0.0F, 0.0F);
/* 142 */     this.hunch.func_78784_a(83, 24).func_228303_a_(-4.5F, 0.0F, -0.5F, 9.0F, 10.0F, 6.0F, 0.0F, false);
/*     */     
/* 144 */     this.rightShoulder = new ModelRenderer((Model)this);
/* 145 */     this.rightShoulder.func_78793_a(-3.5F, 4.0F, 0.0F);
/* 146 */     this.upperBody.func_78792_a(this.rightShoulder);
/* 147 */     setRotationAngle(this.rightShoulder, 0.0F, 0.0F, -0.2618F);
/* 148 */     this.rightShoulder.func_78784_a(83, 0).func_228303_a_(-3.0F, -3.0F, -2.0F, 6.0F, 7.0F, 4.0F, 0.0F, false);
/*     */     
/* 150 */     this.leftShoulder = new ModelRenderer((Model)this);
/* 151 */     this.leftShoulder.func_78793_a(0.0F, 0.0F, 0.0F);
/* 152 */     this.upperBody.func_78792_a(this.leftShoulder);
/* 153 */     setRotationAngle(this.leftShoulder, 0.0F, 0.0F, 0.2618F);
/* 154 */     this.leftShoulder.func_78784_a(83, 0).func_228303_a_(1.5F, 0.0F, -2.0F, 6.0F, 7.0F, 4.0F, 0.0F, false);
/*     */     
/* 156 */     this.neck = new ModelRenderer((Model)this);
/* 157 */     this.neck.func_78793_a(0.0F, 0.0F, 0.0F);
/* 158 */     this.upperBody.func_78792_a(this.neck);
/* 159 */     setRotationAngle(this.neck, 0.4887F, 0.0F, 0.0F);
/* 160 */     this.neck.func_78784_a(83, 12).func_228303_a_(-3.5F, 0.0F, -5.0F, 7.0F, 7.0F, 4.0F, 0.0F, false);
/*     */     
/* 162 */     this.head = new ModelRenderer((Model)this);
/* 163 */     this.head.func_78793_a(0.0F, -4.0F, 0.0F);
/* 164 */     this.head.func_78784_a(0, 0).func_228303_a_(-2.0F, -1.0F, -7.0F, 4.0F, 5.0F, 4.0F, 0.0F, false);
/*     */     
/* 166 */     this.leftHorn1 = new ModelRenderer((Model)this);
/* 167 */     this.leftHorn1.func_78793_a(0.0F, 0.0F, 0.0F);
/* 168 */     this.head.func_78792_a(this.leftHorn1);
/* 169 */     setRotationAngle(this.leftHorn1, 0.0F, 0.0F, -0.733F);
/* 170 */     this.leftHorn1.func_78784_a(115, 0).func_228303_a_(1.0F, 1.0F, -6.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 172 */     this.leftHorn2 = new ModelRenderer((Model)this);
/* 173 */     this.leftHorn2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 174 */     this.leftHorn1.func_78792_a(this.leftHorn2);
/* 175 */     setRotationAngle(this.leftHorn2, 0.0F, 0.0F, -1.2217F);
/* 176 */     this.leftHorn2.func_78784_a(122, 0).func_228303_a_(-0.7F, 2.5F, -6.0F, 2.0F, 1.0F, 1.0F, 0.01F, false);
/*     */     
/* 178 */     this.rightHorn1 = new ModelRenderer((Model)this);
/* 179 */     this.rightHorn1.func_78793_a(0.0F, 0.0F, 0.0F);
/* 180 */     this.head.func_78792_a(this.rightHorn1);
/* 181 */     setRotationAngle(this.rightHorn1, 0.0F, 0.0F, 0.733F);
/* 182 */     this.rightHorn1.func_78784_a(115, 0).func_228303_a_(-3.0F, 1.0F, -6.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 184 */     this.rightHorn2 = new ModelRenderer((Model)this);
/* 185 */     this.rightHorn2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 186 */     this.rightHorn1.func_78792_a(this.rightHorn2);
/* 187 */     setRotationAngle(this.rightHorn2, 0.0F, 0.0F, 1.2217F);
/* 188 */     this.rightHorn2.func_78784_a(122, 0).func_228303_a_(-1.3F, 2.5F, -6.0F, 2.0F, 1.0F, 1.0F, 0.01F, false);
/*     */     
/* 190 */     this.field_78115_e = this.upperBody;
/* 191 */     this.field_78116_c = this.head;
/* 192 */     this.field_178723_h = this.rightArm;
/* 193 */     this.field_178724_i = this.leftArm;
/* 194 */     this.field_178721_j = this.rightLeg;
/* 195 */     this.field_178722_k = this.leftLeg;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 201 */     this.rightArm.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 202 */     this.leftArm.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 203 */     this.rightLeg.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 204 */     this.leftLeg.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 205 */     this.upperBody.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 206 */     this.head.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 213 */     boolean flag = (entity.func_184599_cB() > 4);
/* 214 */     boolean flag1 = entity.func_213314_bj();
/* 215 */     this.head.field_78796_g = netHeadYaw * 0.017453292F;
/* 216 */     if (flag) {
/* 217 */       this.head.field_78795_f = -0.7853982F;
/* 218 */     } else if (this.field_205061_a > 0.0F) {
/*     */       
/* 220 */       if (flag1) {
/* 221 */         this.head.field_78795_f = func_205060_a(this.head.field_78795_f, -0.7853982F, this.field_205061_a);
/*     */       } else {
/* 223 */         this.head.field_78795_f = func_205060_a(this.head.field_78795_f, headPitch * 0.017453292F, this.field_205061_a);
/*     */       } 
/*     */     } else {
/*     */       
/* 227 */       this.head.field_78795_f = headPitch * 0.017453292F;
/* 228 */       if (this.head.field_78795_f > 0.4D) {
/* 229 */         this.head.field_78795_f = 0.4F;
/* 230 */       } else if (this.head.field_78795_f < -0.4D) {
/* 231 */         this.head.field_78795_f = -0.4F;
/*     */       } 
/*     */     } 
/*     */     
/* 235 */     float f = 1.0F;
/* 236 */     this.rightArm.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.6662F + 3.1415927F) * 0.8F * limbSwingAmount * 0.5F / f;
/* 237 */     this.leftArm.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.6662F) * 0.8F * limbSwingAmount * 0.5F / f;
/* 238 */     this.rightLeg.field_78795_f = -0.34F + MathHelper.func_76134_b(limbSwing * 0.6662F) * 0.7F * limbSwingAmount / f;
/* 239 */     this.leftLeg.field_78795_f = -0.34F + MathHelper.func_76134_b(limbSwing * 0.6662F + 3.1415927F) * 0.7F * limbSwingAmount / f;
/* 240 */     if (!entity.func_184614_ca().func_190926_b()) {
/* 241 */       this.rightArm.field_78795_f += -0.15F;
/*     */     }
/*     */     
/* 244 */     this.field_217112_c = ((LivingEntity)entity).field_70733_aJ;
/* 245 */     boolean isBlackLeg = EntityStatsCapability.get((LivingEntity)entity).isBlackLeg();
/* 246 */     if (this.field_217112_c > 0.0F)
/*     */     {
/* 248 */       if (isBlackLeg) {
/*     */         
/* 250 */         this.upperBody.field_78796_g = MathHelper.func_76126_a(MathHelper.func_76129_c(this.field_217112_c) * 6.2831855F) * 0.2F;
/* 251 */         this.rightLeg.field_78796_g += this.upperBody.field_78796_g;
/* 252 */         this.leftLeg.field_78796_g += this.upperBody.field_78796_g;
/* 253 */         float f1 = 1.0F - this.field_217112_c;
/* 254 */         f1 *= f1;
/* 255 */         f1 *= f1;
/* 256 */         f1 = 1.0F - f1;
/* 257 */         float f2 = MathHelper.func_76126_a(f1 * 3.1415927F);
/* 258 */         float f3 = MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * -(this.head.field_78795_f - 0.7F) * 0.75F;
/* 259 */         this.rightLeg.field_78795_f = (float)(this.rightArm.field_78795_f - f2 * 1.5D + f3);
/* 260 */         this.rightLeg.field_78796_g += this.lowerBody.field_78796_g * 2.0F;
/*     */       }
/*     */       else {
/*     */         
/* 264 */         this.upperBody.field_78796_g = MathHelper.func_76126_a(MathHelper.func_76129_c(this.field_217112_c) * 6.2831855F) * 0.2F;
/* 265 */         this.rightArm.field_78798_e = MathHelper.func_76126_a(this.upperBody.field_78796_g) * 5.0F;
/* 266 */         this.rightArm.field_78800_c = -MathHelper.func_76134_b(this.upperBody.field_78796_g) * 5.0F;
/* 267 */         this.leftArm.field_78798_e = -MathHelper.func_76126_a(this.upperBody.field_78796_g) * 5.0F;
/* 268 */         this.leftArm.field_78800_c = MathHelper.func_76134_b(this.upperBody.field_78796_g) * 5.0F;
/* 269 */         this.rightArm.field_78796_g += this.upperBody.field_78796_g;
/* 270 */         this.leftArm.field_78796_g += this.upperBody.field_78796_g;
/* 271 */         this.leftArm.field_78795_f += this.upperBody.field_78796_g;
/* 272 */         float f1 = 1.0F - this.field_217112_c;
/* 273 */         f1 *= f1;
/* 274 */         f1 *= f1;
/* 275 */         f1 = 1.0F - f1;
/* 276 */         float f2 = MathHelper.func_76126_a(f1 * 3.1415927F);
/* 277 */         float f3 = MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * -(this.head.field_78795_f - 0.7F) * 0.75F;
/* 278 */         this.rightArm.field_78795_f = (float)(this.rightArm.field_78795_f - f2 * 1.5D + f3);
/* 279 */         this.rightArm.field_78796_g += this.lowerBody.field_78796_g * 2.0F;
/* 280 */         this.rightArm.field_78808_h += MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * -0.9F;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 285 */     if (entity.func_213453_ef()) {
/*     */       
/* 287 */       this.upperBody.field_78795_f = 0.5F;
/* 288 */       this.upperBody.field_78798_e -= 4.0F;
/* 289 */       this.rightArm.field_78795_f += 0.4F;
/* 290 */       this.rightArm.field_78798_e -= 2.5F;
/* 291 */       this.leftArm.field_78795_f += 0.4F;
/* 292 */       this.leftArm.field_78798_e -= 2.5F;
/* 293 */       this.rightLeg.field_78798_e = 4.0F;
/* 294 */       this.leftLeg.field_78798_e = 4.5F;
/* 295 */       this.rightLeg.field_78797_d = 10.5F;
/* 296 */       this.leftLeg.field_78797_d = 10.5F;
/* 297 */       this.head.field_78797_d = -4.0F;
/* 298 */       this.head.field_78798_e = -2.0F;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 304 */     modelRenderer.field_78795_f = x;
/* 305 */     modelRenderer.field_78796_g = y;
/* 306 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225599_a_(HandSide side, MatrixStack matrixStack) {
/* 312 */     super.func_225599_a_(side, matrixStack);
/* 313 */     matrixStack.func_227861_a_(0.0D, 0.4D, 0.0D);
/* 314 */     matrixStack.func_227863_a_(Vector3f.field_229183_f_.func_229187_a_((side == HandSide.RIGHT) ? -20.0F : 20.0F));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
/* 320 */     if (side == HandSide.RIGHT) {
/*     */       
/* 322 */       matrixStack.func_227861_a_(-0.5D, -0.2D, -0.2D);
/* 323 */       matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(-10.0F));
/* 324 */       matrixStack.func_227863_a_(Vector3f.field_229183_f_.func_229187_a_(10.0F));
/* 325 */       this.rightArm2.func_228309_a_(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     }
/*     */     else {
/*     */       
/* 329 */       matrixStack.func_227861_a_(0.5D, -0.2D, -0.2D);
/* 330 */       matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(-10.0F));
/* 331 */       matrixStack.func_227863_a_(Vector3f.field_229183_f_.func_229187_a_(-10.0F));
/* 332 */       this.leftArm2.func_228309_a_(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
/* 339 */     if (side == HandSide.RIGHT) {
/*     */       
/* 341 */       matrixStack.func_227861_a_(0.0D, -1.2D, 0.3D);
/* 342 */       matrixStack.func_227862_a_(1.5F, 1.5F, 1.5F);
/* 343 */       matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(-60.0F));
/* 344 */       this.rightLeg.func_228309_a_(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     }
/*     */     else {
/*     */       
/* 348 */       matrixStack.func_227861_a_(0.0D, -1.2D, 0.3D);
/* 349 */       matrixStack.func_227862_a_(1.5F, 1.5F, 1.5F);
/* 350 */       matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(60.0F));
/* 351 */       this.leftLeg.func_228309_a_(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\morphs\BisonHeavyModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */