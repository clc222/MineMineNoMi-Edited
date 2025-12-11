/*    */ package xyz.pixelatedw.mineminenomi.animations.oto;
/*    */ 
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.Animation;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.AnimationId;
/*    */ 
/*    */ public class DonAnimation
/*    */   extends Animation<LivingEntity, BipedModel> {
/*    */   private static final float SPEED = 20.0F;
/*    */   
/*    */   public DonAnimation(AnimationId<DonAnimation> animId) {
/* 14 */     super(animId);
/* 15 */     setAnimationAngles(this::angles);
/*    */   }
/*    */   
/*    */   public void angles(LivingEntity player, BipedModel model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 19 */     float angle = MathHelper.func_76131_a(-70.0F + (float)getTime() * 20.0F, -70.0F, -10.0F);
/* 20 */     model.field_178724_i.field_78808_h = (float)Math.toRadians(80.0D);
/* 21 */     model.field_178724_i.field_78795_f = (float)Math.toRadians(angle);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\animations\oto\DonAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */