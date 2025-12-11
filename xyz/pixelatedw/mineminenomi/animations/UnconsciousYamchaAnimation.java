/*    */ package xyz.pixelatedw.mineminenomi.animations;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.Animation;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.AnimationId;
/*    */ 
/*    */ public class UnconsciousYamchaAnimation
/*    */   extends Animation<LivingEntity, EntityModel<?>>
/*    */ {
/*    */   public UnconsciousYamchaAnimation(AnimationId<UnconsciousYamchaAnimation> animId) {
/* 17 */     super(animId);
/* 18 */     setAnimationSetup(this::setup);
/* 19 */     setAnimationAngles(this::angles);
/*    */   }
/*    */   
/*    */   public void angles(LivingEntity entity, EntityModel entityModel, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 23 */     if (entity.func_184218_aH()) {
/* 24 */       if (entityModel instanceof BipedModel) {
/* 25 */         BipedModel bipedModel = (BipedModel)entityModel;
/* 26 */         bipedModel.field_78116_c.field_78797_d = 1.5F;
/* 27 */         bipedModel.field_78116_c.field_78795_f = (float)Math.toRadians(50.0D);
/* 28 */         bipedModel.field_178723_h.field_78795_f += -0.62831855F;
/* 29 */         bipedModel.field_178724_i.field_78795_f += -0.62831855F;
/* 30 */         bipedModel.field_178721_j.field_78795_f = -1.4137167F;
/* 31 */         bipedModel.field_178721_j.field_78796_g = 0.31415927F;
/* 32 */         bipedModel.field_178721_j.field_78808_h = 0.07853982F;
/* 33 */         bipedModel.field_178722_k.field_78795_f = -1.4137167F;
/* 34 */         bipedModel.field_178722_k.field_78796_g = -0.31415927F;
/* 35 */         bipedModel.field_178722_k.field_78808_h = -0.07853982F;
/*    */       } 
/*    */       
/*    */       return;
/*    */     } 
/* 40 */     if (entityModel instanceof BipedModel) {
/* 41 */       BipedModel bipedModel = (BipedModel)entityModel;
/* 42 */       bipedModel.field_178722_k.field_78800_c = 1.0F;
/* 43 */       bipedModel.field_178722_k.field_78795_f = (float)Math.toRadians(5.0D);
/* 44 */       bipedModel.field_178722_k.field_78796_g = (float)Math.toRadians(90.0D);
/*    */       
/* 46 */       bipedModel.field_178721_j.field_78800_c = -4.0F;
/* 47 */       bipedModel.field_178721_j.field_78797_d = 10.0F;
/* 48 */       bipedModel.field_178721_j.field_78795_f = (float)Math.toRadians(10.0D);
/* 49 */       bipedModel.field_178721_j.field_78796_g = (float)Math.toRadians(90.0D);
/*    */       
/* 51 */       bipedModel.field_178724_i.field_78800_c = 1.5F;
/* 52 */       bipedModel.field_178724_i.field_78798_e = -1.2F;
/* 53 */       bipedModel.field_178724_i.field_78797_d = 5.0F;
/* 54 */       bipedModel.field_178724_i.field_78795_f = (float)Math.toRadians(-75.0D);
/* 55 */       bipedModel.field_178724_i.field_78796_g = (float)Math.toRadians(90.0D);
/*    */       
/* 57 */       bipedModel.field_178723_h.field_78798_e = -1.0F;
/* 58 */       bipedModel.field_178723_h.field_78795_f = (float)Math.toRadians(-160.0D);
/*    */       
/* 60 */       bipedModel.field_78115_e.field_78796_g = (float)Math.toRadians(30.0D);
/* 61 */       bipedModel.field_78115_e.field_78808_h = (float)Math.toRadians(5.0D);
/*    */       
/* 63 */       bipedModel.field_78116_c.field_78795_f = 0.0F;
/* 64 */       bipedModel.field_78116_c.field_78796_g = (float)Math.toRadians(40.0D);
/* 65 */       bipedModel.field_78116_c.field_78808_h = 0.0F;
/*    */     } 
/*    */   }
/*    */   
/*    */   public void setup(LivingEntity entity, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, float rotationYaw, float partialTicks) {
/* 70 */     if (entity.func_184218_aH()) {
/* 71 */       Entity vehicle = entity.func_184187_bx();
/* 72 */       entity.field_70177_z = vehicle.field_70177_z;
/* 73 */       entity.field_70125_A = vehicle.field_70125_A;
/* 74 */       matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(entity.field_70177_z + 90.0F));
/*    */       
/*    */       return;
/*    */     } 
/* 78 */     matrixStack.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(90.0F));
/* 79 */     matrixStack.func_227861_a_(0.0D, -0.5D, -1.35D);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\animations\UnconsciousYamchaAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */