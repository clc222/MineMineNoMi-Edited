/*    */ package xyz.pixelatedw.mineminenomi.renderers.abilities;
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
/*    */ import net.minecraft.util.math.vector.Quaternion;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.EntityCloud;
/*    */ import xyz.pixelatedw.mineminenomi.models.abilities.AbareHimatsuriModel;
/*    */ 
/*    */ @OnlyIn(Dist.CLIENT)
/*    */ public class AbareHimatsuriRenderer
/*    */   extends EntityRenderer
/*    */ {
/* 25 */   private static final ResourceLocation TEXTURE = new ResourceLocation("minecraft:textures/block/dirt.png");
/*    */   
/*    */   private AbareHimatsuriModel model;
/*    */   
/*    */   public AbareHimatsuriRenderer(EntityRendererManager renderManager, EntityModel model) {
/* 30 */     super(renderManager);
/* 31 */     this.field_76989_e = 0.0F;
/* 32 */     this.model = (AbareHimatsuriModel)model;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225623_a_(Entity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 38 */     matrixStack.func_227860_a_();
/*    */     
/* 40 */     matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229179_b_, 180.0F, true));
/*    */     
/* 42 */     RenderType type = RenderType.func_228640_c_(func_110775_a(entity));
/* 43 */     IVertexBuilder ivertexbuilder = buffer.getBuffer(type);
/* 44 */     this.model.func_225598_a_(matrixStack, ivertexbuilder, packedLight, OverlayTexture.field_229196_a_, 1.0F, 1.0F, 1.0F, 1.0F);
/*    */     
/* 46 */     matrixStack.func_227865_b_();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation func_110775_a(Entity entity) {
/* 52 */     return TEXTURE;
/*    */   }
/*    */   
/*    */   public static class Factory
/*    */     implements IRenderFactory<EntityCloud>
/*    */   {
/*    */     private AbareHimatsuriModel model;
/*    */     
/*    */     public Factory(AbareHimatsuriModel model) {
/* 61 */       this.model = model;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public EntityRenderer<? super Entity> createRenderFor(EntityRendererManager manager) {
/* 67 */       return new AbareHimatsuriRenderer(manager, (EntityModel)this.model);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\abilities\AbareHimatsuriRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */