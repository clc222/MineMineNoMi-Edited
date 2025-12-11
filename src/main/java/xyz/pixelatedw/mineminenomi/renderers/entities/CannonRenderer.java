/*    */ package xyz.pixelatedw.mineminenomi.renderers.entities;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.entities.CannonEntity;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.vehicles.CannonModel;
/*    */ 
/*    */ public class CannonRenderer
/*    */   extends EntityRenderer<CannonEntity>
/*    */ {
/* 20 */   private static final ResourceLocation TEXTURE = new ResourceLocation("mineminenomi", "textures/models/blocks/cannon.png");
/*    */   
/* 22 */   private CannonModel model = new CannonModel();
/*    */   
/*    */   protected CannonRenderer(EntityRendererManager manager) {
/* 25 */     super(manager);
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(CannonEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 30 */     matrixStack.func_227860_a_();
/* 31 */     matrixStack.func_227861_a_(0.0D, 1.5D, 0.0D);
/* 32 */     matrixStack.func_227862_a_(1.0F, 1.0F, 1.0F);
/* 33 */     matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(180.0F - entityYaw));
/*    */     
/* 35 */     float hurtTime = entity.getHurtTime() - partialTicks;
/* 36 */     if (hurtTime > 0.0F) {
/* 37 */       matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(MathHelper.func_76126_a(hurtTime) * hurtTime));
/*    */     }
/*    */     
/* 40 */     matrixStack.func_227862_a_(-1.0F, -1.0F, 1.0F);
/* 41 */     this.model.func_225597_a_((Entity)entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F);
/* 42 */     IVertexBuilder ivertexbuilder = buffer.getBuffer(this.model.func_228282_a_(getTextureLocation(entity)));
/* 43 */     this.model.func_225598_a_(matrixStack, ivertexbuilder, packedLight, OverlayTexture.field_229196_a_, 1.0F, 1.0F, 1.0F, 1.0F);
/*    */     
/* 45 */     matrixStack.func_227865_b_();
/* 46 */     super.func_225623_a_((Entity)entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
/*    */   }
/*    */ 
/*    */   
/*    */   public ResourceLocation getTextureLocation(CannonEntity entity) {
/* 51 */     return TEXTURE;
/*    */   }
/*    */   
/*    */   public static class Factory
/*    */     implements IRenderFactory {
/*    */     public EntityRenderer createRenderFor(EntityRendererManager manager) {
/* 57 */       return new CannonRenderer(manager);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\CannonRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */