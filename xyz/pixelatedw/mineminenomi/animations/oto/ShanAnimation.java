/*    */ package xyz.pixelatedw.mineminenomi.animations.oto;
/*    */ 
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.Animation;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.AnimationId;
/*    */ 
/*    */ public class ShanAnimation extends Animation<LivingEntity, BipedModel> {
/*    */   private static final float SPEED = 20.0F;
/*    */   
/*    */   public ShanAnimation(AnimationId<ShanAnimation> animId) {
/* 13 */     super(animId);
/* 14 */     setAnimationAngles(this::angles);
/*    */   }
/*    */   
/*    */   public void angles(LivingEntity player, BipedModel model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 18 */     float angle = MathHelper.func_76131_a(-70.0F + (float)getTime() * 20.0F, -70.0F, -10.0F);
/* 19 */     model.field_178724_i.field_78808_h = (float)Math.toRadians(180.0D);
/* 20 */     model.field_178724_i.field_78796_g = (float)Math.toRadians(90.0D);
/* 21 */     model.field_178724_i.field_78795_f = (float)Math.toRadians(angle);
/*    */     
/* 23 */     model.field_178723_h.field_78808_h = (float)Math.toRadians(180.0D);
/* 24 */     model.field_178723_h.field_78796_g = (float)Math.toRadians(-90.0D);
/* 25 */     model.field_178723_h.field_78795_f = (float)Math.toRadians(angle);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\animations\oto\ShanAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */