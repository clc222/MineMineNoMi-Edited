/*    */ package xyz.pixelatedw.mineminenomi.animations;
/*    */ 
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.client.renderer.model.ModelRenderer;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.HandSide;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.Animation;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.AnimationId;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.animals.SeaCowModel;
/*    */ 
/*    */ public class TackleAnimation extends Animation<LivingEntity, EntityModel<?>> {
/* 13 */   private HandSide side = HandSide.RIGHT;
/*    */   
/*    */   public TackleAnimation(AnimationId<TackleAnimation> animId, HandSide side) {
/* 16 */     super(animId);
/* 17 */     this.side = side;
/* 18 */     setAnimationAngles(this::angles);
/*    */   }
/*    */   
/*    */   public void angles(LivingEntity player, EntityModel<?> model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 22 */     if (model instanceof BipedModel) {
/* 23 */       BipedModel<LivingEntity> bipedModel = (BipedModel)model;
/*    */       
/* 25 */       ModelRenderer mainArm = bipedModel.field_178723_h;
/* 26 */       ModelRenderer oppositeArm = bipedModel.field_178724_i;
/* 27 */       int sideMod = 1;
/* 28 */       if (this.side.equals(HandSide.LEFT)) {
/* 29 */         mainArm = bipedModel.field_178724_i;
/* 30 */         oppositeArm = bipedModel.field_178723_h;
/* 31 */         sideMod = -1;
/*    */       } 
/*    */       
/* 34 */       bipedModel.field_78115_e.field_78796_g = -0.5F;
/*    */       
/* 36 */       mainArm.field_78798_e = -1.2F;
/* 37 */       mainArm.field_78800_c = -3.0F;
/* 38 */       mainArm.field_78795_f = -1.9415928F;
/* 39 */       mainArm.field_78796_g = -1.5F * sideMod;
/* 40 */       mainArm.field_78808_h = 0.8F * sideMod;
/*    */       
/* 42 */       oppositeArm.field_78798_e = 2.2F;
/* 43 */       oppositeArm.field_78795_f = 0.3F;
/* 44 */       oppositeArm.field_78808_h = -0.2F;
/*    */     }
/* 46 */     else if (model instanceof SeaCowModel) {
/* 47 */       SeaCowModel<LivingEntity> seaCowModel = (SeaCowModel)model;
/*    */       
/* 49 */       seaCowModel.head.field_78795_f = (float)Math.toRadians(90.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\animations\TackleAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */