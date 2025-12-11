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
/*    */ public class PitchSpinAnimation
/*    */   extends Animation<LivingEntity, BipedModel> {
/*    */   public PitchSpinAnimation(AnimationId<PitchSpinAnimation> animId) {
/* 14 */     super(animId);
/* 15 */     setAnimationSetup(this::setup);
/* 16 */     setAnimationAngles(this::angles);
/*    */   }
/*    */   
/*    */   public void angles(LivingEntity player, BipedModel model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 20 */     model.field_178722_k.field_78795_f = (float)Math.toRadians(-90.0D);
/* 21 */     model.field_178721_j.field_78795_f = (float)Math.toRadians(180.0D);
/*    */   }
/*    */   
/*    */   public void setup(LivingEntity player, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, float rotationYaw, float partialTicks) {
/* 25 */     matrixStack.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_((player.field_70173_aa + partialTicks) * 40.0F));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\animations\PitchSpinAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */