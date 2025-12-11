/*    */ package xyz.pixelatedw.mineminenomi.animations;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.entity.model.PlayerModel;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.Animation;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.AnimationId;
/*    */ 
/*    */ public class SpecialFlyAnimation
/*    */   extends Animation<LivingEntity, BipedModel>
/*    */ {
/*    */   public SpecialFlyAnimation(AnimationId<SpecialFlyAnimation> animId) {
/* 15 */     super(animId);
/* 16 */     setAnimationSetup(this::setup);
/* 17 */     setAnimationAngles(this::angles);
/*    */   }
/*    */   
/*    */   public void angles(LivingEntity player, BipedModel model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 21 */     double posXDiff = Math.abs(player.field_70169_q - player.func_226277_ct_());
/* 22 */     double posZDiff = Math.abs(player.field_70166_s - player.func_226281_cx_());
/*    */     
/* 24 */     float angles = 0.0F;
/* 25 */     float pos = 0.0F;
/*    */ 
/*    */     
/* 28 */     if (posXDiff >= 0.01D || posZDiff >= 0.01D) {
/* 29 */       angles = (float)Math.toRadians(45.0D);
/*    */       
/* 31 */       model.field_178721_j.field_78797_d = 10.0F;
/* 32 */       model.field_178721_j.field_78798_e = 8.0F;
/* 33 */       model.field_178722_k.field_78798_e = 3.0F;
/*    */     } 
/* 35 */     if (posXDiff >= 0.5D || posZDiff >= 0.5D) {
/* 36 */       angles = (float)Math.toRadians(60.0D);
/*    */       
/* 38 */       model.field_178721_j.field_78797_d = 8.0F;
/* 39 */       model.field_178721_j.field_78798_e = 10.0F;
/* 40 */       model.field_178722_k.field_78797_d = 8.0F;
/*    */     } 
/*    */     
/* 43 */     model.field_78115_e.field_78795_f = angles;
/*    */     
/* 45 */     if (player.field_70733_aJ <= 0.0F) {
/* 46 */       model.field_178723_h.field_78795_f = angles;
/* 47 */       model.field_178724_i.field_78795_f = angles;
/*    */     } 
/*    */     
/* 50 */     model.field_178721_j.field_78795_f = angles;
/* 51 */     model.field_178722_k.field_78795_f = angles;
/*    */     
/* 53 */     model.field_178721_j.field_78806_j = false;
/* 54 */     model.field_178722_k.field_78806_j = false;
/*    */     
/* 56 */     if (model instanceof PlayerModel) {
/* 57 */       ((PlayerModel)model).field_178733_c.field_78806_j = false;
/* 58 */       ((PlayerModel)model).field_178731_d.field_78806_j = false;
/*    */     } 
/*    */   }
/*    */   
/*    */   public void setup(LivingEntity player, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, float rotationYaw, float partialTicks) {
/* 63 */     double posXDiff = Math.abs(player.field_70169_q - player.func_226277_ct_());
/* 64 */     double posZDiff = Math.abs(player.field_70166_s - player.func_226281_cx_());
/*    */     
/* 66 */     double drop = 0.0D;
/*    */     
/* 68 */     if (posXDiff >= 0.2D || posZDiff >= 0.2D) {
/* 69 */       drop = 0.3D;
/*    */     }
/* 71 */     if (posXDiff >= 0.5D || posZDiff >= 0.5D) {
/* 72 */       drop = 0.4D;
/*    */     }
/*    */     
/* 75 */     matrixStack.func_227861_a_(0.0D, drop, 0.0D);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\animations\SpecialFlyAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */