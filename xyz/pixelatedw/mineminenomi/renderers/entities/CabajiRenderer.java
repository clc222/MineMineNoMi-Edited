/*    */ package xyz.pixelatedw.mineminenomi.renderers.entities;
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.buggypirates.CabajiEntity;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids.HumanoidModel;
/*    */ 
/*    */ public class CabajiRenderer<E extends CabajiEntity, M extends BipedModel<E>> extends HumanoidRenderer<E, M> {
/*    */   public CabajiRenderer(EntityRendererManager manager, M model) {
/* 15 */     super(manager, model, "cabaji");
/* 16 */     func_177094_a((LayerRenderer)new CabajiUnicycleLayer((IEntityRenderer)this));
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(E entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 21 */     matrixStack.func_227860_a_();
/* 22 */     if (entity.hasUnicycle()) {
/* 23 */       matrixStack.func_227861_a_(0.0D, 0.3D, 0.0D);
/*    */     }
/* 25 */     super.func_225623_a_(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
/* 26 */     matrixStack.func_227865_b_();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static class Factory
/*    */     implements IRenderFactory
/*    */   {
/*    */     public EntityRenderer createRenderFor(EntityRendererManager manager) {
/* 35 */       return (EntityRenderer)new CabajiRenderer<>(manager, new HumanoidModel());
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\CabajiRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */