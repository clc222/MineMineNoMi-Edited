/*     */ package xyz.pixelatedw.mineminenomi.models.entities.mobs.animals;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.FlyingFishEntity;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FlyingFishModel
/*     */   extends EntityModel<FlyingFishEntity>
/*     */ {
/*     */   private final ModelRenderer head;
/*     */   private final ModelRenderer body;
/*     */   private final ModelRenderer leftLowerFin;
/*     */   private final ModelRenderer rightLowerFin;
/*     */   private final ModelRenderer saddle;
/*     */   private final ModelRenderer leftFin;
/*     */   
/*     */   public FlyingFishModel() {
/*  25 */     this.field_78090_t = 256;
/*  26 */     this.field_78089_u = 256;
/*     */     
/*  28 */     this.head = new ModelRenderer((Model)this);
/*  29 */     this.head.func_78793_a(0.0F, 8.625F, -5.375F);
/*  30 */     this.head.func_78784_a(0, 90).func_228303_a_(-6.0F, -5.625F, -25.625F, 12.0F, 15.0F, 9.0F, 0.0F, false);
/*  31 */     this.head.func_78784_a(0, 62).func_228303_a_(-6.5F, -6.125F, -19.625F, 13.0F, 16.0F, 10.0F, 0.0F, false);
/*     */     
/*  33 */     this.body = new ModelRenderer((Model)this);
/*  34 */     this.body.func_78793_a(0.0F, 8.625F, -5.375F);
/*  35 */     this.body.func_78784_a(0, 22).func_228303_a_(-7.5F, -6.625F, -11.625F, 15.0F, 17.0F, 22.0F, 0.0F, false);
/*     */     
/*  37 */     this.leftLowerFin = new ModelRenderer((Model)this);
/*  38 */     this.leftLowerFin.func_78793_a(7.5F, 6.875F, 11.375F);
/*  39 */     this.body.func_78792_a(this.leftLowerFin);
/*  40 */     setRotationAngle(this.leftLowerFin, 0.0F, 0.0F, 0.3491F);
/*  41 */     this.leftLowerFin.func_78784_a(-16, 24).func_228303_a_(-0.0442F, 1.9208F, -20.0F, 10.0F, 0.0F, 16.0F, 0.0F, true);
/*     */     
/*  43 */     this.rightLowerFin = new ModelRenderer((Model)this);
/*  44 */     this.rightLowerFin.func_78793_a(-7.5F, 6.875F, 11.375F);
/*  45 */     this.body.func_78792_a(this.rightLowerFin);
/*  46 */     setRotationAngle(this.rightLowerFin, 0.0F, 0.0F, -0.3491F);
/*  47 */     this.rightLowerFin.func_78784_a(-16, 24).func_228303_a_(-9.9558F, 1.9208F, -20.0F, 10.0F, 0.0F, 16.0F, 0.0F, false);
/*     */     
/*  49 */     this.saddle = new ModelRenderer((Model)this);
/*  50 */     this.saddle.func_78793_a(0.0F, -0.125F, 11.375F);
/*  51 */     this.body.func_78792_a(this.saddle);
/*  52 */     this.saddle.func_78784_a(0, 158).func_228303_a_(-7.5F, -6.5F, -15.0F, 15.0F, 17.0F, 10.0F, 0.25F, false);
/*  53 */     this.saddle.func_78784_a(0, 132).func_228303_a_(-5.5F, -8.0F, -19.8125F, 11.0F, 2.0F, 22.0F, 0.0F, false);
/*  54 */     this.saddle.func_78784_a(46, 132).func_228303_a_(-5.5F, -16.0F, -9.8125F, 11.0F, 8.0F, 12.0F, 0.0F, false);
/*  55 */     this.saddle.func_78784_a(52, 158).func_228303_a_(-4.5F, -20.0F, -9.8125F, 9.0F, 4.0F, 12.0F, 0.0F, false);
/*     */     
/*  57 */     this.leftFin = new ModelRenderer((Model)this);
/*  58 */     this.leftFin.func_78793_a(-7.0F, 6.375F, 3.375F);
/*  59 */     this.body.func_78792_a(this.leftFin);
/*  60 */     this.leftFin.func_78784_a(-22, 0).func_228303_a_(-48.5F, 2.0F, -15.0F, 49.0F, 0.0F, 22.0F, 0.0F, true);
/*     */     
/*  62 */     this.rightFin = new ModelRenderer((Model)this);
/*  63 */     this.rightFin.func_78793_a(7.0F, 6.375F, 3.375F);
/*  64 */     this.body.func_78792_a(this.rightFin);
/*  65 */     this.rightFin.func_78784_a(-22, 0).func_228303_a_(-0.5F, 2.0F, -15.0F, 49.0F, 0.0F, 22.0F, 0.0F, false);
/*     */     
/*  67 */     this.tail = new ModelRenderer((Model)this);
/*  68 */     this.tail.func_78793_a(0.0F, -0.125F, 10.375F);
/*  69 */     this.body.func_78792_a(this.tail);
/*  70 */     this.tail.func_78784_a(79, 23).func_228303_a_(-7.0F, -6.0F, -1.0F, 14.0F, 16.0F, 10.0F, 0.0F, false);
/*  71 */     this.tail.func_78784_a(116, 98).func_228303_a_(0.0F, -18.0F, 3.0F, 0.0F, 24.0F, 6.0F, 0.0F, false);
/*     */     
/*  73 */     this.tail2 = new ModelRenderer((Model)this);
/*  74 */     this.tail2.func_78793_a(0.0F, -0.5F, 9.0F);
/*  75 */     this.tail.func_78792_a(this.tail2);
/*  76 */     this.tail2.func_78784_a(90, 50).func_228303_a_(-6.0F, -5.0F, -2.0F, 12.0F, 15.0F, 7.0F, 0.0F, false);
/*  77 */     this.tail2.func_78784_a(104, 102).func_228303_a_(0.0F, -15.0F, 0.0F, 0.0F, 21.0F, 5.0F, 0.0F, false);
/*     */     
/*  79 */     this.tail3 = new ModelRenderer((Model)this);
/*  80 */     this.tail3.func_78793_a(0.0F, 0.5F, 5.0F);
/*  81 */     this.tail2.func_78792_a(this.tail3);
/*  82 */     this.tail3.func_78784_a(94, 73).func_228303_a_(-5.5F, -5.0F, -2.0F, 11.0F, 14.0F, 6.0F, 0.0F, false);
/*  83 */     this.tail3.func_78784_a(92, 106).func_228303_a_(0.0F, -13.0F, 0.0F, 0.0F, 18.0F, 4.0F, 0.0F, false);
/*     */     
/*  85 */     this.tail4 = new ModelRenderer((Model)this);
/*  86 */     this.tail4.func_78793_a(0.0F, -0.25F, 4.5F);
/*  87 */     this.tail3.func_78792_a(this.tail4);
/*  88 */     this.tail4.func_78784_a(99, 0).func_228303_a_(-4.0F, -3.25F, -0.5F, 8.0F, 11.0F, 3.0F, 0.0F, false);
/*  89 */     this.tail4.func_78784_a(84, 111).func_228303_a_(0.0F, -10.25F, -0.5F, 0.0F, 14.0F, 3.0F, 0.0F, false);
/*     */     
/*  91 */     this.tail5 = new ModelRenderer((Model)this);
/*  92 */     this.tail5.func_78793_a(0.0F, 0.0F, 2.5F);
/*  93 */     this.tail4.func_78792_a(this.tail5);
/*  94 */     this.tail5.func_78784_a(42, 68).func_228303_a_(0.0F, -21.25F, 0.0F, 0.0F, 39.0F, 21.0F, 0.0F, false);
/*     */   }
/*     */   private final ModelRenderer rightFin; private final ModelRenderer tail; private final ModelRenderer tail2; private final ModelRenderer tail3; private final ModelRenderer tail4; private final ModelRenderer tail5;
/*     */   
/*     */   public void setupAnim(FlyingFishEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/*  99 */     this.rightFin.field_78808_h = MathHelper.func_76134_b(entity.field_70173_aa * 0.9F) * 0.2F;
/* 100 */     this.rightFin.field_78795_f = MathHelper.func_76134_b(entity.field_70173_aa * 0.5F) * 0.4F;
/*     */     
/* 102 */     this.leftFin.field_78808_h = -MathHelper.func_76134_b(entity.field_70173_aa * 0.9F) * 0.2F;
/* 103 */     this.leftFin.field_78795_f = MathHelper.func_76134_b(entity.field_70173_aa * 0.5F) * 0.4F;
/*     */     
/* 105 */     this.rightLowerFin.field_78795_f = MathHelper.func_76134_b(entity.field_70173_aa * 0.5F) * 0.2F;
/*     */     
/* 107 */     this.leftLowerFin.field_78795_f = MathHelper.func_76134_b(entity.field_70173_aa * 0.5F) * 0.2F;
/*     */     
/* 109 */     this.tail.field_78796_g = MathHelper.func_76134_b(entity.field_70173_aa * 0.3F) * 0.05F;
/* 110 */     this.tail2.field_78796_g = MathHelper.func_76134_b(entity.field_70173_aa * 0.3F) * 0.05F;
/* 111 */     this.tail3.field_78796_g = MathHelper.func_76134_b(entity.field_70173_aa * 0.3F) * 0.05F;
/* 112 */     this.tail4.field_78796_g = MathHelper.func_76134_b(entity.field_70173_aa * 0.3F) * 0.05F;
/* 113 */     this.tail5.field_78796_g = MathHelper.func_76134_b(entity.field_70173_aa * 0.3F) * 0.05F;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 118 */     this.head.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 119 */     this.body.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 120 */     this.saddle.field_78806_j = false;
/*     */   }
/*     */   
/*     */   public void renderSaddle() {
/* 124 */     this.saddle.field_78806_j = true;
/*     */   }
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 128 */     modelRenderer.field_78795_f = x;
/* 129 */     modelRenderer.field_78796_g = y;
/* 130 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\entities\mobs\animals\FlyingFishModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */