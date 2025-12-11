/*    */ package xyz.pixelatedw.mineminenomi.animations;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.Animation;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.AnimationId;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.SeaCowEntity;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.animals.SeaCowModel;
/*    */ 
/*    */ 
/*    */ public class BellyFlopAnimation
/*    */   extends Animation<LivingEntity, EntityModel<LivingEntity>>
/*    */ {
/*    */   public BellyFlopAnimation(AnimationId<BellyFlopAnimation> animId) {
/* 19 */     super(animId);
/* 20 */     setAnimationSetup(this::setup);
/* 21 */     setAnimationAngles(this::angles);
/*    */   }
/*    */   
/*    */   public void setup(LivingEntity entity, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, float rotationYaw, float partialTicks) {
/* 25 */     if (!(entity instanceof SeaCowEntity)) {
/*    */ 
/*    */ 
/*    */       
/* 29 */       matrixStack.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(90.0F));
/* 30 */       matrixStack.func_227861_a_(0.0D, 0.0D, -0.8D);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void angles(LivingEntity entity, EntityModel<LivingEntity> model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 35 */     if (model instanceof BipedModel) {
/* 36 */       BipedModel<LivingEntity> bipedModel = (BipedModel<LivingEntity>)model;
/*    */       
/* 38 */       bipedModel.field_78116_c.field_78795_f = 0.0F;
/* 39 */       bipedModel.field_78116_c.field_78796_g = 0.0F;
/* 40 */       bipedModel.field_78116_c.field_78808_h = 0.0F;
/* 41 */       bipedModel.field_178720_f.func_217177_a(bipedModel.field_78116_c);
/*    */       
/* 43 */       bipedModel.field_178723_h.field_78808_h = (float)Math.toRadians(45.0D);
/* 44 */       bipedModel.field_178723_h.field_78795_f = (float)Math.toRadians(8.0D);
/*    */       
/* 46 */       bipedModel.field_178724_i.field_78808_h = (float)Math.toRadians(-45.0D);
/* 47 */       bipedModel.field_178724_i.field_78795_f = (float)Math.toRadians(8.0D);
/*    */       
/* 49 */       bipedModel.field_178721_j.field_78808_h = (float)Math.toRadians(15.0D);
/* 50 */       bipedModel.field_178721_j.field_78795_f = (float)Math.toRadians(8.0D);
/*    */       
/* 52 */       bipedModel.field_178722_k.field_78808_h = (float)Math.toRadians(-15.0D);
/* 53 */       bipedModel.field_178722_k.field_78795_f = (float)Math.toRadians(8.0D);
/*    */       
/* 55 */       if (model instanceof xyz.pixelatedw.mineminenomi.models.entities.mobs.animals.GorillaModel) {
/* 56 */         bipedModel.field_178723_h.field_78808_h = (float)Math.toRadians(75.0D);
/* 57 */         bipedModel.field_178724_i.field_78808_h = (float)Math.toRadians(-75.0D);
/*    */       }
/*    */     
/* 60 */     } else if (model instanceof SeaCowModel) {
/* 61 */       SeaCowModel<SeaCowEntity> seaCowModel = (SeaCowModel)model;
/*    */       
/* 63 */       seaCowModel.leftFin.field_78808_h = (float)Math.toRadians(-60.0D);
/* 64 */       seaCowModel.leftFin2.field_78808_h = (float)Math.toRadians(0.0D);
/*    */       
/* 66 */       seaCowModel.rightFin.field_78808_h = (float)Math.toRadians(60.0D);
/* 67 */       seaCowModel.rightFin2.field_78808_h = (float)Math.toRadians(0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\animations\BellyFlopAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */