/*     */ package xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class WeatherWizardModel
/*     */   extends BipedModel
/*     */ {
/*     */   public ModelRenderer field_178723_h;
/*     */   public ModelRenderer field_178721_j;
/*     */   public ModelRenderer field_78116_c;
/*     */   public ModelRenderer field_78115_e;
/*     */   public ModelRenderer field_178724_i;
/*     */   public ModelRenderer field_178722_k;
/*     */   public ModelRenderer hat1;
/*     */   public ModelRenderer beard1;
/*     */   public ModelRenderer hat2;
/*     */   public ModelRenderer hat3;
/*     */   public ModelRenderer hat4;
/*     */   public ModelRenderer hat5;
/*     */   public ModelRenderer beard2;
/*     */   public ModelRenderer rightFancyBeard1;
/*     */   public ModelRenderer leftFancyBeard1;
/*     */   
/*     */   public WeatherWizardModel() {
/*  32 */     super(1.0F);
/*  33 */     this.field_78090_t = 64;
/*  34 */     this.field_78089_u = 64;
/*  35 */     this.hat5 = new ModelRenderer((Model)this, 0, 39);
/*  36 */     this.hat5.func_78793_a(0.0F, 0.0F, 0.0F);
/*  37 */     this.hat5.func_228301_a_(-2.5F, -9.0F, -2.5F, 5.0F, 2.0F, 5.0F, 0.0F);
/*  38 */     this.leftFancyBeard1 = new ModelRenderer((Model)this, 35, 0);
/*  39 */     this.leftFancyBeard1.func_78793_a(0.9F, 1.0F, 0.0F);
/*  40 */     this.leftFancyBeard1.func_228301_a_(0.0F, -2.0F, 0.0F, 4.0F, 2.0F, 1.0F, 0.0F);
/*  41 */     setRotateAngle(this.leftFancyBeard1, 0.0F, 0.0F, 1.0471976F);
/*  42 */     this.beard2 = new ModelRenderer((Model)this, 35, 0);
/*  43 */     this.beard2.func_78793_a(0.0F, 0.0F, 0.0F);
/*  44 */     this.beard2.func_228301_a_(-2.0F, 7.0F, 0.0F, 4.0F, 2.0F, 1.0F, 0.0F);
/*  45 */     this.field_78116_c = new ModelRenderer((Model)this, 0, 0);
/*  46 */     this.field_78116_c.func_78793_a(0.0F, 0.0F, 0.0F);
/*  47 */     this.field_78116_c.func_228301_a_(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F);
/*  48 */     this.beard1 = new ModelRenderer((Model)this, 35, 0);
/*  49 */     this.beard1.func_78793_a(0.0F, -2.0F, -5.0F);
/*  50 */     this.beard1.func_228301_a_(-2.5F, 0.0F, 0.0F, 5.0F, 7.0F, 1.0F, 0.0F);
/*  51 */     this.field_178723_h = new ModelRenderer((Model)this, 40, 16);
/*  52 */     this.field_178723_h.func_78793_a(-5.0F, 2.0F, 0.0F);
/*  53 */     this.field_178723_h.func_228301_a_(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F);
/*  54 */     this.rightFancyBeard1 = new ModelRenderer((Model)this, 35, 0);
/*  55 */     this.rightFancyBeard1.func_78793_a(-0.9F, 1.0F, 0.0F);
/*  56 */     this.rightFancyBeard1.func_228301_a_(-4.0F, -2.0F, 0.0F, 4.0F, 2.0F, 1.0F, 0.0F);
/*  57 */     setRotateAngle(this.rightFancyBeard1, 0.0F, 0.0F, -1.0471976F);
/*  58 */     this.field_78115_e = new ModelRenderer((Model)this, 16, 16);
/*  59 */     this.field_78115_e.func_78793_a(0.0F, 0.0F, 0.0F);
/*  60 */     this.field_78115_e.func_228301_a_(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F);
/*  61 */     this.hat1 = new ModelRenderer((Model)this, 0, 35);
/*  62 */     this.hat1.func_78793_a(0.0F, -8.5F, 0.0F);
/*  63 */     this.hat1.func_228301_a_(-4.5F, 0.0F, -4.5F, 9.0F, 2.0F, 9.0F, 0.0F);
/*  64 */     this.hat2 = new ModelRenderer((Model)this, 0, 36);
/*  65 */     this.hat2.func_78793_a(0.0F, 0.0F, 0.0F);
/*  66 */     this.hat2.func_228301_a_(-4.0F, -2.0F, -4.0F, 8.0F, 2.0F, 8.0F, 0.0F);
/*  67 */     this.hat4 = new ModelRenderer((Model)this, 0, 38);
/*  68 */     this.hat4.func_78793_a(0.0F, 0.0F, 0.0F);
/*  69 */     this.hat4.func_228301_a_(-3.0F, -7.0F, -3.0F, 6.0F, 2.0F, 6.0F, 0.0F);
/*  70 */     this.field_178722_k = new ModelRenderer((Model)this, 16, 48);
/*  71 */     this.field_178722_k.func_78793_a(1.9F, 12.0F, 0.0F);
/*  72 */     this.field_178722_k.func_228301_a_(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F);
/*  73 */     this.field_178721_j = new ModelRenderer((Model)this, 0, 16);
/*  74 */     this.field_178721_j.func_78793_a(-1.9F, 12.0F, 0.0F);
/*  75 */     this.field_178721_j.func_228301_a_(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F);
/*  76 */     this.field_178724_i = new ModelRenderer((Model)this, 32, 48);
/*  77 */     this.field_178724_i.func_78793_a(5.0F, 2.0F, 0.0F);
/*  78 */     this.field_178724_i.func_228301_a_(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F);
/*  79 */     this.hat3 = new ModelRenderer((Model)this, 0, 36);
/*  80 */     this.hat3.func_78793_a(0.0F, 0.0F, 0.0F);
/*  81 */     this.hat3.func_228301_a_(-3.5F, -5.0F, -3.5F, 7.0F, 3.0F, 7.0F, 0.0F);
/*  82 */     this.hat4.func_78792_a(this.hat5);
/*  83 */     this.beard2.func_78792_a(this.leftFancyBeard1);
/*  84 */     this.beard1.func_78792_a(this.beard2);
/*  85 */     this.field_78116_c.func_78792_a(this.beard1);
/*  86 */     this.beard2.func_78792_a(this.rightFancyBeard1);
/*  87 */     this.field_78116_c.func_78792_a(this.hat1);
/*  88 */     this.hat1.func_78792_a(this.hat2);
/*  89 */     this.hat3.func_78792_a(this.hat4);
/*  90 */     this.hat2.func_78792_a(this.hat3);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/*  96 */     super.field_78115_e = this.field_78115_e;
/*  97 */     super.field_78116_c = this.field_78116_c;
/*  98 */     super.field_178724_i = this.field_178724_i;
/*  99 */     super.field_178723_h = this.field_178723_h;
/* 100 */     super.field_178722_k = this.field_178722_k;
/* 101 */     super.field_178721_j = this.field_178721_j;
/*     */     
/* 103 */     this.field_178720_f.field_78806_j = false;
/*     */     
/* 105 */     this.field_78116_c.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 106 */     this.field_178723_h.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 107 */     this.field_78115_e.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 108 */     this.field_178722_k.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 109 */     this.field_178721_j.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 110 */     this.field_178724_i.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
/* 115 */     model.field_78795_f = x;
/* 116 */     model.field_78796_g = y;
/* 117 */     model.field_78808_h = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\entities\mobs\humanoids\WeatherWizardModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */