/*    */ package xyz.pixelatedw.mineminenomi.renderers.entities;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.util.math.vector.Quaternion;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.YagaraBullEntity;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.animals.YagaraBullModel;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class YagaraBullRenderer<T extends YagaraBullEntity, M extends YagaraBullModel<T>> extends HumanoidRenderer<T, M> {
/*    */   public YagaraBullRenderer(EntityRendererManager manager) {
/* 22 */     super(manager, (M)new YagaraBullModel());
/* 23 */     this.scale = new float[] { 1.25F, 1.25F, 1.25F };
/* 24 */     this.field_76989_e = 0.7F;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(T entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 30 */     super.func_225623_a_(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
/* 31 */     if (entity.func_70909_n()) {
/*    */       
/* 33 */       if (!entity.isSaddled()) {
/*    */         
/* 35 */         ((YagaraBullModel)func_217764_d()).saddle.field_78806_j = false;
/* 36 */         ((YagaraBullModel)func_217764_d()).belt1.field_78806_j = false;
/* 37 */         ((YagaraBullModel)func_217764_d()).belt2.field_78806_j = false;
/*    */       }
/*    */       else {
/*    */         
/* 41 */         ((YagaraBullModel)func_217764_d()).saddle.field_78806_j = true;
/* 42 */         ((YagaraBullModel)func_217764_d()).belt1.field_78806_j = true;
/* 43 */         ((YagaraBullModel)func_217764_d()).belt2.field_78806_j = true;
/*    */       } 
/*    */       
/* 46 */       matrixStack.func_227860_a_();
/*    */       
/* 48 */       matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229179_b_, 180.0F, true));
/* 49 */       matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229181_d_, 180.0F, true));
/*    */       
/* 51 */       float ageInTicks = ((YagaraBullEntity)entity).field_70173_aa + partialTicks;
/* 52 */       float headYawOffset = MathHelper.func_219805_h(partialTicks, ((YagaraBullEntity)entity).field_70760_ar, ((YagaraBullEntity)entity).field_70761_aq);
/*    */       
/* 54 */       WyHelper.rotateCorpse(matrixStack, (LivingEntity)entity, ageInTicks, headYawOffset, partialTicks);
/*    */       
/* 56 */       matrixStack.func_227862_a_(1.2F, 1.2F, 1.2F);
/* 57 */       matrixStack.func_227861_a_(0.0D, -1.5099999904632568D, 0.0D);
/*    */       
/* 59 */       ((YagaraBullModel)func_217764_d()).renderSaddle(matrixStack, buffer.getBuffer(RenderType.func_228634_a_(func_110775_a(entity))), packedLight, OverlayTexture.field_229196_a_, 1.0F, 1.0F, 1.0F, 1.0F);
/*    */       
/* 61 */       matrixStack.func_227865_b_();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static class Factory
/*    */     implements IRenderFactory
/*    */   {
/*    */     public EntityRenderer createRenderFor(EntityRendererManager manager) {
/* 70 */       return (EntityRenderer)new YagaraBullRenderer<>(manager);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\YagaraBullRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */