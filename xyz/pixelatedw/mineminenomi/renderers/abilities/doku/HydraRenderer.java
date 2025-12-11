/*    */ package xyz.pixelatedw.mineminenomi.renderers.abilities.doku;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import java.awt.Color;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.culling.ClippingHelper;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.doku.HydraProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.models.abilities.CubeModel;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.projectiles.HydraModel;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.abilities.StretchingProjectileRenderer;
/*    */ 
/*    */ public class HydraRenderer<M extends EntityModel<HydraProjectile>>
/*    */   extends StretchingProjectileRenderer<HydraProjectile, M> {
/* 22 */   private static final ResourceLocation TEXTURE = new ResourceLocation("mineminenomi", "textures/models/projectiles/hydra_full.png");
/* 23 */   private static final Color NORMAL_COLOR = new Color(255, 255, 255, 100);
/* 24 */   private static final Color DEMON_COLOR = new Color(255, 0, 0, 100);
/* 25 */   private static final Vector3f NORMAL_SCALE = new Vector3f(5.5F, 5.5F, 5.5F);
/* 26 */   private static final Vector3f DEMON_SCALE = new Vector3f(7.5F, 7.5F, 7.5F);
/*    */   
/*    */   public HydraRenderer(EntityRendererManager renderManager, M model) {
/* 29 */     super(renderManager, (EntityModel)model, (EntityModel)new CubeModel());
/*    */     
/* 31 */     setTexture(TEXTURE);
/* 32 */     setNormalDetails();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldRender(HydraProjectile pLivingEntity, ClippingHelper pCamera, double pCamX, double pCamY, double pCamZ) {
/* 37 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(HydraProjectile entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 42 */     super.render((AbilityProjectileEntity)entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
/*    */     
/* 44 */     if (entity.isDemonMode()) {
/* 45 */       setDemonDetails();
/*    */     } else {
/*    */       
/* 48 */       setNormalDetails();
/*    */     } 
/*    */   }
/*    */   
/*    */   private void setDemonDetails() {
/* 53 */     setColor(DEMON_COLOR);
/* 54 */     setScale(DEMON_SCALE);
/* 55 */     setStretchScale(DEMON_SCALE.func_195899_a(), DEMON_SCALE.func_195900_b(), 8.0D);
/*    */   }
/*    */   
/*    */   private void setNormalDetails() {
/* 59 */     setColor(NORMAL_COLOR);
/* 60 */     setScale(NORMAL_SCALE);
/* 61 */     setStretchScale(NORMAL_SCALE.func_195899_a(), NORMAL_SCALE.func_195900_b(), 8.0D);
/*    */   }
/*    */   
/*    */   public static class Factory
/*    */     implements IRenderFactory<HydraProjectile> {
/*    */     public HydraRenderer createRenderFor(EntityRendererManager manager) {
/* 67 */       return new HydraRenderer<>(manager, new HydraModel());
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\abilities\doku\HydraRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */