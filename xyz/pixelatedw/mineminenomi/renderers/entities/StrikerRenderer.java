/*    */ package xyz.pixelatedw.mineminenomi.renderers.entities;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.entities.StrikerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.vehicles.StrikerModel;
/*    */ 
/*    */ public class StrikerRenderer extends EntityRenderer<StrikerEntity> {
/* 23 */   private static final ResourceLocation TEXTURE = new ResourceLocation("mineminenomi", "textures/models/striker.png");
/*    */   
/* 25 */   private StrikerModel model = new StrikerModel();
/*    */   
/*    */   protected StrikerRenderer(EntityRendererManager manager) {
/* 28 */     super(manager);
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(StrikerEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 33 */     matrixStack.func_227860_a_();
/* 34 */     matrixStack.func_227861_a_(0.0D, 2.0D, 0.0D);
/* 35 */     matrixStack.func_227862_a_(1.4F, 1.4F, 1.4F);
/* 36 */     matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(180.0F - entityYaw));
/*    */     
/* 38 */     float hurtTime = entity.getHurtTime() - partialTicks;
/* 39 */     if (hurtTime > 0.0F) {
/* 40 */       matrixStack.func_227863_a_(Vector3f.field_229183_f_.func_229187_a_(MathHelper.func_76126_a(hurtTime) * hurtTime));
/* 41 */       matrixStack.func_227863_a_(Vector3f.field_229178_a_.func_229187_a_(MathHelper.func_76126_a(hurtTime) * hurtTime));
/*    */     } 
/*    */     
/* 44 */     matrixStack.func_227862_a_(-1.0F, -1.0F, 1.0F);
/* 45 */     this.model.func_225597_a_((Entity)entity, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F);
/* 46 */     IVertexBuilder ivertexbuilder = buffer.getBuffer(this.model.func_228282_a_(getTextureLocation(entity)));
/* 47 */     this.model.func_225598_a_(matrixStack, ivertexbuilder, packedLight, OverlayTexture.field_229196_a_, 1.0F, 1.0F, 1.0F, 1.0F);
/*    */     
/* 49 */     matrixStack.func_227860_a_();
/* 50 */     matrixStack.func_227861_a_(-0.7D, -1.0D, 0.61D);
/* 51 */     matrixStack.func_227862_a_(1.5F, 1.5F, 1.5F);
/*    */     
/* 53 */     PlayerEntity lastRider = entity.getLastRider();
/* 54 */     if (lastRider != null) {
/* 55 */       IEntityStats props = EntityStatsCapability.get((LivingEntity)lastRider);
/* 56 */       RendererHelper.drawPlayerFactionIcon(props.getFaction(), entity.getCrew(), (LivingEntity)lastRider, matrixStack, buffer, packedLight);
/*    */     } 
/* 58 */     matrixStack.func_227865_b_();
/*    */     
/* 60 */     matrixStack.func_227865_b_();
/* 61 */     super.func_225623_a_((Entity)entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
/*    */   }
/*    */ 
/*    */   
/*    */   public ResourceLocation getTextureLocation(StrikerEntity entity) {
/* 66 */     return TEXTURE;
/*    */   }
/*    */   
/*    */   public static class Factory
/*    */     implements IRenderFactory {
/*    */     public EntityRenderer createRenderFor(EntityRendererManager manager) {
/* 72 */       return new StrikerRenderer(manager);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\StrikerRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */