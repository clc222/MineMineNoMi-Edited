/*    */ package xyz.pixelatedw.mineminenomi.animations;
/*    */ 
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.Animation;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.AnimationId;
/*    */ 
/*    */ public class ThrowChakramAnimation
/*    */   extends Animation<LivingEntity, BipedModel> {
/*    */   public ThrowChakramAnimation(AnimationId<ThrowChakramAnimation> animId) {
/* 12 */     super(animId);
/* 13 */     setAnimationAngles(this::angles);
/*    */   }
/*    */   
/*    */   public void angles(LivingEntity player, BipedModel model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 17 */     long time = getTime();
/* 18 */     time = MathHelper.func_226163_a_(time, 0L, 40L) * 4L;
/*    */     
/* 20 */     double xRot = (-130L - time);
/* 21 */     xRot = MathHelper.func_151237_a(xRot, -170.0D, -130.0D);
/*    */     
/* 23 */     model.field_178723_h.field_78798_e = -3.0F;
/* 24 */     model.field_178723_h.field_78797_d = 3.5F;
/* 25 */     model.field_178723_h.field_78796_g = 0.0F;
/* 26 */     model.field_178723_h.field_78808_h = (float)Math.toRadians(95.0D);
/* 27 */     model.field_178723_h.field_78795_f = (float)Math.toRadians(xRot);
/*    */ 
/*    */     
/* 30 */     model.field_178724_i.field_78795_f = 0.0F;
/* 31 */     model.field_178724_i.field_78796_g = 0.0F;
/* 32 */     model.field_178724_i.field_78808_h = 0.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\animations\ThrowChakramAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */