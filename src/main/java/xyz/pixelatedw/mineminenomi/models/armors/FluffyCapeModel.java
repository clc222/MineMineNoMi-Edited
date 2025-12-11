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
/*     */ public class FluffyCapeModel<T extends LivingEntity>
/*     */   extends BipedModel<T> {
/*     */   private final ModelRenderer cape;
/*     */   private final ModelRenderer capeback;
/*     */   private final ModelRenderer rightSleeve;
/*     */   private final ModelRenderer leftSleeve;
/*     */   private final ModelRenderer capeback3;
/*     */   private final ModelRenderer capeback2_r1;
/*     */   private final ModelRenderer capeback2;
/*     */   private final ModelRenderer capeback2_r2;
/*     */   private final ModelRenderer rightSholderPad;
/*     */   private final ModelRenderer leftSholderPad;
/*     */   
/*     */   public FluffyCapeModel() {
/*  29 */     super(1.0F);
/*  30 */     this.field_78090_t = 128;
/*  31 */     this.field_78089_u = 128;
/*     */     
/*  33 */     this.cape = new ModelRenderer((Model)this);
/*  34 */     this.cape.func_78793_a(0.0F, -1.0F, 0.0F);
/*     */     
/*  36 */     this.capeback = new ModelRenderer((Model)this);
/*  37 */     this.capeback.func_78793_a(-0.5F, 3.25F, 1.25F);
/*  38 */     this.cape.func_78792_a(this.capeback);
/*  39 */     setRotationAngle(this.capeback, 0.0436F, 0.0F, 0.0F);
/*  40 */     this.capeback.func_78784_a(0, 25).func_228303_a_(-10.0F, -3.25F, -0.25F, 21.0F, 22.0F, 3.0F, 0.0F, false);
/*     */     
/*  42 */     this.rightSleeve = new ModelRenderer((Model)this);
/*  43 */     this.rightSleeve.func_78793_a(-4.9F, -3.0F, 0.0F);
/*  44 */     this.capeback.func_78792_a(this.rightSleeve);
/*  45 */     this.rightSleeve.func_78784_a(0, 50).func_228303_a_(-6.1F, 0.0F, -2.75F, 4.0F, 16.0F, 4.0F, 0.0F, false);
/*     */     
/*  47 */     this.leftSleeve = new ModelRenderer((Model)this);
/*  48 */     this.leftSleeve.func_78793_a(7.1F, -3.0F, 0.0F);
/*  49 */     this.capeback.func_78792_a(this.leftSleeve);
/*  50 */     this.leftSleeve.func_78784_a(0, 50).func_228303_a_(0.9F, 0.0F, -2.75F, 4.0F, 16.0F, 4.0F, 0.0F, false);
/*     */     
/*  52 */     this.capeback3 = new ModelRenderer((Model)this);
/*  53 */     this.capeback3.func_78793_a(0.5F, 12.1287F, 3.3518F);
/*  54 */     this.capeback.func_78792_a(this.capeback3);
/*  55 */     setRotationAngle(this.capeback3, -0.0436F, 0.0F, 0.0F);
/*     */     
/*  57 */     this.capeback2_r1 = new ModelRenderer((Model)this);
/*  58 */     this.capeback2_r1.func_78793_a(0.0F, -3.1287F, -3.8518F);
/*  59 */     this.capeback3.func_78792_a(this.capeback2_r1);
/*  60 */     setRotationAngle(this.capeback2_r1, 0.1745F, 0.0F, 0.0F);
/*  61 */     this.capeback2_r1.func_78784_a(45, 47).func_228303_a_(-10.5F, -2.25F, 1.75F, 21.0F, 12.0F, 3.0F, -0.02F, false);
/*     */     
/*  63 */     this.capeback2 = new ModelRenderer((Model)this);
/*  64 */     this.capeback2.func_78793_a(0.0F, 0.0F, 0.0F);
/*  65 */     this.capeback.func_78792_a(this.capeback2);
/*     */     
/*  67 */     this.capeback2_r2 = new ModelRenderer((Model)this);
/*  68 */     this.capeback2_r2.func_78793_a(0.5F, 8.0F, 1.0F);
/*  69 */     this.capeback2.func_78792_a(this.capeback2_r2);
/*  70 */     setRotationAngle(this.capeback2_r2, 0.1745F, 0.0F, 0.0F);
/*  71 */     this.capeback2_r2.func_78784_a(0, 0).func_228303_a_(-10.5F, -11.25F, 1.75F, 21.0F, 21.0F, 4.0F, -0.01F, false);
/*     */     
/*  73 */     this.rightSholderPad = new ModelRenderer((Model)this);
/*  74 */     this.rightSholderPad.func_78793_a(6.9F, -2.75F, -2.25F);
/*  75 */     this.capeback.func_78792_a(this.rightSholderPad);
/*  76 */     setRotationAngle(this.rightSholderPad, -0.192F, 0.0F, 0.0F);
/*  77 */     this.rightSholderPad.func_78784_a(22, 0).func_228303_a_(-2.0F, -2.6F, -1.55F, 6.0F, 3.0F, 8.0F, 0.0F, false);
/*     */     
/*  79 */     this.leftSholderPad = new ModelRenderer((Model)this);
/*  80 */     this.leftSholderPad.func_78793_a(6.9F, -3.75F, -2.25F);
/*  81 */     this.capeback.func_78792_a(this.leftSholderPad);
/*  82 */     setRotationAngle(this.leftSholderPad, -0.192F, 0.0F, 0.0F);
/*  83 */     this.leftSholderPad.func_78784_a(22, 8).func_228303_a_(-16.8F, -1.6F, -1.3F, 6.0F, 3.0F, 8.0F, 0.0F, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/*  89 */     double dist = entity.func_70092_e(((LivingEntity)entity).field_70169_q, ((LivingEntity)entity).field_70167_r, ((LivingEntity)entity).field_70166_s);
/*  90 */     if (dist > 0.0D && dist <= 0.02D)
/*  91 */       dist += 0.02D; 
/*  92 */     double angle = MathHelper.func_151237_a(dist * 1000.0D - 1.0D, 0.0D, 70.0D);
/*  93 */     boolean isMoving = (dist > 0.02D);
/*  94 */     if (isMoving)
/*  95 */       angle += (MathHelper.func_76126_a((float)MathHelper.func_219803_d(angle, ((LivingEntity)entity).field_70141_P, ((LivingEntity)entity).field_70140_Q)) * 6.0F); 
/*  96 */     this.capeback.field_78795_f = (float)Math.toRadians(angle);
/*  97 */     this.rightSleeve.field_78795_f = (float)Math.toRadians(angle - (!isMoving ? false : 50));
/*  98 */     this.leftSleeve.field_78795_f = (float)Math.toRadians(angle - (!isMoving ? false : 50));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 104 */     this.cape.func_217177_a(this.field_78115_e);
/*     */     
/* 106 */     this.cape.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 111 */     modelRenderer.field_78795_f = x;
/* 112 */     modelRenderer.field_78796_g = y;
/* 113 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\armors\FluffyCapeModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */