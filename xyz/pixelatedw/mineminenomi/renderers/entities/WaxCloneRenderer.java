/*    */ package xyz.pixelatedw.mineminenomi.renderers.entities;
/*    */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.resources.DefaultPlayerSkin;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.ability.WaxCloneEntity;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids.HumanoidModel;
/*    */ 
/*    */ public class WaxCloneRenderer extends HumanoidRenderer<WaxCloneEntity, BipedModel<WaxCloneEntity>> {
/* 16 */   public static final ResourceLocation WAX_LOCATION = new ResourceLocation("mineminenomi", "textures/models/zoanmorph/candle_lock.png");
/*    */   
/*    */   public WaxCloneRenderer(EntityRendererManager manager) {
/* 19 */     super(manager, new HumanoidModel());
/*    */   }
/*    */ 
/*    */   
/*    */   public ResourceLocation getTextureLocation(WaxCloneEntity entity) {
/* 24 */     if (entity.isUsingOwnerTexture() && entity.getOwner() instanceof PlayerEntity) {
/* 25 */       PlayerEntity player = (PlayerEntity)entity.getOwner();
/* 26 */       if (player != null) {
/* 27 */         return ((AbstractClientPlayerEntity)player).func_110306_p();
/*    */       }
/*    */       
/* 30 */       return DefaultPlayerSkin.func_177334_a(entity.getOwnerUUID());
/*    */     } 
/*    */ 
/*    */     
/* 34 */     return WAX_LOCATION;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static class Factory
/*    */     implements IRenderFactory<WaxCloneEntity>
/*    */   {
/*    */     public EntityRenderer<? super WaxCloneEntity> createRenderFor(EntityRendererManager manager) {
/* 43 */       return (EntityRenderer<? super WaxCloneEntity>)new WaxCloneRenderer(manager);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\WaxCloneRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */