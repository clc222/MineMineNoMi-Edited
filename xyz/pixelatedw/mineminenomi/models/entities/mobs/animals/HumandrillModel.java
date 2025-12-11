/*     */ package xyz.pixelatedw.mineminenomi.models.entities.mobs.animals;
/*     */ 
/*     */ import com.google.common.collect.ImmutableSet;
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
/*     */ import net.minecraft.util.HandSide;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class HumandrillModel<T extends LivingEntity>
/*     */   extends BipedModel<T>
/*     */   implements IHasArm, IHasHead
/*     */ {
/*     */   private final ModelRenderer head;
/*     */   private final ModelRenderer snout;
/*     */   private final ModelRenderer forehead;
/*     */   private final ModelRenderer leftArm;
/*     */   private final ModelRenderer leftArmArmor;
/*     */   private final ModelRenderer leftArmArmorPlate;
/*     */   private final ModelRenderer lowerLeftArm;
/*     */   private final ModelRenderer rightArm;
/*     */   private final ModelRenderer rightArmArmor;
/*     */   private final ModelRenderer rightArmArmorPlate;
/*     */   private final ModelRenderer lowerRightArm;
/*     */   
/*     */   public HumandrillModel() {
/*  41 */     super(1.0F);
/*  42 */     this.field_78090_t = 128;
/*  43 */     this.field_78089_u = 128;
/*     */     
/*  45 */     this.head = new ModelRenderer((Model)this);
/*  46 */     this.head.func_78793_a(0.0F, 23.0F, -1.0F);
/*  47 */     this.head.func_78784_a(16, 0).func_228303_a_(-3.0625F, -36.125F, -5.4375F, 6.0F, 3.0F, 6.0F, 0.0F, false);
/*     */     
/*  49 */     this.snout = new ModelRenderer((Model)this);
/*  50 */     this.snout.func_78793_a(0.0F, -33.8377F, -2.9299F);
/*  51 */     this.head.func_78792_a(this.snout);
/*  52 */     setRotationAngle(this.snout, 0.1309F, 0.0F, 0.0F);
/*  53 */     this.snout.func_78784_a(0, 16).func_228303_a_(-2.5F, -2.0F, -3.0F, 5.0F, 4.0F, 6.0F, 0.0F, false);
/*     */     
/*  55 */     this.forehead = new ModelRenderer((Model)this);
/*  56 */     this.forehead.func_78793_a(0.0F, -35.4375F, -2.5938F);
/*  57 */     this.head.func_78792_a(this.forehead);
/*  58 */     setRotationAngle(this.forehead, -0.1309F, 0.0F, 0.0F);
/*  59 */     this.forehead.func_78784_a(0, 0).func_228303_a_(-1.5F, -5.25F, -2.0313F, 3.0F, 2.0F, 4.0F, 0.0F, false);
/*  60 */     this.forehead.func_78784_a(0, 7).func_228303_a_(-2.5F, -3.25F, -2.4688F, 5.0F, 3.0F, 5.0F, 0.0F, false);
/*     */     
/*  62 */     this.leftArm = new ModelRenderer((Model)this);
/*  63 */     this.leftArm.func_78793_a(9.0F, -6.0F, 2.25F);
/*  64 */     this.leftArm.func_78784_a(96, 4).func_228303_a_(-0.7187F, -4.208F, -3.9572F, 6.0F, 14.0F, 8.0F, 0.0F, true);
/*     */     
/*  66 */     this.leftArmArmor = new ModelRenderer((Model)this);
/*  67 */     this.leftArmArmor.func_78793_a(2.2813F, 4.3159F, 0.0567F);
/*  68 */     this.leftArm.func_78792_a(this.leftArmArmor);
/*  69 */     this.leftArmArmor.func_78784_a(75, 20).func_228303_a_(-3.0F, -1.5F, -4.0F, 6.0F, 3.0F, 8.0F, 0.3F, true);
/*     */     
/*  71 */     this.leftArmArmorPlate = new ModelRenderer((Model)this);
/*  72 */     this.leftArmArmorPlate.func_78793_a(3.75F, -0.4659F, -0.1552F);
/*  73 */     this.leftArmArmor.func_78792_a(this.leftArmArmorPlate);
/*  74 */     setRotationAngle(this.leftArmArmorPlate, 0.3054F, 0.0F, 0.0F);
/*  75 */     this.leftArmArmorPlate.func_78784_a(85, 35).func_228303_a_(-0.5F, -6.0F, -5.0F, 1.0F, 12.0F, 10.0F, 0.0F, true);
/*     */     
/*  77 */     this.lowerLeftArm = new ModelRenderer((Model)this);
/*  78 */     this.lowerLeftArm.func_78793_a(2.7187F, 9.1562F, -0.25F);
/*  79 */     this.leftArm.func_78792_a(this.lowerLeftArm);
/*  80 */     this.lowerLeftArm.func_78784_a(99, 27).func_228303_a_(-3.0F, -1.2157F, -2.8679F, 5.0F, 10.0F, 6.0F, 0.0F, true);
/*     */     
/*  82 */     this.rightArm = new ModelRenderer((Model)this);
/*  83 */     this.rightArm.func_78793_a(-9.0F, -6.0F, 2.25F);
/*  84 */     this.rightArm.func_78784_a(96, 4).func_228303_a_(-5.2813F, -4.208F, -3.9572F, 6.0F, 14.0F, 8.0F, 0.0F, false);
/*     */     
/*  86 */     this.rightArmArmor = new ModelRenderer((Model)this);
/*  87 */     this.rightArmArmor.func_78793_a(-2.2813F, 4.3159F, 0.0567F);
/*  88 */     this.rightArm.func_78792_a(this.rightArmArmor);
/*  89 */     this.rightArmArmor.func_78784_a(75, 20).func_228303_a_(-3.0F, -1.5F, -4.0F, 6.0F, 3.0F, 8.0F, 0.3F, false);
/*     */     
/*  91 */     this.rightArmArmorPlate = new ModelRenderer((Model)this);
/*  92 */     this.rightArmArmorPlate.func_78793_a(-3.75F, -0.4659F, -0.1552F);
/*  93 */     this.rightArmArmor.func_78792_a(this.rightArmArmorPlate);
/*  94 */     setRotationAngle(this.rightArmArmorPlate, 0.3054F, 0.0F, 0.0F);
/*  95 */     this.rightArmArmorPlate.func_78784_a(85, 35).func_228303_a_(-0.5F, -6.0F, -5.0F, 1.0F, 12.0F, 10.0F, 0.0F, false);
/*     */     
/*  97 */     this.lowerRightArm = new ModelRenderer((Model)this);
/*  98 */     this.lowerRightArm.func_78793_a(-2.7187F, 9.1562F, -0.25F);
/*  99 */     this.rightArm.func_78792_a(this.lowerRightArm);
/* 100 */     this.lowerRightArm.func_78784_a(99, 27).func_228303_a_(-2.0F, -1.2157F, -2.8679F, 5.0F, 10.0F, 6.0F, 0.0F, false);
/*     */     
/* 102 */     this.leftLeg = new ModelRenderer((Model)this);
/* 103 */     this.leftLeg.func_78793_a(3.875F, 4.3546F, 1.5498F);
/* 104 */     setRotationAngle(this.leftLeg, -0.0436F, 0.0F, 0.0F);
/* 105 */     this.leftLeg.func_78784_a(3, 35).func_228303_a_(-3.0F, 0.0229F, -2.9117F, 6.0F, 8.0F, 6.0F, 0.0F, true);
/*     */     
/* 107 */     this.lowerLeftLeg = new ModelRenderer((Model)this);
/* 108 */     this.lowerLeftLeg.func_78793_a(0.0F, 6.7361F, -0.3401F);
/* 109 */     this.leftLeg.func_78792_a(this.lowerLeftLeg);
/* 110 */     setRotationAngle(this.lowerLeftLeg, 0.0873F, 0.0F, 0.0F);
/* 111 */     this.lowerLeftLeg.func_78784_a(7, 50).func_228303_a_(-2.0F, 0.0153F, -2.0871F, 4.0F, 12.0F, 4.0F, 0.0F, true);
/*     */     
/* 113 */     this.leftLegArmor = new ModelRenderer((Model)this);
/* 114 */     this.leftLegArmor.func_78793_a(0.0F, 4.3278F, -0.0871F);
/* 115 */     this.lowerLeftLeg.func_78792_a(this.leftLegArmor);
/* 116 */     this.leftLegArmor.func_78784_a(12, 27).func_228303_a_(-2.0F, -1.0F, -2.0F, 4.0F, 2.0F, 4.0F, 0.3F, true);
/*     */     
/* 118 */     this.leftLegArmorPlate = new ModelRenderer((Model)this);
/* 119 */     this.leftLegArmorPlate.func_78793_a(-6.0E-4F, -0.0054F, -2.8125F);
/* 120 */     this.leftLegArmor.func_78792_a(this.leftLegArmorPlate);
/* 121 */     setRotationAngle(this.leftLegArmorPlate, 0.0F, 0.0F, -0.0873F);
/* 122 */     this.leftLegArmorPlate.func_78784_a(1, 28).func_228303_a_(-2.0F, -2.0F, -0.5F, 4.0F, 4.0F, 1.0F, 0.0F, true);
/*     */     
/* 124 */     this.leftFoot = new ModelRenderer((Model)this);
/* 125 */     this.leftFoot.func_78793_a(-0.0625F, 11.7406F, 0.0398F);
/* 126 */     this.lowerLeftLeg.func_78792_a(this.leftFoot);
/* 127 */     setRotationAngle(this.leftFoot, -0.0436F, 0.0F, 0.0F);
/* 128 */     this.leftFoot.func_78784_a(3, 67).func_228303_a_(-2.5F, -0.9618F, -4.3742F, 5.0F, 2.0F, 7.0F, 0.0F, true);
/*     */     
/* 130 */     this.rightLeg = new ModelRenderer((Model)this);
/* 131 */     this.rightLeg.func_78793_a(-3.875F, 4.3546F, 1.5498F);
/* 132 */     setRotationAngle(this.rightLeg, -0.0436F, 0.0F, 0.0F);
/* 133 */     this.rightLeg.func_78784_a(3, 35).func_228303_a_(-3.0F, 0.0229F, -2.9117F, 6.0F, 8.0F, 6.0F, 0.0F, false);
/*     */     
/* 135 */     this.lowerRightLeg = new ModelRenderer((Model)this);
/* 136 */     this.lowerRightLeg.func_78793_a(0.0F, 6.7361F, -0.3401F);
/* 137 */     this.rightLeg.func_78792_a(this.lowerRightLeg);
/* 138 */     setRotationAngle(this.lowerRightLeg, 0.0873F, 0.0F, 0.0F);
/* 139 */     this.lowerRightLeg.func_78784_a(7, 50).func_228303_a_(-2.0F, 0.0153F, -2.0871F, 4.0F, 12.0F, 4.0F, 0.0F, false);
/*     */     
/* 141 */     this.rightLegArmor = new ModelRenderer((Model)this);
/* 142 */     this.rightLegArmor.func_78793_a(0.0F, 4.3278F, -0.0871F);
/* 143 */     this.lowerRightLeg.func_78792_a(this.rightLegArmor);
/* 144 */     this.rightLegArmor.func_78784_a(12, 27).func_228303_a_(-2.0F, -1.0F, -2.0F, 4.0F, 2.0F, 4.0F, 0.3F, false);
/*     */     
/* 146 */     this.rightLegArmorPlate = new ModelRenderer((Model)this);
/* 147 */     this.rightLegArmorPlate.func_78793_a(6.0E-4F, -0.0054F, -2.8125F);
/* 148 */     this.rightLegArmor.func_78792_a(this.rightLegArmorPlate);
/* 149 */     setRotationAngle(this.rightLegArmorPlate, 0.0F, 0.0F, 0.0873F);
/* 150 */     this.rightLegArmorPlate.func_78784_a(1, 28).func_228303_a_(-2.0F, -2.0F, -0.5F, 4.0F, 4.0F, 1.0F, 0.0F, false);
/*     */     
/* 152 */     this.rightFoot = new ModelRenderer((Model)this);
/* 153 */     this.rightFoot.func_78793_a(0.0625F, 11.7406F, 0.0398F);
/* 154 */     this.lowerRightLeg.func_78792_a(this.rightFoot);
/* 155 */     setRotationAngle(this.rightFoot, -0.0436F, 0.0F, 0.0F);
/* 156 */     this.rightFoot.func_78784_a(3, 67).func_228303_a_(-2.5F, -0.9618F, -4.3742F, 5.0F, 2.0F, 7.0F, 0.0F, false);
/*     */     
/* 158 */     this.body = new ModelRenderer((Model)this);
/* 159 */     this.body.func_78793_a(0.5F, -2.2344F, 0.7188F);
/* 160 */     setRotationAngle(this.body, 0.0873F, 0.0F, 0.0F);
/* 161 */     this.body.func_78784_a(30, 26).func_228303_a_(-9.0F, -8.8906F, -2.6563F, 17.0F, 9.0F, 8.0F, 0.0F, false);
/* 162 */     this.body.func_78784_a(28, 0).func_228303_a_(-8.0F, -7.4531F, -4.7188F, 15.0F, 13.0F, 12.0F, 0.0F, false);
/* 163 */     this.body.func_78784_a(31, 44).func_228303_a_(-7.0F, 4.5469F, -3.7188F, 13.0F, 3.0F, 10.0F, 0.0F, false);
/*     */     
/* 165 */     this.field_78116_c = this.head;
/* 166 */     this.field_78115_e = this.body;
/* 167 */     this.field_178724_i = this.leftArm;
/* 168 */     this.field_178723_h = this.rightArm;
/* 169 */     this.field_178722_k = this.leftLeg;
/* 170 */     this.field_178721_j = this.rightLeg;
/*     */   }
/*     */   private final ModelRenderer leftLeg; private final ModelRenderer lowerLeftLeg; private final ModelRenderer leftLegArmor; private final ModelRenderer leftLegArmorPlate; private final ModelRenderer leftFoot; private final ModelRenderer rightLeg; private final ModelRenderer lowerRightLeg; private final ModelRenderer rightLegArmor; private final ModelRenderer rightLegArmorPlate; private final ModelRenderer rightFoot;
/*     */   private final ModelRenderer body;
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 176 */     this.head.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/* 177 */     this.body.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/* 178 */     this.leftLeg.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/* 179 */     this.leftArm.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/* 180 */     this.rightArm.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/* 181 */     this.rightLeg.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 187 */     this.rightArm.field_78800_c = -9.0F;
/* 188 */     this.rightArm.field_78797_d = -6.0F;
/* 189 */     this.rightArm.field_78798_e = 2.25F;
/* 190 */     this.leftArm.field_78800_c = 9.0F;
/* 191 */     this.leftArm.field_78797_d = -6.0F;
/* 192 */     this.leftArm.field_78798_e = 2.25F;
/* 193 */     this.rightArm.field_78795_f = 0.0F;
/* 194 */     this.rightArm.field_78796_g = 0.0F;
/* 195 */     this.rightArm.field_78808_h = 0.0F;
/* 196 */     this.leftArm.field_78795_f = 0.0F;
/* 197 */     this.leftArm.field_78796_g = 0.0F;
/* 198 */     this.leftArm.field_78808_h = 0.0F;
/* 199 */     this.rightLeg.field_78795_f = 0.0F;
/* 200 */     this.rightLeg.field_78796_g = 0.0F;
/* 201 */     this.rightLeg.field_78808_h = 0.0F;
/* 202 */     this.leftLeg.field_78795_f = 0.0F;
/* 203 */     this.leftLeg.field_78796_g = 0.0F;
/* 204 */     this.leftLeg.field_78808_h = 0.0F;
/*     */ 
/*     */     
/* 207 */     this.rightArm.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.6662F + 3.1415927F) * 0.8F * limbSwingAmount * 0.5F / 1.0F;
/* 208 */     this.leftArm.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.6662F) * 0.8F * limbSwingAmount * 0.5F / 1.0F;
/* 209 */     this.rightLeg.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.6662F) * 0.7F * limbSwingAmount / 1.0F;
/* 210 */     this.leftLeg.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.6662F + 3.1415927F) * 0.7F * limbSwingAmount / 1.0F;
/* 211 */     if (!entity.func_184614_ca().func_190926_b()) {
/* 212 */       this.rightArm.field_78796_g -= -0.15F;
/*     */     }
/*     */     
/* 215 */     func_230486_a_((LivingEntity)entity, ageInTicks);
/* 216 */     RendererHelper.animationAngles((LivingEntity)entity, (EntityModel)this, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/*     */   }
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 220 */     modelRenderer.field_78795_f = x;
/* 221 */     modelRenderer.field_78796_g = y;
/* 222 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */ 
/*     */   
/*     */   protected ModelRenderer func_187074_a(HandSide pSide) {
/* 227 */     return (pSide == HandSide.LEFT) ? this.leftArm : this.rightArm;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_225599_a_(HandSide side, MatrixStack matrixStack) {
/* 232 */     func_187074_a(side).func_228307_a_(matrixStack);
/* 233 */     matrixStack.func_227861_a_((side == HandSide.RIGHT) ? -0.1D : 0.2D, 0.4D, -0.1D);
/*     */   }
/*     */ 
/*     */   
/*     */   public ModelRenderer func_205072_a() {
/* 238 */     return this.head;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Iterable<ModelRenderer> func_225602_a_() {
/* 243 */     return (Iterable<ModelRenderer>)ImmutableSet.of(this.head, this.forehead);
/*     */   }
/*     */ 
/*     */   
/*     */   protected Iterable<ModelRenderer> func_225600_b_() {
/* 248 */     return (Iterable<ModelRenderer>)ImmutableSet.of(this.body, this.leftArm, this.lowerLeftArm, this.rightArm, this.lowerRightArm, this.leftLeg, (Object[])new ModelRenderer[] { this.lowerLeftLeg, this.rightLeg, this.lowerRightLeg });
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\entities\mobs\animals\HumandrillModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */