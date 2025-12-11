/*    */ package xyz.pixelatedw.mineminenomi.renderers.entities;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.client.renderer.model.ItemCameraTransforms;
/*    */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.entities.HookEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*    */ import xyz.pixelatedw.mineminenomi.items.weapons.ModSwordItem;
/*    */ 
/*    */ public class HookRenderer<T extends HookEntity>
/*    */   extends EntityRenderer<T> {
/*    */   protected HookRenderer(EntityRendererManager renderManager) {
/* 24 */     super(renderManager);
/* 25 */     this.field_76989_e = 0.05F;
/* 26 */     this.stack = ((ModSwordItem)ModWeapons.HOOK.get()).func_190903_i();
/*    */   }
/*    */   private final ItemStack stack;
/*    */   
/*    */   public void render(T entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 31 */     if (((HookEntity)entity).field_70173_aa < 2) {
/*    */       return;
/*    */     }
/*    */     
/* 35 */     matrixStack.func_227860_a_();
/*    */     
/* 37 */     matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(MathHelper.func_219799_g(partialTicks, ((HookEntity)entity).field_70126_B, ((HookEntity)entity).field_70177_z) + 180.0F));
/*    */     
/* 39 */     LivingEntity owner = (LivingEntity)entity.func_234616_v_();
/* 40 */     if (this.stack != null && !this.stack.func_190926_b()) {
/* 41 */       (Minecraft.func_71410_x().func_175597_ag()).field_178112_h.func_229109_a_(owner, this.stack, ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, false, matrixStack, buffer, ((HookEntity)entity).field_70170_p, packedLight, OverlayTexture.field_229196_a_);
/*    */     }
/*    */     
/* 44 */     matrixStack.func_227865_b_();
/*    */   }
/*    */ 
/*    */   
/*    */   public ResourceLocation getTextureLocation(T entity) {
/* 49 */     return null;
/*    */   }
/*    */   
/*    */   public static class Factory
/*    */     implements IRenderFactory {
/*    */     public EntityRenderer createRenderFor(EntityRendererManager manager) {
/* 55 */       return new HookRenderer<>(manager);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\HookRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */