/*    */ package xyz.pixelatedw.mineminenomi.animations.goro;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
/*    */ 
/*    */ public class GlowingRaiseBothArmAnimation
/*    */   implements IAnimation<LivingEntity, BipedModel> {
/* 12 */   public static final GlowingRaiseBothArmAnimation INSTANCE = new GlowingRaiseBothArmAnimation();
/*    */ 
/*    */ 
/*    */   
/*    */   public void setAnimationAngles(LivingEntity player, BipedModel entityModel, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 17 */     if (entityModel instanceof BipedModel) {
/*    */       
/* 19 */       BipedModel model = entityModel;
/*    */       
/* 21 */       model.field_178723_h.field_78795_f = model.field_178723_h.field_78795_f * 0.5F - 3.1415927F;
/* 22 */       model.field_178723_h.field_78796_g = 0.0F;
/*    */       
/* 24 */       model.field_178724_i.field_78795_f = model.field_178724_i.field_78795_f * 0.5F - 3.1415927F;
/* 25 */       model.field_178724_i.field_78796_g = 0.0F;
/*    */     } 
/*    */   }
/*    */   
/*    */   public void setupAnimation(LivingEntity player, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, float rotationYaw, float partialTicks) {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\animations\goro\GlowingRaiseBothArmAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */