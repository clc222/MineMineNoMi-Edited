/*    */ package xyz.pixelatedw.mineminenomi.models.blocks;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.client.renderer.model.Model;
/*    */ import net.minecraft.client.renderer.model.ModelRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class FlagModel extends EntityModel<Entity> {
/*    */   private final ModelRenderer flag1;
/*    */   private final ModelRenderer flag2;
/*    */   private final ModelRenderer flag3;
/*    */   
/*    */   public FlagModel() {
/* 17 */     this.field_78090_t = 64;
/* 18 */     this.field_78089_u = 32;
/*    */     
/* 20 */     this.flag1 = new ModelRenderer((Model)this);
/* 21 */     this.flag1.func_78793_a(-8.0F, 16.0F, 0.0F);
/* 22 */     this.flag1.func_78784_a(0, 0).func_228303_a_(0.0F, -8.0F, 0.0F, 8.0F, 16.0F, 0.0F, 0.0F, false);
/*    */     
/* 24 */     this.flag2 = new ModelRenderer((Model)this);
/* 25 */     this.flag2.func_78793_a(8.0F, 0.0F, 0.0F);
/* 26 */     this.flag1.func_78792_a(this.flag2);
/* 27 */     this.flag2.func_78784_a(8, 0).func_228303_a_(0.0F, -8.0F, 0.0F, 8.0F, 16.0F, 0.0F, 0.0F, false);
/*    */     
/* 29 */     this.flag3 = new ModelRenderer((Model)this);
/* 30 */     this.flag3.func_78793_a(8.0F, 0.0F, 0.0F);
/* 31 */     this.flag2.func_78792_a(this.flag3);
/* 32 */     this.flag3.func_78784_a(16, 0).func_228303_a_(0.0F, -8.0F, 0.0F, 8.0F, 16.0F, 0.0F, 0.0F, false);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_225597_a_(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*    */ 
/*    */   
/*    */   public void flagAnimation(World world, long flutterTick) {
/* 40 */     this.flag1.field_78797_d = 16.0F;
/* 41 */     this.flag1.field_78800_c = -8.0F;
/* 42 */     this.flag2.field_78797_d = 0.0F;
/* 43 */     this.flag2.field_78800_c = 8.0F;
/* 44 */     this.flag3.field_78797_d = 0.0F;
/* 45 */     this.flag3.field_78800_c = 8.0F;
/*    */     
/* 47 */     this.flag1.field_78797_d += (float)(Math.cos(((float)flutterTick * 0.05F) + Math.PI) * 0.20000000298023224D);
/* 48 */     this.flag2.field_78797_d += (float)(Math.cos(((float)flutterTick * 0.1F) + Math.PI) * 0.20000000298023224D);
/* 49 */     this.flag3.field_78797_d += (float)(Math.cos(((float)flutterTick * 0.1F) + Math.PI) * 0.20000000298023224D);
/*    */     
/* 51 */     this.flag1.field_78796_g = (float)(Math.cos(((float)flutterTick * 0.12F + 3.1415927F)) * 0.019999999552965164D);
/* 52 */     this.flag2.field_78796_g = (float)(Math.cos(((float)flutterTick * 0.1F + 3.1415927F)) * 0.019999999552965164D);
/* 53 */     this.flag3.field_78796_g = (float)(Math.cos(((float)flutterTick * 0.05F + 3.1415927F)) * 0.029999999329447746D);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 58 */     this.flag1.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*    */   }
/*    */   
/*    */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 62 */     modelRenderer.field_78795_f = x;
/* 63 */     modelRenderer.field_78796_g = y;
/* 64 */     modelRenderer.field_78808_h = z;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\blocks\FlagModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */