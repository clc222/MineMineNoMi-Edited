/*    */ package xyz.pixelatedw.mineminenomi.renderers.entities;
/*    */ 
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.EntityCloud;
/*    */ 
/*    */ public class CloudRenderer
/*    */   extends EntityRenderer<EntityCloud>
/*    */ {
/*    */   protected CloudRenderer(EntityRendererManager manager) {
/* 14 */     super(manager);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation getTextureLocation(EntityCloud entity) {
/* 20 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static class Factory
/*    */     implements IRenderFactory<EntityCloud>
/*    */   {
/*    */     public EntityRenderer<? super EntityCloud> createRenderFor(EntityRendererManager manager) {
/* 30 */       return new CloudRenderer(manager);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\CloudRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */