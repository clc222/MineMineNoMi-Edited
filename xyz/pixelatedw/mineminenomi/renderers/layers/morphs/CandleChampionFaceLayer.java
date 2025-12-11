/*    */ package xyz.pixelatedw.mineminenomi.renderers.layers.morphs;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.LivingRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphModel;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.morphs.CandleChampionRenderer;
/*    */ 
/*    */ public class CandleChampionFaceLayer<T extends LivingEntity, M extends MorphModel<T>>
/*    */   extends LayerRenderer<T, M> {
/*    */   public CandleChampionFaceLayer(CandleChampionRenderer<T, M> renderer) {
/* 23 */     super((IEntityRenderer)renderer);
/* 24 */     this.parentRenderer = renderer;
/*    */   }
/*    */   private final CandleChampionRenderer<T, M> parentRenderer;
/*    */   
/*    */   public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
/* 29 */     if (entity.func_82150_aj()) {
/*    */       return;
/*    */     }
/*    */     
/* 33 */     matrixStack.func_227860_a_();
/* 34 */     matrixStack.func_227861_a_(0.0D, -1.6D, 0.5D);
/* 35 */     matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(180.0F));
/*    */ 
/*    */     
/* 38 */     headPitch = MathHelper.func_76131_a(headPitch, -17.0F, 60.0F);
/* 39 */     netHeadYaw = MathHelper.func_76131_a(netHeadYaw, -27.0F, 27.0F);
/*    */     
/* 41 */     LivingRenderer<T, BipedModel<T>> ogRenderer = this.parentRenderer.getOriginalRenderer();
/* 42 */     ResourceLocation skin = ogRenderer.func_110775_a((Entity)entity);
/* 43 */     RenderType renderType = ModRenderTypes.getZoanRenderType(skin);
/*    */     
/* 45 */     ((BipedModel)ogRenderer.func_217764_d()).func_178719_a(false);
/* 46 */     ((BipedModel)ogRenderer.func_217764_d()).field_217114_e = false;
/* 47 */     ((BipedModel)ogRenderer.func_217764_d()).field_78116_c.field_78806_j = true;
/*    */     
/* 49 */     ((BipedModel)ogRenderer.func_217764_d()).func_212843_a_((LivingEntity)entity, limbSwing, limbSwingAmount, partialTicks);
/* 50 */     ((BipedModel)ogRenderer.func_217764_d()).func_225597_a_((LivingEntity)entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/* 51 */     ((BipedModel)ogRenderer.func_217764_d()).func_225598_a_(matrixStack, buffer.getBuffer(renderType), packedLight, OverlayTexture.field_229196_a_, 1.0F, 1.0F, 1.0F, 1.0F);
/* 52 */     matrixStack.func_227865_b_();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\layers\morphs\CandleChampionFaceLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */