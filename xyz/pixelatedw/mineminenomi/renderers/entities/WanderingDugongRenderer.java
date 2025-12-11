/*    */ package xyz.pixelatedw.mineminenomi.renderers.entities;
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.MobRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.HeadLayer;
/*    */ import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.AbstractDugongEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.WanderingDugongEntity;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.animals.DugongModel;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.layers.AuraLayer;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.layers.PotionLayer;
/*    */ 
/*    */ public class WanderingDugongRenderer<T extends WanderingDugongEntity, M extends DugongModel<T>> extends MobRenderer<T, M> {
/* 23 */   private static final ResourceLocation BASE_TEXTURE = new ResourceLocation("mineminenomi", "textures/models/kung_fu_dugong.png");
/*    */   
/*    */   public WanderingDugongRenderer(EntityRendererManager manager) {
/* 26 */     super(manager, (EntityModel)new DugongModel(), 0.4F);
/*    */     
/* 28 */     func_177094_a((LayerRenderer)new BodyCoatingLayer((IEntityRenderer)this));
/* 29 */     func_177094_a((LayerRenderer)new HeadLayer((IEntityRenderer)this, 1.0F, 1.0F, 1.0F));
/* 30 */     func_177094_a((LayerRenderer)new HeldItemLayer((IEntityRenderer)this));
/* 31 */     func_177094_a((LayerRenderer)new PotionLayer((IEntityRenderer)this));
/* 32 */     func_177094_a((LayerRenderer)new AuraLayer((IEntityRenderer)this));
/* 33 */     func_177094_a((LayerRenderer)new WanderingDugongScarsLayer((IEntityRenderer)this));
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(T entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 38 */     super.func_225623_a_((MobEntity)entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void setupRotations(T entity, MatrixStack matrixStack, float ageInTicks, float rotationYaw, float partialTicks) {
/* 43 */     super.func_225621_a_((LivingEntity)entity, matrixStack, ageInTicks, rotationYaw, partialTicks);
/* 44 */     if (entity.isTraining()) {
/* 45 */       switch (entity.getTrainingMode()) {
/*    */         case PUSHUPS:
/* 47 */           matrixStack.func_227861_a_(0.0D, 0.25D, 0.5D);
/* 48 */           matrixStack.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(-75.0F));
/*    */           break;
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation getTextureLocation(T pEntity) {
/* 60 */     return BASE_TEXTURE;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static class Factory
/*    */     implements IRenderFactory
/*    */   {
/*    */     public EntityRenderer createRenderFor(EntityRendererManager manager) {
/* 70 */       return (EntityRenderer)new WanderingDugongRenderer<>(manager);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\WanderingDugongRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */