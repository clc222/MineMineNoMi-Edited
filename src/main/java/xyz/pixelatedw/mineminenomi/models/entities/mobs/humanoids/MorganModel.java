/*     */ package xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.entity.CreatureEntity;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.marines.MorganEntity;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MorganModel<T extends MorganEntity>
/*     */   extends HumanoidModel<T>
/*     */ {
/*     */   public ModelRenderer jaw;
/*     */   public ModelRenderer rightarm2;
/*     */   public ModelRenderer axe_hand;
/*     */   public ModelRenderer cable;
/*     */   public ModelRenderer axe1;
/*     */   public ModelRenderer axe2;
/*     */   public ModelRenderer axe4;
/*     */   public ModelRenderer axe41;
/*     */   public ModelRenderer axe5;
/*     */   public ModelRenderer axe51;
/*     */   public ModelRenderer axe6;
/*     */   public ModelRenderer axe61;
/*     */   public ModelRenderer leftarm2;
/*     */   
/*     */   public MorganModel() {
/*  34 */     this.field_78090_t = 128;
/*  35 */     this.field_78089_u = 64;
/*  36 */     this.axe51 = new ModelRenderer((Model)this, 105, 59);
/*  37 */     this.axe51.func_78793_a(0.0F, 0.0F, 0.0F);
/*  38 */     this.axe51.func_228301_a_(-1.5F, 12.1F, 5.8F, 1.0F, 3.0F, 2.0F, 0.0F);
/*  39 */     setRotateAngle(this.axe51, -0.41887903F, -0.0F, 0.0F);
/*  40 */     this.axe2 = new ModelRenderer((Model)this, 76, 53);
/*  41 */     this.axe2.func_78793_a(0.0F, 0.0F, 0.0F);
/*  42 */     this.axe2.func_228301_a_(-2.0F, 10.0F, 9.5F, 2.0F, 7.0F, 4.0F, 0.0F);
/*  43 */     setRotateAngle(this.axe2, -0.9599311F, 0.0F, 0.0F);
/*  44 */     this.jaw = new ModelRenderer((Model)this, 25, 0);
/*  45 */     this.jaw.func_78793_a(0.0F, 0.0F, 0.0F);
/*  46 */     this.jaw.func_228301_a_(-3.6F, -2.0F, -4.0F, 7.0F, 2.0F, 7.0F, 0.0F);
/*  47 */     this.field_178724_i = new ModelRenderer((Model)this, 21, 35);
/*  48 */     this.field_178724_i.func_78793_a(6.0F, -7.0F, 0.0F);
/*  49 */     this.field_178724_i.func_228301_a_(-1.0F, -2.0F, -2.5F, 5.0F, 9.0F, 5.0F, 0.0F);
/*  50 */     setRotateAngle(this.field_178724_i, 0.0F, -0.0F, -0.05235988F);
/*  51 */     this.field_78115_e = new ModelRenderer((Model)this, 54, 0);
/*  52 */     this.field_78115_e.func_78793_a(0.0F, -9.0F, 0.0F);
/*  53 */     this.field_78115_e.func_228301_a_(-5.0F, 0.0F, -2.5F, 10.0F, 16.0F, 5.0F, 0.0F);
/*  54 */     this.axe61 = new ModelRenderer((Model)this, 112, 59);
/*  55 */     this.axe61.func_78793_a(0.0F, 0.0F, 0.0F);
/*  56 */     this.axe61.func_228301_a_(-1.5F, 7.4F, 19.9F, 1.0F, 3.0F, 2.0F, 0.0F);
/*  57 */     setRotateAngle(this.axe61, -1.5358897F, -0.0F, 0.0F);
/*  58 */     this.axe41 = new ModelRenderer((Model)this, 100, 54);
/*  59 */     this.axe41.func_78793_a(0.0F, 0.0F, 0.0F);
/*  60 */     this.axe41.func_228301_a_(-1.5F, 9.0F, 13.5F, 1.0F, 9.0F, 1.0F, 0.0F);
/*  61 */     setRotateAngle(this.axe41, -0.9599311F, -0.0F, 0.0F);
/*  62 */     this.leftarm2 = new ModelRenderer((Model)this, 21, 50);
/*  63 */     this.leftarm2.func_78793_a(0.0F, 0.0F, 0.0F);
/*  64 */     this.leftarm2.func_228301_a_(-0.9F, 6.9F, -2.5F, 5.0F, 9.0F, 5.0F, 0.0F);
/*  65 */     setRotateAngle(this.leftarm2, 0.0F, -0.0F, 0.017453292F);
/*  66 */     this.axe5 = new ModelRenderer((Model)this, 105, 52);
/*  67 */     this.axe5.func_78793_a(0.0F, 0.0F, 0.0F);
/*  68 */     this.axe5.func_228301_a_(-2.0F, 12.1F, 2.5F, 2.0F, 2.0F, 4.0F, 0.0F);
/*  69 */     setRotateAngle(this.axe5, -0.41887903F, -0.0F, 0.0F);
/*  70 */     this.field_78116_c = new ModelRenderer((Model)this, 0, 0);
/*  71 */     this.field_78116_c.func_78793_a(0.0F, -9.0F, 0.0F);
/*  72 */     this.field_78116_c.func_228301_a_(-3.0F, -7.0F, -3.0F, 6.0F, 7.0F, 6.0F, 0.0F);
/*  73 */     this.field_178722_k = new ModelRenderer((Model)this, 0, 42);
/*  74 */     this.field_178722_k.func_78793_a(2.5F, 7.0F, 0.0F);
/*  75 */     this.field_178722_k.func_228301_a_(-2.5F, 0.0F, -2.5F, 5.0F, 17.0F, 5.0F, 0.0F);
/*  76 */     this.rightarm2 = new ModelRenderer((Model)this, 21, 14);
/*  77 */     this.rightarm2.field_78809_i = true;
/*  78 */     this.rightarm2.func_78793_a(0.0F, 0.0F, 0.0F);
/*  79 */     this.rightarm2.func_228301_a_(-4.0F, 3.2F, 3.9F, 5.0F, 7.0F, 5.0F, 0.0F);
/*  80 */     setRotateAngle(this.rightarm2, -0.9599311F, -0.0F, 0.0F);
/*  81 */     this.cable = new ModelRenderer((Model)this, 42, 47);
/*  82 */     this.cable.func_78793_a(0.0F, 0.0F, 0.0F);
/*  83 */     this.cable.func_228301_a_(-2.5F, 0.0F, 5.5F, 2.0F, 20.0F, 2.0F, 0.0F);
/*  84 */     setRotateAngle(this.cable, -0.9599311F, 0.0F, 0.0F);
/*  85 */     this.axe_hand = new ModelRenderer((Model)this, 0, 0);
/*  86 */     this.axe_hand.func_78793_a(0.0F, 0.0F, 0.0F);
/*  87 */     this.axe_hand.func_228301_a_(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
/*  88 */     setRotateAngle(this.axe_hand, 0.9599311F, 0.0F, 0.0F);
/*  89 */     this.field_178723_h = new ModelRenderer((Model)this, 0, 14);
/*  90 */     this.field_178723_h.func_78793_a(-6.0F, -7.0F, 0.0F);
/*  91 */     this.field_178723_h.func_228301_a_(-4.0F, -2.0F, -2.5F, 5.0F, 11.0F, 5.0F, 0.0F);
/*  92 */     this.axe1 = new ModelRenderer((Model)this, 51, 51);
/*  93 */     this.axe1.func_78793_a(0.0F, 0.0F, 0.0F);
/*  94 */     this.axe1.func_228301_a_(-4.5F, 10.0F, 3.5F, 6.0F, 7.0F, 6.0F, 0.0F);
/*  95 */     setRotateAngle(this.axe1, -0.9599311F, -0.0F, 0.0F);
/*  96 */     this.axe6 = new ModelRenderer((Model)this, 105, 52);
/*  97 */     this.axe6.func_78793_a(0.0F, 0.0F, 0.0F);
/*  98 */     this.axe6.func_228301_a_(-2.0F, 8.6F, 16.6F, 2.0F, 2.0F, 4.0F, 0.0F);
/*  99 */     setRotateAngle(this.axe6, -1.5184364F, -0.0F, 0.0F);
/* 100 */     this.field_178721_j = new ModelRenderer((Model)this, 0, 42);
/* 101 */     this.field_178721_j.func_78793_a(-2.5F, 7.0F, 0.0F);
/* 102 */     this.field_178721_j.func_228301_a_(-2.5F, 0.0F, -2.5F, 5.0F, 17.0F, 5.0F, 0.0F);
/* 103 */     this.axe4 = new ModelRenderer((Model)this, 89, 52);
/* 104 */     this.axe4.func_78793_a(0.0F, 0.0F, 0.0F);
/* 105 */     this.axe4.func_228301_a_(-2.0F, 9.0F, 10.5F, 2.0F, 9.0F, 3.0F, 0.0F);
/* 106 */     setRotateAngle(this.axe4, -0.9599311F, -0.0F, 0.0F);
/* 107 */     this.axe_hand.func_78792_a(this.axe51);
/* 108 */     this.axe_hand.func_78792_a(this.axe2);
/* 109 */     this.field_78116_c.func_78792_a(this.jaw);
/* 110 */     this.axe_hand.func_78792_a(this.axe61);
/* 111 */     this.axe_hand.func_78792_a(this.axe41);
/* 112 */     this.field_178724_i.func_78792_a(this.leftarm2);
/* 113 */     this.axe_hand.func_78792_a(this.axe5);
/* 114 */     this.field_178723_h.func_78792_a(this.rightarm2);
/* 115 */     this.axe_hand.func_78792_a(this.cable);
/* 116 */     this.rightarm2.func_78792_a(this.axe_hand);
/* 117 */     this.axe_hand.func_78792_a(this.axe1);
/* 118 */     this.axe_hand.func_78792_a(this.axe6);
/* 119 */     this.axe_hand.func_78792_a(this.axe4);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 125 */     matrixStack.func_227860_a_();
/* 126 */     matrixStack.func_227861_a_(0.0D, -0.3D, 0.0D);
/* 127 */     this.field_178723_h.field_78797_d = -0.8F;
/* 128 */     this.field_178723_h.field_78800_c = -6.0F;
/* 129 */     this.field_178724_i.field_78797_d = -0.8F;
/* 130 */     this.field_178724_i.field_78800_c = 6.0F;
/* 131 */     this.field_78115_e.field_78797_d = -3.0F;
/* 132 */     this.field_78116_c.field_78797_d = -3.0F;
/*     */     
/* 134 */     this.field_178724_i.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 135 */     this.field_78115_e.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 136 */     this.field_78116_c.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 137 */     this.field_178722_k.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 138 */     this.field_178723_h.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 139 */     this.field_178721_j.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 140 */     this.jaw.field_78798_e = 0.01F;
/* 141 */     this.jaw.field_78797_d = 0.01F;
/* 142 */     matrixStack.func_227865_b_();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 148 */     super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setupAttackAnimation(MorganEntity entity, float ageInTicks) {
/* 157 */     if (this.field_217112_c > 0.0F) {
/* 158 */       float f = this.field_217112_c;
/* 159 */       f = 1.0F - this.field_217112_c;
/* 160 */       f *= f;
/* 161 */       f *= f;
/* 162 */       f = 1.0F - f;
/* 163 */       float f1 = MathHelper.func_76126_a(f * 3.1415927F);
/* 164 */       float f2 = MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * -(this.field_78116_c.field_78795_f - 0.7F) * 0.75F;
/*     */       
/* 166 */       this.field_178723_h.field_78795_f = (float)(this.field_178723_h.field_78795_f - f1 * 1.6D + f2);
/* 167 */       this.field_178723_h.field_78796_g += this.field_78115_e.field_78796_g * 4.0F;
/* 168 */       this.field_178723_h.field_78808_h += MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * -0.8F;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
/* 174 */     model.field_78795_f = x;
/* 175 */     model.field_78796_g = y;
/* 176 */     model.field_78808_h = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\entities\mobs\humanoids\MorganModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */