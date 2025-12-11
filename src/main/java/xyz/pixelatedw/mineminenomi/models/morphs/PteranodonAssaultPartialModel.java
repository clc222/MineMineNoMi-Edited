/*     */ package xyz.pixelatedw.mineminenomi.models.morphs;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.HandSide;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.vector.Vector3f;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphModel;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ 
/*     */ public class PteranodonAssaultPartialModel<T extends LivingEntity>
/*     */   extends MorphModel<T> {
/*     */   private final ModelRenderer leftWing;
/*     */   private final ModelRenderer leftWing2;
/*     */   private final ModelRenderer leftWingTip;
/*     */   private final ModelRenderer rightWing;
/*     */   private final ModelRenderer rightWing2;
/*     */   private final ModelRenderer rightWingTip;
/*     */   private final ModelRenderer headPiece;
/*     */   
/*     */   public PteranodonAssaultPartialModel() {
/*  27 */     super(1.0F);
/*  28 */     this.field_78090_t = 64;
/*  29 */     this.field_78089_u = 64;
/*     */     
/*  31 */     this.leftWing = new ModelRenderer((Model)this);
/*  32 */     this.leftWing.func_78793_a(3.8557F, 1.6409F, -1.0F);
/*  33 */     setRotationAngle(this.leftWing, -0.2967F, -0.0262F, -0.0873F);
/*  34 */     this.leftWing.func_78784_a(24, 0).func_228303_a_(-0.0557F, -1.5F, -5.0F, 1.0F, 18.0F, 10.0F, 0.0F, true);
/*     */     
/*  36 */     this.leftWingTip = new ModelRenderer((Model)this);
/*  37 */     this.leftWingTip.func_78793_a(1.0F, 15.75F, -4.5F);
/*  38 */     this.leftWing.func_78792_a(this.leftWingTip);
/*  39 */     setRotationAngle(this.leftWingTip, -1.2217F, 0.0F, 0.0F);
/*  40 */     this.leftWingTip.func_78784_a(4, 7).func_228303_a_(-0.8057F, -0.192F, -0.3995F, 1.0F, 2.0F, 1.0F, 0.0F, true);
/*     */     
/*  42 */     this.leftWing2 = new ModelRenderer((Model)this);
/*  43 */     this.leftWing2.func_78793_a(1.25F, 16.25F, -4.5F);
/*  44 */     this.leftWing.func_78792_a(this.leftWing2);
/*  45 */     setRotationAngle(this.leftWing2, -0.6977F, 0.028F, 0.0334F);
/*  46 */     this.leftWing2.func_78784_a(0, 0).func_228303_a_(-0.8057F, -20.6626F, -0.2402F, 1.0F, 21.0F, 10.0F, 0.0F, true);
/*     */     
/*  48 */     this.rightWing = new ModelRenderer((Model)this);
/*  49 */     this.rightWing.func_78793_a(-3.8943F, 1.6409F, -1.0F);
/*  50 */     setRotationAngle(this.rightWing, -0.2967F, -0.0262F, 0.0873F);
/*  51 */     this.rightWing.func_78784_a(24, 0).func_228303_a_(-0.9557F, -1.5F, -5.0F, 1.0F, 18.0F, 10.0F, 0.0F, false);
/*     */     
/*  53 */     this.rightWingTip = new ModelRenderer((Model)this);
/*  54 */     this.rightWingTip.func_78793_a(-1.0F, 15.75F, -4.5F);
/*  55 */     this.rightWing.func_78792_a(this.rightWingTip);
/*  56 */     setRotationAngle(this.rightWingTip, -1.2217F, 0.0F, 0.0F);
/*  57 */     this.rightWingTip.func_78784_a(0, 7).func_228303_a_(-0.2057F, -0.192F, -0.3995F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/*  59 */     this.rightWing2 = new ModelRenderer((Model)this);
/*  60 */     this.rightWing2.func_78793_a(-1.75F, 16.25F, -4.5F);
/*  61 */     this.rightWing.func_78792_a(this.rightWing2);
/*  62 */     setRotationAngle(this.rightWing2, -0.6977F, -0.028F, -0.0334F);
/*  63 */     this.rightWing2.func_78784_a(0, 0).func_228303_a_(0.2943F, -20.6626F, -0.2402F, 1.0F, 21.0F, 10.0F, 0.0F, false);
/*     */     
/*  65 */     this.headPiece = new ModelRenderer((Model)this);
/*  66 */     this.headPiece.func_78793_a(0.0F, 0.75F, 0.0F);
/*  67 */     setRotationAngle(this.headPiece, -0.8727F, 0.0F, 0.0F);
/*  68 */     this.headPiece.func_78784_a(0, 0).func_228303_a_(-1.0F, -8.4532F, -8.5142F, 2.0F, 5.0F, 2.0F, 0.0F, false);
/*  69 */     this.headPiece.func_78784_a(12, 0).func_228303_a_(-1.0F, -11.4532F, -8.5142F, 2.0F, 3.0F, 1.0F, 0.0F, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/*  75 */     this.headPiece.func_217177_a(this.field_78116_c);
/*  76 */     this.field_78116_c.field_78795_f += (float)Math.toRadians(-40.0D);
/*  77 */     this.leftWing.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*  78 */     this.rightWing.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*  79 */     this.headPiece.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/*  85 */     super.func_225597_a_((LivingEntity)entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/*     */     
/*  87 */     if (!entity.func_233570_aj_()) {
/*     */       
/*  89 */       this.rightWing2.field_78795_f = (float)Math.toRadians(190.0D);
/*  90 */       this.rightWing2.field_78798_e = 4.5F;
/*  91 */       this.rightWing2.field_78797_d = 15.0F;
/*  92 */       this.rightWing2.field_78800_c = -1.5F;
/*     */       
/*  94 */       this.leftWing2.field_78795_f = (float)Math.toRadians(190.0D);
/*  95 */       this.leftWing2.field_78798_e = 4.5F;
/*  96 */       this.leftWing2.field_78797_d = 15.0F;
/*  97 */       this.leftWing2.field_78800_c = 0.8F;
/*     */       
/*  99 */       this.rightWing.field_78796_g += 0.9F + MathHelper.func_76134_b((float)(((LivingEntity)entity).field_70173_aa * 0.3D + Math.PI));
/* 100 */       this.rightWing.field_78808_h += 1.3F + MathHelper.func_76134_b((float)(((LivingEntity)entity).field_70173_aa * 0.3D + Math.PI));
/* 101 */       this.rightWing2.field_78808_h += MathHelper.func_76134_b((float)(((LivingEntity)entity).field_70173_aa * 0.3D + Math.PI)) / 3.0F;
/*     */       
/* 103 */       this.leftWing.field_78796_g -= 0.9F + MathHelper.func_76134_b((float)(((LivingEntity)entity).field_70173_aa * 0.3D + Math.PI));
/* 104 */       this.leftWing.field_78808_h -= 1.3F + MathHelper.func_76134_b((float)(((LivingEntity)entity).field_70173_aa * 0.3D + Math.PI));
/* 105 */       this.leftWing2.field_78808_h -= MathHelper.func_76134_b((float)(((LivingEntity)entity).field_70173_aa * 0.3D + Math.PI)) / 3.0F;
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 110 */       float f = 1.0F;
/* 111 */       this.rightWing.field_78795_f = -0.3F + MathHelper.func_76134_b(limbSwing * 0.6662F) * 0.8F * limbSwingAmount / f;
/* 112 */       this.leftWing.field_78795_f = -0.3F + MathHelper.func_76134_b(limbSwing * 0.6662F + 3.1415927F) * 0.8F * limbSwingAmount / f;
/*     */ 
/*     */       
/* 115 */       this.field_217112_c = ((LivingEntity)entity).field_70733_aJ;
/* 116 */       boolean isBlackLeg = (EntityStatsCapability.get((LivingEntity)entity).isBlackLeg() && entity.func_184614_ca().func_190926_b());
/* 117 */       if (this.field_217112_c > 0.0F && !isBlackLeg) {
/*     */         
/* 119 */         this.field_78115_e.field_78796_g = MathHelper.func_76126_a(MathHelper.func_76129_c(this.field_217112_c) * 6.2831855F) * 0.2F;
/* 120 */         this.rightWing.field_78798_e = MathHelper.func_76126_a(this.field_78115_e.field_78796_g) * 5.0F;
/* 121 */         this.rightWing.field_78800_c = -MathHelper.func_76134_b(this.field_78115_e.field_78796_g) * 5.0F;
/* 122 */         this.leftWing.field_78798_e = -MathHelper.func_76126_a(this.field_78115_e.field_78796_g) * 5.0F;
/* 123 */         this.leftWing.field_78800_c = MathHelper.func_76134_b(this.field_78115_e.field_78796_g) * 5.0F;
/* 124 */         this.rightWing.field_78796_g += this.field_78115_e.field_78796_g;
/* 125 */         this.leftWing.field_78796_g += this.field_78115_e.field_78796_g;
/* 126 */         this.leftWing.field_78795_f += this.field_78115_e.field_78796_g;
/* 127 */         float f1 = 1.0F - this.field_217112_c;
/* 128 */         f1 *= f1;
/* 129 */         f1 *= f1;
/* 130 */         f1 = 1.0F - f1;
/* 131 */         float f2 = MathHelper.func_76126_a(f1 * 3.1415927F);
/* 132 */         float f3 = MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * -(this.field_78116_c.field_78795_f - 0.7F) * 0.75F;
/* 133 */         this.rightWing.field_78795_f = (float)(this.rightWing.field_78795_f - f2 * 1.5D + f3);
/* 134 */         this.rightWing.field_78796_g += this.field_78115_e.field_78796_g * 2.0F;
/* 135 */         this.rightWing.field_78808_h += MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * -0.9F;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean renderItemInHand(T entity, HandSide side, MatrixStack matrixStack) {
/* 152 */     if (entity instanceof PlayerEntity)
/*     */     {
/* 154 */       if (!((PlayerEntity)entity).field_71075_bZ.field_75100_b)
/*     */       {
/* 156 */         return false;
/*     */       }
/*     */     }
/* 159 */     this.field_178721_j.func_228307_a_(matrixStack);
/* 160 */     matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(-40.0F));
/* 161 */     matrixStack.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(-30.0F));
/* 162 */     matrixStack.func_227861_a_(-0.12D, -0.05D, 0.3D);
/* 163 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 168 */     modelRenderer.field_78795_f = x;
/* 169 */     modelRenderer.field_78796_g = y;
/* 170 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\morphs\PteranodonAssaultPartialModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */