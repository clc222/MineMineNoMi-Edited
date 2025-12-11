/*     */ package xyz.pixelatedw.mineminenomi.models.morphs;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.renderer.entity.model.IHasArm;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.HandSide;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.vector.Vector3f;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphModel;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ 
/*     */ public class ZouHeavyModel<T extends LivingEntity>
/*     */   extends MorphModel<T> implements IHasArm {
/*     */   private ModelRenderer body;
/*     */   private ModelRenderer body2;
/*     */   private ModelRenderer tail;
/*     */   private ModelRenderer tail2;
/*     */   private ModelRenderer rightLeg;
/*     */   private ModelRenderer rightLeg2;
/*     */   private ModelRenderer leftLeg;
/*     */   private ModelRenderer leftLeg2;
/*     */   private ModelRenderer rightArm;
/*     */   private ModelRenderer rightArm2;
/*     */   private ModelRenderer leftArm;
/*     */   private ModelRenderer leftArm2;
/*     */   private ModelRenderer head;
/*     */   private ModelRenderer snout;
/*     */   private ModelRenderer snout2;
/*     */   private ModelRenderer snout3;
/*     */   private ModelRenderer leftEar;
/*     */   private ModelRenderer rightEar;
/*     */   private ModelRenderer leftTusk;
/*     */   private ModelRenderer leftTusk2;
/*     */   private ModelRenderer rightTusk;
/*     */   private ModelRenderer rightTusk2;
/*     */   
/*     */   public ZouHeavyModel() {
/*  43 */     super(1.0F);
/*  44 */     this.field_78090_t = 128;
/*  45 */     this.field_78089_u = 64;
/*     */     
/*  47 */     this.body = new ModelRenderer((Model)this);
/*  48 */     this.body.func_78793_a(0.0F, -2.6F, 2.7F);
/*  49 */     setRotationAngle(this.body, 0.0873F, 0.0F, 0.0F);
/*  50 */     this.body.func_78784_a(35, 0).func_228303_a_(-9.0F, -10.0F, -7.0F, 18.0F, 14.0F, 10.0F, 0.0F, false);
/*     */     
/*  52 */     this.body2 = new ModelRenderer((Model)this);
/*  53 */     this.body2.func_78793_a(0.0F, 13.8F, 0.3F);
/*  54 */     this.body.func_78792_a(this.body2);
/*  55 */     this.body2.func_78784_a(35, 25).func_228303_a_(-8.5F, -10.0F, -7.0F, 17.0F, 10.0F, 10.0F, 0.0F, false);
/*     */     
/*  57 */     this.tail = new ModelRenderer((Model)this);
/*  58 */     this.tail.func_78793_a(0.0F, -2.5F, 2.0F);
/*  59 */     this.body2.func_78792_a(this.tail);
/*  60 */     setRotationAngle(this.tail, 0.4363F, 0.0F, 0.0F);
/*  61 */     this.tail.func_78784_a(114, 0).func_228303_a_(-0.5F, 0.0F, -0.5F, 1.0F, 6.0F, 1.0F, 0.0F, false);
/*     */     
/*  63 */     this.tail2 = new ModelRenderer((Model)this);
/*  64 */     this.tail2.func_78793_a(0.0F, 5.2282F, -0.0718F);
/*  65 */     this.tail.func_78792_a(this.tail2);
/*  66 */     this.tail2.func_78784_a(119, 0).func_228303_a_(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);
/*     */     
/*  68 */     this.rightLeg = new ModelRenderer((Model)this);
/*  69 */     this.rightLeg.func_78793_a(-5.0F, 11.1F, 1.0F);
/*  70 */     setRotationAngle(this.rightLeg, -0.0524F, 0.0F, 0.0F);
/*  71 */     this.rightLeg.func_78784_a(42, 46).func_228303_a_(-2.5F, 0.0F, -2.5F, 5.0F, 7.0F, 5.0F, 0.0F, false);
/*     */     
/*  73 */     this.rightLeg2 = new ModelRenderer((Model)this);
/*  74 */     this.rightLeg2.func_78793_a(0.0F, 6.8F, 0.0F);
/*  75 */     this.rightLeg.func_78792_a(this.rightLeg2);
/*  76 */     setRotationAngle(this.rightLeg2, 0.0524F, 0.0F, 0.0F);
/*  77 */     this.rightLeg2.func_78784_a(63, 46).func_228303_a_(-2.5F, 0.0F, -2.5F, 5.0F, 6.0F, 5.0F, 0.0F, false);
/*     */     
/*  79 */     this.leftLeg = new ModelRenderer((Model)this);
/*  80 */     this.leftLeg.func_78793_a(5.0F, 11.1F, 1.0F);
/*  81 */     setRotationAngle(this.leftLeg, -0.0524F, 0.0F, 0.0F);
/*  82 */     this.leftLeg.func_78784_a(42, 46).func_228303_a_(-2.5F, 0.0F, -2.5F, 5.0F, 7.0F, 5.0F, 0.0F, false);
/*     */     
/*  84 */     this.leftLeg2 = new ModelRenderer((Model)this);
/*  85 */     this.leftLeg2.func_78793_a(0.0F, 6.8F, 0.0F);
/*  86 */     this.leftLeg.func_78792_a(this.leftLeg2);
/*  87 */     setRotationAngle(this.leftLeg2, 0.0524F, 0.0F, 0.0F);
/*  88 */     this.leftLeg2.func_78784_a(63, 46).func_228303_a_(-2.5F, 0.0F, -2.5F, 5.0F, 6.0F, 5.0F, 0.0F, false);
/*     */     
/*  90 */     this.rightArm = new ModelRenderer((Model)this);
/*  91 */     this.rightArm.func_78793_a(-9.0F, -9.6F, 1.0F);
/*  92 */     setRotationAngle(this.rightArm, 0.0F, 0.0F, 0.0349F);
/*  93 */     this.rightArm.func_78784_a(0, 46).func_228303_a_(-5.0F, -2.0F, -2.5F, 5.0F, 10.0F, 5.0F, 0.0F, false);
/*     */     
/*  95 */     this.rightArm2 = new ModelRenderer((Model)this);
/*  96 */     this.rightArm2.func_78793_a(-2.5F, 7.8F, 0.0F);
/*  97 */     this.rightArm.func_78792_a(this.rightArm2);
/*  98 */     setRotationAngle(this.rightArm2, 0.0F, 0.0F, -0.0349F);
/*  99 */     this.rightArm2.func_78784_a(21, 46).func_228303_a_(-2.5F, 0.0F, -2.5F, 5.0F, 10.0F, 5.0F, 0.0F, false);
/*     */     
/* 101 */     this.leftArm = new ModelRenderer((Model)this);
/* 102 */     this.leftArm.func_78793_a(9.0F, -9.6F, 1.0F);
/* 103 */     setRotationAngle(this.leftArm, 0.0F, 0.0F, -0.0349F);
/* 104 */     this.leftArm.func_78784_a(0, 46).func_228303_a_(0.0F, -2.0F, -2.5F, 5.0F, 10.0F, 5.0F, 0.0F, false);
/*     */     
/* 106 */     this.leftArm2 = new ModelRenderer((Model)this);
/* 107 */     this.leftArm2.func_78793_a(2.5F, 7.8F, 0.0F);
/* 108 */     this.leftArm.func_78792_a(this.leftArm2);
/* 109 */     setRotationAngle(this.leftArm2, 0.0F, 0.0F, 0.0349F);
/* 110 */     this.leftArm2.func_78784_a(21, 46).func_228303_a_(-2.5F, 0.0F, -2.5F, 5.0F, 10.0F, 5.0F, 0.0F, false);
/*     */     
/* 112 */     this.head = new ModelRenderer((Model)this);
/* 113 */     this.head.func_78793_a(0.0F, -12.2F, -3.0F);
/* 114 */     setRotationAngle(this.head, 0.0524F, 0.0F, 0.0F);
/* 115 */     this.head.func_78784_a(0, 0).func_228303_a_(-4.0F, -9.0F, -4.5F, 8.0F, 9.0F, 9.0F, 0.0F, false);
/*     */     
/* 117 */     this.snout = new ModelRenderer((Model)this);
/* 118 */     this.snout.func_78793_a(-2.0F, -1.5F, -4.5F);
/* 119 */     this.head.func_78792_a(this.snout);
/* 120 */     setRotationAngle(this.snout, -0.2094F, 0.0F, 0.0F);
/* 121 */     this.snout.func_78784_a(112, 35).func_228303_a_(0.0F, 0.0F, 0.0F, 4.0F, 7.0F, 4.0F, 0.0F, false);
/*     */     
/* 123 */     this.snout2 = new ModelRenderer((Model)this);
/* 124 */     this.snout2.func_78793_a(0.5F, 7.0F, 0.5F);
/* 125 */     this.snout.func_78792_a(this.snout2);
/* 126 */     setRotationAngle(this.snout2, 0.2094F, 0.0F, 0.0F);
/* 127 */     this.snout2.func_78784_a(116, 47).func_228303_a_(0.0F, 0.0F, 0.0F, 3.0F, 6.0F, 3.0F, 0.0F, false);
/*     */     
/* 129 */     this.snout3 = new ModelRenderer((Model)this);
/* 130 */     this.snout3.func_78793_a(0.5F, 5.5F, 0.5F);
/* 131 */     this.snout2.func_78792_a(this.snout3);
/* 132 */     setRotationAngle(this.snout3, 0.2094F, 0.0F, 0.0F);
/* 133 */     this.snout3.func_78784_a(120, 57).func_228303_a_(0.0F, 0.0F, 0.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);
/*     */     
/* 135 */     this.leftEar = new ModelRenderer((Model)this);
/* 136 */     this.leftEar.func_78793_a(3.5F, -3.8F, 0.0F);
/* 137 */     this.head.func_78792_a(this.leftEar);
/* 138 */     setRotationAngle(this.leftEar, -0.2365F, -1.0306F, 0.274F);
/* 139 */     this.leftEar.func_78784_a(0, 19).func_228303_a_(0.0F, -6.0F, 0.0F, 6.0F, 8.0F, 1.0F, 0.0F, true);
/*     */     
/* 141 */     this.rightEar = new ModelRenderer((Model)this);
/* 142 */     this.rightEar.func_78793_a(-3.5F, -3.8F, -1.0F);
/* 143 */     this.head.func_78792_a(this.rightEar);
/* 144 */     setRotationAngle(this.rightEar, -0.2365F, 1.0306F, -0.274F);
/* 145 */     this.rightEar.func_78784_a(0, 19).func_228303_a_(-6.0F, -6.0F, 0.0F, 6.0F, 8.0F, 1.0F, 0.0F, false);
/*     */     
/* 147 */     this.leftTusk = new ModelRenderer((Model)this);
/* 148 */     this.leftTusk.func_78793_a(2.3F, -1.0F, -3.5F);
/* 149 */     this.head.func_78792_a(this.leftTusk);
/* 150 */     setRotationAngle(this.leftTusk, -0.3491F, -0.1745F, 0.0F);
/* 151 */     this.leftTusk.func_78784_a(15, 19).func_228303_a_(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);
/*     */     
/* 153 */     this.leftTusk2 = new ModelRenderer((Model)this);
/* 154 */     this.leftTusk2.func_78793_a(0.0F, 3.8F, 0.0F);
/* 155 */     this.leftTusk.func_78792_a(this.leftTusk2);
/* 156 */     setRotationAngle(this.leftTusk2, -0.1745F, -0.0175F, 0.0F);
/* 157 */     this.leftTusk2.func_78784_a(15, 25).func_228303_a_(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);
/*     */     
/* 159 */     this.rightTusk = new ModelRenderer((Model)this);
/* 160 */     this.rightTusk.func_78793_a(-2.3F, -1.0F, -3.5F);
/* 161 */     this.head.func_78792_a(this.rightTusk);
/* 162 */     setRotationAngle(this.rightTusk, -0.3491F, 0.1745F, 0.0F);
/* 163 */     this.rightTusk.func_78784_a(15, 19).func_228303_a_(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);
/*     */     
/* 165 */     this.rightTusk2 = new ModelRenderer((Model)this);
/* 166 */     this.rightTusk2.func_78793_a(0.0F, 3.9F, 0.0F);
/* 167 */     this.rightTusk.func_78792_a(this.rightTusk2);
/* 168 */     setRotationAngle(this.rightTusk2, -0.1745F, 0.0175F, 0.0F);
/* 169 */     this.rightTusk2.func_78784_a(15, 25).func_228303_a_(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);
/*     */     
/* 171 */     this.field_78115_e = this.body;
/* 172 */     this.field_78116_c = this.head;
/* 173 */     this.field_178723_h = this.rightArm;
/* 174 */     this.field_178724_i = this.leftArm;
/* 175 */     this.field_178721_j = this.rightLeg;
/* 176 */     this.field_178722_k = this.leftLeg;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 182 */     this.body.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 183 */     this.rightLeg.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 184 */     this.leftLeg.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 185 */     this.rightArm.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 186 */     this.leftArm.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 187 */     this.head.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 194 */     boolean flag = (entity.func_184599_cB() > 4);
/* 195 */     boolean flag1 = entity.func_213314_bj();
/* 196 */     this.head.field_78796_g = netHeadYaw * 0.017453292F;
/* 197 */     if (flag) {
/* 198 */       this.head.field_78795_f = -0.7853982F;
/* 199 */     } else if (this.field_205061_a > 0.0F) {
/*     */       
/* 201 */       if (flag1) {
/* 202 */         this.head.field_78795_f = func_205060_a(this.head.field_78795_f, -0.7853982F, this.field_205061_a);
/*     */       } else {
/* 204 */         this.head.field_78795_f = func_205060_a(this.head.field_78795_f, headPitch * 0.017453292F, this.field_205061_a);
/*     */       } 
/*     */     } else {
/*     */       
/* 208 */       this.head.field_78795_f = headPitch * 0.015707964F;
/* 209 */       if (this.head.field_78795_f > 0.6D) {
/* 210 */         this.head.field_78795_f = 0.6F;
/*     */       }
/*     */     } 
/*     */     
/* 214 */     float f = 1.0F;
/* 215 */     this.rightArm.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.6662F + 3.1415927F) * 0.8F * limbSwingAmount * 0.5F / f;
/* 216 */     this.leftArm.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.6662F) * 0.8F * limbSwingAmount * 0.5F / f;
/* 217 */     this.rightLeg.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.6662F) * 0.7F * limbSwingAmount / f;
/* 218 */     this.leftLeg.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.6662F + 3.1415927F) * 0.7F * limbSwingAmount / f;
/* 219 */     if (!entity.func_184614_ca().func_190926_b())
/* 220 */       this.rightArm.field_78795_f += -0.15F; 
/* 221 */     if (entity.func_70051_ag()) {
/*     */       
/* 223 */       this.tail.field_78795_f = 1.6F + MathHelper.func_76134_b(limbSwing * 0.6662F) * 0.2F * limbSwingAmount;
/* 224 */       this.leftEar.field_78796_g = -0.3F - MathHelper.func_76134_b(limbSwing * 0.6662F) * 0.2F * limbSwingAmount;
/* 225 */       this.rightEar.field_78796_g = 0.3F + MathHelper.func_76134_b(limbSwing * 0.6662F) * 0.2F * limbSwingAmount;
/*     */     } 
/*     */ 
/*     */     
/* 229 */     this.field_217112_c = ((LivingEntity)entity).field_70733_aJ;
/* 230 */     boolean isBlackLeg = EntityStatsCapability.get((LivingEntity)entity).isBlackLeg();
/* 231 */     if (this.field_217112_c > 0.0F)
/*     */     {
/* 233 */       if (isBlackLeg) {
/*     */         
/* 235 */         this.body.field_78796_g = MathHelper.func_76126_a(MathHelper.func_76129_c(this.field_217112_c) * 6.2831855F) * 0.2F;
/* 236 */         this.rightLeg.field_78796_g += this.body.field_78796_g;
/* 237 */         this.leftLeg.field_78796_g += this.body.field_78796_g;
/* 238 */         float f1 = 1.0F - this.field_217112_c;
/* 239 */         f1 *= f1;
/* 240 */         f1 *= f1;
/* 241 */         f1 = 1.0F - f1;
/* 242 */         float f2 = MathHelper.func_76126_a(f1 * 3.1415927F);
/* 243 */         float f3 = MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * -(this.head.field_78795_f - 0.7F) * 0.75F;
/* 244 */         this.rightLeg.field_78795_f = (float)(this.rightArm.field_78795_f - f2 * 1.5D + f3);
/* 245 */         this.rightLeg.field_78796_g += this.body.field_78796_g * 2.0F;
/*     */       }
/*     */       else {
/*     */         
/* 249 */         this.body.field_78796_g = MathHelper.func_76126_a(MathHelper.func_76129_c(this.field_217112_c) * 6.2831855F) * 0.2F;
/* 250 */         this.body.field_78796_g = MathHelper.func_76126_a(MathHelper.func_76129_c(this.field_217112_c) * 6.2831855F) * 0.2F;
/* 251 */         this.rightArm.field_78798_e = MathHelper.func_76126_a(this.body2.field_78796_g) * 8.0F;
/* 252 */         this.rightArm.field_78800_c = -MathHelper.func_76134_b(this.body2.field_78796_g) * 8.0F;
/* 253 */         this.rightArm.field_78796_g += this.body2.field_78796_g;
/* 254 */         this.leftArm.field_78796_g += this.body2.field_78796_g;
/* 255 */         this.leftArm.field_78795_f += this.body2.field_78796_g;
/* 256 */         float f1 = 1.0F - this.field_217112_c;
/* 257 */         f1 *= f1;
/* 258 */         f1 *= f1;
/* 259 */         f1 = 1.0F - f1;
/* 260 */         float f2 = MathHelper.func_76126_a(f1 * 3.1415927F);
/* 261 */         float f3 = MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * -(this.head.field_78795_f - 0.7F) * 0.75F;
/* 262 */         this.rightArm.field_78795_f = (float)(this.rightArm.field_78795_f - f2 * 1.2D + f3);
/* 263 */         this.rightArm.field_78796_g += this.body.field_78796_g * 2.0F;
/* 264 */         this.rightArm.field_78808_h += MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * -0.4F;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 269 */     if (entity.func_213453_ef()) {
/*     */       
/* 271 */       this.body.field_78795_f = 0.5F;
/* 272 */       this.body.field_78798_e -= 4.0F;
/* 273 */       this.rightArm.field_78795_f += 0.4F;
/* 274 */       this.rightArm.field_78798_e -= 8.5F;
/* 275 */       this.rightArm.field_78797_d++;
/* 276 */       this.leftArm.field_78795_f += 0.4F;
/* 277 */       this.leftArm.field_78798_e -= 8.5F;
/* 278 */       this.leftArm.field_78797_d++;
/* 279 */       this.rightLeg.field_78798_e = 1.5F;
/* 280 */       this.leftLeg.field_78798_e = 1.5F;
/* 281 */       this.rightLeg.field_78797_d = 9.0F;
/* 282 */       this.leftLeg.field_78797_d = 9.0F;
/* 283 */       this.head.field_78798_e -= 7.0F;
/* 284 */       this.head.field_78797_d += 3.0F;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
/* 291 */     if (side == HandSide.RIGHT) {
/*     */       
/* 293 */       matrixStack.func_227861_a_(-0.3D, -0.15D, 0.0D);
/* 294 */       this.rightArm2.func_228309_a_(matrixStack, vertex, packedLight, OverlayTexture.field_229196_a_, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */     }
/*     */     else {
/*     */       
/* 298 */       matrixStack.func_227861_a_(0.6D, -0.15D, 0.0D);
/* 299 */       this.rightArm2.func_228309_a_(matrixStack, vertex, packedLight, OverlayTexture.field_229196_a_, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
/* 306 */     if (side == HandSide.RIGHT) {
/*     */       
/* 308 */       matrixStack.func_227861_a_(0.0D, -1.2D, 0.3D);
/* 309 */       matrixStack.func_227862_a_(1.5F, 1.5F, 1.5F);
/* 310 */       matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(-60.0F));
/* 311 */       this.rightLeg.func_228309_a_(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     }
/*     */     else {
/*     */       
/* 315 */       matrixStack.func_227861_a_(0.0D, -1.2D, 0.3D);
/* 316 */       matrixStack.func_227862_a_(1.5F, 1.5F, 1.5F);
/* 317 */       matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(60.0F));
/* 318 */       this.leftLeg.func_228309_a_(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225599_a_(HandSide side, MatrixStack matrixStack) {
/* 325 */     super.func_225599_a_(side, matrixStack);
/* 326 */     matrixStack.func_227861_a_((side == HandSide.RIGHT) ? -0.06D : 0.06D, 0.5D, 0.0D);
/* 327 */     matrixStack.func_227863_a_(Vector3f.field_229183_f_.func_229187_a_(0.0F));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 332 */     modelRenderer.field_78795_f = x;
/* 333 */     modelRenderer.field_78796_g = y;
/* 334 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\morphs\ZouHeavyModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */