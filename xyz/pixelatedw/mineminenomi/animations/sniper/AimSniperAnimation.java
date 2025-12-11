/*    */ package xyz.pixelatedw.mineminenomi.animations.sniper;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.model.ItemCameraTransforms;
/*    */ import net.minecraft.client.renderer.model.ModelRenderer;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.HandSide;
/*    */ import net.minecraft.util.math.vector.Quaternion;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.Animation;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.AnimationId;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*    */ 
/*    */ public class AimSniperAnimation
/*    */   extends Animation<LivingEntity, BipedModel> {
/*    */   public AimSniperAnimation(AnimationId<AimSniperAnimation> animId) {
/* 21 */     super(animId);
/* 22 */     setAnimationAngles(this::angles);
/* 23 */     setAnimationHeldItem(this::heldItem);
/*    */   }
/*    */   
/*    */   public void angles(LivingEntity entity, BipedModel model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 27 */     boolean isFlintlock = (!entity.func_184614_ca().func_190926_b() && entity.func_184614_ca().func_77973_b() == ModWeapons.FLINTLOCK.get());
/*    */     
/* 29 */     if (isFlintlock) {
/* 30 */       HandSide side = (entity.func_184582_a(EquipmentSlotType.MAINHAND).func_77973_b() == ModWeapons.FLINTLOCK.get()) ? HandSide.RIGHT : HandSide.LEFT;
/* 31 */       HandSide opposide = (entity.func_184582_a(EquipmentSlotType.MAINHAND).func_77973_b() == ModWeapons.FLINTLOCK.get()) ? HandSide.LEFT : HandSide.RIGHT;
/* 32 */       (getArm(model, side)).field_78796_g = -0.1F + model.field_78116_c.field_78796_g - 0.4F;
/* 33 */       (getArm(model, opposide)).field_78796_g = 0.5F + model.field_78116_c.field_78796_g;
/* 34 */       (getArm(model, side)).field_78795_f = -1.5707964F + model.field_78116_c.field_78795_f;
/* 35 */       (getArm(model, opposide)).field_78795_f = -1.5707964F + model.field_78116_c.field_78795_f;
/*    */     } else {
/*    */       
/* 38 */       model.field_178723_h.field_78796_g = 0.3F + model.field_78116_c.field_78796_g - 0.4F;
/* 39 */       model.field_178724_i.field_78796_g = 0.8F + model.field_78116_c.field_78796_g;
/* 40 */       model.field_178723_h.field_78795_f = -1.5707964F + model.field_78116_c.field_78795_f;
/* 41 */       model.field_178724_i.field_78795_f = -1.5707964F + model.field_78116_c.field_78795_f;
/*    */     } 
/*    */   }
/*    */   
/*    */   public void heldItem(LivingEntity entity, ItemStack stack, ItemCameraTransforms.TransformType transformType, HandSide handSide, MatrixStack matrixStack, IRenderTypeBuffer renderBuffer, int packedLight) {
/* 46 */     boolean isFlintlock = (!entity.func_184614_ca().func_190926_b() && entity.func_184614_ca().func_77973_b() == ModWeapons.FLINTLOCK.get());
/*    */     
/* 48 */     if (isFlintlock) {
/* 49 */       matrixStack.func_227861_a_(-0.05D, 0.0D, -0.15D);
/* 50 */       matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229180_c_, 20.0F, true));
/*    */     } else {
/*    */       
/* 53 */       matrixStack.func_227861_a_(-0.1D, 0.1D, 0.3D);
/* 54 */       matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229181_d_, -75.0F, true));
/* 55 */       matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229179_b_, -90.0F, true));
/*    */     } 
/*    */   }
/*    */   
/*    */   protected ModelRenderer getArm(BipedModel model, HandSide pSide) {
/* 60 */     return (pSide == HandSide.LEFT) ? model.field_178724_i : model.field_178723_h;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\animations\sniper\AimSniperAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */