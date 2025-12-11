/*    */ package xyz.pixelatedw.mineminenomi.animations;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.TimeAnimation;
/*    */ 
/*    */ public class UppercutKickAnimation extends TimeAnimation<LivingEntity, BipedModel> {
/* 12 */   public static final UppercutKickAnimation INSTANCE = new UppercutKickAnimation();
/*    */ 
/*    */   
/*    */   public void setAnimationAngles(LivingEntity entity, BipedModel model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 16 */     float time = Math.max(0.0F, (float)getTime() * 0.15F);
/* 17 */     if (time > 1.0F) {
/*    */       return;
/*    */     }
/*    */     
/* 21 */     model.field_78115_e.field_78796_g = MathHelper.func_76126_a(MathHelper.func_76129_c(time) * 6.2831855F) * 0.2F;
/* 22 */     model.field_178723_h.field_78798_e = MathHelper.func_76126_a(model.field_78115_e.field_78796_g) * 5.0F;
/* 23 */     model.field_178723_h.field_78800_c = -MathHelper.func_76134_b(model.field_78115_e.field_78796_g) * 5.0F;
/* 24 */     model.field_178724_i.field_78798_e = -MathHelper.func_76126_a(model.field_78115_e.field_78796_g) * 5.0F;
/* 25 */     model.field_178724_i.field_78800_c = MathHelper.func_76134_b(model.field_78115_e.field_78796_g) * 5.0F;
/* 26 */     model.field_178723_h.field_78796_g += model.field_78115_e.field_78796_g;
/* 27 */     model.field_178724_i.field_78796_g += model.field_78115_e.field_78796_g;
/* 28 */     model.field_178724_i.field_78795_f += model.field_78115_e.field_78796_g;
/*    */     
/* 30 */     model.field_178721_j.field_78795_f -= MathHelper.func_76126_a(time * 3.1415927F) * 2.5F;
/* 31 */     model.field_178721_j.field_78796_g -= 0.05F * time * 3.1415927F;
/*    */   }
/*    */   
/*    */   public void setupAnimation(LivingEntity entity, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, float rotationYaw, float partialTicks) {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\animations\UppercutKickAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */