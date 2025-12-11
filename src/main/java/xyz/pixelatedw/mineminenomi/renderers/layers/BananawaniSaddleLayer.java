/*    */ package xyz.pixelatedw.mineminenomi.renderers.layers;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.BananawaniEntity;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.animals.BananawaniSaddleModel;
/*    */ 
/*    */ public class BananawaniSaddleLayer<T extends BananawaniEntity, M extends EntityModel<T>>
/*    */   extends LayerRenderer<T, M> {
/* 16 */   private BananawaniSaddleModel model = new BananawaniSaddleModel();
/* 17 */   private static final ResourceLocation SADDLE_TEXTURE = new ResourceLocation("mineminenomi", "textures/models/bananawani_saddle.png");
/* 18 */   private static final ResourceLocation SADDLE_OVERLAY_TEXTURE = new ResourceLocation("mineminenomi", "textures/models/bananawani_saddle_overlay.png");
/*    */   
/*    */   public BananawaniSaddleLayer(IEntityRenderer<T, M> renderer) {
/* 21 */     super(renderer);
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
/* 26 */     if (entity.func_70909_n() && entity.isSaddled()) {
/* 27 */       float[] color = entity.getSaddleColor().func_193349_f();
/* 28 */       LayerRenderer.func_229141_a_((EntityModel)this.model, SADDLE_OVERLAY_TEXTURE, matrixStack, buffer, packedLight, (LivingEntity)entity, 1.0F, 1.0F, 1.0F);
/* 29 */       LayerRenderer.func_229141_a_((EntityModel)this.model, SADDLE_TEXTURE, matrixStack, buffer, packedLight, (LivingEntity)entity, color[0], color[1], color[2]);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\layers\BananawaniSaddleLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */