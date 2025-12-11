/*    */ package xyz.pixelatedw.mineminenomi.integrations.curios.renderers;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.LivingRenderer;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.client.renderer.model.ItemCameraTransforms;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ 
/*    */ public class ItemCurioRenderer
/*    */   implements ICurioRenderer
/*    */ {
/*    */   public void render(String identifier, int index, MatrixStack matrixStack, IRenderTypeBuffer buffer, int light, LivingEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, ItemStack stack) {
/* 20 */     matrixStack.func_227860_a_();
/*    */ 
/*    */     
/* 23 */     EntityRenderer<? super LivingEntity> render = Minecraft.func_71410_x().func_175598_ae().func_78713_a((Entity)entity);
/* 24 */     if (render instanceof LivingRenderer) {
/* 25 */       EntityModel<LivingEntity> model = ((LivingRenderer)render).func_217764_d();
/*    */       
/* 27 */       if (model instanceof BipedModel) {
/* 28 */         ((BipedModel)model).func_205072_a().func_228307_a_(matrixStack);
/*    */       }
/*    */     } 
/*    */     
/* 32 */     matrixStack.func_227861_a_(0.0D, -0.25D, 0.0D);
/* 33 */     matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(180.0F));
/* 34 */     matrixStack.func_227862_a_(0.625F, -0.625F, -0.625F);
/*    */     
/* 36 */     Minecraft.func_71410_x().func_175597_ag().func_228397_a_(entity, stack, ItemCameraTransforms.TransformType.HEAD, false, matrixStack, buffer, light);
/* 37 */     matrixStack.func_227865_b_();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\integrations\curios\renderers\ItemCurioRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */