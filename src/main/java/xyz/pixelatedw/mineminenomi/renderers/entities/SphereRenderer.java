/*    */ package xyz.pixelatedw.mineminenomi.renderers.entities;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.ActiveRenderInfo;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.culling.ClippingHelper;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.entities.SphereEntity;
/*    */ 
/*    */ public class SphereRenderer<T extends SphereEntity>
/*    */   extends EntityRenderer<T> {
/*    */   private final Minecraft minecraft;
/*    */   private ActiveRenderInfo camera;
/*    */   
/*    */   public SphereRenderer(EntityRendererManager renderManager) {
/* 21 */     super(renderManager);
/* 22 */     this.field_76989_e = 0.0F;
/* 23 */     this.minecraft = Minecraft.func_71410_x();
/* 24 */     this.camera = this.minecraft.field_71460_t.func_215316_n();
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(T entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 29 */     matrixStack.func_227860_a_();
/*    */     
/* 31 */     entity.getSkybox().renderSphereInWorld(matrixStack, this.camera, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/*    */     
/* 33 */     matrixStack.func_227865_b_();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldRender(SphereEntity pLivingEntity, ClippingHelper pCamera, double pCamX, double pCamY, double pCamZ) {
/* 38 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public ResourceLocation getTextureLocation(T entity) {
/* 43 */     return null;
/*    */   }
/*    */   
/*    */   public static class Factory
/*    */     implements IRenderFactory {
/*    */     public EntityRenderer createRenderFor(EntityRendererManager manager) {
/* 49 */       return new SphereRenderer<>(manager);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\SphereRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */