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
/*     */ import xyz.pixelatedw.mineminenomi.abilities.mini.MiniMiniAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphModel;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.morphs.MiniRenderer;
/*     */ 
/*     */ 
/*     */ public class MiniMorphInfo
/*     */   extends MorphInfo
/*     */ {
/*  26 */   private static final EntitySize STANDING_SIZE = EntitySize.func_220314_b(0.2F, 0.4F);
/*  27 */   private static final EntitySize CROUCHING_SIZE = EntitySize.func_220314_b(0.2F, 0.39F);
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public IRenderFactory getRendererFactory(LivingEntity entity) {
/*  33 */     boolean isSlim = false;
/*  34 */     if (entity instanceof AbstractClientPlayerEntity) {
/*  35 */       isSlim = ((AbstractClientPlayerEntity)entity).func_175154_l().equals("slim");
/*     */     }
/*  37 */     return (IRenderFactory)new MiniRenderer.Factory(this, isSlim);
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
/*  60 */     return ModAbilities.MINI_MINI_NO_MI;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getForm() {
/*  66 */     return "mini";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDisplayName() {
/*  72 */     return MiniMiniAbility.INSTANCE.getUnlocalizedName();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getEyeHeight() {
/*  78 */     return 0.4D;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getShadowSize() {
/*  84 */     return 0.2F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public double getCameraHeight(LivingEntity entity) {
/*  92 */     boolean isFirstPerson = ((Minecraft.func_71410_x()).field_71474_y.func_243230_g() == PointOfView.FIRST_PERSON);
/*  93 */     boolean shouldSit = (entity.func_184218_aH() && entity.func_184187_bx() != null && entity.func_184187_bx().shouldRiderSit());
/*  94 */     if (isFirstPerson && shouldSit)
/*     */     {
/*  96 */       return 0.5D;
/*     */     }
/*  98 */     return 0.0D;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<Pose, EntitySize> getSizes() {
/* 104 */     return (Map<Pose, EntitySize>)ImmutableMap.builder()
/* 105 */       .put(Pose.STANDING, STANDING_SIZE)
/* 106 */       .put(Pose.CROUCHING, CROUCHING_SIZE)
/* 107 */       .build();
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\zoan\MiniMorphInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */