/*     */ package xyz.pixelatedw.mineminenomi.effects;
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.potion.EffectType;
/*     */ import net.minecraftforge.common.ForgeMod;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.effects.IColorOverlayEffect;
/*     */ import xyz.pixelatedw.mineminenomi.api.effects.IDisableAbilitiesEffect;
/*     */ import xyz.pixelatedw.mineminenomi.api.effects.ModEffect;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.animation.AnimationDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.animation.IAnimationData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SToggleAnimationPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ public class UnconsciousEffect extends ModEffect implements IColorOverlayEffect, IDisableAbilitiesEffect {
/*     */   public UnconsciousEffect() {
/*  27 */     super(EffectType.HARMFUL, WyHelper.hexToRGB("#000000").getRGB());
/*  28 */     func_220304_a(Attributes.field_233821_d_, "0e091520-be78-40aa-9e74-22aa34f506cf", -1.0D, AttributeModifier.Operation.MULTIPLY_TOTAL);
/*  29 */     func_220304_a((Attribute)ForgeMod.SWIM_SPEED.get(), "71ada06a-e999-4408-9d43-6f205379b52a", -1.0D, AttributeModifier.Operation.MULTIPLY_TOTAL);
/*  30 */     func_220304_a((Attribute)ModAttributes.JUMP_HEIGHT.get(), "7cadbf47-441b-4cd8-b93f-4e0c1147c7c8", -1.0D, AttributeModifier.Operation.MULTIPLY_TOTAL);
/*  31 */     func_220304_a(Attributes.field_233825_h_, "21006ee0-bf00-4ef7-90b0-d6ba8c003a4f", -1.0D, AttributeModifier.Operation.MULTIPLY_TOTAL);
/*  32 */     func_220304_a((Attribute)ModAttributes.MINING_SPEED.get(), "7adfb66b-5442-4b9e-8a42-5b1e25c39226", -1.0D, AttributeModifier.Operation.MULTIPLY_TOTAL);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_76397_a(int duration, int amplifier) {
/*  37 */     return (duration % 15 == 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_76394_a(LivingEntity entity, int amplifier) {
/*  42 */     IAnimationData animationData = AnimationDataCapability.get(entity);
/*  43 */     if (animationData != null) {
/*  44 */       boolean isAnimationPlaying = (animationData.isAnimationPlaying(ModAnimations.UNCONSCIOUS) || animationData.isAnimationPlaying(ModAnimations.UNCONSCIOUS_YAMCHA));
/*  45 */       if (!isAnimationPlaying) {
/*  46 */         EffectInstance instance = entity.func_70660_b((Effect)this);
/*  47 */         startAnimation(entity, instance);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void startEffect(LivingEntity entity, EffectInstance instance) {
/*  54 */     startAnimation(entity, instance);
/*     */   }
/*     */   
/*     */   private void startAnimation(LivingEntity entity, EffectInstance instance) {
/*  58 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/*  62 */     long seed = Math.round(Math.floor(entity.func_142015_aE()));
/*  63 */     int r = (new Random(entity.func_110124_au().getMostSignificantBits() + seed)).nextInt(5);
/*  64 */     boolean hasSpecialAnimation = (r == 0);
/*  65 */     if (entity instanceof net.minecraft.entity.player.PlayerEntity && hasSpecialAnimation) {
/*  66 */       WyNetwork.sendToAllTrackingAndSelf(SToggleAnimationPacket.playAnimation(entity, ModAnimations.UNCONSCIOUS_YAMCHA, instance.func_76459_b(), true), (Entity)entity);
/*     */     } else {
/*     */       
/*  69 */       WyNetwork.sendToAllTrackingAndSelf(SToggleAnimationPacket.playAnimation(entity, ModAnimations.UNCONSCIOUS, instance.func_76459_b(), true), (Entity)entity);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void stopEffect(LivingEntity entity) {
/*  75 */     WyNetwork.sendToAllTrackingAndSelf(SToggleAnimationPacket.stopAnimation(entity, ModAnimations.UNCONSCIOUS_YAMCHA), (Entity)entity);
/*  76 */     WyNetwork.sendToAllTrackingAndSelf(SToggleAnimationPacket.stopAnimation(entity, ModAnimations.UNCONSCIOUS), (Entity)entity);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldUpdateClient() {
/*  81 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public float[] getViewOverlayColor(int duration, int amplifier) {
/*  86 */     return new float[] { 0.0F, 0.0F, 0.0F, 1.0F };
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public float[] getBodyOverlayColor(int duration, int amplifier) {
/*  92 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBlockingRotations() {
/*  97 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRemoveable() {
/* 102 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBlockingSwings() {
/* 107 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public Predicate<IAbility> getDisabledAbilities() {
/* 112 */     return abl -> true;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\effects\UnconsciousEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */