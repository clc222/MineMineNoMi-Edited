/*     */ package xyz.pixelatedw.mineminenomi.models.entities.projectiles;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class OnibiModel<T extends Entity> extends EntityModel<T> {
/*     */   private final ModelRenderer headsection1;
/*     */   private final ModelRenderer backhead;
/*     */   private final ModelRenderer leftface;
/*     */   private final ModelRenderer leftface1_r1;
/*     */   private final ModelRenderer lefteyeoverlay;
/*     */   private final ModelRenderer lefteyeoverlay1_r1;
/*     */   private final ModelRenderer rightface;
/*     */   private final ModelRenderer rightface1_r1;
/*     */   private final ModelRenderer righteyeoverlay;
/*     */   private final ModelRenderer righteyeoverlay1_r1;
/*     */   private final ModelRenderer leftfireflare;
/*     */   private final ModelRenderer rightfireflare;
/*     */   private final ModelRenderer mainfireflare;
/*     */   private final ModelRenderer lefthornsection1;
/*     */   private final ModelRenderer lefthornsection2;
/*     */   private final ModelRenderer lefthornsection3;
/*     */   private final ModelRenderer lefthornsection4;
/*     */   private final ModelRenderer lefthornsection5;
/*     */   private final ModelRenderer lefthornsection6;
/*     */   private final ModelRenderer lefthornsection7;
/*     */   private final ModelRenderer lefthornsection9;
/*     */   private final ModelRenderer righthornsection1;
/*     */   private final ModelRenderer righthornsection2;
/*     */   private final ModelRenderer righthornsection3;
/*     */   private final ModelRenderer righthornsection4;
/*     */   private final ModelRenderer righthornsection5;
/*     */   private final ModelRenderer righthornsection6;
/*     */   private final ModelRenderer righthornsection7;
/*     */   private final ModelRenderer righthornsection9;
/*     */   private final ModelRenderer upperjaw;
/*     */   private final ModelRenderer upperteeth;
/*     */   private final ModelRenderer upperlefttooth;
/*     */   private final ModelRenderer upperrighttooth;
/*     */   private final ModelRenderer lowerjaw;
/*     */   private final ModelRenderer lowerteeth;
/*     */   private final ModelRenderer lowerlefttooth;
/*     */   private final ModelRenderer lowerrighttooth;
/*     */   private final ModelRenderer lowerjawdetail;
/*     */   private final ModelRenderer necksection1;
/*     */   private final ModelRenderer bodysection1;
/*     */   private final ModelRenderer leftarm;
/*     */   private final ModelRenderer upperleftarm;
/*     */   private final ModelRenderer lowerleftarm;
/*     */   private final ModelRenderer lefthand;
/*     */   private final ModelRenderer leftthumbsection1;
/*     */   private final ModelRenderer leftthumbsection2;
/*     */   private final ModelRenderer leftthumbsection3;
/*     */   private final ModelRenderer leftthumbsection4;
/*     */   private final ModelRenderer leftpointfingersection1;
/*     */   private final ModelRenderer leftpointfingersection2;
/*     */   private final ModelRenderer leftpointfingersection3;
/*     */   private final ModelRenderer leftpointfingersection4;
/*     */   private final ModelRenderer leftmiddlefingersection1;
/*     */   private final ModelRenderer leftmiddlefingersection2;
/*     */   private final ModelRenderer leftmiddlefingersection3;
/*     */   private final ModelRenderer leftmiddlefingersection4;
/*     */   private final ModelRenderer leftringfingersection1;
/*     */   private final ModelRenderer leftringfingersection2;
/*     */   private final ModelRenderer leftringfingersection3;
/*     */   private final ModelRenderer leftringfingersection4;
/*     */   private final ModelRenderer leftpinkyfingersection1;
/*     */   private final ModelRenderer leftpinkyfingersection2;
/*     */   private final ModelRenderer leftpinkyfingersection3;
/*     */   private final ModelRenderer leftpinkyfingersection4;
/*     */   private final ModelRenderer rightarm;
/*     */   private final ModelRenderer upperrightarm;
/*     */   private final ModelRenderer lowerrightarm;
/*     */   private final ModelRenderer righthand;
/*     */   private final ModelRenderer rightthumbsection1;
/*     */   private final ModelRenderer rightthumbsection;
/*     */   private final ModelRenderer rightthumbsection3;
/*     */   private final ModelRenderer rightthumbsection4;
/*     */   private final ModelRenderer rightpointfingersection1;
/*     */   private final ModelRenderer rightpointfingersection2;
/*     */   private final ModelRenderer rightpointfingersection3;
/*     */   private final ModelRenderer rightpointfingersection4;
/*     */   private final ModelRenderer rightmiddlefingersection1;
/*     */   private final ModelRenderer rightmiddlefingersection2;
/*     */   private final ModelRenderer rightmiddlefingersection3;
/*     */   private final ModelRenderer rightmiddlefingersection4;
/*     */   private final ModelRenderer rightringfingersection1;
/*     */   private final ModelRenderer rightringfingersection2;
/*     */   private final ModelRenderer rightringfingersection3;
/*     */   private final ModelRenderer rightringfingersection4;
/*     */   private final ModelRenderer rightpinkyfingersection1;
/*     */   private final ModelRenderer rightpinkyfingersection2;
/*     */   private final ModelRenderer rightpinkyfingersection3;
/*     */   private final ModelRenderer rightpinkyfingersection4;
/*     */   private final ModelRenderer bodysection2;
/*     */   private final ModelRenderer bodysection3;
/*     */   private final ModelRenderer bodysection4;
/*     */   private final ModelRenderer bodysection5;
/*     */   private final ModelRenderer bodysection6;
/*     */   private final ModelRenderer bodysection7;
/*     */   private final ModelRenderer bodysection8;
/*     */   private final ModelRenderer bodysection9;
/*     */   private final ModelRenderer bodysection10;
/*     */   private final ModelRenderer bodysection11;
/*     */   private final ModelRenderer tail1;
/*     */   
/*     */   public OnibiModel() {
/* 115 */     this.field_78090_t = 512;
/* 116 */     this.field_78089_u = 512;
/*     */     
/* 118 */     this.headsection1 = new ModelRenderer((Model)this);
/* 119 */     this.headsection1.func_78793_a(-0.0854F, 15.1502F, -5.9749F);
/* 120 */     this.headsection1.func_78784_a(68, 162).func_228303_a_(-6.9719F, 1.118F, -2.7839F, 14.0F, 8.0F, 8.0F, 0.0F, false);
/*     */     
/* 122 */     this.backhead = new ModelRenderer((Model)this);
/* 123 */     this.backhead.func_78793_a(-0.0234F, 0.8987F, 1.2605F);
/* 124 */     this.headsection1.func_78792_a(this.backhead);
/* 125 */     setRotationAngle(this.backhead, -0.0873F, 0.0F, 0.0F);
/* 126 */     this.backhead.func_78784_a(68, 179).func_228303_a_(-6.0438F, -4.38F, -1.83F, 12.0F, 13.0F, 10.0F, 0.01F, false);
/*     */     
/* 128 */     this.leftface = new ModelRenderer((Model)this);
/* 129 */     this.leftface.func_78793_a(2.9662F, -1.89F, -2.84F);
/* 130 */     this.backhead.func_78792_a(this.leftface);
/* 131 */     setRotationAngle(this.leftface, 0.0F, -0.2618F, 0.0F);
/*     */ 
/*     */     
/* 134 */     this.leftface1_r1 = new ModelRenderer((Model)this);
/* 135 */     this.leftface1_r1.func_78793_a(0.119F, -1.8043F, -0.9688F);
/* 136 */     this.leftface.func_78792_a(this.leftface1_r1);
/* 137 */     setRotationAngle(this.leftface1_r1, 0.0F, 0.0F, 0.0F);
/* 138 */     this.leftface1_r1.func_78784_a(81, 155).func_228303_a_(-3.2556F, -0.7719F, 0.9079F, 6.0F, 5.0F, 3.0F, 0.0F, false);
/*     */     
/* 140 */     this.lefteyeoverlay = new ModelRenderer((Model)this);
/* 141 */     this.lefteyeoverlay.func_78793_a(-0.1366F, -0.0763F, 1.439F);
/* 142 */     this.leftface.func_78792_a(this.lefteyeoverlay);
/*     */ 
/*     */     
/* 145 */     this.lefteyeoverlay1_r1 = new ModelRenderer((Model)this);
/* 146 */     this.lefteyeoverlay1_r1.func_78793_a(0.2556F, -1.7281F, -2.4079F);
/* 147 */     this.lefteyeoverlay.func_78792_a(this.lefteyeoverlay1_r1);
/* 148 */     setRotationAngle(this.lefteyeoverlay1_r1, 0.0F, 0.0F, 0.0F);
/* 149 */     this.lefteyeoverlay1_r1.func_78784_a(84, 158).func_228303_a_(-3.2556F, -0.7719F, 0.9032F, 6.0F, 5.0F, 0.0F, 0.0F, false);
/*     */     
/* 151 */     this.rightface = new ModelRenderer((Model)this);
/* 152 */     this.rightface.func_78793_a(-3.0537F, -1.89F, -2.84F);
/* 153 */     this.backhead.func_78792_a(this.rightface);
/* 154 */     setRotationAngle(this.rightface, 0.0F, 0.2618F, 0.0F);
/*     */ 
/*     */     
/* 157 */     this.rightface1_r1 = new ModelRenderer((Model)this);
/* 158 */     this.rightface1_r1.func_78793_a(0.6798F, -1.8043F, -0.784F);
/* 159 */     this.rightface.func_78792_a(this.rightface1_r1);
/* 160 */     setRotationAngle(this.rightface1_r1, 0.0F, 0.0F, 0.0F);
/* 161 */     this.rightface1_r1.func_78784_a(81, 155).func_228303_a_(-3.5508F, -0.7719F, 0.721F, 6.0F, 5.0F, 3.0F, 0.0F, false);
/*     */     
/* 163 */     this.righteyeoverlay = new ModelRenderer((Model)this);
/* 164 */     this.righteyeoverlay.func_78793_a(-0.6852F, -0.3823F, -0.2448F);
/* 165 */     this.rightface.func_78792_a(this.righteyeoverlay);
/*     */ 
/*     */     
/* 168 */     this.righteyeoverlay1_r1 = new ModelRenderer((Model)this);
/* 169 */     this.righteyeoverlay1_r1.func_78793_a(1.3649F, -1.422F, -0.5393F);
/* 170 */     this.righteyeoverlay.func_78792_a(this.righteyeoverlay1_r1);
/* 171 */     setRotationAngle(this.righteyeoverlay1_r1, 0.0F, 0.0F, 0.0F);
/* 172 */     this.righteyeoverlay1_r1.func_78784_a(84, 158).func_228303_a_(-3.5508F, -0.7719F, 0.7184F, 6.0F, 5.0F, 0.0F, 0.0F, false);
/*     */     
/* 174 */     this.leftfireflare = new ModelRenderer((Model)this);
/* 175 */     this.leftfireflare.func_78793_a(7.1089F, 4.12F, -1.83F);
/* 176 */     this.backhead.func_78792_a(this.leftfireflare);
/* 177 */     setRotationAngle(this.leftfireflare, 0.0F, 0.4363F, 0.0F);
/* 178 */     this.leftfireflare.func_78784_a(69, 180).func_228303_a_(-0.0525F, -3.5F, 0.0F, 0.0F, 7.0F, 9.0F, 0.0F, true);
/*     */     
/* 180 */     this.rightfireflare = new ModelRenderer((Model)this);
/* 181 */     this.rightfireflare.func_78793_a(-6.9536F, 4.12F, -1.83F);
/* 182 */     this.backhead.func_78792_a(this.rightfireflare);
/* 183 */     setRotationAngle(this.rightfireflare, 0.0F, -0.4363F, 0.0F);
/* 184 */     this.rightfireflare.func_78784_a(69, 180).func_228303_a_(0.0F, -3.5F, 0.0F, 0.0F, 7.0F, 9.0F, 0.0F, false);
/*     */     
/* 186 */     this.mainfireflare = new ModelRenderer((Model)this);
/* 187 */     this.mainfireflare.func_78793_a(0.0537F, -4.2551F, -0.8408F);
/* 188 */     this.backhead.func_78792_a(this.mainfireflare);
/* 189 */     setRotationAngle(this.mainfireflare, 0.7854F, 0.0F, 0.0F);
/* 190 */     this.mainfireflare.func_78784_a(329, 52).func_228303_a_(-6.0975F, -0.7919F, -0.6012F, 12.0F, 0.0F, 9.0F, 0.01F, false);
/*     */     
/* 192 */     this.lefthornsection1 = new ModelRenderer((Model)this);
/* 193 */     this.lefthornsection1.func_78793_a(4.9989F, -2.3687F, 2.9882F);
/* 194 */     this.backhead.func_78792_a(this.lefthornsection1);
/* 195 */     setRotationAngle(this.lefthornsection1, 0.0244F, -0.3935F, -0.4923F);
/* 196 */     this.lefthornsection1.func_78784_a(-2, -2).func_228303_a_(-2.6F, -2.6F, -2.4F, 7.0F, 5.0F, 5.0F, 0.1F, false);
/*     */     
/* 198 */     this.lefthornsection2 = new ModelRenderer((Model)this);
/* 199 */     this.lefthornsection2.func_78793_a(6.7566F, -13.4905F, -1.5387F);
/* 200 */     this.lefthornsection1.func_78792_a(this.lefthornsection2);
/* 201 */     setRotationAngle(this.lefthornsection2, 0.0F, 0.0F, -0.3927F);
/* 202 */     this.lefthornsection2.func_78784_a(-1, -1).func_228303_a_(-8.44F, 9.4573F, -0.4113F, 4.0F, 4.0F, 4.0F, 0.2F, false);
/*     */     
/* 204 */     this.lefthornsection3 = new ModelRenderer((Model)this);
/* 205 */     this.lefthornsection3.func_78793_a(-4.0421F, -1.35F, -0.1067F);
/* 206 */     this.lefthornsection2.func_78792_a(this.lefthornsection3);
/* 207 */     setRotationAngle(this.lefthornsection3, 0.0328F, -0.0616F, -0.4897F);
/* 208 */     this.lefthornsection3.func_78784_a(0, 0).func_228303_a_(-7.0078F, 9.7673F, 0.2002F, 3.0F, 3.0F, 3.0F, 0.3F, false);
/*     */     
/* 210 */     this.lefthornsection4 = new ModelRenderer((Model)this);
/* 211 */     this.lefthornsection4.func_78793_a(-2.3902F, -1.525F, -0.016F);
/* 212 */     this.lefthornsection3.func_78792_a(this.lefthornsection4);
/* 213 */     setRotationAngle(this.lefthornsection4, 0.0217F, -0.0698F, -0.4306F);
/* 214 */     this.lefthornsection4.func_78784_a(-1, -1).func_228303_a_(-7.29F, 9.6073F, 0.4387F, 3.0F, 3.0F, 3.0F, 0.0F, false);
/*     */     
/* 216 */     this.lefthornsection5 = new ModelRenderer((Model)this);
/* 217 */     this.lefthornsection5.func_78793_a(-2.7F, -1.65F, 0.4375F);
/* 218 */     this.lefthornsection4.func_78792_a(this.lefthornsection5);
/* 219 */     setRotationAngle(this.lefthornsection5, 0.0F, 0.0F, -0.3491F);
/* 220 */     this.lefthornsection5.func_78784_a(0, 0).func_228303_a_(-6.14F, 10.5073F, 0.5387F, 2.0F, 2.0F, 2.0F, 0.25F, false);
/*     */     
/* 222 */     this.lefthornsection6 = new ModelRenderer((Model)this);
/* 223 */     this.lefthornsection6.func_78793_a(3.775F, 0.6F, 0.4375F);
/* 224 */     this.lefthornsection5.func_78792_a(this.lefthornsection6);
/* 225 */     setRotationAngle(this.lefthornsection6, 0.0203F, -0.1116F, 0.073F);
/* 226 */     this.lefthornsection6.func_78784_a(0, 0).func_228303_a_(-6.7343F, 10.4825F, 0.6287F, 2.0F, 2.0F, 2.0F, 0.16F, false);
/*     */     
/* 228 */     this.lefthornsection7 = new ModelRenderer((Model)this);
/* 229 */     this.lefthornsection7.func_78793_a(6.125F, 4.0375F, 0.5F);
/* 230 */     this.lefthornsection6.func_78792_a(this.lefthornsection7);
/* 231 */     setRotationAngle(this.lefthornsection7, -0.0218F, -0.0524F, 0.4581F);
/* 232 */     this.lefthornsection7.func_78784_a(0, 0).func_228303_a_(-6.7843F, 10.3825F, 0.7287F, 2.0F, 2.0F, 2.0F, 0.0F, false);
/*     */     
/* 234 */     this.lefthornsection9 = new ModelRenderer((Model)this);
/* 235 */     this.lefthornsection9.func_78793_a(5.0F, 2.7875F, -0.0625F);
/* 236 */     this.lefthornsection7.func_78792_a(this.lefthornsection9);
/* 237 */     setRotationAngle(this.lefthornsection9, 0.0F, 0.0F, 0.3272F);
/* 238 */     this.lefthornsection9.func_78784_a(0, 0).func_228303_a_(-6.8237F, 10.337F, 0.7912F, 4.0F, 2.0F, 2.0F, 0.0F, false);
/*     */     
/* 240 */     this.righthornsection1 = new ModelRenderer((Model)this);
/* 241 */     this.righthornsection1.func_78793_a(-4.7812F, -2.3687F, 2.9882F);
/* 242 */     this.backhead.func_78792_a(this.righthornsection1);
/* 243 */     setRotationAngle(this.righthornsection1, 0.0244F, 0.3935F, 0.4923F);
/* 244 */     this.righthornsection1.func_78784_a(-2, -2).func_228303_a_(-4.4F, -2.6F, -2.4F, 7.0F, 5.0F, 5.0F, 0.1F, true);
/*     */     
/* 246 */     this.righthornsection2 = new ModelRenderer((Model)this);
/* 247 */     this.righthornsection2.func_78793_a(-6.7566F, -13.4905F, -1.5387F);
/* 248 */     this.righthornsection1.func_78792_a(this.righthornsection2);
/* 249 */     setRotationAngle(this.righthornsection2, 0.0F, 0.0F, 0.3927F);
/* 250 */     this.righthornsection2.func_78784_a(-1, -1).func_228303_a_(4.44F, 9.4573F, -0.4113F, 4.0F, 4.0F, 4.0F, 0.2F, true);
/*     */     
/* 252 */     this.righthornsection3 = new ModelRenderer((Model)this);
/* 253 */     this.righthornsection3.func_78793_a(4.0421F, -1.35F, -0.1067F);
/* 254 */     this.righthornsection2.func_78792_a(this.righthornsection3);
/* 255 */     setRotationAngle(this.righthornsection3, 0.0328F, 0.0616F, 0.4897F);
/* 256 */     this.righthornsection3.func_78784_a(0, 0).func_228303_a_(4.0078F, 9.7673F, 0.2002F, 3.0F, 3.0F, 3.0F, 0.3F, true);
/*     */     
/* 258 */     this.righthornsection4 = new ModelRenderer((Model)this);
/* 259 */     this.righthornsection4.func_78793_a(2.3902F, -1.525F, -0.016F);
/* 260 */     this.righthornsection3.func_78792_a(this.righthornsection4);
/* 261 */     setRotationAngle(this.righthornsection4, 0.0217F, 0.0698F, 0.4306F);
/* 262 */     this.righthornsection4.func_78784_a(-1, -1).func_228303_a_(4.29F, 9.6073F, 0.4387F, 3.0F, 3.0F, 3.0F, 0.0F, true);
/*     */     
/* 264 */     this.righthornsection5 = new ModelRenderer((Model)this);
/* 265 */     this.righthornsection5.func_78793_a(2.7F, -1.65F, 0.4375F);
/* 266 */     this.righthornsection4.func_78792_a(this.righthornsection5);
/* 267 */     setRotationAngle(this.righthornsection5, 0.0F, 0.0F, 0.3491F);
/* 268 */     this.righthornsection5.func_78784_a(0, 0).func_228303_a_(4.14F, 10.5073F, 0.5387F, 2.0F, 2.0F, 2.0F, 0.25F, true);
/*     */     
/* 270 */     this.righthornsection6 = new ModelRenderer((Model)this);
/* 271 */     this.righthornsection6.func_78793_a(-3.775F, 0.6F, 0.4375F);
/* 272 */     this.righthornsection5.func_78792_a(this.righthornsection6);
/* 273 */     setRotationAngle(this.righthornsection6, 0.0203F, 0.1116F, -0.073F);
/* 274 */     this.righthornsection6.func_78784_a(0, 0).func_228303_a_(4.7343F, 10.4825F, 0.6287F, 2.0F, 2.0F, 2.0F, 0.16F, true);
/*     */     
/* 276 */     this.righthornsection7 = new ModelRenderer((Model)this);
/* 277 */     this.righthornsection7.func_78793_a(-6.125F, 4.0375F, 0.5F);
/* 278 */     this.righthornsection6.func_78792_a(this.righthornsection7);
/* 279 */     setRotationAngle(this.righthornsection7, -0.0218F, 0.0524F, -0.4581F);
/* 280 */     this.righthornsection7.func_78784_a(0, 0).func_228303_a_(4.7843F, 10.3825F, 0.7287F, 2.0F, 2.0F, 2.0F, 0.0F, true);
/*     */     
/* 282 */     this.righthornsection9 = new ModelRenderer((Model)this);
/* 283 */     this.righthornsection9.func_78793_a(-5.0F, 2.7875F, -0.0625F);
/* 284 */     this.righthornsection7.func_78792_a(this.righthornsection9);
/* 285 */     setRotationAngle(this.righthornsection9, 0.0F, 0.0F, -0.3272F);
/* 286 */     this.righthornsection9.func_78784_a(0, 0).func_228303_a_(2.8237F, 10.337F, 0.7912F, 4.0F, 2.0F, 2.0F, 0.0F, true);
/*     */     
/* 288 */     this.upperjaw = new ModelRenderer((Model)this);
/* 289 */     this.upperjaw.func_78793_a(-0.0588F, 4.6298F, -1.8479F);
/* 290 */     this.headsection1.func_78792_a(this.upperjaw);
/* 291 */     setRotationAngle(this.upperjaw, -0.2182F, 0.0F, 0.0F);
/* 292 */     this.upperjaw.func_78784_a(5, 212).func_228303_a_(-3.4506F, -3.2757F, -9.7032F, 7.0F, 1.0F, 10.0F, 0.0F, false);
/* 293 */     this.upperjaw.func_78784_a(4, 93).func_228303_a_(-5.4781F, -2.6257F, -8.9232F, 11.0F, 5.0F, 9.0F, 0.0F, false);
/* 294 */     this.upperjaw.func_78784_a(39, 216).func_228303_a_(-4.5106F, -2.8431F, -9.7957F, 9.0F, 3.0F, 3.0F, 0.05F, false);
/* 295 */     this.upperjaw.func_78784_a(38, 208).func_228303_a_(-2.5106F, -3.7757F, -10.2227F, 5.0F, 3.0F, 4.0F, -0.1F, false);
/*     */     
/* 297 */     this.upperteeth = new ModelRenderer((Model)this);
/* 298 */     this.upperteeth.func_78793_a(3.1624F, -39.7937F, 193.4283F);
/* 299 */     this.upperjaw.func_78792_a(this.upperteeth);
/* 300 */     this.upperteeth.func_78784_a(2, 117).func_228303_a_(-8.173F, 41.0731F, -201.0089F, 10.0F, 3.0F, 0.0F, 0.0F, false);
/* 301 */     this.upperteeth.func_78784_a(4, 117).func_228303_a_(-8.173F, 41.0731F, -201.0089F, 0.0F, 3.0F, 8.0F, 0.0F, false);
/* 302 */     this.upperteeth.func_78784_a(4, 117).func_228303_a_(1.827F, 41.0731F, -201.0089F, 0.0F, 3.0F, 8.0F, 0.0F, false);
/*     */     
/* 304 */     this.upperlefttooth = new ModelRenderer((Model)this);
/* 305 */     this.upperlefttooth.func_78793_a(1.6762F, 41.3716F, -198.0714F);
/* 306 */     this.upperteeth.func_78792_a(this.upperlefttooth);
/* 307 */     setRotationAngle(this.upperlefttooth, 0.0F, 0.0F, -0.3491F);
/* 308 */     this.upperlefttooth.func_78784_a(10, 123).func_228303_a_(0.0121F, -0.0118F, -1.0F, 0.0F, 4.0F, 2.0F, 0.0F, false);
/*     */     
/* 310 */     this.upperrighttooth = new ModelRenderer((Model)this);
/* 311 */     this.upperrighttooth.func_78793_a(-8.0251F, 41.3716F, -198.0714F);
/* 312 */     this.upperteeth.func_78792_a(this.upperrighttooth);
/* 313 */     setRotationAngle(this.upperrighttooth, 0.0F, 0.0F, 0.3491F);
/* 314 */     this.upperrighttooth.func_78784_a(10, 123).func_228303_a_(-0.0121F, -0.0118F, -1.0F, 0.0F, 4.0F, 2.0F, 0.0F, true);
/*     */     
/* 316 */     this.lowerjaw = new ModelRenderer((Model)this);
/* 317 */     this.lowerjaw.func_78793_a(-0.0697F, 7.2992F, -2.0908F);
/* 318 */     this.headsection1.func_78792_a(this.lowerjaw);
/* 319 */     this.lowerjaw.func_78784_a(7, 78).func_228303_a_(-4.5F, -2.0575F, -8.0F, 9.0F, 3.0F, 8.0F, 0.6F, false);
/*     */     
/* 321 */     this.lowerteeth = new ModelRenderer((Model)this);
/* 322 */     this.lowerteeth.func_78793_a(3.1733F, 0.7863F, 197.7299F);
/* 323 */     this.lowerjaw.func_78792_a(this.lowerteeth);
/* 324 */     this.lowerteeth.func_78784_a(2, 121).func_228303_a_(-7.1733F, -5.4338F, -205.1049F, 8.0F, 3.0F, 0.0F, 0.0F, false);
/* 325 */     this.lowerteeth.func_78784_a(4, 121).func_228303_a_(0.8267F, -5.4338F, -205.1049F, 0.0F, 3.0F, 8.0F, 0.0F, false);
/* 326 */     this.lowerteeth.func_78784_a(4, 121).func_228303_a_(-7.1733F, -5.4338F, -205.1049F, 0.0F, 3.0F, 8.0F, 0.0F, false);
/*     */     
/* 328 */     this.lowerlefttooth = new ModelRenderer((Model)this);
/* 329 */     this.lowerlefttooth.func_78793_a(0.7642F, -3.4338F, -202.7299F);
/* 330 */     this.lowerteeth.func_78792_a(this.lowerlefttooth);
/* 331 */     setRotationAngle(this.lowerlefttooth, 0.0F, 0.0F, 0.3491F);
/* 332 */     this.lowerlefttooth.func_78784_a(10, 127).func_228303_a_(0.1875F, -3.5F, -1.0F, 0.0F, 4.0F, 2.0F, 0.0F, false);
/*     */     
/* 334 */     this.lowerrighttooth = new ModelRenderer((Model)this);
/* 335 */     this.lowerrighttooth.func_78793_a(-7.1131F, -3.4338F, -202.7299F);
/* 336 */     this.lowerteeth.func_78792_a(this.lowerrighttooth);
/* 337 */     setRotationAngle(this.lowerrighttooth, 0.0F, 0.0F, -0.3491F);
/* 338 */     this.lowerrighttooth.func_78784_a(10, 127).func_228303_a_(-0.1875F, -3.5F, -1.0F, 0.0F, 4.0F, 2.0F, 0.0F, true);
/*     */     
/* 340 */     this.lowerjawdetail = new ModelRenderer((Model)this);
/* 341 */     this.lowerjawdetail.func_78793_a(-0.0303F, 1.3089F, -8.3673F);
/* 342 */     this.lowerjaw.func_78792_a(this.lowerjawdetail);
/* 343 */     setRotationAngle(this.lowerjawdetail, -0.7418F, 0.0F, 0.0F);
/* 344 */     this.lowerjawdetail.func_78784_a(21, 117).func_228303_a_(-5.5F, 0.0349F, -4.0E-4F, 11.0F, 4.0F, 0.0F, 0.0F, false);
/*     */     
/* 346 */     this.necksection1 = new ModelRenderer((Model)this);
/* 347 */     this.necksection1.func_78793_a(-0.1666F, 19.3429F, 0.901F);
/* 348 */     this.necksection1.func_78784_a(69, 203).func_228303_a_(-5.5F, -4.5F, -3.4375F, 11.0F, 9.0F, 11.0F, 0.0F, false);
/*     */     
/* 350 */     this.bodysection1 = new ModelRenderer((Model)this);
/* 351 */     this.bodysection1.func_78793_a(0.1558F, 0.4311F, 4.57F);
/* 352 */     this.necksection1.func_78792_a(this.bodysection1);
/* 353 */     this.bodysection1.func_78784_a(338, 42).func_228303_a_(-0.0568F, -18.7446F, -1.795F, 0.0F, 16.0F, 20.0F, 0.0F, false);
/* 354 */     this.bodysection1.func_78784_a(152, 388).func_228303_a_(-6.4432F, -5.1304F, 0.2325F, 13.0F, 10.0F, 18.0F, 0.0F, false);
/*     */     
/* 356 */     this.leftarm = new ModelRenderer((Model)this);
/* 357 */     this.leftarm.func_78793_a(7.1603F, 2.2059F, 9.2223F);
/* 358 */     this.bodysection1.func_78792_a(this.leftarm);
/* 359 */     setRotationAngle(this.leftarm, 0.0F, 0.3491F, 0.1745F);
/* 360 */     this.leftarm.func_78784_a(2, 26).func_228303_a_(-2.5F, -2.5F, -3.5F, 5.0F, 5.0F, 7.0F, 0.6F, false);
/*     */     
/* 362 */     this.upperleftarm = new ModelRenderer((Model)this);
/* 363 */     this.upperleftarm.func_78793_a(2.45F, 0.1245F, -0.0354F);
/* 364 */     this.leftarm.func_78792_a(this.upperleftarm);
/* 365 */     setRotationAngle(this.upperleftarm, 0.0F, 0.0F, 0.3927F);
/* 366 */     this.upperleftarm.func_78784_a(112, 26).func_228303_a_(-0.9375F, -2.5F, -3.5F, 26.0F, 5.0F, 7.0F, 0.02F, false);
/*     */     
/* 368 */     this.lowerleftarm = new ModelRenderer((Model)this);
/* 369 */     this.lowerleftarm.func_78793_a(23.4475F, 0.4134F, 0.1053F);
/* 370 */     this.upperleftarm.func_78792_a(this.lowerleftarm);
/* 371 */     setRotationAngle(this.lowerleftarm, 0.0F, 0.9599F, 0.0F);
/* 372 */     this.lowerleftarm.func_78784_a(123, 1).func_228303_a_(-2.3931F, -2.4588F, -3.63F, 20.0F, 4.0F, 7.0F, 0.02F, false);
/*     */     
/* 374 */     this.lefthand = new ModelRenderer((Model)this);
/* 375 */     this.lefthand.func_78793_a(16.4105F, -1.3282F, 0.0265F);
/* 376 */     this.lowerleftarm.func_78792_a(this.lefthand);
/* 377 */     this.lefthand.func_78784_a(32, 10).func_228303_a_(-0.0625F, -3.0625F, -4.0F, 5.0F, 6.0F, 8.0F, 0.02F, false);
/*     */     
/* 379 */     this.leftthumbsection1 = new ModelRenderer((Model)this);
/* 380 */     this.leftthumbsection1.func_78793_a(3.5447F, -1.0808F, -3.3107F);
/* 381 */     this.lefthand.func_78792_a(this.leftthumbsection1);
/* 382 */     setRotationAngle(this.leftthumbsection1, 0.0F, 0.7854F, 0.0F);
/* 383 */     this.leftthumbsection1.func_78784_a(99, 34).func_228303_a_(-1.5F, -1.5F, -1.625F, 3.0F, 3.0F, 2.0F, 0.3F, false);
/*     */     
/* 385 */     this.leftthumbsection2 = new ModelRenderer((Model)this);
/* 386 */     this.leftthumbsection2.func_78793_a(1.5815F, 0.0865F, -0.4592F);
/* 387 */     this.leftthumbsection1.func_78792_a(this.leftthumbsection2);
/* 388 */     setRotationAngle(this.leftthumbsection2, 0.0F, -0.5672F, 0.0F);
/* 389 */     this.leftthumbsection2.func_78784_a(99, 29).func_228303_a_(-0.5F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, 0.2F, false);
/*     */     
/* 391 */     this.leftthumbsection3 = new ModelRenderer((Model)this);
/* 392 */     this.leftthumbsection3.func_78793_a(2.3746F, -0.0149F, -0.0947F);
/* 393 */     this.leftthumbsection2.func_78792_a(this.leftthumbsection3);
/* 394 */     setRotationAngle(this.leftthumbsection3, 0.0F, -0.7854F, 0.0F);
/* 395 */     this.leftthumbsection3.func_78784_a(100, 25).func_228303_a_(-0.5F, -1.0F, -0.5F, 3.0F, 2.0F, 1.0F, 0.1F, false);
/*     */     
/* 397 */     this.leftthumbsection4 = new ModelRenderer((Model)this);
/* 398 */     this.leftthumbsection4.func_78793_a(2.3437F, 0.0F, -0.0163F);
/* 399 */     this.leftthumbsection3.func_78792_a(this.leftthumbsection4);
/* 400 */     this.leftthumbsection4.func_78784_a(101, 21).func_228303_a_(-0.25F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 402 */     this.leftpointfingersection1 = new ModelRenderer((Model)this);
/* 403 */     this.leftpointfingersection1.func_78793_a(2.7115F, -3.288F, -2.8486F);
/* 404 */     this.lefthand.func_78792_a(this.leftpointfingersection1);
/* 405 */     this.leftpointfingersection1.func_78784_a(86, 34).func_228303_a_(-2.0F, -2.75F, -1.0F, 4.0F, 3.0F, 2.0F, 0.02F, false);
/*     */     
/* 407 */     this.leftpointfingersection2 = new ModelRenderer((Model)this);
/* 408 */     this.leftpointfingersection2.func_78793_a(0.8083F, -2.7732F, 0.0013F);
/* 409 */     this.leftpointfingersection1.func_78792_a(this.leftpointfingersection2);
/* 410 */     setRotationAngle(this.leftpointfingersection2, 0.0F, 0.0F, 0.1745F);
/* 411 */     this.leftpointfingersection2.func_78784_a(88, 27).func_228303_a_(-1.0F, -2.25F, -1.0F, 2.0F, 4.0F, 2.0F, 0.01F, false);
/*     */     
/* 413 */     this.leftpointfingersection3 = new ModelRenderer((Model)this);
/* 414 */     this.leftpointfingersection3.func_78793_a(-0.0074F, -2.4571F, 0.0F);
/* 415 */     this.leftpointfingersection2.func_78792_a(this.leftpointfingersection3);
/* 416 */     setRotationAngle(this.leftpointfingersection3, 0.0F, 0.0F, 1.4835F);
/* 417 */     this.leftpointfingersection3.func_78784_a(88, 20).func_228303_a_(-1.0F, -3.5F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
/*     */     
/* 419 */     this.leftpointfingersection4 = new ModelRenderer((Model)this);
/* 420 */     this.leftpointfingersection4.func_78793_a(-0.3956F, -3.5959F, -0.0026F);
/* 421 */     this.leftpointfingersection3.func_78792_a(this.leftpointfingersection4);
/* 422 */     this.leftpointfingersection4.func_78784_a(90, 15).func_228303_a_(-0.5F, -2.25F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);
/*     */     
/* 424 */     this.leftmiddlefingersection1 = new ModelRenderer((Model)this);
/* 425 */     this.leftmiddlefingersection1.func_78793_a(2.7115F, -3.163F, -0.6611F);
/* 426 */     this.lefthand.func_78792_a(this.leftmiddlefingersection1);
/* 427 */     setRotationAngle(this.leftmiddlefingersection1, -0.0873F, 0.0F, 0.0F);
/* 428 */     this.leftmiddlefingersection1.func_78784_a(86, 34).func_228303_a_(-2.0F, -2.75F, -1.0F, 4.0F, 3.0F, 2.0F, 0.02F, false);
/*     */     
/* 430 */     this.leftmiddlefingersection2 = new ModelRenderer((Model)this);
/* 431 */     this.leftmiddlefingersection2.func_78793_a(0.8083F, -2.7732F, 0.0013F);
/* 432 */     this.leftmiddlefingersection1.func_78792_a(this.leftmiddlefingersection2);
/* 433 */     setRotationAngle(this.leftmiddlefingersection2, 0.0F, 0.0F, 0.2618F);
/* 434 */     this.leftmiddlefingersection2.func_78784_a(88, 27).func_228303_a_(-1.0F, -2.25F, -1.0F, 2.0F, 4.0F, 2.0F, 0.01F, false);
/*     */     
/* 436 */     this.leftmiddlefingersection3 = new ModelRenderer((Model)this);
/* 437 */     this.leftmiddlefingersection3.func_78793_a(-0.0074F, -2.4571F, 0.0F);
/* 438 */     this.leftmiddlefingersection2.func_78792_a(this.leftmiddlefingersection3);
/* 439 */     setRotationAngle(this.leftmiddlefingersection3, 0.0F, 0.0F, 1.5708F);
/* 440 */     this.leftmiddlefingersection3.func_78784_a(88, 20).func_228303_a_(-1.0F, -3.5F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
/*     */     
/* 442 */     this.leftmiddlefingersection4 = new ModelRenderer((Model)this);
/* 443 */     this.leftmiddlefingersection4.func_78793_a(-0.3956F, -3.5959F, -0.0026F);
/* 444 */     this.leftmiddlefingersection3.func_78792_a(this.leftmiddlefingersection4);
/* 445 */     this.leftmiddlefingersection4.func_78784_a(90, 15).func_228303_a_(-0.5F, -2.25F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);
/*     */     
/* 447 */     this.leftringfingersection1 = new ModelRenderer((Model)this);
/* 448 */     this.leftringfingersection1.func_78793_a(2.7115F, -2.9755F, 1.5264F);
/* 449 */     this.lefthand.func_78792_a(this.leftringfingersection1);
/* 450 */     setRotationAngle(this.leftringfingersection1, -0.1745F, 0.0F, 0.0F);
/* 451 */     this.leftringfingersection1.func_78784_a(86, 34).func_228303_a_(-2.0F, -2.75F, -1.0F, 4.0F, 3.0F, 2.0F, 0.02F, false);
/*     */     
/* 453 */     this.leftringfingersection2 = new ModelRenderer((Model)this);
/* 454 */     this.leftringfingersection2.func_78793_a(0.8083F, -2.7732F, 0.0013F);
/* 455 */     this.leftringfingersection1.func_78792_a(this.leftringfingersection2);
/* 456 */     setRotationAngle(this.leftringfingersection2, 0.0F, 0.0F, 0.3491F);
/* 457 */     this.leftringfingersection2.func_78784_a(88, 27).func_228303_a_(-1.0F, -2.25F, -1.0F, 2.0F, 4.0F, 2.0F, 0.01F, false);
/*     */     
/* 459 */     this.leftringfingersection3 = new ModelRenderer((Model)this);
/* 460 */     this.leftringfingersection3.func_78793_a(-0.0074F, -2.4571F, 0.0F);
/* 461 */     this.leftringfingersection2.func_78792_a(this.leftringfingersection3);
/* 462 */     setRotationAngle(this.leftringfingersection3, 0.0F, 0.0F, 1.7453F);
/* 463 */     this.leftringfingersection3.func_78784_a(88, 20).func_228303_a_(-1.0F, -3.5F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
/*     */     
/* 465 */     this.leftringfingersection4 = new ModelRenderer((Model)this);
/* 466 */     this.leftringfingersection4.func_78793_a(-0.3956F, -3.5959F, -0.0026F);
/* 467 */     this.leftringfingersection3.func_78792_a(this.leftringfingersection4);
/* 468 */     this.leftringfingersection4.func_78784_a(90, 15).func_228303_a_(-0.5F, -2.25F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);
/*     */     
/* 470 */     this.leftpinkyfingersection1 = new ModelRenderer((Model)this);
/* 471 */     this.leftpinkyfingersection1.func_78793_a(2.7115F, -2.3505F, 3.3389F);
/* 472 */     this.lefthand.func_78792_a(this.leftpinkyfingersection1);
/* 473 */     setRotationAngle(this.leftpinkyfingersection1, -0.4276F, 0.0F, 0.0F);
/* 474 */     this.leftpinkyfingersection1.func_78784_a(86, 34).func_228303_a_(-2.0F, -2.75F, -1.0F, 4.0F, 4.0F, 2.0F, 0.02F, false);
/*     */     
/* 476 */     this.leftpinkyfingersection2 = new ModelRenderer((Model)this);
/* 477 */     this.leftpinkyfingersection2.func_78793_a(0.8083F, -2.7732F, 0.0013F);
/* 478 */     this.leftpinkyfingersection1.func_78792_a(this.leftpinkyfingersection2);
/* 479 */     setRotationAngle(this.leftpinkyfingersection2, 0.0F, 0.0F, 0.3491F);
/* 480 */     this.leftpinkyfingersection2.func_78784_a(88, 27).func_228303_a_(-1.0F, -2.25F, -1.0F, 2.0F, 4.0F, 2.0F, 0.01F, false);
/*     */     
/* 482 */     this.leftpinkyfingersection3 = new ModelRenderer((Model)this);
/* 483 */     this.leftpinkyfingersection3.func_78793_a(-0.0074F, -2.4571F, 0.0F);
/* 484 */     this.leftpinkyfingersection2.func_78792_a(this.leftpinkyfingersection3);
/* 485 */     setRotationAngle(this.leftpinkyfingersection3, 0.0F, 0.0F, 1.7453F);
/* 486 */     this.leftpinkyfingersection3.func_78784_a(88, 20).func_228303_a_(-1.0F, -3.5F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
/*     */     
/* 488 */     this.leftpinkyfingersection4 = new ModelRenderer((Model)this);
/* 489 */     this.leftpinkyfingersection4.func_78793_a(-0.3956F, -3.5959F, -0.0026F);
/* 490 */     this.leftpinkyfingersection3.func_78792_a(this.leftpinkyfingersection4);
/* 491 */     this.leftpinkyfingersection4.func_78784_a(90, 15).func_228303_a_(-0.5F, -2.25F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);
/*     */     
/* 493 */     this.rightarm = new ModelRenderer((Model)this);
/* 494 */     this.rightarm.func_78793_a(-7.1387F, 2.2059F, 9.2223F);
/* 495 */     this.bodysection1.func_78792_a(this.rightarm);
/* 496 */     setRotationAngle(this.rightarm, 0.0F, -0.3491F, -0.1745F);
/* 497 */     this.rightarm.func_78784_a(2, 26).func_228303_a_(-2.5F, -2.5F, -3.5F, 5.0F, 5.0F, 7.0F, 0.6F, true);
/*     */     
/* 499 */     this.upperrightarm = new ModelRenderer((Model)this);
/* 500 */     this.upperrightarm.func_78793_a(-2.45F, 0.1245F, -0.0354F);
/* 501 */     this.rightarm.func_78792_a(this.upperrightarm);
/* 502 */     setRotationAngle(this.upperrightarm, 0.0F, 0.0F, -0.3927F);
/* 503 */     this.upperrightarm.func_78784_a(112, 26).func_228303_a_(-25.0625F, -2.5F, -3.5F, 26.0F, 5.0F, 7.0F, 0.02F, true);
/*     */     
/* 505 */     this.lowerrightarm = new ModelRenderer((Model)this);
/* 506 */     this.lowerrightarm.func_78793_a(-23.4475F, 0.4134F, 0.1053F);
/* 507 */     this.upperrightarm.func_78792_a(this.lowerrightarm);
/* 508 */     setRotationAngle(this.lowerrightarm, 0.0F, -0.9599F, 0.0F);
/* 509 */     this.lowerrightarm.func_78784_a(123, 1).func_228303_a_(-17.6069F, -2.4588F, -3.63F, 20.0F, 4.0F, 7.0F, 0.02F, true);
/*     */     
/* 511 */     this.righthand = new ModelRenderer((Model)this);
/* 512 */     this.righthand.func_78793_a(-16.4105F, -1.3282F, 0.0265F);
/* 513 */     this.lowerrightarm.func_78792_a(this.righthand);
/* 514 */     this.righthand.func_78784_a(32, 10).func_228303_a_(-4.9375F, -3.0625F, -4.0F, 5.0F, 6.0F, 8.0F, 0.02F, true);
/*     */     
/* 516 */     this.rightthumbsection1 = new ModelRenderer((Model)this);
/* 517 */     this.rightthumbsection1.func_78793_a(-3.5447F, -1.0808F, -3.3107F);
/* 518 */     this.righthand.func_78792_a(this.rightthumbsection1);
/* 519 */     setRotationAngle(this.rightthumbsection1, 0.0F, -0.7854F, 0.0F);
/* 520 */     this.rightthumbsection1.func_78784_a(99, 34).func_228303_a_(-1.5F, -1.5F, -1.625F, 3.0F, 3.0F, 2.0F, 0.3F, true);
/*     */     
/* 522 */     this.rightthumbsection = new ModelRenderer((Model)this);
/* 523 */     this.rightthumbsection.func_78793_a(-1.5815F, 0.0865F, -0.4592F);
/* 524 */     this.rightthumbsection1.func_78792_a(this.rightthumbsection);
/* 525 */     setRotationAngle(this.rightthumbsection, 0.0F, 0.5672F, 0.0F);
/* 526 */     this.rightthumbsection.func_78784_a(99, 29).func_228303_a_(-2.5F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, 0.2F, true);
/*     */     
/* 528 */     this.rightthumbsection3 = new ModelRenderer((Model)this);
/* 529 */     this.rightthumbsection3.func_78793_a(-2.3746F, -0.0149F, -0.0947F);
/* 530 */     this.rightthumbsection.func_78792_a(this.rightthumbsection3);
/* 531 */     setRotationAngle(this.rightthumbsection3, 0.0F, 0.7854F, 0.0F);
/* 532 */     this.rightthumbsection3.func_78784_a(100, 25).func_228303_a_(-2.5F, -1.0F, -0.5F, 3.0F, 2.0F, 1.0F, 0.1F, true);
/*     */     
/* 534 */     this.rightthumbsection4 = new ModelRenderer((Model)this);
/* 535 */     this.rightthumbsection4.func_78793_a(-2.3437F, 0.0F, -0.0163F);
/* 536 */     this.rightthumbsection3.func_78792_a(this.rightthumbsection4);
/* 537 */     this.rightthumbsection4.func_78784_a(101, 21).func_228303_a_(-1.75F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, true);
/*     */     
/* 539 */     this.rightpointfingersection1 = new ModelRenderer((Model)this);
/* 540 */     this.rightpointfingersection1.func_78793_a(-2.7115F, -3.288F, -2.8486F);
/* 541 */     this.righthand.func_78792_a(this.rightpointfingersection1);
/* 542 */     this.rightpointfingersection1.func_78784_a(86, 34).func_228303_a_(-2.0F, -2.75F, -1.0F, 4.0F, 3.0F, 2.0F, 0.02F, true);
/*     */     
/* 544 */     this.rightpointfingersection2 = new ModelRenderer((Model)this);
/* 545 */     this.rightpointfingersection2.func_78793_a(-0.8083F, -2.7732F, 0.0013F);
/* 546 */     this.rightpointfingersection1.func_78792_a(this.rightpointfingersection2);
/* 547 */     setRotationAngle(this.rightpointfingersection2, 0.0F, 0.0F, -0.1745F);
/* 548 */     this.rightpointfingersection2.func_78784_a(88, 27).func_228303_a_(-1.0F, -2.25F, -1.0F, 2.0F, 4.0F, 2.0F, 0.01F, true);
/*     */     
/* 550 */     this.rightpointfingersection3 = new ModelRenderer((Model)this);
/* 551 */     this.rightpointfingersection3.func_78793_a(0.0074F, -2.4571F, 0.0F);
/* 552 */     this.rightpointfingersection2.func_78792_a(this.rightpointfingersection3);
/* 553 */     setRotationAngle(this.rightpointfingersection3, 0.0F, 0.0F, -1.4835F);
/* 554 */     this.rightpointfingersection3.func_78784_a(88, 20).func_228303_a_(-1.0F, -3.5F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, true);
/*     */     
/* 556 */     this.rightpointfingersection4 = new ModelRenderer((Model)this);
/* 557 */     this.rightpointfingersection4.func_78793_a(0.3956F, -3.5959F, -0.0026F);
/* 558 */     this.rightpointfingersection3.func_78792_a(this.rightpointfingersection4);
/* 559 */     this.rightpointfingersection4.func_78784_a(90, 15).func_228303_a_(-0.5F, -2.25F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, true);
/*     */     
/* 561 */     this.rightmiddlefingersection1 = new ModelRenderer((Model)this);
/* 562 */     this.rightmiddlefingersection1.func_78793_a(-2.7115F, -3.163F, -0.6611F);
/* 563 */     this.righthand.func_78792_a(this.rightmiddlefingersection1);
/* 564 */     setRotationAngle(this.rightmiddlefingersection1, -0.0873F, 0.0F, 0.0F);
/* 565 */     this.rightmiddlefingersection1.func_78784_a(86, 34).func_228303_a_(-2.0F, -2.75F, -1.0F, 4.0F, 3.0F, 2.0F, 0.02F, true);
/*     */     
/* 567 */     this.rightmiddlefingersection2 = new ModelRenderer((Model)this);
/* 568 */     this.rightmiddlefingersection2.func_78793_a(-0.8083F, -2.7732F, 0.0013F);
/* 569 */     this.rightmiddlefingersection1.func_78792_a(this.rightmiddlefingersection2);
/* 570 */     setRotationAngle(this.rightmiddlefingersection2, 0.0F, 0.0F, -0.2618F);
/* 571 */     this.rightmiddlefingersection2.func_78784_a(88, 27).func_228303_a_(-1.0F, -2.25F, -1.0F, 2.0F, 4.0F, 2.0F, 0.01F, true);
/*     */     
/* 573 */     this.rightmiddlefingersection3 = new ModelRenderer((Model)this);
/* 574 */     this.rightmiddlefingersection3.func_78793_a(0.0074F, -2.4571F, 0.0F);
/* 575 */     this.rightmiddlefingersection2.func_78792_a(this.rightmiddlefingersection3);
/* 576 */     setRotationAngle(this.rightmiddlefingersection3, 0.0F, 0.0F, -1.5708F);
/* 577 */     this.rightmiddlefingersection3.func_78784_a(88, 20).func_228303_a_(-1.0F, -3.5F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, true);
/*     */     
/* 579 */     this.rightmiddlefingersection4 = new ModelRenderer((Model)this);
/* 580 */     this.rightmiddlefingersection4.func_78793_a(0.3956F, -3.5959F, -0.0026F);
/* 581 */     this.rightmiddlefingersection3.func_78792_a(this.rightmiddlefingersection4);
/* 582 */     this.rightmiddlefingersection4.func_78784_a(90, 15).func_228303_a_(-0.5F, -2.25F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, true);
/*     */     
/* 584 */     this.rightringfingersection1 = new ModelRenderer((Model)this);
/* 585 */     this.rightringfingersection1.func_78793_a(-2.7115F, -2.9755F, 1.5264F);
/* 586 */     this.righthand.func_78792_a(this.rightringfingersection1);
/* 587 */     setRotationAngle(this.rightringfingersection1, -0.1745F, 0.0F, 0.0F);
/* 588 */     this.rightringfingersection1.func_78784_a(86, 34).func_228303_a_(-2.0F, -2.75F, -1.0F, 4.0F, 3.0F, 2.0F, 0.02F, true);
/*     */     
/* 590 */     this.rightringfingersection2 = new ModelRenderer((Model)this);
/* 591 */     this.rightringfingersection2.func_78793_a(-0.8083F, -2.7732F, 0.0013F);
/* 592 */     this.rightringfingersection1.func_78792_a(this.rightringfingersection2);
/* 593 */     setRotationAngle(this.rightringfingersection2, 0.0F, 0.0F, -0.3491F);
/* 594 */     this.rightringfingersection2.func_78784_a(88, 27).func_228303_a_(-1.0F, -2.25F, -1.0F, 2.0F, 4.0F, 2.0F, 0.01F, true);
/*     */     
/* 596 */     this.rightringfingersection3 = new ModelRenderer((Model)this);
/* 597 */     this.rightringfingersection3.func_78793_a(0.0074F, -2.4571F, 0.0F);
/* 598 */     this.rightringfingersection2.func_78792_a(this.rightringfingersection3);
/* 599 */     setRotationAngle(this.rightringfingersection3, 0.0F, 0.0F, -1.7453F);
/* 600 */     this.rightringfingersection3.func_78784_a(88, 20).func_228303_a_(-1.0F, -3.5F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, true);
/*     */     
/* 602 */     this.rightringfingersection4 = new ModelRenderer((Model)this);
/* 603 */     this.rightringfingersection4.func_78793_a(0.3956F, -3.5959F, -0.0026F);
/* 604 */     this.rightringfingersection3.func_78792_a(this.rightringfingersection4);
/* 605 */     this.rightringfingersection4.func_78784_a(90, 15).func_228303_a_(-0.5F, -2.25F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, true);
/*     */     
/* 607 */     this.rightpinkyfingersection1 = new ModelRenderer((Model)this);
/* 608 */     this.rightpinkyfingersection1.func_78793_a(-2.7115F, -2.3505F, 3.3389F);
/* 609 */     this.righthand.func_78792_a(this.rightpinkyfingersection1);
/* 610 */     setRotationAngle(this.rightpinkyfingersection1, -0.4276F, 0.0F, 0.0F);
/* 611 */     this.rightpinkyfingersection1.func_78784_a(86, 34).func_228303_a_(-2.0F, -2.75F, -1.0F, 4.0F, 4.0F, 2.0F, 0.02F, true);
/*     */     
/* 613 */     this.rightpinkyfingersection2 = new ModelRenderer((Model)this);
/* 614 */     this.rightpinkyfingersection2.func_78793_a(-0.8083F, -2.7732F, 0.0013F);
/* 615 */     this.rightpinkyfingersection1.func_78792_a(this.rightpinkyfingersection2);
/* 616 */     setRotationAngle(this.rightpinkyfingersection2, 0.0F, 0.0F, -0.3491F);
/* 617 */     this.rightpinkyfingersection2.func_78784_a(88, 27).func_228303_a_(-1.0F, -2.25F, -1.0F, 2.0F, 4.0F, 2.0F, 0.01F, true);
/*     */     
/* 619 */     this.rightpinkyfingersection3 = new ModelRenderer((Model)this);
/* 620 */     this.rightpinkyfingersection3.func_78793_a(0.0074F, -2.4571F, 0.0F);
/* 621 */     this.rightpinkyfingersection2.func_78792_a(this.rightpinkyfingersection3);
/* 622 */     setRotationAngle(this.rightpinkyfingersection3, 0.0F, 0.0F, -1.7453F);
/* 623 */     this.rightpinkyfingersection3.func_78784_a(88, 20).func_228303_a_(-1.0F, -3.5F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, true);
/*     */     
/* 625 */     this.rightpinkyfingersection4 = new ModelRenderer((Model)this);
/* 626 */     this.rightpinkyfingersection4.func_78793_a(0.3956F, -3.5959F, -0.0026F);
/* 627 */     this.rightpinkyfingersection3.func_78792_a(this.rightpinkyfingersection4);
/* 628 */     this.rightpinkyfingersection4.func_78784_a(90, 15).func_228303_a_(-0.5F, -2.25F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, true);
/*     */     
/* 630 */     this.bodysection2 = new ModelRenderer((Model)this);
/* 631 */     this.bodysection2.func_78793_a(-0.1057F, -0.1333F, 16.088F);
/* 632 */     this.bodysection1.func_78792_a(this.bodysection2);
/* 633 */     this.bodysection2.func_78784_a(341, 46).func_228303_a_(0.049F, -19.7225F, 0.2315F, 0.0F, 15.0F, 26.0F, 0.0F, false);
/* 634 */     this.bodysection2.func_78784_a(141, 419).func_228303_a_(-7.549F, -5.2775F, 0.206F, 15.0F, 11.0F, 27.0F, 0.0F, false);
/*     */     
/* 636 */     this.bodysection3 = new ModelRenderer((Model)this);
/* 637 */     this.bodysection3.func_78793_a(0.0197F, 0.29F, 26.319F);
/* 638 */     this.bodysection2.func_78792_a(this.bodysection3);
/* 639 */     setRotationAngle(this.bodysection3, 0.0F, 0.0F, 0.0F);
/* 640 */     this.bodysection3.func_78784_a(338, 40).func_228303_a_(0.0293F, -23.1875F, -0.125F, 0.0F, 17.0F, 40.0F, 0.0F, false);
/* 641 */     this.bodysection3.func_78784_a(127, 458).func_228303_a_(-8.0293F, -6.1875F, -0.125F, 16.0F, 13.0F, 40.0F, 0.0F, false);
/*     */     
/* 643 */     this.bodysection4 = new ModelRenderer((Model)this);
/* 644 */     this.bodysection4.func_78793_a(0.5F, -0.25F, 39.75F);
/* 645 */     this.bodysection3.func_78792_a(this.bodysection4);
/* 646 */     setRotationAngle(this.bodysection4, 0.0F, 0.0F, 0.0F);
/* 647 */     this.bodysection4.func_78784_a(338, 40).func_228303_a_(-0.4707F, -21.6875F, 0.125F, 0.0F, 16.0F, 40.0F, 0.0F, false);
/* 648 */     this.bodysection4.func_78784_a(127, 458).func_228303_a_(-8.0293F, -5.6875F, 0.125F, 15.0F, 12.0F, 40.0F, 0.0F, false);
/*     */     
/* 650 */     this.bodysection5 = new ModelRenderer((Model)this);
/* 651 */     this.bodysection5.func_78793_a(0.0F, 0.5F, 40.0F);
/* 652 */     this.bodysection4.func_78792_a(this.bodysection5);
/* 653 */     setRotationAngle(this.bodysection5, 0.0F, 0.0F, 0.0F);
/* 654 */     this.bodysection5.func_78784_a(338, 40).func_228303_a_(-0.4707F, -22.1875F, 0.125F, 0.0F, 16.0F, 40.0F, 0.0F, false);
/* 655 */     this.bodysection5.func_78784_a(127, 458).func_228303_a_(-8.0293F, -6.1875F, 0.125F, 15.0F, 12.0F, 40.0F, 0.0F, false);
/*     */     
/* 657 */     this.bodysection6 = new ModelRenderer((Model)this);
/* 658 */     this.bodysection6.func_78793_a(-0.5F, -0.75F, 40.0F);
/* 659 */     this.bodysection5.func_78792_a(this.bodysection6);
/* 660 */     setRotationAngle(this.bodysection6, 0.0F, 0.0F, 0.0F);
/* 661 */     this.bodysection6.func_78784_a(338, 40).func_228303_a_(0.0293F, -20.1875F, 0.125F, 0.0F, 15.0F, 40.0F, 0.0F, false);
/* 662 */     this.bodysection6.func_78784_a(127, 458).func_228303_a_(-7.0293F, -5.1875F, 0.125F, 14.0F, 11.0F, 40.0F, 0.0F, false);
/*     */     
/* 664 */     this.bodysection7 = new ModelRenderer((Model)this);
/* 665 */     this.bodysection7.func_78793_a(0.0F, 0.25F, 40.25F);
/* 666 */     this.bodysection6.func_78792_a(this.bodysection7);
/* 667 */     setRotationAngle(this.bodysection7, 0.0F, 0.0F, 0.0F);
/* 668 */     this.bodysection7.func_78784_a(338, 40).func_228303_a_(0.0293F, -20.4375F, -0.125F, 0.0F, 15.0F, 40.0F, 0.0F, false);
/* 669 */     this.bodysection7.func_78784_a(127, 458).func_228303_a_(-7.0293F, -5.4375F, -0.125F, 14.0F, 11.0F, 40.0F, 0.0F, false);
/*     */     
/* 671 */     this.bodysection8 = new ModelRenderer((Model)this);
/* 672 */     this.bodysection8.func_78793_a(0.0F, 0.5F, 39.75F);
/* 673 */     this.bodysection7.func_78792_a(this.bodysection8);
/* 674 */     setRotationAngle(this.bodysection8, 0.0F, 0.0F, 0.0F);
/* 675 */     this.bodysection8.func_78784_a(338, 40).func_228303_a_(0.0293F, -20.9375F, 0.125F, 0.0F, 15.0F, 40.0F, 0.0F, false);
/* 676 */     this.bodysection8.func_78784_a(127, 458).func_228303_a_(-7.0293F, -5.9375F, 0.125F, 14.0F, 11.0F, 40.0F, 0.0F, false);
/*     */     
/* 678 */     this.bodysection9 = new ModelRenderer((Model)this);
/* 679 */     this.bodysection9.func_78793_a(0.0F, 0.0F, 40.25F);
/* 680 */     this.bodysection8.func_78792_a(this.bodysection9);
/* 681 */     setRotationAngle(this.bodysection9, 0.0F, 0.0F, 0.0F);
/* 682 */     this.bodysection9.func_78784_a(338, 40).func_228303_a_(0.0293F, -19.9375F, -0.125F, 0.0F, 15.0F, 40.0F, 0.0F, false);
/* 683 */     this.bodysection9.func_78784_a(127, 458).func_228303_a_(-6.5293F, -5.4375F, -0.125F, 13.0F, 10.0F, 40.0F, 0.0F, false);
/*     */     
/* 685 */     this.bodysection10 = new ModelRenderer((Model)this);
/* 686 */     this.bodysection10.func_78793_a(0.0F, -0.25F, 39.75F);
/* 687 */     this.bodysection9.func_78792_a(this.bodysection10);
/* 688 */     setRotationAngle(this.bodysection10, 0.0F, 0.0F, 0.0F);
/* 689 */     this.bodysection10.func_78784_a(338, 40).func_228303_a_(0.0293F, -18.6875F, 0.125F, 0.0F, 14.0F, 40.0F, 0.0F, false);
/* 690 */     this.bodysection10.func_78784_a(127, 458).func_228303_a_(-6.0293F, -4.6875F, 0.125F, 12.0F, 9.0F, 40.0F, 0.0F, false);
/*     */     
/* 692 */     this.bodysection11 = new ModelRenderer((Model)this);
/* 693 */     this.bodysection11.func_78793_a(0.0F, 0.0F, 40.0F);
/* 694 */     this.bodysection10.func_78792_a(this.bodysection11);
/* 695 */     setRotationAngle(this.bodysection11, 0.0F, 0.0F, 0.0F);
/* 696 */     this.bodysection11.func_78784_a(338, 40).func_228303_a_(0.0293F, -17.6875F, 0.125F, 0.0F, 14.0F, 40.0F, 0.0F, false);
/* 697 */     this.bodysection11.func_78784_a(127, 458).func_228303_a_(-5.5293F, -4.1875F, 0.125F, 11.0F, 8.0F, 40.0F, 0.0F, false);
/*     */     
/* 699 */     this.tail1 = new ModelRenderer((Model)this);
/* 700 */     this.tail1.func_78793_a(0.0293F, -0.3934F, 39.7012F);
/* 701 */     this.bodysection11.func_78792_a(this.tail1);
/* 702 */     this.tail1.func_78784_a(338, -54).func_228303_a_(0.0F, -22.625F, -0.25F, 0.0F, 36.0F, 55.0F, 0.0F, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_225597_a_(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
/* 710 */     this.headsection1.func_228309_a_(matrixStack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 711 */     this.necksection1.func_228309_a_(matrixStack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/*     */   }
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 715 */     modelRenderer.field_78795_f = x;
/* 716 */     modelRenderer.field_78796_g = y;
/* 717 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\entities\projectiles\OnibiModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */