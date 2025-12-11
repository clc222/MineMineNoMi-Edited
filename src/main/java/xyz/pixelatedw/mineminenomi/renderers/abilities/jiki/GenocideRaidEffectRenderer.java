/*    */ package xyz.pixelatedw.mineminenomi.renderers.abilities.jiki;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.client.renderer.model.ItemCameraTransforms;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.jiki.GenocideRaidEffectEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ 
/*    */ public class GenocideRaidEffectRenderer<T extends GenocideRaidEffectEntity> extends EntityRenderer<T> {
/*    */   protected GenocideRaidEffectRenderer(EntityRendererManager manager) {
/* 20 */     super(manager);
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(T entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 25 */     float speed = 10.0F;
/* 26 */     float spread = 0.75F;
/*    */     
/* 28 */     matrixStack.func_227860_a_();
/*    */     
/* 30 */     matrixStack.func_227863_a_(Vector3f.field_229178_a_.func_229187_a_(180.0F));
/* 31 */     matrixStack.func_227861_a_(0.75D, 0.35D, 0.75D);
/* 32 */     matrixStack.func_227862_a_(1.0F, 1.0F, 1.0F);
/*    */     
/* 34 */     if (entity.getTarget() == null || !entity.getTarget().func_70644_a((Effect)ModEffects.GENOCIDE_RAID.get())) {
/* 35 */       matrixStack.func_227865_b_();
/*    */       
/*    */       return;
/*    */     } 
/*    */     
/* 40 */     int amount = 5;
/* 41 */     float rotAmount = 72.0F;
/* 42 */     for (int i = 0; i < 5; i++) {
/* 43 */       float rot1 = i * 72.0F; float j;
/* 44 */       for (j = 0.0F; j < 5.0F; j++) {
/* 45 */         float rot2 = j * 72.0F;
/* 46 */         matrixStack.func_227860_a_();
/*    */         
/* 48 */         matrixStack.func_227861_a_(-0.75D, -0.75D, -0.75D);
/* 49 */         matrixStack.func_227863_a_(Vector3f.field_229180_c_.func_229187_a_(rot1 + ((GenocideRaidEffectEntity)entity).field_70173_aa * 10.0F));
/* 50 */         matrixStack.func_227863_a_(Vector3f.field_229182_e_.func_229187_a_(rot2 + ((GenocideRaidEffectEntity)entity).field_70173_aa * 10.0F));
/* 51 */         matrixStack.func_227861_a_(0.75D, 0.75D, 0.75D);
/*    */         
/* 53 */         int seed = (int)((i + j) % entity.getItemsUsed().size());
/* 54 */         ItemStack stack = entity.getItemsUsed().get(seed);
/* 55 */         Minecraft.func_71410_x().func_175597_ag().func_228397_a_(entity.getTarget(), stack, ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, false, matrixStack, buffer, packedLight);
/*    */         
/* 57 */         matrixStack.func_227865_b_();
/*    */       } 
/*    */     } 
/* 60 */     matrixStack.func_227865_b_();
/*    */   }
/*    */ 
/*    */   
/*    */   public ResourceLocation getTextureLocation(T pEntity) {
/* 65 */     return null;
/*    */   }
/*    */   
/*    */   public static class Factory
/*    */     implements IRenderFactory {
/*    */     public EntityRenderer createRenderFor(EntityRendererManager manager) {
/* 71 */       return new GenocideRaidEffectRenderer<>(manager);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\abilities\jiki\GenocideRaidEffectRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */