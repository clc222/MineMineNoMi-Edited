/*    */ package xyz.pixelatedw.mineminenomi.renderers.entities;
/*    */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.resources.DefaultPlayerSkin;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.ability.BlackKnightEntity;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids.HumanoidModel;
/*    */ 
/*    */ @OnlyIn(Dist.CLIENT)
/*    */ public class BlackKnightRenderer extends HumanoidRenderer<BlackKnightEntity, BipedModel<BlackKnightEntity>> {
/*    */   public BlackKnightRenderer(EntityRendererManager manager) {
/* 19 */     super(manager, new HumanoidModel());
/*    */   }
/*    */ 
/*    */   
/*    */   public ResourceLocation getTextureLocation(BlackKnightEntity entity) {
/* 24 */     AbstractClientPlayerEntity player = (AbstractClientPlayerEntity)entity.getOwner();
/* 25 */     if (player != null) {
/* 26 */       return player.func_110306_p();
/*    */     }
/*    */     
/* 29 */     return DefaultPlayerSkin.func_177334_a(entity.getOwnerUUID());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static class Factory
/*    */     implements IRenderFactory<BlackKnightEntity>
/*    */   {
/*    */     public EntityRenderer<? super BlackKnightEntity> createRenderFor(EntityRendererManager manager) {
/* 39 */       return (EntityRenderer<? super BlackKnightEntity>)new BlackKnightRenderer(manager);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\BlackKnightRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */