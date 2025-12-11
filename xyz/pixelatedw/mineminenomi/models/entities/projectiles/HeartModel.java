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
/*     */ public class HeartModel extends EntityModel {
/*     */   private final ModelRenderer pellicle;
/*     */   private final ModelRenderer heart4;
/*     */   private final ModelRenderer heart15;
/*     */   private final ModelRenderer heart9;
/*     */   private final ModelRenderer heart11;
/*     */   private final ModelRenderer heart16;
/*     */   private final ModelRenderer heart2;
/*     */   private final ModelRenderer heart6;
/*     */   private final ModelRenderer heart12;
/*     */   private final ModelRenderer heart7;
/*     */   private final ModelRenderer heart5;
/*     */   private final ModelRenderer heart13;
/*     */   private final ModelRenderer heart18;
/*     */   private final ModelRenderer heart8;
/*     */   private final ModelRenderer heart14;
/*     */   private final ModelRenderer heart1;
/*     */   private final ModelRenderer heart17;
/*     */   private final ModelRenderer heart3;
/*     */   private final ModelRenderer heart10;
/*     */   
/*     */   public HeartModel() {
/*  35 */     this.field_78090_t = 64;
/*  36 */     this.field_78089_u = 32;
/*     */     
/*  38 */     this.pellicle = new ModelRenderer((Model)this);
/*  39 */     this.pellicle.func_78793_a(0.0F, -10.0F, 0.0F);
/*  40 */     this.pellicle.func_78784_a(15, 0).func_228303_a_(-9.5F, -9.0F, 0.0F, 19.0F, 21.0F, 0.0F, 0.0F, false);
/*     */     
/*  42 */     this.heart4 = new ModelRenderer((Model)this);
/*  43 */     this.heart4.func_78793_a(4.7045F, 6.7034F, 0.0F);
/*  44 */     this.pellicle.func_78792_a(this.heart4);
/*  45 */     setRotationAngle(this.heart4, 0.0F, 0.0F, -2.4958F);
/*  46 */     this.heart4.func_78784_a(0, 0).func_228303_a_(-0.5F, -2.5F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
/*     */     
/*  48 */     this.heart15 = new ModelRenderer((Model)this);
/*  49 */     this.heart15.func_78793_a(-3.1279F, -7.2051F, 0.0F);
/*  50 */     this.pellicle.func_78792_a(this.heart15);
/*  51 */     setRotationAngle(this.heart15, 0.0F, 0.0F, -1.0123F);
/*  52 */     this.heart15.func_78784_a(10, 0).func_228303_a_(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);
/*     */     
/*  54 */     this.heart9 = new ModelRenderer((Model)this);
/*  55 */     this.heart9.func_78793_a(-9.3162F, -4.8406F, 0.0F);
/*  56 */     this.pellicle.func_78792_a(this.heart9);
/*  57 */     setRotationAngle(this.heart9, 0.0F, 0.0F, -2.8972F);
/*  58 */     this.heart9.func_78784_a(5, 0).func_228303_a_(-0.5F, -2.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);
/*     */     
/*  60 */     this.heart11 = new ModelRenderer((Model)this);
/*  61 */     this.heart11.func_78793_a(-7.803F, -7.523F, 0.0F);
/*  62 */     this.pellicle.func_78792_a(this.heart11);
/*  63 */     setRotationAngle(this.heart11, 0.0F, 0.0F, -2.3213F);
/*  64 */     this.heart11.func_78784_a(10, 0).func_228303_a_(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);
/*     */     
/*  66 */     this.heart16 = new ModelRenderer((Model)this);
/*  67 */     this.heart16.func_78793_a(3.1279F, -7.2051F, 0.0F);
/*  68 */     this.pellicle.func_78792_a(this.heart16);
/*  69 */     setRotationAngle(this.heart16, 0.0F, 0.0F, 1.0123F);
/*  70 */     this.heart16.func_78784_a(10, 0).func_228303_a_(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);
/*     */     
/*  72 */     this.heart2 = new ModelRenderer((Model)this);
/*  73 */     this.heart2.func_78793_a(1.4983F, 10.2634F, 0.0F);
/*  74 */     this.pellicle.func_78792_a(this.heart2);
/*  75 */     setRotationAngle(this.heart2, 0.0F, 0.0F, -2.3387F);
/*  76 */     this.heart2.func_78784_a(0, 0).func_228303_a_(-0.5F, -2.5F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
/*     */     
/*  78 */     this.heart6 = new ModelRenderer((Model)this);
/*  79 */     this.heart6.func_78793_a(6.2F, 4.9F, 0.0F);
/*  80 */     this.pellicle.func_78792_a(this.heart6);
/*  81 */     setRotationAngle(this.heart6, 0.0F, 0.0F, -2.6529F);
/*  82 */     this.heart6.func_78784_a(0, 0).func_228303_a_(-0.5F, 0.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
/*     */     
/*  84 */     this.heart12 = new ModelRenderer((Model)this);
/*  85 */     this.heart12.func_78793_a(7.803F, -7.523F, 0.0F);
/*  86 */     this.pellicle.func_78792_a(this.heart12);
/*  87 */     setRotationAngle(this.heart12, 0.0F, 0.0F, 2.3213F);
/*  88 */     this.heart12.func_78784_a(10, 0).func_228303_a_(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);
/*     */     
/*  90 */     this.heart7 = new ModelRenderer((Model)this);
/*  91 */     this.heart7.func_78793_a(-9.1513F, -1.2225F, 0.0F);
/*  92 */     this.pellicle.func_78792_a(this.heart7);
/*  93 */     setRotationAngle(this.heart7, 0.0F, 0.0F, 2.8623F);
/*  94 */     this.heart7.func_78784_a(0, 0).func_228303_a_(-0.5F, -2.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);
/*     */     
/*  96 */     this.heart5 = new ModelRenderer((Model)this);
/*  97 */     this.heart5.func_78793_a(-7.3737F, 2.6926F, 0.0F);
/*  98 */     this.pellicle.func_78792_a(this.heart5);
/*  99 */     setRotationAngle(this.heart5, 0.0F, 0.0F, 2.6529F);
/* 100 */     this.heart5.func_78784_a(0, 0).func_228303_a_(-0.5F, -2.5F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
/*     */     
/* 102 */     this.heart13 = new ModelRenderer((Model)this);
/* 103 */     this.heart13.func_78793_a(-5.6185F, -8.1653F, 0.0F);
/* 104 */     this.pellicle.func_78792_a(this.heart13);
/* 105 */     setRotationAngle(this.heart13, 0.0F, 0.0F, -1.4137F);
/* 106 */     this.heart13.func_78784_a(10, 0).func_228303_a_(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, true);
/*     */     
/* 108 */     this.heart18 = new ModelRenderer((Model)this);
/* 109 */     this.heart18.func_78793_a(0.8509F, -5.5358F, 0.0F);
/* 110 */     this.pellicle.func_78792_a(this.heart18);
/* 111 */     setRotationAngle(this.heart18, 0.0F, 0.0F, 0.8727F);
/* 112 */     this.heart18.func_78784_a(10, 0).func_228303_a_(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);
/*     */     
/* 114 */     this.heart8 = new ModelRenderer((Model)this);
/* 115 */     this.heart8.func_78793_a(9.1513F, -1.2225F, 0.0F);
/* 116 */     this.pellicle.func_78792_a(this.heart8);
/* 117 */     setRotationAngle(this.heart8, 0.0F, 0.0F, -2.8623F);
/* 118 */     this.heart8.func_78784_a(0, 0).func_228303_a_(-0.5F, -2.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);
/*     */     
/* 120 */     this.heart14 = new ModelRenderer((Model)this);
/* 121 */     this.heart14.func_78793_a(5.6185F, -8.1653F, 0.0F);
/* 122 */     this.pellicle.func_78792_a(this.heart14);
/* 123 */     setRotationAngle(this.heart14, 0.0F, 0.0F, 1.4137F);
/* 124 */     this.heart14.func_78784_a(10, 0).func_228303_a_(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);
/*     */     
/* 126 */     this.heart1 = new ModelRenderer((Model)this);
/* 127 */     this.heart1.func_78793_a(-1.4983F, 10.2634F, 0.0F);
/* 128 */     this.pellicle.func_78792_a(this.heart1);
/* 129 */     setRotationAngle(this.heart1, 0.0F, 0.0F, 2.3387F);
/* 130 */     this.heart1.func_78784_a(0, 0).func_228303_a_(-0.5F, -2.5F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, true);
/*     */     
/* 132 */     this.heart17 = new ModelRenderer((Model)this);
/* 133 */     this.heart17.func_78793_a(-0.8509F, -5.5358F, 0.0F);
/* 134 */     this.pellicle.func_78792_a(this.heart17);
/* 135 */     setRotationAngle(this.heart17, 0.0F, 0.0F, -0.8727F);
/* 136 */     this.heart17.func_78784_a(10, 0).func_228303_a_(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);
/*     */     
/* 138 */     this.heart3 = new ModelRenderer((Model)this);
/* 139 */     this.heart3.func_78793_a(-4.7045F, 6.7034F, 0.0F);
/* 140 */     this.pellicle.func_78792_a(this.heart3);
/* 141 */     setRotationAngle(this.heart3, 0.0F, 0.0F, 2.4958F);
/* 142 */     this.heart3.func_78784_a(0, 0).func_228303_a_(-0.5F, -2.5F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
/*     */     
/* 144 */     this.heart10 = new ModelRenderer((Model)this);
/* 145 */     this.heart10.func_78793_a(9.3162F, -4.8406F, 0.0F);
/* 146 */     this.pellicle.func_78792_a(this.heart10);
/* 147 */     setRotationAngle(this.heart10, 0.0F, 0.0F, 2.8972F);
/* 148 */     this.heart10.func_78784_a(5, 0).func_228303_a_(-0.5F, -2.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
/* 154 */     matrixStack.func_227860_a_();
/* 155 */     matrixStack.func_227862_a_(1.0F, 1.0F, 1.1F);
/* 156 */     this.pellicle.func_228309_a_(matrixStack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 157 */     matrixStack.func_227865_b_();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225597_a_(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer model, float x, float y, float z) {
/* 166 */     model.field_78795_f = x;
/* 167 */     model.field_78796_g = y;
/* 168 */     model.field_78808_h = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\entities\projectiles\HeartModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */