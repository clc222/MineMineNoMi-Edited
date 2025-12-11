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
/*     */ public class AllosaurusHeavyPartialModel<T extends LivingEntity>
/*     */   extends MorphModel<T> implements IHasArm {
/*     */   private final ModelRenderer tail;
/*     */   private final ModelRenderer tail2;
/*     */   private final ModelRenderer tail3;
/*     */   private final ModelRenderer leftLeg;
/*     */   private final ModelRenderer leftLeg2;
/*     */   private final ModelRenderer leftFeet;
/*     */   private final ModelRenderer leftFeet2;
/*     */   private final ModelRenderer leftToe1;
/*     */   private final ModelRenderer leftToe2;
/*     */   private final ModelRenderer leftToe3;
/*     */   private final ModelRenderer leftToe4;
/*     */   private final ModelRenderer rightLeg;
/*     */   private final ModelRenderer rightLeg2;
/*     */   private final ModelRenderer rightFeet;
/*     */   private final ModelRenderer rightFeet2;
/*     */   private final ModelRenderer rightToe;
/*     */   private final ModelRenderer rightToe2;
/*     */   private final ModelRenderer rightToe3;
/*     */   private final ModelRenderer rightToe4;
/*     */   public final ModelRenderer bodyScales;
/*     */   public final ModelRenderer headScales;
/*     */   public final ModelRenderer leftArmScales;
/*     */   public final ModelRenderer rightArmScales;
/*     */   
/*     */   public AllosaurusHeavyPartialModel() {
/*  43 */     super(1.0F);
/*  44 */     this.field_78090_t = 128;
/*  45 */     this.field_78089_u = 128;
/*     */     
/*  47 */     this.tail = new ModelRenderer((Model)this);
/*  48 */     this.tail.func_78793_a(0.0F, 8.75F, 1.5F);
/*  49 */     setRotationAngle(this.tail, -0.1309F, 0.0F, 0.0F);
/*  50 */     this.tail.func_78784_a(25, 28).func_228303_a_(-3.0F, -5.7243F, -0.3916F, 6.0F, 6.0F, 10.0F, 0.0F, false);
/*     */     
/*  52 */     this.tail2 = new ModelRenderer((Model)this);
/*  53 */     this.tail2.func_78793_a(0.0F, 0.0F, 10.0F);
/*  54 */     this.tail.func_78792_a(this.tail2);
/*  55 */     setRotationAngle(this.tail2, 0.0873F, 0.0F, 0.0F);
/*  56 */     this.tail2.func_78784_a(26, 45).func_228303_a_(-2.5F, -5.2471F, -0.6308F, 5.0F, 5.0F, 10.0F, 0.0F, false);
/*     */     
/*  58 */     this.tail3 = new ModelRenderer((Model)this);
/*  59 */     this.tail3.func_78793_a(0.0F, 0.0F, 8.0F);
/*  60 */     this.tail2.func_78792_a(this.tail3);
/*  61 */     setRotationAngle(this.tail3, 0.0436F, 0.0F, 0.0F);
/*  62 */     this.tail3.func_78784_a(22, 61).func_228303_a_(-2.0F, -4.75F, 0.5F, 4.0F, 4.0F, 15.0F, 0.0F, false);
/*     */     
/*  64 */     this.leftLeg = new ModelRenderer((Model)this);
/*  65 */     this.leftLeg.func_78793_a(2.5F, 11.396F, 0.0604F);
/*  66 */     setRotationAngle(this.leftLeg, 0.2182F, 0.0F, 0.0F);
/*  67 */     this.leftLeg.func_78784_a(0, 34).func_228303_a_(-0.5F, -3.3918F, -2.0119F, 3.0F, 8.0F, 5.0F, 0.01F, false);
/*     */     
/*  69 */     this.leftLeg2 = new ModelRenderer((Model)this);
/*  70 */     this.leftLeg2.func_78793_a(0.0F, 3.8013F, -0.0043F);
/*  71 */     this.leftLeg.func_78792_a(this.leftLeg2);
/*  72 */     setRotationAngle(this.leftLeg2, -0.6981F, 0.0F, 0.0F);
/*  73 */     this.leftLeg2.func_78784_a(1, 49).func_228303_a_(-0.5F, -1.4808F, -1.3065F, 3.0F, 9.0F, 4.0F, 0.0F, false);
/*     */     
/*  75 */     this.leftFeet = new ModelRenderer((Model)this);
/*  76 */     this.leftFeet.func_78793_a(-14.0F, 5.7326F, 2.6722F);
/*  77 */     this.leftLeg2.func_78792_a(this.leftFeet);
/*  78 */     setRotationAngle(this.leftFeet, 0.48F, 0.0F, 0.0F);
/*  79 */     this.leftFeet.func_78784_a(0, 63).func_228303_a_(13.0F, -0.5F, -4.5F, 4.0F, 3.0F, 5.0F, 0.0F, false);
/*     */     
/*  81 */     this.leftFeet2 = new ModelRenderer((Model)this);
/*  82 */     this.leftFeet2.func_78793_a(0.0F, 0.5F, -3.0F);
/*  83 */     this.leftFeet.func_78792_a(this.leftFeet2);
/*  84 */     this.leftFeet2.func_78784_a(0, 72).func_228303_a_(12.5F, 0.0F, -2.5F, 5.0F, 2.0F, 5.0F, 0.01F, false);
/*     */     
/*  86 */     this.leftToe1 = new ModelRenderer((Model)this);
/*  87 */     this.leftToe1.func_78793_a(16.8372F, 1.3799F, -3.6384F);
/*  88 */     this.leftFeet2.func_78792_a(this.leftToe1);
/*  89 */     setRotationAngle(this.leftToe1, 0.0873F, -0.1745F, -0.0036F);
/*  90 */     this.leftToe1.func_78784_a(0, 80).func_228303_a_(-0.1632F, -0.957F, -1.5095F, 1.0F, 2.0F, 3.0F, 0.0F, false);
/*     */     
/*  92 */     this.leftToe2 = new ModelRenderer((Model)this);
/*  93 */     this.leftToe2.func_78793_a(15.0F, 1.5361F, -3.8781F);
/*  94 */     this.leftFeet2.func_78792_a(this.leftToe2);
/*  95 */     setRotationAngle(this.leftToe2, 0.0873F, 0.0F, 0.0F);
/*  96 */     this.leftToe2.func_78784_a(0, 80).func_228303_a_(-0.5F, -1.2064F, -1.5019F, 1.0F, 2.0F, 3.0F, 0.0F, false);
/*     */     
/*  98 */     this.leftToe3 = new ModelRenderer((Model)this);
/*  99 */     this.leftToe3.func_78793_a(12.3246F, 1.2317F, -3.7439F);
/* 100 */     this.leftFeet2.func_78792_a(this.leftToe3);
/* 101 */     setRotationAngle(this.leftToe3, 0.0873F, 0.1745F, 0.0F);
/* 102 */     this.leftToe3.func_78784_a(0, 80).func_228303_a_(-0.0868F, -0.957F, -1.5095F, 1.0F, 2.0F, 3.0F, 0.0F, false);
/*     */     
/* 104 */     this.leftToe4 = new ModelRenderer((Model)this);
/* 105 */     this.leftToe4.func_78793_a(14.5F, -1.038F, 3.3855F);
/* 106 */     this.leftFeet2.func_78792_a(this.leftToe4);
/* 107 */     setRotationAngle(this.leftToe4, -0.5672F, 0.0F, 0.0F);
/* 108 */     this.leftToe4.func_78784_a(9, 81).func_228303_a_(0.0F, -0.2686F, -1.0783F, 1.0F, 1.0F, 3.0F, 0.0F, false);
/*     */     
/* 110 */     this.rightLeg = new ModelRenderer((Model)this);
/* 111 */     this.rightLeg.func_78793_a(-2.5F, 11.396F, 0.0604F);
/* 112 */     setRotationAngle(this.rightLeg, 0.2182F, 0.0F, 0.0F);
/* 113 */     this.rightLeg.func_78784_a(0, 34).func_228303_a_(-2.5F, -3.3918F, -2.0119F, 3.0F, 8.0F, 5.0F, 0.01F, false);
/*     */     
/* 115 */     this.rightLeg2 = new ModelRenderer((Model)this);
/* 116 */     this.rightLeg2.func_78793_a(-2.0F, 3.8013F, -0.0043F);
/* 117 */     this.rightLeg.func_78792_a(this.rightLeg2);
/* 118 */     setRotationAngle(this.rightLeg2, -0.6981F, 0.0F, 0.0F);
/* 119 */     this.rightLeg2.func_78784_a(1, 49).func_228303_a_(-0.5F, -1.4808F, -1.3065F, 3.0F, 9.0F, 4.0F, 0.0F, false);
/*     */     
/* 121 */     this.rightFeet = new ModelRenderer((Model)this);
/* 122 */     this.rightFeet.func_78793_a(-14.0F, 5.7326F, 2.6722F);
/* 123 */     this.rightLeg2.func_78792_a(this.rightFeet);
/* 124 */     setRotationAngle(this.rightFeet, 0.48F, 0.0F, 0.0F);
/* 125 */     this.rightFeet.func_78784_a(0, 63).func_228303_a_(13.0F, -0.5F, -4.5F, 4.0F, 3.0F, 5.0F, 0.0F, false);
/*     */     
/* 127 */     this.rightFeet2 = new ModelRenderer((Model)this);
/* 128 */     this.rightFeet2.func_78793_a(0.0F, 0.5F, -3.0F);
/* 129 */     this.rightFeet.func_78792_a(this.rightFeet2);
/* 130 */     this.rightFeet2.func_78784_a(0, 72).func_228303_a_(12.5F, 0.0F, -2.5F, 5.0F, 2.0F, 5.0F, 0.01F, false);
/*     */     
/* 132 */     this.rightToe = new ModelRenderer((Model)this);
/* 133 */     this.rightToe.func_78793_a(16.8372F, 1.3799F, -3.6384F);
/* 134 */     this.rightFeet2.func_78792_a(this.rightToe);
/* 135 */     setRotationAngle(this.rightToe, 0.0873F, -0.1745F, -0.0036F);
/* 136 */     this.rightToe.func_78784_a(0, 80).func_228303_a_(-0.1632F, -0.957F, -1.5095F, 1.0F, 2.0F, 3.0F, 0.0F, false);
/*     */     
/* 138 */     this.rightToe2 = new ModelRenderer((Model)this);
/* 139 */     this.rightToe2.func_78793_a(15.0F, 1.5361F, -3.8781F);
/* 140 */     this.rightFeet2.func_78792_a(this.rightToe2);
/* 141 */     setRotationAngle(this.rightToe2, 0.0873F, 0.0F, 0.0F);
/* 142 */     this.rightToe2.func_78784_a(0, 80).func_228303_a_(-0.5F, -1.2064F, -1.5019F, 1.0F, 2.0F, 3.0F, 0.0F, false);
/*     */     
/* 144 */     this.rightToe3 = new ModelRenderer((Model)this);
/* 145 */     this.rightToe3.func_78793_a(12.3246F, 1.2317F, -3.7439F);
/* 146 */     this.rightFeet2.func_78792_a(this.rightToe3);
/* 147 */     setRotationAngle(this.rightToe3, 0.0873F, 0.1745F, 0.0F);
/* 148 */     this.rightToe3.func_78784_a(0, 80).func_228303_a_(-0.0868F, -0.957F, -1.5095F, 1.0F, 2.0F, 3.0F, 0.0F, false);
/*     */     
/* 150 */     this.rightToe4 = new ModelRenderer((Model)this);
/* 151 */     this.rightToe4.func_78793_a(14.5F, -1.038F, 3.3855F);
/* 152 */     this.rightFeet2.func_78792_a(this.rightToe4);
/* 153 */     setRotationAngle(this.rightToe4, -0.5672F, 0.0F, 0.0F);
/* 154 */     this.rightToe4.func_78784_a(9, 81).func_228303_a_(0.0F, -0.2686F, -1.0783F, 1.0F, 1.0F, 3.0F, 0.0F, false);
/*     */     
/* 156 */     this.bodyScales = new ModelRenderer((Model)this);
/* 157 */     this.bodyScales.func_78793_a(0.0F, -3.0F, 0.0F);
/* 158 */     this.bodyScales.func_78784_a(0, 17).func_228303_a_(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.01F, false);
/*     */     
/* 160 */     this.headScales = new ModelRenderer((Model)this);
/* 161 */     this.headScales.func_78793_a(0.0F, -3.0F, 0.0F);
/* 162 */     this.headScales.func_78784_a(0, 0).func_228303_a_(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.01F, false);
/*     */     
/* 164 */     this.leftArmScales = new ModelRenderer((Model)this);
/* 165 */     this.leftArmScales.func_78793_a(5.0F, -1.0F, 0.0F);
/* 166 */     this.leftArmScales.func_78784_a(34, 4).func_228303_a_(-1.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.01F, false);
/*     */     
/* 168 */     this.rightArmScales = new ModelRenderer((Model)this);
/* 169 */     this.rightArmScales.func_78793_a(-5.0F, -1.0F, 0.0F);
/* 170 */     this.rightArmScales.func_78784_a(34, 4).func_228303_a_(-3.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.01F, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 176 */     this.tail.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 177 */     this.leftLeg.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 178 */     this.rightLeg.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 179 */     matrixStack.func_227860_a_();
/* 180 */     float scale = 1.1F;
/* 181 */     matrixStack.func_227862_a_(scale, scale, scale);
/* 182 */     matrixStack.func_227861_a_(0.0D, -0.18D, 0.0D);
/* 183 */     this.bodyScales.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 184 */     this.headScales.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 185 */     this.leftArmScales.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 186 */     this.rightArmScales.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 187 */     matrixStack.func_227865_b_();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 194 */     float limbSpeed = 0.5F;
/* 195 */     if (entity.func_70051_ag())
/* 196 */       limbSpeed = 0.7F; 
/* 197 */     this.rightLeg.field_78795_f = 0.2F + MathHelper.func_76134_b(limbSwing * limbSpeed) * 0.5F * limbSwingAmount;
/* 198 */     this.rightLeg2.field_78795_f = -0.7F - MathHelper.func_76126_a(limbSwing * limbSpeed) * 0.5F * limbSwingAmount;
/* 199 */     this.leftLeg.field_78795_f = 0.2F + MathHelper.func_76134_b(limbSwing * limbSpeed + 3.1415927F) * 0.5F * limbSwingAmount;
/* 200 */     this.leftLeg2.field_78795_f = -0.7F - MathHelper.func_76126_a(limbSwing * limbSpeed + 3.1415927F) * 0.5F * limbSwingAmount;
/*     */     
/* 202 */     this.tail.field_78796_g = (float)(this.tail.field_78796_g + Math.sin(ageInTicks * 0.01D) / 20.0D);
/* 203 */     this.tail.field_78795_f = (float)(this.tail.field_78795_f + Math.sin(ageInTicks * 0.005D) / 10.0D);
/* 204 */     this.tail2.field_78796_g = (float)(this.tail2.field_78796_g + Math.sin(ageInTicks * 0.09D) / 20.0D);
/* 205 */     this.tail2.field_78795_f = (float)(this.tail2.field_78795_f + Math.sin(ageInTicks * 0.01D) / 10.0D);
/* 206 */     this.tail3.field_78796_g = (float)(this.tail3.field_78796_g + Math.sin(ageInTicks * 0.1D) / 20.0D);
/* 207 */     this.tail3.field_78795_f = (float)(this.tail3.field_78795_f + Math.sin(ageInTicks * 0.05D) / 10.0D);
/*     */ 
/*     */     
/* 210 */     if (entity.func_226271_bk_()) {
/*     */       
/* 212 */       this.rightLeg.field_78798_e = 2.0F;
/* 213 */       this.rightLeg.field_78797_d = 9.5F;
/* 214 */       this.leftLeg.field_78798_e = 2.0F;
/* 215 */       this.leftLeg.field_78797_d = 9.5F;
/* 216 */       this.tail.field_78798_e = 3.9F;
/* 217 */       this.tail.field_78797_d = 11.0F;
/*     */     } 
/*     */ 
/*     */     
/* 221 */     this.field_217112_c = ((LivingEntity)entity).field_70733_aJ;
/* 222 */     boolean isBlackLeg = EntityStatsCapability.get((LivingEntity)entity).isBlackLeg();
/* 223 */     if (this.field_217112_c > 0.0F)
/*     */     {
/* 225 */       if (isBlackLeg) {
/*     */         
/* 227 */         this.field_78115_e.field_78796_g = MathHelper.func_76126_a(MathHelper.func_76129_c(this.field_217112_c) * 6.2831855F) * 0.2F;
/* 228 */         this.rightLeg.field_78796_g += this.field_78115_e.field_78796_g;
/* 229 */         this.leftLeg.field_78796_g += this.field_78115_e.field_78796_g;
/* 230 */         float f1 = 1.0F - this.field_217112_c;
/* 231 */         f1 *= f1;
/* 232 */         f1 *= f1;
/* 233 */         f1 = 1.0F - f1;
/* 234 */         float f2 = MathHelper.func_76126_a(f1 * 3.1415927F);
/* 235 */         float f3 = MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * -(this.field_78116_c.field_78795_f - 0.7F) * 0.75F;
/* 236 */         this.rightLeg.field_78795_f = (float)(this.field_178723_h.field_78795_f - f2 * 1.5D + f3);
/* 237 */         this.rightLeg.field_78796_g += this.field_78115_e.field_78796_g * 2.0F;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 244 */     modelRenderer.field_78795_f = x;
/* 245 */     modelRenderer.field_78796_g = y;
/* 246 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225599_a_(HandSide side, MatrixStack matrixStack) {
/* 252 */     super.func_225599_a_(side, matrixStack);
/* 253 */     matrixStack.func_227861_a_(0.0D, 0.4D, 0.0D);
/* 254 */     matrixStack.func_227863_a_(Vector3f.field_229183_f_.func_229187_a_((side == HandSide.RIGHT) ? -20.0F : 20.0F));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*     */ 
/*     */   
/*     */   public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
/* 263 */     if (side == HandSide.RIGHT) {
/*     */       
/* 265 */       matrixStack.func_227861_a_(0.0D, -0.8D, 0.3D);
/* 266 */       matrixStack.func_227862_a_(1.5F, 1.5F, 1.5F);
/* 267 */       matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(-60.0F));
/* 268 */       this.rightLeg.func_228309_a_(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     }
/*     */     else {
/*     */       
/* 272 */       matrixStack.func_227861_a_(0.0D, -0.8D, 0.3D);
/* 273 */       matrixStack.func_227862_a_(1.5F, 1.5F, 1.5F);
/* 274 */       matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(60.0F));
/* 275 */       this.leftLeg.func_228309_a_(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\morphs\AllosaurusHeavyPartialModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */