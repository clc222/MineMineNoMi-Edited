/*     */ package xyz.pixelatedw.mineminenomi.models.armors;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ 
/*     */ public class FleetAdmiralHatModel<T extends LivingEntity>
/*     */   extends BipedModel<T> {
/*     */   private final ModelRenderer hat;
/*     */   private final ModelRenderer base;
/*     */   private final ModelRenderer tip;
/*     */   private final ModelRenderer bird;
/*     */   private final ModelRenderer birbTail;
/*     */   private final ModelRenderer birbTail1;
/*     */   private final ModelRenderer birbTail5;
/*     */   private final ModelRenderer birbTail2;
/*     */   private final ModelRenderer birbTail4;
/*     */   private final ModelRenderer birbTail3;
/*     */   private final ModelRenderer birbRightWing;
/*     */   private final ModelRenderer birbRightWing1;
/*     */   private final ModelRenderer birbRightWing2;
/*     */   private final ModelRenderer birbLeftWing;
/*     */   private final ModelRenderer birbLeftWing1;
/*     */   private final ModelRenderer birbLeftWing2;
/*     */   private final ModelRenderer birbHead;
/*     */   
/*     */   public FleetAdmiralHatModel() {
/*  32 */     super(1.0F);
/*  33 */     this.field_78090_t = 32;
/*  34 */     this.field_78089_u = 32;
/*     */     
/*  36 */     this.hat = new ModelRenderer((Model)this);
/*  37 */     this.hat.func_78793_a(0.0F, -1.5F, 0.0F);
/*     */     
/*  39 */     this.base = new ModelRenderer((Model)this);
/*  40 */     this.base.func_78793_a(0.0F, -4.5F, 0.0F);
/*  41 */     this.hat.func_78792_a(this.base);
/*  42 */     this.base.func_78784_a(0, 0).func_228303_a_(-4.0F, -4.0F, -4.0F, 8.0F, 4.0F, 8.0F, 0.502F, false);
/*     */     
/*  44 */     this.tip = new ModelRenderer((Model)this);
/*  45 */     this.tip.func_78793_a(0.0F, -0.6F, -5.0F);
/*  46 */     this.base.func_78792_a(this.tip);
/*  47 */     this.tip.func_78784_a(0, 12).func_228303_a_(-4.0F, -1.0F, -1.0F, 8.0F, 1.0F, 2.0F, 0.501F, false);
/*     */     
/*  49 */     this.bird = new ModelRenderer((Model)this);
/*  50 */     this.bird.func_78793_a(-1.0F, -9.0F, -0.75F);
/*  51 */     this.hat.func_78792_a(this.bird);
/*  52 */     this.bird.func_78784_a(0, 24).func_228303_a_(-0.5F, -1.0F, -0.5F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*  53 */     this.bird.func_78784_a(0, 27).func_228303_a_(-0.5F, -2.0F, 0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
/*  54 */     this.bird.func_78784_a(0, 24).func_228303_a_(1.5F, -1.0F, -0.5F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*  55 */     this.bird.func_78784_a(0, 27).func_228303_a_(1.5F, -2.0F, 0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
/*  56 */     this.bird.func_78784_a(0, 15).func_228303_a_(-0.5F, -4.0F, -1.5F, 3.0F, 2.0F, 4.0F, 0.0F, false);
/*     */     
/*  58 */     this.birbTail = new ModelRenderer((Model)this);
/*  59 */     this.birbTail.func_78793_a(0.0F, -2.0F, 3.0F);
/*  60 */     this.bird.func_78792_a(this.birbTail);
/*  61 */     setRotationAngle(this.birbTail, -0.1745F, 0.0F, 0.0F);
/*     */     
/*  63 */     this.birbTail1 = new ModelRenderer((Model)this);
/*  64 */     this.birbTail1.func_78793_a(0.25F, -0.75F, -0.75F);
/*  65 */     this.birbTail.func_78792_a(this.birbTail1);
/*  66 */     setRotationAngle(this.birbTail1, 0.0F, -0.7418F, 0.0F);
/*  67 */     this.birbTail1.func_78784_a(4, 26).func_228303_a_(-0.25F, -0.5F, -0.5F, 1.0F, 1.0F, 3.0F, 0.0F, false);
/*     */     
/*  69 */     this.birbTail5 = new ModelRenderer((Model)this);
/*  70 */     this.birbTail5.func_78793_a(1.25F, -0.75F, -0.5F);
/*  71 */     this.birbTail.func_78792_a(this.birbTail5);
/*  72 */     setRotationAngle(this.birbTail5, 0.0F, 0.7418F, 0.0F);
/*  73 */     this.birbTail5.func_78784_a(4, 26).func_228303_a_(-0.25F, -0.5F, -0.5F, 1.0F, 1.0F, 3.0F, 0.0F, false);
/*     */     
/*  75 */     this.birbTail2 = new ModelRenderer((Model)this);
/*  76 */     this.birbTail2.func_78793_a(0.75F, -0.75F, -0.5F);
/*  77 */     this.birbTail.func_78792_a(this.birbTail2);
/*  78 */     setRotationAngle(this.birbTail2, 0.0F, -0.3927F, 0.0F);
/*  79 */     this.birbTail2.func_78784_a(4, 26).func_228303_a_(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 3.0F, 0.01F, false);
/*     */     
/*  81 */     this.birbTail4 = new ModelRenderer((Model)this);
/*  82 */     this.birbTail4.func_78793_a(1.25F, -0.75F, -0.5F);
/*  83 */     this.birbTail.func_78792_a(this.birbTail4);
/*  84 */     setRotationAngle(this.birbTail4, 0.0F, 0.3927F, 0.0F);
/*  85 */     this.birbTail4.func_78784_a(4, 26).func_228303_a_(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 3.0F, 0.01F, false);
/*     */     
/*  87 */     this.birbTail3 = new ModelRenderer((Model)this);
/*  88 */     this.birbTail3.func_78793_a(1.25F, -0.75F, -0.5F);
/*  89 */     this.birbTail.func_78792_a(this.birbTail3);
/*  90 */     this.birbTail3.func_78784_a(4, 26).func_228303_a_(-0.75F, -0.5F, -0.5F, 1.0F, 1.0F, 3.0F, 0.02F, false);
/*     */     
/*  92 */     this.birbRightWing = new ModelRenderer((Model)this);
/*  93 */     this.birbRightWing.func_78793_a(-0.5F, -2.75F, -0.25F);
/*  94 */     this.bird.func_78792_a(this.birbRightWing);
/*     */     
/*  96 */     this.birbRightWing1 = new ModelRenderer((Model)this);
/*  97 */     this.birbRightWing1.func_78793_a(0.0F, -0.25F, 0.0F);
/*  98 */     this.birbRightWing.func_78792_a(this.birbRightWing1);
/*  99 */     setRotationAngle(this.birbRightWing1, 0.0F, 0.0F, 0.4363F);
/* 100 */     this.birbRightWing1.func_78784_a(10, 15).func_228303_a_(-3.0F, -0.75F, -0.5F, 4.0F, 1.0F, 2.0F, 0.05F, false);
/*     */     
/* 102 */     this.birbRightWing2 = new ModelRenderer((Model)this);
/* 103 */     this.birbRightWing2.func_78793_a(-2.0F, -1.25F, 0.0F);
/* 104 */     this.birbRightWing.func_78792_a(this.birbRightWing2);
/* 105 */     setRotationAngle(this.birbRightWing2, 0.0F, 0.0F, -0.48F);
/* 106 */     this.birbRightWing2.func_78784_a(0, 21).func_228303_a_(-2.9F, -1.0F, -0.5F, 3.0F, 1.0F, 2.0F, 0.04F, false);
/*     */     
/* 108 */     this.birbLeftWing = new ModelRenderer((Model)this);
/* 109 */     this.birbLeftWing.func_78793_a(2.5F, -2.75F, -0.25F);
/* 110 */     this.bird.func_78792_a(this.birbLeftWing);
/*     */     
/* 112 */     this.birbLeftWing1 = new ModelRenderer((Model)this);
/* 113 */     this.birbLeftWing1.func_78793_a(0.0F, -0.25F, 0.0F);
/* 114 */     this.birbLeftWing.func_78792_a(this.birbLeftWing1);
/* 115 */     setRotationAngle(this.birbLeftWing1, 0.0F, 0.0F, -0.4363F);
/* 116 */     this.birbLeftWing1.func_78784_a(10, 15).func_228303_a_(-1.0F, -0.75F, -0.5F, 4.0F, 1.0F, 2.0F, 0.05F, true);
/*     */     
/* 118 */     this.birbLeftWing2 = new ModelRenderer((Model)this);
/* 119 */     this.birbLeftWing2.func_78793_a(1.75F, -1.25F, 0.0F);
/* 120 */     this.birbLeftWing.func_78792_a(this.birbLeftWing2);
/* 121 */     setRotationAngle(this.birbLeftWing2, 0.0F, 0.0F, 0.48F);
/* 122 */     this.birbLeftWing2.func_78784_a(0, 21).func_228303_a_(0.15F, -1.15F, -0.5F, 3.0F, 1.0F, 2.0F, 0.04F, true);
/*     */     
/* 124 */     this.birbHead = new ModelRenderer((Model)this);
/* 125 */     this.birbHead.func_78793_a(2.0F, -3.0F, -2.0F);
/* 126 */     this.bird.func_78792_a(this.birbHead);
/* 127 */     this.birbHead.func_78784_a(0, 0).func_228303_a_(-2.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
/* 128 */     this.birbHead.func_78784_a(12, 21).func_228303_a_(-1.5F, -1.25F, -2.75F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 140 */     this.hat.func_217177_a(this.field_78116_c);
/* 141 */     this.hat.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 146 */     modelRenderer.field_78795_f = x;
/* 147 */     modelRenderer.field_78796_g = y;
/* 148 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\armors\FleetAdmiralHatModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */