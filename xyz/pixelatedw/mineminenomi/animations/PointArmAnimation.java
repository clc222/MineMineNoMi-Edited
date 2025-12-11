/*    */ package xyz.pixelatedw.mineminenomi.animations;
/*    */ 
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.model.ModelRenderer;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.HandSide;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.Animation;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.AnimationId;
/*    */ 
/*    */ public class PointArmAnimation extends Animation<LivingEntity, BipedModel> {
/* 11 */   private HandSide side = HandSide.RIGHT;
/*    */   
/*    */   public PointArmAnimation(AnimationId<PointArmAnimation> animId, HandSide side) {
/* 14 */     super(animId);
/* 15 */     this.side = side;
/* 16 */     setAnimationAngles(this::angles);
/*    */   }
/*    */   
/*    */   public void angles(LivingEntity player, BipedModel model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 20 */     ModelRenderer arm = model.field_178723_h;
/* 21 */     int sideMod = 1;
/* 22 */     if (this.side.equals(HandSide.LEFT)) {
/* 23 */       arm = model.field_178724_i;
/* 24 */       sideMod = -1;
/*    */     } 
/* 26 */     arm.field_78795_f = arm.field_78795_f * 0.5F - 3.1415927F + 1.8F;
/* 27 */     arm.field_78796_g = 0.2F * sideMod;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\animations\PointArmAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */