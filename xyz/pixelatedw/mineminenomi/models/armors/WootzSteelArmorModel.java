/*     */ package xyz.pixelatedw.mineminenomi.models.armors;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.GunArrayAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.MH5Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ 
/*     */ public class WootzSteelArmorModel<T extends LivingEntity>
/*     */   extends BipedModel<T> {
/*     */   private final ModelRenderer armor;
/*     */   private final ModelRenderer rightArmArmor;
/*     */   private final ModelRenderer leftArmArmor;
/*     */   private final ModelRenderer bodyArmor;
/*     */   private final ModelRenderer rightShoulderBase;
/*     */   private final ModelRenderer rightShoulder1;
/*     */   private final ModelRenderer rightShoulder2;
/*     */   private final ModelRenderer rightShoulderGuns;
/*     */   private final ModelRenderer rightShoulderGun1;
/*     */   private final ModelRenderer rightShoulderGun2;
/*     */   private final ModelRenderer leftShoulderBase;
/*     */   private final ModelRenderer leftShoulder1;
/*     */   private final ModelRenderer leftShoulder2;
/*     */   private final ModelRenderer leftShoulderGuns;
/*     */   private final ModelRenderer leftShoulderGun2;
/*     */   private final ModelRenderer leftShoulderGun1;
/*     */   private boolean isChargingMH5 = false;
/*     */   
/*     */   public WootzSteelArmorModel() {
/*  39 */     super(1.0F);
/*  40 */     this.field_78090_t = 64;
/*  41 */     this.field_78089_u = 32;
/*     */     
/*  43 */     this.armor = new ModelRenderer((Model)this);
/*  44 */     this.armor.func_78793_a(0.0F, 0.25F, 0.0F);
/*     */     
/*  46 */     this.rightArmArmor = new ModelRenderer((Model)this);
/*  47 */     this.rightArmArmor.func_78793_a(-6.0F, 1.75F, 0.0F);
/*     */     
/*  49 */     this.rightArmArmor.func_78784_a(34, 0).func_228303_a_(-3.5F, -2.0F, -2.5F, 5.0F, 10.0F, 5.0F, 0.0F, false);
/*     */     
/*  51 */     this.leftArmArmor = new ModelRenderer((Model)this);
/*  52 */     this.leftArmArmor.func_78793_a(6.0F, 1.75F, 0.0F);
/*     */     
/*  54 */     this.leftArmArmor.func_78784_a(34, 0).func_228303_a_(-1.5F, -2.0F, -2.5F, 5.0F, 10.0F, 5.0F, 0.0F, false);
/*     */     
/*  56 */     this.bodyArmor = new ModelRenderer((Model)this);
/*  57 */     this.bodyArmor.func_78793_a(0.0F, -0.25F, 0.0F);
/*  58 */     this.armor.func_78792_a(this.bodyArmor);
/*  59 */     this.bodyArmor.func_78784_a(0, 0).func_228303_a_(-5.5F, 0.0F, -3.0F, 11.0F, 11.0F, 6.0F, 0.0F, false);
/*     */     
/*  61 */     this.rightShoulderBase = new ModelRenderer((Model)this);
/*  62 */     this.rightShoulderBase.func_78793_a(-8.0F, 0.25F, 3.4F);
/*  63 */     this.armor.func_78792_a(this.rightShoulderBase);
/*  64 */     setRotateAngle(this.rightShoulderBase, 0.0F, 0.0456F, -0.5236F);
/*  65 */     this.rightShoulderBase.func_78784_a(21, 17).func_228303_a_(-4.0F, 0.0F, -7.0F, 8.0F, 1.0F, 8.0F, 0.0F, false);
/*     */     
/*  67 */     this.rightShoulder1 = new ModelRenderer((Model)this);
/*  68 */     this.rightShoulder1.func_78793_a(0.0F, 0.0F, 0.0F);
/*  69 */     this.rightShoulderBase.func_78792_a(this.rightShoulder1);
/*  70 */     this.rightShoulder1.func_78784_a(0, 24).func_228303_a_(-3.5F, -1.0F, -6.5F, 7.0F, 1.0F, 7.0F, 0.0F, false);
/*     */     
/*  72 */     this.rightShoulder2 = new ModelRenderer((Model)this);
/*  73 */     this.rightShoulder2.func_78793_a(8.0F, -0.5F, -3.4F);
/*  74 */     this.rightShoulder1.func_78792_a(this.rightShoulder2);
/*  75 */     this.rightShoulder2.func_78784_a(0, 17).func_228303_a_(-11.0531F, -1.5F, -2.6F, 6.0F, 1.0F, 6.0F, 0.0F, false);
/*     */     
/*  77 */     this.rightShoulderGuns = new ModelRenderer((Model)this);
/*  78 */     this.rightShoulderGuns.func_78793_a(-6.0F, -0.25F, 0.0F);
/*  79 */     this.armor.func_78792_a(this.rightShoulderGuns);
/*  80 */     this.rightShoulderGuns.func_78784_a(0, 0).func_228303_a_(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, false);
/*     */     
/*  82 */     this.rightShoulderGun1 = new ModelRenderer((Model)this);
/*  83 */     this.rightShoulderGun1.func_78793_a(0.0F, 0.0F, 0.0F);
/*  84 */     this.rightShoulderGuns.func_78792_a(this.rightShoulderGun1);
/*  85 */     this.rightShoulderGun1.func_78784_a(48, 16).func_228303_a_(-1.5F, -2.3F, -4.0F, 3.0F, 3.0F, 5.0F, 0.0F, false);
/*     */     
/*  87 */     this.rightShoulderGun2 = new ModelRenderer((Model)this);
/*  88 */     this.rightShoulderGun2.func_78793_a(-3.9F, 1.0F, 0.0F);
/*  89 */     this.rightShoulderGuns.func_78792_a(this.rightShoulderGun2);
/*  90 */     this.rightShoulderGun2.func_78784_a(48, 16).func_228303_a_(-1.5F, -2.3F, -4.0F, 3.0F, 3.0F, 5.0F, 0.0F, false);
/*     */     
/*  92 */     this.leftShoulderBase = new ModelRenderer((Model)this);
/*  93 */     this.leftShoulderBase.func_78793_a(8.0F, 0.25F, 3.4F);
/*  94 */     this.armor.func_78792_a(this.leftShoulderBase);
/*  95 */     setRotateAngle(this.leftShoulderBase, 0.0F, 0.0456F, 0.5236F);
/*  96 */     this.leftShoulderBase.func_78784_a(21, 17).func_228303_a_(-4.0F, 0.0F, -7.0F, 8.0F, 1.0F, 8.0F, 0.0F, false);
/*     */     
/*  98 */     this.leftShoulder1 = new ModelRenderer((Model)this);
/*  99 */     this.leftShoulder1.func_78793_a(0.0F, 0.0F, 0.0F);
/* 100 */     this.leftShoulderBase.func_78792_a(this.leftShoulder1);
/* 101 */     this.leftShoulder1.func_78784_a(0, 24).func_228303_a_(-3.5F, -1.0F, -6.5F, 7.0F, 1.0F, 7.0F, 0.0F, false);
/*     */     
/* 103 */     this.leftShoulder2 = new ModelRenderer((Model)this);
/* 104 */     this.leftShoulder2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 105 */     this.leftShoulder1.func_78792_a(this.leftShoulder2);
/* 106 */     this.leftShoulder2.func_78784_a(0, 17).func_228303_a_(-3.0F, -2.0F, -6.0F, 6.0F, 1.0F, 6.0F, 0.0F, false);
/*     */     
/* 108 */     this.leftShoulderGuns = new ModelRenderer((Model)this);
/* 109 */     this.leftShoulderGuns.func_78793_a(6.0F, -0.25F, 0.0F);
/* 110 */     this.armor.func_78792_a(this.leftShoulderGuns);
/* 111 */     this.leftShoulderGuns.func_78784_a(0, 0).func_228303_a_(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, false);
/*     */     
/* 113 */     this.leftShoulderGun2 = new ModelRenderer((Model)this);
/* 114 */     this.leftShoulderGun2.func_78793_a(3.9F, 1.0F, 0.0F);
/* 115 */     this.leftShoulderGuns.func_78792_a(this.leftShoulderGun2);
/* 116 */     this.leftShoulderGun2.func_78784_a(48, 16).func_228303_a_(-1.5F, -2.3F, -4.0F, 3.0F, 3.0F, 5.0F, 0.0F, false);
/*     */     
/* 118 */     this.leftShoulderGun1 = new ModelRenderer((Model)this);
/* 119 */     this.leftShoulderGun1.func_78793_a(0.0F, 0.0F, 0.0F);
/* 120 */     this.leftShoulderGuns.func_78792_a(this.leftShoulderGun1);
/* 121 */     this.leftShoulderGun1.func_78784_a(48, 16).func_228303_a_(-1.5F, -2.3F, -4.0F, 3.0F, 3.0F, 5.0F, 0.0F, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 126 */     this.armor.func_217177_a(this.field_78115_e);
/* 127 */     this.leftArmArmor.func_217177_a(this.field_178724_i);
/* 128 */     this.rightArmArmor.func_217177_a(this.field_178723_h);
/*     */     
/* 130 */     this.armor.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
/* 131 */     this.rightArmArmor.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
/* 132 */     this.leftArmArmor.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/* 134 */     if (this.isChargingMH5) {
/* 135 */       matrixStack.func_227860_a_();
/* 136 */       matrixStack.func_227862_a_(1.5F, 1.5F, 1.5F);
/* 137 */       matrixStack.func_227861_a_(0.0D, -0.1D, 0.2D);
/* 138 */       this.leftShoulderBase.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
/* 139 */       matrixStack.func_227865_b_();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setState(T entity) {
/* 144 */     boolean hasGunsOut = false;
/*     */     
/* 146 */     IAbilityData aprops = AbilityDataCapability.get((LivingEntity)entity);
/*     */     
/* 148 */     IAbility gunArray = aprops.getEquippedAbility(GunArrayAbility.INSTANCE);
/* 149 */     if (gunArray != null) {
/* 150 */       boolean isActive = ((Boolean)gunArray.getComponent(ModAbilityKeys.CONTINUOUS).map(comp -> Boolean.valueOf(comp.isContinuous())).orElse(Boolean.valueOf(false))).booleanValue();
/* 151 */       if (isActive) {
/* 152 */         hasGunsOut = true;
/*     */       }
/*     */     } 
/*     */     
/* 156 */     IAbility mh5 = aprops.getEquippedAbility(MH5Ability.INSTANCE);
/* 157 */     if (mh5 != null) {
/* 158 */       boolean isActive = ((Boolean)mh5.getComponent(ModAbilityKeys.CHARGE).map(comp -> Boolean.valueOf(comp.isCharging())).orElse(Boolean.valueOf(false))).booleanValue();
/* 159 */       if (isActive) {
/* 160 */         this.isChargingMH5 = true;
/* 161 */         hasGunsOut = false;
/*     */       } 
/*     */     } 
/*     */     
/* 165 */     if (this.isChargingMH5) {
/* 166 */       this.leftShoulderBase.field_78795_f = (float)Math.toRadians(80.0D);
/* 167 */       this.leftShoulderBase.field_78800_c = 2.0F;
/* 168 */       this.leftShoulderBase.field_78798_e = -7.0F;
/*     */     } 
/*     */     
/* 171 */     if (hasGunsOut) {
/* 172 */       this.leftShoulderBase.field_78795_f = (float)Math.toRadians(-70.0D);
/* 173 */       this.rightShoulderBase.field_78795_f = (float)Math.toRadians(-70.0D);
/*     */     } else {
/*     */       
/* 176 */       this.leftShoulderGuns.field_78806_j = false;
/* 177 */       this.rightShoulderGuns.field_78806_j = false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
/* 182 */     model.field_78795_f = x;
/* 183 */     model.field_78796_g = y;
/* 184 */     model.field_78808_h = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\armors\WootzSteelArmorModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */