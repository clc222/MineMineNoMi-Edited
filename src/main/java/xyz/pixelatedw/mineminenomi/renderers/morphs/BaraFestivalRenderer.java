/*    */ package xyz.pixelatedw.mineminenomi.renderers.morphs;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*    */ import net.minecraft.client.resources.DefaultPlayerSkin;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.bara.BaraFestivalEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.projectiles.BaraFestivalModel;
/*    */ 
/*    */ @OnlyIn(Dist.CLIENT)
/*    */ public class BaraFestivalRenderer<T extends BaraFestivalEntity> extends EntityRenderer<T> {
/* 24 */   private BaraFestivalModel model = new BaraFestivalModel();
/*    */ 
/*    */   
/*    */   protected BaraFestivalRenderer(EntityRendererManager renderManager) {
/* 28 */     super(renderManager);
/* 29 */     this.field_76989_e = 0.0F;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(T entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 35 */     matrixStack.func_227860_a_();
/*    */     
/* 37 */     RenderType type = ModRenderTypes.getZoanRenderType(getTextureLocation(entity));
/* 38 */     IVertexBuilder ivertexbuilder = buffer.getBuffer(type);
/* 39 */     this.model.setupAnim((BaraFestivalEntity)entity, 0.0F, 0.0F, ((BaraFestivalEntity)entity).field_70173_aa, 0.0F, 0.0F);
/* 40 */     this.model.func_225598_a_(matrixStack, ivertexbuilder, packedLight, OverlayTexture.field_229196_a_, 1.0F, 1.0F, 1.0F, 1.0F);
/*    */     
/* 42 */     matrixStack.func_227865_b_();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation getTextureLocation(T entity) {
/* 48 */     if (entity.getOwnerUUID() == null)
/* 49 */       return DefaultPlayerSkin.func_177335_a(); 
/* 50 */     PlayerEntity player = ((BaraFestivalEntity)entity).field_70170_p.func_217371_b(entity.getOwnerUUID());
/* 51 */     if (player != null) {
/* 52 */       return ((AbstractClientPlayerEntity)player).func_110306_p();
/*    */     }
/* 54 */     return DefaultPlayerSkin.func_177334_a(entity.getOwnerUUID());
/*    */   }
/*    */ 
/*    */   
/*    */   public static class Factory
/*    */     implements IRenderFactory
/*    */   {
/*    */     public EntityRenderer createRenderFor(EntityRendererManager manager) {
/* 62 */       return new BaraFestivalRenderer<>(manager);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\morphs\BaraFestivalRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */