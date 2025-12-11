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
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.PandaSharkEntity;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.animals.PandaSharkModel;
/*    */ 
/*    */ public class PandaSharkRenderer extends MobRenderer<PandaSharkEntity, PandaSharkModel> {
/* 16 */   private static final ResourceLocation TEXTURE = new ResourceLocation("mineminenomi", "textures/models/panda_shark.png");
/*    */   
/*    */   public PandaSharkRenderer(EntityRendererManager manager) {
/* 19 */     super(manager, (EntityModel)new PandaSharkModel(), 1.5F);
/*    */   }
/*    */ 
/*    */   
/*    */   public void scale(PandaSharkEntity entity, MatrixStack matrixStack, float partialTickTime) {
/* 24 */     matrixStack.func_227862_a_(entity.getSize(), entity.getSize(), entity.getSize());
/* 25 */     this.field_76989_e = entity.getSize() * 1.5F;
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(PandaSharkEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 30 */     super.func_225623_a_((MobEntity)entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
/*    */   }
/*    */ 
/*    */   
/*    */   public ResourceLocation getTextureLocation(PandaSharkEntity entity) {
/* 35 */     return TEXTURE;
/*    */   }
/*    */   
/*    */   public static class Factory
/*    */     implements IRenderFactory {
/*    */     public EntityRenderer createRenderFor(EntityRendererManager manager) {
/* 41 */       return (EntityRenderer)new PandaSharkRenderer(manager);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\PandaSharkRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */