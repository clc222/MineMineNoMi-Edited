/*    */ package xyz.pixelatedw.mineminenomi.renderers.morphs;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
/*    */ import net.minecraft.client.renderer.entity.layers.CapeLayer;
/*    */ import net.minecraft.client.renderer.entity.layers.ElytraLayer;
/*    */ import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.entity.model.PlayerModel;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphModel;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.layers.AuraLayer;
/*    */ 
/*    */ @OnlyIn(Dist.CLIENT)
/*    */ public class BaraPartialMorphRenderer<T extends AbstractClientPlayerEntity, M extends MorphModel>
/*    */   extends ZoanMorphRenderer<T, M> {
/*    */   public BaraPartialMorphRenderer(EntityRendererManager renderManager, MorphInfo info, BaraMode mode) {
/* 31 */     super(renderManager, info);
/* 32 */     removeLayer((Class)HeldItemLayer.class);
/* 33 */     func_177094_a((LayerRenderer)new AuraLayer((IEntityRenderer)this));
/* 34 */     this.layer = new BipedArmorLayer((IEntityRenderer)this, new BipedModel(0.5F), new BipedModel(1.0F));
/* 35 */     func_177094_a((LayerRenderer)this.layer);
/* 36 */     func_177094_a((LayerRenderer)new CapeLayer((IEntityRenderer)this));
/* 37 */     func_177094_a((LayerRenderer)new ElytraLayer((IEntityRenderer)this));
/* 38 */     this.mode = mode;
/*    */   }
/*    */   private BaraMode mode;
/*    */   private BipedArmorLayer layer;
/*    */   
/*    */   public void func_225623_a_(AbstractClientPlayerEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 44 */     if (this.mode == BaraMode.FESTIVAL) {
/*    */       
/* 46 */       ((PlayerModel)func_217764_d()).field_178724_i.field_78806_j = false;
/* 47 */       ((PlayerModel)func_217764_d()).field_178734_a.field_78806_j = false;
/* 48 */       ((PlayerModel)func_217764_d()).field_178723_h.field_78806_j = false;
/* 49 */       ((PlayerModel)func_217764_d()).field_178732_b.field_78806_j = false;
/* 50 */       ((PlayerModel)func_217764_d()).field_178722_k.field_78806_j = false;
/* 51 */       ((PlayerModel)func_217764_d()).field_178733_c.field_78806_j = false;
/* 52 */       ((PlayerModel)func_217764_d()).field_178721_j.field_78806_j = false;
/* 53 */       ((PlayerModel)func_217764_d()).field_178731_d.field_78806_j = false;
/*    */     }
/* 55 */     else if (this.mode == BaraMode.CIRCUS) {
/*    */       
/* 57 */       ((PlayerModel)func_217764_d()).field_178724_i.field_78806_j = false;
/* 58 */       ((PlayerModel)func_217764_d()).field_178734_a.field_78806_j = false;
/* 59 */       ((PlayerModel)func_217764_d()).field_178723_h.field_78806_j = false;
/* 60 */       ((PlayerModel)func_217764_d()).field_178732_b.field_78806_j = false;
/*    */     }
/* 62 */     else if (this.mode == BaraMode.HO) {
/*    */       
/* 64 */       ((PlayerModel)func_217764_d()).field_178723_h.field_78806_j = false;
/* 65 */       ((PlayerModel)func_217764_d()).field_178732_b.field_78806_j = false;
/*    */     } 
/* 67 */     super.func_225623_a_(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation func_110775_a(AbstractClientPlayerEntity entity) {
/* 73 */     return entity.func_110306_p();
/*    */   }
/*    */   
/*    */   public static class Factory<T extends PlayerEntity>
/*    */     implements IRenderFactory<T>
/*    */   {
/*    */     private MorphInfo info;
/*    */     private BaraPartialMorphRenderer.BaraMode mode;
/*    */     
/*    */     public Factory(MorphInfo info, BaraPartialMorphRenderer.BaraMode mode) {
/* 83 */       this.info = info;
/* 84 */       this.mode = mode;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public EntityRenderer<? super T> createRenderFor(EntityRendererManager manager) {
/* 90 */       return (EntityRenderer)new BaraPartialMorphRenderer<>(manager, this.info, this.mode);
/*    */     }
/*    */   }
/*    */   
/*    */   public enum BaraMode
/*    */   {
/* 96 */     FESTIVAL,
/* 97 */     CIRCUS,
/* 98 */     HO;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\morphs\BaraPartialMorphRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */