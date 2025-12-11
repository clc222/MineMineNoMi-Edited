/*    */ package xyz.pixelatedw.mineminenomi.animations;
/*    */ 
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.Animation;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.AnimationId;
/*    */ 
/*    */ public class PunchRushAnimation extends Animation<LivingEntity, BipedModel> {
/*    */   public PunchRushAnimation(AnimationId<PunchRushAnimation> animId) {
/* 11 */     super(animId);
/* 12 */     setAnimationAngles(this::angles);
/*    */   }
/*    */   
/*    */   public void angles(LivingEntity player, BipedModel model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 16 */     float rightArmMovement = -MathHelper.func_76134_b(ageInTicks * 2.2F);
/* 17 */     model.field_178723_h.field_78798_e = 5.0F * (0.5F - rightArmMovement);
/* 18 */     model.field_178723_h.field_78795_f = (float)Math.toRadians(-90.0D);
/* 19 */     model.field_178723_h.field_78796_g = 0.0F;
/*    */     
/* 21 */     float leftArmMovement = MathHelper.func_76134_b(ageInTicks * 2.2F);
/* 22 */     model.field_178724_i.field_78798_e = 5.0F * (0.5F - leftArmMovement);
/* 23 */     model.field_178724_i.field_78795_f = (float)Math.toRadians(-90.0D);
/* 24 */     model.field_178724_i.field_78796_g = 0.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\animations\PunchRushAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */