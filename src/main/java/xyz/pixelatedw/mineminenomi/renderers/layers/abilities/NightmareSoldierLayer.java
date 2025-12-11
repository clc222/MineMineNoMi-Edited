/*    */ package xyz.pixelatedw.mineminenomi.renderers.layers.abilities;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*    */ 
/*    */ public class NightmareSoldierLayer<T extends LivingEntity, M extends EntityModel<T>> extends LayerRenderer<T, M> {
/*    */   public NightmareSoldierLayer(IEntityRenderer renderer) {
/* 17 */     super(renderer);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
/* 23 */     IVertexBuilder vertex = buffer.getBuffer(ModRenderTypes.getAbilityBody(ModResources.BUSOSHOKU_HAKI_ARM));
/* 24 */     func_215332_c().func_225598_a_(matrixStack, vertex, packedLight, OverlayTexture.field_229196_a_, 0.2F, 0.2F, 0.2F, 0.8F);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\layers\abilities\NightmareSoldierLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */