/*     */ package xyz.pixelatedw.mineminenomi.models.armors;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class CaptainCapeModel<T extends LivingEntity>
/*     */   extends BipedModel<T> {
/*     */   public ModelRenderer cape;
/*     */   public ModelRenderer capeback;
/*     */   private ModelRenderer capeleftsholderpad2;
/*     */   private ModelRenderer capeleftsholderpad1;
/*     */   private ModelRenderer capeleftarm;
/*     */   private ModelRenderer capeleft;
/*     */   private ModelRenderer capefrontleft;
/*     */   private ModelRenderer caperightsholderpad2;
/*     */   private ModelRenderer caperightarm;
/*     */   private ModelRenderer caperightsholderpad1;
/*     */   private ModelRenderer caperight;
/*     */   private ModelRenderer capefrontright;
/*     */   private ModelRenderer caperightsholder;
/*     */   private ModelRenderer capeleftsholder;
/*     */   private ModelRenderer capeleftcollar1;
/*     */   private ModelRenderer capeleftcollar2;
/*     */   private ModelRenderer caperightcollar1;
/*     */   private ModelRenderer caperightcollar2;
/*     */   
/*     */   public CaptainCapeModel() {
/*  37 */     super(1.0F);
/*  38 */     this.field_78090_t = 128;
/*  39 */     this.field_78089_u = 128;
/*     */     
/*  41 */     this.cape = new ModelRenderer((Model)this);
/*  42 */     this.cape.func_78793_a(0.0F, 0.5F, 0.0F);
/*     */ 
/*     */     
/*  45 */     this.capeback = new ModelRenderer((Model)this);
/*  46 */     this.capeback.func_78793_a(-0.5F, -0.5F, 2.5F);
/*  47 */     this.cape.func_78792_a(this.capeback);
/*  48 */     this.capeback.func_78784_a(5, 75).func_228303_a_(-8.0F, 0.0F, 0.0F, 17.0F, 22.0F, 0.0F, 0.0F, false);
/*     */     
/*  50 */     this.capeleftsholderpad2 = new ModelRenderer((Model)this);
/*  51 */     this.capeleftsholderpad2.func_78793_a(6.4F, 0.0F, 0.0F);
/*  52 */     this.capeback.func_78792_a(this.capeleftsholderpad2);
/*  53 */     this.capeleftsholderpad2.func_78784_a(5, 106).func_228303_a_(0.0F, 0.0F, -5.5F, 5.0F, 3.0F, 6.0F, 0.0F, false);
/*     */     
/*  55 */     this.capeleftsholderpad1 = new ModelRenderer((Model)this);
/*  56 */     this.capeleftsholderpad1.func_78793_a(0.0F, 0.0F, 0.0F);
/*  57 */     this.capeleftsholderpad2.func_78792_a(this.capeleftsholderpad1);
/*  58 */     setRotationAngle(this.capeleftsholderpad1, 0.0F, 0.0F, 0.1745F);
/*  59 */     this.capeleftsholderpad1.func_78784_a(5, 98).func_228303_a_(0.0F, -1.0F, -5.5F, 5.0F, 1.0F, 6.0F, -0.01F, false);
/*     */     
/*  61 */     this.capeleftarm = new ModelRenderer((Model)this);
/*  62 */     this.capeleftarm.func_78793_a(0.0F, 0.0F, 0.0F);
/*  63 */     this.capeleftsholderpad2.func_78792_a(this.capeleftarm);
/*  64 */     this.capeleftarm.func_78784_a(35, 98).func_228303_a_(2.6F, 1.0F, -4.5F, 2.0F, 12.0F, 4.0F, 0.0F, false);
/*     */     
/*  66 */     this.capeleft = new ModelRenderer((Model)this);
/*  67 */     this.capeleft.func_78793_a(9.0F, 0.0F, 0.0F);
/*  68 */     this.capeback.func_78792_a(this.capeleft);
/*  69 */     this.capeleft.func_78784_a(40, 70).func_228303_a_(0.0F, 0.0F, -5.0F, 0.0F, 22.0F, 5.0F, 0.0F, false);
/*     */     
/*  71 */     this.capefrontleft = new ModelRenderer((Model)this);
/*  72 */     this.capefrontleft.func_78793_a(0.0F, 0.0F, -5.0F);
/*  73 */     this.capeleft.func_78792_a(this.capefrontleft);
/*  74 */     this.capefrontleft.func_78784_a(28, 98).func_228303_a_(-3.0F, 0.0F, 0.0F, 3.0F, 22.0F, 0.0F, 0.0F, false);
/*     */     
/*  76 */     this.caperightsholderpad2 = new ModelRenderer((Model)this);
/*  77 */     this.caperightsholderpad2.func_78793_a(-6.4F, 0.0F, 0.0F);
/*  78 */     this.capeback.func_78792_a(this.caperightsholderpad2);
/*  79 */     this.caperightsholderpad2.func_78784_a(5, 106).func_228303_a_(-4.0F, 0.0F, -5.5F, 5.0F, 3.0F, 6.0F, 0.0F, false);
/*     */     
/*  81 */     this.caperightarm = new ModelRenderer((Model)this);
/*  82 */     this.caperightarm.func_78793_a(0.5F, 0.0F, -2.5F);
/*  83 */     this.caperightsholderpad2.func_78792_a(this.caperightarm);
/*  84 */     this.caperightarm.func_78784_a(35, 98).func_228303_a_(-4.1F, 1.0F, -2.0F, 2.0F, 12.0F, 4.0F, 0.0F, false);
/*     */     
/*  86 */     this.caperightsholderpad1 = new ModelRenderer((Model)this);
/*  87 */     this.caperightsholderpad1.func_78793_a(0.5F, 0.0F, -2.5F);
/*  88 */     this.caperightsholderpad2.func_78792_a(this.caperightsholderpad1);
/*  89 */     setRotationAngle(this.caperightsholderpad1, 0.0F, 0.0F, -0.1745F);
/*  90 */     this.caperightsholderpad1.func_78784_a(5, 98).func_228303_a_(-4.5F, -0.8F, -3.0F, 5.0F, 1.0F, 6.0F, -0.01F, false);
/*     */     
/*  92 */     this.caperight = new ModelRenderer((Model)this);
/*  93 */     this.caperight.func_78793_a(-8.0F, 0.0F, 0.0F);
/*  94 */     this.capeback.func_78792_a(this.caperight);
/*  95 */     this.caperight.func_78784_a(40, 70).func_228303_a_(0.0F, 0.0F, -5.0F, 0.0F, 22.0F, 5.0F, 0.0F, false);
/*     */     
/*  97 */     this.capefrontright = new ModelRenderer((Model)this);
/*  98 */     this.capefrontright.func_78793_a(0.0F, 0.0F, -5.0F);
/*  99 */     this.caperight.func_78792_a(this.capefrontright);
/* 100 */     this.capefrontright.func_78784_a(28, 98).func_228303_a_(0.0F, 0.0F, 0.0F, 3.0F, 22.0F, 0.0F, 0.0F, false);
/*     */     
/* 102 */     this.caperightsholder = new ModelRenderer((Model)this);
/* 103 */     this.caperightsholder.func_78793_a(-3.5F, -0.51F, -2.5F);
/* 104 */     this.cape.func_78792_a(this.caperightsholder);
/* 105 */     this.caperightsholder.func_78784_a(51, 75).func_228303_a_(-6.0F, 0.0F, 0.0F, 6.0F, 0.0F, 5.0F, 0.0F, false);
/*     */     
/* 107 */     this.capeleftsholder = new ModelRenderer((Model)this);
/* 108 */     this.capeleftsholder.func_78793_a(3.5F, -0.51F, -2.5F);
/* 109 */     this.cape.func_78792_a(this.capeleftsholder);
/* 110 */     this.capeleftsholder.func_78784_a(51, 75).func_228303_a_(0.0F, 0.0F, 0.0F, 6.0F, 0.0F, 5.0F, 0.0F, false);
/*     */     
/* 112 */     this.capeleftcollar1 = new ModelRenderer((Model)this);
/* 113 */     this.capeleftcollar1.func_78793_a(5.2F, -3.5F, -2.3F);
/* 114 */     this.cape.func_78792_a(this.capeleftcollar1);
/* 115 */     setRotationAngle(this.capeleftcollar1, -0.0169F, -0.1913F, 0.0888F);
/* 116 */     this.capeleftcollar1.func_78784_a(51, 81).func_228303_a_(0.0F, 0.0F, 0.0F, 0.0F, 3.0F, 5.0F, 0.0F, false);
/*     */     
/* 118 */     this.capeleftcollar2 = new ModelRenderer((Model)this);
/* 119 */     this.capeleftcollar2.func_78793_a(5.2F, -3.5F, -2.3F);
/* 120 */     this.cape.func_78792_a(this.capeleftcollar2);
/* 121 */     setRotationAngle(this.capeleftcollar2, 0.0202F, -0.1909F, -0.1066F);
/* 122 */     this.capeleftcollar2.func_78784_a(51, 90).func_228303_a_(0.0F, 0.0F, 0.0F, 0.0F, 2.0F, 5.0F, 0.0F, false);
/*     */     
/* 124 */     this.caperightcollar1 = new ModelRenderer((Model)this);
/* 125 */     this.caperightcollar1.func_78793_a(-5.2F, -3.5F, -2.3F);
/* 126 */     this.cape.func_78792_a(this.caperightcollar1);
/* 127 */     setRotationAngle(this.caperightcollar1, -0.0169F, 0.1913F, -0.0888F);
/* 128 */     this.caperightcollar1.func_78784_a(51, 81).func_228303_a_(0.0F, 0.0F, 0.0F, 0.0F, 3.0F, 5.0F, 0.0F, false);
/*     */     
/* 130 */     this.caperightcollar2 = new ModelRenderer((Model)this);
/* 131 */     this.caperightcollar2.func_78793_a(-5.2F, -3.5F, -2.3F);
/* 132 */     this.cape.func_78792_a(this.caperightcollar2);
/* 133 */     setRotationAngle(this.caperightcollar2, 0.0202F, 0.1909F, 0.1066F);
/* 134 */     this.caperightcollar2.func_78784_a(51, 90).func_228303_a_(0.0F, 0.0F, 0.0F, 0.0F, 2.0F, 5.0F, 0.0F, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 140 */     this.cape.func_217177_a(this.field_78115_e);
/* 141 */     this.cape.field_78798_e = 0.2F;
/* 142 */     this.cape.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 148 */     double dist = entity.func_70092_e(((LivingEntity)entity).field_70169_q, ((LivingEntity)entity).field_70167_r, ((LivingEntity)entity).field_70166_s);
/* 149 */     if (dist > 0.0D && dist <= 0.02D)
/* 150 */       dist += 0.02D; 
/* 151 */     double angle = MathHelper.func_151237_a(dist * 1000.0D - 1.0D, 0.0D, 70.0D);
/* 152 */     boolean isMoving = (dist > 0.02D);
/* 153 */     if (isMoving)
/* 154 */       angle += (MathHelper.func_76126_a((float)MathHelper.func_219803_d(angle, ((LivingEntity)entity).field_70141_P, ((LivingEntity)entity).field_70140_Q)) * 6.0F); 
/* 155 */     this.capeback.field_78795_f = (float)Math.toRadians(angle);
/* 156 */     this.caperightsholderpad2.field_78795_f = (float)Math.toRadians(-angle);
/* 157 */     this.capeleftsholderpad2.field_78795_f = (float)Math.toRadians(-angle);
/* 158 */     this.caperightarm.field_78795_f = (float)Math.toRadians(angle - (!isMoving ? false : 20));
/* 159 */     this.capeleftarm.field_78795_f = (float)Math.toRadians(angle - (!isMoving ? false : 20));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer model, float x, float y, float z) {
/* 164 */     model.field_78795_f = x;
/* 165 */     model.field_78796_g = y;
/* 166 */     model.field_78808_h = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\armors\CaptainCapeModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */