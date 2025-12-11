/*    */ package xyz.pixelatedw.mineminenomi.renderers.layers.armor;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.worldgov.CelestialDragonEntity;
/*    */ 
/*    */ public class CelestialDragonWigLayer<T extends CelestialDragonEntity, M extends BipedModel<T>> extends LayerRenderer<T, M> {
/*    */   private final BipedModel wigModel;
/* 17 */   private static final ResourceLocation TEXTURE = new ResourceLocation("mineminenomi", "textures/models/armor/celestial_dragon_wig.png");
/*    */   
/*    */   public CelestialDragonWigLayer(IEntityRenderer<T, M> renderer) {
/* 20 */     super(renderer);
/*    */     this.wigModel = new BipedModel(res -> RenderType.func_228640_c_((ResourceLocation)res), 0.1F, 0.0F, 64, 64);
/*    */   }
/*    */   
/*    */   public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
/* 25 */     RenderType renderType = RenderType.func_228640_c_(TEXTURE);
/* 26 */     ((BipedModel)func_215332_c()).func_217148_a(this.wigModel);
/* 27 */     this.wigModel.func_225597_a_((LivingEntity)entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/*    */     
/* 29 */     float hairRed = entity.getHairColor().getRed() / 255.0F;
/* 30 */     float hairGreen = entity.getHairColor().getGreen() / 255.0F;
/* 31 */     float hairBlue = entity.getHairColor().getBlue() / 255.0F;
/*    */     
/* 33 */     this.wigModel.func_225598_a_(matrixStack, buffer.getBuffer(renderType), packedLight, OverlayTexture.field_229196_a_, hairRed, hairGreen, hairBlue, 1.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\layers\armor\CelestialDragonWigLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */