/*     */ package xyz.pixelatedw.mineminenomi.models.entities.projectiles.effects;
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
/*     */ public class SpiralEffectModel
/*     */   extends EntityModel
/*     */ {
/*     */   private final ModelRenderer effect1;
/*     */   private final ModelRenderer effect1a;
/*     */   private final ModelRenderer effect1b;
/*     */   private final ModelRenderer effect1c;
/*     */   private final ModelRenderer effect1d;
/*     */   private final ModelRenderer effect2;
/*     */   private final ModelRenderer effect2a;
/*     */   private final ModelRenderer effect2b;
/*     */   private final ModelRenderer effect2c;
/*     */   private final ModelRenderer effect2d;
/*     */   private final ModelRenderer effect3;
/*     */   private final ModelRenderer effect3a;
/*     */   private final ModelRenderer effect3b;
/*     */   private final ModelRenderer effect3c;
/*     */   private final ModelRenderer effect3d;
/*     */   
/*     */   public SpiralEffectModel() {
/*  33 */     this.field_78090_t = 64;
/*  34 */     this.field_78089_u = 64;
/*  35 */     this.effect1 = new ModelRenderer((Model)this);
/*  36 */     this.effect1.func_78793_a(0.0F, -1.5F, 0.0F);
/*     */     
/*  38 */     this.effect1a = new ModelRenderer((Model)this);
/*  39 */     this.effect1a.func_78793_a(0.0F, 0.0F, 0.0F);
/*  40 */     this.effect1.func_78792_a(this.effect1a);
/*  41 */     setRotationAngle(this.effect1a, 0.4363F, 0.0F, 0.0F);
/*  42 */     this.effect1a.func_78784_a(0, 0).func_228303_a_(-4.0F, -2.4435F, 0.2658F, 8.0F, 0.0F, 9.0F, 0.0F, false);
/*     */     
/*  44 */     this.effect1b = new ModelRenderer((Model)this);
/*  45 */     this.effect1b.func_78793_a(0.0F, 0.0F, 0.0F);
/*  46 */     this.effect1.func_78792_a(this.effect1b);
/*  47 */     setRotationAngle(this.effect1b, -0.4363F, 0.0F, 0.0F);
/*  48 */     this.effect1b.func_78784_a(0, 0).func_228303_a_(-4.0F, 2.4435F, 0.2658F, 8.0F, 0.0F, 9.0F, 0.0F, false);
/*     */     
/*  50 */     this.effect1c = new ModelRenderer((Model)this);
/*  51 */     this.effect1c.func_78793_a(0.0F, 0.0F, 0.0F);
/*  52 */     this.effect1.func_78792_a(this.effect1c);
/*  53 */     setRotationAngle(this.effect1c, 0.0F, 0.4363F, 0.0F);
/*  54 */     this.effect1c.func_78784_a(9, 0).func_228303_a_(2.9435F, -4.5F, 0.2658F, 0.0F, 8.0F, 9.0F, 0.0F, false);
/*     */     
/*  56 */     this.effect1d = new ModelRenderer((Model)this);
/*  57 */     this.effect1d.func_78793_a(0.0F, 0.0F, 0.0F);
/*  58 */     this.effect1.func_78792_a(this.effect1d);
/*  59 */     setRotationAngle(this.effect1d, 0.0F, -0.4363F, 0.0F);
/*  60 */     this.effect1d.func_78784_a(9, 0).func_228303_a_(-2.9435F, -4.5F, 0.2658F, 0.0F, 8.0F, 9.0F, 0.0F, false);
/*     */     
/*  62 */     this.effect2 = new ModelRenderer((Model)this);
/*  63 */     this.effect2.func_78793_a(0.0F, -1.5F, 2.0F);
/*  64 */     setRotationAngle(this.effect2, 0.0F, 0.0F, 0.3491F);
/*     */     
/*  66 */     this.effect2a = new ModelRenderer((Model)this);
/*  67 */     this.effect2a.func_78793_a(0.0F, 0.0F, 0.0F);
/*  68 */     this.effect2.func_78792_a(this.effect2a);
/*  69 */     setRotationAngle(this.effect2a, 0.4363F, 0.0F, 0.0F);
/*  70 */     this.effect2a.func_78784_a(0, 0).func_228303_a_(-4.0F, -2.4435F, 0.2658F, 8.0F, 0.0F, 9.0F, 0.0F, false);
/*     */     
/*  72 */     this.effect2b = new ModelRenderer((Model)this);
/*  73 */     this.effect2b.func_78793_a(0.0F, 0.0F, 0.0F);
/*  74 */     this.effect2.func_78792_a(this.effect2b);
/*  75 */     setRotationAngle(this.effect2b, -0.4363F, 0.0F, 0.0F);
/*  76 */     this.effect2b.func_78784_a(0, 0).func_228303_a_(-4.0F, 2.4435F, 0.2658F, 8.0F, 0.0F, 9.0F, 0.0F, false);
/*     */     
/*  78 */     this.effect2c = new ModelRenderer((Model)this);
/*  79 */     this.effect2c.func_78793_a(0.0F, 0.0F, 0.0F);
/*  80 */     this.effect2.func_78792_a(this.effect2c);
/*  81 */     setRotationAngle(this.effect2c, 0.0F, 0.4363F, 0.0F);
/*  82 */     this.effect2c.func_78784_a(9, 0).func_228303_a_(2.9435F, -4.5F, 0.2658F, 0.0F, 8.0F, 9.0F, 0.0F, false);
/*     */     
/*  84 */     this.effect2d = new ModelRenderer((Model)this);
/*  85 */     this.effect2d.func_78793_a(0.0F, 0.0F, 0.0F);
/*  86 */     this.effect2.func_78792_a(this.effect2d);
/*  87 */     setRotationAngle(this.effect2d, 0.0F, -0.4363F, 0.0F);
/*  88 */     this.effect2d.func_78784_a(9, 0).func_228303_a_(-2.9435F, -4.5F, 0.2658F, 0.0F, 8.0F, 9.0F, 0.0F, false);
/*     */     
/*  90 */     this.effect3 = new ModelRenderer((Model)this);
/*  91 */     this.effect3.func_78793_a(0.0F, -1.5F, 5.0F);
/*  92 */     setRotationAngle(this.effect3, 0.0F, 0.0F, -0.3491F);
/*     */     
/*  94 */     this.effect3a = new ModelRenderer((Model)this);
/*  95 */     this.effect3a.func_78793_a(0.0F, 0.0F, 0.0F);
/*  96 */     this.effect3.func_78792_a(this.effect3a);
/*  97 */     setRotationAngle(this.effect3a, 0.4363F, 0.0F, 0.0F);
/*  98 */     this.effect3a.func_78784_a(0, 0).func_228303_a_(-4.0F, -2.4435F, 0.2658F, 8.0F, 0.0F, 9.0F, 0.0F, false);
/*     */     
/* 100 */     this.effect3b = new ModelRenderer((Model)this);
/* 101 */     this.effect3b.func_78793_a(0.0F, 0.0F, 0.0F);
/* 102 */     this.effect3.func_78792_a(this.effect3b);
/* 103 */     setRotationAngle(this.effect3b, -0.4363F, 0.0F, 0.0F);
/* 104 */     this.effect3b.func_78784_a(0, 0).func_228303_a_(-4.0F, 2.4435F, 0.2658F, 8.0F, 0.0F, 9.0F, 0.0F, false);
/*     */     
/* 106 */     this.effect3c = new ModelRenderer((Model)this);
/* 107 */     this.effect3c.func_78793_a(0.0F, 0.0F, 0.0F);
/* 108 */     this.effect3.func_78792_a(this.effect3c);
/* 109 */     setRotationAngle(this.effect3c, 0.0F, 0.4363F, 0.0F);
/* 110 */     this.effect3c.func_78784_a(9, 0).func_228303_a_(2.9435F, -4.5F, 0.2658F, 0.0F, 8.0F, 9.0F, 0.0F, false);
/*     */     
/* 112 */     this.effect3d = new ModelRenderer((Model)this);
/* 113 */     this.effect3d.func_78793_a(0.0F, 0.0F, 0.0F);
/* 114 */     this.effect3.func_78792_a(this.effect3d);
/* 115 */     setRotationAngle(this.effect3d, 0.0F, -0.4363F, 0.0F);
/* 116 */     this.effect3d.func_78784_a(9, 0).func_228303_a_(-2.9435F, -4.5F, 0.2658F, 0.0F, 8.0F, 9.0F, 0.0F, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225597_a_(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 122 */     this.effect1.field_78808_h = ageInTicks * 0.6F % 360.0F;
/* 123 */     this.effect2.field_78808_h = ageInTicks * -0.6F % 360.0F;
/* 124 */     this.effect3.field_78808_h = ageInTicks * 0.5F % 360.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 130 */     this.effect1.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 131 */     this.effect2.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 132 */     this.effect3.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 137 */     modelRenderer.field_78795_f = x;
/* 138 */     modelRenderer.field_78796_g = y;
/* 139 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\entities\projectiles\effects\SpiralEffectModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */