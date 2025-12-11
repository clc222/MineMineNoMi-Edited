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
/*    */ public class BodyRotateAnimation
/*    */   extends Animation<LivingEntity, BipedModel> {
/* 13 */   protected float speed = 40.0F;
/*    */   
/*    */   public BodyRotateAnimation(AnimationId<? extends BodyRotateAnimation> animId, float speed) {
/* 16 */     super(animId);
/* 17 */     this.speed = speed;
/* 18 */     setAnimationSetup(this::setup);
/*    */   }
/*    */   
/*    */   public void setup(LivingEntity player, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, float rotationYaw, float partialTicks) {
/* 22 */     float rot = (float)getTime() * this.speed + partialTicks;
/* 23 */     matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(rot));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\animations\BodyRotateAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */