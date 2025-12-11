/*     */ package xyz.pixelatedw.mineminenomi.models.morphs;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.HandSide;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.vector.Vector3f;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphModel;
/*     */ 
/*     */ public class KameGuardModel<T extends LivingEntity>
/*     */   extends MorphModel<T> {
/*     */   private final ModelRenderer body;
/*     */   private final ModelRenderer frontLeftFin;
/*     */   private final ModelRenderer frontRightFin;
/*     */   private final ModelRenderer backRightFin;
/*     */   private final ModelRenderer backLeftFin;
/*     */   private final ModelRenderer head;
/*     */   private final ModelRenderer shell;
/*     */   
/*     */   public KameGuardModel() {
/*  25 */     super(1.0F);
/*  26 */     this.field_78090_t = 64;
/*  27 */     this.field_78089_u = 64;
/*     */     
/*  29 */     this.body = new ModelRenderer((Model)this);
/*  30 */     this.body.func_78793_a(0.0F, 22.0F, -1.0F);
/*     */     
/*  32 */     this.frontLeftFin = new ModelRenderer((Model)this);
/*  33 */     this.frontLeftFin.func_78793_a(-1.0F, 0.0F, 0.0F);
/*  34 */     this.body.func_78792_a(this.frontLeftFin);
/*  35 */     this.frontLeftFin.func_78784_a(33, 8).func_228303_a_(5.0F, 0.85F, -4.0F, 6.0F, 0.0F, 3.0F, 0.0F, false);
/*     */     
/*  37 */     this.frontRightFin = new ModelRenderer((Model)this);
/*  38 */     this.frontRightFin.func_78793_a(-1.0F, 0.0F, 0.0F);
/*  39 */     this.body.func_78792_a(this.frontRightFin);
/*  40 */     this.frontRightFin.func_78784_a(28, 23).func_228303_a_(-9.0F, 1.0F, -4.0F, 6.0F, 0.0F, 3.0F, 0.0F, false);
/*     */     
/*  42 */     this.backRightFin = new ModelRenderer((Model)this);
/*  43 */     this.backRightFin.func_78793_a(-1.0F, 0.0F, 0.0F);
/*  44 */     this.body.func_78792_a(this.backRightFin);
/*  45 */     this.backRightFin.func_78784_a(27, 29).func_228303_a_(-3.0F, 1.0F, 6.0F, 3.0F, 0.0F, 6.0F, 0.0F, false);
/*     */     
/*  47 */     this.backLeftFin = new ModelRenderer((Model)this);
/*  48 */     this.backLeftFin.func_78793_a(3.0F, 1.0F, 6.0F);
/*  49 */     this.body.func_78792_a(this.backLeftFin);
/*  50 */     this.backLeftFin.func_78784_a(28, 16).func_228303_a_(-2.0F, 0.0F, 0.0F, 3.0F, 0.0F, 6.0F, 0.0F, false);
/*     */     
/*  52 */     this.head = new ModelRenderer((Model)this);
/*  53 */     this.head.func_78793_a(0.0F, 0.0F, -4.0F);
/*  54 */     this.body.func_78792_a(this.head);
/*  55 */     this.head.func_78784_a(33, 0).func_228303_a_(-2.0F, -1.0F, -4.0F, 4.0F, 3.0F, 4.0F, 0.0F, false);
/*     */     
/*  57 */     this.shell = new ModelRenderer((Model)this);
/*  58 */     this.shell.func_78793_a(-1.0F, 0.0F, 0.0F);
/*  59 */     this.body.func_78792_a(this.shell);
/*  60 */     this.shell.func_78784_a(0, 0).func_228303_a_(-4.0F, -2.0F, -5.0F, 10.0F, 3.0F, 12.0F, 0.0F, false);
/*  61 */     this.shell.func_78784_a(0, 29).func_228303_a_(-3.0F, -3.0F, -4.0F, 8.0F, 1.0F, 10.0F, 0.0F, false);
/*  62 */     this.shell.func_78784_a(0, 16).func_228303_a_(-3.0F, 1.0F, -4.0F, 8.0F, 1.0F, 11.0F, 0.0F, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/*  68 */     this.body.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/*  74 */     if (entity.func_213453_ef()) {
/*     */       
/*  76 */       this.head.field_78806_j = false;
/*  77 */       this.frontRightFin.field_78806_j = false;
/*  78 */       this.frontLeftFin.field_78806_j = false;
/*  79 */       this.backRightFin.field_78806_j = false;
/*  80 */       this.backLeftFin.field_78806_j = false;
/*     */     } 
/*     */ 
/*     */     
/*  84 */     this.frontRightFin.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.4F) * 0.1F;
/*  85 */     this.frontRightFin.field_78808_h = MathHelper.func_76126_a(limbSwing * 0.4F) * 0.1F;
/*     */     
/*  87 */     this.frontLeftFin.field_78795_f = -MathHelper.func_76134_b(limbSwing * 0.4F) * 0.1F;
/*  88 */     this.frontLeftFin.field_78808_h = MathHelper.func_76126_a(limbSwing * 0.4F) * 0.1F;
/*     */     
/*  90 */     this.backRightFin.field_78796_g = MathHelper.func_76134_b(limbSwing * 0.4F) * 0.1F;
/*  91 */     this.backRightFin.field_78808_h = MathHelper.func_76126_a(limbSwing * 0.4F) * 0.1F;
/*     */     
/*  93 */     this.backLeftFin.field_78796_g = MathHelper.func_76134_b(limbSwing * 0.4F) * 0.2F;
/*  94 */     this.backLeftFin.field_78808_h = -MathHelper.func_76126_a(limbSwing * 0.4F) * 0.1F;
/*     */ 
/*     */     
/*  97 */     this.field_217112_c = ((LivingEntity)entity).field_110158_av;
/*  98 */     if (this.field_217112_c > 0.0F) {
/*     */       
/* 100 */       this.head.field_78796_g += this.body.field_78796_g;
/* 101 */       float f1 = 1.0F - this.field_217112_c;
/* 102 */       f1 *= f1;
/* 103 */       f1 *= f1;
/* 104 */       f1 = 1.0F - f1;
/* 105 */       float f2 = MathHelper.func_76126_a(f1 * 3.1415927F);
/* 106 */       float f3 = MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * -(this.head.field_78795_f - 0.1F) * 0.15F;
/* 107 */       this.head.field_78795_f = (float)(this.head.field_78795_f - f2 * 1.5D + f3);
/* 108 */       this.head.field_78808_h -= MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * -0.4F;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 114 */     modelRenderer.field_78795_f = x;
/* 115 */     modelRenderer.field_78796_g = y;
/* 116 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225599_a_(HandSide side, MatrixStack matrixStack) {
/* 122 */     if (!this.head.field_78806_j)
/* 123 */       matrixStack.func_227862_a_(-10.0F, -10.0F, -10.0F); 
/* 124 */     matrixStack.func_227861_a_(-0.2D, 1.2D, -0.01D);
/* 125 */     this.head.func_228307_a_(matrixStack);
/* 126 */     matrixStack.func_227863_a_(Vector3f.field_229183_f_.func_229187_a_(90.0F));
/* 127 */     matrixStack.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(280.0F));
/* 128 */     matrixStack.func_227861_a_(0.3D, -0.3D, -0.2D);
/*     */   }
/*     */   
/*     */   public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*     */   
/*     */   public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\morphs\KameGuardModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */