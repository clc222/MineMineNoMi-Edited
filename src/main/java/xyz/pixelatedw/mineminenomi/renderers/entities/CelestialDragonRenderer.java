/*    */ package xyz.pixelatedw.mineminenomi.renderers.entities;
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.worldgov.CelestialDragonEntity;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids.HumanoidModel;
/*    */ 
/*    */ public class CelestialDragonRenderer extends HumanoidRenderer<CelestialDragonEntity, HumanoidModel<CelestialDragonEntity>> {
/*    */   public CelestialDragonRenderer(EntityRendererManager manager) {
/* 16 */     super(manager, new HumanoidModel());
/*    */     
/* 18 */     func_177094_a((LayerRenderer)new CelestialDragonWigLayer((IEntityRenderer)this));
/* 19 */     func_177094_a((LayerRenderer)new CelestialDragonSlaveRideLayer((IEntityRenderer)this));
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(CelestialDragonEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 24 */     super.func_225623_a_(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void setupRotations(CelestialDragonEntity entity, MatrixStack matrixStack, float ageInTicks, float rotationYaw, float partialTicks) {
/* 29 */     ((HumanoidModel)this.field_77045_g).field_217113_d = entity.isRidingSlave();
/* 30 */     super.setupRotations(entity, matrixStack, ageInTicks, rotationYaw, partialTicks);
/*    */   }
/*    */   
/*    */   public static class Factory
/*    */     implements IRenderFactory {
/*    */     public EntityRenderer createRenderFor(EntityRendererManager manager) {
/* 36 */       return (EntityRenderer)new CelestialDragonRenderer(manager);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\CelestialDragonRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */