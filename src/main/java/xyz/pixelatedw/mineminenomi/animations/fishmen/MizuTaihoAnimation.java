/*    */ package xyz.pixelatedw.mineminenomi.animations.fishmen;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.TimeAnimation;
/*    */ 
/*    */ public class MizuTaihoAnimation
/*    */   extends TimeAnimation<LivingEntity, BipedModel> {
/* 12 */   public static final MizuTaihoAnimation INSTANCE = new MizuTaihoAnimation();
/*    */   
/*    */   public void setAnimationAngles(LivingEntity entity, BipedModel model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*    */   
/*    */   public void setupAnimation(LivingEntity entity, BipedModel model, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, float rotationYaw, float partialTicks) {}
/*    */   
/*    */   public void setupAnimation(LivingEntity entity, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, float rotationYaw, float partialTicks) {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\animations\fishmen\MizuTaihoAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */