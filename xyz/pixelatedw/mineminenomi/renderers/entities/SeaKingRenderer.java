/*    */ package xyz.pixelatedw.mineminenomi.renderers.entities;
/*    */ 
/*    */ import net.minecraft.client.renderer.culling.ClippingHelper;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.dispenser.IPosition;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.animals.SeaKingModel;
/*    */ 
/*    */ public class SeaKingRenderer<T extends MobEntity, M extends SeaKingModel<T>> extends HumanoidRenderer<T, M> {
/*    */   public SeaKingRenderer(EntityRendererManager manager) {
/* 16 */     super(manager, (M)new SeaKingModel());
/* 17 */     this.scale = new float[] { 1.0F, 1.0F, 1.0F };
/* 18 */     this.field_76989_e = 0.7F;
/*    */   }
/*    */ 
/*    */   
/*    */   public ResourceLocation func_110775_a(T entity) {
/* 23 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_225626_a_(T entity, ClippingHelper p_225626_2_, double camX, double camY, double camZ) {
/* 28 */     if (entity.func_233580_cy_().func_218137_a((IPosition)new Vector3d(camX, camY, camZ), 100.0D))
/*    */     {
/* 30 */       return true;
/*    */     }
/* 32 */     return super.func_225626_a_(entity, p_225626_2_, camX, camY, camZ);
/*    */   }
/*    */ 
/*    */   
/*    */   public static class Factory
/*    */     implements IRenderFactory
/*    */   {
/*    */     public EntityRenderer createRenderFor(EntityRendererManager manager) {
/* 40 */       return (EntityRenderer)new SeaKingRenderer<>(manager);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\SeaKingRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */