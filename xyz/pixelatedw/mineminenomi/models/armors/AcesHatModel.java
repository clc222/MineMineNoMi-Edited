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
/*     */ public class AcesHatModel<T extends LivingEntity>
/*     */   extends BipedModel<T> {
/*     */   private final ModelRenderer hat;
/*     */   private final ModelRenderer base_0;
/*     */   private final ModelRenderer base_1;
/*     */   private final ModelRenderer mid_0;
/*     */   private final ModelRenderer symbols;
/*     */   private final ModelRenderer symb_1_r1;
/*     */   private final ModelRenderer beads_0;
/*     */   private final ModelRenderer beads_1;
/*     */   private final ModelRenderer beads_2;
/*     */   private final ModelRenderer beads_3;
/*     */   private final ModelRenderer right_string;
/*     */   private final ModelRenderer right_string_0;
/*     */   private final ModelRenderer right_string_1;
/*     */   private final ModelRenderer right_string_2;
/*     */   private final ModelRenderer left_string;
/*     */   private final ModelRenderer left_string_0;
/*     */   private final ModelRenderer left_string_1;
/*     */   private final ModelRenderer left_string_2;
/*     */   private final ModelRenderer ornament;
/*     */   
/*     */   public AcesHatModel() {
/*  34 */     super(1.0F);
/*  35 */     this.field_78090_t = 80;
/*  36 */     this.field_78089_u = 80;
/*     */     
/*  38 */     this.hat = new ModelRenderer((Model)this);
/*  39 */     this.hat.func_78793_a(0.0F, 1.0F, 0.0F);
/*     */ 
/*     */     
/*  42 */     this.base_0 = new ModelRenderer((Model)this);
/*  43 */     this.base_0.func_78793_a(0.0F, -6.0F, 0.0F);
/*  44 */     this.hat.func_78792_a(this.base_0);
/*  45 */     this.base_0.func_78784_a(0, 0).func_228303_a_(-6.0F, -1.01F, -7.0F, 12.0F, 1.0F, 14.0F, 0.0F, false);
/*  46 */     this.base_0.func_78784_a(0, 16).func_228303_a_(-7.0F, -1.0F, -6.0F, 14.0F, 1.0F, 12.0F, 0.0F, false);
/*     */     
/*  48 */     this.base_1 = new ModelRenderer((Model)this);
/*  49 */     this.base_1.func_78793_a(1.0F, -7.0F, -1.0F);
/*  50 */     this.hat.func_78792_a(this.base_1);
/*  51 */     this.base_1.func_78784_a(0, 30).func_228303_a_(-6.0F, -1.0F, -5.0F, 10.0F, 1.0F, 12.0F, 0.0F, false);
/*  52 */     this.base_1.func_78784_a(33, 30).func_228303_a_(-7.0F, -1.0F, -4.0F, 12.0F, 1.0F, 10.0F, 0.0F, false);
/*     */     
/*  54 */     this.mid_0 = new ModelRenderer((Model)this);
/*  55 */     this.mid_0.func_78793_a(0.0F, -6.0F, 0.0F);
/*  56 */     this.hat.func_78792_a(this.mid_0);
/*  57 */     this.mid_0.func_78784_a(39, 0).func_228303_a_(-4.0F, -3.0F, -4.0F, 8.0F, 1.0F, 8.0F, 0.0F, false);
/*  58 */     this.mid_0.func_78784_a(41, 16).func_228303_a_(-3.0F, -5.0F, -3.0F, 6.0F, 2.0F, 6.0F, 0.0F, false);
/*  59 */     this.mid_0.func_78784_a(41, 42).func_228303_a_(-2.0F, -6.0F, -2.0F, 4.0F, 1.0F, 4.0F, 0.0F, false);
/*     */     
/*  61 */     this.symbols = new ModelRenderer((Model)this);
/*  62 */     this.symbols.func_78793_a(0.0F, -6.0F, 0.0F);
/*  63 */     this.hat.func_78792_a(this.symbols);
/*     */ 
/*     */     
/*  66 */     this.symb_1_r1 = new ModelRenderer((Model)this);
/*  67 */     this.symb_1_r1.func_78793_a(1.5F, -3.0F, -4.5F);
/*  68 */     this.symbols.func_78792_a(this.symb_1_r1);
/*  69 */     setRotationAngle(this.symb_1_r1, -0.3927F, 0.0F, 0.0F);
/*  70 */     this.symb_1_r1.func_78784_a(5, 24).func_228303_a_(-1.0F, -2.0F, 0.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
/*  71 */     this.symb_1_r1.func_78784_a(5, 36).func_228303_a_(-4.0F, -2.0F, 0.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/*  73 */     this.beads_0 = new ModelRenderer((Model)this);
/*  74 */     this.beads_0.func_78793_a(0.5F, -6.0F, 0.0F);
/*  75 */     this.hat.func_78792_a(this.beads_0);
/*  76 */     this.beads_0.func_78784_a(44, 10).func_228303_a_(2.0F, -3.0F, -5.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);
/*  77 */     this.beads_0.func_78784_a(35, 44).func_228303_a_(0.5F, -3.0F, -5.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);
/*  78 */     this.beads_0.func_78784_a(30, 44).func_228303_a_(-1.0F, -3.0F, -5.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);
/*  79 */     this.beads_0.func_78784_a(25, 44).func_228303_a_(-2.5F, -3.0F, -5.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);
/*  80 */     this.beads_0.func_78784_a(20, 44).func_228303_a_(-4.0F, -3.0F, -5.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/*  82 */     this.beads_1 = new ModelRenderer((Model)this);
/*  83 */     this.beads_1.func_78793_a(0.5F, -6.0F, 9.5F);
/*  84 */     this.hat.func_78792_a(this.beads_1);
/*  85 */     this.beads_1.func_78784_a(15, 44).func_228303_a_(2.0F, -3.0F, -5.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);
/*  86 */     this.beads_1.func_78784_a(10, 44).func_228303_a_(0.5F, -3.0F, -5.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);
/*  87 */     this.beads_1.func_78784_a(5, 44).func_228303_a_(-1.0F, -3.0F, -5.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);
/*  88 */     this.beads_1.func_78784_a(0, 44).func_228303_a_(-2.5F, -3.0F, -5.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);
/*  89 */     this.beads_1.func_78784_a(41, 25).func_228303_a_(-4.0F, -3.0F, -5.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/*  91 */     this.beads_2 = new ModelRenderer((Model)this);
/*  92 */     this.beads_2.func_78793_a(-4.5F, -6.0F, 0.5F);
/*  93 */     this.hat.func_78792_a(this.beads_2);
/*  94 */     setRotationAngle(this.beads_2, 0.0F, -1.5708F, 0.0F);
/*  95 */     this.beads_2.func_78784_a(41, 19).func_228303_a_(2.0F, -3.0F, -0.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);
/*  96 */     this.beads_2.func_78784_a(41, 16).func_228303_a_(0.5F, -3.0F, -0.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);
/*  97 */     this.beads_2.func_78784_a(39, 10).func_228303_a_(-1.0F, -3.0F, -0.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);
/*  98 */     this.beads_2.func_78784_a(39, 3).func_228303_a_(-2.5F, -3.0F, -0.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);
/*  99 */     this.beads_2.func_78784_a(39, 0).func_228303_a_(-4.0F, -3.0F, -0.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 101 */     this.beads_3 = new ModelRenderer((Model)this);
/* 102 */     this.beads_3.func_78793_a(5.0F, -6.0F, 0.5F);
/* 103 */     this.hat.func_78792_a(this.beads_3);
/* 104 */     setRotationAngle(this.beads_3, 0.0F, -1.5708F, 0.0F);
/* 105 */     this.beads_3.func_78784_a(38, 36).func_228303_a_(2.0F, -3.0F, -0.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);
/* 106 */     this.beads_3.func_78784_a(38, 33).func_228303_a_(0.5F, -3.0F, -0.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);
/* 107 */     this.beads_3.func_78784_a(38, 30).func_228303_a_(-1.0F, -3.0F, -0.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);
/* 108 */     this.beads_3.func_78784_a(33, 30).func_228303_a_(-2.5F, -3.0F, -0.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);
/* 109 */     this.beads_3.func_78784_a(9, 9).func_228303_a_(-4.0F, -3.0F, -0.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 111 */     this.right_string = new ModelRenderer((Model)this);
/* 112 */     this.right_string.func_78793_a(-5.0F, -6.0F, -3.0F);
/* 113 */     this.hat.func_78792_a(this.right_string);
/*     */ 
/*     */     
/* 116 */     this.right_string_0 = new ModelRenderer((Model)this);
/* 117 */     this.right_string_0.func_78793_a(-0.5F, 0.0F, 1.5F);
/* 118 */     this.right_string.func_78792_a(this.right_string_0);
/* 119 */     setRotationAngle(this.right_string_0, -0.3927F, 0.0F, -0.0873F);
/* 120 */     this.right_string_0.func_78784_a(0, 36).func_228303_a_(-0.5048F, -0.2181F, -0.5F, 1.0F, 4.0F, 1.0F, -0.2F, false);
/*     */     
/* 122 */     this.right_string_1 = new ModelRenderer((Model)this);
/* 123 */     this.right_string_1.func_78793_a(-0.0048F, 3.5319F, 0.0F);
/* 124 */     this.right_string_0.func_78792_a(this.right_string_1);
/* 125 */     setRotationAngle(this.right_string_1, -0.1309F, 0.0F, -0.1309F);
/* 126 */     this.right_string_1.func_78784_a(33, 33).func_228303_a_(-0.5F, -0.25F, -0.5F, 1.0F, 4.0F, 1.0F, -0.2F, false);
/*     */     
/* 128 */     this.right_string_2 = new ModelRenderer((Model)this);
/* 129 */     this.right_string_2.func_78793_a(0.0F, 3.5F, 0.0F);
/* 130 */     this.right_string_1.func_78792_a(this.right_string_2);
/* 131 */     setRotationAngle(this.right_string_2, 0.2182F, 0.0F, -0.3927F);
/* 132 */     this.right_string_2.func_78784_a(5, 0).func_228303_a_(-0.5F, -0.25F, -0.5F, 1.0F, 8.0F, 1.0F, -0.2F, false);
/*     */     
/* 134 */     this.left_string = new ModelRenderer((Model)this);
/* 135 */     this.left_string.func_78793_a(5.0F, -6.0F, -3.0F);
/* 136 */     this.hat.func_78792_a(this.left_string);
/*     */ 
/*     */     
/* 139 */     this.left_string_0 = new ModelRenderer((Model)this);
/* 140 */     this.left_string_0.func_78793_a(0.5F, 0.0F, 1.5F);
/* 141 */     this.left_string.func_78792_a(this.left_string_0);
/* 142 */     setRotationAngle(this.left_string_0, -0.3927F, 0.0F, 0.0873F);
/* 143 */     this.left_string_0.func_78784_a(5, 30).func_228303_a_(-0.5048F, -0.2181F, -0.5F, 1.0F, 4.0F, 1.0F, -0.2F, false);
/*     */     
/* 145 */     this.left_string_1 = new ModelRenderer((Model)this);
/* 146 */     this.left_string_1.func_78793_a(-0.0048F, 3.5319F, 0.0F);
/* 147 */     this.left_string_0.func_78792_a(this.left_string_1);
/* 148 */     setRotationAngle(this.left_string_1, -0.1309F, 0.0F, 0.1309F);
/* 149 */     this.left_string_1.func_78784_a(0, 30).func_228303_a_(-0.5F, -0.25F, -0.5F, 1.0F, 4.0F, 1.0F, -0.2F, false);
/*     */     
/* 151 */     this.left_string_2 = new ModelRenderer((Model)this);
/* 152 */     this.left_string_2.func_78793_a(0.0F, 3.5F, 0.0F);
/* 153 */     this.left_string_1.func_78792_a(this.left_string_2);
/* 154 */     setRotationAngle(this.left_string_2, 0.2182F, 0.0F, 0.3927F);
/* 155 */     this.left_string_2.func_78784_a(0, 0).func_228303_a_(-0.5F, -0.25F, -0.5F, 1.0F, 8.0F, 1.0F, -0.2F, false);
/*     */     
/* 157 */     this.ornament = new ModelRenderer((Model)this);
/* 158 */     this.ornament.func_78793_a(0.0F, 6.0F, -6.0F);
/* 159 */     this.hat.func_78792_a(this.ornament);
/* 160 */     this.ornament.func_78784_a(5, 20).func_228303_a_(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
/* 161 */     this.ornament.func_78784_a(0, 20).func_228303_a_(-0.5F, -1.0F, -0.75F, 1.0F, 5.0F, 1.0F, -0.1F, false);
/* 162 */     this.ornament.func_78784_a(0, 16).func_228303_a_(-1.0F, 1.25F, -1.25F, 2.0F, 1.0F, 2.0F, -0.3F, false);
/* 163 */     this.ornament.func_78784_a(0, 10).func_228303_a_(-1.0F, 2.5F, -1.25F, 2.0F, 1.0F, 2.0F, -0.3F, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 169 */     super.func_225597_a_((LivingEntity)entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 175 */     this.hat.func_217177_a(this.field_78116_c);
/* 176 */     this.hat.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 181 */     modelRenderer.field_78795_f = x;
/* 182 */     modelRenderer.field_78796_g = y;
/* 183 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\armors\AcesHatModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */