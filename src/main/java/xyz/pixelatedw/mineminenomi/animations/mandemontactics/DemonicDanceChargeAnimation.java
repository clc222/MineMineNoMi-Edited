/*    */ package xyz.pixelatedw.mineminenomi.animations.mandemontactics;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.client.renderer.model.ItemCameraTransforms;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.HandSide;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.Animation;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.AnimationId;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*    */ 
/*    */ public class DemonicDanceChargeAnimation
/*    */   extends Animation<LivingEntity, EntityModel<LivingEntity>>
/*    */ {
/*    */   public DemonicDanceChargeAnimation(AnimationId<DemonicDanceChargeAnimation> animId) {
/* 19 */     super(animId);
/* 20 */     setAnimationSetup(this::setup);
/* 21 */     setAnimationAngles(this::angles);
/* 22 */     setAnimationHeldItem(this::heldItem);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setup(LivingEntity entity, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, float rotationYaw, float partialTicks) {}
/*    */ 
/*    */   
/*    */   public void angles(LivingEntity entity, EntityModel<LivingEntity> model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*    */ 
/*    */   
/*    */   public void heldItem(LivingEntity entity, ItemStack stack, ItemCameraTransforms.TransformType transformType, HandSide handSide, MatrixStack matrixStack, IRenderTypeBuffer renderBuffer, int packedLight) {
/* 34 */     if (stack.func_77973_b() != ModWeapons.TONFA.get()) {
/*    */       return;
/*    */     }
/*    */     
/* 38 */     if (handSide == HandSide.RIGHT) {
/* 39 */       matrixStack.func_227861_a_(-0.05D, -0.15D, 0.1D);
/* 40 */       matrixStack.func_227863_a_(Vector3f.field_229183_f_.func_229187_a_(95.0F));
/* 41 */       matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_((float)(-getTime() * 40L % 360L)));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\animations\mandemontactics\DemonicDanceChargeAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */