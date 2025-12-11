/*    */ package xyz.pixelatedw.mineminenomi.renderers.layers;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import java.util.function.Predicate;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ 
/*    */ public class EyesLayer<T extends LivingEntity, M extends EntityModel<T>>
/*    */   extends LayerRenderer<T, M> {
/*    */   private final RenderType eyesRenderer;
/*    */   private final Predicate<T> predicate;
/*    */   
/*    */   public EyesLayer(IEntityRenderer renderer, RenderType eyesRenderer, Predicate<T> predicate) {
/* 21 */     super(renderer);
/* 22 */     this.eyesRenderer = eyesRenderer;
/* 23 */     this.predicate = predicate;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
/* 29 */     if (this.predicate.test(entity)) {
/* 30 */       IVertexBuilder ivertexbuilder = buffer.getBuffer(this.eyesRenderer);
/* 31 */       func_215332_c().func_225598_a_(matrixStack, ivertexbuilder, packedLight, OverlayTexture.field_229196_a_, 1.0F, 1.0F, 1.0F, 1.0F);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\layers\EyesLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */