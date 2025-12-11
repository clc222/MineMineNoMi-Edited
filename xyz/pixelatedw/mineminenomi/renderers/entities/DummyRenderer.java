/*    */ package xyz.pixelatedw.mineminenomi.renderers.entities;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.LivingRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ 
/*    */ public class DummyRenderer<T extends LivingEntity, M extends EntityModel<T>> extends LivingRenderer<T, M> {
/*    */   private ResourceLocation texture;
/*    */   private Vector3f size;
/*    */   
/*    */   public DummyRenderer(EntityRendererManager manager, M model, ResourceLocation texture, Vector3f size) {
/* 23 */     super(manager, (EntityModel)model, 0.8F);
/* 24 */     this.texture = texture;
/* 25 */     this.size = size;
/* 26 */     func_177094_a((LayerRenderer)new BipedArmorLayer((IEntityRenderer)this, new BipedModel(0.5F), new BipedModel(1.0F)));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void func_225620_a_(T entitylivingbase, MatrixStack matrixStack, float partialTickTime) {
/* 32 */     matrixStack.func_227862_a_(this.size.func_195899_a(), this.size.func_195900_b(), this.size.func_195902_c());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation getTextureLocation(T entity) {
/* 38 */     return this.texture;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean func_177070_b(T entity) {
/* 44 */     return false;
/*    */   }
/*    */   
/*    */   public static class Factory<M extends EntityModel>
/*    */     implements IRenderFactory
/*    */   {
/*    */     private M model;
/*    */     private ResourceLocation texture;
/*    */     private Vector3f size;
/*    */     
/*    */     public Factory(M model, ResourceLocation texture) {
/* 55 */       this(model, texture, new Vector3f(1.0F, 1.0F, 1.0F));
/*    */     }
/*    */ 
/*    */     
/*    */     public Factory(M model, ResourceLocation texture, float size) {
/* 60 */       this(model, texture, new Vector3f(size, size, size));
/*    */     }
/*    */ 
/*    */     
/*    */     public Factory(M model, ResourceLocation texture, Vector3f size) {
/* 65 */       this.model = model;
/* 66 */       this.texture = texture;
/* 67 */       this.size = size;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public EntityRenderer createRenderFor(EntityRendererManager manager) {
/* 73 */       return (EntityRenderer)new DummyRenderer<>(manager, (EntityModel<LivingEntity>)this.model, this.texture, this.size);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\DummyRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */