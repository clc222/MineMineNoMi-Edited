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
/*     */ public class MammothHeavyModel<T extends LivingEntity>
/*     */   extends MorphModel<T> implements IHasArm {
/*     */   private final ModelRenderer body;
/*     */   private final ModelRenderer body2;
/*     */   private final ModelRenderer tail;
/*     */   private final ModelRenderer tail_r1;
/*     */   private final ModelRenderer rightLeg;
/*     */   private final ModelRenderer rightLeg2;
/*     */   private final ModelRenderer leftLeg;
/*     */   private final ModelRenderer leftLeg2;
/*     */   private final ModelRenderer rightArm;
/*     */   private final ModelRenderer rightArm2;
/*     */   private final ModelRenderer leftArm;
/*     */   private final ModelRenderer leftArm2;
/*     */   private final ModelRenderer head;
/*     */   private final ModelRenderer snout;
/*     */   private final ModelRenderer snout2;
/*     */   private final ModelRenderer snout3;
/*     */   private final ModelRenderer leftEar;
/*     */   private final ModelRenderer rightEar;
/*     */   private final ModelRenderer rightTusk;
/*     */   private final ModelRenderer rightTusk2;
/*     */   private final ModelRenderer rightTusk3;
/*     */   private final ModelRenderer leftTusk;
/*     */   private final ModelRenderer leftTusk2;
/*     */   private final ModelRenderer leftTusk3;
/*     */   
/*     */   public MammothHeavyModel() {
/*  45 */     super(1.0F);
/*  46 */     this.field_78090_t = 128;
/*  47 */     this.field_78089_u = 128;
/*     */     
/*  49 */     this.body = new ModelRenderer((Model)this);
/*  50 */     this.body.func_78793_a(0.0F, -2.6F, 2.7F);
/*  51 */     setRotationAngle(this.body, 0.0873F, 0.0F, 0.0F);
/*  52 */     this.body.func_78784_a(0, 0).func_228303_a_(-9.0F, -10.0F, -7.0F, 18.0F, 14.0F, 10.0F, 0.0F, false);
/*     */     
/*  54 */     this.body2 = new ModelRenderer((Model)this);
/*  55 */     this.body2.func_78793_a(0.0F, 13.8F, 0.3F);
/*  56 */     this.body.func_78792_a(this.body2);
/*  57 */     this.body2.func_78784_a(0, 24).func_228303_a_(-8.5F, -10.0F, -7.0F, 17.0F, 10.0F, 10.0F, 0.0F, false);
/*     */     
/*  59 */     this.tail = new ModelRenderer((Model)this);
/*  60 */     this.tail.func_78793_a(0.25F, -2.5F, 2.0F);
/*  61 */     this.body2.func_78792_a(this.tail);
/*  62 */     setRotationAngle(this.tail, 0.4363F, 0.0F, 0.0F);
/*     */     
/*  64 */     this.tail_r1 = new ModelRenderer((Model)this);
/*  65 */     this.tail_r1.func_78793_a(-1.0F, -2.7F, 0.0F);
/*  66 */     this.tail.func_78792_a(this.tail_r1);
/*  67 */     setRotationAngle(this.tail_r1, 0.48F, 0.0F, 0.0F);
/*  68 */     this.tail_r1.func_78784_a(56, 0).func_228303_a_(-0.75F, 0.587F, -3.0383F, 3.0F, 8.0F, 3.0F, 0.0F, false);
/*     */     
/*  70 */     this.rightLeg = new ModelRenderer((Model)this);
/*  71 */     this.rightLeg.func_78793_a(-4.0F, 11.1F, 1.0F);
/*  72 */     setRotationAngle(this.rightLeg, -0.0524F, 0.0F, 0.0F);
/*  73 */     this.rightLeg.func_78784_a(28, 60).func_228303_a_(-3.5F, 0.0F, -2.5F, 6.0F, 7.0F, 6.0F, 0.0F, false);
/*     */     
/*  75 */     this.rightLeg2 = new ModelRenderer((Model)this);
/*  76 */     this.rightLeg2.func_78793_a(0.0F, 6.8F, 0.0F);
/*  77 */     this.rightLeg.func_78792_a(this.rightLeg2);
/*  78 */     setRotationAngle(this.rightLeg2, 0.0524F, 0.0F, 0.0F);
/*  79 */     this.rightLeg2.func_78784_a(0, 62).func_228303_a_(-3.5F, 0.0F, -2.5F, 6.0F, 6.0F, 6.0F, 0.0F, false);
/*     */     
/*  81 */     this.leftLeg = new ModelRenderer((Model)this);
/*  82 */     this.leftLeg.func_78793_a(5.0F, 11.1F, 1.0F);
/*  83 */     setRotationAngle(this.leftLeg, -0.0524F, 0.0F, 0.0F);
/*  84 */     this.leftLeg.func_78784_a(28, 60).func_228303_a_(-3.5F, 0.0F, -2.5F, 6.0F, 7.0F, 6.0F, 0.0F, false);
/*     */     
/*  86 */     this.leftLeg2 = new ModelRenderer((Model)this);
/*  87 */     this.leftLeg2.func_78793_a(0.0F, 6.8F, 0.0F);
/*  88 */     this.leftLeg.func_78792_a(this.leftLeg2);
/*  89 */     setRotationAngle(this.leftLeg2, 0.0524F, 0.0F, 0.0F);
/*  90 */     this.leftLeg2.func_78784_a(0, 62).func_228303_a_(-3.5F, 0.0F, -2.5F, 6.0F, 6.0F, 6.0F, 0.0F, false);
/*     */     
/*  92 */     this.rightArm = new ModelRenderer((Model)this);
/*  93 */     this.rightArm.func_78793_a(-9.0F, -9.6F, 1.0F);
/*  94 */     setRotationAngle(this.rightArm, 0.0F, 0.0F, 0.0349F);
/*  95 */     this.rightArm.func_78784_a(50, 18).func_228303_a_(-6.0F, -2.0F, -3.5F, 6.0F, 10.0F, 6.0F, 0.0F, false);
/*     */     
/*  97 */     this.rightArm2 = new ModelRenderer((Model)this);
/*  98 */     this.rightArm2.func_78793_a(-2.5F, 7.8F, 0.0F);
/*  99 */     this.rightArm.func_78792_a(this.rightArm2);
/* 100 */     setRotationAngle(this.rightArm2, 0.0F, 0.0F, -0.0349F);
/* 101 */     this.rightArm2.func_78784_a(54, 34).func_228303_a_(-3.5F, 0.0F, -3.5F, 6.0F, 10.0F, 6.0F, 0.01F, false);
/*     */     
/* 103 */     this.leftArm = new ModelRenderer((Model)this);
/* 104 */     this.leftArm.func_78793_a(9.0F, -9.6F, 1.0F);
/* 105 */     setRotationAngle(this.leftArm, 0.0F, 0.0F, -0.0349F);
/* 106 */     this.leftArm.func_78784_a(50, 18).func_228303_a_(0.0F, -2.0F, -3.5F, 6.0F, 10.0F, 6.0F, 0.0F, true);
/*     */     
/* 108 */     this.leftArm2 = new ModelRenderer((Model)this);
/* 109 */     this.leftArm2.func_78793_a(2.5F, 7.8F, 0.0F);
/* 110 */     this.leftArm.func_78792_a(this.leftArm2);
/* 111 */     setRotationAngle(this.leftArm2, 0.0F, 0.0F, 0.0349F);
/* 112 */     this.leftArm2.func_78784_a(54, 34).func_228303_a_(-2.5F, 0.0F, -3.5F, 6.0F, 10.0F, 6.0F, 0.01F, true);
/*     */     
/* 114 */     this.head = new ModelRenderer((Model)this);
/* 115 */     this.head.func_78793_a(0.0F, -12.2F, -3.0F);
/* 116 */     setRotationAngle(this.head, 0.0524F, 0.0F, 0.0F);
/* 117 */     this.head.func_78784_a(0, 44).func_228303_a_(-4.0F, -9.0F, -4.5F, 8.0F, 9.0F, 9.0F, 0.0F, false);
/*     */     
/* 119 */     this.snout = new ModelRenderer((Model)this);
/* 120 */     this.snout.func_78793_a(-2.5F, -1.5F, -4.5F);
/* 121 */     this.head.func_78792_a(this.snout);
/* 122 */     setRotationAngle(this.snout, -0.3839F, 0.0F, 0.0F);
/* 123 */     this.snout.func_78784_a(34, 48).func_228303_a_(0.0F, 0.0F, 0.0F, 5.0F, 7.0F, 5.0F, 0.0F, false);
/*     */     
/* 125 */     this.snout2 = new ModelRenderer((Model)this);
/* 126 */     this.snout2.func_78793_a(1.5F, 7.0F, 0.5F);
/* 127 */     this.snout.func_78792_a(this.snout2);
/* 128 */     setRotationAngle(this.snout2, 0.2094F, 0.0F, 0.0F);
/* 129 */     this.snout2.func_78784_a(72, 30).func_228303_a_(-1.0F, 0.0F, 0.0F, 4.0F, 6.0F, 4.0F, 0.0F, false);
/*     */     
/* 131 */     this.snout3 = new ModelRenderer((Model)this);
/* 132 */     this.snout3.func_78793_a(0.5F, 5.5F, 0.5F);
/* 133 */     this.snout2.func_78792_a(this.snout3);
/* 134 */     setRotationAngle(this.snout3, 0.2094F, 0.0F, 0.0F);
/* 135 */     this.snout3.func_78784_a(74, 22).func_228303_a_(-1.0F, 0.0F, 0.0F, 3.0F, 5.0F, 3.0F, 0.0F, false);
/*     */     
/* 137 */     this.leftEar = new ModelRenderer((Model)this);
/* 138 */     this.leftEar.func_78793_a(3.5F, -3.55F, 0.0F);
/* 139 */     this.head.func_78792_a(this.leftEar);
/* 140 */     setRotationAngle(this.leftEar, -0.2365F, -1.0306F, 0.274F);
/* 141 */     this.leftEar.func_78784_a(68, 13).func_228303_a_(-1.0F, -6.0F, -0.5F, 8.0F, 8.0F, 1.0F, 0.0F, true);
/*     */     
/* 143 */     this.rightEar = new ModelRenderer((Model)this);
/* 144 */     this.rightEar.func_78793_a(-3.5F, -3.8F, -1.0F);
/* 145 */     this.head.func_78792_a(this.rightEar);
/* 146 */     setRotationAngle(this.rightEar, -0.2365F, 1.0306F, -0.274F);
/* 147 */     this.rightEar.func_78784_a(68, 13).func_228303_a_(-8.0F, -6.0F, 0.0F, 8.0F, 8.0F, 1.0F, 0.0F, false);
/*     */     
/* 149 */     this.rightTusk = new ModelRenderer((Model)this);
/* 150 */     this.rightTusk.func_78793_a(-3.25F, -1.8F, -1.0F);
/* 151 */     this.head.func_78792_a(this.rightTusk);
/* 152 */     setRotationAngle(this.rightTusk, 2.5307F, 0.2618F, 0.0F);
/* 153 */     this.rightTusk.func_78784_a(69, 0).func_228303_a_(-1.4352F, -6.5246F, -1.629F, 3.0F, 6.0F, 3.0F, 0.0F, false);
/*     */     
/* 155 */     this.rightTusk2 = new ModelRenderer((Model)this);
/* 156 */     this.rightTusk2.func_78793_a(0.2445F, -6.1921F, -0.3535F);
/* 157 */     this.rightTusk.func_78792_a(this.rightTusk2);
/* 158 */     setRotationAngle(this.rightTusk2, -0.3054F, 0.0F, 0.0F);
/* 159 */     this.rightTusk2.func_78784_a(69, 0).func_228303_a_(-1.6298F, -5.9104F, -1.4339F, 3.0F, 6.0F, 3.0F, 0.0F, false);
/*     */     
/* 161 */     this.rightTusk3 = new ModelRenderer((Model)this);
/* 162 */     this.rightTusk3.func_78793_a(0.0F, 0.0F, 0.0F);
/* 163 */     this.rightTusk2.func_78792_a(this.rightTusk3);
/* 164 */     setRotationAngle(this.rightTusk3, -0.3054F, 0.0F, 0.0F);
/* 165 */     this.rightTusk3.func_78784_a(69, 0).func_228303_a_(-1.6298F, -11.1604F, -3.1839F, 3.0F, 6.0F, 3.0F, 0.0F, false);
/*     */     
/* 167 */     this.leftTusk = new ModelRenderer((Model)this);
/* 168 */     this.leftTusk.func_78793_a(2.5F, -1.8F, -1.0F);
/* 169 */     this.head.func_78792_a(this.leftTusk);
/* 170 */     setRotationAngle(this.leftTusk, 2.5307F, -0.2618F, 0.0F);
/* 171 */     this.leftTusk.func_78784_a(69, 0).func_228303_a_(-1.0F, -6.5246F, -1.629F, 3.0F, 6.0F, 3.0F, 0.0F, true);
/*     */     
/* 173 */     this.leftTusk2 = new ModelRenderer((Model)this);
/* 174 */     this.leftTusk2.func_78793_a(0.2445F, -6.1921F, -0.3535F);
/* 175 */     this.leftTusk.func_78792_a(this.leftTusk2);
/* 176 */     setRotationAngle(this.leftTusk2, -0.3054F, 0.0F, 0.0F);
/* 177 */     this.leftTusk2.func_78784_a(69, 0).func_228303_a_(-1.2445F, -5.9104F, -1.4339F, 3.0F, 6.0F, 3.0F, 0.0F, true);
/*     */     
/* 179 */     this.leftTusk3 = new ModelRenderer((Model)this);
/* 180 */     this.leftTusk3.func_78793_a(0.0F, 0.0F, 0.0F);
/* 181 */     this.leftTusk2.func_78792_a(this.leftTusk3);
/* 182 */     setRotationAngle(this.leftTusk3, -0.3054F, 0.0F, 0.0F);
/* 183 */     this.leftTusk3.func_78784_a(69, 0).func_228303_a_(-1.2445F, -11.1604F, -3.1839F, 3.0F, 6.0F, 3.0F, 0.0F, true);
/*     */     
/* 185 */     this.field_78115_e = this.body;
/* 186 */     this.field_78116_c = this.head;
/* 187 */     this.field_178723_h = this.rightArm;
/* 188 */     this.field_178724_i = this.leftArm;
/* 189 */     this.field_178721_j = this.rightLeg;
/* 190 */     this.field_178722_k = this.leftLeg;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 197 */     boolean flag = (entity.func_184599_cB() > 4);
/* 198 */     boolean flag1 = entity.func_213314_bj();
/* 199 */     this.head.field_78796_g = netHeadYaw * 0.011635529F;
/* 200 */     if (flag) {
/* 201 */       this.head.field_78795_f = -0.7853982F;
/* 202 */     } else if (this.field_205061_a > 0.0F) {
/*     */       
/* 204 */       if (flag1) {
/* 205 */         this.head.field_78795_f = func_205060_a(this.head.field_78795_f, -0.7853982F, this.field_205061_a);
/*     */       } else {
/* 207 */         this.head.field_78795_f = func_205060_a(this.head.field_78795_f, headPitch * 0.017453292F, this.field_205061_a);
/*     */       } 
/*     */     } else {
/*     */       
/* 211 */       this.head.field_78795_f = headPitch * 0.015707964F;
/* 212 */       if (this.head.field_78795_f > 0.6D) {
/* 213 */         this.head.field_78795_f = 0.6F;
/*     */       }
/*     */     } 
/*     */     
/* 217 */     float f = 1.0F;
/* 218 */     this.rightArm.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.3F + 3.1415927F) * 0.8F * limbSwingAmount * 0.5F / f;
/* 219 */     this.leftArm.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.3F) * 0.8F * limbSwingAmount * 0.5F / f;
/* 220 */     this.rightLeg.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.3F) * 0.7F * limbSwingAmount / f;
/* 221 */     this.leftLeg.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.3F + 3.1415927F) * 0.7F * limbSwingAmount / f;
/* 222 */     if (!entity.func_184614_ca().func_190926_b())
/* 223 */       this.rightArm.field_78795_f += -0.15F; 
/* 224 */     if (entity.func_70051_ag()) {
/*     */       
/* 226 */       this.tail.field_78795_f = 1.6F + MathHelper.func_76134_b(limbSwing * 0.6662F) * 0.2F * limbSwingAmount;
/* 227 */       this.leftEar.field_78796_g = -0.3F - MathHelper.func_76134_b(limbSwing * 0.6F) * 0.2F * limbSwingAmount;
/* 228 */       this.rightEar.field_78800_c = (float)(this.rightEar.field_78800_c + 0.8D);
/* 229 */       this.rightEar.field_78796_g = 0.3F + MathHelper.func_76134_b(limbSwing * 0.6F) * 0.2F * limbSwingAmount;
/*     */     } 
/*     */ 
/*     */     
/* 233 */     this.field_217112_c = ((LivingEntity)entity).field_70733_aJ;
/* 234 */     boolean isBlackLeg = EntityStatsCapability.get((LivingEntity)entity).isBlackLeg();
/* 235 */     if (this.field_217112_c > 0.0F) {
/*     */       
/* 237 */       HandSide handside = func_217147_a((LivingEntity)entity);
/* 238 */       ModelRenderer modelrenderer = func_187074_a(handside);
/* 239 */       if (isBlackLeg) {
/*     */         
/* 241 */         this.body.field_78796_g = MathHelper.func_76126_a(MathHelper.func_76129_c(this.field_217112_c) * 6.2831855F) * 0.2F;
/* 242 */         this.rightLeg.field_78796_g += this.body.field_78796_g;
/* 243 */         this.leftLeg.field_78796_g += this.body.field_78796_g;
/* 244 */         float f1 = 1.0F - this.field_217112_c;
/* 245 */         f1 *= f1;
/* 246 */         f1 *= f1;
/* 247 */         f1 = 1.0F - f1;
/* 248 */         float f2 = MathHelper.func_76126_a(f1 * 3.1415927F);
/* 249 */         float f3 = MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * -(this.head.field_78795_f - 0.7F) * 0.75F;
/* 250 */         this.rightLeg.field_78795_f = (float)(this.rightArm.field_78795_f - f2 * 1.5D + f3);
/* 251 */         this.rightLeg.field_78796_g += this.body.field_78796_g * 2.0F;
/*     */       }
/*     */       else {
/*     */         
/* 255 */         f = this.field_217112_c;
/* 256 */         this.body.field_78796_g = MathHelper.func_76126_a(MathHelper.func_76129_c(f) * 6.2831855F) * 0.2F;
/* 257 */         if (handside == HandSide.LEFT)
/* 258 */           this.body.field_78796_g *= -1.0F; 
/* 259 */         this.rightArm.field_78798_e = MathHelper.func_76126_a(this.body.field_78796_g) * 8.0F;
/* 260 */         this.rightArm.field_78800_c = -MathHelper.func_76134_b(this.body.field_78796_g) * 8.0F;
/* 261 */         this.leftArm.field_78798_e = -MathHelper.func_76126_a(this.body.field_78796_g) * 8.0F;
/* 262 */         this.leftArm.field_78800_c = MathHelper.func_76134_b(this.body.field_78796_g) * 8.0F;
/* 263 */         this.rightArm.field_78796_g += this.body.field_78796_g;
/* 264 */         this.leftArm.field_78796_g += this.body.field_78796_g;
/* 265 */         this.leftArm.field_78795_f += this.body.field_78796_g;
/* 266 */         f = 1.0F - this.field_217112_c;
/* 267 */         f *= f;
/* 268 */         f *= f;
/* 269 */         f = 1.0F - f;
/* 270 */         float f1 = MathHelper.func_76126_a(f * 3.1415927F);
/* 271 */         float f2 = MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * -(this.head.field_78795_f - 0.7F) * 0.75F;
/* 272 */         modelrenderer.field_78795_f = (float)(modelrenderer.field_78795_f - f1 * 1.2D + f2);
/* 273 */         modelrenderer.field_78796_g += this.body.field_78796_g * 2.0F;
/* 274 */         modelrenderer.field_78808_h += MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * -0.4F;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 279 */     if (entity.func_213453_ef()) {
/*     */       
/* 281 */       this.body.field_78795_f = 0.5F;
/* 282 */       this.body.field_78798_e -= 4.0F;
/* 283 */       this.rightArm.field_78795_f += 0.4F;
/* 284 */       this.rightArm.field_78798_e -= 8.5F;
/* 285 */       this.rightArm.field_78797_d++;
/* 286 */       this.leftArm.field_78795_f += 0.4F;
/* 287 */       this.leftArm.field_78798_e -= 8.5F;
/* 288 */       this.leftArm.field_78797_d++;
/* 289 */       this.rightLeg.field_78798_e = 1.5F;
/* 290 */       this.leftLeg.field_78798_e = 1.5F;
/* 291 */       this.rightLeg.field_78797_d = 9.0F;
/* 292 */       this.leftLeg.field_78797_d = 9.0F;
/* 293 */       this.head.field_78798_e -= 7.0F;
/* 294 */       this.head.field_78797_d += 3.0F;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 301 */     this.body.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 302 */     this.rightLeg.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 303 */     this.leftLeg.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 304 */     this.rightArm.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 305 */     this.leftArm.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 306 */     this.head.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 311 */     modelRenderer.field_78795_f = x;
/* 312 */     modelRenderer.field_78796_g = y;
/* 313 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
/* 319 */     if (side == HandSide.RIGHT) {
/*     */       
/* 321 */       matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(90.0F));
/* 322 */       matrixStack.func_227861_a_(0.15D, -0.3D, -0.5D);
/* 323 */       this.rightArm2.func_228309_a_(matrixStack, vertex, packedLight, OverlayTexture.field_229196_a_, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */     }
/*     */     else {
/*     */       
/* 327 */       matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(-90.0F));
/* 328 */       matrixStack.func_227861_a_(0.15D, -0.3D, -0.5D);
/* 329 */       this.rightArm2.func_228309_a_(matrixStack, vertex, packedLight, OverlayTexture.field_229196_a_, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
/* 336 */     if (side == HandSide.RIGHT) {
/*     */       
/* 338 */       matrixStack.func_227861_a_(0.0D, -1.2D, 0.3D);
/* 339 */       matrixStack.func_227862_a_(1.5F, 1.5F, 1.5F);
/* 340 */       matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(-60.0F));
/* 341 */       this.rightLeg.func_228309_a_(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     }
/*     */     else {
/*     */       
/* 345 */       matrixStack.func_227861_a_(0.0D, -1.2D, 0.3D);
/* 346 */       matrixStack.func_227862_a_(1.5F, 1.5F, 1.5F);
/* 347 */       matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(60.0F));
/* 348 */       this.leftLeg.func_228309_a_(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225599_a_(HandSide side, MatrixStack matrixStack) {
/* 355 */     super.func_225599_a_(side, matrixStack);
/* 356 */     matrixStack.func_227861_a_((side == HandSide.RIGHT) ? -0.1D : 0.1D, 0.4D, 0.0D);
/* 357 */     matrixStack.func_227863_a_(Vector3f.field_229183_f_.func_229187_a_(0.0F));
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\morphs\MammothHeavyModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */