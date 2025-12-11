/*     */ package xyz.pixelatedw.mineminenomi.models.entities.mobs.animals;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.BananawaniEntity;
/*     */ 
/*     */ public class BananawaniModel<T extends LivingEntity> extends BipedModel<T> {
/*     */   private final ModelRenderer body;
/*     */   private final ModelRenderer tail;
/*     */   private final ModelRenderer tail2;
/*     */   private final ModelRenderer tail3;
/*     */   private final ModelRenderer tail4;
/*     */   private final ModelRenderer tail5;
/*     */   private final ModelRenderer tail6;
/*     */   private final ModelRenderer tail7;
/*     */   private final ModelRenderer tailOrnament;
/*     */   private final ModelRenderer tailOrnament_r1;
/*     */   private final ModelRenderer tailOrnament2;
/*     */   private final ModelRenderer tailOrnament3;
/*     */   private final ModelRenderer tailOrnament4;
/*     */   private final ModelRenderer tailOrnament5;
/*     */   private final ModelRenderer rightFrontLeg;
/*     */   private final ModelRenderer rightFrontLeg1_r1;
/*     */   private final ModelRenderer rightFrontLeg2;
/*     */   private final ModelRenderer rightFrontPaw1;
/*     */   private final ModelRenderer rightFrontPaw2;
/*     */   private final ModelRenderer rightBackLeg;
/*     */   private final ModelRenderer rightBackLeg1_r1;
/*     */   private final ModelRenderer rightBackLeg2;
/*     */   private final ModelRenderer rightBackPaw1;
/*     */   private final ModelRenderer rightBackPaw2;
/*     */   private final ModelRenderer head;
/*     */   private final ModelRenderer lowerMouth;
/*     */   private final ModelRenderer upperMouth;
/*     */   private final ModelRenderer ornament;
/*     */   private final ModelRenderer ornament_r1;
/*     */   private final ModelRenderer ornament2;
/*     */   private final ModelRenderer ornament3;
/*     */   private final ModelRenderer ornament4;
/*     */   private final ModelRenderer ornament5;
/*     */   private final ModelRenderer leftBackLeg;
/*     */   private final ModelRenderer leftBackLeg_r1;
/*     */   private final ModelRenderer leftBackLeg2;
/*     */   private final ModelRenderer leftBackPaw;
/*     */   private final ModelRenderer leftBackPaw2;
/*     */   private final ModelRenderer leftFrontLeg;
/*     */   private final ModelRenderer leftFrontLeg_r1;
/*     */   private final ModelRenderer leftFrontLeg2;
/*     */   private final ModelRenderer leftFrontPaw;
/*     */   private final ModelRenderer leftFrontPaw2;
/*     */   protected float biteAnimationProgress;
/*     */   
/*     */   public BananawaniModel() {
/*  60 */     super(1.0F);
/*  61 */     this.field_78090_t = 128;
/*  62 */     this.field_78089_u = 128;
/*     */     
/*  64 */     this.body = new ModelRenderer((Model)this);
/*  65 */     this.body.func_78793_a(0.0F, 18.0F, 3.0F);
/*  66 */     this.body.func_78784_a(0, 1).func_228303_a_(-8.0F, -7.0F, -12.0F, 16.0F, 9.0F, 24.0F, 0.0F, false);
/*  67 */     this.body.func_78784_a(0, 35).func_228303_a_(-7.5F, 2.0F, -12.0F, 15.0F, 1.0F, 24.0F, 0.0F, false);
/*  68 */     this.body.func_78784_a(52, 63).func_228303_a_(-7.0F, -9.0F, -12.0F, 14.0F, 2.0F, 24.0F, 0.0F, false);
/*  69 */     this.body.func_78784_a(0, 62).func_228303_a_(-6.0F, -10.0F, -11.0F, 12.0F, 1.0F, 22.0F, 0.0F, false);
/*  70 */     this.body.func_78784_a(51, 91).func_228303_a_(-7.0F, -7.0F, -16.0F, 14.0F, 9.0F, 4.0F, 0.0F, false);
/*  71 */     this.body.func_78784_a(59, 18).func_228303_a_(-6.0F, -8.25F, -15.5F, 12.0F, 2.0F, 4.0F, 0.0F, false);
/*     */     
/*  73 */     this.tail = new ModelRenderer((Model)this);
/*  74 */     this.tail.func_78793_a(0.0F, 15.5F, 14.5F);
/*  75 */     this.tail.func_78784_a(60, 37).func_228303_a_(-7.0F, -4.5F, 0.0F, 14.0F, 9.0F, 8.0F, 0.0F, false);
/*  76 */     this.tail.func_78784_a(60, 0).func_228303_a_(-6.0F, -5.5F, 0.5F, 12.0F, 1.0F, 5.0F, 0.0F, false);
/*     */     
/*  78 */     this.tail2 = new ModelRenderer((Model)this);
/*  79 */     this.tail2.func_78793_a(0.0F, 1.25F, 6.75F);
/*  80 */     this.tail.func_78792_a(this.tail2);
/*  81 */     setRotationAngle(this.tail2, 0.2182F, 0.0F, 0.0F);
/*  82 */     this.tail2.func_78784_a(92, 0).func_228303_a_(-6.0F, -5.0F, 0.5F, 12.0F, 8.0F, 6.0F, 0.0F, false);
/*     */     
/*  84 */     this.tail3 = new ModelRenderer((Model)this);
/*  85 */     this.tail3.func_78793_a(0.0F, -1.0F, 5.5F);
/*  86 */     this.tail2.func_78792_a(this.tail3);
/*  87 */     setRotationAngle(this.tail3, 0.0873F, 0.0F, 0.0F);
/*  88 */     this.tail3.func_78784_a(100, 15).func_228303_a_(-5.0F, -3.25F, 0.5F, 10.0F, 7.0F, 4.0F, 0.0F, false);
/*     */     
/*  90 */     this.tail4 = new ModelRenderer((Model)this);
/*  91 */     this.tail4.func_78793_a(0.0F, 0.0F, 3.75F);
/*  92 */     this.tail3.func_78792_a(this.tail4);
/*  93 */     setRotationAngle(this.tail4, 0.1745F, 0.0F, 0.0F);
/*  94 */     this.tail4.func_78784_a(106, 27).func_228303_a_(-4.0F, -2.5F, 0.0F, 8.0F, 6.0F, 3.0F, 0.0F, false);
/*     */     
/*  96 */     this.tail5 = new ModelRenderer((Model)this);
/*  97 */     this.tail5.func_78793_a(0.0F, 0.75F, 2.75F);
/*  98 */     this.tail4.func_78792_a(this.tail5);
/*  99 */     setRotationAngle(this.tail5, 0.0873F, 0.0F, 0.0F);
/* 100 */     this.tail5.func_78784_a(110, 37).func_228303_a_(-3.0F, -2.5F, 0.0F, 6.0F, 5.0F, 3.0F, 0.0F, false);
/*     */     
/* 102 */     this.tail6 = new ModelRenderer((Model)this);
/* 103 */     this.tail6.func_78793_a(0.0F, 0.25F, 2.75F);
/* 104 */     this.tail5.func_78792_a(this.tail6);
/* 105 */     setRotationAngle(this.tail6, 0.1745F, 0.0F, 0.0F);
/* 106 */     this.tail6.func_78784_a(116, 46).func_228303_a_(-2.0F, -2.0F, 0.0F, 4.0F, 4.0F, 2.0F, 0.0F, false);
/*     */     
/* 108 */     this.tail7 = new ModelRenderer((Model)this);
/* 109 */     this.tail7.func_78793_a(0.0F, 0.25F, 1.5F);
/* 110 */     this.tail6.func_78792_a(this.tail7);
/* 111 */     setRotationAngle(this.tail7, 0.2182F, 0.0F, 0.0F);
/* 112 */     this.tail7.func_78784_a(120, 53).func_228303_a_(-1.0F, -1.5F, 0.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);
/*     */     
/* 114 */     this.tailOrnament = new ModelRenderer((Model)this);
/* 115 */     this.tailOrnament.func_78793_a(-0.4837F, -0.7728F, 1.0F);
/* 116 */     this.tail7.func_78792_a(this.tailOrnament);
/* 117 */     setRotationAngle(this.tailOrnament, -1.0036F, 0.0F, 0.0F);
/*     */     
/* 119 */     this.tailOrnament_r1 = new ModelRenderer((Model)this);
/* 120 */     this.tailOrnament_r1.func_78793_a(0.0814F, -0.0843F, 0.0F);
/* 121 */     this.tailOrnament.func_78792_a(this.tailOrnament_r1);
/* 122 */     setRotationAngle(this.tailOrnament_r1, 0.0F, 0.0F, 0.3491F);
/* 123 */     this.tailOrnament_r1.func_78784_a(106, 58).func_228303_a_(-3.0477F, -2.3929F, -0.5F, 4.0F, 3.0F, 3.0F, 0.0F, false);
/*     */     
/* 125 */     this.tailOrnament2 = new ModelRenderer((Model)this);
/* 126 */     this.tailOrnament2.func_78793_a(3.4837F, -0.7772F, 0.0F);
/* 127 */     this.tailOrnament.func_78792_a(this.tailOrnament2);
/* 128 */     setRotationAngle(this.tailOrnament2, 0.0F, 0.0F, -0.3491F);
/* 129 */     this.tailOrnament2.func_78784_a(106, 58).func_228303_a_(-3.0F, -2.5F, -0.5F, 4.0F, 3.0F, 3.0F, -0.001F, false);
/*     */     
/* 131 */     this.tailOrnament3 = new ModelRenderer((Model)this);
/* 132 */     this.tailOrnament3.func_78793_a(0.95F, -0.35F, 0.0F);
/* 133 */     this.tailOrnament2.func_78792_a(this.tailOrnament3);
/* 134 */     setRotationAngle(this.tailOrnament3, 0.0F, 0.0F, 0.7854F);
/* 135 */     this.tailOrnament3.func_78784_a(106, 58).func_228303_a_(-2.5F, -2.5F, -0.5F, 3.0F, 3.0F, 3.0F, 0.0F, false);
/*     */     
/* 137 */     this.tailOrnament4 = new ModelRenderer((Model)this);
/* 138 */     this.tailOrnament4.func_78793_a(-0.05F, -3.05F, -1.0F);
/* 139 */     this.tailOrnament3.func_78792_a(this.tailOrnament4);
/* 140 */     this.tailOrnament4.func_78784_a(106, 54).func_228303_a_(-2.5F, -2.5F, -1.5F, 3.0F, 3.0F, 7.0F, 0.0F, false);
/*     */     
/* 142 */     this.tailOrnament5 = new ModelRenderer((Model)this);
/* 143 */     this.tailOrnament5.func_78793_a(-1.2163F, -2.3772F, 0.0F);
/* 144 */     this.tailOrnament.func_78792_a(this.tailOrnament5);
/* 145 */     setRotationAngle(this.tailOrnament5, 0.0F, 0.0F, -0.5672F);
/* 146 */     this.tailOrnament5.func_78784_a(106, 58).func_228303_a_(-2.5F, -2.5F, -0.5F, 3.0F, 3.0F, 3.0F, -0.001F, false);
/*     */     
/* 148 */     this.rightFrontLeg = new ModelRenderer((Model)this);
/* 149 */     this.rightFrontLeg.func_78793_a(-7.75F, 18.0F, -2.5F);
/* 150 */     setRotationAngle(this.rightFrontLeg, 0.0F, -0.2182F, 0.0F);
/*     */     
/* 152 */     this.rightFrontLeg1_r1 = new ModelRenderer((Model)this);
/* 153 */     this.rightFrontLeg1_r1.func_78793_a(-1.1818F, 0.6759F, 0.3827F);
/* 154 */     this.rightFrontLeg.func_78792_a(this.rightFrontLeg1_r1);
/* 155 */     setRotationAngle(this.rightFrontLeg1_r1, 0.0F, 0.0F, -0.5236F);
/* 156 */     this.rightFrontLeg1_r1.func_78784_a(105, 95).func_228303_a_(-3.5F, -2.0F, -1.5F, 7.0F, 4.0F, 4.0F, 0.0F, false);
/*     */     
/* 158 */     this.rightFrontLeg2 = new ModelRenderer((Model)this);
/* 159 */     this.rightFrontLeg2.func_78793_a(-4.0887F, 2.9488F, 0.1398F);
/* 160 */     this.rightFrontLeg.func_78792_a(this.rightFrontLeg2);
/* 161 */     setRotationAngle(this.rightFrontLeg2, -0.0886F, 0.1739F, -0.0154F);
/* 162 */     this.rightFrontLeg2.func_78784_a(105, 105).func_228303_a_(-1.9113F, -2.5F, -1.6398F, 4.0F, 5.0F, 4.0F, 0.0F, false);
/*     */     
/* 164 */     this.rightFrontPaw1 = new ModelRenderer((Model)this);
/* 165 */     this.rightFrontPaw1.func_78793_a(-0.5931F, 3.2271F, 0.2929F);
/* 166 */     this.rightFrontLeg2.func_78792_a(this.rightFrontPaw1);
/* 167 */     setRotationAngle(this.rightFrontPaw1, 0.0873F, 0.0F, 0.0F);
/* 168 */     this.rightFrontPaw1.func_78784_a(105, 116).func_228303_a_(-1.3182F, -1.0F, -1.8327F, 4.0F, 2.0F, 4.0F, 0.01F, false);
/*     */     
/* 170 */     this.rightFrontPaw2 = new ModelRenderer((Model)this);
/* 171 */     this.rightFrontPaw2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 172 */     this.rightFrontPaw1.func_78792_a(this.rightFrontPaw2);
/* 173 */     this.rightFrontPaw2.func_78784_a(108, 125).func_228303_a_(-1.3182F, 0.0F, -2.6827F, 4.0F, 1.0F, 1.0F, 0.02F, false);
/*     */     
/* 175 */     this.rightBackLeg = new ModelRenderer((Model)this);
/* 176 */     this.rightBackLeg.func_78793_a(-7.75F, 18.0F, 9.5F);
/* 177 */     setRotationAngle(this.rightBackLeg, 0.0F, 0.2618F, 0.0F);
/*     */     
/* 179 */     this.rightBackLeg1_r1 = new ModelRenderer((Model)this);
/* 180 */     this.rightBackLeg1_r1.func_78793_a(-0.923F, 0.6759F, -0.5832F);
/* 181 */     this.rightBackLeg.func_78792_a(this.rightBackLeg1_r1);
/* 182 */     setRotationAngle(this.rightBackLeg1_r1, 0.0F, 0.0F, -0.5236F);
/* 183 */     this.rightBackLeg1_r1.func_78784_a(104, 95).func_228303_a_(-3.5F, -2.0F, -1.5F, 7.0F, 4.0F, 4.0F, 0.0F, false);
/*     */     
/* 185 */     this.rightBackLeg2 = new ModelRenderer((Model)this);
/* 186 */     this.rightBackLeg2.func_78793_a(-3.8299F, 2.9488F, -0.8261F);
/* 187 */     this.rightBackLeg.func_78792_a(this.rightBackLeg2);
/* 188 */     setRotationAngle(this.rightBackLeg2, -0.0886F, 0.1739F, -0.0154F);
/* 189 */     this.rightBackLeg2.func_78784_a(105, 105).func_228303_a_(-1.9113F, -2.5F, -1.6398F, 4.0F, 5.0F, 4.0F, 0.0F, false);
/*     */     
/* 191 */     this.rightBackPaw1 = new ModelRenderer((Model)this);
/* 192 */     this.rightBackPaw1.func_78793_a(-0.5931F, 3.2271F, 0.2929F);
/* 193 */     this.rightBackLeg2.func_78792_a(this.rightBackPaw1);
/* 194 */     setRotationAngle(this.rightBackPaw1, 0.0873F, 0.0F, 0.0F);
/* 195 */     this.rightBackPaw1.func_78784_a(105, 116).func_228303_a_(-1.3182F, -1.0F, -1.8327F, 4.0F, 2.0F, 4.0F, 0.01F, false);
/*     */     
/* 197 */     this.rightBackPaw2 = new ModelRenderer((Model)this);
/* 198 */     this.rightBackPaw2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 199 */     this.rightBackPaw1.func_78792_a(this.rightBackPaw2);
/* 200 */     this.rightBackPaw2.func_78784_a(108, 125).func_228303_a_(-1.3182F, 0.0F, -2.6827F, 4.0F, 1.0F, 1.0F, 0.02F, false);
/*     */     
/* 202 */     this.head = new ModelRenderer((Model)this);
/* 203 */     this.head.func_78793_a(0.0F, 15.2667F, -12.25F);
/* 204 */     this.head.func_78784_a(59, 7).func_228303_a_(-5.0F, -3.9667F, -7.75F, 10.0F, 3.0F, 7.0F, 0.0F, false);
/* 205 */     this.head.func_78784_a(49, 106).func_228303_a_(-6.0F, -1.7667F, -8.75F, 12.0F, 6.0F, 8.0F, 0.0F, false);
/*     */     
/* 207 */     this.lowerMouth = new ModelRenderer((Model)this);
/* 208 */     this.lowerMouth.func_78793_a(0.0F, 0.9833F, -8.5F);
/* 209 */     this.head.func_78792_a(this.lowerMouth);
/* 210 */     this.lowerMouth.func_78784_a(0, 103).func_228303_a_(-5.5F, -0.25F, -12.25F, 11.0F, 3.0F, 12.0F, 0.0F, false);
/*     */     
/* 212 */     this.upperMouth = new ModelRenderer((Model)this);
/* 213 */     this.upperMouth.func_78793_a(0.0F, 0.5F, -8.5F);
/* 214 */     this.head.func_78792_a(this.upperMouth);
/* 215 */     this.upperMouth.func_78784_a(0, 87).func_228303_a_(-5.5F, -1.7667F, -12.25F, 11.0F, 2.0F, 12.0F, 0.0F, false);
/*     */     
/* 217 */     this.ornament = new ModelRenderer((Model)this);
/* 218 */     this.ornament.func_78793_a(-0.4837F, -2.2894F, -10.0F);
/* 219 */     this.upperMouth.func_78792_a(this.ornament);
/*     */     
/* 221 */     this.ornament_r1 = new ModelRenderer((Model)this);
/* 222 */     this.ornament_r1.func_78793_a(0.0814F, -0.0843F, 0.0F);
/* 223 */     this.ornament.func_78792_a(this.ornament_r1);
/* 224 */     setRotationAngle(this.ornament_r1, 0.0F, 0.0F, 0.3491F);
/* 225 */     this.ornament_r1.func_78784_a(1, 93).func_228303_a_(-1.0477F, -0.3929F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 227 */     this.ornament2 = new ModelRenderer((Model)this);
/* 228 */     this.ornament2.func_78793_a(1.4837F, -0.0272F, 0.0F);
/* 229 */     this.ornament.func_78792_a(this.ornament2);
/* 230 */     setRotationAngle(this.ornament2, 0.0F, 0.0F, -0.3491F);
/* 231 */     this.ornament2.func_78784_a(1, 93).func_228303_a_(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, -0.001F, false);
/*     */     
/* 233 */     this.ornament3 = new ModelRenderer((Model)this);
/* 234 */     this.ornament3.func_78793_a(0.95F, -0.35F, 0.0F);
/* 235 */     this.ornament2.func_78792_a(this.ornament3);
/* 236 */     setRotationAngle(this.ornament3, 0.0F, 0.0F, 0.7854F);
/* 237 */     this.ornament3.func_78784_a(1, 93).func_228303_a_(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 239 */     this.ornament4 = new ModelRenderer((Model)this);
/* 240 */     this.ornament4.func_78793_a(-0.05F, -0.8F, 0.0F);
/* 241 */     this.ornament3.func_78792_a(this.ornament4);
/* 242 */     this.ornament4.func_78784_a(1, 91).func_228303_a_(-0.5F, -0.5F, -1.5F, 1.0F, 1.0F, 3.0F, 0.0F, false);
/*     */     
/* 244 */     this.ornament5 = new ModelRenderer((Model)this);
/* 245 */     this.ornament5.func_78793_a(-0.9663F, -0.6272F, 0.0F);
/* 246 */     this.ornament.func_78792_a(this.ornament5);
/* 247 */     setRotationAngle(this.ornament5, 0.0F, 0.0F, -0.5672F);
/* 248 */     this.ornament5.func_78784_a(1, 93).func_228303_a_(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, -0.001F, false);
/*     */     
/* 250 */     this.leftBackLeg = new ModelRenderer((Model)this);
/* 251 */     this.leftBackLeg.func_78793_a(7.25F, 18.0F, 9.75F);
/* 252 */     setRotationAngle(this.leftBackLeg, 0.0F, -0.7418F, 0.0F);
/*     */     
/* 254 */     this.leftBackLeg_r1 = new ModelRenderer((Model)this);
/* 255 */     this.leftBackLeg_r1.func_78793_a(1.223F, 1.361F, -2.2513F);
/* 256 */     this.leftBackLeg.func_78792_a(this.leftBackLeg_r1);
/* 257 */     setRotationAngle(this.leftBackLeg_r1, 0.1309F, 0.6981F, 0.48F);
/* 258 */     this.leftBackLeg_r1.func_78784_a(105, 95).func_228303_a_(-4.723F, -2.0F, -0.5F, 7.0F, 4.0F, 4.0F, 0.0F, false);
/*     */     
/* 260 */     this.leftBackLeg2 = new ModelRenderer((Model)this);
/* 261 */     this.leftBackLeg2.func_78793_a(3.3395F, 2.9488F, -2.9942F);
/* 262 */     this.leftBackLeg.func_78792_a(this.leftBackLeg2);
/* 263 */     setRotationAngle(this.leftBackLeg2, -0.0886F, 0.1739F, -0.0154F);
/* 264 */     this.leftBackLeg2.func_78784_a(105, 105).func_228303_a_(-1.9113F, -2.5F, -1.6398F, 4.0F, 5.0F, 4.0F, 0.0F, false);
/*     */     
/* 266 */     this.leftBackPaw = new ModelRenderer((Model)this);
/* 267 */     this.leftBackPaw.func_78793_a(-0.5931F, 3.2271F, 0.2929F);
/* 268 */     this.leftBackLeg2.func_78792_a(this.leftBackPaw);
/* 269 */     setRotationAngle(this.leftBackPaw, 0.0873F, 0.0F, 0.0F);
/* 270 */     this.leftBackPaw.func_78784_a(105, 116).func_228303_a_(-1.3182F, -1.0F, -1.8327F, 4.0F, 2.0F, 4.0F, 0.01F, false);
/*     */     
/* 272 */     this.leftBackPaw2 = new ModelRenderer((Model)this);
/* 273 */     this.leftBackPaw2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 274 */     this.leftBackPaw.func_78792_a(this.leftBackPaw2);
/* 275 */     this.leftBackPaw2.func_78784_a(108, 125).func_228303_a_(-1.3182F, 0.0F, -2.6827F, 4.0F, 1.0F, 1.0F, 0.02F, false);
/*     */     
/* 277 */     this.leftFrontLeg = new ModelRenderer((Model)this);
/* 278 */     this.leftFrontLeg.func_78793_a(7.25F, 18.0F, -2.25F);
/* 279 */     setRotationAngle(this.leftFrontLeg, 0.0F, -0.5236F, 0.0F);
/*     */     
/* 281 */     this.leftFrontLeg_r1 = new ModelRenderer((Model)this);
/* 282 */     this.leftFrontLeg_r1.func_78793_a(1.223F, 1.361F, -2.2513F);
/* 283 */     this.leftFrontLeg.func_78792_a(this.leftFrontLeg_r1);
/* 284 */     setRotationAngle(this.leftFrontLeg_r1, 0.1309F, 0.6981F, 0.48F);
/* 285 */     this.leftFrontLeg_r1.func_78784_a(105, 95).func_228303_a_(-4.723F, -2.0F, -0.5F, 7.0F, 4.0F, 4.0F, 0.0F, false);
/*     */     
/* 287 */     this.leftFrontLeg2 = new ModelRenderer((Model)this);
/* 288 */     this.leftFrontLeg2.func_78793_a(3.3395F, 2.9488F, -2.9942F);
/* 289 */     this.leftFrontLeg.func_78792_a(this.leftFrontLeg2);
/* 290 */     setRotationAngle(this.leftFrontLeg2, -0.1007F, 0.5214F, -0.0503F);
/* 291 */     this.leftFrontLeg2.func_78784_a(105, 105).func_228303_a_(-1.9113F, -2.5F, -1.6398F, 4.0F, 5.0F, 4.0F, 0.0F, false);
/*     */     
/* 293 */     this.leftFrontPaw = new ModelRenderer((Model)this);
/* 294 */     this.leftFrontPaw.func_78793_a(-0.5931F, 3.2271F, 0.2929F);
/* 295 */     this.leftFrontLeg2.func_78792_a(this.leftFrontPaw);
/* 296 */     setRotationAngle(this.leftFrontPaw, 0.0873F, 0.0F, 0.0F);
/* 297 */     this.leftFrontPaw.func_78784_a(105, 116).func_228303_a_(-1.3182F, -1.0F, -1.8327F, 4.0F, 2.0F, 4.0F, 0.01F, false);
/*     */     
/* 299 */     this.leftFrontPaw2 = new ModelRenderer((Model)this);
/* 300 */     this.leftFrontPaw2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 301 */     this.leftFrontPaw.func_78792_a(this.leftFrontPaw2);
/* 302 */     this.leftFrontPaw2.func_78784_a(108, 125).func_228303_a_(-1.3182F, 0.0F, -2.6827F, 4.0F, 1.0F, 1.0F, 0.02F, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_212843_a_(T entity, float limbSwing, float limbSwingAmount, float partialTicks) {
/* 307 */     if (entity instanceof BananawaniEntity) {
/* 308 */       this.biteAnimationProgress = ((BananawaniEntity)entity).getBiteAnimationProgress(partialTicks);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 315 */     this.rightFrontLeg.field_78795_f = 0.3F;
/* 316 */     this.rightFrontLeg.field_78796_g = 0.0F;
/* 317 */     this.rightFrontLeg.field_78808_h = 0.0F;
/*     */     
/* 319 */     this.leftFrontLeg.field_78795_f = 0.0F;
/* 320 */     this.leftFrontLeg.field_78796_g = -0.6F;
/* 321 */     this.leftFrontLeg.field_78808_h = 0.0F;
/*     */     
/* 323 */     this.rightBackLeg.field_78795_f = 0.3F;
/* 324 */     this.rightBackLeg.field_78796_g = 0.0F;
/* 325 */     this.rightBackLeg.field_78808_h = 0.0F;
/*     */     
/* 327 */     this.leftBackLeg.field_78795_f = 0.0F;
/* 328 */     this.leftBackLeg.field_78796_g = -0.3F;
/* 329 */     this.leftBackLeg.field_78808_h = 0.0F;
/*     */     
/* 331 */     float f = 1.0F;
/* 332 */     float speed = 0.25F;
/* 333 */     float spread = 0.4F;
/* 334 */     if (!entity.func_184186_bw()) {
/* 335 */       speed = 0.8F;
/*     */     }
/* 337 */     this.rightFrontLeg.field_78795_f = MathHelper.func_76134_b(limbSwing * speed) * spread * limbSwingAmount / f;
/* 338 */     this.leftFrontLeg.field_78795_f = MathHelper.func_76134_b(limbSwing * speed + 3.1415927F) * spread * limbSwingAmount / f;
/* 339 */     this.rightBackLeg.field_78795_f = MathHelper.func_76126_a(limbSwing * speed) * spread * limbSwingAmount / f;
/* 340 */     this.leftBackLeg.field_78795_f = MathHelper.func_76126_a(limbSwing * speed + 3.1415927F) * spread * limbSwingAmount / f;
/*     */     
/* 342 */     if (this.biteAnimationProgress < 1.0F) {
/* 343 */       float angle = MathHelper.func_76126_a(this.biteAnimationProgress * 0.5F * 3.1415927F) * 0.7F;
/* 344 */       this.upperMouth.field_78795_f = (float)Math.toRadians(-40.0D) + angle;
/* 345 */       this.lowerMouth.field_78795_f = (float)Math.toRadians(40.0D) - angle;
/*     */     }
/* 347 */     else if (this.biteAnimationProgress >= 1.0F) {
/* 348 */       ((LivingEntity)entity).field_82175_bq = false;
/* 349 */       this.upperMouth.field_78795_f = (float)Math.toRadians(0.0D);
/* 350 */       this.lowerMouth.field_78795_f = (float)Math.toRadians(0.0D);
/*     */     } 
/*     */     
/* 353 */     this.tail.field_78796_g = MathHelper.func_76126_a(ageInTicks * 0.05F) * 0.1F;
/* 354 */     this.tail3.field_78796_g = MathHelper.func_76126_a(ageInTicks * 0.05F) * 0.1F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 360 */     if (this.field_217114_e) {
/* 361 */       matrixStack.func_227862_a_(0.5F, 0.5F, 0.5F);
/* 362 */       matrixStack.func_227861_a_(0.0D, 1.5D, 0.0D);
/*     */     } 
/*     */     
/* 365 */     this.body.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/* 366 */     this.tail.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/* 367 */     this.rightFrontLeg.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/* 368 */     this.rightBackLeg.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/* 369 */     this.head.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/* 370 */     this.leftBackLeg.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/* 371 */     this.leftFrontLeg.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/*     */   }
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 375 */     modelRenderer.field_78795_f = x;
/* 376 */     modelRenderer.field_78796_g = y;
/* 377 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\entities\mobs\animals\BananawaniModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */