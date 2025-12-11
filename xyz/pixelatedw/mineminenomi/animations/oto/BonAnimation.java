/*    */ package xyz.pixelatedw.mineminenomi.animations.oto;
/*    */ 
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.Animation;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.AnimationId;
/*    */ 
/*    */ public class BonAnimation
/*    */   extends Animation<LivingEntity, BipedModel> {
/*    */   private static final float SPEED = 15.0F;
/*    */   
/*    */   public BonAnimation(AnimationId<BonAnimation> animId) {
/* 14 */     super(animId);
/* 15 */     setAnimationAngles(this::angles);
/*    */   }
/*    */   
/*    */   public void angles(LivingEntity player, BipedModel model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 19 */     model.field_178724_i.field_78808_h = (float)Math.toRadians(30.0D);
/* 20 */     model.field_178724_i.field_78795_f = (float)Math.toRadians(-50.0D);
/*    */     
/* 22 */     model.field_178723_h.field_78808_h = (float)Math.toRadians(-60.0D);
/* 23 */     float angle = MathHelper.func_76131_a(0.0F + (float)getTime() * 15.0F, 0.0F, 30.0F);
/* 24 */     model.field_178723_h.field_78795_f = (float)Math.toRadians((-60.0F + angle));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\animations\oto\BonAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */