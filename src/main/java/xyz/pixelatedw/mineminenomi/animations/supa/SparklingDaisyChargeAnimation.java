/*    */ package xyz.pixelatedw.mineminenomi.animations.supa;
/*    */ 
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.Animation;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.AnimationId;
/*    */ 
/*    */ public class SparklingDaisyChargeAnimation
/*    */   extends Animation<LivingEntity, BipedModel> {
/*    */   private static final float SPEED = 0.07F;
/*    */   
/*    */   public SparklingDaisyChargeAnimation(AnimationId<SparklingDaisyChargeAnimation> animId) {
/* 14 */     super(animId);
/* 15 */     setAnimationAngles(this::angles);
/*    */   }
/*    */   
/*    */   public void angles(LivingEntity entity, BipedModel model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 19 */     float time = (float)getTime() * 0.07F;
/* 20 */     float waitTime = 0.7F;
/*    */     
/* 22 */     if (time < waitTime) {
/* 23 */       model.field_178723_h.field_78795_f = (float)Math.toRadians(300.0D);
/* 24 */       model.field_178723_h.field_78796_g = (float)Math.toRadians(30.0D);
/* 25 */       model.field_178723_h.field_78808_h = (float)Math.toRadians(-30.0D);
/*    */       
/* 27 */       model.field_178724_i.field_78795_f = (float)Math.toRadians(300.0D);
/* 28 */       model.field_178724_i.field_78796_g = (float)Math.toRadians(-30.0D);
/* 29 */       model.field_178724_i.field_78808_h = (float)Math.toRadians(30.0D);
/*    */     } else {
/*    */       
/* 32 */       double t2 = (time - waitTime);
/* 33 */       model.field_178723_h.field_78795_f = (float)Math.toRadians(300.0D);
/* 34 */       model.field_178723_h.field_78796_g = (float)MathHelper.func_151237_a(t2 + (float)Math.toRadians(30.0D), 0.0D, 2.5D);
/* 35 */       model.field_178723_h.field_78808_h = (float)Math.toRadians(-30.0D);
/*    */       
/* 37 */       model.field_178724_i.field_78795_f = (float)Math.toRadians(300.0D);
/* 38 */       model.field_178724_i.field_78796_g = (float)MathHelper.func_151237_a(-t2 + (float)Math.toRadians(-30.0D), -2.5D, 0.0D);
/* 39 */       model.field_178724_i.field_78808_h = (float)Math.toRadians(30.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\animations\supa\SparklingDaisyChargeAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */