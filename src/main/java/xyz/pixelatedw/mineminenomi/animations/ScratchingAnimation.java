/*    */ package xyz.pixelatedw.mineminenomi.animations;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
/*    */ 
/*    */ public class ScratchingAnimation implements IAnimation<LivingEntity, BipedModel> {
/* 11 */   public static final ScratchingAnimation INSTANCE = new ScratchingAnimation();
/*    */ 
/*    */ 
/*    */   
/*    */   public void setAnimationAngles(LivingEntity player, BipedModel model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 16 */     float spread = 0.9F;
/* 17 */     float speed = 0.5F;
/*    */     
/* 19 */     model.field_178723_h.field_78795_f = (float)Math.toRadians(-90.0D);
/* 20 */     model.field_178723_h.field_78795_f += (float)(Math.sin(ageInTicks * Math.PI * speed) * spread);
/* 21 */     model.field_178723_h.field_78808_h = -0.3F - (float)(Math.sin(ageInTicks * Math.PI * speed) * -0.3D);
/* 22 */     model.field_178723_h.field_78796_g = (float)(model.field_178723_h.field_78796_g + Math.sin(ageInTicks * Math.PI * speed) * 0.2D);
/*    */     
/* 24 */     model.field_178724_i.field_78795_f = (float)Math.toRadians(-90.0D);
/* 25 */     model.field_178724_i.field_78795_f += (float)(Math.cos(ageInTicks * Math.PI * speed) * spread);
/* 26 */     model.field_178724_i.field_78808_h = 0.3F - (float)(Math.cos(ageInTicks * Math.PI * speed) * 0.3D);
/* 27 */     model.field_178724_i.field_78796_g = (float)(model.field_178724_i.field_78796_g - Math.cos(ageInTicks * Math.PI * speed) * 0.2D);
/*    */   }
/*    */   
/*    */   public void setupAnimation(LivingEntity player, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, float rotationYaw, float partialTicks) {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\animations\ScratchingAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */