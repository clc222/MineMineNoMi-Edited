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
/*     */ public class PhoenixFlyModel<T extends LivingEntity>
/*     */   extends MorphModel<T> implements IHasArm {
/*     */   private final ModelRenderer head;
/*     */   private final ModelRenderer head2;
/*     */   private final ModelRenderer beak;
/*     */   private final ModelRenderer beak2;
/*     */   private final ModelRenderer beak3;
/*     */   private final ModelRenderer beak4;
/*     */   private final ModelRenderer head3;
/*     */   private final ModelRenderer neck;
/*     */   private final ModelRenderer neckFire;
/*     */   private final ModelRenderer body;
/*     */   private final ModelRenderer body2;
/*     */   private final ModelRenderer body3;
/*     */   private final ModelRenderer body4;
/*     */   private final ModelRenderer backFire;
/*     */   private final ModelRenderer rightWing;
/*     */   private final ModelRenderer rightWing2;
/*     */   private final ModelRenderer leftWing;
/*     */   private final ModelRenderer leftWing2;
/*     */   private final ModelRenderer tail1;
/*     */   private final ModelRenderer tail1b;
/*     */   private final ModelRenderer tail2;
/*     */   private final ModelRenderer tail2b;
/*     */   private final ModelRenderer tail3;
/*     */   private final ModelRenderer tail3b;
/*     */   private final ModelRenderer tail4;
/*     */   private final ModelRenderer tail4b;
/*     */   private final ModelRenderer leftLeg;
/*     */   private final ModelRenderer leftLeg2;
/*     */   private final ModelRenderer leftLeg3;
/*     */   private final ModelRenderer leftTalon5;
/*     */   private final ModelRenderer rightTalon3_r1;
/*     */   private final ModelRenderer rightTalon3_r2;
/*     */   private final ModelRenderer rightTalon2_r1;
/*     */   private final ModelRenderer leftTalon1b;
/*     */   private final ModelRenderer leftTalon1b_r;
/*     */   private final ModelRenderer rightTalon3b_r3_r1;
/*     */   private final ModelRenderer rightTalon3b_r3_r2;
/*     */   private final ModelRenderer rightTalon2b_r2_r1;
/*     */   private final ModelRenderer leftTalon1c;
/*     */   private final ModelRenderer leftTalon1c_r;
/*     */   private final ModelRenderer rightTalon3c_r3_r1;
/*     */   private final ModelRenderer rightTalon3c_r3_r2;
/*     */   private final ModelRenderer rightTalon2c_r2_r1;
/*     */   private final ModelRenderer leftTalon6;
/*     */   private final ModelRenderer leftTalonb2;
/*     */   private final ModelRenderer leftTalonb_r2;
/*     */   private final ModelRenderer leftTalonc2;
/*     */   private final ModelRenderer leftTalonc_r2;
/*     */   private final ModelRenderer leftTalon7;
/*     */   private final ModelRenderer leftTalon3_r;
/*     */   private final ModelRenderer leftTalon3b;
/*     */   private final ModelRenderer leftTalon3c;
/*     */   private final ModelRenderer leftTalon8;
/*     */   private final ModelRenderer rightTalon5_r1;
/*     */   private final ModelRenderer leftTalon4b;
/*     */   private final ModelRenderer leftTalon4b_r;
/*     */   private final ModelRenderer rightTalon4b_r2_r1;
/*     */   private final ModelRenderer rightLeg;
/*     */   private final ModelRenderer rightLeg2;
/*     */   private final ModelRenderer rightLeg3;
/*     */   private final ModelRenderer rightTalon;
/*     */   private final ModelRenderer rightTalon4_r1;
/*     */   private final ModelRenderer rightTalon4_r2;
/*     */   private final ModelRenderer rightTalon3_r3;
/*     */   private final ModelRenderer rightTalon1b;
/*     */   private final ModelRenderer rightTalon1b_r;
/*     */   private final ModelRenderer rightTalon4b_r4_r1;
/*     */   private final ModelRenderer rightTalon4b_r4_r2;
/*     */   private final ModelRenderer rightTalon3b_r3_r3;
/*     */   private final ModelRenderer rightTalon1c;
/*     */   private final ModelRenderer rightTalon1c_r;
/*     */   private final ModelRenderer rightTalon4c_r4_r1;
/*     */   private final ModelRenderer rightTalon4c_r4_r2;
/*     */   private final ModelRenderer rightTalon3c_r3_r3;
/*     */   private final ModelRenderer rightTalon3;
/*     */   private final ModelRenderer rightTalonb3;
/*     */   private final ModelRenderer rightTalonb_r3;
/*     */   private final ModelRenderer rightTalonc3;
/*     */   private final ModelRenderer rightTalonc_r3;
/*     */   private final ModelRenderer rightTalon4;
/*     */   private final ModelRenderer rightTalon3_r;
/*     */   private final ModelRenderer rightTalon3b;
/*     */   private final ModelRenderer rightTalon3c;
/*     */   private final ModelRenderer rightTalon9;
/*     */   private final ModelRenderer rightTalon6_r1;
/*     */   private final ModelRenderer rightTalon4b;
/*     */   private final ModelRenderer rightTalon4b_r;
/*     */   private final ModelRenderer rightTalon4b_r3_r1;
/*     */   
/*     */   public PhoenixFlyModel() {
/* 107 */     super(1.0F);
/* 108 */     this.field_78090_t = 128;
/* 109 */     this.field_78089_u = 64;
/*     */     
/* 111 */     this.head = new ModelRenderer((Model)this);
/* 112 */     this.head.func_78793_a(-1.5F, 1.2F, -16.0F);
/* 113 */     setRotationAngle(this.head, 0.0524F, 0.0F, 0.0F);
/* 114 */     this.head.func_78784_a(0, 4).func_228303_a_(0.0F, 0.4993F, -0.0262F, 3.0F, 3.0F, 2.0F, 0.0F, false);
/*     */     
/* 116 */     this.head2 = new ModelRenderer((Model)this);
/* 117 */     this.head2.func_78793_a(0.0F, 1.0F, -1.7F);
/* 118 */     this.head.func_78792_a(this.head2);
/* 119 */     setRotationAngle(this.head2, -0.0175F, 0.0F, 0.0F);
/* 120 */     this.head2.func_78784_a(0, 10).func_228303_a_(0.0F, 0.4997F, -0.0174F, 3.0F, 2.0F, 2.0F, 0.0F, false);
/*     */     
/* 122 */     this.beak = new ModelRenderer((Model)this);
/* 123 */     this.beak.func_78793_a(0.5F, 0.8F, -1.3F);
/* 124 */     this.head2.func_78792_a(this.beak);
/* 125 */     setRotationAngle(this.beak, -0.0175F, 0.0F, 0.0F);
/* 126 */     this.beak.func_78784_a(33, 0).func_228303_a_(0.0F, 0.4999F, -0.0087F, 2.0F, 1.0F, 2.0F, 0.01F, false);
/*     */     
/* 128 */     this.beak2 = new ModelRenderer((Model)this);
/* 129 */     this.beak2.func_78793_a(1.5F, 0.0F, 2.0F);
/* 130 */     this.beak.func_78792_a(this.beak2);
/* 131 */     setRotationAngle(this.beak2, 0.0F, 0.2618F, 0.0F);
/* 132 */     this.beak2.func_78784_a(33, 4).func_228303_a_(0.0023F, 0.4999F, -3.0084F, 1.0F, 1.0F, 3.0F, 0.0F, false);
/*     */     
/* 134 */     this.beak3 = new ModelRenderer((Model)this);
/* 135 */     this.beak3.func_78793_a(0.5F, 0.0F, 2.0F);
/* 136 */     this.beak.func_78792_a(this.beak3);
/* 137 */     setRotationAngle(this.beak3, 0.0F, -0.2618F, 0.0F);
/* 138 */     this.beak3.func_78784_a(33, 9).func_228303_a_(-1.0023F, 0.4999F, -3.0084F, 1.0F, 1.0F, 3.0F, 0.0F, false);
/*     */     
/* 140 */     this.beak4 = new ModelRenderer((Model)this);
/* 141 */     this.beak4.func_78793_a(1.0F, 0.0F, -1.8F);
/* 142 */     this.beak.func_78792_a(this.beak4);
/* 143 */     setRotationAngle(this.beak4, 0.0F, 0.7854F, 0.0F);
/* 144 */     this.beak4.func_78784_a(33, 14).func_228303_a_(-0.9938F, 0.4999F, -0.0062F, 1.0F, 1.0F, 1.0F, -0.01F, false);
/*     */     
/* 146 */     this.head3 = new ModelRenderer((Model)this);
/* 147 */     this.head3.func_78793_a(0.0F, 1.1F, -1.7F);
/* 148 */     this.head.func_78792_a(this.head3);
/* 149 */     setRotationAngle(this.head3, 0.5236F, 0.0F, 0.0F);
/* 150 */     this.head3.func_78784_a(0, 0).func_228303_a_(0.0F, 0.4193F, -0.2723F, 3.0F, 1.0F, 2.0F, -0.01F, false);
/*     */     
/* 152 */     this.neck = new ModelRenderer((Model)this);
/* 153 */     this.neck.func_78793_a(-1.0F, 2.0F, -14.0F);
/* 154 */     setRotationAngle(this.neck, 0.0175F, 0.0F, 0.0F);
/* 155 */     this.neck.func_78784_a(11, 0).func_228303_a_(0.0F, 0.4999F, -0.0087F, 2.0F, 2.0F, 5.0F, 0.0F, false);
/*     */     
/* 157 */     this.neckFire = new ModelRenderer((Model)this);
/* 158 */     this.neckFire.func_78793_a(1.0F, -2.9956F, -1.75F);
/* 159 */     this.neck.func_78792_a(this.neckFire);
/* 160 */     setRotationAngle(this.neckFire, -0.0175F, 0.0F, 0.0F);
/* 161 */     this.neckFire.func_78784_a(78, 3).func_228303_a_(0.0F, 0.5F, 0.0F, 0.0F, 3.0F, 7.0F, 0.0F, false);
/*     */     
/* 163 */     this.body = new ModelRenderer((Model)this);
/* 164 */     this.body.func_78793_a(-2.5F, 6.0F, -9.0F);
/* 165 */     setRotationAngle(this.body, 1.5708F, 0.0F, 0.0F);
/* 166 */     this.body.func_78784_a(1, 19).func_228303_a_(0.0F, 0.0F, 0.0F, 5.0F, 18.0F, 5.0F, 0.0F, false);
/*     */     
/* 168 */     this.body2 = new ModelRenderer((Model)this);
/* 169 */     this.body2.func_78793_a(-2.0F, 1.5F, 1.0F);
/* 170 */     this.body.func_78792_a(this.body2);
/* 171 */     this.body2.func_78784_a(24, 20).func_228303_a_(0.0F, 0.0F, 0.0F, 9.0F, 15.0F, 3.0F, 0.0F, false);
/*     */     
/* 173 */     this.body3 = new ModelRenderer((Model)this);
/* 174 */     this.body3.func_78793_a(-1.0F, 1.0F, 0.5F);
/* 175 */     this.body.func_78792_a(this.body3);
/* 176 */     this.body3.func_78784_a(1, 44).func_228303_a_(0.0F, 0.0F, 0.0F, 7.0F, 16.0F, 4.0F, 0.0F, false);
/*     */     
/* 178 */     this.body4 = new ModelRenderer((Model)this);
/* 179 */     this.body4.func_78793_a(0.5F, -1.0F, 1.0F);
/* 180 */     this.body.func_78792_a(this.body4);
/* 181 */     this.body4.func_78784_a(26, 40).func_228303_a_(0.0F, 0.0F, 0.0F, 4.0F, 21.0F, 3.0F, 0.0F, false);
/*     */     
/* 183 */     this.backFire = new ModelRenderer((Model)this);
/* 184 */     this.backFire.func_78793_a(2.5F, 0.0F, 7.5F);
/* 185 */     this.body.func_78792_a(this.backFire);
/* 186 */     setRotationAngle(this.backFire, -1.5708F, 0.0F, 0.0F);
/* 187 */     this.backFire.func_78784_a(78, -12).func_228303_a_(0.0F, 0.0F, 0.0F, 0.0F, 3.0F, 18.0F, 0.0F, false);
/*     */     
/* 189 */     this.rightWing = new ModelRenderer((Model)this);
/* 190 */     this.rightWing.func_78793_a(-4.0F, 2.0F, -7.0F);
/* 191 */     setRotationAngle(this.rightWing, 0.0F, -0.0436F, 0.0F);
/* 192 */     this.rightWing.func_78784_a(65, 20).func_228303_a_(-13.0F, 1.25F, 0.0F, 13.0F, 0.0F, 10.0F, 0.0F, false);
/*     */     
/* 194 */     this.rightWing2 = new ModelRenderer((Model)this);
/* 195 */     this.rightWing2.func_78793_a(-12.0F, 0.0F, 0.0F);
/* 196 */     this.rightWing.func_78792_a(this.rightWing2);
/* 197 */     setRotationAngle(this.rightWing2, 0.0F, -0.0698F, 0.0F);
/* 198 */     this.rightWing2.func_78784_a(55, 31).func_228303_a_(-15.0F, 1.25F, 0.0F, 15.0F, 0.0F, 12.0F, 0.0F, false);
/*     */     
/* 200 */     this.leftWing = new ModelRenderer((Model)this);
/* 201 */     this.leftWing.func_78793_a(4.0F, 2.0F, -7.0F);
/* 202 */     setRotationAngle(this.leftWing, 0.0F, 0.0436F, 0.0F);
/* 203 */     this.leftWing.func_78784_a(92, 20).func_228303_a_(0.0F, 1.25F, 0.0F, 13.0F, 0.0F, 10.0F, 0.0F, false);
/*     */     
/* 205 */     this.leftWing2 = new ModelRenderer((Model)this);
/* 206 */     this.leftWing2.func_78793_a(12.0F, 0.0F, 0.0F);
/* 207 */     this.leftWing.func_78792_a(this.leftWing2);
/* 208 */     setRotationAngle(this.leftWing2, 0.0F, 0.0524F, 0.0F);
/* 209 */     this.leftWing2.func_78784_a(86, 31).func_228303_a_(0.0F, 1.25F, 0.0F, 15.0F, 0.0F, 12.0F, 0.0F, false);
/*     */     
/* 211 */     this.tail1 = new ModelRenderer((Model)this);
/* 212 */     this.tail1.func_78793_a(0.5F, 3.0F, 11.0F);
/* 213 */     setRotationAngle(this.tail1, 0.0F, -0.0873F, 0.0F);
/* 214 */     this.tail1.func_78784_a(110, 55).func_228303_a_(-2.5F, 0.0F, 0.0F, 2.0F, 0.0F, 9.0F, 0.0F, false);
/*     */     
/* 216 */     this.tail1b = new ModelRenderer((Model)this);
/* 217 */     this.tail1b.func_78793_a(0.0F, 0.0F, 8.5F);
/* 218 */     this.tail1.func_78792_a(this.tail1b);
/* 219 */     this.tail1b.func_78784_a(117, 57).func_228303_a_(-2.5F, 0.0F, 0.0F, 2.0F, 0.0F, 7.0F, 0.0F, false);
/*     */     
/* 221 */     this.tail2 = new ModelRenderer((Model)this);
/* 222 */     this.tail2.func_78793_a(1.25F, 3.0F, 11.0F);
/* 223 */     this.tail2.func_78784_a(110, 55).func_228303_a_(-1.4924F, 0.0F, -0.1744F, 2.0F, 0.0F, 9.0F, 0.0F, false);
/*     */     
/* 225 */     this.tail2b = new ModelRenderer((Model)this);
/* 226 */     this.tail2b.func_78793_a(-1.3924F, 0.0F, 8.3256F);
/* 227 */     this.tail2.func_78792_a(this.tail2b);
/* 228 */     this.tail2b.func_78784_a(117, 57).func_228303_a_(0.0F, 0.0F, 0.0F, 2.0F, 0.0F, 7.0F, 0.0F, false);
/*     */     
/* 230 */     this.tail3 = new ModelRenderer((Model)this);
/* 231 */     this.tail3.func_78793_a(3.0F, 3.0F, 10.0F);
/* 232 */     setRotationAngle(this.tail3, 0.0F, 0.0873F, 0.0F);
/* 233 */     this.tail3.func_78784_a(110, 55).func_228303_a_(-1.4924F, 0.0F, -0.1744F, 2.0F, 0.0F, 9.0F, 0.0F, false);
/*     */     
/* 235 */     this.tail3b = new ModelRenderer((Model)this);
/* 236 */     this.tail3b.func_78793_a(-1.3924F, 0.0F, 8.3256F);
/* 237 */     this.tail3.func_78792_a(this.tail3b);
/* 238 */     setRotationAngle(this.tail3b, 0.0F, 0.1309F, 0.0F);
/* 239 */     this.tail3b.func_78784_a(117, 57).func_228303_a_(0.0F, 0.0F, 0.0F, 2.0F, 0.0F, 7.0F, 0.0F, false);
/*     */     
/* 241 */     this.tail4 = new ModelRenderer((Model)this);
/* 242 */     this.tail4.func_78793_a(-0.75F, 3.0F, 10.25F);
/* 243 */     setRotationAngle(this.tail4, 0.0F, -0.2618F, 0.0F);
/* 244 */     this.tail4.func_78784_a(110, 55).func_228303_a_(-2.5F, 0.0F, 0.0F, 2.0F, 0.0F, 9.0F, 0.0F, false);
/*     */     
/* 246 */     this.tail4b = new ModelRenderer((Model)this);
/* 247 */     this.tail4b.func_78793_a(0.0F, 0.0F, 8.5F);
/* 248 */     this.tail4.func_78792_a(this.tail4b);
/* 249 */     this.tail4b.func_78784_a(117, 57).func_228303_a_(-2.5F, 0.0F, 0.0F, 2.0F, 0.0F, 7.0F, 0.0F, false);
/*     */     
/* 251 */     this.leftLeg = new ModelRenderer((Model)this);
/* 252 */     this.leftLeg.func_78793_a(2.0F, 5.0F, 6.0F);
/* 253 */     setRotationAngle(this.leftLeg, 0.48F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 256 */     this.leftLeg2 = new ModelRenderer((Model)this);
/* 257 */     this.leftLeg2.func_78793_a(0.0F, 6.8761F, -6.7439F);
/* 258 */     this.leftLeg.func_78792_a(this.leftLeg2);
/* 259 */     this.leftLeg2.func_78784_a(43, 46).func_228303_a_(-1.5F, -7.0F, 5.75F, 3.0F, 3.0F, 3.0F, -0.001F, true);
/*     */     
/* 261 */     this.leftLeg3 = new ModelRenderer((Model)this);
/* 262 */     this.leftLeg3.func_78793_a(0.0F, -5.0F, 7.25F);
/* 263 */     this.leftLeg2.func_78792_a(this.leftLeg3);
/* 264 */     setRotationAngle(this.leftLeg3, 0.5236F, 0.0F, 0.0F);
/* 265 */     this.leftLeg3.func_78784_a(45, 54).func_228303_a_(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, true);
/*     */     
/* 267 */     this.leftTalon5 = new ModelRenderer((Model)this);
/* 268 */     this.leftTalon5.func_78793_a(0.75F, 10.5F, -7.0F);
/* 269 */     this.leftLeg3.func_78792_a(this.leftTalon5);
/* 270 */     setRotationAngle(this.leftTalon5, 0.0437F, -0.1309F, 0.0F);
/*     */ 
/*     */     
/* 273 */     this.rightTalon3_r1 = new ModelRenderer((Model)this);
/* 274 */     this.rightTalon3_r1.func_78793_a(-0.0719F, -6.4278F, 7.1532F);
/* 275 */     this.leftTalon5.func_78792_a(this.rightTalon3_r1);
/* 276 */     setRotationAngle(this.rightTalon3_r1, 0.2595F, 0.1338F, -0.0339F);
/* 277 */     this.rightTalon3_r1.func_78784_a(57, 47).func_228303_a_(-0.3781F, -0.7566F, -1.9665F, 1.0F, 1.0F, 2.0F, 0.0F, true);
/*     */     
/* 279 */     this.rightTalon3_r2 = new ModelRenderer((Model)this);
/* 280 */     this.rightTalon3_r2.func_78793_a(-1.4172F, -5.5126F, 5.2954F);
/* 281 */     this.leftTalon5.func_78792_a(this.rightTalon3_r2);
/* 282 */     setRotationAngle(this.rightTalon3_r2, 0.2205F, 0.276F, 0.0041F);
/* 283 */     this.rightTalon3_r2.func_78784_a(57, 47).func_228303_a_(-0.5F, -1.06F, 0.339F, 1.0F, 1.0F, 2.0F, 0.0F, true);
/*     */     
/* 285 */     this.rightTalon2_r1 = new ModelRenderer((Model)this);
/* 286 */     this.rightTalon2_r1.func_78793_a(0.9606F, -5.5496F, 4.8723F);
/* 287 */     this.leftTalon5.func_78792_a(this.rightTalon2_r1);
/* 288 */     setRotationAngle(this.rightTalon2_r1, 0.2595F, 0.0029F, -0.0339F);
/* 289 */     this.rightTalon2_r1.func_78784_a(57, 47).func_228303_a_(-0.5143F, -1.06F, 0.339F, 1.0F, 1.0F, 2.0F, 0.0F, true);
/*     */     
/* 291 */     this.leftTalon1b = new ModelRenderer((Model)this);
/* 292 */     this.leftTalon1b.func_78793_a(0.0F, 0.0F, -1.5F);
/* 293 */     this.leftTalon5.func_78792_a(this.leftTalon1b);
/* 294 */     setRotationAngle(this.leftTalon1b, -0.0436F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 297 */     this.leftTalon1b_r = new ModelRenderer((Model)this);
/* 298 */     this.leftTalon1b_r.func_78793_a(-2.75F, 0.75F, 1.25F);
/* 299 */     this.leftTalon1b.func_78792_a(this.leftTalon1b_r);
/* 300 */     setRotationAngle(this.leftTalon1b_r, 0.0436F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 303 */     this.rightTalon3b_r3_r1 = new ModelRenderer((Model)this);
/* 304 */     this.rightTalon3b_r3_r1.func_78793_a(2.7601F, -4.8487F, 4.5135F);
/* 305 */     this.leftTalon1b_r.func_78792_a(this.rightTalon3b_r3_r1);
/* 306 */     setRotationAngle(this.rightTalon3b_r3_r1, 0.565F, 0.1338F, -0.0339F);
/* 307 */     this.rightTalon3b_r3_r1.func_78784_a(57, 52).func_228303_a_(-0.7801F, -1.7606F, 0.4487F, 1.0F, 1.0F, 2.0F, -0.1F, true);
/*     */     
/* 309 */     this.rightTalon3b_r3_r2 = new ModelRenderer((Model)this);
/* 310 */     this.rightTalon3b_r3_r2.func_78793_a(1.3328F, -6.3163F, 5.5793F);
/* 311 */     this.leftTalon1b_r.func_78792_a(this.rightTalon3b_r3_r2);
/* 312 */     setRotationAngle(this.rightTalon3b_r3_r2, 0.526F, 0.276F, 0.0041F);
/* 313 */     this.rightTalon3b_r3_r2.func_78784_a(57, 52).func_228303_a_(-0.5F, -0.8829F, -1.0195F, 1.0F, 1.0F, 2.0F, -0.1F, true);
/*     */     
/* 315 */     this.rightTalon2b_r2_r1 = new ModelRenderer((Model)this);
/* 316 */     this.rightTalon2b_r2_r1.func_78793_a(3.7106F, -6.3534F, 5.1562F);
/* 317 */     this.leftTalon1b_r.func_78792_a(this.rightTalon2b_r2_r1);
/* 318 */     setRotationAngle(this.rightTalon2b_r2_r1, 0.565F, 0.0029F, -0.0339F);
/* 319 */     this.rightTalon2b_r2_r1.func_78784_a(57, 52).func_228303_a_(-0.5161F, -0.8829F, -1.0195F, 1.0F, 1.0F, 2.0F, -0.1F, true);
/*     */     
/* 321 */     this.leftTalon1c = new ModelRenderer((Model)this);
/* 322 */     this.leftTalon1c.func_78793_a(0.0F, 0.0F, -1.0F);
/* 323 */     this.leftTalon1b.func_78792_a(this.leftTalon1c);
/* 324 */     setRotationAngle(this.leftTalon1c, 0.0873F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 327 */     this.leftTalon1c_r = new ModelRenderer((Model)this);
/* 328 */     this.leftTalon1c_r.func_78793_a(-2.75F, 0.75F, 1.25F);
/* 329 */     this.leftTalon1c.func_78792_a(this.leftTalon1c_r);
/* 330 */     setRotationAngle(this.leftTalon1c_r, 0.0436F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 333 */     this.rightTalon3c_r3_r1 = new ModelRenderer((Model)this);
/* 334 */     this.rightTalon3c_r3_r1.func_78793_a(2.7601F, -4.8487F, 5.5135F);
/* 335 */     this.leftTalon1c_r.func_78792_a(this.rightTalon3c_r3_r1);
/* 336 */     setRotationAngle(this.rightTalon3c_r3_r1, 1.0014F, 0.1309F, -0.0341F);
/* 337 */     this.rightTalon3c_r3_r1.func_78784_a(57, 52).func_228303_a_(-0.8401F, -0.5099F, -0.9865F, 1.0F, 1.0F, 2.0F, -0.25F, true);
/*     */     
/* 339 */     this.rightTalon3c_r3_r2 = new ModelRenderer((Model)this);
/* 340 */     this.rightTalon3c_r3_r2.func_78793_a(1.3328F, -5.5723F, 7.0255F);
/* 341 */     this.leftTalon1c_r.func_78792_a(this.rightTalon3c_r3_r2);
/* 342 */     setRotationAngle(this.rightTalon3c_r3_r2, 0.9589F, 0.2753F, -0.0206F);
/* 343 */     this.rightTalon3c_r3_r2.func_78784_a(57, 52).func_228303_a_(-0.5F, -1.1089F, -2.3174F, 1.0F, 1.0F, 2.0F, -0.25F, true);
/*     */     
/* 345 */     this.rightTalon2c_r2_r1 = new ModelRenderer((Model)this);
/* 346 */     this.rightTalon2c_r2_r1.func_78793_a(3.7106F, -5.6461F, 6.6073F);
/* 347 */     this.leftTalon1c_r.func_78792_a(this.rightTalon2c_r2_r1);
/* 348 */     setRotationAngle(this.rightTalon2c_r2_r1, 1.0014F, 0.0F, -0.0341F);
/* 349 */     this.rightTalon2c_r2_r1.func_78784_a(57, 52).func_228303_a_(-0.4857F, -1.1089F, -2.3174F, 1.0F, 1.0F, 2.0F, -0.25F, true);
/*     */     
/* 351 */     this.leftTalon6 = new ModelRenderer((Model)this);
/* 352 */     this.leftTalon6.func_78793_a(0.0F, 10.5F, -7.0F);
/* 353 */     this.leftLeg3.func_78792_a(this.leftTalon6);
/* 354 */     setRotationAngle(this.leftTalon6, 0.0437F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 357 */     this.leftTalonb2 = new ModelRenderer((Model)this);
/* 358 */     this.leftTalonb2.func_78793_a(0.0F, 0.0F, -1.5F);
/* 359 */     this.leftTalon6.func_78792_a(this.leftTalonb2);
/* 360 */     setRotationAngle(this.leftTalonb2, 0.1833F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 363 */     this.leftTalonb_r2 = new ModelRenderer((Model)this);
/* 364 */     this.leftTalonb_r2.func_78793_a(-2.0F, 0.75F, 1.25F);
/* 365 */     this.leftTalonb2.func_78792_a(this.leftTalonb_r2);
/* 366 */     setRotationAngle(this.leftTalonb_r2, -0.1745F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 369 */     this.leftTalonc2 = new ModelRenderer((Model)this);
/* 370 */     this.leftTalonc2.func_78793_a(0.0F, -0.25F, -2.75F);
/* 371 */     this.leftTalonb2.func_78792_a(this.leftTalonc2);
/* 372 */     setRotationAngle(this.leftTalonc2, 0.0873F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 375 */     this.leftTalonc_r2 = new ModelRenderer((Model)this);
/* 376 */     this.leftTalonc_r2.func_78793_a(-2.0F, 0.8154F, 2.7486F);
/* 377 */     this.leftTalonc2.func_78792_a(this.leftTalonc_r2);
/* 378 */     setRotationAngle(this.leftTalonc_r2, -0.1745F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 381 */     this.leftTalon7 = new ModelRenderer((Model)this);
/* 382 */     this.leftTalon7.func_78793_a(-0.75F, 10.5F, -7.0F);
/* 383 */     this.leftLeg3.func_78792_a(this.leftTalon7);
/* 384 */     setRotationAngle(this.leftTalon7, 0.0873F, 0.1309F, 0.0F);
/*     */ 
/*     */     
/* 387 */     this.leftTalon3_r = new ModelRenderer((Model)this);
/* 388 */     this.leftTalon3_r.func_78793_a(-1.25F, 0.75F, -0.25F);
/* 389 */     this.leftTalon7.func_78792_a(this.leftTalon3_r);
/* 390 */     setRotationAngle(this.leftTalon3_r, -0.0436F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 393 */     this.leftTalon3b = new ModelRenderer((Model)this);
/* 394 */     this.leftTalon3b.func_78793_a(0.0F, 0.0F, -2.0F);
/* 395 */     this.leftTalon7.func_78792_a(this.leftTalon3b);
/* 396 */     setRotationAngle(this.leftTalon3b, -0.0436F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 399 */     this.leftTalon3c = new ModelRenderer((Model)this);
/* 400 */     this.leftTalon3c.func_78793_a(0.0F, 0.0F, -1.25F);
/* 401 */     this.leftTalon3b.func_78792_a(this.leftTalon3c);
/* 402 */     setRotationAngle(this.leftTalon3c, 0.0436F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 405 */     this.leftTalon8 = new ModelRenderer((Model)this);
/* 406 */     this.leftTalon8.func_78793_a(0.0F, 10.5F, -7.25F);
/* 407 */     this.leftLeg3.func_78792_a(this.leftTalon8);
/* 408 */     setRotationAngle(this.leftTalon8, 0.0873F, -3.1416F, 0.0F);
/*     */ 
/*     */     
/* 411 */     this.rightTalon5_r1 = new ModelRenderer((Model)this);
/* 412 */     this.rightTalon5_r1.func_78793_a(-1.0E-4F, -7.5837F, -7.363F);
/* 413 */     this.leftTalon8.func_78792_a(this.rightTalon5_r1);
/* 414 */     setRotationAngle(this.rightTalon5_r1, 0.48F, 0.0F, 0.0F);
/* 415 */     this.rightTalon5_r1.func_78784_a(57, 47).func_228303_a_(-0.5F, -0.5F, -2.0F, 1.0F, 1.0F, 2.0F, -0.1F, true);
/*     */     
/* 417 */     this.leftTalon4b = new ModelRenderer((Model)this);
/* 418 */     this.leftTalon4b.func_78793_a(0.0F, 0.0F, -3.0F);
/* 419 */     this.leftTalon8.func_78792_a(this.leftTalon4b);
/* 420 */     setRotationAngle(this.leftTalon4b, 0.1309F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 423 */     this.leftTalon4b_r = new ModelRenderer((Model)this);
/* 424 */     this.leftTalon4b_r.func_78793_a(-2.0F, 0.9665F, 1.4763F);
/* 425 */     this.leftTalon4b.func_78792_a(this.leftTalon4b_r);
/* 426 */     setRotationAngle(this.leftTalon4b_r, -0.0873F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 429 */     this.rightTalon4b_r2_r1 = new ModelRenderer((Model)this);
/* 430 */     this.rightTalon4b_r2_r1.func_78793_a(1.9999F, -7.6703F, -7.0028F);
/* 431 */     this.leftTalon4b_r.func_78792_a(this.rightTalon4b_r2_r1);
/* 432 */     setRotationAngle(this.rightTalon4b_r2_r1, 1.0908F, 0.0F, 0.0F);
/* 433 */     this.rightTalon4b_r2_r1.func_78784_a(57, 52).func_228303_a_(-0.5F, -0.7456F, -1.6073F, 1.0F, 1.0F, 2.0F, -0.25F, true);
/*     */     
/* 435 */     this.rightLeg = new ModelRenderer((Model)this);
/* 436 */     this.rightLeg.func_78793_a(-2.0F, 5.0F, 6.0F);
/* 437 */     setRotationAngle(this.rightLeg, 0.48F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 440 */     this.rightLeg2 = new ModelRenderer((Model)this);
/* 441 */     this.rightLeg2.func_78793_a(0.0F, 6.8761F, -6.7439F);
/* 442 */     this.rightLeg.func_78792_a(this.rightLeg2);
/* 443 */     this.rightLeg2.func_78784_a(43, 46).func_228303_a_(-1.5F, -7.0F, 5.75F, 3.0F, 3.0F, 3.0F, -0.001F, false);
/*     */     
/* 445 */     this.rightLeg3 = new ModelRenderer((Model)this);
/* 446 */     this.rightLeg3.func_78793_a(0.0F, -5.0F, 7.25F);
/* 447 */     this.rightLeg2.func_78792_a(this.rightLeg3);
/* 448 */     setRotationAngle(this.rightLeg3, 0.5236F, 0.0F, 0.0F);
/* 449 */     this.rightLeg3.func_78784_a(45, 54).func_228303_a_(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
/*     */     
/* 451 */     this.rightTalon = new ModelRenderer((Model)this);
/* 452 */     this.rightTalon.func_78793_a(-0.75F, 10.5F, -7.0F);
/* 453 */     this.rightLeg3.func_78792_a(this.rightTalon);
/* 454 */     setRotationAngle(this.rightTalon, 0.0437F, 0.1309F, 0.0F);
/*     */ 
/*     */     
/* 457 */     this.rightTalon4_r1 = new ModelRenderer((Model)this);
/* 458 */     this.rightTalon4_r1.func_78793_a(0.0719F, -6.4278F, 7.1532F);
/* 459 */     this.rightTalon.func_78792_a(this.rightTalon4_r1);
/* 460 */     setRotationAngle(this.rightTalon4_r1, 0.2595F, -0.1338F, 0.0339F);
/* 461 */     this.rightTalon4_r1.func_78784_a(57, 47).func_228303_a_(-0.6219F, -0.7566F, -1.9665F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 463 */     this.rightTalon4_r2 = new ModelRenderer((Model)this);
/* 464 */     this.rightTalon4_r2.func_78793_a(1.4172F, -5.5126F, 5.2954F);
/* 465 */     this.rightTalon.func_78792_a(this.rightTalon4_r2);
/* 466 */     setRotationAngle(this.rightTalon4_r2, 0.2205F, -0.276F, -0.0041F);
/* 467 */     this.rightTalon4_r2.func_78784_a(57, 47).func_228303_a_(-0.5F, -1.06F, 0.339F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 469 */     this.rightTalon3_r3 = new ModelRenderer((Model)this);
/* 470 */     this.rightTalon3_r3.func_78793_a(-0.9606F, -5.5496F, 4.8723F);
/* 471 */     this.rightTalon.func_78792_a(this.rightTalon3_r3);
/* 472 */     setRotationAngle(this.rightTalon3_r3, 0.2595F, -0.0029F, 0.0339F);
/* 473 */     this.rightTalon3_r3.func_78784_a(57, 47).func_228303_a_(-0.4857F, -1.06F, 0.339F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 475 */     this.rightTalon1b = new ModelRenderer((Model)this);
/* 476 */     this.rightTalon1b.func_78793_a(0.0F, 0.0F, -1.5F);
/* 477 */     this.rightTalon.func_78792_a(this.rightTalon1b);
/* 478 */     setRotationAngle(this.rightTalon1b, -0.0436F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 481 */     this.rightTalon1b_r = new ModelRenderer((Model)this);
/* 482 */     this.rightTalon1b_r.func_78793_a(2.75F, 0.75F, 1.25F);
/* 483 */     this.rightTalon1b.func_78792_a(this.rightTalon1b_r);
/* 484 */     setRotationAngle(this.rightTalon1b_r, 0.0436F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 487 */     this.rightTalon4b_r4_r1 = new ModelRenderer((Model)this);
/* 488 */     this.rightTalon4b_r4_r1.func_78793_a(-2.7601F, -4.8487F, 4.5135F);
/* 489 */     this.rightTalon1b_r.func_78792_a(this.rightTalon4b_r4_r1);
/* 490 */     setRotationAngle(this.rightTalon4b_r4_r1, 0.565F, -0.1338F, 0.0339F);
/* 491 */     this.rightTalon4b_r4_r1.func_78784_a(57, 52).func_228303_a_(-0.2199F, -1.7606F, 0.4487F, 1.0F, 1.0F, 2.0F, -0.1F, false);
/*     */     
/* 493 */     this.rightTalon4b_r4_r2 = new ModelRenderer((Model)this);
/* 494 */     this.rightTalon4b_r4_r2.func_78793_a(-1.3328F, -6.3163F, 5.5793F);
/* 495 */     this.rightTalon1b_r.func_78792_a(this.rightTalon4b_r4_r2);
/* 496 */     setRotationAngle(this.rightTalon4b_r4_r2, 0.526F, -0.276F, -0.0041F);
/* 497 */     this.rightTalon4b_r4_r2.func_78784_a(57, 52).func_228303_a_(-0.5F, -0.8829F, -1.0195F, 1.0F, 1.0F, 2.0F, -0.1F, false);
/*     */     
/* 499 */     this.rightTalon3b_r3_r3 = new ModelRenderer((Model)this);
/* 500 */     this.rightTalon3b_r3_r3.func_78793_a(-3.7106F, -6.3534F, 5.1562F);
/* 501 */     this.rightTalon1b_r.func_78792_a(this.rightTalon3b_r3_r3);
/* 502 */     setRotationAngle(this.rightTalon3b_r3_r3, 0.565F, -0.0029F, 0.0339F);
/* 503 */     this.rightTalon3b_r3_r3.func_78784_a(57, 52).func_228303_a_(-0.4839F, -0.8829F, -1.0195F, 1.0F, 1.0F, 2.0F, -0.1F, false);
/*     */     
/* 505 */     this.rightTalon1c = new ModelRenderer((Model)this);
/* 506 */     this.rightTalon1c.func_78793_a(0.0F, 0.0F, -1.0F);
/* 507 */     this.rightTalon1b.func_78792_a(this.rightTalon1c);
/* 508 */     setRotationAngle(this.rightTalon1c, 0.0873F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 511 */     this.rightTalon1c_r = new ModelRenderer((Model)this);
/* 512 */     this.rightTalon1c_r.func_78793_a(2.75F, 0.75F, 1.25F);
/* 513 */     this.rightTalon1c.func_78792_a(this.rightTalon1c_r);
/* 514 */     setRotationAngle(this.rightTalon1c_r, 0.0436F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 517 */     this.rightTalon4c_r4_r1 = new ModelRenderer((Model)this);
/* 518 */     this.rightTalon4c_r4_r1.func_78793_a(-2.7601F, -4.8487F, 5.5135F);
/* 519 */     this.rightTalon1c_r.func_78792_a(this.rightTalon4c_r4_r1);
/* 520 */     setRotationAngle(this.rightTalon4c_r4_r1, 1.0014F, -0.1309F, 0.0341F);
/* 521 */     this.rightTalon4c_r4_r1.func_78784_a(57, 52).func_228303_a_(-0.1599F, -0.5099F, -0.9865F, 1.0F, 1.0F, 2.0F, -0.25F, false);
/*     */     
/* 523 */     this.rightTalon4c_r4_r2 = new ModelRenderer((Model)this);
/* 524 */     this.rightTalon4c_r4_r2.func_78793_a(-1.3328F, -5.5723F, 7.0255F);
/* 525 */     this.rightTalon1c_r.func_78792_a(this.rightTalon4c_r4_r2);
/* 526 */     setRotationAngle(this.rightTalon4c_r4_r2, 0.9589F, -0.2753F, 0.0206F);
/* 527 */     this.rightTalon4c_r4_r2.func_78784_a(57, 52).func_228303_a_(-0.5F, -1.1089F, -2.3174F, 1.0F, 1.0F, 2.0F, -0.25F, false);
/*     */     
/* 529 */     this.rightTalon3c_r3_r3 = new ModelRenderer((Model)this);
/* 530 */     this.rightTalon3c_r3_r3.func_78793_a(-3.7106F, -5.6461F, 6.6073F);
/* 531 */     this.rightTalon1c_r.func_78792_a(this.rightTalon3c_r3_r3);
/* 532 */     setRotationAngle(this.rightTalon3c_r3_r3, 1.0014F, 0.0F, 0.0341F);
/* 533 */     this.rightTalon3c_r3_r3.func_78784_a(57, 52).func_228303_a_(-0.5143F, -1.1089F, -2.3174F, 1.0F, 1.0F, 2.0F, -0.25F, false);
/*     */     
/* 535 */     this.rightTalon3 = new ModelRenderer((Model)this);
/* 536 */     this.rightTalon3.func_78793_a(0.0F, 10.5F, -7.0F);
/* 537 */     this.rightLeg3.func_78792_a(this.rightTalon3);
/* 538 */     setRotationAngle(this.rightTalon3, 0.0437F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 541 */     this.rightTalonb3 = new ModelRenderer((Model)this);
/* 542 */     this.rightTalonb3.func_78793_a(0.0F, 0.0F, -1.5F);
/* 543 */     this.rightTalon3.func_78792_a(this.rightTalonb3);
/* 544 */     setRotationAngle(this.rightTalonb3, 0.1833F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 547 */     this.rightTalonb_r3 = new ModelRenderer((Model)this);
/* 548 */     this.rightTalonb_r3.func_78793_a(2.0F, 0.75F, 1.25F);
/* 549 */     this.rightTalonb3.func_78792_a(this.rightTalonb_r3);
/* 550 */     setRotationAngle(this.rightTalonb_r3, -0.1745F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 553 */     this.rightTalonc3 = new ModelRenderer((Model)this);
/* 554 */     this.rightTalonc3.func_78793_a(0.0F, -0.25F, -2.75F);
/* 555 */     this.rightTalonb3.func_78792_a(this.rightTalonc3);
/* 556 */     setRotationAngle(this.rightTalonc3, 0.0873F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 559 */     this.rightTalonc_r3 = new ModelRenderer((Model)this);
/* 560 */     this.rightTalonc_r3.func_78793_a(2.0F, 0.8154F, 2.7486F);
/* 561 */     this.rightTalonc3.func_78792_a(this.rightTalonc_r3);
/* 562 */     setRotationAngle(this.rightTalonc_r3, -0.1745F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 565 */     this.rightTalon4 = new ModelRenderer((Model)this);
/* 566 */     this.rightTalon4.func_78793_a(0.75F, 10.5F, -7.0F);
/* 567 */     this.rightLeg3.func_78792_a(this.rightTalon4);
/* 568 */     setRotationAngle(this.rightTalon4, 0.0873F, -0.1309F, 0.0F);
/*     */ 
/*     */     
/* 571 */     this.rightTalon3_r = new ModelRenderer((Model)this);
/* 572 */     this.rightTalon3_r.func_78793_a(1.25F, 0.75F, -0.25F);
/* 573 */     this.rightTalon4.func_78792_a(this.rightTalon3_r);
/* 574 */     setRotationAngle(this.rightTalon3_r, -0.0436F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 577 */     this.rightTalon3b = new ModelRenderer((Model)this);
/* 578 */     this.rightTalon3b.func_78793_a(0.0F, 0.0F, -2.0F);
/* 579 */     this.rightTalon4.func_78792_a(this.rightTalon3b);
/* 580 */     setRotationAngle(this.rightTalon3b, -0.0436F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 583 */     this.rightTalon3c = new ModelRenderer((Model)this);
/* 584 */     this.rightTalon3c.func_78793_a(0.0F, 0.0F, -1.25F);
/* 585 */     this.rightTalon3b.func_78792_a(this.rightTalon3c);
/* 586 */     setRotationAngle(this.rightTalon3c, 0.0436F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 589 */     this.rightTalon9 = new ModelRenderer((Model)this);
/* 590 */     this.rightTalon9.func_78793_a(0.0F, 10.5F, -7.25F);
/* 591 */     this.rightLeg3.func_78792_a(this.rightTalon9);
/* 592 */     setRotationAngle(this.rightTalon9, 0.0873F, 3.1416F, 0.0F);
/*     */ 
/*     */     
/* 595 */     this.rightTalon6_r1 = new ModelRenderer((Model)this);
/* 596 */     this.rightTalon6_r1.func_78793_a(1.0E-4F, -7.5837F, -7.363F);
/* 597 */     this.rightTalon9.func_78792_a(this.rightTalon6_r1);
/* 598 */     setRotationAngle(this.rightTalon6_r1, 0.48F, 0.0F, 0.0F);
/* 599 */     this.rightTalon6_r1.func_78784_a(57, 47).func_228303_a_(-0.5F, -0.5F, -2.0F, 1.0F, 1.0F, 2.0F, -0.1F, false);
/*     */     
/* 601 */     this.rightTalon4b = new ModelRenderer((Model)this);
/* 602 */     this.rightTalon4b.func_78793_a(0.0F, 0.0F, -3.0F);
/* 603 */     this.rightTalon9.func_78792_a(this.rightTalon4b);
/* 604 */     setRotationAngle(this.rightTalon4b, 0.1309F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 607 */     this.rightTalon4b_r = new ModelRenderer((Model)this);
/* 608 */     this.rightTalon4b_r.func_78793_a(2.0F, 0.9665F, 1.4763F);
/* 609 */     this.rightTalon4b.func_78792_a(this.rightTalon4b_r);
/* 610 */     setRotationAngle(this.rightTalon4b_r, -0.0873F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 613 */     this.rightTalon4b_r3_r1 = new ModelRenderer((Model)this);
/* 614 */     this.rightTalon4b_r3_r1.func_78793_a(-1.9999F, -7.6703F, -7.0028F);
/* 615 */     this.rightTalon4b_r.func_78792_a(this.rightTalon4b_r3_r1);
/* 616 */     setRotationAngle(this.rightTalon4b_r3_r1, 1.0908F, 0.0F, 0.0F);
/* 617 */     this.rightTalon4b_r3_r1.func_78784_a(57, 52).func_228303_a_(-0.5F, -0.7456F, -1.6073F, 1.0F, 1.0F, 2.0F, -0.25F, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 623 */     this.field_178723_h.field_78806_j = false;
/* 624 */     this.field_178724_i.field_78806_j = false;
/*     */     
/* 626 */     this.head.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 627 */     this.neck.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 628 */     this.body.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 629 */     this.rightWing.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 630 */     this.leftWing.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 631 */     this.tail1.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 632 */     this.tail2.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 633 */     this.tail3.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 634 */     this.tail4.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 635 */     this.rightLeg.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 636 */     this.leftLeg.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 642 */     if ((entity.func_213322_ci()).field_72448_b < -1.7D) {
/*     */       
/* 644 */       this.leftWing.field_78795_f = (float)(this.leftWing.field_78795_f + Math.toRadians(-90.0D));
/* 645 */       this.leftWing.field_78796_g = (float)(this.leftWing.field_78796_g + Math.toRadians(-85.0D));
/* 646 */       this.leftWing2.field_78808_h = (float)(this.leftWing2.field_78808_h + Math.toRadians(-5.0D));
/* 647 */       this.leftWing.field_78796_g += MathHelper.func_76134_b(((LivingEntity)entity).field_70173_aa * 0.9F) / 50.0F;
/* 648 */       this.leftWing.field_78808_h += MathHelper.func_76134_b(((LivingEntity)entity).field_70173_aa * 2.9F) / 50.0F;
/*     */       
/* 650 */       this.rightWing.field_78795_f = (float)(this.rightWing.field_78795_f + Math.toRadians(-90.0D));
/* 651 */       this.rightWing.field_78796_g = (float)(this.rightWing.field_78796_g + Math.toRadians(85.0D));
/* 652 */       this.rightWing2.field_78808_h = (float)(this.rightWing2.field_78808_h + Math.toRadians(-5.0D));
/* 653 */       this.rightWing.field_78796_g += MathHelper.func_76134_b(((LivingEntity)entity).field_70173_aa * 0.9F) / 50.0F;
/* 654 */       this.rightWing.field_78808_h += MathHelper.func_76134_b(((LivingEntity)entity).field_70173_aa * 2.9F) / 50.0F;
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 659 */       this.leftWing.field_78808_h = MathHelper.func_76134_b(ageInTicks * 0.4F) * 0.7F;
/* 660 */       this.rightWing.field_78808_h = MathHelper.func_76134_b(ageInTicks * 0.4F + 3.1415927F) * 0.7F;
/* 661 */       this.leftWing2.field_78808_h = MathHelper.func_76134_b(ageInTicks * 0.4F) * 0.4F;
/* 662 */       this.rightWing2.field_78808_h = MathHelper.func_76134_b(ageInTicks * 0.4F + 3.1415927F) * 0.4F;
/*     */     } 
/*     */     
/* 665 */     this.tail1.field_78795_f = 0.5F * MathHelper.func_76134_b(ageInTicks * 0.4F) * 0.3F;
/* 666 */     this.tail1.field_78796_g = (float)(this.tail1.field_78796_g + Math.sin(ageInTicks * 0.04D) / 10.0D);
/* 667 */     this.tail1b.field_78795_f = 0.2F * MathHelper.func_76134_b(ageInTicks * 0.5F) * 0.8F;
/* 668 */     this.tail1b.field_78796_g = (float)(this.tail1b.field_78796_g + Math.sin(ageInTicks * 0.02D) / 5.0D);
/*     */     
/* 670 */     this.tail2.field_78795_f = 0.5F * MathHelper.func_76134_b(ageInTicks * 0.4F + 3.1415927F) * 0.1F;
/* 671 */     this.tail2.field_78796_g = (float)(this.tail2.field_78796_g + Math.sin(ageInTicks * 0.04D) / 10.0D);
/* 672 */     this.tail2b.field_78795_f = 0.2F * MathHelper.func_76134_b(ageInTicks * 0.5F + 3.1415927F) * 0.4F;
/* 673 */     this.tail2b.field_78796_g = (float)(this.tail2b.field_78796_g + Math.sin(ageInTicks * 0.02D) / 5.0D);
/*     */     
/* 675 */     this.tail3.field_78795_f = 0.5F * MathHelper.func_76134_b(ageInTicks * 0.3F + 3.1415927F) * 0.4F;
/* 676 */     this.tail3.field_78796_g = (float)(this.tail3.field_78796_g - Math.sin(ageInTicks * 0.04D) / 10.0D);
/* 677 */     this.tail3b.field_78795_f = 0.2F * MathHelper.func_76134_b(ageInTicks * 0.5F + 3.1415927F) * 0.8F;
/* 678 */     this.tail3b.field_78796_g = (float)(this.tail3b.field_78796_g - Math.sin(ageInTicks * 0.02D) / 5.0D);
/*     */     
/* 680 */     this.tail4.field_78795_f = 0.5F * MathHelper.func_76134_b(ageInTicks * 0.4F) * 0.1F;
/* 681 */     this.tail4.field_78796_g = (float)(this.tail4.field_78796_g - Math.sin(ageInTicks * 0.04D) / 9.0D);
/* 682 */     this.tail4b.field_78795_f = 0.2F * MathHelper.func_76134_b(ageInTicks * 0.5F) * 0.5F;
/* 683 */     this.tail4b.field_78796_g = (float)(this.tail4b.field_78796_g - Math.sin(ageInTicks * 0.02D) / 3.0D);
/*     */ 
/*     */     
/* 686 */     this.field_217112_c = ((LivingEntity)entity).field_70733_aJ;
/* 687 */     if (this.field_217112_c > 0.0F) {
/*     */       
/* 689 */       this.head.field_78796_g += this.body.field_78796_g;
/* 690 */       float f1 = 1.0F - this.field_217112_c;
/* 691 */       f1 *= f1;
/* 692 */       f1 *= f1;
/* 693 */       f1 = 1.0F - f1;
/* 694 */       float f2 = MathHelper.func_76126_a(f1 * 3.1415927F);
/* 695 */       float f3 = MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * -(this.head.field_78795_f - 0.1F) * 0.15F;
/* 696 */       this.head.field_78795_f = (float)(this.head.field_78795_f - f2 * 1.5D + f3);
/* 697 */       this.head.field_78808_h -= MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * -0.4F;
/*     */     } 
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
/*     */   
/*     */   public void func_225599_a_(HandSide side, MatrixStack matrixStack) {
/* 715 */     this.head.func_228307_a_(matrixStack);
/* 716 */     matrixStack.func_227862_a_(0.5F, 0.5F, 0.5F);
/* 717 */     matrixStack.func_227863_a_(Vector3f.field_229183_f_.func_229187_a_(90.0F));
/* 718 */     matrixStack.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(260.0F));
/* 719 */     matrixStack.func_227861_a_(0.35D, -0.1D, -0.1D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 724 */     modelRenderer.field_78795_f = x;
/* 725 */     modelRenderer.field_78796_g = y;
/* 726 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\morphs\PhoenixFlyModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */