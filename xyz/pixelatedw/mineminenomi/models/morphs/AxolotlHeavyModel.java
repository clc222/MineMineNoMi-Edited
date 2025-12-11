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
/*     */ public class AxolotlHeavyModel<T extends LivingEntity>
/*     */   extends MorphModel<T> implements IHasArm {
/*     */   private final ModelRenderer body;
/*     */   private final ModelRenderer bodyDetail_r1;
/*     */   private final ModelRenderer rightNipple_r1;
/*     */   private final ModelRenderer hips_r1;
/*     */   private final ModelRenderer lowerBody_r1;
/*     */   private final ModelRenderer tail;
/*     */   private final ModelRenderer tail1Detail_r1;
/*     */   private final ModelRenderer tail1_r1;
/*     */   private final ModelRenderer tail2;
/*     */   private final ModelRenderer tail2Detail_r1;
/*     */   private final ModelRenderer tail2_r1;
/*     */   private final ModelRenderer tail3;
/*     */   private final ModelRenderer tail3Detail_r1;
/*     */   private final ModelRenderer tail3_r1;
/*     */   private final ModelRenderer tail4;
/*     */   private final ModelRenderer tail4Detail_r1;
/*     */   private final ModelRenderer head;
/*     */   private final ModelRenderer head_r1;
/*     */   private final ModelRenderer neck_r1;
/*     */   private final ModelRenderer leftLeg;
/*     */   private final ModelRenderer leftLeg2_r1;
/*     */   private final ModelRenderer leftLeg1_r1;
/*     */   private final ModelRenderer rightLeg;
/*     */   private final ModelRenderer rightLeg2_r1;
/*     */   private final ModelRenderer rightLeg1_r1;
/*     */   private final ModelRenderer leftArm;
/*     */   private final ModelRenderer leftArm3_r1;
/*     */   private final ModelRenderer leftArm2_r1;
/*     */   private final ModelRenderer leftArm1_r1;
/*     */   private final ModelRenderer rightArm;
/*     */   private final ModelRenderer rightArm3_r1;
/*     */   private final ModelRenderer rightArm2_r1;
/*     */   private final ModelRenderer rightArm1_r1;
/*     */   
/*     */   public AxolotlHeavyModel() {
/*  53 */     super(1.0F);
/*  54 */     this.field_78090_t = 256;
/*  55 */     this.field_78089_u = 256;
/*     */     
/*  57 */     this.body = new ModelRenderer((Model)this);
/*  58 */     this.body.func_78793_a(4.0F, 14.0F, 35.0F);
/*  59 */     setRotationAngle(this.body, 0.3491F, 0.0F, 0.0F);
/*     */     
/*  61 */     this.bodyDetail_r1 = new ModelRenderer((Model)this);
/*  62 */     this.bodyDetail_r1.func_78793_a(-5.0F, -27.0F, -19.0F);
/*  63 */     this.body.func_78792_a(this.bodyDetail_r1);
/*  64 */     setRotationAngle(this.bodyDetail_r1, 0.0873F, 0.0F, 0.0F);
/*  65 */     this.bodyDetail_r1.func_78784_a(107, 11).func_228303_a_(1.0F, -12.0F, -6.0F, 0.0F, 25.0F, 23.0F, 0.01F, false);
/*     */     
/*  67 */     this.rightNipple_r1 = new ModelRenderer((Model)this);
/*  68 */     this.rightNipple_r1.func_78793_a(0.0F, 0.0F, 0.0F);
/*  69 */     this.body.func_78792_a(this.rightNipple_r1);
/*  70 */     setRotationAngle(this.rightNipple_r1, 0.0873F, 0.0F, 0.0F);
/*  71 */     this.rightNipple_r1.func_78784_a(0, 8).func_228303_a_(-13.0F, -30.0F, -32.6F, 3.0F, 2.0F, 2.0F, 0.0F, false);
/*  72 */     this.rightNipple_r1.func_78784_a(0, 8).func_228303_a_(2.0F, -30.0F, -32.6F, 3.0F, 2.0F, 2.0F, 0.0F, false);
/*  73 */     this.rightNipple_r1.func_78784_a(47, 50).func_228303_a_(-15.0F, -36.3F, -31.3F, 22.0F, 3.0F, 13.0F, 0.0F, false);
/*  74 */     this.rightNipple_r1.func_78784_a(0, 0).func_228303_a_(-16.0F, -36.0F, -32.3F, 24.0F, 16.0F, 15.0F, 0.0F, false);
/*     */     
/*  76 */     this.hips_r1 = new ModelRenderer((Model)this);
/*  77 */     this.hips_r1.func_78793_a(0.0F, 0.0F, 0.0F);
/*  78 */     this.body.func_78792_a(this.hips_r1);
/*  79 */     setRotationAngle(this.hips_r1, 0.0436F, 0.0F, 0.0F);
/*  80 */     this.hips_r1.func_78784_a(24, 32).func_228303_a_(-15.5F, -20.0F, -31.5F, 23.0F, 4.0F, 13.0F, 0.0F, false);
/*     */     
/*  82 */     this.lowerBody_r1 = new ModelRenderer((Model)this);
/*  83 */     this.lowerBody_r1.func_78793_a(0.0F, -7.0F, -25.0F);
/*  84 */     this.body.func_78792_a(this.lowerBody_r1);
/*  85 */     setRotationAngle(this.lowerBody_r1, -0.48F, 0.0F, 0.0F);
/*  86 */     this.lowerBody_r1.func_78784_a(47, 67).func_228303_a_(-13.0F, -10.0F, -10.0F, 18.0F, 10.0F, 11.0F, 0.0F, false);
/*     */     
/*  88 */     this.tail = new ModelRenderer((Model)this);
/*  89 */     this.tail.func_78793_a(0.0F, 11.0F, 13.0F);
/*  90 */     setRotationAngle(this.tail, -0.1309F, 0.0F, 0.0F);
/*     */     
/*  92 */     this.tail1Detail_r1 = new ModelRenderer((Model)this);
/*  93 */     this.tail1Detail_r1.func_78793_a(1.0F, 0.4965F, 2.1955F);
/*  94 */     this.tail.func_78792_a(this.tail1Detail_r1);
/*  95 */     setRotationAngle(this.tail1Detail_r1, -0.2182F, 0.0F, 0.0F);
/*  96 */     this.tail1Detail_r1.func_78784_a(106, 53).func_228303_a_(-1.0F, -9.0F, -8.0F, 0.0F, 14.0F, 15.0F, 0.0F, false);
/*     */     
/*  98 */     this.tail1_r1 = new ModelRenderer((Model)this);
/*  99 */     this.tail1_r1.func_78793_a(1.0F, 15.4965F, 20.1955F);
/* 100 */     this.tail.func_78792_a(this.tail1_r1);
/* 101 */     setRotationAngle(this.tail1_r1, -0.3054F, 0.0F, 0.0F);
/* 102 */     this.tail1_r1.func_78784_a(41, 91).func_228303_a_(-4.5F, -11.0F, -34.0F, 7.0F, 8.0F, 19.0F, 0.0F, false);
/*     */     
/* 104 */     this.tail2 = new ModelRenderer((Model)this);
/* 105 */     this.tail2.func_78793_a(0.0F, 5.5F, 8.0F);
/* 106 */     this.tail.func_78792_a(this.tail2);
/*     */     
/* 108 */     this.tail2Detail_r1 = new ModelRenderer((Model)this);
/* 109 */     this.tail2Detail_r1.func_78793_a(1.0F, -2.5035F, 5.1955F);
/* 110 */     this.tail2.func_78792_a(this.tail2Detail_r1);
/* 111 */     setRotationAngle(this.tail2Detail_r1, -0.1745F, 0.0F, 0.0F);
/* 112 */     this.tail2Detail_r1.func_78784_a(96, 87).func_228303_a_(-1.0F, -8.8987F, -6.0675F, 0.0F, 14.0F, 10.0F, 0.0F, false);
/*     */     
/* 114 */     this.tail2_r1 = new ModelRenderer((Model)this);
/* 115 */     this.tail2_r1.func_78793_a(1.0F, 9.4965F, 9.1955F);
/* 116 */     this.tail2.func_78792_a(this.tail2_r1);
/* 117 */     setRotationAngle(this.tail2_r1, -0.1745F, 0.0F, 0.0F);
/* 118 */     this.tail2_r1.func_78784_a(84, 114).func_228303_a_(-3.5F, -11.0F, -12.0F, 5.0F, 6.0F, 10.0F, 0.0F, false);
/*     */     
/* 120 */     this.tail3 = new ModelRenderer((Model)this);
/* 121 */     this.tail3.func_78793_a(0.0F, 2.0F, 13.0F);
/* 122 */     this.tail2.func_78792_a(this.tail3);
/*     */     
/* 124 */     this.tail3Detail_r1 = new ModelRenderer((Model)this);
/* 125 */     this.tail3Detail_r1.func_78793_a(1.0F, -4.5035F, 3.1955F);
/* 126 */     this.tail3.func_78792_a(this.tail3Detail_r1);
/* 127 */     setRotationAngle(this.tail3Detail_r1, -0.1309F, 0.0F, 0.0F);
/* 128 */     this.tail3Detail_r1.func_78784_a(120, 87).func_228303_a_(-1.0F, -6.9629F, -6.9734F, 0.0F, 13.0F, 10.0F, 0.0F, false);
/*     */     
/* 130 */     this.tail3_r1 = new ModelRenderer((Model)this);
/* 131 */     this.tail3_r1.func_78793_a(1.0F, 7.4965F, -3.8045F);
/* 132 */     this.tail3.func_78792_a(this.tail3_r1);
/* 133 */     setRotationAngle(this.tail3_r1, -0.0873F, 0.0F, 0.0F);
/* 134 */     this.tail3_r1.func_78784_a(115, 114).func_228303_a_(-2.5F, -10.0F, -3.0F, 3.0F, 4.0F, 12.0F, 0.0F, false);
/*     */     
/* 136 */     this.tail4 = new ModelRenderer((Model)this);
/* 137 */     this.tail4.func_78793_a(0.0F, 1.0F, 6.0F);
/* 138 */     this.tail3.func_78792_a(this.tail4);
/* 139 */     this.tail4.func_78784_a(0, 0).func_228303_a_(-0.5F, -1.0035F, -0.8045F, 1.0F, 2.0F, 5.0F, 0.0F, false);
/*     */     
/* 141 */     this.tail4Detail_r1 = new ModelRenderer((Model)this);
/* 142 */     this.tail4Detail_r1.func_78793_a(1.0F, -5.5035F, -4.8045F);
/* 143 */     this.tail4.func_78792_a(this.tail4Detail_r1);
/* 144 */     setRotationAngle(this.tail4Detail_r1, -0.0436F, 0.0F, 0.0F);
/* 145 */     this.tail4Detail_r1.func_78784_a(119, 131).func_228303_a_(-1.0F, -6.4629F, 4.0266F, 0.0F, 12.0F, 5.0F, 0.0F, false);
/*     */     
/* 147 */     this.head = new ModelRenderer((Model)this);
/* 148 */     this.head.func_78793_a(0.0F, -7.0F, -4.0F);
/* 149 */     setRotationAngle(this.head, 0.3491F, 0.0F, 0.0F);
/* 150 */     this.head.func_78784_a(100, 4).func_228303_a_(-8.0F, -10.9277F, 1.4656F, 16.0F, 9.0F, 0.0F, 0.0F, false);
/*     */     
/* 152 */     this.head_r1 = new ModelRenderer((Model)this);
/* 153 */     this.head_r1.func_78793_a(0.0F, -1.9277F, 0.4656F);
/* 154 */     this.head.func_78792_a(this.head_r1);
/* 155 */     setRotationAngle(this.head_r1, 0.0436F, 0.0F, 0.0F);
/* 156 */     this.head_r1.func_78784_a(64, 0).func_228303_a_(-4.0F, -4.5F, -3.5F, 8.0F, 5.0F, 8.0F, 0.0F, false);
/*     */     
/* 158 */     this.neck_r1 = new ModelRenderer((Model)this);
/* 159 */     this.neck_r1.func_78793_a(0.0F, -1.9277F, 0.4656F);
/* 160 */     this.head.func_78792_a(this.neck_r1);
/* 161 */     setRotationAngle(this.neck_r1, 0.1745F, 0.0F, 0.0F);
/* 162 */     this.neck_r1.func_78784_a(81, 16).func_228303_a_(-3.5F, -2.0F, -2.0F, 7.0F, 5.0F, 6.0F, 0.0F, false);
/*     */     
/* 164 */     this.leftLeg = new ModelRenderer((Model)this);
/* 165 */     this.leftLeg.func_78793_a(7.0F, 14.25F, 3.0F);
/* 166 */     setRotationAngle(this.leftLeg, -0.2618F, 0.0F, 0.0F);
/*     */     
/* 168 */     this.leftLeg2_r1 = new ModelRenderer((Model)this);
/* 169 */     this.leftLeg2_r1.func_78793_a(-3.0F, -5.0F, 2.0F);
/* 170 */     this.leftLeg.func_78792_a(this.leftLeg2_r1);
/* 171 */     setRotationAngle(this.leftLeg2_r1, 0.2618F, 0.0F, 0.0F);
/* 172 */     this.leftLeg2_r1.func_78784_a(2, 148).func_228303_a_(0.0F, 6.0F, -7.5F, 6.0F, 8.0F, 5.0F, 0.0F, true);
/*     */     
/* 174 */     this.leftLeg1_r1 = new ModelRenderer((Model)this);
/* 175 */     this.leftLeg1_r1.func_78793_a(-3.0F, -2.0F, 1.0F);
/* 176 */     this.leftLeg.func_78792_a(this.leftLeg1_r1);
/* 177 */     setRotationAngle(this.leftLeg1_r1, -0.5672F, 0.0F, 0.0F);
/* 178 */     this.leftLeg1_r1.func_78784_a(0, 131).func_228303_a_(-0.5F, -1.0F, -1.5F, 7.0F, 8.0F, 6.0F, 0.0F, true);
/*     */     
/* 180 */     this.rightLeg = new ModelRenderer((Model)this);
/* 181 */     this.rightLeg.func_78793_a(-7.0F, 14.25F, 4.0F);
/* 182 */     setRotationAngle(this.rightLeg, -0.2618F, 0.0F, 0.0F);
/*     */     
/* 184 */     this.rightLeg2_r1 = new ModelRenderer((Model)this);
/* 185 */     this.rightLeg2_r1.func_78793_a(-3.0F, -5.0341F, 1.2588F);
/* 186 */     this.rightLeg.func_78792_a(this.rightLeg2_r1);
/* 187 */     setRotationAngle(this.rightLeg2_r1, 0.2618F, 0.0F, 0.0F);
/* 188 */     this.rightLeg2_r1.func_78784_a(2, 148).func_228303_a_(0.0F, 6.0F, -7.5F, 6.0F, 8.0F, 5.0F, 0.0F, false);
/*     */     
/* 190 */     this.rightLeg1_r1 = new ModelRenderer((Model)this);
/* 191 */     this.rightLeg1_r1.func_78793_a(-3.0F, -2.0341F, 0.2588F);
/* 192 */     this.rightLeg.func_78792_a(this.rightLeg1_r1);
/* 193 */     setRotationAngle(this.rightLeg1_r1, -0.5672F, 0.0F, 0.0F);
/* 194 */     this.rightLeg1_r1.func_78784_a(0, 131).func_228303_a_(-0.5F, -1.0F, -1.5F, 7.0F, 8.0F, 6.0F, 0.0F, false);
/*     */     
/* 196 */     this.leftArm = new ModelRenderer((Model)this);
/* 197 */     this.leftArm.func_78793_a(12.0F, -4.0F, -1.0F);
/* 198 */     setRotationAngle(this.leftArm, -0.4363F, 0.0F, 0.0F);
/*     */     
/* 200 */     this.leftArm3_r1 = new ModelRenderer((Model)this);
/* 201 */     this.leftArm3_r1.func_78793_a(6.0F, 16.0F, 2.0F);
/* 202 */     this.leftArm.func_78792_a(this.leftArm3_r1);
/* 203 */     setRotationAngle(this.leftArm3_r1, 0.0843F, -0.0226F, 0.2608F);
/* 204 */     this.leftArm3_r1.func_78784_a(3, 105).func_228303_a_(-6.0F, -2.1134F, -4.5979F, 10.0F, 15.0F, 9.0F, 0.0F, true);
/*     */     
/* 206 */     this.leftArm2_r1 = new ModelRenderer((Model)this);
/* 207 */     this.leftArm2_r1.func_78793_a(0.0F, 0.0F, 0.0F);
/* 208 */     this.leftArm.func_78792_a(this.leftArm2_r1);
/* 209 */     setRotationAngle(this.leftArm2_r1, 0.0865F, 0.0114F, -0.1304F);
/* 210 */     this.leftArm2_r1.func_78784_a(0, 71).func_228303_a_(-1.0F, -3.0F, -5.0F, 12.0F, 20.0F, 11.0F, 0.0F, true);
/*     */     
/* 212 */     this.leftArm1_r1 = new ModelRenderer((Model)this);
/* 213 */     this.leftArm1_r1.func_78793_a(4.0F, -2.0F, 0.0F);
/* 214 */     this.leftArm.func_78792_a(this.leftArm1_r1);
/* 215 */     setRotationAngle(this.leftArm1_r1, 0.0865F, 0.0114F, -0.1304F);
/* 216 */     this.leftArm1_r1.func_78784_a(1, 52).func_228303_a_(-4.0F, -7.0076F, -4.1743F, 9.0F, 8.0F, 9.0F, 0.0F, true);
/*     */     
/* 218 */     this.rightArm = new ModelRenderer((Model)this);
/* 219 */     this.rightArm.func_78793_a(-12.0F, -4.0F, -1.0F);
/* 220 */     setRotationAngle(this.rightArm, -0.4363F, 0.0F, 0.0F);
/*     */     
/* 222 */     this.rightArm3_r1 = new ModelRenderer((Model)this);
/* 223 */     this.rightArm3_r1.func_78793_a(-6.0F, 16.2451F, 1.8498F);
/* 224 */     this.rightArm.func_78792_a(this.rightArm3_r1);
/* 225 */     setRotationAngle(this.rightArm3_r1, 0.0843F, 0.0226F, -0.2608F);
/* 226 */     this.rightArm3_r1.func_78784_a(3, 105).func_228303_a_(-4.0F, -2.1134F, -4.5979F, 10.0F, 15.0F, 9.0F, 0.0F, false);
/*     */     
/* 228 */     this.rightArm2_r1 = new ModelRenderer((Model)this);
/* 229 */     this.rightArm2_r1.func_78793_a(0.0F, 0.2451F, -0.1502F);
/* 230 */     this.rightArm.func_78792_a(this.rightArm2_r1);
/* 231 */     setRotationAngle(this.rightArm2_r1, 0.0865F, -0.0114F, 0.1304F);
/* 232 */     this.rightArm2_r1.func_78784_a(0, 71).func_228303_a_(-11.0F, -3.0F, -5.0F, 12.0F, 20.0F, 11.0F, 0.0F, false);
/*     */     
/* 234 */     this.rightArm1_r1 = new ModelRenderer((Model)this);
/* 235 */     this.rightArm1_r1.func_78793_a(-4.0F, -1.7549F, -0.1502F);
/* 236 */     this.rightArm.func_78792_a(this.rightArm1_r1);
/* 237 */     setRotationAngle(this.rightArm1_r1, 0.0865F, -0.0114F, 0.1304F);
/* 238 */     this.rightArm1_r1.func_78784_a(1, 52).func_228303_a_(-5.0F, -7.0076F, -4.1743F, 9.0F, 8.0F, 9.0F, 0.0F, false);
/*     */     
/* 240 */     this.field_78115_e = this.body;
/* 241 */     this.field_78116_c = this.head;
/* 242 */     this.field_178723_h = this.rightArm;
/* 243 */     this.field_178724_i = this.leftArm;
/* 244 */     this.field_178721_j = this.rightLeg;
/* 245 */     this.field_178722_k = this.leftLeg;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 252 */     boolean flag = (entity.func_184599_cB() > 4);
/* 253 */     boolean flag1 = entity.func_213314_bj();
/* 254 */     this.head.field_78796_g = netHeadYaw * 0.017453292F;
/* 255 */     if (flag) {
/* 256 */       this.head.field_78795_f = -0.7853982F;
/* 257 */     } else if (this.field_205061_a > 0.0F) {
/*     */       
/* 259 */       if (flag1) {
/* 260 */         this.head.field_78795_f = func_205060_a(this.head.field_78795_f, -0.7853982F, this.field_205061_a);
/*     */       } else {
/* 262 */         this.head.field_78795_f = func_205060_a(this.head.field_78795_f, headPitch * 0.017453292F, this.field_205061_a);
/*     */       } 
/*     */     } else {
/*     */       
/* 266 */       this.head.field_78795_f = headPitch * 0.017453292F;
/* 267 */       if (this.head.field_78795_f > 0.4D) {
/* 268 */         this.head.field_78795_f = 0.4F;
/* 269 */       } else if (this.head.field_78795_f < -0.4D) {
/* 270 */         this.head.field_78795_f = -0.4F;
/*     */       } 
/*     */     } 
/*     */     
/* 274 */     float f = 1.0F;
/* 275 */     this.leftArm.field_78795_f = -0.34F + MathHelper.func_76134_b(limbSwing * 0.6662F + 3.1415927F) * 0.8F * limbSwingAmount * 0.5F / f;
/* 276 */     this.rightArm.field_78795_f = -0.34F + MathHelper.func_76134_b(limbSwing * 0.6662F + 3.1415927F) * 0.8F * limbSwingAmount * 0.5F / f;
/* 277 */     this.leftLeg.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.6662F) * 0.7F * limbSwingAmount / f;
/* 278 */     this.rightLeg.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.6662F) * 0.7F * limbSwingAmount / f;
/* 279 */     if (!entity.func_184614_ca().func_190926_b()) {
/* 280 */       this.rightArm.field_78795_f += -0.15F;
/*     */     }
/* 282 */     this.tail.field_78796_g = (float)(this.tail.field_78796_g + Math.sin(ageInTicks * 0.06D) / 10.0D);
/* 283 */     this.tail.field_78795_f = (float)(this.tail.field_78795_f + Math.sin(ageInTicks * 0.02D) / 10.0D);
/*     */ 
/*     */     
/* 286 */     this.field_217112_c = ((LivingEntity)entity).field_70733_aJ;
/* 287 */     boolean isBlackLeg = EntityStatsCapability.get((LivingEntity)entity).isBlackLeg();
/* 288 */     if (this.field_217112_c > 0.0F)
/*     */     {
/* 290 */       if (isBlackLeg) {
/*     */         
/* 292 */         this.leftLeg.field_78796_g += this.body.field_78796_g;
/* 293 */         this.rightLeg.field_78796_g += this.body.field_78796_g;
/* 294 */         float f1 = 1.0F - this.field_217112_c;
/* 295 */         f1 *= f1;
/* 296 */         f1 *= f1;
/* 297 */         f1 = 1.0F - f1;
/* 298 */         float f2 = MathHelper.func_76126_a(f1 * 3.1415927F);
/* 299 */         float f3 = MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * -(this.head.field_78795_f - 0.7F) * 0.75F;
/* 300 */         this.leftLeg.field_78795_f = (float)(this.leftArm.field_78795_f - f2 * 1.5D + f3);
/* 301 */         this.leftLeg.field_78808_h = (float)(this.leftArm.field_78795_f - f2 * 0.5D + f3);
/* 302 */         this.leftLeg.field_78796_g += this.body.field_78796_g * 2.0F;
/*     */       }
/*     */       else {
/*     */         
/* 306 */         this.body.field_78796_g = MathHelper.func_76126_a(MathHelper.func_76129_c(this.field_217112_c) * 6.2831855F) * 0.05F;
/* 307 */         this.rightArm.field_78798_e = MathHelper.func_76126_a(this.body.field_78796_g) * 5.0F;
/* 308 */         this.rightArm.field_78800_c = -MathHelper.func_76134_b(this.body.field_78796_g) * 8.0F;
/* 309 */         this.leftArm.field_78798_e = -MathHelper.func_76126_a(this.body.field_78796_g) * 5.0F;
/* 310 */         this.leftArm.field_78800_c = MathHelper.func_76134_b(this.body.field_78796_g) * 8.0F;
/* 311 */         this.rightArm.field_78796_g += this.body.field_78796_g;
/* 312 */         this.leftArm.field_78796_g += this.body.field_78796_g;
/* 313 */         this.leftArm.field_78795_f += this.body.field_78796_g;
/* 314 */         float f1 = 1.0F - this.field_217112_c;
/* 315 */         f1 *= f1;
/* 316 */         f1 *= f1;
/* 317 */         f1 = 1.0F - f1;
/* 318 */         float f2 = MathHelper.func_76126_a(f1 * 3.1415927F);
/* 319 */         float f3 = MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * -(this.head.field_78795_f - 0.7F) * 0.75F;
/* 320 */         this.rightArm.field_78795_f = (float)(this.rightArm.field_78795_f - f2 * 1.5D + f3);
/* 321 */         this.rightArm.field_78796_g += this.body.field_78796_g * 2.0F;
/* 322 */         this.rightArm.field_78808_h += MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * -0.9F;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 330 */     this.body.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 331 */     this.tail.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 332 */     this.head.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 333 */     this.leftLeg.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 334 */     this.rightLeg.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 335 */     this.leftArm.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 336 */     this.rightArm.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 341 */     modelRenderer.field_78795_f = x;
/* 342 */     modelRenderer.field_78796_g = y;
/* 343 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225599_a_(HandSide side, MatrixStack matrixStack) {
/* 349 */     super.func_225599_a_(side, matrixStack);
/* 350 */     if (side == HandSide.RIGHT) {
/* 351 */       matrixStack.func_227861_a_(-0.3D, 1.1D, 0.1D);
/*     */     } else {
/* 353 */       matrixStack.func_227861_a_(0.3D, 1.1D, 0.1D);
/* 354 */     }  matrixStack.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(-20.0F));
/* 355 */     matrixStack.func_227863_a_(Vector3f.field_229183_f_.func_229187_a_((side == HandSide.RIGHT) ? -20.0F : 20.0F));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
/* 361 */     if (side == HandSide.RIGHT) {
/*     */       
/* 363 */       matrixStack.func_227861_a_(0.2D, 0.3D, 0.3D);
/* 364 */       matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(-10.0F));
/* 365 */       matrixStack.func_227863_a_(Vector3f.field_229183_f_.func_229187_a_(-10.0F));
/* 366 */       this.rightArm2_r1.func_228309_a_(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     }
/*     */     else {
/*     */       
/* 370 */       matrixStack.func_227861_a_(-0.2D, 0.3D, 0.3D);
/* 371 */       matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(-10.0F));
/* 372 */       matrixStack.func_227863_a_(Vector3f.field_229183_f_.func_229187_a_(10.0F));
/* 373 */       this.leftArm2_r1.func_228309_a_(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
/* 380 */     if (side == HandSide.RIGHT) {
/*     */       
/* 382 */       matrixStack.func_227861_a_(0.4D, -0.8D, -0.1D);
/* 383 */       matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(-10.0F));
/* 384 */       this.rightLeg.func_228309_a_(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     }
/*     */     else {
/*     */       
/* 388 */       matrixStack.func_227861_a_(-0.4D, -0.8D, -0.1D);
/* 389 */       matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(10.0F));
/* 390 */       this.leftLeg.func_228309_a_(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\morphs\AxolotlHeavyModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */