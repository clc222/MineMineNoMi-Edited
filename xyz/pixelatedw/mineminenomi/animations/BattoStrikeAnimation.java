/*    */ package xyz.pixelatedw.mineminenomi.animations;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.model.ItemCameraTransforms;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.HandSide;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.Animation;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.AnimationId;
/*    */ 
/*    */ public class BattoStrikeAnimation
/*    */   extends Animation<LivingEntity, BipedModel> {
/*    */   public BattoStrikeAnimation(AnimationId<BattoStrikeAnimation> animId) {
/* 18 */     super(animId);
/* 19 */     setAnimationAngles(this::angles);
/* 20 */     setAnimationHeldItem(this::heldItem);
/*    */   }
/*    */   
/*    */   public void angles(LivingEntity player, BipedModel model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 24 */     float time = (float)getTime();
/*    */     
/* 26 */     float xRot = 70.0F - time * 20.0F;
/* 27 */     xRot = MathHelper.func_76131_a(xRot, -50.0F, 50.0F);
/*    */     
/* 29 */     model.field_178723_h.field_78800_c = -4.0F;
/* 30 */     model.field_178723_h.field_78797_d = 4.0F;
/* 31 */     model.field_178723_h.field_78798_e = 1.5F;
/* 32 */     model.field_178723_h.field_78795_f = (float)Math.toRadians(xRot);
/* 33 */     model.field_178723_h.field_78796_g = (float)Math.toRadians(20.0D);
/* 34 */     model.field_178723_h.field_78808_h = (float)Math.toRadians(40.0D);
/*    */     
/* 36 */     model.field_178724_i.field_78800_c = (float)(model.field_178724_i.field_78800_c - 5.0D);
/* 37 */     model.field_178724_i.field_78797_d = 2.0F;
/* 38 */     model.field_178724_i.field_78798_e = (float)(model.field_178724_i.field_78798_e - 3.0D);
/* 39 */     model.field_178724_i.field_78795_f = (float)Math.toRadians((xRot + 15.0F));
/* 40 */     model.field_178724_i.field_78796_g = (float)Math.toRadians(20.0D);
/* 41 */     model.field_178724_i.field_78808_h = (float)Math.toRadians(50.0D);
/*    */   }
/*    */   
/*    */   public void heldItem(LivingEntity entity, ItemStack stack, ItemCameraTransforms.TransformType transformType, HandSide handSide, MatrixStack matrixStack, IRenderTypeBuffer renderBuffer, int packedLight) {
/* 45 */     float time = (float)getTime();
/*    */     
/* 47 */     float zRot = -95.0F + time * 20.0F;
/* 48 */     zRot = MathHelper.func_76131_a(zRot, -100.0F, -45.0F);
/*    */     
/* 50 */     matrixStack.func_227861_a_(0.0D, 0.0D, 0.0D);
/* 51 */     matrixStack.func_227863_a_(Vector3f.field_229183_f_.func_229187_a_(zRot));
/* 52 */     matrixStack.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(-35.0F));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\animations\BattoStrikeAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */