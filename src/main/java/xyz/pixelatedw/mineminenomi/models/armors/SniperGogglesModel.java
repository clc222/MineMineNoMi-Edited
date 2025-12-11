/*     */ package xyz.pixelatedw.mineminenomi.models.armors;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*     */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.ZoomAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class SniperGogglesModel<T extends LivingEntity>
/*     */   extends BipedModel<T>
/*     */ {
/*     */   public ModelRenderer base;
/*     */   public ModelRenderer frame1;
/*     */   public ModelRenderer frame2;
/*     */   public ModelRenderer frame3;
/*     */   public ModelRenderer frame4;
/*     */   public ModelRenderer frame5;
/*     */   public ModelRenderer frame6;
/*     */   public ModelRenderer frame7;
/*     */   public ModelRenderer frame8;
/*     */   public ModelRenderer frame9;
/*     */   public ModelRenderer right_ear_support;
/*     */   public ModelRenderer left_ear_support;
/*     */   public ModelRenderer right_eye;
/*     */   public ModelRenderer right_eye_extension;
/*     */   public ModelRenderer right_eye_circle_1;
/*     */   public ModelRenderer right_eye_circle_2;
/*     */   public ModelRenderer left_eye;
/*     */   public ModelRenderer left_eye_extension;
/*     */   public ModelRenderer left_eye_circle_1;
/*     */   public ModelRenderer left_eye_circle_2;
/*     */   
/*     */   public SniperGogglesModel() {
/*  44 */     super(1.0F);
/*  45 */     this.field_78090_t = 32;
/*  46 */     this.field_78089_u = 16;
/*  47 */     this.right_eye = new ModelRenderer((Model)this, 6, 8);
/*  48 */     this.right_eye.func_78793_a(0.0F, 0.0F, 0.0F);
/*  49 */     this.right_eye.func_228301_a_(-3.0F, 3.5F, -5.0F, 2.0F, 2.0F, 1.0F, 0.0F);
/*  50 */     this.right_eye_extension = new ModelRenderer((Model)this, 6, 12);
/*  51 */     this.right_eye_extension.func_78793_a(0.0F, 0.0F, 0.0F);
/*  52 */     this.right_eye_extension.func_228301_a_(-2.5F, 4.0F, -5.3F, 1.0F, 1.0F, 1.0F, 0.0F);
/*  53 */     this.left_eye_circle_1 = new ModelRenderer((Model)this, 6, 8);
/*  54 */     this.left_eye_circle_1.func_78793_a(0.0F, 0.0F, 0.0F);
/*  55 */     this.left_eye_circle_1.func_228301_a_(0.5F, 4.0F, -5.0F, 3.0F, 1.0F, 1.0F, 0.0F);
/*  56 */     this.base = new ModelRenderer((Model)this, 0, 0);
/*  57 */     this.base.func_78793_a(0.0F, 0.0F, 0.0F);
/*  58 */     this.base.func_228301_a_(-4.0F, -8.0F, -4.0F, 0.0F, 0.0F, 0.0F, 0.0F);
/*  59 */     this.left_ear_support = new ModelRenderer((Model)this, 10, 3);
/*  60 */     this.left_ear_support.func_78793_a(0.0F, 0.0F, 0.0F);
/*  61 */     this.left_ear_support.func_228301_a_(4.0F, 4.0F, -0.5F, 1.0F, 2.0F, 2.0F, 0.0F);
/*  62 */     this.right_ear_support = new ModelRenderer((Model)this, 10, 3);
/*  63 */     this.right_ear_support.func_78793_a(0.0F, 0.0F, 0.0F);
/*  64 */     this.right_ear_support.func_228301_a_(-5.0F, 4.0F, -0.5F, 1.0F, 2.0F, 2.0F, 0.0F);
/*  65 */     this.frame7 = new ModelRenderer((Model)this, 5, 3);
/*  66 */     this.frame7.func_78793_a(0.0F, 0.0F, 0.0F);
/*  67 */     this.frame7.func_228301_a_(1.5F, 1.0F, -4.5F, 1.0F, 3.0F, 1.0F, 0.0F);
/*  68 */     this.left_eye = new ModelRenderer((Model)this, 6, 8);
/*  69 */     this.left_eye.func_78793_a(0.0F, 0.0F, 0.0F);
/*  70 */     this.left_eye.func_228301_a_(1.0F, 3.5F, -5.0F, 2.0F, 2.0F, 1.0F, 0.0F);
/*  71 */     this.frame2 = new ModelRenderer((Model)this, 0, 3);
/*  72 */     this.frame2.func_78793_a(0.0F, 0.0F, 0.0F);
/*  73 */     this.frame2.func_228301_a_(-4.5F, 0.9F, 0.0F, 1.0F, 5.0F, 1.0F, 0.0F);
/*  74 */     this.frame4 = new ModelRenderer((Model)this, 12, 6);
/*  75 */     this.frame4.func_78793_a(0.0F, 0.0F, 0.0F);
/*  76 */     this.frame4.func_228301_a_(-2.5F, 0.0F, -4.5F, 1.0F, 1.0F, 9.0F, 0.0F);
/*  77 */     this.left_eye_extension = new ModelRenderer((Model)this, 6, 12);
/*  78 */     this.left_eye_extension.func_78793_a(0.0F, 0.0F, 0.0F);
/*  79 */     this.left_eye_extension.func_228301_a_(1.5F, 4.0F, -5.3F, 1.0F, 1.0F, 1.0F, 0.0F);
/*  80 */     this.frame3 = new ModelRenderer((Model)this, 0, 3);
/*  81 */     this.frame3.func_78793_a(0.0F, 0.0F, 0.0F);
/*  82 */     this.frame3.func_228301_a_(3.5F, 0.9F, 0.0F, 1.0F, 5.0F, 1.0F, 0.0F);
/*  83 */     this.frame5 = new ModelRenderer((Model)this, 12, 6);
/*  84 */     this.frame5.func_78793_a(0.0F, 0.0F, 0.0F);
/*  85 */     this.frame5.func_228301_a_(1.5F, 0.0F, -4.5F, 1.0F, 1.0F, 9.0F, 0.0F);
/*  86 */     this.left_eye_circle_2 = new ModelRenderer((Model)this, 6, 8);
/*  87 */     this.left_eye_circle_2.func_78793_a(0.0F, 0.0F, 0.0F);
/*  88 */     this.left_eye_circle_2.func_228301_a_(1.5F, 3.0F, -5.0F, 1.0F, 3.0F, 1.0F, 0.0F);
/*  89 */     this.frame9 = new ModelRenderer((Model)this, 5, 3);
/*  90 */     this.frame9.func_78793_a(0.0F, 0.0F, 0.0F);
/*  91 */     this.frame9.func_228301_a_(-2.5F, 1.0F, 3.5F, 1.0F, 4.0F, 1.0F, 0.0F);
/*  92 */     this.frame6 = new ModelRenderer((Model)this, 5, 3);
/*  93 */     this.frame6.func_78793_a(0.0F, 0.0F, 0.0F);
/*  94 */     this.frame6.func_228301_a_(-2.5F, 1.0F, -4.5F, 1.0F, 3.0F, 1.0F, 0.0F);
/*  95 */     this.right_eye_circle_1 = new ModelRenderer((Model)this, 6, 8);
/*  96 */     this.right_eye_circle_1.func_78793_a(0.0F, 0.0F, 0.0F);
/*  97 */     this.right_eye_circle_1.func_228301_a_(-3.5F, 4.0F, -5.0F, 3.0F, 1.0F, 1.0F, 0.0F);
/*  98 */     this.right_eye_circle_2 = new ModelRenderer((Model)this, 6, 8);
/*  99 */     this.right_eye_circle_2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 100 */     this.right_eye_circle_2.func_228301_a_(-2.5F, 3.0F, -5.0F, 1.0F, 3.0F, 1.0F, 0.0F);
/* 101 */     this.frame1 = new ModelRenderer((Model)this, 0, 0);
/* 102 */     this.frame1.func_78793_a(0.0F, -8.5F, 0.0F);
/* 103 */     this.frame1.func_228301_a_(-4.5F, -0.1F, 0.0F, 9.0F, 1.0F, 1.0F, 0.0F);
/* 104 */     this.frame8 = new ModelRenderer((Model)this, 5, 3);
/* 105 */     this.frame8.func_78793_a(0.0F, 0.0F, 0.0F);
/* 106 */     this.frame8.func_228301_a_(1.5F, 1.0F, 3.5F, 1.0F, 4.0F, 1.0F, 0.0F);
/* 107 */     this.frame6.func_78792_a(this.right_eye);
/* 108 */     this.right_eye.func_78792_a(this.right_eye_extension);
/* 109 */     this.left_eye.func_78792_a(this.left_eye_circle_1);
/* 110 */     this.frame3.func_78792_a(this.left_ear_support);
/* 111 */     this.frame2.func_78792_a(this.right_ear_support);
/* 112 */     this.frame1.func_78792_a(this.frame7);
/* 113 */     this.frame7.func_78792_a(this.left_eye);
/* 114 */     this.frame1.func_78792_a(this.frame2);
/* 115 */     this.frame1.func_78792_a(this.frame4);
/* 116 */     this.left_eye.func_78792_a(this.left_eye_extension);
/* 117 */     this.frame1.func_78792_a(this.frame3);
/* 118 */     this.frame1.func_78792_a(this.frame5);
/* 119 */     this.left_eye.func_78792_a(this.left_eye_circle_2);
/* 120 */     this.frame1.func_78792_a(this.frame9);
/* 121 */     this.frame1.func_78792_a(this.frame6);
/* 122 */     this.right_eye.func_78792_a(this.right_eye_circle_1);
/* 123 */     this.right_eye.func_78792_a(this.right_eye_circle_2);
/* 124 */     this.base.func_78792_a(this.frame1);
/* 125 */     this.frame1.func_78792_a(this.frame8);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 131 */     this.base.func_217177_a(this.field_78116_c);
/* 132 */     this.base.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setState(T entity) {
/* 137 */     if (entity instanceof net.minecraft.entity.player.PlayerEntity) {
/*     */       
/* 139 */       ClientPlayerEntity clientPlayerEntity = (Minecraft.func_71410_x()).field_71439_g;
/* 140 */       IAbilityData aprops = AbilityDataCapability.get((LivingEntity)clientPlayerEntity);
/*     */       
/* 142 */       this.left_eye.field_78797_d = -2.0F;
/* 143 */       this.right_eye.field_78797_d = -2.0F;
/*     */       
/* 145 */       if (aprops.getEquippedAbility(ZoomAbility.INSTANCE) != null) {
/*     */         
/* 147 */         Ability ability = (Ability)aprops.getEquippedAbility(ZoomAbility.INSTANCE);
/*     */         
/* 149 */         if (ability.isContinuous()) {
/*     */           
/* 151 */           this.left_eye.field_78797_d += 2.0F;
/* 152 */           this.right_eye.field_78797_d += 2.0F;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
/* 160 */     model.field_78795_f = x;
/* 161 */     model.field_78796_g = y;
/* 162 */     model.field_78808_h = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\armors\SniperGogglesModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */