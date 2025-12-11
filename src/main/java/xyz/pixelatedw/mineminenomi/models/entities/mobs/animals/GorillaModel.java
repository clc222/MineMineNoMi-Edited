/*     */ package xyz.pixelatedw.mineminenomi.models.entities.mobs.animals;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*     */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*     */ import net.minecraft.client.renderer.entity.model.IHasArm;
/*     */ import net.minecraft.client.renderer.entity.model.IHasHead;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.HandSide;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.vector.Vector3f;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.AbstractGorillaEntity;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GorillaModel<E extends AbstractGorillaEntity>
/*     */   extends BipedModel<E>
/*     */   implements IHasArm, IHasHead
/*     */ {
/*     */   private final ModelRenderer rightArm;
/*     */   private final ModelRenderer rightArm2;
/*     */   private final ModelRenderer rightPalm;
/*     */   private final ModelRenderer leftArm;
/*     */   private final ModelRenderer leftArm2;
/*     */   private final ModelRenderer leftPalm;
/*     */   private final ModelRenderer rightLeg;
/*     */   private final ModelRenderer rightFeet1;
/*     */   
/*     */   public GorillaModel() {
/*  37 */     super(1.0F);
/*  38 */     this.field_78090_t = 64;
/*  39 */     this.field_78089_u = 64;
/*     */     
/*  41 */     this.rightArm = new ModelRenderer((Model)this);
/*  42 */     this.rightArm.func_78793_a(-2.0F, 7.0F, 2.0F);
/*  43 */     setRotationAngle(this.rightArm, 0.0F, 0.0F, 0.8727F);
/*  44 */     this.rightArm.func_78784_a(0, 0).func_228303_a_(-2.0F, 0.5F, -2.0F, 4.0F, 7.0F, 4.0F, 0.0F, false);
/*     */     
/*  46 */     this.rightArm2 = new ModelRenderer((Model)this);
/*  47 */     this.rightArm2.func_78793_a(0.0F, 6.75F, 0.0F);
/*  48 */     this.rightArm.func_78792_a(this.rightArm2);
/*  49 */     setRotationAngle(this.rightArm2, 0.0F, 0.0F, -0.1745F);
/*  50 */     this.rightArm2.func_78784_a(18, 0).func_228303_a_(-2.0F, 0.25F, -2.0F, 4.0F, 7.0F, 4.0F, -0.01F, false);
/*  51 */     this.rightArm2.func_78784_a(36, 0).func_228303_a_(-2.5F, 5.25F, -2.5F, 5.0F, 2.0F, 5.0F, 0.0F, false);
/*     */     
/*  53 */     this.rightPalm = new ModelRenderer((Model)this);
/*  54 */     this.rightPalm.func_78793_a(0.1771F, 7.7431F, 0.0F);
/*  55 */     this.rightArm2.func_78792_a(this.rightPalm);
/*  56 */     setRotationAngle(this.rightPalm, 0.0F, 0.0F, -0.2182F);
/*  57 */     this.rightPalm.func_78784_a(35, 9).func_228303_a_(-1.25F, -1.0F, -2.0F, 3.0F, 2.0F, 4.0F, 0.0F, false);
/*     */     
/*  59 */     this.leftArm = new ModelRenderer((Model)this);
/*  60 */     this.leftArm.func_78793_a(2.0F, 7.0F, 2.0F);
/*  61 */     setRotationAngle(this.leftArm, 0.0F, 0.0F, -0.8727F);
/*  62 */     this.leftArm.func_78784_a(0, 0).func_228303_a_(-2.0F, 0.5F, -2.0F, 4.0F, 7.0F, 4.0F, 0.0F, false);
/*     */     
/*  64 */     this.leftArm2 = new ModelRenderer((Model)this);
/*  65 */     this.leftArm2.func_78793_a(0.0F, 6.75F, 0.0F);
/*  66 */     this.leftArm.func_78792_a(this.leftArm2);
/*  67 */     setRotationAngle(this.leftArm2, 0.0F, 0.0F, 0.1745F);
/*  68 */     this.leftArm2.func_78784_a(18, 0).func_228303_a_(-2.0F, 0.25F, -2.0F, 4.0F, 7.0F, 4.0F, -0.01F, false);
/*  69 */     this.leftArm2.func_78784_a(36, 0).func_228303_a_(-2.5F, 5.25F, -2.5F, 5.0F, 2.0F, 5.0F, 0.0F, false);
/*     */     
/*  71 */     this.leftPalm = new ModelRenderer((Model)this);
/*  72 */     this.leftPalm.func_78793_a(-0.8229F, 7.7431F, 0.0F);
/*  73 */     this.leftArm2.func_78792_a(this.leftPalm);
/*  74 */     setRotationAngle(this.leftPalm, 0.0F, 0.0F, 0.2182F);
/*  75 */     this.leftPalm.func_78784_a(35, 9).func_228303_a_(-1.0F, -1.0F, -2.0F, 3.0F, 2.0F, 4.0F, 0.0F, false);
/*     */     
/*  77 */     this.rightLeg = new ModelRenderer((Model)this);
/*  78 */     this.rightLeg.func_78793_a(-3.5F, 18.8333F, 1.5F);
/*  79 */     setRotationAngle(this.rightLeg, 0.0F, 0.7854F, 0.0F);
/*  80 */     this.rightLeg.func_78784_a(39, 19).func_228303_a_(-0.2929F, 0.1667F, -0.25F, 2.0F, 5.0F, 2.0F, 0.0F, false);
/*     */     
/*  82 */     this.rightFeet1 = new ModelRenderer((Model)this);
/*  83 */     this.rightFeet1.func_78793_a(0.0F, 0.0F, 0.0F);
/*  84 */     this.rightLeg.func_78792_a(this.rightFeet1);
/*  85 */     setRotationAngle(this.rightFeet1, 0.0F, 0.1309F, 0.0F);
/*  86 */     this.rightFeet1.func_78784_a(38, 28).func_228303_a_(-0.5489F, 4.1667F, -2.5077F, 1.0F, 1.0F, 4.0F, -0.01F, false);
/*     */     
/*  88 */     this.rightFeet2 = new ModelRenderer((Model)this);
/*  89 */     this.rightFeet2.func_78793_a(0.0F, 0.0F, 0.0F);
/*  90 */     this.rightLeg.func_78792_a(this.rightFeet2);
/*  91 */     this.rightFeet2.func_78784_a(38, 28).func_228303_a_(0.4571F, 4.1667F, -2.5F, 1.0F, 1.0F, 4.0F, -0.01F, false);
/*     */     
/*  93 */     this.rightFeet3 = new ModelRenderer((Model)this);
/*  94 */     this.rightFeet3.func_78793_a(0.0F, 0.0F, -0.25F);
/*  95 */     this.rightLeg.func_78792_a(this.rightFeet3);
/*  96 */     setRotationAngle(this.rightFeet3, 0.0F, -0.3054F, 0.0F);
/*  97 */     this.rightFeet3.func_78784_a(49, 29).func_228303_a_(1.1744F, 4.1667F, -1.7126F, 1.0F, 1.0F, 3.0F, -0.01F, false);
/*     */     
/*  99 */     this.leftLeg = new ModelRenderer((Model)this);
/* 100 */     this.leftLeg.func_78793_a(2.5F, 18.8333F, 1.5F);
/* 101 */     setRotationAngle(this.leftLeg, 0.0F, -0.7854F, 0.0F);
/* 102 */     this.leftLeg.func_78784_a(39, 19).func_228303_a_(-1.0F, 0.1667F, -0.9571F, 2.0F, 5.0F, 2.0F, 0.0F, false);
/*     */     
/* 104 */     this.leftFeet1 = new ModelRenderer((Model)this);
/* 105 */     this.leftFeet1.func_78793_a(0.0F, 0.0F, 0.0F);
/* 106 */     this.leftLeg.func_78792_a(this.leftFeet1);
/* 107 */     setRotationAngle(this.leftFeet1, 0.0F, -0.1309F, 0.0F);
/* 108 */     this.leftFeet1.func_78784_a(38, 28).func_228303_a_(0.1577F, 4.1667F, -3.1511F, 1.0F, 1.0F, 4.0F, -0.01F, false);
/*     */     
/* 110 */     this.leftFeet2 = new ModelRenderer((Model)this);
/* 111 */     this.leftFeet2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 112 */     this.leftLeg.func_78792_a(this.leftFeet2);
/* 113 */     this.leftFeet2.func_78784_a(38, 28).func_228303_a_(-0.75F, 4.1667F, -3.1571F, 1.0F, 1.0F, 4.0F, -0.01F, false);
/*     */     
/* 115 */     this.leftFeet3 = new ModelRenderer((Model)this);
/* 116 */     this.leftFeet3.func_78793_a(0.0F, 0.0F, 0.0F);
/* 117 */     this.leftLeg.func_78792_a(this.leftFeet3);
/* 118 */     setRotationAngle(this.leftFeet3, 0.0F, 0.3054F, 0.0F);
/* 119 */     this.leftFeet3.func_78784_a(49, 29).func_228303_a_(-1.1874F, 4.1667F, -2.6744F, 1.0F, 1.0F, 3.0F, -0.01F, false);
/*     */     
/* 121 */     this.body = new ModelRenderer((Model)this);
/* 122 */     this.body.func_78793_a(0.0F, 12.25F, 1.5F);
/* 123 */     this.body.func_78784_a(0, 32).func_228303_a_(-5.5F, -3.25F, -5.5F, 11.0F, 3.0F, 10.0F, 0.0F, false);
/* 124 */     this.body.func_78784_a(0, 46).func_228303_a_(-6.0F, -0.25F, -6.0F, 12.0F, 7.0F, 11.0F, 0.0F, false);
/*     */     
/* 126 */     this.head = new ModelRenderer((Model)this);
/* 127 */     this.head.func_78793_a(0.0F, -5.25F, -0.25F);
/* 128 */     this.body.func_78792_a(this.head);
/* 129 */     this.head.func_78784_a(0, 18).func_228303_a_(-4.5F, -2.5F, -4.25F, 9.0F, 5.0F, 8.0F, 0.0F, false);
/*     */     
/* 131 */     this.field_178722_k = this.leftLeg;
/* 132 */     this.field_178721_j = this.rightLeg;
/* 133 */     this.field_178724_i = this.leftArm;
/* 134 */     this.field_178723_h = this.rightArm;
/* 135 */     this.field_78116_c = this.head;
/* 136 */     this.field_78115_e = this.body;
/*     */   }
/*     */   private final ModelRenderer rightFeet2; private final ModelRenderer rightFeet3; private final ModelRenderer leftLeg; private final ModelRenderer leftFeet1; private final ModelRenderer leftFeet2; private final ModelRenderer leftFeet3; private final ModelRenderer body;
/*     */   private final ModelRenderer head;
/*     */   
/*     */   public void setupAnim(E entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 142 */     this.rightArm.field_78800_c = -2.0F;
/* 143 */     this.rightArm.field_78797_d = 7.0F;
/* 144 */     this.rightArm.field_78798_e = 2.0F;
/* 145 */     this.leftArm.field_78800_c = 2.0F;
/* 146 */     this.leftArm.field_78797_d = 7.0F;
/* 147 */     this.leftArm.field_78798_e = 2.0F;
/* 148 */     this.rightArm.field_78795_f = 0.0F;
/* 149 */     this.rightArm.field_78796_g = 0.0F;
/* 150 */     this.rightArm.field_78808_h = 0.8727F;
/* 151 */     this.leftArm.field_78795_f = 0.0F;
/* 152 */     this.leftArm.field_78796_g = 0.0F;
/* 153 */     this.leftArm.field_78808_h = -0.8727F;
/* 154 */     this.rightLeg.field_78795_f = 0.0F;
/* 155 */     this.rightLeg.field_78796_g = 0.7854F;
/* 156 */     this.rightLeg.field_78808_h = 0.0F;
/* 157 */     this.leftLeg.field_78795_f = 0.0F;
/* 158 */     this.leftLeg.field_78796_g = -0.7854F;
/* 159 */     this.leftLeg.field_78808_h = 0.0F;
/* 160 */     this.body.field_78796_g = -0.0F;
/*     */     
/* 162 */     boolean isMoving = ((entity.func_213322_ci()).field_72450_a != 0.0D || (entity.func_213322_ci()).field_72449_c != 0.0D);
/* 163 */     if (isMoving) {
/* 164 */       this.rightLeg.field_78796_g = 0.3F;
/* 165 */       this.leftLeg.field_78796_g = -0.3F;
/*     */     } 
/*     */     
/* 168 */     float f = 1.0F;
/* 169 */     this.rightArm.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.6F + 3.1415927F) * 2.0F * limbSwingAmount * 0.5F / f;
/* 170 */     this.leftArm.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.6F) * 2.0F * limbSwingAmount * 0.5F / f;
/* 171 */     this.rightLeg.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.8F) * 1.4F * limbSwingAmount / f;
/* 172 */     this.leftLeg.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.8F + 3.1415927F) * 1.4F * limbSwingAmount / f;
/*     */     
/* 174 */     setupAttackAnimation(entity, ageInTicks);
/* 175 */     RendererHelper.animationAngles((LivingEntity)entity, (EntityModel)this, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setupAttackAnimation(E entity, float ageInTicks) {
/* 180 */     if (this.field_217112_c <= 0.0F) {
/*     */       return;
/*     */     }
/* 183 */     HandSide handside = getAttackArm(entity);
/* 184 */     ModelRenderer arm = func_187074_a(handside);
/* 185 */     float f = this.field_217112_c;
/* 186 */     this.body.field_78796_g = MathHelper.func_76126_a(MathHelper.func_76129_c(f) * 6.2831855F) * 0.2F;
/* 187 */     if (handside == HandSide.LEFT) {
/* 188 */       this.body.field_78796_g *= -1.0F;
/*     */     }
/* 190 */     this.rightArm.field_78796_g += this.body.field_78796_g;
/* 191 */     this.leftArm.field_78796_g += this.body.field_78796_g;
/* 192 */     this.leftArm.field_78795_f += this.body.field_78796_g;
/* 193 */     f = 1.0F - this.field_217112_c;
/* 194 */     f *= f;
/* 195 */     f *= f;
/* 196 */     f = 1.0F - f;
/* 197 */     float f1 = MathHelper.func_76126_a(f * 3.1415927F);
/* 198 */     float f2 = MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * -(this.head.field_78795_f - 0.7F) * 0.75F;
/* 199 */     arm.field_78795_f = (float)(arm.field_78795_f - f1 * 1.2D + f2);
/* 200 */     arm.field_78796_g += this.body.field_78796_g * 2.0F;
/* 201 */     arm.field_78808_h += MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * -0.4F;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 206 */     this.rightArm.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 207 */     this.leftArm.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 208 */     this.rightLeg.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 209 */     this.leftLeg.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 210 */     this.body.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */   }
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 214 */     modelRenderer.field_78795_f = x;
/* 215 */     modelRenderer.field_78796_g = y;
/* 216 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */ 
/*     */   
/*     */   protected ModelRenderer func_187074_a(HandSide side) {
/* 221 */     return (side == HandSide.LEFT) ? this.leftArm : this.rightArm;
/*     */   }
/*     */ 
/*     */   
/*     */   protected HandSide getAttackArm(E entity) {
/* 226 */     HandSide handside = entity.func_184591_cq();
/* 227 */     return (((AbstractGorillaEntity)entity).field_184622_au == Hand.MAIN_HAND) ? handside : handside.func_188468_a();
/*     */   }
/*     */ 
/*     */   
/*     */   public ModelRenderer func_205072_a() {
/* 232 */     return this.head;
/*     */   }
/*     */ 
/*     */   
/*     */   public Iterable<ModelRenderer> func_225600_b_() {
/* 237 */     return (Iterable<ModelRenderer>)ImmutableList.of(this.body, this.rightArm, this.leftArm, this.rightLeg, this.leftLeg);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_225599_a_(HandSide side, MatrixStack matrixStack) {
/* 242 */     ModelRenderer arm = func_187074_a(side);
/* 243 */     arm.func_228307_a_(matrixStack);
/* 244 */     matrixStack.func_227863_a_(Vector3f.field_229183_f_.func_229187_a_((side == HandSide.RIGHT) ? -20.0F : 20.0F));
/* 245 */     matrixStack.func_227861_a_((side == HandSide.RIGHT) ? -0.12D : 0.12D, 0.32D, 0.15D);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\entities\mobs\animals\GorillaModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */