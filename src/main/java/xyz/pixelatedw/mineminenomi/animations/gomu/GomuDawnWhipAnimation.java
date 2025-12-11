/*    */ package xyz.pixelatedw.mineminenomi.animations.gomu;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import xyz.pixelatedw.mineminenomi.animations.BodyRotateAnimation;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.AnimationId;
/*    */ 
/*    */ public class GomuDawnWhipAnimation
/*    */   extends BodyRotateAnimation {
/*    */   public GomuDawnWhipAnimation(AnimationId<GomuDawnWhipAnimation> animId) {
/* 14 */     super(animId, 75.0F);
/*    */     
/* 16 */     setAnimationAngles(this::angles);
/* 17 */     setAnimationSetup(this::setup);
/*    */   }
/*    */   
/*    */   public void angles(LivingEntity player, BipedModel model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 21 */     model.field_178724_i.field_78808_h = (float)Math.toRadians(-90.0D);
/* 22 */     model.field_178723_h.field_78808_h = (float)Math.toRadians(90.0D);
/* 23 */     model.field_178721_j.field_78808_h = (float)Math.toRadians(90.0D);
/* 24 */     model.field_178722_k.field_78808_h = (float)Math.toRadians(-90.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setup(LivingEntity player, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, float rotationYaw, float partialTicks) {
/* 29 */     float rot = (float)(Math.sin(((float)getTime() * 0.1F) + Math.PI) * 16.0D);
/* 30 */     matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229193_c_(rot));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\animations\gomu\GomuDawnWhipAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */