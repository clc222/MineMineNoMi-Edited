/*     */ package xyz.pixelatedw.mineminenomi.models.entities.mobs.animals;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BananawaniSaddleModel<T extends LivingEntity>
/*     */   extends BipedModel<T>
/*     */ {
/*     */   private final ModelRenderer saddle;
/*     */   private final ModelRenderer saddle_seat;
/*     */   private final ModelRenderer saddle_seat2;
/*     */   private final ModelRenderer saddle_seat_border_left;
/*     */   private final ModelRenderer saddle_seat_border_left2;
/*     */   private final ModelRenderer saddle_seat_border_right;
/*     */   private final ModelRenderer saddle_seat_border_right2;
/*     */   private final ModelRenderer saddle_seat_pillow;
/*     */   private final ModelRenderer saddle_roof;
/*     */   private final ModelRenderer saddle_roof_spikes1;
/*     */   private final ModelRenderer saddle_roof_spikes2;
/*     */   private final ModelRenderer saddle_roof_spikes3;
/*     */   private final ModelRenderer saddle_roof_spikes4;
/*     */   
/*     */   public BananawaniSaddleModel() {
/*  39 */     super(1.0F);
/*  40 */     this.field_78090_t = 128;
/*  41 */     this.field_78089_u = 128;
/*     */     
/*  43 */     this.saddle = new ModelRenderer((Model)this);
/*  44 */     this.saddle.func_78793_a(0.0F, 8.0F, 3.0F);
/*  45 */     this.saddle.func_78784_a(0, 33).func_228303_a_(-5.0F, -1.0F, -11.0F, 10.0F, 1.0F, 21.0F, 0.0F, false);
/*     */     
/*  47 */     this.saddle_seat = new ModelRenderer((Model)this);
/*  48 */     this.saddle_seat.func_78793_a(0.0F, 0.25F, 4.75F);
/*  49 */     this.saddle.func_78792_a(this.saddle_seat);
/*  50 */     setRotationAngle(this.saddle_seat, -0.5236F, 0.0F, 0.0F);
/*  51 */     this.saddle_seat.func_78784_a(0, 0).func_228303_a_(-5.0F, -7.0F, -0.25F, 10.0F, 7.0F, 1.0F, 0.01F, false);
/*     */     
/*  53 */     this.saddle_seat2 = new ModelRenderer((Model)this);
/*  54 */     this.saddle_seat2.func_78793_a(0.0F, -6.25F, 0.0F);
/*  55 */     this.saddle_seat.func_78792_a(this.saddle_seat2);
/*  56 */     setRotationAngle(this.saddle_seat2, 0.1745F, 0.0F, 0.0F);
/*  57 */     this.saddle_seat2.func_78784_a(0, 8).func_228303_a_(-5.0F, -6.0F, -0.25F, 10.0F, 6.0F, 1.0F, 0.0F, false);
/*     */     
/*  59 */     this.saddle_seat_border_left = new ModelRenderer((Model)this);
/*  60 */     this.saddle_seat_border_left.func_78793_a(5.5F, -1.0F, -0.75F);
/*  61 */     this.saddle_seat.func_78792_a(this.saddle_seat_border_left);
/*  62 */     this.saddle_seat_border_left.func_78784_a(20, 19).func_228303_a_(-1.0F, -6.0F, -0.25F, 1.0F, 8.0F, 1.0F, 0.0F, false);
/*     */     
/*  64 */     this.saddle_seat_border_left2 = new ModelRenderer((Model)this);
/*  65 */     this.saddle_seat_border_left2.func_78793_a(0.0F, -7.75F, -0.25F);
/*  66 */     this.saddle_seat_border_left.func_78792_a(this.saddle_seat_border_left2);
/*  67 */     setRotationAngle(this.saddle_seat_border_left2, 0.1745F, 0.0F, 0.0F);
/*  68 */     this.saddle_seat_border_left2.func_78784_a(22, 7).func_228303_a_(-1.0F, -4.0F, -0.25F, 1.0F, 6.0F, 1.0F, 0.01F, false);
/*     */     
/*  70 */     this.saddle_seat_border_right = new ModelRenderer((Model)this);
/*  71 */     this.saddle_seat_border_right.func_78793_a(-4.5F, -1.0F, -0.75F);
/*  72 */     this.saddle_seat.func_78792_a(this.saddle_seat_border_right);
/*  73 */     this.saddle_seat_border_right.func_78784_a(16, 19).func_228303_a_(-1.0F, -6.0F, -0.25F, 1.0F, 8.0F, 1.0F, 0.0F, false);
/*     */     
/*  75 */     this.saddle_seat_border_right2 = new ModelRenderer((Model)this);
/*  76 */     this.saddle_seat_border_right2.func_78793_a(0.0F, -7.75F, -0.25F);
/*  77 */     this.saddle_seat_border_right.func_78792_a(this.saddle_seat_border_right2);
/*  78 */     setRotationAngle(this.saddle_seat_border_right2, 0.1745F, 0.0F, 0.0F);
/*  79 */     this.saddle_seat_border_right2.func_78784_a(22, 0).func_228303_a_(-1.0F, -4.0F, -0.25F, 1.0F, 6.0F, 1.0F, 0.01F, false);
/*     */     
/*  81 */     this.saddle_seat_pillow = new ModelRenderer((Model)this);
/*  82 */     this.saddle_seat_pillow.func_78793_a(0.0F, -10.75F, -1.75F);
/*  83 */     this.saddle_seat.func_78792_a(this.saddle_seat_pillow);
/*  84 */     setRotationAngle(this.saddle_seat_pillow, 0.3491F, 0.0F, 0.0F);
/*  85 */     this.saddle_seat_pillow.func_78784_a(0, 15).func_228303_a_(-5.0F, -3.0F, 0.0F, 10.0F, 3.0F, 1.0F, 0.0F, false);
/*     */     
/*  87 */     this.saddle_roof = new ModelRenderer((Model)this);
/*  88 */     this.saddle_roof.func_78793_a(0.0F, -14.5F, 4.5F);
/*  89 */     this.saddle.func_78792_a(this.saddle_roof);
/*  90 */     this.saddle_roof.func_78784_a(0, 0).func_228303_a_(-5.0F, -2.0F, -16.75F, 10.0F, 2.0F, 31.0F, 0.02F, false);
/*     */     
/*  92 */     this.saddle_roof_spikes1 = new ModelRenderer((Model)this);
/*  93 */     this.saddle_roof_spikes1.func_78793_a(0.0F, -2.0F, -13.0F);
/*  94 */     this.saddle_roof.func_78792_a(this.saddle_roof_spikes1);
/*  95 */     setRotationAngle(this.saddle_roof_spikes1, 0.2618F, 0.0F, 0.0F);
/*  96 */     this.saddle_roof_spikes1.func_78784_a(0, 55).func_228303_a_(-5.0F, -1.0F, -3.5F, 10.0F, 2.0F, 7.0F, 0.0F, false);
/*     */     
/*  98 */     this.saddle_roof_spikes2 = new ModelRenderer((Model)this);
/*  99 */     this.saddle_roof_spikes2.func_78793_a(0.0F, -2.0F, -9.0F);
/* 100 */     this.saddle_roof.func_78792_a(this.saddle_roof_spikes2);
/* 101 */     setRotationAngle(this.saddle_roof_spikes2, 0.2618F, 0.0F, 0.0F);
/* 102 */     this.saddle_roof_spikes2.func_78784_a(51, 18).func_228303_a_(-5.0F, -1.0F, -3.0F, 10.0F, 2.0F, 7.0F, 0.01F, false);
/*     */     
/* 104 */     this.saddle_roof_spikes3 = new ModelRenderer((Model)this);
/* 105 */     this.saddle_roof_spikes3.func_78793_a(0.0F, -2.0F, -4.0F);
/* 106 */     this.saddle_roof.func_78792_a(this.saddle_roof_spikes3);
/* 107 */     setRotationAngle(this.saddle_roof_spikes3, 0.2618F, 0.0F, 0.0F);
/* 108 */     this.saddle_roof_spikes3.func_78784_a(51, 9).func_228303_a_(-5.0F, -1.0F, -3.5F, 10.0F, 2.0F, 7.0F, 0.0F, false);
/*     */     
/* 110 */     this.saddle_roof_spikes4 = new ModelRenderer((Model)this);
/* 111 */     this.saddle_roof_spikes4.func_78793_a(0.0F, -2.0F, 1.0F);
/* 112 */     this.saddle_roof.func_78792_a(this.saddle_roof_spikes4);
/* 113 */     setRotationAngle(this.saddle_roof_spikes4, 0.2618F, 0.0F, 0.0F);
/* 114 */     this.saddle_roof_spikes4.func_78784_a(41, 33).func_228303_a_(-5.0F, -1.0F, -3.5F, 10.0F, 2.0F, 7.0F, 0.0F, false);
/*     */     
/* 116 */     this.saddle_roof_spikes5 = new ModelRenderer((Model)this);
/* 117 */     this.saddle_roof_spikes5.func_78793_a(0.0F, -2.0F, 6.0F);
/* 118 */     this.saddle_roof.func_78792_a(this.saddle_roof_spikes5);
/* 119 */     setRotationAngle(this.saddle_roof_spikes5, 0.2618F, 0.0F, 0.0F);
/* 120 */     this.saddle_roof_spikes5.func_78784_a(51, 0).func_228303_a_(-5.0F, -1.0F, -3.5F, 10.0F, 2.0F, 7.0F, 0.0F, false);
/*     */     
/* 122 */     this.saddle_roof_spikes6 = new ModelRenderer((Model)this);
/* 123 */     this.saddle_roof_spikes6.func_78793_a(0.0F, -2.0F, 11.0F);
/* 124 */     this.saddle_roof.func_78792_a(this.saddle_roof_spikes6);
/* 125 */     setRotationAngle(this.saddle_roof_spikes6, 0.2618F, 0.0F, 0.0F);
/* 126 */     this.saddle_roof_spikes6.func_78784_a(41, 42).func_228303_a_(-5.0F, -1.0F, -3.5F, 10.0F, 2.0F, 7.0F, 0.0F, false);
/*     */     
/* 128 */     this.saddle_support_back_left = new ModelRenderer((Model)this);
/* 129 */     this.saddle_support_back_left.func_78793_a(5.0F, 0.0F, 9.0F);
/* 130 */     this.saddle.func_78792_a(this.saddle_support_back_left);
/* 131 */     setRotationAngle(this.saddle_support_back_left, -0.3491F, 0.0F, 0.0F);
/* 132 */     this.saddle_support_back_left.func_78784_a(12, 19).func_228303_a_(-1.0F, -9.0F, 0.0F, 1.0F, 9.0F, 1.0F, 0.01F, false);
/*     */     
/* 134 */     this.saddle_support_back_left2 = new ModelRenderer((Model)this);
/* 135 */     this.saddle_support_back_left2.func_78793_a(0.0F, -9.0F, 0.0F);
/* 136 */     this.saddle_support_back_left.func_78792_a(this.saddle_support_back_left2);
/* 137 */     setRotationAngle(this.saddle_support_back_left2, -0.3491F, 0.0F, 0.0F);
/* 138 */     this.saddle_support_back_left2.func_78784_a(8, 19).func_228303_a_(-1.0F, -9.0F, 0.0F, 1.0F, 9.0F, 1.0F, 0.01F, false);
/*     */     
/* 140 */     this.saddle_support_front_left = new ModelRenderer((Model)this);
/* 141 */     this.saddle_support_front_left.func_78793_a(4.5F, 0.5F, -10.75F);
/* 142 */     this.saddle.func_78792_a(this.saddle_support_front_left);
/* 143 */     setRotationAngle(this.saddle_support_front_left, 0.0F, 0.0F, 0.3491F);
/* 144 */     this.saddle_support_front_left.func_78784_a(26, 7).func_228303_a_(-1.0F, -6.0F, 0.0F, 1.0F, 6.0F, 1.0F, 0.01F, false);
/*     */     
/* 146 */     this.saddle_support_front_left2 = new ModelRenderer((Model)this);
/* 147 */     this.saddle_support_front_left2.func_78793_a(0.0F, -5.75F, 0.0F);
/* 148 */     this.saddle_support_front_left.func_78792_a(this.saddle_support_front_left2);
/* 149 */     setRotationAngle(this.saddle_support_front_left2, -0.0436F, 0.0F, -0.2618F);
/* 150 */     this.saddle_support_front_left2.func_78784_a(4, 33).func_228303_a_(-1.0F, -5.0F, 0.0F, 1.0F, 5.0F, 1.0F, 0.01F, false);
/*     */     
/* 152 */     this.saddle_support_front_left3 = new ModelRenderer((Model)this);
/* 153 */     this.saddle_support_front_left3.func_78793_a(0.0F, -5.0F, 0.0F);
/* 154 */     this.saddle_support_front_left2.func_78792_a(this.saddle_support_front_left3);
/* 155 */     setRotationAngle(this.saddle_support_front_left3, 0.0F, 0.0F, -0.4363F);
/* 156 */     this.saddle_support_front_left3.func_78784_a(26, 0).func_228303_a_(-1.0F, -6.0F, 0.0F, 1.0F, 6.0F, 1.0F, 0.01F, false);
/*     */     
/* 158 */     this.saddle_support_front_right = new ModelRenderer((Model)this);
/* 159 */     this.saddle_support_front_right.func_78793_a(-3.5F, 0.0F, -10.75F);
/* 160 */     this.saddle.func_78792_a(this.saddle_support_front_right);
/* 161 */     setRotationAngle(this.saddle_support_front_right, 0.0F, 0.0F, -0.3944F);
/* 162 */     this.saddle_support_front_right.func_78784_a(24, 21).func_228303_a_(-1.0F, -6.0F, 0.0F, 1.0F, 6.0F, 1.0F, 0.01F, false);
/*     */     
/* 164 */     this.saddle_support_front_right2 = new ModelRenderer((Model)this);
/* 165 */     this.saddle_support_front_right2.func_78793_a(0.0F, -5.75F, 0.0F);
/* 166 */     this.saddle_support_front_right.func_78792_a(this.saddle_support_front_right2);
/* 167 */     setRotationAngle(this.saddle_support_front_right2, -0.0435F, 0.0038F, 0.349F);
/* 168 */     this.saddle_support_front_right2.func_78784_a(0, 33).func_228303_a_(-1.0F, -5.0F, 0.0F, 1.0F, 5.0F, 1.0F, 0.01F, false);
/*     */     
/* 170 */     this.saddle_support_front_right3 = new ModelRenderer((Model)this);
/* 171 */     this.saddle_support_front_right3.func_78793_a(0.0F, -4.5F, 0.0F);
/* 172 */     this.saddle_support_front_right2.func_78792_a(this.saddle_support_front_right3);
/* 173 */     setRotationAngle(this.saddle_support_front_right3, 0.0F, 0.0F, 0.5236F);
/*     */     
/* 175 */     this.saddle_support_front_right3_r1 = new ModelRenderer((Model)this);
/* 176 */     this.saddle_support_front_right3_r1.func_78793_a(0.0F, 0.0F, 0.0F);
/* 177 */     this.saddle_support_front_right3.func_78792_a(this.saddle_support_front_right3_r1);
/* 178 */     setRotationAngle(this.saddle_support_front_right3_r1, 0.0F, 0.0F, -0.1309F);
/* 179 */     this.saddle_support_front_right3_r1.func_78784_a(24, 14).func_228303_a_(-1.1F, -6.0F, 0.0F, 1.0F, 6.0F, 1.0F, 0.0F, false);
/*     */     
/* 181 */     this.saddle_support_back_right = new ModelRenderer((Model)this);
/* 182 */     this.saddle_support_back_right.func_78793_a(-4.0F, 0.0F, 9.0F);
/* 183 */     this.saddle.func_78792_a(this.saddle_support_back_right);
/* 184 */     setRotationAngle(this.saddle_support_back_right, -0.3491F, 0.0F, 0.0F);
/* 185 */     this.saddle_support_back_right.func_78784_a(4, 19).func_228303_a_(-1.0F, -9.0F, 0.0F, 1.0F, 9.0F, 1.0F, 0.01F, false);
/*     */     
/* 187 */     this.saddle_support_back_right2 = new ModelRenderer((Model)this);
/* 188 */     this.saddle_support_back_right2.func_78793_a(0.0F, -9.0F, 0.0F);
/* 189 */     this.saddle_support_back_right.func_78792_a(this.saddle_support_back_right2);
/* 190 */     setRotationAngle(this.saddle_support_back_right2, -0.3491F, 0.0F, 0.0F);
/* 191 */     this.saddle_support_back_right2.func_78784_a(0, 19).func_228303_a_(-1.0F, -9.0F, 0.0F, 1.0F, 9.0F, 1.0F, 0.01F, false);
/*     */   }
/*     */   private final ModelRenderer saddle_roof_spikes5; private final ModelRenderer saddle_roof_spikes6; private final ModelRenderer saddle_support_back_left; private final ModelRenderer saddle_support_back_left2; private final ModelRenderer saddle_support_front_left; private final ModelRenderer saddle_support_front_left2; private final ModelRenderer saddle_support_front_left3; private final ModelRenderer saddle_support_front_right; private final ModelRenderer saddle_support_front_right2; private final ModelRenderer saddle_support_front_right3;
/*     */   private final ModelRenderer saddle_support_front_right3_r1;
/*     */   private final ModelRenderer saddle_support_back_right;
/*     */   private final ModelRenderer saddle_support_back_right2;
/*     */   
/*     */   public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 201 */     this.saddle.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */   }
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 205 */     modelRenderer.field_78795_f = x;
/* 206 */     modelRenderer.field_78796_g = y;
/* 207 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\entities\mobs\animals\BananawaniSaddleModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */