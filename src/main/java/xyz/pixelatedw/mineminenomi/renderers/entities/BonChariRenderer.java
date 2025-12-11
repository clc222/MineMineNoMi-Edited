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
/*    */ import net.minecraft.util.math.vector.Quaternion;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.entities.BonChariEntity;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.vehicles.BonChariModel;
/*    */ 
/*    */ public class BonChariRenderer
/*    */   extends EntityRenderer<BonChariEntity>
/*    */ {
/* 21 */   private static final ResourceLocation TEXTURE = new ResourceLocation("mineminenomi", "textures/models/bon_chari.png");
/*    */   
/* 23 */   private BonChariModel model = new BonChariModel();
/*    */   
/*    */   protected BonChariRenderer(EntityRendererManager manager) {
/* 26 */     super(manager);
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(BonChariEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 31 */     matrixStack.func_227860_a_();
/* 32 */     matrixStack.func_227861_a_(0.0D, 0.875D, 0.0D);
/* 33 */     matrixStack.func_227862_a_(1.5F, 1.5F, 1.5F);
/* 34 */     matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(180.0F - entityYaw));
/*    */     
/* 36 */     float angle = entity.getBurstingAngle(partialTicks);
/* 37 */     if (!MathHelper.func_180185_a(angle, 0.0F)) {
/* 38 */       matrixStack.func_227863_a_(new Quaternion(new Vector3f(1.0F, 0.0F, 1.0F), entity.getBurstingAngle(partialTicks), true));
/*    */     }
/*    */     
/* 41 */     matrixStack.func_227862_a_(-1.0F, -1.0F, 1.0F);
/* 42 */     this.model.func_225597_a_((Entity)entity, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F);
/* 43 */     IVertexBuilder ivertexbuilder = buffer.getBuffer(this.model.func_228282_a_(getTextureLocation(entity)));
/* 44 */     this.model.func_225598_a_(matrixStack, ivertexbuilder, packedLight, OverlayTexture.field_229196_a_, 1.0F, 1.0F, 1.0F, 1.0F);
/*    */     
/* 46 */     matrixStack.func_227865_b_();
/*    */   }
/*    */ 
/*    */   
/*    */   public ResourceLocation getTextureLocation(BonChariEntity entity) {
/* 51 */     return TEXTURE;
/*    */   }
/*    */   
/*    */   public static class Factory
/*    */     implements IRenderFactory {
/*    */     public EntityRenderer createRenderFor(EntityRendererManager manager) {
/* 57 */       return new BonChariRenderer(manager);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\BonChariRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */