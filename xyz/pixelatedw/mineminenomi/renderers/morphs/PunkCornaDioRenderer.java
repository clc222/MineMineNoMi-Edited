/*    */ package xyz.pixelatedw.mineminenomi.renderers.morphs;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphModel;
/*    */ import xyz.pixelatedw.mineminenomi.entities.zoan.PunkCornaDioBullMorphInfo;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.layers.BodyCoatingLayer;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.layers.MinkFeaturesLayer;
/*    */ 
/*    */ @OnlyIn(Dist.CLIENT)
/*    */ public class PunkCornaDioRenderer<T extends AbstractClientPlayerEntity, M extends MorphModel> extends ZoanMorphRenderer<T, M> {
/*    */   public PunkCornaDioRenderer(EntityRendererManager renderManager, MorphInfo info) {
/* 26 */     super(renderManager, info);
/* 27 */     func_177094_a((LayerRenderer)new BodyCoatingLayer((IEntityRenderer)this));
/* 28 */     this.cornaDioRenderer = (ZoanMorphRenderer)(new ZoanMorphRenderer.Factory<>((MorphInfo)PunkCornaDioBullMorphInfo.INSTANCE)).createRenderFor(renderManager);
/* 29 */     func_177094_a((LayerRenderer)new MinkFeaturesLayer((IEntityRenderer)this));
/*    */   }
/*    */   
/*    */   private ZoanMorphRenderer<AbstractClientPlayerEntity, MorphModel> cornaDioRenderer;
/*    */   
/*    */   public void func_225623_a_(AbstractClientPlayerEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 35 */     matrixStack.func_227860_a_();
/* 36 */     matrixStack.func_227861_a_(0.0D, 2.0D, 0.0D);
/* 37 */     super.func_225623_a_(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
/* 38 */     matrixStack.func_227865_b_();
/* 39 */     matrixStack.func_227860_a_();
/* 40 */     matrixStack.func_227862_a_(2.0F, 2.0F, 2.0F);
/* 41 */     this.cornaDioRenderer.func_225623_a_(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
/* 42 */     matrixStack.func_227865_b_();
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
/*    */     
/*    */     public Factory(MorphInfo info) {
/* 57 */       this.info = info;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public EntityRenderer<? super T> createRenderFor(EntityRendererManager manager) {
/* 63 */       return (EntityRenderer)new PunkCornaDioRenderer<>(manager, this.info);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\morphs\PunkCornaDioRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */