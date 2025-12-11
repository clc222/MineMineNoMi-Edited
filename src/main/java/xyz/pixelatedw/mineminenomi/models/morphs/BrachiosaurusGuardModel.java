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
/*     */ public class BrachiosaurusGuardModel<T extends LivingEntity>
/*     */   extends MorphModel<T> implements IHasArm {
/*     */   private final ModelRenderer rightBackLeg;
/*     */   private final ModelRenderer rightBackLeg_r1;
/*     */   private final ModelRenderer rightBackLeg2;
/*     */   private final ModelRenderer rightBackLeg2_r1;
/*     */   private final ModelRenderer leftBackLeg;
/*     */   private final ModelRenderer leftBackLeg_r1;
/*     */   private final ModelRenderer leftBackLeg2;
/*     */   private final ModelRenderer leftBackLeg2_r1;
/*     */   private final ModelRenderer rightFrontLeg;
/*     */   private final ModelRenderer rightFrontLeg_r1;
/*     */   private final ModelRenderer rightFrontLeg2;
/*     */   private final ModelRenderer rightFrontLeg2_r1;
/*     */   private final ModelRenderer leftFrontLeg;
/*     */   private final ModelRenderer leftFrontLeg_r1;
/*     */   private final ModelRenderer leftFrontLeg2;
/*     */   private final ModelRenderer leftFrontLeg2_r1;
/*     */   private final ModelRenderer body;
/*     */   private final ModelRenderer tail;
/*     */   private final ModelRenderer tail2;
/*     */   private final ModelRenderer tail3;
/*     */   private final ModelRenderer head;
/*     */   private final ModelRenderer mouth;
/*     */   private final ModelRenderer upperMouth;
/*     */   private final ModelRenderer upperMouthTeeth;
/*     */   private final ModelRenderer lowerMouth;
/*     */   private final ModelRenderer lowerMouthTeeth;
/*     */   private final ModelRenderer neck;
/*     */   private final ModelRenderer neck1_r1;
/*     */   private final ModelRenderer neck2;
/*     */   private final ModelRenderer neck2_r1;
/*     */   private final ModelRenderer neck3;
/*     */   private final ModelRenderer neck3_r1;
/*     */   private final ModelRenderer neck4;
/*     */   private final ModelRenderer neck4_r1;
/*     */   private final ModelRenderer neck5;
/*     */   private final ModelRenderer neck5_r1;
/*     */   private final ModelRenderer neck6;
/*     */   private final ModelRenderer neck6_r1;
/*     */   private final ModelRenderer neck7;
/*     */   private final ModelRenderer neck7_r1;
/*     */   
/*     */   public BrachiosaurusGuardModel() {
/*  59 */     super(1.0F);
/*  60 */     this.field_78090_t = 128;
/*  61 */     this.field_78089_u = 128;
/*     */     
/*  63 */     this.rightBackLeg = new ModelRenderer((Model)this);
/*  64 */     this.rightBackLeg.func_78793_a(-3.0F, 10.3009F, 6.3044F);
/*     */ 
/*     */     
/*  67 */     this.rightBackLeg_r1 = new ModelRenderer((Model)this);
/*  68 */     this.rightBackLeg_r1.func_78793_a(-2.0F, 1.0F, 0.0F);
/*  69 */     this.rightBackLeg.func_78792_a(this.rightBackLeg_r1);
/*  70 */     setRotationAngle(this.rightBackLeg_r1, -0.1745F, 0.0F, 0.0F);
/*  71 */     this.rightBackLeg_r1.func_78784_a(22, 92).func_228303_a_(-2.0F, -3.5F, -3.0F, 4.0F, 7.0F, 6.0F, 0.01F, false);
/*     */     
/*  73 */     this.rightBackLeg2 = new ModelRenderer((Model)this);
/*  74 */     this.rightBackLeg2.func_78793_a(-2.0F, 9.3921F, -0.0439F);
/*  75 */     this.rightBackLeg.func_78792_a(this.rightBackLeg2);
/*  76 */     this.rightBackLeg2.func_78784_a(21, 119).func_228303_a_(-2.0F, 0.4158F, -2.1965F, 4.0F, 4.0F, 5.0F, -0.01F, false);
/*     */     
/*  78 */     this.rightBackLeg2_r1 = new ModelRenderer((Model)this);
/*  79 */     this.rightBackLeg2_r1.func_78793_a(0.0F, -2.4158F, -0.3035F);
/*  80 */     this.rightBackLeg2.func_78792_a(this.rightBackLeg2_r1);
/*  81 */     setRotationAngle(this.rightBackLeg2_r1, 0.1745F, 0.0F, 0.0F);
/*  82 */     this.rightBackLeg2_r1.func_78784_a(21, 106).func_228303_a_(-2.0F, -3.5F, -2.5F, 4.0F, 7.0F, 5.0F, 0.0F, false);
/*     */     
/*  84 */     this.leftBackLeg = new ModelRenderer((Model)this);
/*  85 */     this.leftBackLeg.func_78793_a(3.0F, 10.3009F, 6.3044F);
/*     */ 
/*     */     
/*  88 */     this.leftBackLeg_r1 = new ModelRenderer((Model)this);
/*  89 */     this.leftBackLeg_r1.func_78793_a(2.0F, 1.0F, 0.0F);
/*  90 */     this.leftBackLeg.func_78792_a(this.leftBackLeg_r1);
/*  91 */     setRotationAngle(this.leftBackLeg_r1, -0.1745F, 0.0F, 0.0F);
/*  92 */     this.leftBackLeg_r1.func_78784_a(22, 92).func_228303_a_(-2.0F, -3.5F, -3.0F, 4.0F, 7.0F, 6.0F, 0.01F, false);
/*     */     
/*  94 */     this.leftBackLeg2 = new ModelRenderer((Model)this);
/*  95 */     this.leftBackLeg2.func_78793_a(2.0F, 9.3921F, -0.0439F);
/*  96 */     this.leftBackLeg.func_78792_a(this.leftBackLeg2);
/*  97 */     this.leftBackLeg2.func_78784_a(21, 119).func_228303_a_(-2.0F, 0.4158F, -2.1965F, 4.0F, 4.0F, 5.0F, -0.01F, false);
/*     */     
/*  99 */     this.leftBackLeg2_r1 = new ModelRenderer((Model)this);
/* 100 */     this.leftBackLeg2_r1.func_78793_a(0.0F, -2.4158F, -0.3035F);
/* 101 */     this.leftBackLeg2.func_78792_a(this.leftBackLeg2_r1);
/* 102 */     setRotationAngle(this.leftBackLeg2_r1, 0.1745F, 0.0F, 0.0F);
/* 103 */     this.leftBackLeg2_r1.func_78784_a(21, 106).func_228303_a_(-2.0F, -3.5F, -2.5F, 4.0F, 7.0F, 5.0F, 0.0F, false);
/*     */     
/* 105 */     this.rightFrontLeg = new ModelRenderer((Model)this);
/* 106 */     this.rightFrontLeg.func_78793_a(-4.0F, 10.9056F, -7.0765F);
/*     */ 
/*     */     
/* 109 */     this.rightFrontLeg_r1 = new ModelRenderer((Model)this);
/* 110 */     this.rightFrontLeg_r1.func_78793_a(-2.5F, 1.0F, 0.0F);
/* 111 */     this.rightFrontLeg.func_78792_a(this.rightFrontLeg_r1);
/* 112 */     setRotationAngle(this.rightFrontLeg_r1, 0.1745F, 0.0F, 0.0F);
/* 113 */     this.rightFrontLeg_r1.func_78784_a(0, 91).func_228303_a_(-1.5F, -3.5F, -3.0F, 4.0F, 7.0F, 6.0F, 0.0F, false);
/*     */     
/* 115 */     this.rightFrontLeg2 = new ModelRenderer((Model)this);
/* 116 */     this.rightFrontLeg2.func_78793_a(-2.0F, 4.4468F, 0.7974F);
/* 117 */     this.rightFrontLeg.func_78792_a(this.rightFrontLeg2);
/* 118 */     this.rightFrontLeg2.func_78784_a(0, 118).func_228303_a_(-2.0F, 5.5064F, -3.7849F, 4.0F, 4.0F, 5.0F, 0.01F, false);
/*     */     
/* 120 */     this.rightFrontLeg2_r1 = new ModelRenderer((Model)this);
/* 121 */     this.rightFrontLeg2_r1.func_78793_a(0.0F, 2.4936F, -0.7151F);
/* 122 */     this.rightFrontLeg2.func_78792_a(this.rightFrontLeg2_r1);
/* 123 */     setRotationAngle(this.rightFrontLeg2_r1, -0.1745F, 0.0F, 0.0F);
/* 124 */     this.rightFrontLeg2_r1.func_78784_a(0, 105).func_228303_a_(-2.0F, -3.5F, -2.5F, 4.0F, 7.0F, 5.0F, 0.01F, false);
/*     */     
/* 126 */     this.leftFrontLeg = new ModelRenderer((Model)this);
/* 127 */     this.leftFrontLeg.func_78793_a(4.0F, 10.9056F, -7.0765F);
/*     */ 
/*     */     
/* 130 */     this.leftFrontLeg_r1 = new ModelRenderer((Model)this);
/* 131 */     this.leftFrontLeg_r1.func_78793_a(1.5F, 1.0F, 0.0F);
/* 132 */     this.leftFrontLeg.func_78792_a(this.leftFrontLeg_r1);
/* 133 */     setRotationAngle(this.leftFrontLeg_r1, 0.1745F, 0.0F, 0.0F);
/* 134 */     this.leftFrontLeg_r1.func_78784_a(0, 91).func_228303_a_(-1.5F, -3.5F, -3.0F, 4.0F, 7.0F, 6.0F, 0.0F, false);
/*     */     
/* 136 */     this.leftFrontLeg2 = new ModelRenderer((Model)this);
/* 137 */     this.leftFrontLeg2.func_78793_a(2.0F, 4.4468F, 0.7974F);
/* 138 */     this.leftFrontLeg.func_78792_a(this.leftFrontLeg2);
/* 139 */     this.leftFrontLeg2.func_78784_a(0, 118).func_228303_a_(-2.0F, 5.5064F, -3.7849F, 4.0F, 4.0F, 5.0F, 0.01F, false);
/*     */     
/* 141 */     this.leftFrontLeg2_r1 = new ModelRenderer((Model)this);
/* 142 */     this.leftFrontLeg2_r1.func_78793_a(0.0F, 2.4936F, -0.7151F);
/* 143 */     this.leftFrontLeg2.func_78792_a(this.leftFrontLeg2_r1);
/* 144 */     setRotationAngle(this.leftFrontLeg2_r1, -0.1745F, 0.0F, 0.0F);
/* 145 */     this.leftFrontLeg2_r1.func_78784_a(0, 105).func_228303_a_(-2.0F, -3.5F, -2.5F, 4.0F, 7.0F, 5.0F, 0.01F, false);
/*     */     
/* 147 */     this.body = new ModelRenderer((Model)this);
/* 148 */     this.body.func_78793_a(0.0F, 7.375F, -4.0F);
/* 149 */     this.body.func_78784_a(0, 0).func_228303_a_(-5.0F, -8.375F, -4.0F, 10.0F, 17.0F, 14.0F, 0.0F, false);
/* 150 */     this.body.func_78784_a(0, 32).func_228303_a_(-4.5F, -7.375F, -7.0F, 9.0F, 15.0F, 3.0F, 0.0F, false);
/* 151 */     this.body.func_78784_a(0, 51).func_228303_a_(-4.0F, -6.875F, -9.0F, 8.0F, 13.0F, 2.0F, 0.0F, false);
/* 152 */     this.body.func_78784_a(50, 0).func_228303_a_(-4.5F, -7.375F, 9.0F, 9.0F, 15.0F, 3.0F, 0.0F, false);
/* 153 */     this.body.func_78784_a(50, 19).func_228303_a_(-4.0F, -6.875F, 12.0F, 8.0F, 13.0F, 3.0F, 0.0F, false);
/* 154 */     this.body.func_78784_a(28, 33).func_228303_a_(-3.5F, -6.375F, 15.0F, 7.0F, 12.0F, 3.0F, 0.0F, false);
/*     */     
/* 156 */     this.tail = new ModelRenderer((Model)this);
/* 157 */     this.tail.func_78793_a(0.0F, 0.0F, 18.0F);
/* 158 */     this.body.func_78792_a(this.tail);
/* 159 */     this.tail.func_78784_a(77, 1).func_228303_a_(-2.0F, -3.0F, -1.0F, 4.0F, 6.0F, 10.0F, 0.0F, false);
/*     */     
/* 161 */     this.tail2 = new ModelRenderer((Model)this);
/* 162 */     this.tail2.func_78793_a(-0.5F, -0.75F, 6.0F);
/* 163 */     this.tail.func_78792_a(this.tail2);
/* 164 */     this.tail2.func_78784_a(74, 18).func_228303_a_(-1.0F, -2.0F, 0.0F, 3.0F, 5.0F, 14.0F, 0.0F, false);
/*     */     
/* 166 */     this.tail3 = new ModelRenderer((Model)this);
/* 167 */     this.tail3.func_78793_a(-0.5F, -0.75F, 8.0F);
/* 168 */     this.tail2.func_78792_a(this.tail3);
/* 169 */     this.tail3.func_78784_a(72, 38).func_228303_a_(0.0F, -1.0F, 0.0F, 2.0F, 4.0F, 15.0F, 0.0F, false);
/*     */     
/* 171 */     this.head = new ModelRenderer((Model)this);
/* 172 */     this.head.func_78793_a(0.0F, 4.0F, -11.0F);
/*     */ 
/*     */     
/* 175 */     this.mouth = new ModelRenderer((Model)this);
/* 176 */     this.mouth.func_78793_a(-1.0F, -109.0F, -14.0F);
/* 177 */     this.head.func_78792_a(this.mouth);
/*     */ 
/*     */     
/* 180 */     this.upperMouth = new ModelRenderer((Model)this);
/* 181 */     this.upperMouth.func_78793_a(1.0F, 86.1616F, 4.9651F);
/* 182 */     this.mouth.func_78792_a(this.upperMouth);
/* 183 */     setRotationAngle(this.upperMouth, -0.1309F, 0.0F, 0.0F);
/* 184 */     this.upperMouth.func_78784_a(68, 100).func_228303_a_(-2.5F, -3.0999F, -2.6049F, 5.0F, 4.0F, 3.0F, 0.0F, false);
/* 185 */     this.upperMouth.func_78784_a(68, 108).func_228303_a_(-2.0F, -2.0999F, -3.6049F, 4.0F, 3.0F, 1.0F, 0.0F, false);
/* 186 */     this.upperMouth.func_78784_a(68, 113).func_228303_a_(-1.5F, -1.0999F, -5.6049F, 3.0F, 2.0F, 2.0F, 0.0F, false);
/*     */     
/* 188 */     this.upperMouthTeeth = new ModelRenderer((Model)this);
/* 189 */     this.upperMouthTeeth.func_78793_a(-1.0F, -79.1968F, -23.5542F);
/* 190 */     this.upperMouth.func_78792_a(this.upperMouthTeeth);
/* 191 */     this.upperMouthTeeth.func_78784_a(68, 118).func_228303_a_(2.25F, 79.8469F, 18.1993F, 0.0F, 1.0F, 5.0F, 0.0F, false);
/* 192 */     this.upperMouthTeeth.func_78784_a(68, 118).func_228303_a_(-0.25F, 79.8469F, 18.1993F, 0.0F, 1.0F, 5.0F, 0.0F, false);
/* 193 */     this.upperMouthTeeth.func_78784_a(72, 126).func_228303_a_(0.0F, 79.8469F, 18.1993F, 2.0F, 1.0F, 0.0F, 0.0F, false);
/*     */     
/* 195 */     this.lowerMouth = new ModelRenderer((Model)this);
/* 196 */     this.lowerMouth.func_78793_a(1.0F, 87.2041F, 4.72F);
/* 197 */     this.mouth.func_78792_a(this.lowerMouth);
/* 198 */     setRotationAngle(this.lowerMouth, 0.2182F, 0.0F, 0.0F);
/* 199 */     this.lowerMouth.func_78784_a(85, 104).func_228303_a_(-2.5F, 0.0434F, -1.3357F, 5.0F, 1.0F, 2.0F, 0.0F, false);
/* 200 */     this.lowerMouth.func_78784_a(85, 109).func_228303_a_(-2.0F, 0.0434F, -2.3357F, 4.0F, 1.0F, 1.0F, 0.0F, false);
/* 201 */     this.lowerMouth.func_78784_a(84, 112).func_228303_a_(-1.5F, 0.0434F, -5.3357F, 3.0F, 1.0F, 3.0F, 0.0F, false);
/*     */     
/* 203 */     this.lowerMouthTeeth = new ModelRenderer((Model)this);
/* 204 */     this.lowerMouthTeeth.func_78793_a(-5.0F, -140.6931F, -19.4114F);
/* 205 */     this.lowerMouth.func_78792_a(this.lowerMouthTeeth);
/* 206 */     this.lowerMouthTeeth.func_78784_a(84, 118).func_228303_a_(6.3F, 139.9865F, 14.3257F, 0.0F, 1.0F, 5.0F, 0.0F, false);
/* 207 */     this.lowerMouthTeeth.func_78784_a(84, 118).func_228303_a_(3.7F, 139.9865F, 14.3257F, 0.0F, 1.0F, 5.0F, 0.0F, false);
/* 208 */     this.lowerMouthTeeth.func_78784_a(87, 126).func_228303_a_(4.0F, 139.9865F, 14.3257F, 2.0F, 1.0F, 0.0F, 0.0F, false);
/*     */     
/* 210 */     this.neck = new ModelRenderer((Model)this);
/* 211 */     this.neck.func_78793_a(-0.5F, -1.7431F, -0.7119F);
/* 212 */     this.head.func_78792_a(this.neck);
/*     */ 
/*     */     
/* 215 */     this.neck1_r1 = new ModelRenderer((Model)this);
/* 216 */     this.neck1_r1.func_78793_a(0.0F, -1.5359F, -2.0F);
/* 217 */     this.neck.func_78792_a(this.neck1_r1);
/* 218 */     setRotationAngle(this.neck1_r1, 0.5236F, 0.0F, 0.0F);
/* 219 */     this.neck1_r1.func_78784_a(45, 112).func_228303_a_(-2.0F, -4.0F, -2.5F, 5.0F, 10.0F, 5.0F, 0.0F, false);
/*     */     
/* 221 */     this.neck2 = new ModelRenderer((Model)this);
/* 222 */     this.neck2.func_78793_a(0.0F, -4.0762F, -3.6967F);
/* 223 */     this.neck.func_78792_a(this.neck2);
/*     */ 
/*     */     
/* 226 */     this.neck2_r1 = new ModelRenderer((Model)this);
/* 227 */     this.neck2_r1.func_78793_a(0.0F, -4.3879F, -2.3033F);
/* 228 */     this.neck2.func_78792_a(this.neck2_r1);
/* 229 */     setRotationAngle(this.neck2_r1, 0.3491F, 0.0F, 0.0F);
/* 230 */     this.neck2_r1.func_78784_a(45, 100).func_228303_a_(-2.0F, -1.25F, -1.75F, 5.0F, 6.0F, 5.0F, -0.01F, false);
/*     */     
/* 232 */     this.neck3 = new ModelRenderer((Model)this);
/* 233 */     this.neck3.func_78793_a(0.0F, -4.6906F, -1.2016F);
/* 234 */     this.neck2.func_78792_a(this.neck3);
/* 235 */     setRotationAngle(this.neck3, -0.1309F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 238 */     this.neck3_r1 = new ModelRenderer((Model)this);
/* 239 */     this.neck3_r1.func_78793_a(0.0F, -2.9848F, -1.1736F);
/* 240 */     this.neck3.func_78792_a(this.neck3_r1);
/* 241 */     setRotationAngle(this.neck3_r1, 0.1745F, 0.0F, 0.0F);
/* 242 */     this.neck3_r1.func_78784_a(45, 90).func_228303_a_(-2.0F, -1.0F, -2.5F, 5.0F, 4.0F, 5.0F, 0.0F, false);
/*     */     
/* 244 */     this.neck4 = new ModelRenderer((Model)this);
/* 245 */     this.neck4.func_78793_a(0.0F, -3.841F, -1.054F);
/* 246 */     this.neck3.func_78792_a(this.neck4);
/*     */ 
/*     */     
/* 249 */     this.neck4_r1 = new ModelRenderer((Model)this);
/* 250 */     this.neck4_r1.func_78793_a(0.0F, -5.0526F, -1.1616F);
/* 251 */     this.neck4.func_78792_a(this.neck4_r1);
/* 252 */     setRotationAngle(this.neck4_r1, -0.0436F, 0.0F, 0.0F);
/* 253 */     this.neck4_r1.func_78784_a(45, 76).func_228303_a_(-2.0F, -1.5F, -1.25F, 5.0F, 7.0F, 5.0F, 0.01F, false);
/*     */     
/* 255 */     this.neck5 = new ModelRenderer((Model)this);
/* 256 */     this.neck5.func_78793_a(0.0F, -5.9933F, 0.3053F);
/* 257 */     this.neck4.func_78792_a(this.neck5);
/* 258 */     setRotationAngle(this.neck5, 0.3927F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 261 */     this.neck5_r1 = new ModelRenderer((Model)this);
/* 262 */     this.neck5_r1.func_78793_a(0.0F, 1.9407F, -1.4669F);
/* 263 */     this.neck5.func_78792_a(this.neck5_r1);
/* 264 */     setRotationAngle(this.neck5_r1, -0.0436F, 0.0F, 0.0F);
/* 265 */     this.neck5_r1.func_78784_a(45, 67).func_228303_a_(-2.0F, -4.5F, -1.25F, 5.0F, 3.0F, 5.0F, 0.0F, false);
/*     */     
/* 267 */     this.neck6 = new ModelRenderer((Model)this);
/* 268 */     this.neck6.func_78793_a(0.0F, -1.5F, 0.25F);
/* 269 */     this.neck5.func_78792_a(this.neck6);
/* 270 */     setRotationAngle(this.neck6, 0.5672F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 273 */     this.neck6_r1 = new ModelRenderer((Model)this);
/* 274 */     this.neck6_r1.func_78793_a(0.0F, 1.9407F, -1.4669F);
/* 275 */     this.neck6.func_78792_a(this.neck6_r1);
/* 276 */     setRotationAngle(this.neck6_r1, -0.0436F, 0.0F, 0.0F);
/* 277 */     this.neck6_r1.func_78784_a(45, 58).func_228303_a_(-2.0F, -4.5F, -1.25F, 5.0F, 3.0F, 5.0F, 0.015F, false);
/*     */     
/* 279 */     this.neck7 = new ModelRenderer((Model)this);
/* 280 */     this.neck7.func_78793_a(0.0F, -1.5F, 0.25F);
/* 281 */     this.neck6.func_78792_a(this.neck7);
/* 282 */     setRotationAngle(this.neck7, 0.6545F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 285 */     this.neck7_r1 = new ModelRenderer((Model)this);
/* 286 */     this.neck7_r1.func_78793_a(0.0F, 1.9407F, -1.4669F);
/* 287 */     this.neck7.func_78792_a(this.neck7_r1);
/* 288 */     setRotationAngle(this.neck7_r1, -0.0436F, 0.0F, 0.0F);
/* 289 */     this.neck7_r1.func_78784_a(45, 49).func_228303_a_(-2.0F, -4.5F, -1.25F, 5.0F, 3.0F, 5.0F, 0.017F, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 295 */     this.rightBackLeg.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 296 */     this.leftBackLeg.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 297 */     this.rightFrontLeg.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 298 */     this.leftFrontLeg.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 299 */     this.body.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 300 */     this.head.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 307 */     float limbSpeed = 0.3F;
/* 308 */     if (entity.func_70051_ag())
/* 309 */       limbSpeed = 0.4F; 
/* 310 */     this.rightFrontLeg.field_78795_f = MathHelper.func_76134_b(limbSwing * limbSpeed) * 0.3F * limbSwingAmount;
/* 311 */     this.leftFrontLeg.field_78795_f = MathHelper.func_76134_b(limbSwing * limbSpeed) * 0.4F * limbSwingAmount;
/* 312 */     this.rightBackLeg.field_78795_f = MathHelper.func_76134_b(limbSwing * limbSpeed + 3.1415927F) * 0.3F * limbSwingAmount;
/* 313 */     this.leftBackLeg.field_78795_f = MathHelper.func_76134_b(limbSwing * limbSpeed + 3.1415927F) * 0.4F * limbSwingAmount;
/*     */ 
/*     */     
/* 316 */     this.tail.field_78796_g = (float)(this.tail.field_78796_g + Math.sin(ageInTicks * 0.01D) / 20.0D);
/* 317 */     this.tail.field_78795_f = (float)(this.tail.field_78795_f + Math.sin(ageInTicks * 0.005D) / 10.0D);
/* 318 */     this.tail2.field_78796_g = (float)(this.tail2.field_78796_g + Math.sin(ageInTicks * 0.01D) / 10.0D);
/* 319 */     this.tail2.field_78795_f = (float)(this.tail2.field_78795_f + Math.sin(ageInTicks * 0.005D) / 5.0D);
/* 320 */     this.tail3.field_78796_g = (float)(this.tail3.field_78796_g + Math.sin(ageInTicks * 0.01D) / 10.0D);
/* 321 */     this.tail3.field_78795_f = (float)(this.tail3.field_78795_f + Math.sin(ageInTicks * 0.005D) / 5.0D);
/*     */ 
/*     */     
/* 324 */     this.field_217112_c = ((LivingEntity)entity).field_70733_aJ;
/* 325 */     if (this.field_217112_c > 0.0F) {
/*     */       
/* 327 */       this.head.field_78796_g += this.body.field_78796_g;
/* 328 */       float f1 = 1.0F - this.field_217112_c;
/* 329 */       f1 *= f1;
/* 330 */       f1 *= f1;
/* 331 */       f1 = 1.0F - f1;
/* 332 */       float f2 = MathHelper.func_76126_a(f1 * 3.1415927F);
/* 333 */       float f3 = MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * -(this.head.field_78795_f - 0.1F) * 0.15F;
/* 334 */       this.head.field_78795_f = (float)(this.head.field_78795_f + f2 * 1.8D + f3);
/* 335 */       this.head.field_78808_h -= MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * -0.2F;
/*     */     } 
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
/*     */   public ModelRenderer func_187074_a(HandSide side) {
/* 359 */     return this.head;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225599_a_(HandSide side, MatrixStack matrixStack) {
/* 365 */     this.head.func_228307_a_(matrixStack);
/* 366 */     matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(-90.0F));
/* 367 */     matrixStack.func_227863_a_(Vector3f.field_229183_f_.func_229187_a_(90.0F));
/* 368 */     matrixStack.func_227861_a_(-1.3D, 0.25D, -0.05D);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\morphs\BrachiosaurusGuardModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */