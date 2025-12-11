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
/*     */ public class BazookaModel
/*     */   extends EntityModel
/*     */ {
/*     */   public ModelRenderer rightarm;
/*     */   public ModelRenderer leftarm;
/*     */   public ModelRenderer righthand;
/*     */   public ModelRenderer rightfinger10;
/*     */   public ModelRenderer rightfinger11;
/*     */   public ModelRenderer rightfinger20;
/*     */   public ModelRenderer rightfinger21;
/*     */   public ModelRenderer rightfinger30;
/*     */   public ModelRenderer rightfinger31;
/*     */   public ModelRenderer rightfinger40;
/*     */   public ModelRenderer rightfinger41;
/*     */   public ModelRenderer rightfinger50;
/*     */   public ModelRenderer lefthand;
/*     */   public ModelRenderer leftfinger10;
/*     */   public ModelRenderer leftfinger11;
/*     */   public ModelRenderer leftfinger20;
/*     */   public ModelRenderer leftfinger21;
/*     */   public ModelRenderer leftfinger30;
/*     */   public ModelRenderer leftfinger31;
/*     */   public ModelRenderer leftfinger40;
/*     */   public ModelRenderer leftfinger41;
/*     */   public ModelRenderer leftfinger50;
/*     */   
/*     */   public BazookaModel() {
/*  40 */     this.field_78090_t = 64;
/*  41 */     this.field_78089_u = 32;
/*  42 */     this.rightfinger20 = new ModelRenderer((Model)this, 17, 6);
/*  43 */     this.rightfinger20.func_78793_a(-3.9F, 7.5F, -10.4F);
/*  44 */     this.rightfinger20.func_228301_a_(-1.0F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F);
/*  45 */     setRotateAngle(this.rightfinger20, -1.586163F, 0.17386198F, -0.0886057F);
/*  46 */     this.leftfinger21 = new ModelRenderer((Model)this, 17, 10);
/*  47 */     this.leftfinger21.func_78793_a(4.3F, 7.5F, -11.6F);
/*  48 */     this.leftfinger21.func_228301_a_(0.0F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F);
/*  49 */     setRotateAngle(this.leftfinger21, -1.5551676F, 0.66025555F, 0.11057187F);
/*  50 */     this.leftfinger20 = new ModelRenderer((Model)this, 17, 6);
/*  51 */     this.leftfinger20.func_78793_a(3.9F, 7.5F, -10.4F);
/*  52 */     this.leftfinger20.func_228301_a_(0.0F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F);
/*  53 */     setRotateAngle(this.leftfinger20, -1.586163F, -0.17386198F, 0.0886057F);
/*  54 */     this.leftfinger10 = new ModelRenderer((Model)this, 17, 6);
/*  55 */     this.leftfinger10.func_78793_a(3.9F, 8.5F, -10.4F);
/*  56 */     this.leftfinger10.func_228301_a_(0.0F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F);
/*  57 */     setRotateAngle(this.leftfinger10, -1.5859874F, -0.08593739F, 0.17518608F);
/*  58 */     this.leftfinger31 = new ModelRenderer((Model)this, 17, 10);
/*  59 */     this.leftfinger31.func_78793_a(4.3F, 6.5F, -11.6F);
/*  60 */     this.leftfinger31.func_228301_a_(0.0F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F);
/*  61 */     setRotateAngle(this.leftfinger31, -1.5709534F, 0.64393747F, -0.087335125F);
/*  62 */     this.rightfinger40 = new ModelRenderer((Model)this, 17, 6);
/*  63 */     this.rightfinger40.func_78793_a(-3.9F, 5.5F, -10.4F);
/*  64 */     this.rightfinger40.func_228301_a_(-1.0F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F);
/*  65 */     setRotateAngle(this.rightfinger40, -1.5481565F, 0.08428574F, 0.2627541F);
/*  66 */     this.rightfinger41 = new ModelRenderer((Model)this, 17, 10);
/*  67 */     this.rightfinger41.func_78793_a(-4.3F, 5.4F, -11.6F);
/*  68 */     this.rightfinger41.func_228301_a_(-1.0F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F);
/*  69 */     setRotateAngle(this.rightfinger41, -1.5916333F, -0.78724486F, 0.24858259F);
/*  70 */     this.leftfinger50 = new ModelRenderer((Model)this, 22, 6);
/*  71 */     this.leftfinger50.func_78793_a(0.5F, 5.0F, -9.8F);
/*  72 */     this.leftfinger50.func_228301_a_(0.0F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F);
/*  73 */     setRotateAngle(this.leftfinger50, -2.0943952F, 0.08726646F, 0.0F);
/*  74 */     this.rightfinger21 = new ModelRenderer((Model)this, 17, 10);
/*  75 */     this.rightfinger21.func_78793_a(-4.3F, 7.5F, -11.6F);
/*  76 */     this.rightfinger21.func_228301_a_(-1.0F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F);
/*  77 */     setRotateAngle(this.rightfinger21, -1.5551676F, -0.66025555F, -0.11057187F);
/*  78 */     this.rightfinger50 = new ModelRenderer((Model)this, 22, 6);
/*  79 */     this.rightfinger50.func_78793_a(-0.5F, 5.0F, -9.8F);
/*  80 */     this.rightfinger50.func_228301_a_(-1.0F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F);
/*  81 */     setRotateAngle(this.rightfinger50, -2.0943952F, -0.08726646F, 0.0F);
/*  82 */     this.lefthand = new ModelRenderer((Model)this, 17, 0);
/*  83 */     this.lefthand.func_78793_a(2.3F, 7.0F, -10.0F);
/*  84 */     this.lefthand.func_228301_a_(-2.0F, 0.0F, -2.0F, 4.0F, 1.0F, 4.0F, 0.0F);
/*  85 */     setRotateAngle(this.lefthand, -1.5707964F, 0.17453292F, 0.0F);
/*  86 */     this.rightfinger30 = new ModelRenderer((Model)this, 17, 6);
/*  87 */     this.rightfinger30.func_78793_a(-3.9F, 6.5F, -10.4F);
/*  88 */     this.rightfinger30.func_228301_a_(-1.0F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F);
/*  89 */     setRotateAngle(this.rightfinger30, -1.5554297F, 0.17386198F, 0.0886057F);
/*  90 */     this.leftfinger40 = new ModelRenderer((Model)this, 17, 6);
/*  91 */     this.leftfinger40.func_78793_a(3.9F, 5.5F, -10.4F);
/*  92 */     this.leftfinger40.func_228301_a_(0.0F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F);
/*  93 */     setRotateAngle(this.leftfinger40, -1.5481565F, -0.08428574F, -0.2627541F);
/*  94 */     this.rightarm = new ModelRenderer((Model)this, 0, 0);
/*  95 */     this.rightarm.func_78793_a(-2.3F, 7.0F, 0.0F);
/*  96 */     this.rightarm.func_228301_a_(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, 0.0F);
/*  97 */     setRotateAngle(this.rightarm, -1.5707964F, -0.0F, 0.0F);
/*  98 */     this.leftarm = new ModelRenderer((Model)this, 0, 0);
/*  99 */     this.leftarm.func_78793_a(2.3F, 7.0F, 0.0F);
/* 100 */     this.leftarm.func_228301_a_(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, 0.0F);
/* 101 */     setRotateAngle(this.leftarm, -1.5707964F, -0.0F, 0.0F);
/* 102 */     this.rightfinger31 = new ModelRenderer((Model)this, 17, 10);
/* 103 */     this.rightfinger31.func_78793_a(-4.3F, 6.5F, -11.6F);
/* 104 */     this.rightfinger31.func_228301_a_(-1.0F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F);
/* 105 */     setRotateAngle(this.rightfinger31, -1.5709534F, -0.64393747F, 0.087335125F);
/* 106 */     this.leftfinger30 = new ModelRenderer((Model)this, 17, 6);
/* 107 */     this.leftfinger30.func_78793_a(3.9F, 6.5F, -10.4F);
/* 108 */     this.leftfinger30.func_228301_a_(0.0F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F);
/* 109 */     setRotateAngle(this.leftfinger30, -1.5554297F, -0.17386198F, -0.0886057F);
/* 110 */     this.righthand = new ModelRenderer((Model)this, 17, 0);
/* 111 */     this.righthand.func_78793_a(-2.3F, 7.0F, -10.0F);
/* 112 */     this.righthand.func_228301_a_(-2.0F, 0.0F, -2.0F, 4.0F, 1.0F, 4.0F, 0.0F);
/* 113 */     setRotateAngle(this.righthand, -1.5707964F, -0.17453292F, 0.0F);
/* 114 */     this.rightfinger11 = new ModelRenderer((Model)this, 17, 10);
/* 115 */     this.rightfinger11.func_78793_a(-4.3F, 8.6F, -11.6F);
/* 116 */     this.rightfinger11.func_228301_a_(-1.0F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F);
/* 117 */     setRotateAngle(this.rightfinger11, -1.6060332F, -0.78160006F, -0.123102024F);
/* 118 */     this.rightfinger10 = new ModelRenderer((Model)this, 17, 6);
/* 119 */     this.rightfinger10.func_78793_a(-3.9F, 8.5F, -10.4F);
/* 120 */     this.rightfinger10.func_228301_a_(-1.0F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F);
/* 121 */     setRotateAngle(this.rightfinger10, -1.5859874F, 0.08593739F, -0.17518608F);
/* 122 */     this.leftfinger41 = new ModelRenderer((Model)this, 17, 10);
/* 123 */     this.leftfinger41.func_78793_a(4.3F, 5.4F, -11.6F);
/* 124 */     this.leftfinger41.func_228301_a_(0.0F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F);
/* 125 */     setRotateAngle(this.leftfinger41, -1.5916333F, 0.78724486F, -0.24858259F);
/* 126 */     this.leftfinger11 = new ModelRenderer((Model)this, 17, 10);
/* 127 */     this.leftfinger11.func_78793_a(4.3F, 8.6F, -11.6F);
/* 128 */     this.leftfinger11.func_228301_a_(0.0F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F);
/* 129 */     setRotateAngle(this.leftfinger11, -1.6060332F, 0.78160006F, 0.123102024F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225597_a_(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 141 */     matrixStack.func_227861_a_(0.0D, -0.6D, 0.0D);
/* 142 */     this.rightfinger20.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 143 */     this.leftfinger21.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 144 */     this.leftfinger20.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 145 */     this.leftfinger10.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 146 */     this.leftfinger31.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 147 */     this.rightfinger40.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 148 */     this.rightfinger41.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 149 */     this.leftfinger50.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 150 */     this.rightfinger21.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 151 */     this.rightfinger50.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 152 */     this.lefthand.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 153 */     this.rightfinger30.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 154 */     this.leftfinger40.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 155 */     this.rightarm.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 156 */     this.leftarm.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 157 */     this.rightfinger31.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 158 */     this.leftfinger30.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 159 */     this.righthand.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 160 */     this.rightfinger11.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 161 */     this.rightfinger10.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 162 */     this.leftfinger41.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 163 */     this.leftfinger11.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
/* 168 */     model.field_78795_f = x;
/* 169 */     model.field_78796_g = y;
/* 170 */     model.field_78808_h = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\entities\projectiles\BazookaModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */