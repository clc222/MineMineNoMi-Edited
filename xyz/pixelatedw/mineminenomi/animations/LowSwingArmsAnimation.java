/*    */ package xyz.pixelatedw.mineminenomi.animations;
/*    */ 
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.Animation;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.AnimationId;
/*    */ 
/*    */ public class LowSwingArmsAnimation
/*    */   extends Animation<LivingEntity, BipedModel> {
/*    */   public LowSwingArmsAnimation(AnimationId<LowSwingArmsAnimation> animId) {
/* 11 */     super(animId);
/* 12 */     setAnimationAngles(this::angles);
/*    */   }
/*    */   
/*    */   public void angles(LivingEntity entity, BipedModel model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 16 */     float spread = 0.3F;
/* 17 */     float speed = 0.1F;
/*    */     
/* 19 */     model.field_178723_h.field_78795_f = (float)Math.toRadians(-10.0D);
/* 20 */     model.field_178723_h.field_78796_g = (float)(Math.cos(ageInTicks * Math.PI * speed) * spread);
/* 21 */     model.field_178723_h.field_78808_h = (float)(Math.sin(ageInTicks * Math.PI * speed) * spread);
/* 22 */     model.field_178723_h.field_78798_e = -2.0F;
/* 23 */     model.field_178723_h.field_78797_d = 2.0F;
/*    */     
/* 25 */     model.field_178723_h.field_78796_g = (float)(model.field_178723_h.field_78796_g + Math.toRadians(90.0D));
/*    */     
/* 27 */     model.field_178724_i.field_78795_f = (float)Math.toRadians(-10.0D);
/* 28 */     model.field_178724_i.field_78796_g = (float)(Math.cos(ageInTicks * Math.PI * speed) * spread);
/* 29 */     model.field_178724_i.field_78808_h = (float)(Math.sin(ageInTicks * Math.PI * speed) * spread);
/* 30 */     model.field_178724_i.field_78798_e = -2.0F;
/* 31 */     model.field_178724_i.field_78797_d = 2.0F;
/*    */     
/* 33 */     model.field_178724_i.field_78796_g = (float)(model.field_178724_i.field_78796_g - Math.toRadians(-90.0D));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\animations\LowSwingArmsAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */