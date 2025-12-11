/*     */ package xyz.pixelatedw.mineminenomi.api.morph;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import java.util.Map;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntitySize;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.Pose;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.event.entity.EntityEvent;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*     */ import net.minecraftforge.registries.ForgeRegistryEntry;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*     */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.morphs.ZoanMorphRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class MorphInfo
/*     */   extends ForgeRegistryEntry<MorphInfo>
/*     */ {
/*     */   @Nullable
/*     */   public AkumaNoMiItem getDevilFruit() {
/*  31 */     return null;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public abstract String getForm();
/*     */   
/*     */   public boolean isActive(LivingEntity entity) {
/*  38 */     IDevilFruit props = DevilFruitCapability.get(entity);
/*  39 */     boolean check = (props.getCurrentMorph().isPresent() && props.hasMorphInQueue(this));
/*  40 */     boolean legacyCheck = props.getZoanPoint().equalsIgnoreCase(getForm());
/*  41 */     return (check || legacyCheck);
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public abstract MorphModel getModel();
/*     */   
/*     */   @Nullable
/*     */   public ResourceLocation getTexture(LivingEntity entity) {
/*  50 */     return getTexture();
/*     */   }
/*     */   public abstract String getDisplayName();
/*     */   
/*     */   @Nullable
/*     */   public ResourceLocation getTexture() {
/*  56 */     if (getDevilFruit() != null) {
/*  57 */       return new ResourceLocation("mineminenomi", "textures/models/zoanmorph/" + WyHelper.getResourceName(getForm()) + ".png");
/*     */     }
/*  59 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public IRenderFactory getRendererFactory(LivingEntity entity) {
/*  65 */     return getRendererFactory();
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public IRenderFactory getRendererFactory() {
/*  71 */     return (IRenderFactory)new ZoanMorphRenderer.Factory(this, hasCulling());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPartial() {
/*  76 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public boolean shouldRenderFirstPersonHand() {
/*  82 */     return (getModel() != null);
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public boolean shouldRenderFirstPersonLeg() {
/*  88 */     return shouldRenderFirstPersonHand();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public void preRenderCallback(LivingEntity entity, MatrixStack matrixStack, float partialTickTime) {}
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Map<Pose, EntitySize> getSizes() {
/* 101 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getEyeHeight() {
/* 106 */     return 0.0D;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getShadowSize() {
/* 111 */     return -1.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canMount() {
/* 116 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public boolean hasCulling() {
/* 122 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public boolean hasEqualDepthTest() {
/* 128 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public double getCameraZoom(LivingEntity entity) {
/* 134 */     return 0.0D;
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public double getCameraHeight(LivingEntity entity) {
/* 140 */     return 0.0D;
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateMorphSize(LivingEntity entity) {
/* 145 */     MinecraftForge.EVENT_BUS.post((Event)new EntityEvent.Size((Entity)entity, entity.func_213283_Z(), entity.func_213305_a(entity.func_213283_Z()), entity.func_213302_cg()));
/* 146 */     entity.func_213323_x_();
/* 147 */     entity.func_184226_ay();
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\morph\MorphInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */