/*    */ package xyz.pixelatedw.mineminenomi.animations.swordsman;
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
/*    */ public class UpperSlashAnimation
/*    */   extends Animation<LivingEntity, BipedModel>
/*    */ {
/*    */   public UpperSlashAnimation(AnimationId<UpperSlashAnimation> animId) {
/* 18 */     super(animId);
/* 19 */     setAnimationAngles(this::angles);
/* 20 */     setAnimationHeldItem(this::heldItem);
/*    */   }
/*    */   
/*    */   public void angles(LivingEntity player, BipedModel model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 24 */     model.field_178723_h.field_78798_e += 3.0F;
/* 25 */     model.field_178723_h.field_78795_f = (float)(model.field_178723_h.field_78795_f + Math.toRadians(-50.0D));
/* 26 */     model.field_178723_h.field_78808_h = (float)(model.field_178723_h.field_78808_h + Math.toRadians(20.0D));
/* 27 */     model.field_178723_h.field_78796_g = (float)(model.field_178723_h.field_78796_g + Math.toRadians(-5.0D));
/*    */     
/* 29 */     model.field_178724_i.field_78795_f = (float)(model.field_178724_i.field_78795_f + Math.toRadians(-130.0D));
/* 30 */     model.field_178724_i.field_78796_g = (float)(model.field_178724_i.field_78796_g + Math.toRadians(60.0D));
/*    */   }
/*    */   
/*    */   public void heldItem(LivingEntity entity, ItemStack stack, ItemCameraTransforms.TransformType transformType, HandSide handSide, MatrixStack matrixStack, IRenderTypeBuffer renderBuffer, int packedLight) {
/* 34 */     matrixStack.func_227861_a_(0.0D, -0.1D, -0.05D);
/* 35 */     matrixStack.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(25.0F));
/* 36 */     matrixStack.func_227863_a_(Vector3f.field_229183_f_.func_229187_a_(15.0F));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\animations\swordsman\UpperSlashAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */