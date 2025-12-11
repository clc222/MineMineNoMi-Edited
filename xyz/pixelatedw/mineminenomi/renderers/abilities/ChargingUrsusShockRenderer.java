/*    */ package xyz.pixelatedw.mineminenomi.renderers.abilities;
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
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.nikyu.ChargingUrsusShockEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.projectiles.PawModel;
/*    */ 
/*    */ public class ChargingUrsusShockRenderer<T extends ChargingUrsusShockEntity> extends EntityRenderer<T> {
/*    */   private PawModel model;
/*    */   
/*    */   protected ChargingUrsusShockRenderer(EntityRendererManager manager) {
/* 23 */     super(manager);
/* 24 */     this.model = new PawModel();
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(T entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 29 */     matrixStack.func_227860_a_();
/*    */     
/* 31 */     matrixStack.func_227861_a_(0.0D, 1.5D, 0.0D);
/*    */     
/* 33 */     matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229179_b_, 180.0F, true));
/* 34 */     matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229181_d_, 180.0F, true));
/* 35 */     matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229181_d_, ((ChargingUrsusShockEntity)entity).field_70126_B + (((ChargingUrsusShockEntity)entity).field_70177_z - ((ChargingUrsusShockEntity)entity).field_70126_B) * partialTicks - 180.0F, true));
/*    */     
/* 37 */     float size = 1.0F + entity.getCharge() / 2.0F;
/*    */     
/* 39 */     matrixStack.func_227862_a_(size, size, size);
/*    */     
/* 41 */     RenderType type = ModRenderTypes.TRANSPARENT_COLOR;
/* 42 */     IVertexBuilder ivertexbuilder = buffer.getBuffer(type);
/* 43 */     this.model.func_225598_a_(matrixStack, ivertexbuilder, packedLight, OverlayTexture.field_229196_a_, 1.0F, 1.0F, 1.0F, 0.6F);
/*    */     
/* 45 */     matrixStack.func_227860_a_();
/*    */     
/* 47 */     double t = (((ChargingUrsusShockEntity)entity).field_70173_aa * 3 % 100);
/* 48 */     double mirageSize = (t >= 50.0D) ? (2.0D - t / 100.0D) : (1.0D + t / 100.0D);
/* 49 */     float scale = (float)mirageSize;
/* 50 */     matrixStack.func_227862_a_(scale, scale, scale);
/* 51 */     this.model.func_225598_a_(matrixStack, ivertexbuilder, packedLight, OverlayTexture.field_229196_a_, 1.0F, 1.0F, 1.0F, 0.2F);
/*    */     
/* 53 */     matrixStack.func_227865_b_();
/*    */     
/* 55 */     matrixStack.func_227865_b_();
/*    */   }
/*    */ 
/*    */   
/*    */   public ResourceLocation getTextureLocation(ChargingUrsusShockEntity entity) {
/* 60 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static class Factory
/*    */     implements IRenderFactory<ChargingUrsusShockEntity>
/*    */   {
/*    */     public EntityRenderer<? super ChargingUrsusShockEntity> createRenderFor(EntityRendererManager manager) {
/* 69 */       return new ChargingUrsusShockRenderer<>(manager);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\abilities\ChargingUrsusShockRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */