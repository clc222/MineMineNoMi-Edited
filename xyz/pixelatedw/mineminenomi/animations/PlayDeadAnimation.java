/*    */ package xyz.pixelatedw.mineminenomi.animations;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.Animation;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.AnimationId;
/*    */ 
/*    */ public class PlayDeadAnimation
/*    */   extends Animation<LivingEntity, BipedModel> {
/*    */   public PlayDeadAnimation(AnimationId<PlayDeadAnimation> animId) {
/* 14 */     super(animId);
/* 15 */     setAnimationSetup(this::setup);
/*    */   }
/*    */   
/*    */   public void setup(LivingEntity entity, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, float rotationYaw, float partialTicks) {
/* 19 */     matrixStack.func_227863_a_(Vector3f.field_229183_f_.func_229187_a_(-90.0F));
/* 20 */     matrixStack.func_227861_a_(-1.5D, -1.2D, 0.0D);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\animations\PlayDeadAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */