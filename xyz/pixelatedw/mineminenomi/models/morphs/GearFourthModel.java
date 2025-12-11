/*     */ package xyz.pixelatedw.mineminenomi.models.morphs;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.entity.model.IHasArm;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.HandSide;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.vector.Vector3f;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.gomu.GomuGomuNoRocketAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphModel;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ 
/*     */ public class GearFourthModel<T extends LivingEntity>
/*     */   extends MorphModel<T>
/*     */   implements IHasArm {
/*     */   private final ModelRenderer rightArm;
/*     */   private final ModelRenderer rightLeg;
/*     */   private final ModelRenderer head;
/*     */   private final ModelRenderer body;
/*     */   private final ModelRenderer leftArm;
/*     */   private final ModelRenderer leftLeg;
/*     */   boolean gomuAnimations = true;
/*     */   
/*     */   public GearFourthModel(boolean gomuAnimations) {
/*  34 */     super(-0.2F);
/*  35 */     this.field_78090_t = 64;
/*  36 */     this.field_78089_u = 64;
/*  37 */     this.gomuAnimations = gomuAnimations;
/*     */     
/*  39 */     this.rightArm = new ModelRenderer((Model)this);
/*  40 */     this.rightArm.func_78793_a(-9.5F, -2.0F, 0.0F);
/*  41 */     this.rightArm.func_78784_a(40, 16).func_228303_a_(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
/*     */     
/*  43 */     this.rightLeg = new ModelRenderer((Model)this);
/*  44 */     this.rightLeg.func_78793_a(-3.6F, 15.0F, 0.0F);
/*  45 */     this.rightLeg.func_78784_a(0, 16).func_228303_a_(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
/*     */     
/*  47 */     this.head = new ModelRenderer((Model)this);
/*  48 */     this.head.func_78793_a(0.0F, -5.4F, 0.0F);
/*  49 */     this.head.func_78784_a(0, 0).func_228303_a_(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
/*     */     
/*  51 */     this.body = new ModelRenderer((Model)this);
/*  52 */     this.body.func_78793_a(0.0F, -5.4F, 0.0F);
/*  53 */     this.body.func_78784_a(16, 16).func_228303_a_(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F, false);
/*     */     
/*  55 */     this.leftArm = new ModelRenderer((Model)this);
/*  56 */     this.leftArm.func_78793_a(9.5F, -2.0F, 0.0F);
/*  57 */     this.leftArm.func_78784_a(32, 48).func_228303_a_(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
/*     */     
/*  59 */     this.leftLeg = new ModelRenderer((Model)this);
/*  60 */     this.leftLeg.func_78793_a(3.2F, 15.0F, 0.0F);
/*  61 */     this.leftLeg.func_78784_a(16, 48).func_228303_a_(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
/*     */     
/*  63 */     this.field_78115_e = this.body;
/*  64 */     this.field_78116_c = this.head;
/*  65 */     this.field_178723_h = this.rightArm;
/*  66 */     this.field_178724_i = this.leftArm;
/*  67 */     this.field_178721_j = this.rightLeg;
/*  68 */     this.field_178722_k = this.leftLeg;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/*  74 */     float scale = 1.5F;
/*  75 */     matrixStack.func_227860_a_();
/*  76 */     matrixStack.func_227862_a_(scale, scale, scale);
/*  77 */     matrixStack.func_227861_a_(0.0D, -0.8D, 0.0D);
/*     */     
/*  79 */     matrixStack.func_227860_a_();
/*  80 */     matrixStack.func_227861_a_(0.0D, -0.21D, 0.0D);
/*  81 */     this.head.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*  82 */     if (this.gomuAnimations) {
/*     */       
/*  84 */       this.field_178720_f.func_217177_a(this.head);
/*  85 */       this.field_178720_f.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */     } 
/*  87 */     matrixStack.func_227865_b_();
/*     */     
/*  89 */     matrixStack.func_227860_a_();
/*  90 */     matrixStack.func_227862_a_(2.0F, 1.7F, 3.0F);
/*  91 */     this.body.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  97 */     matrixStack.func_227865_b_();
/*     */     
/*  99 */     if (this.gomuAnimations) {
/*     */       
/* 101 */       float time = 0.2F;
/*     */       
/* 103 */       Minecraft mc = Minecraft.func_71410_x();
/*     */       
/* 105 */       GomuGomuNoRocketAbility ability = (GomuGomuNoRocketAbility)AbilityDataCapability.get((LivingEntity)mc.field_71439_g).getEquippedAbility(GomuGomuNoRocketAbility.INSTANCE);
/* 106 */       if (ability == null || ability.isContinuous());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 114 */       matrixStack.func_227860_a_();
/* 115 */       matrixStack.func_227862_a_(1.75F * 5.0F * time, 1.75F * 5.0F * time, 1.75F * 5.0F * time);
/* 116 */       matrixStack.func_227861_a_((0.25F + 0.26F * time), -0.07D + 0.28D * time, 0.0D);
/*     */     }
/*     */     else {
/*     */       
/* 120 */       matrixStack.func_227860_a_();
/* 121 */       matrixStack.func_227862_a_(1.75F, 1.75F, 1.75F);
/* 122 */       matrixStack.func_227861_a_(0.25D, -0.07D, 0.0D);
/*     */     } 
/*     */     
/* 125 */     this.rightArm.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 131 */     matrixStack.func_227865_b_();
/*     */     
/* 133 */     matrixStack.func_227860_a_();
/* 134 */     matrixStack.func_227862_a_(1.75F, 1.75F, 1.75F);
/* 135 */     matrixStack.func_227861_a_(-0.25D, -0.07D, 0.0D);
/* 136 */     this.leftArm.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 142 */     matrixStack.func_227865_b_();
/*     */     
/* 144 */     matrixStack.func_227860_a_();
/* 145 */     matrixStack.func_227861_a_(0.05D, -0.4D, 0.0D);
/* 146 */     this.rightLeg.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 147 */     matrixStack.func_227865_b_();
/*     */     
/* 149 */     matrixStack.func_227860_a_();
/* 150 */     matrixStack.func_227861_a_(-0.05D, -0.4D, 0.0D);
/* 151 */     this.leftLeg.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 152 */     matrixStack.func_227865_b_();
/* 153 */     matrixStack.func_227865_b_();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225597_a_(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 159 */     double x = entity.field_70169_q - entity.func_226277_ct_();
/* 160 */     double z = entity.field_70166_s - entity.func_226281_cx_();
/* 161 */     boolean isMoving = (x != 0.0D || z != 0.0D);
/* 162 */     BlockPos pos = entity.func_233580_cy_().func_177979_c(2);
/* 163 */     boolean isInAir = (entity.field_70170_p.func_180495_p(pos).func_185904_a() == Material.field_151579_a);
/* 164 */     boolean isFlying = (isMoving && isInAir);
/*     */     
/* 166 */     if (this.gomuAnimations) {
/*     */ 
/*     */       
/* 169 */       if (isFlying) {
/*     */         
/* 171 */         this.rightArm.field_78808_h = (float)Math.toRadians(90.0D);
/* 172 */         this.leftArm.field_78808_h = (float)Math.toRadians(-90.0D);
/*     */       } 
/*     */ 
/*     */       
/* 176 */       boolean flag = (entity.func_184599_cB() > 4);
/* 177 */       boolean flag1 = entity.func_213314_bj();
/* 178 */       this.head.field_78796_g = netHeadYaw * 0.017453292F;
/* 179 */       if (flag) {
/* 180 */         this.head.field_78795_f = -0.7853982F;
/* 181 */       } else if (this.field_205061_a > 0.0F) {
/*     */         
/* 183 */         if (flag1) {
/* 184 */           this.head.field_78795_f = func_205060_a(this.head.field_78795_f, -0.7853982F, this.field_205061_a);
/*     */         } else {
/* 186 */           this.head.field_78795_f = func_205060_a(this.head.field_78795_f, headPitch * 0.017453292F, this.field_205061_a);
/*     */         } 
/*     */       } else {
/*     */         
/* 190 */         this.head.field_78795_f = headPitch * 0.017453292F;
/* 191 */         if (this.head.field_78795_f > 0.6D) {
/* 192 */           this.head.field_78795_f = 0.6F;
/*     */         }
/*     */       } 
/*     */       
/* 196 */       float f = 1.0F;
/* 197 */       if (!isFlying) {
/*     */         
/* 199 */         this.rightArm.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.6662F + 3.1415927F) * 0.8F * limbSwingAmount * 0.5F / f;
/* 200 */         this.leftArm.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.6662F) * 0.8F * limbSwingAmount * 0.5F / f;
/*     */       } 
/* 202 */       float speed = 0.4F;
/* 203 */       if (entity.func_70051_ag())
/* 204 */         speed = 0.7F; 
/* 205 */       this.rightLeg.field_78797_d += -2.0F + MathHelper.func_76134_b(ageInTicks * speed) * 2.0F;
/* 206 */       this.leftLeg.field_78797_d += -2.0F + MathHelper.func_76134_b(ageInTicks * speed) * 2.0F;
/* 207 */       if (!entity.func_184614_ca().func_190926_b()) {
/* 208 */         this.rightArm.field_78795_f += -0.15F;
/*     */       }
/*     */       
/* 211 */       this.field_217112_c = entity.field_70733_aJ;
/* 212 */       boolean isBlackLeg = EntityStatsCapability.get(entity).isBlackLeg();
/* 213 */       if (this.field_217112_c > 0.0F) {
/*     */         
/* 215 */         this.body.field_78796_g = MathHelper.func_76126_a(MathHelper.func_76129_c(this.field_217112_c) * 6.2831855F) * 0.2F;
/* 216 */         if (isBlackLeg) {
/*     */           
/* 218 */           this.rightLeg.field_78796_g += this.body.field_78796_g;
/* 219 */           this.leftLeg.field_78796_g += this.body.field_78796_g;
/* 220 */           float f1 = 1.0F - this.field_217112_c;
/* 221 */           f1 *= f1;
/* 222 */           f1 *= f1;
/* 223 */           f1 = 1.0F - f1;
/* 224 */           float f2 = MathHelper.func_76126_a(f1 * 3.1415927F);
/* 225 */           float f3 = MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * -(this.head.field_78795_f - 0.7F) * 0.75F;
/* 226 */           this.rightLeg.field_78795_f = (float)(this.rightArm.field_78795_f - f2 * 1.5D + f3);
/* 227 */           this.rightLeg.field_78796_g += this.body.field_78796_g * 2.0F;
/*     */         }
/*     */         else {
/*     */           
/* 231 */           this.rightArm.field_78798_e = MathHelper.func_76126_a(this.body.field_78796_g) * 12.0F;
/* 232 */           this.rightArm.field_78800_c = -MathHelper.func_76134_b(this.body.field_78796_g) * 9.0F;
/* 233 */           this.rightArm.field_78796_g += this.body.field_78796_g;
/* 234 */           this.leftArm.field_78798_e = -MathHelper.func_76126_a(this.body.field_78796_g) * 5.0F;
/* 235 */           this.leftArm.field_78796_g -= this.body.field_78796_g;
/* 236 */           this.leftArm.field_78795_f -= this.body.field_78796_g;
/* 237 */           float f1 = 1.0F - this.field_217112_c;
/* 238 */           f1 *= f1;
/* 239 */           f1 *= f1;
/* 240 */           f1 = 1.0F - f1;
/* 241 */           float f2 = MathHelper.func_76126_a(f1 * 3.1415927F);
/* 242 */           float f3 = MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * -(this.head.field_78795_f - 0.7F) * 0.75F;
/* 243 */           this.rightArm.field_78795_f = (float)(this.rightArm.field_78795_f - f2 * 1.2D + f3);
/* 244 */           this.rightArm.field_78796_g += this.body.field_78796_g * 2.0F;
/* 245 */           this.rightArm.field_78808_h += MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * -0.4F;
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 250 */       if (!isMoving)
/*     */       {
/* 252 */         this.rightArm.field_78795_f = (float)Math.toRadians(-90.0D);
/* 253 */         this.leftArm.field_78795_f = (float)Math.toRadians(-90.0D);
/* 254 */         this.leftArm.field_78808_h = (float)Math.toRadians(10.0D);
/* 255 */         this.leftArm.field_78796_g = (float)Math.toRadians(-5.0D);
/* 256 */         this.leftArm.field_78798_e += 4.0F;
/*     */       
/*     */       }
/*     */ 
/*     */     
/*     */     }
/* 262 */     else if (isFlying && entity.func_70051_ag()) {
/*     */       
/* 264 */       this.rightArm.field_78808_h = (float)Math.toRadians(90.0D);
/* 265 */       this.leftArm.field_78808_h = (float)Math.toRadians(-90.0D);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
/* 274 */     model.field_78795_f = x;
/* 275 */     model.field_78796_g = y;
/* 276 */     model.field_78808_h = z;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
/* 282 */     if (side == HandSide.RIGHT) {
/*     */       
/* 284 */       matrixStack.func_227861_a_(0.2D, 0.3D, 0.0D);
/* 285 */       this.rightArm.func_228309_a_(matrixStack, vertex, packedLight, OverlayTexture.field_229196_a_, 1.0F, 0.7F, 0.0F, 1.0F);
/*     */     }
/*     */     else {
/*     */       
/* 289 */       matrixStack.func_227861_a_(-0.2D, 0.3D, 0.0D);
/* 290 */       this.leftArm.func_228309_a_(matrixStack, vertex, packedLight, OverlayTexture.field_229196_a_, 1.0F, 0.7F, 0.0F, 1.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
/* 297 */     if (side == HandSide.RIGHT) {
/*     */       
/* 299 */       matrixStack.func_227861_a_(0.0D, -1.2D, 0.3D);
/* 300 */       matrixStack.func_227862_a_(1.5F, 1.5F, 1.5F);
/* 301 */       matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(-60.0F));
/* 302 */       this.rightLeg.func_228309_a_(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     }
/*     */     else {
/*     */       
/* 306 */       matrixStack.func_227861_a_(0.0D, -1.2D, 0.3D);
/* 307 */       matrixStack.func_227862_a_(1.5F, 1.5F, 1.5F);
/* 308 */       matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(60.0F));
/* 309 */       this.leftLeg.func_228309_a_(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225599_a_(HandSide side, MatrixStack matrixStack) {
/* 316 */     super.func_225599_a_(side, matrixStack);
/* 317 */     matrixStack.func_227861_a_((side == HandSide.RIGHT) ? -0.6D : 0.6D, -0.5D, -0.2D);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\morphs\GearFourthModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */