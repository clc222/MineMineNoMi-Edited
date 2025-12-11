/*    */ package xyz.pixelatedw.mineminenomi.renderers.entities;
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.client.renderer.entity.MobRenderer;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.SeaCowEntity;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.animals.SeaCowModel;
/*    */ 
/*    */ public class SeaCowRenderer<T extends SeaCowEntity, M extends SeaCowModel<T>> extends MobRenderer<T, M> {
/* 17 */   public static final ResourceLocation TEXTURE = new ResourceLocation("mineminenomi", "textures/models/sea_cow.png");
/*    */   
/*    */   public SeaCowRenderer(EntityRendererManager manager) {
/* 20 */     super(manager, (EntityModel)new SeaCowModel(), 0.8F);
/*    */   }
/*    */ 
/*    */   
/*    */   public void scale(T entity, MatrixStack matrixStack, float partialTickTime) {
/* 25 */     matrixStack.func_227862_a_(entity.getSize(), entity.getSize(), entity.getSize());
/* 26 */     this.field_76989_e = entity.getSize();
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(T entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 31 */     super.func_225623_a_((MobEntity)entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
/*    */   }
/*    */ 
/*    */   
/*    */   public ResourceLocation getTextureLocation(T entity) {
/* 36 */     return TEXTURE;
/*    */   }
/*    */   
/*    */   public static class Factory
/*    */     implements IRenderFactory {
/*    */     public EntityRenderer createRenderFor(EntityRendererManager manager) {
/* 42 */       return (EntityRenderer)new SeaCowRenderer<>(manager);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\SeaCowRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */