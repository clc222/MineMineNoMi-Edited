/*    */ package xyz.pixelatedw.mineminenomi.animations.mandemontactics;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.model.ItemCameraTransforms;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.HandSide;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.Animation;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.AnimationId;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
/*    */ 
/*    */ public class DemonicDanceLeapAnimation
/*    */   extends Animation<LivingEntity, BipedModel<LivingEntity>> {
/*    */   public DemonicDanceLeapAnimation(AnimationId<DemonicDanceLeapAnimation> animId) {
/* 19 */     super(animId);
/* 20 */     setAnimationSetup(this::setup);
/* 21 */     setAnimationAngles(this::angles);
/* 22 */     setAnimationHeldItem(this::heldItem);
/*    */   }
/*    */   
/*    */   public void setup(LivingEntity entity, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, float rotationYaw, float partialTicks) {
/* 26 */     if (!isInAir(entity)) {
/* 27 */       matrixStack.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(30.0F));
/*    */     }
/*    */   }
/*    */   
/*    */   public void angles(LivingEntity entity, BipedModel<LivingEntity> model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 32 */     if (isInAir(entity)) {
/* 33 */       model.field_178723_h.field_78795_f = (float)Math.toRadians(180.0D);
/* 34 */       model.field_178723_h.field_78808_h = (float)Math.toRadians(-10.0D);
/*    */       
/* 36 */       model.field_178721_j.field_78795_f = 1.2F;
/* 37 */       model.field_178721_j.field_78796_g = -0.1F;
/* 38 */       model.field_178721_j.field_78798_e = -1.0F;
/*    */       
/* 40 */       model.field_178722_k.field_78795_f = 1.2F;
/* 41 */       model.field_178722_k.field_78796_g = 0.1F;
/* 42 */       model.field_178722_k.field_78798_e = -1.0F;
/*    */     } else {
/*    */       
/* 45 */       model.field_178723_h.field_78795_f = (float)Math.toRadians(-90.0D);
/*    */       
/* 47 */       model.field_178721_j.field_78795_f = -1.2F;
/* 48 */       model.field_178721_j.field_78796_g = 0.3F;
/* 49 */       model.field_178721_j.field_78798_e = 1.0F;
/*    */       
/* 51 */       model.field_178722_k.field_78795_f = 1.2F;
/* 52 */       model.field_178722_k.field_78796_g = 0.1F;
/* 53 */       model.field_178722_k.field_78798_e = -1.0F;
/*    */     } 
/*    */   }
/*    */   
/*    */   public void heldItem(LivingEntity entity, ItemStack stack, ItemCameraTransforms.TransformType transformType, HandSide handSide, MatrixStack matrixStack, IRenderTypeBuffer renderBuffer, int packedLight) {
/* 58 */     if (handSide == HandSide.RIGHT) {
/* 59 */       matrixStack.func_227861_a_(0.0D, 0.0D, 0.15D);
/* 60 */       matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(180.0F));
/*    */     } 
/*    */   }
/*    */   
/*    */   private boolean isInAir(LivingEntity entity) {
/* 65 */     double groundDistance = DevilFruitHelper.getDifferenceToFloor((Entity)entity);
/*    */     
/* 67 */     return (getTime() <= 5L || groundDistance > 0.800000011920929D);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\animations\mandemontactics\DemonicDanceLeapAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */