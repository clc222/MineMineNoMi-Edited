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
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.entities.SpikeEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*    */ import xyz.pixelatedw.mineminenomi.models.abilities.CubeModel;
/*    */ 
/*    */ public class SpikeRenderer<T extends SpikeEntity>
/*    */   extends EntityRenderer<T> {
/* 21 */   private CubeModel spike = new CubeModel();
/*    */ 
/*    */   
/*    */   protected SpikeRenderer(EntityRendererManager renderManager) {
/* 25 */     super(renderManager);
/* 26 */     this.field_76989_e = 0.05F;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(T entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 32 */     matrixStack.func_227860_a_();
/*    */     
/* 34 */     matrixStack.func_227861_a_(0.0D, 0.02D, 0.0D);
/*    */     
/* 36 */     float scale = 0.2F;
/* 37 */     matrixStack.func_227862_a_(scale, scale, scale);
/*    */     
/* 39 */     matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229179_b_, 45.0F, true));
/* 40 */     matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229181_d_, 45.0F, true));
/*    */     
/* 42 */     RenderType type = ModRenderTypes.TRANSPARENT_COLOR;
/* 43 */     IVertexBuilder ivertexbuilder = buffer.getBuffer(type);
/* 44 */     this.spike.func_225598_a_(matrixStack, ivertexbuilder, packedLight, OverlayTexture.field_229196_a_, 0.1F, 0.1F, 0.1F, 1.0F);
/*    */     
/* 46 */     matrixStack.func_227865_b_();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation getTextureLocation(T entity) {
/* 52 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public static class Factory
/*    */     implements IRenderFactory
/*    */   {
/*    */     public EntityRenderer createRenderFor(EntityRendererManager manager) {
/* 60 */       return new SpikeRenderer<>(manager);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\SpikeRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */