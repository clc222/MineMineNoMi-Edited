/*    */ package xyz.pixelatedw.mineminenomi.renderers.abilities.doku;
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import java.awt.Color;
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.doku.ChloroBallProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.models.abilities.SphereModel;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.abilities.AbilityProjectileRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class ChloroBallRenderer extends AbilityProjectileRenderer<ChloroBallProjectile, SphereModel<ChloroBallProjectile>> {
/* 20 */   private static final Color NORMAL_COLOR = WyHelper.hexToRGB("#A020F0");
/* 21 */   private static final Color DEMON_COLOR = WyHelper.hexToRGB("#5A0000");
/* 22 */   private static final Vector3f NORMAL_SCALE = new Vector3f(2.0F, 2.0F, 2.0F);
/* 23 */   private static final Vector3f DEMON_SCALE = new Vector3f(4.0F, 4.0F, 4.0F);
/*    */   
/*    */   public ChloroBallRenderer(EntityRendererManager renderManager, SphereModel model, Set<AbilityProjectileRenderer.Effect> effects) {
/* 26 */     super(renderManager, (EntityModel)model, effects);
/*    */     
/* 28 */     setNormalDetails();
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(ChloroBallProjectile entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 33 */     super.render((AbilityProjectileEntity)entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
/*    */     
/* 35 */     if (entity.isDemonMode()) {
/* 36 */       setDemonDetails();
/*    */     } else {
/*    */       
/* 39 */       setNormalDetails();
/*    */     } 
/*    */   }
/*    */   
/*    */   private void setDemonDetails() {
/* 44 */     setColor(DEMON_COLOR);
/* 45 */     setScale(DEMON_SCALE);
/*    */   }
/*    */   
/*    */   private void setNormalDetails() {
/* 49 */     setColor(NORMAL_COLOR);
/* 50 */     setScale(NORMAL_SCALE);
/*    */   }
/*    */   
/*    */   public static class Factory
/*    */     implements IRenderFactory<ChloroBallProjectile> {
/*    */     public ChloroBallRenderer createRenderFor(EntityRendererManager manager) {
/* 56 */       return new ChloroBallRenderer(manager, new SphereModel(), new HashSet<>());
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\abilities\doku\ChloroBallRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */