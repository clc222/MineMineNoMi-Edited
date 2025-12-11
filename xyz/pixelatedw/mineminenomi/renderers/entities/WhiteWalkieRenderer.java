/*    */ package xyz.pixelatedw.mineminenomi.renderers.entities;
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.MobRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.WhiteWalkieEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.animals.WhiteWalkieModel;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.layers.EyesLayer;
/*    */ 
/*    */ public class WhiteWalkieRenderer<T extends WhiteWalkieEntity, M extends WhiteWalkieModel<T>> extends MobRenderer<T, M> {
/* 22 */   private static final RenderType SLEEPING_EYES = ModRenderTypes.getEyesLayerRenderType(new ResourceLocation("mineminenomi", "textures/models/white_walkie_sleeping_eyes.png"));
/*    */   
/* 24 */   public static final ResourceLocation TEXTURE = new ResourceLocation("mineminenomi", "textures/models/white_walkie.png");
/*    */   
/* 26 */   private static final Predicate<WhiteWalkieEntity> IS_SLEEPING = WhiteWalkieEntity::func_70608_bn;
/*    */   
/*    */   public WhiteWalkieRenderer(EntityRendererManager manager) {
/* 29 */     super(manager, (EntityModel)new WhiteWalkieModel(), 1.2F);
/* 30 */     func_177094_a((LayerRenderer)new EyesLayer((IEntityRenderer)this, SLEEPING_EYES, IS_SLEEPING));
/* 31 */     func_177094_a((LayerRenderer)new WhiteWalkieSaddleLayer((IEntityRenderer)this));
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(T entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 36 */     super.func_225623_a_((MobEntity)entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void setupRotations(T entity, MatrixStack matrixStack, float ageInTicks, float rotationYaw, float partialTicks) {
/* 41 */     super.func_225621_a_((LivingEntity)entity, matrixStack, ageInTicks, rotationYaw, partialTicks);
/* 42 */     if (entity.func_70608_bn()) {
/* 43 */       matrixStack.func_227861_a_(0.0D, -0.55D, 0.0D);
/* 44 */       ((WhiteWalkieModel)func_217764_d()).setSleepPosition(true);
/*    */     } else {
/*    */       
/* 47 */       ((WhiteWalkieModel)func_217764_d()).setSleepPosition(false);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public ResourceLocation getTextureLocation(T entity) {
/* 53 */     return TEXTURE;
/*    */   }
/*    */   
/*    */   public static class Factory
/*    */     implements IRenderFactory {
/*    */     public EntityRenderer createRenderFor(EntityRendererManager manager) {
/* 59 */       return (EntityRenderer)new WhiteWalkieRenderer<>(manager);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\WhiteWalkieRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */