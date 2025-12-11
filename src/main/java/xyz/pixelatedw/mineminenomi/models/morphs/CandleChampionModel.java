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
/*     */ 
/*     */ public class CandleChampionModel<T extends LivingEntity>
/*     */   extends MorphModel<T>
/*     */   implements IHasArm {
/*     */   private final ModelRenderer body;
/*     */   private final ModelRenderer spikes;
/*     */   private final ModelRenderer leftSpikes;
/*     */   private final ModelRenderer leftSpike1;
/*     */   private final ModelRenderer leftSpike2;
/*     */   private final ModelRenderer leftSpike3;
/*     */   private final ModelRenderer leftSpike4;
/*     */   private final ModelRenderer rightSpikes;
/*     */   private final ModelRenderer rightSpike1;
/*     */   private final ModelRenderer rightSpike2;
/*     */   private final ModelRenderer rightSpike3;
/*     */   private final ModelRenderer rightSpike4;
/*     */   private final ModelRenderer rightArm;
/*     */   private final ModelRenderer leftArm;
/*     */   private final ModelRenderer leftLeg;
/*     */   private final ModelRenderer leftLeg2_r1;
/*     */   private final ModelRenderer leftLeg1_r1;
/*     */   private final ModelRenderer rightLeg;
/*     */   private final ModelRenderer rightLeg2_r1;
/*     */   private final ModelRenderer rightLeg1_r1;
/*     */   
/*     */   public CandleChampionModel() {
/*  40 */     super(1.0F);
/*  41 */     this.field_78090_t = 128;
/*  42 */     this.field_78089_u = 128;
/*     */     
/*  44 */     this.body = new ModelRenderer((Model)this);
/*  45 */     this.body.func_78793_a(0.0F, 24.0F, 0.0F);
/*  46 */     this.body.func_78784_a(0, 0).func_228303_a_(-8.5F, -31.1F, -8.5F, 17.0F, 11.0F, 12.0F, 0.0F, false);
/*  47 */     this.body.func_78784_a(0, 23).func_228303_a_(-4.5F, -32.1F, -6.5F, 9.0F, 10.0F, 11.0F, 0.0F, false);
/*  48 */     this.body.func_78784_a(0, 44).func_228303_a_(-11.5F, -30.5F, -8.0F, 3.0F, 10.0F, 11.0F, 0.0F, false);
/*  49 */     this.body.func_78784_a(0, 44).func_228303_a_(8.5F, -30.5F, -8.0F, 3.0F, 10.0F, 11.0F, 0.0F, false);
/*  50 */     this.body.func_78784_a(46, 23).func_228303_a_(-3.0F, -26.1F, -7.0F, 6.0F, 7.0F, 9.0F, 0.0F, false);
/*  51 */     this.body.func_78784_a(67, 21).func_228303_a_(-2.5F, -20.1F, -5.5F, 5.0F, 5.0F, 6.0F, 0.0F, false);
/*  52 */     this.body.func_78784_a(46, 0).func_228303_a_(-4.5F, -15.1F, -6.5F, 9.0F, 1.0F, 8.0F, 0.0F, false);
/*  53 */     this.body.func_78784_a(0, 66).func_228303_a_(-4.0F, -15.0F, -6.0F, 8.0F, 4.0F, 7.0F, 0.0F, false);
/*  54 */     this.body.func_78784_a(28, 54).func_228303_a_(-2.5F, -13.5F, -6.5F, 5.0F, 3.0F, 8.0F, 0.0F, false);
/*     */     
/*  56 */     this.spikes = new ModelRenderer((Model)this);
/*  57 */     this.spikes.func_78793_a(0.0F, 0.0F, 0.0F);
/*  58 */     this.body.func_78792_a(this.spikes);
/*     */     
/*  60 */     this.leftSpikes = new ModelRenderer((Model)this);
/*  61 */     this.leftSpikes.func_78793_a(0.0F, 0.0F, 0.0F);
/*  62 */     this.spikes.func_78792_a(this.leftSpikes);
/*     */     
/*  64 */     this.leftSpike1 = new ModelRenderer((Model)this);
/*  65 */     this.leftSpike1.func_78793_a(-10.0F, -32.5F, -2.5F);
/*  66 */     this.leftSpikes.func_78792_a(this.leftSpike1);
/*  67 */     this.leftSpike1.func_78784_a(0, 8).func_228303_a_(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
/*  68 */     this.leftSpike1.func_78784_a(8, 8).func_228303_a_(-0.5F, -2.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/*  70 */     this.leftSpike2 = new ModelRenderer((Model)this);
/*  71 */     this.leftSpike2.func_78793_a(-10.0F, -18.5F, -2.5F);
/*  72 */     this.leftSpikes.func_78792_a(this.leftSpike2);
/*  73 */     this.leftSpike2.func_78784_a(0, 8).func_228303_a_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
/*  74 */     this.leftSpike2.func_78784_a(8, 8).func_228303_a_(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/*  76 */     this.leftSpike3 = new ModelRenderer((Model)this);
/*  77 */     this.leftSpike3.func_78793_a(-10.0F, -25.5F, -9.5F);
/*  78 */     this.leftSpikes.func_78792_a(this.leftSpike3);
/*  79 */     setRotationAngle(this.leftSpike3, -1.5708F, 0.0F, 0.0F);
/*  80 */     this.leftSpike3.func_78784_a(0, 8).func_228303_a_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
/*  81 */     this.leftSpike3.func_78784_a(8, 8).func_228303_a_(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/*  83 */     this.leftSpike4 = new ModelRenderer((Model)this);
/*  84 */     this.leftSpike4.func_78793_a(-10.0F, -25.5F, 4.5F);
/*  85 */     this.leftSpikes.func_78792_a(this.leftSpike4);
/*  86 */     setRotationAngle(this.leftSpike4, 1.5708F, 0.0F, 0.0F);
/*  87 */     this.leftSpike4.func_78784_a(0, 8).func_228303_a_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
/*  88 */     this.leftSpike4.func_78784_a(8, 8).func_228303_a_(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/*  90 */     this.rightSpikes = new ModelRenderer((Model)this);
/*  91 */     this.rightSpikes.func_78793_a(0.0F, 0.0F, 0.0F);
/*  92 */     this.spikes.func_78792_a(this.rightSpikes);
/*     */     
/*  94 */     this.rightSpike1 = new ModelRenderer((Model)this);
/*  95 */     this.rightSpike1.func_78793_a(10.0F, -32.5F, -2.5F);
/*  96 */     this.rightSpikes.func_78792_a(this.rightSpike1);
/*  97 */     this.rightSpike1.func_78784_a(0, 8).func_228303_a_(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
/*  98 */     this.rightSpike1.func_78784_a(8, 8).func_228303_a_(-0.5F, -2.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/* 100 */     this.rightSpike2 = new ModelRenderer((Model)this);
/* 101 */     this.rightSpike2.func_78793_a(10.0F, -18.5F, -2.5F);
/* 102 */     this.rightSpikes.func_78792_a(this.rightSpike2);
/* 103 */     this.rightSpike2.func_78784_a(0, 8).func_228303_a_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
/* 104 */     this.rightSpike2.func_78784_a(8, 8).func_228303_a_(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/* 106 */     this.rightSpike3 = new ModelRenderer((Model)this);
/* 107 */     this.rightSpike3.func_78793_a(10.0F, -25.5F, -9.5F);
/* 108 */     this.rightSpikes.func_78792_a(this.rightSpike3);
/* 109 */     setRotationAngle(this.rightSpike3, -1.5708F, 0.0F, 0.0F);
/* 110 */     this.rightSpike3.func_78784_a(0, 8).func_228303_a_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
/* 111 */     this.rightSpike3.func_78784_a(8, 8).func_228303_a_(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/* 113 */     this.rightSpike4 = new ModelRenderer((Model)this);
/* 114 */     this.rightSpike4.func_78793_a(10.0F, -25.5F, 4.5F);
/* 115 */     this.rightSpikes.func_78792_a(this.rightSpike4);
/* 116 */     setRotationAngle(this.rightSpike4, 1.5708F, 0.0F, 0.0F);
/* 117 */     this.rightSpike4.func_78784_a(0, 8).func_228303_a_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
/* 118 */     this.rightSpike4.func_78784_a(8, 8).func_228303_a_(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/* 120 */     this.rightArm = new ModelRenderer((Model)this);
/* 121 */     this.rightArm.func_78793_a(12.0F, -0.5F, -2.5F);
/* 122 */     setRotationAngle(this.rightArm, 0.0F, 0.0F, -0.3927F);
/* 123 */     this.rightArm.func_78784_a(0, 44).func_228303_a_(-0.5F, -2.5F, -0.5F, 1.0F, 8.0F, 1.0F, 0.0F, false);
/* 124 */     this.rightArm.func_78784_a(17, 49).func_228303_a_(-1.5F, 5.5F, -1.5F, 3.0F, 2.0F, 3.0F, 0.0F, false);
/* 125 */     this.rightArm.func_78784_a(48, 70).func_228303_a_(-3.0F, 7.5F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);
/* 126 */     this.rightArm.func_78784_a(35, 68).func_228303_a_(-3.5F, 8.5F, -2.0F, 1.0F, 4.0F, 4.0F, 0.0F, false);
/*     */     
/* 128 */     this.leftArm = new ModelRenderer((Model)this);
/* 129 */     this.leftArm.func_78793_a(-11.5F, -1.5F, -2.5F);
/* 130 */     setRotationAngle(this.leftArm, 0.0F, 0.0F, 0.3927F);
/* 131 */     this.leftArm.func_78784_a(0, 44).func_228303_a_(-0.5F, -1.5F, -0.5F, 1.0F, 8.0F, 1.0F, 0.0F, false);
/* 132 */     this.leftArm.func_78784_a(17, 49).func_228303_a_(-1.5F, 6.5F, -1.5F, 3.0F, 2.0F, 3.0F, 0.0F, false);
/* 133 */     this.leftArm.func_78784_a(48, 70).func_228303_a_(-3.0F, 8.5F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);
/* 134 */     this.leftArm.func_78784_a(35, 68).func_228303_a_(2.5F, 9.5F, -2.0F, 1.0F, 4.0F, 4.0F, 0.0F, false);
/*     */     
/* 136 */     this.leftLeg = new ModelRenderer((Model)this);
/* 137 */     this.leftLeg.func_78793_a(-3.5F, 11.0F, -2.5F);
/* 138 */     this.leftLeg.func_78784_a(17, 44).func_228303_a_(-3.5F, 5.0F, -1.5F, 3.0F, 2.0F, 3.0F, 0.0F, false);
/* 139 */     this.leftLeg.func_78784_a(54, 58).func_228303_a_(-5.0F, 7.0F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);
/* 140 */     this.leftLeg.func_78784_a(47, 45).func_228303_a_(-5.0F, 7.0F, 5.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);
/* 141 */     this.leftLeg.func_78784_a(72, 64).func_228303_a_(-4.5F, 8.0F, 2.0F, 5.0F, 5.0F, 6.0F, 0.0F, false);
/*     */     
/* 143 */     this.leftLeg2_r1 = new ModelRenderer((Model)this);
/* 144 */     this.leftLeg2_r1.func_78793_a(-1.4463F, 3.8114F, 0.0F);
/* 145 */     this.leftLeg.func_78792_a(this.leftLeg2_r1);
/* 146 */     setRotationAngle(this.leftLeg2_r1, 0.0F, 0.0F, 0.1745F);
/* 147 */     this.leftLeg2_r1.func_78784_a(35, 45).func_228303_a_(-0.5F, -2.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);
/*     */     
/* 149 */     this.leftLeg1_r1 = new ModelRenderer((Model)this);
/* 150 */     this.leftLeg1_r1.func_78793_a(-0.293F, 0.1536F, 0.0F);
/* 151 */     this.leftLeg.func_78792_a(this.leftLeg1_r1);
/* 152 */     setRotationAngle(this.leftLeg1_r1, 0.0F, 0.0F, 0.4363F);
/* 153 */     this.leftLeg1_r1.func_78784_a(30, 45).func_228303_a_(-0.5F, -2.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);
/*     */     
/* 155 */     this.rightLeg = new ModelRenderer((Model)this);
/* 156 */     this.rightLeg.func_78793_a(3.5F, 11.0F, -2.5F);
/* 157 */     this.rightLeg.func_78784_a(17, 44).func_228303_a_(0.5F, 5.0F, -1.5F, 3.0F, 2.0F, 3.0F, 0.0F, false);
/* 158 */     this.rightLeg.func_78784_a(54, 58).func_228303_a_(-1.0F, 7.0F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);
/* 159 */     this.rightLeg.func_78784_a(47, 45).func_228303_a_(-1.0F, 7.0F, 5.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);
/* 160 */     this.rightLeg.func_78784_a(72, 64).func_228303_a_(-0.5F, 8.0F, 2.0F, 5.0F, 5.0F, 6.0F, 0.0F, false);
/*     */     
/* 162 */     this.rightLeg2_r1 = new ModelRenderer((Model)this);
/* 163 */     this.rightLeg2_r1.func_78793_a(1.6087F, 3.9529F, 0.0F);
/* 164 */     this.rightLeg.func_78792_a(this.rightLeg2_r1);
/* 165 */     setRotationAngle(this.rightLeg2_r1, 0.0F, 0.0F, -0.1745F);
/* 166 */     this.rightLeg2_r1.func_78784_a(35, 45).func_228303_a_(-0.5F, -2.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);
/*     */     
/* 168 */     this.rightLeg1_r1 = new ModelRenderer((Model)this);
/* 169 */     this.rightLeg1_r1.func_78793_a(0.4555F, 0.2951F, 0.0F);
/* 170 */     this.rightLeg.func_78792_a(this.rightLeg1_r1);
/* 171 */     setRotationAngle(this.rightLeg1_r1, 0.0F, 0.0F, -0.4363F);
/* 172 */     this.rightLeg1_r1.func_78784_a(30, 45).func_228303_a_(-0.5F, -2.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 178 */     matrixStack.func_227860_a_();
/* 179 */     matrixStack.func_227862_a_(2.0F, 2.0F, 2.0F);
/* 180 */     matrixStack.func_227861_a_(0.0D, -0.7D, 0.0D);
/* 181 */     this.body.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/* 182 */     this.rightArm.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/* 183 */     this.leftArm.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/* 184 */     this.leftLeg.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/* 185 */     this.rightLeg.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/* 186 */     matrixStack.func_227865_b_();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 193 */     float f = 1.0F;
/* 194 */     this.rightArm.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.6662F + 3.1415927F) * 0.8F * limbSwingAmount * 0.5F / f;
/* 195 */     this.leftArm.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.6662F) * 0.8F * limbSwingAmount * 0.5F / f;
/* 196 */     this.rightLeg.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.6662F) * 0.7F * limbSwingAmount / f;
/* 197 */     this.leftLeg.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.6662F + 3.1415927F) * 0.7F * limbSwingAmount / f;
/* 198 */     if (!entity.func_184614_ca().func_190926_b()) {
/* 199 */       this.rightArm.field_78795_f += -0.15F;
/*     */     }
/*     */     
/* 202 */     this.field_217112_c = ((LivingEntity)entity).field_70733_aJ;
/* 203 */     if (this.field_217112_c > 0.0F) {
/*     */       
/* 205 */       this.body.field_78796_g = MathHelper.func_76126_a(MathHelper.func_76129_c(this.field_217112_c) * 6.2831855F) * 0.2F;
/* 206 */       this.leftArm.field_78798_e = -MathHelper.func_76126_a(this.body.field_78796_g) * 5.0F;
/* 207 */       this.leftArm.field_78796_g += this.body.field_78796_g;
/* 208 */       this.leftArm.field_78795_f += this.body.field_78796_g;
/* 209 */       float f1 = 1.0F - this.field_217112_c;
/* 210 */       f1 *= f1;
/* 211 */       f1 *= f1;
/* 212 */       f1 = 1.0F - f1;
/* 213 */       float f2 = MathHelper.func_76126_a(f1 * 3.1415927F);
/* 214 */       float f3 = MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * 0.75F;
/* 215 */       this.rightArm.field_78795_f = -((float)(this.rightArm.field_78795_f - f2 * 1.5D + f3));
/* 216 */       this.rightArm.field_78796_g += this.body.field_78796_g * 2.0F;
/* 217 */       this.rightArm.field_78808_h += MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * -0.9F;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 223 */     modelRenderer.field_78795_f = x;
/* 224 */     modelRenderer.field_78796_g = y;
/* 225 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
/* 231 */     if (side == HandSide.RIGHT) {
/*     */       
/* 233 */       matrixStack.func_227861_a_(-1.5D, -0.1D, 0.1D);
/* 234 */       matrixStack.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(10.0F));
/* 235 */       this.rightArm.func_228309_a_(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     }
/*     */     else {
/*     */       
/* 239 */       matrixStack.func_227861_a_(1.5D, -0.1D, 0.1D);
/* 240 */       matrixStack.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(-10.0F));
/* 241 */       this.leftArm.func_228309_a_(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
/* 248 */     if (side == HandSide.RIGHT) {
/*     */       
/* 250 */       matrixStack.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(30.0F));
/* 251 */       matrixStack.func_227861_a_(0.2D, -1.2D, 0.3D);
/* 252 */       matrixStack.func_227862_a_(1.5F, 1.5F, 1.5F);
/* 253 */       matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(130.0F));
/* 254 */       this.rightLeg.func_228309_a_(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     }
/*     */     else {
/*     */       
/* 258 */       matrixStack.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(30.0F));
/* 259 */       matrixStack.func_227861_a_(-0.3D, -1.0D, -0.3D);
/* 260 */       matrixStack.func_227862_a_(1.5F, 1.5F, 1.5F);
/* 261 */       matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(-130.0F));
/* 262 */       this.leftLeg.func_228309_a_(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225599_a_(HandSide side, MatrixStack matrixStack) {
/* 269 */     super.func_225599_a_(side, matrixStack);
/* 270 */     matrixStack.func_227861_a_((side == HandSide.RIGHT) ? -0.6D : 0.6D, -0.5D, -0.2D);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\morphs\CandleChampionModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */