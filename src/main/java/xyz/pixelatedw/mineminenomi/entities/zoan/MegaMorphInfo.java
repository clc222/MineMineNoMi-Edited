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
/*     */ 
/*     */ public class MegaMorphInfo
/*     */   extends MorphInfo
/*     */ {
/*  26 */   private static final EntitySize STANDING_SIZE = EntitySize.func_220314_b(1.7F, 2.4F);
/*  27 */   private static final EntitySize CROUCHING_SIZE = EntitySize.func_220314_b(1.7F, 2.39F);
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public IRenderFactory getRendererFactory(LivingEntity entity) {
/*  33 */     boolean isSlim = false;
/*  34 */     if (entity instanceof AbstractClientPlayerEntity) {
/*  35 */       isSlim = ((AbstractClientPlayerEntity)entity).func_175154_l().equals("slim");
/*     */     }
/*  37 */     return (IRenderFactory)new MegaRenderer.Factory(this, isSlim);
/*     */   }
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
/*     */   
/*     */   public void preRenderCallback(LivingEntity entity, MatrixStack matrixStack, float partialTickTime) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AkumaNoMiItem getDevilFruit() {
/*  60 */     return ModAbilities.DEKA_DEKA_NO_MI;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getForm() {
/*  66 */     return "mega";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDisplayName() {
/*  73 */     return DekaDekaAbility.INSTANCE.getUnlocalizedName();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getEyeHeight() {
/*  79 */     return 8.45D;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getShadowSize() {
/*  85 */     return 1.25F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public double getCameraZoom(LivingEntity entity) {
/*  92 */     return 8.0D;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canMount() {
/*  98 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public double getCameraHeight(LivingEntity entity) {
/* 106 */     boolean isFirstPerson = ((Minecraft.func_71410_x()).field_71474_y.func_243230_g() == PointOfView.FIRST_PERSON);
/* 107 */     boolean shouldSit = (entity.func_184218_aH() && entity.func_184187_bx() != null && entity.func_184187_bx().shouldRiderSit());
/* 108 */     if (isFirstPerson && shouldSit)
/*     */     {
/* 110 */       return 0.5D;
/*     */     }
/* 112 */     return 0.0D;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<Pose, EntitySize> getSizes() {
/* 118 */     return (Map<Pose, EntitySize>)ImmutableMap.builder()
/* 119 */       .put(Pose.STANDING, EntitySize.func_220314_b(4.0F, 8.5F))
/* 120 */       .put(Pose.CROUCHING, EntitySize.func_220314_b(4.0F, 7.6F))
/* 121 */       .build();
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\zoan\MegaMorphInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */