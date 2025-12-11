/*    */ package xyz.pixelatedw.mineminenomi.renderers.entities;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.PirateBomberEntity;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids.HumanoidBigBomberModel;
/*    */ 
/*    */ @OnlyIn(Dist.CLIENT)
/*    */ public class HumanoidBigBomberRenderer<T extends PirateBomberEntity, M extends BipedModel<T>> extends HumanoidRenderer<T, M> {
/*    */   private BombRenderer bombRenderer;
/*    */   
/*    */   public HumanoidBigBomberRenderer(EntityRendererManager manager) {
/* 25 */     super(manager, (M)new HumanoidBigBomberModel(), new float[] { 1.05F, 1.1F, 1.05F }, null);
/* 26 */     this.bombRenderer = new BombRenderer<>(manager);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(T entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 32 */     super.func_225623_a_(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
/*    */     
/* 34 */     if (entity.hasBomb()) {
/*    */       
/* 36 */       matrixStack.func_227860_a_();
/* 37 */       matrixStack.func_227861_a_(0.0D, 2.15D, 0.0D);
/* 38 */       matrixStack.func_227862_a_(2.0F, 2.0F, 2.0F);
/* 39 */       matrixStack.func_227863_a_(Vector3f.field_229180_c_.func_229187_a_(MathHelper.func_219799_g(partialTicks, ((PirateBomberEntity)entity).field_70760_ar, ((PirateBomberEntity)entity).field_70761_aq)));
/* 40 */       this.bombRenderer.func_225623_a_(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
/* 41 */       matrixStack.func_227865_b_();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void setupRotations(T entityLiving, MatrixStack matrixStack, float ageInTicks, float rotationYaw, float partialTicks) {
/* 48 */     super.setupRotations(entityLiving, matrixStack, ageInTicks, rotationYaw, partialTicks);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation getTextureLocation(T entity) {
/* 54 */     return super.func_110775_a(entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public static class Factory
/*    */     implements IRenderFactory
/*    */   {
/*    */     public EntityRenderer createRenderFor(EntityRendererManager manager) {
/* 62 */       return (EntityRenderer)new HumanoidBigBomberRenderer<>(manager);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\HumanoidBigBomberRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */