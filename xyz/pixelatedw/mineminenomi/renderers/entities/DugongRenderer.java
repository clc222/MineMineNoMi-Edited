/*    */ package xyz.pixelatedw.mineminenomi.renderers.entities;
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import java.util.function.Predicate;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.RenderType;
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
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.AbstractDugongEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.animals.DugongModel;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.layers.AuraLayer;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.layers.EyesLayer;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.layers.PotionLayer;
/*    */ 
/*    */ public class DugongRenderer<T extends AbstractDugongEntity, M extends DugongModel<T>> extends MobRenderer<T, M> {
/* 28 */   private static final RenderType ANGRY_EYES = ModRenderTypes.getEyesLayerRenderType(new ResourceLocation("mineminenomi", "textures/models/kung_fu_dugong_angry_eyes.png")); private final ResourceLocation baseTexture;
/* 29 */   private static final RenderType SLEEPING_EYES = ModRenderTypes.getEyesLayerRenderType(new ResourceLocation("mineminenomi", "textures/models/kung_fu_dugong_sleeping_eyes.png"));
/*    */   
/* 31 */   private static final Predicate<AbstractDugongEntity> IS_ANGRY = AbstractDugongEntity::isEnraged;
/* 32 */   private static final Predicate<AbstractDugongEntity> IS_RESTING = AbstractDugongEntity::isResting;
/*    */   
/*    */   public DugongRenderer(EntityRendererManager manager, ResourceLocation texture) {
/* 35 */     super(manager, (EntityModel)new DugongModel(), 0.4F);
/*    */     
/* 37 */     this.baseTexture = texture;
/*    */     
/* 39 */     func_177094_a((LayerRenderer)new BodyCoatingLayer((IEntityRenderer)this));
/* 40 */     func_177094_a((LayerRenderer)new HeadLayer((IEntityRenderer)this, 1.0F, 1.0F, 1.0F));
/* 41 */     func_177094_a((LayerRenderer)new HeldItemLayer((IEntityRenderer)this));
/* 42 */     func_177094_a((LayerRenderer)new PotionLayer((IEntityRenderer)this));
/* 43 */     func_177094_a((LayerRenderer)new AuraLayer((IEntityRenderer)this));
/* 44 */     func_177094_a((LayerRenderer)new EyesLayer((IEntityRenderer)this, ANGRY_EYES, IS_ANGRY));
/* 45 */     func_177094_a((LayerRenderer)new EyesLayer((IEntityRenderer)this, SLEEPING_EYES, IS_RESTING));
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(T entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 50 */     super.func_225623_a_((MobEntity)entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void setupRotations(T entity, MatrixStack matrixStack, float ageInTicks, float rotationYaw, float partialTicks) {
/* 55 */     super.func_225621_a_((LivingEntity)entity, matrixStack, ageInTicks, rotationYaw, partialTicks);
/* 56 */     if (entity.isTraining()) {
/* 57 */       switch (entity.getTrainingMode()) {
/*    */         case PUSHUPS:
/* 59 */           matrixStack.func_227861_a_(0.0D, 0.25D, 0.5D);
/* 60 */           matrixStack.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(-75.0F));
/*    */           break;
/*    */       } 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     }
/* 68 */     if (entity.isResting()) {
/* 69 */       matrixStack.func_227861_a_(0.0D, 0.25D, 0.5D);
/* 70 */       matrixStack.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(-90.0F));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public ResourceLocation getTextureLocation(T pEntity) {
/* 76 */     return this.baseTexture;
/*    */   }
/*    */   
/*    */   public static class Factory implements IRenderFactory {
/*    */     private final ResourceLocation texture;
/*    */     
/*    */     public Factory(ResourceLocation texture) {
/* 83 */       this.texture = texture;
/*    */     }
/*    */ 
/*    */     
/*    */     public EntityRenderer createRenderFor(EntityRendererManager manager) {
/* 88 */       return (EntityRenderer)new DugongRenderer<>(manager, this.texture);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\DugongRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */