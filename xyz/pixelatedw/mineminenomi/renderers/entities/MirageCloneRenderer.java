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
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.ability.MirageCloneEntity;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids.HumanoidModel;
/*    */ 
/*    */ @OnlyIn(Dist.CLIENT)
/*    */ public class MirageCloneRenderer extends HumanoidRenderer<MirageCloneEntity, BipedModel<MirageCloneEntity>> {
/*    */   public MirageCloneRenderer(EntityRendererManager manager) {
/* 20 */     super(manager, new HumanoidModel());
/*    */   }
/*    */ 
/*    */   
/*    */   public ResourceLocation getTextureLocation(MirageCloneEntity entity) {
/* 25 */     if (entity.getOwnerUUID() == null) {
/* 26 */       return DefaultPlayerSkin.func_177334_a(entity.func_110124_au());
/*    */     }
/* 28 */     PlayerEntity player = entity.field_70170_p.func_217371_b(entity.getOwnerUUID());
/* 29 */     if (player != null) {
/* 30 */       return ((AbstractClientPlayerEntity)player).func_110306_p();
/*    */     }
/*    */     
/* 33 */     return DefaultPlayerSkin.func_177334_a(entity.getOwnerUUID());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static class Factory
/*    */     implements IRenderFactory<MirageCloneEntity>
/*    */   {
/*    */     public EntityRenderer<? super MirageCloneEntity> createRenderFor(EntityRendererManager manager) {
/* 43 */       return (EntityRenderer<? super MirageCloneEntity>)new MirageCloneRenderer(manager);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\MirageCloneRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */