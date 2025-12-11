/*    */ package xyz.pixelatedw.mineminenomi.animations;
/*    */ 
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.Animation;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.AnimationId;
/*    */ 
/*    */ public class UnicycleRidingAnimation
/*    */   extends Animation<LivingEntity, BipedModel>
/*    */ {
/*    */   public UnicycleRidingAnimation(AnimationId<UnicycleRidingAnimation> animId) {
/* 13 */     super(animId);
/* 14 */     setAnimationAngles(this::angles);
/*    */   }
/*    */   
/*    */   public void angles(LivingEntity entity, BipedModel model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 18 */     float rightLegY = 10.0F;
/* 19 */     float rightLegX = 0.0F;
/* 20 */     float leftLegY = 12.0F;
/* 21 */     float leftLegX = 0.0F;
/*    */     
/* 23 */     double xDiff = Math.abs(entity.func_226277_ct_() - entity.field_70142_S);
/* 24 */     double zDiff = Math.abs(entity.func_226281_cx_() - entity.field_70136_U);
/* 25 */     boolean isMoving = (xDiff != 0.0D || zDiff != 0.0D);
/*    */     
/* 27 */     if (isMoving || entity.field_191988_bg != 0.0F) {
/* 28 */       if (entity instanceof net.minecraft.entity.player.PlayerEntity) {
/* 29 */         float swingSpeed = 7.5F;
/* 30 */         rightLegY = 11.0F + -MathHelper.func_76134_b(limbSwing * swingSpeed) * 12.9F * limbSwingAmount / 1.0F;
/* 31 */         rightLegX = MathHelper.func_76126_a(limbSwing * swingSpeed + 3.1415927F) * 1.9F * limbSwingAmount / 1.0F;
/* 32 */         leftLegY = 11.0F + MathHelper.func_76134_b(limbSwing * swingSpeed) * 12.9F * limbSwingAmount / 1.0F;
/* 33 */         leftLegX = -MathHelper.func_76126_a(limbSwing * swingSpeed + 3.1415927F) * 1.9F * limbSwingAmount / 1.0F;
/*    */       } else {
/*    */         
/* 36 */         float swingSpeed = 0.8F;
/* 37 */         rightLegY = 11.0F + -MathHelper.func_76134_b(limbSwing * swingSpeed) * 0.9F * limbSwingAmount / 1.0F;
/* 38 */         rightLegX = MathHelper.func_76126_a(limbSwing * swingSpeed + 3.1415927F) * 0.3F * limbSwingAmount / 1.0F;
/* 39 */         leftLegY = 11.0F + MathHelper.func_76134_b(limbSwing * swingSpeed) * 0.9F * limbSwingAmount / 1.0F;
/* 40 */         leftLegX = -MathHelper.func_76126_a(limbSwing * swingSpeed + 3.1415927F) * 0.3F * limbSwingAmount / 1.0F;
/*    */       } 
/*    */     }
/*    */     
/* 44 */     model.field_178721_j.field_78798_e = -0.5F;
/* 45 */     model.field_178721_j.field_78797_d = rightLegY;
/* 46 */     model.field_178721_j.field_78800_c = -2.5F;
/* 47 */     model.field_178721_j.field_78795_f = rightLegX;
/* 48 */     model.field_178721_j.field_78796_g = 0.2F;
/* 49 */     model.field_178721_j.field_78808_h = 0.02F;
/*    */     
/* 51 */     model.field_178722_k.field_78798_e = -0.5F;
/* 52 */     model.field_178722_k.field_78797_d = leftLegY;
/* 53 */     model.field_178722_k.field_78800_c = 2.5F;
/* 54 */     model.field_178722_k.field_78795_f = leftLegX;
/* 55 */     model.field_178722_k.field_78796_g = -0.2F;
/* 56 */     model.field_178722_k.field_78808_h = -0.02F;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\animations\UnicycleRidingAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */