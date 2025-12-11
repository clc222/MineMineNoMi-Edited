/*    */ package xyz.pixelatedw.mineminenomi.renderers;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.BlockRenderType;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.BlockRendererDispatcher;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.RenderTypeLookup;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.inventory.container.PlayerContainer;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.IBlockDisplayReader;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.client.ForgeHooksClient;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.ope.TaktBlockEntity;
/*    */ 
/*    */ public class TaktBlockRenderer extends EntityRenderer<TaktBlockEntity> {
/*    */   public TaktBlockRenderer(EntityRendererManager renderManager) {
/* 27 */     super(renderManager);
/* 28 */     this.field_76989_e = 0.5F;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(TaktBlockEntity entity, float pEntityYaw, float pPartialTicks, MatrixStack pMatrixStack, IRenderTypeBuffer pBuffer, int pPackedLight) {
/* 34 */     BlockState blockstate = entity.getBlockState();
/* 35 */     if (blockstate.func_185901_i() == BlockRenderType.MODEL) {
/*    */       
/* 37 */       World world = entity.getLevel();
/* 38 */       if (blockstate != world.func_180495_p(entity.func_233580_cy_()) && blockstate.func_185901_i() != BlockRenderType.INVISIBLE) {
/*    */         
/* 40 */         pMatrixStack.func_227860_a_();
/* 41 */         BlockPos blockpos = new BlockPos(entity.func_226277_ct_(), (entity.func_174813_aQ()).field_72337_e, entity.func_226281_cx_());
/* 42 */         pMatrixStack.func_227861_a_(-0.5D, 0.0D, -0.5D);
/* 43 */         BlockRendererDispatcher blockrendererdispatcher = Minecraft.func_71410_x().func_175602_ab();
/* 44 */         for (RenderType type : RenderType.func_228661_n_()) {
/*    */           
/* 46 */           if (RenderTypeLookup.canRenderInLayer(blockstate, type)) {
/*    */             
/* 48 */             ForgeHooksClient.setRenderLayer(type);
/* 49 */             blockrendererdispatcher.func_175019_b().func_228802_a_((IBlockDisplayReader)world, blockrendererdispatcher.func_184389_a(blockstate), blockstate, blockpos, pMatrixStack, pBuffer.getBuffer(type), false, new Random(), blockstate.func_209533_a(entity.getStartPos()), OverlayTexture.field_229196_a_);
/*    */           } 
/*    */         } 
/* 52 */         ForgeHooksClient.setRenderLayer(null);
/* 53 */         pMatrixStack.func_227865_b_();
/* 54 */         super.func_225623_a_((Entity)entity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation getTextureLocation(TaktBlockEntity entity) {
/* 62 */     return PlayerContainer.field_226615_c_;
/*    */   }
/*    */ 
/*    */   
/*    */   public static class Factory
/*    */     implements IRenderFactory<TaktBlockEntity>
/*    */   {
/*    */     public EntityRenderer<? super TaktBlockEntity> createRenderFor(EntityRendererManager manager) {
/* 70 */       return new TaktBlockRenderer(manager);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\TaktBlockRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */