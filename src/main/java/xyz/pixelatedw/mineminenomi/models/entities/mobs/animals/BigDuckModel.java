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
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.BigDuckEntity;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BigDuckModel<T extends BigDuckEntity>
/*     */   extends BipedModel<T>
/*     */ {
/*     */   private final ModelRenderer body;
/*     */   private final ModelRenderer leftWing;
/*     */   private final ModelRenderer rightWing;
/*     */   private final ModelRenderer tail;
/*     */   private final ModelRenderer leftLeg;
/*     */   private final ModelRenderer lowerLeftLeg;
/*     */   private final ModelRenderer leftFoot;
/*     */   private final ModelRenderer rightLeg;
/*     */   
/*     */   public BigDuckModel() {
/*  32 */     super(1.0F);
/*  33 */     this.field_78090_t = 128;
/*  34 */     this.field_78089_u = 128;
/*     */     
/*  36 */     this.body = new ModelRenderer((Model)this);
/*  37 */     this.body.func_78793_a(0.0F, 6.4042F, 7.8958F);
/*  38 */     this.body.func_78784_a(64, 30).func_228303_a_(-7.0F, -5.4042F, -14.8958F, 14.0F, 11.0F, 18.0F, 0.0F, false);
/*  39 */     this.body.func_78784_a(60, 61).func_228303_a_(-6.5F, -3.6542F, -12.3958F, 13.0F, 10.0F, 15.0F, 0.0F, false);
/*  40 */     this.body.func_78784_a(72, 0).func_228303_a_(-5.5F, -5.9542F, -15.6458F, 11.0F, 11.0F, 17.0F, 0.0F, false);
/*  41 */     this.body.func_78784_a(61, 89).func_228303_a_(-6.0F, -4.7042F, 2.6042F, 12.0F, 10.0F, 3.0F, 0.0F, false);
/*  42 */     this.body.func_78784_a(61, 104).func_228303_a_(-5.0F, -4.4042F, 5.6042F, 10.0F, 8.0F, 2.0F, 0.0F, false);
/*     */     
/*  44 */     this.leftWing = new ModelRenderer((Model)this);
/*  45 */     this.leftWing.func_78793_a(6.5F, -2.9042F, -11.8958F);
/*  46 */     this.body.func_78792_a(this.leftWing);
/*  47 */     setRotationAngle(this.leftWing, -0.2182F, 0.0F, 0.0F);
/*  48 */     this.leftWing.func_78784_a(8, 72).func_228303_a_(0.5F, -2.4526F, -2.2454F, 1.0F, 9.0F, 13.0F, 0.0F, true);
/*  49 */     this.leftWing.func_78784_a(0, 84).func_228303_a_(0.5F, -2.4526F, 10.7546F, 1.0F, 8.0F, 2.0F, 0.0F, true);
/*     */     
/*  51 */     this.rightWing = new ModelRenderer((Model)this);
/*  52 */     this.rightWing.func_78793_a(-6.5F, -2.9042F, -11.8958F);
/*  53 */     this.body.func_78792_a(this.rightWing);
/*  54 */     setRotationAngle(this.rightWing, -0.2182F, 0.0F, 0.0F);
/*  55 */     this.rightWing.func_78784_a(8, 72).func_228303_a_(-1.5F, -2.5F, -1.8125F, 1.0F, 9.0F, 13.0F, 0.0F, false);
/*  56 */     this.rightWing.func_78784_a(0, 84).func_228303_a_(-1.5F, -2.5F, 11.1875F, 1.0F, 8.0F, 2.0F, 0.0F, false);
/*     */     
/*  58 */     this.tail = new ModelRenderer((Model)this);
/*  59 */     this.tail.func_78793_a(0.0F, -4.3853F, 7.3423F);
/*  60 */     this.body.func_78792_a(this.tail);
/*  61 */     setRotationAngle(this.tail, -0.1309F, 0.0F, 0.0F);
/*  62 */     this.tail.func_78784_a(53, 116).func_228303_a_(-5.0F, 0.1937F, 0.0119F, 10.0F, 0.0F, 10.0F, 0.0F, false);
/*     */     
/*  64 */     this.leftLeg = new ModelRenderer((Model)this);
/*  65 */     this.leftLeg.func_78793_a(2.5F, 11.3125F, 3.0F);
/*  66 */     this.leftLeg.func_78784_a(16, 97).func_228303_a_(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, true);
/*     */     
/*  68 */     this.lowerLeftLeg = new ModelRenderer((Model)this);
/*  69 */     this.lowerLeftLeg.func_78793_a(0.0F, 6.0F, 0.0F);
/*  70 */     this.leftLeg.func_78792_a(this.lowerLeftLeg);
/*  71 */     this.lowerLeftLeg.func_78784_a(16, 107).func_228303_a_(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, true);
/*     */     
/*  73 */     this.leftFoot = new ModelRenderer((Model)this);
/*  74 */     this.leftFoot.func_78793_a(0.0F, 5.6875F, 0.0F);
/*  75 */     this.lowerLeftLeg.func_78792_a(this.leftFoot);
/*  76 */     this.leftFoot.func_78784_a(-7, 114).func_228303_a_(-2.5F, 1.0F, -7.0F, 5.0F, 0.0F, 8.0F, 0.0F, true);
/*  77 */     this.leftFoot.func_78784_a(13, 117).func_228303_a_(-1.5F, 0.0F, -3.0F, 3.0F, 1.0F, 4.0F, 0.0F, true);
/*     */     
/*  79 */     this.rightLeg = new ModelRenderer((Model)this);
/*  80 */     this.rightLeg.func_78793_a(-2.5F, 11.3125F, 3.0F);
/*  81 */     this.rightLeg.func_78784_a(16, 97).func_228303_a_(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);
/*     */     
/*  83 */     this.lowerRightLeg = new ModelRenderer((Model)this);
/*  84 */     this.lowerRightLeg.func_78793_a(0.0F, 6.0F, 0.0F);
/*  85 */     this.rightLeg.func_78792_a(this.lowerRightLeg);
/*  86 */     this.lowerRightLeg.func_78784_a(16, 107).func_228303_a_(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);
/*     */     
/*  88 */     this.rightFoot = new ModelRenderer((Model)this);
/*  89 */     this.rightFoot.func_78793_a(0.0F, 5.9375F, 0.0625F);
/*  90 */     this.lowerRightLeg.func_78792_a(this.rightFoot);
/*  91 */     this.rightFoot.func_78784_a(13, 117).func_228303_a_(-1.5F, -0.25F, -3.0625F, 3.0F, 1.0F, 4.0F, 0.0F, false);
/*  92 */     this.rightFoot.func_78784_a(-7, 114).func_228303_a_(-2.5F, 0.75F, -7.0625F, 5.0F, 0.0F, 8.0F, 0.0F, false);
/*     */     
/*  94 */     this.head = new ModelRenderer((Model)this);
/*  95 */     this.head.func_78793_a(-0.0789F, 0.1837F, -4.0F);
/*     */     
/*  97 */     this.neck = new ModelRenderer((Model)this);
/*  98 */     this.neck.func_78793_a(3.0F, 0.0F, 1.0F);
/*  99 */     this.head.func_78792_a(this.neck);
/* 100 */     setRotationAngle(this.neck, 0.4363F, 0.0F, 0.0F);
/* 101 */     this.neck.func_78784_a(38, 25).func_228303_a_(-6.9211F, -4.61F, -4.4195F, 8.0F, 11.0F, 7.0F, 0.0F, false);
/*     */     
/* 103 */     this.mainHead = new ModelRenderer((Model)this);
/* 104 */     this.mainHead.func_78793_a(0.0789F, -2.9837F, -0.5F);
/* 105 */     this.head.func_78792_a(this.mainHead);
/* 106 */     this.mainHead.func_78784_a(0, 0).func_228303_a_(-5.5F, -8.5F, -9.0F, 11.0F, 11.0F, 12.0F, 0.0F, false);
/*     */     
/* 108 */     this.upperBeak = new ModelRenderer((Model)this);
/* 109 */     this.upperBeak.func_78793_a(0.0F, -2.0F, -9.0F);
/* 110 */     this.mainHead.func_78792_a(this.upperBeak);
/* 111 */     this.upperBeak.func_78784_a(0, 24).func_228303_a_(-5.0F, -1.5F, -5.0F, 10.0F, 3.0F, 6.0F, 0.0F, false);
/* 112 */     this.upperBeak.func_78784_a(0, 35).func_228303_a_(-5.5F, -1.5F, -11.0F, 11.0F, 3.0F, 6.0F, 0.0F, false);
/*     */     
/* 114 */     this.lowerBeak = new ModelRenderer((Model)this);
/* 115 */     this.lowerBeak.func_78793_a(0.0F, 0.5F, -8.0F);
/* 116 */     this.mainHead.func_78792_a(this.lowerBeak);
/* 117 */     this.lowerBeak.func_78784_a(0, 46).func_228303_a_(-4.5F, -1.0F, -11.5F, 9.0F, 2.0F, 11.0F, 0.0F, false);
/*     */   }
/*     */   private final ModelRenderer lowerRightLeg; private final ModelRenderer rightFoot; private final ModelRenderer head; private final ModelRenderer neck; private final ModelRenderer mainHead; private final ModelRenderer upperBeak; private final ModelRenderer lowerBeak; protected float quackAnimationProgress;
/*     */   
/*     */   public void prepareMobModel(T entity, float limbSwing, float limbSwingAmount, float partialTicks) {
/* 122 */     this.quackAnimationProgress = entity.getQuackAnimationProgress(partialTicks);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 129 */     this.head.field_78796_g = netHeadYaw * 0.017453292F;
/* 130 */     this.head.field_78795_f = headPitch * 0.017453292F;
/*     */     
/* 132 */     if (this.quackAnimationProgress < 1.0F) {
/* 133 */       this.upperBeak.field_78795_f = -MathHelper.func_76126_a(this.quackAnimationProgress * 2.0F * 3.1415927F) * 0.5F;
/* 134 */       this.upperBeak.field_78795_f = Math.min(this.upperBeak.field_78795_f, 0.0F);
/* 135 */       this.lowerBeak.field_78795_f = MathHelper.func_76126_a(this.quackAnimationProgress * 2.0F * 3.1415927F) * 0.5F;
/* 136 */       this.lowerBeak.field_78795_f = Math.max(this.lowerBeak.field_78795_f, 0.0F);
/*     */     } else {
/*     */       
/* 139 */       this.upperBeak.field_78795_f = 0.0F;
/* 140 */       this.lowerBeak.field_78795_f = 0.0F;
/*     */     } 
/*     */     
/* 143 */     Vector3d vector3d = entity.func_213322_ci();
/* 144 */     if (!entity.func_233570_aj_() && vector3d.field_72448_b < 0.0D) {
/* 145 */       this.leftWing.field_78795_f = (float)Math.toRadians(75.0D);
/* 146 */       this.leftWing.field_78808_h = (float)Math.toRadians(75.0D) + (float)(Math.sin((ageInTicks * 0.75F)) * 0.8999999761581421D);
/*     */       
/* 148 */       this.rightWing.field_78795_f = (float)Math.toRadians(75.0D);
/* 149 */       this.rightWing.field_78808_h = -((float)Math.toRadians(75.0D)) - (float)(Math.sin((ageInTicks * 0.75F)) * 0.8999999761581421D);
/*     */       
/* 151 */       this.tail.field_78795_f = (float)(Math.sin((ageInTicks * 1.2F)) * 0.4000000059604645D);
/*     */     } else {
/*     */       
/* 154 */       this.leftWing.field_78808_h = this.leftWing.field_78796_g = this.leftWing.field_78795_f = 0.0F;
/* 155 */       this.rightWing.field_78808_h = this.rightWing.field_78796_g = this.rightWing.field_78795_f = 0.0F;
/*     */ 
/*     */       
/* 158 */       float f = 1.0F;
/* 159 */       this.rightLeg.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.6F) * 1.5F * limbSwingAmount / f;
/* 160 */       this.leftLeg.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.6F + 3.1415927F) * 1.5F * limbSwingAmount / f;
/* 161 */       float tailSpeed = 0.2F;
/* 162 */       if (entity.func_184179_bs() != null && entity.func_184179_bs().func_70051_ag()) {
/* 163 */         tailSpeed = 0.6F;
/*     */         
/* 165 */         this.leftWing.field_78808_h = (float)Math.toRadians(55.0D);
/* 166 */         this.leftWing.field_78795_f = (float)(Math.sin((ageInTicks * 0.15F)) * 0.05000000074505806D);
/* 167 */         this.leftWing.field_78796_g = (float)Math.toRadians(25.0D);
/*     */         
/* 169 */         this.rightWing.field_78808_h = (float)Math.toRadians(-55.0D);
/* 170 */         this.rightWing.field_78795_f = (float)(Math.sin((ageInTicks * 0.15F)) * 0.05000000074505806D);
/* 171 */         this.rightWing.field_78796_g = (float)Math.toRadians(-25.0D);
/*     */       } 
/*     */       
/* 174 */       this.tail.field_78795_f = (float)(Math.sin((ageInTicks * tailSpeed)) * 0.10000000149011612D);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 181 */     if (this.field_217114_e) {
/* 182 */       matrixStack.func_227862_a_(0.5F, 0.5F, 0.5F);
/* 183 */       matrixStack.func_227861_a_(0.0D, 1.5D, 0.0D);
/*     */     } 
/*     */     
/* 186 */     this.body.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 187 */     this.leftLeg.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 188 */     this.rightLeg.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 189 */     this.head.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */   }
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 193 */     modelRenderer.field_78795_f = x;
/* 194 */     modelRenderer.field_78796_g = y;
/* 195 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\entities\mobs\animals\BigDuckModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */