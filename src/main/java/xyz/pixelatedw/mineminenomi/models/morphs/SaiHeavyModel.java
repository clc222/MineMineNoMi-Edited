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
/*     */ public class SaiHeavyModel<T extends LivingEntity>
/*     */   extends MorphModel<T> implements IHasArm {
/*     */   private final ModelRenderer body;
/*     */   private final ModelRenderer rightLeg;
/*     */   private final ModelRenderer rightLeg2;
/*     */   private final ModelRenderer rightLeg3;
/*     */   private final ModelRenderer rightArm;
/*     */   private final ModelRenderer rightArm2;
/*     */   private final ModelRenderer leftLeg;
/*     */   private final ModelRenderer leftLeg2;
/*     */   private final ModelRenderer leftLeg3;
/*     */   private final ModelRenderer leftArm;
/*     */   private final ModelRenderer leftArm2;
/*     */   private final ModelRenderer neck;
/*     */   private final ModelRenderer head;
/*     */   private final ModelRenderer lowerHead;
/*     */   private final ModelRenderer upperHead;
/*     */   private final ModelRenderer middleHead;
/*     */   private final ModelRenderer horn1;
/*     */   private final ModelRenderer horn2;
/*     */   private final ModelRenderer horn3;
/*     */   private final ModelRenderer horn4;
/*     */   private final ModelRenderer rightEar;
/*     */   private final ModelRenderer leftEar;
/*     */   
/*     */   public SaiHeavyModel() {
/*  42 */     super(1.0F);
/*  43 */     this.field_78090_t = 128;
/*  44 */     this.field_78089_u = 128;
/*     */     
/*  46 */     this.body = new ModelRenderer((Model)this);
/*  47 */     this.body.func_78793_a(0.0F, 4.6667F, 0.0F);
/*  48 */     this.body.func_78784_a(0, 0).func_228303_a_(-4.0F, -10.6667F, -8.5F, 10.0F, 12.0F, 17.0F, 0.0F, false);
/*  49 */     this.body.func_78784_a(0, 29).func_228303_a_(-6.0F, -9.6667F, -6.5F, 11.0F, 15.0F, 13.0F, 0.0F, false);
/*  50 */     this.body.func_78784_a(37, 0).func_228303_a_(-5.0F, 4.3333F, -5.5F, 9.0F, 5.0F, 11.0F, 0.0F, false);
/*     */     
/*  52 */     this.rightLeg = new ModelRenderer((Model)this);
/*  53 */     this.rightLeg.func_78793_a(1.45F, 12.5F, -3.5F);
/*  54 */     setRotationAngle(this.rightLeg, 0.0F, 0.0F, 0.1745F);
/*  55 */     this.rightLeg.func_78784_a(66, 0).func_228303_a_(-4.45F, 0.0F, -2.5F, 5.0F, 6.0F, 5.0F, 0.0F, false);
/*     */     
/*  57 */     this.rightLeg2 = new ModelRenderer((Model)this);
/*  58 */     this.rightLeg2.func_78793_a(-2.3908F, 6.3707F, 0.0F);
/*  59 */     this.rightLeg.func_78792_a(this.rightLeg2);
/*  60 */     setRotationAngle(this.rightLeg2, 0.0F, 0.0F, -0.3054F);
/*  61 */     this.rightLeg2.func_78784_a(72, 11).func_228303_a_(-1.5092F, -1.1207F, -2.5F, 4.0F, 6.0F, 5.0F, -0.01F, false);
/*     */     
/*  63 */     this.rightLeg3 = new ModelRenderer((Model)this);
/*  64 */     this.rightLeg3.func_78793_a(1.5408F, 5.1293F, 0.0F);
/*  65 */     this.rightLeg2.func_78792_a(this.rightLeg3);
/*  66 */     setRotationAngle(this.rightLeg3, 0.0F, 0.0F, 0.1309F);
/*  67 */     this.rightLeg3.func_78784_a(62, 28).func_228303_a_(-3.5F, -1.0F, -2.5F, 7.0F, 2.0F, 5.0F, 0.0F, true);
/*     */     
/*  69 */     this.rightArm = new ModelRenderer((Model)this);
/*  70 */     this.rightArm.func_78793_a(1.0F, -1.25F, -7.5F);
/*  71 */     setRotationAngle(this.rightArm, 0.0F, 0.0F, 0.1745F);
/*  72 */     this.rightArm.func_78784_a(0, 57).func_228303_a_(-3.0F, -3.25F, -5.5F, 6.0F, 10.0F, 5.0F, 0.0F, false);
/*     */     
/*  74 */     this.rightArm2 = new ModelRenderer((Model)this);
/*  75 */     this.rightArm2.func_78793_a(-1.1263F, 7.7688F, -3.0F);
/*  76 */     this.rightArm.func_78792_a(this.rightArm2);
/*  77 */     setRotationAngle(this.rightArm2, 0.0F, 0.0F, -0.2618F);
/*  78 */     this.rightArm2.func_78784_a(0, 72).func_228303_a_(-1.0737F, -1.5188F, -2.0F, 5.0F, 7.0F, 4.0F, 0.0F, false);
/*     */     
/*  80 */     this.leftLeg = new ModelRenderer((Model)this);
/*  81 */     this.leftLeg.func_78793_a(-0.8F, 12.0F, 3.5F);
/*  82 */     setRotationAngle(this.leftLeg, 0.0F, 0.0F, 0.1745F);
/*  83 */     this.leftLeg.func_78784_a(66, 0).func_228303_a_(-2.2F, 0.25F, -2.5F, 5.0F, 6.0F, 5.0F, 0.0F, true);
/*     */     
/*  85 */     this.leftLeg2 = new ModelRenderer((Model)this);
/*  86 */     this.leftLeg2.func_78793_a(-0.6408F, 6.1207F, 0.0F);
/*  87 */     this.leftLeg.func_78792_a(this.leftLeg2);
/*  88 */     setRotationAngle(this.leftLeg2, 0.0F, 0.0F, -0.3054F);
/*  89 */     this.leftLeg2.func_78784_a(72, 11).func_228303_a_(-1.0592F, -0.6207F, -2.5F, 4.0F, 6.0F, 5.0F, -0.01F, true);
/*     */     
/*  91 */     this.leftLeg3 = new ModelRenderer((Model)this);
/*  92 */     this.leftLeg3.func_78793_a(1.5408F, 4.8793F, 0.0F);
/*  93 */     this.leftLeg2.func_78792_a(this.leftLeg3);
/*  94 */     setRotationAngle(this.leftLeg3, 0.0F, 0.0F, 0.1309F);
/*  95 */     this.leftLeg3.func_78784_a(62, 28).func_228303_a_(-3.0F, -0.25F, -2.5F, 7.0F, 2.0F, 5.0F, 0.0F, true);
/*     */     
/*  97 */     this.leftArm = new ModelRenderer((Model)this);
/*  98 */     this.leftArm.func_78793_a(1.0F, -2.0F, 7.5F);
/*  99 */     setRotationAngle(this.leftArm, 0.0F, 0.0F, 0.1745F);
/* 100 */     this.leftArm.func_78784_a(0, 57).func_228303_a_(-3.0F, -2.5F, 0.5F, 6.0F, 10.0F, 5.0F, 0.0F, true);
/*     */     
/* 102 */     this.leftArm2 = new ModelRenderer((Model)this);
/* 103 */     this.leftArm2.func_78793_a(1.1883F, 6.7568F, 3.0F);
/* 104 */     this.leftArm.func_78792_a(this.leftArm2);
/* 105 */     setRotationAngle(this.leftArm2, 0.0F, 3.1416F, -0.2618F);
/* 106 */     this.leftArm2.func_78784_a(0, 72).func_228303_a_(-1.1383F, -0.7568F, -2.0F, 5.0F, 7.0F, 4.0F, 0.0F, true);
/*     */     
/* 108 */     this.neck = new ModelRenderer((Model)this);
/* 109 */     this.neck.func_78793_a(-1.0F, 24.5F, 0.0F);
/* 110 */     setRotationAngle(this.neck, 0.0F, 0.0F, 0.2182F);
/* 111 */     this.neck.func_78784_a(54, 16).func_228303_a_(-8.6F, -33.25F, -3.5F, 4.0F, 5.0F, 7.0F, 0.0F, false);
/*     */     
/* 113 */     this.head = new ModelRenderer((Model)this);
/* 114 */     this.head.func_78793_a(-1.0F, 24.0F, 0.0F);
/* 115 */     this.head.func_78784_a(0, 0).func_228303_a_(2.0F, -34.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 117 */     this.lowerHead = new ModelRenderer((Model)this);
/* 118 */     this.lowerHead.func_78793_a(4.1283F, -31.9102F, 0.0F);
/* 119 */     this.head.func_78792_a(this.lowerHead);
/* 120 */     setRotationAngle(this.lowerHead, 0.0F, 0.0F, -0.4363F);
/* 121 */     this.lowerHead.func_78784_a(48, 42).func_228303_a_(-4.0F, -4.0F, -3.5F, 8.0F, 8.0F, 7.0F, 0.01F, false);
/*     */     
/* 123 */     this.upperHead = new ModelRenderer((Model)this);
/* 124 */     this.upperHead.func_78793_a(9.9143F, -30.4729F, 0.0F);
/* 125 */     this.head.func_78792_a(this.upperHead);
/* 126 */     setRotationAngle(this.upperHead, 0.0F, 0.0F, 0.48F);
/* 127 */     this.upperHead.func_78784_a(35, 29).func_228303_a_(-5.0F, -3.0F, -3.5F, 10.0F, 6.0F, 7.0F, 0.02F, false);
/*     */     
/* 129 */     this.middleHead = new ModelRenderer((Model)this);
/* 130 */     this.middleHead.func_78793_a(7.0387F, -34.7554F, 0.0F);
/* 131 */     this.head.func_78792_a(this.middleHead);
/* 132 */     setRotationAngle(this.middleHead, 0.0F, 0.0F, 0.5236F);
/* 133 */     this.middleHead.func_78784_a(44, 57).func_228303_a_(-2.5F, -1.0F, -3.5F, 5.0F, 2.0F, 7.0F, 0.0F, false);
/*     */     
/* 135 */     this.horn1 = new ModelRenderer((Model)this);
/* 136 */     this.horn1.func_78793_a(15.3463F, -32.0735F, 0.0F);
/* 137 */     this.head.func_78792_a(this.horn1);
/* 138 */     setRotationAngle(this.horn1, 0.0F, 0.0F, 0.6109F);
/* 139 */     this.horn1.func_78784_a(0, 5).func_228303_a_(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
/*     */     
/* 141 */     this.horn2 = new ModelRenderer((Model)this);
/* 142 */     this.horn2.func_78793_a(16.3124F, -34.0707F, 0.0F);
/* 143 */     this.head.func_78792_a(this.horn2);
/* 144 */     setRotationAngle(this.horn2, 0.0F, 0.0F, 0.3491F);
/* 145 */     this.horn2.func_78784_a(0, 0).func_228303_a_(-1.0F, -1.5F, -1.0F, 2.0F, 3.0F, 2.0F, 0.01F, false);
/*     */     
/* 147 */     this.horn3 = new ModelRenderer((Model)this);
/* 148 */     this.horn3.func_78793_a(12.8548F, -32.4177F, 0.0F);
/* 149 */     this.head.func_78792_a(this.horn3);
/* 150 */     setRotationAngle(this.horn3, 0.0F, 0.0F, 0.6109F);
/* 151 */     this.horn3.func_78784_a(6, 3).func_228303_a_(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
/*     */     
/* 153 */     this.horn4 = new ModelRenderer((Model)this);
/* 154 */     this.horn4.func_78793_a(13.4789F, -33.4751F, 0.0F);
/* 155 */     this.head.func_78792_a(this.horn4);
/* 156 */     setRotationAngle(this.horn4, 0.0F, 0.0F, 0.3491F);
/* 157 */     this.horn4.func_78784_a(8, 0).func_228303_a_(-1.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, 0.01F, false);
/*     */     
/* 159 */     this.rightEar = new ModelRenderer((Model)this);
/* 160 */     this.rightEar.func_78793_a(6.3332F, -37.2594F, -2.8669F);
/* 161 */     this.head.func_78792_a(this.rightEar);
/* 162 */     setRotationAngle(this.rightEar, 0.1745F, 0.0F, 0.4363F);
/* 163 */     this.rightEar.func_78784_a(0, 9).func_228303_a_(-0.5F, -1.5F, -1.0F, 1.0F, 3.0F, 2.0F, 0.0F, true);
/*     */     
/* 165 */     this.leftEar = new ModelRenderer((Model)this);
/* 166 */     this.leftEar.func_78793_a(6.3446F, -37.2837F, 2.9562F);
/* 167 */     this.head.func_78792_a(this.leftEar);
/* 168 */     setRotationAngle(this.leftEar, -0.1745F, 0.0F, 0.4363F);
/* 169 */     this.leftEar.func_78784_a(0, 9).func_228303_a_(-0.5F, -1.5F, -1.0F, 1.0F, 3.0F, 2.0F, 0.0F, true);
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
/* 184 */     this.rightArm.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 185 */     this.leftLeg.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 186 */     this.leftArm.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 187 */     this.neck.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 188 */     this.head.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 195 */     float f = 1.0F;
/* 196 */     this.rightArm.field_78808_h = MathHelper.func_76134_b(limbSwing * 0.6662F + 3.1415927F) * 0.8F * limbSwingAmount * 0.5F / f;
/* 197 */     this.leftArm.field_78808_h = MathHelper.func_76134_b(limbSwing * 0.6662F) * 0.8F * limbSwingAmount * 0.5F / f;
/* 198 */     this.rightLeg.field_78808_h = MathHelper.func_76134_b(limbSwing * 0.6662F) * 0.7F * limbSwingAmount / f;
/* 199 */     this.leftLeg.field_78808_h = MathHelper.func_76134_b(limbSwing * 0.6662F + 3.1415927F) * 0.7F * limbSwingAmount / f;
/* 200 */     if (!entity.func_184614_ca().func_190926_b()) {
/* 201 */       this.rightArm.field_78808_h += -0.15F;
/*     */     }
/*     */     
/* 204 */     this.field_217112_c = ((LivingEntity)entity).field_70733_aJ;
/* 205 */     boolean isBlackLeg = EntityStatsCapability.get((LivingEntity)entity).isBlackLeg();
/* 206 */     if (this.field_217112_c > 0.0F)
/*     */     {
/* 208 */       if (isBlackLeg) {
/*     */         
/* 210 */         this.body.field_78796_g = MathHelper.func_76126_a(MathHelper.func_76129_c(this.field_217112_c) * 6.2831855F) * 0.2F;
/* 211 */         this.leftLeg.field_78796_g += this.body.field_78796_g;
/* 212 */         this.leftLeg.field_78795_f += this.body.field_78796_g;
/* 213 */         this.rightLeg.field_78796_g += this.body.field_78796_g * 3.0F;
/* 214 */         this.rightLeg.field_78808_h += MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * -1.9F;
/*     */       }
/*     */       else {
/*     */         
/* 218 */         this.body.field_78796_g = MathHelper.func_76126_a(MathHelper.func_76129_c(this.field_217112_c) * 6.2831855F) * 0.2F;
/* 219 */         this.leftArm.field_78796_g += this.body.field_78796_g;
/* 220 */         this.leftArm.field_78795_f += this.body.field_78796_g;
/* 221 */         float f1 = 1.0F - this.field_217112_c;
/* 222 */         f1 *= f1;
/* 223 */         f1 *= f1;
/* 224 */         f1 = 1.0F - f1;
/* 225 */         float f2 = MathHelper.func_76126_a(f1 * 3.1415927F);
/* 226 */         float f3 = MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * 0.75F;
/* 227 */         this.rightArm.field_78795_f -= (float)(this.rightArm.field_78795_f - f2 * -0.8D + f3);
/* 228 */         this.rightArm.field_78796_g += this.body.field_78796_g * 1.0F;
/* 229 */         this.rightArm.field_78808_h += MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * -1.9F;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 237 */     modelRenderer.field_78795_f = x;
/* 238 */     modelRenderer.field_78796_g = y;
/* 239 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225599_a_(HandSide side, MatrixStack matrixStack) {
/* 245 */     super.func_225599_a_(side, matrixStack);
/* 246 */     matrixStack.func_227861_a_(-0.1D, 0.3D, -0.12D);
/* 247 */     matrixStack.func_227863_a_(Vector3f.field_229183_f_.func_229187_a_((side == HandSide.RIGHT) ? -20.0F : 20.0F));
/* 248 */     matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(-90.0F));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
/* 254 */     if (side == HandSide.RIGHT) {
/*     */       
/* 256 */       matrixStack.func_227861_a_(-0.4D, -0.2D, -0.3D);
/* 257 */       matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(-270.0F));
/* 258 */       matrixStack.func_227863_a_(Vector3f.field_229183_f_.func_229187_a_(10.0F));
/* 259 */       this.rightArm2.func_228309_a_(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     }
/*     */     else {
/*     */       
/* 263 */       matrixStack.func_227861_a_(0.4D, -0.2D, -0.3D);
/* 264 */       matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(-270.0F));
/* 265 */       matrixStack.func_227863_a_(Vector3f.field_229183_f_.func_229187_a_(10.0F));
/* 266 */       this.leftArm2.func_228309_a_(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
/* 273 */     if (side == HandSide.RIGHT) {
/*     */       
/* 275 */       matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(50.0F));
/* 276 */       matrixStack.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(40.0F));
/* 277 */       matrixStack.func_227861_a_(-0.1D, -0.9D, 0.2D);
/* 278 */       this.rightLeg.func_228309_a_(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     }
/*     */     else {
/*     */       
/* 282 */       matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(150.0F));
/* 283 */       matrixStack.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(-20.0F));
/* 284 */       matrixStack.func_227861_a_(-0.1D, -0.9D, -0.2D);
/* 285 */       this.leftLeg.func_228309_a_(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\morphs\SaiHeavyModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */