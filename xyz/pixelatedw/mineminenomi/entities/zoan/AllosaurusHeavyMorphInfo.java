/*     */ package xyz.pixelatedw.mineminenomi.entities.zoan;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import java.util.Map;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*     */ import net.minecraft.entity.EntitySize;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.Pose;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.ryuallosaurus.AllosaurusHeavyPointAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphModel;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*     */ import xyz.pixelatedw.mineminenomi.models.morphs.AllosaurusHeavyPartialModel;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.morphs.AllosaurusHeavyPartialMorphRenderer;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AllosaurusHeavyMorphInfo
/*     */   extends MorphInfo
/*     */ {
/*  27 */   private static final EntitySize STANDING_SIZE = EntitySize.func_220314_b(0.9F, 2.8F);
/*  28 */   private static final EntitySize CROUCHING_SIZE = EntitySize.func_220314_b(0.9F, 2.6F);
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public IRenderFactory getRendererFactory(LivingEntity entity) {
/*  33 */     boolean isSlim = false;
/*  34 */     if (entity instanceof AbstractClientPlayerEntity) {
/*  35 */       isSlim = ((AbstractClientPlayerEntity)entity).func_175154_l().equals("slim");
/*     */     }
/*  37 */     return (IRenderFactory)new AllosaurusHeavyPartialMorphRenderer.Factory(this, isSlim);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public MorphModel getModel() {
/*  44 */     return (MorphModel)new AllosaurusHeavyPartialModel();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public AkumaNoMiItem getDevilFruit() {
/*  51 */     return ModAbilities.RYU_RYU_NO_MI_ALLOSAURUS;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPartial() {
/*  57 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public boolean shouldRenderFirstPersonHand() {
/*  64 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public boolean shouldRenderFirstPersonLeg() {
/*  71 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public void preRenderCallback(LivingEntity entity, MatrixStack matrixStack, float partialTickTime) {
/*  78 */     float scale = 1.4F;
/*  79 */     matrixStack.func_227862_a_(scale, scale, scale);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getForm() {
/*  85 */     return "allosaurus_heavy";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDisplayName() {
/*  91 */     return AllosaurusHeavyPointAbility.INSTANCE.getUnlocalizedName();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getEyeHeight() {
/*  97 */     return 2.5D;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getShadowSize() {
/* 103 */     return 0.8F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<Pose, EntitySize> getSizes() {
/* 109 */     return (Map<Pose, EntitySize>)ImmutableMap.builder()
/* 110 */       .put(Pose.STANDING, STANDING_SIZE)
/* 111 */       .put(Pose.CROUCHING, CROUCHING_SIZE)
/* 112 */       .build();
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\zoan\AllosaurusHeavyMorphInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */