/*    */ package xyz.pixelatedw.mineminenomi.renderers.entities;
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.BananawaniEntity;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.animals.BananawaniModel;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.layers.BananawaniSaddleLayer;
/*    */ 
/*    */ public class BananawaniRenderer<T extends BananawaniEntity, M extends BananawaniModel<T>> extends HumanoidRenderer<T, M> {
/*    */   public BananawaniRenderer(EntityRendererManager manager) {
/* 19 */     super(manager, (M)new BananawaniModel());
/* 20 */     this.scale = new float[] { 1.5F, 1.5F, 1.5F };
/* 21 */     this.field_76989_e = 1.5F;
/* 22 */     this.texture = new ResourceLocation("mineminenomi", "textures/models/bananawani.png");
/* 23 */     func_177094_a((LayerRenderer)new BananawaniSaddleLayer((IEntityRenderer)this));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(T entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 29 */     matrixStack.func_227860_a_();
/* 30 */     matrixStack.func_227861_a_(0.0D, 0.1D, 0.0D);
/* 31 */     super.func_225623_a_(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
/* 32 */     matrixStack.func_227865_b_();
/*    */   }
/*    */ 
/*    */   
/*    */   public static class Factory
/*    */     implements IRenderFactory
/*    */   {
/*    */     public EntityRenderer createRenderFor(EntityRendererManager manager) {
/* 40 */       return (EntityRenderer)new BananawaniRenderer<>(manager);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\BananawaniRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */