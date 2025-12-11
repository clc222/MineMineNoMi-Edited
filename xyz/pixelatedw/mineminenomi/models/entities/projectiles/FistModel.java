/*     */ package xyz.pixelatedw.mineminenomi.models.entities.projectiles;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class FistModel
/*     */   extends EntityModel
/*     */ {
/*     */   private final ModelRenderer arm;
/*     */   private final ModelRenderer rightfinger50;
/*     */   private final ModelRenderer rightfinger30;
/*     */   private final ModelRenderer rightfinger31;
/*     */   private final ModelRenderer righthand2;
/*     */   private final ModelRenderer rightfinger40;
/*     */   private final ModelRenderer rightfinger41;
/*     */   private final ModelRenderer rightfinger10;
/*     */   private final ModelRenderer rightfinger11;
/*     */   private final ModelRenderer elbow;
/*     */   private final ModelRenderer rightfinger20;
/*     */   private final ModelRenderer rightfinger21;
/*     */   private final ModelRenderer righthand1;
/*     */   
/*     */   public FistModel() {
/*  31 */     this.field_78090_t = 64;
/*  32 */     this.field_78089_u = 32;
/*     */     
/*  34 */     this.arm = new ModelRenderer((Model)this);
/*  35 */     this.arm.func_78793_a(0.0F, -1.5F, 1.0F);
/*     */ 
/*     */     
/*  38 */     this.rightfinger30 = new ModelRenderer((Model)this);
/*  39 */     this.rightfinger30.func_78793_a(0.4993F, 0.5F, -4.4738F);
/*  40 */     this.arm.func_78792_a(this.rightfinger30);
/*  41 */     setRotationAngle(this.rightfinger30, 0.0F, -0.0524F, 0.0F);
/*  42 */     this.rightfinger30.func_78784_a(17, 6).func_228303_a_(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/*  44 */     this.rightfinger31 = new ModelRenderer((Model)this);
/*  45 */     this.rightfinger31.func_78793_a(0.0F, 0.491F, 0.6511F);
/*  46 */     this.rightfinger30.func_78792_a(this.rightfinger31);
/*  47 */     setRotationAngle(this.rightfinger31, 1.885F, 0.0F, 0.0F);
/*  48 */     this.rightfinger31.func_78784_a(17, 10).func_228303_a_(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/*  50 */     this.rightfinger50 = new ModelRenderer((Model)this);
/*  51 */     this.rightfinger50.func_78793_a(1.2847F, 0.8365F, -2.7746F);
/*  52 */     this.arm.func_78792_a(this.rightfinger50);
/*  53 */     setRotationAngle(this.rightfinger50, 0.0641F, 0.9545F, 1.6947F);
/*  54 */     this.rightfinger50.func_78784_a(17, 14).func_228303_a_(-1.0F, -1.0F, -0.5F, 2.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/*  56 */     this.righthand2 = new ModelRenderer((Model)this);
/*  57 */     this.righthand2.func_78793_a(1.7758F, 0.0F, -1.595F);
/*  58 */     this.arm.func_78792_a(this.righthand2);
/*  59 */     setRotationAngle(this.righthand2, -1.5708F, -0.4363F, 0.0F);
/*  60 */     this.righthand2.func_78784_a(28, 0).func_228303_a_(-0.5F, -1.0F, -1.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
/*     */     
/*  62 */     this.rightfinger40 = new ModelRenderer((Model)this);
/*  63 */     this.rightfinger40.func_78793_a(1.4981F, 0.5F, -4.4564F);
/*  64 */     this.arm.func_78792_a(this.rightfinger40);
/*  65 */     setRotationAngle(this.rightfinger40, 0.0F, -0.0873F, 0.0F);
/*  66 */     this.rightfinger40.func_78784_a(17, 6).func_228303_a_(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/*  68 */     this.rightfinger41 = new ModelRenderer((Model)this);
/*  69 */     this.rightfinger41.func_78793_a(0.0F, 0.491F, 0.6511F);
/*  70 */     this.rightfinger40.func_78792_a(this.rightfinger41);
/*  71 */     setRotationAngle(this.rightfinger41, 1.885F, 0.0F, 0.0F);
/*  72 */     this.rightfinger41.func_78784_a(17, 10).func_228303_a_(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/*  74 */     this.rightfinger10 = new ModelRenderer((Model)this);
/*  75 */     this.rightfinger10.func_78793_a(-1.4981F, 0.5F, -4.4564F);
/*  76 */     this.arm.func_78792_a(this.rightfinger10);
/*  77 */     setRotationAngle(this.rightfinger10, 0.0F, 0.0873F, 0.0F);
/*  78 */     this.rightfinger10.func_78784_a(17, 6).func_228303_a_(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/*  80 */     this.rightfinger11 = new ModelRenderer((Model)this);
/*  81 */     this.rightfinger11.func_78793_a(0.0F, 0.5076F, 0.6563F);
/*  82 */     this.rightfinger10.func_78792_a(this.rightfinger11);
/*  83 */     setRotationAngle(this.rightfinger11, 1.8675F, 0.0F, 0.0F);
/*  84 */     this.rightfinger11.func_78784_a(17, 10).func_228303_a_(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/*  86 */     this.elbow = new ModelRenderer((Model)this);
/*  87 */     this.elbow.func_78793_a(0.0F, 0.0F, 1.5F);
/*  88 */     this.arm.func_78792_a(this.elbow);
/*  89 */     this.elbow.func_78784_a(0, 0).func_228303_a_(-2.0F, -2.0F, -2.5F, 4.0F, 4.0F, 5.0F, 0.01F, false);
/*     */     
/*  91 */     this.rightfinger20 = new ModelRenderer((Model)this);
/*  92 */     this.rightfinger20.func_78793_a(-0.4993F, 0.5F, -4.4738F);
/*  93 */     this.arm.func_78792_a(this.rightfinger20);
/*  94 */     setRotationAngle(this.rightfinger20, 0.0F, 0.0524F, 0.0F);
/*  95 */     this.rightfinger20.func_78784_a(17, 6).func_228303_a_(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/*  97 */     this.rightfinger21 = new ModelRenderer((Model)this);
/*  98 */     this.rightfinger21.func_78793_a(0.0F, 0.5076F, 0.6563F);
/*  99 */     this.rightfinger20.func_78792_a(this.rightfinger21);
/* 100 */     setRotationAngle(this.rightfinger21, 1.8675F, 0.0F, 0.0F);
/* 101 */     this.rightfinger21.func_78784_a(17, 10).func_228303_a_(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/* 103 */     this.righthand1 = new ModelRenderer((Model)this);
/* 104 */     this.righthand1.func_78793_a(0.0F, -1.0F, -2.5F);
/* 105 */     this.arm.func_78792_a(this.righthand1);
/* 106 */     setRotationAngle(this.righthand1, -1.5708F, 0.0F, 0.0F);
/* 107 */     this.righthand1.func_78784_a(17, 0).func_228303_a_(-2.0F, -2.5F, -0.5F, 4.0F, 5.0F, 1.0F, 0.0F, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
/* 113 */     this.arm.func_228309_a_(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225597_a_(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer model, float x, float y, float z) {
/* 124 */     model.field_78795_f = x;
/* 125 */     model.field_78796_g = y;
/* 126 */     model.field_78808_h = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\entities\projectiles\FistModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */