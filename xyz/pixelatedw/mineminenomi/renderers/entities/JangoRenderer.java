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
/*    */ import xyz.pixelatedw.mineminenomi.entities.JangoEntity;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids.HumanoidModel;
/*    */ 
/*    */ public class JangoRenderer extends HumanoidRenderer<JangoEntity, HumanoidModel<JangoEntity>> {
/*    */   public JangoRenderer(EntityRendererManager manager) {
/* 16 */     super(manager, new HumanoidModel(), "jango");
/* 17 */     this.bullshitFlag = true;
/*    */     
/* 19 */     func_177094_a((LayerRenderer)new JangoMushroomLayer((IEntityRenderer)this));
/* 20 */     func_177094_a((LayerRenderer)new JangoGlassesLayer((IEntityRenderer)this));
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(JangoEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 25 */     super.func_225623_a_(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
/*    */   }
/*    */   
/*    */   public static class Factory
/*    */     implements IRenderFactory {
/*    */     public EntityRenderer createRenderFor(EntityRendererManager manager) {
/* 31 */       return (EntityRenderer)new JangoRenderer(manager);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\JangoRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */