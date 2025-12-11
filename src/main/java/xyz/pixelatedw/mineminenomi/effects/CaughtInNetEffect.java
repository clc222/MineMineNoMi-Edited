/*     */ package xyz.pixelatedw.mineminenomi.effects;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.potion.EffectType;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.common.ForgeMod;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.effects.ITextureOverlayEffect;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.NetType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SToggleAnimationPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ public class CaughtInNetEffect extends ModEffect implements ITextureOverlayEffect {
/*  23 */   public static final ResourceLocation NET_TEXTURE = new ResourceLocation("mineminenomi", "textures/models/armor/net.png");
/*  24 */   public static final ResourceLocation KAIROSEKI_NET_TEXTURE = new ResourceLocation("mineminenomi", "textures/models/armor/kairoseki_net.png");
/*     */   
/*     */   private NetType type;
/*     */   
/*     */   public CaughtInNetEffect(NetType type) {
/*  29 */     super(EffectType.HARMFUL, WyHelper.hexToRGB("#000000").getRGB());
/*  30 */     this.type = type;
/*  31 */     func_220304_a(Attributes.field_233821_d_, "0e091520-be78-40aa-9e74-22aa34f506cf", -1.0D, AttributeModifier.Operation.MULTIPLY_TOTAL);
/*  32 */     func_220304_a((Attribute)ForgeMod.SWIM_SPEED.get(), "71ada06a-e999-4408-9d43-6f205379b52a", -1.0D, AttributeModifier.Operation.MULTIPLY_TOTAL);
/*  33 */     func_220304_a((Attribute)ModAttributes.JUMP_HEIGHT.get(), "7cadbf47-441b-4cd8-b93f-4e0c1147c7c8", -1.0D, AttributeModifier.Operation.MULTIPLY_TOTAL);
/*  34 */     func_220304_a(Attributes.field_233825_h_, "21006ee0-bf00-4ef7-90b0-d6ba8c003a4f", -1.0D, AttributeModifier.Operation.MULTIPLY_TOTAL);
/*  35 */     func_220304_a((Attribute)ModAttributes.MINING_SPEED.get(), "7adfb66b-5442-4b9e-8a42-5b1e25c39226", -1.0D, AttributeModifier.Operation.MULTIPLY_TOTAL);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldUpdateClient() {
/*  40 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_76397_a(int duration, int amplifier) {
/*  45 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_76394_a(LivingEntity entity, int amplifier) {
/*  50 */     EffectInstance instance = entity.func_70660_b((Effect)this);
/*     */     
/*  52 */     if (instance.func_76459_b() <= 1) {
/*  53 */       entity.func_195063_d((Effect)this);
/*  54 */       AbilityHelper.enableAbilities(entity, ability -> (ability != null && ability.getCore().getCategory() == AbilityCategory.DEVIL_FRUITS));
/*     */       
/*     */       return;
/*     */     } 
/*  58 */     if (this.type == NetType.NORMAL) {
/*  59 */       if (entity.func_70027_ad()) {
/*  60 */         entity.func_195063_d((Effect)this);
/*     */       }
/*     */       
/*  63 */       if (AbilityHelper.isLogiaBlocking((Entity)entity, entity)) {
/*  64 */         entity.func_195063_d((Effect)this);
/*     */       }
/*     */     }
/*  67 */     else if (this.type == NetType.KAIROSEKI) {
/*  68 */       AbilityHelper.disableAbilities(entity, instance
/*     */           
/*  70 */           .func_76459_b(), ability -> 
/*  71 */           (ability != null && ability.getCore().getCategory() == AbilityCategory.DEVIL_FRUITS));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void startEffect(LivingEntity entity, EffectInstance instance) {
/*  77 */     WyNetwork.sendToAllTrackingAndSelf(SToggleAnimationPacket.playAnimation(entity, ModAnimations.CAUGHT_IN_NET, instance.func_76459_b(), true), (Entity)entity);
/*     */   }
/*     */ 
/*     */   
/*     */   public void stopEffect(LivingEntity entity) {
/*  82 */     WyNetwork.sendToAllTrackingAndSelf(SToggleAnimationPacket.stopAnimation(entity, ModAnimations.CAUGHT_IN_NET), (Entity)entity);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRemoveable() {
/*  87 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBlockingSwings() {
/*  92 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBlockingRotations() {
/*  97 */     return true;
/*     */   }
/*     */   
/*     */   public NetType getType() {
/* 101 */     return this.type;
/*     */   }
/*     */ 
/*     */   
/*     */   public ResourceLocation getBodyTexture(int duration, int amplifier) {
/* 106 */     switch (this.type) {
/*     */       case KAIROSEKI:
/* 108 */         return KAIROSEKI_NET_TEXTURE;
/*     */     } 
/*     */     
/* 111 */     return NET_TEXTURE;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\effects\CaughtInNetEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */