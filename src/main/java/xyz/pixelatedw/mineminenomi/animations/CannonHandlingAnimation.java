/*    */ package xyz.pixelatedw.mineminenomi.animations;
/*    */ 
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.Animation;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.AnimationId;
/*    */ 
/*    */ public class CannonHandlingAnimation
/*    */   extends Animation<LivingEntity, BipedModel> {
/*    */   public CannonHandlingAnimation(AnimationId<CannonHandlingAnimation> animId) {
/* 11 */     super(animId);
/* 12 */     setAnimationAngles(this::angles);
/*    */   }
/*    */   
/*    */   public void angles(LivingEntity entity, BipedModel model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 16 */     model.field_78115_e.field_78795_f = 0.5F;
/* 17 */     model.field_178723_h.field_78795_f -= 0.8F;
/* 18 */     model.field_178724_i.field_78795_f -= 0.8F;
/* 19 */     model.field_178721_j.field_78798_e = 4.0F;
/* 20 */     model.field_178722_k.field_78798_e = 4.0F;
/* 21 */     model.field_178721_j.field_78797_d = 12.2F;
/* 22 */     model.field_178722_k.field_78797_d = 12.2F;
/* 23 */     model.field_78116_c.field_78797_d = 4.2F;
/* 24 */     model.field_78115_e.field_78797_d = 3.2F;
/* 25 */     model.field_178724_i.field_78797_d = 5.2F;
/* 26 */     model.field_178723_h.field_78797_d = 5.2F;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\animations\CannonHandlingAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */