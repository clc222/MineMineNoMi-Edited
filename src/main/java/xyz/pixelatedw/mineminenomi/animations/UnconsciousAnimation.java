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
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
/*    */ 
/*    */ public class UnconsciousAnimation
/*    */   extends Animation<LivingEntity, EntityModel<?>>
/*    */ {
/*    */   public UnconsciousAnimation(AnimationId<UnconsciousAnimation> animId) {
/* 18 */     super(animId);
/* 19 */     setAnimationSetup(this::setup);
/* 20 */     setAnimationAngles(this::angles);
/*    */   }
/*    */   
/*    */   public void angles(LivingEntity entity, EntityModel entityModel, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 24 */     RendererHelper.resetHumanoidModelToDefaultPivots(entityModel);
/*    */     
/* 26 */     if (entity.func_184218_aH()) {
/* 27 */       if (entityModel instanceof BipedModel) {
/* 28 */         BipedModel bipedModel = (BipedModel)entityModel;
/* 29 */         bipedModel.field_78116_c.field_78797_d = 1.5F;
/* 30 */         bipedModel.field_78116_c.field_78795_f = (float)Math.toRadians(50.0D);
/* 31 */         bipedModel.field_178723_h.field_78795_f += -0.62831855F;
/* 32 */         bipedModel.field_178724_i.field_78795_f += -0.62831855F;
/* 33 */         bipedModel.field_178721_j.field_78795_f = -1.4137167F;
/* 34 */         bipedModel.field_178721_j.field_78796_g = 0.31415927F;
/* 35 */         bipedModel.field_178721_j.field_78808_h = 0.07853982F;
/* 36 */         bipedModel.field_178722_k.field_78795_f = -1.4137167F;
/* 37 */         bipedModel.field_178722_k.field_78796_g = -0.31415927F;
/* 38 */         bipedModel.field_178722_k.field_78808_h = -0.07853982F;
/*    */       } 
/*    */       
/*    */       return;
/*    */     } 
/* 43 */     if (entityModel instanceof BipedModel) {
/* 44 */       BipedModel bipedModel = (BipedModel)entityModel;
/* 45 */       bipedModel.field_178722_k.field_78795_f = 0.0F;
/* 46 */       bipedModel.field_178721_j.field_78795_f = 0.0F;
/* 47 */       bipedModel.field_178724_i.field_78795_f = 0.0F;
/* 48 */       bipedModel.field_178724_i.field_78808_h = -0.05F;
/* 49 */       bipedModel.field_178723_h.field_78795_f = 0.0F;
/* 50 */       bipedModel.field_178723_h.field_78808_h = 0.05F;
/* 51 */       bipedModel.field_78116_c.field_78795_f = 0.0F;
/* 52 */       bipedModel.field_78116_c.field_78796_g = 0.0F;
/* 53 */       bipedModel.field_78116_c.field_78808_h = 0.0F;
/*    */     } 
/*    */   }
/*    */   
/*    */   public void setup(LivingEntity entity, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, float rotationYaw, float partialTicks) {
/* 58 */     if (entity.func_184218_aH()) {
/* 59 */       Entity vehicle = entity.func_184187_bx();
/* 60 */       entity.field_70177_z = vehicle.field_70177_z;
/* 61 */       entity.field_70125_A = vehicle.field_70125_A;
/* 62 */       matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(entity.field_70177_z + 90.0F));
/*    */       
/*    */       return;
/*    */     } 
/* 66 */     matrixStack.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(90.0F));
/* 67 */     matrixStack.func_227861_a_(0.0D, -0.5D, -1.4D);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\animations\UnconsciousAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */