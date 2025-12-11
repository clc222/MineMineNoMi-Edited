/*    */ package xyz.pixelatedw.mineminenomi.animations;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.model.AgeableModel;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.model.ItemCameraTransforms;
/*    */ import net.minecraft.client.renderer.model.ModelRenderer;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.HandSide;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.Animation;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.AnimationId;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*    */ 
/*    */ public class CrossedAttackAnimation
/*    */   extends Animation<LivingEntity, AgeableModel>
/*    */ {
/*    */   public CrossedAttackAnimation(AnimationId<CrossedAttackAnimation> animId) {
/* 22 */     super(animId);
/* 23 */     setAnimationAngles(this::angles);
/* 24 */     setAnimationHeldItem(this::heldItem);
/*    */   }
/*    */   
/*    */   public void angles(LivingEntity entity, AgeableModel model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 28 */     ModelRenderer rightArm = null;
/* 29 */     ModelRenderer leftArm = null;
/*    */     
/* 31 */     if (model instanceof BipedModel) {
/* 32 */       BipedModel<?> biped = (BipedModel)model;
/* 33 */       rightArm = biped.field_178723_h;
/* 34 */       leftArm = biped.field_178724_i;
/*    */     } 
/*    */     
/* 37 */     float xRot = MathHelper.func_219799_g((float)getTime() / 4.0F, -180.0F, -40.0F);
/* 38 */     xRot = MathHelper.func_76131_a(xRot, -180.0F, -40.0F);
/*    */     
/* 40 */     if (rightArm != null) {
/* 41 */       rightArm.field_78795_f = (float)Math.toRadians(xRot);
/* 42 */       rightArm.field_78796_g = (float)Math.toRadians(0.0D);
/* 43 */       rightArm.field_78808_h = (float)Math.toRadians(-45.0D);
/*    */     } 
/*    */     
/* 46 */     if (leftArm != null) {
/* 47 */       leftArm.field_78795_f = (float)Math.toRadians(xRot);
/* 48 */       leftArm.field_78796_g = (float)Math.toRadians(0.0D);
/* 49 */       leftArm.field_78808_h = (float)Math.toRadians(45.0D);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void heldItem(LivingEntity entity, ItemStack stack, ItemCameraTransforms.TransformType transformType, HandSide handSide, MatrixStack matrixStack, IRenderTypeBuffer renderBuffer, int packedLight) {
/* 54 */     if (!stack.func_190926_b() && 
/* 55 */       stack.func_77973_b() == ModWeapons.CAT_CLAWS.get()) {
/* 56 */       matrixStack.func_227861_a_(-0.11D, -0.11D, 0.0D);
/* 57 */       matrixStack.func_227863_a_(Vector3f.field_229183_f_.func_229187_a_(90.0F));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\animations\CrossedAttackAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */