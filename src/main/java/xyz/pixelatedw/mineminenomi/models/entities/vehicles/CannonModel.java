/*     */ package xyz.pixelatedw.mineminenomi.models.entities.vehicles;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ 
/*     */ public class CannonModel extends EntityModel<Entity> {
/*     */   private final ModelRenderer cart;
/*     */   private final ModelRenderer cartBottom;
/*     */   private final ModelRenderer cartWheel1;
/*     */   private final ModelRenderer cartWheel3;
/*     */   private final ModelRenderer cartWheel2;
/*     */   private final ModelRenderer cartWheel4;
/*     */   private final ModelRenderer cartWallFront;
/*     */   private final ModelRenderer cartWallRight0;
/*     */   private final ModelRenderer cartWallRight1;
/*     */   private final ModelRenderer cartWallRight2;
/*     */   private final ModelRenderer cartWallLeft0;
/*     */   private final ModelRenderer cartWallLeft1;
/*     */   private final ModelRenderer cartWallLeft2;
/*     */   private final ModelRenderer cannon;
/*     */   private final ModelRenderer cannonDeco1;
/*     */   private final ModelRenderer cannonDeco2;
/*     */   private final ModelRenderer cannonDeco3;
/*     */   private final ModelRenderer cannonBack;
/*     */   private final ModelRenderer cannonBack2;
/*     */   private final ModelRenderer cannonBackKnob;
/*     */   private final ModelRenderer cannonThing;
/*     */   
/*     */   public CannonModel() {
/*  35 */     this.field_78090_t = 64;
/*  36 */     this.field_78089_u = 64;
/*     */     
/*  38 */     this.cart = new ModelRenderer((Model)this);
/*  39 */     this.cart.func_78793_a(0.0F, 16.0F, 0.0F);
/*  40 */     this.cart.func_78784_a(24, 44).func_228303_a_(-2.5F, 0.0F, -6.0F, 5.0F, 5.0F, 15.0F, 0.0F, false);
/*     */     
/*  42 */     this.cartBottom = new ModelRenderer((Model)this);
/*  43 */     this.cartBottom.func_78793_a(0.0F, 5.5F, 3.0F);
/*  44 */     this.cart.func_78792_a(this.cartBottom);
/*  45 */     this.cartBottom.func_78784_a(0, 43).func_228303_a_(-6.0F, -0.5F, -10.0F, 12.0F, 1.0F, 20.0F, 0.0F, false);
/*     */     
/*  47 */     this.cartWheel1 = new ModelRenderer((Model)this);
/*  48 */     this.cartWheel1.func_78793_a(-6.0F, 0.5F, -5.5F);
/*  49 */     this.cartBottom.func_78792_a(this.cartWheel1);
/*  50 */     this.cartWheel1.func_78784_a(0, 0).func_228303_a_(-1.0F, -2.5F, -2.5F, 2.0F, 5.0F, 5.0F, 0.0F, false);
/*     */     
/*  52 */     this.cartWheel3 = new ModelRenderer((Model)this);
/*  53 */     this.cartWheel3.func_78793_a(6.0F, 0.5F, -5.5F);
/*  54 */     this.cartBottom.func_78792_a(this.cartWheel3);
/*  55 */     this.cartWheel3.func_78784_a(0, 0).func_228303_a_(-1.0F, -2.5F, -2.5F, 2.0F, 5.0F, 5.0F, 0.0F, false);
/*     */     
/*  57 */     this.cartWheel2 = new ModelRenderer((Model)this);
/*  58 */     this.cartWheel2.func_78793_a(-6.0F, 0.5F, 5.0F);
/*  59 */     this.cartBottom.func_78792_a(this.cartWheel2);
/*  60 */     this.cartWheel2.func_78784_a(0, 0).func_228303_a_(-1.0F, -2.5F, -2.5F, 2.0F, 5.0F, 5.0F, 0.0F, false);
/*     */     
/*  62 */     this.cartWheel4 = new ModelRenderer((Model)this);
/*  63 */     this.cartWheel4.func_78793_a(6.0F, 0.5F, 5.0F);
/*  64 */     this.cartBottom.func_78792_a(this.cartWheel4);
/*  65 */     this.cartWheel4.func_78784_a(0, 0).func_228303_a_(-1.0F, -2.5F, -2.5F, 2.0F, 5.0F, 5.0F, 0.0F, false);
/*     */     
/*  67 */     this.cartWallFront = new ModelRenderer((Model)this);
/*  68 */     this.cartWallFront.func_78793_a(0.0F, 2.0F, -6.5F);
/*  69 */     this.cart.func_78792_a(this.cartWallFront);
/*  70 */     this.cartWallFront.func_78784_a(0, 45).func_228303_a_(-6.0F, -3.0F, -0.5F, 12.0F, 6.0F, 1.0F, 0.0F, false);
/*     */     
/*  72 */     this.cartWallRight0 = new ModelRenderer((Model)this);
/*  73 */     this.cartWallRight0.func_78793_a(5.0F, 4.0F, 3.0F);
/*  74 */     this.cart.func_78792_a(this.cartWallRight0);
/*  75 */     this.cartWallRight0.func_78784_a(0, 44).func_228303_a_(-1.0F, -1.0F, -9.0F, 2.0F, 2.0F, 18.0F, 0.0F, false);
/*     */     
/*  77 */     this.cartWallRight1 = new ModelRenderer((Model)this);
/*  78 */     this.cartWallRight1.func_78793_a(7.0F, -18.0F, -15.0F);
/*  79 */     this.cartWallRight0.func_78792_a(this.cartWallRight1);
/*  80 */     this.cartWallRight1.func_78784_a(0, 44).func_228303_a_(-8.0F, 15.0F, 6.0F, 2.0F, 2.0F, 16.0F, 0.0F, false);
/*     */     
/*  82 */     this.cartWallRight2 = new ModelRenderer((Model)this);
/*  83 */     this.cartWallRight2.func_78793_a(7.0F, -20.0F, -15.0F);
/*  84 */     this.cartWallRight0.func_78792_a(this.cartWallRight2);
/*  85 */     this.cartWallRight2.func_78784_a(0, 44).func_228303_a_(-8.0F, 15.0F, 6.0F, 2.0F, 2.0F, 14.0F, 0.0F, false);
/*     */     
/*  87 */     this.cartWallLeft0 = new ModelRenderer((Model)this);
/*  88 */     this.cartWallLeft0.func_78793_a(-5.0F, 4.0F, 3.0F);
/*  89 */     this.cart.func_78792_a(this.cartWallLeft0);
/*  90 */     this.cartWallLeft0.func_78784_a(0, 44).func_228303_a_(-1.0F, -1.0F, -9.0F, 2.0F, 2.0F, 18.0F, 0.0F, false);
/*     */     
/*  92 */     this.cartWallLeft2 = new ModelRenderer((Model)this);
/*  93 */     this.cartWallLeft2.func_78793_a(7.0F, -5.0F, -15.0F);
/*  94 */     this.cartWallLeft0.func_78792_a(this.cartWallLeft2);
/*  95 */     this.cartWallLeft2.func_78784_a(0, 44).func_228303_a_(-8.0F, 0.0F, 6.0F, 2.0F, 2.0F, 14.0F, 0.0F, false);
/*     */     
/*  97 */     this.cartWallLeft1 = new ModelRenderer((Model)this);
/*  98 */     this.cartWallLeft1.func_78793_a(7.0F, -3.0F, -15.0F);
/*  99 */     this.cartWallLeft0.func_78792_a(this.cartWallLeft1);
/* 100 */     this.cartWallLeft1.func_78784_a(0, 44).func_228303_a_(-8.0F, 0.0F, 6.0F, 2.0F, 2.0F, 16.0F, 0.0F, false);
/*     */     
/* 102 */     this.cannon = new ModelRenderer((Model)this);
/* 103 */     this.cannon.func_78793_a(0.0F, 15.0F, 3.0F);
/* 104 */     this.cannon.func_78784_a(0, 0).func_228303_a_(-3.0F, -5.5F, -18.0F, 6.0F, 6.0F, 24.0F, 0.0F, false);
/*     */     
/* 106 */     this.cannonDeco3 = new ModelRenderer((Model)this);
/* 107 */     this.cannonDeco3.func_78793_a(0.0F, -2.5F, -5.0F);
/* 108 */     this.cannon.func_78792_a(this.cannonDeco3);
/* 109 */     this.cannonDeco3.func_78784_a(0, 0).func_228303_a_(-3.5F, -3.5F, -0.5F, 7.0F, 7.0F, 1.0F, 0.0F, false);
/*     */     
/* 111 */     this.cannonBack2 = new ModelRenderer((Model)this);
/* 112 */     this.cannonBack2.func_78793_a(0.0F, -2.5F, 8.0F);
/* 113 */     this.cannon.func_78792_a(this.cannonBack2);
/* 114 */     this.cannonBack2.func_78784_a(0, 0).func_228303_a_(-3.0F, -3.0F, -1.0F, 6.0F, 6.0F, 2.0F, 0.0F, false);
/*     */     
/* 116 */     this.cannonBackKnob = new ModelRenderer((Model)this);
/* 117 */     this.cannonBackKnob.func_78793_a(0.0F, -2.5F, 10.0F);
/* 118 */     this.cannon.func_78792_a(this.cannonBackKnob);
/* 119 */     this.cannonBackKnob.func_78784_a(0, 0).func_228303_a_(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
/*     */     
/* 121 */     this.cannonBack = new ModelRenderer((Model)this);
/* 122 */     this.cannonBack.func_78793_a(0.0F, -2.5F, 5.0F);
/* 123 */     this.cannon.func_78792_a(this.cannonBack);
/* 124 */     this.cannonBack.func_78784_a(0, 0).func_228303_a_(-3.5F, -3.5F, -2.0F, 7.0F, 7.0F, 4.0F, 0.0F, false);
/*     */     
/* 126 */     this.cannonDeco1 = new ModelRenderer((Model)this);
/* 127 */     this.cannonDeco1.func_78793_a(0.0F, -2.5F, -18.0F);
/* 128 */     this.cannon.func_78792_a(this.cannonDeco1);
/* 129 */     this.cannonDeco1.func_78784_a(46, 0).func_228303_a_(-3.5F, -3.5F, -1.0F, 7.0F, 7.0F, 2.0F, 0.0F, false);
/*     */     
/* 131 */     this.cannonThing = new ModelRenderer((Model)this);
/* 132 */     this.cannonThing.func_78793_a(8.0F, -1.5F, -8.0F);
/* 133 */     this.cannon.func_78792_a(this.cannonThing);
/* 134 */     this.cannonThing.func_78784_a(0, 1).func_228303_a_(-15.5F, 0.0F, 6.0F, 15.0F, 2.0F, 2.0F, 0.0F, false);
/*     */     
/* 136 */     this.cannonDeco2 = new ModelRenderer((Model)this);
/* 137 */     this.cannonDeco2.func_78793_a(8.0F, -2.5F, -20.5F);
/* 138 */     this.cannon.func_78792_a(this.cannonDeco2);
/* 139 */     this.cannonDeco2.func_78784_a(0, 0).func_228303_a_(-11.5F, -3.5F, 6.0F, 7.0F, 7.0F, 1.0F, 0.0F, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_225597_a_(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 144 */     if (entity.func_184179_bs() != null && entity.func_184179_bs() instanceof PlayerEntity) {
/* 145 */       PlayerEntity player = (PlayerEntity)entity.func_184179_bs();
/*     */       
/* 147 */       this.cartWheel1.field_78795_f += 0.02F * player.field_191988_bg;
/* 148 */       this.cartWheel2.field_78795_f += 0.02F * player.field_191988_bg;
/* 149 */       this.cartWheel3.field_78795_f += 0.02F * player.field_191988_bg;
/* 150 */       this.cartWheel4.field_78795_f += 0.02F * player.field_191988_bg;
/*     */     } 
/* 152 */     this.cannon.field_78795_f = (float)Math.toRadians(entity.field_70125_A);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 157 */     this.cart.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 158 */     this.cannon.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */   }
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 162 */     modelRenderer.field_78795_f = x;
/* 163 */     modelRenderer.field_78796_g = y;
/* 164 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\entities\vehicles\CannonModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */