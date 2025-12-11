/*     */ package xyz.pixelatedw.mineminenomi.models.entities.mobs.animals;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SeaCowModel<E extends LivingEntity>
/*     */   extends EntityModel<E>
/*     */ {
/*     */   public final ModelRenderer head;
/*     */   private final ModelRenderer mouth;
/*     */   private final ModelRenderer noseRing;
/*     */   private final ModelRenderer leftHorn;
/*     */   private final ModelRenderer leftHorn2;
/*     */   private final ModelRenderer leftHorn3;
/*     */   private final ModelRenderer rightHorn;
/*     */   private final ModelRenderer rightHorn2;
/*     */   private final ModelRenderer rightHorn3;
/*     */   private final ModelRenderer leftEar;
/*     */   private final ModelRenderer rightEar;
/*     */   public final ModelRenderer rightFin;
/*     */   private final ModelRenderer rightFin_r1;
/*     */   
/*     */   public SeaCowModel() {
/*  41 */     this.field_78090_t = 128;
/*  42 */     this.field_78089_u = 128;
/*     */     
/*  44 */     this.head = new ModelRenderer((Model)this);
/*  45 */     this.head.func_78793_a(0.0F, 7.75F, -18.0F);
/*  46 */     this.head.func_78784_a(76, 82).func_228303_a_(-6.5F, -7.75F, -12.0F, 13.0F, 13.0F, 13.0F, 0.0F, false);
/*     */     
/*  48 */     this.mouth = new ModelRenderer((Model)this);
/*  49 */     this.mouth.func_78793_a(0.0F, 0.25F, -11.5F);
/*  50 */     this.head.func_78792_a(this.mouth);
/*  51 */     setRotationAngle(this.mouth, 0.0873F, 0.0F, 0.0F);
/*  52 */     this.mouth.func_78784_a(98, 110).func_228303_a_(-3.5F, -2.1781F, -4.5795F, 7.0F, 6.0F, 5.0F, 0.0F, false);
/*     */     
/*  54 */     this.noseRing = new ModelRenderer((Model)this);
/*  55 */     this.noseRing.func_78793_a(0.0F, -0.1663F, -4.299F);
/*  56 */     this.mouth.func_78792_a(this.noseRing);
/*  57 */     setRotationAngle(this.noseRing, -0.1222F, 0.0F, 0.0F);
/*  58 */     this.noseRing.func_78784_a(99, 123).func_228303_a_(-2.5F, -0.8423F, -0.4163F, 5.0F, 5.0F, 0.0F, 0.0F, false);
/*     */     
/*  60 */     this.leftHorn = new ModelRenderer((Model)this);
/*  61 */     this.leftHorn.func_78793_a(6.1004F, -10.7588F, 7.2694F);
/*  62 */     this.head.func_78792_a(this.leftHorn);
/*  63 */     setRotationAngle(this.leftHorn, -0.1745F, 0.0F, 0.6981F);
/*  64 */     this.leftHorn.func_78784_a(81, 118).func_228303_a_(1.1337F, 1.3166F, -12.316F, 3.0F, 5.0F, 5.0F, 0.4F, true);
/*     */     
/*  66 */     this.leftHorn2 = new ModelRenderer((Model)this);
/*  67 */     this.leftHorn2.func_78793_a(-0.3542F, -4.5847F, -0.4754F);
/*  68 */     this.leftHorn.func_78792_a(this.leftHorn2);
/*  69 */     setRotationAngle(this.leftHorn2, 0.0F, 0.0F, -0.5236F);
/*  70 */     this.leftHorn2.func_78784_a(63, 119).func_228303_a_(-1.6504F, 3.1528F, -11.816F, 3.0F, 4.0F, 5.0F, 0.0F, true);
/*     */     
/*  72 */     this.leftHorn3 = new ModelRenderer((Model)this);
/*  73 */     this.leftHorn3.func_78793_a(-0.4638F, -4.1606F, 0.0033F);
/*  74 */     this.leftHorn2.func_78792_a(this.leftHorn3);
/*  75 */     setRotationAngle(this.leftHorn3, 0.0F, 0.0F, -0.3491F);
/*  76 */     this.leftHorn3.func_78784_a(51, 121).func_228303_a_(-2.9892F, 3.5255F, -10.816F, 2.0F, 4.0F, 3.0F, 0.0F, true);
/*     */     
/*  78 */     this.rightHorn = new ModelRenderer((Model)this);
/*  79 */     this.rightHorn.func_78793_a(-6.1004F, -10.7588F, 7.2694F);
/*  80 */     this.head.func_78792_a(this.rightHorn);
/*  81 */     setRotationAngle(this.rightHorn, -0.1745F, 0.0F, -0.6981F);
/*  82 */     this.rightHorn.func_78784_a(81, 118).func_228303_a_(-4.1337F, 1.3166F, -12.316F, 3.0F, 5.0F, 5.0F, 0.4F, false);
/*     */     
/*  84 */     this.rightHorn2 = new ModelRenderer((Model)this);
/*  85 */     this.rightHorn2.func_78793_a(0.3542F, -4.5847F, -0.4754F);
/*  86 */     this.rightHorn.func_78792_a(this.rightHorn2);
/*  87 */     setRotationAngle(this.rightHorn2, 0.0F, 0.0F, 0.5236F);
/*  88 */     this.rightHorn2.func_78784_a(63, 119).func_228303_a_(-1.3496F, 3.1528F, -11.816F, 3.0F, 4.0F, 5.0F, 0.0F, false);
/*     */     
/*  90 */     this.rightHorn3 = new ModelRenderer((Model)this);
/*  91 */     this.rightHorn3.func_78793_a(0.4638F, -4.1606F, 0.0033F);
/*  92 */     this.rightHorn2.func_78792_a(this.rightHorn3);
/*  93 */     setRotationAngle(this.rightHorn3, 0.0F, 0.0F, 0.3491F);
/*  94 */     this.rightHorn3.func_78784_a(51, 121).func_228303_a_(0.9892F, 3.5255F, -10.816F, 2.0F, 4.0F, 3.0F, 0.0F, false);
/*     */     
/*  96 */     this.leftEar = new ModelRenderer((Model)this);
/*  97 */     this.leftEar.func_78793_a(6.5F, -6.125F, -8.3125F);
/*  98 */     this.head.func_78792_a(this.leftEar);
/*  99 */     setRotationAngle(this.leftEar, -0.1129F, 0.1334F, 1.1618F);
/* 100 */     this.leftEar.func_78784_a(110, 122).func_228303_a_(0.0F, -1.0F, -2.0F, 5.0F, 2.0F, 4.0F, 0.0F, true);
/*     */     
/* 102 */     this.rightEar = new ModelRenderer((Model)this);
/* 103 */     this.rightEar.func_78793_a(-6.5F, -6.125F, -8.3125F);
/* 104 */     this.head.func_78792_a(this.rightEar);
/* 105 */     setRotationAngle(this.rightEar, -0.1129F, -0.1334F, -1.1618F);
/* 106 */     this.rightEar.func_78784_a(110, 122).func_228303_a_(-5.0F, -1.0F, -2.0F, 5.0F, 2.0F, 4.0F, 0.0F, false);
/*     */     
/* 108 */     this.rightFin = new ModelRenderer((Model)this);
/* 109 */     this.rightFin.func_78793_a(-7.0F, 8.5F, -4.0F);
/* 110 */     setRotationAngle(this.rightFin, 0.0F, 0.0F, 0.1745F);
/*     */     
/* 112 */     this.rightFin_r1 = new ModelRenderer((Model)this);
/* 113 */     this.rightFin_r1.func_78793_a(-0.9285F, 7.2187F, -0.9823F);
/* 114 */     this.rightFin.func_78792_a(this.rightFin_r1);
/* 115 */     setRotationAngle(this.rightFin_r1, 0.0F, 0.0F, 0.0F);
/* 116 */     this.rightFin_r1.func_78784_a(0, 83).func_228303_a_(-2.0F, -8.0F, -5.0F, 4.0F, 16.0F, 10.0F, 0.0F, false);
/*     */     
/* 118 */     this.rightFin2 = new ModelRenderer((Model)this);
/* 119 */     this.rightFin2.func_78793_a(-0.4321F, 13.9255F, -0.9823F);
/* 120 */     this.rightFin.func_78792_a(this.rightFin2);
/* 121 */     setRotationAngle(this.rightFin2, 0.0F, 0.0F, 1.2217F);
/*     */     
/* 123 */     this.rightFin2_r1 = new ModelRenderer((Model)this);
/* 124 */     this.rightFin2_r1.func_78793_a(0.1636F, 3.1611F, 0.0F);
/* 125 */     this.rightFin2.func_78792_a(this.rightFin2_r1);
/* 126 */     setRotationAngle(this.rightFin2_r1, 0.0F, 0.0F, 0.0F);
/* 127 */     this.rightFin2_r1.func_78784_a(0, 110).func_228303_a_(-1.5F, -4.0F, -4.5F, 3.0F, 8.0F, 9.0F, 0.0F, false);
/*     */     
/* 129 */     this.leftFin = new ModelRenderer((Model)this);
/* 130 */     this.leftFin.func_78793_a(7.0F, 8.25F, -4.0F);
/* 131 */     setRotationAngle(this.leftFin, 0.0F, 0.0F, -0.1745F);
/*     */ 
/*     */     
/* 134 */     this.leftFin_r1 = new ModelRenderer((Model)this);
/* 135 */     this.leftFin_r1.func_78793_a(1.0411F, 7.566F, -0.9823F);
/* 136 */     this.leftFin.func_78792_a(this.leftFin_r1);
/* 137 */     setRotationAngle(this.leftFin_r1, 0.0F, 0.0F, 0.0F);
/* 138 */     this.leftFin_r1.func_78784_a(0, 83).func_228303_a_(-2.0F, -8.0F, -5.0F, 4.0F, 16.0F, 10.0F, 0.0F, true);
/*     */     
/* 140 */     this.leftFin2 = new ModelRenderer((Model)this);
/* 141 */     this.leftFin2.func_78793_a(0.5375F, 14.0228F, -0.9823F);
/* 142 */     this.leftFin.func_78792_a(this.leftFin2);
/* 143 */     setRotationAngle(this.leftFin2, 0.0F, 0.0F, -1.1781F);
/*     */ 
/*     */     
/* 146 */     this.leftFin2_r1 = new ModelRenderer((Model)this);
/* 147 */     this.leftFin2_r1.func_78793_a(-0.3364F, 3.1611F, 0.0F);
/* 148 */     this.leftFin2.func_78792_a(this.leftFin2_r1);
/* 149 */     setRotationAngle(this.leftFin2_r1, 0.0F, 0.0F, 0.0F);
/* 150 */     this.leftFin2_r1.func_78784_a(0, 110).func_228303_a_(-1.5F, -4.0F, -4.5F, 3.0F, 8.0F, 9.0F, 0.0F, true);
/*     */ 
/*     */     
/* 153 */     this.body = new ModelRenderer((Model)this);
/* 154 */     this.body.func_78793_a(0.0F, 8.3125F, -10.625F);
/* 155 */     this.body.func_78784_a(0, 0).func_228303_a_(-5.5F, -4.268F, -7.9762F, 11.0F, 13.0F, 9.0F, 0.0F, false);
/* 156 */     this.body.func_78784_a(110, -9).func_228303_a_(0.0F, -8.268F, -7.9762F, 0.0F, 5.0F, 9.0F, 0.0F, false);
/*     */     
/* 158 */     this.body2 = new ModelRenderer((Model)this);
/* 159 */     this.body2.func_78793_a(0.0F, 1.3961F, -0.5129F);
/* 160 */     this.body.func_78792_a(this.body2);
/* 161 */     this.body2.func_78784_a(0, 24).func_228303_a_(-6.5F, -3.4375F, -2.3125F, 13.0F, 15.0F, 19.0F, 0.0F, false);
/* 162 */     this.body2.func_78784_a(93, -10).func_228303_a_(0.0F, -7.4375F, 0.6875F, 0.0F, 5.0F, 16.0F, 0.0F, false);
/*     */     
/* 164 */     this.body3 = new ModelRenderer((Model)this);
/* 165 */     this.body3.func_78793_a(0.0F, 1.8493F, 11.443F);
/* 166 */     this.body2.func_78792_a(this.body3);
/* 167 */     this.body3.func_78784_a(20, 60).func_228303_a_(-7.5F, -2.3125F, -0.125F, 15.0F, 14.0F, 13.0F, 0.0F, false);
/* 168 */     this.body3.func_78784_a(107, 4).func_228303_a_(0.0F, -7.3125F, 4.875F, 0.0F, 5.0F, 8.0F, 0.0F, false);
/*     */     
/* 170 */     this.body4 = new ModelRenderer((Model)this);
/* 171 */     this.body4.func_78793_a(0.0F, 3.4819F, 11.5169F);
/* 172 */     this.body3.func_78792_a(this.body4);
/* 173 */     this.body4.func_78784_a(42, 0).func_228303_a_(-7.0F, -3.3125F, -0.4375F, 14.0F, 12.0F, 8.0F, 0.0F, false);
/* 174 */     this.body4.func_78784_a(113, 11).func_228303_a_(0.0F, -9.0625F, 0.5625F, 0.0F, 7.0F, 7.0F, 0.0F, false);
/*     */     
/* 176 */     this.body5 = new ModelRenderer((Model)this);
/* 177 */     this.body5.func_78793_a(0.0F, 3.5852F, 6.553F);
/* 178 */     this.body4.func_78792_a(this.body5);
/* 179 */     this.body5.func_78784_a(46, 22).func_228303_a_(-6.0F, -4.0625F, -1.1875F, 12.0F, 9.0F, 11.0F, 0.0F, false);
/* 180 */     this.body5.func_78784_a(108, 17).func_228303_a_(0.0F, -11.0625F, 0.8125F, 0.0F, 7.0F, 9.0F, 0.0F, false);
/*     */     
/* 182 */     this.body6 = new ModelRenderer((Model)this);
/* 183 */     this.body6.func_78793_a(0.0F, 0.875F, 8.8125F);
/* 184 */     this.body5.func_78792_a(this.body6);
/* 185 */     setRotationAngle(this.body6, 0.3054F, 0.0F, 0.0F);
/* 186 */     this.body6.func_78784_a(65, 44).func_228303_a_(-5.0F, -3.1993F, -1.0463F, 10.0F, 7.0F, 12.0F, 0.0F, false);
/* 187 */     this.body6.func_78784_a(106, 25).func_228303_a_(0.0F, -9.1993F, 1.9537F, 0.0F, 7.0F, 9.0F, 0.0F, false);
/*     */     
/* 189 */     this.tail = new ModelRenderer((Model)this);
/* 190 */     this.tail.func_78793_a(0.0F, 0.0426F, 9.893F);
/* 191 */     this.body6.func_78792_a(this.tail);
/* 192 */     setRotationAngle(this.tail, 0.6109F, 0.0F, 0.0F);
/* 193 */     this.tail.func_78784_a(19, 100).func_228303_a_(-9.5F, -2.308F, -0.5636F, 19.0F, 4.0F, 11.0F, 0.0F, false);
/*     */   }
/*     */   public final ModelRenderer rightFin2; private final ModelRenderer rightFin2_r1; public final ModelRenderer leftFin; private final ModelRenderer leftFin_r1; public final ModelRenderer leftFin2; private final ModelRenderer leftFin2_r1; private final ModelRenderer body; private final ModelRenderer body2; private final ModelRenderer body3; private final ModelRenderer body4; private final ModelRenderer body5; private final ModelRenderer body6;
/*     */   private final ModelRenderer tail;
/*     */   
/*     */   public void setupAnim(E entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 199 */     this.head.field_78795_f = 0.0F;
/*     */     
/* 201 */     if (entity.func_70090_H()) {
/* 202 */       float backSwingSpeed = 0.4F;
/* 203 */       float backSwingAmount = 0.4F;
/* 204 */       float finSwingSpeed = 0.4F;
/* 205 */       float finSwingAmount = 0.9F;
/* 206 */       float finSwingSpeed2 = 0.4F;
/* 207 */       float finSwingAmount2 = 0.2F;
/*     */       
/* 209 */       this.leftFin2.field_78808_h = 0.0F;
/* 210 */       this.leftFin.field_78795_f = (float)Math.toRadians(45.0D);
/* 211 */       this.rightFin2.field_78808_h = 0.0F;
/* 212 */       this.rightFin.field_78795_f = (float)Math.toRadians(45.0D);
/*     */       
/* 214 */       this.body6.field_78795_f = 0.0F;
/* 215 */       this.tail.field_78795_f = (float)Math.toRadians(5.0D);
/*     */       
/* 217 */       this.body5.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.4F + 3.1415927F) * 0.4F * limbSwingAmount * 0.7F / 1.0F;
/* 218 */       this.body6.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.4F + 3.1415927F) * 0.4F * limbSwingAmount * 0.7F / 1.0F;
/* 219 */       this.tail.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.4F + 3.1415927F) * 0.4F * limbSwingAmount * 0.7F / 1.0F;
/*     */       
/* 221 */       this.rightFin.field_78808_h = (float)(Math.toRadians(20.0D) + (MathHelper.func_76134_b(limbSwing * 0.4F + 3.1415927F) * 0.9F * limbSwingAmount * 0.7F / 1.0F));
/* 222 */       this.rightFin.field_78796_g = (float)(Math.toRadians(-20.0D) - (MathHelper.func_76134_b(limbSwing * 0.4F + 3.1415927F) * 0.2F * limbSwingAmount * 0.7F / 1.0F));
/* 223 */       this.leftFin.field_78808_h = (float)(Math.toRadians(-20.0D) - (MathHelper.func_76134_b(limbSwing * 0.4F + 3.1415927F) * 0.9F * limbSwingAmount * 0.7F / 1.0F));
/* 224 */       this.leftFin.field_78796_g = (float)(Math.toRadians(20.0D) + (MathHelper.func_76134_b(limbSwing * 0.4F + 3.1415927F) * 0.2F * limbSwingAmount * 0.7F / 1.0F));
/*     */     } else {
/*     */       
/* 227 */       float swingSpeed = 0.4F;
/* 228 */       float swingAmount = 0.9F;
/*     */       
/* 230 */       this.leftFin2.field_78808_h = (float)Math.toRadians(-67.5D);
/* 231 */       this.leftFin.field_78795_f = (float)Math.toRadians(0.0D);
/* 232 */       this.rightFin2.field_78808_h = (float)Math.toRadians(67.5D);
/* 233 */       this.rightFin.field_78795_f = (float)Math.toRadians(0.0D);
/*     */       
/* 235 */       this.body5.field_78795_f = 0.0F;
/* 236 */       this.body6.field_78795_f = (float)Math.toRadians(17.5D);
/* 237 */       this.tail.field_78795_f = (float)Math.toRadians(35.0D);
/*     */       
/* 239 */       this.rightFin.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.4F + 3.1415927F) * 0.9F * limbSwingAmount * 0.7F / 1.0F;
/* 240 */       this.rightFin.field_78796_g = 0.0F;
/* 241 */       this.rightFin.field_78808_h = (float)Math.toRadians(10.0D);
/* 242 */       this.leftFin.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.4F + 3.1415927F) * 0.9F * limbSwingAmount * 0.7F / 1.0F;
/* 243 */       this.leftFin.field_78796_g = 0.0F;
/* 244 */       this.leftFin.field_78808_h = (float)Math.toRadians(-10.0D);
/*     */     } 
/*     */     
/* 247 */     RendererHelper.animationAngles((LivingEntity)entity, this, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 252 */     if (this.field_217114_e) {
/* 253 */       matrixStack.func_227862_a_(0.5F, 0.5F, 0.5F);
/* 254 */       matrixStack.func_227861_a_(0.0D, 1.5D, 0.0D);
/*     */     } 
/*     */     
/* 257 */     this.noseRing.field_78806_j = false;
/*     */     
/* 259 */     this.head.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 260 */     this.rightFin.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 261 */     this.leftFin.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 262 */     this.body.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */   }
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 266 */     modelRenderer.field_78795_f = x;
/* 267 */     modelRenderer.field_78796_g = y;
/* 268 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\entities\mobs\animals\SeaCowModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */