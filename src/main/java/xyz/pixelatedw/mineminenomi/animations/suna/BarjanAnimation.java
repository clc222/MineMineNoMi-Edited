/*    */ package xyz.pixelatedw.mineminenomi.animations.suna;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.Animation;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.AnimationId;
/*    */ 
/*    */ public class BarjanAnimation
/*    */   extends Animation<LivingEntity, BipedModel>
/*    */ {
/*    */   public BarjanAnimation(AnimationId<BarjanAnimation> animId) {
/* 15 */     super(animId);
/* 16 */     setAnimationSetup(this::setup);
/* 17 */     setAnimationAngles(this::angles);
/*    */   }
/*    */   
/*    */   public void angles(LivingEntity entity, BipedModel entityModel, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 21 */     double angle = 90.0D;
/* 22 */     if (getTime() > 20L) {
/* 23 */       angle -= ((getTime() - 20L) * 20L);
/*    */     }
/*    */     
/* 26 */     double yAngle = MathHelper.func_151237_a(angle, 0.0D, 180.0D) - 130.0D;
/* 27 */     double xAngle = -MathHelper.func_151237_a(angle, 0.0D, 180.0D) + 20.0D;
/*    */     
/* 29 */     if (angle <= 0.0D) {
/*    */       return;
/*    */     }
/*    */     
/* 33 */     entityModel.field_178723_h.field_78796_g = (float)Math.toRadians(yAngle);
/* 34 */     entityModel.field_178723_h.field_78795_f = (float)Math.toRadians(xAngle);
/*    */   }
/*    */   
/*    */   public void setup(LivingEntity entity, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, float rotationYaw, float partialTicks) {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\animations\suna\BarjanAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */