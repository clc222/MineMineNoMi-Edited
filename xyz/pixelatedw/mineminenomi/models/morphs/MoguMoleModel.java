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
/*     */ public class MoguMoleModel<T extends LivingEntity>
/*     */   extends MorphModel<T> implements IHasArm {
/*     */   private final ModelRenderer head;
/*     */   private final ModelRenderer mouth3;
/*     */   private final ModelRenderer mouth4;
/*     */   private final ModelRenderer mouth2;
/*     */   private final ModelRenderer mouth1;
/*     */   private final ModelRenderer body;
/*     */   private final ModelRenderer body2;
/*     */   private final ModelRenderer body3;
/*     */   private final ModelRenderer rightArm;
/*     */   private final ModelRenderer rightArm2;
/*     */   private final ModelRenderer rightHand;
/*     */   private final ModelRenderer rightClaw1;
/*     */   private final ModelRenderer rightClaw1b;
/*     */   private final ModelRenderer rightClaw1c;
/*     */   private final ModelRenderer rightClaw2;
/*     */   private final ModelRenderer rightClaw2b;
/*     */   private final ModelRenderer rightClaw2c;
/*     */   private final ModelRenderer rightClaw3;
/*     */   private final ModelRenderer rightClaw3b;
/*     */   private final ModelRenderer rightClaw3c;
/*     */   private final ModelRenderer rightClaw4;
/*     */   private final ModelRenderer rightClaw4_1;
/*     */   private final ModelRenderer leftArm;
/*     */   private final ModelRenderer leftArm2;
/*     */   private final ModelRenderer leftHand;
/*     */   private final ModelRenderer leftClaw1;
/*     */   private final ModelRenderer leftClaw1b;
/*     */   private final ModelRenderer leftClaw1c;
/*     */   private final ModelRenderer leftClaw2;
/*     */   private final ModelRenderer leftClaw2b;
/*     */   private final ModelRenderer leftClaw2c;
/*     */   private final ModelRenderer leftClaw3;
/*     */   private final ModelRenderer leftClaw3b;
/*     */   private final ModelRenderer leftClaw3c;
/*     */   private final ModelRenderer leftClaw4;
/*     */   private final ModelRenderer leftClaw4b;
/*     */   private final ModelRenderer leftLeg;
/*     */   private final ModelRenderer leftFoot;
/*     */   private final ModelRenderer leftFootClaw1;
/*     */   private final ModelRenderer leftFootClaw1_r1;
/*     */   private final ModelRenderer leftFootClaw2;
/*     */   private final ModelRenderer leftFootClaw3;
/*     */   private final ModelRenderer leftFootClaw4;
/*     */   private final ModelRenderer rightLeg;
/*     */   private final ModelRenderer rightFoot2;
/*     */   private final ModelRenderer rightFootClaw1;
/*     */   private final ModelRenderer rightFootClaw1_r1;
/*     */   private final ModelRenderer rightFootClaw2;
/*     */   private final ModelRenderer rightFootClaw3;
/*     */   private final ModelRenderer rightFootClaw4;
/*     */   
/*     */   public MoguMoleModel() {
/*  71 */     super(1.0F);
/*  72 */     this.field_78090_t = 128;
/*  73 */     this.field_78089_u = 64;
/*     */     
/*  75 */     this.head = new ModelRenderer((Model)this);
/*  76 */     this.head.func_78793_a(0.0F, 0.0F, 0.0F);
/*  77 */     this.head.func_78784_a(0, 0).func_228303_a_(-3.0F, -5.0F, -3.0F, 6.0F, 5.0F, 6.0F, 0.0F, false);
/*     */     
/*  79 */     this.mouth3 = new ModelRenderer((Model)this);
/*  80 */     this.mouth3.func_78793_a(0.0F, -2.0F, -2.7F);
/*  81 */     this.head.func_78792_a(this.mouth3);
/*  82 */     setRotationAngle(this.mouth3, 0.1745F, 0.0F, 0.0F);
/*  83 */     this.mouth3.func_78784_a(36, 0).func_228303_a_(-1.0F, -1.0F, -3.0F, 2.0F, 1.0F, 3.0F, 0.0F, false);
/*     */     
/*  85 */     this.mouth4 = new ModelRenderer((Model)this);
/*  86 */     this.mouth4.func_78793_a(0.0F, 1.0F, 0.0F);
/*  87 */     this.mouth3.func_78792_a(this.mouth4);
/*  88 */     setRotationAngle(this.mouth4, -0.2443F, 0.0F, 0.0F);
/*  89 */     this.mouth4.func_78784_a(36, 5).func_228303_a_(-1.0F, -1.0F, -3.0F, 2.0F, 1.0F, 3.0F, -0.01F, false);
/*     */     
/*  91 */     this.mouth2 = new ModelRenderer((Model)this);
/*  92 */     this.mouth2.func_78793_a(1.0F, 0.0F, 0.2F);
/*  93 */     this.mouth3.func_78792_a(this.mouth2);
/*  94 */     setRotationAngle(this.mouth2, 0.0F, 0.3491F, 0.0F);
/*  95 */     this.mouth2.func_78784_a(25, 5).func_228303_a_(-1.0F, -1.0F, -3.0F, 2.0F, 1.0F, 3.0F, -0.01F, false);
/*     */     
/*  97 */     this.mouth1 = new ModelRenderer((Model)this);
/*  98 */     this.mouth1.func_78793_a(-1.0F, 0.0F, 0.5F);
/*  99 */     this.mouth3.func_78792_a(this.mouth1);
/* 100 */     setRotationAngle(this.mouth1, 0.0F, -0.3491F, 0.0F);
/* 101 */     this.mouth1.func_78784_a(25, 0).func_228303_a_(-1.0F, -1.0F, -3.0F, 2.0F, 1.0F, 3.0F, -0.01F, false);
/*     */     
/* 103 */     this.body = new ModelRenderer((Model)this);
/* 104 */     this.body.func_78793_a(0.0F, 0.0F, 0.0F);
/* 105 */     this.body.func_78784_a(17, 12).func_228303_a_(-5.5F, 0.0F, -3.0F, 11.0F, 17.0F, 6.0F, 0.0F, false);
/*     */     
/* 107 */     this.body2 = new ModelRenderer((Model)this);
/* 108 */     this.body2.func_78793_a(0.0F, 0.5F, -0.5F);
/* 109 */     this.body.func_78792_a(this.body2);
/* 110 */     this.body2.func_78784_a(17, 36).func_228303_a_(-5.0F, 0.0F, -3.0F, 10.0F, 16.0F, 7.0F, 0.0F, false);
/*     */     
/* 112 */     this.body3 = new ModelRenderer((Model)this);
/* 113 */     this.body3.func_78793_a(0.0F, 9.0F, -3.0F);
/* 114 */     this.body2.func_78792_a(this.body3);
/* 115 */     this.body3.func_78784_a(52, 12).func_228303_a_(-4.5F, -8.5F, -1.0F, 9.0F, 15.0F, 1.0F, 0.0F, false);
/*     */     
/* 117 */     this.rightArm = new ModelRenderer((Model)this);
/* 118 */     this.rightArm.func_78793_a(-5.4F, 2.0F, 0.0F);
/* 119 */     setRotationAngle(this.rightArm, 0.0F, 0.0F, 0.0698F);
/* 120 */     this.rightArm.func_78784_a(73, 12).func_228303_a_(-4.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);
/*     */     
/* 122 */     this.rightArm2 = new ModelRenderer((Model)this);
/* 123 */     this.rightArm2.func_78793_a(-2.0F, 5.6F, 0.0F);
/* 124 */     this.rightArm.func_78792_a(this.rightArm2);
/* 125 */     setRotationAngle(this.rightArm2, 0.0F, 0.0F, -0.0698F);
/* 126 */     this.rightArm2.func_78784_a(73, 25).func_228303_a_(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, 0.0F, false);
/*     */     
/* 128 */     this.rightHand = new ModelRenderer((Model)this);
/* 129 */     this.rightHand.func_78793_a(-0.2F, 6.8F, 0.0F);
/* 130 */     this.rightArm2.func_78792_a(this.rightHand);
/* 131 */     setRotationAngle(this.rightHand, 0.0F, 0.0F, -0.0873F);
/* 132 */     this.rightHand.func_78784_a(73, 37).func_228303_a_(-1.0F, 0.0F, -2.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);
/*     */     
/* 134 */     this.rightClaw1 = new ModelRenderer((Model)this);
/* 135 */     this.rightClaw1.func_78793_a(-6.6273F, -1.2306F, 1.5F);
/* 136 */     this.rightHand.func_78792_a(this.rightClaw1);
/* 137 */     setRotationAngle(this.rightClaw1, 0.0F, 0.0F, 0.4363F);
/* 138 */     this.rightClaw1.func_78784_a(73, 44).func_228303_a_(5.7219F, -0.8395F, -0.5F, 2.0F, 3.0F, 1.0F, 0.0F, false);
/*     */     
/* 140 */     this.rightClaw1b = new ModelRenderer((Model)this);
/* 141 */     this.rightClaw1b.func_78793_a(0.0824F, 2.8796F, 0.0F);
/* 142 */     this.rightClaw1.func_78792_a(this.rightClaw1b);
/* 143 */     setRotationAngle(this.rightClaw1b, 0.0F, 0.0F, -0.4363F);
/* 144 */     this.rightClaw1b.func_78784_a(73, 49).func_228303_a_(5.4385F, 1.6334F, -0.5F, 2.0F, 3.0F, 1.0F, 0.01F, false);
/*     */     
/* 146 */     this.rightClaw1c = new ModelRenderer((Model)this);
/* 147 */     this.rightClaw1c.func_78793_a(-0.2194F, 2.7529F, 0.5F);
/* 148 */     this.rightClaw1b.func_78792_a(this.rightClaw1c);
/* 149 */     setRotationAngle(this.rightClaw1c, 0.0F, 0.0F, -0.9599F);
/* 150 */     this.rightClaw1c.func_78784_a(73, 49).func_228303_a_(1.6987F, 5.6297F, -1.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
/*     */     
/* 152 */     this.rightClaw2 = new ModelRenderer((Model)this);
/* 153 */     this.rightClaw2.func_78793_a(-6.6273F, -1.2306F, 0.25F);
/* 154 */     this.rightHand.func_78792_a(this.rightClaw2);
/* 155 */     setRotationAngle(this.rightClaw2, 0.0F, 0.0F, 0.4363F);
/* 156 */     this.rightClaw2.func_78784_a(73, 44).func_228303_a_(5.7219F, -0.8395F, -0.5F, 2.0F, 3.0F, 1.0F, 0.0F, false);
/*     */     
/* 158 */     this.rightClaw2b = new ModelRenderer((Model)this);
/* 159 */     this.rightClaw2b.func_78793_a(0.0824F, 2.8796F, 0.0F);
/* 160 */     this.rightClaw2.func_78792_a(this.rightClaw2b);
/* 161 */     setRotationAngle(this.rightClaw2b, 0.0F, 0.0F, -0.4363F);
/* 162 */     this.rightClaw2b.func_78784_a(73, 49).func_228303_a_(5.4385F, 1.6334F, -0.5F, 2.0F, 3.0F, 1.0F, 0.01F, false);
/*     */     
/* 164 */     this.rightClaw2c = new ModelRenderer((Model)this);
/* 165 */     this.rightClaw2c.func_78793_a(-0.2194F, 2.7529F, 0.5F);
/* 166 */     this.rightClaw2b.func_78792_a(this.rightClaw2c);
/* 167 */     setRotationAngle(this.rightClaw2c, 0.0F, 0.0F, -0.9599F);
/* 168 */     this.rightClaw2c.func_78784_a(73, 49).func_228303_a_(1.6987F, 5.6297F, -1.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
/*     */     
/* 170 */     this.rightClaw3 = new ModelRenderer((Model)this);
/* 171 */     this.rightClaw3.func_78793_a(-6.6273F, -1.2306F, -1.0F);
/* 172 */     this.rightHand.func_78792_a(this.rightClaw3);
/* 173 */     setRotationAngle(this.rightClaw3, 0.0F, 0.0F, 0.4363F);
/* 174 */     this.rightClaw3.func_78784_a(73, 44).func_228303_a_(5.7219F, -0.8395F, -0.5F, 2.0F, 3.0F, 1.0F, 0.0F, false);
/*     */     
/* 176 */     this.rightClaw3b = new ModelRenderer((Model)this);
/* 177 */     this.rightClaw3b.func_78793_a(0.0824F, 2.8796F, 0.0F);
/* 178 */     this.rightClaw3.func_78792_a(this.rightClaw3b);
/* 179 */     setRotationAngle(this.rightClaw3b, 0.0F, 0.0F, -0.4363F);
/* 180 */     this.rightClaw3b.func_78784_a(73, 49).func_228303_a_(5.4385F, 1.6334F, -0.5F, 2.0F, 3.0F, 1.0F, 0.01F, false);
/*     */     
/* 182 */     this.rightClaw3c = new ModelRenderer((Model)this);
/* 183 */     this.rightClaw3c.func_78793_a(-0.2194F, 2.7529F, 0.5F);
/* 184 */     this.rightClaw3b.func_78792_a(this.rightClaw3c);
/* 185 */     setRotationAngle(this.rightClaw3c, 0.0F, 0.0F, -0.9599F);
/* 186 */     this.rightClaw3c.func_78784_a(73, 49).func_228303_a_(1.6987F, 5.6297F, -1.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
/*     */     
/* 188 */     this.rightClaw4 = new ModelRenderer((Model)this);
/* 189 */     this.rightClaw4.func_78793_a(9.4363F, -7.7041F, -1.5F);
/* 190 */     this.rightHand.func_78792_a(this.rightClaw4);
/* 191 */     setRotationAngle(this.rightClaw4, -0.6981F, 0.0F, 0.0F);
/* 192 */     this.rightClaw4.func_78784_a(80, 44).func_228303_a_(-9.5176F, 6.7226F, 5.1409F, 1.0F, 2.0F, 1.0F, 0.0F, true);
/*     */     
/* 194 */     this.rightClaw4_1 = new ModelRenderer((Model)this);
/* 195 */     this.rightClaw4_1.func_78793_a(0.4358F, 1.9296F, -0.7285F);
/* 196 */     this.rightClaw4.func_78792_a(this.rightClaw4_1);
/* 197 */     setRotationAngle(this.rightClaw4_1, 0.7741F, 0.0F, 0.0F);
/* 198 */     this.rightClaw4_1.func_78784_a(80, 48).func_228303_a_(-9.9501F, 8.8318F, -0.5371F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/* 200 */     this.leftArm = new ModelRenderer((Model)this);
/* 201 */     this.leftArm.func_78793_a(5.4F, 2.0F, 0.0F);
/* 202 */     setRotationAngle(this.leftArm, 0.0F, 0.0F, -0.0698F);
/* 203 */     this.leftArm.func_78784_a(73, 12).func_228303_a_(0.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);
/*     */     
/* 205 */     this.leftArm2 = new ModelRenderer((Model)this);
/* 206 */     this.leftArm2.func_78793_a(2.0F, 5.6F, 0.0F);
/* 207 */     this.leftArm.func_78792_a(this.leftArm2);
/* 208 */     setRotationAngle(this.leftArm2, 0.0F, 0.0F, 0.0698F);
/* 209 */     this.leftArm2.func_78784_a(73, 25).func_228303_a_(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, 0.0F, false);
/*     */     
/* 211 */     this.leftHand = new ModelRenderer((Model)this);
/* 212 */     this.leftHand.func_78793_a(0.0F, 6.8F, 0.0F);
/* 213 */     this.leftArm2.func_78792_a(this.leftHand);
/* 214 */     setRotationAngle(this.leftHand, 0.0F, 0.0F, 0.0873F);
/* 215 */     this.leftHand.func_78784_a(73, 37).func_228303_a_(-1.0F, 0.0F, -2.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);
/*     */     
/* 217 */     this.leftClaw1 = new ModelRenderer((Model)this);
/* 218 */     this.leftClaw1.func_78793_a(10.0391F, -7.4945F, 1.5F);
/* 219 */     this.leftHand.func_78792_a(this.leftClaw1);
/* 220 */     setRotationAngle(this.leftClaw1, 0.0F, 0.0F, -0.4363F);
/* 221 */     this.leftClaw1.func_78784_a(73, 44).func_228303_a_(-13.4283F, 3.3539F, -0.5F, 2.0F, 3.0F, 1.0F, 0.0F, false);
/*     */     
/* 223 */     this.leftClaw1b = new ModelRenderer((Model)this);
/* 224 */     this.leftClaw1b.func_78793_a(-0.5F, 2.95F, 0.0F);
/* 225 */     this.leftClaw1.func_78792_a(this.leftClaw1b);
/* 226 */     setRotationAngle(this.leftClaw1b, 0.0F, 0.0F, 0.4363F);
/* 227 */     this.leftClaw1b.func_78784_a(73, 49).func_228303_a_(-10.5176F, 7.7757F, -0.5F, 2.0F, 3.0F, 1.0F, 0.01F, false);
/*     */     
/* 229 */     this.leftClaw1c = new ModelRenderer((Model)this);
/* 230 */     this.leftClaw1c.func_78793_a(-1.2156F, 2.6658F, 0.5F);
/* 231 */     this.leftClaw1b.func_78792_a(this.leftClaw1c);
/* 232 */     setRotationAngle(this.leftClaw1c, 0.0F, 0.0F, 0.9599F);
/* 233 */     this.leftClaw1c.func_78784_a(73, 49).func_228303_a_(1.4797F, 10.6895F, -1.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
/*     */     
/* 235 */     this.leftClaw2 = new ModelRenderer((Model)this);
/* 236 */     this.leftClaw2.func_78793_a(10.0391F, -7.4945F, 0.25F);
/* 237 */     this.leftHand.func_78792_a(this.leftClaw2);
/* 238 */     setRotationAngle(this.leftClaw2, 0.0F, 0.0F, -0.4363F);
/* 239 */     this.leftClaw2.func_78784_a(73, 44).func_228303_a_(-13.4283F, 3.3539F, -0.5F, 2.0F, 3.0F, 1.0F, 0.0F, false);
/*     */     
/* 241 */     this.leftClaw2b = new ModelRenderer((Model)this);
/* 242 */     this.leftClaw2b.func_78793_a(-0.5F, 2.95F, 0.0F);
/* 243 */     this.leftClaw2.func_78792_a(this.leftClaw2b);
/* 244 */     setRotationAngle(this.leftClaw2b, 0.0F, 0.0F, 0.4363F);
/* 245 */     this.leftClaw2b.func_78784_a(73, 49).func_228303_a_(-10.5176F, 7.7757F, -0.5F, 2.0F, 3.0F, 1.0F, 0.01F, false);
/*     */     
/* 247 */     this.leftClaw2c = new ModelRenderer((Model)this);
/* 248 */     this.leftClaw2c.func_78793_a(-1.2156F, 2.6658F, 0.5F);
/* 249 */     this.leftClaw2b.func_78792_a(this.leftClaw2c);
/* 250 */     setRotationAngle(this.leftClaw2c, 0.0F, 0.0F, 0.9599F);
/* 251 */     this.leftClaw2c.func_78784_a(73, 49).func_228303_a_(1.4797F, 10.6895F, -1.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
/*     */     
/* 253 */     this.leftClaw3 = new ModelRenderer((Model)this);
/* 254 */     this.leftClaw3.func_78793_a(10.0391F, -7.4945F, -1.0F);
/* 255 */     this.leftHand.func_78792_a(this.leftClaw3);
/* 256 */     setRotationAngle(this.leftClaw3, 0.0F, 0.0F, -0.4363F);
/* 257 */     this.leftClaw3.func_78784_a(73, 44).func_228303_a_(-13.4283F, 3.3539F, -0.5F, 2.0F, 3.0F, 1.0F, 0.0F, false);
/*     */     
/* 259 */     this.leftClaw3b = new ModelRenderer((Model)this);
/* 260 */     this.leftClaw3b.func_78793_a(-0.5F, 2.95F, 0.0F);
/* 261 */     this.leftClaw3.func_78792_a(this.leftClaw3b);
/* 262 */     setRotationAngle(this.leftClaw3b, 0.0F, 0.0F, 0.4363F);
/* 263 */     this.leftClaw3b.func_78784_a(73, 49).func_228303_a_(-10.5176F, 7.7757F, -0.5F, 2.0F, 3.0F, 1.0F, 0.01F, false);
/*     */     
/* 265 */     this.leftClaw3c = new ModelRenderer((Model)this);
/* 266 */     this.leftClaw3c.func_78793_a(-1.2156F, 2.6658F, 0.5F);
/* 267 */     this.leftClaw3b.func_78792_a(this.leftClaw3c);
/* 268 */     setRotationAngle(this.leftClaw3c, 0.0F, 0.0F, 0.9599F);
/* 269 */     this.leftClaw3c.func_78784_a(73, 49).func_228303_a_(1.4797F, 10.6895F, -1.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
/*     */     
/* 271 */     this.leftClaw4 = new ModelRenderer((Model)this);
/* 272 */     this.leftClaw4.func_78793_a(8.492F, -7.7291F, -1.5F);
/* 273 */     this.leftHand.func_78792_a(this.leftClaw4);
/* 274 */     setRotationAngle(this.leftClaw4, -0.6981F, 0.0F, 0.0F);
/* 275 */     this.leftClaw4.func_78784_a(80, 44).func_228303_a_(-9.5176F, 6.7226F, 5.1409F, 1.0F, 2.0F, 1.0F, 0.0F, true);
/*     */     
/* 277 */     this.leftClaw4b = new ModelRenderer((Model)this);
/* 278 */     this.leftClaw4b.func_78793_a(0.4358F, 1.9296F, -0.7285F);
/* 279 */     this.leftClaw4.func_78792_a(this.leftClaw4b);
/* 280 */     setRotationAngle(this.leftClaw4b, 0.7741F, 0.0F, 0.0F);
/* 281 */     this.leftClaw4b.func_78784_a(80, 48).func_228303_a_(-9.9501F, 8.8318F, -0.5371F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/* 283 */     this.leftLeg = new ModelRenderer((Model)this);
/* 284 */     this.leftLeg.func_78793_a(3.0F, 17.0F, 0.0F);
/* 285 */     this.leftLeg.func_78784_a(0, 12).func_228303_a_(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, 0.0F, false);
/*     */     
/* 287 */     this.leftFoot = new ModelRenderer((Model)this);
/* 288 */     this.leftFoot.func_78793_a(0.0F, 5.5F, -1.9F);
/* 289 */     this.leftLeg.func_78792_a(this.leftFoot);
/* 290 */     setRotationAngle(this.leftFoot, 0.7854F, 0.0F, 0.0F);
/* 291 */     this.leftFoot.func_78784_a(0, 24).func_228303_a_(-2.0F, 0.0F, -1.0F, 4.0F, 1.0F, 1.0F, -0.01F, false);
/*     */     
/* 293 */     this.leftFootClaw1 = new ModelRenderer((Model)this);
/* 294 */     this.leftFootClaw1.func_78793_a(1.0F, 0.5768F, -0.2232F);
/* 295 */     this.leftFoot.func_78792_a(this.leftFootClaw1);
/* 296 */     setRotationAngle(this.leftFootClaw1, -0.7418F, -0.1745F, 0.1745F);
/*     */ 
/*     */     
/* 299 */     this.leftFootClaw1_r1 = new ModelRenderer((Model)this);
/* 300 */     this.leftFootClaw1_r1.func_78793_a(-4.5F, 1.1F, 2.3F);
/* 301 */     this.leftFootClaw1.func_78792_a(this.leftFootClaw1_r1);
/* 302 */     setRotationAngle(this.leftFootClaw1_r1, 0.0F, 0.0436F, 0.0F);
/* 303 */     this.leftFootClaw1_r1.func_78784_a(0, 27).func_228303_a_(4.4F, -1.1F, -4.3F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 305 */     this.leftFootClaw2 = new ModelRenderer((Model)this);
/* 306 */     this.leftFootClaw2.func_78793_a(0.0F, 0.4F, -0.4F);
/* 307 */     this.leftFoot.func_78792_a(this.leftFootClaw2);
/* 308 */     setRotationAngle(this.leftFootClaw2, -0.7418F, 0.0F, 0.0F);
/* 309 */     this.leftFootClaw2.func_78784_a(0, 27).func_228303_a_(0.0F, 0.0F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 311 */     this.leftFootClaw3 = new ModelRenderer((Model)this);
/* 312 */     this.leftFootClaw3.func_78793_a(0.0F, 0.4F, -0.4F);
/* 313 */     this.leftFoot.func_78792_a(this.leftFootClaw3);
/* 314 */     setRotationAngle(this.leftFootClaw3, -0.7418F, 0.0F, 0.0F);
/* 315 */     this.leftFootClaw3.func_78784_a(0, 27).func_228303_a_(-1.1F, 0.0F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 317 */     this.leftFootClaw4 = new ModelRenderer((Model)this);
/* 318 */     this.leftFootClaw4.func_78793_a(0.0F, 0.4F, -0.4F);
/* 319 */     this.leftFoot.func_78792_a(this.leftFootClaw4);
/* 320 */     setRotationAngle(this.leftFootClaw4, -0.7418F, 0.1745F, -0.1745F);
/* 321 */     this.leftFootClaw4.func_78784_a(0, 27).func_228303_a_(-1.8541F, 0.0077F, -2.2F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 323 */     this.rightLeg = new ModelRenderer((Model)this);
/* 324 */     this.rightLeg.func_78793_a(-3.0F, 17.0F, 0.0F);
/* 325 */     this.rightLeg.func_78784_a(0, 12).func_228303_a_(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, 0.0F, false);
/*     */     
/* 327 */     this.rightFoot2 = new ModelRenderer((Model)this);
/* 328 */     this.rightFoot2.func_78793_a(0.0F, 5.5F, -1.9F);
/* 329 */     this.rightLeg.func_78792_a(this.rightFoot2);
/* 330 */     setRotationAngle(this.rightFoot2, 0.7854F, 0.0F, 0.0F);
/* 331 */     this.rightFoot2.func_78784_a(0, 24).func_228303_a_(-2.0F, 0.0F, -1.0F, 4.0F, 1.0F, 1.0F, -0.01F, false);
/*     */     
/* 333 */     this.rightFootClaw1 = new ModelRenderer((Model)this);
/* 334 */     this.rightFootClaw1.func_78793_a(1.0F, 0.5768F, -0.2232F);
/* 335 */     this.rightFoot2.func_78792_a(this.rightFootClaw1);
/* 336 */     setRotationAngle(this.rightFootClaw1, -0.7418F, -0.1745F, 0.1745F);
/*     */ 
/*     */     
/* 339 */     this.rightFootClaw1_r1 = new ModelRenderer((Model)this);
/* 340 */     this.rightFootClaw1_r1.func_78793_a(-4.5F, 1.1F, 2.3F);
/* 341 */     this.rightFootClaw1.func_78792_a(this.rightFootClaw1_r1);
/* 342 */     setRotationAngle(this.rightFootClaw1_r1, 0.0F, 0.0436F, 0.0F);
/* 343 */     this.rightFootClaw1_r1.func_78784_a(0, 27).func_228303_a_(4.4F, -1.1F, -4.3F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 345 */     this.rightFootClaw2 = new ModelRenderer((Model)this);
/* 346 */     this.rightFootClaw2.func_78793_a(0.0F, 0.4F, -0.4F);
/* 347 */     this.rightFoot2.func_78792_a(this.rightFootClaw2);
/* 348 */     setRotationAngle(this.rightFootClaw2, -0.7418F, 0.0F, 0.0F);
/* 349 */     this.rightFootClaw2.func_78784_a(0, 27).func_228303_a_(0.0F, 0.0F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 351 */     this.rightFootClaw3 = new ModelRenderer((Model)this);
/* 352 */     this.rightFootClaw3.func_78793_a(0.0F, 0.4F, -0.4F);
/* 353 */     this.rightFoot2.func_78792_a(this.rightFootClaw3);
/* 354 */     setRotationAngle(this.rightFootClaw3, -0.7418F, 0.0F, 0.0F);
/* 355 */     this.rightFootClaw3.func_78784_a(0, 27).func_228303_a_(-1.1F, 0.0F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 357 */     this.rightFootClaw4 = new ModelRenderer((Model)this);
/* 358 */     this.rightFootClaw4.func_78793_a(0.0F, 0.4F, -0.4F);
/* 359 */     this.rightFoot2.func_78792_a(this.rightFootClaw4);
/* 360 */     setRotationAngle(this.rightFootClaw4, -0.7418F, 0.1745F, -0.1745F);
/* 361 */     this.rightFootClaw4.func_78784_a(0, 27).func_228303_a_(-1.8541F, 0.0077F, -2.2F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 363 */     this.field_78115_e = this.body;
/* 364 */     this.field_78116_c = this.head;
/* 365 */     this.field_178723_h = this.rightArm;
/* 366 */     this.field_178724_i = this.leftArm;
/* 367 */     this.field_178721_j = this.rightLeg;
/* 368 */     this.field_178722_k = this.leftLeg;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 374 */     this.head.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 375 */     this.body.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 376 */     this.rightArm.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 377 */     this.leftArm.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 378 */     this.leftLeg.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 379 */     this.rightLeg.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 386 */     boolean flag = (entity.func_184599_cB() > 4);
/* 387 */     boolean flag1 = entity.func_213314_bj();
/* 388 */     this.head.field_78796_g = netHeadYaw * 0.017453292F;
/* 389 */     if (flag) {
/* 390 */       this.head.field_78795_f = -0.7853982F;
/* 391 */     } else if (this.field_205061_a > 0.0F) {
/*     */       
/* 393 */       if (flag1) {
/* 394 */         this.head.field_78795_f = func_205060_a(this.head.field_78795_f, -0.7853982F, this.field_205061_a);
/*     */       } else {
/* 396 */         this.head.field_78795_f = func_205060_a(this.head.field_78795_f, headPitch * 0.017453292F, this.field_205061_a);
/*     */       } 
/*     */     } else {
/*     */       
/* 400 */       this.head.field_78795_f = headPitch * 0.017453292F;
/* 401 */       if (this.head.field_78795_f > 0.6D) {
/* 402 */         this.head.field_78795_f = 0.6F;
/*     */       }
/*     */     } 
/*     */     
/* 406 */     float f = 1.0F;
/* 407 */     this.rightArm.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.6662F + 3.1415927F) * 0.8F * limbSwingAmount * 0.5F / f;
/* 408 */     this.leftArm.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.6662F) * 0.8F * limbSwingAmount * 0.5F / f;
/* 409 */     this.rightLeg.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.6662F) * 0.7F * limbSwingAmount / f;
/* 410 */     this.leftLeg.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.6662F + 3.1415927F) * 0.7F * limbSwingAmount / f;
/* 411 */     if (!entity.func_184614_ca().func_190926_b()) {
/* 412 */       this.rightArm.field_78795_f += -0.15F;
/*     */     }
/*     */     
/* 415 */     this.field_217112_c = ((LivingEntity)entity).field_70733_aJ;
/* 416 */     boolean isBlackLeg = EntityStatsCapability.get((LivingEntity)entity).isBlackLeg();
/* 417 */     if (this.field_217112_c > 0.0F)
/*     */     {
/* 419 */       if (isBlackLeg) {
/*     */         
/* 421 */         this.body.field_78796_g = MathHelper.func_76126_a(MathHelper.func_76129_c(this.field_217112_c) * 6.2831855F) * 0.2F;
/* 422 */         this.rightLeg.field_78796_g += this.body.field_78796_g;
/* 423 */         this.leftLeg.field_78796_g += this.body.field_78796_g;
/* 424 */         float f1 = 1.0F - this.field_217112_c;
/* 425 */         f1 *= f1;
/* 426 */         f1 *= f1;
/* 427 */         f1 = 1.0F - f1;
/* 428 */         float f2 = MathHelper.func_76126_a(f1 * 3.1415927F);
/* 429 */         float f3 = MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * -(this.head.field_78795_f - 0.7F) * 0.75F;
/* 430 */         this.rightLeg.field_78795_f = (float)(this.rightArm.field_78795_f - f2 * 1.5D + f3);
/* 431 */         this.rightLeg.field_78796_g += this.body.field_78796_g * 2.0F;
/*     */       }
/*     */       else {
/*     */         
/* 435 */         this.body.field_78796_g = MathHelper.func_76126_a(MathHelper.func_76129_c(this.field_217112_c) * 6.2831855F) * 0.2F;
/* 436 */         this.rightArm.field_78798_e = MathHelper.func_76126_a(this.body2.field_78796_g) * 5.0F;
/* 437 */         this.rightArm.field_78800_c = -MathHelper.func_76134_b(this.body2.field_78796_g) * 5.0F;
/* 438 */         this.leftArm.field_78798_e = -MathHelper.func_76126_a(this.body2.field_78796_g) * 5.0F;
/* 439 */         this.leftArm.field_78800_c = MathHelper.func_76134_b(this.body2.field_78796_g) * 5.0F;
/* 440 */         this.rightArm.field_78796_g += this.body2.field_78796_g;
/* 441 */         this.leftArm.field_78796_g += this.body2.field_78796_g;
/* 442 */         this.leftArm.field_78795_f += this.body2.field_78796_g;
/* 443 */         float f1 = 1.0F - this.field_217112_c;
/* 444 */         f1 *= f1;
/* 445 */         f1 *= f1;
/* 446 */         f1 = 1.0F - f1;
/* 447 */         float f2 = MathHelper.func_76126_a(f1 * 3.1415927F);
/* 448 */         float f3 = MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * -(this.head.field_78795_f - 0.7F) * 1.75F;
/* 449 */         this.rightArm.field_78795_f = (float)(this.rightArm.field_78795_f - f2 * 1.2D + f3);
/* 450 */         this.rightArm.field_78796_g += this.body.field_78796_g * 4.0F;
/* 451 */         this.rightArm.field_78808_h += MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * -0.4F;
/*     */       } 
/*     */     }
/*     */     
/* 455 */     if (entity.func_213453_ef()) {
/*     */       
/* 457 */       this.body.field_78795_f = 0.5F;
/* 458 */       this.body.field_78798_e -= 4.0F;
/* 459 */       this.rightArm.field_78795_f += 0.4F;
/* 460 */       this.rightArm.field_78798_e -= 2.5F;
/* 461 */       this.leftArm.field_78795_f += 0.4F;
/* 462 */       this.leftArm.field_78798_e -= 2.5F;
/* 463 */       this.rightLeg.field_78798_e = 3.0F;
/* 464 */       this.rightLeg.field_78797_d = 15.0F;
/* 465 */       this.leftLeg.field_78798_e = 3.0F;
/* 466 */       this.leftLeg.field_78797_d = 15.0F;
/* 467 */       this.head.field_78798_e = -6.0F;
/* 468 */       this.head.field_78797_d = 1.0F;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225599_a_(HandSide side, MatrixStack matrixStack) {
/* 475 */     super.func_225599_a_(side, matrixStack);
/* 476 */     matrixStack.func_227861_a_((side == HandSide.RIGHT) ? -0.1D : 0.1D, 0.5D, 0.0D);
/* 477 */     matrixStack.func_227863_a_(Vector3f.field_229183_f_.func_229187_a_((side == HandSide.RIGHT) ? -10.0F : 10.0F));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
/* 483 */     if (side == HandSide.RIGHT) {
/*     */       
/* 485 */       matrixStack.func_227861_a_(-0.5D, -0.05D, 0.0D);
/* 486 */       matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(20.0F));
/* 487 */       this.rightHand.func_228309_a_(matrixStack, vertex, packedLight, OverlayTexture.field_229196_a_, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */     }
/*     */     else {
/*     */       
/* 491 */       matrixStack.func_227861_a_(0.5D, -0.05D, 0.0D);
/* 492 */       matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(-20.0F));
/* 493 */       this.leftHand.func_228309_a_(matrixStack, vertex, packedLight, OverlayTexture.field_229196_a_, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
/* 500 */     if (side == HandSide.RIGHT) {
/*     */       
/* 502 */       matrixStack.func_227861_a_(0.0D, -1.2D, 0.3D);
/* 503 */       matrixStack.func_227862_a_(1.5F, 1.5F, 1.5F);
/* 504 */       matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(-60.0F));
/* 505 */       this.rightLeg.func_228309_a_(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     }
/*     */     else {
/*     */       
/* 509 */       matrixStack.func_227861_a_(0.0D, -1.2D, 0.3D);
/* 510 */       matrixStack.func_227862_a_(1.5F, 1.5F, 1.5F);
/* 511 */       matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(60.0F));
/* 512 */       this.leftLeg.func_228309_a_(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 518 */     modelRenderer.field_78795_f = x;
/* 519 */     modelRenderer.field_78796_g = y;
/* 520 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\morphs\MoguMoleModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */