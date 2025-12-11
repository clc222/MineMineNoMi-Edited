/*    */ package xyz.pixelatedw.mineminenomi.renderers.entities;
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.client.renderer.entity.MobRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.FlyingFishEntity;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.animals.FlyingFishModel;
/*    */ 
/*    */ public class FlyingFishRenderer extends MobRenderer<FlyingFishEntity, FlyingFishModel> {
/* 15 */   private static final ResourceLocation TEXTURE = new ResourceLocation("mineminenomi", "textures/models/flying_fish.png");
/*    */   
/*    */   public FlyingFishRenderer(EntityRendererManager manager) {
/* 18 */     super(manager, (EntityModel)new FlyingFishModel(), 1.5F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void scale(FlyingFishEntity entity, MatrixStack matrixStack, float partialTickTime) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(FlyingFishEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 28 */     if (entity.isSaddled()) {
/* 29 */       ((FlyingFishModel)this.field_77045_g).renderSaddle();
/*    */     }
/*    */     
/* 32 */     super.func_225623_a_((MobEntity)entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
/*    */   }
/*    */ 
/*    */   
/*    */   public ResourceLocation getTextureLocation(FlyingFishEntity entity) {
/* 37 */     return TEXTURE;
/*    */   }
/*    */   
/*    */   public static class Factory
/*    */     implements IRenderFactory {
/*    */     public EntityRenderer createRenderFor(EntityRendererManager manager) {
/* 43 */       return (EntityRenderer)new FlyingFishRenderer(manager);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\FlyingFishRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */