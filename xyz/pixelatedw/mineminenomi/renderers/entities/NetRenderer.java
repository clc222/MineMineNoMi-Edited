/*    */ package xyz.pixelatedw.mineminenomi.renderers.entities;
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.api.enums.NetType;
/*    */ import xyz.pixelatedw.mineminenomi.effects.CaughtInNetEffect;
/*    */ import xyz.pixelatedw.mineminenomi.entities.NetEntity;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.projectiles.PlaneModel;
/*    */ 
/*    */ public class NetRenderer<T extends NetEntity> extends EntityRenderer<T> {
/* 20 */   private PlaneModel net = new PlaneModel();
/*    */   
/*    */   protected NetRenderer(EntityRendererManager renderManager) {
/* 23 */     super(renderManager);
/* 24 */     this.field_76989_e = 0.05F;
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(T entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 29 */     matrixStack.func_227860_a_();
/*    */     
/* 31 */     ((NetEntity)entity).field_70125_A = MathHelper.func_76131_a(((NetEntity)entity).field_70125_A, -30.0F, 50.0F);
/* 32 */     ((NetEntity)entity).field_70127_C = MathHelper.func_76131_a(((NetEntity)entity).field_70127_C, -30.0F, 50.0F);
/*    */     
/* 34 */     matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(MathHelper.func_219799_g(partialTicks, ((NetEntity)entity).field_70126_B, ((NetEntity)entity).field_70177_z) + 0.0F));
/* 35 */     matrixStack.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(MathHelper.func_219799_g(partialTicks, ((NetEntity)entity).field_70127_C, ((NetEntity)entity).field_70125_A) + -10.0F));
/*    */     
/* 37 */     matrixStack.func_227862_a_(6.0F, 6.0F, 6.0F);
/* 38 */     matrixStack.func_227861_a_(0.0D, -1.45D, 0.0D);
/*    */     
/* 40 */     IVertexBuilder ivertexbuilder = buffer.getBuffer(RenderType.func_228642_d_(getTextureLocation(entity)));
/* 41 */     this.net.func_225598_a_(matrixStack, ivertexbuilder, packedLight, OverlayTexture.field_229196_a_, 1.0F, 1.0F, 1.0F, 1.0F);
/*    */     
/* 43 */     matrixStack.func_227865_b_();
/*    */   }
/*    */ 
/*    */   
/*    */   public ResourceLocation getTextureLocation(T entity) {
/* 48 */     switch (entity.getNetType()) {
/*    */       case KAIROSEKI:
/* 50 */         return CaughtInNetEffect.KAIROSEKI_NET_TEXTURE;
/*    */     } 
/*    */     
/* 53 */     return CaughtInNetEffect.NET_TEXTURE;
/*    */   }
/*    */   
/*    */   public static class Factory
/*    */     implements IRenderFactory
/*    */   {
/*    */     public EntityRenderer createRenderFor(EntityRendererManager manager) {
/* 60 */       return new NetRenderer<>(manager);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\NetRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */