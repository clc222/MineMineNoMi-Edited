/*    */ package xyz.pixelatedw.mineminenomi.animations;
/*    */ 
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.Animation;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.AnimationId;
/*    */ 
/*    */ public class HandcuffedAnimation
/*    */   extends Animation<LivingEntity, BipedModel> {
/*    */   public HandcuffedAnimation(AnimationId<HandcuffedAnimation> animId) {
/* 11 */     super(animId);
/* 12 */     setAnimationAngles(this::angles);
/*    */   }
/*    */   
/*    */   public void angles(LivingEntity player, BipedModel model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 16 */     model.field_178723_h.field_78795_f = (float)Math.toRadians(-40.0D);
/* 17 */     model.field_178724_i.field_78795_f = (float)Math.toRadians(-40.0D);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\animations\HandcuffedAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */