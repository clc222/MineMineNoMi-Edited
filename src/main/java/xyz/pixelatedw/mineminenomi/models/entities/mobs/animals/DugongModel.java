/*     */ package xyz.pixelatedw.mineminenomi.models.entities.mobs.animals;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.renderer.entity.model.AgeableModel;
/*     */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*     */ import net.minecraft.client.renderer.entity.model.IHasArm;
/*     */ import net.minecraft.client.renderer.entity.model.IHasHead;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.HandSide;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.AbstractDugongEntity;
/*     */ 
/*     */ 
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class DugongModel<T extends AbstractDugongEntity>
/*     */   extends AgeableModel<T>
/*     */   implements IHasArm, IHasHead
/*     */ {
/*     */   private final ModelRenderer head;
/*     */   private final ModelRenderer snout;
/*     */   private final ModelRenderer headShell;
/*     */   private final ModelRenderer body;
/*     */   private final ModelRenderer shell;
/*     */   private final ModelRenderer lowerBody;
/*     */   private final ModelRenderer lowerBody2;
/*     */   
/*     */   public DugongModel() {
/*  37 */     this.field_78090_t = 100;
/*  38 */     this.field_78089_u = 50;
/*     */     
/*  40 */     this.head = new ModelRenderer((Model)this);
/*  41 */     this.head.func_78793_a(0.0F, 10.75F, -2.2F);
/*  42 */     this.head.func_78784_a(0, 9).func_228303_a_(-2.5F, -4.75F, -2.6F, 5.0F, 5.0F, 5.0F, 0.0F, false);
/*     */     
/*  44 */     this.snout = new ModelRenderer((Model)this);
/*  45 */     this.snout.func_78793_a(0.0F, -1.25F, -0.7F);
/*  46 */     this.head.func_78792_a(this.snout);
/*  47 */     this.snout.func_78784_a(0, 20).func_228303_a_(-1.5F, -1.0F, -3.0F, 3.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/*  49 */     this.headShell = new ModelRenderer((Model)this);
/*  50 */     this.headShell.func_78793_a(0.0F, -2.55F, 1.7F);
/*  51 */     this.head.func_78792_a(this.headShell);
/*  52 */     this.headShell.func_78784_a(0, 0).func_228303_a_(-3.0F, -2.5F, -2.0F, 6.0F, 5.0F, 3.0F, 0.0F, false);
/*     */     
/*  54 */     this.body = new ModelRenderer((Model)this);
/*  55 */     this.body.func_78793_a(0.0F, 15.0F, -2.0F);
/*  56 */     this.body.func_78784_a(21, 0).func_228303_a_(-4.0F, -4.0F, -2.5F, 8.0F, 8.0F, 5.0F, 0.0F, false);
/*     */     
/*  58 */     this.shell = new ModelRenderer((Model)this);
/*  59 */     this.shell.func_78793_a(0.0F, -4.0F, 4.0F);
/*  60 */     this.body.func_78792_a(this.shell);
/*  61 */     this.shell.func_78784_a(21, 25).func_228303_a_(-4.5F, -0.5F, -3.5F, 9.0F, 9.0F, 3.0F, 0.0F, false);
/*     */     
/*  63 */     this.lowerBody = new ModelRenderer((Model)this);
/*  64 */     this.lowerBody.func_78793_a(0.0F, 20.7189F, -0.9321F);
/*  65 */     setRotationAngle(this.lowerBody, 0.4363F, 0.0F, 0.0F);
/*  66 */     this.lowerBody.func_78784_a(21, 14).func_228303_a_(-3.0F, -3.0F, -2.0F, 6.0F, 5.0F, 4.0F, 0.0F, false);
/*     */     
/*  68 */     this.lowerBody2 = new ModelRenderer((Model)this);
/*  69 */     this.lowerBody2.func_78793_a(0.0F, 1.25F, 0.0F);
/*  70 */     this.lowerBody.func_78792_a(this.lowerBody2);
/*  71 */     setRotationAngle(this.lowerBody2, 0.6981F, 0.0F, 0.0F);
/*  72 */     this.lowerBody2.func_78784_a(0, 35).func_228303_a_(-3.0F, -0.75F, -2.0079F, 6.0F, 2.0F, 3.0F, -0.01F, false);
/*     */     
/*  74 */     this.tailBase = new ModelRenderer((Model)this);
/*  75 */     this.tailBase.func_78793_a(0.0F, 22.1689F, 1.8179F);
/*  76 */     setRotationAngle(this.tailBase, 1.5708F, 0.0F, 0.0F);
/*  77 */     this.tailBase.func_78784_a(48, 0).func_228303_a_(-3.0F, -2.0F, -2.0F, 6.0F, 4.0F, 4.0F, 0.01F, false);
/*     */     
/*  79 */     this.tail2 = new ModelRenderer((Model)this);
/*  80 */     this.tail2.func_78793_a(0.0F, 1.1491F, -0.3358F);
/*  81 */     this.tailBase.func_78792_a(this.tail2);
/*  82 */     setRotationAngle(this.tail2, 0.6981F, 0.0F, 0.0F);
/*  83 */     this.tail2.func_78784_a(48, 9).func_228303_a_(-2.5F, -0.0912F, -1.3767F, 5.0F, 3.0F, 3.0F, 0.0F, false);
/*     */     
/*  85 */     this.tail3 = new ModelRenderer((Model)this);
/*  86 */     this.tail3.func_78793_a(0.0F, 2.7088F, 0.1233F);
/*  87 */     this.tail2.func_78792_a(this.tail3);
/*  88 */     this.tail3.func_78784_a(48, 16).func_228303_a_(-2.0F, 0.0F, -1.0F, 4.0F, 2.0F, 2.0F, 0.0F, false);
/*     */     
/*  90 */     this.tail4 = new ModelRenderer((Model)this);
/*  91 */     this.tail4.func_78793_a(0.0F, 4.2169F, -1.2119F);
/*  92 */     this.tail3.func_78792_a(this.tail4);
/*  93 */     setRotationAngle(this.tail4, -2.9671F, 0.0F, 0.0F);
/*  94 */     this.tail4.func_78784_a(48, 21).func_228303_a_(-0.5F, 0.2856F, -2.0321F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/*  96 */     this.tailTip1 = new ModelRenderer((Model)this);
/*  97 */     this.tailTip1.func_78793_a(-1.5314F, -0.4396F, -0.3374F);
/*  98 */     this.tail4.func_78792_a(this.tailTip1);
/*  99 */     setRotationAngle(this.tailTip1, 0.0F, -0.2269F, 0.3491F);
/* 100 */     this.tailTip1.func_78784_a(48, 25).func_228303_a_(-1.4162F, 0.208F, -2.0917F, 3.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/* 102 */     this.tailTip2 = new ModelRenderer((Model)this);
/* 103 */     this.tailTip2.func_78793_a(1.5371F, -0.478F, -0.3502F);
/* 104 */     this.tail4.func_78792_a(this.tailTip2);
/* 105 */     setRotationAngle(this.tailTip2, 0.0F, 0.2269F, -0.3491F);
/* 106 */     this.tailTip2.func_78784_a(48, 25).func_228303_a_(-1.5838F, 0.208F, -2.0917F, 3.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/* 108 */     this.rightArm = new ModelRenderer((Model)this);
/* 109 */     this.rightArm.func_78793_a(-4.5F, 12.3F, -2.0F);
/* 110 */     this.rightArm.func_78784_a(0, 24).func_228303_a_(-1.5F, -1.0F, -1.5F, 2.0F, 7.0F, 3.0F, 0.0F, false);
/*     */     
/* 112 */     this.leftArm = new ModelRenderer((Model)this);
/* 113 */     this.leftArm.func_78793_a(4.5F, 12.3F, -2.0F);
/* 114 */     this.leftArm.func_78784_a(0, 24).func_228303_a_(-0.5F, -1.0F, -1.5F, 2.0F, 7.0F, 3.0F, 0.0F, true);
/*     */   }
/*     */   public final ModelRenderer tailBase; private final ModelRenderer tail2; private final ModelRenderer tail3; private final ModelRenderer tail4; private final ModelRenderer tailTip1; private final ModelRenderer tailTip2; public final ModelRenderer rightArm; public final ModelRenderer leftArm;
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 119 */     if (this.field_217114_e) {
/* 120 */       matrixStack.func_227862_a_(0.5F, 0.5F, 0.5F);
/* 121 */       matrixStack.func_227861_a_(0.0D, 1.5D, 0.0D);
/*     */     } 
/*     */     
/* 124 */     this.head.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 125 */     this.body.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 126 */     this.lowerBody.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 127 */     this.tailBase.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 128 */     this.rightArm.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 129 */     this.leftArm.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 134 */     this.head.field_78798_e = -2.2F;
/* 135 */     this.head.field_78797_d = 10.75F;
/* 136 */     this.head.field_78795_f = 0.0F;
/* 137 */     this.rightArm.field_78800_c = -4.5F;
/* 138 */     this.rightArm.field_78797_d = 12.3F;
/* 139 */     this.rightArm.field_78798_e = -1.5F;
/* 140 */     this.leftArm.field_78800_c = 4.5F;
/* 141 */     this.leftArm.field_78797_d = 12.3F;
/* 142 */     this.leftArm.field_78798_e = -1.5F;
/* 143 */     this.rightArm.field_78796_g = this.rightArm.field_78808_h = 0.0F;
/* 144 */     this.leftArm.field_78796_g = this.leftArm.field_78808_h = 0.0F;
/* 145 */     this.body.field_78798_e = -2.0F;
/* 146 */     this.lowerBody.field_78798_e = -0.9321F;
/* 147 */     this.lowerBody.field_78795_f = 0.4363F;
/* 148 */     this.tailBase.field_78798_e = 1.8179F;
/* 149 */     this.tailBase.field_78797_d = 22.1689F;
/* 150 */     this.tailBase.field_78795_f = 1.5708F;
/* 151 */     this.tail2.field_78795_f = 0.69F;
/*     */     
/* 153 */     this.rightArm.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.6662F + 3.1415927F) * 2.0F * limbSwingAmount * 0.5F;
/* 154 */     this.leftArm.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;
/*     */ 
/*     */     
/* 157 */     this.head.field_78795_f = headPitch * 0.017453292F;
/* 158 */     this.head.field_78796_g = netHeadYaw * 0.017453292F;
/*     */ 
/*     */     
/* 161 */     if (entity.isCheering()) {
/* 162 */       this.rightArm.field_78795_f = (float)Math.toRadians(180.0D) + MathHelper.func_76126_a((float)(ageInTicks * Math.PI * 0.30000001192092896D)) * 0.2F;
/* 163 */       this.leftArm.field_78795_f = (float)Math.toRadians(180.0D) + MathHelper.func_76126_a((float)(ageInTicks * Math.PI * 0.30000001192092896D)) * 0.2F;
/*     */     } 
/*     */ 
/*     */     
/* 167 */     if (entity.isResting()) {
/* 168 */       this.head.field_78795_f = (float)Math.toRadians(-90.0D);
/* 169 */       this.head.field_78798_e = -4.0F;
/* 170 */       this.head.field_78797_d = 9.0F;
/* 171 */       this.rightArm.field_78796_g = (float)Math.toRadians(90.0D);
/* 172 */       this.rightArm.field_78795_f = (float)Math.toRadians(-45.0D);
/* 173 */       this.rightArm.field_78798_e = -3.5F;
/* 174 */       this.leftArm.field_78796_g = (float)Math.toRadians(-90.0D);
/* 175 */       this.leftArm.field_78795_f = (float)Math.toRadians(-45.0D);
/* 176 */       this.leftArm.field_78798_e = -3.5F;
/* 177 */       this.lowerBody.field_78795_f = 0.0F;
/* 178 */       this.lowerBody.field_78798_e = -2.0F;
/* 179 */       this.tailBase.field_78795_f = 0.0F;
/* 180 */       this.tailBase.field_78797_d = 23.0F;
/* 181 */       this.tailBase.field_78798_e = -1.95F;
/* 182 */       this.tail2.field_78795_f = 0.0F;
/*     */     } 
/*     */ 
/*     */     
/* 186 */     if (entity.isTraining()) {
/* 187 */       float bodyMovement; float rightArmMovement; float leftArmMovement; switch (entity.getTrainingMode()) {
/*     */         case PUSHUPS:
/* 189 */           this.rightArm.field_78795_f = (float)Math.toRadians(-90.0D);
/* 190 */           this.leftArm.field_78795_f = (float)Math.toRadians(-90.0D);
/* 191 */           this.tailBase.field_78795_f = (float)Math.toRadians(30.0D);
/* 192 */           this.lowerBody.field_78795_f = (float)Math.toRadians(0.0D);
/* 193 */           bodyMovement = -MathHelper.func_76134_b(ageInTicks * 0.5F);
/* 194 */           this.lowerBody.field_78798_e = -2.0F;
/* 195 */           this.tailBase.field_78798_e = -1.0F;
/* 196 */           this.tailBase.field_78797_d = 24.0F;
/* 197 */           this.head.field_78798_e = -0.7F * (3.5F - bodyMovement);
/*     */           break;
/*     */         case SHADOW_BOXING:
/* 200 */           rightArmMovement = -MathHelper.func_76134_b(ageInTicks * 1.2F);
/* 201 */           this.rightArm.field_78798_e = -2.5F * (0.5F - rightArmMovement);
/* 202 */           this.rightArm.field_78795_f = (float)Math.toRadians(-90.0D);
/* 203 */           this.rightArm.field_78796_g = 0.0F;
/* 204 */           leftArmMovement = MathHelper.func_76134_b(ageInTicks * 1.2F);
/* 205 */           this.leftArm.field_78798_e = -2.5F * (0.5F - leftArmMovement);
/* 206 */           this.leftArm.field_78795_f = (float)Math.toRadians(-90.0D);
/* 207 */           this.leftArm.field_78796_g = 0.0F;
/*     */           break;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     } 
/* 215 */     if (entity.isHappy()) {
/* 216 */       this.tail2.field_78795_f = 0.4F * (0.7F + MathHelper.func_76134_b(ageInTicks * 0.4F));
/* 217 */       this.tail2.field_78808_h = this.tail2.field_78796_g = 0.0F;
/* 218 */       this.tail3.field_78795_f = 0.6F * this.tail2.field_78795_f;
/* 219 */       this.tail3.field_78808_h = this.tail3.field_78796_g = 0.0F;
/*     */     } else {
/*     */       
/* 222 */       this.tail2.field_78795_f = 0.69F;
/* 223 */       this.tail3.field_78795_f = (float)Math.toRadians(0.0D);
/*     */       
/* 225 */       this.tail2.field_78808_h = MathHelper.func_76134_b(limbSwing * 0.4F + 3.1415927F) * 2.0F * limbSwingAmount * 0.3F;
/* 226 */       this.tail3.field_78808_h = MathHelper.func_76134_b(limbSwing * 0.4F + 3.1415927F) * 2.0F * limbSwingAmount * 0.3F;
/*     */     } 
/*     */     
/* 229 */     setupAttackAnimation(entity, ageInTicks);
/* 230 */     RendererHelper.animationAngles((LivingEntity)entity, (EntityModel)this, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/*     */   }
/*     */   
/*     */   protected void setupAttackAnimation(T entity, float ageInTicks) {
/* 234 */     if (this.field_217112_c > 0.0F) {
/* 235 */       float f = this.field_217112_c;
/* 236 */       this.body.field_78796_g = MathHelper.func_76126_a(MathHelper.func_76129_c(f) * 6.2831855F) * 0.2F;
/* 237 */       this.rightArm.field_78796_g += this.body.field_78796_g;
/* 238 */       this.leftArm.field_78796_g += this.body.field_78796_g;
/* 239 */       this.leftArm.field_78795_f += this.body.field_78796_g;
/* 240 */       f = 1.0F - this.field_217112_c;
/* 241 */       f *= f;
/* 242 */       f *= f;
/* 243 */       f = 1.0F - f;
/* 244 */       float f1 = MathHelper.func_76126_a(f * 3.1415927F);
/* 245 */       float f2 = MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * -(this.head.field_78795_f - 0.7F) * 0.75F;
/* 246 */       this.rightArm.field_78795_f = (float)(this.rightArm.field_78795_f - f1 * 1.2D + f2);
/* 247 */       this.rightArm.field_78796_g += this.body.field_78796_g * 2.0F;
/* 248 */       this.rightArm.field_78808_h += MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * -0.4F;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setRotationAngle(ModelRenderer model, float x, float y, float z) {
/* 253 */     model.field_78795_f = x;
/* 254 */     model.field_78796_g = y;
/* 255 */     model.field_78808_h = z;
/*     */   }
/*     */ 
/*     */   
/*     */   public ModelRenderer func_205072_a() {
/* 260 */     return this.head;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_225599_a_(HandSide side, MatrixStack matrixStack) {
/* 265 */     getArm(side).func_228307_a_(matrixStack);
/* 266 */     matrixStack.func_227861_a_((side == HandSide.RIGHT) ? 0.03D : -0.03D, -0.2D, 0.0D);
/*     */   }
/*     */   
/*     */   protected ModelRenderer getArm(HandSide side) {
/* 270 */     return (side == HandSide.LEFT) ? this.leftArm : this.rightArm;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Iterable<ModelRenderer> func_225602_a_() {
/* 275 */     return (Iterable<ModelRenderer>)ImmutableList.of(this.head);
/*     */   }
/*     */ 
/*     */   
/*     */   protected Iterable<ModelRenderer> func_225600_b_() {
/* 280 */     return (Iterable<ModelRenderer>)ImmutableList.of(this.body, this.lowerBody, this.rightArm, this.leftArm, this.tailBase);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\entities\mobs\animals\DugongModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */