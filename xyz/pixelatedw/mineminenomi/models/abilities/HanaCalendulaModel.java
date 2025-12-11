/*     */ package xyz.pixelatedw.mineminenomi.models.abilities;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.renderer.entity.model.PlayerModel;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.math.vector.Vector3f;
/*     */ 
/*     */ public class HanaCalendulaModel<T extends LivingEntity>
/*     */   extends PlayerModel<T> {
/*     */   private final ModelRenderer calendula;
/*     */   private final ModelRenderer calendula1;
/*     */   private final ModelRenderer calendula2;
/*     */   private final ModelRenderer calendula3;
/*     */   private final ModelRenderer calendula4;
/*     */   private final ModelRenderer calendula5;
/*     */   private final ModelRenderer calendula6;
/*     */   private final ModelRenderer calendula7;
/*     */   private final ModelRenderer calendula8;
/*     */   
/*     */   public HanaCalendulaModel() {
/*  25 */     super(1.0F, false);
/*  26 */     this.field_78090_t = 64;
/*  27 */     this.field_78089_u = 64;
/*     */     
/*  29 */     this.calendula = new ModelRenderer((Model)this);
/*  30 */     this.calendula.func_78793_a(-9.0F, 7.0F, 0.0F);
/*     */     
/*  32 */     this.calendula1 = new ModelRenderer((Model)this);
/*  33 */     this.calendula1.func_78793_a(0.0F, 0.0F, 0.0F);
/*  34 */     this.calendula.func_78792_a(this.calendula1);
/*  35 */     this.calendula1.func_78784_a(40, 16).func_228303_a_(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
/*     */     
/*  37 */     this.calendula2 = new ModelRenderer((Model)this);
/*  38 */     this.calendula2.func_78793_a(0.0F, 0.0F, 0.0F);
/*  39 */     this.calendula.func_78792_a(this.calendula2);
/*  40 */     setRotationAngle(this.calendula2, -0.7854F, 0.0F, 0.0F);
/*  41 */     this.calendula2.func_78784_a(40, 16).func_228303_a_(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.01F, false);
/*     */     
/*  43 */     this.calendula3 = new ModelRenderer((Model)this);
/*  44 */     this.calendula3.func_78793_a(0.0F, 0.0F, 0.0F);
/*  45 */     this.calendula.func_78792_a(this.calendula3);
/*  46 */     setRotationAngle(this.calendula3, -1.5708F, 0.0F, 0.0F);
/*  47 */     this.calendula3.func_78784_a(40, 16).func_228303_a_(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.011F, false);
/*     */     
/*  49 */     this.calendula4 = new ModelRenderer((Model)this);
/*  50 */     this.calendula4.func_78793_a(0.0F, 0.0F, 0.0F);
/*  51 */     this.calendula.func_78792_a(this.calendula4);
/*  52 */     setRotationAngle(this.calendula4, -2.3562F, 0.0F, 0.0F);
/*  53 */     this.calendula4.func_78784_a(40, 16).func_228303_a_(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.012F, false);
/*     */     
/*  55 */     this.calendula5 = new ModelRenderer((Model)this);
/*  56 */     this.calendula5.func_78793_a(0.0F, 0.0F, 0.0F);
/*  57 */     this.calendula.func_78792_a(this.calendula5);
/*  58 */     setRotationAngle(this.calendula5, 3.1416F, 0.0F, 0.0F);
/*  59 */     this.calendula5.func_78784_a(40, 16).func_228303_a_(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.013F, false);
/*     */     
/*  61 */     this.calendula6 = new ModelRenderer((Model)this);
/*  62 */     this.calendula6.func_78793_a(0.0F, 0.0F, 0.0F);
/*  63 */     this.calendula.func_78792_a(this.calendula6);
/*  64 */     setRotationAngle(this.calendula6, 2.3562F, 0.0F, 0.0F);
/*  65 */     this.calendula6.func_78784_a(40, 16).func_228303_a_(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.014F, false);
/*     */     
/*  67 */     this.calendula7 = new ModelRenderer((Model)this);
/*  68 */     this.calendula7.func_78793_a(0.0F, 0.0F, 0.0F);
/*  69 */     this.calendula.func_78792_a(this.calendula7);
/*  70 */     setRotationAngle(this.calendula7, 1.5708F, 0.0F, 0.0F);
/*  71 */     this.calendula7.func_78784_a(40, 16).func_228303_a_(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.015F, false);
/*     */     
/*  73 */     this.calendula8 = new ModelRenderer((Model)this);
/*  74 */     this.calendula8.func_78793_a(0.0F, 0.0F, 0.0F);
/*  75 */     this.calendula.func_78792_a(this.calendula8);
/*  76 */     setRotationAngle(this.calendula8, 0.7854F, 0.0F, 0.0F);
/*  77 */     this.calendula8.func_78784_a(40, 16).func_228303_a_(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.016F, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/*  83 */     this.calendula.field_78795_f = -ageInTicks * 0.2F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/*  89 */     matrixStack.func_227860_a_();
/*  90 */     matrixStack.func_227861_a_(0.0D, -0.4D, 0.0D);
/*  91 */     matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(-90.0F));
/*  92 */     this.calendula.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/*  93 */     matrixStack.func_227865_b_();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/*  98 */     modelRenderer.field_78795_f = x;
/*  99 */     modelRenderer.field_78796_g = y;
/* 100 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\abilities\HanaCalendulaModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */