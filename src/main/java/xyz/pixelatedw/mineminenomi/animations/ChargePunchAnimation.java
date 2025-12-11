/*    */ package xyz.pixelatedw.mineminenomi.animations;
/*    */ 
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.Animation;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.AnimationId;
/*    */ 
/*    */ public class ChargePunchAnimation
/*    */   extends Animation<LivingEntity, BipedModel> {
/*    */   public ChargePunchAnimation(AnimationId<ChargePunchAnimation> animId) {
/* 12 */     super(animId);
/* 13 */     setAnimationAngles(this::angles);
/*    */   }
/*    */   
/*    */   public void angles(LivingEntity entity, BipedModel model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 17 */     float time = (float)getTime() * 0.1F;
/*    */     
/* 19 */     model.field_178723_h.field_78798_e += 4.0F;
/* 20 */     model.field_178723_h.field_78796_g = (float)Math.toRadians(20.0D);
/* 21 */     model.field_178723_h.field_78795_f = (float)Math.toRadians(-90.0D) + MathHelper.func_76126_a((float)(time * Math.PI * 1.8D)) / 7.0F;
/* 22 */     model.field_178723_h.field_78808_h = MathHelper.func_76134_b((float)(time * Math.PI * 1.8D)) / 7.0F;
/*    */     
/* 24 */     model.field_178724_i.field_78795_f = (float)Math.toRadians(-80.0D);
/* 25 */     model.field_178724_i.field_78796_g = (float)Math.toRadians(15.0D);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\animations\ChargePunchAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */