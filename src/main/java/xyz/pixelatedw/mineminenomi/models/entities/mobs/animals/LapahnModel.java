/*     */ package xyz.pixelatedw.mineminenomi.models.entities.mobs.animals;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.renderer.entity.model.AgeableModel;
/*     */ import net.minecraft.client.renderer.entity.model.IHasArm;
/*     */ import net.minecraft.client.renderer.entity.model.IHasHead;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.HandSide;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.LapahnEntity;
/*     */ 
/*     */ public class LapahnModel<T extends LapahnEntity> extends AgeableModel<T> implements IHasArm, IHasHead {
/*     */   private final ModelRenderer head;
/*     */   private final ModelRenderer leftEar;
/*     */   private final ModelRenderer rightEar;
/*     */   private final ModelRenderer wiskers;
/*     */   private final ModelRenderer body;
/*     */   private final ModelRenderer tail;
/*     */   private final ModelRenderer rightLeg;
/*     */   private final ModelRenderer rightLeg2;
/*     */   private final ModelRenderer rightFoot;
/*     */   private final ModelRenderer leftLeg;
/*     */   private final ModelRenderer leftLeg2;
/*     */   private final ModelRenderer leftFoot;
/*     */   private final ModelRenderer rightArm;
/*     */   private final ModelRenderer rightArm2;
/*     */   private final ModelRenderer leftArm;
/*     */   private final ModelRenderer leftArm2;
/*     */   protected float jumpAnimationProgress;
/*     */   
/*     */   public LapahnModel() {
/*  36 */     this.field_78090_t = 140;
/*  37 */     this.field_78089_u = 70;
/*     */     
/*  39 */     this.head = new ModelRenderer((Model)this);
/*  40 */     this.head.func_78793_a(0.0F, -7.0F, 0.0F);
/*  41 */     this.head.func_78784_a(0, 0).func_228303_a_(-3.0F, -6.0F, -1.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);
/*     */     
/*  43 */     this.leftEar = new ModelRenderer((Model)this);
/*  44 */     this.leftEar.func_78793_a(1.7F, -5.5F, 0.0F);
/*  45 */     this.head.func_78792_a(this.leftEar);
/*  46 */     setRotationAngle(this.leftEar, 0.0719F, -0.1738F, 0.0887F);
/*  47 */     this.leftEar.func_78784_a(25, 0).func_228303_a_(-0.6541F, -5.8585F, 1.4648F, 2.0F, 6.0F, 1.0F, 0.0F, false);
/*     */     
/*  49 */     this.rightEar = new ModelRenderer((Model)this);
/*  50 */     this.rightEar.func_78793_a(-1.7F, -5.5F, 0.0F);
/*  51 */     this.head.func_78792_a(this.rightEar);
/*  52 */     setRotationAngle(this.rightEar, 0.0719F, 0.1738F, -0.0887F);
/*  53 */     this.rightEar.func_78784_a(25, 0).func_228303_a_(-1.3459F, -5.8585F, 1.4648F, 2.0F, 6.0F, 1.0F, 0.0F, false);
/*     */     
/*  55 */     this.wiskers = new ModelRenderer((Model)this);
/*  56 */     this.wiskers.func_78793_a(0.0F, -1.0F, -0.1F);
/*  57 */     this.head.func_78792_a(this.wiskers);
/*  58 */     this.wiskers.func_78784_a(92, 44).func_228303_a_(-4.5F, -6.0F, -1.0F, 9.0F, 9.0F, 0.0F, 0.0F, false);
/*     */     
/*  60 */     this.body = new ModelRenderer((Model)this);
/*  61 */     this.body.func_78793_a(0.0F, 6.0F, 0.0F);
/*  62 */     this.body.func_78784_a(35, 13).func_228303_a_(-8.5F, 0.0F, -4.0F, 17.0F, 10.0F, 12.0F, 0.0F, false);
/*  63 */     this.body.func_78784_a(35, 36).func_228303_a_(-8.0F, -6.0F, -3.5F, 16.0F, 6.0F, 11.0F, 0.0F, false);
/*  64 */     this.body.func_78784_a(35, 54).func_228303_a_(-7.0F, -11.0F, -3.0F, 14.0F, 5.0F, 10.0F, 0.0F, false);
/*  65 */     this.body.func_78784_a(90, 0).func_228303_a_(-6.5F, -13.0F, -2.5F, 13.0F, 2.0F, 9.0F, 0.0F, false);
/*  66 */     this.body.func_78784_a(35, 0).func_228303_a_(-8.0F, 10.0F, -3.5F, 16.0F, 1.0F, 11.0F, 0.0F, false);
/*     */     
/*  68 */     this.tail = new ModelRenderer((Model)this);
/*  69 */     this.tail.func_78793_a(0.0F, 7.0F, 5.8F);
/*  70 */     this.body.func_78792_a(this.tail);
/*  71 */     setRotationAngle(this.tail, -0.1047F, 0.0F, 0.0F);
/*  72 */     this.tail.func_78784_a(0, 50).func_228303_a_(-1.0F, -1.209F, 1.989F, 2.0F, 2.0F, 2.0F, 1.0F, false);
/*     */     
/*  74 */     this.rightLeg = new ModelRenderer((Model)this);
/*  75 */     this.rightLeg.func_78793_a(-4.8F, 14.9F, -2.2F);
/*  76 */     setRotationAngle(this.rightLeg, -0.2967F, 0.0F, 0.0F);
/*  77 */     this.rightLeg.func_78784_a(0, 13).func_228303_a_(-4.0F, -1.5847F, -2.0874F, 8.0F, 6.0F, 9.0F, 0.0F, false);
/*     */     
/*  79 */     this.rightLeg2 = new ModelRenderer((Model)this);
/*  80 */     this.rightLeg2.func_78793_a(-4.8F, 20.5228F, 5.2592F);
/*  81 */     this.rightLeg.func_78792_a(this.rightLeg2);
/*  82 */     setRotationAngle(this.rightLeg2, 0.3316F, 0.0F, 0.0F);
/*  83 */     this.rightLeg2.func_78784_a(0, 29).func_228303_a_(2.8F, -16.3302F, 2.1988F, 4.0F, 3.0F, 4.0F, 0.0F, false);
/*     */     
/*  85 */     this.rightFoot = new ModelRenderer((Model)this);
/*  86 */     this.rightFoot.func_78793_a(4.85F, -13.9053F, 2.0669F);
/*  87 */     this.rightLeg2.func_78792_a(this.rightFoot);
/*  88 */     setRotationAngle(this.rightFoot, -0.0349F, 0.0F, 0.0F);
/*  89 */     this.rightFoot.func_78784_a(0, 37).func_228303_a_(-2.5F, 0.0692F, -4.9639F, 5.0F, 2.0F, 10.0F, 0.0F, false);
/*     */     
/*  91 */     this.leftLeg = new ModelRenderer((Model)this);
/*  92 */     this.leftLeg.func_78793_a(4.8F, 14.9F, -2.2F);
/*  93 */     setRotationAngle(this.leftLeg, -0.2967F, 0.0F, 0.0F);
/*  94 */     this.leftLeg.func_78784_a(0, 13).func_228303_a_(-4.0F, -1.5847F, -2.0874F, 8.0F, 6.0F, 9.0F, 0.0F, false);
/*     */     
/*  96 */     this.leftLeg2 = new ModelRenderer((Model)this);
/*  97 */     this.leftLeg2.func_78793_a(0.0F, 3.8F, 2.0F);
/*  98 */     this.leftLeg.func_78792_a(this.leftLeg2);
/*  99 */     setRotationAngle(this.leftLeg2, 0.3316F, 0.0F, 0.0F);
/* 100 */     this.leftLeg2.func_78784_a(0, 29).func_228303_a_(-2.0F, 0.3198F, -0.0012F, 4.0F, 3.0F, 4.0F, 0.0F, false);
/*     */     
/* 102 */     this.leftFoot = new ModelRenderer((Model)this);
/* 103 */     this.leftFoot.func_78793_a(-0.05F, 3.2447F, 0.1169F);
/* 104 */     this.leftLeg2.func_78792_a(this.leftFoot);
/* 105 */     setRotationAngle(this.leftFoot, -0.0349F, 0.0F, 0.0F);
/* 106 */     this.leftFoot.func_78784_a(0, 37).func_228303_a_(-2.5F, -0.1719F, -5.2225F, 5.0F, 2.0F, 10.0F, 0.0F, false);
/*     */     
/* 108 */     this.rightArm = new ModelRenderer((Model)this);
/* 109 */     this.rightArm.func_78793_a(-6.5F, -3.0F, 0.0F);
/* 110 */     setRotationAngle(this.rightArm, 0.0F, 0.0F, 0.2618F);
/* 111 */     this.rightArm.func_78784_a(93, 12).func_228303_a_(-6.0F, -2.0F, -0.5F, 6.0F, 10.0F, 6.0F, 0.0F, false);
/*     */     
/* 113 */     this.rightArm2 = new ModelRenderer((Model)this);
/* 114 */     this.rightArm2.func_78793_a(-2.5F, 7.5F, 0.0F);
/* 115 */     this.rightArm.func_78792_a(this.rightArm2);
/* 116 */     setRotationAngle(this.rightArm2, 0.0F, 0.0F, -0.1222F);
/* 117 */     this.rightArm2.func_78784_a(93, 29).func_228303_a_(-3.5F, 0.0F, -0.5F, 6.0F, 9.0F, 6.0F, 0.01F, false);
/*     */     
/* 119 */     this.leftArm = new ModelRenderer((Model)this);
/* 120 */     this.leftArm.func_78793_a(6.5F, -3.0F, 0.0F);
/* 121 */     setRotationAngle(this.leftArm, 0.0F, 0.0F, -0.2618F);
/* 122 */     this.leftArm.func_78784_a(93, 12).func_228303_a_(0.0F, -2.0F, -0.5F, 6.0F, 10.0F, 6.0F, 0.0F, false);
/*     */     
/* 124 */     this.leftArm2 = new ModelRenderer((Model)this);
/* 125 */     this.leftArm2.func_78793_a(2.5F, 8.5F, 0.0F);
/* 126 */     this.leftArm.func_78792_a(this.leftArm2);
/* 127 */     setRotationAngle(this.leftArm2, 0.0F, 0.0F, 0.1222F);
/* 128 */     this.leftArm2.func_78784_a(93, 29).func_228303_a_(-2.5F, -1.0F, -0.5F, 6.0F, 9.0F, 6.0F, 0.01F, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 134 */     this.head.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 135 */     this.body.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 136 */     this.rightLeg.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 137 */     this.leftLeg.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 138 */     this.rightArm.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 139 */     this.leftArm.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */   }
/*     */ 
/*     */   
/*     */   public void prepareMobModel(T entity, float limbSwing, float limbSwingAmount, float partialTicks) {
/* 144 */     this.jumpAnimationProgress = entity.getJumpAnimationProgress(partialTicks);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 149 */     this.head.field_78800_c = 0.0F;
/* 150 */     this.head.field_78797_d = -7.0F;
/* 151 */     this.head.field_78798_e = 0.0F;
/* 152 */     setRotationAngle(this.head, 0.0F, 0.0F, 0.0F);
/* 153 */     this.body.field_78800_c = 0.0F;
/* 154 */     this.body.field_78797_d = 6.0F;
/* 155 */     this.body.field_78798_e = 0.0F;
/* 156 */     setRotationAngle(this.body, 0.0F, 0.0F, 0.0F);
/* 157 */     this.leftLeg.field_78800_c = 4.8F;
/* 158 */     this.leftLeg.field_78797_d = 14.9F;
/* 159 */     this.leftLeg.field_78798_e = -2.2F;
/* 160 */     setRotationAngle(this.leftLeg, -0.2967F, 0.0F, 0.0F);
/* 161 */     this.rightLeg.field_78800_c = -4.8F;
/* 162 */     this.rightLeg.field_78797_d = 14.9F;
/* 163 */     this.rightLeg.field_78798_e = -2.2F;
/* 164 */     setRotationAngle(this.rightLeg, -0.2967F, 0.0F, 0.0F);
/* 165 */     this.rightArm.field_78800_c = -6.5F;
/* 166 */     this.rightArm.field_78797_d = -3.0F;
/* 167 */     this.rightArm.field_78798_e = 0.0F;
/* 168 */     setRotationAngle(this.rightArm, 0.0F, 0.0F, 0.2618F);
/* 169 */     this.leftArm.field_78800_c = 6.5F;
/* 170 */     this.leftArm.field_78797_d = -3.0F;
/* 171 */     this.leftArm.field_78798_e = 0.0F;
/* 172 */     setRotationAngle(this.leftArm, 0.0F, 0.0F, -0.2618F);
/*     */ 
/*     */     
/* 175 */     this.head.field_78796_g = netHeadYaw * 0.017453292F;
/* 176 */     this.head.field_78795_f = headPitch * 0.017453292F;
/*     */     
/* 178 */     if (this.jumpAnimationProgress > 0.0F) {
/* 179 */       this.rightLeg.field_78795_f = MathHelper.func_76126_a(this.jumpAnimationProgress * 2.0F * 3.1415927F) * 0.8F;
/* 180 */       this.leftLeg.field_78795_f = MathHelper.func_76126_a(this.jumpAnimationProgress * 2.0F * 3.1415927F) * 0.8F;
/*     */     } 
/*     */ 
/*     */     
/* 184 */     boolean isRunning = entity.isChasing();
/* 185 */     if (isRunning) {
/* 186 */       float swingSpeed = 0.9F;
/* 187 */       float swingAmount = 0.7F;
/* 188 */       this.head.field_78798_e = -19.0F;
/* 189 */       this.head.field_78797_d = 7.0F;
/* 190 */       this.body.field_78797_d = 11.0F;
/* 191 */       this.body.field_78798_e = -4.0F;
/* 192 */       this.body.field_78795_f = (float)Math.toRadians(80.0D);
/* 193 */       this.rightArm.field_78798_e = -16.0F;
/* 194 */       this.rightArm.field_78800_c = -3.5F;
/* 195 */       this.rightArm.field_78797_d = 8.0F;
/* 196 */       this.rightArm.field_78795_f = (float)Math.toRadians(-20.0D) + MathHelper.func_76134_b(limbSwing * 0.9F + 3.1415927F) * 0.7F * limbSwingAmount * 0.7F / 1.0F;
/* 197 */       this.leftArm.field_78798_e = -16.0F;
/* 198 */       this.leftArm.field_78800_c = 3.5F;
/* 199 */       this.leftArm.field_78797_d = 8.0F;
/* 200 */       this.leftArm.field_78795_f = (float)Math.toRadians(-20.0D) + MathHelper.func_76134_b(limbSwing * 0.9F + 3.1415927F) * 0.7F * limbSwingAmount * 0.7F / 1.0F;
/* 201 */       this.rightLeg.field_78800_c = -7.0F;
/* 202 */       this.rightLeg.field_78795_f = -MathHelper.func_76134_b(limbSwing * 0.9F + 3.1415927F) * 0.7F * limbSwingAmount * 0.7F / 1.0F;
/* 203 */       this.leftLeg.field_78800_c = 7.0F;
/* 204 */       this.leftLeg.field_78795_f = -MathHelper.func_76134_b(limbSwing * 0.9F + 3.1415927F) * 0.7F * limbSwingAmount * 0.7F / 1.0F;
/*     */     } 
/*     */ 
/*     */     
/* 208 */     boolean isSitting = (entity.isResting() || this.field_217113_d);
/* 209 */     if (isSitting) {
/* 210 */       this.rightLeg.field_78795_f = (float)Math.toRadians(-90.0D);
/* 211 */       this.leftLeg.field_78795_f = (float)Math.toRadians(-90.0D);
/* 212 */       this.rightLeg.field_78797_d = 10.9F;
/* 213 */       this.leftLeg.field_78797_d = 10.9F;
/*     */     } 
/*     */ 
/*     */     
/* 217 */     if (ageInTicks % 300.0F > 0.0F && ageInTicks % 300.0F < 100.0F) {
/* 218 */       this.leftEar.field_78795_f = 0.1F * (0.3F + MathHelper.func_76134_b(ageInTicks * 0.55F));
/*     */     }
/*     */   }
/*     */   
/*     */   public void setRotationAngle(ModelRenderer model, float x, float y, float z) {
/* 223 */     model.field_78795_f = x;
/* 224 */     model.field_78796_g = y;
/* 225 */     model.field_78808_h = z;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225599_a_(HandSide pSide, MatrixStack pMatrixStack) {}
/*     */ 
/*     */   
/*     */   public ModelRenderer func_205072_a() {
/* 234 */     return this.head;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Iterable<ModelRenderer> func_225602_a_() {
/* 239 */     return (Iterable<ModelRenderer>)ImmutableList.of(this.head, this.wiskers, this.leftEar, this.rightEar);
/*     */   }
/*     */ 
/*     */   
/*     */   protected Iterable<ModelRenderer> func_225600_b_() {
/* 244 */     return (Iterable<ModelRenderer>)ImmutableList.of(this.body, this.rightArm, this.leftArm, this.rightLeg, this.leftLeg);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\entities\mobs\animals\LapahnModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */