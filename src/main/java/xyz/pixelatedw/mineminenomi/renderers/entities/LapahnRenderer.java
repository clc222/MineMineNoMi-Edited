/*    */ package xyz.pixelatedw.mineminenomi.renderers.entities;
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.client.renderer.entity.MobRenderer;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.LapahnEntity;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.animals.LapahnModel;
/*    */ 
/*    */ public class LapahnRenderer<T extends LapahnEntity, M extends LapahnModel<T>> extends MobRenderer<T, M> {
/* 16 */   private static final ResourceLocation NORMAL_TEXTURE = new ResourceLocation("mineminenomi", "textures/models/lapahn.png");
/* 17 */   private static final ResourceLocation ENRAGED_TEXTURE = new ResourceLocation("mineminenomi", "textures/models/lapahn_angry.png");
/*    */   
/*    */   public LapahnRenderer(EntityRendererManager manager) {
/* 20 */     super(manager, (EntityModel)new LapahnModel(), 0.8F);
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(T entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 25 */     super.func_225623_a_((MobEntity)entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void setupRotations(T entity, MatrixStack matrixStack, float ageInTicks, float rotationYaw, float partialTicks) {
/* 30 */     super.func_225621_a_((LivingEntity)entity, matrixStack, ageInTicks, rotationYaw, partialTicks);
/* 31 */     if (entity.isResting()) {
/* 32 */       matrixStack.func_227861_a_(0.0D, -0.45D, 0.0D);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public ResourceLocation getTextureLocation(T entity) {
/* 38 */     return entity.isEnraged() ? ENRAGED_TEXTURE : NORMAL_TEXTURE;
/*    */   }
/*    */   
/*    */   public static class Factory
/*    */     implements IRenderFactory {
/*    */     public EntityRenderer createRenderFor(EntityRendererManager manager) {
/* 44 */       return (EntityRenderer)new LapahnRenderer<>(manager);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\LapahnRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */