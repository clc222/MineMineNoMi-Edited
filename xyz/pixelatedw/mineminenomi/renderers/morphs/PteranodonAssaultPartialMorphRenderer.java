/*    */ package xyz.pixelatedw.mineminenomi.renderers.morphs;
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.client.renderer.entity.model.PlayerModel;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphModel;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.layers.AuraLayer;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.layers.MinkFeaturesLayer;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.layers.morphs.PartialZoanHeldItemLayer;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.layers.morphs.PteranodonAssaultLayer;
/*    */ 
/*    */ @OnlyIn(Dist.CLIENT)
/*    */ public class PteranodonAssaultPartialMorphRenderer<T extends AbstractClientPlayerEntity, M extends MorphModel> extends ZoanMorphRenderer<T, M> {
/*    */   public PteranodonAssaultPartialMorphRenderer(EntityRendererManager renderManager, MorphInfo info) {
/* 26 */     super(renderManager, info);
/* 27 */     removeLayer((Class)HeldItemLayer.class);
/* 28 */     PteranodonAssaultLayer partialRenderer = new PteranodonAssaultLayer((IEntityRenderer)this);
/* 29 */     func_177094_a((LayerRenderer)partialRenderer);
/* 30 */     func_177094_a((LayerRenderer)new AuraLayer((IEntityRenderer)this));
/* 31 */     func_177094_a((LayerRenderer)new PartialZoanHeldItemLayer((IEntityRenderer)this, (MorphModel)partialRenderer.getPartialModel()));
/* 32 */     func_177094_a((LayerRenderer)new MinkFeaturesLayer((IEntityRenderer)this));
/* 33 */     removeLayer((Class)BodyCoatingLayer.class);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225623_a_(AbstractClientPlayerEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 39 */     ((PlayerModel)func_217764_d()).field_178724_i.field_78806_j = false;
/* 40 */     ((PlayerModel)func_217764_d()).field_178723_h.field_78806_j = false;
/*    */     
/* 42 */     ((PlayerModel)func_217764_d()).field_178734_a.field_78806_j = false;
/* 43 */     ((PlayerModel)func_217764_d()).field_178732_b.field_78806_j = false;
/*    */     
/* 45 */     super.func_225623_a_(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
/*    */   }
/*    */   
/*    */   public static class Factory<T extends PlayerEntity>
/*    */     implements IRenderFactory<T>
/*    */   {
/*    */     private MorphInfo info;
/*    */     
/*    */     public Factory(MorphInfo info) {
/* 54 */       this.info = info;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public EntityRenderer<? super T> createRenderFor(EntityRendererManager manager) {
/* 60 */       return (EntityRenderer)new PteranodonAssaultPartialMorphRenderer<>(manager, this.info);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\morphs\PteranodonAssaultPartialMorphRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */