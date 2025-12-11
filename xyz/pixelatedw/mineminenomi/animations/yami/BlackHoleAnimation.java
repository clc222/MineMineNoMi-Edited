/*    */ package xyz.pixelatedw.mineminenomi.animations.yami;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
/*    */ 
/*    */ public class BlackHoleAnimation
/*    */   implements IAnimation<LivingEntity, BipedModel> {
/* 12 */   public static final BlackHoleAnimation INSTANCE = new BlackHoleAnimation();
/*    */   
/*    */   public void setAnimationAngles(LivingEntity player, BipedModel model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*    */   
/*    */   public void setupAnimation(LivingEntity player, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, float rotationYaw, float partialTicks) {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\animations\yami\BlackHoleAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */