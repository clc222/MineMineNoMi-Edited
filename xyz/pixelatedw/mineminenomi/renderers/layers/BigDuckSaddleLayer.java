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
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.BigDuckEntity;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.animals.BigDuckModel;
/*    */ 
/*    */ public class BigDuckSaddleLayer<T extends BigDuckEntity, M extends EntityModel<T>>
/*    */   extends LayerRenderer<T, M> {
/* 16 */   private BigDuckModel model = new BigDuckModel();
/* 17 */   private static final ResourceLocation SADDLE_TEXTURE = new ResourceLocation("mineminenomi", "textures/models/big_duck_saddle.png");
/*    */   
/*    */   public BigDuckSaddleLayer(IEntityRenderer<T, M> renderer) {
/* 20 */     super(renderer);
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
/* 25 */     if (entity.func_70909_n() && entity.isSaddled()) {
/* 26 */       matrixStack.func_227860_a_();
/* 27 */       matrixStack.func_227862_a_(1.1F, 1.1F, 1.1F);
/* 28 */       matrixStack.func_227861_a_(0.0D, -0.01D, 0.0D);
/* 29 */       this.model.field_217114_e = false;
/* 30 */       this.model.prepareMobModel((BigDuckEntity)entity, pLimbSwing, pLimbSwingAmount, pPartialTicks);
/* 31 */       LayerRenderer.func_229141_a_((EntityModel)this.model, SADDLE_TEXTURE, matrixStack, buffer, packedLight, (LivingEntity)entity, 1.0F, 1.0F, 1.0F);
/* 32 */       matrixStack.func_227865_b_();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\layers\BigDuckSaddleLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */