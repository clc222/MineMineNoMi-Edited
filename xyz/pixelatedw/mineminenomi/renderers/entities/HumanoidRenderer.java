/*     */ package xyz.pixelatedw.mineminenomi.renderers.entities;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*     */ import net.minecraft.client.renderer.culling.ClippingHelper;
/*     */ import net.minecraft.client.renderer.entity.BipedRenderer;
/*     */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*     */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*     */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*     */ import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
/*     */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*     */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.IRandomTexture;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.layers.AuraLayer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.layers.BindLayer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.layers.BodyCoatingLayer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.layers.HandcuffsLayer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.layers.PotionLayer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.layers.abilities.HanaHandsLayer;
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class HumanoidRenderer<T extends MobEntity, M extends BipedModel<T>>
/*     */   extends BipedRenderer<T, M> {
/*     */   protected ResourceLocation texture;
/*     */   
/*     */   public HumanoidRenderer(EntityRendererManager manager, M model) {
/*  36 */     this(manager, model, (String)null);
/*     */   }
/*     */   protected float[] scale; public boolean bullshitFlag;
/*     */   public HumanoidRenderer(EntityRendererManager manager, M model, String tex) {
/*  40 */     this(manager, model, new float[] { 1.0F, 1.0F, 1.0F }, tex);
/*     */   }
/*     */   
/*     */   public HumanoidRenderer(EntityRendererManager manager, M model, float[] scale, @Nullable String tex) {
/*  44 */     super(manager, (BipedModel)model, (float)(0.5D * Math.pow(scale[0], 1.5D)));
/*  45 */     this.scale = scale;
/*  46 */     if (tex != null) {
/*  47 */       this.texture = new ResourceLocation("mineminenomi", "textures/models/" + tex + ".png");
/*     */     }
/*  49 */     func_177094_a((LayerRenderer)new BodyCoatingLayer((IEntityRenderer)this));
/*  50 */     func_177094_a((LayerRenderer)new BipedArmorLayer((IEntityRenderer)this, new BipedModel(0.5F), new BipedModel(1.0F)));
/*  51 */     func_177094_a((LayerRenderer)new PotionLayer((IEntityRenderer)this));
/*  52 */     func_177094_a((LayerRenderer)new AuraLayer((IEntityRenderer)this));
/*  53 */     func_177094_a((LayerRenderer)new HandcuffsLayer((IEntityRenderer)this));
/*  54 */     func_177094_a((LayerRenderer)new BindLayer((IEntityRenderer)this));
/*  55 */     func_177094_a((LayerRenderer)new HanaHandsLayer((IEntityRenderer)this));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void scale(T entity, MatrixStack matrixStack, float partialTickTime) {
/*  61 */     matrixStack.func_227862_a_(this.scale[0], this.scale[1], this.scale[2]);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225623_a_(T entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/*  67 */     if (!this.bullshitFlag) {
/*  68 */       ((BipedModel)this.field_77045_g).func_178719_a(true);
/*     */     }
/*  70 */     ((BipedModel)this.field_77045_g).field_178720_f.field_78806_j = false;
/*  71 */     boolean shouldSit = (entity.func_184218_aH() && entity.func_184187_bx() != null && entity.func_184187_bx().shouldRiderSit());
/*  72 */     ((BipedModel)this.field_77045_g).field_217113_d = shouldSit;
/*  73 */     ((BipedModel)this.field_77045_g).field_228270_o_ = entity.func_213453_ef();
/*  74 */     super.func_225623_a_((MobEntity)entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setupRotations(T entityLiving, MatrixStack matrixStack, float ageInTicks, float rotationYaw, float partialTicks) {
/*  80 */     super.func_225621_a_((LivingEntity)entityLiving, matrixStack, ageInTicks, rotationYaw, partialTicks);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ResourceLocation func_110775_a(T entity) {
/*  86 */     if ((this.texture == null && entity instanceof IRandomTexture) || this.texture.equals(ModResources.NULL_ENTITY_TEXTURE)) {
/*     */       
/*  88 */       ResourceLocation texture = ((IRandomTexture)entity).getCurrentTexture();
/*  89 */       if (texture == null) {
/*  90 */         texture = ((IRandomTexture)entity).getDefaultTexture();
/*     */       }
/*  92 */       return texture;
/*     */     } 
/*     */     
/*  95 */     return this.texture;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_225626_a_(T pLivingEntity, ClippingHelper pCamera, double pCamX, double pCamY, double pCamZ) {
/* 100 */     return super.func_225626_a_((MobEntity)pLivingEntity, pCamera, pCamX, pCamY, pCamZ);
/*     */   }
/*     */   
/*     */   public static class Factory
/*     */     implements IRenderFactory
/*     */   {
/*     */     protected BipedModel model;
/*     */     private float[] scale;
/*     */     private String texture;
/*     */     
/*     */     public Factory(BipedModel model, float scale) {
/* 111 */       this(model, scale, scale, scale, null);
/*     */     }
/*     */ 
/*     */     
/*     */     public Factory(BipedModel model, float scale, String texture) {
/* 116 */       this(model, scale, scale, scale, texture);
/*     */     }
/*     */ 
/*     */     
/*     */     public Factory(BipedModel model, float scaleX, float scaleY, float scaleZ) {
/* 121 */       this(model, scaleX, scaleY, scaleZ, null);
/*     */     }
/*     */ 
/*     */     
/*     */     public Factory(BipedModel model, float scaleX, float scaleY, float scaleZ, String texture) {
/* 126 */       this.model = model;
/* 127 */       this.texture = texture;
/*     */       
/* 129 */       this.scale = new float[] { scaleX, scaleY, scaleZ };
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public EntityRenderer createRenderFor(EntityRendererManager manager) {
/* 135 */       return (EntityRenderer)new HumanoidRenderer<>(manager, this.model, this.scale, this.texture);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\HumanoidRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */