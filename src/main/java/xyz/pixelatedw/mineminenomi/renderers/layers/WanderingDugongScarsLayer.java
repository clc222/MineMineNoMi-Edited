/*    */ package xyz.pixelatedw.mineminenomi.renderers.layers;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.MobsHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.WanderingDugongEntity;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.animals.DugongModel;
/*    */ 
/*    */ public class WanderingDugongScarsLayer<T extends WanderingDugongEntity, M extends EntityModel<T>>
/*    */   extends LayerRenderer<T, M> {
/* 19 */   private DugongModel<T> model = new DugongModel();
/*    */   
/*    */   public WanderingDugongScarsLayer(IEntityRenderer<T, M> renderer) {
/* 22 */     super(renderer);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
/* 32 */     if (entity.getHeadScarId() >= 0) {
/* 33 */       ResourceLocation headScarTexture = MobsHelper.DUGONG_HEAD_SCARS_TEXTURES[entity.getHeadScarId()];
/* 34 */       IVertexBuilder vertex = buffer.getBuffer(RenderType.func_228640_c_(headScarTexture));
/* 35 */       func_215332_c().func_225598_a_(matrixStack, vertex, packedLight, OverlayTexture.field_229196_a_, 1.0F, 1.0F, 1.0F, 1.0F);
/*    */     } 
/*    */ 
/*    */     
/* 39 */     if (entity.getChestScarId() >= 0) {
/* 40 */       ResourceLocation chestScarTexture = MobsHelper.DUGONG_CHEST_SCARS_TEXTURES[entity.getChestScarId()];
/* 41 */       IVertexBuilder vertex = buffer.getBuffer(RenderType.func_228640_c_(chestScarTexture));
/* 42 */       func_215332_c().func_225598_a_(matrixStack, vertex, packedLight, OverlayTexture.field_229196_a_, 1.0F, 1.0F, 1.0F, 1.0F);
/*    */     } 
/*    */ 
/*    */     
/* 46 */     if (entity.getArmsScarId() >= 0) {
/* 47 */       ResourceLocation armsScarTexture = MobsHelper.DUGONG_ARMS_SCARS_TEXTURES[entity.getArmsScarId()];
/* 48 */       IVertexBuilder vertex = buffer.getBuffer(RenderType.func_228640_c_(armsScarTexture));
/* 49 */       func_215332_c().func_225598_a_(matrixStack, vertex, packedLight, OverlayTexture.field_229196_a_, 1.0F, 1.0F, 1.0F, 1.0F);
/*    */     } 
/*    */ 
/*    */     
/* 53 */     if (entity.getTailScarId() >= 0) {
/* 54 */       ResourceLocation tailScarTexture = MobsHelper.DUGONG_TAIL_SCARS_TEXTURES[entity.getTailScarId()];
/* 55 */       IVertexBuilder vertex = buffer.getBuffer(RenderType.func_228640_c_(tailScarTexture));
/* 56 */       func_215332_c().func_225598_a_(matrixStack, vertex, packedLight, OverlayTexture.field_229196_a_, 1.0F, 1.0F, 1.0F, 1.0F);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\layers\WanderingDugongScarsLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */