/*    */ package xyz.pixelatedw.mineminenomi.renderers.layers;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import java.util.Optional;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*    */ import xyz.pixelatedw.mineminenomi.models.HandcuffModel;
/*    */ 
/*    */ 
/*    */ public class HandcuffsLayer<T extends LivingEntity, M extends EntityModel<T>>
/*    */   extends LayerRenderer<T, M>
/*    */ {
/* 22 */   private static final HandcuffModel MODEL = new HandcuffModel();
/*    */   
/*    */   public HandcuffsLayer(IEntityRenderer renderer) {
/* 25 */     super(renderer);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
/* 33 */     Optional<EffectInstance> inst = entity.func_70651_bq().stream().filter(e -> (e.func_188419_a() instanceof xyz.pixelatedw.mineminenomi.effects.HandcuffedEffect && entity.func_70644_a(e.func_188419_a()))).findFirst();
/* 34 */     if (inst.isPresent()) {
/*    */       
/* 36 */       if (((EffectInstance)inst.get()).func_76459_b() <= 0) {
/* 37 */         entity.func_195063_d(((EffectInstance)inst.get()).func_188419_a());
/*    */       }
/*    */       
/* 40 */       ResourceLocation res = ModResources.HANDCUFFS;
/*    */       
/* 42 */       if (((EffectInstance)inst.get()).func_188419_a() == ModEffects.HANDCUFFED_KAIROSEKI.get()) {
/* 43 */         res = ModResources.KAIROSEKI_HANDCUFFS;
/*    */       } else {
/*    */         
/* 46 */         res = ModResources.HANDCUFFS;
/*    */       } 
/*    */       
/* 49 */       if (func_215332_c() instanceof net.minecraft.client.renderer.entity.model.BipedModel) {
/* 50 */         matrixStack.func_227861_a_(0.0D, -0.05D, 0.05D);
/* 51 */         MODEL.func_225598_a_(matrixStack, buffer.getBuffer(ModRenderTypes.getAbilityHand(res)), packedLight, 0, 1.0F, 1.0F, 1.0F, 1.0F);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\layers\HandcuffsLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */