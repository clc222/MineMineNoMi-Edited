/*    */ package xyz.pixelatedw.mineminenomi.animations;
/*    */ 
/*    */ import net.minecraft.client.renderer.entity.model.AgeableModel;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.model.ModelRenderer;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.Animation;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.AnimationId;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.animals.DugongModel;
/*    */ 
/*    */ public class PointArmsAnimation extends Animation<LivingEntity, AgeableModel> {
/*    */   public PointArmsAnimation(AnimationId<PointArmsAnimation> animId) {
/* 13 */     super(animId);
/* 14 */     setAnimationAngles(this::angles);
/*    */   }
/*    */   
/*    */   public void angles(LivingEntity player, AgeableModel model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 18 */     ModelRenderer rightArm = null;
/* 19 */     ModelRenderer leftArm = null;
/*    */     
/* 21 */     if (model instanceof BipedModel) {
/* 22 */       BipedModel<?> biped = (BipedModel)model;
/* 23 */       rightArm = biped.field_178723_h;
/* 24 */       leftArm = biped.field_178724_i;
/*    */     }
/* 26 */     else if (model instanceof DugongModel) {
/* 27 */       DugongModel<?> dugong = (DugongModel)model;
/* 28 */       rightArm = dugong.rightArm;
/* 29 */       leftArm = dugong.leftArm;
/*    */     } 
/*    */     
/* 32 */     if (rightArm != null) {
/* 33 */       rightArm.field_78795_f = rightArm.field_78795_f * 0.5F - 3.1415927F + 1.8F;
/* 34 */       rightArm.field_78796_g = -0.2F;
/*    */     } 
/*    */     
/* 37 */     if (leftArm != null) {
/* 38 */       leftArm.field_78795_f = leftArm.field_78795_f * 0.5F - 3.1415927F + 1.8F;
/* 39 */       leftArm.field_78796_g = 0.2F;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\animations\PointArmsAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */