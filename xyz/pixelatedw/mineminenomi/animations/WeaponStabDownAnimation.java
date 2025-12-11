/*    */ package xyz.pixelatedw.mineminenomi.animations;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.model.ItemCameraTransforms;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.HandSide;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.Animation;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.AnimationId;
/*    */ 
/*    */ public class WeaponStabDownAnimation
/*    */   extends Animation<LivingEntity, BipedModel>
/*    */ {
/*    */   public WeaponStabDownAnimation(AnimationId<WeaponStabDownAnimation> animId) {
/* 18 */     super(animId);
/* 19 */     setAnimationAngles(this::angles);
/* 20 */     setAnimationHeldItem(this::heldItem);
/*    */   }
/*    */   
/*    */   public void angles(LivingEntity entity, BipedModel model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 24 */     model.field_178723_h.field_78795_f = (float)Math.toRadians(-70.0D);
/* 25 */     model.field_178723_h.field_78808_h = (float)Math.toRadians(-20.0D);
/* 26 */     model.field_178723_h.field_78796_g = (float)Math.toRadians(-20.0D);
/*    */     
/* 28 */     model.field_178724_i.field_78795_f = (float)Math.toRadians(-70.0D);
/* 29 */     model.field_178724_i.field_78808_h = (float)Math.toRadians(20.0D);
/* 30 */     model.field_178724_i.field_78796_g = (float)Math.toRadians(20.0D);
/*    */   }
/*    */   
/*    */   public void heldItem(LivingEntity entity, ItemStack stack, ItemCameraTransforms.TransformType transformType, HandSide handSide, MatrixStack matrixStack, IRenderTypeBuffer renderBuffer, int packedLight) {
/* 34 */     matrixStack.func_227861_a_(-0.02D, -0.3D, -0.1D);
/* 35 */     matrixStack.func_227863_a_(Vector3f.field_229183_f_.func_229187_a_(192.0F));
/* 36 */     matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(30.0F));
/* 37 */     matrixStack.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(-5.0F));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\animations\WeaponStabDownAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */