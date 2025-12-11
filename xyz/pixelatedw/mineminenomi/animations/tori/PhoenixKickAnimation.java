/*    */ package xyz.pixelatedw.mineminenomi.animations.tori;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.Animation;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.AnimationId;
/*    */ import xyz.pixelatedw.mineminenomi.models.morphs.PhoenixAssaultPartialModel;
/*    */ 
/*    */ public class PhoenixKickAnimation
/*    */   extends Animation<LivingEntity, BipedModel>
/*    */ {
/*    */   public PhoenixKickAnimation(AnimationId<PhoenixKickAnimation> animId) {
/* 16 */     super(animId);
/* 17 */     setAnimationSetup(this::setup);
/* 18 */     setAnimationAngles(this::angles);
/*    */   }
/*    */   
/*    */   public void angles(LivingEntity entity, BipedModel model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 22 */     model.field_178721_j.field_78795_f = model.field_178721_j.field_78796_g = model.field_178721_j.field_78808_h = 0.0F;
/* 23 */     model.field_178722_k.field_78795_f = model.field_178722_k.field_78796_g = model.field_178722_k.field_78808_h = 0.0F;
/*    */     
/* 25 */     model.field_178721_j.field_78795_f = model.field_178721_j.field_78795_f * 0.5F - 3.1415927F + 2.3F;
/* 26 */     model.field_178721_j.field_78796_g = model.field_178721_j.field_78796_g * 0.5F - 3.1415927F - 2.2F;
/*    */     
/* 28 */     model.field_178722_k.field_78795_f = model.field_178722_k.field_78795_f * 0.5F - 3.1415927F + 2.5F;
/* 29 */     model.field_178722_k.field_78795_f = model.field_178722_k.field_78795_f * 0.5F - 3.1415927F + 2.5F;
/*    */     
/* 31 */     if (model instanceof PhoenixAssaultPartialModel) {
/* 32 */       PhoenixAssaultPartialModel model2 = (PhoenixAssaultPartialModel)model;
/* 33 */       model2.leftLeg2.field_78796_g = model2.field_178722_k.field_78796_g * 0.5F - 3.1415927F + 2.1F;
/* 34 */       model2.leftLeg2.field_78808_h = model2.field_178722_k.field_78808_h * 0.5F - 3.1415927F - 2.1F;
/*    */     } 
/*    */   }
/*    */   
/*    */   public void setup(LivingEntity entity, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, float rotationYaw, float partialTicks) {
/* 39 */     matrixStack.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(-45.0F));
/* 40 */     matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(-45.0F));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\animations\tori\PhoenixKickAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */