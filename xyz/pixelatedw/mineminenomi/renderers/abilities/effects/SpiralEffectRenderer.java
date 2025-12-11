/*    */ package xyz.pixelatedw.mineminenomi.renderers.abilities.effects;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.projectiles.effects.SpiralEffectModel;
/*    */ 
/*    */ public class SpiralEffectRenderer<T extends Entity, M extends EntityModel<T>>
/*    */   extends EntityRenderer<T>
/*    */ {
/* 19 */   private SpiralEffectModel model = new SpiralEffectModel();
/*    */ 
/*    */   
/*    */   public SpiralEffectRenderer(EntityRendererManager manager) {
/* 23 */     super(manager);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225623_a_(T entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 29 */     float ageInTicks = ((Entity)entity).field_70173_aa + partialTicks;
/* 30 */     matrixStack.func_227860_a_();
/*    */     
/* 32 */     float scale = 1.2F;
/* 33 */     matrixStack.func_227862_a_(scale, scale, scale);
/* 34 */     IVertexBuilder ivb = buffer.getBuffer(RenderType.func_228644_e_(func_110775_a(entity)));
/* 35 */     this.model.func_225597_a_((Entity)entity, 0.0F, 0.0F, ageInTicks, 0.0F, 0.0F);
/* 36 */     this.model.func_225598_a_(matrixStack, ivb, packedLight, OverlayTexture.field_229196_a_, 1.0F, 1.0F, 1.0F, 0.5F);
/*    */     
/* 38 */     matrixStack.func_227865_b_();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation func_110775_a(T entity) {
/* 44 */     return ModResources.PROJ_EFFECT_2;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\abilities\effects\SpiralEffectRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */