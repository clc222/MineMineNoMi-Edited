/*     */ package xyz.pixelatedw.mineminenomi.models.armors;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ 
/*     */ public class PearlArmorModel<T extends LivingEntity>
/*     */   extends BipedModel<T> {
/*     */   private final ModelRenderer leftArmGear;
/*     */   private final ModelRenderer leftElbowGear;
/*     */   private final ModelRenderer leftPunchGear;
/*     */   private final ModelRenderer rightArmGear;
/*     */   private final ModelRenderer rightElbowGear;
/*     */   private final ModelRenderer rightPunchGear;
/*     */   private final ModelRenderer leftLegGear;
/*     */   private final ModelRenderer leftKneeGear;
/*     */   private final ModelRenderer leftKneeGearBase;
/*     */   private final ModelRenderer rightLegGear;
/*     */   private final ModelRenderer rightKneeGear;
/*     */   private final ModelRenderer rightKneeGearBase;
/*     */   private final ModelRenderer bodyFace;
/*     */   private final ModelRenderer bodyBack;
/*     */   private final ModelRenderer headPeal;
/*     */   private EquipmentSlotType slotType;
/*     */   
/*     */   public PearlArmorModel(EquipmentSlotType slotType) {
/*  32 */     super(1.0F);
/*     */     
/*  34 */     this.slotType = slotType;
/*     */     
/*  36 */     this.field_78090_t = 64;
/*  37 */     this.field_78089_u = 64;
/*     */     
/*  39 */     this.leftArmGear = new ModelRenderer((Model)this);
/*  40 */     this.leftArmGear.func_78793_a(5.0F, 2.0F, 0.0F);
/*     */     
/*  42 */     this.leftElbowGear = new ModelRenderer((Model)this);
/*  43 */     this.leftElbowGear.func_78793_a(4.0F, 3.25F, 0.0F);
/*  44 */     this.leftArmGear.func_78792_a(this.leftElbowGear);
/*  45 */     this.leftElbowGear.func_78784_a(0, 7).func_228303_a_(-1.0F, -3.5F, -3.5F, 2.0F, 7.0F, 7.0F, 0.0F, false);
/*  46 */     this.leftElbowGear.func_78784_a(0, 0).func_228303_a_(1.0F, -1.5F, -1.5F, 1.0F, 3.0F, 3.0F, 0.0F, false);
/*  47 */     this.leftElbowGear.func_78784_a(0, 22).func_228303_a_(-5.1F, -0.75F, -2.0F, 4.0F, 1.0F, 4.0F, 0.1F, false);
/*     */     
/*  49 */     this.leftPunchGear = new ModelRenderer((Model)this);
/*  50 */     this.leftPunchGear.func_78793_a(1.0F, 11.0F, 0.0F);
/*  51 */     this.leftArmGear.func_78792_a(this.leftPunchGear);
/*  52 */     setRotationAngle(this.leftPunchGear, 0.0F, 0.0F, 1.5708F);
/*  53 */     this.leftPunchGear.func_78784_a(0, 7).func_228303_a_(-1.0F, -3.5F, -3.5F, 2.0F, 7.0F, 7.0F, 0.0F, false);
/*  54 */     this.leftPunchGear.func_78784_a(0, 0).func_228303_a_(1.0F, -1.5F, -1.5F, 1.0F, 3.0F, 3.0F, 0.0F, false);
/*     */     
/*  56 */     this.rightArmGear = new ModelRenderer((Model)this);
/*  57 */     this.rightArmGear.func_78793_a(-5.0F, 2.0F, 0.0F);
/*     */     
/*  59 */     this.rightElbowGear = new ModelRenderer((Model)this);
/*  60 */     this.rightElbowGear.func_78793_a(-4.0F, 3.25F, 0.0F);
/*  61 */     this.rightArmGear.func_78792_a(this.rightElbowGear);
/*  62 */     this.rightElbowGear.func_78784_a(0, 7).func_228303_a_(-1.0F, -3.5F, -3.5F, 2.0F, 7.0F, 7.0F, 0.0F, false);
/*  63 */     this.rightElbowGear.func_78784_a(0, 0).func_228303_a_(-2.0F, -1.5F, -1.5F, 1.0F, 3.0F, 3.0F, 0.0F, false);
/*  64 */     this.rightElbowGear.func_78784_a(0, 22).func_228303_a_(1.1F, -0.75F, -2.0F, 4.0F, 1.0F, 4.0F, 0.1F, false);
/*     */     
/*  66 */     this.rightPunchGear = new ModelRenderer((Model)this);
/*  67 */     this.rightPunchGear.func_78793_a(-1.0F, 11.0F, 0.0F);
/*  68 */     this.rightArmGear.func_78792_a(this.rightPunchGear);
/*  69 */     setRotationAngle(this.rightPunchGear, 0.0F, 0.0F, 1.5708F);
/*  70 */     this.rightPunchGear.func_78784_a(0, 7).func_228303_a_(-1.0F, -3.5F, -3.5F, 2.0F, 7.0F, 7.0F, 0.0F, false);
/*  71 */     this.rightPunchGear.func_78784_a(0, 0).func_228303_a_(1.0F, -1.5F, -1.5F, 1.0F, 3.0F, 3.0F, 0.0F, false);
/*     */     
/*  73 */     this.leftLegGear = new ModelRenderer((Model)this);
/*  74 */     this.leftLegGear.func_78793_a(1.9F, 12.0F, 0.0F);
/*     */     
/*  76 */     this.leftKneeGear = new ModelRenderer((Model)this);
/*  77 */     this.leftKneeGear.func_78793_a(-0.4F, 6.25F, -2.0F);
/*  78 */     this.leftLegGear.func_78792_a(this.leftKneeGear);
/*  79 */     this.leftKneeGear.func_78784_a(0, 22).func_228303_a_(-1.6F, -0.75F, 0.0F, 4.0F, 1.0F, 4.0F, 0.1F, false);
/*     */     
/*  81 */     this.leftKneeGearBase = new ModelRenderer((Model)this);
/*  82 */     this.leftKneeGearBase.func_78793_a(-1.0F, 0.75F, -0.25F);
/*  83 */     this.leftKneeGear.func_78792_a(this.leftKneeGearBase);
/*  84 */     this.leftKneeGearBase.func_78784_a(0, 28).func_228303_a_(-1.5F, -3.5F, -1.25F, 6.0F, 6.0F, 2.0F, 0.01F, false);
/*  85 */     this.leftKneeGearBase.func_78784_a(20, 25).func_228303_a_(0.0F, -2.0F, -2.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);
/*     */     
/*  87 */     this.rightLegGear = new ModelRenderer((Model)this);
/*  88 */     this.rightLegGear.func_78793_a(-1.9F, 12.0F, 0.0F);
/*     */     
/*  90 */     this.rightKneeGear = new ModelRenderer((Model)this);
/*  91 */     this.rightKneeGear.func_78793_a(-0.35F, 6.25F, -2.0F);
/*  92 */     this.rightLegGear.func_78792_a(this.rightKneeGear);
/*  93 */     this.rightKneeGear.func_78784_a(0, 22).func_228303_a_(-1.6F, -0.75F, 0.0F, 4.0F, 1.0F, 4.0F, 0.1F, false);
/*     */     
/*  95 */     this.rightKneeGearBase = new ModelRenderer((Model)this);
/*  96 */     this.rightKneeGearBase.func_78793_a(-1.0F, 0.75F, -0.25F);
/*  97 */     this.rightKneeGear.func_78792_a(this.rightKneeGearBase);
/*  98 */     this.rightKneeGearBase.func_78784_a(0, 28).func_228303_a_(-1.5F, -3.5F, -1.25F, 6.0F, 6.0F, 2.0F, 0.01F, false);
/*  99 */     this.rightKneeGearBase.func_78784_a(20, 25).func_228303_a_(0.0F, -2.0F, -2.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);
/*     */     
/* 101 */     this.bodyFace = new ModelRenderer((Model)this);
/* 102 */     this.bodyFace.func_78793_a(0.0F, 0.0F, 0.0F);
/* 103 */     this.bodyFace.func_78784_a(22, 1).func_228303_a_(-7.0F, -0.5F, -3.75F, 14.0F, 15.0F, 2.0F, 0.0F, false);
/* 104 */     this.bodyFace.func_78784_a(19, 19).func_228303_a_(-1.5F, 5.0F, -5.25F, 3.0F, 3.0F, 2.0F, 0.0F, false);
/* 105 */     this.bodyFace.func_78784_a(11, 1).func_228303_a_(5.0F, -0.5F, -2.0F, 1.0F, 1.0F, 4.0F, -0.01F, false);
/* 106 */     this.bodyFace.func_78784_a(11, 1).func_228303_a_(-6.0F, -0.5F, -2.0F, 1.0F, 1.0F, 4.0F, -0.01F, false);
/*     */     
/* 108 */     this.bodyBack = new ModelRenderer((Model)this);
/* 109 */     this.bodyBack.func_78793_a(0.0F, 0.0F, -0.5F);
/* 110 */     this.bodyBack.func_78784_a(22, 1).func_228303_a_(-7.0F, -0.5F, 2.25F, 14.0F, 15.0F, 2.0F, 0.0F, false);
/* 111 */     this.bodyBack.func_78784_a(19, 19).func_228303_a_(-1.5F, 5.0F, 3.75F, 3.0F, 3.0F, 2.0F, 0.0F, false);
/*     */     
/* 113 */     this.headPeal = new ModelRenderer((Model)this);
/* 114 */     this.headPeal.func_78793_a(0.0F, -2.0F, 0.0F);
/* 115 */     this.headPeal.func_78784_a(0, 37).func_228303_a_(-4.5F, -7.0F, -4.5F, 9.0F, 1.0F, 9.0F, 0.1F, false);
/* 116 */     this.headPeal.func_78784_a(0, 48).func_228303_a_(-4.5F, -13.0F, -4.5F, 9.0F, 6.0F, 9.0F, 0.0F, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 121 */     this.rightArmGear.func_217177_a(this.field_178723_h);
/* 122 */     this.leftArmGear.func_217177_a(this.field_178724_i);
/* 123 */     this.rightLegGear.func_217177_a(this.field_178721_j);
/* 124 */     this.leftLegGear.func_217177_a(this.field_178722_k);
/* 125 */     this.bodyFace.func_217177_a(this.field_78115_e);
/* 126 */     this.bodyBack.func_217177_a(this.field_78115_e);
/* 127 */     this.headPeal.func_217177_a(this.field_78116_c);
/*     */     
/* 129 */     if (this.slotType == EquipmentSlotType.HEAD) {
/* 130 */       this.headPeal.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/*     */     }
/* 132 */     else if (this.slotType == EquipmentSlotType.CHEST) {
/* 133 */       this.bodyFace.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/* 134 */       this.bodyBack.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/* 135 */       this.rightArmGear.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/* 136 */       this.leftArmGear.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/*     */     }
/* 138 */     else if (this.slotType == EquipmentSlotType.LEGS) {
/* 139 */       this.rightLegGear.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/* 140 */       this.leftLegGear.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 152 */     modelRenderer.field_78795_f = x;
/* 153 */     modelRenderer.field_78796_g = y;
/* 154 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\armors\PearlArmorModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */