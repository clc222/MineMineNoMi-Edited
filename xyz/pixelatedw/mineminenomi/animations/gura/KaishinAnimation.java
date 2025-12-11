/*    */ package xyz.pixelatedw.mineminenomi.animations.gura;
/*    */ 
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.Animation;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.AnimationId;
/*    */ 
/*    */ public class KaishinAnimation
/*    */   extends Animation<LivingEntity, BipedModel> {
/*    */   private static final double SPEED = 20.0D;
/*    */   
/*    */   public KaishinAnimation(AnimationId<KaishinAnimation> animId) {
/* 14 */     super(animId);
/* 15 */     setAnimationAngles(this::angles);
/*    */   }
/*    */   
/*    */   public void angles(LivingEntity player, BipedModel model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 19 */     double angle = MathHelper.func_151237_a((80L - getTime()) * 20.0D, -50.0D, 80.0D);
/* 20 */     model.field_178724_i.field_78808_h = (float)Math.toRadians(angle);
/* 21 */     model.field_178724_i.field_78795_f = (float)Math.toRadians(-10.0D);
/*    */     
/* 23 */     model.field_178723_h.field_78808_h = (float)Math.toRadians(-angle);
/* 24 */     model.field_178723_h.field_78795_f = (float)Math.toRadians(-10.0D);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\animations\gura\KaishinAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */