/*    */ package xyz.pixelatedw.mineminenomi.animations.brawler;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.Animation;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.AnimationId;
/*    */ 
/*    */ public class RyuNoIbukiAnimation
/*    */   extends Animation<LivingEntity, BipedModel> {
/*    */   public RyuNoIbukiAnimation(AnimationId<RyuNoIbukiAnimation> animId) {
/* 14 */     super(animId);
/* 15 */     setAnimationSetup(this::setup);
/* 16 */     setAnimationAngles(this::angles);
/*    */   }
/*    */   
/*    */   public void setup(LivingEntity entity, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, float rotationYaw, float partialTicks) {
/* 20 */     matrixStack.func_227861_a_(0.0D, 0.7D, 0.0D);
/* 21 */     matrixStack.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(20.0F));
/*    */   }
/*    */   
/*    */   public void angles(LivingEntity player, BipedModel model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 25 */     model.field_178721_j.field_78797_d = 2.0F;
/* 26 */     model.field_178721_j.field_78798_e = -4.0F;
/* 27 */     model.field_178721_j.field_78795_f = (float)Math.toRadians(-20.0D);
/*    */     
/* 29 */     model.field_178722_k.field_78797_d = 7.0F;
/* 30 */     model.field_178722_k.field_78798_e = -7.0F;
/* 31 */     model.field_178722_k.field_78795_f = (float)Math.toRadians(50.0D);
/*    */     
/* 33 */     model.field_178724_i.field_78797_d = 2.0F;
/* 34 */     model.field_178724_i.field_78798_e = -2.0F;
/* 35 */     model.field_178724_i.field_78795_f = (float)Math.toRadians(-40.0D);
/*    */     
/* 37 */     model.field_178723_h.field_78797_d = 2.0F;
/* 38 */     model.field_178723_h.field_78798_e = -2.0F;
/* 39 */     model.field_178723_h.field_78795_f = (float)Math.toRadians(-40.0D);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\animations\brawler\RyuNoIbukiAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */