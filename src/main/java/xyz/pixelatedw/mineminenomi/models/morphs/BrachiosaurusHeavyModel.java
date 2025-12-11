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
/*     */ public class BrachiosaurusHeavyModel<T extends LivingEntity>
/*     */   extends MorphModel<T> implements IHasArm {
/*     */   private final ModelRenderer head;
/*     */   private final ModelRenderer headBase;
/*     */   private final ModelRenderer headBase3_r1;
/*     */   private final ModelRenderer headBase1_r1;
/*     */   private final ModelRenderer neck;
/*     */   private final ModelRenderer neck5_r1;
/*     */   private final ModelRenderer neck4_r1;
/*     */   private final ModelRenderer neck3_r1;
/*     */   private final ModelRenderer neck2_r1;
/*     */   private final ModelRenderer upperMouth;
/*     */   private final ModelRenderer upperTeeth;
/*     */   private final ModelRenderer lowerMouth;
/*     */   private final ModelRenderer lowerTeeth;
/*     */   private final ModelRenderer leftArm;
/*     */   private final ModelRenderer leftArm2_r1;
/*     */   private final ModelRenderer leftArm1_r1;
/*     */   private final ModelRenderer rightArm;
/*     */   private final ModelRenderer rightArm2_r1;
/*     */   private final ModelRenderer rightArm1_r1;
/*     */   private final ModelRenderer rightLeg;
/*     */   private final ModelRenderer rightLeg2_r1;
/*     */   private final ModelRenderer rightLeg1_r1;
/*     */   private final ModelRenderer leftLeg;
/*     */   private final ModelRenderer leftLeg2_r1;
/*     */   private final ModelRenderer leftLeg1_r1;
/*     */   private final ModelRenderer tail;
/*     */   private final ModelRenderer tail2;
/*     */   private final ModelRenderer body;
/*     */   
/*     */   public BrachiosaurusHeavyModel() {
/*  48 */     super(1.0F);
/*  49 */     this.field_78090_t = 128;
/*  50 */     this.field_78089_u = 128;
/*     */     
/*  52 */     this.head = new ModelRenderer((Model)this);
/*  53 */     this.head.func_78793_a(0.0F, -2.0F, -5.75F);
/*     */     
/*  55 */     this.headBase = new ModelRenderer((Model)this);
/*  56 */     this.headBase.func_78793_a(0.0F, 0.0F, 0.0F);
/*  57 */     this.head.func_78792_a(this.headBase);
/*  58 */     this.headBase.func_78784_a(0, 0).func_228303_a_(-2.5F, -11.75F, -9.0F, 5.0F, 6.0F, 2.0F, 0.01F, false);
/*     */     
/*  60 */     this.headBase3_r1 = new ModelRenderer((Model)this);
/*  61 */     this.headBase3_r1.func_78793_a(0.0F, -8.0F, -9.0F);
/*  62 */     this.headBase.func_78792_a(this.headBase3_r1);
/*  63 */     setRotationAngle(this.headBase3_r1, -0.0873F, 0.0F, 0.0F);
/*  64 */     this.headBase3_r1.func_78784_a(0, 60).func_228303_a_(-2.5F, -3.5F, -1.5728F, 5.0F, 5.0F, 2.0F, 0.0F, false);
/*     */     
/*  66 */     this.headBase1_r1 = new ModelRenderer((Model)this);
/*  67 */     this.headBase1_r1.func_78793_a(0.0F, -8.0392F, -6.2777F);
/*  68 */     this.headBase.func_78792_a(this.headBase1_r1);
/*  69 */     setRotationAngle(this.headBase1_r1, 1.3963F, 0.0F, 0.0F);
/*  70 */     this.headBase1_r1.func_78784_a(78, 31).func_228303_a_(-2.0F, -1.4992F, -2.5824F, 4.0F, 3.0F, 6.0F, 0.0F, false);
/*     */     
/*  72 */     this.neck = new ModelRenderer((Model)this);
/*  73 */     this.neck.func_78793_a(0.0F, 0.0F, -0.1878F);
/*  74 */     this.head.func_78792_a(this.neck);
/*     */     
/*  76 */     this.neck5_r1 = new ModelRenderer((Model)this);
/*  77 */     this.neck5_r1.func_78793_a(0.0F, -0.3992F, 0.1235F);
/*  78 */     this.neck.func_78792_a(this.neck5_r1);
/*  79 */     setRotationAngle(this.neck5_r1, 0.48F, 0.0F, 0.0F);
/*  80 */     this.neck5_r1.func_78784_a(29, 65).func_228303_a_(-2.5F, -4.0723F, -2.3627F, 5.0F, 4.0F, 5.0F, 0.0F, false);
/*     */     
/*  82 */     this.neck4_r1 = new ModelRenderer((Model)this);
/*  83 */     this.neck4_r1.func_78793_a(0.0F, -1.9203F, -2.5618F);
/*  84 */     this.neck.func_78792_a(this.neck4_r1);
/*  85 */     setRotationAngle(this.neck4_r1, 0.5672F, 0.0F, 0.0F);
/*  86 */     this.neck4_r1.func_78784_a(93, 51).func_228303_a_(-2.0F, -3.7189F, -0.6203F, 4.0F, 3.0F, 5.0F, 0.0F, false);
/*     */     
/*  88 */     this.neck3_r1 = new ModelRenderer((Model)this);
/*  89 */     this.neck3_r1.func_78793_a(0.0F, -5.3328F, -2.302F);
/*  90 */     this.neck.func_78792_a(this.neck3_r1);
/*  91 */     setRotationAngle(this.neck3_r1, 0.9599F, 0.0F, 0.0F);
/*  92 */     this.neck3_r1.func_78784_a(0, 96).func_228303_a_(-2.0F, -2.8466F, -1.9423F, 4.0F, 3.0F, 4.0F, -0.01F, false);
/*     */     
/*  94 */     this.neck2_r1 = new ModelRenderer((Model)this);
/*  95 */     this.neck2_r1.func_78793_a(0.0F, -6.4513F, -4.0017F);
/*  96 */     this.neck.func_78792_a(this.neck2_r1);
/*  97 */     setRotationAngle(this.neck2_r1, 1.2217F, 0.0F, 0.0F);
/*  98 */     this.neck2_r1.func_78784_a(92, 23).func_228303_a_(-2.0F, -2.2568F, -1.8199F, 4.0F, 2.0F, 5.0F, 0.01F, false);
/*     */     
/* 100 */     this.upperMouth = new ModelRenderer((Model)this);
/* 101 */     this.upperMouth.func_78793_a(0.0F, -7.0F, -8.0F);
/* 102 */     this.head.func_78792_a(this.upperMouth);
/* 103 */     setRotationAngle(this.upperMouth, -0.1309F, 0.0F, 0.0F);
/* 104 */     this.upperMouth.func_78784_a(0, 35).func_228303_a_(-2.0F, -2.4731F, -4.5535F, 4.0F, 3.0F, 2.0F, 0.0F, false);
/* 105 */     this.upperMouth.func_78784_a(0, 30).func_228303_a_(-2.0F, -2.4731F, -6.5535F, 4.0F, 3.0F, 2.0F, 0.01F, false);
/*     */     
/* 107 */     this.upperTeeth = new ModelRenderer((Model)this);
/* 108 */     this.upperTeeth.func_78793_a(0.0F, -1.4731F, -0.5535F);
/* 109 */     this.upperMouth.func_78792_a(this.upperTeeth);
/* 110 */     this.upperTeeth.func_78784_a(0, 3).func_228303_a_(1.75F, 1.5F, -5.75F, 0.0F, 1.0F, 6.0F, 0.0F, false);
/* 111 */     this.upperTeeth.func_78784_a(0, 2).func_228303_a_(-1.75F, 1.5F, -5.75F, 0.0F, 1.0F, 6.0F, 0.0F, false);
/* 112 */     this.upperTeeth.func_78784_a(0, 13).func_228303_a_(-2.0F, 1.5F, -5.75F, 4.0F, 1.0F, 0.0F, 0.0F, false);
/*     */     
/* 114 */     this.lowerMouth = new ModelRenderer((Model)this);
/* 115 */     this.lowerMouth.func_78793_a(0.0F, -5.3928F, -11.2171F);
/* 116 */     this.head.func_78792_a(this.lowerMouth);
/* 117 */     setRotationAngle(this.lowerMouth, 0.2182F, 0.0F, 0.0F);
/* 118 */     this.lowerMouth.func_78784_a(0, 45).func_228303_a_(-1.5F, -0.5F, -3.0F, 3.0F, 1.0F, 2.0F, 0.0F, false);
/* 119 */     this.lowerMouth.func_78784_a(0, 40).func_228303_a_(-2.0F, -0.5F, -1.0F, 4.0F, 1.0F, 2.0F, 0.0F, false);
/* 120 */     this.lowerMouth.func_78784_a(0, 10).func_228303_a_(-2.0F, -0.5F, 1.0F, 4.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 122 */     this.lowerTeeth = new ModelRenderer((Model)this);
/* 123 */     this.lowerTeeth.func_78793_a(-0.004F, -0.4557F, -1.2455F);
/* 124 */     this.lowerMouth.func_78792_a(this.lowerTeeth);
/* 125 */     this.lowerTeeth.func_78784_a(8, 44).func_228303_a_(-1.756F, -0.5F, 0.4F, 0.0F, 1.0F, 2.0F, 0.0F, false);
/* 126 */     this.lowerTeeth.func_78784_a(8, 43).func_228303_a_(-1.256F, -0.5F, -1.6F, 0.0F, 1.0F, 2.0F, 0.0F, false);
/* 127 */     this.lowerTeeth.func_78784_a(10, 9).func_228303_a_(1.764F, -0.5F, 0.4F, 0.0F, 1.0F, 2.0F, 0.0F, false);
/* 128 */     this.lowerTeeth.func_78784_a(10, 8).func_228303_a_(1.244F, -0.5F, -1.6F, 0.0F, 1.0F, 2.0F, 0.0F, false);
/* 129 */     this.lowerTeeth.func_78784_a(8, 13).func_228303_a_(-1.496F, -0.5F, -1.6F, 3.0F, 1.0F, 0.0F, 0.0F, false);
/*     */     
/* 131 */     this.leftArm = new ModelRenderer((Model)this);
/* 132 */     this.leftArm.func_78793_a(7.1414F, -1.5F, 0.7938F);
/* 133 */     setRotationAngle(this.leftArm, 0.0F, 0.0F, -0.2618F);
/*     */     
/* 135 */     this.leftArm2_r1 = new ModelRenderer((Model)this);
/* 136 */     this.leftArm2_r1.func_78793_a(1.0578F, 11.3381F, -2.2409F);
/* 137 */     this.leftArm.func_78792_a(this.leftArm2_r1);
/* 138 */     setRotationAngle(this.leftArm2_r1, -0.0873F, 0.0F, 0.0F);
/* 139 */     this.leftArm2_r1.func_78784_a(72, 77).func_228303_a_(-1.1991F, -4.5881F, -2.0528F, 5.0F, 9.0F, 6.0F, -0.01F, false);
/*     */     
/* 141 */     this.leftArm1_r1 = new ModelRenderer((Model)this);
/* 142 */     this.leftArm1_r1.func_78793_a(1.3917F, 3.4646F, -2.2409F);
/* 143 */     this.leftArm.func_78792_a(this.leftArm1_r1);
/* 144 */     setRotationAngle(this.leftArm1_r1, 0.1745F, 0.0F, 0.0F);
/* 145 */     this.leftArm1_r1.func_78784_a(0, 81).func_228303_a_(-1.5331F, -4.4646F, -2.0528F, 5.0F, 9.0F, 6.0F, 0.0F, false);
/*     */     
/* 147 */     this.rightArm = new ModelRenderer((Model)this);
/* 148 */     this.rightArm.func_78793_a(-6.8586F, -1.5F, -1.4562F);
/* 149 */     setRotationAngle(this.rightArm, 0.0F, 0.0F, 0.2618F);
/*     */     
/* 151 */     this.rightArm2_r1 = new ModelRenderer((Model)this);
/* 152 */     this.rightArm2_r1.func_78793_a(-4.2895F, 11.3116F, -1.0365F);
/* 153 */     this.rightArm.func_78792_a(this.rightArm2_r1);
/* 154 */     setRotationAngle(this.rightArm2_r1, -0.0873F, 0.0F, 0.0F);
/* 155 */     this.rightArm2_r1.func_78784_a(22, 81).func_228303_a_(-0.8519F, -4.5616F, -1.2573F, 5.0F, 9.0F, 6.0F, -0.01F, false);
/*     */     
/* 157 */     this.rightArm1_r1 = new ModelRenderer((Model)this);
/* 158 */     this.rightArm1_r1.func_78793_a(-3.9555F, 3.4382F, -1.0365F);
/* 159 */     this.rightArm.func_78792_a(this.rightArm1_r1);
/* 160 */     setRotationAngle(this.rightArm1_r1, 0.1745F, 0.0F, 0.0F);
/* 161 */     this.rightArm1_r1.func_78784_a(83, 59).func_228303_a_(-1.1859F, -4.4382F, -1.7573F, 5.0F, 9.0F, 6.0F, 0.0F, false);
/*     */     
/* 163 */     this.rightLeg = new ModelRenderer((Model)this);
/* 164 */     this.rightLeg.func_78793_a(-6.5F, 8.0F, -1.0F);
/* 165 */     this.rightLeg.func_78784_a(89, 74).func_228303_a_(-2.0F, 12.0F, -3.25F, 4.0F, 4.0F, 5.0F, 0.01F, false);
/*     */     
/* 167 */     this.rightLeg2_r1 = new ModelRenderer((Model)this);
/* 168 */     this.rightLeg2_r1.func_78793_a(11.5F, 41.6483F, -27.1858F);
/* 169 */     this.rightLeg.func_78792_a(this.rightLeg2_r1);
/* 170 */     setRotationAngle(this.rightLeg2_r1, 0.1745F, 0.0F, 0.0F);
/* 171 */     this.rightLeg2_r1.func_78784_a(90, 0).func_228303_a_(-13.5F, -29.1483F, 28.6858F, 4.0F, 5.0F, 5.0F, 0.0F, false);
/*     */     
/* 173 */     this.rightLeg1_r1 = new ModelRenderer((Model)this);
/* 174 */     this.rightLeg1_r1.func_78793_a(11.5F, 27.0F, -35.0F);
/* 175 */     this.rightLeg.func_78792_a(this.rightLeg1_r1);
/* 176 */     setRotationAngle(this.rightLeg1_r1, -0.1745F, 0.0F, 0.0F);
/* 177 */     this.rightLeg1_r1.func_78784_a(88, 86).func_228303_a_(-14.0F, -31.0F, 27.0F, 5.0F, 7.0F, 6.0F, 0.0F, false);
/*     */     
/* 179 */     this.leftLeg = new ModelRenderer((Model)this);
/* 180 */     this.leftLeg.func_78793_a(6.5F, 8.0F, -1.0F);
/* 181 */     this.leftLeg.func_78784_a(60, 92).func_228303_a_(-2.0F, 12.0F, -3.25F, 4.0F, 4.0F, 5.0F, 0.01F, false);
/*     */     
/* 183 */     this.leftLeg2_r1 = new ModelRenderer((Model)this);
/* 184 */     this.leftLeg2_r1.func_78793_a(11.5F, 41.6483F, -27.1858F);
/* 185 */     this.leftLeg.func_78792_a(this.leftLeg2_r1);
/* 186 */     setRotationAngle(this.leftLeg2_r1, 0.1745F, 0.0F, 0.0F);
/* 187 */     this.leftLeg2_r1.func_78784_a(92, 13).func_228303_a_(-13.5F, -29.1483F, 28.6858F, 4.0F, 5.0F, 5.0F, 0.0F, false);
/*     */     
/* 189 */     this.leftLeg1_r1 = new ModelRenderer((Model)this);
/* 190 */     this.leftLeg1_r1.func_78793_a(11.5F, 27.0F, -35.0F);
/* 191 */     this.leftLeg.func_78792_a(this.leftLeg1_r1);
/* 192 */     setRotationAngle(this.leftLeg1_r1, -0.1745F, 0.0F, 0.0F);
/* 193 */     this.leftLeg1_r1.func_78784_a(38, 90).func_228303_a_(-14.0F, -31.0F, 27.0F, 5.0F, 7.0F, 6.0F, 0.0F, false);
/*     */     
/* 195 */     this.tail = new ModelRenderer((Model)this);
/* 196 */     this.tail.func_78793_a(0.0F, 11.5F, 4.1667F);
/* 197 */     setRotationAngle(this.tail, -0.3491F, 0.0F, 0.0F);
/* 198 */     this.tail.func_78784_a(0, 60).func_228303_a_(-3.5F, -1.5F, -0.1667F, 7.0F, 6.0F, 15.0F, -0.01F, false);
/*     */     
/* 200 */     this.tail2 = new ModelRenderer((Model)this);
/* 201 */     this.tail2.func_78793_a(0.0F, 0.25F, 13.3333F);
/* 202 */     this.tail.func_78792_a(this.tail2);
/* 203 */     setRotationAngle(this.tail2, 0.2182F, 0.0F, 0.0F);
/* 204 */     this.tail2.func_78784_a(39, 41).func_228303_a_(-2.5F, -1.0F, 0.5F, 5.0F, 5.0F, 19.0F, -0.01F, false);
/*     */     
/* 206 */     this.body = new ModelRenderer((Model)this);
/* 207 */     this.body.func_78793_a(0.0F, 5.8571F, -3.2143F);
/* 208 */     this.body.func_78784_a(0, 0).func_228303_a_(-8.5F, -7.8571F, -4.7857F, 17.0F, 16.0F, 14.0F, 0.0F, false);
/* 209 */     this.body.func_78784_a(0, 45).func_228303_a_(-8.0F, -9.3571F, -4.2857F, 16.0F, 2.0F, 13.0F, 0.0F, false);
/* 210 */     this.body.func_78784_a(50, 18).func_228303_a_(-7.5F, -10.1071F, -3.5357F, 15.0F, 1.0F, 12.0F, 0.0F, false);
/* 211 */     this.body.func_78784_a(44, 65).func_228303_a_(-7.0F, -10.8571F, -3.0357F, 14.0F, 1.0F, 11.0F, 0.0F, false);
/* 212 */     this.body.func_78784_a(0, 30).func_228303_a_(-8.0F, 7.1429F, -4.2857F, 16.0F, 2.0F, 13.0F, 0.0F, false);
/* 213 */     this.body.func_78784_a(48, 0).func_228303_a_(-7.5F, 8.8929F, -3.7857F, 15.0F, 1.0F, 12.0F, 0.0F, false);
/* 214 */     this.body.func_78784_a(45, 31).func_228303_a_(-6.0F, -11.8571F, -2.0357F, 12.0F, 1.0F, 9.0F, 0.0F, false);
/* 215 */     this.body.func_78784_a(68, 41).func_228303_a_(-7.0F, -6.8571F, -5.7857F, 14.0F, 14.0F, 1.0F, 0.0F, false);
/* 216 */     this.body.func_78784_a(44, 77).func_228303_a_(-6.5F, -5.8571F, -6.5357F, 13.0F, 12.0F, 1.0F, 0.0F, false);
/*     */     
/* 218 */     this.field_78115_e = this.body;
/* 219 */     this.field_78116_c = this.head;
/* 220 */     this.field_178723_h = this.rightArm;
/* 221 */     this.field_178724_i = this.leftArm;
/* 222 */     this.field_178721_j = this.rightLeg;
/* 223 */     this.field_178722_k = this.leftLeg;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 229 */     this.head.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 230 */     this.leftArm.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 231 */     this.rightArm.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 232 */     this.rightLeg.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 233 */     this.leftLeg.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 234 */     this.tail.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 235 */     this.body.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 242 */     boolean flag = (entity.func_184599_cB() > 4);
/* 243 */     boolean flag1 = entity.func_213314_bj();
/* 244 */     this.head.field_78796_g = netHeadYaw * 0.017453292F;
/* 245 */     if (flag) {
/* 246 */       this.head.field_78795_f = -0.7853982F;
/* 247 */     } else if (this.field_205061_a > 0.0F) {
/*     */       
/* 249 */       if (flag1) {
/* 250 */         this.head.field_78795_f = func_205060_a(this.head.field_78795_f, -0.7853982F, this.field_205061_a);
/*     */       } else {
/* 252 */         this.head.field_78795_f = func_205060_a(this.head.field_78795_f, headPitch * 0.017453292F, this.field_205061_a);
/*     */       } 
/*     */     } else {
/*     */       
/* 256 */       this.head.field_78795_f = headPitch * 0.015707964F;
/* 257 */       if (this.head.field_78795_f > 0.6D) {
/* 258 */         this.head.field_78795_f = 0.6F;
/*     */       }
/*     */     } 
/*     */     
/* 262 */     float f = 1.0F;
/* 263 */     this.rightArm.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.6662F + 3.1415927F) * 0.4F * limbSwingAmount * 0.5F / f;
/* 264 */     this.leftArm.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.6662F) * 0.4F * limbSwingAmount * 0.5F / f;
/* 265 */     this.rightLeg.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.6662F) * 0.3F * limbSwingAmount / f;
/* 266 */     this.leftLeg.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.6662F + 3.1415927F) * 0.3F * limbSwingAmount / f;
/* 267 */     if (!entity.func_184614_ca().func_190926_b())
/* 268 */       this.rightArm.field_78795_f += -0.15F; 
/* 269 */     if (entity.func_70051_ag()) {
/* 270 */       this.tail.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.6662F) * 0.2F * limbSwingAmount;
/*     */     }
/*     */     
/* 273 */     this.field_217112_c = ((LivingEntity)entity).field_70733_aJ;
/* 274 */     boolean isBlackLeg = EntityStatsCapability.get((LivingEntity)entity).isBlackLeg();
/* 275 */     if (this.field_217112_c > 0.0F)
/*     */     {
/* 277 */       if (isBlackLeg) {
/*     */         
/* 279 */         this.body.field_78796_g = MathHelper.func_76126_a(MathHelper.func_76129_c(this.field_217112_c) * 6.2831855F) * 0.2F;
/* 280 */         this.rightLeg.field_78796_g += this.body.field_78796_g;
/* 281 */         this.leftLeg.field_78796_g += this.body.field_78796_g;
/* 282 */         float f1 = 1.0F - this.field_217112_c;
/* 283 */         f1 *= f1;
/* 284 */         f1 *= f1;
/* 285 */         f1 = 1.0F - f1;
/* 286 */         float f2 = MathHelper.func_76126_a(f1 * 3.1415927F);
/* 287 */         float f3 = MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * -(this.head.field_78795_f - 0.7F) * 0.75F;
/* 288 */         this.rightLeg.field_78795_f = (float)(this.rightArm.field_78795_f - f2 * 0.5D + f3);
/* 289 */         this.rightLeg.field_78796_g += this.body.field_78796_g * 2.0F;
/*     */       }
/*     */       else {
/*     */         
/* 293 */         this.body.field_78796_g = MathHelper.func_76126_a(MathHelper.func_76129_c(this.field_217112_c) * 6.2831855F) * 0.2F;
/* 294 */         this.body.field_78796_g = MathHelper.func_76126_a(MathHelper.func_76129_c(this.field_217112_c) * 6.2831855F) * 0.2F;
/* 295 */         this.rightArm.field_78798_e = MathHelper.func_76126_a(this.body.field_78796_g) * 8.0F;
/* 296 */         this.rightArm.field_78800_c = -MathHelper.func_76134_b(this.body.field_78796_g) * 8.0F;
/* 297 */         this.rightArm.field_78796_g += this.body.field_78796_g;
/* 298 */         this.leftArm.field_78796_g += this.body.field_78796_g;
/* 299 */         this.leftArm.field_78795_f += this.body.field_78796_g;
/* 300 */         float f1 = 1.0F - this.field_217112_c;
/* 301 */         f1 *= f1;
/* 302 */         f1 *= f1;
/* 303 */         f1 = 1.0F - f1;
/* 304 */         float f2 = MathHelper.func_76126_a(f1 * 3.1415927F);
/* 305 */         float f3 = MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * -(this.head.field_78795_f - 0.7F) * 0.75F;
/* 306 */         this.rightArm.field_78795_f = (float)(this.rightArm.field_78795_f - f2 * 1.2D + f3);
/* 307 */         this.rightArm.field_78796_g += this.body.field_78796_g * 2.0F;
/* 308 */         this.rightArm.field_78808_h += MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * -0.4F;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 315 */     modelRenderer.field_78795_f = x;
/* 316 */     modelRenderer.field_78796_g = y;
/* 317 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225599_a_(HandSide side, MatrixStack matrixStack) {
/* 323 */     super.func_225599_a_(side, matrixStack);
/* 324 */     matrixStack.func_227861_a_(0.0D, 0.45D, -0.05D);
/* 325 */     matrixStack.func_227863_a_(Vector3f.field_229183_f_.func_229187_a_((side == HandSide.RIGHT) ? 5.0F : -5.0F));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
/* 331 */     if (side == HandSide.RIGHT) {
/*     */       
/* 333 */       matrixStack.func_227861_a_(-0.3D, -0.3D, -0.2D);
/* 334 */       matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(-10.0F));
/* 335 */       matrixStack.func_227863_a_(Vector3f.field_229183_f_.func_229187_a_(10.0F));
/* 336 */       this.rightArm2_r1.func_228309_a_(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     }
/*     */     else {
/*     */       
/* 340 */       matrixStack.func_227861_a_(0.3D, -0.3D, -0.2D);
/* 341 */       matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(-10.0F));
/* 342 */       matrixStack.func_227863_a_(Vector3f.field_229183_f_.func_229187_a_(-10.0F));
/* 343 */       this.leftArm2_r1.func_228309_a_(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
/* 350 */     if (side == HandSide.RIGHT) {
/*     */       
/* 352 */       matrixStack.func_227861_a_(0.2D, -0.5D, 0.4D);
/* 353 */       matrixStack.func_227862_a_(1.5F, 1.5F, 1.5F);
/* 354 */       matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(-60.0F));
/* 355 */       matrixStack.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(10.0F));
/* 356 */       this.rightLeg.func_228309_a_(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     }
/*     */     else {
/*     */       
/* 360 */       matrixStack.func_227861_a_(-0.3D, -0.5D, 0.3D);
/* 361 */       matrixStack.func_227862_a_(1.5F, 1.5F, 1.5F);
/* 362 */       matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(60.0F));
/* 363 */       matrixStack.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(10.0F));
/* 364 */       this.leftLeg.func_228309_a_(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\morphs\BrachiosaurusHeavyModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */