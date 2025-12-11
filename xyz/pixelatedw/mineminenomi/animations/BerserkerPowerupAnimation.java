/*    */ package xyz.pixelatedw.mineminenomi.animations;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
/*    */ 
/*    */ public class BerserkerPowerupAnimation
/*    */   implements IAnimation<LivingEntity, BipedModel> {
/* 12 */   public static final BerserkerPowerupAnimation INSTANCE = new BerserkerPowerupAnimation();
/*    */ 
/*    */ 
/*    */   
/*    */   public void setAnimationAngles(LivingEntity player, BipedModel model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 17 */     model.field_178723_h.field_78795_f = (float)Math.toRadians(300.0D);
/* 18 */     model.field_178723_h.field_78796_g = (float)Math.toRadians(30.0D);
/* 19 */     model.field_178723_h.field_78808_h = (float)Math.toRadians(-30.0D);
/*    */     
/* 21 */     model.field_178724_i.field_78795_f = (float)Math.toRadians(300.0D);
/* 22 */     model.field_178724_i.field_78796_g = (float)Math.toRadians(-30.0D);
/* 23 */     model.field_178724_i.field_78808_h = (float)Math.toRadians(30.0D);
/*    */     
/* 25 */     model.field_78115_e.field_78795_f = 0.5F;
/* 26 */     model.field_178721_j.field_78798_e = 4.0F;
/* 27 */     model.field_178722_k.field_78798_e = 4.0F;
/* 28 */     model.field_178721_j.field_78797_d = 12.2F;
/* 29 */     model.field_178722_k.field_78797_d = 12.2F;
/* 30 */     model.field_78116_c.field_78797_d += 4.2F;
/* 31 */     model.field_178720_f.field_78797_d += 4.2F;
/* 32 */     model.field_78115_e.field_78797_d = 3.2F;
/* 33 */     model.field_178724_i.field_78797_d = 5.2F;
/* 34 */     model.field_178724_i.field_78798_e += 3.0F;
/* 35 */     model.field_178723_h.field_78797_d = 5.2F;
/* 36 */     model.field_178723_h.field_78798_e += 3.0F;
/*    */   }
/*    */   
/*    */   public void setupAnimation(LivingEntity player, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, float rotationYaw, float partialTicks) {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\animations\BerserkerPowerupAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */