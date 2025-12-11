/*    */ package xyz.pixelatedw.mineminenomi.animations;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.model.ItemCameraTransforms;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.HandSide;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.AnimationId;
/*    */ 
/*    */ public class SpinToWinAnimation
/*    */   extends BodyRotateAnimation
/*    */ {
/*    */   public SpinToWinAnimation(AnimationId<SpinToWinAnimation> animId, float speed) {
/* 17 */     super((AnimationId)animId, speed);
/* 18 */     setAnimationAngles(this::angles);
/* 19 */     setAnimationHeldItem(this::heldItem);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setup(LivingEntity entity, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, float rotationYaw, float partialTicks) {
/* 24 */     super.setup(entity, matrixStack, buffer, packedLight, rotationYaw, partialTicks);
/* 25 */     matrixStack.func_227861_a_(0.0D, 0.0D, 0.4000000059604645D);
/*    */   }
/*    */   
/*    */   private void angles(LivingEntity entity, BipedModel model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 29 */     model.field_178723_h.field_78795_f = (float)Math.toRadians(-90.0D);
/* 30 */     model.field_178723_h.field_78808_h = (float)Math.toRadians(20.0D);
/* 31 */     model.field_178723_h.field_78796_g = (float)Math.toRadians(-20.0D);
/*    */     
/* 33 */     model.field_178724_i.field_78795_f = (float)Math.toRadians(-90.0D);
/* 34 */     model.field_178724_i.field_78808_h = (float)Math.toRadians(-20.0D);
/* 35 */     model.field_178724_i.field_78796_g = (float)Math.toRadians(20.0D);
/*    */     
/* 37 */     model.field_78115_e.field_78797_d = 0.5F;
/* 38 */     model.field_78115_e.field_78795_f = (float)Math.toRadians(-15.0D);
/* 39 */     model.field_178721_j.field_78798_e = -3.0F;
/* 40 */     model.field_178721_j.field_78795_f = (float)Math.toRadians(-15.0D);
/* 41 */     model.field_178722_k.field_78798_e = -3.0F;
/* 42 */     model.field_178722_k.field_78795_f = (float)Math.toRadians(-15.0D);
/*    */   }
/*    */   
/*    */   private void heldItem(LivingEntity entity, ItemStack stack, ItemCameraTransforms.TransformType transformType, HandSide handSide, MatrixStack matrixStack, IRenderTypeBuffer renderBuffer, int packedLight) {
/* 46 */     matrixStack.func_227861_a_(-0.15D, -0.1D, -0.2D);
/* 47 */     matrixStack.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(-85.0F));
/* 48 */     matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(20.0F));
/* 49 */     matrixStack.func_227863_a_(Vector3f.field_229183_f_.func_229187_a_(-16.0F));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\animations\SpinToWinAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */