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
/*     */ public class SharkModel
/*     */   extends EntityModel
/*     */ {
/*     */   public ModelRenderer head1;
/*     */   public ModelRenderer head2;
/*     */   public ModelRenderer head3;
/*     */   public ModelRenderer head4;
/*     */   public ModelRenderer teeth1;
/*     */   public ModelRenderer teeth2;
/*     */   public ModelRenderer body1;
/*     */   public ModelRenderer body2;
/*     */   public ModelRenderer body3;
/*     */   public ModelRenderer body4;
/*     */   public ModelRenderer body5;
/*     */   public ModelRenderer tail1;
/*     */   public ModelRenderer tail2;
/*     */   public ModelRenderer tail3;
/*     */   public ModelRenderer tail4;
/*     */   public ModelRenderer fin1;
/*     */   public ModelRenderer rightFin;
/*     */   public ModelRenderer leftFin;
/*     */   
/*     */   public SharkModel() {
/*  36 */     this.field_78090_t = 128;
/*  37 */     this.field_78089_u = 64;
/*  38 */     this.fin1 = new ModelRenderer((Model)this, 70, 0);
/*  39 */     this.fin1.func_78793_a(0.0F, -2.5F, -2.0F);
/*  40 */     this.fin1.func_228301_a_(-0.5F, -1.5F, 0.0F, 1.0F, 3.0F, 5.0F, 0.0F);
/*  41 */     setRotateAngle(this.fin1, 0.55850536F, -0.0F, 0.0F);
/*  42 */     this.tail3 = new ModelRenderer((Model)this, 0, 58);
/*  43 */     this.tail3.func_78793_a(0.0F, 1.5F, 13.0F);
/*  44 */     this.tail3.func_228301_a_(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, 0.0F);
/*  45 */     setRotateAngle(this.tail3, -2.0594885F, -0.0F, 0.0F);
/*  46 */     this.body4 = new ModelRenderer((Model)this, 0, 38);
/*  47 */     this.body4.func_78793_a(0.0F, 0.0F, 10.5F);
/*  48 */     this.body4.func_228301_a_(-2.5F, -2.5F, 0.0F, 5.0F, 5.0F, 3.0F, 0.0F);
/*  49 */     this.teeth1 = new ModelRenderer((Model)this, 37, 29);
/*  50 */     this.teeth1.func_78793_a(0.0F, 0.6F, -5.9F);
/*  51 */     this.teeth1.func_228301_a_(-2.5F, -0.5F, -6.0F, 5.0F, 1.0F, 6.0F, 0.0F);
/*  52 */     this.tail1 = new ModelRenderer((Model)this, 0, 58);
/*  53 */     this.tail1.func_78793_a(0.0F, -1.5F, 13.0F);
/*  54 */     this.tail1.func_228301_a_(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, 0.0F);
/*  55 */     setRotateAngle(this.tail1, -0.9773844F, -0.0F, 0.0F);
/*  56 */     this.tail4 = new ModelRenderer((Model)this, 7, 58);
/*  57 */     this.tail4.func_78793_a(0.0F, 3.8F, 14.8F);
/*  58 */     this.tail4.func_228301_a_(-0.5F, -3.0F, -1.5F, 1.0F, 3.0F, 1.0F, 0.0F);
/*  59 */     setRotateAngle(this.tail4, -0.7853982F, -0.0F, 0.0F);
/*  60 */     this.body2 = new ModelRenderer((Model)this, 0, 16);
/*  61 */     this.body2.func_78793_a(0.0F, 0.0F, 3.5F);
/*  62 */     this.body2.func_228301_a_(-3.5F, -3.5F, 0.0F, 7.0F, 7.0F, 4.0F, 0.0F);
/*  63 */     this.leftFin = new ModelRenderer((Model)this, 83, 5);
/*  64 */     this.leftFin.func_78793_a(3.5F, 2.0F, -2.0F);
/*  65 */     this.leftFin.func_228301_a_(0.0F, 0.0F, 0.0F, 5.0F, 1.0F, 3.0F, 0.0F);
/*  66 */     setRotateAngle(this.leftFin, 0.074610986F, -0.51484954F, 0.20085935F);
/*  67 */     this.rightFin = new ModelRenderer((Model)this, 83, 0);
/*  68 */     this.rightFin.func_78793_a(-3.5F, 2.0F, -2.0F);
/*  69 */     this.rightFin.func_228301_a_(-5.0F, 0.0F, 0.0F, 5.0F, 1.0F, 3.0F, 0.0F);
/*  70 */     setRotateAngle(this.rightFin, -0.27445486F, 0.51484954F, -0.20085935F);
/*  71 */     this.head3 = new ModelRenderer((Model)this, 37, 16);
/*  72 */     this.head3.func_78793_a(0.0F, 3.2F, -6.4F);
/*  73 */     this.head3.func_228301_a_(-2.5F, -1.0F, -5.0F, 5.0F, 1.0F, 5.0F, 0.0F);
/*  74 */     setRotateAngle(this.head3, 0.08726646F, -0.0F, 0.0F);
/*  75 */     this.head4 = new ModelRenderer((Model)this, 37, 23);
/*  76 */     this.head4.func_78793_a(0.0F, -0.9F, -6.0F);
/*  77 */     this.head4.func_228301_a_(-2.5F, -1.0F, -3.0F, 5.0F, 2.0F, 3.0F, 0.0F);
/*  78 */     this.head2 = new ModelRenderer((Model)this, 37, 8);
/*  79 */     this.head2.func_78793_a(0.0F, 0.1F, -6.0F);
/*  80 */     this.head2.func_228301_a_(-2.5F, -1.0F, -6.0F, 5.0F, 1.0F, 6.0F, 0.0F);
/*  81 */     this.tail2 = new ModelRenderer((Model)this, 7, 58);
/*  82 */     this.tail2.func_78793_a(0.0F, -3.6F, 14.0F);
/*  83 */     this.tail2.func_228301_a_(-0.5F, -3.1333334F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F);
/*  84 */     setRotateAngle(this.tail2, -2.3212879F, -0.0F, 0.0F);
/*  85 */     this.head1 = new ModelRenderer((Model)this, 37, 0);
/*  86 */     this.head1.func_78793_a(0.0F, -2.0F, -6.0F);
/*  87 */     this.head1.func_228301_a_(-2.5F, -1.0F, -6.0F, 5.0F, 1.0F, 6.0F, 0.0F);
/*  88 */     setRotateAngle(this.head1, 0.34906584F, -0.0F, 0.0F);
/*  89 */     this.teeth2 = new ModelRenderer((Model)this, 37, 37);
/*  90 */     this.teeth2.func_78793_a(0.0F, 3.2F, -6.2F);
/*  91 */     this.teeth2.func_228301_a_(-2.5F, -2.0F, -5.0F, 5.0F, 1.0F, 5.0F, 0.0F);
/*  92 */     setRotateAngle(this.teeth2, 0.15707964F, -0.0F, 0.0F);
/*  93 */     this.body5 = new ModelRenderer((Model)this, 0, 47);
/*  94 */     this.body5.func_78793_a(0.0F, 0.0F, -6.5F);
/*  95 */     this.body5.func_228301_a_(-3.5F, -3.5F, 0.0F, 7.0F, 7.0F, 3.0F, 0.0F);
/*  96 */     this.body3 = new ModelRenderer((Model)this, 0, 28);
/*  97 */     this.body3.func_78793_a(0.0F, 0.0F, 7.5F);
/*  98 */     this.body3.func_228301_a_(-3.0F, -3.0F, 0.0F, 6.0F, 6.0F, 3.0F, 0.0F);
/*  99 */     this.body1 = new ModelRenderer((Model)this, 0, 0);
/* 100 */     this.body1.func_78793_a(0.0F, 0.0F, 0.0F);
/* 101 */     this.body1.func_228301_a_(-4.0F, -4.0F, -3.5F, 8.0F, 8.0F, 7.0F, 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225597_a_(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 112 */     modelRenderer.field_78795_f = x;
/* 113 */     modelRenderer.field_78796_g = y;
/* 114 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
/* 120 */     this.fin1.func_228309_a_(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 121 */     this.tail3.func_228309_a_(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 122 */     this.body4.func_228309_a_(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 123 */     this.teeth1.func_228309_a_(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 124 */     this.tail1.func_228309_a_(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 125 */     this.tail4.func_228309_a_(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 126 */     this.body2.func_228309_a_(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 127 */     this.leftFin.func_228309_a_(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 128 */     this.rightFin.func_228309_a_(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 129 */     this.head3.func_228309_a_(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 130 */     this.head4.func_228309_a_(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 131 */     this.head2.func_228309_a_(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 132 */     this.tail2.func_228309_a_(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 133 */     this.head1.func_228309_a_(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 134 */     this.teeth2.func_228309_a_(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 135 */     this.body5.func_228309_a_(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 136 */     this.body3.func_228309_a_(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 137 */     this.body1.func_228309_a_(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\entities\projectiles\SharkModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */