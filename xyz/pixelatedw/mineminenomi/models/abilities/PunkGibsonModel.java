/*     */ package xyz.pixelatedw.mineminenomi.models.abilities;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ 
/*     */ public class PunkGibsonModel
/*     */   extends EntityModel<Entity>
/*     */ {
/*     */   public final ModelRenderer rightArm;
/*     */   private final ModelRenderer smallArm;
/*     */   private final ModelRenderer bigArm;
/*     */   private final ModelRenderer finger4;
/*     */   private final ModelRenderer finger4b;
/*     */   private final ModelRenderer finger3;
/*     */   private final ModelRenderer finger3b;
/*     */   private final ModelRenderer finger2;
/*     */   private final ModelRenderer finger2b;
/*     */   private final ModelRenderer finger1;
/*     */   private final ModelRenderer finger1b;
/*     */   
/*     */   public PunkGibsonModel() {
/*  26 */     this.field_78090_t = 128;
/*  27 */     this.field_78089_u = 128;
/*     */     
/*  29 */     this.rightArm = new ModelRenderer((Model)this);
/*  30 */     this.rightArm.func_78793_a(-2.75F, -0.75F, -0.5F);
/*  31 */     setRotationAngle(this.rightArm, -1.5272F, 0.0F, 0.0F);
/*     */     
/*  33 */     this.smallArm = new ModelRenderer((Model)this);
/*  34 */     this.smallArm.func_78793_a(-1.0019F, -0.0872F, 2.5F);
/*  35 */     this.rightArm.func_78792_a(this.smallArm);
/*  36 */     this.smallArm.func_78784_a(64, 83).func_228303_a_(-16.0F, -7.0F, -6.0F, 16.0F, 29.0F, 16.0F, 0.0F, false);
/*     */     
/*  38 */     this.bigArm = new ModelRenderer((Model)this);
/*  39 */     this.bigArm.func_78793_a(-8.0019F, 19.9128F, 5.0F);
/*  40 */     this.rightArm.func_78792_a(this.bigArm);
/*  41 */     setRotationAngle(this.bigArm, 0.0452F, 0.3127F, -0.1608F);
/*  42 */     this.bigArm.func_78784_a(0, 0).func_228303_a_(-12.7334F, 0.2782F, -11.8359F, 24.0F, 58.0F, 24.0F, 0.0F, false);
/*     */     
/*  44 */     this.finger4 = new ModelRenderer((Model)this);
/*  45 */     this.finger4.func_78793_a(11.3785F, 55.124F, -6.8285F);
/*  46 */     this.bigArm.func_78792_a(this.finger4);
/*  47 */     setRotationAngle(this.finger4, 0.7105F, -0.7987F, -0.3516F);
/*  48 */     this.finger4.func_78784_a(0, 103).func_228303_a_(-3.5F, -2.0F, -7.0F, 7.0F, 4.0F, 14.0F, 0.0F, false);
/*     */     
/*  50 */     this.finger4b = new ModelRenderer((Model)this);
/*  51 */     this.finger4b.func_78793_a(0.0F, -1.0418F, -6.3826F);
/*  52 */     this.finger4.func_78792_a(this.finger4b);
/*  53 */     setRotationAngle(this.finger4b, 1.0472F, 0.0F, 0.0F);
/*  54 */     this.finger4b.func_78784_a(0, 85).func_228303_a_(-3.5F, -1.2224F, -14.1691F, 7.0F, 4.0F, 14.0F, 0.01F, false);
/*     */     
/*  56 */     this.finger3 = new ModelRenderer((Model)this);
/*  57 */     this.finger3.func_78793_a(6.6941F, 56.5544F, -11.7482F);
/*  58 */     this.bigArm.func_78792_a(this.finger3);
/*  59 */     setRotationAngle(this.finger3, 0.8733F, -0.028F, -0.0259F);
/*  60 */     this.finger3.func_78784_a(0, 103).func_228303_a_(-3.2897F, -0.6527F, -11.7155F, 7.0F, 4.0F, 14.0F, 0.0F, false);
/*     */     
/*  62 */     this.finger3b = new ModelRenderer((Model)this);
/*  63 */     this.finger3b.func_78793_a(0.0F, -2.6608F, -11.5565F);
/*  64 */     this.finger3.func_78792_a(this.finger3b);
/*  65 */     setRotationAngle(this.finger3b, 1.0036F, 0.0F, 0.0F);
/*  66 */     this.finger3b.func_78784_a(0, 85).func_228303_a_(-3.2897F, 0.9734F, -15.8321F, 7.0F, 4.0F, 14.0F, 0.01F, false);
/*     */     
/*  68 */     this.finger2 = new ModelRenderer((Model)this);
/*  69 */     this.finger2.func_78793_a(-1.3059F, 56.5544F, -11.7482F);
/*  70 */     this.bigArm.func_78792_a(this.finger2);
/*  71 */     setRotationAngle(this.finger2, 0.8728F, 0.0F, 0.0076F);
/*  72 */     this.finger2.func_78784_a(0, 103).func_228303_a_(-3.299F, -0.6527F, -11.7065F, 7.0F, 4.0F, 14.0F, 0.0F, false);
/*     */     
/*  74 */     this.finger2b = new ModelRenderer((Model)this);
/*  75 */     this.finger2b.func_78793_a(0.0F, -2.9108F, -12.3065F);
/*  76 */     this.finger2.func_78792_a(this.finger2b);
/*  77 */     setRotationAngle(this.finger2b, 1.0036F, 0.0F, 0.0F);
/*  78 */     this.finger2b.func_78784_a(0, 85).func_228303_a_(-3.299F, 1.5005F, -15.4963F, 7.0F, 4.0F, 14.0F, 0.01F, false);
/*     */     
/*  80 */     this.finger1 = new ModelRenderer((Model)this);
/*  81 */     this.finger1.func_78793_a(-9.3674F, 56.552F, -11.763F);
/*  82 */     this.bigArm.func_78792_a(this.finger1);
/*  83 */     setRotationAngle(this.finger1, 0.8728F, 0.043F, 0.0076F);
/*  84 */     this.finger1.func_78784_a(0, 103).func_228303_a_(-3.1633F, -0.6465F, -11.6975F, 7.0F, 4.0F, 14.0F, 0.0F, false);
/*     */     
/*  86 */     this.finger1b = new ModelRenderer((Model)this);
/*  87 */     this.finger1b.func_78793_a(0.2148F, -2.91F, -12.3019F);
/*  88 */     this.finger1.func_78792_a(this.finger1b);
/*  89 */     setRotationAngle(this.finger1b, 1.0036F, 0.0F, 0.0F);
/*  90 */     this.finger1b.func_78784_a(0, 85).func_228303_a_(-3.3781F, 1.5072F, -15.4983F, 7.0F, 4.0F, 14.0F, 0.01F, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225597_a_(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 102 */     this.rightArm.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 107 */     modelRenderer.field_78795_f = x;
/* 108 */     modelRenderer.field_78796_g = y;
/* 109 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\abilities\PunkGibsonModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */