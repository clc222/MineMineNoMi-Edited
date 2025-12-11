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
/*     */ public class YomiModel<T extends LivingEntity>
/*     */   extends MorphModel<T> implements IHasArm {
/*     */   public ModelRenderer field_78116_c;
/*     */   public ModelRenderer field_78115_e;
/*     */   public ModelRenderer field_178723_h;
/*     */   public ModelRenderer field_178724_i;
/*     */   public ModelRenderer field_178721_j;
/*     */   public ModelRenderer field_178722_k;
/*     */   public ModelRenderer afro;
/*     */   
/*     */   public YomiModel() {
/*  28 */     super(1.0F);
/*  29 */     this.field_78090_t = 64;
/*  30 */     this.field_78089_u = 64;
/*     */     
/*  32 */     this.field_78116_c = new ModelRenderer((Model)this, 0, 0);
/*  33 */     this.field_78116_c.func_228301_a_(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F);
/*  34 */     this.field_78116_c.func_78793_a(0.0F, 0.0F, 0.0F);
/*  35 */     this.field_78115_e = new ModelRenderer((Model)this, 16, 16);
/*  36 */     this.field_78115_e.func_228301_a_(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F);
/*  37 */     this.field_78115_e.func_78793_a(0.0F, 0.0F, 0.0F);
/*  38 */     this.field_178723_h = new ModelRenderer((Model)this, 40, 16);
/*  39 */     this.field_178723_h.func_228301_a_(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, 0.0F);
/*  40 */     this.field_178723_h.func_78793_a(-5.0F, 2.0F, 0.0F);
/*  41 */     this.field_178724_i = new ModelRenderer((Model)this, 40, 16);
/*  42 */     this.field_178724_i.field_78809_i = true;
/*  43 */     this.field_178724_i.func_228301_a_(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, 0.0F);
/*  44 */     this.field_178724_i.func_78793_a(5.0F, 2.0F, 0.0F);
/*  45 */     this.field_178721_j = new ModelRenderer((Model)this, 0, 16);
/*  46 */     this.field_178721_j.func_228301_a_(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, 0.0F);
/*  47 */     this.field_178721_j.func_78793_a(-2.0F, 12.0F, 0.0F);
/*  48 */     this.field_178722_k = new ModelRenderer((Model)this, 0, 16);
/*  49 */     this.field_178722_k.field_78809_i = true;
/*  50 */     this.field_178722_k.func_228301_a_(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, 0.0F);
/*  51 */     this.field_178722_k.func_78793_a(2.0F, 12.0F, 0.0F);
/*     */     
/*  53 */     super.field_78115_e = this.field_78115_e;
/*  54 */     super.field_78116_c = this.field_78116_c;
/*  55 */     super.field_178723_h = this.field_178723_h;
/*  56 */     super.field_178724_i = this.field_178724_i;
/*  57 */     super.field_178721_j = this.field_178721_j;
/*  58 */     super.field_178722_k = this.field_178722_k;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/*  64 */     this.field_178723_h.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*  65 */     this.field_178724_i.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*  66 */     this.field_178721_j.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*  67 */     this.field_178722_k.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*  68 */     this.field_78116_c.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*  69 */     this.field_78115_e.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */     
/*  71 */     this.field_178731_d.func_217177_a(this.field_178721_j);
/*  72 */     this.field_178733_c.func_217177_a(this.field_178722_k);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/*  79 */     boolean flag = (entity.func_184599_cB() > 4);
/*  80 */     boolean flag1 = entity.func_213314_bj();
/*  81 */     this.field_78116_c.field_78796_g = netHeadYaw * 0.017453292F;
/*  82 */     if (flag) {
/*  83 */       this.field_78116_c.field_78795_f = -0.7853982F;
/*  84 */     } else if (this.field_205061_a > 0.0F) {
/*     */       
/*  86 */       if (flag1) {
/*  87 */         this.field_78116_c.field_78795_f = func_205060_a(this.field_78116_c.field_78795_f, -0.7853982F, this.field_205061_a);
/*     */       } else {
/*  89 */         this.field_78116_c.field_78795_f = func_205060_a(this.field_78116_c.field_78795_f, headPitch * 0.017453292F, this.field_205061_a);
/*     */       } 
/*     */     } else {
/*     */       
/*  93 */       this.field_78116_c.field_78795_f = headPitch * 0.017453292F;
/*  94 */       if (this.field_78116_c.field_78795_f > 0.6D) {
/*  95 */         this.field_78116_c.field_78795_f = 0.6F;
/*     */       }
/*     */     } 
/*     */     
/*  99 */     float f = 1.0F;
/* 100 */     float speed = entity.func_70051_ag() ? 1.0F : 0.6F;
/* 101 */     float spread = entity.func_70051_ag() ? 1.0F : 0.8F;
/* 102 */     this.field_178723_h.field_78795_f = MathHelper.func_76134_b(limbSwing * speed + 3.1415927F) * spread * limbSwingAmount * 0.5F / f;
/* 103 */     this.field_178724_i.field_78795_f = MathHelper.func_76134_b(limbSwing * speed) * spread * limbSwingAmount * 0.5F / f;
/* 104 */     this.field_178721_j.field_78795_f = MathHelper.func_76134_b(limbSwing * speed) * spread * limbSwingAmount / f;
/* 105 */     this.field_178722_k.field_78795_f = MathHelper.func_76134_b(limbSwing * speed + 3.1415927F) * spread * limbSwingAmount / f;
/* 106 */     if (!entity.func_184614_ca().func_190926_b()) {
/* 107 */       this.field_178723_h.field_78795_f += -0.15F;
/*     */     }
/*     */     
/* 110 */     this.field_217112_c = ((LivingEntity)entity).field_70733_aJ;
/* 111 */     boolean isBlackLeg = EntityStatsCapability.get((LivingEntity)entity).isBlackLeg();
/* 112 */     if (this.field_217112_c > 0.0F)
/*     */     {
/* 114 */       if (isBlackLeg) {
/*     */         
/* 116 */         this.field_78115_e.field_78796_g = MathHelper.func_76126_a(MathHelper.func_76129_c(this.field_217112_c) * 6.2831855F) * 0.2F;
/* 117 */         this.field_178721_j.field_78796_g += this.field_78115_e.field_78796_g;
/* 118 */         this.field_178722_k.field_78796_g += this.field_78115_e.field_78796_g;
/* 119 */         float f1 = 1.0F - this.field_217112_c;
/* 120 */         f1 *= f1;
/* 121 */         f1 *= f1;
/* 122 */         f1 = 1.0F - f1;
/* 123 */         float f2 = MathHelper.func_76126_a(f1 * 3.1415927F);
/* 124 */         float f3 = MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * -(this.field_78116_c.field_78795_f - 0.7F) * 0.75F;
/* 125 */         this.field_178721_j.field_78795_f = (float)(this.field_178723_h.field_78795_f - f2 * 1.5D + f3);
/* 126 */         this.field_178721_j.field_78796_g += this.field_78115_e.field_78796_g * 2.0F;
/*     */       }
/*     */       else {
/*     */         
/* 130 */         this.field_78115_e.field_78796_g = MathHelper.func_76126_a(MathHelper.func_76129_c(this.field_217112_c) * 6.2831855F) * 0.2F;
/* 131 */         this.field_178723_h.field_78798_e = MathHelper.func_76126_a(this.field_78115_e.field_78796_g) * 4.0F;
/* 132 */         this.field_178723_h.field_78800_c = -MathHelper.func_76134_b(this.field_78115_e.field_78796_g) * 4.0F;
/* 133 */         this.field_178723_h.field_78796_g += this.field_78115_e.field_78796_g;
/* 134 */         this.field_178724_i.field_78796_g += this.field_78115_e.field_78796_g;
/* 135 */         this.field_178724_i.field_78795_f += this.field_78115_e.field_78796_g;
/* 136 */         float f1 = 1.0F - this.field_217112_c;
/* 137 */         f1 *= f1;
/* 138 */         f1 *= f1;
/* 139 */         f1 = 1.0F - f1;
/* 140 */         float f2 = MathHelper.func_76126_a(f1 * 3.1415927F);
/* 141 */         float f3 = MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * -(this.field_78116_c.field_78795_f - 0.7F) * 0.75F;
/* 142 */         this.field_178723_h.field_78795_f = (float)(this.field_178723_h.field_78795_f - f2 * 1.2D + f3);
/* 143 */         this.field_178723_h.field_78796_g += this.field_78115_e.field_78796_g * 2.0F;
/* 144 */         this.field_178723_h.field_78808_h += MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * -0.4F;
/*     */       } 
/*     */     }
/*     */     
/* 148 */     if (entity.func_213453_ef()) {
/*     */       
/* 150 */       this.field_78115_e.field_78795_f = 0.5F;
/* 151 */       this.field_78115_e.field_78798_e -= 4.0F;
/* 152 */       this.field_178723_h.field_78795_f += 0.4F;
/* 153 */       this.field_178723_h.field_78798_e -= 2.5F;
/* 154 */       this.field_178724_i.field_78795_f += 0.4F;
/* 155 */       this.field_178724_i.field_78798_e -= 2.5F;
/* 156 */       this.field_178721_j.field_78798_e = 1.0F;
/* 157 */       this.field_178721_j.field_78797_d = 10.0F;
/* 158 */       this.field_178722_k.field_78798_e = 1.0F;
/* 159 */       this.field_178722_k.field_78797_d = 10.0F;
/* 160 */       this.field_78116_c.field_78798_e = -4.0F;
/* 161 */       this.field_78116_c.field_78797_d = 1.0F;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
/* 167 */     model.field_78795_f = x;
/* 168 */     model.field_78796_g = y;
/* 169 */     model.field_78808_h = z;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
/* 175 */     if (side == HandSide.RIGHT) {
/*     */       
/* 177 */       matrixStack.func_227861_a_(-0.2D, 0.0D, -0.0D);
/* 178 */       matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(-10.0F));
/* 179 */       matrixStack.func_227863_a_(Vector3f.field_229183_f_.func_229187_a_(10.0F));
/* 180 */       this.field_178723_h.func_228309_a_(matrixStack, vertex, packedLight, OverlayTexture.field_229196_a_, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */     }
/*     */     else {
/*     */       
/* 184 */       matrixStack.func_227861_a_(0.2D, 0.0D, -0.2D);
/* 185 */       matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(-10.0F));
/* 186 */       matrixStack.func_227863_a_(Vector3f.field_229183_f_.func_229187_a_(-10.0F));
/* 187 */       this.field_178724_i.func_228309_a_(matrixStack, vertex, packedLight, OverlayTexture.field_229196_a_, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
/* 194 */     if (side == HandSide.RIGHT) {
/*     */       
/* 196 */       matrixStack.func_227861_a_(0.0D, -1.2D, 0.3D);
/* 197 */       matrixStack.func_227862_a_(1.5F, 1.5F, 1.5F);
/* 198 */       matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(-60.0F));
/* 199 */       this.field_178721_j.func_228309_a_(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     }
/*     */     else {
/*     */       
/* 203 */       matrixStack.func_227861_a_(0.0D, -1.2D, 0.3D);
/* 204 */       matrixStack.func_227862_a_(1.5F, 1.5F, 1.5F);
/* 205 */       matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(60.0F));
/* 206 */       this.field_178722_k.func_228309_a_(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225599_a_(HandSide side, MatrixStack matrixStack) {
/* 213 */     super.func_225599_a_(side, matrixStack);
/* 214 */     matrixStack.func_227861_a_((side == HandSide.RIGHT) ? 0.06D : -0.06D, 0.1D, 0.0D);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\morphs\YomiModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */