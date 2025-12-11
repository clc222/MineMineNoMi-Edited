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
/*     */ import xyz.pixelatedw.mineminenomi.api.math.EasingFunctionHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.WhiteWalkieEntity;
/*     */ 
/*     */ public class WhiteWalkieModel<T extends LivingEntity> extends EntityModel<T> {
/*     */   private final ModelRenderer body;
/*     */   private final ModelRenderer ass;
/*     */   private final ModelRenderer tail;
/*     */   private final ModelRenderer frontLeftLeg;
/*     */   private final ModelRenderer frontLeftFoot;
/*     */   private final ModelRenderer frontRightLeg;
/*     */   private final ModelRenderer frontRightFoot;
/*     */   private final ModelRenderer backRightLeg;
/*     */   private final ModelRenderer backRightFoot;
/*     */   private final ModelRenderer backLeftLeg;
/*     */   private final ModelRenderer backLeftFoot;
/*     */   private final ModelRenderer bodyFur;
/*     */   private final ModelRenderer head;
/*     */   private final ModelRenderer upperMouth;
/*     */   private final ModelRenderer lowerMouth;
/*     */   private final ModelRenderer chinFur;
/*     */   private final ModelRenderer leftEar;
/*     */   private final ModelRenderer rightEar;
/*     */   protected float entityTick;
/*     */   protected float biteAnimationProgress;
/*     */   private boolean isSleeping;
/*     */   
/*     */   public WhiteWalkieModel() {
/*  38 */     this.field_78090_t = 256;
/*  39 */     this.field_78089_u = 256;
/*     */     
/*  41 */     this.body = new ModelRenderer((Model)this);
/*  42 */     this.body.func_78793_a(0.0F, 3.4271F, 9.751F);
/*  43 */     this.body.func_78784_a(6, 109).func_228303_a_(-11.0F, -8.3771F, -27.5417F, 22.0F, 16.0F, 5.0F, 0.0F, false);
/*  44 */     this.body.func_78784_a(0, 0).func_228303_a_(-13.0F, -9.0385F, -24.5417F, 26.0F, 20.0F, 34.0F, 0.0F, false);
/*  45 */     this.body.func_78784_a(0, 54).func_228303_a_(-11.0F, -11.0385F, -22.5417F, 22.0F, 20.0F, 30.0F, 0.0F, false);
/*     */     
/*  47 */     this.ass = new ModelRenderer((Model)this);
/*  48 */     this.ass.func_78793_a(3.0F, 2.5729F, 23.874F);
/*  49 */     this.body.func_78792_a(this.ass);
/*  50 */     setRotationAngle(this.ass, -0.0436F, 0.0F, 0.0F);
/*  51 */     this.ass.func_78784_a(89, 0).func_228303_a_(-15.0F, -10.1202F, -18.2696F, 24.0F, 18.0F, 8.0F, 0.0F, false);
/*     */     
/*  53 */     this.tail = new ModelRenderer((Model)this);
/*  54 */     this.tail.func_78793_a(0.0F, -5.4285F, 12.8316F);
/*  55 */     this.body.func_78792_a(this.tail);
/*  56 */     setRotationAngle(this.tail, 0.1309F, 0.0F, 0.0F);
/*  57 */     this.tail.func_78784_a(126, 29).func_228303_a_(-4.0F, -0.2505F, 1.0033F, 8.0F, 10.0F, 0.0F, 0.0F, false);
/*     */     
/*  59 */     this.frontLeftLeg = new ModelRenderer((Model)this);
/*  60 */     this.frontLeftLeg.func_78793_a(7.0F, 10.5729F, -19.751F);
/*  61 */     this.body.func_78792_a(this.frontLeftLeg);
/*  62 */     setRotationAngle(this.frontLeftLeg, -0.1309F, 0.0F, 0.0F);
/*  63 */     this.frontLeftLeg.func_78784_a(113, 61).func_228303_a_(-3.0F, -1.5847F, -3.176F, 6.0F, 10.0F, 6.0F, 0.0F, true);
/*     */     
/*  65 */     this.frontLeftFoot = new ModelRenderer((Model)this);
/*  66 */     this.frontLeftFoot.func_78793_a(0.0F, 8.3032F, -1.0862F);
/*  67 */     this.frontLeftLeg.func_78792_a(this.frontLeftFoot);
/*  68 */     setRotationAngle(this.frontLeftFoot, 0.1309F, 0.0F, 0.0F);
/*  69 */     this.frontLeftFoot.func_78784_a(110, 78).func_228303_a_(-3.0F, -2.0F, -4.25F, 6.0F, 4.0F, 8.0F, -0.01F, true);
/*     */     
/*  71 */     this.frontRightLeg = new ModelRenderer((Model)this);
/*  72 */     this.frontRightLeg.func_78793_a(-7.0F, 10.5729F, -21.001F);
/*  73 */     this.body.func_78792_a(this.frontRightLeg);
/*  74 */     setRotationAngle(this.frontRightLeg, -0.1309F, 0.0F, 0.0F);
/*  75 */     this.frontRightLeg.func_78784_a(113, 61).func_228303_a_(-3.0F, -1.4799F, -1.7758F, 6.0F, 10.0F, 6.0F, 0.0F, false);
/*     */     
/*  77 */     this.frontRightFoot = new ModelRenderer((Model)this);
/*  78 */     this.frontRightFoot.func_78793_a(0.0F, 8.408F, -0.6861F);
/*  79 */     this.frontRightLeg.func_78792_a(this.frontRightFoot);
/*  80 */     setRotationAngle(this.frontRightFoot, 0.1309F, 0.0F, 0.0F);
/*  81 */     this.frontRightFoot.func_78784_a(110, 78).func_228303_a_(-3.0F, -2.0F, -3.25F, 6.0F, 4.0F, 8.0F, -0.01F, false);
/*     */     
/*  83 */     this.backRightLeg = new ModelRenderer((Model)this);
/*  84 */     this.backRightLeg.func_78793_a(-8.0F, 10.5729F, 4.3735F);
/*  85 */     this.body.func_78792_a(this.backRightLeg);
/*  86 */     setRotationAngle(this.backRightLeg, -0.1309F, 0.0F, 0.0F);
/*  87 */     this.backRightLeg.func_78784_a(139, 63).func_228303_a_(-3.0F, -0.058F, -2.7389F, 6.0F, 8.0F, 6.0F, 0.0F, false);
/*     */     
/*  89 */     this.backRightFoot = new ModelRenderer((Model)this);
/*  90 */     this.backRightFoot.func_78793_a(0.0F, 8.0212F, -0.7307F);
/*  91 */     this.backRightLeg.func_78792_a(this.backRightFoot);
/*  92 */     setRotationAngle(this.backRightFoot, 0.1309F, 0.0F, 0.0F);
/*  93 */     this.backRightFoot.func_78784_a(139, 78).func_228303_a_(-3.0F, -2.0F, -4.0F, 6.0F, 4.0F, 8.0F, -0.01F, false);
/*     */     
/*  95 */     this.backLeftLeg = new ModelRenderer((Model)this);
/*  96 */     this.backLeftLeg.func_78793_a(8.0F, 10.5729F, 4.6658F);
/*  97 */     this.body.func_78792_a(this.backLeftLeg);
/*  98 */     setRotationAngle(this.backLeftLeg, -0.1309F, 0.0F, 0.0F);
/*  99 */     this.backLeftLeg.func_78784_a(139, 63).func_228303_a_(-3.0F, -0.0792F, -3.0F, 6.0F, 8.0F, 6.0F, 0.0F, true);
/*     */     
/* 101 */     this.backLeftFoot = new ModelRenderer((Model)this);
/* 102 */     this.backLeftFoot.func_78793_a(0.0F, 8.0F, -0.9917F);
/* 103 */     this.backLeftLeg.func_78792_a(this.backLeftFoot);
/* 104 */     setRotationAngle(this.backLeftFoot, 0.1309F, 0.0F, 0.0F);
/* 105 */     this.backLeftFoot.func_78784_a(139, 78).func_228303_a_(-3.0F, -2.0F, -4.0F, 6.0F, 4.0F, 8.0F, -0.01F, true);
/*     */     
/* 107 */     this.bodyFur = new ModelRenderer((Model)this);
/* 108 */     this.bodyFur.func_78793_a(0.0F, 11.8229F, 3.874F);
/* 109 */     this.body.func_78792_a(this.bodyFur);
/* 110 */     this.bodyFur.func_78784_a(64, 120).func_228303_a_(-11.0F, -2.25F, -28.0F, 22.0F, 7.0F, 0.0F, 0.0F, false);
/* 111 */     this.bodyFur.func_78784_a(64, 77).func_228303_a_(12.0F, -2.25F, -27.0F, 0.0F, 7.0F, 32.0F, 0.0F, false);
/* 112 */     this.bodyFur.func_78784_a(64, 77).func_228303_a_(-12.0F, -2.25F, -27.0F, 0.0F, 7.0F, 32.0F, 0.0F, false);
/* 113 */     this.bodyFur.func_78784_a(64, 119).func_228303_a_(-11.0F, -3.25F, 6.0F, 22.0F, 8.0F, 0.0F, 0.0F, false);
/*     */     
/* 115 */     this.head = new ModelRenderer((Model)this);
/* 116 */     this.head.func_78793_a(0.0F, 4.5813F, -4.4598F);
/* 117 */     setRotationAngle(this.head, 0.0436F, 0.0F, 0.0F);
/* 118 */     this.head.func_78784_a(4, 133).func_228303_a_(-10.0F, -9.879F, -20.199F, 20.0F, 18.0F, 10.0F, 0.0F, false);
/*     */     
/* 120 */     this.upperMouth = new ModelRenderer((Model)this);
/* 121 */     this.upperMouth.func_78793_a(0.0F, 4.7688F, -14.9009F);
/* 122 */     this.head.func_78792_a(this.upperMouth);
/* 123 */     this.upperMouth.func_78784_a(6, 162).func_228303_a_(-9.0F, -11.6868F, -16.3353F, 18.0F, 10.0F, 16.0F, 0.0F, false);
/* 124 */     this.upperMouth.func_78784_a(4, 170).func_228303_a_(3.0F, -2.3763F, -15.8167F, 4.0F, 2.0F, 1.0F, 0.0F, false);
/* 125 */     this.upperMouth.func_78784_a(4, 170).func_228303_a_(-7.0F, -2.3763F, -15.8167F, 4.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/* 127 */     this.lowerMouth = new ModelRenderer((Model)this);
/* 128 */     this.lowerMouth.func_78793_a(0.0F, 2.912F, -18.2061F);
/* 129 */     this.head.func_78792_a(this.lowerMouth);
/* 130 */     this.lowerMouth.func_78784_a(3, 190).func_228303_a_(-8.0F, 0.2077F, -11.7395F, 16.0F, 4.0F, 12.0F, 0.0F, false);
/*     */     
/* 132 */     this.chinFur = new ModelRenderer((Model)this);
/* 133 */     this.chinFur.func_78793_a(0.0F, 4.5067F, 3.7492F);
/* 134 */     this.lowerMouth.func_78792_a(this.chinFur);
/* 135 */     this.chinFur.func_78784_a(3, 203).func_228303_a_(7.0F, -2.4798F, -14.4479F, 0.0F, 4.0F, 10.375F, 0.0F, false);
/* 136 */     this.chinFur.func_78784_a(3, 203).func_228303_a_(-7.0F, -2.4798F, -14.4479F, 0.0F, 4.0F, 10.375F, 0.0F, false);
/* 137 */     this.chinFur.func_78784_a(3, 208).func_228303_a_(-7.0F, -2.4798F, -14.4479F, 14.0F, 4.0F, 0.0F, 0.0F, false);
/*     */     
/* 139 */     this.leftEar = new ModelRenderer((Model)this);
/* 140 */     this.leftEar.func_78793_a(9.3123F, -8.7506F, -15.4048F);
/* 141 */     this.head.func_78792_a(this.leftEar);
/* 142 */     setRotationAngle(this.leftEar, 0.0F, 0.0F, 0.48F);
/* 143 */     this.leftEar.func_78784_a(1, 164).func_228303_a_(-0.7642F, -0.5365F, -1.5F, 6.0F, 1.0F, 3.0F, 0.0F, true);
/*     */     
/* 145 */     this.rightEar = new ModelRenderer((Model)this);
/* 146 */     this.rightEar.func_78793_a(-9.4837F, -8.6947F, -15.4048F);
/* 147 */     this.head.func_78792_a(this.rightEar);
/* 148 */     setRotationAngle(this.rightEar, 0.0F, 0.0F, -0.48F);
/* 149 */     this.rightEar.func_78784_a(1, 164).func_228303_a_(-5.2358F, -0.5365F, -1.5F, 6.0F, 1.0F, 3.0F, 0.0F, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void prepareMobModel(T entity, float limbSwing, float limbSwingAmount, float partialTicks) {
/* 154 */     if (entity instanceof WhiteWalkieEntity) {
/* 155 */       this.biteAnimationProgress = ((WhiteWalkieEntity)entity).getBiteAnimationProgress(partialTicks);
/*     */     }
/* 157 */     this.entityTick = ((LivingEntity)entity).field_70173_aa;
/* 158 */     this.isSleeping = entity.func_70608_bn();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 163 */     this.head.field_78808_h = 0.0F;
/* 164 */     this.leftEar.field_78808_h = (float)Math.toRadians(60.0D);
/* 165 */     this.rightEar.field_78808_h = (float)Math.toRadians(-60.0D);
/*     */     
/* 167 */     float speed = 0.95F;
/* 168 */     float spread = 0.6F;
/* 169 */     if (entity.func_70051_ag()) {
/* 170 */       speed = 0.125F;
/*     */     }
/*     */     
/* 173 */     this.frontRightLeg.field_78795_f = (float)(Math.toRadians(-8.0D) + (MathHelper.func_76134_b(limbSwing * speed) * spread * limbSwingAmount));
/* 174 */     this.frontLeftLeg.field_78795_f = (float)(Math.toRadians(-8.0D) + (MathHelper.func_76134_b(limbSwing * speed + 3.1415927F) * spread * limbSwingAmount));
/* 175 */     this.backRightLeg.field_78795_f = (float)(Math.toRadians(-8.0D) + (MathHelper.func_76134_b(limbSwing * speed + 3.1415927F) * spread * limbSwingAmount));
/* 176 */     this.backLeftLeg.field_78795_f = (float)(Math.toRadians(-8.0D) + (MathHelper.func_76134_b(limbSwing * speed) * spread * limbSwingAmount));
/*     */ 
/*     */     
/* 179 */     if (entity instanceof WhiteWalkieEntity && ((WhiteWalkieEntity)entity).getShakeAnimationTime() > 0.0F) {
/* 180 */       this.head.field_78808_h = (float)(Math.cos((ageInTicks * 0.4F) + Math.PI) * 0.10000000149011612D);
/* 181 */       this.leftEar.field_78808_h = (float)(this.leftEar.field_78808_h + Math.cos((ageInTicks * 0.8F) + Math.PI) * 0.20000000298023224D);
/* 182 */       this.rightEar.field_78808_h = (float)(this.rightEar.field_78808_h + Math.cos((ageInTicks * 0.8F) + Math.PI) * 0.20000000298023224D);
/*     */     } 
/*     */     
/* 185 */     if (this.biteAnimationProgress < 1.0F) {
/* 186 */       float angle = MathHelper.func_76126_a(this.biteAnimationProgress * 0.5F * 3.1415927F) * 0.7F;
/* 187 */       this.upperMouth.field_78795_f = (float)Math.toRadians(-40.0D) + angle;
/* 188 */       this.lowerMouth.field_78795_f = (float)Math.toRadians(40.0D) - angle;
/*     */     }
/* 190 */     else if (this.biteAnimationProgress >= 1.0F) {
/* 191 */       ((LivingEntity)entity).field_82175_bq = false;
/* 192 */       this.upperMouth.field_78795_f = (float)Math.toRadians(0.0D);
/* 193 */       this.lowerMouth.field_78795_f = (float)Math.toRadians(0.0D);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 199 */     if (this.field_217114_e) {
/* 200 */       matrixStack.func_227862_a_(0.5F, 0.5F, 0.5F);
/* 201 */       matrixStack.func_227861_a_(0.0D, 1.5D, 0.0D);
/*     */     } 
/*     */     
/* 204 */     matrixStack.func_227860_a_();
/* 205 */     if (this.isSleeping) {
/* 206 */       float progress = this.entityTick % 100.0F / 100.0F;
/* 207 */       progress = (progress < 0.5F) ? progress : (1.0F - progress);
/* 208 */       progress = EasingFunctionHelper.easeInOutCubic(Float.valueOf(progress));
/* 209 */       float initialScale = 1.0F;
/* 210 */       float scale = 1.0F + 1.0F * progress * 0.1F;
/* 211 */       matrixStack.func_227862_a_(scale, scale, scale);
/*     */     } 
/* 213 */     this.body.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 214 */     matrixStack.func_227865_b_();
/* 215 */     this.head.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */   }
/*     */   
/*     */   public void setSleepPosition(boolean flag) {
/* 219 */     showLegs(!flag);
/* 220 */     this.head.field_78797_d = flag ? 7.0F : 4.5813F;
/*     */   }
/*     */   
/*     */   public void showLegs(boolean showFlag) {
/* 224 */     if (showFlag) {
/* 225 */       this.frontRightLeg.field_78806_j = true;
/* 226 */       this.frontLeftLeg.field_78806_j = true;
/* 227 */       this.backRightLeg.field_78806_j = true;
/* 228 */       this.backLeftLeg.field_78806_j = true;
/*     */     } else {
/*     */       
/* 231 */       this.frontRightLeg.field_78806_j = false;
/* 232 */       this.frontLeftLeg.field_78806_j = false;
/* 233 */       this.backRightLeg.field_78806_j = false;
/* 234 */       this.backLeftLeg.field_78806_j = false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 239 */     modelRenderer.field_78795_f = x;
/* 240 */     modelRenderer.field_78796_g = y;
/* 241 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\entities\mobs\animals\WhiteWalkieModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */