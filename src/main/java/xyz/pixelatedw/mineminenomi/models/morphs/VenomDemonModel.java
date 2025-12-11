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
/*     */ 
/*     */ public class VenomDemonModel<T extends LivingEntity>
/*     */   extends MorphModel<T> implements IHasArm {
/*     */   private final ModelRenderer head;
/*     */   private final ModelRenderer jaw;
/*     */   private final ModelRenderer mouth;
/*     */   private final ModelRenderer rightHorn;
/*     */   private final ModelRenderer rightHorn2;
/*     */   private final ModelRenderer rightHorn3;
/*     */   private final ModelRenderer leftHorn;
/*     */   private final ModelRenderer leftHorn2;
/*     */   private final ModelRenderer leftHorn3;
/*     */   private final ModelRenderer leftHorn3_r1;
/*     */   private final ModelRenderer body;
/*     */   private final ModelRenderer body2;
/*     */   private final ModelRenderer body3;
/*     */   private final ModelRenderer body4;
/*     */   private final ModelRenderer body5;
/*     */   private final ModelRenderer base;
/*     */   private final ModelRenderer base2;
/*     */   private final ModelRenderer base3;
/*     */   private final ModelRenderer shoulders;
/*     */   private final ModelRenderer spine;
/*     */   private final ModelRenderer spineb;
/*     */   private final ModelRenderer neck;
/*     */   private final ModelRenderer spine2;
/*     */   private final ModelRenderer spine2b;
/*     */   private final ModelRenderer rightWing;
/*     */   private final ModelRenderer leftWing;
/*     */   private final ModelRenderer leftArm;
/*     */   private final ModelRenderer leftArm2;
/*     */   private final ModelRenderer leftHand;
/*     */   private final ModelRenderer leftHandFinger1;
/*     */   private final ModelRenderer leftHandFinger1b;
/*     */   private final ModelRenderer leftHandFinger2;
/*     */   private final ModelRenderer leftHandFinger2b;
/*     */   private final ModelRenderer leftHandFinger3;
/*     */   private final ModelRenderer leftHandFinger3b;
/*     */   private final ModelRenderer leftHandFinger4;
/*     */   private final ModelRenderer leftHandFinger4b;
/*     */   private final ModelRenderer leftHandFinger5;
/*     */   private final ModelRenderer leftHandFinger5b;
/*     */   private final ModelRenderer rightArm;
/*     */   private final ModelRenderer rightArm2;
/*     */   private final ModelRenderer rightHand;
/*     */   private final ModelRenderer rightHandFinger1;
/*     */   private final ModelRenderer rightHandFinger1b;
/*     */   private final ModelRenderer rightHandFinger2;
/*     */   private final ModelRenderer rightHandFinger2b;
/*     */   private final ModelRenderer rightHandFinger3;
/*     */   private final ModelRenderer rightHandFinger3b;
/*     */   private final ModelRenderer rightHandFinger4;
/*     */   private final ModelRenderer rightHandFinger4b;
/*     */   private final ModelRenderer rightHandFinger5;
/*     */   private final ModelRenderer rightHandFinger5b;
/*     */   
/*     */   public VenomDemonModel() {
/*  72 */     super(1.0F);
/*  73 */     this.field_78090_t = 256;
/*  74 */     this.field_78089_u = 256;
/*     */     
/*  76 */     this.head = new ModelRenderer((Model)this);
/*  77 */     this.head.func_78793_a(0.5F, -21.0F, -11.5F);
/*  78 */     this.head.func_78784_a(202, 46).func_228303_a_(-3.0F, -3.0F, -6.0F, 5.0F, 4.0F, 5.0F, 0.0F, false);
/*     */     
/*  80 */     this.jaw = new ModelRenderer((Model)this);
/*  81 */     this.jaw.func_78793_a(-3.0F, -3.0F, -6.0F);
/*  82 */     this.head.func_78792_a(this.jaw);
/*  83 */     setRotationAngle(this.jaw, 0.4363F, 0.0F, 0.0F);
/*  84 */     this.jaw.func_78784_a(240, 49).func_228303_a_(0.5F, 7.0F, -2.5F, 4.0F, 2.0F, 4.0F, 0.0F, false);
/*     */     
/*  86 */     this.mouth = new ModelRenderer((Model)this);
/*  87 */     this.mouth.func_78793_a(-3.0F, -3.0F, -6.0F);
/*  88 */     this.head.func_78792_a(this.mouth);
/*  89 */     this.mouth.func_78784_a(223, 49).func_228303_a_(0.5F, 3.9F, 0.6F, 4.0F, 2.0F, 4.0F, 0.0F, false);
/*     */     
/*  91 */     this.rightHorn = new ModelRenderer((Model)this);
/*  92 */     this.rightHorn.func_78793_a(-2.0F, -2.0F, -2.5F);
/*  93 */     this.head.func_78792_a(this.rightHorn);
/*  94 */     setRotationAngle(this.rightHorn, 0.8727F, -0.9599F, -0.1222F);
/*  95 */     this.rightHorn.func_78784_a(230, 0).func_228303_a_(0.0F, 0.0F, 0.0F, 2.0F, 2.0F, 3.0F, 0.0F, false);
/*     */     
/*  97 */     this.rightHorn2 = new ModelRenderer((Model)this);
/*  98 */     this.rightHorn2.func_78793_a(0.0F, 0.0F, 2.8F);
/*  99 */     this.rightHorn.func_78792_a(this.rightHorn2);
/* 100 */     setRotationAngle(this.rightHorn2, -0.4363F, 0.1222F, 0.0F);
/* 101 */     this.rightHorn2.func_78784_a(241, 0).func_228303_a_(0.0F, 0.0F, 0.0F, 2.0F, 2.0F, 3.0F, 0.0F, false);
/*     */     
/* 103 */     this.rightHorn3 = new ModelRenderer((Model)this);
/* 104 */     this.rightHorn3.func_78793_a(0.0F, 0.0F, 0.0F);
/* 105 */     this.rightHorn2.func_78792_a(this.rightHorn3);
/* 106 */     setRotationAngle(this.rightHorn3, -0.2967F, 0.0524F, 0.0F);
/* 107 */     this.rightHorn3.func_78784_a(252, 1).func_228303_a_(0.4F, -2.5F, 1.9F, 1.0F, 3.0F, 1.0F, 0.0F, false);
/*     */     
/* 109 */     this.leftHorn = new ModelRenderer((Model)this);
/* 110 */     this.leftHorn.func_78793_a(1.0F, -2.0F, -2.5F);
/* 111 */     this.head.func_78792_a(this.leftHorn);
/* 112 */     setRotationAngle(this.leftHorn, 0.8727F, 0.9599F, 0.1222F);
/* 113 */     this.leftHorn.func_78784_a(230, 0).func_228303_a_(-2.0F, 0.0F, 0.0F, 2.0F, 2.0F, 3.0F, 0.0F, false);
/*     */     
/* 115 */     this.leftHorn2 = new ModelRenderer((Model)this);
/* 116 */     this.leftHorn2.func_78793_a(0.0F, 0.0F, 2.8F);
/* 117 */     this.leftHorn.func_78792_a(this.leftHorn2);
/* 118 */     setRotationAngle(this.leftHorn2, -0.4363F, 0.1222F, 0.0F);
/* 119 */     this.leftHorn2.func_78784_a(241, 0).func_228303_a_(-2.0F, 0.0F, 0.0F, 2.0F, 2.0F, 3.0F, 0.0F, false);
/*     */     
/* 121 */     this.leftHorn3 = new ModelRenderer((Model)this);
/* 122 */     this.leftHorn3.func_78793_a(-1.3145F, 0.4024F, 2.5599F);
/* 123 */     this.leftHorn2.func_78792_a(this.leftHorn3);
/* 124 */     setRotationAngle(this.leftHorn3, -0.2967F, -0.0524F, 0.0F);
/*     */     
/* 126 */     this.leftHorn3_r1 = new ModelRenderer((Model)this);
/* 127 */     this.leftHorn3_r1.func_78793_a(-0.142F, 0.2276F, -0.077F);
/* 128 */     this.leftHorn3.func_78792_a(this.leftHorn3_r1);
/* 129 */     setRotationAngle(this.leftHorn3_r1, 0.0873F, 0.0F, 0.0F);
/* 130 */     this.leftHorn3_r1.func_78784_a(252, 1).func_228303_a_(0.1012F, -2.3755F, -1.03F, 1.0F, 3.0F, 1.0F, 0.0F, false);
/*     */     
/* 132 */     this.body = new ModelRenderer((Model)this);
/* 133 */     this.body.func_78793_a(-10.0F, -19.5F, 6.5F);
/* 134 */     setRotationAngle(this.body, 0.0524F, 0.0F, 0.0F);
/* 135 */     this.body.func_78784_a(0, 142).func_228303_a_(0.0F, 0.0F, -18.0F, 20.0F, 10.0F, 18.0F, 0.0F, false);
/*     */     
/* 137 */     this.body2 = new ModelRenderer((Model)this);
/* 138 */     this.body2.func_78793_a(0.0F, 9.5F, 0.0F);
/* 139 */     this.body.func_78792_a(this.body2);
/* 140 */     setRotationAngle(this.body2, 0.0175F, 0.0F, 0.0F);
/* 141 */     this.body2.func_78784_a(0, 116).func_228303_a_(0.0F, -0.0349F, -17.4988F, 20.0F, 8.0F, 17.0F, 0.0F, false);
/*     */     
/* 143 */     this.body3 = new ModelRenderer((Model)this);
/* 144 */     this.body3.func_78793_a(1.0F, 7.9477F, -0.7482F);
/* 145 */     this.body2.func_78792_a(this.body3);
/* 146 */     this.body3.func_78784_a(0, 94).func_228303_a_(0.0F, 0.0F, -16.0F, 18.0F, 5.0F, 16.0F, 0.0F, false);
/*     */     
/* 148 */     this.body4 = new ModelRenderer((Model)this);
/* 149 */     this.body4.func_78793_a(1.0F, 4.9651F, -0.4988F);
/* 150 */     this.body3.func_78792_a(this.body4);
/* 151 */     this.body4.func_78784_a(0, 73).func_228303_a_(0.0F, 0.0F, -15.0F, 16.0F, 5.0F, 15.0F, 0.0F, false);
/*     */     
/* 153 */     this.body5 = new ModelRenderer((Model)this);
/* 154 */     this.body5.func_78793_a(1.0F, 4.9651F, -0.4988F);
/* 155 */     this.body4.func_78792_a(this.body5);
/* 156 */     this.body5.func_78784_a(0, 55).func_228303_a_(0.0F, 0.0F, -14.0F, 14.0F, 3.0F, 14.0F, 0.0F, false);
/*     */     
/* 158 */     this.base = new ModelRenderer((Model)this);
/* 159 */     this.base.func_78793_a(1.0F, 2.2344F, -12.6971F);
/* 160 */     this.body5.func_78792_a(this.base);
/* 161 */     setRotationAngle(this.base, -0.0524F, 0.0F, 0.0F);
/* 162 */     this.base.func_78784_a(0, 34).func_228303_a_(0.0F, 0.0F, 0.0F, 12.0F, 8.0F, 12.0F, 0.0F, false);
/*     */     
/* 164 */     this.base2 = new ModelRenderer((Model)this);
/* 165 */     this.base2.func_78793_a(-0.5F, 8.1029F, -0.5438F);
/* 166 */     this.base.func_78792_a(this.base2);
/* 167 */     this.base2.func_78784_a(0, 17).func_228303_a_(0.0F, 0.0F, 0.0F, 13.0F, 3.0F, 13.0F, 0.0F, false);
/*     */     
/* 169 */     this.base3 = new ModelRenderer((Model)this);
/* 170 */     this.base3.func_78793_a(-0.5F, 2.9956F, -0.75F);
/* 171 */     this.base2.func_78792_a(this.base3);
/* 172 */     this.base3.func_78784_a(0, 0).func_228303_a_(0.0F, 0.0044F, 0.25F, 14.0F, 2.0F, 14.0F, 0.0F, false);
/*     */     
/* 174 */     this.shoulders = new ModelRenderer((Model)this);
/* 175 */     this.shoulders.func_78793_a(0.5F, -2.4993F, -0.3738F);
/* 176 */     this.body.func_78792_a(this.shoulders);
/* 177 */     setRotationAngle(this.shoulders, -0.1047F, 0.0F, 0.0F);
/* 178 */     this.shoulders.func_78784_a(0, 171).func_228303_a_(0.0F, 0.0F, -17.0F, 19.0F, 5.0F, 17.0F, 0.0F, false);
/*     */     
/* 180 */     this.spine = new ModelRenderer((Model)this);
/* 181 */     this.spine.func_78793_a(9.0F, 1.1399F, -8.8429F);
/* 182 */     this.shoulders.func_78792_a(this.spine);
/* 183 */     setRotationAngle(this.spine, 1.5708F, 0.0F, 0.0F);
/* 184 */     this.spine.func_78784_a(233, 9).func_228303_a_(0.0F, -7.191F, 1.0063F, 1.0F, 17.0F, 1.0F, 0.0F, false);
/*     */     
/* 186 */     this.spineb = new ModelRenderer((Model)this);
/* 187 */     this.spineb.func_78793_a(0.5F, -8.1102F, 1.1063F);
/* 188 */     this.spine.func_78792_a(this.spineb);
/* 189 */     this.spineb.func_78784_a(243, 7).func_228303_a_(0.0F, 0.9193F, 0.0523F, 0.0F, 17.0F, 3.0F, 0.0F, false);
/*     */     
/* 191 */     this.neck = new ModelRenderer((Model)this);
/* 192 */     this.neck.func_78793_a(7.5F, -0.0021F, -18.8738F);
/* 193 */     this.shoulders.func_78792_a(this.neck);
/* 194 */     this.neck.func_78784_a(189, 49).func_228303_a_(0.0F, 0.0F, 0.0F, 4.0F, 4.0F, 2.0F, 0.0F, false);
/*     */     
/* 196 */     this.spine2 = new ModelRenderer((Model)this);
/* 197 */     this.spine2.func_78793_a(9.5F, -2.7F, -0.5F);
/* 198 */     this.body.func_78792_a(this.spine2);
/* 199 */     setRotationAngle(this.spine2, -0.0087F, 0.0F, 0.0F);
/* 200 */     this.spine2.func_78784_a(238, 7).func_228303_a_(0.0F, 0.0F, 0.0F, 1.0F, 19.0F, 1.0F, 0.0F, false);
/*     */     
/* 202 */     this.spine2b = new ModelRenderer((Model)this);
/* 203 */     this.spine2b.func_78793_a(0.5F, 0.2F, 1.0F);
/* 204 */     this.spine2.func_78792_a(this.spine2b);
/* 205 */     this.spine2b.func_78784_a(250, 6).func_228303_a_(0.0F, 0.0F, 0.0F, 0.0F, 18.0F, 3.0F, 0.0F, false);
/*     */     
/* 207 */     this.rightWing = new ModelRenderer((Model)this);
/* 208 */     this.rightWing.func_78793_a(4.0F, -0.5F, -0.5F);
/* 209 */     this.body.func_78792_a(this.rightWing);
/* 210 */     setRotationAngle(this.rightWing, 0.203F, 0.2261F, 0.5387F);
/* 211 */     this.rightWing.func_78784_a(151, 0).func_228303_a_(-14.0F, 0.0F, 0.0F, 14.0F, 9.0F, 0.0F, 0.0F, false);
/*     */     
/* 213 */     this.leftWing = new ModelRenderer((Model)this);
/* 214 */     this.leftWing.func_78793_a(16.0F, -0.5F, -0.5F);
/* 215 */     this.body.func_78792_a(this.leftWing);
/* 216 */     setRotationAngle(this.leftWing, 0.203F, -0.2261F, -0.5387F);
/* 217 */     this.leftWing.func_78784_a(151, 10).func_228303_a_(0.0F, 0.0F, 0.0F, 14.0F, 9.0F, 0.0F, 0.0F, false);
/*     */     
/* 219 */     this.leftArm = new ModelRenderer((Model)this);
/* 220 */     this.leftArm.func_78793_a(8.0F, -17.0F, 0.0F);
/* 221 */     setRotationAngle(this.leftArm, -0.5236F, -0.5236F, 0.0F);
/* 222 */     this.leftArm.func_78784_a(180, 0).func_228303_a_(0.0F, 0.0F, -6.0F, 6.0F, 15.0F, 6.0F, 0.0F, false);
/*     */     
/* 224 */     this.leftArm2 = new ModelRenderer((Model)this);
/* 225 */     this.leftArm2.func_78793_a(1.2067F, 13.3142F, 0.4276F);
/* 226 */     this.leftArm.func_78792_a(this.leftArm2);
/* 227 */     setRotationAngle(this.leftArm2, -0.3665F, 0.0436F, 0.4363F);
/* 228 */     this.leftArm2.func_78784_a(205, 0).func_228303_a_(-0.8613F, 0.2473F, -6.5225F, 6.0F, 15.0F, 6.0F, 0.0F, false);
/*     */     
/* 230 */     this.leftHand = new ModelRenderer((Model)this);
/* 231 */     this.leftHand.func_78793_a(-0.9984F, 14.6745F, -0.5989F);
/* 232 */     this.leftArm2.func_78792_a(this.leftHand);
/* 233 */     setRotationAngle(this.leftHand, 0.0262F, 0.0F, 0.0611F);
/* 234 */     this.leftHand.func_78784_a(232, 28).func_228303_a_(0.0F, 0.0F, -6.0F, 6.0F, 2.0F, 6.0F, 0.0F, false);
/*     */     
/* 236 */     this.leftHandFinger1 = new ModelRenderer((Model)this);
/* 237 */     this.leftHandFinger1.func_78793_a(1.4422F, 0.743F, -6.0F);
/* 238 */     this.leftHand.func_78792_a(this.leftHandFinger1);
/* 239 */     setRotationAngle(this.leftHandFinger1, 0.8552F, 0.1309F, 0.0087F);
/* 240 */     this.leftHandFinger1.func_78784_a(225, 33).func_228303_a_(-1.0F, 0.0F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 242 */     this.leftHandFinger1b = new ModelRenderer((Model)this);
/* 243 */     this.leftHandFinger1b.func_78793_a(0.0131F, -0.0528F, -1.9653F);
/* 244 */     this.leftHandFinger1.func_78792_a(this.leftHandFinger1b);
/* 245 */     setRotationAngle(this.leftHandFinger1b, 0.3142F, 0.0F, 0.0524F);
/* 246 */     this.leftHandFinger1b.func_78784_a(225, 29).func_228303_a_(-1.0F, 0.0828F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 248 */     this.leftHandFinger2 = new ModelRenderer((Model)this);
/* 249 */     this.leftHandFinger2.func_78793_a(2.9283F, 0.7596F, -6.203F);
/* 250 */     this.leftHand.func_78792_a(this.leftHandFinger2);
/* 251 */     setRotationAngle(this.leftHandFinger2, 0.8552F, 0.0873F, 0.0087F);
/* 252 */     this.leftHandFinger2.func_78784_a(225, 33).func_228303_a_(-1.0F, 0.0F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 254 */     this.leftHandFinger2b = new ModelRenderer((Model)this);
/* 255 */     this.leftHandFinger2b.func_78793_a(0.0131F, -0.0528F, -1.9653F);
/* 256 */     this.leftHandFinger2.func_78792_a(this.leftHandFinger2b);
/* 257 */     setRotationAngle(this.leftHandFinger2b, 0.3142F, 0.0F, 0.0524F);
/* 258 */     this.leftHandFinger2b.func_78784_a(225, 29).func_228303_a_(-1.0F, 0.0828F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 260 */     this.leftHandFinger3 = new ModelRenderer((Model)this);
/* 261 */     this.leftHandFinger3.func_78793_a(4.4405F, 0.9206F, -6.2036F);
/* 262 */     this.leftHand.func_78792_a(this.leftHandFinger3);
/* 263 */     setRotationAngle(this.leftHandFinger3, 0.8552F, -0.0436F, 0.0087F);
/* 264 */     this.leftHandFinger3.func_78784_a(225, 33).func_228303_a_(-1.0F, 0.0F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 266 */     this.leftHandFinger3b = new ModelRenderer((Model)this);
/* 267 */     this.leftHandFinger3b.func_78793_a(0.0131F, -0.0528F, -1.9653F);
/* 268 */     this.leftHandFinger3.func_78792_a(this.leftHandFinger3b);
/* 269 */     setRotationAngle(this.leftHandFinger3b, 0.3142F, 0.0F, 0.0524F);
/* 270 */     this.leftHandFinger3b.func_78784_a(225, 29).func_228303_a_(-1.0F, 0.0828F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 272 */     this.leftHandFinger4 = new ModelRenderer((Model)this);
/* 273 */     this.leftHandFinger4.func_78793_a(5.9266F, 0.9372F, -6.4066F);
/* 274 */     this.leftHand.func_78792_a(this.leftHandFinger4);
/* 275 */     setRotationAngle(this.leftHandFinger4, 0.9425F, -0.1309F, 0.0087F);
/* 276 */     this.leftHandFinger4.func_78784_a(225, 33).func_228303_a_(-1.0F, 0.0F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 278 */     this.leftHandFinger4b = new ModelRenderer((Model)this);
/* 279 */     this.leftHandFinger4b.func_78793_a(0.0131F, -0.0528F, -1.9653F);
/* 280 */     this.leftHandFinger4.func_78792_a(this.leftHandFinger4b);
/* 281 */     setRotationAngle(this.leftHandFinger4b, 0.3142F, 0.0F, 0.0524F);
/* 282 */     this.leftHandFinger4b.func_78784_a(225, 29).func_228303_a_(-1.0F, 0.0828F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 284 */     this.leftHandFinger5 = new ModelRenderer((Model)this);
/* 285 */     this.leftHandFinger5.func_78793_a(0.117F, 0.3919F, -2.7991F);
/* 286 */     this.leftHand.func_78792_a(this.leftHandFinger5);
/* 287 */     setRotationAngle(this.leftHandFinger5, 0.9425F, 1.6581F, -0.2531F);
/* 288 */     this.leftHandFinger5.func_78784_a(225, 33).func_228303_a_(-1.0F, 0.0F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 290 */     this.leftHandFinger5b = new ModelRenderer((Model)this);
/* 291 */     this.leftHandFinger5b.func_78793_a(0.0131F, -0.0528F, -1.9653F);
/* 292 */     this.leftHandFinger5.func_78792_a(this.leftHandFinger5b);
/* 293 */     setRotationAngle(this.leftHandFinger5b, 0.3142F, 0.0F, 0.0524F);
/* 294 */     this.leftHandFinger5b.func_78784_a(225, 29).func_228303_a_(-1.0F, 0.0828F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 296 */     this.rightArm = new ModelRenderer((Model)this);
/* 297 */     this.rightArm.func_78793_a(-13.0F, -17.0F, 2.75F);
/* 298 */     setRotationAngle(this.rightArm, -0.5236F, 0.5236F, 0.0873F);
/* 299 */     this.rightArm.func_78784_a(180, 0).func_228303_a_(0.0F, 0.0F, -6.0F, 6.0F, 15.0F, 6.0F, 0.0F, false);
/*     */     
/* 301 */     this.rightArm2 = new ModelRenderer((Model)this);
/* 302 */     this.rightArm2.func_78793_a(0.7753F, 13.4765F, 0.2337F);
/* 303 */     this.rightArm.func_78792_a(this.rightArm2);
/* 304 */     setRotationAngle(this.rightArm2, -0.3665F, 0.0436F, -0.5236F);
/* 305 */     this.rightArm2.func_78784_a(205, 0).func_228303_a_(-1.1789F, 2.1831F, -5.4493F, 6.0F, 15.0F, 6.0F, 0.0F, false);
/*     */     
/* 307 */     this.rightHand = new ModelRenderer((Model)this);
/* 308 */     this.rightHand.func_78793_a(-0.9984F, 14.6745F, -0.5989F);
/* 309 */     this.rightArm2.func_78792_a(this.rightHand);
/* 310 */     setRotationAngle(this.rightHand, 0.0262F, 0.0F, 0.0611F);
/* 311 */     this.rightHand.func_78784_a(232, 28).func_228303_a_(-0.1988F, 1.9791F, -4.9783F, 6.0F, 2.0F, 6.0F, 0.0F, false);
/*     */     
/* 313 */     this.rightHandFinger1 = new ModelRenderer((Model)this);
/* 314 */     this.rightHandFinger1.func_78793_a(1.4422F, 0.743F, -6.0F);
/* 315 */     this.rightHand.func_78792_a(this.rightHandFinger1);
/* 316 */     setRotationAngle(this.rightHandFinger1, 0.8552F, 0.1309F, 0.0087F);
/* 317 */     this.rightHandFinger1.func_78784_a(225, 33).func_228303_a_(-1.3133F, 2.0461F, -2.8459F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 319 */     this.rightHandFinger1b = new ModelRenderer((Model)this);
/* 320 */     this.rightHandFinger1b.func_78793_a(0.0131F, -0.0528F, -1.9653F);
/* 321 */     this.rightHandFinger1.func_78792_a(this.rightHandFinger1b);
/* 322 */     setRotationAngle(this.rightHandFinger1b, 0.3142F, 0.0F, 0.0524F);
/* 323 */     this.rightHandFinger1b.func_78784_a(225, 29).func_228303_a_(-1.2058F, 1.7802F, -3.4409F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 325 */     this.rightHandFinger2 = new ModelRenderer((Model)this);
/* 326 */     this.rightHandFinger2.func_78793_a(2.9283F, 0.7596F, -6.203F);
/* 327 */     this.rightHand.func_78792_a(this.rightHandFinger2);
/* 328 */     setRotationAngle(this.rightHandFinger2, 0.8552F, 0.0873F, 0.0087F);
/* 329 */     this.rightHandFinger2.func_78784_a(225, 33).func_228303_a_(-1.2699F, 2.0557F, -2.8375F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 331 */     this.rightHandFinger2b = new ModelRenderer((Model)this);
/* 332 */     this.rightHandFinger2b.func_78793_a(0.0131F, -0.0528F, -1.9653F);
/* 333 */     this.rightHandFinger2.func_78792_a(this.rightHandFinger2b);
/* 334 */     setRotationAngle(this.rightHandFinger2b, 0.3142F, 0.0F, 0.0524F);
/* 335 */     this.rightHandFinger2b.func_78784_a(225, 29).func_228303_a_(-1.1619F, 1.7898F, -3.4352F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 337 */     this.rightHandFinger3 = new ModelRenderer((Model)this);
/* 338 */     this.rightHandFinger3.func_78793_a(4.4405F, 0.9206F, -6.2036F);
/* 339 */     this.rightHand.func_78792_a(this.rightHandFinger3);
/* 340 */     setRotationAngle(this.rightHandFinger3, 0.8552F, -0.0436F, 0.0087F);
/* 341 */     this.rightHandFinger3.func_78784_a(225, 33).func_228303_a_(-1.1368F, 2.0758F, -2.82F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 343 */     this.rightHandFinger3b = new ModelRenderer((Model)this);
/* 344 */     this.rightHandFinger3b.func_78793_a(0.0131F, -0.0528F, -1.9653F);
/* 345 */     this.rightHandFinger3.func_78792_a(this.rightHandFinger3b);
/* 346 */     setRotationAngle(this.rightHandFinger3b, 0.3142F, 0.0F, 0.0524F);
/* 347 */     this.rightHandFinger3b.func_78784_a(225, 29).func_228303_a_(-1.028F, 1.8077F, -3.4227F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 349 */     this.rightHandFinger4 = new ModelRenderer((Model)this);
/* 350 */     this.rightHandFinger4.func_78793_a(5.9266F, 0.9372F, -6.4066F);
/* 351 */     this.rightHand.func_78792_a(this.rightHandFinger4);
/* 352 */     setRotationAngle(this.rightHandFinger4, 0.9425F, -0.1309F, 0.0087F);
/* 353 */     this.rightHandFinger4.func_78784_a(225, 33).func_228303_a_(-1.0466F, 2.0029F, -2.9931F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 355 */     this.rightHandFinger4b = new ModelRenderer((Model)this);
/* 356 */     this.rightHandFinger4b.func_78793_a(0.0131F, -0.0528F, -1.9653F);
/* 357 */     this.rightHandFinger4.func_78792_a(this.rightHandFinger4b);
/* 358 */     setRotationAngle(this.rightHandFinger4b, 0.3142F, 0.0F, 0.0524F);
/* 359 */     this.rightHandFinger4b.func_78784_a(225, 29).func_228303_a_(-0.9417F, 1.6805F, -3.5633F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 361 */     this.rightHandFinger5 = new ModelRenderer((Model)this);
/* 362 */     this.rightHandFinger5.func_78793_a(4.642F, 1.0653F, -2.5333F);
/* 363 */     this.rightHand.func_78792_a(this.rightHandFinger5);
/* 364 */     setRotationAngle(this.rightHandFinger5, 2.0769F, 1.6581F, -0.2531F);
/* 365 */     this.rightHandFinger5.func_78784_a(225, 33).func_228303_a_(-1.9578F, -1.5821F, -3.2568F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 367 */     this.rightHandFinger5b = new ModelRenderer((Model)this);
/* 368 */     this.rightHandFinger5b.func_78793_a(0.4204F, 0.3134F, -2.0771F);
/* 369 */     this.rightHandFinger5.func_78792_a(this.rightHandFinger5b);
/* 370 */     setRotationAngle(this.rightHandFinger5b, -0.3142F, 0.0F, 0.0524F);
/* 371 */     this.rightHandFinger5b.func_78784_a(225, 29).func_228303_a_(-2.4653F, -1.3458F, -3.4515F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 373 */     this.field_78115_e = this.body;
/* 374 */     this.field_78116_c = this.head;
/* 375 */     this.field_178723_h = this.rightArm;
/* 376 */     this.field_178724_i = this.leftArm;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 382 */     this.head.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 383 */     this.body.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 384 */     this.leftArm.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 385 */     this.rightArm.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 392 */     boolean flag = (entity.func_184599_cB() > 4);
/* 393 */     boolean flag1 = entity.func_213314_bj();
/* 394 */     this.head.field_78796_g = netHeadYaw * 0.017453292F;
/* 395 */     if (flag) {
/* 396 */       this.head.field_78795_f = -0.7853982F;
/* 397 */     } else if (this.field_205061_a > 0.0F) {
/*     */       
/* 399 */       if (flag1) {
/* 400 */         this.head.field_78795_f = func_205060_a(this.head.field_78795_f, -0.7853982F, this.field_205061_a);
/*     */       } else {
/* 402 */         this.head.field_78795_f = func_205060_a(this.head.field_78795_f, headPitch * 0.017453292F, this.field_205061_a);
/*     */       } 
/*     */     } else {
/*     */       
/* 406 */       this.head.field_78795_f = headPitch * 0.017453292F;
/* 407 */       if (this.head.field_78795_f > 0.6D) {
/* 408 */         this.head.field_78795_f = 0.6F;
/*     */       }
/*     */     } 
/*     */     
/* 412 */     this.field_217112_c = ((LivingEntity)entity).field_70733_aJ;
/* 413 */     if (this.field_217112_c > 0.0F) {
/*     */       
/* 415 */       this.body.field_78796_g = MathHelper.func_76126_a(MathHelper.func_76129_c(this.field_217112_c) * 6.2831855F) * 0.02F;
/* 416 */       this.rightArm.field_78798_e = MathHelper.func_76126_a(this.body.field_78796_g) * 12.0F;
/* 417 */       this.rightArm.field_78800_c = -MathHelper.func_76134_b(this.body.field_78796_g) * 9.0F;
/* 418 */       this.rightArm.field_78796_g += this.body.field_78796_g;
/* 419 */       this.leftArm.field_78798_e = -MathHelper.func_76126_a(this.body.field_78796_g) * 5.0F;
/* 420 */       this.leftArm.field_78796_g -= this.body.field_78796_g;
/* 421 */       this.leftArm.field_78795_f -= this.body.field_78796_g;
/* 422 */       float f1 = 1.0F - this.field_217112_c;
/* 423 */       f1 *= f1;
/* 424 */       f1 *= f1;
/* 425 */       f1 = 1.0F - f1;
/* 426 */       float f2 = MathHelper.func_76126_a(f1 * 3.1415927F);
/* 427 */       float f3 = MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * -(this.head.field_78795_f - 0.7F) * 0.75F;
/* 428 */       this.rightArm.field_78795_f = (float)(this.rightArm.field_78795_f - f2 * 1.2D + f3);
/* 429 */       this.rightArm.field_78796_g += this.body.field_78796_g * 2.0F;
/* 430 */       this.rightArm.field_78808_h += MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * -0.4F;
/*     */     } 
/*     */     
/* 433 */     float f = 1.0F;
/* 434 */     this.rightWing.field_78796_g = MathHelper.func_76134_b(limbSwing * 0.4F + 3.1415927F) * 0.4F * limbSwingAmount * 0.2F / f;
/* 435 */     this.rightWing.field_78808_h += MathHelper.func_76134_b(limbSwing * 0.4F + 3.1415927F) * 0.4F * limbSwingAmount * 0.2F / f;
/* 436 */     this.leftWing.field_78796_g = MathHelper.func_76134_b(limbSwing * 0.4F) * 0.4F * limbSwingAmount * 0.2F / f;
/* 437 */     this.leftWing.field_78808_h += MathHelper.func_76134_b(limbSwing * 0.4F) * 0.4F * limbSwingAmount * 0.2F / f;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225599_a_(HandSide side, MatrixStack matrixStack) {
/* 443 */     super.func_225599_a_(side, matrixStack);
/* 444 */     matrixStack.func_227861_a_(0.7D, 1.3D, -0.4D);
/* 445 */     matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(-20.0F));
/* 446 */     matrixStack.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(-30.0F));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
/* 452 */     if (side == HandSide.RIGHT) {
/*     */       
/* 454 */       matrixStack.func_227861_a_(-1.4D, -0.9D, 0.2D);
/* 455 */       matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(70.0F));
/* 456 */       matrixStack.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(40.0F));
/* 457 */       this.rightArm2.func_228309_a_(matrixStack, vertex, packedLight, OverlayTexture.field_229196_a_, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */     }
/*     */     else {
/*     */       
/* 461 */       matrixStack.func_227861_a_(0.7D, -0.6D, -0.5D);
/* 462 */       matrixStack.func_227863_a_(Vector3f.field_229183_f_.func_229187_a_(30.0F));
/* 463 */       matrixStack.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(30.0F));
/* 464 */       matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(-90.0F));
/* 465 */       this.leftArm2.func_228309_a_(matrixStack, vertex, packedLight, OverlayTexture.field_229196_a_, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 476 */     modelRenderer.field_78795_f = x;
/* 477 */     modelRenderer.field_78796_g = y;
/* 478 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\morphs\VenomDemonModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */