/*    */ package xyz.pixelatedw.mineminenomi.renderers.entities;
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.MobRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.AbstractGorillaEntity;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.animals.GorillaModel;
/*    */ 
/*    */ public class GorillaRenderer<T extends AbstractGorillaEntity, M extends GorillaModel<T>> extends MobRenderer<T, M> {
/* 19 */   public static final ResourceLocation BLUGORI_TEXTURE = new ResourceLocation("mineminenomi", "textures/models/blugori.png");
/* 20 */   public static final ResourceLocation BLAGORI_TEXTURE = new ResourceLocation("mineminenomi", "textures/models/blagori.png");
/*    */   
/*    */   private ResourceLocation texture;
/*    */   
/*    */   public GorillaRenderer(EntityRendererManager manager, ResourceLocation texture) {
/* 25 */     super(manager, (EntityModel)new GorillaModel(), 0.8F);
/* 26 */     func_177094_a((LayerRenderer)new HeadLayer((IEntityRenderer)this, 1.0F, 1.0F, 1.0F));
/* 27 */     func_177094_a((LayerRenderer)new HeldItemLayer((IEntityRenderer)this));
/* 28 */     this.texture = texture;
/*    */   }
/*    */ 
/*    */   
/*    */   public void scale(T entity, MatrixStack matrixStack, float partialTickTime) {
/* 33 */     matrixStack.func_227862_a_(entity.getSize(), entity.getSize(), entity.getSize());
/* 34 */     this.field_76989_e = entity.getSize() / 1.9F;
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(T entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 39 */     super.func_225623_a_((MobEntity)entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
/*    */   }
/*    */ 
/*    */   
/*    */   public ResourceLocation getTextureLocation(T entity) {
/* 44 */     return this.texture;
/*    */   }
/*    */   
/*    */   public static class Factory
/*    */     implements IRenderFactory {
/*    */     private ResourceLocation texture;
/*    */     
/*    */     public Factory(ResourceLocation texture) {
/* 52 */       this.texture = texture;
/*    */     }
/*    */ 
/*    */     
/*    */     public EntityRenderer createRenderFor(EntityRendererManager manager) {
/* 57 */       return (EntityRenderer)new GorillaRenderer<>(manager, this.texture);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\GorillaRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */