/*    */ package xyz.pixelatedw.mineminenomi.animations.pteranodon;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.Animation;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.AnimationId;
/*    */ import xyz.pixelatedw.mineminenomi.models.morphs.PteranodonFlyModel;
/*    */ 
/*    */ public class PteraOpenMouthAnimation
/*    */   extends Animation<LivingEntity, PteranodonFlyModel> {
/*    */   public PteraOpenMouthAnimation(AnimationId<PteraOpenMouthAnimation> animId) {
/* 11 */     super(animId);
/* 12 */     setAnimationAngles(this::angles);
/*    */   }
/*    */   
/*    */   public void angles(LivingEntity player, PteranodonFlyModel model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 16 */     model.lowerBeck.field_78795_f = (float)Math.toRadians(40.0D);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\animations\pteranodon\PteraOpenMouthAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */