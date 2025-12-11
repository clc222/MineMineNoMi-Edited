/*    */ package xyz.pixelatedw.mineminenomi.animations.ito;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.client.renderer.entity.model.PlayerModel;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
/*    */ 
/*    */ public class ShufukuSagyoAnimation
/*    */   implements IAnimation
/*    */ {
/* 14 */   public static final ShufukuSagyoAnimation INSTANCE = new ShufukuSagyoAnimation();
/*    */ 
/*    */ 
/*    */   
/*    */   public void setAnimationAngles(LivingEntity player, EntityModel entityModel, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 19 */     if (entityModel instanceof BipedModel) {
/*    */       
/* 21 */       BipedModel model = (BipedModel)entityModel;
/* 22 */       boolean HPreq = (player.func_110143_aJ() > player.func_110138_aP() * 0.7D);
/* 23 */       if (!HPreq) {
/*    */         
/* 25 */         model.field_178723_h.field_78795_f = model.field_178723_h.field_78795_f * 0.5F - 3.1415927F + 1.8F;
/* 26 */         model.field_178723_h.field_78796_g = -1.1F;
/* 27 */         model.field_178723_h.field_78808_h = -0.8F;
/*    */       } 
/*    */       
/* 30 */       model.field_178724_i.field_78795_f = model.field_178724_i.field_78795_f * 0.5F - 3.1415927F + 1.8F;
/* 31 */       model.field_178724_i.field_78796_g = 1.1F;
/* 32 */       model.field_178724_i.field_78808_h = 0.8F;
/*    */       
/* 34 */       if (model instanceof PlayerModel) {
/*    */         
/* 36 */         if (!HPreq)
/* 37 */           ((PlayerModel)model).field_178732_b.func_217177_a(model.field_178723_h); 
/* 38 */         ((PlayerModel)model).field_178734_a.func_217177_a(model.field_178724_i);
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public void setupAnimation(LivingEntity player, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, float rotationYaw, float partialTicks) {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\animations\ito\ShufukuSagyoAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */