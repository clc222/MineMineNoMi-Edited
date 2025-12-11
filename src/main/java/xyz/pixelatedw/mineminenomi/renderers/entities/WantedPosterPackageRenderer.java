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
/*    */ import xyz.pixelatedw.mineminenomi.entities.WantedPosterPackageEntity;
/*    */ import xyz.pixelatedw.mineminenomi.models.blocks.WantedPosterPackageModel;
/*    */ 
/*    */ public class WantedPosterPackageRenderer<T extends WantedPosterPackageEntity>
/*    */   extends EntityRenderer<T>
/*    */ {
/* 21 */   private static final ResourceLocation TEXTURE = new ResourceLocation("mineminenomi", "textures/models/wantedposterspackage.png");
/* 22 */   private WantedPosterPackageModel model = new WantedPosterPackageModel();
/*    */ 
/*    */   
/*    */   protected WantedPosterPackageRenderer(EntityRendererManager manager) {
/* 26 */     super(manager);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(T entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 32 */     matrixStack.func_227860_a_();
/*    */     
/* 34 */     matrixStack.func_227861_a_(0.0D, 1.5D, 0.0D);
/* 35 */     matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229179_b_, 180.0F, true));
/* 36 */     matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229181_d_, 180.0F, true));
/* 37 */     matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229181_d_, ((WantedPosterPackageEntity)entity).field_70127_C + (((WantedPosterPackageEntity)entity).field_70125_A - ((WantedPosterPackageEntity)entity).field_70127_C) * partialTicks - 180.0F, true));
/*    */     
/* 39 */     matrixStack.func_227862_a_(1.5F, 1.0F, 1.5F);
/*    */     
/* 41 */     RenderType type = RenderType.func_228640_c_(getTextureLocation((WantedPosterPackageEntity)entity));
/* 42 */     IVertexBuilder ivertexbuilder = buffer.getBuffer(type);
/* 43 */     this.model.func_225598_a_(matrixStack, ivertexbuilder, packedLight, OverlayTexture.field_229196_a_, 1.0F, 1.0F, 1.0F, 1.0F);
/*    */     
/* 45 */     matrixStack.func_227865_b_();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation getTextureLocation(WantedPosterPackageEntity entity) {
/* 51 */     return TEXTURE;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static class Factory
/*    */     implements IRenderFactory<WantedPosterPackageEntity>
/*    */   {
/*    */     public EntityRenderer<? super WantedPosterPackageEntity> createRenderFor(EntityRendererManager manager) {
/* 61 */       return new WantedPosterPackageRenderer<>(manager);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\WantedPosterPackageRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */