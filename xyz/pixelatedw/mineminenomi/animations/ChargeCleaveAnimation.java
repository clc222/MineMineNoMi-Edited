/*    */ package xyz.pixelatedw.mineminenomi.animations;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.model.ItemCameraTransforms;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.HandSide;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.Animation;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.AnimationId;
/*    */ 
/*    */ public class ChargeCleaveAnimation
/*    */   extends Animation<LivingEntity, BipedModel>
/*    */ {
/*    */   public ChargeCleaveAnimation(AnimationId<ChargeCleaveAnimation> animId) {
/* 19 */     super(animId);
/* 20 */     setAnimationAngles(this::angles);
/* 21 */     setAnimationHeldItem(this::heldItem);
/*    */   }
/*    */   
/*    */   public void angles(LivingEntity player, BipedModel model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 25 */     float multiplier = 50.0F;
/* 26 */     float time = (float)getTime();
/* 27 */     float waitTime = 5.0F;
/*    */     
/* 29 */     double rightArmX = -200.0D;
/* 30 */     double rightArmY = 20.0D;
/*    */     
/* 32 */     double leftArmX = -130.0D;
/* 33 */     double leftArmY = 110.0D;
/*    */     
/* 35 */     if (time > waitTime) {
/* 36 */       double t2 = ((time - waitTime) * multiplier);
/* 37 */       rightArmX = MathHelper.func_151237_a(-200.0D + t2, -200.0D, -20.0D);
/* 38 */       rightArmY = MathHelper.func_151237_a(20.0D - t2 / 3.0D, -10.0D, 20.0D);
/*    */       
/* 40 */       leftArmX = MathHelper.func_151237_a(-130.0D + t2 / 2.0D, -130.0D, -50.0D);
/* 41 */       leftArmY = MathHelper.func_151237_a(110.0D - t2 / 3.0D, 50.0D, 110.0D);
/*    */     } 
/*    */     
/* 44 */     model.field_178723_h.field_78798_e += 0.0F;
/* 45 */     model.field_178723_h.field_78795_f = (float)(model.field_178723_h.field_78795_f + Math.toRadians(rightArmX));
/* 46 */     model.field_178723_h.field_78796_g = (float)(model.field_178723_h.field_78796_g + Math.toRadians(rightArmY));
/*    */     
/* 48 */     model.field_178724_i.field_78800_c -= 2.0F;
/* 49 */     model.field_178724_i.field_78798_e -= 2.0F;
/* 50 */     model.field_178724_i.field_78795_f = (float)(model.field_178724_i.field_78795_f + Math.toRadians(leftArmX));
/* 51 */     model.field_178724_i.field_78796_g = (float)(model.field_178724_i.field_78796_g + Math.toRadians(leftArmY));
/*    */     
/* 53 */     model.field_78115_e.field_78796_g = (float)(model.field_78115_e.field_78796_g + Math.toRadians(30.0D));
/*    */     
/* 55 */     model.field_178721_j.field_78798_e++;
/* 56 */     model.field_178721_j.field_78796_g = (float)(model.field_178721_j.field_78796_g + Math.toRadians(40.0D));
/*    */     
/* 58 */     model.field_178722_k.field_78798_e--;
/* 59 */     model.field_178722_k.field_78796_g = (float)(model.field_178722_k.field_78796_g - Math.toRadians(20.0D));
/*    */   }
/*    */   
/*    */   public void heldItem(LivingEntity entity, ItemStack stack, ItemCameraTransforms.TransformType transformType, HandSide handSide, MatrixStack matrixStack, IRenderTypeBuffer renderBuffer, int packedLight) {
/* 63 */     matrixStack.func_227861_a_(-0.1D, -0.2D, -0.05D);
/* 64 */     matrixStack.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(-75.0F));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\animations\ChargeCleaveAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */