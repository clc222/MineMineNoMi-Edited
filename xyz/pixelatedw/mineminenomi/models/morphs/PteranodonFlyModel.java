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
/*     */ public class PteranodonFlyModel<T extends LivingEntity> extends MorphModel<T> implements IHasArm {
/*     */   private final ModelRenderer head;
/*     */   private final ModelRenderer head7_r1;
/*     */   private final ModelRenderer head6_r1;
/*     */   private final ModelRenderer head5_r1;
/*     */   private final ModelRenderer head4_r1;
/*     */   private final ModelRenderer head3_r1;
/*     */   private final ModelRenderer head2_r1;
/*     */   private final ModelRenderer neck;
/*     */   private final ModelRenderer neck_r1;
/*     */   private final ModelRenderer beck;
/*     */   private final ModelRenderer upperBeck;
/*     */   public final ModelRenderer lowerBeck;
/*     */   private final ModelRenderer body;
/*     */   private final ModelRenderer body3_r1;
/*     */   private final ModelRenderer rightWing;
/*     */   private final ModelRenderer leftWing;
/*     */   private final ModelRenderer rightLeg;
/*     */   private final ModelRenderer rightLeg5_r1;
/*     */   private final ModelRenderer rightLeg1_r1;
/*     */   private final ModelRenderer leftLeg;
/*     */   private final ModelRenderer leftLeg5_r1;
/*     */   private final ModelRenderer leftLeg1_r1;
/*     */   
/*     */   public PteranodonFlyModel() {
/*  40 */     super(1.0F);
/*  41 */     this.field_78090_t = 64;
/*  42 */     this.field_78089_u = 64;
/*     */     
/*  44 */     this.head = new ModelRenderer((Model)this);
/*  45 */     this.head.func_78793_a(-0.5F, 17.0F, -2.0F);
/*  46 */     this.head.func_78784_a(17, 37).func_228303_a_(-1.5F, -4.0F, -17.0F, 3.0F, 4.0F, 7.0F, 0.0F, false);
/*     */     
/*  48 */     this.head7_r1 = new ModelRenderer((Model)this);
/*  49 */     this.head7_r1.func_78793_a(0.0F, -3.6703F, -11.8864F);
/*  50 */     this.head.func_78792_a(this.head7_r1);
/*  51 */     setRotationAngle(this.head7_r1, 1.0036F, 0.0F, 0.0F);
/*  52 */     this.head7_r1.func_78784_a(8, 10).func_228303_a_(-0.5F, 1.1F, 7.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/*  54 */     this.head6_r1 = new ModelRenderer((Model)this);
/*  55 */     this.head6_r1.func_78793_a(0.0F, -3.9663F, -13.3072F);
/*  56 */     this.head.func_78792_a(this.head6_r1);
/*  57 */     setRotationAngle(this.head6_r1, 0.829F, 0.0F, 0.0F);
/*  58 */     this.head6_r1.func_78784_a(8, 14).func_228303_a_(-0.5F, 1.1F, 6.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/*  60 */     this.head5_r1 = new ModelRenderer((Model)this);
/*  61 */     this.head5_r1.func_78793_a(0.0F, -3.1147F, -15.0223F);
/*  62 */     this.head.func_78792_a(this.head5_r1);
/*  63 */     setRotationAngle(this.head5_r1, 0.7418F, 0.0F, 0.0F);
/*  64 */     this.head5_r1.func_78784_a(15, 10).func_228303_a_(-0.5F, 1.1F, 6.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/*  66 */     this.head4_r1 = new ModelRenderer((Model)this);
/*  67 */     this.head4_r1.func_78793_a(0.0F, -3.372F, -15.126F);
/*  68 */     this.head.func_78792_a(this.head4_r1);
/*  69 */     setRotationAngle(this.head4_r1, 0.6981F, 0.0F, 0.0F);
/*  70 */     this.head4_r1.func_78784_a(15, 14).func_228303_a_(-0.5F, 1.1F, 4.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/*  72 */     this.head3_r1 = new ModelRenderer((Model)this);
/*  73 */     this.head3_r1.func_78793_a(0.0F, -3.9118F, -11.6183F);
/*  74 */     this.head.func_78792_a(this.head3_r1);
/*  75 */     setRotationAngle(this.head3_r1, 0.4363F, 0.0F, 0.0F);
/*  76 */     this.head3_r1.func_78784_a(22, 10).func_228303_a_(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
/*     */     
/*  78 */     this.head2_r1 = new ModelRenderer((Model)this);
/*  79 */     this.head2_r1.func_78793_a(0.0F, -3.7133F, -13.62F);
/*  80 */     this.head.func_78792_a(this.head2_r1);
/*  81 */     setRotationAngle(this.head2_r1, 0.2618F, 0.0F, 0.0F);
/*  82 */     this.head2_r1.func_78784_a(22, 15).func_228303_a_(-1.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/*  84 */     this.neck = new ModelRenderer((Model)this);
/*  85 */     this.neck.func_78793_a(0.0F, 0.0F, 0.0F);
/*  86 */     this.head.func_78792_a(this.neck);
/*     */ 
/*     */     
/*  89 */     this.neck_r1 = new ModelRenderer((Model)this);
/*  90 */     this.neck_r1.func_78793_a(0.0F, -0.9169F, -9.6038F);
/*  91 */     this.neck.func_78792_a(this.neck_r1);
/*  92 */     setRotationAngle(this.neck_r1, -0.1309F, 0.0F, 0.0F);
/*  93 */     this.neck_r1.func_78784_a(31, 30).func_228303_a_(-0.5F, -1.5F, -4.5F, 1.0F, 3.0F, 9.0F, 0.0F, false);
/*     */     
/*  95 */     this.beck = new ModelRenderer((Model)this);
/*  96 */     this.beck.func_78793_a(0.0F, -1.0F, -10.0F);
/*  97 */     this.head.func_78792_a(this.beck);
/*  98 */     this.beck.func_78784_a(0, 0).func_228303_a_(-1.0F, -2.5F, -9.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);
/*  99 */     this.beck.func_78784_a(35, 10).func_228303_a_(-1.0F, 0.0F, -9.0F, 2.0F, 1.0F, 2.0F, 0.01F, false);
/*     */     
/* 101 */     this.upperBeck = new ModelRenderer((Model)this);
/* 102 */     this.upperBeck.func_78793_a(0.0F, 0.0F, -2.0F);
/* 103 */     this.beck.func_78792_a(this.upperBeck);
/* 104 */     this.upperBeck.func_78784_a(0, 18).func_228303_a_(-0.5F, -2.0F, -18.0F, 1.0F, 2.0F, 12.0F, 0.01F, false);
/*     */     
/* 106 */     this.lowerBeck = new ModelRenderer((Model)this);
/* 107 */     this.lowerBeck.func_78793_a(0.0F, 0.0F, -9.0F);
/* 108 */     this.beck.func_78792_a(this.lowerBeck);
/* 109 */     this.lowerBeck.func_78784_a(14, 20).func_228303_a_(-0.5F, 0.0F, -11.0F, 1.0F, 1.0F, 12.0F, 0.0F, false);
/*     */     
/* 111 */     this.body = new ModelRenderer((Model)this);
/* 112 */     this.body.func_78793_a(-0.5F, 16.0F, 4.0F);
/* 113 */     this.body.func_78784_a(28, 18).func_228303_a_(-3.0F, -1.5F, -12.0F, 6.0F, 5.0F, 7.0F, 0.0F, false);
/*     */     
/* 115 */     this.body3_r1 = new ModelRenderer((Model)this);
/* 116 */     this.body3_r1.func_78793_a(0.5F, 40.0F, -20.0F);
/* 117 */     this.body.func_78792_a(this.body3_r1);
/* 118 */     setRotationAngle(this.body3_r1, -0.0873F, 0.0F, 0.0F);
/* 119 */     this.body3_r1.func_78784_a(46, 44).func_228303_a_(-2.0F, -42.0F, 18.1F, 3.0F, 2.0F, 1.0F, 0.0F, false);
/* 120 */     this.body3_r1.func_78784_a(0, 33).func_228303_a_(-3.0F, -42.4F, 11.1F, 5.0F, 4.0F, 7.0F, 0.0F, false);
/*     */     
/* 122 */     this.rightWing = new ModelRenderer((Model)this);
/* 123 */     this.rightWing.func_78793_a(-3.0F, 17.0F, 0.0F);
/* 124 */     this.rightWing.func_78784_a(17, 33).func_228303_a_(-8.5F, -0.5F, -6.0F, 8.0F, 1.0F, 1.0F, 0.0F, true);
/* 125 */     this.rightWing.func_78784_a(14, 24).func_228303_a_(-5.5F, -0.5F, -7.0F, 5.0F, 1.0F, 1.0F, 0.0F, true);
/* 126 */     this.rightWing.func_78784_a(17, 33).func_228303_a_(-13.5F, -0.5F, -5.0F, 8.0F, 1.0F, 1.0F, 0.0F, true);
/* 127 */     this.rightWing.func_78784_a(0, 27).func_228303_a_(-18.5F, -0.5F, -3.0F, 4.0F, 1.0F, 1.0F, 0.0F, true);
/* 128 */     this.rightWing.func_78784_a(0, 44).func_228303_a_(-17.5F, -0.5F, -4.0F, 7.0F, 1.0F, 1.0F, 0.0F, true);
/* 129 */     this.rightWing.func_78784_a(7, 20).func_228303_a_(-18.5F, -0.5F, -2.0F, 1.0F, 1.0F, 1.0F, 0.0F, true);
/* 130 */     this.rightWing.func_78784_a(0, 0).func_228303_a_(-18.5F, 0.0F, -5.0F, 19.0F, 0.0F, 9.0F, 0.0F, true);
/*     */     
/* 132 */     this.leftWing = new ModelRenderer((Model)this);
/* 133 */     this.leftWing.func_78793_a(2.0F, 17.0F, 0.0F);
/* 134 */     this.leftWing.func_78784_a(17, 33).func_228303_a_(0.5F, -0.5F, -6.0F, 8.0F, 1.0F, 1.0F, 0.0F, false);
/* 135 */     this.leftWing.func_78784_a(14, 24).func_228303_a_(0.5F, -0.5F, -7.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);
/* 136 */     this.leftWing.func_78784_a(17, 33).func_228303_a_(5.5F, -0.5F, -5.0F, 8.0F, 1.0F, 1.0F, 0.0F, false);
/* 137 */     this.leftWing.func_78784_a(0, 27).func_228303_a_(14.5F, -0.5F, -3.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
/* 138 */     this.leftWing.func_78784_a(0, 44).func_228303_a_(10.5F, -0.5F, -4.0F, 7.0F, 1.0F, 1.0F, 0.0F, false);
/* 139 */     this.leftWing.func_78784_a(7, 20).func_228303_a_(17.5F, -0.5F, -2.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
/* 140 */     this.leftWing.func_78784_a(0, 0).func_228303_a_(-0.5F, 0.0F, -5.0F, 19.0F, 0.0F, 9.0F, 0.0F, false);
/*     */     
/* 142 */     this.rightLeg = new ModelRenderer((Model)this);
/* 143 */     this.rightLeg.func_78793_a(-3.0F, 18.0F, 11.0F);
/* 144 */     setRotationAngle(this.rightLeg, 1.3526F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 147 */     this.rightLeg5_r1 = new ModelRenderer((Model)this);
/* 148 */     this.rightLeg5_r1.func_78793_a(5.0F, 12.1847F, 7.6754F);
/* 149 */     this.rightLeg.func_78792_a(this.rightLeg5_r1);
/* 150 */     setRotationAngle(this.rightLeg5_r1, -1.0472F, 0.0F, 0.0F);
/* 151 */     this.rightLeg5_r1.func_78784_a(14, 20).func_228303_a_(-7.5F, 4.6958F, -15.7354F, 5.0F, 4.0F, 0.0F, 0.0F, false);
/* 152 */     this.rightLeg5_r1.func_78784_a(0, 5).func_228303_a_(-6.5F, 3.93F, -15.7223F, 3.0F, 3.0F, 1.0F, 0.0F, false);
/* 153 */     this.rightLeg5_r1.func_78784_a(0, 13).func_228303_a_(-5.5F, 0.6958F, -15.7223F, 1.0F, 4.0F, 1.0F, -0.01F, false);
/* 154 */     this.rightLeg5_r1.func_78784_a(0, 13).func_228303_a_(-5.5F, 0.6958F, -20.7223F, 1.0F, 2.0F, 5.0F, 0.0F, false);
/*     */     
/* 156 */     this.rightLeg1_r1 = new ModelRenderer((Model)this);
/* 157 */     this.rightLeg1_r1.func_78793_a(5.0F, 13.8689F, 7.5424F);
/* 158 */     this.rightLeg.func_78792_a(this.rightLeg1_r1);
/* 159 */     setRotationAngle(this.rightLeg1_r1, -2.2253F, 0.0F, 0.0F);
/* 160 */     this.rightLeg1_r1.func_78784_a(42, 30).func_228303_a_(-6.0F, 18.0963F, -11.8567F, 2.0F, 3.0F, 5.0F, 0.0F, false);
/*     */     
/* 162 */     this.leftLeg = new ModelRenderer((Model)this);
/* 163 */     this.leftLeg.func_78793_a(2.0F, 18.0F, 11.0F);
/* 164 */     setRotationAngle(this.leftLeg, 1.3526F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 167 */     this.leftLeg5_r1 = new ModelRenderer((Model)this);
/* 168 */     this.leftLeg5_r1.func_78793_a(5.0F, 12.1847F, 7.6754F);
/* 169 */     this.leftLeg.func_78792_a(this.leftLeg5_r1);
/* 170 */     setRotationAngle(this.leftLeg5_r1, -1.0472F, 0.0F, 0.0F);
/* 171 */     this.leftLeg5_r1.func_78784_a(14, 20).func_228303_a_(-7.5F, 4.6958F, -15.7354F, 5.0F, 4.0F, 0.0F, 0.0F, false);
/* 172 */     this.leftLeg5_r1.func_78784_a(0, 5).func_228303_a_(-6.5F, 3.93F, -15.7223F, 3.0F, 3.0F, 1.0F, 0.0F, false);
/* 173 */     this.leftLeg5_r1.func_78784_a(0, 13).func_228303_a_(-5.5F, 0.6958F, -15.7223F, 1.0F, 4.0F, 1.0F, -0.01F, false);
/* 174 */     this.leftLeg5_r1.func_78784_a(0, 13).func_228303_a_(-5.5F, 0.6958F, -20.7223F, 1.0F, 2.0F, 5.0F, 0.0F, false);
/*     */     
/* 176 */     this.leftLeg1_r1 = new ModelRenderer((Model)this);
/* 177 */     this.leftLeg1_r1.func_78793_a(5.0F, 13.8689F, 7.5424F);
/* 178 */     this.leftLeg.func_78792_a(this.leftLeg1_r1);
/* 179 */     setRotationAngle(this.leftLeg1_r1, -2.2253F, 0.0F, 0.0F);
/* 180 */     this.leftLeg1_r1.func_78784_a(42, 30).func_228303_a_(-6.0F, 18.0963F, -11.8567F, 2.0F, 3.0F, 5.0F, 0.0F, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 186 */     this.field_178723_h.field_78806_j = false;
/* 187 */     this.field_178724_i.field_78806_j = false;
/*     */     
/* 189 */     matrixStack.func_227860_a_();
/* 190 */     matrixStack.func_227861_a_(0.0D, -1.0D, 0.0D);
/* 191 */     this.head.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 192 */     this.body.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 193 */     matrixStack.func_227860_a_();
/* 194 */     matrixStack.func_227862_a_(1.75F, 1.0F, 1.0F);
/* 195 */     matrixStack.func_227861_a_(0.10000000149011612D, 0.0D, 0.0D);
/* 196 */     this.rightWing.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 197 */     matrixStack.func_227861_a_(-0.20000000298023224D, 0.0D, 0.0D);
/* 198 */     this.leftWing.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 199 */     matrixStack.func_227865_b_();
/* 200 */     this.rightLeg.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 201 */     this.leftLeg.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 202 */     matrixStack.func_227865_b_();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 208 */     if ((entity.func_213322_ci()).field_72448_b < -1.7D) {
/*     */       
/* 210 */       this.leftWing.field_78795_f = (float)(this.leftWing.field_78795_f + Math.toRadians(-90.0D));
/* 211 */       this.leftWing.field_78796_g = (float)(this.leftWing.field_78796_g + Math.toRadians(-85.0D));
/* 212 */       this.leftWing.field_78796_g += MathHelper.func_76134_b(((LivingEntity)entity).field_70173_aa * 0.9F) / 50.0F;
/* 213 */       this.leftWing.field_78808_h += MathHelper.func_76134_b(((LivingEntity)entity).field_70173_aa * 2.9F) / 50.0F;
/*     */       
/* 215 */       this.rightWing.field_78795_f = (float)(this.rightWing.field_78795_f + Math.toRadians(-90.0D));
/* 216 */       this.rightWing.field_78796_g = (float)(this.rightWing.field_78796_g + Math.toRadians(85.0D));
/* 217 */       this.rightWing.field_78796_g += MathHelper.func_76134_b(((LivingEntity)entity).field_70173_aa * 0.9F) / 50.0F;
/* 218 */       this.rightWing.field_78808_h += MathHelper.func_76134_b(((LivingEntity)entity).field_70173_aa * 2.9F) / 50.0F;
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 223 */       this.leftWing.field_78808_h = MathHelper.func_76134_b(ageInTicks * 0.4F) * 0.7F;
/* 224 */       this.rightWing.field_78808_h = MathHelper.func_76134_b(ageInTicks * 0.4F + 3.1415927F) * 0.7F;
/*     */     } 
/*     */ 
/*     */     
/* 228 */     this.field_217112_c = ((LivingEntity)entity).field_110158_av;
/* 229 */     if (this.field_217112_c > 0.0F) {
/*     */       
/* 231 */       this.head.field_78796_g += this.body.field_78796_g;
/* 232 */       float f1 = 1.0F - this.field_217112_c;
/* 233 */       f1 *= f1;
/* 234 */       f1 *= f1;
/* 235 */       f1 = 1.0F - f1;
/* 236 */       float f2 = MathHelper.func_76126_a(f1 * 3.1415927F);
/* 237 */       float f3 = MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * -(this.head.field_78795_f - 0.1F) * 0.15F;
/* 238 */       this.head.field_78795_f = (float)(this.head.field_78795_f - f2 * 0.5D + f3);
/* 239 */       this.head.field_78808_h -= MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * -0.4F;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 245 */     modelRenderer.field_78795_f = x;
/* 246 */     modelRenderer.field_78796_g = y;
/* 247 */     modelRenderer.field_78808_h = z;
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
/* 263 */     this.head.func_228307_a_(matrixStack);
/* 264 */     matrixStack.func_227862_a_(0.5F, 0.5F, 0.5F);
/* 265 */     matrixStack.func_227863_a_(Vector3f.field_229183_f_.func_229187_a_(90.0F));
/* 266 */     matrixStack.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(260.0F));
/* 267 */     matrixStack.func_227861_a_(0.35D, -0.1D, -0.1D);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\morphs\PteranodonFlyModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */