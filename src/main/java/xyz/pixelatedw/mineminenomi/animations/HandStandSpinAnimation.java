/*    */ package xyz.pixelatedw.mineminenomi.animations;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.AnimationId;
/*    */ 
/*    */ public class HandStandSpinAnimation
/*    */   extends BodyRotateAnimation {
/*    */   public HandStandSpinAnimation(AnimationId<HandStandSpinAnimation> animId) {
/* 13 */     super((AnimationId)animId, 35.0F);
/* 14 */     setAnimationSetup(this::setup);
/* 15 */     setAnimationAngles(this::angles);
/*    */   }
/*    */   
/*    */   public void angles(LivingEntity player, BipedModel model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 19 */     model.field_178724_i.field_78795_f = (float)Math.toRadians(180.0D);
/* 20 */     model.field_178724_i.field_78808_h = (float)Math.toRadians(10.0D);
/* 21 */     model.field_178723_h.field_78795_f = (float)Math.toRadians(180.0D);
/* 22 */     model.field_178723_h.field_78808_h = (float)Math.toRadians(-10.0D);
/* 23 */     model.field_178721_j.field_78808_h = (float)Math.toRadians(90.0D);
/* 24 */     model.field_178722_k.field_78808_h = (float)Math.toRadians(-90.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setup(LivingEntity player, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, float rotationYaw, float partialTicks) {
/* 29 */     matrixStack.func_227861_a_(0.0D, 1.0D, 0.0D);
/* 30 */     matrixStack.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(180.0F));
/* 31 */     super.setup(player, matrixStack, buffer, packedLight, rotationYaw, partialTicks);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\animations\HandStandSpinAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */