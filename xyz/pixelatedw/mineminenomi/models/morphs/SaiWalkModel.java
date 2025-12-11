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
/*     */ public class SaiWalkModel<T extends LivingEntity>
/*     */   extends MorphModel<T> implements IHasArm {
/*     */   private final ModelRenderer body;
/*     */   private final ModelRenderer lowerBody_r1;
/*     */   private final ModelRenderer upperBody_r1;
/*     */   private final ModelRenderer head;
/*     */   private final ModelRenderer neck_r1;
/*     */   private final ModelRenderer head2_r1;
/*     */   private final ModelRenderer head1_r1;
/*     */   private final ModelRenderer horns;
/*     */   private final ModelRenderer horn4_r1;
/*     */   private final ModelRenderer horn3_r1;
/*     */   private final ModelRenderer horn2_r1;
/*     */   private final ModelRenderer horn1_r1;
/*     */   private final ModelRenderer rightEars;
/*     */   private final ModelRenderer rightEar_r1;
/*     */   private final ModelRenderer leftEar;
/*     */   private final ModelRenderer leftEar_r1;
/*     */   private final ModelRenderer leftFrontLeg;
/*     */   private final ModelRenderer leftFrontLeg2_r1;
/*     */   private final ModelRenderer leftFrontLeg1_r1;
/*     */   private final ModelRenderer rightFrontLeg;
/*     */   private final ModelRenderer rightFrontLeg2_r1;
/*     */   private final ModelRenderer rightFrontLeg1_r1;
/*     */   private final ModelRenderer rightBackLeg;
/*     */   private final ModelRenderer rightBackLeg2_r1;
/*     */   private final ModelRenderer rightBackLeg1_r1;
/*     */   private final ModelRenderer leftBackLeg;
/*     */   private final ModelRenderer leftBackLeg2_r1;
/*     */   private final ModelRenderer leftBackLeg1_r1;
/*     */   private final ModelRenderer tail;
/*     */   private final ModelRenderer tail_r1;
/*     */   
/*     */   public SaiWalkModel() {
/*  49 */     super(1.0F);
/*  50 */     this.field_78090_t = 128;
/*  51 */     this.field_78089_u = 128;
/*     */     
/*  53 */     this.body = new ModelRenderer((Model)this);
/*  54 */     this.body.func_78793_a(-1.3508F, 11.8354F, 0.0F);
/*     */     
/*  56 */     this.lowerBody_r1 = new ModelRenderer((Model)this);
/*  57 */     this.lowerBody_r1.func_78793_a(-4.5067F, 0.2204F, 0.0F);
/*  58 */     this.body.func_78792_a(this.lowerBody_r1);
/*  59 */     setRotationAngle(this.lowerBody_r1, 0.0F, 0.0F, 0.0436F);
/*  60 */     this.lowerBody_r1.func_78784_a(0, 23).func_228303_a_(-5.5F, -4.5F, -5.5F, 11.0F, 9.0F, 11.0F, 0.0F, false);
/*     */     
/*  62 */     this.upperBody_r1 = new ModelRenderer((Model)this);
/*  63 */     this.upperBody_r1.func_78793_a(4.5067F, -0.2204F, 0.0F);
/*  64 */     this.body.func_78792_a(this.upperBody_r1);
/*  65 */     setRotationAngle(this.upperBody_r1, 0.0F, 0.0F, -0.0873F);
/*  66 */     this.upperBody_r1.func_78784_a(0, 0).func_228303_a_(-5.5F, -5.0F, -6.5F, 11.0F, 10.0F, 13.0F, 0.0F, false);
/*     */     
/*  68 */     this.head = new ModelRenderer((Model)this);
/*  69 */     this.head.func_78793_a(8.0271F, 9.6204F, -1.5F);
/*     */     
/*  71 */     this.neck_r1 = new ModelRenderer((Model)this);
/*  72 */     this.neck_r1.func_78793_a(-5.0271F, -1.6204F, 1.0F);
/*  73 */     this.head.func_78792_a(this.neck_r1);
/*  74 */     setRotationAngle(this.neck_r1, 0.0F, 0.0F, -0.4363F);
/*  75 */     this.neck_r1.func_78784_a(37, 36).func_228303_a_(2.0F, 0.0F, -3.0F, 8.0F, 8.0F, 7.0F, 0.01F, false);
/*     */     
/*  77 */     this.head2_r1 = new ModelRenderer((Model)this);
/*  78 */     this.head2_r1.func_78793_a(7.8872F, 0.9067F, 1.5F);
/*  79 */     this.head.func_78792_a(this.head2_r1);
/*  80 */     setRotationAngle(this.head2_r1, 0.0F, 0.0F, 0.48F);
/*  81 */     this.head2_r1.func_78784_a(35, 0).func_228303_a_(-5.0F, -3.0F, -3.5F, 10.0F, 6.0F, 7.0F, 0.02F, false);
/*     */     
/*  83 */     this.head1_r1 = new ModelRenderer((Model)this);
/*  84 */     this.head1_r1.func_78793_a(5.0116F, -3.3758F, 1.5F);
/*  85 */     this.head.func_78792_a(this.head1_r1);
/*  86 */     setRotationAngle(this.head1_r1, 0.0F, 0.0F, 0.5236F);
/*  87 */     this.head1_r1.func_78784_a(39, 24).func_228303_a_(-2.5F, -1.0F, -3.5F, 5.0F, 2.0F, 7.0F, 0.0F, false);
/*     */     
/*  89 */     this.horns = new ModelRenderer((Model)this);
/*  90 */     this.horns.func_78793_a(-8.0271F, 1.3796F, 1.5F);
/*  91 */     this.head.func_78792_a(this.horns);
/*     */     
/*  93 */     this.horn4_r1 = new ModelRenderer((Model)this);
/*  94 */     this.horn4_r1.func_78793_a(19.4789F, -3.4751F, 0.0F);
/*  95 */     this.horns.func_78792_a(this.horn4_r1);
/*  96 */     setRotationAngle(this.horn4_r1, 0.0F, 0.0F, 0.3491F);
/*  97 */     this.horn4_r1.func_78784_a(0, 31).func_228303_a_(-1.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, 0.01F, false);
/*     */     
/*  99 */     this.horn3_r1 = new ModelRenderer((Model)this);
/* 100 */     this.horn3_r1.func_78793_a(18.8548F, -2.4177F, 0.0F);
/* 101 */     this.horns.func_78792_a(this.horn3_r1);
/* 102 */     setRotationAngle(this.horn3_r1, 0.0F, 0.0F, 0.6109F);
/* 103 */     this.horn3_r1.func_78784_a(0, 23).func_228303_a_(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
/*     */     
/* 105 */     this.horn2_r1 = new ModelRenderer((Model)this);
/* 106 */     this.horn2_r1.func_78793_a(22.3124F, -4.0707F, 0.0F);
/* 107 */     this.horns.func_78792_a(this.horn2_r1);
/* 108 */     setRotationAngle(this.horn2_r1, 0.0F, 0.0F, 0.3491F);
/* 109 */     this.horn2_r1.func_78784_a(4, 8).func_228303_a_(-1.0F, -1.5F, -1.0F, 2.0F, 3.0F, 2.0F, 0.01F, false);
/*     */     
/* 111 */     this.horn1_r1 = new ModelRenderer((Model)this);
/* 112 */     this.horn1_r1.func_78793_a(21.3463F, -2.0735F, 0.0F);
/* 113 */     this.horns.func_78792_a(this.horn1_r1);
/* 114 */     setRotationAngle(this.horn1_r1, 0.0F, 0.0F, 0.6109F);
/* 115 */     this.horn1_r1.func_78784_a(0, 27).func_228303_a_(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
/*     */     
/* 117 */     this.rightEars = new ModelRenderer((Model)this);
/* 118 */     this.rightEars.func_78793_a(-8.0271F, 1.3796F, 1.5F);
/* 119 */     this.head.func_78792_a(this.rightEars);
/*     */ 
/*     */     
/* 122 */     this.rightEar_r1 = new ModelRenderer((Model)this);
/* 123 */     this.rightEar_r1.func_78793_a(12.3332F, -7.2594F, -2.8669F);
/* 124 */     this.rightEars.func_78792_a(this.rightEar_r1);
/* 125 */     setRotationAngle(this.rightEar_r1, 0.1309F, 0.0F, 0.4363F);
/* 126 */     this.rightEar_r1.func_78784_a(6, 0).func_228303_a_(-0.5F, -1.5F, -1.0F, 1.0F, 3.0F, 2.0F, 0.0F, false);
/*     */     
/* 128 */     this.leftEar = new ModelRenderer((Model)this);
/* 129 */     this.leftEar.func_78793_a(-8.0271F, 1.3796F, 1.5F);
/* 130 */     this.head.func_78792_a(this.leftEar);
/*     */     
/* 132 */     this.leftEar_r1 = new ModelRenderer((Model)this);
/* 133 */     this.leftEar_r1.func_78793_a(12.3446F, -7.2837F, 2.9562F);
/* 134 */     this.leftEar.func_78792_a(this.leftEar_r1);
/* 135 */     setRotationAngle(this.leftEar_r1, -0.1745F, 0.0F, 0.4363F);
/* 136 */     this.leftEar_r1.func_78784_a(6, 0).func_228303_a_(-0.5F, -1.5F, -1.0F, 1.0F, 3.0F, 2.0F, 0.0F, true);
/*     */     
/* 138 */     this.leftFrontLeg = new ModelRenderer((Model)this);
/* 139 */     this.leftFrontLeg.func_78793_a(5.0F, 11.0F, 5.5F);
/* 140 */     this.leftFrontLeg.func_78784_a(12, 66).func_228303_a_(-2.1F, 11.0F, -2.5F, 5.0F, 2.0F, 4.0F, 0.01F, true);
/*     */     
/* 142 */     this.leftFrontLeg2_r1 = new ModelRenderer((Model)this);
/* 143 */     this.leftFrontLeg2_r1.func_78793_a(-0.3916F, 9.0257F, -0.5F);
/* 144 */     this.leftFrontLeg.func_78792_a(this.leftFrontLeg2_r1);
/* 145 */     setRotationAngle(this.leftFrontLeg2_r1, 0.0F, 0.0F, -0.1309F);
/* 146 */     this.leftFrontLeg2_r1.func_78784_a(0, 58).func_228303_a_(-2.0F, -3.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F, true);
/*     */     
/* 148 */     this.leftFrontLeg1_r1 = new ModelRenderer((Model)this);
/* 149 */     this.leftFrontLeg1_r1.func_78793_a(1.0F, 5.0F, -1.0F);
/* 150 */     this.leftFrontLeg.func_78792_a(this.leftFrontLeg1_r1);
/* 151 */     setRotationAngle(this.leftFrontLeg1_r1, 0.0F, 0.0F, 0.0873F);
/* 152 */     this.leftFrontLeg1_r1.func_78784_a(0, 43).func_228303_a_(-4.5F, -7.0F, -2.0F, 6.0F, 10.0F, 5.0F, 0.0F, true);
/*     */     
/* 154 */     this.rightFrontLeg = new ModelRenderer((Model)this);
/* 155 */     this.rightFrontLeg.func_78793_a(5.0F, 11.0F, -5.5F);
/* 156 */     this.rightFrontLeg.func_78784_a(12, 66).func_228303_a_(-2.1F, 11.0F, -1.5F, 5.0F, 2.0F, 4.0F, 0.01F, true);
/*     */     
/* 158 */     this.rightFrontLeg2_r1 = new ModelRenderer((Model)this);
/* 159 */     this.rightFrontLeg2_r1.func_78793_a(-0.3916F, 9.0257F, 0.5F);
/* 160 */     this.rightFrontLeg.func_78792_a(this.rightFrontLeg2_r1);
/* 161 */     setRotationAngle(this.rightFrontLeg2_r1, 0.0F, 0.0F, -0.1309F);
/* 162 */     this.rightFrontLeg2_r1.func_78784_a(0, 58).func_228303_a_(-2.0F, -3.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F, true);
/*     */     
/* 164 */     this.rightFrontLeg1_r1 = new ModelRenderer((Model)this);
/* 165 */     this.rightFrontLeg1_r1.func_78793_a(1.0F, 5.0F, 2.0F);
/* 166 */     this.rightFrontLeg.func_78792_a(this.rightFrontLeg1_r1);
/* 167 */     setRotationAngle(this.rightFrontLeg1_r1, 0.0F, 0.0F, 0.0873F);
/* 168 */     this.rightFrontLeg1_r1.func_78784_a(0, 43).func_228303_a_(-4.5F, -7.0F, -4.0F, 6.0F, 10.0F, 5.0F, 0.0F, true);
/*     */     
/* 170 */     this.rightBackLeg = new ModelRenderer((Model)this);
/* 171 */     this.rightBackLeg.func_78793_a(-7.0F, 11.0F, -5.0F);
/* 172 */     this.rightBackLeg.func_78784_a(12, 66).func_228303_a_(-2.0F, 11.0F, -1.0F, 5.0F, 2.0F, 4.0F, 0.01F, true);
/*     */     
/* 174 */     this.rightBackLeg2_r1 = new ModelRenderer((Model)this);
/* 175 */     this.rightBackLeg2_r1.func_78793_a(-0.3349F, 9.3909F, 1.0F);
/* 176 */     this.rightBackLeg.func_78792_a(this.rightBackLeg2_r1);
/* 177 */     setRotationAngle(this.rightBackLeg2_r1, 0.0F, 0.0F, -0.1309F);
/* 178 */     this.rightBackLeg2_r1.func_78784_a(0, 68).func_228303_a_(-2.0F, -2.5F, -2.0F, 4.0F, 5.0F, 4.0F, 0.0F, true);
/*     */     
/* 180 */     this.rightBackLeg1_r1 = new ModelRenderer((Model)this);
/* 181 */     this.rightBackLeg1_r1.func_78793_a(0.0F, 4.0F, 2.5F);
/* 182 */     this.rightBackLeg.func_78792_a(this.rightBackLeg1_r1);
/* 183 */     setRotationAngle(this.rightBackLeg1_r1, 0.0F, 0.0F, 0.0873F);
/* 184 */     this.rightBackLeg1_r1.func_78784_a(0, 43).func_228303_a_(-3.5F, -6.0F, -4.0F, 6.0F, 10.0F, 5.0F, 0.0F, true);
/*     */     
/* 186 */     this.leftBackLeg = new ModelRenderer((Model)this);
/* 187 */     this.leftBackLeg.func_78793_a(-7.0F, 11.0F, 5.0F);
/* 188 */     this.leftBackLeg.func_78784_a(12, 66).func_228303_a_(-2.0F, 11.0F, -3.0F, 5.0F, 2.0F, 4.0F, 0.01F, true);
/*     */     
/* 190 */     this.leftBackLeg2_r1 = new ModelRenderer((Model)this);
/* 191 */     this.leftBackLeg2_r1.func_78793_a(-0.3349F, 9.3909F, -1.0F);
/* 192 */     this.leftBackLeg.func_78792_a(this.leftBackLeg2_r1);
/* 193 */     setRotationAngle(this.leftBackLeg2_r1, 0.0F, 0.0F, -0.1309F);
/* 194 */     this.leftBackLeg2_r1.func_78784_a(0, 68).func_228303_a_(-2.0F, -2.5F, -2.0F, 4.0F, 5.0F, 4.0F, 0.0F, true);
/*     */     
/* 196 */     this.leftBackLeg1_r1 = new ModelRenderer((Model)this);
/* 197 */     this.leftBackLeg1_r1.func_78793_a(0.0F, 4.0F, -1.5F);
/* 198 */     this.leftBackLeg.func_78792_a(this.leftBackLeg1_r1);
/* 199 */     setRotationAngle(this.leftBackLeg1_r1, 0.0F, 0.0F, 0.0873F);
/* 200 */     this.leftBackLeg1_r1.func_78784_a(0, 43).func_228303_a_(-3.5F, -6.0F, -2.0F, 6.0F, 10.0F, 5.0F, 0.0F, true);
/*     */     
/* 202 */     this.tail = new ModelRenderer((Model)this);
/* 203 */     this.tail.func_78793_a(-10.8276F, 8.5712F, 0.0F);
/* 204 */     setRotationAngle(this.tail, 0.0F, 0.0F, 0.1745F);
/*     */     
/* 206 */     this.tail_r1 = new ModelRenderer((Model)this);
/* 207 */     this.tail_r1.func_78793_a(-0.2589F, -1.165F, -1.0F);
/* 208 */     this.tail.func_78792_a(this.tail_r1);
/* 209 */     setRotationAngle(this.tail_r1, 0.0F, 0.0F, 0.1309F);
/* 210 */     this.tail_r1.func_78784_a(0, 0).func_228303_a_(-0.1428F, 0.2831F, 0.0F, 1.0F, 8.0F, 2.0F, 0.0F, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 216 */     this.body.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 217 */     this.head.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 218 */     this.leftFrontLeg.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 219 */     this.rightFrontLeg.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 220 */     this.rightBackLeg.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 221 */     this.leftBackLeg.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 222 */     this.tail.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 229 */     this.rightFrontLeg.field_78808_h = MathHelper.func_76134_b(limbSwing * 0.77F) * 0.5F * limbSwingAmount;
/* 230 */     this.leftFrontLeg.field_78808_h = MathHelper.func_76134_b(limbSwing * 0.75F) * 0.5F * limbSwingAmount;
/* 231 */     this.rightBackLeg.field_78808_h = MathHelper.func_76134_b(limbSwing * 0.78F) * 0.5F * limbSwingAmount;
/* 232 */     this.leftBackLeg.field_78808_h = MathHelper.func_76134_b(limbSwing * 0.74F) * 0.5F * limbSwingAmount;
/* 233 */     if (entity.func_70051_ag()) {
/* 234 */       this.tail.field_78808_h = 1.2F + MathHelper.func_76134_b(limbSwing * 0.6662F) * 0.2F * limbSwingAmount;
/*     */     }
/*     */     
/* 237 */     this.field_217112_c = ((LivingEntity)entity).field_70733_aJ;
/* 238 */     if (this.field_217112_c > 0.0F) {
/*     */       
/* 240 */       this.head.field_78796_g += this.body.field_78796_g;
/* 241 */       float f1 = 1.0F - this.field_217112_c;
/* 242 */       f1 *= f1;
/* 243 */       f1 *= f1;
/* 244 */       f1 = 1.0F - f1;
/* 245 */       float f2 = MathHelper.func_76126_a(f1 * 3.1415927F);
/* 246 */       float f3 = MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * -(this.head.field_78795_f - 0.1F) * 0.15F;
/* 247 */       this.head.field_78795_f = (float)(this.head.field_78795_f - f2 * 0.5D + f3);
/* 248 */       this.head.field_78808_h += MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * -0.8F;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 254 */     modelRenderer.field_78795_f = x;
/* 255 */     modelRenderer.field_78796_g = y;
/* 256 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225599_a_(HandSide side, MatrixStack matrixStack) {
/* 262 */     this.head.func_228307_a_(matrixStack);
/* 263 */     matrixStack.func_227863_a_(Vector3f.field_229183_f_.func_229187_a_(115.0F));
/* 264 */     matrixStack.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(170.0F));
/* 265 */     matrixStack.func_227861_a_(-0.01D, 0.1D, -0.1D);
/*     */   }
/*     */   
/*     */   public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*     */   
/*     */   public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\morphs\SaiWalkModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */