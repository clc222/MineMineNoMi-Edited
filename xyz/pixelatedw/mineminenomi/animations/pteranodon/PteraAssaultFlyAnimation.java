/*    */ package xyz.pixelatedw.mineminenomi.animations.pteranodon;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.Animation;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.AnimationId;
/*    */ 
/*    */ public class PteraAssaultFlyAnimation
/*    */   extends Animation<LivingEntity, BipedModel>
/*    */ {
/*    */   private static final double THRESHOLD_1 = 0.2D;
/*    */   private static final double THRESHOLD_2 = 0.6D;
/*    */   
/*    */   public PteraAssaultFlyAnimation(AnimationId<PteraAssaultFlyAnimation> animId) {
/* 18 */     super(animId);
/* 19 */     setAnimationSetup(this::setup);
/* 20 */     setAnimationAngles(this::angles);
/*    */   }
/*    */   
/*    */   public void angles(LivingEntity entity, BipedModel model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 24 */     double posXDiff = Math.abs(entity.field_70169_q - entity.func_226277_ct_());
/* 25 */     double posZDiff = Math.abs(entity.field_70166_s - entity.func_226281_cx_());
/* 26 */     Vector3d look = entity.func_70040_Z();
/*    */     
/* 28 */     Vector3d oldPos = new Vector3d(entity.field_70169_q, entity.field_70167_r, entity.field_70166_s);
/* 29 */     double movementDot = entity.func_213303_ch().func_178788_d(oldPos).func_72432_b().func_72430_b(look);
/*    */     
/* 31 */     float angles = 0.0F;
/*    */     
/* 33 */     if (movementDot > 0.0D) {
/*    */       
/* 35 */       if (posXDiff >= 0.2D || posZDiff >= 0.2D) {
/* 36 */         angles = (float)Math.toRadians(45.0D);
/*    */         
/* 38 */         model.field_178722_k.field_78797_d = 8.0F;
/* 39 */         model.field_178722_k.field_78798_e = 8.0F;
/* 40 */         model.field_178722_k.field_78795_f = angles + 0.2F;
/*    */       } 
/*    */       
/* 43 */       if (posXDiff >= 0.6D || posZDiff >= 0.6D) {
/* 44 */         angles = (float)Math.toRadians(60.0D);
/*    */         
/* 46 */         model.field_178722_k.field_78797_d = 5.8F;
/* 47 */         model.field_178722_k.field_78798_e = 10.0F;
/* 48 */         model.field_178722_k.field_78795_f = angles + 0.3F;
/*    */       } 
/* 50 */     } else if (movementDot < 0.0D) {
/* 51 */       if (posXDiff >= 0.2D || posZDiff >= 0.2D) {
/* 52 */         angles = (float)Math.toRadians(-45.0D);
/*    */         
/* 54 */         model.field_178722_k.field_78797_d = 8.0F;
/* 55 */         model.field_178722_k.field_78798_e = -8.0F;
/* 56 */         model.field_178722_k.field_78795_f = angles - 0.2F;
/*    */       } 
/*    */       
/* 59 */       if (posXDiff >= 0.6D || posZDiff >= 0.6D) {
/* 60 */         angles = (float)Math.toRadians(-60.0D);
/*    */         
/* 62 */         model.field_178722_k.field_78797_d = 5.8F;
/* 63 */         model.field_178722_k.field_78798_e = -10.0F;
/* 64 */         model.field_178722_k.field_78795_f = angles - 0.3F;
/*    */       } 
/*    */     } 
/*    */     
/* 68 */     model.field_78115_e.field_78795_f = angles;
/*    */     
/* 70 */     if (entity.field_70733_aJ <= 0.0F) {
/* 71 */       model.field_178723_h.field_78795_f = angles;
/* 72 */       model.field_178724_i.field_78795_f = angles;
/*    */     } 
/*    */   }
/*    */   
/*    */   public void setup(LivingEntity player, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, float rotationYaw, float partialTicks) {
/* 77 */     double posXDiff = Math.abs(player.field_70169_q - player.func_226277_ct_());
/* 78 */     double posZDiff = Math.abs(player.field_70166_s - player.func_226281_cx_());
/*    */     
/* 80 */     double drop = 0.0D;
/*    */     
/* 82 */     if (posXDiff >= 0.2D || posZDiff >= 0.2D) {
/* 83 */       drop = 0.3D;
/*    */     }
/* 85 */     if (posXDiff >= 0.6D || posZDiff >= 0.6D) {
/* 86 */       drop = 0.4D;
/*    */     }
/*    */     
/* 89 */     matrixStack.func_227861_a_(0.0D, drop, 0.0D);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\animations\pteranodon\PteraAssaultFlyAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */