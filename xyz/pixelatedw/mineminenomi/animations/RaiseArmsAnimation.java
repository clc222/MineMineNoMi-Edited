/*    */ package xyz.pixelatedw.mineminenomi.animations;
/*    */ 
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.Animation;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.AnimationId;
/*    */ 
/*    */ public class RaiseArmsAnimation extends Animation<LivingEntity, BipedModel> {
/*    */   public RaiseArmsAnimation(AnimationId<RaiseArmsAnimation> animId) {
/* 10 */     super(animId);
/* 11 */     setAnimationAngles(this::angles);
/*    */   }
/*    */   
/*    */   public void angles(LivingEntity player, BipedModel model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 15 */     model.field_178723_h.field_78795_f = (float)Math.toRadians(180.0D);
/* 16 */     model.field_178723_h.field_78796_g = 0.0F;
/*    */     
/* 18 */     model.field_178724_i.field_78795_f = (float)Math.toRadians(180.0D);
/* 19 */     model.field_178724_i.field_78796_g = 0.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\animations\RaiseArmsAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */