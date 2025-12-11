/*    */ package xyz.pixelatedw.mineminenomi.renderers.entities;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.ability.DoppelmanEntity;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids.HumanoidModel;
/*    */ 
/*    */ @OnlyIn(Dist.CLIENT)
/*    */ public class DoppelmanRenderer
/*    */   extends HumanoidRenderer<DoppelmanEntity, BipedModel<DoppelmanEntity>> {
/*    */   public DoppelmanRenderer(EntityRendererManager renderManager) {
/* 19 */     super(renderManager, new HumanoidModel(), "doppelman");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void scale(DoppelmanEntity entity, MatrixStack matrixStack, float partialTickTime) {
/* 25 */     float scale = 1.0F + entity.getShadows() / 7.0F;
/*    */     
/* 27 */     if (scale < 1.0F) {
/* 28 */       scale = 1.0F;
/*    */     }
/* 30 */     matrixStack.func_227862_a_(scale, scale, scale);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static class Factory
/*    */     implements IRenderFactory<DoppelmanEntity>
/*    */   {
/*    */     public EntityRenderer<? super DoppelmanEntity> createRenderFor(EntityRendererManager manager) {
/* 42 */       return (EntityRenderer<? super DoppelmanEntity>)new DoppelmanRenderer(manager);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\DoppelmanRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */