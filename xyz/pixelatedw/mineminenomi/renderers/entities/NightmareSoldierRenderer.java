/*    */ package xyz.pixelatedw.mineminenomi.renderers.entities;
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.ability.NightmareSoldierEntity;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids.HumanoidModel;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.layers.abilities.NightmareSoldierLayer;
/*    */ 
/*    */ @OnlyIn(Dist.CLIENT)
/*    */ public class NightmareSoldierRenderer extends HumanoidRenderer<NightmareSoldierEntity, BipedModel<NightmareSoldierEntity>> {
/*    */   public NightmareSoldierRenderer(EntityRendererManager renderManager) {
/* 19 */     super(renderManager, new HumanoidModel());
/* 20 */     func_177094_a((LayerRenderer)new NightmareSoldierLayer((IEntityRenderer)this));
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(NightmareSoldierEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 25 */     super.func_225623_a_(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static class Factory
/*    */     implements IRenderFactory<NightmareSoldierEntity>
/*    */   {
/*    */     public EntityRenderer<? super NightmareSoldierEntity> createRenderFor(EntityRendererManager manager) {
/* 34 */       return (EntityRenderer<? super NightmareSoldierEntity>)new NightmareSoldierRenderer(manager);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\NightmareSoldierRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */