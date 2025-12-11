/*    */ package xyz.pixelatedw.mineminenomi.renderers.entities;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.math.vector.Quaternion;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.entities.VivreCardEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*    */ import xyz.pixelatedw.mineminenomi.models.abilities.CubeModel;
/*    */ 
/*    */ @OnlyIn(Dist.CLIENT)
/*    */ public class VivreCardRenderer<T extends VivreCardEntity>
/*    */   extends EntityRenderer<T> {
/* 24 */   private CubeModel model = new CubeModel();
/*    */ 
/*    */   
/*    */   protected VivreCardRenderer(EntityRendererManager manager) {
/* 28 */     super(manager);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation getTextureLocation(VivreCardEntity entity) {
/* 34 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(T entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 40 */     matrixStack.func_227860_a_();
/*    */     
/* 42 */     matrixStack.func_227861_a_(0.0D, 0.05D, 0.0D);
/* 43 */     matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229181_d_, -((VivreCardEntity)entity).field_70177_z, true));
/*    */     
/* 45 */     matrixStack.func_227862_a_(0.4F, 0.1F, 0.5F);
/*    */     
/* 47 */     RenderType type = ModRenderTypes.TRANSPARENT_COLOR;
/* 48 */     IVertexBuilder ivertexbuilder = buffer.getBuffer(type);
/* 49 */     this.model.func_225598_a_(matrixStack, ivertexbuilder, packedLight, OverlayTexture.field_229196_a_, 1.0F, 1.0F, 1.0F, 1.0F);
/*    */     
/* 51 */     matrixStack.func_227865_b_();
/* 52 */     super.func_225623_a_((Entity)entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static class Factory
/*    */     implements IRenderFactory<VivreCardEntity>
/*    */   {
/*    */     public EntityRenderer<? super VivreCardEntity> createRenderFor(EntityRendererManager manager) {
/* 64 */       return new VivreCardRenderer<>(manager);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\VivreCardRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */