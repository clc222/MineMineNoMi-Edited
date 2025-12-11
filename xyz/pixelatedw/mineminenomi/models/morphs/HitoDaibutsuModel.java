/*     */ package xyz.pixelatedw.mineminenomi.models.morphs;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.renderer.entity.model.IHasArm;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.HandSide;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.vector.Vector3f;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphModel;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ 
/*     */ public class HitoDaibutsuModel<T extends LivingEntity>
/*     */   extends MorphModel<T> implements IHasArm {
/*     */   private final ModelRenderer rightArm;
/*     */   private final ModelRenderer rightLeg;
/*     */   private final ModelRenderer head;
/*     */   private final ModelRenderer body;
/*     */   private final ModelRenderer leftArm;
/*     */   private final ModelRenderer leftLeg;
/*     */   
/*     */   public HitoDaibutsuModel() {
/*  27 */     super(1.0F);
/*  28 */     this.field_78090_t = 64;
/*  29 */     this.field_78089_u = 64;
/*     */     
/*  31 */     this.rightArm = new ModelRenderer((Model)this);
/*  32 */     this.rightArm.func_78793_a(-9.5F, -2.0F, 0.0F);
/*  33 */     this.rightArm.func_78784_a(40, 16).func_228303_a_(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
/*     */     
/*  35 */     this.rightLeg = new ModelRenderer((Model)this);
/*  36 */     this.rightLeg.func_78793_a(-3.6F, 15.0F, 0.0F);
/*  37 */     this.rightLeg.func_78784_a(0, 16).func_228303_a_(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
/*     */     
/*  39 */     this.head = new ModelRenderer((Model)this);
/*  40 */     this.head.func_78793_a(0.0F, -5.4F, 0.0F);
/*  41 */     this.head.func_78784_a(0, 0).func_228303_a_(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
/*     */     
/*  43 */     this.body = new ModelRenderer((Model)this);
/*  44 */     this.body.func_78793_a(0.0F, -5.4F, 0.0F);
/*  45 */     this.body.func_78784_a(16, 16).func_228303_a_(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F, false);
/*     */     
/*  47 */     this.leftArm = new ModelRenderer((Model)this);
/*  48 */     this.leftArm.func_78793_a(9.5F, -2.0F, 0.0F);
/*  49 */     this.leftArm.func_78784_a(32, 48).func_228303_a_(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
/*     */     
/*  51 */     this.leftLeg = new ModelRenderer((Model)this);
/*  52 */     this.leftLeg.func_78793_a(3.2F, 15.0F, 0.0F);
/*  53 */     this.leftLeg.func_78784_a(16, 48).func_228303_a_(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
/*     */     
/*  55 */     this.field_78115_e = this.body;
/*  56 */     this.field_78116_c = this.head;
/*  57 */     this.field_178723_h = this.rightArm;
/*  58 */     this.field_178724_i = this.leftArm;
/*  59 */     this.field_178721_j = this.rightLeg;
/*  60 */     this.field_178722_k = this.leftLeg;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/*  66 */     float scale = 4.5F;
/*  67 */     matrixStack.func_227860_a_();
/*  68 */     matrixStack.func_227862_a_(scale, scale, scale);
/*  69 */     matrixStack.func_227861_a_(0.0D, (scale != 4.5D) ? -1.0D : -0.8D, 0.0D);
/*     */     
/*  71 */     matrixStack.func_227860_a_();
/*  72 */     matrixStack.func_227862_a_(1.1F, 1.15F, 1.15F);
/*  73 */     matrixStack.func_227861_a_(0.0D, -0.25D, 0.0D);
/*  74 */     this.head.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*  75 */     matrixStack.func_227865_b_();
/*     */     
/*  77 */     matrixStack.func_227860_a_();
/*  78 */     matrixStack.func_227862_a_(2.5F, 2.0F, 4.0F);
/*  79 */     this.body.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*  80 */     matrixStack.func_227865_b_();
/*     */     
/*  82 */     matrixStack.func_227860_a_();
/*  83 */     matrixStack.func_227862_a_(1.75F, 1.75F, 1.75F);
/*  84 */     matrixStack.func_227861_a_(0.2D, -0.1D, 0.0D);
/*  85 */     this.rightArm.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*  86 */     matrixStack.func_227865_b_();
/*     */     
/*  88 */     matrixStack.func_227860_a_();
/*  89 */     matrixStack.func_227862_a_(1.75F, 1.75F, 1.75F);
/*  90 */     matrixStack.func_227861_a_(-0.2D, -0.1D, 0.0D);
/*  91 */     this.leftArm.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*  92 */     matrixStack.func_227865_b_();
/*     */     
/*  94 */     matrixStack.func_227860_a_();
/*  95 */     matrixStack.func_227862_a_(1.75F, 1.35F, 1.75F);
/*  96 */     matrixStack.func_227861_a_(0.05D, -0.4D, 0.0D);
/*  97 */     this.rightLeg.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*  98 */     matrixStack.func_227865_b_();
/*     */     
/* 100 */     matrixStack.func_227860_a_();
/* 101 */     matrixStack.func_227862_a_(1.75F, 1.35F, 1.75F);
/* 102 */     matrixStack.func_227861_a_(-0.05D, -0.4D, 0.0D);
/* 103 */     this.leftLeg.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 104 */     matrixStack.func_227865_b_();
/* 105 */     matrixStack.func_227865_b_();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225597_a_(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 112 */     boolean flag = (entity.func_184599_cB() > 4);
/* 113 */     boolean flag1 = entity.func_213314_bj();
/* 114 */     this.head.field_78796_g = netHeadYaw * 0.017453292F;
/* 115 */     if (flag) {
/* 116 */       this.head.field_78795_f = -0.7853982F;
/* 117 */     } else if (this.field_205061_a > 0.0F) {
/*     */       
/* 119 */       if (flag1) {
/* 120 */         this.head.field_78795_f = func_205060_a(this.head.field_78795_f, -0.7853982F, this.field_205061_a);
/*     */       } else {
/* 122 */         this.head.field_78795_f = func_205060_a(this.head.field_78795_f, headPitch * 0.017453292F, this.field_205061_a);
/*     */       } 
/*     */     } else {
/*     */       
/* 126 */       this.head.field_78795_f = headPitch * 0.017453292F;
/* 127 */       if (this.head.field_78795_f > 0.6D) {
/* 128 */         this.head.field_78795_f = 0.6F;
/*     */       }
/*     */     } 
/*     */     
/* 132 */     float f = 1.0F;
/* 133 */     this.rightArm.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.6662F + 3.1415927F) * 0.8F * limbSwingAmount * 0.5F / f;
/* 134 */     this.leftArm.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.6662F) * 0.8F * limbSwingAmount * 0.5F / f;
/* 135 */     this.rightLeg.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.6662F) * 0.7F * limbSwingAmount / f;
/* 136 */     this.leftLeg.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.6662F + 3.1415927F) * 0.7F * limbSwingAmount / f;
/* 137 */     if (!entity.func_184614_ca().func_190926_b()) {
/* 138 */       this.rightArm.field_78795_f += -0.15F;
/*     */     }
/*     */     
/* 141 */     if (entity.func_213453_ef()) {
/*     */       
/* 143 */       this.body.field_78795_f = 0.2F;
/* 144 */       this.body.field_78798_e -= 2.0F;
/* 145 */       this.body.field_78797_d -= 0.2F;
/* 146 */       this.rightArm.field_78795_f += 0.3F;
/* 147 */       this.rightArm.field_78798_e -= 3.5F;
/* 148 */       this.rightArm.field_78797_d += 1.2F;
/* 149 */       this.leftArm.field_78795_f += 0.3F;
/* 150 */       this.leftArm.field_78798_e -= 3.5F;
/* 151 */       this.leftArm.field_78797_d += 1.2F;
/* 152 */       this.rightLeg.field_78798_e = 1.0F;
/* 153 */       this.rightLeg.field_78797_d = 14.0F;
/* 154 */       this.leftLeg.field_78798_e = 1.0F;
/* 155 */       this.leftLeg.field_78797_d = 14.0F;
/* 156 */       this.head.field_78798_e = -6.0F;
/* 157 */       this.head.field_78797_d = -5.0F;
/*     */     } 
/*     */ 
/*     */     
/* 161 */     this.field_217112_c = entity.field_70733_aJ;
/* 162 */     boolean isBlackLeg = EntityStatsCapability.get(entity).isBlackLeg();
/* 163 */     if (this.field_217112_c > 0.0F)
/*     */     {
/* 165 */       if (isBlackLeg) {
/*     */         
/* 167 */         this.body.field_78796_g = MathHelper.func_76126_a(MathHelper.func_76129_c(this.field_217112_c) * 6.2831855F) * 0.2F;
/* 168 */         this.rightLeg.field_78796_g += this.body.field_78796_g;
/* 169 */         this.leftLeg.field_78796_g += this.body.field_78796_g;
/* 170 */         float f1 = 1.0F - this.field_217112_c;
/* 171 */         f1 *= f1;
/* 172 */         f1 *= f1;
/* 173 */         f1 = 1.0F - f1;
/* 174 */         float f2 = MathHelper.func_76126_a(f1 * 3.1415927F);
/* 175 */         float f3 = MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * -(this.head.field_78795_f - 0.7F) * 0.75F;
/* 176 */         this.rightLeg.field_78795_f = (float)(this.rightArm.field_78795_f - f2 * 1.5D + f3);
/* 177 */         this.rightLeg.field_78796_g += this.body.field_78796_g * 2.0F;
/*     */       }
/*     */       else {
/*     */         
/* 181 */         this.body.field_78796_g = MathHelper.func_76126_a(MathHelper.func_76129_c(this.field_217112_c) * 6.2831855F) * 0.2F;
/* 182 */         this.rightArm.field_78798_e = MathHelper.func_76126_a(this.body.field_78796_g) * 12.0F;
/* 183 */         this.rightArm.field_78800_c = -MathHelper.func_76134_b(this.body.field_78796_g) * 9.0F;
/* 184 */         this.rightArm.field_78796_g += this.body.field_78796_g;
/* 185 */         this.leftArm.field_78798_e = -MathHelper.func_76126_a(this.body.field_78796_g) * 5.0F;
/* 186 */         this.leftArm.field_78796_g -= this.body.field_78796_g;
/* 187 */         this.leftArm.field_78795_f -= this.body.field_78796_g;
/* 188 */         float f1 = 1.0F - this.field_217112_c;
/* 189 */         f1 *= f1;
/* 190 */         f1 *= f1;
/* 191 */         f1 = 1.0F - f1;
/* 192 */         float f2 = MathHelper.func_76126_a(f1 * 3.1415927F);
/* 193 */         float f3 = MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * -(this.head.field_78795_f - 0.7F) * 0.75F;
/* 194 */         this.rightArm.field_78795_f = (float)(this.rightArm.field_78795_f - f2 * 1.2D + f3);
/* 195 */         this.rightArm.field_78796_g += this.body.field_78796_g * 2.0F;
/* 196 */         this.rightArm.field_78808_h += MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * -0.4F;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
/* 203 */     model.field_78795_f = x;
/* 204 */     model.field_78796_g = y;
/* 205 */     model.field_78808_h = z;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
/* 211 */     if (side == HandSide.RIGHT) {
/*     */       
/* 213 */       matrixStack.func_227861_a_(0.2D, 0.3D, 0.0D);
/* 214 */       this.rightArm.func_228309_a_(matrixStack, vertex, packedLight, OverlayTexture.field_229196_a_, red, green, blue, alpha);
/*     */     }
/*     */     else {
/*     */       
/* 218 */       matrixStack.func_227861_a_(-0.2D, 0.3D, 0.0D);
/* 219 */       this.leftArm.func_228309_a_(matrixStack, vertex, packedLight, OverlayTexture.field_229196_a_, red, green, blue, alpha);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
/* 226 */     if (side == HandSide.RIGHT) {
/*     */       
/* 228 */       matrixStack.func_227861_a_(0.0D, -1.2D, 0.3D);
/* 229 */       matrixStack.func_227862_a_(1.5F, 1.5F, 1.5F);
/* 230 */       matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(-60.0F));
/* 231 */       this.rightLeg.func_228309_a_(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     }
/*     */     else {
/*     */       
/* 235 */       matrixStack.func_227861_a_(0.0D, -1.2D, 0.3D);
/* 236 */       matrixStack.func_227862_a_(1.5F, 1.5F, 1.5F);
/* 237 */       matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(60.0F));
/* 238 */       this.leftLeg.func_228309_a_(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225599_a_(HandSide side, MatrixStack matrixStack) {
/* 245 */     super.func_225599_a_(side, matrixStack);
/* 246 */     matrixStack.func_227861_a_((side == HandSide.RIGHT) ? -0.6D : 0.6D, -0.5D, -0.2D);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\morphs\HitoDaibutsuModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */