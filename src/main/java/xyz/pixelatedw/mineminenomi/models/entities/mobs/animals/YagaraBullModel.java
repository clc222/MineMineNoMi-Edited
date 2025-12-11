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
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.YagaraBullEntity;
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class YagaraBullModel<T extends YagaraBullEntity> extends BipedModel<T> {
/*     */   public ModelRenderer body1;
/*     */   public ModelRenderer body2;
/*     */   public ModelRenderer tail1;
/*     */   public ModelRenderer neck1;
/*     */   public ModelRenderer leftfin1;
/*     */   public ModelRenderer leftfin2;
/*     */   public ModelRenderer rightfin1;
/*     */   public ModelRenderer rightfin2;
/*     */   public ModelRenderer saddle;
/*     */   public ModelRenderer belt1;
/*     */   public ModelRenderer belt2;
/*     */   public ModelRenderer tail2;
/*     */   public ModelRenderer tail3;
/*     */   public ModelRenderer tail4;
/*     */   public ModelRenderer tail5;
/*     */   public ModelRenderer tail6;
/*     */   public ModelRenderer neck2;
/*     */   public ModelRenderer neck3;
/*     */   public ModelRenderer neck4;
/*     */   public ModelRenderer head1;
/*     */   public ModelRenderer mane;
/*     */   public ModelRenderer head2;
/*     */   public ModelRenderer head3;
/*     */   public ModelRenderer righteye;
/*     */   public ModelRenderer lefteye;
/*     */   public ModelRenderer leftgill;
/*     */   public ModelRenderer rightgill;
/*     */   public ModelRenderer mout;
/*     */   public ModelRenderer saddleside1;
/*     */   public ModelRenderer saddleside2;
/*     */   public ModelRenderer saddlefront;
/*     */   public ModelRenderer saddleside3;
/*     */   public ModelRenderer saddleside4;
/*     */   public ModelRenderer saddleback;
/*     */   public ModelRenderer saddlemiddle;
/*     */   
/*     */   public YagaraBullModel() {
/*  54 */     super(1.0F);
/*  55 */     this.field_78090_t = 180;
/*  56 */     this.field_78089_u = 90;
/*     */     
/*  58 */     this.saddleside2 = new ModelRenderer((Model)this, 25, 64);
/*  59 */     this.saddleside2.func_78793_a(-4.8F, -1.2F, 10.1F);
/*  60 */     this.saddleside2.func_228301_a_(0.0F, -5.0F, -5.5F, 1.0F, 5.0F, 11.0F, 0.0F);
/*  61 */     setRotateAngle(this.saddleside2, 0.017453292F, 0.10471976F, -0.13962634F);
/*  62 */     this.rightfin1 = new ModelRenderer((Model)this, 99, 41);
/*  63 */     this.rightfin1.func_78793_a(-3.9F, 20.3F, -6.0F);
/*  64 */     this.rightfin1.func_228301_a_(-8.0F, -0.5F, -6.0F, 8.0F, 1.0F, 6.0F, 0.0F);
/*  65 */     setRotateAngle(this.rightfin1, -0.19221127F, 0.6485391F, -0.4435539F);
/*  66 */     this.tail2 = new ModelRenderer((Model)this, 47, 15);
/*  67 */     this.tail2.func_78793_a(0.0F, 0.0F, 4.0F);
/*  68 */     this.tail2.func_228301_a_(-4.0F, -3.5F, 0.0F, 8.0F, 7.0F, 5.0F, 0.0F);
/*  69 */     setRotateAngle(this.tail2, -0.034906585F, -0.0F, 0.0F);
/*  70 */     this.saddleside3 = new ModelRenderer((Model)this, 50, 64);
/*  71 */     this.saddleside3.func_78793_a(5.2F, -0.8F, -0.6F);
/*  72 */     this.saddleside3.func_228301_a_(-1.0F, -5.0F, -5.5F, 1.0F, 5.0F, 11.0F, 0.0F);
/*  73 */     setRotateAngle(this.saddleside3, 0.05235988F, 0.10471976F, 0.13962634F);
/*  74 */     this.saddle = new ModelRenderer((Model)this, 115, 0);
/*  75 */     this.saddle.func_78793_a(0.0F, 12.7F, -5.0F);
/*  76 */     this.saddle.func_228301_a_(-5.5F, -1.0F, -5.5F, 11.0F, 1.0F, 21.0F, 0.0F);
/*  77 */     setRotateAngle(this.saddle, -0.05235988F, -0.0F, 0.0F);
/*  78 */     this.neck3 = new ModelRenderer((Model)this, 78, 30);
/*  79 */     this.neck3.func_78793_a(0.0F, -1.6F, -3.0F);
/*  80 */     this.neck3.func_228301_a_(-3.5F, -2.5F, 0.0F, 7.0F, 5.0F, 5.0F, 0.0F);
/*  81 */     setRotateAngle(this.neck3, -0.4886922F, 0.0F, 0.0F);
/*  82 */     this.body2 = new ModelRenderer((Model)this, 0, 23);
/*  83 */     this.body2.func_78793_a(0.0F, 18.4F, 5.3F);
/*  84 */     this.body2.func_228301_a_(-6.0F, -5.0F, -4.5F, 12.0F, 10.0F, 9.0F, 0.0F);
/*  85 */     setRotateAngle(this.body2, 0.034906585F, -0.0F, 0.0F);
/*  86 */     this.rightgill = new ModelRenderer((Model)this, 0, 61);
/*  87 */     this.rightgill.func_78793_a(-2.2F, -1.0F, 0.0F);
/*  88 */     this.rightgill.func_228301_a_(-3.0F, 0.0F, -2.0F, 3.0F, 0.0F, 2.0F, 0.0F);
/*  89 */     setRotateAngle(this.rightgill, -0.17732546F, 0.3064798F, -0.93322754F);
/*  90 */     this.tail6 = new ModelRenderer((Model)this, 47, 42);
/*  91 */     this.tail6.func_78793_a(0.0F, 0.0F, 2.5F);
/*  92 */     this.tail6.func_228301_a_(0.0F, -2.5F, -2.5F, 0.0F, 7.0F, 7.0F, 0.0F);
/*  93 */     setRotateAngle(this.tail6, 0.6981317F, -0.0F, 0.0F);
/*  94 */     this.body1 = new ModelRenderer((Model)this, 0, 0);
/*  95 */     this.body1.func_78793_a(0.0F, 18.3F, -4.0F);
/*  96 */     this.body1.func_228301_a_(-6.0F, -5.5F, -5.5F, 12.0F, 11.0F, 11.0F, 0.0F);
/*  97 */     setRotateAngle(this.body1, -0.06981317F, -0.0F, 0.0F);
/*  98 */     this.tail4 = new ModelRenderer((Model)this, 47, 37);
/*  99 */     this.tail4.func_78793_a(0.0F, 0.0F, 3.0F);
/* 100 */     this.tail4.func_228301_a_(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 3.0F, 0.0F);
/* 101 */     setRotateAngle(this.tail4, -0.034906585F, -0.0F, 0.0F);
/* 102 */     this.neck2 = new ModelRenderer((Model)this, 78, 0);
/* 103 */     this.neck2.func_78793_a(0.0F, -3.0F, -3.5F);
/* 104 */     this.neck2.func_228301_a_(-4.0F, -3.0F, 0.0F, 8.0F, 6.0F, 7.0F, 0.0F);
/* 105 */     setRotateAngle(this.neck2, -0.6981317F, -0.0F, 0.0F);
/* 106 */     this.head2 = new ModelRenderer((Model)this, 0, 52);
/* 107 */     this.head2.func_78793_a(0.0F, 0.0F, -0.5F);
/* 108 */     this.head2.func_228301_a_(-3.0F, -5.0F, -3.0F, 6.0F, 5.0F, 3.0F, 0.0F);
/* 109 */     setRotateAngle(this.head2, 0.034906585F, -0.0F, 0.0F);
/* 110 */     this.rightfin2 = new ModelRenderer((Model)this, 99, 49);
/* 111 */     this.rightfin2.func_78793_a(-4.4F, 21.3F, 8.0F);
/* 112 */     this.rightfin2.func_228301_a_(-6.0F, -0.5F, -5.0F, 6.0F, 1.0F, 5.0F, 0.0F);
/* 113 */     setRotateAngle(this.rightfin2, -0.24228175F, 0.72677505F, -0.47536334F);
/* 114 */     this.leftfin2 = new ModelRenderer((Model)this, 99, 49);
/* 115 */     this.leftfin2.func_78793_a(4.4F, 21.3F, 8.0F);
/* 116 */     this.leftfin2.func_228301_a_(0.0F, -0.5F, -5.0F, 6.0F, 1.0F, 5.0F, 0.0F);
/* 117 */     setRotateAngle(this.leftfin2, -0.24228175F, -0.72677505F, 0.47536334F);
/* 118 */     this.mout = new ModelRenderer((Model)this, 19, 52);
/* 119 */     this.mout.func_78793_a(0.0F, 0.0F, -4.0F);
/* 120 */     this.mout.func_228301_a_(-3.0F, -2.5F, -4.0F, 6.0F, 5.0F, 4.0F, 0.0F);
/* 121 */     this.saddlemiddle = new ModelRenderer((Model)this, 100, 78);
/* 122 */     this.saddlemiddle.func_78793_a(-0.2F, -0.9F, 4.5F);
/* 123 */     this.saddlemiddle.func_228301_a_(-5.0F, -5.0F, -1.0F, 11.0F, 5.0F, 1.0F, 0.0F);
/* 124 */     setRotateAngle(this.saddlemiddle, -0.08726646F, -0.0F, 0.0F);
/* 125 */     this.saddleside4 = new ModelRenderer((Model)this, 75, 64);
/* 126 */     this.saddleside4.func_78793_a(5.2F, -1.1F, 9.9F);
/* 127 */     this.saddleside4.func_228301_a_(-1.0F, -5.0F, -5.5F, 1.0F, 5.0F, 11.0F, 0.0F);
/* 128 */     setRotateAngle(this.saddleside4, 0.017453292F, -0.10367256F, 0.14032447F);
/* 129 */     this.head1 = new ModelRenderer((Model)this, 0, 43);
/* 130 */     this.head1.func_78793_a(0.0F, -0.5F, 0.5F);
/* 131 */     this.head1.func_228301_a_(-3.0F, -5.0F, -1.0F, 6.0F, 5.0F, 3.0F, 0.0F);
/* 132 */     setRotateAngle(this.head1, 1.7976891F, -0.0F, 0.0F);
/* 133 */     this.lefteye = new ModelRenderer((Model)this, 40, 57);
/* 134 */     this.lefteye.func_78793_a(2.5F, -4.5F, -1.5F);
/* 135 */     this.lefteye.func_228301_a_(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 2.0F, 0.0F);
/* 136 */     setRotateAngle(this.lefteye, -0.08726646F, -0.0F, 0.0F);
/* 137 */     this.saddleback = new ModelRenderer((Model)this, 100, 71);
/* 138 */     this.saddleback.func_78793_a(0.4F, -1.3F, 15.6F);
/* 139 */     this.saddleback.func_228301_a_(-4.5F, -5.0F, -1.0F, 9.0F, 5.0F, 1.0F, 0.0F);
/* 140 */     setRotateAngle(this.saddleback, 0.017453292F, -0.0F, 0.0F);
/* 141 */     this.righteye = new ModelRenderer((Model)this, 40, 57);
/* 142 */     this.righteye.func_78793_a(-2.5F, -4.5F, -1.5F);
/* 143 */     this.righteye.func_228301_a_(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 2.0F, 0.0F);
/* 144 */     setRotateAngle(this.righteye, -0.08726646F, -0.0F, 0.0F);
/* 145 */     this.neck1 = new ModelRenderer((Model)this, 78, 14);
/* 146 */     this.neck1.func_78793_a(0.0F, 16.0F, -13.8F);
/* 147 */     this.neck1.func_228301_a_(-5.5F, -4.5F, 0.0F, 11.0F, 9.0F, 6.0F, 0.0F);
/* 148 */     setRotateAngle(this.neck1, -0.34906584F, -0.0F, 0.0F);
/* 149 */     this.saddleside1 = new ModelRenderer((Model)this, 0, 64);
/* 150 */     this.saddleside1.func_78793_a(-5.0F, -0.9F, -0.7F);
/* 151 */     this.saddleside1.func_228301_a_(0.0F, -5.0F, -5.5F, 1.0F, 5.0F, 11.0F, 0.0F);
/* 152 */     setRotateAngle(this.saddleside1, 0.05235988F, -0.05235988F, -0.13962634F);
/* 153 */     this.tail3 = new ModelRenderer((Model)this, 47, 28);
/* 154 */     this.tail3.func_78793_a(0.0F, 0.0F, 4.5F);
/* 155 */     this.tail3.func_228301_a_(-2.5F, -2.5F, 0.0F, 5.0F, 5.0F, 3.0F, 0.0F);
/* 156 */     setRotateAngle(this.tail3, -0.06981317F, -0.0F, 0.0F);
/* 157 */     this.belt1 = new ModelRenderer((Model)this, 152, 64);
/* 158 */     this.belt1.func_78793_a(0.0F, 12.8F, 6.9F);
/* 159 */     this.belt1.func_228301_a_(-6.5F, 0.0F, -1.0F, 13.0F, 11.0F, 1.0F, 0.0F);
/* 160 */     setRotateAngle(this.belt1, -0.08726646F, -0.0F, 0.0F);
/* 161 */     this.head3 = new ModelRenderer((Model)this, 19, 43);
/* 162 */     this.head3.func_78793_a(0.0F, -2.5F, -2.5F);
/* 163 */     this.head3.func_228301_a_(-2.5F, -2.0F, -4.0F, 5.0F, 4.0F, 4.0F, 0.0F);
/* 164 */     setRotateAngle(this.head3, 0.13962634F, -0.0F, 0.0F);
/* 165 */     this.tail5 = new ModelRenderer((Model)this, 47, 44);
/* 166 */     this.tail5.func_78793_a(0.0F, 0.0F, 3.0F);
/* 167 */     this.tail5.func_228301_a_(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 3.0F, 0.0F);
/* 168 */     setRotateAngle(this.tail5, -0.08726646F, -0.0F, 0.0F);
/* 169 */     this.belt2 = new ModelRenderer((Model)this, 152, 77);
/* 170 */     this.belt2.func_78793_a(0.0F, 12.3F, -4.1F);
/* 171 */     this.belt2.func_228301_a_(-6.5F, 0.0F, -1.0F, 13.0F, 12.0F, 1.0F, 0.0F);
/* 172 */     setRotateAngle(this.belt2, -0.08726646F, -0.0F, 0.0F);
/* 173 */     this.saddlefront = new ModelRenderer((Model)this, 100, 64);
/* 174 */     this.saddlefront.func_78793_a(0.0F, -0.7F, -5.0F);
/* 175 */     this.saddlefront.func_228301_a_(-5.0F, -5.0F, -1.0F, 10.0F, 5.0F, 1.0F, 0.0F);
/* 176 */     setRotateAngle(this.saddlefront, 0.08726646F, -0.0F, 0.0F);
/* 177 */     this.leftfin1 = new ModelRenderer((Model)this, 99, 41);
/* 178 */     this.leftfin1.func_78793_a(3.9F, 20.3F, -6.0F);
/* 179 */     this.leftfin1.func_228301_a_(0.0F, -0.5F, -6.0F, 8.0F, 1.0F, 6.0F, 0.0F);
/* 180 */     setRotateAngle(this.leftfin1, -0.19221127F, -0.6485391F, 0.4435539F);
/* 181 */     this.mane = new ModelRenderer((Model)this, 67, 29);
/* 182 */     this.mane.func_78793_a(0.0F, -7.5F, 1.0F);
/* 183 */     this.mane.func_228301_a_(0.0F, 0.0F, 0.0F, 0.0F, 10.0F, 4.0F, 0.0F);
/* 184 */     setRotateAngle(this.mane, -0.17453292F, -0.0F, 0.0F);
/* 185 */     this.neck4 = new ModelRenderer((Model)this, 78, 41);
/* 186 */     this.neck4.func_78793_a(0.0F, -0.8F, -3.0F);
/* 187 */     this.neck4.func_228301_a_(-3.0F, -2.5F, 0.0F, 6.0F, 5.0F, 4.0F, 0.0F);
/* 188 */     setRotateAngle(this.neck4, -0.20943952F, -0.0F, 0.0F);
/* 189 */     this.tail1 = new ModelRenderer((Model)this, 47, 0);
/* 190 */     this.tail1.func_78793_a(0.0F, 18.0F, 9.5F);
/* 191 */     this.tail1.func_228301_a_(-5.0F, -4.5F, 0.0F, 10.0F, 9.0F, 5.0F, 0.0F);
/* 192 */     setRotateAngle(this.tail1, 0.08726646F, -0.0F, 0.0F);
/* 193 */     this.leftgill = new ModelRenderer((Model)this, 0, 61);
/* 194 */     this.leftgill.func_78793_a(2.2F, -1.0F, 0.0F);
/* 195 */     this.leftgill.func_228301_a_(0.0F, 0.0F, -2.0F, 3.0F, 0.0F, 2.0F, 0.0F);
/* 196 */     setRotateAngle(this.leftgill, -0.17732546F, -0.3064798F, 0.93322754F);
/* 197 */     this.saddle.func_78792_a(this.saddleside2);
/* 198 */     this.tail1.func_78792_a(this.tail2);
/* 199 */     this.saddle.func_78792_a(this.saddleside3);
/* 200 */     this.neck2.func_78792_a(this.neck3);
/* 201 */     this.head2.func_78792_a(this.rightgill);
/* 202 */     this.tail5.func_78792_a(this.tail6);
/* 203 */     this.tail3.func_78792_a(this.tail4);
/* 204 */     this.neck1.func_78792_a(this.neck2);
/* 205 */     this.head1.func_78792_a(this.head2);
/* 206 */     this.head3.func_78792_a(this.mout);
/* 207 */     this.saddle.func_78792_a(this.saddlemiddle);
/* 208 */     this.saddle.func_78792_a(this.saddleside4);
/* 209 */     this.neck4.func_78792_a(this.head1);
/* 210 */     this.head2.func_78792_a(this.lefteye);
/* 211 */     this.saddle.func_78792_a(this.saddleback);
/* 212 */     this.head2.func_78792_a(this.righteye);
/* 213 */     this.saddle.func_78792_a(this.saddleside1);
/* 214 */     this.tail2.func_78792_a(this.tail3);
/* 215 */     this.head2.func_78792_a(this.head3);
/* 216 */     this.tail4.func_78792_a(this.tail5);
/* 217 */     this.saddle.func_78792_a(this.saddlefront);
/* 218 */     this.head1.func_78792_a(this.mane);
/* 219 */     this.neck3.func_78792_a(this.neck4);
/* 220 */     this.head2.func_78792_a(this.leftgill);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 226 */     if (this.field_217114_e) {
/* 227 */       matrixStack.func_227862_a_(0.5F, 0.5F, 0.5F);
/* 228 */       matrixStack.func_227861_a_(0.0D, 1.5D, 0.0D);
/*     */     } 
/*     */     
/* 231 */     this.body1.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 232 */     this.rightfin1.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 233 */     this.leftfin1.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 234 */     this.tail1.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 235 */     this.neck1.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 236 */     this.leftfin2.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 237 */     this.rightfin2.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 238 */     this.body2.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderSaddle(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 243 */     this.saddle.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 244 */     this.belt1.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 245 */     this.belt2.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 251 */     this.tail1.field_78796_g = 0.1F * (-0.2F + MathHelper.func_76134_b(ageInTicks * 0.15F));
/* 252 */     this.tail2.field_78796_g = 0.2F * (-0.2F + MathHelper.func_76134_b(ageInTicks * 0.15F));
/* 253 */     this.tail3.field_78796_g = 0.1F * (-0.2F + MathHelper.func_76134_b(ageInTicks * 0.15F));
/* 254 */     this.tail4.field_78796_g = 0.2F * (-0.2F + MathHelper.func_76134_b(ageInTicks * 0.15F));
/* 255 */     this.tail5.field_78796_g = 0.1F * (-0.2F + MathHelper.func_76134_b(ageInTicks * 0.15F));
/*     */     
/* 257 */     this.leftfin1.field_78796_g = 0.2F * -MathHelper.func_76134_b(ageInTicks * 0.15F);
/* 258 */     this.leftfin1.field_78795_f = 0.2F * -MathHelper.func_76134_b(ageInTicks * 0.15F);
/* 259 */     this.leftfin2.field_78796_g = 0.2F * MathHelper.func_76134_b(ageInTicks * 0.15F);
/* 260 */     this.leftfin2.field_78795_f = 0.2F * MathHelper.func_76134_b(ageInTicks * 0.15F);
/*     */     
/* 262 */     this.rightfin1.field_78796_g = 0.2F * -MathHelper.func_76134_b(ageInTicks * 0.15F);
/* 263 */     this.rightfin1.field_78795_f = 0.2F * -MathHelper.func_76134_b(ageInTicks * 0.15F);
/* 264 */     this.rightfin2.field_78796_g = 0.2F * MathHelper.func_76134_b(ageInTicks * 0.15F);
/* 265 */     this.rightfin2.field_78795_f = 0.2F * MathHelper.func_76134_b(ageInTicks * 0.15F);
/*     */ 
/*     */     
/* 268 */     if (ageInTicks % 300.0F > 0.0F && ageInTicks % 300.0F < 50.0F) {
/* 269 */       this.neck4.field_78808_h = 0.4F * MathHelper.func_76134_b(ageInTicks * 0.25F);
/*     */     } else {
/* 271 */       this.neck4.field_78808_h = (float)Math.toDegrees(0.0D);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 276 */     modelRenderer.field_78795_f = x;
/* 277 */     modelRenderer.field_78796_g = y;
/* 278 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\entities\mobs\animals\YagaraBullModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */