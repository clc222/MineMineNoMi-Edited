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
/*     */ public class GiraffeHeavyModel<T extends LivingEntity>
/*     */   extends MorphModel<T> implements IHasArm {
/*     */   private final ModelRenderer neck;
/*     */   private final ModelRenderer neck2;
/*     */   private final ModelRenderer head;
/*     */   private final ModelRenderer rightEar;
/*     */   private final ModelRenderer head2;
/*     */   private final ModelRenderer leftHorn;
/*     */   private final ModelRenderer rightHorn;
/*     */   private final ModelRenderer leftEar;
/*     */   private final ModelRenderer mane;
/*     */   private final ModelRenderer mane2;
/*     */   private final ModelRenderer body;
/*     */   private final ModelRenderer leftShoulder;
/*     */   private final ModelRenderer rightShoulder;
/*     */   private final ModelRenderer rightArm;
/*     */   private final ModelRenderer rightArm2;
/*     */   private final ModelRenderer rightHand2;
/*     */   private final ModelRenderer rightHand1;
/*     */   private final ModelRenderer leftArm;
/*     */   private final ModelRenderer leftArm2;
/*     */   private final ModelRenderer leftHand2;
/*     */   private final ModelRenderer leftHand1;
/*     */   private final ModelRenderer rightLeg;
/*     */   private final ModelRenderer rightLeg3;
/*     */   private final ModelRenderer rightLeg2;
/*     */   private final ModelRenderer rightHoof;
/*     */   private final ModelRenderer rightHoof2;
/*     */   private final ModelRenderer rightHoof3;
/*     */   private final ModelRenderer tail;
/*     */   private final ModelRenderer tail2;
/*     */   private final ModelRenderer tail3;
/*     */   private final ModelRenderer leftLeg;
/*     */   private final ModelRenderer leftLeg2;
/*     */   private final ModelRenderer leftLeg3;
/*     */   private final ModelRenderer leftHoof;
/*     */   private final ModelRenderer leftHoof2;
/*     */   private final ModelRenderer leftHoof3;
/*     */   
/*     */   public GiraffeHeavyModel() {
/*  57 */     super(1.0F);
/*  58 */     this.field_78090_t = 128;
/*  59 */     this.field_78089_u = 64;
/*     */     
/*  61 */     this.neck = new ModelRenderer((Model)this);
/*  62 */     this.neck.func_78793_a(-2.0F, -11.0F, -3.5F);
/*  63 */     setRotationAngle(this.neck, 0.1745F, 0.0F, 0.0F);
/*  64 */     this.neck.func_78784_a(79, 0).func_228303_a_(0.0F, 0.0F, 0.0F, 5.0F, 8.0F, 5.0F, 0.0F, false);
/*     */     
/*  66 */     this.neck2 = new ModelRenderer((Model)this);
/*  67 */     this.neck2.func_78793_a(0.5F, -7.0F, -0.5F);
/*  68 */     this.neck.func_78792_a(this.neck2);
/*  69 */     setRotationAngle(this.neck2, 0.1396F, 0.0F, 0.0F);
/*  70 */     this.neck2.func_78784_a(79, 14).func_228303_a_(0.0F, 0.0F, 0.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);
/*     */     
/*  72 */     this.head = new ModelRenderer((Model)this);
/*  73 */     this.head.func_78793_a(2.0F, 0.5F, 1.5F);
/*  74 */     this.neck2.func_78792_a(this.head);
/*  75 */     setRotationAngle(this.head, -0.3142F, 0.0F, 0.0F);
/*  76 */     this.head.func_78784_a(32, 18).func_228303_a_(-2.0F, -4.0F, -6.0F, 4.0F, 3.0F, 8.0F, 0.0F, false);
/*     */     
/*  78 */     this.rightEar = new ModelRenderer((Model)this);
/*  79 */     this.rightEar.func_78793_a(-2.0F, -3.5F, 0.0F);
/*  80 */     this.head.func_78792_a(this.rightEar);
/*  81 */     setRotationAngle(this.rightEar, 0.0F, 0.0F, -0.2618F);
/*  82 */     this.rightEar.func_78784_a(32, 0).func_228303_a_(-3.0F, 0.0F, 0.0F, 3.0F, 2.0F, 0.0F, 0.0F, false);
/*     */     
/*  84 */     this.head2 = new ModelRenderer((Model)this);
/*  85 */     this.head2.func_78793_a(-2.0F, -1.0F, 0.5F);
/*  86 */     this.head.func_78792_a(this.head2);
/*  87 */     this.head2.func_78784_a(31, 29).func_228303_a_(0.01F, 0.0F, -4.0F, 4.0F, 2.0F, 5.0F, 0.0F, false);
/*     */     
/*  89 */     this.leftHorn = new ModelRenderer((Model)this);
/*  90 */     this.leftHorn.func_78793_a(0.5F, -6.0F, -0.5F);
/*  91 */     this.head.func_78792_a(this.leftHorn);
/*  92 */     this.leftHorn.func_78784_a(60, 20).func_228303_a_(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/*  94 */     this.rightHorn = new ModelRenderer((Model)this);
/*  95 */     this.rightHorn.func_78793_a(-1.5F, -6.0F, -0.5F);
/*  96 */     this.head.func_78792_a(this.rightHorn);
/*  97 */     this.rightHorn.func_78784_a(60, 20).func_228303_a_(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/*  99 */     this.leftEar = new ModelRenderer((Model)this);
/* 100 */     this.leftEar.func_78793_a(2.0F, -3.5F, 0.0F);
/* 101 */     this.head.func_78792_a(this.leftEar);
/* 102 */     setRotationAngle(this.leftEar, 0.0F, 0.0F, 0.2618F);
/* 103 */     this.leftEar.func_78784_a(32, 0).func_228303_a_(0.0F, 0.0F, 0.0F, 3.0F, 2.0F, 0.0F, 0.0F, true);
/*     */     
/* 105 */     this.mane = new ModelRenderer((Model)this);
/* 106 */     this.mane.func_78793_a(2.0F, -2.0F, 3.5F);
/* 107 */     this.neck2.func_78792_a(this.mane);
/* 108 */     setRotationAngle(this.mane, 0.0349F, 0.0F, 0.0F);
/* 109 */     this.mane.func_78784_a(76, 0).func_228303_a_(0.0F, 0.0F, 0.0F, 0.0F, 10.0F, 1.0F, 0.0F, false);
/*     */     
/* 111 */     this.mane2 = new ModelRenderer((Model)this);
/* 112 */     this.mane2.func_78793_a(2.5F, 0.0F, 4.6F);
/* 113 */     this.neck.func_78792_a(this.mane2);
/* 114 */     setRotationAngle(this.mane2, 0.0175F, 0.0F, 0.0F);
/* 115 */     this.mane2.func_78784_a(76, 0).func_228303_a_(0.0F, 0.0F, 0.0F, 0.0F, 8.0F, 1.0F, 0.0F, false);
/*     */     
/* 117 */     this.body = new ModelRenderer((Model)this);
/* 118 */     this.body.func_78793_a(-0.5F, -4.0F, 0.0F);
/* 119 */     this.body.func_78784_a(0, 0).func_228303_a_(-4.0F, 0.0F, -2.0F, 10.0F, 16.0F, 5.0F, 0.0F, false);
/*     */     
/* 121 */     this.leftShoulder = new ModelRenderer((Model)this);
/* 122 */     this.leftShoulder.func_78793_a(-0.5F, 7.5F, -1.9F);
/* 123 */     this.body.func_78792_a(this.leftShoulder);
/* 124 */     setRotationAngle(this.leftShoulder, 0.0F, 0.0F, -0.9599F);
/* 125 */     this.leftShoulder.func_78784_a(47, 0).func_228303_a_(0.0F, 0.0F, -0.1F, 9.0F, 7.0F, 5.0F, -0.01F, true);
/*     */     
/* 127 */     this.rightShoulder = new ModelRenderer((Model)this);
/* 128 */     this.rightShoulder.func_78793_a(2.5F, 7.4F, -1.91F);
/* 129 */     this.body.func_78792_a(this.rightShoulder);
/* 130 */     setRotationAngle(this.rightShoulder, 0.0F, 0.0F, -2.2689F);
/* 131 */     this.rightShoulder.func_78784_a(47, 0).func_228303_a_(0.0F, -7.5F, -0.09F, 9.0F, 7.0F, 5.0F, -0.01F, false);
/*     */     
/* 133 */     this.rightArm = new ModelRenderer((Model)this);
/* 134 */     this.rightArm.func_78793_a(-8.0F, 1.0F, -1.0F);
/* 135 */     setRotationAngle(this.rightArm, 0.0F, 0.0F, 0.2793F);
/* 136 */     this.rightArm.func_78784_a(23, 30).func_228303_a_(0.0F, 0.0F, 0.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);
/*     */     
/* 138 */     this.rightArm2 = new ModelRenderer((Model)this);
/* 139 */     this.rightArm2.func_78793_a(0.0F, 6.0F, 0.0F);
/* 140 */     this.rightArm.func_78792_a(this.rightArm2);
/* 141 */     setRotationAngle(this.rightArm2, 0.0F, 0.0F, -0.4189F);
/* 142 */     this.rightArm2.func_78784_a(23, 39).func_228303_a_(0.0F, 0.0F, 0.1F, 2.0F, 6.0F, 2.0F, 0.0F, false);
/*     */     
/* 144 */     this.rightHand2 = new ModelRenderer((Model)this);
/* 145 */     this.rightHand2.func_78793_a(1.1392F, 5.1097F, 0.8F);
/* 146 */     this.rightArm2.func_78792_a(this.rightHand2);
/* 147 */     setRotationAngle(this.rightHand2, 0.1745F, 0.0F, -0.3491F);
/* 148 */     this.rightHand2.func_78784_a(65, 20).func_228303_a_(0.0F, -0.0868F, -0.4924F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/* 150 */     this.rightHand1 = new ModelRenderer((Model)this);
/* 151 */     this.rightHand1.func_78793_a(0.0F, 5.5F, 2.0F);
/* 152 */     this.rightArm2.func_78792_a(this.rightHand1);
/* 153 */     setRotationAngle(this.rightHand1, 0.1745F, 1.5708F, 0.1396F);
/* 154 */     this.rightHand1.func_78784_a(65, 24).func_228303_a_(-0.1F, 0.0F, 0.1F, 2.0F, 2.0F, 1.0F, -0.01F, false);
/*     */     
/* 156 */     this.leftArm = new ModelRenderer((Model)this);
/* 157 */     this.leftArm.func_78793_a(9.0F, 1.0F, -1.0F);
/* 158 */     setRotationAngle(this.leftArm, 0.0F, 0.0F, -0.2793F);
/* 159 */     this.leftArm.func_78784_a(23, 30).func_228303_a_(-2.0F, 0.0F, 0.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);
/*     */     
/* 161 */     this.leftArm2 = new ModelRenderer((Model)this);
/* 162 */     this.leftArm2.func_78793_a(0.0F, 6.0F, 0.0F);
/* 163 */     this.leftArm.func_78792_a(this.leftArm2);
/* 164 */     setRotationAngle(this.leftArm2, 0.0F, 0.0F, 0.4189F);
/* 165 */     this.leftArm2.func_78784_a(23, 39).func_228303_a_(-2.0F, 0.0F, 0.1F, 2.0F, 6.0F, 2.0F, 0.0F, false);
/*     */     
/* 167 */     this.leftHand2 = new ModelRenderer((Model)this);
/* 168 */     this.leftHand2.func_78793_a(-7.4075F, 2.7019F, 1.8F);
/* 169 */     this.leftArm2.func_78792_a(this.leftHand2);
/* 170 */     setRotationAngle(this.leftHand2, 0.1745F, 0.0F, 0.3491F);
/* 171 */     this.leftHand2.func_78784_a(65, 20).func_228303_a_(5.9F, -0.07F, -1.4F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/* 173 */     this.leftHand1 = new ModelRenderer((Model)this);
/* 174 */     this.leftHand1.func_78793_a(1.8929F, 2.9691F, 1.5F);
/* 175 */     this.leftArm2.func_78792_a(this.leftHand1);
/* 176 */     setRotationAngle(this.leftHand1, 0.1745F, -1.5708F, -0.1396F);
/* 177 */     this.leftHand1.func_78784_a(65, 24).func_228303_a_(-1.4F, 2.6065F, 1.9F, 2.0F, 2.0F, 1.0F, -0.01F, false);
/*     */     
/* 179 */     this.rightLeg = new ModelRenderer((Model)this);
/* 180 */     this.rightLeg.func_78793_a(-2.0F, 11.6F, 1.0F);
/* 181 */     setRotationAngle(this.rightLeg, -0.3491F, 0.0F, 0.0F);
/* 182 */     this.rightLeg.func_78784_a(10, 30).func_228303_a_(-2.0F, 0.0F, -2.0F, 3.0F, 7.0F, 3.0F, 0.0F, false);
/*     */     
/* 184 */     this.rightLeg3 = new ModelRenderer((Model)this);
/* 185 */     this.rightLeg3.func_78793_a(0.5F, 5.0F, 0.0F);
/* 186 */     this.rightLeg.func_78792_a(this.rightLeg3);
/* 187 */     setRotationAngle(this.rightLeg3, 1.7453F, 0.0F, 0.0F);
/* 188 */     this.rightLeg3.func_78784_a(10, 41).func_228303_a_(-2.0F, 0.0F, -2.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
/*     */     
/* 190 */     this.rightLeg2 = new ModelRenderer((Model)this);
/* 191 */     this.rightLeg2.func_78793_a(0.0F, 5.2065F, -0.5747F);
/* 192 */     this.rightLeg3.func_78792_a(this.rightLeg2);
/* 193 */     setRotationAngle(this.rightLeg2, -1.9199F, 0.0F, 0.0F);
/* 194 */     this.rightLeg2.func_78784_a(0, 30).func_228303_a_(-2.0F, 0.0F, -2.0F, 2.0F, 6.0F, 2.0F, 0.01F, false);
/*     */     
/* 196 */     this.rightHoof = new ModelRenderer((Model)this);
/* 197 */     this.rightHoof.func_78793_a(0.0F, 5.0F, -0.5F);
/* 198 */     this.rightLeg2.func_78792_a(this.rightHoof);
/* 199 */     setRotationAngle(this.rightHoof, 0.5236F, 0.0F, 0.0F);
/* 200 */     this.rightHoof.func_78784_a(0, 41).func_228303_a_(-2.0F, 0.0F, -2.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
/*     */     
/* 202 */     this.rightHoof2 = new ModelRenderer((Model)this);
/* 203 */     this.rightHoof2.func_78793_a(-1.0F, 0.0F, -2.5F);
/* 204 */     this.rightHoof.func_78792_a(this.rightHoof2);
/* 205 */     setRotationAngle(this.rightHoof2, -0.1211F, -0.4883F, -0.0394F);
/* 206 */     this.rightHoof2.func_78784_a(65, 20).func_228303_a_(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/* 208 */     this.rightHoof3 = new ModelRenderer((Model)this);
/* 209 */     this.rightHoof3.func_78793_a(-2.0F, 0.0F, -2.0F);
/* 210 */     this.rightHoof.func_78792_a(this.rightHoof3);
/* 211 */     setRotationAngle(this.rightHoof3, -0.1211F, 0.4883F, 0.0394F);
/* 212 */     this.rightHoof3.func_78784_a(65, 20).func_228303_a_(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/* 214 */     this.tail = new ModelRenderer((Model)this);
/* 215 */     this.tail.func_78793_a(0.0F, 10.0F, 3.0F);
/* 216 */     setRotationAngle(this.tail, -0.733F, 0.0F, 0.0F);
/* 217 */     this.tail.func_78784_a(31, 3).func_228303_a_(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 5.0F, 0.0F, false);
/*     */     
/* 219 */     this.tail2 = new ModelRenderer((Model)this);
/* 220 */     this.tail2.func_78793_a(0.0F, 0.0F, 4.5F);
/* 221 */     this.tail.func_78792_a(this.tail2);
/* 222 */     setRotationAngle(this.tail2, 0.4712F, 0.0F, 0.0F);
/* 223 */     this.tail2.func_78784_a(31, 10).func_228303_a_(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
/*     */     
/* 225 */     this.tail3 = new ModelRenderer((Model)this);
/* 226 */     this.tail3.func_78793_a(-0.5F, -0.3578F, 3.2176F);
/* 227 */     this.tail2.func_78792_a(this.tail3);
/* 228 */     setRotationAngle(this.tail3, 0.2094F, 0.0F, 0.0F);
/* 229 */     this.tail3.func_78784_a(60, 13).func_228303_a_(0.0F, 0.0F, 0.0F, 2.0F, 2.0F, 3.0F, 0.0F, false);
/*     */     
/* 231 */     this.leftLeg = new ModelRenderer((Model)this);
/* 232 */     this.leftLeg.func_78793_a(4.0F, 11.6F, 1.0F);
/* 233 */     setRotationAngle(this.leftLeg, -0.3491F, 0.0F, 0.0F);
/* 234 */     this.leftLeg.func_78784_a(10, 30).func_228303_a_(-2.0F, 0.0F, -2.0F, 3.0F, 7.0F, 3.0F, 0.0F, false);
/*     */     
/* 236 */     this.leftLeg2 = new ModelRenderer((Model)this);
/* 237 */     this.leftLeg2.func_78793_a(0.5F, 5.0F, 0.0F);
/* 238 */     this.leftLeg.func_78792_a(this.leftLeg2);
/* 239 */     setRotationAngle(this.leftLeg2, 1.7453F, 0.0F, 0.0F);
/* 240 */     this.leftLeg2.func_78784_a(10, 41).func_228303_a_(-2.0F, 0.0F, -2.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
/*     */     
/* 242 */     this.leftLeg3 = new ModelRenderer((Model)this);
/* 243 */     this.leftLeg3.func_78793_a(0.0F, 5.2065F, -0.5747F);
/* 244 */     this.leftLeg2.func_78792_a(this.leftLeg3);
/* 245 */     setRotationAngle(this.leftLeg3, -1.9199F, 0.0F, 0.0F);
/* 246 */     this.leftLeg3.func_78784_a(0, 30).func_228303_a_(-2.0F, 0.0F, -2.0F, 2.0F, 6.0F, 2.0F, 0.01F, false);
/*     */     
/* 248 */     this.leftHoof = new ModelRenderer((Model)this);
/* 249 */     this.leftHoof.func_78793_a(0.0F, 5.0F, -0.5F);
/* 250 */     this.leftLeg3.func_78792_a(this.leftHoof);
/* 251 */     setRotationAngle(this.leftHoof, 0.5236F, 0.0F, 0.0F);
/* 252 */     this.leftHoof.func_78784_a(0, 41).func_228303_a_(-2.0F, 0.0F, -2.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
/*     */     
/* 254 */     this.leftHoof2 = new ModelRenderer((Model)this);
/* 255 */     this.leftHoof2.func_78793_a(-1.0F, 0.0F, -2.5F);
/* 256 */     this.leftHoof.func_78792_a(this.leftHoof2);
/* 257 */     setRotationAngle(this.leftHoof2, -0.1211F, -0.4883F, -0.0394F);
/* 258 */     this.leftHoof2.func_78784_a(65, 20).func_228303_a_(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/* 260 */     this.leftHoof3 = new ModelRenderer((Model)this);
/* 261 */     this.leftHoof3.func_78793_a(-2.0F, 0.0F, -2.0F);
/* 262 */     this.leftHoof.func_78792_a(this.leftHoof3);
/* 263 */     setRotationAngle(this.leftHoof3, -0.1211F, 0.4883F, 0.0394F);
/* 264 */     this.leftHoof3.func_78784_a(65, 20).func_228303_a_(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/* 266 */     this.field_78115_e = this.body;
/* 267 */     this.field_78116_c = this.head;
/* 268 */     this.field_178723_h = this.rightArm;
/* 269 */     this.field_178724_i = this.leftArm;
/* 270 */     this.field_178721_j = this.rightLeg;
/* 271 */     this.field_178722_k = this.leftLeg;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 277 */     this.neck.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 278 */     this.body.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 279 */     this.rightArm.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 280 */     this.leftArm.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 281 */     this.rightLeg.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 282 */     this.tail.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 283 */     this.leftLeg.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 290 */     boolean flag = (entity.func_184599_cB() > 4);
/* 291 */     boolean flag1 = entity.func_213314_bj();
/* 292 */     this.head.field_78796_g = netHeadYaw * 0.017453292F;
/* 293 */     if (flag) {
/* 294 */       this.head.field_78795_f = -0.7853982F;
/* 295 */     } else if (this.field_205061_a > 0.0F) {
/*     */       
/* 297 */       if (flag1) {
/* 298 */         this.head.field_78795_f = func_205060_a(this.head.field_78795_f, -0.7853982F, this.field_205061_a);
/*     */       } else {
/* 300 */         this.head.field_78795_f = func_205060_a(this.head.field_78795_f, headPitch * 0.017453292F, this.field_205061_a);
/*     */       } 
/*     */     } else {
/*     */       
/* 304 */       this.head.field_78795_f = headPitch * 0.015707964F;
/* 305 */       if (this.head.field_78795_f > 0.6D) {
/* 306 */         this.head.field_78795_f = 0.6F;
/*     */       }
/*     */     } 
/*     */     
/* 310 */     float f = 1.0F;
/* 311 */     this.rightArm.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.6662F + 3.1415927F) * 0.8F * limbSwingAmount * 0.5F / f;
/* 312 */     this.leftArm.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.6662F) * 0.8F * limbSwingAmount * 0.5F / f;
/* 313 */     this.rightLeg.field_78795_f = -0.34F + MathHelper.func_76134_b(limbSwing * 0.6662F) * 0.7F * limbSwingAmount / f;
/* 314 */     this.leftLeg.field_78795_f = -0.34F + MathHelper.func_76134_b(limbSwing * 0.6662F + 3.1415927F) * 0.7F * limbSwingAmount / f;
/* 315 */     if (!entity.func_184614_ca().func_190926_b())
/* 316 */       this.rightArm.field_78795_f += -0.15F; 
/* 317 */     if (entity.func_70051_ag()) {
/*     */       
/* 319 */       this.tail.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.6662F) * 0.2F * limbSwingAmount;
/* 320 */       this.leftEar.field_78796_g = -0.3F - MathHelper.func_76134_b(limbSwing * 0.6662F) * 0.2F * limbSwingAmount;
/* 321 */       this.rightEar.field_78796_g = 0.3F + MathHelper.func_76134_b(limbSwing * 0.6662F) * 0.2F * limbSwingAmount;
/*     */     } 
/*     */ 
/*     */     
/* 325 */     this.field_217112_c = ((LivingEntity)entity).field_70733_aJ;
/* 326 */     boolean isBlackLeg = EntityStatsCapability.get((LivingEntity)entity).isBlackLeg();
/* 327 */     if (this.field_217112_c > 0.0F)
/*     */     {
/* 329 */       if (isBlackLeg) {
/*     */         
/* 331 */         this.body.field_78796_g = MathHelper.func_76126_a(MathHelper.func_76129_c(this.field_217112_c) * 6.2831855F) * 0.2F;
/* 332 */         this.rightLeg.field_78796_g += this.body.field_78796_g;
/* 333 */         this.leftLeg.field_78796_g += this.body.field_78796_g;
/* 334 */         float f1 = 1.0F - this.field_217112_c;
/* 335 */         f1 *= f1;
/* 336 */         f1 *= f1;
/* 337 */         f1 = 1.0F - f1;
/* 338 */         float f2 = MathHelper.func_76126_a(f1 * 3.1415927F);
/* 339 */         float f3 = MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * -(this.head.field_78795_f - 0.7F) * 0.75F;
/* 340 */         this.rightLeg.field_78795_f = (float)(this.rightArm.field_78795_f - f2 * 1.5D + f3);
/* 341 */         this.rightLeg.field_78796_g += this.body.field_78796_g * 2.0F;
/*     */       }
/*     */       else {
/*     */         
/* 345 */         this.body.field_78796_g = MathHelper.func_76126_a(MathHelper.func_76129_c(this.field_217112_c) * 6.2831855F) * 0.2F;
/* 346 */         this.rightArm.field_78798_e = MathHelper.func_76126_a(this.body.field_78796_g) * 12.0F;
/* 347 */         this.rightArm.field_78800_c = -MathHelper.func_76134_b(this.body.field_78796_g) * 9.0F;
/* 348 */         this.rightArm.field_78796_g += this.body.field_78796_g;
/* 349 */         this.leftArm.field_78798_e = -MathHelper.func_76126_a(this.body.field_78796_g) * 5.0F;
/* 350 */         this.leftArm.field_78796_g -= this.body.field_78796_g;
/* 351 */         this.leftArm.field_78795_f -= this.body.field_78796_g;
/* 352 */         float f1 = 1.0F - this.field_217112_c;
/* 353 */         f1 *= f1;
/* 354 */         f1 *= f1;
/* 355 */         f1 = 1.0F - f1;
/* 356 */         float f2 = MathHelper.func_76126_a(f1 * 3.1415927F);
/* 357 */         float f3 = MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * -(this.head.field_78795_f - 0.7F) * 0.75F;
/* 358 */         this.rightArm.field_78795_f = (float)(this.rightArm.field_78795_f - f2 * 1.2D + f3);
/* 359 */         this.rightArm.field_78796_g += this.body.field_78796_g * 2.0F;
/* 360 */         this.rightArm.field_78808_h += MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * -0.4F;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 365 */     if (entity.func_213453_ef()) {
/*     */       
/* 367 */       this.body.field_78795_f = 0.5F;
/* 368 */       this.body.field_78798_e -= 4.0F;
/* 369 */       this.rightArm.field_78795_f += 0.4F;
/* 370 */       this.rightArm.field_78798_e -= 0.5F;
/* 371 */       this.leftArm.field_78795_f += 0.4F;
/* 372 */       this.leftArm.field_78798_e -= 0.5F;
/* 373 */       this.rightLeg.field_78798_e = 4.0F;
/* 374 */       this.leftLeg.field_78798_e = 4.5F;
/* 375 */       this.rightLeg.field_78797_d = 10.5F;
/* 376 */       this.leftLeg.field_78797_d = 10.5F;
/* 377 */       this.head.field_78797_d = 0.0F;
/* 378 */       this.head.field_78798_e = 2.0F;
/* 379 */       this.neck.field_78795_f += 0.25F;
/* 380 */       this.neck.field_78798_e = -9.0F;
/* 381 */       this.neck.field_78797_d++;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225599_a_(HandSide side, MatrixStack matrixStack) {
/* 388 */     super.func_225599_a_(side, matrixStack);
/* 389 */     matrixStack.func_227861_a_((side == HandSide.RIGHT) ? 0.12D : -0.12D, 0.2D, 0.1D);
/* 390 */     matrixStack.func_227863_a_(Vector3f.field_229183_f_.func_229187_a_((side == HandSide.RIGHT) ? -20.0F : 20.0F));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
/* 396 */     if (side == HandSide.RIGHT) {
/*     */       
/* 398 */       matrixStack.func_227861_a_(-0.5D, 0.0D, -0.1D);
/* 399 */       matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(-10.0F));
/* 400 */       matrixStack.func_227863_a_(Vector3f.field_229183_f_.func_229187_a_(10.0F));
/* 401 */       this.rightArm2.func_228309_a_(matrixStack, vertex, packedLight, OverlayTexture.field_229196_a_, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */     }
/*     */     else {
/*     */       
/* 405 */       matrixStack.func_227861_a_(0.5D, 0.0D, -0.1D);
/* 406 */       matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(-10.0F));
/* 407 */       matrixStack.func_227863_a_(Vector3f.field_229183_f_.func_229187_a_(-10.0F));
/* 408 */       this.leftArm2.func_228309_a_(matrixStack, vertex, packedLight, OverlayTexture.field_229196_a_, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
/* 415 */     if (side == HandSide.RIGHT) {
/*     */       
/* 417 */       matrixStack.func_227861_a_(0.0D, -1.2D, 0.3D);
/* 418 */       matrixStack.func_227862_a_(1.5F, 1.5F, 1.5F);
/* 419 */       matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(-60.0F));
/* 420 */       this.rightLeg.func_228309_a_(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     }
/*     */     else {
/*     */       
/* 424 */       matrixStack.func_227861_a_(0.0D, -1.2D, 0.3D);
/* 425 */       matrixStack.func_227862_a_(1.5F, 1.5F, 1.5F);
/* 426 */       matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(60.0F));
/* 427 */       this.leftLeg.func_228309_a_(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 433 */     modelRenderer.field_78795_f = x;
/* 434 */     modelRenderer.field_78796_g = y;
/* 435 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\morphs\GiraffeHeavyModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */