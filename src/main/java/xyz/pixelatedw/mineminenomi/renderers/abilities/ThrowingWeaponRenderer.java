/*    */ package xyz.pixelatedw.mineminenomi.renderers.abilities;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.client.renderer.model.ItemCameraTransforms;
/*    */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.projectile.ThrowableEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.entities.ThrowingWeaponEntity;
/*    */ 
/*    */ public class ThrowingWeaponRenderer<T extends ThrowingWeaponEntity, M extends EntityModel<T>> extends EntityRenderer<T> {
/*    */   public ThrowingWeaponRenderer(EntityRendererManager manager) {
/* 23 */     super(manager);
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(T entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 28 */     if (((ThrowingWeaponEntity)entity).field_70173_aa < 2) {
/*    */       return;
/*    */     }
/*    */     
/* 32 */     matrixStack.func_227860_a_();
/*    */ 
/*    */     
/* 35 */     matrixStack.func_227861_a_(0.0D, 0.5D, 0.0D);
/* 36 */     matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(MathHelper.func_219799_g(partialTicks, ((ThrowingWeaponEntity)entity).field_70126_B, ((ThrowingWeaponEntity)entity).field_70177_z) + 180.0F));
/* 37 */     matrixStack.func_227863_a_(Vector3f.field_229178_a_.func_229187_a_(MathHelper.func_219799_g(partialTicks, ((ThrowingWeaponEntity)entity).field_70127_C, ((ThrowingWeaponEntity)entity).field_70125_A)));
/* 38 */     matrixStack.func_227863_a_(Vector3f.field_229178_a_.func_229187_a_(entity.getRotation()));
/*    */     
/* 40 */     ItemStack stack = entity.getItem();
/* 41 */     LivingEntity owner = (LivingEntity)entity.func_234616_v_();
/* 42 */     if (stack != null && !stack.func_190926_b()) {
/* 43 */       (Minecraft.func_71410_x().func_175597_ag()).field_178112_h.func_229109_a_(owner, stack, ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, false, matrixStack, buffer, ((ThrowingWeaponEntity)entity).field_70170_p, packedLight, OverlayTexture.field_229196_a_);
/*    */     }
/* 45 */     matrixStack.func_227865_b_();
/*    */   }
/*    */ 
/*    */   
/*    */   public ResourceLocation getTextureLocation(T pEntity) {
/* 50 */     return null;
/*    */   }
/*    */   
/*    */   public static class Factory
/*    */     implements IRenderFactory<ThrowableEntity> {
/*    */     public EntityRenderer<? super ThrowableEntity> createRenderFor(EntityRendererManager manager) {
/* 56 */       ThrowingWeaponRenderer<ThrowingWeaponEntity, EntityModel<ThrowingWeaponEntity>> renderer = new ThrowingWeaponRenderer<>(manager);
/* 57 */       return (EntityRenderer)renderer;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\abilities\ThrowingWeaponRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */