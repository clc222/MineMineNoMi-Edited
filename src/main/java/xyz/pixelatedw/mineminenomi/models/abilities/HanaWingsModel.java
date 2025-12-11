/*     */ package xyz.pixelatedw.mineminenomi.models.abilities;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.renderer.entity.model.PlayerModel;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ 
/*     */ public class HanaWingsModel<T extends LivingEntity>
/*     */   extends PlayerModel<T> {
/*     */   private final ModelRenderer wings;
/*     */   private final ModelRenderer rightWing;
/*     */   private final ModelRenderer rightWing1;
/*     */   private final ModelRenderer rightWing2;
/*     */   private final ModelRenderer rightWing3;
/*     */   private final ModelRenderer rightWing4;
/*     */   private final ModelRenderer rightWing5;
/*     */   private final ModelRenderer rightWing6;
/*     */   private final ModelRenderer rightWing7;
/*     */   private final ModelRenderer rightWing8;
/*     */   private final ModelRenderer rightWingBone1;
/*     */   private final ModelRenderer rightWingBone11;
/*     */   private final ModelRenderer rightWingBone2;
/*     */   private final ModelRenderer rightWingBone21;
/*     */   private final ModelRenderer rightWingBone3;
/*     */   private final ModelRenderer rightWingBone31;
/*     */   private final ModelRenderer leftWing;
/*     */   private final ModelRenderer leftWing1;
/*     */   private final ModelRenderer leftWing2;
/*     */   private final ModelRenderer leftWing3;
/*     */   private final ModelRenderer leftWing4;
/*     */   private final ModelRenderer leftWing5;
/*     */   private final ModelRenderer leftWing6;
/*     */   private final ModelRenderer leftWing7;
/*     */   private final ModelRenderer leftWing8;
/*     */   private final ModelRenderer leftWingBone1;
/*     */   private final ModelRenderer leftWingBone11;
/*     */   private final ModelRenderer leftWingBone2;
/*     */   private final ModelRenderer leftWingBone21;
/*     */   private final ModelRenderer leftWingBone3;
/*     */   private final ModelRenderer leftWingBone31;
/*     */   
/*     */   public HanaWingsModel() {
/*  46 */     super(1.0F, false);
/*  47 */     this.field_78090_t = 64;
/*  48 */     this.field_78089_u = 64;
/*     */     
/*  50 */     this.wings = new ModelRenderer((Model)this);
/*  51 */     this.wings.func_78793_a(0.0F, 4.0F, 2.0F);
/*     */ 
/*     */     
/*  54 */     this.rightWing = new ModelRenderer((Model)this);
/*  55 */     this.rightWing.func_78793_a(-1.0F, -1.0F, 2.0F);
/*  56 */     this.wings.func_78792_a(this.rightWing);
/*  57 */     setRotationAngle(this.rightWing, 0.0F, 0.0F, 0.1745F);
/*     */ 
/*     */     
/*  60 */     this.rightWing1 = new ModelRenderer((Model)this);
/*  61 */     this.rightWing1.func_78793_a(-3.0F, -2.0F, 0.0F);
/*  62 */     this.rightWing.func_78792_a(this.rightWing1);
/*  63 */     setRotationAngle(this.rightWing1, 0.0F, 0.0F, 0.0436F);
/*  64 */     this.rightWing1.func_78784_a(40, 16).func_228303_a_(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, -0.01F, false);
/*     */     
/*  66 */     this.rightWing2 = new ModelRenderer((Model)this);
/*  67 */     this.rightWing2.func_78793_a(-7.0F, -3.0F, 0.0F);
/*  68 */     this.rightWing.func_78792_a(this.rightWing2);
/*  69 */     setRotationAngle(this.rightWing2, 0.0F, 0.0F, -0.1309F);
/*  70 */     this.rightWing2.func_78784_a(40, 16).func_228303_a_(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, -0.02F, false);
/*     */     
/*  72 */     this.rightWing3 = new ModelRenderer((Model)this);
/*  73 */     this.rightWing3.func_78793_a(-10.0F, -3.0F, 0.0F);
/*  74 */     this.rightWing.func_78792_a(this.rightWing3);
/*  75 */     this.rightWing3.func_78784_a(40, 16).func_228303_a_(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, -0.01F, false);
/*     */     
/*  77 */     this.rightWing4 = new ModelRenderer((Model)this);
/*  78 */     this.rightWing4.func_78793_a(-13.5F, -2.0F, 0.0F);
/*  79 */     this.rightWing.func_78792_a(this.rightWing4);
/*  80 */     setRotationAngle(this.rightWing4, 0.0F, 0.0F, -0.2182F);
/*  81 */     this.rightWing4.func_78784_a(40, 16).func_228303_a_(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, -0.02F, false);
/*     */     
/*  83 */     this.rightWing5 = new ModelRenderer((Model)this);
/*  84 */     this.rightWing5.func_78793_a(-16.5F, -2.0F, 0.0F);
/*  85 */     this.rightWing.func_78792_a(this.rightWing5);
/*  86 */     setRotationAngle(this.rightWing5, 0.0F, 0.0F, -0.1309F);
/*  87 */     this.rightWing5.func_78784_a(40, 16).func_228303_a_(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, -0.01F, false);
/*     */     
/*  89 */     this.rightWing6 = new ModelRenderer((Model)this);
/*  90 */     this.rightWing6.func_78793_a(-20.5F, -3.0F, 0.0F);
/*  91 */     this.rightWing.func_78792_a(this.rightWing6);
/*  92 */     setRotationAngle(this.rightWing6, 0.0F, 0.0F, -0.2618F);
/*  93 */     this.rightWing6.func_78784_a(40, 16).func_228303_a_(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, -0.02F, false);
/*     */     
/*  95 */     this.rightWing7 = new ModelRenderer((Model)this);
/*  96 */     this.rightWing7.func_78793_a(-23.5F, -3.0F, 0.0F);
/*  97 */     this.rightWing.func_78792_a(this.rightWing7);
/*  98 */     setRotationAngle(this.rightWing7, 0.0F, 0.0F, -0.1309F);
/*  99 */     this.rightWing7.func_78784_a(40, 16).func_228303_a_(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, -0.03F, false);
/*     */     
/* 101 */     this.rightWing8 = new ModelRenderer((Model)this);
/* 102 */     this.rightWing8.func_78793_a(-24.0F, -3.0F, 0.0F);
/* 103 */     this.rightWing.func_78792_a(this.rightWing8);
/* 104 */     setRotationAngle(this.rightWing8, 0.0F, 0.0F, 0.2618F);
/* 105 */     this.rightWing8.func_78784_a(40, 16).func_228303_a_(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, -0.01F, false);
/*     */     
/* 107 */     this.rightWingBone1 = new ModelRenderer((Model)this);
/* 108 */     this.rightWingBone1.func_78793_a(-3.0F, -1.0F, 0.0F);
/* 109 */     this.rightWing.func_78792_a(this.rightWingBone1);
/* 110 */     setRotationAngle(this.rightWingBone1, 0.0F, 0.0F, 2.0508F);
/* 111 */     this.rightWingBone1.func_78784_a(40, 16).func_228303_a_(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
/*     */     
/* 113 */     this.rightWingBone11 = new ModelRenderer((Model)this);
/* 114 */     this.rightWingBone11.func_78793_a(-1.5F, 2.0F, 0.0F);
/* 115 */     this.rightWingBone1.func_78792_a(this.rightWingBone11);
/* 116 */     setRotationAngle(this.rightWingBone11, 0.0F, 0.0F, 0.1309F);
/* 117 */     this.rightWingBone11.func_78784_a(40, 16).func_228303_a_(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, -0.01F, false);
/*     */     
/* 119 */     this.rightWingBone2 = new ModelRenderer((Model)this);
/* 120 */     this.rightWingBone2.func_78793_a(-12.0F, -5.0F, 0.0F);
/* 121 */     this.rightWing.func_78792_a(this.rightWingBone2);
/* 122 */     setRotationAngle(this.rightWingBone2, 0.0F, 0.0F, 1.3963F);
/* 123 */     this.rightWingBone2.func_78784_a(40, 16).func_228303_a_(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.015F, false);
/*     */     
/* 125 */     this.rightWingBone21 = new ModelRenderer((Model)this);
/* 126 */     this.rightWingBone21.func_78793_a(-2.0F, 0.0F, 0.0F);
/* 127 */     this.rightWingBone2.func_78792_a(this.rightWingBone21);
/* 128 */     setRotationAngle(this.rightWingBone21, 0.0F, 0.0F, 0.0873F);
/* 129 */     this.rightWingBone21.func_78784_a(40, 16).func_228303_a_(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.01F, false);
/*     */     
/* 131 */     this.rightWingBone3 = new ModelRenderer((Model)this);
/* 132 */     this.rightWingBone3.func_78793_a(-22.75F, -2.5F, 0.0F);
/* 133 */     this.rightWing.func_78792_a(this.rightWingBone3);
/* 134 */     setRotationAngle(this.rightWingBone3, 0.0F, 0.0F, 0.9599F);
/* 135 */     this.rightWingBone3.func_78784_a(40, 16).func_228303_a_(-3.15F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
/*     */     
/* 137 */     this.rightWingBone31 = new ModelRenderer((Model)this);
/* 138 */     this.rightWingBone31.func_78793_a(-1.5F, -2.0F, 0.0F);
/* 139 */     this.rightWingBone3.func_78792_a(this.rightWingBone31);
/* 140 */     setRotationAngle(this.rightWingBone31, 0.0F, 0.0F, 0.1309F);
/* 141 */     this.rightWingBone31.func_78784_a(40, 16).func_228303_a_(-3.15F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, -0.02F, false);
/*     */     
/* 143 */     this.leftWing = new ModelRenderer((Model)this);
/* 144 */     this.leftWing.func_78793_a(1.0F, -2.0F, 2.0F);
/* 145 */     this.wings.func_78792_a(this.leftWing);
/* 146 */     setRotationAngle(this.leftWing, 0.0F, 3.1416F, -0.1745F);
/*     */ 
/*     */     
/* 149 */     this.leftWing1 = new ModelRenderer((Model)this);
/* 150 */     this.leftWing1.func_78793_a(-2.8264F, -1.0152F, 0.0F);
/* 151 */     this.leftWing.func_78792_a(this.leftWing1);
/* 152 */     setRotationAngle(this.leftWing1, 0.0F, 0.0F, 0.0436F);
/* 153 */     this.leftWing1.func_78784_a(40, 16).func_228303_a_(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, -0.01F, true);
/*     */     
/* 155 */     this.leftWing2 = new ModelRenderer((Model)this);
/* 156 */     this.leftWing2.func_78793_a(-6.8264F, -2.0152F, 0.0F);
/* 157 */     this.leftWing.func_78792_a(this.leftWing2);
/* 158 */     setRotationAngle(this.leftWing2, 0.0F, 0.0F, -0.1309F);
/* 159 */     this.leftWing2.func_78784_a(40, 16).func_228303_a_(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, -0.02F, true);
/*     */     
/* 161 */     this.leftWing3 = new ModelRenderer((Model)this);
/* 162 */     this.leftWing3.func_78793_a(-9.8264F, -2.0152F, 0.0F);
/* 163 */     this.leftWing.func_78792_a(this.leftWing3);
/* 164 */     this.leftWing3.func_78784_a(40, 16).func_228303_a_(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, -0.01F, true);
/*     */     
/* 166 */     this.leftWing4 = new ModelRenderer((Model)this);
/* 167 */     this.leftWing4.func_78793_a(-13.3264F, -1.0152F, 0.0F);
/* 168 */     this.leftWing.func_78792_a(this.leftWing4);
/* 169 */     setRotationAngle(this.leftWing4, 0.0F, 0.0F, -0.2182F);
/* 170 */     this.leftWing4.func_78784_a(40, 16).func_228303_a_(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, -0.02F, true);
/*     */     
/* 172 */     this.leftWing5 = new ModelRenderer((Model)this);
/* 173 */     this.leftWing5.func_78793_a(-16.3264F, -1.0152F, 0.0F);
/* 174 */     this.leftWing.func_78792_a(this.leftWing5);
/* 175 */     setRotationAngle(this.leftWing5, 0.0F, 0.0F, -0.1309F);
/* 176 */     this.leftWing5.func_78784_a(40, 16).func_228303_a_(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, -0.01F, true);
/*     */     
/* 178 */     this.leftWing6 = new ModelRenderer((Model)this);
/* 179 */     this.leftWing6.func_78793_a(-20.3264F, -2.0152F, 0.0F);
/* 180 */     this.leftWing.func_78792_a(this.leftWing6);
/* 181 */     setRotationAngle(this.leftWing6, 0.0F, 0.0F, -0.2618F);
/* 182 */     this.leftWing6.func_78784_a(40, 16).func_228303_a_(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, -0.02F, true);
/*     */     
/* 184 */     this.leftWing7 = new ModelRenderer((Model)this);
/* 185 */     this.leftWing7.func_78793_a(-23.3264F, -2.0152F, 0.0F);
/* 186 */     this.leftWing.func_78792_a(this.leftWing7);
/* 187 */     setRotationAngle(this.leftWing7, 0.0F, 0.0F, -0.1309F);
/* 188 */     this.leftWing7.func_78784_a(40, 16).func_228303_a_(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, -0.03F, true);
/*     */     
/* 190 */     this.leftWing8 = new ModelRenderer((Model)this);
/* 191 */     this.leftWing8.func_78793_a(-23.8264F, -2.0152F, 0.0F);
/* 192 */     this.leftWing.func_78792_a(this.leftWing8);
/* 193 */     setRotationAngle(this.leftWing8, 0.0F, 0.0F, 0.2618F);
/* 194 */     this.leftWing8.func_78784_a(40, 16).func_228303_a_(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, -0.01F, true);
/*     */     
/* 196 */     this.leftWingBone1 = new ModelRenderer((Model)this);
/* 197 */     this.leftWingBone1.func_78793_a(-2.8264F, -0.0152F, 0.0F);
/* 198 */     this.leftWing.func_78792_a(this.leftWingBone1);
/* 199 */     setRotationAngle(this.leftWingBone1, 0.0F, 0.0F, 2.0508F);
/* 200 */     this.leftWingBone1.func_78784_a(40, 16).func_228303_a_(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, true);
/*     */     
/* 202 */     this.leftWingBone11 = new ModelRenderer((Model)this);
/* 203 */     this.leftWingBone11.func_78793_a(-1.5F, 2.0F, 0.0F);
/* 204 */     this.leftWingBone1.func_78792_a(this.leftWingBone11);
/* 205 */     setRotationAngle(this.leftWingBone11, 0.0F, 0.0F, 0.1309F);
/* 206 */     this.leftWingBone11.func_78784_a(40, 16).func_228303_a_(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, -0.01F, true);
/*     */     
/* 208 */     this.leftWingBone2 = new ModelRenderer((Model)this);
/* 209 */     this.leftWingBone2.func_78793_a(-11.8264F, -4.0152F, 0.0F);
/* 210 */     this.leftWing.func_78792_a(this.leftWingBone2);
/* 211 */     setRotationAngle(this.leftWingBone2, 0.0F, 0.0F, 1.3963F);
/* 212 */     this.leftWingBone2.func_78784_a(40, 16).func_228303_a_(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.015F, true);
/*     */     
/* 214 */     this.leftWingBone21 = new ModelRenderer((Model)this);
/* 215 */     this.leftWingBone21.func_78793_a(-2.0F, 0.0F, 0.0F);
/* 216 */     this.leftWingBone2.func_78792_a(this.leftWingBone21);
/* 217 */     setRotationAngle(this.leftWingBone21, 0.0F, 0.0F, 0.0873F);
/* 218 */     this.leftWingBone21.func_78784_a(40, 16).func_228303_a_(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.01F, true);
/*     */     
/* 220 */     this.leftWingBone3 = new ModelRenderer((Model)this);
/* 221 */     this.leftWingBone3.func_78793_a(-22.5764F, -1.5152F, 0.0F);
/* 222 */     this.leftWing.func_78792_a(this.leftWingBone3);
/* 223 */     setRotationAngle(this.leftWingBone3, 0.0F, 0.0F, 0.9599F);
/* 224 */     this.leftWingBone3.func_78784_a(40, 16).func_228303_a_(-3.15F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, true);
/*     */     
/* 226 */     this.leftWingBone31 = new ModelRenderer((Model)this);
/* 227 */     this.leftWingBone31.func_78793_a(-1.5F, -2.0F, 0.0F);
/* 228 */     this.leftWingBone3.func_78792_a(this.leftWingBone31);
/* 229 */     setRotationAngle(this.leftWingBone31, 0.0F, 0.0F, 0.1309F);
/* 230 */     this.leftWingBone31.func_78784_a(40, 16).func_228303_a_(-3.15F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, -0.02F, true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 236 */     if (entity.func_213453_ef()) {
/* 237 */       this.wings.field_78795_f = 0.4F;
/* 238 */       this.wings.field_78797_d = 5.5F;
/* 239 */       this.wings.field_78798_e = 2.5F;
/*     */     } else {
/*     */       
/* 242 */       this.wings.field_78795_f = 0.0F;
/*     */     } 
/*     */     
/* 245 */     this.rightWing.field_78808_h = 0.5F + (float)Math.sin((((LivingEntity)entity).field_70173_aa * 0.2F)) / 4.0F;
/* 246 */     this.rightWing.field_78796_g = 0.2F + (float)Math.sin((((LivingEntity)entity).field_70173_aa * 0.2F)) / 2.0F;
/* 247 */     this.leftWing.field_78808_h = -0.5F - (float)Math.sin((((LivingEntity)entity).field_70173_aa * 0.2F)) / 4.0F;
/* 248 */     this.leftWing.field_78796_g = (float)(Math.toRadians(180.0D) - 0.20000000298023224D - Math.sin((((LivingEntity)entity).field_70173_aa * 0.2F)) / 2.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 254 */     this.wings.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 259 */     modelRenderer.field_78795_f = x;
/* 260 */     modelRenderer.field_78796_g = y;
/* 261 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\abilities\HanaWingsModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */