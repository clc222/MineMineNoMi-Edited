/*    */ package xyz.pixelatedw.mineminenomi.renderers.morphs;
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.entity.model.PlayerModel;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphModel;
/*    */ 
/*    */ public class BaraSplitPartialMorphRenderer<T extends AbstractClientPlayerEntity, M extends MorphModel> extends ZoanMorphRenderer<T, M> {
/*    */   public BaraSplitPartialMorphRenderer(EntityRendererManager renderManager, MorphInfo info, boolean hasSmallHands) {
/* 22 */     super(renderManager, info, hasSmallHands);
/* 23 */     func_177094_a((LayerRenderer)new BipedArmorLayer((IEntityRenderer)this, new BipedModel(0.5F), new BipedModel(1.0F)));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225623_a_(AbstractClientPlayerEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 29 */     PlayerModel<AbstractClientPlayerEntity> playermodel = (PlayerModel<AbstractClientPlayerEntity>)func_217764_d();
/* 30 */     playermodel.field_178721_j.field_78806_j = false;
/* 31 */     playermodel.field_178722_k.field_78806_j = false;
/* 32 */     playermodel.field_178731_d.field_78806_j = false;
/* 33 */     playermodel.field_178733_c.field_78806_j = false;
/* 34 */     super.func_225623_a_(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation func_110775_a(AbstractClientPlayerEntity entity) {
/* 42 */     return entity.func_110306_p();
/*    */   }
/*    */   
/*    */   public static class Factory<T extends PlayerEntity>
/*    */     implements IRenderFactory<T>
/*    */   {
/*    */     private MorphInfo info;
/*    */     private boolean hasSmallHands;
/*    */     
/*    */     public Factory(MorphInfo info, boolean hasSmallHands) {
/* 52 */       this.info = info;
/* 53 */       this.hasSmallHands = hasSmallHands;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public EntityRenderer<? super T> createRenderFor(EntityRendererManager manager) {
/* 59 */       return (EntityRenderer)new BaraSplitPartialMorphRenderer<>(manager, this.info, this.hasSmallHands);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\morphs\BaraSplitPartialMorphRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */