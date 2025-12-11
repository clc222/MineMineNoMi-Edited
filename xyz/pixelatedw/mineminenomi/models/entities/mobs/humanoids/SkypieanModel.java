/*    */ package xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.renderer.model.Model;
/*    */ import net.minecraft.client.renderer.model.ModelRenderer;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*    */ 
/*    */ 
/*    */ @OnlyIn(Dist.CLIENT)
/*    */ public class SkypieanModel<T extends OPEntity>
/*    */   extends HumanoidModel<T>
/*    */ {
/*    */   private final ModelRenderer antennas;
/*    */   private final ModelRenderer antennas_2;
/*    */   private final ModelRenderer antenna_2_ball;
/*    */   private final ModelRenderer antennas_1;
/*    */   private final ModelRenderer antenna_1_ball;
/*    */   private final ModelRenderer wings;
/*    */   private final ModelRenderer wing_1;
/*    */   private final ModelRenderer wing_1_r1;
/*    */   private final ModelRenderer wing_2;
/*    */   private final ModelRenderer wing_2_r1;
/*    */   
/*    */   public SkypieanModel() {
/* 28 */     this.field_78090_t = 64;
/* 29 */     this.field_78089_u = 64;
/*    */     
/* 31 */     this.antennas = new ModelRenderer((Model)this);
/* 32 */     this.antennas.func_78793_a(0.0F, 28.0F, 0.0F);
/*    */     
/* 34 */     this.antennas_2 = new ModelRenderer((Model)this);
/* 35 */     this.antennas_2.func_78793_a(3.0F, -5.5F, 0.0F);
/* 36 */     this.antennas.func_78792_a(this.antennas_2);
/* 37 */     setRotationAngle(this.antennas_2, 0.0F, 0.0F, 0.5236F);
/* 38 */     this.antennas_2.func_78784_a(0, 34).func_228303_a_(-1.0F, -4.0F, 0.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
/*    */     
/* 40 */     this.antenna_2_ball = new ModelRenderer((Model)this);
/* 41 */     this.antenna_2_ball.func_78793_a(0.0F, 0.0F, 0.0F);
/* 42 */     this.antennas_2.func_78792_a(this.antenna_2_ball);
/* 43 */     this.antenna_2_ball.func_78784_a(6, 35).func_228303_a_(-1.5F, -6.0F, -0.5F, 2.0F, 2.0F, 2.0F, 0.0F, false);
/*    */     
/* 45 */     this.antennas_1 = new ModelRenderer((Model)this);
/* 46 */     this.antennas_1.func_78793_a(-2.0F, -6.0F, 0.0F);
/* 47 */     this.antennas.func_78792_a(this.antennas_1);
/* 48 */     setRotationAngle(this.antennas_1, 0.0F, 0.0F, -0.5236F);
/* 49 */     this.antennas_1.func_78784_a(0, 34).func_228303_a_(-1.0F, -4.0F, 0.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
/*    */     
/* 51 */     this.antenna_1_ball = new ModelRenderer((Model)this);
/* 52 */     this.antenna_1_ball.func_78793_a(0.0F, 0.0F, 0.0F);
/* 53 */     this.antennas_1.func_78792_a(this.antenna_1_ball);
/* 54 */     this.antenna_1_ball.func_78784_a(6, 35).func_228303_a_(-1.5F, -6.0F, -0.5F, 2.0F, 2.0F, 2.0F, 0.0F, false);
/*    */     
/* 56 */     this.wings = new ModelRenderer((Model)this);
/* 57 */     this.wings.func_78793_a(0.0F, 27.0F, -1.0F);
/*    */     
/* 59 */     this.wing_1 = new ModelRenderer((Model)this);
/* 60 */     this.wing_1.func_78793_a(-3.0F, 6.0F, 1.0F);
/* 61 */     this.wings.func_78792_a(this.wing_1);
/* 62 */     setRotationAngle(this.wing_1, 0.0F, 0.0F, 0.1309F);
/*    */     
/* 64 */     this.wing_1_r1 = new ModelRenderer((Model)this);
/* 65 */     this.wing_1_r1.func_78793_a(0.0F, -5.0F, 0.0F);
/* 66 */     this.wing_1.func_78792_a(this.wing_1_r1);
/* 67 */     setRotationAngle(this.wing_1_r1, 0.0F, 0.1309F, 0.0F);
/* 68 */     this.wing_1_r1.func_78784_a(16, 33).func_228303_a_(-11.0F, -1.0F, 1.0F, 11.0F, 6.0F, 0.0F, 0.0F, false);
/*    */     
/* 70 */     this.wing_2 = new ModelRenderer((Model)this);
/* 71 */     this.wing_2.func_78793_a(3.0F, 6.0F, 2.0F);
/* 72 */     this.wings.func_78792_a(this.wing_2);
/* 73 */     setRotationAngle(this.wing_2, 0.0F, 3.1416F, -0.1309F);
/*    */     
/* 75 */     this.wing_2_r1 = new ModelRenderer((Model)this);
/* 76 */     this.wing_2_r1.func_78793_a(0.0F, -5.0F, 0.0F);
/* 77 */     this.wing_2.func_78792_a(this.wing_2_r1);
/* 78 */     setRotationAngle(this.wing_2_r1, 0.0F, -0.1309F, 0.0F);
/* 79 */     this.wing_2_r1.func_78784_a(16, 33).func_228303_a_(-11.0F, -1.0F, 0.0F, 11.0F, 6.0F, 0.0F, 0.0F, false);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 85 */     super.func_225598_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 86 */     this.antennas.func_217177_a(this.field_78116_c);
/* 87 */     this.antennas.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/* 88 */     this.wings.func_217177_a(this.field_78115_e);
/* 89 */     this.wings.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\entities\mobs\humanoids\SkypieanModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */