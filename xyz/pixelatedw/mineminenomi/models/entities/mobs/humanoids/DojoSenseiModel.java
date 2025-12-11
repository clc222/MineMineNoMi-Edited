/*     */ package xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Deprecated
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class DojoSenseiModel
/*     */   extends BipedModel
/*     */ {
/*     */   public ModelRenderer field_78116_c;
/*     */   public ModelRenderer rightleg;
/*     */   public ModelRenderer leftleg;
/*     */   public ModelRenderer sheath1;
/*     */   public ModelRenderer sheath2;
/*     */   public ModelRenderer Slipper3;
/*     */   public ModelRenderer Slipper4;
/*     */   public ModelRenderer Slipper1;
/*     */   public ModelRenderer Slipper2;
/*     */   public ModelRenderer hat1;
/*     */   public ModelRenderer hat2;
/*     */   public ModelRenderer hat3;
/*     */   public ModelRenderer hat4;
/*     */   public ModelRenderer hat5;
/*     */   public ModelRenderer hat6;
/*     */   public ModelRenderer hat7;
/*     */   public ModelRenderer hat8;
/*     */   public ModelRenderer hat9;
/*     */   public ModelRenderer hat10;
/*     */   public ModelRenderer hat11;
/*     */   public ModelRenderer hat12;
/*     */   public ModelRenderer hat13;
/*     */   public ModelRenderer hat14;
/*     */   public ModelRenderer hat15;
/*     */   public ModelRenderer hat16;
/*     */   public ModelRenderer hat17;
/*     */   public ModelRenderer hat18;
/*     */   public ModelRenderer hat19;
/*     */   public ModelRenderer hat20;
/*     */   public ModelRenderer hat21;
/*     */   public ModelRenderer hat22;
/*     */   public ModelRenderer hat23;
/*     */   public ModelRenderer hat24;
/*     */   public ModelRenderer hat25;
/*     */   public ModelRenderer hat26;
/*     */   public ModelRenderer hat27;
/*     */   public ModelRenderer hat28;
/*     */   public ModelRenderer hat29;
/*     */   public ModelRenderer hat30;
/*     */   public ModelRenderer hat31;
/*     */   public ModelRenderer hat32;
/*     */   public ModelRenderer hat33;
/*     */   public ModelRenderer hat34;
/*     */   public ModelRenderer hat35;
/*     */   public ModelRenderer hat36;
/*     */   public ModelRenderer hat37;
/*     */   public ModelRenderer hat38;
/*     */   public ModelRenderer hat39;
/*     */   public ModelRenderer hat40;
/*     */   public ModelRenderer hat41;
/*     */   public ModelRenderer hat42;
/*     */   public ModelRenderer hat43;
/*     */   public ModelRenderer hat44;
/*     */   public ModelRenderer hat45;
/*     */   public ModelRenderer hat46;
/*     */   
/*     */   public DojoSenseiModel() {
/*  78 */     super(0.0F, 0.0F, 128, 128);
/*  79 */     this.field_78090_t = 128;
/*  80 */     this.field_78089_u = 128;
/*     */     
/*  82 */     this.field_78116_c = new ModelRenderer((Model)this);
/*  83 */     this.field_78116_c.func_78793_a(0.0F, -1.0F, 0.0F);
/*  84 */     this.field_78116_c.func_78784_a(0, 0).func_228303_a_(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
/*     */     
/*  86 */     this.field_178720_f = new ModelRenderer((Model)this);
/*  87 */     this.field_178720_f.func_78793_a(0.0F, 0.0F, 0.0F);
/*  88 */     this.field_78116_c.func_78792_a(this.field_178720_f);
/*     */ 
/*     */     
/*  91 */     this.hat1 = new ModelRenderer((Model)this);
/*  92 */     this.hat1.func_78793_a(0.0F, -9.0F, 0.0F);
/*  93 */     this.field_178720_f.func_78792_a(this.hat1);
/*  94 */     setRotationAngle(this.hat1, -0.1571F, 0.0F, 0.1571F);
/*  95 */     this.hat1.func_78784_a(0, 34).func_228303_a_(0.0F, 0.0F, -0.5F, 7.0F, 1.0F, 1.0F, 0.1F, false);
/*     */     
/*  97 */     this.hat2 = new ModelRenderer((Model)this);
/*  98 */     this.hat2.func_78793_a(0.0F, -9.0F, 0.0F);
/*  99 */     this.field_178720_f.func_78792_a(this.hat2);
/* 100 */     setRotationAngle(this.hat2, -0.1791F, -0.1379F, 0.1586F);
/* 101 */     this.hat2.func_78784_a(0, 34).func_228303_a_(0.0F, 0.0F, -0.5F, 7.0F, 1.0F, 1.0F, 0.1F, false);
/*     */     
/* 103 */     this.hat3 = new ModelRenderer((Model)this);
/* 104 */     this.hat3.func_78793_a(0.0F, -9.0F, 0.0F);
/* 105 */     this.field_178720_f.func_78792_a(this.hat3);
/* 106 */     setRotationAngle(this.hat3, -0.2019F, -0.2757F, 0.1633F);
/* 107 */     this.hat3.func_78784_a(0, 34).func_228303_a_(0.0F, 0.0F, -0.5F, 7.0F, 1.0F, 1.0F, 0.1F, false);
/*     */     
/* 109 */     this.hat4 = new ModelRenderer((Model)this);
/* 110 */     this.hat4.func_78793_a(0.0F, -9.0F, 0.0F);
/* 111 */     this.field_178720_f.func_78792_a(this.hat4);
/* 112 */     setRotationAngle(this.hat4, -0.2266F, -0.4134F, 0.1717F);
/* 113 */     this.hat4.func_78784_a(0, 34).func_228303_a_(0.0F, 0.0F, -0.5F, 7.0F, 1.0F, 1.0F, 0.1F, false);
/*     */     
/* 115 */     this.hat5 = new ModelRenderer((Model)this);
/* 116 */     this.hat5.func_78793_a(0.0F, -9.0F, 0.0F);
/* 117 */     this.field_178720_f.func_78792_a(this.hat5);
/* 118 */     setRotationAngle(this.hat5, -0.2545F, -0.5508F, 0.1846F);
/* 119 */     this.hat5.func_78784_a(0, 34).func_228303_a_(0.0F, 0.0F, -0.5F, 7.0F, 1.0F, 1.0F, 0.1F, false);
/*     */     
/* 121 */     this.hat6 = new ModelRenderer((Model)this);
/* 122 */     this.hat6.func_78793_a(0.0F, -9.0F, 0.0F);
/* 123 */     this.field_178720_f.func_78792_a(this.hat6);
/* 124 */     setRotationAngle(this.hat6, -0.2876F, -0.6878F, 0.2039F);
/* 125 */     this.hat6.func_78784_a(0, 34).func_228303_a_(0.0F, 0.0F, -0.5F, 7.0F, 1.0F, 1.0F, 0.1F, false);
/*     */     
/* 127 */     this.hat7 = new ModelRenderer((Model)this);
/* 128 */     this.hat7.func_78793_a(0.0F, -9.0F, 0.0F);
/* 129 */     this.field_178720_f.func_78792_a(this.hat7);
/* 130 */     setRotationAngle(this.hat7, -0.3291F, -0.8242F, 0.2324F);
/* 131 */     this.hat7.func_78784_a(0, 34).func_228303_a_(0.0F, 0.0F, -0.5F, 7.0F, 1.0F, 1.0F, 0.1F, false);
/*     */     
/* 133 */     this.hat8 = new ModelRenderer((Model)this);
/* 134 */     this.hat8.func_78793_a(0.0F, -9.0F, 0.0F);
/* 135 */     this.field_178720_f.func_78792_a(this.hat8);
/* 136 */     setRotationAngle(this.hat8, -0.385F, -0.9594F, 0.276F);
/* 137 */     this.hat8.func_78784_a(0, 34).func_228303_a_(0.0F, 0.0F, -0.5F, 7.0F, 1.0F, 1.0F, 0.1F, false);
/*     */     
/* 139 */     this.hat9 = new ModelRenderer((Model)this);
/* 140 */     this.hat9.func_78793_a(0.0F, -9.0F, 0.0F);
/* 141 */     this.field_178720_f.func_78792_a(this.hat9);
/* 142 */     setRotationAngle(this.hat9, -0.4675F, -1.0924F, 0.3467F);
/* 143 */     this.hat9.func_78784_a(0, 34).func_228303_a_(0.0F, 0.0F, -0.5F, 7.0F, 1.0F, 1.0F, 0.1F, false);
/*     */     
/* 145 */     this.hat10 = new ModelRenderer((Model)this);
/* 146 */     this.hat10.func_78793_a(0.0F, -9.0F, 0.0F);
/* 147 */     this.field_178720_f.func_78792_a(this.hat10);
/* 148 */     setRotationAngle(this.hat10, -0.6058F, -1.2207F, 0.4736F);
/* 149 */     this.hat10.func_78784_a(0, 34).func_228303_a_(0.0F, 0.0F, -0.5F, 7.0F, 1.0F, 1.0F, 0.1F, false);
/*     */     
/* 151 */     this.hat11 = new ModelRenderer((Model)this);
/* 152 */     this.hat11.func_78793_a(0.0F, -9.0F, 0.0F);
/* 153 */     this.field_178720_f.func_78792_a(this.hat11);
/* 154 */     setRotationAngle(this.hat11, -0.8828F, -1.3365F, 0.7395F);
/* 155 */     this.hat11.func_78784_a(0, 34).func_228303_a_(0.0F, 0.0F, -0.5F, 7.0F, 1.0F, 1.0F, 0.1F, false);
/*     */     
/* 157 */     this.hat12 = new ModelRenderer((Model)this);
/* 158 */     this.hat12.func_78793_a(0.0F, -9.0F, 0.0F);
/* 159 */     this.field_178720_f.func_78792_a(this.hat12);
/* 160 */     setRotationAngle(this.hat12, -1.5082F, -1.4099F, 1.3539F);
/* 161 */     this.hat12.func_78784_a(0, 34).func_228303_a_(0.0F, 0.0F, -0.5F, 7.0F, 1.0F, 1.0F, 0.1F, false);
/*     */     
/* 163 */     this.hat13 = new ModelRenderer((Model)this);
/* 164 */     this.hat13.func_78793_a(0.0F, -9.0F, 0.0F);
/* 165 */     this.field_178720_f.func_78792_a(this.hat13);
/* 166 */     setRotationAngle(this.hat13, -2.3195F, -1.3822F, 2.1541F);
/* 167 */     this.hat13.func_78784_a(0, 34).func_228303_a_(0.0F, 0.0F, -0.5F, 7.0F, 1.0F, 1.0F, 0.1F, false);
/*     */     
/* 169 */     this.hat14 = new ModelRenderer((Model)this);
/* 170 */     this.hat14.func_78793_a(0.0F, -9.0F, 0.0F);
/* 171 */     this.field_178720_f.func_78792_a(this.hat14);
/* 172 */     setRotationAngle(this.hat14, -2.7383F, -1.2812F, 2.5619F);
/* 173 */     this.hat14.func_78784_a(0, 34).func_228303_a_(0.0F, 0.0F, -0.5F, 7.0F, 1.0F, 1.0F, 0.1F, false);
/*     */     
/* 175 */     this.hat15 = new ModelRenderer((Model)this);
/* 176 */     this.hat15.func_78793_a(0.0F, -9.0F, 0.0F);
/* 177 */     this.field_178720_f.func_78792_a(this.hat15);
/* 178 */     setRotationAngle(this.hat15, -2.9293F, -1.1574F, 2.7416F);
/* 179 */     this.hat15.func_78784_a(0, 34).func_228303_a_(0.0F, 0.0F, -0.5F, 7.0F, 1.0F, 1.0F, 0.1F, false);
/*     */     
/* 181 */     this.hat16 = new ModelRenderer((Model)this);
/* 182 */     this.hat16.func_78793_a(0.0F, -9.0F, 0.0F);
/* 183 */     this.field_178720_f.func_78792_a(this.hat16);
/* 184 */     setRotationAngle(this.hat16, -3.0341F, -1.0263F, 2.8348F);
/* 185 */     this.hat16.func_78784_a(0, 34).func_228303_a_(0.0F, 0.0F, -0.5F, 7.0F, 1.0F, 1.0F, 0.1F, false);
/*     */     
/* 187 */     this.hat17 = new ModelRenderer((Model)this);
/* 188 */     this.hat17.func_78793_a(0.0F, -9.0F, 0.0F);
/* 189 */     this.field_178720_f.func_78792_a(this.hat17);
/* 190 */     setRotationAngle(this.hat17, -3.1011F, -0.892F, 2.8898F);
/* 191 */     this.hat17.func_78784_a(0, 34).func_228303_a_(0.0F, 0.0F, -0.5F, 7.0F, 1.0F, 1.0F, 0.1F, false);
/*     */     
/* 193 */     this.hat18 = new ModelRenderer((Model)this);
/* 194 */     this.hat18.func_78793_a(0.0F, -9.0F, 0.0F);
/* 195 */     this.field_178720_f.func_78792_a(this.hat18);
/* 196 */     setRotationAngle(this.hat18, 3.1344F, -0.7561F, 2.9249F);
/* 197 */     this.hat18.func_78784_a(0, 34).func_228303_a_(0.0F, 0.0F, -0.5F, 7.0F, 1.0F, 1.0F, 0.1F, false);
/*     */     
/* 199 */     this.hat19 = new ModelRenderer((Model)this);
/* 200 */     this.hat19.func_78793_a(0.0F, -9.0F, 0.0F);
/* 201 */     this.field_178720_f.func_78792_a(this.hat19);
/* 202 */     setRotationAngle(this.hat19, 3.0977F, -0.6194F, 2.9483F);
/* 203 */     this.hat19.func_78784_a(0, 34).func_228303_a_(0.0F, 0.0F, -0.5F, 7.0F, 1.0F, 1.0F, 0.1F, false);
/*     */     
/* 205 */     this.hat20 = new ModelRenderer((Model)this);
/* 206 */     this.hat20.func_78793_a(0.0F, -9.0F, 0.0F);
/* 207 */     this.field_178720_f.func_78792_a(this.hat20);
/* 208 */     setRotationAngle(this.hat20, 3.0675F, -0.4822F, 2.9641F);
/* 209 */     this.hat20.func_78784_a(0, 34).func_228303_a_(0.0F, 0.0F, -0.5F, 7.0F, 1.0F, 1.0F, 0.1F, false);
/*     */     
/* 211 */     this.hat21 = new ModelRenderer((Model)this);
/* 212 */     this.hat21.func_78793_a(0.0F, -9.0F, 0.0F);
/* 213 */     this.field_178720_f.func_78792_a(this.hat21);
/* 214 */     setRotationAngle(this.hat21, 3.0414F, -0.3446F, 2.9746F);
/* 215 */     this.hat21.func_78784_a(0, 34).func_228303_a_(0.0F, 0.0F, -0.5F, 7.0F, 1.0F, 1.0F, 0.1F, false);
/*     */     
/* 217 */     this.hat22 = new ModelRenderer((Model)this);
/* 218 */     this.hat22.func_78793_a(0.0F, -9.0F, 0.0F);
/* 219 */     this.field_178720_f.func_78792_a(this.hat22);
/* 220 */     setRotationAngle(this.hat22, 3.0178F, -0.2068F, 2.9811F);
/* 221 */     this.hat22.func_78784_a(0, 34).func_228303_a_(0.0F, 0.0F, -0.5F, 7.0F, 1.0F, 1.0F, 0.1F, false);
/*     */     
/* 223 */     this.hat23 = new ModelRenderer((Model)this);
/* 224 */     this.hat23.func_78793_a(0.0F, -9.0F, 0.0F);
/* 225 */     this.field_178720_f.func_78792_a(this.hat23);
/* 226 */     setRotationAngle(this.hat23, 3.0178F, -0.2068F, 2.9811F);
/* 227 */     this.hat23.func_78784_a(0, 34).func_228303_a_(0.0F, 0.0F, -0.5F, 7.0F, 1.0F, 1.0F, 0.1F, false);
/*     */     
/* 229 */     this.hat24 = new ModelRenderer((Model)this);
/* 230 */     this.hat24.func_78793_a(0.0F, -9.0F, 0.0F);
/* 231 */     this.field_178720_f.func_78792_a(this.hat24);
/* 232 */     setRotationAngle(this.hat24, 2.9955F, -0.069F, 2.9841F);
/* 233 */     this.hat24.func_78784_a(0, 34).func_228303_a_(0.0F, 0.0F, -0.5F, 7.0F, 1.0F, 1.0F, 0.1F, false);
/*     */     
/* 235 */     this.hat25 = new ModelRenderer((Model)this);
/* 236 */     this.hat25.func_78793_a(0.0F, -9.0F, 0.0F);
/* 237 */     this.field_178720_f.func_78792_a(this.hat25);
/* 238 */     setRotationAngle(this.hat25, 2.9791F, 0.0345F, 2.9844F);
/* 239 */     this.hat25.func_78784_a(0, 34).func_228303_a_(0.0F, 0.0F, -0.5F, 7.0F, 1.0F, 1.0F, 0.1F, false);
/*     */     
/* 241 */     this.hat26 = new ModelRenderer((Model)this);
/* 242 */     this.hat26.func_78793_a(0.0F, -9.0F, 0.0F);
/* 243 */     this.field_178720_f.func_78792_a(this.hat26);
/* 244 */     setRotationAngle(this.hat26, 2.9569F, 0.1724F, 2.9821F);
/* 245 */     this.hat26.func_78784_a(0, 34).func_228303_a_(0.0F, 0.0F, -0.5F, 7.0F, 1.0F, 1.0F, 0.1F, false);
/*     */     
/* 247 */     this.hat27 = new ModelRenderer((Model)this);
/* 248 */     this.hat27.func_78793_a(0.0F, -9.0F, 0.0F);
/* 249 */     this.field_178720_f.func_78792_a(this.hat27);
/* 250 */     setRotationAngle(this.hat27, 2.9337F, 0.3102F, 2.9766F);
/* 251 */     this.hat27.func_78784_a(0, 34).func_228303_a_(0.0F, 0.0F, -0.5F, 7.0F, 1.0F, 1.0F, 0.1F, false);
/*     */     
/* 253 */     this.hat28 = new ModelRenderer((Model)this);
/* 254 */     this.hat28.func_78793_a(0.0F, -9.0F, 0.0F);
/* 255 */     this.field_178720_f.func_78792_a(this.hat28);
/* 256 */     setRotationAngle(this.hat28, 2.9084F, 0.4478F, 2.9672F);
/* 257 */     this.hat28.func_78784_a(0, 34).func_228303_a_(0.0F, 0.0F, -0.5F, 7.0F, 1.0F, 1.0F, 0.1F, false);
/*     */     
/* 259 */     this.hat29 = new ModelRenderer((Model)this);
/* 260 */     this.hat29.func_78793_a(0.0F, -9.0F, 0.0F);
/* 261 */     this.field_178720_f.func_78792_a(this.hat29);
/* 262 */     setRotationAngle(this.hat29, 2.8794F, 0.5851F, 2.9528F);
/* 263 */     this.hat29.func_78784_a(0, 34).func_228303_a_(0.0F, 0.0F, -0.5F, 7.0F, 1.0F, 1.0F, 0.1F, false);
/*     */     
/* 265 */     this.hat30 = new ModelRenderer((Model)this);
/* 266 */     this.hat30.func_78793_a(0.0F, -9.0F, 0.0F);
/* 267 */     this.field_178720_f.func_78792_a(this.hat30);
/* 268 */     setRotationAngle(this.hat30, 2.8446F, 0.722F, 2.9316F);
/* 269 */     this.hat30.func_78784_a(0, 34).func_228303_a_(0.0F, 0.0F, -0.5F, 7.0F, 1.0F, 1.0F, 0.1F, false);
/*     */     
/* 271 */     this.hat31 = new ModelRenderer((Model)this);
/* 272 */     this.hat31.func_78793_a(0.0F, -9.0F, 0.0F);
/* 273 */     this.field_178720_f.func_78792_a(this.hat31);
/* 274 */     setRotationAngle(this.hat31, 2.8002F, 0.8581F, 2.9F);
/* 275 */     this.hat31.func_78784_a(0, 34).func_228303_a_(0.0F, 0.0F, -0.5F, 7.0F, 1.0F, 1.0F, 0.1F, false);
/*     */     
/* 277 */     this.hat32 = new ModelRenderer((Model)this);
/* 278 */     this.hat32.func_78793_a(0.0F, -9.0F, 0.0F);
/* 279 */     this.field_178720_f.func_78792_a(this.hat32);
/* 280 */     setRotationAngle(this.hat32, 2.7392F, 0.9929F, 2.8512F);
/* 281 */     this.hat32.func_78784_a(0, 34).func_228303_a_(0.0F, 0.0F, -0.5F, 7.0F, 1.0F, 1.0F, 0.1F, false);
/*     */     
/* 283 */     this.hat33 = new ModelRenderer((Model)this);
/* 284 */     this.hat33.func_78793_a(0.0F, -9.0F, 0.0F);
/* 285 */     this.field_178720_f.func_78792_a(this.hat33);
/* 286 */     setRotationAngle(this.hat33, 2.6466F, 1.1251F, 2.7703F);
/* 287 */     this.hat33.func_78784_a(0, 34).func_228303_a_(0.0F, 0.0F, -0.5F, 7.0F, 1.0F, 1.0F, 0.1F, false);
/*     */     
/* 289 */     this.hat34 = new ModelRenderer((Model)this);
/* 290 */     this.hat34.func_78793_a(0.0F, -9.0F, 0.0F);
/* 291 */     this.field_178720_f.func_78792_a(this.hat34);
/* 292 */     setRotationAngle(this.hat34, 2.4851F, 1.2514F, 2.6201F);
/* 293 */     this.hat34.func_78784_a(0, 34).func_228303_a_(0.0F, 0.0F, -0.5F, 7.0F, 1.0F, 1.0F, 0.1F, false);
/*     */     
/* 295 */     this.hat35 = new ModelRenderer((Model)this);
/* 296 */     this.hat35.func_78793_a(0.0F, -9.0F, 0.0F);
/* 297 */     this.field_178720_f.func_78792_a(this.hat35);
/* 298 */     setRotationAngle(this.hat35, 2.1456F, 1.361F, 2.2917F);
/* 299 */     this.hat35.func_78784_a(0, 34).func_228303_a_(0.0F, 0.0F, -0.5F, 7.0F, 1.0F, 1.0F, 0.1F, false);
/*     */     
/* 301 */     this.hat36 = new ModelRenderer((Model)this);
/* 302 */     this.hat36.func_78793_a(0.0F, -9.0F, 0.0F);
/* 303 */     this.field_178720_f.func_78792_a(this.hat36);
/* 304 */     setRotationAngle(this.hat36, 1.4137F, 1.4137F, 1.5708F);
/* 305 */     this.hat36.func_78784_a(0, 34).func_228303_a_(0.0F, 0.0F, -0.5F, 7.0F, 1.0F, 1.0F, 0.1F, false);
/*     */     
/* 307 */     this.hat37 = new ModelRenderer((Model)this);
/* 308 */     this.hat37.func_78793_a(0.0F, -9.0F, 0.0F);
/* 309 */     this.field_178720_f.func_78792_a(this.hat37);
/* 310 */     setRotationAngle(this.hat37, 0.6818F, 1.361F, 0.8499F);
/* 311 */     this.hat37.func_78784_a(0, 34).func_228303_a_(0.0F, 0.0F, -0.5F, 7.0F, 1.0F, 1.0F, 0.1F, false);
/*     */     
/* 313 */     this.hat38 = new ModelRenderer((Model)this);
/* 314 */     this.hat38.func_78793_a(0.0F, -9.0F, 0.0F);
/* 315 */     this.field_178720_f.func_78792_a(this.hat38);
/* 316 */     setRotationAngle(this.hat38, 0.3423F, 1.2514F, 0.5215F);
/* 317 */     this.hat38.func_78784_a(0, 34).func_228303_a_(0.0F, 0.0F, -0.5F, 7.0F, 1.0F, 1.0F, 0.1F, false);
/*     */     
/* 319 */     this.hat39 = new ModelRenderer((Model)this);
/* 320 */     this.hat39.func_78793_a(0.0F, -9.0F, 0.0F);
/* 321 */     this.field_178720_f.func_78792_a(this.hat39);
/* 322 */     setRotationAngle(this.hat39, 0.1808F, 1.1251F, 0.3713F);
/* 323 */     this.hat39.func_78784_a(0, 34).func_228303_a_(0.0F, 0.0F, -0.5F, 7.0F, 1.0F, 1.0F, 0.1F, false);
/*     */     
/* 325 */     this.hat40 = new ModelRenderer((Model)this);
/* 326 */     this.hat40.func_78793_a(0.0F, -9.0F, 0.0F);
/* 327 */     this.field_178720_f.func_78792_a(this.hat40);
/* 328 */     setRotationAngle(this.hat40, 0.0882F, 0.9929F, 0.2904F);
/* 329 */     this.hat40.func_78784_a(0, 34).func_228303_a_(0.0F, 0.0F, -0.5F, 7.0F, 1.0F, 1.0F, 0.1F, false);
/*     */     
/* 331 */     this.hat41 = new ModelRenderer((Model)this);
/* 332 */     this.hat41.func_78793_a(0.0F, -9.0F, 0.0F);
/* 333 */     this.field_178720_f.func_78792_a(this.hat41);
/* 334 */     setRotationAngle(this.hat41, 0.0272F, 0.8581F, 0.2416F);
/* 335 */     this.hat41.func_78784_a(0, 34).func_228303_a_(0.0F, 0.0F, -0.5F, 7.0F, 1.0F, 1.0F, 0.1F, false);
/*     */     
/* 337 */     this.hat42 = new ModelRenderer((Model)this);
/* 338 */     this.hat42.func_78793_a(0.0F, -9.0F, 0.0F);
/* 339 */     this.field_178720_f.func_78792_a(this.hat42);
/* 340 */     setRotationAngle(this.hat42, -0.0171F, 0.722F, 0.21F);
/* 341 */     this.hat42.func_78784_a(0, 34).func_228303_a_(0.0F, 0.0F, -0.5F, 7.0F, 1.0F, 1.0F, 0.1F, false);
/*     */     
/* 343 */     this.hat43 = new ModelRenderer((Model)this);
/* 344 */     this.hat43.func_78793_a(0.0F, -9.0F, 0.0F);
/* 345 */     this.field_178720_f.func_78792_a(this.hat43);
/* 346 */     setRotationAngle(this.hat43, -0.052F, 0.5851F, 0.1888F);
/* 347 */     this.hat43.func_78784_a(0, 34).func_228303_a_(0.0F, 0.0F, -0.5F, 7.0F, 1.0F, 1.0F, 0.1F, false);
/*     */     
/* 349 */     this.hat44 = new ModelRenderer((Model)this);
/* 350 */     this.hat44.func_78793_a(0.0F, -9.0F, 0.0F);
/* 351 */     this.field_178720_f.func_78792_a(this.hat44);
/* 352 */     setRotationAngle(this.hat44, -0.0809F, 0.4478F, 0.1744F);
/* 353 */     this.hat44.func_78784_a(0, 34).func_228303_a_(0.0F, 0.0F, -0.5F, 7.0F, 1.0F, 1.0F, 0.1F, false);
/*     */     
/* 355 */     this.hat45 = new ModelRenderer((Model)this);
/* 356 */     this.hat45.func_78793_a(0.0F, -9.0F, 0.0F);
/* 357 */     this.field_178720_f.func_78792_a(this.hat45);
/* 358 */     setRotationAngle(this.hat45, -0.1063F, 0.3102F, 0.165F);
/* 359 */     this.hat45.func_78784_a(0, 34).func_228303_a_(0.0F, 0.0F, -0.5F, 7.0F, 1.0F, 1.0F, 0.1F, false);
/*     */     
/* 361 */     this.hat46 = new ModelRenderer((Model)this);
/* 362 */     this.hat46.func_78793_a(0.0F, -9.0F, 0.0F);
/* 363 */     this.field_178720_f.func_78792_a(this.hat46);
/* 364 */     setRotationAngle(this.hat46, -0.1295F, 0.1724F, 0.1595F);
/* 365 */     this.hat46.func_78784_a(0, 34).func_228303_a_(0.0F, 0.0F, -0.5F, 7.0F, 1.0F, 1.0F, 0.1F, false);
/*     */     
/* 367 */     this.rightleg = new ModelRenderer((Model)this);
/* 368 */     this.rightleg.func_78793_a(-2.0F, 11.0F, 0.0F);
/* 369 */     this.rightleg.func_78784_a(0, 17).func_228303_a_(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
/*     */     
/* 371 */     this.Slipper4 = new ModelRenderer((Model)this);
/* 372 */     this.Slipper4.func_78793_a(0.0F, 0.0F, 0.0F);
/* 373 */     this.rightleg.func_78792_a(this.Slipper4);
/* 374 */     this.Slipper4.func_78784_a(33, 0).func_228303_a_(-2.0F, 12.0F, 0.5F, 4.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 376 */     this.Slipper3 = new ModelRenderer((Model)this);
/* 377 */     this.Slipper3.func_78793_a(0.0F, 0.0F, 0.0F);
/* 378 */     this.rightleg.func_78792_a(this.Slipper3);
/* 379 */     this.Slipper3.func_78784_a(33, 0).func_228303_a_(-2.0F, 12.0F, -1.5F, 4.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 381 */     this.leftleg = new ModelRenderer((Model)this);
/* 382 */     this.leftleg.func_78793_a(2.0F, 11.0F, 0.0F);
/* 383 */     this.leftleg.func_78784_a(0, 17).func_228303_a_(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
/*     */     
/* 385 */     this.Slipper2 = new ModelRenderer((Model)this);
/* 386 */     this.Slipper2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 387 */     this.leftleg.func_78792_a(this.Slipper2);
/* 388 */     this.Slipper2.func_78784_a(33, 0).func_228303_a_(-2.0F, 12.0F, 0.5F, 4.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 390 */     this.Slipper1 = new ModelRenderer((Model)this);
/* 391 */     this.Slipper1.func_78793_a(0.0F, 0.0F, 0.0F);
/* 392 */     this.leftleg.func_78792_a(this.Slipper1);
/* 393 */     this.Slipper1.func_78784_a(33, 0).func_228303_a_(-2.0F, 12.0F, -1.5F, 4.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 395 */     this.field_178722_k = this.leftleg;
/* 396 */     this.field_178721_j = this.rightleg;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 402 */     this.field_78115_e.field_78797_d = -1.0F;
/* 403 */     this.field_178724_i.field_78797_d = 1.0F;
/* 404 */     this.field_178723_h.field_78797_d = 1.0F;
/* 405 */     this.field_178722_k.field_78797_d = 11.0F;
/* 406 */     this.field_178721_j.field_78797_d = 11.0F;
/*     */     
/* 408 */     this.field_78116_c.field_78806_j = false;
/*     */     
/* 410 */     super.func_225598_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225597_a_(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 416 */     super.func_225597_a_(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/*     */     
/* 418 */     this.leftleg.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.6662F) * 1.0F * limbSwingAmount;
/* 419 */     this.rightleg.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.6662F + 3.1415927F) * 1.0F * limbSwingAmount;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer model, float x, float y, float z) {
/* 424 */     model.field_78795_f = x;
/* 425 */     model.field_78796_g = y;
/* 426 */     model.field_78808_h = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\entities\mobs\humanoids\DojoSenseiModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */