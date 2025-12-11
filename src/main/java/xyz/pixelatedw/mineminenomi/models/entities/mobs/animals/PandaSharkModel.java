/*     */ package xyz.pixelatedw.mineminenomi.models.entities.mobs.animals;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.PandaSharkEntity;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PandaSharkModel
/*     */   extends EntityModel<PandaSharkEntity>
/*     */ {
/*     */   private final ModelRenderer body;
/*     */   private final ModelRenderer head;
/*     */   private final ModelRenderer mouth;
/*     */   private final ModelRenderer body2;
/*     */   private final ModelRenderer backFin;
/*     */   
/*     */   public PandaSharkModel() {
/*  25 */     this.field_78090_t = 128;
/*  26 */     this.field_78089_u = 128;
/*     */     
/*  28 */     this.body = new ModelRenderer((Model)this);
/*  29 */     this.body.func_78793_a(0.0F, 16.0F, -3.25F);
/*  30 */     this.body.func_78784_a(0, 26).func_228303_a_(-4.0F, -5.0F, -9.75F, 8.0F, 10.0F, 3.0F, 0.2F, false);
/*     */     
/*  32 */     this.head = new ModelRenderer((Model)this);
/*  33 */     this.head.func_78793_a(0.0F, 0.0F, 0.0F);
/*  34 */     this.body.func_78792_a(this.head);
/*  35 */     this.head.func_78784_a(0, 40).func_228303_a_(-4.0F, -5.0F, -14.75F, 8.0F, 10.0F, 6.0F, 0.0F, false);
/*     */     
/*  37 */     this.mouth = new ModelRenderer((Model)this);
/*  38 */     this.mouth.func_78793_a(0.0F, 1.0086F, -6.7946F);
/*  39 */     this.head.func_78792_a(this.mouth);
/*  40 */     setRotationAngle(this.mouth, 0.0873F, 0.0F, 0.0F);
/*  41 */     this.mouth.func_78784_a(0, 57).func_228303_a_(-3.5F, -3.5F, -12.0F, 7.0F, 7.0F, 6.0F, 0.0F, false);
/*     */     
/*  43 */     this.body2 = new ModelRenderer((Model)this);
/*  44 */     this.body2.func_78793_a(0.0F, -0.0614F, 1.9991F);
/*  45 */     this.body.func_78792_a(this.body2);
/*  46 */     this.body2.func_78784_a(0, 0).func_228303_a_(-4.5F, -5.4386F, -9.7491F, 9.0F, 11.0F, 14.0F, 0.0F, false);
/*     */     
/*  48 */     this.backFin = new ModelRenderer((Model)this);
/*  49 */     this.backFin.func_78793_a(0.0F, -3.1886F, -1.7491F);
/*  50 */     this.body2.func_78792_a(this.backFin);
/*  51 */     setRotationAngle(this.backFin, -0.48F, 0.0F, 0.0F);
/*  52 */     this.backFin.func_78784_a(50, 37).func_228303_a_(0.0F, -11.6077F, -5.1699F, 0.0F, 12.0F, 8.0F, 0.0F, false);
/*     */     
/*  54 */     this.leftFin = new ModelRenderer((Model)this);
/*  55 */     this.leftFin.func_78793_a(4.5F, 3.5614F, -3.7491F);
/*  56 */     this.body2.func_78792_a(this.leftFin);
/*  57 */     setRotationAngle(this.leftFin, 0.0F, 0.0F, 0.4363F);
/*  58 */     this.leftFin.func_78784_a(-10, 73).func_228303_a_(-1.0F, 0.0F, -5.0F, 14.0F, 0.0F, 10.0F, 0.0F, true);
/*     */     
/*  60 */     this.rightFin = new ModelRenderer((Model)this);
/*  61 */     this.rightFin.func_78793_a(-4.5F, 3.5614F, -3.7491F);
/*  62 */     this.body2.func_78792_a(this.rightFin);
/*  63 */     setRotationAngle(this.rightFin, 0.0F, 0.0F, -0.4363F);
/*  64 */     this.rightFin.func_78784_a(-10, 73).func_228303_a_(-13.0F, 0.0F, -5.0F, 14.0F, 0.0F, 10.0F, 0.0F, false);
/*     */     
/*  66 */     this.body3 = new ModelRenderer((Model)this);
/*  67 */     this.body3.func_78793_a(0.0F, 0.0614F, 4.0009F);
/*  68 */     this.body2.func_78792_a(this.body3);
/*  69 */     this.body3.func_78784_a(24, 26).func_228303_a_(-3.5F, -5.0F, -1.75F, 7.0F, 10.0F, 7.0F, 0.0F, false);
/*     */     
/*  71 */     this.body4 = new ModelRenderer((Model)this);
/*  72 */     this.body4.func_78793_a(0.0F, 0.0F, 5.25F);
/*  73 */     this.body3.func_78792_a(this.body4);
/*  74 */     this.body4.func_78784_a(29, 44).func_228303_a_(-2.5F, -4.5F, -1.0F, 5.0F, 9.0F, 5.0F, 0.0F, false);
/*     */     
/*  76 */     this.body5 = new ModelRenderer((Model)this);
/*  77 */     this.body5.func_78793_a(0.0F, 0.0F, 3.75F);
/*  78 */     this.body4.func_78792_a(this.body5);
/*  79 */     this.body5.func_78784_a(27, 59).func_228303_a_(-2.0F, -4.0F, -0.75F, 4.0F, 8.0F, 4.0F, 0.0F, false);
/*     */     
/*  81 */     this.tail = new ModelRenderer((Model)this);
/*  82 */     this.tail.func_78793_a(0.0F, 0.0F, 3.25F);
/*  83 */     this.body5.func_78792_a(this.tail);
/*  84 */     this.tail.func_78784_a(30, 63).func_228303_a_(0.0F, -11.0F, 0.0F, 0.0F, 22.0F, 9.0F, 0.0F, false);
/*     */   }
/*     */   private final ModelRenderer leftFin; private final ModelRenderer rightFin; private final ModelRenderer body3; private final ModelRenderer body4; private final ModelRenderer body5; private final ModelRenderer tail;
/*     */   
/*     */   public void setupAnim(PandaSharkEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/*  89 */     this.body.field_78795_f = headPitch * 0.017453292F;
/*  90 */     this.body.field_78796_g = netHeadYaw * 0.017453292F;
/*     */     
/*  92 */     this.tail.field_78796_g = 0.1F * MathHelper.func_76134_b(ageInTicks * 0.15F);
/*  93 */     this.rightFin.field_78796_g = 0.1F * MathHelper.func_76134_b(ageInTicks * 0.35F);
/*  94 */     this.rightFin.field_78808_h = 0.1F * MathHelper.func_76134_b(ageInTicks * 0.35F);
/*  95 */     this.leftFin.field_78796_g = 0.1F * -MathHelper.func_76134_b(ageInTicks * 0.35F);
/*  96 */     this.leftFin.field_78808_h = 0.1F * -MathHelper.func_76134_b(ageInTicks * 0.35F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 101 */     this.body.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */   }
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 105 */     modelRenderer.field_78795_f = x;
/* 106 */     modelRenderer.field_78796_g = y;
/* 107 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\entities\mobs\animals\PandaSharkModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */