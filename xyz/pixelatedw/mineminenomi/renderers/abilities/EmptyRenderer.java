/*    */ package xyz.pixelatedw.mineminenomi.renderers.abilities;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.WorldRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.math.AxisAlignedBB;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class EmptyRenderer<T extends Entity>
/*    */   extends EntityRenderer<T> {
/*    */   public EmptyRenderer(EntityRendererManager renderManager) {
/* 20 */     super(renderManager);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_225623_a_(T entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 25 */     if (Minecraft.func_71410_x().func_175598_ae().func_178634_b() && !entity.func_82150_aj() && !Minecraft.func_71410_x().func_189648_am()) {
/* 26 */       renderDebugBox(matrixStack, buffer.getBuffer(RenderType.func_228659_m_()), entity);
/*    */     }
/*    */     
/* 29 */     super.func_225623_a_((Entity)entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
/*    */   }
/*    */   
/*    */   private void renderDebugBox(MatrixStack matrixStack, IVertexBuilder vertexBuilder, T entity) {
/* 33 */     if (!(entity instanceof AbilityProjectileEntity)) {
/*    */       return;
/*    */     }
/*    */     
/* 37 */     AbilityProjectileEntity proj = (AbilityProjectileEntity)entity;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 45 */     AxisAlignedBB axisalignedbb = new AxisAlignedBB(-proj.getBlockCollisionBox().func_216364_b() / 2.0D, -proj.getBlockCollisionBox().func_216360_c() / 2.0D, -proj.getBlockCollisionBox().func_216362_d() / 2.0D, proj.getBlockCollisionBox().func_216364_b() / 2.0D, proj.getBlockCollisionBox().func_216360_c() / 2.0D, proj.getBlockCollisionBox().func_216362_d() / 2.0D);
/*    */     
/* 47 */     WorldRenderer.func_228430_a_(matrixStack, vertexBuilder, axisalignedbb, 1.0F, 0.0F, 0.0F, 1.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public ResourceLocation func_110775_a(T entity) {
/* 52 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public static class Factory
/*    */     implements IRenderFactory<Entity>
/*    */   {
/*    */     public EntityRenderer<? super Entity> createRenderFor(EntityRendererManager manager) {
/* 60 */       return new EmptyRenderer<>(manager);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\abilities\EmptyRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */