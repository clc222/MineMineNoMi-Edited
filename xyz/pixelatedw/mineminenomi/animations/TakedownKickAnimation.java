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
/*    */ public class TakedownKickAnimation
/*    */   extends Animation<LivingEntity, BipedModel>
/*    */ {
/*    */   public TakedownKickAnimation(AnimationId<TakedownKickAnimation> animId) {
/* 15 */     super(animId);
/* 16 */     setAnimationSetup(this::setup);
/* 17 */     setAnimationAngles(this::angles);
/*    */   }
/*    */   
/*    */   public void setup(LivingEntity player, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, float rotationYaw, float partialTicks) {
/* 21 */     matrixStack.func_227863_a_(Vector3f.field_229183_f_.func_229187_a_(80.0F));
/* 22 */     matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(180.0F - ((float)getTime() + partialTicks) * 40.0F));
/*    */   }
/*    */   
/*    */   public void angles(LivingEntity player, BipedModel model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 26 */     model.field_178721_j.field_78795_f = (float)Math.toRadians(-80.0D);
/* 27 */     model.field_178721_j.field_78808_h = (float)Math.toRadians(80.0D);
/*    */     
/* 29 */     model.field_78115_e.field_78796_g = (float)Math.toRadians(50.0D);
/* 30 */     model.field_178723_h.field_78798_e += 2.0F;
/* 31 */     model.field_178724_i.field_78798_e -= 2.0F;
/* 32 */     model.field_178723_h.field_78795_f = (float)Math.toRadians(-20.0D);
/* 33 */     model.field_178724_i.field_78795_f = (float)Math.toRadians(-20.0D);
/* 34 */     model.field_178724_i.field_78796_g = (float)Math.toRadians(40.0D);
/*    */     
/* 36 */     model.field_178721_j.field_78797_d -= 2.0F;
/* 37 */     model.field_178722_k.field_78797_d -= 4.0F;
/* 38 */     model.field_178722_k.field_78798_e -= 3.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\animations\TakedownKickAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */