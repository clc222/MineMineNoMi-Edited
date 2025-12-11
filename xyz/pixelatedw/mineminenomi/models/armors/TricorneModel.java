/*     */ package xyz.pixelatedw.mineminenomi.models.armors;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ 
/*     */ public class TricorneModel<T extends LivingEntity>
/*     */   extends BipedModel<T> {
/*     */   private final ModelRenderer hat;
/*     */   private final ModelRenderer side1;
/*     */   private final ModelRenderer side12;
/*     */   private final ModelRenderer side2;
/*     */   private final ModelRenderer side22;
/*     */   private final ModelRenderer side3;
/*     */   private final ModelRenderer side32;
/*     */   private final ModelRenderer mid;
/*     */   private final ModelRenderer filling1;
/*     */   private final ModelRenderer filling2;
/*     */   
/*     */   public TricorneModel() {
/*  25 */     super(1.0F);
/*  26 */     this.field_78090_t = 128;
/*  27 */     this.field_78089_u = 128;
/*     */     
/*  29 */     this.hat = new ModelRenderer((Model)this);
/*  30 */     this.hat.func_78793_a(0.0F, 0.0F, 0.0F);
/*     */     
/*  32 */     this.side1 = new ModelRenderer((Model)this);
/*  33 */     this.side1.func_78793_a(-5.8303F, -5.5F, -3.1883F);
/*  34 */     this.hat.func_78792_a(this.side1);
/*  35 */     setRotationAngle(this.side1, 0.0F, -0.6109F, 0.0F);
/*  36 */     this.side1.func_78784_a(21, 0).func_228303_a_(-0.2997F, -0.5F, -10.6117F, 1.0F, 1.0F, 20.0F, 0.0F, false);
/*     */     
/*  38 */     this.side12 = new ModelRenderer((Model)this);
/*  39 */     this.side12.func_78793_a(-0.0497F, -2.0F, -0.2117F);
/*  40 */     this.side1.func_78792_a(this.side12);
/*  41 */     setRotationAngle(this.side12, 0.0F, 0.0F, 0.096F);
/*  42 */     this.side12.func_78784_a(0, 35).func_228303_a_(0.0F, -2.4F, -10.4F, 0.0F, 4.0F, 20.0F, 0.0F, false);
/*     */     
/*  44 */     this.side2 = new ModelRenderer((Model)this);
/*  45 */     this.side2.func_78793_a(5.8961F, -5.5F, -3.1225F);
/*  46 */     this.hat.func_78792_a(this.side2);
/*  47 */     setRotationAngle(this.side2, 0.0F, 0.6109F, 0.0F);
/*  48 */     this.side2.func_78784_a(22, 0).func_228303_a_(-0.5F, -0.5F, -10.25F, 1.0F, 1.0F, 20.0F, -0.01F, false);
/*     */     
/*  50 */     this.side22 = new ModelRenderer((Model)this);
/*  51 */     this.side22.func_78793_a(0.75F, -1.0F, 0.0F);
/*  52 */     this.side2.func_78792_a(this.side22);
/*  53 */     setRotationAngle(this.side22, 0.0F, 0.0F, -0.1309F);
/*  54 */     this.side22.func_78784_a(0, 35).func_228303_a_(-0.5F, -3.4F, -10.5F, 0.0F, 4.0F, 20.0F, 0.0F, false);
/*     */     
/*  56 */     this.side3 = new ModelRenderer((Model)this);
/*  57 */     this.side3.func_78793_a(0.1F, -5.5F, 4.75F);
/*  58 */     this.hat.func_78792_a(this.side3);
/*  59 */     setRotationAngle(this.side3, 0.0F, -1.5708F, 0.0F);
/*  60 */     this.side3.func_78784_a(0, 22).func_228303_a_(-0.5F, -0.5F, -11.0F, 1.0F, 1.0F, 22.0F, -0.001F, false);
/*     */     
/*  62 */     this.side32 = new ModelRenderer((Model)this);
/*  63 */     this.side32.func_78793_a(-0.25F, -1.0F, 0.0F);
/*  64 */     this.side3.func_78792_a(this.side32);
/*  65 */     setRotationAngle(this.side32, 0.0F, 0.0F, -0.1309F);
/*  66 */     this.side32.func_78784_a(0, 26).func_228303_a_(0.5F, -3.4F, -11.0F, 0.0F, 4.0F, 22.0F, 0.0F, false);
/*     */     
/*  68 */     this.mid = new ModelRenderer((Model)this);
/*  69 */     this.mid.func_78793_a(2.5F, -5.5F, 4.75F);
/*  70 */     this.hat.func_78792_a(this.mid);
/*  71 */     this.mid.func_78784_a(44, 0).func_228303_a_(-7.5F, -4.5F, -9.75F, 10.0F, 5.0F, 10.0F, -0.48F, false);
/*     */     
/*  73 */     this.filling1 = new ModelRenderer((Model)this);
/*  74 */     this.filling1.func_78793_a(-0.6889F, -5.5F, -5.0154F);
/*  75 */     this.hat.func_78792_a(this.filling1);
/*  76 */     setRotationAngle(this.filling1, 0.0F, -0.8727F, 0.0F);
/*  77 */     this.filling1.func_78784_a(0, 0).func_228303_a_(-3.75F, -0.5F, -4.25F, 8.0F, 1.0F, 8.0F, -0.05F, false);
/*     */     
/*  79 */     this.filling2 = new ModelRenderer((Model)this);
/*  80 */     this.filling2.func_78793_a(5.5611F, -5.5F, 2.2346F);
/*  81 */     this.hat.func_78792_a(this.filling2);
/*  82 */     this.filling2.func_78784_a(26, 23).func_228303_a_(-16.0F, -0.5F, -6.5F, 21.0F, 1.0F, 9.0F, -0.04F, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/*  93 */     this.hat.func_217177_a(this.field_78116_c);
/*  94 */     this.hat.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/*  99 */     modelRenderer.field_78795_f = x;
/* 100 */     modelRenderer.field_78796_g = y;
/* 101 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\armors\TricorneModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */