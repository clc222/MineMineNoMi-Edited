/*    */ package xyz.pixelatedw.mineminenomi.animations.mandemontactics;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.model.ItemCameraTransforms;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.HandSide;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.Animation;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.AnimationId;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*    */ 
/*    */ public class DemonicSmashAnimation
/*    */   extends Animation<LivingEntity, BipedModel<LivingEntity>>
/*    */ {
/*    */   public DemonicSmashAnimation(AnimationId<DemonicSmashAnimation> animId) {
/* 19 */     super(animId);
/* 20 */     setAnimationSetup(this::setup);
/* 21 */     setAnimationAngles(this::angles);
/* 22 */     setAnimationHeldItem(this::heldItem);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setup(LivingEntity entity, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, float rotationYaw, float partialTicks) {}
/*    */ 
/*    */   
/*    */   public void angles(LivingEntity entity, BipedModel<LivingEntity> model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 30 */     boolean hasTonfaInMain = (!entity.func_184614_ca().func_190926_b() && entity.func_184614_ca().func_77973_b() == ModWeapons.TONFA.get());
/* 31 */     boolean hasTonfaInOff = (!entity.func_184592_cb().func_190926_b() && entity.func_184592_cb().func_77973_b() == ModWeapons.TONFA.get());
/*    */     
/* 33 */     double rotation = (180L - getTime() * 40L);
/*    */     
/* 35 */     rotation = Math.max(rotation, 20.0D);
/* 36 */     if (hasTonfaInMain) {
/* 37 */       model.field_178723_h.field_78795_f = (float)Math.toRadians(rotation);
/* 38 */       model.field_178723_h.field_78808_h = (float)Math.toRadians(-90.0D);
/* 39 */       model.field_178723_h.field_78796_g = (float)Math.toRadians(-180.0D);
/*    */     } 
/*    */     
/* 42 */     if (hasTonfaInOff) {
/* 43 */       model.field_178724_i.field_78795_f = (float)Math.toRadians(20.0D + rotation);
/* 44 */       model.field_178724_i.field_78808_h = (float)Math.toRadians(-90.0D);
/* 45 */       model.field_178724_i.field_78796_g = (float)Math.toRadians(-180.0D);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void heldItem(LivingEntity entity, ItemStack stack, ItemCameraTransforms.TransformType transformType, HandSide handSide, MatrixStack matrixStack, IRenderTypeBuffer renderBuffer, int packedLight) {
/* 50 */     if (stack.func_77973_b() != ModWeapons.TONFA.get()) {
/*    */       return;
/*    */     }
/*    */     
/* 54 */     matrixStack.func_227861_a_(0.0D, 0.0D, 0.2D);
/* 55 */     matrixStack.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(10.0F));
/* 56 */     matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(180.0F));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\animations\mandemontactics\DemonicSmashAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */