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
/*     */ import xyz.pixelatedw.mineminenomi.abilities.karu.IngaZarashiAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.karu.KarmaAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphModel;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.morphs.ModifiedPlayerRenderer;
/*     */ 
/*     */ 
/*     */ public class IngaZarashiMorphInfo
/*     */   extends MorphInfo
/*     */ {
/*  28 */   private float karma = 0.0F;
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public IRenderFactory getRendererFactory(LivingEntity entity) {
/*  34 */     KarmaAbility abl = (KarmaAbility)AbilityDataCapability.get(entity).getEquippedOrPassiveAbility(KarmaAbility.INSTANCE);
/*  35 */     if (abl != null) {
/*  36 */       this.karma = abl.getKarma();
/*     */     }
/*  38 */     boolean isSlim = false;
/*  39 */     if (entity instanceof AbstractClientPlayerEntity) {
/*  40 */       isSlim = ((AbstractClientPlayerEntity)entity).func_175154_l().equals("slim");
/*     */     }
/*  42 */     return (IRenderFactory)new ModifiedPlayerRenderer.Factory(this, isSlim, (1.0F + this.karma / 60.0F));
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
/*  53 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public boolean shouldRenderFirstPersonHand() {
/*  60 */     return true;
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
/*  72 */     return ModAbilities.KARU_KARU_NO_MI;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getForm() {
/*  78 */     return "karu";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDisplayName() {
/*  84 */     return IngaZarashiAbility.INSTANCE.getUnlocalizedName();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getEyeHeight() {
/*  90 */     return 1.85D + (this.karma / 30.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getShadowSize() {
/*  96 */     return 0.5F + this.karma / 50.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public double getCameraHeight(LivingEntity entity) {
/* 104 */     boolean isFirstPerson = ((Minecraft.func_71410_x()).field_71474_y.func_243230_g() == PointOfView.FIRST_PERSON);
/* 105 */     boolean shouldSit = (entity.func_184218_aH() && entity.func_184187_bx() != null && entity.func_184187_bx().shouldRiderSit());
/* 106 */     if (isFirstPerson && shouldSit)
/*     */     {
/* 108 */       return 0.5D;
/*     */     }
/* 110 */     return 0.0D;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<Pose, EntitySize> getSizes() {
/* 116 */     return (Map<Pose, EntitySize>)ImmutableMap.builder()
/* 117 */       .put(Pose.STANDING, EntitySize.func_220314_b(0.6F + this.karma / 60.0F, 1.8F + this.karma / 30.0F))
/* 118 */       .put(Pose.CROUCHING, EntitySize.func_220314_b(0.6F + this.karma / 60.0F, 1.6F + this.karma / 30.0F))
/* 119 */       .build();
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\zoan\IngaZarashiMorphInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */