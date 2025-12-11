/*    */ package xyz.pixelatedw.mineminenomi.animations;
/*    */ 
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.AnimationId;
/*    */ 
/*    */ public class BodyRotateWideArmsAnimation extends BodyRotateAnimation {
/*    */   public BodyRotateWideArmsAnimation(AnimationId<BodyRotateWideArmsAnimation> animId, float speed) {
/*  9 */     super((AnimationId)animId, speed);
/* 10 */     setAnimationAngles(this::angles);
/*    */   }
/*    */   
/*    */   public void angles(LivingEntity player, BipedModel model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 14 */     model.field_178723_h.field_78795_f = (float)Math.toRadians(-90.0D);
/* 15 */     model.field_178723_h.field_78808_h = (float)Math.toRadians(-90.0D);
/*    */     
/* 17 */     model.field_178724_i.field_78795_f = (float)Math.toRadians(90.0D);
/* 18 */     model.field_178724_i.field_78808_h = (float)Math.toRadians(-90.0D);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\animations\BodyRotateWideArmsAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */