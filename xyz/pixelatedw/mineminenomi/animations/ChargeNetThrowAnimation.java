/*    */ package xyz.pixelatedw.mineminenomi.animations;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.model.ItemCameraTransforms;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.Hand;
/*    */ import net.minecraft.util.HandSide;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.Animation;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.AnimationId;
/*    */ 
/*    */ public class ChargeNetThrowAnimation
/*    */   extends Animation<LivingEntity, BipedModel>
/*    */ {
/*    */   public ChargeNetThrowAnimation(AnimationId<ChargeNetThrowAnimation> animId) {
/* 20 */     super(animId);
/* 21 */     setAnimationAngles(this::angles);
/* 22 */     setAnimationHeldItem(this::heldItem);
/*    */   }
/*    */   
/*    */   public void angles(LivingEntity entity, BipedModel model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 26 */     ItemStack itemstack = entity.func_184586_b(Hand.MAIN_HAND);
/*    */     
/* 28 */     int useTime = itemstack.func_77988_m() - entity.func_184605_cv();
/* 29 */     useTime = MathHelper.func_76125_a(useTime, 0, 20);
/*    */     
/* 31 */     double bodyYRot = 20.0D + useTime * 0.2D;
/* 32 */     double rightArmXRot = (-10 + useTime);
/* 33 */     double rightArmZRot = 5.0D + useTime * 0.3D;
/* 34 */     double leftArmXRot = 0.0D + useTime * 0.5D;
/* 35 */     double leftArmZRot = 25.0D + useTime * 0.5D;
/*    */     
/* 37 */     model.field_78115_e.field_78796_g = (float)Math.toRadians(bodyYRot);
/*    */     
/* 39 */     model.field_178723_h.field_78800_c = -4.0F;
/* 40 */     model.field_178723_h.field_78808_h = (float)Math.toRadians(rightArmZRot);
/* 41 */     model.field_178723_h.field_78795_f = (float)Math.toRadians(rightArmXRot);
/*    */     
/* 43 */     model.field_178724_i.field_78800_c = 1.0F;
/* 44 */     model.field_178724_i.field_78797_d = 1.75F;
/* 45 */     model.field_178724_i.field_78798_e = -2.5F;
/* 46 */     model.field_178724_i.field_78808_h = (float)Math.toRadians(leftArmZRot);
/* 47 */     model.field_178724_i.field_78795_f = (float)Math.toRadians(leftArmXRot);
/*    */   }
/*    */   
/*    */   public void heldItem(LivingEntity entity, ItemStack stack, ItemCameraTransforms.TransformType transformType, HandSide handSide, MatrixStack matrixStack, IRenderTypeBuffer renderBuffer, int packedLight) {
/* 51 */     matrixStack.func_227861_a_(-0.1D, -0.3D, -0.05D);
/* 52 */     matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(-5.0F));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\animations\ChargeNetThrowAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */