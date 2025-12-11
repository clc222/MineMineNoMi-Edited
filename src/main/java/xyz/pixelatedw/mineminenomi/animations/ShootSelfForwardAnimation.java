/*    */ package xyz.pixelatedw.mineminenomi.animations;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.Animation;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.AnimationId;
/*    */ 
/*    */ public class ShootSelfForwardAnimation
/*    */   extends Animation<LivingEntity, BipedModel>
/*    */ {
/*    */   public ShootSelfForwardAnimation(AnimationId<ShootSelfForwardAnimation> animId) {
/* 16 */     super(animId);
/* 17 */     setAnimationSetup(this::setup);
/* 18 */     setAnimationAngles(this::angles);
/*    */   }
/*    */   
/*    */   public void angles(LivingEntity entity, BipedModel entityModel, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 22 */     entityModel.field_78116_c.field_78795_f = (float)Math.toRadians(0.0D);
/* 23 */     entityModel.field_78116_c.field_78796_g = (float)Math.toRadians(0.0D);
/* 24 */     entityModel.field_178722_k.field_78796_g = 0.0F;
/* 25 */     entityModel.field_178722_k.field_78795_f = 0.0F;
/* 26 */     entityModel.field_178721_j.field_78795_f = 0.0F;
/* 27 */     entityModel.field_178721_j.field_78796_g = 0.0F;
/* 28 */     entityModel.field_178724_i.field_78796_g = 0.0F;
/* 29 */     entityModel.field_178724_i.field_78795_f = 0.0F;
/* 30 */     entityModel.field_178723_h.field_78795_f = 0.0F;
/* 31 */     entityModel.field_178723_h.field_78796_g = 0.0F;
/*    */   }
/*    */   
/*    */   public void setup(LivingEntity entity, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, float rotationYaw, float partialTicks) {
/* 35 */     matrixStack.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(MathHelper.func_219799_g(partialTicks, entity.field_70127_C, entity.field_70125_A) + 90.0F));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\animations\ShootSelfForwardAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */