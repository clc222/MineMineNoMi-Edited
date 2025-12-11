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
/*    */ public class CrossedArmsAnimation
/*    */   extends Animation<LivingEntity, AgeableModel> {
/*    */   public CrossedArmsAnimation(AnimationId<CrossedArmsAnimation> animId) {
/* 14 */     super(animId);
/* 15 */     setAnimationAngles(this::angles);
/*    */   }
/*    */   
/*    */   public void angles(LivingEntity entity, AgeableModel model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 19 */     ModelRenderer rightArm = null;
/* 20 */     ModelRenderer leftArm = null;
/*    */     
/* 22 */     if (model instanceof BipedModel) {
/* 23 */       BipedModel<?> biped = (BipedModel)model;
/* 24 */       rightArm = biped.field_178723_h;
/* 25 */       leftArm = biped.field_178724_i;
/*    */     }
/* 27 */     else if (model instanceof DugongModel) {
/* 28 */       DugongModel<?> dugong = (DugongModel)model;
/* 29 */       rightArm = dugong.rightArm;
/* 30 */       leftArm = dugong.leftArm;
/*    */       
/* 32 */       rightArm.field_78798_e = -4.0F;
/* 33 */       leftArm.field_78798_e = -4.0F;
/*    */     } 
/*    */     
/* 36 */     if (rightArm != null) {
/* 37 */       rightArm.field_78795_f = -1.2415929F;
/* 38 */       rightArm.field_78796_g = -1.2F;
/* 39 */       rightArm.field_78808_h = -0.8F;
/*    */     } 
/*    */     
/* 42 */     if (leftArm != null) {
/* 43 */       leftArm.field_78795_f = -1.2415929F;
/* 44 */       leftArm.field_78796_g = 1.2F;
/* 45 */       leftArm.field_78808_h = 0.8F;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\animations\CrossedArmsAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */