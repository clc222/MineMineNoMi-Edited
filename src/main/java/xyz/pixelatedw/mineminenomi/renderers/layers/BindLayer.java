/*    */ package xyz.pixelatedw.mineminenomi.renderers.layers;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*    */ import xyz.pixelatedw.mineminenomi.models.abilities.ChainsModel;
/*    */ 
/*    */ public class BindLayer<T extends LivingEntity, M extends EntityModel<T>>
/*    */   extends LayerRenderer<T, M> {
/* 18 */   private static final ChainsModel MODEL = new ChainsModel();
/*    */ 
/*    */   
/*    */   public BindLayer(IEntityRenderer renderer) {
/* 22 */     super(renderer);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
/* 28 */     if (entity.func_70644_a((Effect)ModEffects.BIND.get())) {
/*    */       
/* 30 */       if (entity.func_70644_a((Effect)ModEffects.BIND.get()) && entity.func_70660_b((Effect)ModEffects.BIND.get()).func_76459_b() <= 0) {
/* 31 */         entity.func_195063_d((Effect)ModEffects.BIND.get());
/*    */       }
/* 33 */       if (func_215332_c() instanceof net.minecraft.client.renderer.entity.model.BipedModel) {
/*    */         
/* 35 */         matrixStack.func_227861_a_(0.0D, -0.05D, 0.05D);
/* 36 */         MODEL.func_225598_a_(matrixStack, buffer.getBuffer(ModRenderTypes.getAbilityHand(ModResources.CANDLE_LOCK)), packedLight, 0, 0.4F, 0.4F, 0.5F, 1.0F);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\layers\BindLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */