/*    */ package xyz.pixelatedw.mineminenomi.renderers.layers;
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.worldgov.CelestialDragonEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids.CelestialDragonSlaveRideModel;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids.HumanoidModel;
/*    */ 
/*    */ public class CelestialDragonSlaveRideLayer<E extends CelestialDragonEntity, M extends HumanoidModel<E>> extends LayerRenderer<E, M> {
/* 17 */   private static final ResourceLocation VILLAGER_BASE_SKIN = new ResourceLocation("textures/entity/villager/villager.png");
/*    */   
/* 19 */   private CelestialDragonSlaveRideModel<LivingEntity> ride = new CelestialDragonSlaveRideModel();
/*    */   
/*    */   public CelestialDragonSlaveRideLayer(IEntityRenderer renderer) {
/* 22 */     super(renderer);
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, E entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
/* 27 */     if (!entity.isRidingSlave()) {
/*    */       return;
/*    */     }
/*    */     
/* 31 */     matrixStack.func_227860_a_();
/* 32 */     RenderType renderType = ModRenderTypes.getZoanRenderType(VILLAGER_BASE_SKIN);
/* 33 */     this.ride.func_225597_a_((LivingEntity)entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/* 34 */     this.ride.func_225598_a_(matrixStack, buffer.getBuffer(renderType), packedLight, OverlayTexture.field_229196_a_, 1.0F, 1.0F, 1.0F, 1.0F);
/* 35 */     matrixStack.func_227865_b_();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\layers\CelestialDragonSlaveRideLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */