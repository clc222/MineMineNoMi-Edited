/*    */ package xyz.pixelatedw.mineminenomi.renderers.morphs;
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
/*    */ import net.minecraft.client.renderer.entity.layers.CapeLayer;
/*    */ import net.minecraft.client.renderer.entity.layers.ElytraLayer;
/*    */ import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphModel;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.layers.MinkFeaturesLayer;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.layers.morphs.KameWalkLayer;
/*    */ 
/*    */ @OnlyIn(Dist.CLIENT)
/*    */ public class KameWalkPartialMorphRenderer<T extends AbstractClientPlayerEntity, M extends MorphModel> extends ZoanMorphRenderer<T, M> {
/*    */   public KameWalkPartialMorphRenderer(EntityRendererManager renderManager, MorphInfo info, boolean hasSmallHands) {
/* 28 */     super(renderManager, info, hasSmallHands);
/* 29 */     removeLayer((Class)HeldItemLayer.class);
/* 30 */     KameWalkLayer partialRenderer = new KameWalkLayer((IEntityRenderer)this);
/* 31 */     func_177094_a((LayerRenderer)partialRenderer);
/* 32 */     func_177094_a((LayerRenderer)new HeldItemLayer((IEntityRenderer)this));
/* 33 */     func_177094_a((LayerRenderer)new BipedArmorLayer((IEntityRenderer)this, new BipedModel(0.5F), new BipedModel(1.0F)));
/* 34 */     func_177094_a((LayerRenderer)new MinkFeaturesLayer((IEntityRenderer)this));
/* 35 */     func_177094_a((LayerRenderer)new CapeLayer((IEntityRenderer)this));
/* 36 */     func_177094_a((LayerRenderer)new ElytraLayer((IEntityRenderer)this));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225623_a_(AbstractClientPlayerEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 42 */     super.func_225623_a_(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation func_110775_a(AbstractClientPlayerEntity entity) {
/* 48 */     return entity.func_110306_p();
/*    */   }
/*    */   
/*    */   public static class Factory<T extends PlayerEntity>
/*    */     implements IRenderFactory<T>
/*    */   {
/*    */     private MorphInfo info;
/*    */     private boolean hasSmallHands;
/*    */     
/*    */     public Factory(MorphInfo info, boolean hasSmallHands) {
/* 58 */       this.info = info;
/* 59 */       this.hasSmallHands = hasSmallHands;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public EntityRenderer<? super T> createRenderFor(EntityRendererManager manager) {
/* 65 */       return (EntityRenderer)new KameWalkPartialMorphRenderer<>(manager, this.info, this.hasSmallHands);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\morphs\KameWalkPartialMorphRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */