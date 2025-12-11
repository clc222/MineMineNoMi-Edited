/*    */ package xyz.pixelatedw.mineminenomi.renderers.entities;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.models.morphs.NewPhoenixFlyModel;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.layers.morphs.PhoenixFlamesLayer;
/*    */ 
/*    */ public class DummyNewPhoenixRenderer<T extends LivingEntity> extends DummyRenderer<T, NewPhoenixFlyModel<T>> {
/*    */   public DummyNewPhoenixRenderer(EntityRendererManager manager, NewPhoenixFlyModel<T> model, ResourceLocation texture, Vector3f size) {
/* 15 */     super(manager, model, texture, size);
/* 16 */     func_177094_a((LayerRenderer)new PhoenixFlamesLayer((IEntityRenderer)this));
/*    */   }
/*    */   
/*    */   public static class Factory<M extends NewPhoenixFlyModel>
/*    */     implements IRenderFactory {
/*    */     private M model;
/*    */     private ResourceLocation texture;
/* 23 */     private Vector3f size = new Vector3f(1.0F, 1.0F, 1.0F);
/*    */ 
/*    */     
/*    */     public Factory(M model, ResourceLocation texture) {
/* 27 */       this(model, texture, new Vector3f(1.0F, 1.0F, 1.0F));
/*    */     }
/*    */ 
/*    */     
/*    */     public Factory(M model, ResourceLocation texture, Vector3f size) {
/* 32 */       this.model = model;
/* 33 */       this.texture = texture;
/* 34 */       this.size = size;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public EntityRenderer createRenderFor(EntityRendererManager manager) {
/* 40 */       return (EntityRenderer)new DummyNewPhoenixRenderer<>(manager, (NewPhoenixFlyModel<LivingEntity>)this.model, this.texture, this.size);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\DummyNewPhoenixRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */