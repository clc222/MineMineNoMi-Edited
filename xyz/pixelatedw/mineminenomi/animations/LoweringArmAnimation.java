/*    */ package xyz.pixelatedw.mineminenomi.animations;
/*    */ 
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.Animation;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.AnimationId;
/*    */ 
/*    */ public class LoweringArmAnimation
/*    */   extends Animation<LivingEntity, BipedModel> {
/*    */   public LoweringArmAnimation(AnimationId<LoweringArmAnimation> animId) {
/* 12 */     super(animId);
/* 13 */     setAnimationAngles(this::angles);
/*    */   }
/*    */   
/*    */   public void angles(LivingEntity entity, BipedModel model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 17 */     float time = (float)getTime();
/* 18 */     float armMovement = 0.0F;
/* 19 */     float waitTime = 15.0F;
/* 20 */     if (time > waitTime) {
/* 21 */       armMovement = MathHelper.func_76131_a(0.2F * (time - waitTime) * 3.1415927F, 0.0F, 3.0F);
/*    */     }
/*    */     
/* 24 */     model.field_178723_h.field_78795_f = (float)Math.toRadians(180.0D) + armMovement;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\animations\LoweringArmAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */