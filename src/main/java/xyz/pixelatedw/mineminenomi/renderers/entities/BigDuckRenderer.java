/*    */ package xyz.pixelatedw.mineminenomi.renderers.entities;
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.BigDuckEntity;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.animals.BigDuckModel;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.layers.BigDuckSaddleLayer;
/*    */ 
/*    */ public class BigDuckRenderer<T extends BigDuckEntity, M extends BigDuckModel<T>> extends HumanoidRenderer<T, M> {
/*    */   public BigDuckRenderer(EntityRendererManager manager) {
/* 19 */     super(manager, (M)new BigDuckModel());
/* 20 */     this.field_76989_e = 0.7F;
/* 21 */     this.texture = new ResourceLocation("mineminenomi", "textures/models/big_duck.png");
/* 22 */     func_177094_a((LayerRenderer)new BigDuckSaddleLayer((IEntityRenderer)this));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(T entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 28 */     matrixStack.func_227860_a_();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 42 */     super.func_225623_a_(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
/* 43 */     matrixStack.func_227865_b_();
/*    */   }
/*    */ 
/*    */   
/*    */   public static class Factory
/*    */     implements IRenderFactory
/*    */   {
/*    */     public EntityRenderer createRenderFor(EntityRendererManager manager) {
/* 51 */       return (EntityRenderer)new BigDuckRenderer<>(manager);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\BigDuckRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */