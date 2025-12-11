/*    */ package xyz.pixelatedw.mineminenomi.renderers.entities;
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.client.renderer.model.ItemCameraTransforms;
/*    */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.entities.ChakramEntity;
/*    */ 
/*    */ public class ChakramRenderer<T extends ChakramEntity> extends EntityRenderer<T> {
/*    */   protected ChakramRenderer(EntityRendererManager renderManager) {
/* 20 */     super(renderManager);
/* 21 */     this.field_76989_e = 0.05F;
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(T entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 26 */     if (((ChakramEntity)entity).field_70173_aa < 2) {
/*    */       return;
/*    */     }
/* 29 */     matrixStack.func_227860_a_();
/*    */     
/* 31 */     float rotation = ((ChakramEntity)entity).field_70173_aa * 20.0F % 360.0F;
/*    */     
/* 33 */     matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(MathHelper.func_219799_g(partialTicks, ((ChakramEntity)entity).field_70126_B, ((ChakramEntity)entity).field_70177_z) + rotation));
/* 34 */     matrixStack.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(MathHelper.func_219799_g(partialTicks, ((ChakramEntity)entity).field_70127_C, ((ChakramEntity)entity).field_70125_A) - 180.0F));
/* 35 */     matrixStack.func_227863_a_(Vector3f.field_229183_f_.func_229187_a_(90.0F));
/*    */     
/* 37 */     matrixStack.func_227861_a_(-0.1D, 0.1D, 0.0D);
/*    */     
/* 39 */     ItemStack stack = entity.getItemUsed();
/* 40 */     LivingEntity owner = (LivingEntity)entity.func_234616_v_();
/* 41 */     if (stack != null && !stack.func_190926_b()) {
/* 42 */       (Minecraft.func_71410_x().func_175597_ag()).field_178112_h.func_229109_a_(owner, stack, ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, false, matrixStack, buffer, ((ChakramEntity)entity).field_70170_p, packedLight, OverlayTexture.field_229196_a_);
/*    */     }
/*    */     
/* 45 */     matrixStack.func_227865_b_();
/*    */   }
/*    */ 
/*    */   
/*    */   public ResourceLocation getTextureLocation(T entity) {
/* 50 */     return null;
/*    */   }
/*    */   
/*    */   public static class Factory
/*    */     implements IRenderFactory {
/*    */     public EntityRenderer createRenderFor(EntityRendererManager manager) {
/* 56 */       return new ChakramRenderer<>(manager);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\ChakramRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */