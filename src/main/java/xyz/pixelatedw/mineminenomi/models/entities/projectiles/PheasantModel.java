/*     */ package xyz.pixelatedw.mineminenomi.models.entities.projectiles;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class PheasantModel
/*     */   extends EntityModel
/*     */ {
/*     */   private final ModelRenderer body1;
/*     */   private final ModelRenderer body2;
/*     */   private final ModelRenderer body3;
/*     */   private final ModelRenderer body4;
/*     */   private final ModelRenderer body5;
/*     */   private final ModelRenderer head;
/*     */   private final ModelRenderer tuft1;
/*     */   private final ModelRenderer tuft2;
/*     */   private final ModelRenderer tuft3;
/*     */   private final ModelRenderer beak1;
/*     */   private final ModelRenderer beak2;
/*     */   private final ModelRenderer beak3;
/*     */   private final ModelRenderer beak4;
/*     */   private final ModelRenderer tail1;
/*     */   private final ModelRenderer tail2;
/*     */   private final ModelRenderer tail3;
/*     */   private final ModelRenderer rightleg1;
/*     */   private final ModelRenderer rightleg2;
/*     */   private final ModelRenderer rightfoot1;
/*     */   private final ModelRenderer leftleg1;
/*     */   private final ModelRenderer leftleg2;
/*     */   private final ModelRenderer leftfoot1;
/*     */   private final ModelRenderer rightWing1;
/*     */   private final ModelRenderer rightWing3;
/*     */   private final ModelRenderer rightWing5;
/*     */   private final ModelRenderer rightWing2;
/*     */   private final ModelRenderer rightWing4;
/*     */   private final ModelRenderer leftWing1;
/*     */   private final ModelRenderer leftWing3;
/*     */   private final ModelRenderer leftWing5;
/*     */   private final ModelRenderer leftWing2;
/*     */   private final ModelRenderer leftWing4;
/*     */   
/*     */   public PheasantModel() {
/*  51 */     this.field_78090_t = 128;
/*  52 */     this.field_78089_u = 64;
/*     */     
/*  54 */     this.body1 = new ModelRenderer((Model)this);
/*  55 */     this.body1.func_78793_a(0.0F, 9.5F, 0.0F);
/*  56 */     this.body1.func_78784_a(0, 0).func_228303_a_(-3.5F, -4.0F, -5.0F, 7.0F, 7.0F, 10.0F, 0.0F, false);
/*     */     
/*  58 */     this.body2 = new ModelRenderer((Model)this);
/*  59 */     this.body2.func_78793_a(0.0F, 9.0F, -0.5F);
/*  60 */     this.body2.func_78784_a(0, 18).func_228303_a_(-3.0F, -3.0F, 5.0F, 6.0F, 6.0F, 2.0F, 0.0F, false);
/*     */     
/*  62 */     this.body3 = new ModelRenderer((Model)this);
/*  63 */     this.body3.func_78793_a(0.0F, 8.5F, -5.7F);
/*  64 */     setRotateAngle(this.body3, -0.0524F, 0.0F, 0.0F);
/*  65 */     this.body3.func_78784_a(0, 27).func_228303_a_(-3.0F, -3.0F, -1.0F, 6.0F, 6.0F, 2.0F, 0.0F, false);
/*     */     
/*  67 */     this.body4 = new ModelRenderer((Model)this);
/*  68 */     this.body4.func_78793_a(0.0F, 8.0F, -7.5F);
/*  69 */     setRotateAngle(this.body4, -0.0524F, 0.0F, 0.0F);
/*  70 */     this.body4.func_78784_a(0, 36).func_228303_a_(-2.5F, -2.5F, -1.0F, 5.0F, 5.0F, 2.0F, 0.0F, false);
/*     */     
/*  72 */     this.body5 = new ModelRenderer((Model)this);
/*  73 */     this.body5.func_78793_a(0.0F, 8.5F, -8.7F);
/*  74 */     this.body5.func_78784_a(0, 44).func_228303_a_(-2.0F, -2.5F, -1.5F, 4.0F, 4.0F, 2.0F, 0.0F, false);
/*     */     
/*  76 */     this.head = new ModelRenderer((Model)this);
/*  77 */     this.head.func_78793_a(0.0F, 8.0F, -11.5F);
/*  78 */     setRotateAngle(this.head, 0.0873F, 0.0F, 0.0F);
/*  79 */     this.head.func_78784_a(0, 51).func_228303_a_(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, 0.0F, false);
/*     */     
/*  81 */     this.tuft1 = new ModelRenderer((Model)this);
/*  82 */     this.tuft1.func_78793_a(0.0F, 7.0F, -12.5F);
/*  83 */     setRotateAngle(this.tuft1, 0.6981F, 0.0F, 0.0F);
/*  84 */     this.tuft1.func_78784_a(13, 51).func_228303_a_(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
/*     */     
/*  86 */     this.tuft2 = new ModelRenderer((Model)this);
/*  87 */     this.tuft2.func_78793_a(0.0F, 7.0F, -12.5F);
/*  88 */     setRotateAngle(this.tuft2, 0.6981F, 0.0F, -0.3491F);
/*  89 */     this.tuft2.func_78784_a(22, 51).func_228303_a_(-1.0F, -0.5F, 0.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/*  91 */     this.tuft3 = new ModelRenderer((Model)this);
/*  92 */     this.tuft3.func_78793_a(0.0F, 7.0F, -12.5F);
/*  93 */     setRotateAngle(this.tuft3, 0.6981F, 0.0F, 0.3491F);
/*  94 */     this.tuft3.func_78784_a(29, 51).func_228303_a_(0.0F, -0.5F, 0.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/*  96 */     this.beak1 = new ModelRenderer((Model)this);
/*  97 */     this.beak1.func_78793_a(0.0F, 8.4F, -13.5F);
/*  98 */     setRotateAngle(this.beak1, 0.2665F, -0.2618F, -0.0181F);
/*  99 */     this.beak1.func_78784_a(0, 58).func_228303_a_(-0.8F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 101 */     this.beak2 = new ModelRenderer((Model)this);
/* 102 */     this.beak2.func_78793_a(0.0F, 8.4F, -13.5F);
/* 103 */     setRotateAngle(this.beak2, 0.2665F, 0.2618F, 0.0181F);
/* 104 */     this.beak2.func_78784_a(0, 61).func_228303_a_(-0.2F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 106 */     this.beak3 = new ModelRenderer((Model)this);
/* 107 */     this.beak3.func_78793_a(0.0F, 8.3F, -13.5F);
/* 108 */     setRotateAngle(this.beak3, 0.2618F, 0.0F, 0.0F);
/* 109 */     this.beak3.func_78784_a(7, 58).func_228303_a_(-0.5F, -0.5F, -1.2F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 111 */     this.beak4 = new ModelRenderer((Model)this);
/* 112 */     this.beak4.func_78793_a(0.0F, 8.9F, -13.2F);
/* 113 */     setRotateAngle(this.beak4, -0.1745F, 0.0F, 0.0F);
/* 114 */     this.beak4.func_78784_a(7, 61).func_228303_a_(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 116 */     this.tail1 = new ModelRenderer((Model)this);
/* 117 */     this.tail1.func_78793_a(0.0F, 8.0F, 6.0F);
/* 118 */     setRotateAngle(this.tail1, 0.2618F, 0.0F, 0.0F);
/* 119 */     this.tail1.func_78784_a(17, 18).func_228303_a_(-1.0F, -0.5F, 0.0F, 2.0F, 1.0F, 8.0F, 0.0F, false);
/*     */     
/* 121 */     this.tail2 = new ModelRenderer((Model)this);
/* 122 */     this.tail2.func_78793_a(0.0F, 8.0F, 6.0F);
/* 123 */     setRotateAngle(this.tail2, 0.2618F, -0.2618F, 0.0F);
/* 124 */     this.tail2.func_78784_a(38, 18).func_228303_a_(-3.2F, -0.5F, 0.0F, 3.0F, 1.0F, 7.0F, 0.0F, false);
/*     */     
/* 126 */     this.tail3 = new ModelRenderer((Model)this);
/* 127 */     this.tail3.func_78793_a(0.0F, 8.0F, 6.0F);
/* 128 */     setRotateAngle(this.tail3, 0.2618F, 0.2618F, 0.0F);
/* 129 */     this.tail3.func_78784_a(59, 18).func_228303_a_(0.2F, -0.5F, 0.0F, 3.0F, 1.0F, 7.0F, 0.0F, false);
/*     */     
/* 131 */     this.rightleg1 = new ModelRenderer((Model)this);
/* 132 */     this.rightleg1.func_78793_a(-2.0F, 10.5F, 2.0F);
/* 133 */     setRotateAngle(this.rightleg1, 0.7854F, -0.1745F, 0.0F);
/* 134 */     this.rightleg1.func_78784_a(35, 0).func_228303_a_(-1.0F, 0.0F, -1.5F, 3.0F, 4.0F, 3.0F, 0.0F, false);
/*     */     
/* 136 */     this.rightleg2 = new ModelRenderer((Model)this);
/* 137 */     this.rightleg2.func_78793_a(-2.0F, 10.5F, 2.0F);
/* 138 */     setRotateAngle(this.rightleg2, 0.7854F, -0.1745F, 0.0F);
/* 139 */     this.rightleg2.func_78784_a(48, 0).func_228303_a_(-0.5F, 4.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
/*     */     
/* 141 */     this.rightfoot1 = new ModelRenderer((Model)this);
/* 142 */     this.rightfoot1.func_78793_a(-1.8F, 14.8F, 3.9F);
/* 143 */     setRotateAngle(this.rightfoot1, 1.309F, -0.1745F, 0.0F);
/* 144 */     this.rightfoot1.func_78784_a(48, 5).func_228303_a_(-1.0F, 1.0F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 146 */     this.leftleg1 = new ModelRenderer((Model)this);
/* 147 */     this.leftleg1.func_78793_a(2.0F, 10.5F, 2.0F);
/* 148 */     setRotateAngle(this.leftleg1, 0.7854F, 0.1745F, 0.0F);
/* 149 */     this.leftleg1.func_78784_a(35, 0).func_228303_a_(-2.0F, 0.0F, -1.5F, 3.0F, 4.0F, 3.0F, 0.0F, false);
/*     */     
/* 151 */     this.leftleg2 = new ModelRenderer((Model)this);
/* 152 */     this.leftleg2.func_78793_a(2.0F, 10.5F, 2.0F);
/* 153 */     setRotateAngle(this.leftleg2, 0.7854F, 0.1745F, 0.0F);
/* 154 */     this.leftleg2.func_78784_a(48, 0).func_228303_a_(-1.5F, 4.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
/*     */     
/* 156 */     this.leftfoot1 = new ModelRenderer((Model)this);
/* 157 */     this.leftfoot1.func_78793_a(1.8F, 14.8F, 3.9F);
/* 158 */     setRotateAngle(this.leftfoot1, 1.309F, 0.1745F, 0.0F);
/* 159 */     this.leftfoot1.func_78784_a(48, 5).func_228303_a_(-1.0F, 1.0F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 161 */     this.rightWing1 = new ModelRenderer((Model)this);
/* 162 */     this.rightWing1.func_78793_a(-2.9F, 6.1F, -4.0F);
/* 163 */     setRotateAngle(this.rightWing1, -0.0373F, -0.2592F, 0.1445F);
/* 164 */     this.rightWing1.func_78784_a(57, 0).func_228303_a_(-4.0F, -0.5F, -0.5F, 4.0F, 2.0F, 2.0F, 0.0F, false);
/*     */     
/* 166 */     this.rightWing3 = new ModelRenderer((Model)this);
/* 167 */     this.rightWing3.func_78793_a(-12.0F, -0.7F, -1.0F);
/* 168 */     this.rightWing1.func_78792_a(this.rightWing3);
/* 169 */     setRotateAngle(this.rightWing3, 0.0F, 0.2443F, 0.0F);
/* 170 */     this.rightWing3.func_78784_a(91, 0).func_228303_a_(-6.5F, 0.0F, 0.0F, 6.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 172 */     this.rightWing5 = new ModelRenderer((Model)this);
/* 173 */     this.rightWing5.func_78793_a(0.0F, 0.0F, 0.0F);
/* 174 */     this.rightWing3.func_78792_a(this.rightWing5);
/* 175 */     setRotateAngle(this.rightWing5, -0.0175F, 0.0F, 0.0F);
/* 176 */     this.rightWing5.func_78784_a(94, 5).func_228303_a_(-6.2F, 0.5F, 1.0F, 6.0F, 0.0F, 6.0F, 0.0F, false);
/*     */     
/* 178 */     this.rightWing2 = new ModelRenderer((Model)this);
/* 179 */     this.rightWing2.func_78793_a(-3.1F, -0.1F, -0.5F);
/* 180 */     this.rightWing1.func_78792_a(this.rightWing2);
/* 181 */     setRotateAngle(this.rightWing2, -0.0358F, -0.1004F, 0.0607F);
/* 182 */     this.rightWing2.func_78784_a(70, 0).func_228303_a_(-9.5F, 0.0F, 0.5F, 9.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 184 */     this.rightWing4 = new ModelRenderer((Model)this);
/* 185 */     this.rightWing4.func_78793_a(0.0F, 0.0F, 0.0F);
/* 186 */     this.rightWing2.func_78792_a(this.rightWing4);
/* 187 */     setRotateAngle(this.rightWing4, 0.0175F, 0.0456F, 0.0F);
/* 188 */     this.rightWing4.func_78784_a(57, 5).func_228303_a_(-8.8F, 0.5F, 1.0F, 12.0F, 0.0F, 6.0F, 0.0F, false);
/*     */     
/* 190 */     this.leftWing1 = new ModelRenderer((Model)this);
/* 191 */     this.leftWing1.func_78793_a(2.9F, 6.1F, -4.0F);
/* 192 */     setRotateAngle(this.leftWing1, -0.0374F, 0.2592F, -0.1445F);
/* 193 */     this.leftWing1.func_78784_a(57, 0).func_228303_a_(0.0F, -0.5F, -0.5F, 4.0F, 2.0F, 2.0F, 0.0F, false);
/*     */     
/* 195 */     this.leftWing3 = new ModelRenderer((Model)this);
/* 196 */     this.leftWing3.func_78793_a(12.0F, -0.6F, -1.0F);
/* 197 */     this.leftWing1.func_78792_a(this.leftWing3);
/* 198 */     setRotateAngle(this.leftWing3, 0.0F, -0.2443F, 0.0F);
/* 199 */     this.leftWing3.func_78784_a(91, 0).func_228303_a_(0.5F, 0.0F, 0.0F, 6.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 201 */     this.leftWing5 = new ModelRenderer((Model)this);
/* 202 */     this.leftWing5.func_78793_a(-0.15F, -0.1F, 0.0F);
/* 203 */     this.leftWing3.func_78792_a(this.leftWing5);
/* 204 */     setRotateAngle(this.leftWing5, -0.0175F, 0.0F, 0.0F);
/* 205 */     this.leftWing5.func_78784_a(94, 5).func_228303_a_(0.5F, 0.5F, 1.0F, 6.0F, 0.0F, 6.0F, 0.0F, false);
/*     */     
/* 207 */     this.leftWing2 = new ModelRenderer((Model)this);
/* 208 */     this.leftWing2.func_78793_a(3.0F, -0.1F, -0.5F);
/* 209 */     this.leftWing1.func_78792_a(this.leftWing2);
/* 210 */     setRotateAngle(this.leftWing2, -0.0358F, 0.1004F, -0.0607F);
/* 211 */     this.leftWing2.func_78784_a(70, 0).func_228303_a_(0.5F, 0.0F, 0.5F, 9.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 213 */     this.leftWing4 = new ModelRenderer((Model)this);
/* 214 */     this.leftWing4.func_78793_a(0.1F, -0.1F, 0.0F);
/* 215 */     this.leftWing2.func_78792_a(this.leftWing4);
/* 216 */     setRotateAngle(this.leftWing4, 0.0175F, -0.0456F, 0.0F);
/* 217 */     this.leftWing4.func_78784_a(57, 5).func_228303_a_(-2.8F, 0.5F, 1.0F, 12.0F, 0.0F, 6.0F, 0.0F, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225597_a_(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void setupAnim(float limbSwing, float limbSwingAmount, float ageInTicks, float headYaw, float headPitch, float scaleFactor, Entity ent) {
/* 228 */     double[] animationWingMovement = { 5.0D, 10.0D, 15.0D, 20.0D, 25.0D, 30.0D, 35.0D, 30.0D, 25.0D, 20.0D, 15.0D, 10.0D, 5.0D, 0.0D, -5.0D, -10.0D, -15.0D, -10.0D, -5.0D, 0.0D, 5.0D };
/*     */     
/* 230 */     int cycleIndexFly = (int)(ent.field_70173_aa * 1.85D % animationWingMovement.length);
/*     */     
/* 232 */     if (!Minecraft.func_71410_x().func_147113_T()) {
/*     */       
/* 234 */       this.rightWing1.field_78808_h = degToRad(animationWingMovement[cycleIndexFly]);
/* 235 */       this.leftWing1.field_78808_h = degToRad(animationWingMovement[cycleIndexFly]) * -1.0F;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected float degToRad(double degrees) {
/* 241 */     return (float)(degrees * Math.PI / 180.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
/* 246 */     model.field_78795_f = x;
/* 247 */     model.field_78796_g = y;
/* 248 */     model.field_78808_h = z;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 254 */     matrixStack.func_227860_a_();
/* 255 */     matrixStack.func_227861_a_(0.0D, -0.75D, 0.0D);
/*     */     
/* 257 */     this.body3.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 258 */     this.tuft3.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 259 */     this.rightleg2.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 260 */     this.body1.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 261 */     this.beak4.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 262 */     this.beak1.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 263 */     this.leftfoot1.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 264 */     this.rightleg1.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 265 */     this.tuft2.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 266 */     this.leftleg1.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 267 */     this.head.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 268 */     this.beak2.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 269 */     this.beak3.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 270 */     this.rightfoot1.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 271 */     this.body2.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 272 */     this.tail3.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 273 */     this.tail1.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 274 */     this.tail2.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 275 */     this.leftleg2.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 276 */     this.tuft1.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 277 */     this.rightWing1.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 278 */     this.body4.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 279 */     this.body5.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 280 */     this.leftWing1.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 281 */     matrixStack.func_227865_b_();
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\entities\projectiles\PheasantModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */