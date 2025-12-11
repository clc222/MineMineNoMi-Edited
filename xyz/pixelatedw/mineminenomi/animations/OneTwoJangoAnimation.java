/*    */ package xyz.pixelatedw.mineminenomi.animations;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.model.ItemCameraTransforms;
/*    */ import net.minecraft.client.renderer.model.ModelRenderer;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.HandSide;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.Animation;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.AnimationId;
/*    */ 
/*    */ public class OneTwoJangoAnimation
/*    */   extends Animation<LivingEntity, BipedModel> {
/*    */   public OneTwoJangoAnimation(AnimationId<OneTwoJangoAnimation> animId) {
/* 18 */     super(animId);
/* 19 */     setAnimationAngles(this::angles);
/* 20 */     setAnimationHeldItem(this::heldItem);
/*    */   }
/*    */   
/*    */   public void angles(LivingEntity player, BipedModel model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 24 */     ModelRenderer arm = model.field_178723_h;
/*    */     
/* 26 */     arm.field_78795_f = arm.field_78795_f * 0.5F - 3.1415927F + 1.5F;
/* 27 */     arm.field_78796_g = 0.2F;
/*    */   }
/*    */   
/*    */   public void heldItem(LivingEntity entity, ItemStack stack, ItemCameraTransforms.TransformType transformType, HandSide handSide, MatrixStack matrixStack, IRenderTypeBuffer renderBuffer, int packedLight) {
/* 31 */     if (entity.func_184591_cq() == handSide) {
/* 32 */       float angle = (float)Math.toDegrees(Math.sin((0.6F * entity.field_70173_aa) + Math.PI)) * 0.15F;
/* 33 */       matrixStack.func_227863_a_(Vector3f.field_229183_f_.func_229187_a_(angle));
/* 34 */       matrixStack.func_227861_a_(0.1D, -0.4D, 0.05D);
/* 35 */       matrixStack.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(-90.0F));
/* 36 */       matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(5.0F));
/* 37 */       matrixStack.func_227863_a_(Vector3f.field_229183_f_.func_229187_a_(-90.0F));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\animations\OneTwoJangoAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */