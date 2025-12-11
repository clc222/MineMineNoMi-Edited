/*    */ package xyz.pixelatedw.mineminenomi.renderers.blocks;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*    */ import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
/*    */ import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.math.vector.Quaternion;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import xyz.pixelatedw.mineminenomi.blocks.tileentities.WantedPosterPackageTileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.models.blocks.WantedPosterPackageModel;
/*    */ 
/*    */ public class WantedPostersPackageTileEntityRenderer
/*    */   extends TileEntityRenderer<WantedPosterPackageTileEntity>
/*    */ {
/*    */   private WantedPosterPackageModel model;
/* 21 */   private static final ResourceLocation TEXTURE = new ResourceLocation("mineminenomi", "textures/models/wantedposterspackage.png");
/*    */ 
/*    */   
/*    */   public WantedPostersPackageTileEntityRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
/* 25 */     super(rendererDispatcherIn);
/* 26 */     this.model = new WantedPosterPackageModel();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(WantedPosterPackageTileEntity tileEntity, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
/* 32 */     matrixStack.func_227860_a_();
/*    */     
/* 34 */     matrixStack.func_227861_a_(0.5D, 1.6D, 0.5D);
/* 35 */     matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229183_f_, 180.0F, true));
/*    */     
/* 37 */     this.model.parachute.field_78806_j = false;
/* 38 */     RenderType type = RenderType.func_228634_a_(TEXTURE);
/* 39 */     IVertexBuilder ivertexbuilder = buffer.getBuffer(type);
/* 40 */     this.model.func_225598_a_(matrixStack, ivertexbuilder, combinedLight, OverlayTexture.field_229196_a_, 1.0F, 1.0F, 1.0F, 1.0F);
/*    */     
/* 42 */     matrixStack.func_227865_b_();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\blocks\WantedPostersPackageTileEntityRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */