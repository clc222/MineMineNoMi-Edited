/*     */ package xyz.pixelatedw.mineminenomi.models.morphs;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.renderer.entity.model.IHasArm;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.HandSide;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.vector.Vector3f;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphModel;
/*     */ 
/*     */ public class BisonWalkModel<T extends LivingEntity>
/*     */   extends MorphModel<T> implements IHasArm {
/*     */   private final ModelRenderer head;
/*     */   private final ModelRenderer snout;
/*     */   private final ModelRenderer rightHorn;
/*     */   private final ModelRenderer rightHorn2;
/*     */   private final ModelRenderer leftHorn;
/*     */   private final ModelRenderer leftHorn2;
/*     */   private final ModelRenderer body;
/*     */   private final ModelRenderer hunch;
/*     */   private final ModelRenderer lowerBack;
/*     */   private final ModelRenderer tail;
/*     */   private final ModelRenderer leftFrontLeg;
/*     */   private final ModelRenderer leftFrontLeg2;
/*     */   private final ModelRenderer leftFrontNail1;
/*     */   private final ModelRenderer leftFrontNail2;
/*     */   private final ModelRenderer rightFrontLeg;
/*     */   private final ModelRenderer rightFrontLeg2;
/*     */   private final ModelRenderer rightFrontNail1;
/*     */   private final ModelRenderer rightFrontNail2;
/*     */   private final ModelRenderer leftRearLeg;
/*     */   private final ModelRenderer leftRearNail1;
/*     */   private final ModelRenderer leftRearNail2;
/*     */   private final ModelRenderer rightRearLeg;
/*     */   private final ModelRenderer rightRearNail1;
/*     */   private final ModelRenderer rightRearNail2;
/*     */   
/*     */   public BisonWalkModel() {
/*  43 */     super(1.0F);
/*  44 */     this.field_78090_t = 128;
/*  45 */     this.field_78089_u = 64;
/*     */     
/*  47 */     this.head = new ModelRenderer((Model)this);
/*  48 */     this.head.func_78793_a(-2.5F, 8.0F, -8.5F);
/*  49 */     setRotationAngle(this.head, 0.0873F, 0.0F, 0.0F);
/*  50 */     this.head.func_78784_a(13, 29).func_228303_a_(0.0F, 0.3861F, -7.0606F, 5.0F, 5.0F, 5.0F, 0.0F, false);
/*     */     
/*  52 */     this.snout = new ModelRenderer((Model)this);
/*  53 */     this.snout.func_78793_a(0.5F, 2.0F, -2.5F);
/*  54 */     this.head.func_78792_a(this.snout);
/*  55 */     setRotationAngle(this.snout, 0.0873F, 0.0F, 0.0F);
/*  56 */     this.snout.func_78784_a(13, 40).func_228303_a_(0.0F, -0.2307F, -7.0674F, 4.0F, 3.0F, 3.0F, 0.0F, false);
/*     */     
/*  58 */     this.rightHorn = new ModelRenderer((Model)this);
/*  59 */     this.rightHorn.func_78793_a(-0.5F, -1.0F, 3.5F);
/*  60 */     this.head.func_78792_a(this.rightHorn);
/*  61 */     setRotationAngle(this.rightHorn, 0.0F, 0.0F, 0.733F);
/*  62 */     this.rightHorn.func_78784_a(115, 0).func_228303_a_(0.2584F, 0.2869F, -7.0606F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/*  64 */     this.rightHorn2 = new ModelRenderer((Model)this);
/*  65 */     this.rightHorn2.func_78793_a(0.3809F, -1.1029F, 0.0436F);
/*  66 */     this.rightHorn.func_78792_a(this.rightHorn2);
/*  67 */     setRotationAngle(this.rightHorn2, 0.0F, 0.0F, 1.3352F);
/*  68 */     this.rightHorn2.func_78784_a(122, 0).func_228303_a_(0.2584F, -0.3313F, -7.1041F, 2.0F, 1.0F, 1.0F, 0.01F, false);
/*     */     
/*  70 */     this.leftHorn = new ModelRenderer((Model)this);
/*  71 */     this.leftHorn.func_78793_a(5.5F, -0.751F, 3.4782F);
/*  72 */     this.head.func_78792_a(this.leftHorn);
/*  73 */     setRotationAngle(this.leftHorn, 0.0F, 0.0F, -0.733F);
/*  74 */     this.leftHorn.func_78784_a(115, 0).func_228303_a_(-2.0911F, 0.1011F, -7.0606F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/*  76 */     this.leftHorn2 = new ModelRenderer((Model)this);
/*  77 */     this.leftHorn2.func_78793_a(0.3457F, -4.1516F, 0.0F);
/*  78 */     this.leftHorn.func_78792_a(this.leftHorn2);
/*  79 */     setRotationAngle(this.leftHorn2, 0.0F, 0.0F, -1.3352F);
/*  80 */     this.leftHorn2.func_78784_a(122, 0).func_228303_a_(-5.1393F, -0.1842F, -7.0606F, 2.0F, 1.0F, 1.0F, 0.01F, false);
/*     */     
/*  82 */     this.body = new ModelRenderer((Model)this);
/*  83 */     this.body.func_78793_a(-4.5F, 7.0F, -3.5F);
/*  84 */     this.body.func_78784_a(0, 0).func_228303_a_(0.0F, 1.0F, -7.0F, 9.0F, 7.0F, 11.0F, 0.0F, false);
/*     */     
/*  86 */     this.hunch = new ModelRenderer((Model)this);
/*  87 */     this.hunch.func_78793_a(0.5F, -5.0F, 3.5F);
/*  88 */     this.body.func_78792_a(this.hunch);
/*  89 */     setRotationAngle(this.hunch, -0.5934F, 0.0F, 0.0F);
/*  90 */     this.hunch.func_78784_a(41, 0).func_228303_a_(0.0F, 4.7434F, -5.2441F, 8.0F, 6.0F, 9.0F, 0.0F, false);
/*     */     
/*  92 */     this.lowerBack = new ModelRenderer((Model)this);
/*  93 */     this.lowerBack.func_78793_a(0.5F, 0.0F, 10.5F);
/*  94 */     this.body.func_78792_a(this.lowerBack);
/*  95 */     this.lowerBack.func_78784_a(76, 0).func_228303_a_(0.0F, 1.0F, -7.0F, 8.0F, 7.0F, 8.0F, 0.0F, false);
/*     */     
/*  97 */     this.tail = new ModelRenderer((Model)this);
/*  98 */     this.tail.func_78793_a(4.0F, 4.0F, 0.5F);
/*  99 */     this.lowerBack.func_78792_a(this.tail);
/* 100 */     setRotationAngle(this.tail, 0.3491F, 0.0F, 0.0F);
/* 101 */     this.tail.func_78784_a(110, 0).func_228303_a_(-0.5F, 3.0E-4F, 0.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
/*     */     
/* 103 */     this.leftFrontLeg = new ModelRenderer((Model)this);
/* 104 */     this.leftFrontLeg.func_78793_a(2.75F, 14.0F, -7.0F);
/* 105 */     this.leftFrontLeg.func_78784_a(0, 29).func_228303_a_(-1.5F, 0.0F, -2.0F, 3.0F, 6.0F, 3.0F, 0.0F, false);
/*     */     
/* 107 */     this.leftFrontLeg2 = new ModelRenderer((Model)this);
/* 108 */     this.leftFrontLeg2.func_78793_a(0.0F, 6.0F, 0.0F);
/* 109 */     this.leftFrontLeg.func_78792_a(this.leftFrontLeg2);
/* 110 */     this.leftFrontLeg2.func_78784_a(0, 22).func_228303_a_(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
/*     */     
/* 112 */     this.leftFrontNail1 = new ModelRenderer((Model)this);
/* 113 */     this.leftFrontNail1.func_78793_a(0.0F, 3.1F, 6.5F);
/* 114 */     this.leftFrontLeg2.func_78792_a(this.leftFrontNail1);
/* 115 */     setRotationAngle(this.leftFrontNail1, -0.1211F, -0.4883F, -0.0394F);
/* 116 */     this.leftFrontNail1.func_78784_a(0, 55).func_228303_a_(-3.7841F, 0.7469F, -7.1365F, 1.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 118 */     this.leftFrontNail2 = new ModelRenderer((Model)this);
/* 119 */     this.leftFrontNail2.func_78793_a(0.0F, 3.1F, 6.5F);
/* 120 */     this.leftFrontLeg2.func_78792_a(this.leftFrontNail2);
/* 121 */     setRotationAngle(this.leftFrontNail2, -0.1211F, 0.4883F, 0.0394F);
/* 122 */     this.leftFrontNail2.func_78784_a(0, 55).func_228303_a_(2.7841F, 0.7469F, -7.1365F, 1.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 124 */     this.rightFrontLeg = new ModelRenderer((Model)this);
/* 125 */     this.rightFrontLeg.func_78793_a(-2.75F, 14.0F, -7.0F);
/* 126 */     this.rightFrontLeg.func_78784_a(0, 29).func_228303_a_(-1.5F, 0.0F, -2.0F, 3.0F, 6.0F, 3.0F, 0.0F, false);
/*     */     
/* 128 */     this.rightFrontLeg2 = new ModelRenderer((Model)this);
/* 129 */     this.rightFrontLeg2.func_78793_a(0.0F, 6.0F, 0.0F);
/* 130 */     this.rightFrontLeg.func_78792_a(this.rightFrontLeg2);
/* 131 */     this.rightFrontLeg2.func_78784_a(0, 22).func_228303_a_(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
/*     */     
/* 133 */     this.rightFrontNail1 = new ModelRenderer((Model)this);
/* 134 */     this.rightFrontNail1.func_78793_a(0.0F, 3.1F, 6.5F);
/* 135 */     this.rightFrontLeg2.func_78792_a(this.rightFrontNail1);
/* 136 */     setRotationAngle(this.rightFrontNail1, -0.1211F, -0.4883F, -0.0394F);
/* 137 */     this.rightFrontNail1.func_78784_a(0, 55).func_228303_a_(-3.7841F, 0.7469F, -7.1365F, 1.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 139 */     this.rightFrontNail2 = new ModelRenderer((Model)this);
/* 140 */     this.rightFrontNail2.func_78793_a(0.0F, 3.1F, 6.5F);
/* 141 */     this.rightFrontLeg2.func_78792_a(this.rightFrontNail2);
/* 142 */     setRotationAngle(this.rightFrontNail2, -0.1211F, 0.4883F, 0.0394F);
/* 143 */     this.rightFrontNail2.func_78784_a(0, 55).func_228303_a_(2.7841F, 0.7469F, -7.1365F, 1.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 145 */     this.leftRearLeg = new ModelRenderer((Model)this);
/* 146 */     this.leftRearLeg.func_78793_a(3.0F, 14.0F, 7.0F);
/* 147 */     this.leftRearLeg.func_78784_a(0, 39).func_228303_a_(-1.25F, 0.0F, -1.25F, 2.0F, 10.0F, 2.0F, 0.0F, false);
/*     */     
/* 149 */     this.leftRearNail1 = new ModelRenderer((Model)this);
/* 150 */     this.leftRearNail1.func_78793_a(0.0F, 9.1F, 6.5F);
/* 151 */     this.leftRearLeg.func_78792_a(this.leftRearNail1);
/* 152 */     setRotationAngle(this.leftRearNail1, -0.1211F, 0.4883F, 0.0394F);
/* 153 */     this.leftRearNail1.func_78784_a(0, 52).func_228303_a_(2.6808F, 0.7975F, -7.4708F, 1.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 155 */     this.leftRearNail2 = new ModelRenderer((Model)this);
/* 156 */     this.leftRearNail2.func_78793_a(0.0F, 9.1F, 6.5F);
/* 157 */     this.leftRearLeg.func_78792_a(this.leftRearNail2);
/* 158 */     setRotationAngle(this.leftRearNail2, -0.1211F, -0.4883F, -0.0394F);
/* 159 */     this.leftRearNail2.func_78784_a(0, 52).func_228303_a_(-4.122F, 0.7496F, -7.2405F, 1.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 161 */     this.rightRearLeg = new ModelRenderer((Model)this);
/* 162 */     this.rightRearLeg.func_78793_a(-2.75F, 14.0F, 6.75F);
/* 163 */     this.rightRearLeg.func_78784_a(0, 39).func_228303_a_(-1.0F, 0.0F, -1.0F, 2.0F, 10.0F, 2.0F, 0.0F, false);
/*     */     
/* 165 */     this.rightRearNail1 = new ModelRenderer((Model)this);
/* 166 */     this.rightRearNail1.func_78793_a(0.0F, 9.1F, 6.5F);
/* 167 */     this.rightRearLeg.func_78792_a(this.rightRearNail1);
/* 168 */     setRotationAngle(this.rightRearNail1, -0.1211F, 0.4883F, 0.0394F);
/* 169 */     this.rightRearNail1.func_78784_a(0, 52).func_228303_a_(2.7841F, 0.7469F, -7.1365F, 1.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 171 */     this.rightRearNail2 = new ModelRenderer((Model)this);
/* 172 */     this.rightRearNail2.func_78793_a(0.0F, 9.1F, 6.5F);
/* 173 */     this.rightRearLeg.func_78792_a(this.rightRearNail2);
/* 174 */     setRotationAngle(this.rightRearNail2, -0.1211F, -0.4883F, -0.0394F);
/* 175 */     this.rightRearNail2.func_78784_a(0, 52).func_228303_a_(-3.7841F, 0.7469F, -7.1365F, 1.0F, 1.0F, 1.0F, 0.0F, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 181 */     this.head.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 182 */     this.body.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 183 */     this.leftFrontLeg.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 184 */     this.rightFrontLeg.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 185 */     this.leftRearLeg.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 186 */     this.rightRearLeg.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 193 */     this.rightFrontLeg.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.9F) * 0.5F * limbSwingAmount;
/* 194 */     this.leftFrontLeg.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.9F) * 0.5F * limbSwingAmount;
/* 195 */     this.rightRearLeg.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.9F + 3.1415927F) * 0.5F * limbSwingAmount;
/* 196 */     this.leftRearLeg.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.9F + 3.1415927F) * 0.5F * limbSwingAmount;
/* 197 */     if (entity.func_70051_ag()) {
/* 198 */       this.tail.field_78795_f = 1.2F + MathHelper.func_76134_b(limbSwing * 0.6662F) * 0.2F * limbSwingAmount;
/*     */     }
/*     */     
/* 201 */     this.field_217112_c = ((LivingEntity)entity).field_70733_aJ;
/* 202 */     if (this.field_217112_c > 0.0F) {
/*     */       
/* 204 */       this.head.field_78796_g += this.body.field_78796_g;
/* 205 */       float f1 = 1.0F - this.field_217112_c;
/* 206 */       f1 *= f1;
/* 207 */       f1 *= f1;
/* 208 */       f1 = 1.0F - f1;
/* 209 */       float f2 = MathHelper.func_76126_a(f1 * 3.1415927F);
/* 210 */       float f3 = MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * -(this.head.field_78795_f - 0.1F) * 0.15F;
/* 211 */       this.head.field_78795_f = (float)(this.head.field_78795_f - f2 * 1.5D + f3);
/* 212 */       this.head.field_78808_h -= MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * -0.4F;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225599_a_(HandSide side, MatrixStack matrixStack) {
/* 219 */     this.head.func_228307_a_(matrixStack);
/* 220 */     matrixStack.func_227863_a_(Vector3f.field_229183_f_.func_229187_a_(90.0F));
/* 221 */     matrixStack.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(260.0F));
/* 222 */     matrixStack.func_227861_a_(0.3D, 0.0D, -0.03D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*     */ 
/*     */   
/*     */   public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 233 */     modelRenderer.field_78795_f = x;
/* 234 */     modelRenderer.field_78796_g = y;
/* 235 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\morphs\BisonWalkModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */