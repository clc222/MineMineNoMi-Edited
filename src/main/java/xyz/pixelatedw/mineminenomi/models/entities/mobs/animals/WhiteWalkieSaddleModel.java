/*    */ package xyz.pixelatedw.mineminenomi.models.entities.mobs.animals;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.model.Model;
/*    */ import net.minecraft.client.renderer.model.ModelRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.WhiteWalkieEntity;
/*    */ 
/*    */ public class WhiteWalkieSaddleModel<T extends LivingEntity>
/*    */   extends BipedModel<T> {
/*    */   private final ModelRenderer leftBag;
/*    */   private final ModelRenderer rightBag;
/*    */   
/*    */   public WhiteWalkieSaddleModel() {
/* 18 */     super(1.0F);
/* 19 */     this.field_78090_t = 128;
/* 20 */     this.field_78089_u = 128;
/*    */     
/* 22 */     this.leftBag = new ModelRenderer((Model)this);
/* 23 */     this.leftBag.func_78793_a(14.9375F, 5.4511F, 2.7092F);
/* 24 */     this.leftBag.func_78784_a(0, 0).func_228303_a_(-2.5F, -2.5F, 1.5F, 5.0F, 5.0F, 4.0F, 0.1F, false);
/* 25 */     this.leftBag.func_78784_a(0, 0).func_228303_a_(-2.5F, -2.5F, -5.5F, 5.0F, 5.0F, 4.0F, 0.1F, false);
/* 26 */     this.leftBag.func_78784_a(54, 23).func_228303_a_(-2.5F, -2.5F, -9.5F, 5.0F, 5.0F, 19.0F, 0.0F, false);
/*    */     
/* 28 */     this.rightBag = new ModelRenderer((Model)this);
/* 29 */     this.rightBag.func_78793_a(-14.9375F, 5.4511F, 2.7092F);
/* 30 */     this.rightBag.func_78784_a(0, 0).func_228303_a_(-2.5F, -2.5F, 1.5F, 5.0F, 5.0F, 4.0F, 0.1F, false);
/* 31 */     this.rightBag.func_78784_a(54, 23).func_228303_a_(-2.5F, -2.5F, -9.5F, 5.0F, 5.0F, 19.0F, 0.0F, false);
/* 32 */     this.rightBag.func_78784_a(0, 0).func_228303_a_(-2.5F, -2.5F, -5.5F, 5.0F, 5.0F, 4.0F, 0.1F, false);
/*    */     
/* 34 */     this.saddle = new ModelRenderer((Model)this);
/* 35 */     this.saddle.func_78793_a(0.0F, 3.4271F, 9.751F);
/* 36 */     this.saddle.func_78784_a(0, 0).func_228303_a_(-11.0F, -11.0385F, -17.5417F, 22.0F, 2.0F, 21.0F, 0.4F, false);
/* 37 */     this.saddle.func_78784_a(0, 24).func_228303_a_(-13.4375F, -10.726F, -17.5417F, 3.0F, 16.0F, 21.0F, 0.0F, false);
/* 38 */     this.saddle.func_78784_a(0, 24).func_228303_a_(10.4375F, -10.726F, -17.5417F, 3.0F, 16.0F, 21.0F, 0.0F, false);
/*    */     
/* 40 */     this.saddlechair = new ModelRenderer((Model)this);
/* 41 */     this.saddlechair.func_78793_a(0.0F, -15.5214F, 1.7193F);
/* 42 */     this.saddle.func_78792_a(this.saddlechair);
/* 43 */     setRotationAngle(this.saddlechair, -0.1309F, 0.0F, 0.0F);
/* 44 */     this.saddlechair.func_78784_a(30, 26).func_228303_a_(-6.0F, -4.5F, -1.0F, 12.0F, 9.0F, 2.0F, 0.4F, false);
/*    */   }
/*    */   private final ModelRenderer saddle; private final ModelRenderer saddlechair;
/*    */   
/*    */   public void func_212843_a_(T entity, float pLimbSwing, float pLimbSwingAmount, float pPartialTick) {
/* 49 */     if (entity instanceof WhiteWalkieEntity) {
/* 50 */       WhiteWalkieEntity entityW = (WhiteWalkieEntity)entity;
/* 51 */       if (entityW.getChests() <= 0) {
/* 52 */         this.rightBag.field_78806_j = false;
/* 53 */         this.leftBag.field_78806_j = false;
/*    */       }
/* 55 */       else if (entityW.getChests() == 1) {
/* 56 */         this.rightBag.field_78806_j = true;
/* 57 */         this.leftBag.field_78806_j = false;
/*    */       } else {
/*    */         
/* 60 */         this.rightBag.field_78806_j = true;
/* 61 */         this.leftBag.field_78806_j = true;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 73 */     this.leftBag.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 74 */     this.rightBag.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 75 */     this.saddle.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*    */   }
/*    */   
/*    */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 79 */     modelRenderer.field_78795_f = x;
/* 80 */     modelRenderer.field_78796_g = y;
/* 81 */     modelRenderer.field_78808_h = z;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\entities\mobs\animals\WhiteWalkieSaddleModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */