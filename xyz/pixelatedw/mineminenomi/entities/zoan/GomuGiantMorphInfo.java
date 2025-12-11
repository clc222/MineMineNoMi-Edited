/*     */ package xyz.pixelatedw.mineminenomi.entities.zoan;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import java.util.Map;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*     */ import net.minecraft.client.settings.PointOfView;
/*     */ import net.minecraft.entity.EntitySize;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.Pose;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.mega.DekaDekaAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphModel;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.morphs.MegaRenderer;
/*     */ 
/*     */ public class GomuGiantMorphInfo
/*     */   extends MorphInfo
/*     */ {
/*  25 */   private static final EntitySize STANDING_SIZE = EntitySize.func_220314_b(4.0F, 8.5F);
/*  26 */   private static final EntitySize CROUCHING_SIZE = EntitySize.func_220314_b(4.0F, 7.6F);
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public IRenderFactory getRendererFactory(LivingEntity entity) {
/*  31 */     boolean isSlim = false;
/*  32 */     if (entity instanceof AbstractClientPlayerEntity) {
/*  33 */       isSlim = ((AbstractClientPlayerEntity)entity).func_175154_l().equals("slim");
/*     */     }
/*  35 */     return (IRenderFactory)new MegaRenderer.Factory(this, isSlim);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public MorphModel getModel() {
/*  48 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void preRenderCallback(LivingEntity entity, MatrixStack matrixStack, float partialTickTime) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public AkumaNoMiItem getDevilFruit() {
/*  58 */     return ModAbilities.GOMU_GOMU_NO_MI;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getForm() {
/*  63 */     return "gomu_giant";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDisplayName() {
/*  68 */     return DekaDekaAbility.INSTANCE.getUnlocalizedName();
/*     */   }
/*     */ 
/*     */   
/*     */   public double getEyeHeight() {
/*  73 */     return 8.45D;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getShadowSize() {
/*  78 */     return 1.25F;
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public double getCameraZoom(LivingEntity entity) {
/*  84 */     return 8.0D;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canMount() {
/*  89 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public double getCameraHeight(LivingEntity entity) {
/*  96 */     boolean isFirstPerson = ((Minecraft.func_71410_x()).field_71474_y.func_243230_g() == PointOfView.FIRST_PERSON);
/*  97 */     boolean shouldSit = (entity.func_184218_aH() && entity.func_184187_bx() != null && entity.func_184187_bx().shouldRiderSit());
/*  98 */     if (isFirstPerson && shouldSit) {
/*  99 */       return 0.5D;
/*     */     }
/* 101 */     return 0.0D;
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<Pose, EntitySize> getSizes() {
/* 106 */     return (Map<Pose, EntitySize>)ImmutableMap.builder()
/* 107 */       .put(Pose.STANDING, STANDING_SIZE)
/* 108 */       .put(Pose.CROUCHING, CROUCHING_SIZE)
/* 109 */       .build();
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\zoan\GomuGiantMorphInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */