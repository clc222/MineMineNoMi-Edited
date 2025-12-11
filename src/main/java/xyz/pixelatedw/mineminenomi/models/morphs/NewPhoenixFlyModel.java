/*     */ package xyz.pixelatedw.mineminenomi.models.morphs;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ 
/*     */ public class NewPhoenixFlyModel<E extends Entity>
/*     */   extends EntityModel<E> {
/*     */   private final ModelRenderer head;
/*     */   private final ModelRenderer head2;
/*     */   private final ModelRenderer head3;
/*     */   private final ModelRenderer head5;
/*     */   private final ModelRenderer head6;
/*     */   private final ModelRenderer beak;
/*     */   private final ModelRenderer beak2;
/*     */   private final ModelRenderer body;
/*     */   private final ModelRenderer body1;
/*     */   private final ModelRenderer body2;
/*     */   private final ModelRenderer body3;
/*     */   private final ModelRenderer body4;
/*     */   private final ModelRenderer body5;
/*     */   private final ModelRenderer body6;
/*     */   private final ModelRenderer tail;
/*     */   private final ModelRenderer tail1;
/*     */   private final ModelRenderer tail1b;
/*     */   private final ModelRenderer tail1c;
/*     */   private final ModelRenderer tail2;
/*     */   private final ModelRenderer tail2b;
/*     */   private final ModelRenderer tail2c;
/*     */   private final ModelRenderer tail3;
/*     */   private final ModelRenderer tail3b;
/*     */   private final ModelRenderer tail3c;
/*     */   private final ModelRenderer tail4;
/*     */   private final ModelRenderer tail4b;
/*     */   private final ModelRenderer tail4c;
/*     */   private final ModelRenderer backFlames;
/*     */   private final ModelRenderer rightBackFlames;
/*     */   private final ModelRenderer rightBackFlames2;
/*     */   private final ModelRenderer RightFlame2_r1;
/*     */   private final ModelRenderer leftBackFlames;
/*     */   private final ModelRenderer leftBackFlames2;
/*     */   private final ModelRenderer LeftFlame2_r1;
/*     */   private final ModelRenderer leftLeg;
/*     */   private final ModelRenderer UpperLeftLeg1_r1;
/*     */   private final ModelRenderer leftClaw;
/*     */   private final ModelRenderer leftBackTalon;
/*     */   private final ModelRenderer LeftBackTalon_r1;
/*     */   private final ModelRenderer leftBackTalon2;
/*     */   private final ModelRenderer LeftBackTalon2_r1;
/*     */   private final ModelRenderer leftFrontTalons;
/*     */   private final ModelRenderer leftFrontTalon1;
/*     */   private final ModelRenderer LeftPinkyTalon1_r1;
/*     */   private final ModelRenderer leftFrontTalon1b;
/*     */   private final ModelRenderer LeftPinkyTalon2_r1;
/*     */   private final ModelRenderer leftFrontTalon1c;
/*     */   private final ModelRenderer LeftPinkyTalon3_r1;
/*     */   private final ModelRenderer leftFrontTalon2;
/*     */   private final ModelRenderer LeftMiddleTalon1_r1;
/*     */   private final ModelRenderer leftFrontTalon2b;
/*     */   private final ModelRenderer LeftMiddleTalon2_r1;
/*     */   private final ModelRenderer leftFrontTalon2c;
/*     */   private final ModelRenderer LeftMiddleTalon3_r1;
/*     */   private final ModelRenderer leftFrontTalon3;
/*     */   private final ModelRenderer LeftPointTalon1_r1;
/*     */   private final ModelRenderer leftFrontTalon3b;
/*     */   private final ModelRenderer LeftPointTalon2_r1;
/*     */   private final ModelRenderer leftFrontTalon3c;
/*     */   private final ModelRenderer LeftPointTalon3_r1;
/*     */   private final ModelRenderer rightLeg;
/*     */   private final ModelRenderer UpperRightLeg1_r1;
/*     */   private final ModelRenderer rightClaw;
/*     */   private final ModelRenderer rightBackTalon;
/*     */   private final ModelRenderer RightBackTalon1_r1;
/*     */   private final ModelRenderer rightBackTalon2;
/*     */   private final ModelRenderer RightBackTalon2_r1;
/*     */   private final ModelRenderer rightFrontTalons;
/*     */   private final ModelRenderer rightFrontTalons1;
/*     */   private final ModelRenderer RightPinkyTalon1_r1;
/*     */   private final ModelRenderer rightFrontTalons1b;
/*     */   private final ModelRenderer RightPinkyTalon2_r1;
/*     */   private final ModelRenderer rightFrontTalons1c;
/*     */   private final ModelRenderer RightPinkyTalon3_r1;
/*     */   private final ModelRenderer rightFrontTalons2;
/*     */   private final ModelRenderer RightMiddleTalon1_r1;
/*     */   private final ModelRenderer rightFrontTalons2b;
/*     */   private final ModelRenderer RightMiddleTalon2_r1;
/*     */   private final ModelRenderer rightFrontTalons2c;
/*     */   private final ModelRenderer RightMiddleTalon3_r1;
/*     */   private final ModelRenderer rightFrontTalons3;
/*     */   private final ModelRenderer RightPointTalon1_r1;
/*     */   private final ModelRenderer rightFrontTalons3b;
/*     */   private final ModelRenderer RightPointTalon2_r1;
/*     */   private final ModelRenderer rightFrontTalons3c;
/*     */   private final ModelRenderer RightPointTalon3_r1;
/*     */   private final ModelRenderer leftWing;
/*     */   private final ModelRenderer LeftWingSection3Feather3_r1;
/*     */   private final ModelRenderer leftWing2;
/*     */   private final ModelRenderer LeftWingSection3Feather3_r2;
/*     */   private final ModelRenderer LeftWingBone2_r1;
/*     */   private final ModelRenderer leftWing3;
/*     */   private final ModelRenderer LeftWingSection3Feather3_r3;
/*     */   private final ModelRenderer LeftWingBone3_r1;
/*     */   private final ModelRenderer leftWing4;
/*     */   private final ModelRenderer rightWing;
/*     */   private final ModelRenderer RightWingSection3Feather3_r1;
/*     */   private final ModelRenderer rightWing2;
/*     */   private final ModelRenderer RightWingSection3Feather3_r2;
/*     */   private final ModelRenderer RightWingBone2_r1;
/*     */   private final ModelRenderer rightWing3;
/*     */   private final ModelRenderer RightWingSection3Feather3_r3;
/*     */   private final ModelRenderer RightWingBone3_r1;
/*     */   private final ModelRenderer rightWing4;
/*     */   
/*     */   public NewPhoenixFlyModel() {
/* 118 */     this.field_78090_t = 128;
/* 119 */     this.field_78089_u = 128;
/*     */ 
/*     */     
/* 122 */     this.head = new ModelRenderer((Model)this);
/* 123 */     this.head.func_78793_a(0.0F, 2.567F, -32.5965F);
/* 124 */     setRotationAngle(this.head, 0.0524F, 0.0F, 0.0F);
/* 125 */     this.head.func_78784_a(0, 27).func_228303_a_(-1.5F, 8.387F, 12.1767F, 3.0F, 3.0F, 5.0F, 0.0F, false);
/* 126 */     this.head.func_78784_a(70, 40).func_228303_a_(-1.5F, 8.387F, 9.1767F, 3.0F, 3.0F, 3.0F, -0.2F, false);
/*     */     
/* 128 */     this.head2 = new ModelRenderer((Model)this);
/* 129 */     this.head2.func_78793_a(-1.0176F, 9.887F, 12.6086F);
/* 130 */     this.head.func_78792_a(this.head2);
/* 131 */     setRotationAngle(this.head2, 0.0F, -0.2618F, 0.0F);
/* 132 */     this.head2.func_78784_a(15, 36).func_228303_a_(-1.0F, -1.5F, -2.5F, 2.0F, 3.0F, 5.0F, 0.0F, false);
/*     */     
/* 134 */     this.head3 = new ModelRenderer((Model)this);
/* 135 */     this.head3.func_78793_a(1.0176F, 9.887F, 12.6086F);
/* 136 */     this.head.func_78792_a(this.head3);
/* 137 */     setRotationAngle(this.head3, 0.0F, 0.2618F, 0.0F);
/* 138 */     this.head3.func_78784_a(0, 36).func_228303_a_(-1.0F, -1.5F, -2.5F, 2.0F, 3.0F, 5.0F, 0.0F, true);
/*     */     
/* 140 */     this.head5 = new ModelRenderer((Model)this);
/* 141 */     this.head5.func_78793_a(0.0F, 8.6427F, 12.8981F);
/* 142 */     this.head.func_78792_a(this.head5);
/* 143 */     setRotationAngle(this.head5, 0.3491F, 0.0F, 0.0F);
/* 144 */     this.head5.func_78784_a(17, 28).func_228303_a_(-1.5F, -1.0F, -2.5F, 3.0F, 2.0F, 5.0F, -0.05F, false);
/*     */     
/* 146 */     this.head6 = new ModelRenderer((Model)this);
/* 147 */     this.head6.func_78793_a(0.0F, 8.5386F, 15.7939F);
/* 148 */     this.head.func_78792_a(this.head6);
/* 149 */     setRotationAngle(this.head6, -0.4363F, 0.0F, 0.0F);
/* 150 */     this.head6.func_78784_a(0, 21).func_228303_a_(-1.5F, -1.0F, -1.5F, 3.0F, 2.0F, 3.0F, -0.1F, false);
/*     */     
/* 152 */     this.beak = new ModelRenderer((Model)this);
/* 153 */     this.beak.func_78793_a(0.0F, 11.2963F, 8.5898F);
/* 154 */     this.head.func_78792_a(this.beak);
/* 155 */     setRotationAngle(this.beak, 0.2618F, 0.0F, 0.0F);
/* 156 */     this.beak.func_78784_a(61, 41).func_228303_a_(-0.5F, -1.4345F, -0.8702F, 1.0F, 2.0F, 3.0F, 0.0F, false);
/*     */     
/* 158 */     this.beak2 = new ModelRenderer((Model)this);
/* 159 */     this.beak2.func_78793_a(0.0F, 0.025F, -0.9166F);
/* 160 */     this.beak.func_78792_a(this.beak2);
/* 161 */     setRotationAngle(this.beak2, 0.6109F, 0.0F, 0.0F);
/* 162 */     this.beak2.func_78784_a(54, 43).func_228303_a_(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, -0.05F, false);
/*     */     
/* 164 */     this.body = new ModelRenderer((Model)this);
/* 165 */     this.body.func_78793_a(-2.5F, 6.0F, -9.0F);
/*     */ 
/*     */     
/* 168 */     this.body1 = new ModelRenderer((Model)this);
/* 169 */     this.body1.func_78793_a(2.5F, 5.5827F, 34.3947F);
/* 170 */     this.body.func_78792_a(this.body1);
/* 171 */     setRotationAngle(this.body1, 1.8762F, 0.0F, 0.0F);
/* 172 */     this.body1.func_78784_a(33, 17).func_228303_a_(-3.5F, -2.5F, -2.0F, 7.0F, 5.0F, 4.0F, 0.25F, false);
/*     */     
/* 174 */     this.body2 = new ModelRenderer((Model)this);
/* 175 */     this.body2.func_78793_a(2.5F, 6.1287F, 31.9686F);
/* 176 */     this.body.func_78792_a(this.body2);
/* 177 */     setRotationAngle(this.body2, 1.8326F, 0.0F, 0.0F);
/* 178 */     this.body2.func_78784_a(71, 17).func_228303_a_(-4.5F, -3.0F, -3.0F, 9.0F, 6.0F, 6.0F, 0.0F, false);
/*     */     
/* 180 */     this.body3 = new ModelRenderer((Model)this);
/* 181 */     this.body3.func_78793_a(2.5F, 6.7941F, 24.9276F);
/* 182 */     this.body.func_78792_a(this.body3);
/* 183 */     setRotationAngle(this.body3, 1.5708F, 0.0F, 0.0F);
/* 184 */     this.body3.func_78784_a(98, 0).func_228303_a_(-4.5F, -5.0F, -3.0F, 9.0F, 10.0F, 6.0F, 0.25F, false);
/*     */     
/* 186 */     this.body4 = new ModelRenderer((Model)this);
/* 187 */     this.body4.func_78793_a(2.5F, 6.5708F, 14.9019F);
/* 188 */     this.body.func_78792_a(this.body4);
/* 189 */     setRotationAngle(this.body4, 1.5272F, 0.0F, 0.0F);
/* 190 */     this.body4.func_78784_a(71, 0).func_228303_a_(-3.5F, -5.0F, -3.0F, 7.0F, 10.0F, 6.0F, 0.25F, false);
/*     */     
/* 192 */     this.body5 = new ModelRenderer((Model)this);
/* 193 */     this.body5.func_78793_a(2.5F, 6.2437F, 7.4091F);
/* 194 */     this.body.func_78792_a(this.body5);
/* 195 */     setRotationAngle(this.body5, 1.5272F, 0.0F, 0.0F);
/* 196 */     this.body5.func_78784_a(83, 30).func_228303_a_(-2.5F, -2.5F, -2.0F, 5.0F, 5.0F, 4.0F, 0.25F, false);
/*     */     
/* 198 */     this.body6 = new ModelRenderer((Model)this);
/* 199 */     this.body6.func_78793_a(2.5F, 5.8947F, -0.5833F);
/* 200 */     this.body.func_78792_a(this.body6);
/* 201 */     setRotationAngle(this.body6, 1.5272F, 0.0F, 0.0F);
/* 202 */     this.body6.func_78784_a(52, 23).func_228303_a_(-2.5F, -6.5F, -2.0F, 5.0F, 13.0F, 4.0F, -0.4F, false);
/*     */     
/* 204 */     this.tail = new ModelRenderer((Model)this);
/* 205 */     this.tail.func_78793_a(2.5F, 4.0F, 36.4375F);
/* 206 */     this.body.func_78792_a(this.tail);
/*     */ 
/*     */     
/* 209 */     this.tail1 = new ModelRenderer((Model)this);
/* 210 */     this.tail1.func_78793_a(1.7688F, 0.0F, -0.0883F);
/* 211 */     this.tail.func_78792_a(this.tail1);
/* 212 */     setRotationAngle(this.tail1, 0.0F, 0.2618F, 0.0F);
/* 213 */     this.tail1.func_78784_a(-11, 66).func_228303_a_(-1.2343F, 0.0F, -0.2642F, 3.0F, 0.0F, 14.0F, 0.0F, true);
/*     */     
/* 215 */     this.tail1b = new ModelRenderer((Model)this);
/* 216 */     this.tail1b.func_78793_a(0.2657F, 0.0F, 13.7358F);
/* 217 */     this.tail1.func_78792_a(this.tail1b);
/* 218 */     this.tail1b.func_78784_a(-11, 66).func_228303_a_(-1.5F, 0.0F, 0.0F, 3.0F, 0.0F, 14.0F, 0.0F, true);
/*     */     
/* 220 */     this.tail1c = new ModelRenderer((Model)this);
/* 221 */     this.tail1c.func_78793_a(0.0F, 0.0F, 14.0F);
/* 222 */     this.tail1b.func_78792_a(this.tail1c);
/* 223 */     this.tail1c.func_78784_a(-11, 66).func_228303_a_(-1.5F, 0.0F, 0.0F, 3.0F, 0.0F, 14.0F, 0.0F, true);
/*     */     
/* 225 */     this.tail2 = new ModelRenderer((Model)this);
/* 226 */     this.tail2.func_78793_a(0.377F, 1.0F, 0.3331F);
/* 227 */     this.tail.func_78792_a(this.tail2);
/* 228 */     setRotationAngle(this.tail2, 0.0F, 0.0873F, 0.0F);
/* 229 */     this.tail2.func_78784_a(-11, 66).func_228303_a_(-1.1515F, 0.0F, -0.9466F, 3.0F, 0.0F, 14.0F, 0.0F, true);
/*     */     
/* 231 */     this.tail2b = new ModelRenderer((Model)this);
/* 232 */     this.tail2b.func_78793_a(0.3485F, 0.0F, 13.0534F);
/* 233 */     this.tail2.func_78792_a(this.tail2b);
/* 234 */     this.tail2b.func_78784_a(-11, 66).func_228303_a_(-1.5F, 0.0F, 0.0F, 3.0F, 0.0F, 14.0F, 0.0F, true);
/*     */     
/* 236 */     this.tail2c = new ModelRenderer((Model)this);
/* 237 */     this.tail2c.func_78793_a(0.0F, 0.0F, 14.0F);
/* 238 */     this.tail2b.func_78792_a(this.tail2c);
/* 239 */     this.tail2c.func_78784_a(-11, 66).func_228303_a_(-1.5F, 0.0F, 0.0F, 3.0F, 0.0F, 14.0F, 0.0F, true);
/*     */     
/* 241 */     this.tail3 = new ModelRenderer((Model)this);
/* 242 */     this.tail3.func_78793_a(-1.627F, 0.0F, 0.3331F);
/* 243 */     this.tail.func_78792_a(this.tail3);
/* 244 */     setRotationAngle(this.tail3, 0.0F, -0.0873F, 0.0F);
/* 245 */     this.tail3.func_78784_a(-11, 66).func_228303_a_(-1.5995F, 0.0F, -0.9684F, 3.0F, 0.0F, 14.0F, 0.0F, true);
/*     */     
/* 247 */     this.tail3b = new ModelRenderer((Model)this);
/* 248 */     this.tail3b.func_78793_a(-0.0995F, 0.0F, 13.0316F);
/* 249 */     this.tail3.func_78792_a(this.tail3b);
/* 250 */     this.tail3b.func_78784_a(-11, 66).func_228303_a_(-1.5F, 0.0F, 0.0F, 3.0F, 0.0F, 14.0F, 0.0F, true);
/*     */     
/* 252 */     this.tail3c = new ModelRenderer((Model)this);
/* 253 */     this.tail3c.func_78793_a(0.0F, 0.0F, 14.0F);
/* 254 */     this.tail3b.func_78792_a(this.tail3c);
/* 255 */     this.tail3c.func_78784_a(-11, 66).func_228303_a_(-1.5F, 0.0F, 0.0F, 3.0F, 0.0F, 14.0F, 0.0F, true);
/*     */     
/* 257 */     this.tail4 = new ModelRenderer((Model)this);
/* 258 */     this.tail4.func_78793_a(-1.9563F, 1.0F, 0.3492F);
/* 259 */     this.tail.func_78792_a(this.tail4);
/* 260 */     setRotationAngle(this.tail4, 0.0F, -0.2618F, 0.0F);
/* 261 */     this.tail4.func_78784_a(-11, 66).func_228303_a_(-1.6978F, 0.0F, -0.7353F, 3.0F, 0.0F, 14.0F, 0.0F, true);
/*     */     
/* 263 */     this.tail4b = new ModelRenderer((Model)this);
/* 264 */     this.tail4b.func_78793_a(-0.1978F, 0.0F, 13.2647F);
/* 265 */     this.tail4.func_78792_a(this.tail4b);
/* 266 */     this.tail4b.func_78784_a(-11, 66).func_228303_a_(-1.5F, 0.0F, 0.0F, 3.0F, 0.0F, 14.0F, 0.0F, true);
/*     */     
/* 268 */     this.tail4c = new ModelRenderer((Model)this);
/* 269 */     this.tail4c.func_78793_a(0.0F, 0.0F, 14.0F);
/* 270 */     this.tail4b.func_78792_a(this.tail4c);
/* 271 */     this.tail4c.func_78784_a(-11, 66).func_228303_a_(-1.5F, 0.0F, 0.0F, 3.0F, 0.0F, 14.0F, 0.0F, true);
/*     */     
/* 273 */     this.backFlames = new ModelRenderer((Model)this);
/* 274 */     this.backFlames.func_78793_a(2.5F, 2.4574F, 10.8332F);
/* 275 */     this.body.func_78792_a(this.backFlames);
/* 276 */     this.backFlames.func_78784_a(52, 24).func_228303_a_(0.0F, -5.4574F, -24.8332F, 0.0F, 8.0F, 24.0F, 0.0F, false);
/* 277 */     this.backFlames.func_78784_a(0, 19).func_228303_a_(0.0F, -8.4574F, -0.8332F, 0.0F, 11.0F, 26.0F, 0.0F, false);
/*     */     
/* 279 */     this.rightBackFlames = new ModelRenderer((Model)this);
/* 280 */     this.rightBackFlames.func_78793_a(-2.8523F, -1.8963F, 0.1666F);
/* 281 */     this.backFlames.func_78792_a(this.rightBackFlames);
/* 282 */     setRotationAngle(this.rightBackFlames, 0.0F, 0.0F, -0.2618F);
/* 283 */     this.rightBackFlames.func_78784_a(52, 24).func_228303_a_(0.6876F, -3.4078F, -23.9998F, 0.0F, 8.0F, 24.0F, 0.0F, false);
/*     */     
/* 285 */     this.rightBackFlames2 = new ModelRenderer((Model)this);
/* 286 */     this.rightBackFlames2.func_78793_a(-0.6876F, -0.5922F, 11.9998F);
/* 287 */     this.rightBackFlames.func_78792_a(this.rightBackFlames2);
/* 288 */     setRotationAngle(this.rightBackFlames2, 0.0F, -0.0436F, 0.0F);
/*     */ 
/*     */     
/* 291 */     this.RightFlame2_r1 = new ModelRenderer((Model)this);
/* 292 */     this.RightFlame2_r1.func_78793_a(0.0F, 0.0F, 0.0F);
/* 293 */     this.rightBackFlames2.func_78792_a(this.RightFlame2_r1);
/* 294 */     setRotationAngle(this.RightFlame2_r1, 0.0F, 0.0F, 0.0F);
/* 295 */     this.RightFlame2_r1.func_78784_a(0, 19).func_228303_a_(0.0F, -5.5F, -13.0F, 0.0F, 11.0F, 26.0F, 0.0F, false);
/*     */     
/* 297 */     this.leftBackFlames = new ModelRenderer((Model)this);
/* 298 */     this.leftBackFlames.func_78793_a(2.8523F, -1.8963F, 0.1666F);
/* 299 */     this.backFlames.func_78792_a(this.leftBackFlames);
/* 300 */     setRotationAngle(this.leftBackFlames, 0.0F, 0.0F, 0.2618F);
/* 301 */     this.leftBackFlames.func_78784_a(52, 24).func_228303_a_(-0.6876F, -3.4078F, -23.9998F, 0.0F, 8.0F, 24.0F, 0.0F, true);
/*     */     
/* 303 */     this.leftBackFlames2 = new ModelRenderer((Model)this);
/* 304 */     this.leftBackFlames2.func_78793_a(0.6876F, -0.5922F, 11.9998F);
/* 305 */     this.leftBackFlames.func_78792_a(this.leftBackFlames2);
/* 306 */     setRotationAngle(this.leftBackFlames2, 0.0F, 0.0436F, 0.0F);
/*     */ 
/*     */     
/* 309 */     this.LeftFlame2_r1 = new ModelRenderer((Model)this);
/* 310 */     this.LeftFlame2_r1.func_78793_a(0.0F, 0.0F, 0.0F);
/* 311 */     this.leftBackFlames2.func_78792_a(this.LeftFlame2_r1);
/* 312 */     setRotationAngle(this.LeftFlame2_r1, 0.0F, 0.0F, 0.0F);
/* 313 */     this.LeftFlame2_r1.func_78784_a(0, 19).func_228303_a_(0.0F, -5.5F, -13.0F, 0.0F, 11.0F, 26.0F, 0.0F, true);
/*     */     
/* 315 */     this.leftLeg = new ModelRenderer((Model)this);
/* 316 */     this.leftLeg.func_78793_a(5.5F, 8.6189F, 33.5692F);
/* 317 */     this.body.func_78792_a(this.leftLeg);
/* 318 */     setRotationAngle(this.leftLeg, 0.48F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 321 */     this.UpperLeftLeg1_r1 = new ModelRenderer((Model)this);
/* 322 */     this.UpperLeftLeg1_r1.func_78793_a(-2.0F, -16.6313F, -11.3481F);
/* 323 */     this.leftLeg.func_78792_a(this.UpperLeftLeg1_r1);
/* 324 */     setRotationAngle(this.UpperLeftLeg1_r1, 0.1309F, 0.0F, 0.0F);
/* 325 */     this.UpperLeftLeg1_r1.func_78784_a(49, 5).func_228303_a_(0.5F, 16.8732F, 7.5134F, 3.0F, 5.0F, 3.0F, -0.001F, true);
/*     */     
/* 327 */     this.leftClaw = new ModelRenderer((Model)this);
/* 328 */     this.leftClaw.func_78793_a(0.0F, 3.6836F, 0.5754F);
/* 329 */     this.leftLeg.func_78792_a(this.leftClaw);
/* 330 */     setRotationAngle(this.leftClaw, 0.5236F, 0.0F, 0.0F);
/* 331 */     this.leftClaw.func_78784_a(62, 7).func_228303_a_(-1.0F, -1.009F, -1.2835F, 2.0F, 4.0F, 2.0F, 0.0F, true);
/*     */     
/* 333 */     this.leftBackTalon = new ModelRenderer((Model)this);
/* 334 */     this.leftBackTalon.func_78793_a(0.0F, 2.678F, 0.6186F);
/* 335 */     this.leftClaw.func_78792_a(this.leftBackTalon);
/* 336 */     setRotationAngle(this.leftBackTalon, 0.0873F, -3.1416F, 0.0F);
/*     */ 
/*     */     
/* 339 */     this.LeftBackTalon_r1 = new ModelRenderer((Model)this);
/* 340 */     this.LeftBackTalon_r1.func_78793_a(0.0F, -19.0516F, 3.3734F);
/* 341 */     this.leftBackTalon.func_78792_a(this.LeftBackTalon_r1);
/* 342 */     setRotationAngle(this.LeftBackTalon_r1, 0.48F, 0.0F, 0.0F);
/* 343 */     this.LeftBackTalon_r1.func_78784_a(63, 0).func_228303_a_(-0.5F, 14.8409F, -13.6042F, 1.0F, 1.0F, 3.0F, -0.1F, true);
/*     */     
/* 345 */     this.leftBackTalon2 = new ModelRenderer((Model)this);
/* 346 */     this.leftBackTalon2.func_78793_a(0.0F, 0.7102F, -1.3897F);
/* 347 */     this.leftBackTalon.func_78792_a(this.leftBackTalon2);
/*     */ 
/*     */     
/* 350 */     this.LeftBackTalon2_r1 = new ModelRenderer((Model)this);
/* 351 */     this.LeftBackTalon2_r1.func_78793_a(0.0F, -18.882F, 3.5996F);
/* 352 */     this.leftBackTalon2.func_78792_a(this.LeftBackTalon2_r1);
/* 353 */     setRotationAngle(this.LeftBackTalon2_r1, 1.0908F, 0.0F, 0.0F);
/* 354 */     this.LeftBackTalon2_r1.func_78784_a(64, 2).func_228303_a_(-0.5F, 4.9241F, -20.0273F, 1.0F, 1.0F, 2.0F, -0.25F, true);
/*     */     
/* 356 */     this.leftFrontTalons = new ModelRenderer((Model)this);
/* 357 */     this.leftFrontTalons.func_78793_a(0.0F, 2.7036F, -1.172F);
/* 358 */     this.leftClaw.func_78792_a(this.leftFrontTalons);
/*     */ 
/*     */     
/* 361 */     this.leftFrontTalon1 = new ModelRenderer((Model)this);
/* 362 */     this.leftFrontTalon1.func_78793_a(0.7634F, 0.2945F, -0.0914F);
/* 363 */     this.leftFrontTalons.func_78792_a(this.leftFrontTalon1);
/*     */ 
/*     */     
/* 366 */     this.LeftPinkyTalon1_r1 = new ModelRenderer((Model)this);
/* 367 */     this.LeftPinkyTalon1_r1.func_78793_a(0.4356F, 0.7785F, -2.0459F);
/* 368 */     this.leftFrontTalon1.func_78792_a(this.LeftPinkyTalon1_r1);
/* 369 */     setRotationAngle(this.LeftPinkyTalon1_r1, 0.2537F, -0.2616F, -0.0361F);
/* 370 */     this.LeftPinkyTalon1_r1.func_78784_a(64, 2).func_228303_a_(-0.4964F, -0.8257F, 0.441F, 1.0F, 1.0F, 2.0F, 0.0F, true);
/*     */     
/* 372 */     this.leftFrontTalon1b = new ModelRenderer((Model)this);
/* 373 */     this.leftFrontTalon1b.func_78793_a(0.2202F, 0.2675F, -1.3286F);
/* 374 */     this.leftFrontTalon1.func_78792_a(this.leftFrontTalon1b);
/*     */ 
/*     */     
/* 377 */     this.LeftPinkyTalon2_r1 = new ModelRenderer((Model)this);
/* 378 */     this.LeftPinkyTalon2_r1.func_78793_a(0.2155F, 0.511F, -0.7173F);
/* 379 */     this.leftFrontTalon1b.func_78792_a(this.LeftPinkyTalon2_r1);
/* 380 */     setRotationAngle(this.LeftPinkyTalon2_r1, 0.6177F, -0.2616F, -0.0351F);
/* 381 */     this.LeftPinkyTalon2_r1.func_78784_a(64, 2).func_228303_a_(-0.505F, -0.6526F, -1.0902F, 1.0F, 1.0F, 2.0F, -0.1F, true);
/*     */     
/* 383 */     this.leftFrontTalon1c = new ModelRenderer((Model)this);
/* 384 */     this.leftFrontTalon1c.func_78793_a(0.4844F, 0.8509F, -1.3787F);
/* 385 */     this.leftFrontTalon1b.func_78792_a(this.leftFrontTalon1c);
/*     */ 
/*     */     
/* 388 */     this.LeftPinkyTalon3_r1 = new ModelRenderer((Model)this);
/* 389 */     this.LeftPinkyTalon3_r1.func_78793_a(-0.269F, -0.3399F, 0.6614F);
/* 390 */     this.leftFrontTalon1c.func_78792_a(this.LeftPinkyTalon3_r1);
/* 391 */     setRotationAngle(this.LeftPinkyTalon3_r1, 0.8446F, -0.2616F, -0.0351F);
/* 392 */     this.LeftPinkyTalon3_r1.func_78784_a(64, 2).func_228303_a_(-0.5089F, -0.832F, -2.4331F, 1.0F, 1.0F, 2.0F, -0.25F, true);
/*     */     
/* 394 */     this.leftFrontTalon2 = new ModelRenderer((Model)this);
/* 395 */     this.leftFrontTalon2.func_78793_a(0.0391F, 0.2956F, -0.0288F);
/* 396 */     this.leftFrontTalons.func_78792_a(this.leftFrontTalon2);
/*     */ 
/*     */     
/* 399 */     this.LeftMiddleTalon1_r1 = new ModelRenderer((Model)this);
/* 400 */     this.LeftMiddleTalon1_r1.func_78793_a(0.0154F, 0.113F, -0.7891F);
/* 401 */     this.leftFrontTalon2.func_78792_a(this.LeftMiddleTalon1_r1);
/* 402 */     setRotationAngle(this.LeftMiddleTalon1_r1, 0.2443F, 0.0F, -0.0349F);
/* 403 */     this.LeftMiddleTalon1_r1.func_78784_a(64, 2).func_228303_a_(-0.5245F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F, true);
/*     */     
/* 405 */     this.leftFrontTalon2b = new ModelRenderer((Model)this);
/* 406 */     this.leftFrontTalon2b.func_78793_a(-0.0066F, 0.1426F, -1.6448F);
/* 407 */     this.leftFrontTalon2.func_78792_a(this.leftFrontTalon2b);
/*     */ 
/*     */     
/* 410 */     this.LeftMiddleTalon2_r1 = new ModelRenderer((Model)this);
/* 411 */     this.LeftMiddleTalon2_r1.func_78793_a(-0.2184F, 0.4921F, -1.0601F);
/* 412 */     this.leftFrontTalon2b.func_78792_a(this.LeftMiddleTalon2_r1);
/* 413 */     setRotationAngle(this.LeftMiddleTalon2_r1, 0.6086F, 0.0F, -0.0339F);
/* 414 */     this.LeftMiddleTalon2_r1.func_78784_a(64, 2).func_228303_a_(-0.2841F, -0.1875F, -0.6875F, 1.0F, 1.0F, 2.0F, -0.1F, true);
/*     */     
/* 416 */     this.leftFrontTalon2c = new ModelRenderer((Model)this);
/* 417 */     this.leftFrontTalon2c.func_78793_a(0.0222F, 0.976F, -1.2338F);
/* 418 */     this.leftFrontTalon2b.func_78792_a(this.leftFrontTalon2c);
/*     */ 
/*     */     
/* 421 */     this.LeftMiddleTalon3_r1 = new ModelRenderer((Model)this);
/* 422 */     this.LeftMiddleTalon3_r1.func_78793_a(-0.3608F, -0.223F, -0.7841F);
/* 423 */     this.leftFrontTalon2c.func_78792_a(this.LeftMiddleTalon3_r1);
/* 424 */     setRotationAngle(this.LeftMiddleTalon3_r1, 0.8355F, 0.0F, -0.0339F);
/* 425 */     this.LeftMiddleTalon3_r1.func_78784_a(64, 2).func_228303_a_(-0.1639F, 0.25F, -1.3125F, 1.0F, 1.0F, 2.0F, -0.25F, true);
/*     */     
/* 427 */     this.leftFrontTalon3 = new ModelRenderer((Model)this);
/* 428 */     this.leftFrontTalon3.func_78793_a(-0.6034F, 0.2211F, -0.0453F);
/* 429 */     this.leftFrontTalons.func_78792_a(this.leftFrontTalon3);
/*     */ 
/*     */     
/* 432 */     this.LeftPointTalon1_r1 = new ModelRenderer((Model)this);
/* 433 */     this.LeftPointTalon1_r1.func_78793_a(-0.5101F, 0.8519F, -2.092F);
/* 434 */     this.leftFrontTalon3.func_78792_a(this.LeftPointTalon1_r1);
/* 435 */     setRotationAngle(this.LeftPointTalon1_r1, 0.235F, 0.2616F, -0.0361F);
/* 436 */     this.LeftPointTalon1_r1.func_78784_a(64, 2).func_228303_a_(-0.4964F, -0.8257F, 0.441F, 1.0F, 1.0F, 2.0F, 0.0F, true);
/*     */     
/* 438 */     this.leftFrontTalon3b = new ModelRenderer((Model)this);
/* 439 */     this.leftFrontTalon3b.func_78793_a(-0.3715F, 0.3409F, -1.6209F);
/* 440 */     this.leftFrontTalon3.func_78792_a(this.leftFrontTalon3b);
/*     */ 
/*     */     
/* 443 */     this.LeftPointTalon2_r1 = new ModelRenderer((Model)this);
/* 444 */     this.LeftPointTalon2_r1.func_78793_a(-0.1386F, 0.511F, -0.4712F);
/* 445 */     this.leftFrontTalon3b.func_78792_a(this.LeftPointTalon2_r1);
/* 446 */     setRotationAngle(this.LeftPointTalon2_r1, 0.5995F, 0.2616F, -0.0351F);
/* 447 */     this.LeftPointTalon2_r1.func_78784_a(64, 2).func_228303_a_(-0.505F, -0.6526F, -1.0902F, 1.0F, 1.0F, 2.0F, -0.1F, true);
/*     */     
/* 449 */     this.leftFrontTalon3c = new ModelRenderer((Model)this);
/* 450 */     this.leftFrontTalon3c.func_78793_a(-0.3697F, 0.9134F, -1.2677F);
/* 451 */     this.leftFrontTalon3b.func_78792_a(this.leftFrontTalon3c);
/*     */ 
/*     */     
/* 454 */     this.LeftPointTalon3_r1 = new ModelRenderer((Model)this);
/* 455 */     this.LeftPointTalon3_r1.func_78793_a(0.2311F, -0.4024F, 0.7965F);
/* 456 */     this.leftFrontTalon3c.func_78792_a(this.LeftPointTalon3_r1);
/* 457 */     setRotationAngle(this.LeftPointTalon3_r1, 0.8264F, 0.2616F, -0.0351F);
/* 458 */     this.LeftPointTalon3_r1.func_78784_a(64, 2).func_228303_a_(-0.5089F, -0.832F, -2.4331F, 1.0F, 1.0F, 2.0F, -0.25F, true);
/*     */     
/* 460 */     this.rightLeg = new ModelRenderer((Model)this);
/* 461 */     this.rightLeg.func_78793_a(-0.5F, 8.6189F, 33.5692F);
/* 462 */     this.body.func_78792_a(this.rightLeg);
/* 463 */     setRotationAngle(this.rightLeg, 0.48F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 466 */     this.UpperRightLeg1_r1 = new ModelRenderer((Model)this);
/* 467 */     this.UpperRightLeg1_r1.func_78793_a(2.0F, -16.6313F, -11.3481F);
/* 468 */     this.rightLeg.func_78792_a(this.UpperRightLeg1_r1);
/* 469 */     setRotationAngle(this.UpperRightLeg1_r1, 0.1309F, 0.0F, 0.0F);
/* 470 */     this.UpperRightLeg1_r1.func_78784_a(49, 5).func_228303_a_(-3.5F, 16.8732F, 7.5134F, 3.0F, 5.0F, 3.0F, -0.001F, false);
/*     */     
/* 472 */     this.rightClaw = new ModelRenderer((Model)this);
/* 473 */     this.rightClaw.func_78793_a(0.0F, 3.6836F, 0.5754F);
/* 474 */     this.rightLeg.func_78792_a(this.rightClaw);
/* 475 */     setRotationAngle(this.rightClaw, 0.5236F, 0.0F, 0.0F);
/* 476 */     this.rightClaw.func_78784_a(62, 7).func_228303_a_(-1.0F, -1.009F, -1.2835F, 2.0F, 4.0F, 2.0F, 0.0F, false);
/*     */     
/* 478 */     this.rightBackTalon = new ModelRenderer((Model)this);
/* 479 */     this.rightBackTalon.func_78793_a(0.0F, 2.678F, 0.6186F);
/* 480 */     this.rightClaw.func_78792_a(this.rightBackTalon);
/* 481 */     setRotationAngle(this.rightBackTalon, 0.0873F, 3.1416F, 0.0F);
/*     */ 
/*     */     
/* 484 */     this.RightBackTalon1_r1 = new ModelRenderer((Model)this);
/* 485 */     this.RightBackTalon1_r1.func_78793_a(0.0F, -19.0516F, 3.3734F);
/* 486 */     this.rightBackTalon.func_78792_a(this.RightBackTalon1_r1);
/* 487 */     setRotationAngle(this.RightBackTalon1_r1, 0.48F, 0.0F, 0.0F);
/* 488 */     this.RightBackTalon1_r1.func_78784_a(63, 0).func_228303_a_(-0.5F, 14.8409F, -13.6042F, 1.0F, 1.0F, 3.0F, -0.1F, false);
/*     */     
/* 490 */     this.rightBackTalon2 = new ModelRenderer((Model)this);
/* 491 */     this.rightBackTalon2.func_78793_a(0.0F, 0.7102F, -1.3897F);
/* 492 */     this.rightBackTalon.func_78792_a(this.rightBackTalon2);
/*     */ 
/*     */     
/* 495 */     this.RightBackTalon2_r1 = new ModelRenderer((Model)this);
/* 496 */     this.RightBackTalon2_r1.func_78793_a(0.0F, -18.882F, 3.5996F);
/* 497 */     this.rightBackTalon2.func_78792_a(this.RightBackTalon2_r1);
/* 498 */     setRotationAngle(this.RightBackTalon2_r1, 1.0908F, 0.0F, 0.0F);
/* 499 */     this.RightBackTalon2_r1.func_78784_a(64, 2).func_228303_a_(-0.5F, 4.9241F, -20.0273F, 1.0F, 1.0F, 2.0F, -0.25F, false);
/*     */     
/* 501 */     this.rightFrontTalons = new ModelRenderer((Model)this);
/* 502 */     this.rightFrontTalons.func_78793_a(0.0F, 2.7036F, -1.172F);
/* 503 */     this.rightClaw.func_78792_a(this.rightFrontTalons);
/*     */ 
/*     */     
/* 506 */     this.rightFrontTalons1 = new ModelRenderer((Model)this);
/* 507 */     this.rightFrontTalons1.func_78793_a(-0.7634F, 0.2945F, -0.0914F);
/* 508 */     this.rightFrontTalons.func_78792_a(this.rightFrontTalons1);
/*     */ 
/*     */     
/* 511 */     this.RightPinkyTalon1_r1 = new ModelRenderer((Model)this);
/* 512 */     this.RightPinkyTalon1_r1.func_78793_a(-0.4356F, 0.7785F, -2.0459F);
/* 513 */     this.rightFrontTalons1.func_78792_a(this.RightPinkyTalon1_r1);
/* 514 */     setRotationAngle(this.RightPinkyTalon1_r1, 0.2537F, 0.2616F, 0.0361F);
/* 515 */     this.RightPinkyTalon1_r1.func_78784_a(64, 2).func_228303_a_(-0.5036F, -0.8257F, 0.441F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 517 */     this.rightFrontTalons1b = new ModelRenderer((Model)this);
/* 518 */     this.rightFrontTalons1b.func_78793_a(-0.2202F, 0.2675F, -1.3286F);
/* 519 */     this.rightFrontTalons1.func_78792_a(this.rightFrontTalons1b);
/*     */ 
/*     */     
/* 522 */     this.RightPinkyTalon2_r1 = new ModelRenderer((Model)this);
/* 523 */     this.RightPinkyTalon2_r1.func_78793_a(-0.2155F, 0.511F, -0.7173F);
/* 524 */     this.rightFrontTalons1b.func_78792_a(this.RightPinkyTalon2_r1);
/* 525 */     setRotationAngle(this.RightPinkyTalon2_r1, 0.6177F, 0.2616F, 0.0351F);
/* 526 */     this.RightPinkyTalon2_r1.func_78784_a(64, 2).func_228303_a_(-0.495F, -0.6526F, -1.0902F, 1.0F, 1.0F, 2.0F, -0.1F, false);
/*     */     
/* 528 */     this.rightFrontTalons1c = new ModelRenderer((Model)this);
/* 529 */     this.rightFrontTalons1c.func_78793_a(-0.4844F, 0.8509F, -1.3787F);
/* 530 */     this.rightFrontTalons1b.func_78792_a(this.rightFrontTalons1c);
/*     */ 
/*     */     
/* 533 */     this.RightPinkyTalon3_r1 = new ModelRenderer((Model)this);
/* 534 */     this.RightPinkyTalon3_r1.func_78793_a(0.269F, -0.3399F, 0.6614F);
/* 535 */     this.rightFrontTalons1c.func_78792_a(this.RightPinkyTalon3_r1);
/* 536 */     setRotationAngle(this.RightPinkyTalon3_r1, 0.8446F, 0.2616F, 0.0351F);
/* 537 */     this.RightPinkyTalon3_r1.func_78784_a(64, 2).func_228303_a_(-0.4911F, -0.832F, -2.4331F, 1.0F, 1.0F, 2.0F, -0.25F, false);
/*     */     
/* 539 */     this.rightFrontTalons2 = new ModelRenderer((Model)this);
/* 540 */     this.rightFrontTalons2.func_78793_a(-0.0391F, 0.2956F, -0.0288F);
/* 541 */     this.rightFrontTalons.func_78792_a(this.rightFrontTalons2);
/*     */ 
/*     */     
/* 544 */     this.RightMiddleTalon1_r1 = new ModelRenderer((Model)this);
/* 545 */     this.RightMiddleTalon1_r1.func_78793_a(-0.0154F, 0.113F, -0.7891F);
/* 546 */     this.rightFrontTalons2.func_78792_a(this.RightMiddleTalon1_r1);
/* 547 */     setRotationAngle(this.RightMiddleTalon1_r1, 0.2443F, 0.0F, 0.0349F);
/* 548 */     this.RightMiddleTalon1_r1.func_78784_a(64, 2).func_228303_a_(-0.4755F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 550 */     this.rightFrontTalons2b = new ModelRenderer((Model)this);
/* 551 */     this.rightFrontTalons2b.func_78793_a(0.0066F, 0.1426F, -1.6448F);
/* 552 */     this.rightFrontTalons2.func_78792_a(this.rightFrontTalons2b);
/*     */ 
/*     */     
/* 555 */     this.RightMiddleTalon2_r1 = new ModelRenderer((Model)this);
/* 556 */     this.RightMiddleTalon2_r1.func_78793_a(0.2184F, 0.4921F, -1.0601F);
/* 557 */     this.rightFrontTalons2b.func_78792_a(this.RightMiddleTalon2_r1);
/* 558 */     setRotationAngle(this.RightMiddleTalon2_r1, 0.6086F, 0.0F, 0.0339F);
/* 559 */     this.RightMiddleTalon2_r1.func_78784_a(64, 2).func_228303_a_(-0.7159F, -0.1875F, -0.6875F, 1.0F, 1.0F, 2.0F, -0.1F, false);
/*     */     
/* 561 */     this.rightFrontTalons2c = new ModelRenderer((Model)this);
/* 562 */     this.rightFrontTalons2c.func_78793_a(-0.0222F, 0.976F, -1.2338F);
/* 563 */     this.rightFrontTalons2b.func_78792_a(this.rightFrontTalons2c);
/*     */ 
/*     */     
/* 566 */     this.RightMiddleTalon3_r1 = new ModelRenderer((Model)this);
/* 567 */     this.RightMiddleTalon3_r1.func_78793_a(0.3608F, -0.223F, -0.7841F);
/* 568 */     this.rightFrontTalons2c.func_78792_a(this.RightMiddleTalon3_r1);
/* 569 */     setRotationAngle(this.RightMiddleTalon3_r1, 0.8355F, 0.0F, 0.0339F);
/* 570 */     this.RightMiddleTalon3_r1.func_78784_a(64, 2).func_228303_a_(-0.8361F, 0.25F, -1.3125F, 1.0F, 1.0F, 2.0F, -0.25F, false);
/*     */     
/* 572 */     this.rightFrontTalons3 = new ModelRenderer((Model)this);
/* 573 */     this.rightFrontTalons3.func_78793_a(0.6034F, 0.2211F, -0.0453F);
/* 574 */     this.rightFrontTalons.func_78792_a(this.rightFrontTalons3);
/*     */ 
/*     */     
/* 577 */     this.RightPointTalon1_r1 = new ModelRenderer((Model)this);
/* 578 */     this.RightPointTalon1_r1.func_78793_a(0.5101F, 0.8519F, -2.092F);
/* 579 */     this.rightFrontTalons3.func_78792_a(this.RightPointTalon1_r1);
/* 580 */     setRotationAngle(this.RightPointTalon1_r1, 0.235F, -0.2616F, 0.0361F);
/* 581 */     this.RightPointTalon1_r1.func_78784_a(64, 2).func_228303_a_(-0.5036F, -0.8257F, 0.441F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 583 */     this.rightFrontTalons3b = new ModelRenderer((Model)this);
/* 584 */     this.rightFrontTalons3b.func_78793_a(0.3715F, 0.3409F, -1.6209F);
/* 585 */     this.rightFrontTalons3.func_78792_a(this.rightFrontTalons3b);
/*     */ 
/*     */     
/* 588 */     this.RightPointTalon2_r1 = new ModelRenderer((Model)this);
/* 589 */     this.RightPointTalon2_r1.func_78793_a(0.1386F, 0.511F, -0.4712F);
/* 590 */     this.rightFrontTalons3b.func_78792_a(this.RightPointTalon2_r1);
/* 591 */     setRotationAngle(this.RightPointTalon2_r1, 0.5995F, -0.2616F, 0.0351F);
/* 592 */     this.RightPointTalon2_r1.func_78784_a(64, 2).func_228303_a_(-0.495F, -0.6526F, -1.0902F, 1.0F, 1.0F, 2.0F, -0.1F, false);
/*     */     
/* 594 */     this.rightFrontTalons3c = new ModelRenderer((Model)this);
/* 595 */     this.rightFrontTalons3c.func_78793_a(0.3697F, 0.9134F, -1.2677F);
/* 596 */     this.rightFrontTalons3b.func_78792_a(this.rightFrontTalons3c);
/*     */ 
/*     */     
/* 599 */     this.RightPointTalon3_r1 = new ModelRenderer((Model)this);
/* 600 */     this.RightPointTalon3_r1.func_78793_a(-0.2311F, -0.4024F, 0.7965F);
/* 601 */     this.rightFrontTalons3c.func_78792_a(this.RightPointTalon3_r1);
/* 602 */     setRotationAngle(this.RightPointTalon3_r1, 0.8264F, -0.2616F, 0.0351F);
/* 603 */     this.RightPointTalon3_r1.func_78784_a(64, 2).func_228303_a_(-0.4911F, -0.832F, -2.4331F, 1.0F, 1.0F, 2.0F, -0.25F, false);
/*     */     
/* 605 */     this.leftWing = new ModelRenderer((Model)this);
/* 606 */     this.leftWing.func_78793_a(6.25F, 6.0F, 10.625F);
/* 607 */     this.body.func_78792_a(this.leftWing);
/*     */ 
/*     */     
/* 610 */     this.LeftWingSection3Feather3_r1 = new ModelRenderer((Model)this);
/* 611 */     this.LeftWingSection3Feather3_r1.func_78793_a(-1.25F, -1.0F, 0.375F);
/* 612 */     this.leftWing.func_78792_a(this.LeftWingSection3Feather3_r1);
/* 613 */     setRotationAngle(this.LeftWingSection3Feather3_r1, 0.0F, 0.2618F, 0.0F);
/* 614 */     this.LeftWingSection3Feather3_r1.func_78784_a(-17, 56).func_228303_a_(-4.5F, -0.2F, 0.0F, 33.0F, 0.0F, 33.0F, 0.0F, true);
/* 615 */     this.LeftWingSection3Feather3_r1.func_78784_a(-17, 56).func_228303_a_(-4.5F, 0.3F, 0.0F, 33.0F, 0.0F, 33.0F, 0.0F, true);
/* 616 */     this.LeftWingSection3Feather3_r1.func_78784_a(-17, 56).func_228303_a_(-4.5F, 0.8625F, 0.0F, 33.0F, 0.0F, 33.0F, 0.0F, true);
/* 617 */     this.LeftWingSection3Feather3_r1.func_78784_a(-1, -1).func_228303_a_(0.5F, -1.0F, -2.0F, 18.0F, 3.0F, 3.0F, -0.75F, true);
/*     */     
/* 619 */     this.leftWing2 = new ModelRenderer((Model)this);
/* 620 */     this.leftWing2.func_78793_a(15.7333F, 0.0F, -4.1984F);
/* 621 */     this.leftWing.func_78792_a(this.leftWing2);
/*     */ 
/*     */     
/* 624 */     this.LeftWingSection3Feather3_r2 = new ModelRenderer((Model)this);
/* 625 */     this.LeftWingSection3Feather3_r2.func_78793_a(-0.25F, -1.0F, -0.25F);
/* 626 */     this.leftWing2.func_78792_a(this.LeftWingSection3Feather3_r2);
/* 627 */     setRotationAngle(this.LeftWingSection3Feather3_r2, 0.0F, 0.6109F, 0.0F);
/* 628 */     this.LeftWingSection3Feather3_r2.func_78784_a(67, 24).func_228303_a_(-1.9116F, -0.15F, 0.3282F, 13.0F, 0.0F, 35.0F, 0.0F, true);
/* 629 */     this.LeftWingSection3Feather3_r2.func_78784_a(67, 24).func_228303_a_(-1.9116F, 0.35F, 0.3282F, 13.0F, 0.0F, 35.0F, 0.0F, true);
/* 630 */     this.LeftWingSection3Feather3_r2.func_78784_a(67, 24).func_228303_a_(-1.9116F, 0.85F, 0.3282F, 13.0F, 0.0F, 35.0F, 0.0F, true);
/*     */     
/* 632 */     this.LeftWingBone2_r1 = new ModelRenderer((Model)this);
/* 633 */     this.LeftWingBone2_r1.func_78793_a(0.0248F, -0.01F, -0.3315F);
/* 634 */     this.leftWing2.func_78792_a(this.LeftWingBone2_r1);
/* 635 */     setRotationAngle(this.LeftWingBone2_r1, 0.0F, 0.6109F, 0.0F);
/* 636 */     this.LeftWingBone2_r1.func_78784_a(-1, -1).func_228303_a_(-0.9116F, -2.0F, -1.6718F, 12.0F, 3.0F, 3.0F, -0.76F, true);
/*     */     
/* 638 */     this.leftWing3 = new ModelRenderer((Model)this);
/* 639 */     this.leftWing3.func_78793_a(8.2063F, -0.05F, -5.7807F);
/* 640 */     this.leftWing2.func_78792_a(this.leftWing3);
/*     */ 
/*     */     
/* 643 */     this.LeftWingSection3Feather3_r3 = new ModelRenderer((Model)this);
/* 644 */     this.LeftWingSection3Feather3_r3.func_78793_a(0.0917F, -0.95F, 0.1451F);
/* 645 */     this.leftWing3.func_78792_a(this.LeftWingSection3Feather3_r3);
/* 646 */     setRotationAngle(this.LeftWingSection3Feather3_r3, 0.0F, 0.4363F, 0.0F);
/* 647 */     this.LeftWingSection3Feather3_r3.func_78784_a(47, 59).func_228303_a_(-3.0027F, -0.1F, -0.1632F, 23.0F, 0.0F, 35.0F, 0.0F, true);
/* 648 */     this.LeftWingSection3Feather3_r3.func_78784_a(47, 59).func_228303_a_(-3.0027F, 0.4F, -0.1632F, 23.0F, 0.0F, 35.0F, 0.0F, true);
/* 649 */     this.LeftWingSection3Feather3_r3.func_78784_a(47, 59).func_228303_a_(-3.0027F, 0.9F, -0.1632F, 23.0F, 0.0F, 35.0F, 0.0F, true);
/*     */     
/* 651 */     this.LeftWingBone3_r1 = new ModelRenderer((Model)this);
/* 652 */     this.LeftWingBone3_r1.func_78793_a(0.569F, 0.04F, -0.0528F);
/* 653 */     this.leftWing3.func_78792_a(this.LeftWingBone3_r1);
/* 654 */     setRotationAngle(this.LeftWingBone3_r1, 0.0F, 0.4363F, 0.0F);
/* 655 */     this.LeftWingBone3_r1.func_78784_a(-1, -1).func_228303_a_(-1.0027F, -2.0F, -2.1632F, 21.0F, 3.0F, 3.0F, -0.76F, true);
/*     */     
/* 657 */     this.leftWing4 = new ModelRenderer((Model)this);
/* 658 */     this.leftWing4.func_78793_a(17.2598F, 0.05F, -7.8054F);
/* 659 */     this.leftWing3.func_78792_a(this.leftWing4);
/* 660 */     this.leftWing4.func_78784_a(-1, -1).func_228303_a_(-0.6089F, -2.01F, -2.4091F, 21.0F, 3.0F, 3.0F, -0.76F, true);
/* 661 */     this.leftWing4.func_78784_a(-38, 90).func_228303_a_(-1.0625F, 0.0F, -0.3125F, 29.0F, 0.0F, 38.0F, 0.0F, true);
/* 662 */     this.leftWing4.func_78784_a(-38, 90).func_228303_a_(-1.0625F, -0.5F, -0.3125F, 29.0F, 0.0F, 38.0F, 0.0F, true);
/* 663 */     this.leftWing4.func_78784_a(-38, 90).func_228303_a_(-1.0625F, -1.0F, -0.3125F, 29.0F, 0.0F, 38.0F, 0.0F, true);
/*     */     
/* 665 */     this.rightWing = new ModelRenderer((Model)this);
/* 666 */     this.rightWing.func_78793_a(-1.1875F, 6.0F, 10.75F);
/* 667 */     this.body.func_78792_a(this.rightWing);
/*     */ 
/*     */     
/* 670 */     this.RightWingSection3Feather3_r1 = new ModelRenderer((Model)this);
/* 671 */     this.RightWingSection3Feather3_r1.func_78793_a(1.1875F, -1.0F, 0.25F);
/* 672 */     this.rightWing.func_78792_a(this.RightWingSection3Feather3_r1);
/* 673 */     setRotationAngle(this.RightWingSection3Feather3_r1, 0.0F, -0.2618F, 0.0F);
/* 674 */     this.RightWingSection3Feather3_r1.func_78784_a(-17, 56).func_228303_a_(-28.5F, -0.2F, 0.0F, 33.0F, 0.0F, 33.0F, 0.0F, false);
/* 675 */     this.RightWingSection3Feather3_r1.func_78784_a(-17, 56).func_228303_a_(-28.5F, 0.3F, 0.0F, 33.0F, 0.0F, 33.0F, 0.0F, false);
/* 676 */     this.RightWingSection3Feather3_r1.func_78784_a(-17, 56).func_228303_a_(-28.5F, 0.8F, 0.0F, 33.0F, 0.0F, 33.0F, 0.0F, false);
/* 677 */     this.RightWingSection3Feather3_r1.func_78784_a(-1, -1).func_228303_a_(-18.5F, -1.0F, -2.0F, 18.0F, 3.0F, 3.0F, -0.75F, false);
/*     */     
/* 679 */     this.rightWing2 = new ModelRenderer((Model)this);
/* 680 */     this.rightWing2.func_78793_a(-15.8583F, 0.0F, -4.3859F);
/* 681 */     this.rightWing.func_78792_a(this.rightWing2);
/*     */ 
/*     */     
/* 684 */     this.RightWingSection3Feather3_r2 = new ModelRenderer((Model)this);
/* 685 */     this.RightWingSection3Feather3_r2.func_78793_a(0.3125F, -1.0F, -0.1875F);
/* 686 */     this.rightWing2.func_78792_a(this.RightWingSection3Feather3_r2);
/* 687 */     setRotationAngle(this.RightWingSection3Feather3_r2, 0.0F, -0.6109F, 0.0F);
/* 688 */     this.RightWingSection3Feather3_r2.func_78784_a(67, 24).func_228303_a_(-11.0884F, -0.15F, 0.3282F, 13.0F, 0.0F, 35.0F, 0.0F, false);
/* 689 */     this.RightWingSection3Feather3_r2.func_78784_a(67, 24).func_228303_a_(-11.0884F, 0.35F, 0.3282F, 13.0F, 0.0F, 35.0F, 0.0F, false);
/* 690 */     this.RightWingSection3Feather3_r2.func_78784_a(67, 24).func_228303_a_(-11.0884F, 0.85F, 0.3282F, 13.0F, 0.0F, 35.0F, 0.0F, false);
/*     */     
/* 692 */     this.RightWingBone2_r1 = new ModelRenderer((Model)this);
/* 693 */     this.RightWingBone2_r1.func_78793_a(0.0377F, -0.01F, -0.269F);
/* 694 */     this.rightWing2.func_78792_a(this.RightWingBone2_r1);
/* 695 */     setRotationAngle(this.RightWingBone2_r1, 0.0F, -0.6109F, 0.0F);
/* 696 */     this.RightWingBone2_r1.func_78784_a(-1, -1).func_228303_a_(-11.0884F, -2.0F, -1.6718F, 12.0F, 3.0F, 3.0F, -0.76F, false);
/*     */     
/* 698 */     this.rightWing3 = new ModelRenderer((Model)this);
/* 699 */     this.rightWing3.func_78793_a(-8.2355F, 0.0F, -5.5731F);
/* 700 */     this.rightWing2.func_78792_a(this.rightWing3);
/*     */ 
/*     */     
/* 703 */     this.RightWingSection3Feather3_r3 = new ModelRenderer((Model)this);
/* 704 */     this.RightWingSection3Feather3_r3.func_78793_a(0.0F, -1.0F, 0.0F);
/* 705 */     this.rightWing3.func_78792_a(this.RightWingSection3Feather3_r3);
/* 706 */     setRotationAngle(this.RightWingSection3Feather3_r3, 0.0F, -0.4363F, 0.0F);
/* 707 */     this.RightWingSection3Feather3_r3.func_78784_a(47, 59).func_228303_a_(-19.9973F, -0.1F, -0.1632F, 23.0F, 0.0F, 35.0F, 0.0F, false);
/* 708 */     this.RightWingSection3Feather3_r3.func_78784_a(47, 59).func_228303_a_(-19.9973F, 0.4F, -0.1632F, 23.0F, 0.0F, 35.0F, 0.0F, false);
/* 709 */     this.RightWingSection3Feather3_r3.func_78784_a(47, 59).func_228303_a_(-19.9973F, 0.9F, -0.1632F, 23.0F, 0.0F, 35.0F, 0.0F, false);
/*     */     
/* 711 */     this.RightWingBone3_r1 = new ModelRenderer((Model)this);
/* 712 */     this.RightWingBone3_r1.func_78793_a(-0.4773F, -0.01F, -0.1979F);
/* 713 */     this.rightWing3.func_78792_a(this.RightWingBone3_r1);
/* 714 */     setRotationAngle(this.RightWingBone3_r1, 0.0F, -0.4363F, 0.0F);
/* 715 */     this.RightWingBone3_r1.func_78784_a(-1, -1).func_228303_a_(-19.9973F, -2.0F, -2.1632F, 21.0F, 3.0F, 3.0F, -0.76F, false);
/*     */     
/* 717 */     this.rightWing4 = new ModelRenderer((Model)this);
/* 718 */     this.rightWing4.func_78793_a(-17.0709F, 0.0F, -8.0039F);
/* 719 */     this.rightWing3.func_78792_a(this.rightWing4);
/* 720 */     this.rightWing4.func_78784_a(-1, -1).func_228303_a_(-20.4884F, -2.01F, -2.3557F, 21.0F, 3.0F, 3.0F, -0.76F, false);
/* 721 */     this.rightWing4.func_78784_a(-38, 90).func_228303_a_(-28.0347F, 0.0F, -0.2592F, 29.0F, 0.0F, 38.0F, 0.0F, false);
/* 722 */     this.rightWing4.func_78784_a(-38, 90).func_228303_a_(-28.0347F, -0.5F, -0.2592F, 29.0F, 0.0F, 38.0F, 0.0F, false);
/* 723 */     this.rightWing4.func_78784_a(-38, 90).func_228303_a_(-28.0347F, -1.0F, -0.2592F, 29.0F, 0.0F, 38.0F, 0.0F, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225597_a_(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 733 */     this.head.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 734 */     this.body.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */   }
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 738 */     modelRenderer.field_78795_f = x;
/* 739 */     modelRenderer.field_78796_g = y;
/* 740 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\morphs\NewPhoenixFlyModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */