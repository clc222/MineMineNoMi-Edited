/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.entity.ai.attributes.Attributes;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.EffectType;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.ModEffect;
/*    */ import xyz.pixelatedw.mineminenomi.api.enums.HandcuffType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.SToggleAnimationPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*    */ 
/*    */ public class HandcuffedEffect extends ModEffect {
/*    */   public HandcuffedEffect(HandcuffType type) {
/* 22 */     super(EffectType.HARMFUL, WyHelper.hexToRGB("#000000").getRGB());
/* 23 */     this.type = type;
/* 24 */     func_220304_a(Attributes.field_233821_d_, "b8154269-30c6-429f-9a5f-1fd7fdafc0cd", -0.30000001192092896D, AttributeModifier.Operation.MULTIPLY_TOTAL);
/* 25 */     func_220304_a(Attributes.field_233825_h_, "caced4c2-b03a-4f70-8fb3-4f702daa0a80", -0.10000000149011612D, AttributeModifier.Operation.MULTIPLY_TOTAL);
/* 26 */     func_220304_a((Attribute)ModAttributes.MINING_SPEED.get(), "bd141f77-4c9d-41ef-aceb-96601c21dccb", -0.10000000149011612D, AttributeModifier.Operation.MULTIPLY_TOTAL);
/*    */   }
/*    */   private HandcuffType type;
/*    */   
/*    */   public boolean shouldUpdateClient() {
/* 31 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_76397_a(int duration, int amplifier) {
/* 36 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_76394_a(LivingEntity entity, int amplifier) {
/* 41 */     EffectInstance instance = entity.func_70660_b((Effect)this);
/*    */     
/* 43 */     if (instance.func_76459_b() <= 1) {
/* 44 */       entity.func_195063_d((Effect)this);
/* 45 */       AbilityHelper.enableAbilities(entity, ability -> (ability != null && ability.getCore().getCategory() == AbilityCategory.DEVIL_FRUITS));
/*    */       
/*    */       return;
/*    */     } 
/* 49 */     if (this.type == HandcuffType.KAIROSEKI) {
/* 50 */       AbilityHelper.disableAbilities(entity, instance
/*    */           
/* 52 */           .func_76459_b(), ability -> 
/* 53 */           (ability != null && ability.getCore().getCategory() == AbilityCategory.DEVIL_FRUITS));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void startEffect(LivingEntity entity, EffectInstance instance) {
/* 59 */     WyNetwork.sendToAllTrackingAndSelf(SToggleAnimationPacket.playAnimation(entity, ModAnimations.HANDCUFFED, instance.func_76459_b(), true), (Entity)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public void stopEffect(LivingEntity entity) {
/* 64 */     WyNetwork.sendToAllTrackingAndSelf(SToggleAnimationPacket.stopAnimation(entity, ModAnimations.HANDCUFFED), (Entity)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isRemoveable() {
/* 69 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isBlockingSwings() {
/* 74 */     return true;
/*    */   }
/*    */   
/*    */   public HandcuffType getType() {
/* 78 */     return this.type;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\effects\HandcuffedEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */