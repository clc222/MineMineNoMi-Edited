/*    */ package xyz.pixelatedw.mineminenomi.animations.pacifista;
/*    */ 
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.Animation;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.AnimationId;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class HeadLaserChargeAnimation
/*    */   extends Animation<LivingEntity, BipedModel> {
/*    */   public HeadLaserChargeAnimation(AnimationId<HeadLaserChargeAnimation> animId) {
/* 12 */     super(animId);
/* 13 */     setAnimationAngles(this::angles);
/*    */   }
/*    */   
/*    */   public void angles(LivingEntity entity, BipedModel entityModel, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 17 */     entityModel.field_78116_c.field_78797_d += 3.0F;
/* 18 */     entityModel.field_78116_c.field_78798_e = (float)(WyHelper.randomDouble(entity.func_70681_au()) / 4.0D);
/* 19 */     entityModel.field_78116_c.field_78800_c = (float)(WyHelper.randomDouble(entity.func_70681_au()) / 4.0D);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\animations\pacifista\HeadLaserChargeAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */