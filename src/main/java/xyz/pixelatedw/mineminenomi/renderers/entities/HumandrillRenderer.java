/*    */ package xyz.pixelatedw.mineminenomi.renderers.entities;
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.MobRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.HumandrillEntity;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.animals.HumandrillModel;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.layers.AuraLayer;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.layers.HandcuffsLayer;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.layers.PotionLayer;
/*    */ 
/*    */ public class HumandrillRenderer<T extends HumandrillEntity, M extends HumandrillModel<T>> extends MobRenderer<T, M> {
/* 21 */   public static final ResourceLocation TEXTURE = new ResourceLocation("mineminenomi", "textures/models/humandrill.png");
/*    */   
/*    */   public HumandrillRenderer(EntityRendererManager manager) {
/* 24 */     super(manager, (EntityModel)new HumandrillModel(), 1.2F);
/* 25 */     func_177094_a((LayerRenderer)new BodyCoatingLayer((IEntityRenderer)this));
/* 26 */     func_177094_a((LayerRenderer)new PotionLayer((IEntityRenderer)this));
/* 27 */     func_177094_a((LayerRenderer)new AuraLayer((IEntityRenderer)this));
/* 28 */     func_177094_a((LayerRenderer)new HandcuffsLayer((IEntityRenderer)this));
/* 29 */     func_177094_a((LayerRenderer)new BindLayer((IEntityRenderer)this));
/* 30 */     func_177094_a((LayerRenderer)new HanaHandsLayer((IEntityRenderer)this));
/* 31 */     func_177094_a((LayerRenderer)new HeldItemLayer((IEntityRenderer)this));
/*    */   }
/*    */ 
/*    */   
/*    */   public void scale(T entity, MatrixStack matrixStack, float partialTickTime) {
/* 36 */     matrixStack.func_227862_a_(entity.getSize(), entity.getSize(), entity.getSize());
/* 37 */     this.field_76989_e = entity.getSize() / 1.2F;
/*    */   }
/*    */ 
/*    */   
/*    */   public ResourceLocation getTextureLocation(T pEntity) {
/* 42 */     return TEXTURE;
/*    */   }
/*    */   
/*    */   public static class Factory
/*    */     implements IRenderFactory {
/*    */     public EntityRenderer createRenderFor(EntityRendererManager manager) {
/* 48 */       return (EntityRenderer)new HumandrillRenderer<>(manager);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\HumandrillRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */