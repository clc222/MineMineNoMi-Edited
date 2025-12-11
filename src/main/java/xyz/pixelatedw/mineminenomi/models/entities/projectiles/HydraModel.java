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
/*     */ public class HydraModel<T extends Entity>
/*     */   extends EntityModel<T>
/*     */ {
/*     */   public ModelRenderer neck;
/*     */   public ModelRenderer spine;
/*     */   public ModelRenderer thorn1;
/*     */   public ModelRenderer thorn2;
/*     */   public ModelRenderer thorn3;
/*     */   public ModelRenderer thorn4;
/*     */   public ModelRenderer thorn5;
/*     */   public ModelRenderer thorn6;
/*     */   public ModelRenderer head1;
/*     */   public ModelRenderer head2;
/*     */   public ModelRenderer head3;
/*     */   public ModelRenderer head4;
/*     */   public ModelRenderer rightHorn1;
/*     */   public ModelRenderer rightHorn2;
/*     */   public ModelRenderer leftHorn1;
/*     */   public ModelRenderer leftHorn2;
/*     */   public ModelRenderer jaw1;
/*     */   public ModelRenderer jaw2;
/*     */   public ModelRenderer teeth1;
/*     */   public ModelRenderer teeth2;
/*     */   
/*     */   public HydraModel() {
/*  38 */     this.field_78090_t = 128;
/*  39 */     this.field_78089_u = 64;
/*  40 */     this.thorn2 = new ModelRenderer((Model)this, 7, 36);
/*  41 */     this.thorn2.func_78793_a(0.0F, -5.0F, 9.0F);
/*  42 */     this.thorn2.func_228301_a_(-1.0F, 0.0F, -0.5F, 2.0F, 4.0F, 2.0F, 0.0F);
/*  43 */     setRotateAngle(this.thorn2, 2.268928F, -0.0F, 0.0F);
/*  44 */     this.teeth2 = new ModelRenderer((Model)this, 63, 40);
/*  45 */     this.teeth2.func_78793_a(0.0F, -1.0F, -10.0F);
/*  46 */     this.teeth2.func_228301_a_(-4.0F, 0.0F, -10.3F, 8.0F, 2.0F, 10.0F, 0.0F);
/*  47 */     setRotateAngle(this.teeth2, 0.2268928F, -0.0F, 0.0F);
/*  48 */     this.thorn5 = new ModelRenderer((Model)this, 7, 43);
/*  49 */     this.thorn5.func_78793_a(0.0F, -5.0F, 22.0F);
/*  50 */     this.thorn5.func_228301_a_(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F);
/*  51 */     setRotateAngle(this.thorn5, 2.268928F, -0.0F, 0.0F);
/*  52 */     this.head3 = new ModelRenderer((Model)this, 0, 21);
/*  53 */     this.head3.func_78793_a(0.0F, -6.3F, -11.7F);
/*  54 */     this.head3.func_228301_a_(0.0F, -0.5F, -0.5F, 4.0F, 1.0F, 1.0F, 0.0F);
/*  55 */     setRotateAngle(this.head3, -0.044734888F, -0.086933546F, -0.08759811F);
/*  56 */     this.thorn1 = new ModelRenderer((Model)this, 7, 27);
/*  57 */     this.thorn1.func_78793_a(0.0F, -5.0F, 3.0F);
/*  58 */     this.thorn1.func_228301_a_(-1.0F, 0.0F, -0.5F, 2.0F, 6.0F, 2.0F, 0.0F);
/*  59 */     setRotateAngle(this.thorn1, 2.268928F, -0.0F, 0.0F);
/*  60 */     this.head2 = new ModelRenderer((Model)this, 0, 21);
/*  61 */     this.head2.func_78793_a(0.0F, -6.3F, -11.7F);
/*  62 */     this.head2.func_228301_a_(-4.0F, -0.5F, -0.5F, 4.0F, 1.0F, 1.0F, 0.0F);
/*  63 */     setRotateAngle(this.head2, -0.044734888F, 0.086933546F, 0.08759811F);
/*  64 */     this.thorn3 = new ModelRenderer((Model)this, 7, 43);
/*  65 */     this.thorn3.func_78793_a(0.0F, -5.0F, 13.0F);
/*  66 */     this.thorn3.func_228301_a_(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F);
/*  67 */     setRotateAngle(this.thorn3, 2.268928F, -0.0F, 0.0F);
/*  68 */     this.leftHorn1 = new ModelRenderer((Model)this, 93, 0);
/*  69 */     this.leftHorn1.func_78793_a(2.6F, -5.6F, -3.0F);
/*  70 */     this.leftHorn1.func_228301_a_(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 6.0F, 0.0F);
/*  71 */     setRotateAngle(this.leftHorn1, 0.70455766F, 0.12200478F, 0.052752364F);
/*  72 */     this.rightHorn1 = new ModelRenderer((Model)this, 74, 0);
/*  73 */     this.rightHorn1.field_78809_i = true;
/*  74 */     this.rightHorn1.func_78793_a(-2.6F, -5.6F, -3.0F);
/*  75 */     this.rightHorn1.func_228301_a_(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 6.0F, 0.0F);
/*  76 */     setRotateAngle(this.rightHorn1, 0.70455766F, -0.12200478F, -0.052752364F);
/*  77 */     this.rightHorn2 = new ModelRenderer((Model)this, 74, 10);
/*  78 */     this.rightHorn2.func_78793_a(-3.5F, -9.0F, 0.8F);
/*  79 */     this.rightHorn2.func_228301_a_(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 6.0F, 0.0F);
/*  80 */     setRotateAngle(this.rightHorn2, 0.99234694F, -0.12125788F, -0.12308134F);
/*  81 */     this.leftHorn2 = new ModelRenderer((Model)this, 93, 10);
/*  82 */     this.leftHorn2.func_78793_a(3.5F, -9.0F, 0.8F);
/*  83 */     this.leftHorn2.func_228301_a_(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 6.0F, 0.0F);
/*  84 */     setRotateAngle(this.leftHorn2, 0.99234694F, 0.12125788F, 0.12308134F);
/*  85 */     this.teeth1 = new ModelRenderer((Model)this, 63, 27);
/*  86 */     this.teeth1.func_78793_a(0.0F, -2.0F, -9.9F);
/*  87 */     this.teeth1.func_228301_a_(-4.0F, 0.0F, -10.0F, 8.0F, 2.0F, 10.0F, 0.0F);
/*  88 */     this.jaw1 = new ModelRenderer((Model)this, 37, 0);
/*  89 */     this.jaw1.func_78793_a(0.0F, -4.0F, -10.0F);
/*  90 */     this.jaw1.func_228301_a_(-4.0F, 0.0F, -10.0F, 8.0F, 2.0F, 10.0F, 0.0F);
/*  91 */     this.neck = new ModelRenderer((Model)this, 0, 27);
/*  92 */     this.neck.func_78793_a(0.0F, -1.5F, 29.0F);
/*  93 */     this.neck.func_228301_a_(-3.5F, -4.0F, -29.0F, 7.0F, 8.0F, 29.0F, 0.0F);
/*  94 */     this.head1 = new ModelRenderer((Model)this, 0, 0);
/*  95 */     this.head1.func_78793_a(0.0F, -2.0F, 0.0F);
/*  96 */     this.head1.func_228301_a_(-4.0F, -5.0F, -10.0F, 8.0F, 10.0F, 10.0F, 0.0F);
/*  97 */     this.head4 = new ModelRenderer((Model)this, 11, 21);
/*  98 */     this.head4.func_78793_a(0.0F, -5.5F, -9.8F);
/*  99 */     this.head4.func_228301_a_(-4.0F, -1.5F, -2.0F, 8.0F, 3.0F, 2.0F, 0.0F);
/* 100 */     this.thorn4 = new ModelRenderer((Model)this, 7, 36);
/* 101 */     this.thorn4.func_78793_a(0.0F, -5.0F, 18.0F);
/* 102 */     this.thorn4.func_228301_a_(-1.0F, 0.0F, -0.5F, 2.0F, 4.0F, 2.0F, 0.0F);
/* 103 */     setRotateAngle(this.thorn4, 2.268928F, -0.0F, 0.0F);
/* 104 */     this.jaw2 = new ModelRenderer((Model)this, 37, 14);
/* 105 */     this.jaw2.func_78793_a(0.0F, 1.0F, -10.0F);
/* 106 */     this.jaw2.func_228301_a_(-4.0F, 0.0F, -10.0F, 8.0F, 2.0F, 10.0F, 0.0F);
/* 107 */     setRotateAngle(this.jaw2, 0.2268928F, -0.0F, 0.0F);
/* 108 */     this.spine = new ModelRenderer((Model)this, 0, 24);
/* 109 */     this.spine.func_78793_a(0.0F, -6.0F, 0.0F);
/* 110 */     this.spine.func_228301_a_(-1.0F, 0.0F, -0.5F, 2.0F, 29.0F, 1.0F, 0.0F);
/* 111 */     setRotateAngle(this.spine, 1.5707964F, -0.0F, 0.0F);
/* 112 */     this.thorn6 = new ModelRenderer((Model)this, 7, 43);
/* 113 */     this.thorn6.func_78793_a(0.0F, -5.0F, 26.0F);
/* 114 */     this.thorn6.func_228301_a_(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F);
/* 115 */     setRotateAngle(this.thorn6, 2.268928F, -0.0F, 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
/* 121 */     this.thorn2.func_228309_a_(matrixStack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 122 */     this.teeth2.func_228309_a_(matrixStack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 123 */     this.thorn5.func_228309_a_(matrixStack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 124 */     this.head3.func_228309_a_(matrixStack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 125 */     this.thorn1.func_228309_a_(matrixStack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 126 */     this.head2.func_228309_a_(matrixStack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 127 */     this.thorn3.func_228309_a_(matrixStack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 128 */     this.leftHorn1.func_228309_a_(matrixStack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 129 */     this.rightHorn1.func_228309_a_(matrixStack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 130 */     this.rightHorn2.func_228309_a_(matrixStack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 131 */     this.leftHorn2.func_228309_a_(matrixStack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 132 */     this.teeth1.func_228309_a_(matrixStack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 133 */     this.jaw1.func_228309_a_(matrixStack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 134 */     this.neck.func_228309_a_(matrixStack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 135 */     this.head1.func_228309_a_(matrixStack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 136 */     this.head4.func_228309_a_(matrixStack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 137 */     this.thorn4.func_228309_a_(matrixStack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 138 */     this.jaw2.func_228309_a_(matrixStack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 139 */     this.spine.func_228309_a_(matrixStack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 140 */     this.thorn6.func_228309_a_(matrixStack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225597_a_(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
/* 150 */     model.field_78795_f = x;
/* 151 */     model.field_78796_g = y;
/* 152 */     model.field_78808_h = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\entities\projectiles\HydraModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */