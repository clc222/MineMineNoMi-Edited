/*     */ package xyz.pixelatedw.mineminenomi.models.morphs;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.HandSide;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphModel;
/*     */ 
/*     */ public class MinkDogPartialModel<T extends LivingEntity>
/*     */   extends MorphModel<T> {
/*     */   private final ModelRenderer ears;
/*     */   private final ModelRenderer rightEar1;
/*     */   private final ModelRenderer rightEar2;
/*     */   private final ModelRenderer leftEar1;
/*     */   private final ModelRenderer leftEar2;
/*     */   private final ModelRenderer tailBase;
/*     */   private final ModelRenderer tail;
/*     */   private final ModelRenderer tail2;
/*     */   private final ModelRenderer mouth;
/*     */   
/*     */   public MinkDogPartialModel() {
/*  26 */     super(1.0F);
/*  27 */     this.field_78090_t = 32;
/*  28 */     this.field_78089_u = 32;
/*     */     
/*  30 */     this.ears = new ModelRenderer((Model)this);
/*  31 */     this.ears.func_78793_a(0.0F, 8.0F, 0.0F);
/*     */ 
/*     */     
/*  34 */     this.rightEar1 = new ModelRenderer((Model)this);
/*  35 */     this.rightEar1.func_78793_a(-2.5F, -7.5F, 0.0F);
/*  36 */     this.ears.func_78792_a(this.rightEar1);
/*  37 */     setRotationAngle(this.rightEar1, 0.0F, -0.1745F, -0.2618F);
/*  38 */     this.rightEar1.func_78784_a(0, 25).func_228303_a_(-1.1471F, -2.5F, -0.1504F, 3.0F, 3.0F, 1.0F, 0.0F, false);
/*     */     
/*  40 */     this.rightEar2 = new ModelRenderer((Model)this);
/*  41 */     this.rightEar2.func_78793_a(0.3214F, -2.2239F, -0.0325F);
/*  42 */     this.rightEar1.func_78792_a(this.rightEar2);
/*  43 */     setRotationAngle(this.rightEar2, 0.0F, 0.0F, 0.7854F);
/*  44 */     this.rightEar2.func_78784_a(0, 16).func_228303_a_(-1.1814F, -1.189F, -0.1179F, 2.0F, 2.0F, 1.0F, -0.001F, false);
/*     */     
/*  46 */     this.leftEar1 = new ModelRenderer((Model)this);
/*  47 */     this.leftEar1.func_78793_a(2.5F, -7.0F, 0.0F);
/*  48 */     this.ears.func_78792_a(this.leftEar1);
/*  49 */     setRotationAngle(this.leftEar1, 0.0F, 0.1745F, 0.2618F);
/*  50 */     this.leftEar1.func_78784_a(0, 25).func_228303_a_(-2.0F, -3.0F, 0.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);
/*     */     
/*  52 */     this.leftEar2 = new ModelRenderer((Model)this);
/*  53 */     this.leftEar2.func_78793_a(-0.5315F, -2.7239F, 0.1179F);
/*  54 */     this.leftEar1.func_78792_a(this.leftEar2);
/*  55 */     setRotationAngle(this.leftEar2, 0.0F, 0.0F, 0.7854F);
/*  56 */     this.leftEar2.func_78784_a(0, 16).func_228303_a_(-1.1814F, -1.189F, -0.1179F, 2.0F, 2.0F, 1.0F, -0.001F, false);
/*     */     
/*  58 */     this.tail = new ModelRenderer((Model)this);
/*  59 */     this.tail.func_78793_a(0.0F, 11.0F, 1.0F);
/*  60 */     setRotationAngle(this.tail, 0.3054F, 0.0F, 0.0F);
/*  61 */     this.tail.func_78784_a(0, 0).func_228303_a_(-1.0F, -0.2456F, 0.653F, 2.0F, 2.0F, 3.0F, 0.0F, false);
/*     */     
/*  63 */     this.tail2 = new ModelRenderer((Model)this);
/*  64 */     this.tail2.func_78793_a(0.5F, 1.3463F, 3.8713F);
/*  65 */     this.tail.func_78792_a(this.tail2);
/*  66 */     setRotationAngle(this.tail2, -0.3054F, 0.0F, 0.0F);
/*  67 */     this.tail2.func_78784_a(0, 6).func_228303_a_(-1.5F, -1.4919F, -0.6388F, 2.0F, 2.0F, 4.0F, -0.01F, false);
/*     */     
/*  69 */     this.tailBase = new ModelRenderer((Model)this);
/*  70 */     this.tailBase.func_78793_a(0.0F, 11.0F, 1.0F);
/*  71 */     this.tailBase.func_78792_a(this.tail);
/*  72 */     setRotationAngle(this.tailBase, 0.3054F, 0.0F, 0.0F);
/*     */     
/*  74 */     this.mouth = new ModelRenderer((Model)this);
/*  75 */     this.mouth.func_78793_a(0.0F, 6.0F, 1.0F);
/*  76 */     this.mouth.func_78784_a(16, 13).func_228303_a_(-1.5F, -3.0F, -8.0F, 3.0F, 3.0F, 5.0F, 0.0F, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/*  82 */     this.ears.func_217177_a(this.field_78116_c);
/*  83 */     this.mouth.func_217177_a(this.field_78116_c);
/*  84 */     this.ears.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/*  85 */     this.mouth.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/*  86 */     this.tailBase.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/*  92 */     super.func_225597_a_((LivingEntity)entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/*     */     
/*  94 */     this.tailBase.func_217177_a(this.field_78115_e);
/*     */     
/*  96 */     this.tail.field_78796_g = (float)(Math.sin(ageInTicks * 0.06D) / 6.0D);
/*  97 */     this.tail.field_78795_f = -this.field_78115_e.field_78795_f + (float)(Math.sin(ageInTicks * 0.02D) / 10.0D);
/*     */     
/*  99 */     if (entity.func_213453_ef()) {
/* 100 */       this.tail.field_78795_f = -0.2F;
/* 101 */       this.tail.field_78797_d = 9.5F;
/*     */     } 
/* 103 */     if (entity instanceof PlayerEntity && ((PlayerEntity)entity).field_71075_bZ.field_75100_b) {
/* 104 */       double posXDiff = Math.abs(((LivingEntity)entity).field_70169_q - entity.func_226277_ct_());
/* 105 */       double posZDiff = Math.abs(((LivingEntity)entity).field_70166_s - entity.func_226281_cx_());
/* 106 */       if (posXDiff >= 0.2D || posZDiff >= 0.2D) {
/* 107 */         this.tail.field_78795_f = -0.2F;
/*     */       }
/*     */     } else {
/*     */       
/* 111 */       this.tail.field_78797_d = 10.0F;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 117 */     modelRenderer.field_78795_f = x;
/* 118 */     modelRenderer.field_78796_g = y;
/* 119 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */   
/*     */   public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*     */   
/*     */   public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\morphs\MinkDogPartialModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */