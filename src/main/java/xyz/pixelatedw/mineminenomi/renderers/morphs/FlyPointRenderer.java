/*    */ package xyz.pixelatedw.mineminenomi.renderers.morphs;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphModel;
/*    */ 
/*    */ 
/*    */ public class FlyPointRenderer<T extends AbstractClientPlayerEntity, M extends MorphModel>
/*    */   extends ZoanMorphRenderer<T, M>
/*    */ {
/*    */   public FlyPointRenderer(EntityRendererManager rendererManager, MorphInfo info) {
/* 20 */     super(rendererManager, info);
/* 21 */     setCullingState(true);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void renderModel(AbstractClientPlayerEntity entity, MatrixStack matrixStack, int packedLight, IRenderTypeBuffer buffer, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
/* 27 */     if (this.info instanceof xyz.pixelatedw.mineminenomi.entities.zoan.PteranodonFlyMorphInfo) {
/* 28 */       matrixStack.func_227861_a_(0.0D, 1.0D, 0.0D);
/*    */     }
/* 30 */     matrixStack.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(headPitch));
/* 31 */     double distanceX = entity.func_226277_ct_() - entity.field_70169_q;
/* 32 */     double distanceZ = entity.func_226281_cx_() - entity.field_70166_s;
/* 33 */     double movementSpeed = MathHelper.func_76133_a(distanceX * distanceX + distanceZ * distanceZ);
/* 34 */     float maxRotation = (float)(movementSpeed * 80.0D);
/* 35 */     float rot = -netHeadYaw * 3.0F;
/* 36 */     rot = MathHelper.func_76131_a(rot, -maxRotation, maxRotation);
/* 37 */     matrixStack.func_227863_a_(Vector3f.field_229183_f_.func_229187_a_(rot));
/* 38 */     super.renderModel(entity, matrixStack, packedLight, buffer, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch);
/*    */   }
/*    */   
/*    */   public static class Factory<T extends PlayerEntity>
/*    */     implements IRenderFactory<T>
/*    */   {
/*    */     private MorphInfo info;
/*    */     private boolean hasCulling;
/*    */     
/*    */     public Factory(MorphInfo info) {
/* 48 */       this.info = info;
/*    */     }
/*    */ 
/*    */     
/*    */     public Factory(MorphInfo info, boolean hasCulling) {
/* 53 */       this.info = info;
/* 54 */       this.hasCulling = hasCulling;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public EntityRenderer<? super T> createRenderFor(EntityRendererManager manager) {
/* 60 */       FlyPointRenderer<AbstractClientPlayerEntity, MorphModel> renderer = new FlyPointRenderer<>(manager, this.info);
/* 61 */       renderer.setCullingState(this.hasCulling);
/* 62 */       return (EntityRenderer)renderer;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\morphs\FlyPointRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */