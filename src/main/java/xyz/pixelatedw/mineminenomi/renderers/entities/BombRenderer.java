/*    */ package xyz.pixelatedw.mineminenomi.renderers.entities;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.PirateBomberEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*    */ import xyz.pixelatedw.mineminenomi.models.abilities.SphereModel;
/*    */ 
/*    */ public class BombRenderer<T extends Entity>
/*    */   extends EntityRenderer<T> {
/* 23 */   private static final ResourceLocation TEXTURE = new ResourceLocation("mineminenomi", "textures/models/bomb.png");
/*    */   
/* 25 */   private SphereModel sphere = new SphereModel();
/*    */   
/*    */   protected BombRenderer(EntityRendererManager renderManager) {
/* 28 */     super(renderManager);
/* 29 */     this.field_76989_e = 0.6F;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_225623_a_(T entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 34 */     matrixStack.func_227860_a_();
/*    */     
/* 36 */     matrixStack.func_227861_a_(0.0D, 0.8D, 0.0D);
/* 37 */     matrixStack.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(180.0F));
/*    */     
/* 39 */     if (((Entity)entity).field_70173_aa < 4) {
/* 40 */       matrixStack.func_227861_a_(0.0D, 1.5D - (((Entity)entity).field_70173_aa / 3.0F), 0.0D);
/*    */     }
/*    */     else {
/*    */       
/* 44 */       matrixStack.func_227861_a_(0.0D, 0.4D, 0.0D);
/*    */     } 
/*    */     
/* 47 */     float scale = 3.0F;
/* 48 */     matrixStack.func_227862_a_(scale, scale, scale);
/*    */     
/* 50 */     int t1 = 1;
/*    */     
/* 52 */     if (entity instanceof PirateBomberEntity) {
/* 53 */       t1 = ((PirateBomberEntity)entity).getBombFuseTime();
/*    */     } else {
/*    */       
/* 56 */       matrixStack.func_227863_a_(Vector3f.field_229180_c_.func_229187_a_(MathHelper.func_219799_g(partialTicks, ((Entity)entity).field_70177_z, ((Entity)entity).field_70126_B)));
/*    */     } 
/*    */     
/* 59 */     int t3 = Math.min(t1, 6) - 1;
/*    */     
/* 61 */     RenderType type = RenderType.func_228644_e_(func_110775_a(entity));
/* 62 */     if (t1 > 0) {
/* 63 */       t1 = Math.max(3, t1);
/* 64 */       int t2 = Math.max(40, t1 * 10) + entity.hashCode() % 10;
/* 65 */       if (t2 > 0 && ((Entity)entity).field_70173_aa % t2 > 0 && ((Entity)entity).field_70173_aa % t2 < t1) {
/* 66 */         type = ModRenderTypes.TRANSPARENT_COLOR;
/*    */       }
/*    */     } 
/*    */     
/* 70 */     IVertexBuilder ivertexbuilder = buffer.getBuffer(type);
/* 71 */     this.sphere.func_225598_a_(matrixStack, ivertexbuilder, packedLight, OverlayTexture.field_229196_a_, 1.0F, 1.0F, 1.0F, 1.0F);
/*    */     
/* 73 */     if (((Entity)entity).field_70173_aa % 15 == 0) {
/* 74 */       for (int i = 0; i < t3; i++) {
/* 75 */         ((Entity)entity).field_70170_p.func_195594_a((IParticleData)ParticleTypes.field_197631_x, entity.func_226277_ct_(), entity.func_226278_cu_() + 3.700000047683716D + (i / 8.0F), entity.func_226281_cx_(), 0.0D, 0.0D, 0.0D);
/*    */       }
/*    */     }
/*    */     
/* 79 */     matrixStack.func_227865_b_();
/*    */   }
/*    */ 
/*    */   
/*    */   public ResourceLocation func_110775_a(T entity) {
/* 84 */     return TEXTURE;
/*    */   }
/*    */   
/*    */   public static class Factory
/*    */     implements IRenderFactory {
/*    */     public EntityRenderer createRenderFor(EntityRendererManager manager) {
/* 90 */       return new BombRenderer<>(manager);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\BombRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */