/*    */ package xyz.pixelatedw.mineminenomi.renderers.morphs;
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphModel;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.layers.BodyCoatingLayer;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.layers.morphs.CandleChampionFaceLayer;
/*    */ 
/*    */ @OnlyIn(Dist.CLIENT)
/*    */ public class CandleChampionRenderer<T extends LivingEntity, M extends MorphModel<T>> extends ZoanMorphRenderer2<T, M> {
/*    */   public CandleChampionRenderer(EntityRendererManager renderManager, MorphInfo info) {
/* 22 */     super(renderManager, info);
/* 23 */     func_177094_a((LayerRenderer)new BodyCoatingLayer((IEntityRenderer)this));
/* 24 */     func_177094_a((LayerRenderer)new CandleChampionFaceLayer(this));
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_225623_a_(T entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 29 */     super.func_225623_a_(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
/*    */   }
/*    */ 
/*    */   
/*    */   public ResourceLocation getTextureLocation(T entity) {
/* 34 */     return this.info.getTexture();
/*    */   }
/*    */   
/*    */   public static class Factory<T extends LivingEntity> implements IRenderFactory<T> {
/*    */     private MorphInfo info;
/*    */     
/*    */     public Factory(MorphInfo info) {
/* 41 */       this.info = info;
/*    */     }
/*    */ 
/*    */     
/*    */     public EntityRenderer<? super T> createRenderFor(EntityRendererManager manager) {
/* 46 */       return (EntityRenderer)new CandleChampionRenderer<>(manager, this.info);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\morphs\CandleChampionRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */