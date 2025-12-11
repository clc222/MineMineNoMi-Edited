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
/*     */ import xyz.pixelatedw.mineminenomi.abilities.kage.ShadowsAsgardAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.karu.IngaZarashiAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphModel;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.morphs.ModifiedPlayerRenderer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ShadowsAsgardMorphInfo
/*     */   extends MorphInfo
/*     */ {
/*     */   private int cachedShadow;
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public IRenderFactory getRendererFactory(LivingEntity entity) {
/*  34 */     boolean isSlim = false;
/*  35 */     if (entity instanceof AbstractClientPlayerEntity) {
/*  36 */       isSlim = ((AbstractClientPlayerEntity)entity).func_175154_l().equals("slim");
/*     */     }
/*  38 */     return (IRenderFactory)new ModifiedPlayerRenderer.Factory(this, isSlim, (1.0F + this.cachedShadow / 60.0F));
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
/*  49 */     return null;
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
/*  61 */     return ModAbilities.KARU_KARU_NO_MI;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getForm() {
/*  67 */     return "karu";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDisplayName() {
/*  73 */     return IngaZarashiAbility.INSTANCE.getUnlocalizedName();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getEyeHeight() {
/*  79 */     return 1.85D + (this.cachedShadow / 30.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getShadowSize() {
/*  85 */     return 0.5F + this.cachedShadow / 50.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public double getCameraHeight(LivingEntity entity) {
/*  97 */     updateShadows(entity);
/*     */     
/*  99 */     boolean isFirstPerson = ((Minecraft.func_71410_x()).field_71474_y.func_243230_g() == PointOfView.FIRST_PERSON);
/* 100 */     boolean shouldSit = (entity.func_184218_aH() && entity.func_184187_bx() != null && entity.func_184187_bx().shouldRiderSit());
/* 101 */     if (isFirstPerson && shouldSit)
/*     */     {
/* 103 */       return 0.5D;
/*     */     }
/* 105 */     return 0.0D;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<Pose, EntitySize> getSizes() {
/* 111 */     return (Map<Pose, EntitySize>)ImmutableMap.builder()
/* 112 */       .put(Pose.STANDING, EntitySize.func_220314_b(0.6F + this.cachedShadow / 60.0F, 1.8F + this.cachedShadow / 30.0F))
/* 113 */       .put(Pose.CROUCHING, EntitySize.func_220314_b(0.6F + this.cachedShadow / 60.0F, 1.6F + this.cachedShadow / 30.0F))
/* 114 */       .build();
/*     */   }
/*     */   
/*     */   private void updateShadows(LivingEntity entity) {
/* 118 */     if (this.cachedShadow == 0 || entity.field_70173_aa % 20 == 0)
/* 119 */       this.cachedShadow = ((ShadowsAsgardAbility)AbilityDataCapability.get(entity).getEquippedAbility(ShadowsAsgardAbility.INSTANCE)).getShadows(); 
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\zoan\ShadowsAsgardMorphInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */