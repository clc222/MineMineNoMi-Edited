/*     */ package xyz.pixelatedw.mineminenomi.models.abilities;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ 
/*     */ public class GomuSmokeModel extends EntityModel<Entity> {
/*     */   private final ModelRenderer smoke;
/*     */   private final ModelRenderer rightSmoke;
/*     */   private final ModelRenderer rightSmoke2;
/*     */   private final ModelRenderer rightSmoke3;
/*     */   private final ModelRenderer leftSmoke;
/*     */   private final ModelRenderer leftSmoke2;
/*     */   private final ModelRenderer leftSmoke3;
/*     */   private final ModelRenderer backSmoke;
/*     */   private final ModelRenderer backSmoke3;
/*     */   private final ModelRenderer backSmoke2;
/*     */   private final ModelRenderer backSmoke5;
/*     */   private final ModelRenderer backSmoke4;
/*     */   
/*     */   public GomuSmokeModel() {
/*  25 */     this.field_78090_t = 32;
/*  26 */     this.field_78089_u = 32;
/*     */     
/*  28 */     this.smoke = new ModelRenderer((Model)this);
/*  29 */     this.smoke.func_78793_a(0.0F, 0.0F, 0.5F);
/*     */ 
/*     */     
/*  32 */     this.rightSmoke = new ModelRenderer((Model)this);
/*  33 */     this.rightSmoke.func_78793_a(-6.0F, -1.1156F, -2.112F);
/*  34 */     this.smoke.func_78792_a(this.rightSmoke);
/*  35 */     setRotationAngle(this.rightSmoke, -0.5672F, 0.0F, 0.0F);
/*  36 */     this.rightSmoke.func_78784_a(0, 15).func_228303_a_(-2.0F, -1.3844F, 0.0F, 4.0F, 4.0F, 0.0F, 0.0F, false);
/*     */     
/*  38 */     this.rightSmoke2 = new ModelRenderer((Model)this);
/*  39 */     this.rightSmoke2.func_78793_a(0.0F, 4.8844F, 0.888F);
/*  40 */     this.rightSmoke.func_78792_a(this.rightSmoke2);
/*  41 */     setRotationAngle(this.rightSmoke2, 0.3491F, 0.0F, 0.0F);
/*  42 */     this.rightSmoke2.func_78784_a(0, 9).func_228303_a_(-2.0F, -2.5F, 0.0F, 4.0F, 5.0F, 0.0F, 0.0F, false);
/*     */     
/*  44 */     this.rightSmoke3 = new ModelRenderer((Model)this);
/*  45 */     this.rightSmoke3.func_78793_a(0.0F, 5.0F, 0.75F);
/*  46 */     this.rightSmoke2.func_78792_a(this.rightSmoke3);
/*  47 */     setRotationAngle(this.rightSmoke3, 0.2618F, 0.0F, 0.0F);
/*  48 */     this.rightSmoke3.func_78784_a(0, 0).func_228303_a_(-2.0F, -2.6188F, -0.0759F, 4.0F, 8.0F, 0.0F, 0.0F, false);
/*     */     
/*  50 */     this.leftSmoke = new ModelRenderer((Model)this);
/*  51 */     this.leftSmoke.func_78793_a(6.0F, -1.1156F, -2.112F);
/*  52 */     this.smoke.func_78792_a(this.leftSmoke);
/*  53 */     setRotationAngle(this.leftSmoke, -0.5672F, 0.0F, 0.0F);
/*  54 */     this.leftSmoke.func_78784_a(0, 15).func_228303_a_(-2.0F, -1.3844F, 0.0F, 4.0F, 4.0F, 0.0F, 0.0F, true);
/*     */     
/*  56 */     this.leftSmoke2 = new ModelRenderer((Model)this);
/*  57 */     this.leftSmoke2.func_78793_a(0.0F, 4.8844F, 0.888F);
/*  58 */     this.leftSmoke.func_78792_a(this.leftSmoke2);
/*  59 */     setRotationAngle(this.leftSmoke2, 0.3491F, 0.0F, 0.0F);
/*  60 */     this.leftSmoke2.func_78784_a(0, 9).func_228303_a_(-2.0F, -2.5F, 0.0F, 4.0F, 5.0F, 0.0F, 0.0F, true);
/*     */     
/*  62 */     this.leftSmoke3 = new ModelRenderer((Model)this);
/*  63 */     this.leftSmoke3.func_78793_a(0.0F, 5.0F, 0.75F);
/*  64 */     this.leftSmoke2.func_78792_a(this.leftSmoke3);
/*  65 */     setRotationAngle(this.leftSmoke3, 0.2618F, 0.0F, 0.0F);
/*  66 */     this.leftSmoke3.func_78784_a(0, 0).func_228303_a_(-2.0F, -2.6188F, -0.0759F, 4.0F, 8.0F, 0.0F, 0.0F, true);
/*     */     
/*  68 */     this.backSmoke = new ModelRenderer((Model)this);
/*  69 */     this.backSmoke.func_78793_a(-6.5F, -3.065F, 1.0686F);
/*  70 */     this.smoke.func_78792_a(this.backSmoke);
/*  71 */     setRotationAngle(this.backSmoke, -1.309F, 0.0F, 0.0F);
/*  72 */     this.backSmoke.func_78784_a(0, 20).func_228303_a_(-1.5F, -2.435F, 0.1314F, 4.0F, 5.0F, 0.0F, 0.0F, false);
/*     */     
/*  74 */     this.backSmoke3 = new ModelRenderer((Model)this);
/*  75 */     this.backSmoke3.func_78793_a(0.9475F, -6.5053F, -1.1009F);
/*  76 */     this.backSmoke.func_78792_a(this.backSmoke3);
/*  77 */     setRotationAngle(this.backSmoke3, 0.5236F, 0.0F, 0.0F);
/*  78 */     this.backSmoke3.func_78784_a(0, 26).func_228303_a_(-2.5F, -3.5F, -0.25F, 16.0F, 5.0F, 0.0F, 0.0F, false);
/*     */     
/*  80 */     this.backSmoke2 = new ModelRenderer((Model)this);
/*  81 */     this.backSmoke2.func_78793_a(-0.0525F, -3.5053F, -0.1009F);
/*  82 */     this.backSmoke.func_78792_a(this.backSmoke2);
/*  83 */     setRotationAngle(this.backSmoke2, 0.2618F, 0.0F, 0.0F);
/*  84 */     this.backSmoke2.func_78784_a(11, 0).func_228303_a_(-1.5F, -2.75F, -0.0977F, 4.0F, 4.0F, 0.0F, 0.0F, false);
/*     */     
/*  86 */     this.backSmoke5 = new ModelRenderer((Model)this);
/*  87 */     this.backSmoke5.func_78793_a(11.9475F, -3.5053F, -0.1009F);
/*  88 */     this.backSmoke.func_78792_a(this.backSmoke5);
/*  89 */     setRotationAngle(this.backSmoke5, 0.2618F, 0.0F, 0.0F);
/*  90 */     this.backSmoke5.func_78784_a(11, 0).func_228303_a_(-1.5F, -2.75F, -0.0977F, 4.0F, 4.0F, 0.0F, 0.0F, true);
/*     */     
/*  92 */     this.backSmoke4 = new ModelRenderer((Model)this);
/*  93 */     this.backSmoke4.func_78793_a(12.0F, 0.0F, 0.0F);
/*  94 */     this.backSmoke.func_78792_a(this.backSmoke4);
/*  95 */     this.backSmoke4.func_78784_a(0, 20).func_228303_a_(-1.5F, -2.435F, 0.1314F, 4.0F, 5.0F, 0.0F, 0.0F, true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225597_a_(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 105 */     this.smoke.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */   }
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 109 */     modelRenderer.field_78795_f = x;
/* 110 */     modelRenderer.field_78796_g = y;
/* 111 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\abilities\GomuSmokeModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */