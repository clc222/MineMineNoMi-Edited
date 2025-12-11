/*    */ package xyz.pixelatedw.mineminenomi.animations;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.model.ItemCameraTransforms;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.HandSide;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.Animation;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.AnimationId;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
/*    */ 
/*    */ 
/*    */ public class WeaponSlamAnimation
/*    */   extends Animation<LivingEntity, BipedModel>
/*    */ {
/*    */   public WeaponSlamAnimation(AnimationId<WeaponSlamAnimation> animId) {
/* 22 */     super(animId);
/* 23 */     setAnimationSetup(this::setup);
/* 24 */     setAnimationAngles(this::angles);
/* 25 */     setAnimationHeldItem(this::heldItem);
/*    */   }
/*    */   
/*    */   public void setup(LivingEntity entity, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, float rotationYaw, float partialTicks) {
/* 29 */     int animTime = (int)((int)getTime() * 4.5F);
/* 30 */     animTime = MathHelper.func_76125_a(animTime, 0, 50) - 20;
/* 31 */     matrixStack.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(animTime));
/*    */   }
/*    */   
/*    */   public void angles(LivingEntity entity, BipedModel model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 35 */     double groundDistance = DevilFruitHelper.getDifferenceToFloor((Entity)entity);
/*    */     
/* 37 */     if (groundDistance > 2.0D) {
/* 38 */       if (model instanceof xyz.pixelatedw.mineminenomi.models.entities.mobs.animals.GorillaModel) {
/* 39 */         model.field_178723_h.field_78795_f = (float)Math.toRadians(150.0D);
/* 40 */         model.field_178723_h.field_78808_h = (float)Math.toRadians(0.0D);
/*    */         
/* 42 */         model.field_178724_i.field_78795_f = (float)Math.toRadians(150.0D);
/* 43 */         model.field_178724_i.field_78808_h = (float)Math.toRadians(0.0D);
/*    */       } else {
/*    */         
/* 46 */         model.field_178723_h.field_78795_f = (float)Math.toRadians(150.0D);
/* 47 */         model.field_178723_h.field_78808_h = (float)Math.toRadians(20.0D);
/*    */         
/* 49 */         model.field_178724_i.field_78795_f = (float)Math.toRadians(150.0D);
/* 50 */         model.field_178724_i.field_78808_h = (float)Math.toRadians(-20.0D);
/*    */       } 
/*    */       
/* 53 */       model.field_178721_j.field_78795_f = 1.2F;
/* 54 */       model.field_178721_j.field_78796_g = -0.1F;
/* 55 */       model.field_178721_j.field_78798_e = -1.0F;
/*    */       
/* 57 */       model.field_178722_k.field_78795_f = 1.2F;
/* 58 */       model.field_178722_k.field_78796_g = 0.1F;
/* 59 */       model.field_178722_k.field_78798_e = -1.0F;
/*    */     } else {
/*    */       
/* 62 */       model.field_178723_h.field_78795_f = (float)Math.toRadians(-50.0D);
/* 63 */       model.field_178723_h.field_78808_h = (float)Math.toRadians(-20.0D);
/*    */       
/* 65 */       model.field_178724_i.field_78795_f = (float)Math.toRadians(-50.0D);
/* 66 */       model.field_178724_i.field_78808_h = (float)Math.toRadians(20.0D);
/*    */       
/* 68 */       model.field_178721_j.field_78795_f = -1.2F;
/* 69 */       model.field_178721_j.field_78796_g = 0.3F;
/* 70 */       model.field_178721_j.field_78798_e = 1.0F;
/*    */       
/* 72 */       model.field_178722_k.field_78795_f = -1.2F;
/* 73 */       model.field_178722_k.field_78796_g = -0.3F;
/* 74 */       model.field_178722_k.field_78798_e = 1.0F;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void heldItem(LivingEntity entity, ItemStack stack, ItemCameraTransforms.TransformType transformType, HandSide handSide, MatrixStack matrixStack, IRenderTypeBuffer renderBuffer, int packedLight) {
/* 80 */     double groundDistance = DevilFruitHelper.getDifferenceToFloor((Entity)entity);
/*    */     
/* 82 */     if (!(entity instanceof xyz.pixelatedw.mineminenomi.entities.mobs.animals.AbstractGorillaEntity))
/*    */     {
/*    */ 
/*    */       
/* 86 */       matrixStack.func_227861_a_(-0.15D, -0.2D, -0.2D);
/*    */     }
/*    */     
/* 89 */     if (groundDistance > 2.0D) {
/* 90 */       matrixStack.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(-55.0F));
/*    */     } else {
/*    */       
/* 93 */       matrixStack.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(-75.0F));
/*    */     } 
/* 95 */     matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(-20.0F));
/* 96 */     matrixStack.func_227863_a_(Vector3f.field_229183_f_.func_229187_a_(-7.0F));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\animations\WeaponSlamAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */