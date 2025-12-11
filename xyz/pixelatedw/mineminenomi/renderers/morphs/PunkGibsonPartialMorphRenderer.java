/*    */ package xyz.pixelatedw.mineminenomi.renderers.morphs;
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
/*    */ import net.minecraft.client.renderer.entity.layers.ElytraLayer;
/*    */ import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
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
/*    */ import xyz.pixelatedw.mineminenomi.renderers.layers.morphs.DamnedPunkLayer;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.layers.morphs.PunkGibsonLayer;
/*    */ 
/*    */ @OnlyIn(Dist.CLIENT)
/*    */ public class PunkGibsonPartialMorphRenderer<T extends AbstractClientPlayerEntity, M extends MorphModel> extends ZoanMorphRenderer<T, M> {
/*    */   public PunkGibsonPartialMorphRenderer(EntityRendererManager renderManager, MorphInfo info, boolean isDamnedPunk) {
/* 29 */     super(renderManager, info);
/* 30 */     removeLayer((Class)HeldItemLayer.class);
/* 31 */     func_177094_a((LayerRenderer)new AuraLayer((IEntityRenderer)this));
/* 32 */     func_177094_a((LayerRenderer)new BipedArmorLayer((IEntityRenderer)this, new BipedModel(0.5F), new BipedModel(1.0F)));
/* 33 */     func_177094_a((LayerRenderer)new MinkFeaturesLayer((IEntityRenderer)this));
/* 34 */     func_177094_a((LayerRenderer)new CapeLayer((IEntityRenderer)this));
/* 35 */     func_177094_a((LayerRenderer)new ElytraLayer((IEntityRenderer)this));
/* 36 */     if (isDamnedPunk) {
/* 37 */       func_177094_a((LayerRenderer)new DamnedPunkLayer((IEntityRenderer)this));
/*    */     } else {
/* 39 */       func_177094_a((LayerRenderer)new PunkGibsonLayer((IEntityRenderer)this));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_225623_a_(AbstractClientPlayerEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 45 */     ((PlayerModel)func_217764_d()).field_178723_h.field_78806_j = false;
/* 46 */     ((PlayerModel)func_217764_d()).field_178732_b.field_78806_j = false;
/*    */     
/* 48 */     super.func_225623_a_(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
/*    */   }
/*    */   
/*    */   public static class Factory<T extends PlayerEntity>
/*    */     implements IRenderFactory<T>
/*    */   {
/*    */     private MorphInfo info;
/*    */     private boolean isDamnedPunk;
/*    */     
/*    */     public Factory(MorphInfo info, boolean isDamnedPunk) {
/* 58 */       this.info = info;
/* 59 */       this.isDamnedPunk = isDamnedPunk;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public EntityRenderer<? super T> createRenderFor(EntityRendererManager manager) {
/* 65 */       return (EntityRenderer)new PunkGibsonPartialMorphRenderer<>(manager, this.info, this.isDamnedPunk);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\morphs\PunkGibsonPartialMorphRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */