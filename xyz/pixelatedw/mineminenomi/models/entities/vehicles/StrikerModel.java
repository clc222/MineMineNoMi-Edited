/*     */ package xyz.pixelatedw.mineminenomi.models.entities.vehicles;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ 
/*     */ public class StrikerModel extends EntityModel<Entity> {
/*     */   private final ModelRenderer Striker_Main;
/*     */   private final ModelRenderer Body;
/*     */   private final ModelRenderer Body2_r1;
/*     */   private final ModelRenderer Body1_r1;
/*     */   private final ModelRenderer Motor;
/*     */   private final ModelRenderer StrikerSail;
/*     */   private final ModelRenderer SailPole;
/*     */   private final ModelRenderer Sail;
/*     */   
/*     */   public StrikerModel() {
/*  21 */     this.field_78090_t = 256;
/*  22 */     this.field_78089_u = 256;
/*     */     
/*  24 */     this.Striker_Main = new ModelRenderer((Model)this);
/*  25 */     this.Striker_Main.func_78793_a(-1.0F, 24.0F, -7.0F);
/*  26 */     setRotationAngle(this.Striker_Main, 0.0F, 1.5708F, 0.0F);
/*     */ 
/*     */     
/*  29 */     this.Body = new ModelRenderer((Model)this);
/*  30 */     this.Body.func_78793_a(0.0F, 0.0F, -9.0F);
/*  31 */     this.Striker_Main.func_78792_a(this.Body);
/*  32 */     this.Body.func_78784_a(100, 11).func_228303_a_(10.25F, -7.6F, 8.0F, 5.0F, 6.0F, 5.0F, 0.0F, false);
/*  33 */     this.Body.func_78784_a(40, 78).func_228303_a_(3.0F, -7.3125F, 6.5F, 9.0F, 7.0F, 8.0F, 0.0F, false);
/*  34 */     this.Body.func_78784_a(85, 38).func_228303_a_(3.0F, -7.0F, 6.0F, 7.0F, 3.0F, 9.0F, 0.0F, false);
/*  35 */     this.Body.func_78784_a(50, 38).func_228303_a_(-14.0F, -4.0F, 6.0F, 20.0F, 3.0F, 1.0F, 0.0F, false);
/*  36 */     this.Body.func_78784_a(0, 37).func_228303_a_(-14.0F, -4.0F, 14.0F, 21.0F, 3.0F, 1.0F, 0.0F, false);
/*  37 */     this.Body.func_78784_a(50, 0).func_228303_a_(-20.0F, -3.0F, 6.5F, 23.0F, 3.0F, 8.0F, 0.0F, false);
/*  38 */     this.Body.func_78784_a(84, 33).func_228303_a_(-14.0F, -7.0F, 15.0F, 18.0F, 4.0F, 1.0F, 0.0F, false);
/*  39 */     this.Body.func_78784_a(99, 99).func_228303_a_(-14.0F, -7.0F, 5.0F, 18.0F, 4.0F, 1.0F, 0.0F, false);
/*     */     
/*  41 */     this.Body2_r1 = new ModelRenderer((Model)this);
/*  42 */     this.Body2_r1.func_78793_a(17.2579F, -6.4062F, 10.5F);
/*  43 */     this.Body.func_78792_a(this.Body2_r1);
/*  44 */     setRotationAngle(this.Body2_r1, 0.0F, 0.0F, -0.2182F);
/*  45 */     this.Body2_r1.func_78784_a(75, 100).func_228303_a_(-3.5F, -2.0F, -2.0F, 6.0F, 5.0F, 4.0F, 0.0F, false);
/*     */     
/*  47 */     this.Body1_r1 = new ModelRenderer((Model)this);
/*  48 */     this.Body1_r1.func_78793_a(21.4255F, -7.9231F, 10.5F);
/*  49 */     this.Body.func_78792_a(this.Body1_r1);
/*  50 */     setRotationAngle(this.Body1_r1, 0.0F, 0.0F, -0.48F);
/*  51 */     this.Body1_r1.func_78784_a(66, 78).func_228303_a_(-3.5F, -2.0F, -1.5F, 6.0F, 4.0F, 3.0F, 0.0F, false);
/*     */     
/*  53 */     this.Motor = new ModelRenderer((Model)this);
/*  54 */     this.Motor.func_78793_a(-6.0F, -5.0F, 8.0F);
/*  55 */     this.Body.func_78792_a(this.Motor);
/*  56 */     this.Motor.func_78784_a(27, 0).func_228303_a_(12.5625F, -2.5F, 0.5F, 4.0F, 2.0F, 4.0F, 0.0F, false);
/*  57 */     this.Motor.func_78784_a(100, 85).func_228303_a_(10.5625F, -2.85F, -0.5F, 4.0F, 2.0F, 6.0F, 0.0F, false);
/*  58 */     this.Motor.func_78784_a(100, 50).func_228303_a_(9.5625F, -3.5F, -1.0F, 3.0F, 4.0F, 7.0F, 0.0F, false);
/*  59 */     this.Motor.func_78784_a(40, 93).func_228303_a_(7.75F, -2.5F, -2.0F, 2.0F, 2.0F, 9.0F, 0.0F, false);
/*  60 */     this.Motor.func_78784_a(0, 47).func_228303_a_(-5.0F, -2.5F, 6.5F, 14.0F, 2.0F, 1.0F, 0.0F, false);
/*  61 */     this.Motor.func_78784_a(0, 41).func_228303_a_(-5.0F, -2.5F, -2.5F, 14.0F, 2.0F, 1.0F, 0.0F, false);
/*  62 */     this.Motor.func_78784_a(90, 68).func_228303_a_(-7.0F, -5.5F, -2.5F, 3.0F, 7.0F, 10.0F, 0.0F, false);
/*  63 */     this.Motor.func_78784_a(50, 23).func_228303_a_(-17.0F, -5.0F, -1.0F, 10.0F, 8.0F, 7.0F, 0.0F, false);
/*  64 */     this.Motor.func_78784_a(53, 95).func_228303_a_(-16.0F, -6.0F, 0.0F, 8.0F, 1.0F, 5.0F, 0.0F, false);
/*  65 */     this.Motor.func_78784_a(0, 22).func_228303_a_(-17.0F, -4.0F, -2.0F, 10.0F, 6.0F, 9.0F, 0.0F, false);
/*  66 */     this.Motor.func_78784_a(50, 11).func_228303_a_(-5.0F, -0.5F, -2.5F, 13.0F, 2.0F, 10.0F, 0.0F, false);
/*  67 */     this.Motor.func_78784_a(0, 0).func_228303_a_(-25.0F, -7.0F, -3.0F, 8.0F, 11.0F, 11.0F, 0.0F, false);
/*  68 */     this.Motor.func_78784_a(0, 78).func_228303_a_(-23.0F, -7.5F, -3.5F, 4.0F, 12.0F, 12.0F, 0.0F, false);
/*  69 */     this.Motor.func_78784_a(86, 13).func_228303_a_(-27.0F, -6.5F, -2.5F, 2.0F, 10.0F, 10.0F, 0.0F, false);
/*  70 */     this.Motor.func_78784_a(74, 78).func_228303_a_(-28.0F, -7.0F, -3.0F, 2.0F, 11.0F, 11.0F, 0.0F, false);
/*     */     
/*  72 */     this.StrikerSail = new ModelRenderer((Model)this);
/*  73 */     this.StrikerSail.func_78793_a(0.0F, 0.0F, -9.0F);
/*  74 */     this.Striker_Main.func_78792_a(this.StrikerSail);
/*  75 */     this.StrikerSail.func_78784_a(27, 6).func_228303_a_(-19.5F, -13.0F, 9.0F, 3.0F, 2.0F, 3.0F, 0.0F, false);
/*     */     
/*  77 */     this.SailPole = new ModelRenderer((Model)this);
/*  78 */     this.SailPole.func_78793_a(-17.975F, -12.9583F, 10.5F);
/*  79 */     this.StrikerSail.func_78792_a(this.SailPole);
/*  80 */     this.SailPole.func_78784_a(32, 78).func_228303_a_(-1.025F, -41.0417F, -1.0F, 2.0F, 41.0F, 2.0F, 0.0F, false);
/*  81 */     this.SailPole.func_78784_a(0, 0).func_228303_a_(-1.525F, -31.0417F, -22.0F, 3.0F, 3.0F, 44.0F, 0.0F, false);
/*     */     
/*  83 */     this.Sail = new ModelRenderer((Model)this);
/*  84 */     this.Sail.func_78793_a(1.175F, -28.6042F, 0.0F);
/*  85 */     this.SailPole.func_78792_a(this.Sail);
/*  86 */     this.Sail.func_78784_a(0, 0).func_228303_a_(0.0F, -1.0F, -25.0F, 0.0F, 28.0F, 50.0F, 0.0F, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225597_a_(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/*  96 */     this.Striker_Main.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */   }
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 100 */     modelRenderer.field_78795_f = x;
/* 101 */     modelRenderer.field_78796_g = y;
/* 102 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\entities\vehicles\StrikerModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */