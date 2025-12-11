/*    */ package xyz.pixelatedw.mineminenomi.animations.donkrieg;
/*    */ 
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.Animation;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.AnimationId;
/*    */ 
/*    */ public class MH5Animation
/*    */   extends Animation<LivingEntity, BipedModel> {
/*    */   public MH5Animation(AnimationId<MH5Animation> animId) {
/* 11 */     super(animId);
/* 12 */     setAnimationAngles(this::angles);
/*    */   }
/*    */   
/*    */   public void angles(LivingEntity entity, BipedModel model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 16 */     model.field_178724_i.field_78795_f = (float)Math.toRadians(-90.0D);
/* 17 */     model.field_178724_i.field_78796_g = (float)Math.toRadians(80.0D);
/* 18 */     model.field_178724_i.field_78798_e -= 2.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\animations\donkrieg\MH5Animation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */