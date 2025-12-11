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
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.WhiteWalkieEntity;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.animals.WhiteWalkieSaddleModel;
/*    */ 
/*    */ public class WhiteWalkieSaddleLayer<T extends WhiteWalkieEntity, M extends EntityModel<T>>
/*    */   extends LayerRenderer<T, M> {
/* 16 */   private WhiteWalkieSaddleModel model = new WhiteWalkieSaddleModel();
/* 17 */   private static final ResourceLocation SADDLE_TEXTURE = new ResourceLocation("mineminenomi", "textures/models/white_walkie_saddle.png");
/*    */   
/*    */   public WhiteWalkieSaddleLayer(IEntityRenderer<T, M> renderer) {
/* 20 */     super(renderer);
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
/* 25 */     if (entity.func_70909_n() && entity.isSaddled()) {
/* 26 */       matrixStack.func_227860_a_();
/* 27 */       matrixStack.func_227862_a_(1.1F, 1.1F, 1.1F);
/* 28 */       this.model.func_212843_a_((LivingEntity)entity, pLimbSwing, pLimbSwingAmount, pPartialTicks);
/* 29 */       LayerRenderer.func_229141_a_((EntityModel)this.model, SADDLE_TEXTURE, matrixStack, buffer, packedLight, (LivingEntity)entity, 1.0F, 1.0F, 1.0F);
/* 30 */       matrixStack.func_227865_b_();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\layers\WhiteWalkieSaddleLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */