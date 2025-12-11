/*     */ package xyz.pixelatedw.mineminenomi.models.entities.mobs.animals;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.FightingFishEntity;
/*     */ 
/*     */ public class FightingFishModel<T extends FightingFishEntity>
/*     */   extends EntityModel<T> {
/*     */   private final ModelRenderer body;
/*     */   private final ModelRenderer topFins;
/*     */   private final ModelRenderer Fin3_r1;
/*     */   private final ModelRenderer Fin2_r1;
/*     */   private final ModelRenderer tail;
/*     */   private final ModelRenderer flippers1;
/*     */   private final ModelRenderer flippers2;
/*     */   private final ModelRenderer head;
/*     */   private final ModelRenderer nose_r1;
/*     */   private final ModelRenderer eyebrow2_r1;
/*     */   private final ModelRenderer eyebrow1_r1;
/*     */   private final ModelRenderer horn2;
/*     */   private final ModelRenderer Horn5_r1;
/*     */   private final ModelRenderer Horn4_r1;
/*     */   private final ModelRenderer Horn3_r1;
/*     */   private final ModelRenderer Horn2_r1;
/*     */   private final ModelRenderer Horn1_r1;
/*     */   private final ModelRenderer horn1;
/*     */   private final ModelRenderer Horn5_r2;
/*     */   private final ModelRenderer Horn4_r2;
/*     */   private final ModelRenderer Horn3_r2;
/*     */   private final ModelRenderer Horn2_r2;
/*     */   private final ModelRenderer Horn1_r2;
/*     */   
/*     */   public FightingFishModel() {
/*  39 */     this.field_78090_t = 288;
/*  40 */     this.field_78089_u = 288;
/*     */     
/*  42 */     this.body = new ModelRenderer((Model)this);
/*  43 */     this.body.func_78793_a(0.0F, 16.0F, 0.0F);
/*  44 */     this.body.func_78784_a(83, 83).func_228303_a_(-18.0F, -56.0F, -7.0F, 36.0F, 50.0F, 26.0F, 0.0F, false);
/*  45 */     this.body.func_78784_a(0, 105).func_228303_a_(-17.0F, -55.0F, 19.0F, 34.0F, 48.0F, 7.0F, 0.0F, false);
/*  46 */     this.body.func_78784_a(192, 53).func_228303_a_(-16.0F, -54.0F, 26.0F, 32.0F, 46.0F, 6.0F, 0.0F, false);
/*  47 */     this.body.func_78784_a(0, 161).func_228303_a_(-13.0F, -51.0F, 32.0F, 26.0F, 40.0F, 9.0F, 0.0F, false);
/*  48 */     this.body.func_78784_a(55, 0).func_228303_a_(-7.0F, -46.0F, 41.0F, 14.0F, 30.0F, 9.0F, 0.0F, false);
/*     */     
/*  50 */     this.topFins = new ModelRenderer((Model)this);
/*  51 */     this.topFins.func_78793_a(0.0F, 8.0F, 0.0F);
/*  52 */     this.body.func_78792_a(this.topFins);
/*  53 */     setRotationAngle(this.topFins, 0.0873F, 0.0F, 0.0F);
/*     */ 
/*     */     
/*  56 */     this.Fin3_r1 = new ModelRenderer((Model)this);
/*  57 */     this.Fin3_r1.func_78793_a(-1.0F, -56.0F, 1.0F);
/*  58 */     this.topFins.func_78792_a(this.Fin3_r1);
/*  59 */     setRotationAngle(this.Fin3_r1, 1.1345F, 0.0F, 0.0F);
/*  60 */     this.Fin3_r1.func_78784_a(156, 160).func_228303_a_(0.0F, 7.3853F, 9.8288F, 0.0F, 42.0F, 29.0F, 0.0F, false);
/*     */     
/*  62 */     this.Fin2_r1 = new ModelRenderer((Model)this);
/*  63 */     this.Fin2_r1.func_78793_a(-1.0F, -56.0F, 1.0F);
/*  64 */     this.topFins.func_78792_a(this.Fin2_r1);
/*  65 */     setRotationAngle(this.Fin2_r1, 1.2217F, 0.0F, 0.0F);
/*  66 */     this.Fin2_r1.func_78784_a(42, 186).func_228303_a_(0.0F, -1.0162F, 8.3144F, 0.0F, 14.0F, 29.0F, 0.0F, false);
/*     */     
/*  68 */     this.tail = new ModelRenderer((Model)this);
/*  69 */     this.tail.func_78793_a(0.0F, -22.0F, 54.0F);
/*  70 */     this.body.func_78792_a(this.tail);
/*  71 */     this.tail.func_78784_a(156, 160).func_228303_a_(-1.0F, -18.0F, -4.0F, 2.0F, 18.0F, 8.0F, 0.0F, false);
/*  72 */     this.tail.func_78784_a(0, 0).func_228303_a_(0.0F, -28.0F, 4.0F, 0.0F, 50.0F, 54.0F, 0.0F, false);
/*     */     
/*  74 */     this.flippers1 = new ModelRenderer((Model)this);
/*  75 */     this.flippers1.func_78793_a(16.0F, -10.0F, -13.0F);
/*  76 */     this.body.func_78792_a(this.flippers1);
/*  77 */     setRotationAngle(this.flippers1, 0.0F, 0.0F, 0.3927F);
/*  78 */     this.flippers1.func_78784_a(186, 166).func_228303_a_(-1.1585F, -7.7967F, 0.0F, 38.0F, 16.0F, 0.0F, 0.0F, false);
/*     */     
/*  80 */     this.flippers2 = new ModelRenderer((Model)this);
/*  81 */     this.flippers2.func_78793_a(-17.0F, -10.0F, -13.0F);
/*  82 */     this.body.func_78792_a(this.flippers2);
/*  83 */     setRotationAngle(this.flippers2, 0.0F, 0.0F, -0.3927F);
/*  84 */     this.flippers2.func_78784_a(109, 59).func_228303_a_(-35.8839F, -7.9665F, 0.0F, 38.0F, 16.0F, 0.0F, 0.0F, false);
/*     */     
/*  86 */     this.head = new ModelRenderer((Model)this);
/*  87 */     this.head.func_78793_a(0.0F, 8.0F, 0.0F);
/*  88 */     this.body.func_78792_a(this.head);
/*  89 */     this.head.func_78784_a(109, 0).func_228303_a_(-17.0F, -63.0F, -17.0F, 34.0F, 48.0F, 10.0F, 0.0F, false);
/*  90 */     this.head.func_78784_a(75, 160).func_228303_a_(-16.0F, -62.0F, -25.0F, 32.0F, 46.0F, 8.0F, 0.0F, false);
/*  91 */     this.head.func_78784_a(198, 0).func_228303_a_(-15.0F, -58.0F, -33.0F, 30.0F, 38.0F, 8.0F, 0.0F, false);
/*  92 */     this.head.func_78784_a(184, 259).func_228303_a_(-13.5F, -36.0F, -38.0F, 27.0F, 11.0F, 5.0F, 0.0F, false);
/*     */     
/*  94 */     this.nose_r1 = new ModelRenderer((Model)this);
/*  95 */     this.nose_r1.func_78793_a(1.0F, -45.0F, -33.0F);
/*  96 */     this.head.func_78792_a(this.nose_r1);
/*  97 */     setRotationAngle(this.nose_r1, -0.48F, 0.0F, 0.0F);
/*  98 */     this.nose_r1.func_78784_a(257, 267).func_228303_a_(-4.0F, -1.0F, 0.0F, 6.0F, 10.0F, 6.0F, 0.0F, false);
/*     */     
/* 100 */     this.eyebrow2_r1 = new ModelRenderer((Model)this);
/* 101 */     this.eyebrow2_r1.func_78793_a(13.0F, -58.0F, -35.0F);
/* 102 */     this.head.func_78792_a(this.eyebrow2_r1);
/* 103 */     setRotationAngle(this.eyebrow2_r1, -0.2967F, 0.0F, -0.2182F);
/* 104 */     this.eyebrow2_r1.func_78784_a(250, 248).func_228303_a_(-13.0F, 0.0F, 0.0F, 13.0F, 8.0F, 6.0F, 0.0F, false);
/*     */     
/* 106 */     this.eyebrow1_r1 = new ModelRenderer((Model)this);
/* 107 */     this.eyebrow1_r1.func_78793_a(-13.0F, -58.0F, -35.0F);
/* 108 */     this.head.func_78792_a(this.eyebrow1_r1);
/* 109 */     setRotationAngle(this.eyebrow1_r1, -0.2967F, 0.0F, 0.2182F);
/* 110 */     this.eyebrow1_r1.func_78784_a(250, 248).func_228303_a_(0.0F, 0.0F, 0.0F, 13.0F, 8.0F, 6.0F, 0.0F, false);
/*     */     
/* 112 */     this.horn2 = new ModelRenderer((Model)this);
/* 113 */     this.horn2.func_78793_a(-6.0F, -2.0F, -1.0F);
/* 114 */     this.head.func_78792_a(this.horn2);
/* 115 */     setRotationAngle(this.horn2, 0.3927F, 0.3054F, 0.0F);
/*     */ 
/*     */     
/* 118 */     this.Horn5_r1 = new ModelRenderer((Model)this);
/* 119 */     this.Horn5_r1.func_78793_a(-26.1548F, -46.1359F, 0.7065F);
/* 120 */     this.horn2.func_78792_a(this.Horn5_r1);
/* 121 */     setRotationAngle(this.Horn5_r1, -0.9599F, 0.0F, 0.0F);
/* 122 */     this.Horn5_r1.func_78784_a(215, 215).func_228303_a_(17.8372F, -20.223F, -17.9248F, 10.0F, 11.0F, 13.0F, 0.0F, false);
/*     */     
/* 124 */     this.Horn4_r1 = new ModelRenderer((Model)this);
/* 125 */     this.Horn4_r1.func_78793_a(-28.0F, -47.0F, -25.0F);
/* 126 */     this.horn2.func_78792_a(this.Horn4_r1);
/* 127 */     setRotationAngle(this.Horn4_r1, 0.7418F, 0.0F, -0.1309F);
/* 128 */     this.Horn4_r1.func_78784_a(25, 38).func_228303_a_(22.4127F, -19.896F, 12.5141F, 6.0F, 9.0F, 6.0F, 0.0F, false);
/*     */     
/* 130 */     this.Horn3_r1 = new ModelRenderer((Model)this);
/* 131 */     this.Horn3_r1.func_78793_a(-26.0F, -45.5325F, -17.0188F);
/* 132 */     this.horn2.func_78792_a(this.Horn3_r1);
/* 133 */     setRotationAngle(this.Horn3_r1, -0.0873F, 0.2182F, 0.0F);
/* 134 */     this.Horn3_r1.func_78784_a(177, 232).func_228303_a_(17.4088F, -28.0357F, -3.7533F, 8.0F, 8.0F, 12.0F, 0.0F, false);
/*     */     
/* 136 */     this.Horn2_r1 = new ModelRenderer((Model)this);
/* 137 */     this.Horn2_r1.func_78793_a(-27.0F, -50.4966F, -7.4925F);
/* 138 */     this.horn2.func_78792_a(this.Horn2_r1);
/* 139 */     setRotationAngle(this.Horn2_r1, 0.5236F, 0.0F, 0.0F);
/* 140 */     this.Horn2_r1.func_78784_a(29, 230).func_228303_a_(18.6824F, -21.7016F, 3.1475F, 10.0F, 8.0F, 12.0F, 0.0F, false);
/*     */     
/* 142 */     this.Horn1_r1 = new ModelRenderer((Model)this);
/* 143 */     this.Horn1_r1.func_78793_a(-26.0F, -46.0F, 0.0F);
/* 144 */     this.horn2.func_78792_a(this.Horn1_r1);
/* 145 */     setRotationAngle(this.Horn1_r1, -0.3491F, 0.0F, 0.0F);
/* 146 */     this.Horn1_r1.func_78784_a(226, 106).func_228303_a_(18.6824F, -27.2156F, -15.4664F, 8.0F, 11.0F, 13.0F, 0.0F, false);
/*     */     
/* 148 */     this.horn1 = new ModelRenderer((Model)this);
/* 149 */     this.horn1.func_78793_a(-28.0F, -2.0F, -1.0F);
/* 150 */     this.head.func_78792_a(this.horn1);
/* 151 */     setRotationAngle(this.horn1, 0.3927F, -0.3054F, 0.0F);
/*     */ 
/*     */     
/* 154 */     this.Horn5_r2 = new ModelRenderer((Model)this);
/* 155 */     this.Horn5_r2.func_78793_a(26.1548F, -46.1359F, 0.7065F);
/* 156 */     this.horn1.func_78792_a(this.Horn5_r2);
/* 157 */     setRotationAngle(this.Horn5_r2, -0.9599F, 0.0F, 0.0F);
/* 158 */     this.Horn5_r2.func_78784_a(101, 215).func_228303_a_(4.5895F, -14.7305F, -26.547F, 10.0F, 11.0F, 13.0F, 0.0F, false);
/*     */     
/* 160 */     this.Horn4_r2 = new ModelRenderer((Model)this);
/* 161 */     this.Horn4_r2.func_78793_a(28.0F, -47.0F, -25.0F);
/* 162 */     this.horn1.func_78792_a(this.Horn4_r2);
/* 163 */     setRotationAngle(this.Horn4_r2, 0.7418F, 0.0F, 0.1309F);
/* 164 */     this.Horn4_r2.func_78784_a(0, 38).func_228303_a_(3.226F, -32.2571F, 11.031F, 6.0F, 9.0F, 6.0F, 0.0F, false);
/*     */     
/* 166 */     this.Horn3_r2 = new ModelRenderer((Model)this);
/* 167 */     this.Horn3_r2.func_78793_a(26.0F, -45.5325F, -17.0188F);
/* 168 */     this.horn1.func_78792_a(this.Horn3_r2);
/* 169 */     setRotationAngle(this.Horn3_r2, -0.0873F, -0.2182F, 0.0F);
/* 170 */     this.Horn3_r2.func_78784_a(136, 232).func_228303_a_(4.2045F, -30.517F, -20.2728F, 8.0F, 8.0F, 12.0F, 0.0F, false);
/*     */     
/* 172 */     this.Horn2_r2 = new ModelRenderer((Model)this);
/* 173 */     this.Horn2_r2.func_78793_a(27.0F, -50.4966F, -7.4925F);
/* 174 */     this.horn1.func_78792_a(this.Horn2_r2);
/* 175 */     setRotationAngle(this.Horn2_r2, 0.5236F, 0.0F, 0.0F);
/* 176 */     this.Horn2_r2.func_78784_a(226, 131).func_228303_a_(3.7443F, -29.812F, -3.0758F, 10.0F, 8.0F, 12.0F, 0.0F, false);
/*     */     
/* 178 */     this.Horn1_r2 = new ModelRenderer((Model)this);
/* 179 */     this.Horn1_r2.func_78793_a(26.0F, -46.0F, 0.0F);
/* 180 */     this.horn1.func_78792_a(this.Horn1_r2);
/* 181 */     setRotationAngle(this.Horn1_r2, -0.3491F, 0.0F, 0.0F);
/* 182 */     this.Horn1_r2.func_78784_a(215, 183).func_228303_a_(5.7443F, -27.6612F, -25.6796F, 8.0F, 11.0F, 13.0F, 0.0F, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 188 */     this.body.field_78795_f = headPitch * 0.017453292F;
/* 189 */     this.body.field_78796_g = netHeadYaw * 0.017453292F;
/*     */     
/* 191 */     this.tail.field_78796_g = 0.1F * (-0.2F + MathHelper.func_76134_b(ageInTicks * 0.15F));
/* 192 */     this.flippers1.field_78796_g = 0.1F * (-1.5F + MathHelper.func_76134_b(ageInTicks * 0.35F));
/* 193 */     this.flippers2.field_78796_g = 0.1F * (-1.5F + MathHelper.func_76134_b(ageInTicks * 0.35F));
/* 194 */     this.topFins.field_78795_f = 0.01F * (-0.2F + MathHelper.func_76134_b(ageInTicks * 0.15F));
/* 195 */     this.topFins.field_78796_g = 0.02F * (-0.2F + MathHelper.func_76134_b(ageInTicks * 0.15F));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 201 */     this.body.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 206 */     modelRenderer.field_78795_f = x;
/* 207 */     modelRenderer.field_78796_g = y;
/* 208 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\entities\mobs\animals\FightingFishModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */