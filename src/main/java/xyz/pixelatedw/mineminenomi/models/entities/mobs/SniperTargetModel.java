/*     */ package xyz.pixelatedw.mineminenomi.models.entities.mobs;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ 
/*     */ public class SniperTargetModel
/*     */   extends BipedModel<LivingEntity>
/*     */ {
/*     */   public ModelRenderer parachute;
/*     */   public ModelRenderer target;
/*     */   public ModelRenderer parachuteChild;
/*     */   public ModelRenderer parachuteChild_1;
/*     */   public ModelRenderer parachuteChild_2;
/*     */   public ModelRenderer parachuteChild_3;
/*     */   public ModelRenderer parachuteChild_4;
/*     */   public ModelRenderer parachuteChild_5;
/*     */   public ModelRenderer parachuteChild_6;
/*     */   public ModelRenderer parachuteChild_7;
/*     */   public ModelRenderer parachuteChild_8;
/*     */   public ModelRenderer parachuteChild_9;
/*     */   public ModelRenderer parachuteChild_10;
/*     */   public ModelRenderer parachuteChild_11;
/*     */   
/*     */   public SniperTargetModel() {
/*  29 */     super(1.0F);
/*  30 */     this.field_78090_t = 64;
/*  31 */     this.field_78089_u = 64;
/*  32 */     this.parachuteChild_4 = new ModelRenderer((Model)this, 5, 30);
/*  33 */     this.parachuteChild_4.func_78793_a(0.0F, 0.0F, 0.0F);
/*  34 */     this.parachuteChild_4.func_228301_a_(-4.0F, -11.5F, -9.0F, 8.0F, 1.0F, 11.0F, 0.0F);
/*  35 */     setRotateAngle(this.parachuteChild_4, 0.2617994F, 1.5707964F, 0.0F);
/*  36 */     this.parachuteChild_11 = new ModelRenderer((Model)this, 5, 30);
/*  37 */     this.parachuteChild_11.func_78793_a(0.0F, 0.0F, 0.0F);
/*  38 */     this.parachuteChild_11.func_228301_a_(-4.0F, -11.5F, -9.0F, 8.0F, 1.0F, 11.0F, 0.0F);
/*  39 */     setRotateAngle(this.parachuteChild_11, 0.2617994F, 3.1415927F, 0.0F);
/*  40 */     this.parachuteChild_3 = new ModelRenderer((Model)this, 0, 30);
/*  41 */     this.parachuteChild_3.func_78793_a(0.0F, 0.0F, 0.0F);
/*  42 */     this.parachuteChild_3.func_228301_a_(0.0F, -11.0F, -1.0F, 1.0F, 12.0F, 1.0F, 0.0F);
/*  43 */     setRotateAngle(this.parachuteChild_3, 0.43633232F, 0.0F, 0.0F);
/*  44 */     this.parachuteChild_9 = new ModelRenderer((Model)this, 5, 30);
/*  45 */     this.parachuteChild_9.func_78793_a(0.0F, 0.0F, 0.0F);
/*  46 */     this.parachuteChild_9.func_228301_a_(-4.0F, -11.5F, -8.0F, 8.0F, 1.0F, 10.0F, 0.0F);
/*  47 */     setRotateAngle(this.parachuteChild_9, 0.2617994F, 2.3561945F, 0.0F);
/*  48 */     this.parachuteChild_7 = new ModelRenderer((Model)this, 0, 30);
/*  49 */     this.parachuteChild_7.func_78793_a(0.0F, 0.0F, 0.0F);
/*  50 */     this.parachuteChild_7.func_228301_a_(0.0F, -11.0F, -1.0F, 1.0F, 12.0F, 1.0F, 0.0F);
/*  51 */     setRotateAngle(this.parachuteChild_7, 0.43633232F, 1.5707964F, 0.0F);
/*  52 */     this.parachuteChild_5 = new ModelRenderer((Model)this, 5, 30);
/*  53 */     this.parachuteChild_5.func_78793_a(0.0F, 0.0F, 0.0F);
/*  54 */     this.parachuteChild_5.func_228301_a_(-4.0F, -11.5F, -8.0F, 8.0F, 1.0F, 10.0F, 0.0F);
/*  55 */     setRotateAngle(this.parachuteChild_5, 0.2617994F, 3.9269907F, 0.0F);
/*  56 */     this.parachute = new ModelRenderer((Model)this, 0, 30);
/*  57 */     this.parachute.func_78793_a(0.0F, 19.0F, 0.0F);
/*  58 */     this.parachute.func_228301_a_(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F);
/*  59 */     this.target = new ModelRenderer((Model)this, 0, 0);
/*  60 */     this.target.func_78793_a(0.0F, 20.0F, 0.0F);
/*  61 */     this.target.func_228301_a_(-4.0F, 0.0F, -0.5F, 8.0F, 8.0F, 1.0F, 0.0F);
/*  62 */     this.parachuteChild_1 = new ModelRenderer((Model)this, 5, 30);
/*  63 */     this.parachuteChild_1.func_78793_a(0.0F, 0.0F, 0.0F);
/*  64 */     this.parachuteChild_1.func_228301_a_(-4.0F, -11.5F, -8.0F, 8.0F, 1.0F, 10.0F, 0.0F);
/*  65 */     setRotateAngle(this.parachuteChild_1, 0.2617994F, 0.7853982F, 0.0F);
/*  66 */     this.parachuteChild_10 = new ModelRenderer((Model)this, 0, 30);
/*  67 */     this.parachuteChild_10.func_78793_a(0.0F, 0.0F, 0.0F);
/*  68 */     this.parachuteChild_10.func_228301_a_(0.0F, -11.0F, -1.0F, 1.0F, 12.0F, 1.0F, 0.0F);
/*  69 */     setRotateAngle(this.parachuteChild_10, 0.43633232F, -1.5707964F, 0.0F);
/*  70 */     this.parachuteChild_8 = new ModelRenderer((Model)this, 5, 30);
/*  71 */     this.parachuteChild_8.func_78793_a(0.0F, 0.0F, 0.0F);
/*  72 */     this.parachuteChild_8.func_228301_a_(-4.0F, -11.5F, -9.0F, 8.0F, 1.0F, 11.0F, 0.0F);
/*  73 */     setRotateAngle(this.parachuteChild_8, 0.2617994F, 0.0F, 0.0F);
/*  74 */     this.parachuteChild = new ModelRenderer((Model)this, 5, 30);
/*  75 */     this.parachuteChild.func_78793_a(0.0F, 0.0F, 0.0F);
/*  76 */     this.parachuteChild.func_228301_a_(-4.0F, -11.5F, -8.0F, 8.0F, 1.0F, 10.0F, 0.0F);
/*  77 */     setRotateAngle(this.parachuteChild, 0.2617994F, 5.497787F, 0.0F);
/*  78 */     this.parachuteChild_2 = new ModelRenderer((Model)this, 5, 30);
/*  79 */     this.parachuteChild_2.func_78793_a(0.0F, 0.0F, 0.0F);
/*  80 */     this.parachuteChild_2.func_228301_a_(-4.0F, -11.5F, -9.0F, 8.0F, 1.0F, 11.0F, 0.0F);
/*  81 */     setRotateAngle(this.parachuteChild_2, 0.2617994F, 4.712389F, 0.0F);
/*  82 */     this.parachuteChild_6 = new ModelRenderer((Model)this, 0, 30);
/*  83 */     this.parachuteChild_6.func_78793_a(0.0F, 0.0F, 0.0F);
/*  84 */     this.parachuteChild_6.func_228301_a_(0.0F, -11.0F, -1.0F, 1.0F, 12.0F, 1.0F, 0.0F);
/*  85 */     setRotateAngle(this.parachuteChild_6, 0.43633232F, 3.1415927F, 0.0F);
/*  86 */     this.parachute.func_78792_a(this.parachuteChild_4);
/*  87 */     this.parachute.func_78792_a(this.parachuteChild_11);
/*  88 */     this.parachute.func_78792_a(this.parachuteChild_3);
/*  89 */     this.parachute.func_78792_a(this.parachuteChild_9);
/*  90 */     this.parachute.func_78792_a(this.parachuteChild_7);
/*  91 */     this.parachute.func_78792_a(this.parachuteChild_5);
/*  92 */     this.parachute.func_78792_a(this.parachuteChild_1);
/*  93 */     this.parachute.func_78792_a(this.parachuteChild_10);
/*  94 */     this.parachute.func_78792_a(this.parachuteChild_8);
/*  95 */     this.parachute.func_78792_a(this.parachuteChild);
/*  96 */     this.parachute.func_78792_a(this.parachuteChild_2);
/*  97 */     this.parachute.func_78792_a(this.parachuteChild_6);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 103 */     this.parachute.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 104 */     this.target.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
/* 109 */     model.field_78795_f = x;
/* 110 */     model.field_78796_g = y;
/* 111 */     model.field_78808_h = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\entities\mobs\SniperTargetModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */