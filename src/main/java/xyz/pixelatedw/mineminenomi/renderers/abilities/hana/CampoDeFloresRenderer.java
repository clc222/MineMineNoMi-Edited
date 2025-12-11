/*    */ package xyz.pixelatedw.mineminenomi.renderers.abilities.hana;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import java.util.Random;
/*    */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.hana.CampoDeFloresEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*    */ import xyz.pixelatedw.mineminenomi.models.abilities.EntityArmModel;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CampoDeFloresRenderer<T extends CampoDeFloresEntity>
/*    */   extends EntityRenderer<T>
/*    */ {
/* 24 */   private EntityArmModel model = new EntityArmModel();
/*    */   
/*    */   protected CampoDeFloresRenderer(EntityRendererManager manager) {
/* 27 */     super(manager);
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(T entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 32 */     if (entity.getOwner() == null || !(entity.getOwner() instanceof net.minecraft.entity.player.PlayerEntity)) {
/*    */       return;
/*    */     }
/* 35 */     matrixStack.func_227860_a_();
/*    */     
/* 37 */     ResourceLocation skin = ((AbstractClientPlayerEntity)entity.getOwner()).func_110306_p();
/* 38 */     RenderType renderType = ModRenderTypes.getZoanRenderType(skin);
/*    */     
/* 40 */     matrixStack.func_227861_a_(0.0D, -0.15D, 0.0D);
/* 41 */     Random rand = new Random();
/* 42 */     rand.setSeed(1L);
/* 43 */     int maxSize = 10;
/* 44 */     int minSize = maxSize - 1;
/* 45 */     for (int i = -minSize; i < maxSize; i++) {
/* 46 */       for (int j = -minSize; j < maxSize; j++) {
/* 47 */         if ((i * i + j * j) <= Math.pow(maxSize, 2.0D)) {
/*    */ 
/*    */           
/* 50 */           float spread = 0.2F + rand.nextFloat() / 10.0F;
/* 51 */           float speed = 0.1F + rand.nextFloat() / 10.0F;
/* 52 */           float anim = (float)Math.sin(((CampoDeFloresEntity)entity).field_70173_aa * Math.PI * speed) * spread;
/* 53 */           matrixStack.func_227860_a_();
/* 54 */           matrixStack.func_227861_a_((i / 1.0F), anim, (j / 1.0F));
/* 55 */           matrixStack.func_227863_a_(Vector3f.field_229178_a_.func_229187_a_(-90.0F));
/* 56 */           this.model.func_225598_a_(matrixStack, buffer.getBuffer(renderType), packedLight, OverlayTexture.field_229196_a_, 1.0F, 1.0F, 1.0F, 1.0F);
/* 57 */           matrixStack.func_227865_b_();
/*    */         } 
/*    */       } 
/*    */     } 
/* 61 */     matrixStack.func_227865_b_();
/*    */   }
/*    */ 
/*    */   
/*    */   public ResourceLocation getTextureLocation(T pEntity) {
/* 66 */     return null;
/*    */   }
/*    */   
/*    */   public static class Factory
/*    */     implements IRenderFactory {
/*    */     public EntityRenderer createRenderFor(EntityRendererManager manager) {
/* 72 */       return new CampoDeFloresRenderer<>(manager);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\abilities\hana\CampoDeFloresRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */