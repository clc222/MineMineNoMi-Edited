/*     */ package xyz.pixelatedw.mineminenomi.models.entities.vehicles;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.buggypirates.CabajiEntity;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class UnicycleModel
/*     */   extends EntityModel<Entity>
/*     */ {
/*     */   private final ModelRenderer seat;
/*     */   private final ModelRenderer support;
/*     */   private final ModelRenderer pedals;
/*     */   private final ModelRenderer left;
/*     */   private final ModelRenderer leftPedal;
/*     */   private final ModelRenderer right;
/*     */   private final ModelRenderer rightPedal;
/*     */   private final ModelRenderer wheel;
/*     */   
/*     */   public UnicycleModel() {
/*  31 */     this.field_78090_t = 32;
/*  32 */     this.field_78089_u = 32;
/*     */ 
/*     */     
/*  35 */     this.seat = new ModelRenderer((Model)this);
/*  36 */     this.seat.func_78793_a(0.0F, 11.0F, -0.5F);
/*  37 */     this.seat.func_78784_a(10, 23).func_228303_a_(-2.0F, -2.0F, -3.25F, 4.0F, 2.0F, 7.0F, 0.0F, false);
/*  38 */     this.seat.func_78784_a(20, 0).func_228303_a_(-2.0F, -3.0F, 2.75F, 4.0F, 3.0F, 2.0F, 0.001F, false);
/*     */     
/*  40 */     this.support = new ModelRenderer((Model)this);
/*  41 */     this.support.func_78793_a(0.0F, 3.5F, 0.0F);
/*  42 */     this.seat.func_78792_a(this.support);
/*  43 */     this.support.func_78784_a(0, 0).func_228303_a_(-1.0F, -4.0F, -0.5F, 2.0F, 4.0F, 2.0F, -0.1F, false);
/*  44 */     this.support.func_78784_a(20, 11).func_228303_a_(-2.0F, -1.0F, -0.5F, 4.0F, 1.0F, 2.0F, 0.001F, false);
/*  45 */     this.support.func_78784_a(12, 11).func_228303_a_(1.0F, -1.0F, -0.5F, 1.0F, 6.0F, 2.0F, -0.05F, false);
/*  46 */     this.support.func_78784_a(12, 11).func_228303_a_(-2.0F, -1.0F, -0.5F, 1.0F, 6.0F, 2.0F, -0.05F, false);
/*  47 */     this.support.func_78784_a(0, 20).func_228303_a_(-1.0F, 3.5F, -0.5F, 2.0F, 2.0F, 2.0F, 0.0F, false);
/*     */     
/*  49 */     this.pedals = new ModelRenderer((Model)this);
/*  50 */     this.pedals.func_78793_a(0.0F, 4.5F, 0.5F);
/*  51 */     this.support.func_78792_a(this.pedals);
/*     */ 
/*     */     
/*  54 */     this.left = new ModelRenderer((Model)this);
/*  55 */     this.left.func_78793_a(1.0833F, 0.1667F, 0.0F);
/*  56 */     this.pedals.func_78792_a(this.left);
/*  57 */     this.left.func_78784_a(12, 8).func_228303_a_(-0.0833F, -0.6667F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/*  59 */     this.leftPedal = new ModelRenderer((Model)this);
/*  60 */     this.leftPedal.func_78793_a(2.4167F, 0.8333F, 0.0F);
/*  61 */     this.left.func_78792_a(this.leftPedal);
/*  62 */     this.leftPedal.func_78784_a(11, 1).func_228303_a_(0.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, 0.1F, false);
/*  63 */     this.leftPedal.func_78784_a(11, 5).func_228303_a_(-1.5F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/*  65 */     this.right = new ModelRenderer((Model)this);
/*  66 */     this.right.func_78793_a(-1.0F, 0.0F, 0.0F);
/*  67 */     this.pedals.func_78792_a(this.right);
/*  68 */     setRotationAngle(this.right, 3.1416F, 0.0F, 0.0F);
/*  69 */     this.right.func_78784_a(12, 8).func_228303_a_(-2.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, true);
/*     */     
/*  71 */     this.rightPedal = new ModelRenderer((Model)this);
/*  72 */     this.rightPedal.func_78793_a(-2.5F, 1.0F, 0.0F);
/*  73 */     this.right.func_78792_a(this.rightPedal);
/*  74 */     setRotationAngle(this.rightPedal, 3.1416F, 0.0F, 0.0F);
/*  75 */     this.rightPedal.func_78784_a(11, 1).func_228303_a_(-2.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, 0.001F, true);
/*  76 */     this.rightPedal.func_78784_a(11, 5).func_228303_a_(-1.5F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, 0.0F, true);
/*     */     
/*  78 */     this.wheel = new ModelRenderer((Model)this);
/*  79 */     this.wheel.func_78793_a(0.0F, 4.5F, 0.5F);
/*  80 */     this.support.func_78792_a(this.wheel);
/*  81 */     this.wheel.func_78784_a(0, 15).func_228303_a_(-1.0F, -0.9946F, -5.0F, 2.0F, 1.9891F, 2.0F, 0.001F, false);
/*  82 */     this.wheel.func_78784_a(1, 9).func_228303_a_(-0.5F, -0.5054F, -3.0F, 1.0F, 1.0F, 3.0F, -1.0E-4F, false);
/*  83 */     this.wheel.func_78784_a(0, 15).func_228303_a_(-1.0F, -0.9946F, 3.0F, 2.0F, 1.9891F, 2.0F, 0.001F, false);
/*  84 */     this.wheel.func_78784_a(0, 8).func_228303_a_(-0.5F, -0.5054F, 0.0F, 1.0F, 1.0F, 3.0F, -2.0E-4F, false);
/*  85 */     this.wheel.func_78784_a(0, 15).func_228303_a_(-1.0F, 3.0F, -0.9946F, 2.0F, 2.0F, 1.9891F, 0.001F, false);
/*  86 */     this.wheel.func_78784_a(0, 8).func_228303_a_(-0.5F, 0.0F, -0.4946F, 1.0F, 3.0F, 1.0F, 1.0E-4F, false);
/*  87 */     this.wheel.func_78784_a(0, 15).func_228303_a_(-1.0F, -5.0F, -0.9946F, 2.0F, 2.0F, 1.9891F, 0.001F, false);
/*  88 */     this.wheel.func_78784_a(0, 8).func_228303_a_(-0.5F, -3.0F, -0.5054F, 1.0F, 3.0F, 1.0F, -1.0E-4F, false);
/*     */     
/*  90 */     this.hexadecagon_r1 = new ModelRenderer((Model)this);
/*  91 */     this.hexadecagon_r1.func_78793_a(0.0F, 0.0F, 0.0F);
/*  92 */     this.wheel.func_78792_a(this.hexadecagon_r1);
/*  93 */     setRotationAngle(this.hexadecagon_r1, -0.3927F, 0.0F, 0.0F);
/*  94 */     this.hexadecagon_r1.func_78784_a(0, 15).func_228303_a_(-1.0F, -5.0F, -0.9946F, 2.0F, 2.0F, 1.9891F, -0.001F, false);
/*  95 */     this.hexadecagon_r1.func_78784_a(0, 15).func_228303_a_(-1.0F, 3.0F, -0.9946F, 2.0F, 2.0F, 1.9891F, -0.001F, false);
/*  96 */     this.hexadecagon_r1.func_78784_a(0, 15).func_228303_a_(-1.0F, -0.9946F, 3.0F, 2.0F, 1.9891F, 2.0F, -0.001F, false);
/*  97 */     this.hexadecagon_r1.func_78784_a(0, 15).func_228303_a_(-1.0F, -0.9946F, -5.0F, 2.0F, 1.9891F, 2.0F, -0.001F, false);
/*     */     
/*  99 */     this.hexadecagon_r2 = new ModelRenderer((Model)this);
/* 100 */     this.hexadecagon_r2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 101 */     this.wheel.func_78792_a(this.hexadecagon_r2);
/* 102 */     setRotationAngle(this.hexadecagon_r2, 0.3927F, 0.0F, 0.0F);
/* 103 */     this.hexadecagon_r2.func_78784_a(0, 15).func_228303_a_(-1.0F, -5.0F, -0.9946F, 2.0F, 2.0F, 1.9891F, -0.001F, false);
/* 104 */     this.hexadecagon_r2.func_78784_a(0, 15).func_228303_a_(-1.0F, 3.0F, -0.9946F, 2.0F, 2.0F, 1.9891F, -0.001F, false);
/* 105 */     this.hexadecagon_r2.func_78784_a(0, 15).func_228303_a_(-1.0F, -0.9946F, 3.0F, 2.0F, 1.9891F, 2.0F, -0.001F, false);
/* 106 */     this.hexadecagon_r2.func_78784_a(0, 15).func_228303_a_(-1.0F, -0.9946F, -5.0F, 2.0F, 1.9891F, 2.0F, -0.001F, false);
/*     */     
/* 108 */     this.inner_r1 = new ModelRenderer((Model)this);
/* 109 */     this.inner_r1.func_78793_a(0.5F, -0.5F, 0.0F);
/* 110 */     this.wheel.func_78792_a(this.inner_r1);
/* 111 */     setRotationAngle(this.inner_r1, -0.7854F, 0.0F, 0.0F);
/* 112 */     this.inner_r1.func_78784_a(0, 8).func_228303_a_(-1.0F, -0.0054F, 0.0F, 1.0F, 1.0F, 4.0F, -1.0E-4F, false);
/*     */     
/* 114 */     this.hexadecagon_r3 = new ModelRenderer((Model)this);
/* 115 */     this.hexadecagon_r3.func_78793_a(0.0F, 0.0F, 0.0F);
/* 116 */     this.wheel.func_78792_a(this.hexadecagon_r3);
/* 117 */     setRotationAngle(this.hexadecagon_r3, -0.7854F, 0.0F, 0.0F);
/* 118 */     this.hexadecagon_r3.func_78784_a(0, 15).func_228303_a_(-1.0F, -0.9946F, 3.0F, 2.0F, 1.9891F, 2.0F, 0.001F, false);
/* 119 */     this.hexadecagon_r3.func_78784_a(0, 15).func_228303_a_(-1.0F, -0.9946F, -5.0F, 2.0F, 1.9891F, 2.0F, 0.001F, false);
/*     */     
/* 121 */     this.inner_r2 = new ModelRenderer((Model)this);
/* 122 */     this.inner_r2.func_78793_a(0.5F, 0.0F, -0.75F);
/* 123 */     this.wheel.func_78792_a(this.inner_r2);
/* 124 */     setRotationAngle(this.inner_r2, 0.7854F, 0.0F, 0.0F);
/* 125 */     this.inner_r2.func_78784_a(0, 8).func_228303_a_(-1.0F, -0.0054F, 1.0F, 1.0F, 1.0F, 3.0F, -3.0E-4F, false);
/*     */     
/* 127 */     this.hexadecagon_r4 = new ModelRenderer((Model)this);
/* 128 */     this.hexadecagon_r4.func_78793_a(0.0F, 0.0F, 0.0F);
/* 129 */     this.wheel.func_78792_a(this.hexadecagon_r4);
/* 130 */     setRotationAngle(this.hexadecagon_r4, 0.7854F, 0.0F, 0.0F);
/* 131 */     this.hexadecagon_r4.func_78784_a(0, 15).func_228303_a_(-1.0F, -0.9946F, 3.0F, 2.0F, 1.9891F, 2.0F, 0.001F, false);
/* 132 */     this.hexadecagon_r4.func_78784_a(0, 15).func_228303_a_(-1.0F, -0.9946F, -5.0F, 2.0F, 1.9891F, 2.0F, 0.001F, false);
/*     */     
/* 134 */     this.inner_r3 = new ModelRenderer((Model)this);
/* 135 */     this.inner_r3.func_78793_a(0.5F, -0.5F, 0.25F);
/* 136 */     this.wheel.func_78792_a(this.inner_r3);
/* 137 */     setRotationAngle(this.inner_r3, -0.7854F, 0.0F, 0.0F);
/* 138 */     this.inner_r3.func_78784_a(0, 8).func_228303_a_(-1.0F, -0.0054F, -3.0F, 1.0F, 1.0F, 3.0F, 3.0E-4F, false);
/*     */     
/* 140 */     this.inner_r4 = new ModelRenderer((Model)this);
/* 141 */     this.inner_r4.func_78793_a(0.5F, -0.25F, -0.5F);
/* 142 */     this.wheel.func_78792_a(this.inner_r4);
/* 143 */     setRotationAngle(this.inner_r4, 0.7854F, 0.0F, 0.0F);
/* 144 */     this.inner_r4.func_78784_a(0, 8).func_228303_a_(-1.0F, -0.0054F, -3.0F, 1.0F, 1.0F, 3.0F, 2.0E-4F, false);
/*     */   }
/*     */   private final ModelRenderer hexadecagon_r1; private final ModelRenderer hexadecagon_r2; private final ModelRenderer inner_r1; private final ModelRenderer hexadecagon_r3; private final ModelRenderer inner_r2; private final ModelRenderer hexadecagon_r4; private final ModelRenderer inner_r3; private final ModelRenderer inner_r4;
/*     */   
/*     */   public void func_225597_a_(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 149 */     LivingEntity controller = null;
/* 150 */     float swing = 0.0F;
/*     */     
/* 152 */     if (entity.func_184179_bs() != null && entity.func_184179_bs() instanceof PlayerEntity) {
/* 153 */       PlayerEntity playerEntity = (PlayerEntity)entity.func_184179_bs();
/* 154 */       swing = ((PlayerEntity)entity.func_184179_bs()).field_191988_bg;
/*     */     }
/* 156 */     else if (entity instanceof CabajiEntity) {
/* 157 */       controller = (LivingEntity)entity;
/* 158 */       double xDiff = Math.abs(entity.func_226277_ct_() - entity.field_70142_S);
/* 159 */       double zDiff = Math.abs(entity.func_226281_cx_() - entity.field_70136_U);
/* 160 */       if (((CabajiEntity)entity).func_70638_az() != null) {
/* 161 */         swing = 1.0F;
/*     */       }
/* 163 */       else if (xDiff > 0.0D || zDiff > 0.0D) {
/* 164 */         swing = 1.0F;
/*     */       } 
/*     */     } 
/*     */     
/* 168 */     if (controller != null) {
/* 169 */       this.wheel.field_78795_f += 0.1F * swing % 360.0F;
/* 170 */       this.pedals.field_78795_f += 0.2F * swing % 360.0F;
/* 171 */       this.rightPedal.field_78795_f -= 0.2F * swing % 360.0F;
/* 172 */       this.leftPedal.field_78795_f -= 0.2F * swing % 360.0F;
/*     */       
/* 174 */       if (swing == 0.0F) {
/* 175 */         this.leftPedal.field_78795_f = 0.0F;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 182 */     this.seat.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */   }
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 186 */     modelRenderer.field_78795_f = x;
/* 187 */     modelRenderer.field_78796_g = y;
/* 188 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\entities\vehicles\UnicycleModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */