/*     */ package xyz.pixelatedw.mineminenomi.models.morphs;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.HandSide;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.vector.Vector3f;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphModel;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ 
/*     */ public class PhoenixAssaultPartialModel<T extends LivingEntity>
/*     */   extends MorphModel<T> {
/*     */   public final ModelRenderer rightWing;
/*     */   private final ModelRenderer rightWing2;
/*     */   private final ModelRenderer rightWingLayer1;
/*     */   private final ModelRenderer rightWingLayer1b;
/*     */   private final ModelRenderer rightWingLayer2;
/*     */   private final ModelRenderer rightWingLayer2b;
/*     */   public final ModelRenderer leftWing;
/*     */   private final ModelRenderer leftWing2;
/*     */   private final ModelRenderer leftWingLayer1;
/*     */   private final ModelRenderer leftWingLayer1b;
/*     */   private final ModelRenderer leftWingLayer2;
/*     */   private final ModelRenderer leftWingLayer2b;
/*     */   public final ModelRenderer field_178721_j;
/*     */   public final ModelRenderer rightLeg2;
/*     */   public final ModelRenderer rightLeg3;
/*     */   private final ModelRenderer rightTalon1;
/*     */   private final ModelRenderer rightTalon1b;
/*     */   private final ModelRenderer rightTalon1b_r1;
/*     */   private final ModelRenderer rightTalon1c;
/*     */   private final ModelRenderer rightTalon1c_r1;
/*     */   private final ModelRenderer rightTalon2;
/*     */   private final ModelRenderer rightTalon2b;
/*     */   private final ModelRenderer rightTalon2b_r1;
/*     */   private final ModelRenderer rightTalon2c;
/*     */   private final ModelRenderer rightTalon2c_r1;
/*     */   private final ModelRenderer rightTalon3;
/*     */   private final ModelRenderer rightTalon3_r1;
/*     */   private final ModelRenderer rightTalon3b;
/*     */   private final ModelRenderer rightTalon3c;
/*     */   private final ModelRenderer rightTalon4;
/*     */   private final ModelRenderer rightTalon4b;
/*     */   private final ModelRenderer rightTalon4b_r1;
/*     */   public final ModelRenderer field_178722_k;
/*     */   public final ModelRenderer leftLeg2;
/*     */   public final ModelRenderer leftLeg3;
/*     */   private final ModelRenderer leftTalon1;
/*     */   private final ModelRenderer leftTalon1_r1;
/*     */   private final ModelRenderer leftTalon1b;
/*     */   private final ModelRenderer leftTalon1c;
/*     */   private final ModelRenderer leftTalon1c_r1;
/*     */   private final ModelRenderer leftTalon2;
/*     */   private final ModelRenderer leftTalon2_r1;
/*     */   private final ModelRenderer leftTalon2b;
/*     */   private final ModelRenderer leftTalon2c;
/*     */   private final ModelRenderer leftTalon3;
/*     */   private final ModelRenderer leftTalon3_r1;
/*     */   private final ModelRenderer leftTalon3b;
/*     */   private final ModelRenderer leftTalon3c;
/*     */   private final ModelRenderer leftTalon4;
/*     */   private final ModelRenderer leftTalon4b;
/*     */   public final ModelRenderer tail1;
/*     */   private final ModelRenderer tail1b;
/*     */   private final ModelRenderer tail1c;
/*     */   public final ModelRenderer tail2;
/*     */   private final ModelRenderer tail2b;
/*     */   private final ModelRenderer tail2c;
/*     */   public final ModelRenderer tail3;
/*     */   private final ModelRenderer tail3b;
/*     */   private final ModelRenderer tail3c;
/*     */   
/*     */   public PhoenixAssaultPartialModel() {
/*  79 */     super(1.0F);
/*  80 */     this.field_78090_t = 128;
/*  81 */     this.field_78089_u = 64;
/*     */     
/*  83 */     this.rightWing = new ModelRenderer((Model)this);
/*  84 */     this.rightWing.func_78793_a(-1.5F, 1.0F, 2.5F);
/*  85 */     setRotationAngle(this.rightWing, 1.5708F, 0.0F, -1.3963F);
/*  86 */     this.rightWing.func_78784_a(71, 54).func_228303_a_(-13.0F, -5.0F, 1.0F, 13.0F, 10.0F, 0.0F, 0.0F, false);
/*     */     
/*  88 */     this.rightWing2 = new ModelRenderer((Model)this);
/*  89 */     this.rightWing2.func_78793_a(-12.1F, -5.05F, 1.0F);
/*  90 */     this.rightWing.func_78792_a(this.rightWing2);
/*  91 */     setRotationAngle(this.rightWing2, 0.0F, 0.0F, 0.1047F);
/*  92 */     this.rightWing2.func_78784_a(98, 52).func_228303_a_(-14.9F, 0.0F, 0.0F, 15.0F, 12.0F, 0.0F, 0.0F, false);
/*     */     
/*  94 */     this.rightWingLayer1 = new ModelRenderer((Model)this);
/*  95 */     this.rightWingLayer1.func_78793_a(0.0F, 0.0F, 0.0F);
/*  96 */     this.rightWing.func_78792_a(this.rightWingLayer1);
/*  97 */     setRotationAngle(this.rightWingLayer1, -0.1745F, 0.0F, 0.0F);
/*  98 */     this.rightWingLayer1.func_78784_a(71, 54).func_228303_a_(-13.0F, -5.0F, 1.0F, 13.0F, 10.0F, 0.0F, 0.0F, false);
/*     */     
/* 100 */     this.rightWingLayer1b = new ModelRenderer((Model)this);
/* 101 */     this.rightWingLayer1b.func_78793_a(-12.1F, -5.05F, 1.0F);
/* 102 */     this.rightWingLayer1.func_78792_a(this.rightWingLayer1b);
/* 103 */     setRotationAngle(this.rightWingLayer1b, 0.0F, 0.0F, 0.1047F);
/* 104 */     this.rightWingLayer1b.func_78784_a(98, 52).func_228303_a_(-14.9F, 0.0F, 0.0F, 15.0F, 12.0F, 0.0F, 0.0F, false);
/*     */     
/* 106 */     this.rightWingLayer2 = new ModelRenderer((Model)this);
/* 107 */     this.rightWingLayer2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 108 */     this.rightWing.func_78792_a(this.rightWingLayer2);
/* 109 */     setRotationAngle(this.rightWingLayer2, 0.1745F, 0.0F, 0.0F);
/* 110 */     this.rightWingLayer2.func_78784_a(71, 54).func_228303_a_(-13.0F, -5.0F, 1.0F, 13.0F, 10.0F, 0.0F, 0.0F, false);
/*     */     
/* 112 */     this.rightWingLayer2b = new ModelRenderer((Model)this);
/* 113 */     this.rightWingLayer2b.func_78793_a(-12.1F, -5.05F, 1.0F);
/* 114 */     this.rightWingLayer2.func_78792_a(this.rightWingLayer2b);
/* 115 */     setRotationAngle(this.rightWingLayer2b, 0.0F, 0.0F, 0.1047F);
/* 116 */     this.rightWingLayer2b.func_78784_a(98, 52).func_228303_a_(-14.9F, 0.0F, 0.0F, 15.0F, 12.0F, 0.0F, 0.0F, false);
/*     */     
/* 118 */     this.leftWing = new ModelRenderer((Model)this);
/* 119 */     this.leftWing.func_78793_a(1.5F, 1.0F, 2.5F);
/* 120 */     setRotationAngle(this.leftWing, 1.5708F, 0.0F, 1.3963F);
/* 121 */     this.leftWing.func_78784_a(72, 39).func_228303_a_(0.0F, -5.0F, 1.0F, 13.0F, 10.0F, 0.0F, 0.0F, false);
/*     */     
/* 123 */     this.leftWing2 = new ModelRenderer((Model)this);
/* 124 */     this.leftWing2.func_78793_a(12.0F, -5.05F, 1.0F);
/* 125 */     this.leftWing.func_78792_a(this.leftWing2);
/* 126 */     setRotationAngle(this.leftWing2, 0.0F, 0.0F, -0.1047F);
/* 127 */     this.leftWing2.func_78784_a(98, 39).func_228303_a_(0.1055F, -0.0545F, 0.0F, 15.0F, 12.0F, 0.0F, 0.0F, false);
/*     */     
/* 129 */     this.leftWingLayer1 = new ModelRenderer((Model)this);
/* 130 */     this.leftWingLayer1.func_78793_a(0.0F, 0.0F, 0.0F);
/* 131 */     this.leftWing.func_78792_a(this.leftWingLayer1);
/* 132 */     setRotationAngle(this.leftWingLayer1, 0.1745F, 0.0F, 0.0F);
/* 133 */     this.leftWingLayer1.func_78784_a(72, 39).func_228303_a_(0.0F, -5.0F, 1.0F, 13.0F, 10.0F, 0.0F, 0.0F, false);
/*     */     
/* 135 */     this.leftWingLayer1b = new ModelRenderer((Model)this);
/* 136 */     this.leftWingLayer1b.func_78793_a(12.0F, -5.05F, 1.0F);
/* 137 */     this.leftWingLayer1.func_78792_a(this.leftWingLayer1b);
/* 138 */     setRotationAngle(this.leftWingLayer1b, 0.0F, 0.0F, -0.1047F);
/* 139 */     this.leftWingLayer1b.func_78784_a(98, 39).func_228303_a_(0.1055F, -0.0545F, 0.0F, 15.0F, 12.0F, 0.0F, 0.0F, false);
/*     */     
/* 141 */     this.leftWingLayer2 = new ModelRenderer((Model)this);
/* 142 */     this.leftWingLayer2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 143 */     this.leftWing.func_78792_a(this.leftWingLayer2);
/* 144 */     setRotationAngle(this.leftWingLayer2, -0.1745F, 0.0F, 0.0F);
/* 145 */     this.leftWingLayer2.func_78784_a(72, 39).func_228303_a_(0.0F, -5.0F, 1.0F, 13.0F, 10.0F, 0.0F, 0.0F, false);
/*     */     
/* 147 */     this.leftWingLayer2b = new ModelRenderer((Model)this);
/* 148 */     this.leftWingLayer2b.func_78793_a(12.0F, -5.05F, 1.0F);
/* 149 */     this.leftWingLayer2.func_78792_a(this.leftWingLayer2b);
/* 150 */     setRotationAngle(this.leftWingLayer2b, 0.0F, 0.0F, -0.1047F);
/* 151 */     this.leftWingLayer2b.func_78784_a(98, 39).func_228303_a_(0.1055F, -0.0545F, 0.0F, 15.0F, 12.0F, 0.0F, 0.0F, false);
/*     */     
/* 153 */     this.field_178721_j = new ModelRenderer((Model)this);
/* 154 */     this.field_178721_j.func_78793_a(-2.0F, 12.0F, 0.0F);
/* 155 */     this.field_178721_j.func_78784_a(0, 34).func_228303_a_(-2.0F, 0.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.0F, false);
/*     */     
/* 157 */     this.rightLeg2 = new ModelRenderer((Model)this);
/* 158 */     this.rightLeg2.func_78793_a(0.0F, 4.75F, 0.0F);
/* 159 */     this.field_178721_j.func_78792_a(this.rightLeg2);
/* 160 */     this.rightLeg2.func_78784_a(0, 45).func_228303_a_(-1.5F, 0.0F, -1.25F, 3.0F, 3.0F, 3.0F, 0.0F, false);
/*     */     
/* 162 */     this.rightLeg3 = new ModelRenderer((Model)this);
/* 163 */     this.rightLeg3.func_78793_a(0.0F, 0.0F, 0.0F);
/* 164 */     this.rightLeg2.func_78792_a(this.rightLeg3);
/* 165 */     this.rightLeg3.func_78784_a(0, 54).func_228303_a_(-1.0F, 3.0F, -0.75F, 2.0F, 4.0F, 2.0F, 0.0F, false);
/*     */     
/* 167 */     this.rightTalon1 = new ModelRenderer((Model)this);
/* 168 */     this.rightTalon1.func_78793_a(-0.75F, 6.5F, 0.25F);
/* 169 */     this.rightLeg3.func_78792_a(this.rightTalon1);
/* 170 */     setRotationAngle(this.rightTalon1, 0.0437F, 0.1309F, 0.0F);
/* 171 */     this.rightTalon1.func_78784_a(13, 46).func_228303_a_(-0.5F, -0.5F, -2.25F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 173 */     this.rightTalon1b = new ModelRenderer((Model)this);
/* 174 */     this.rightTalon1b.func_78793_a(0.0F, 0.0F, -1.5F);
/* 175 */     this.rightTalon1.func_78792_a(this.rightTalon1b);
/* 176 */     setRotationAngle(this.rightTalon1b, -0.0436F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 179 */     this.rightTalon1b_r1 = new ModelRenderer((Model)this);
/* 180 */     this.rightTalon1b_r1.func_78793_a(2.75F, 0.75F, 1.25F);
/* 181 */     this.rightTalon1b.func_78792_a(this.rightTalon1b_r1);
/* 182 */     setRotationAngle(this.rightTalon1b_r1, 0.0436F, 0.0F, 0.0F);
/* 183 */     this.rightTalon1b_r1.func_78784_a(13, 53).func_228303_a_(-3.25F, -1.15F, -3.7264F, 1.0F, 1.0F, 2.0F, -0.1F, false);
/*     */     
/* 185 */     this.rightTalon1c = new ModelRenderer((Model)this);
/* 186 */     this.rightTalon1c.func_78793_a(0.0F, 0.0F, -1.0F);
/* 187 */     this.rightTalon1b.func_78792_a(this.rightTalon1c);
/* 188 */     setRotationAngle(this.rightTalon1c, 0.0873F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 191 */     this.rightTalon1c_r1 = new ModelRenderer((Model)this);
/* 192 */     this.rightTalon1c_r1.func_78793_a(2.75F, 0.75F, 1.25F);
/* 193 */     this.rightTalon1c.func_78792_a(this.rightTalon1c_r1);
/* 194 */     setRotationAngle(this.rightTalon1c_r1, 0.0436F, 0.0F, 0.0F);
/* 195 */     this.rightTalon1c_r1.func_78784_a(13, 53).func_228303_a_(-3.25F, -1.15F, -3.9764F, 1.0F, 1.0F, 2.0F, -0.25F, false);
/*     */     
/* 197 */     this.rightTalon2 = new ModelRenderer((Model)this);
/* 198 */     this.rightTalon2.func_78793_a(0.0F, 6.5F, 0.25F);
/* 199 */     this.rightLeg3.func_78792_a(this.rightTalon2);
/* 200 */     setRotationAngle(this.rightTalon2, 0.0437F, 0.0F, 0.0F);
/* 201 */     this.rightTalon2.func_78784_a(13, 46).func_228303_a_(-0.5F, -0.5F, -2.3F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 203 */     this.rightTalon2b = new ModelRenderer((Model)this);
/* 204 */     this.rightTalon2b.func_78793_a(0.0F, 0.0F, -1.5F);
/* 205 */     this.rightTalon2.func_78792_a(this.rightTalon2b);
/* 206 */     setRotationAngle(this.rightTalon2b, 0.1833F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 209 */     this.rightTalon2b_r1 = new ModelRenderer((Model)this);
/* 210 */     this.rightTalon2b_r1.func_78793_a(2.0F, 0.75F, 1.25F);
/* 211 */     this.rightTalon2b.func_78792_a(this.rightTalon2b_r1);
/* 212 */     setRotationAngle(this.rightTalon2b_r1, -0.1745F, 0.0F, 0.0F);
/* 213 */     this.rightTalon2b_r1.func_78784_a(13, 53).func_228303_a_(-2.5F, -0.9F, -3.9764F, 1.0F, 1.0F, 2.0F, -0.1F, false);
/*     */     
/* 215 */     this.rightTalon2c = new ModelRenderer((Model)this);
/* 216 */     this.rightTalon2c.func_78793_a(0.0F, -0.25F, -2.75F);
/* 217 */     this.rightTalon2b.func_78792_a(this.rightTalon2c);
/* 218 */     setRotationAngle(this.rightTalon2c, 0.0873F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 221 */     this.rightTalon2c_r1 = new ModelRenderer((Model)this);
/* 222 */     this.rightTalon2c_r1.func_78793_a(2.0F, 0.8154F, 2.7486F);
/* 223 */     this.rightTalon2c.func_78792_a(this.rightTalon2c_r1);
/* 224 */     setRotationAngle(this.rightTalon2c_r1, -0.1745F, 0.0F, 0.0F);
/* 225 */     this.rightTalon2c_r1.func_78784_a(13, 53).func_228303_a_(-2.5F, -0.8F, -3.9764F, 1.0F, 1.0F, 2.0F, -0.25F, false);
/*     */     
/* 227 */     this.rightTalon3 = new ModelRenderer((Model)this);
/* 228 */     this.rightTalon3.func_78793_a(0.75F, 6.5F, 0.25F);
/* 229 */     this.rightLeg3.func_78792_a(this.rightTalon3);
/* 230 */     setRotationAngle(this.rightTalon3, 0.0873F, -0.1309F, 0.0F);
/*     */     
/* 232 */     this.rightTalon3_r1 = new ModelRenderer((Model)this);
/* 233 */     this.rightTalon3_r1.func_78793_a(1.25F, 0.75F, -0.25F);
/* 234 */     this.rightTalon3.func_78792_a(this.rightTalon3_r1);
/* 235 */     setRotationAngle(this.rightTalon3_r1, -0.0436F, 0.0F, 0.0F);
/* 236 */     this.rightTalon3_r1.func_78784_a(13, 46).func_228303_a_(-1.75F, -1.25F, -1.98F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 238 */     this.rightTalon3b = new ModelRenderer((Model)this);
/* 239 */     this.rightTalon3b.func_78793_a(0.0F, 0.0F, -2.0F);
/* 240 */     this.rightTalon3.func_78792_a(this.rightTalon3b);
/* 241 */     setRotationAngle(this.rightTalon3b, -0.0436F, 0.0F, 0.0F);
/* 242 */     this.rightTalon3b.func_78784_a(13, 53).func_228303_a_(-0.5F, -0.45F, -1.9883F, 1.0F, 1.0F, 2.0F, -0.1F, false);
/*     */     
/* 244 */     this.rightTalon3c = new ModelRenderer((Model)this);
/* 245 */     this.rightTalon3c.func_78793_a(0.0F, 0.0F, -1.25F);
/* 246 */     this.rightTalon3b.func_78792_a(this.rightTalon3c);
/* 247 */     setRotationAngle(this.rightTalon3c, 0.0436F, 0.0F, 0.0F);
/* 248 */     this.rightTalon3c.func_78784_a(13, 53).func_228303_a_(-0.5F, -0.35F, -1.9883F, 1.0F, 1.0F, 2.0F, -0.25F, false);
/*     */     
/* 250 */     this.rightTalon4 = new ModelRenderer((Model)this);
/* 251 */     this.rightTalon4.func_78793_a(0.0F, 6.5F, 0.0F);
/* 252 */     this.rightLeg3.func_78792_a(this.rightTalon4);
/* 253 */     setRotationAngle(this.rightTalon4, 0.0873F, 3.1416F, 0.0F);
/* 254 */     this.rightTalon4.func_78784_a(13, 46).func_228303_a_(-0.5F, -0.5F, -3.0F, 1.0F, 1.0F, 2.0F, -0.1F, false);
/*     */     
/* 256 */     this.rightTalon4b = new ModelRenderer((Model)this);
/* 257 */     this.rightTalon4b.func_78793_a(0.0F, 0.0F, -3.0F);
/* 258 */     this.rightTalon4.func_78792_a(this.rightTalon4b);
/* 259 */     setRotationAngle(this.rightTalon4b, 0.1309F, 0.0F, 0.0F);
/*     */     
/* 261 */     this.rightTalon4b_r1 = new ModelRenderer((Model)this);
/* 262 */     this.rightTalon4b_r1.func_78793_a(2.0F, 0.9665F, 1.4763F);
/* 263 */     this.rightTalon4b.func_78792_a(this.rightTalon4b_r1);
/* 264 */     setRotationAngle(this.rightTalon4b_r1, -0.0873F, 0.0F, 0.0F);
/* 265 */     this.rightTalon4b_r1.func_78784_a(13, 53).func_228303_a_(-2.5F, -1.3165F, -2.9764F, 1.0F, 1.0F, 2.0F, -0.25F, false);
/*     */     
/* 267 */     this.field_178722_k = new ModelRenderer((Model)this);
/* 268 */     this.field_178722_k.func_78793_a(2.0F, 12.0F, 0.0F);
/* 269 */     this.field_178722_k.func_78784_a(0, 34).func_228303_a_(-2.0F, 0.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.0F, false);
/*     */     
/* 271 */     this.leftLeg2 = new ModelRenderer((Model)this);
/* 272 */     this.leftLeg2.func_78793_a(0.0F, 4.75F, 0.0F);
/* 273 */     this.field_178722_k.func_78792_a(this.leftLeg2);
/* 274 */     this.leftLeg2.func_78784_a(0, 45).func_228303_a_(-1.5F, 0.0F, -1.25F, 3.0F, 3.0F, 3.0F, 0.0F, false);
/*     */     
/* 276 */     this.leftLeg3 = new ModelRenderer((Model)this);
/* 277 */     this.leftLeg3.func_78793_a(0.0F, 0.0F, 0.0F);
/* 278 */     this.leftLeg2.func_78792_a(this.leftLeg3);
/* 279 */     this.leftLeg3.func_78784_a(0, 54).func_228303_a_(-1.0F, 3.0F, -0.75F, 2.0F, 4.0F, 2.0F, 0.0F, false);
/*     */     
/* 281 */     this.leftTalon1 = new ModelRenderer((Model)this);
/* 282 */     this.leftTalon1.func_78793_a(-0.75F, 6.5F, 0.25F);
/* 283 */     this.leftLeg3.func_78792_a(this.leftTalon1);
/* 284 */     setRotationAngle(this.leftTalon1, 0.0873F, 0.1309F, 0.0F);
/*     */     
/* 286 */     this.leftTalon1_r1 = new ModelRenderer((Model)this);
/* 287 */     this.leftTalon1_r1.func_78793_a(-1.25F, 0.75F, -0.25F);
/* 288 */     this.leftTalon1.func_78792_a(this.leftTalon1_r1);
/* 289 */     setRotationAngle(this.leftTalon1_r1, -0.0433F, -6.0E-4F, -0.0057F);
/* 290 */     this.leftTalon1_r1.func_78784_a(13, 46).func_228303_a_(0.7892F, -1.2759F, -2.0463F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 292 */     this.leftTalon1b = new ModelRenderer((Model)this);
/* 293 */     this.leftTalon1b.func_78793_a(0.0F, 0.0F, -2.0F);
/* 294 */     this.leftTalon1.func_78792_a(this.leftTalon1b);
/* 295 */     setRotationAngle(this.leftTalon1b, -0.0436F, 0.0F, 0.0F);
/* 296 */     this.leftTalon1b.func_78784_a(13, 53).func_228303_a_(-0.4608F, -0.4895F, -2.0101F, 1.0F, 1.0F, 2.0F, -0.1F, false);
/*     */     
/* 298 */     this.leftTalon1c = new ModelRenderer((Model)this);
/* 299 */     this.leftTalon1c.func_78793_a(0.0F, 0.0F, -1.25F);
/* 300 */     this.leftTalon1b.func_78792_a(this.leftTalon1c);
/* 301 */     setRotationAngle(this.leftTalon1c, 0.0436F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 304 */     this.leftTalon1c_r1 = new ModelRenderer((Model)this);
/* 305 */     this.leftTalon1c_r1.func_78793_a(-1.25F, 0.75F, 1.25F);
/* 306 */     this.leftTalon1c.func_78792_a(this.leftTalon1c_r1);
/* 307 */     setRotationAngle(this.leftTalon1c_r1, 0.0436F, 0.0F, 0.0F);
/* 308 */     this.leftTalon1c_r1.func_78784_a(13, 53).func_228303_a_(0.7892F, -1.2395F, -3.2601F, 1.0F, 1.0F, 2.0F, -0.25F, false);
/*     */     
/* 310 */     this.leftTalon2 = new ModelRenderer((Model)this);
/* 311 */     this.leftTalon2.func_78793_a(0.0F, 6.5F, 0.25F);
/* 312 */     this.leftLeg3.func_78792_a(this.leftTalon2);
/* 313 */     setRotationAngle(this.leftTalon2, 0.0873F, 0.0F, 0.0F);
/*     */     
/* 315 */     this.leftTalon2_r1 = new ModelRenderer((Model)this);
/* 316 */     this.leftTalon2_r1.func_78793_a(-2.0F, 0.75F, -0.25F);
/* 317 */     this.leftTalon2.func_78792_a(this.leftTalon2_r1);
/* 318 */     setRotationAngle(this.leftTalon2_r1, -0.0436F, 0.0F, 0.0F);
/* 319 */     this.leftTalon2_r1.func_78784_a(13, 46).func_228303_a_(1.5F, -1.2762F, -2.0489F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 321 */     this.leftTalon2b = new ModelRenderer((Model)this);
/* 322 */     this.leftTalon2b.func_78793_a(0.0F, 0.0F, -2.0F);
/* 323 */     this.leftTalon2.func_78792_a(this.leftTalon2b);
/* 324 */     setRotationAngle(this.leftTalon2b, -0.0436F, 0.0F, 0.0F);
/* 325 */     this.leftTalon2b.func_78784_a(13, 53).func_228303_a_(-0.5F, -0.4902F, -2.0125F, 1.0F, 1.0F, 2.0F, -0.1F, false);
/*     */     
/* 327 */     this.leftTalon2c = new ModelRenderer((Model)this);
/* 328 */     this.leftTalon2c.func_78793_a(0.0F, 0.0F, -1.25F);
/* 329 */     this.leftTalon2b.func_78792_a(this.leftTalon2c);
/* 330 */     setRotationAngle(this.leftTalon2c, 0.0873F, 0.0F, 0.0F);
/* 331 */     this.leftTalon2c.func_78784_a(13, 53).func_228303_a_(-0.5F, -0.4902F, -2.1F, 1.0F, 1.0F, 2.0F, -0.25F, false);
/*     */     
/* 333 */     this.leftTalon3 = new ModelRenderer((Model)this);
/* 334 */     this.leftTalon3.func_78793_a(0.75F, 6.5F, 0.25F);
/* 335 */     this.leftLeg3.func_78792_a(this.leftTalon3);
/* 336 */     setRotationAngle(this.leftTalon3, 0.0873F, -0.1309F, 0.0F);
/*     */     
/* 338 */     this.leftTalon3_r1 = new ModelRenderer((Model)this);
/* 339 */     this.leftTalon3_r1.func_78793_a(-2.75F, 0.75F, -0.25F);
/* 340 */     this.leftTalon3.func_78792_a(this.leftTalon3_r1);
/* 341 */     setRotationAngle(this.leftTalon3_r1, -0.0433F, 6.0E-4F, 0.0057F);
/* 342 */     this.leftTalon3_r1.func_78784_a(13, 46).func_228303_a_(2.2108F, -1.2759F, -2.0463F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 344 */     this.leftTalon3b = new ModelRenderer((Model)this);
/* 345 */     this.leftTalon3b.func_78793_a(0.0F, 0.0F, -2.0F);
/* 346 */     this.leftTalon3.func_78792_a(this.leftTalon3b);
/* 347 */     setRotationAngle(this.leftTalon3b, -0.0349F, 0.0F, 0.0F);
/* 348 */     this.leftTalon3b.func_78784_a(13, 53).func_228303_a_(-0.5392F, -0.4895F, -2.0101F, 1.0F, 1.0F, 2.0F, -0.1F, false);
/*     */     
/* 350 */     this.leftTalon3c = new ModelRenderer((Model)this);
/* 351 */     this.leftTalon3c.func_78793_a(0.0F, 0.0F, -1.25F);
/* 352 */     this.leftTalon3b.func_78792_a(this.leftTalon3c);
/* 353 */     setRotationAngle(this.leftTalon3c, 0.096F, 0.0F, 0.0F);
/* 354 */     this.leftTalon3c.func_78784_a(13, 53).func_228303_a_(-0.5392F, -0.4895F, -2.0101F, 1.0F, 1.0F, 2.0F, -0.25F, false);
/*     */     
/* 356 */     this.leftTalon4 = new ModelRenderer((Model)this);
/* 357 */     this.leftTalon4.func_78793_a(0.0F, 6.5F, 0.0F);
/* 358 */     this.leftLeg3.func_78792_a(this.leftTalon4);
/* 359 */     setRotationAngle(this.leftTalon4, 0.0873F, 3.1416F, 0.0F);
/* 360 */     this.leftTalon4.func_78784_a(13, 46).func_228303_a_(-0.5F, -0.55F, -2.9962F, 1.0F, 1.0F, 2.0F, -0.1F, false);
/*     */     
/* 362 */     this.leftTalon4b = new ModelRenderer((Model)this);
/* 363 */     this.leftTalon4b.func_78793_a(0.0F, -0.25F, -3.0F);
/* 364 */     this.leftTalon4.func_78792_a(this.leftTalon4b);
/* 365 */     setRotationAngle(this.leftTalon4b, 0.0437F, 0.0F, 0.0F);
/* 366 */     this.leftTalon4b.func_78784_a(13, 53).func_228303_a_(-0.5F, -0.2F, -1.4F, 1.0F, 1.0F, 2.0F, -0.25F, false);
/*     */     
/* 368 */     this.tail1 = new ModelRenderer((Model)this);
/* 369 */     this.tail1.func_78793_a(0.5F, 11.25F, 2.0F);
/* 370 */     setRotationAngle(this.tail1, 0.0F, -0.2182F, 0.0F);
/* 371 */     this.tail1.func_78784_a(18, 53).func_228303_a_(-2.2651F, -1.0F, -0.0855F, 2.0F, 0.0F, 9.0F, 0.0F, false);
/*     */     
/* 373 */     this.tail1b = new ModelRenderer((Model)this);
/* 374 */     this.tail1b.func_78793_a(0.0F, 0.0F, 8.5F);
/* 375 */     this.tail1.func_78792_a(this.tail1b);
/* 376 */     setRotationAngle(this.tail1b, 0.0F, -0.1745F, 0.0F);
/* 377 */     this.tail1b.func_78784_a(18, 53).func_228303_a_(-2.2835F, -1.0F, -0.125F, 2.0F, 0.0F, 9.0F, 0.0F, false);
/*     */     
/* 379 */     this.tail1c = new ModelRenderer((Model)this);
/* 380 */     this.tail1c.func_78793_a(-1.5F, 0.0F, 9.0F);
/* 381 */     this.tail1b.func_78792_a(this.tail1c);
/* 382 */     setRotationAngle(this.tail1c, 0.0F, 0.1746F, 0.0F);
/* 383 */     this.tail1c.func_78784_a(20, 45).func_228303_a_(-0.7708F, -1.0F, -0.2162F, 2.0F, 0.0F, 7.0F, 0.0F, false);
/*     */     
/* 385 */     this.tail2 = new ModelRenderer((Model)this);
/* 386 */     this.tail2.func_78793_a(0.0F, 11.25F, 2.0F);
/* 387 */     setRotationAngle(this.tail2, 0.0F, 0.1745F, 0.0F);
/* 388 */     this.tail2.func_78784_a(18, 53).func_228303_a_(-0.2842F, -1.0F, -0.304F, 2.0F, 0.0F, 9.0F, 0.0F, false);
/*     */     
/* 390 */     this.tail2b = new ModelRenderer((Model)this);
/* 391 */     this.tail2b.func_78793_a(0.4696F, 0.0F, 8.1526F);
/* 392 */     this.tail2.func_78792_a(this.tail2b);
/* 393 */     setRotationAngle(this.tail2b, 0.0F, 0.1746F, 0.0F);
/* 394 */     this.tail2b.func_78784_a(18, 53).func_228303_a_(-0.7708F, -1.0F, -0.0452F, 2.0F, 0.0F, 9.0F, 0.0F, false);
/*     */     
/* 396 */     this.tail2c = new ModelRenderer((Model)this);
/* 397 */     this.tail2c.func_78793_a(-0.0057F, 0.0F, 8.8693F);
/* 398 */     this.tail2b.func_78792_a(this.tail2c);
/* 399 */     setRotationAngle(this.tail2c, 0.0F, -0.1309F, 0.0F);
/* 400 */     this.tail2c.func_78784_a(20, 45).func_228303_a_(-0.7616F, -1.0F, -0.0766F, 2.0F, 0.0F, 7.0F, 0.0F, false);
/*     */     
/* 402 */     this.tail3 = new ModelRenderer((Model)this);
/* 403 */     this.tail3.func_78793_a(-0.25F, 11.5F, 2.0F);
/* 404 */     setRotationAngle(this.tail3, 0.0F, 0.0F, 0.0F);
/* 405 */     this.tail3.func_78784_a(18, 53).func_228303_a_(-1.0342F, -1.0F, -0.304F, 2.0F, 0.0F, 9.0F, 0.0F, false);
/*     */     
/* 407 */     this.tail3b = new ModelRenderer((Model)this);
/* 408 */     this.tail3b.func_78793_a(-0.2804F, 0.0F, 8.1526F);
/* 409 */     this.tail3.func_78792_a(this.tail3b);
/* 410 */     setRotationAngle(this.tail3b, 0.0F, -0.0436F, 0.0F);
/* 411 */     this.tail3b.func_78784_a(18, 53).func_228303_a_(-0.7708F, -1.0F, -0.0452F, 2.0F, 0.0F, 9.0F, 0.0F, false);
/*     */     
/* 413 */     this.tail3c = new ModelRenderer((Model)this);
/* 414 */     this.tail3c.func_78793_a(-0.0057F, 0.0F, 8.8693F);
/* 415 */     this.tail3b.func_78792_a(this.tail3c);
/* 416 */     setRotationAngle(this.tail3c, 0.0F, 0.0436F, 0.0F);
/* 417 */     this.tail3c.func_78784_a(20, 45).func_228303_a_(-0.7616F, -1.0F, -0.0766F, 2.0F, 0.0F, 7.0F, 0.0F, false);
/*     */     
/* 419 */     super.field_178722_k = this.field_178722_k;
/* 420 */     super.field_178721_j = this.field_178721_j;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 426 */     matrixStack.func_227860_a_();
/* 427 */     this.field_178721_j.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 428 */     this.field_178722_k.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 429 */     this.tail1.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 430 */     this.tail2.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 431 */     this.tail3.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 432 */     matrixStack.func_227862_a_(1.6F, 1.6F, 1.6F);
/* 433 */     matrixStack.func_227861_a_(0.0D, -0.1D, 0.0D);
/* 434 */     this.rightWing.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 435 */     this.leftWing.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 436 */     matrixStack.func_227865_b_();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 442 */     super.func_225597_a_((LivingEntity)entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/*     */     
/* 444 */     this.tail1.field_78796_g = (float)(this.tail1.field_78796_g + Math.sin(ageInTicks * 0.04D) / 10.0D);
/* 445 */     this.tail1.field_78795_f = (float)(this.tail1.field_78795_f + Math.sin(ageInTicks * 0.01D) / 8.0D);
/* 446 */     this.tail2.field_78796_g = (float)(this.tail2.field_78796_g - Math.sin(ageInTicks * 0.08D) / 10.0D);
/* 447 */     this.tail2.field_78795_f = (float)(this.tail2.field_78795_f - Math.sin(ageInTicks * 0.05D) / 8.0D);
/* 448 */     this.tail3.field_78796_g = (float)(this.tail3.field_78796_g + Math.sin(ageInTicks * 0.08D) / 10.0D);
/* 449 */     this.tail3.field_78795_f = (float)(this.tail3.field_78795_f + Math.sin(ageInTicks * 0.05D) / 8.0D);
/*     */     
/* 451 */     this.tail1b.field_78796_g = (float)(this.tail1b.field_78796_g + Math.sin(ageInTicks * 0.06D) / 10.0D);
/* 452 */     this.tail1b.field_78795_f = (float)(this.tail1b.field_78795_f + Math.sin(ageInTicks * 0.02D) / 5.0D);
/* 453 */     this.tail2b.field_78796_g = (float)(this.tail2b.field_78796_g + Math.sin(ageInTicks * 0.06D) / 10.0D);
/* 454 */     this.tail2b.field_78795_f = (float)(this.tail2b.field_78795_f + Math.sin(ageInTicks * 0.02D) / 5.0D);
/* 455 */     this.tail3b.field_78796_g = (float)(this.tail3b.field_78796_g - Math.sin(ageInTicks * 0.04D) / 10.0D);
/* 456 */     this.tail3b.field_78795_f = (float)(this.tail3b.field_78795_f - Math.sin(ageInTicks * 0.01D) / 5.0D);
/*     */     
/* 458 */     this.tail1c.field_78796_g = (float)(this.tail1c.field_78796_g + Math.sin(ageInTicks * 0.08D) / 10.0D);
/* 459 */     this.tail1c.field_78795_f = (float)(this.tail1c.field_78795_f + Math.sin(ageInTicks * 0.05D) / 8.0D);
/* 460 */     this.tail2c.field_78796_g = (float)(this.tail2c.field_78796_g + Math.sin(ageInTicks * 0.08D) / 10.0D);
/* 461 */     this.tail2c.field_78795_f = (float)(this.tail2c.field_78795_f + Math.sin(ageInTicks * 0.05D) / 8.0D);
/* 462 */     this.tail3c.field_78796_g = (float)(this.tail3c.field_78796_g + Math.sin(ageInTicks * 0.08D) / 10.0D);
/* 463 */     this.tail3c.field_78795_f = (float)(this.tail3c.field_78795_f + Math.sin(ageInTicks * 0.05D) / 8.0D);
/*     */     
/* 465 */     this.tail1.field_78795_f = 0.5F * MathHelper.func_76134_b(ageInTicks * 0.2F) * 0.3F;
/* 466 */     this.tail1b.field_78795_f = 0.2F * MathHelper.func_76134_b(ageInTicks * 0.3F) * 0.8F;
/* 467 */     this.tail1c.field_78795_f = 0.2F * MathHelper.func_76134_b(ageInTicks * 0.4F) * 0.8F;
/* 468 */     this.tail2.field_78795_f = 0.5F * MathHelper.func_76134_b(ageInTicks * 0.2F + 3.1415927F) * 0.3F;
/* 469 */     this.tail2b.field_78795_f = 0.2F * MathHelper.func_76134_b(ageInTicks * 0.3F + 3.1415927F) * 0.8F;
/* 470 */     this.tail2c.field_78795_f = 0.2F * MathHelper.func_76134_b(ageInTicks * 0.4F + 3.1415927F) * 0.8F;
/* 471 */     this.tail3b.field_78795_f = 0.2F * MathHelper.func_76134_b(ageInTicks * 0.3F + 3.1415927F) * 0.8F;
/* 472 */     this.tail3c.field_78795_f = 0.2F * MathHelper.func_76134_b(ageInTicks * 0.4F + 3.1415927F) * 0.8F;
/*     */     
/* 474 */     if (!entity.func_233570_aj_()) {
/*     */       
/* 476 */       this.rightWing.field_78800_c--;
/* 477 */       this.rightWing.field_78797_d += 2.0F;
/* 478 */       this.rightWing.field_78808_h = 0.3F + MathHelper.func_76134_b((float)(((LivingEntity)entity).field_70173_aa * 0.2D));
/* 479 */       this.rightWing.field_78796_g = MathHelper.func_76134_b((float)(((LivingEntity)entity).field_70173_aa * 0.2D)) / 3.0F;
/* 480 */       this.rightWing2.field_78796_g = MathHelper.func_76134_b((float)(((LivingEntity)entity).field_70173_aa * 0.2D)) / 3.0F;
/* 481 */       this.rightWingLayer1b.field_78796_g = MathHelper.func_76134_b((float)(((LivingEntity)entity).field_70173_aa * 0.2D)) / 3.0F;
/* 482 */       this.rightWingLayer2b.field_78796_g = MathHelper.func_76134_b((float)(((LivingEntity)entity).field_70173_aa * 0.2D)) / 3.0F;
/*     */       
/* 484 */       this.leftWing.field_78800_c += 0.3F;
/* 485 */       this.leftWing.field_78797_d += 1.55F;
/* 486 */       this.leftWing.field_78808_h = -0.3F - MathHelper.func_76134_b((float)(((LivingEntity)entity).field_70173_aa * 0.2D));
/* 487 */       this.leftWing.field_78796_g = -MathHelper.func_76134_b((float)(((LivingEntity)entity).field_70173_aa * 0.2D)) / 3.0F;
/* 488 */       this.leftWing2.field_78796_g = -MathHelper.func_76134_b((float)(((LivingEntity)entity).field_70173_aa * 0.2D)) / 3.0F;
/* 489 */       this.leftWingLayer1b.field_78796_g = -MathHelper.func_76134_b((float)(((LivingEntity)entity).field_70173_aa * 0.2D)) / 3.0F;
/* 490 */       this.leftWingLayer2b.field_78796_g = -MathHelper.func_76134_b((float)(((LivingEntity)entity).field_70173_aa * 0.2D)) / 3.0F;
/*     */       
/* 492 */       if (!entity.func_184614_ca().func_190926_b()) {
/*     */         
/* 494 */         this.field_178721_j.field_78795_f -= 0.15F;
/* 495 */         this.rightLeg2.field_78797_d -= 3.0F;
/* 496 */         this.rightLeg2.field_78795_f = (float)(this.rightLeg2.field_78795_f - 0.4D);
/* 497 */         this.rightLeg2.field_78796_g = (float)(this.rightLeg2.field_78796_g + 0.8D);
/* 498 */         this.rightTalon1.field_78795_f = (float)(this.rightTalon1.field_78795_f + 0.7D);
/* 499 */         this.rightTalon1b.field_78795_f = (float)(this.rightTalon1b.field_78795_f + 0.7D);
/* 500 */         this.rightTalon2.field_78795_f = (float)(this.rightTalon2.field_78795_f + 0.5D);
/* 501 */         this.rightTalon2b.field_78795_f = (float)(this.rightTalon2b.field_78795_f + 0.7D);
/* 502 */         this.rightTalon3.field_78795_f = (float)(this.rightTalon3.field_78795_f + 0.4D);
/* 503 */         this.rightTalon3b.field_78795_f = (float)(this.rightTalon3b.field_78795_f + 0.7D);
/* 504 */         this.rightTalon4.field_78795_f = (float)(this.rightTalon4.field_78795_f + 0.6D);
/* 505 */         this.rightTalon4b.field_78795_f = (float)(this.rightTalon4b.field_78795_f + 0.7D);
/*     */       } 
/*     */ 
/*     */       
/* 509 */       this.field_217112_c = ((LivingEntity)entity).field_70733_aJ;
/* 510 */       if (this.field_217112_c > 0.0F && !EntityStatsCapability.get((LivingEntity)entity).isBlackLeg())
/*     */       {
/* 512 */         float swingProgress = ((LivingEntity)entity).field_70733_aJ;
/* 513 */         this.field_178721_j.field_78795_f -= ((LivingEntity)entity).field_70733_aJ * 2.0F;
/* 514 */         this.field_178721_j.field_78808_h += ((LivingEntity)entity).field_70733_aJ * 2.0F;
/* 515 */         this.field_78115_e.field_78796_g = MathHelper.func_76126_a(MathHelper.func_76129_c(swingProgress) * 6.2831855F) * 0.2F;
/* 516 */         this.field_178723_h.field_78798_e = MathHelper.func_76126_a(this.field_78115_e.field_78796_g) * 5.0F;
/* 517 */         this.field_178723_h.field_78800_c = -MathHelper.func_76134_b(this.field_78115_e.field_78796_g) * 5.0F;
/* 518 */         this.field_178724_i.field_78798_e = -MathHelper.func_76126_a(this.field_78115_e.field_78796_g) * 5.0F;
/* 519 */         this.field_178724_i.field_78800_c = MathHelper.func_76134_b(this.field_78115_e.field_78796_g) * 5.0F;
/* 520 */         this.field_178723_h.field_78796_g += this.field_78115_e.field_78796_g;
/* 521 */         this.field_178724_i.field_78796_g += this.field_78115_e.field_78796_g;
/* 522 */         this.field_178724_i.field_78795_f += this.field_78115_e.field_78796_g;
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 527 */       this.rightWing2.field_78806_j = false;
/* 528 */       this.rightWingLayer1b.field_78806_j = false;
/* 529 */       this.rightWingLayer2b.field_78806_j = false;
/* 530 */       this.leftWing2.field_78806_j = false;
/* 531 */       this.leftWingLayer1b.field_78806_j = false;
/* 532 */       this.leftWingLayer2b.field_78806_j = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 538 */       float f = 1.0F;
/* 539 */       this.rightWing.field_78796_g = MathHelper.func_76134_b(limbSwing * 0.6662F + 3.1415927F) * 0.8F * limbSwingAmount * 0.5F / f;
/* 540 */       this.leftWing.field_78796_g = MathHelper.func_76134_b(limbSwing * 0.6662F + 3.1415927F) * 0.8F * limbSwingAmount * 0.5F / f;
/*     */ 
/*     */       
/* 543 */       this.rightWing.field_78796_g = MathHelper.func_76134_b(limbSwing * 0.6662F + 3.1415927F) * 0.8F * limbSwingAmount * 0.5F / f;
/* 544 */       this.leftWing.field_78796_g = MathHelper.func_76134_b(limbSwing * 0.6662F + 3.1415927F) * 0.8F * limbSwingAmount * 0.5F / f;
/*     */ 
/*     */       
/* 547 */       this.field_217112_c = ((LivingEntity)entity).field_70733_aJ;
/* 548 */       boolean isBlackLeg = (EntityStatsCapability.get((LivingEntity)entity).isBlackLeg() && entity.func_184614_ca().func_190926_b());
/* 549 */       if (this.field_217112_c > 0.0F && !isBlackLeg) {
/*     */         
/* 551 */         this.rightWing.field_78796_g += this.field_78115_e.field_78796_g;
/* 552 */         float f1 = 1.0F - this.field_217112_c;
/* 553 */         f1 *= f1;
/* 554 */         f1 *= f1;
/* 555 */         f1 = 1.0F - f1;
/* 556 */         float f2 = MathHelper.func_76126_a(f1 * 3.1415927F);
/* 557 */         float f3 = MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * -(this.field_78116_c.field_78795_f - 0.7F) * 0.75F;
/* 558 */         this.rightWing.field_78796_g -= (float)(this.rightWing.field_78795_f - f2 * 1.5D + f3);
/* 559 */         this.rightWing.field_78808_h -= this.field_78115_e.field_78796_g * 2.0F;
/* 560 */         this.rightWing.field_78808_h -= MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * -0.9F;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
/* 575 */     if (side == HandSide.RIGHT) {
/*     */       
/* 577 */       matrixStack.func_227861_a_(0.0D, -1.2D, 0.3D);
/* 578 */       matrixStack.func_227862_a_(1.5F, 1.5F, 1.5F);
/* 579 */       matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(-60.0F));
/* 580 */       this.field_178721_j.func_228309_a_(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     }
/*     */     else {
/*     */       
/* 584 */       matrixStack.func_227861_a_(0.0D, -1.2D, 0.3D);
/* 585 */       matrixStack.func_227862_a_(1.5F, 1.5F, 1.5F);
/* 586 */       matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(60.0F));
/* 587 */       this.field_178722_k.func_228309_a_(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean renderItemInHand(T entity, HandSide side, MatrixStack matrixStack) {
/* 594 */     if (entity instanceof PlayerEntity)
/*     */     {
/* 596 */       if (!((PlayerEntity)entity).field_71075_bZ.field_75100_b)
/*     */       {
/* 598 */         return false;
/*     */       }
/*     */     }
/* 601 */     this.field_178721_j.func_228307_a_(matrixStack);
/* 602 */     matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(-40.0F));
/* 603 */     matrixStack.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(-30.0F));
/* 604 */     matrixStack.func_227861_a_(-0.12D, -0.05D, 0.3D);
/* 605 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 610 */     modelRenderer.field_78795_f = x;
/* 611 */     modelRenderer.field_78796_g = y;
/* 612 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\morphs\PhoenixAssaultPartialModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */