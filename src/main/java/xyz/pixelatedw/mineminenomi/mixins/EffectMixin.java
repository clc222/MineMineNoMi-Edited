/*    */ package xyz.pixelatedw.mineminenomi.mixins;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifierManager;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraftforge.common.MinecraftForge;
/*    */ import net.minecraftforge.eventbus.api.Event;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import xyz.pixelatedw.mineminenomi.api.events.AttributeModifierApplyEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.events.AttributeModifierRemoveEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({Effect.class})
/*    */ public class EffectMixin
/*    */ {
/*    */   @Inject(method = {"addAttributeModifiers"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/entity/ai/attributes/ModifiableAttributeInstance;addPermanentModifier(Lnet/minecraft/entity/ai/attributes/AttributeModifier;)V", shift = At.Shift.AFTER)})
/*    */   public void applyAttributesModifiersToEntity(LivingEntity entity, AttributeModifierManager map, int amplifier, CallbackInfo info) {
/* 28 */     AttributeModifierApplyEvent event = new AttributeModifierApplyEvent(entity, map, amplifier);
/* 29 */     MinecraftForge.EVENT_BUS.post((Event)event);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Inject(method = {"removeAttributeModifiers"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/entity/ai/attributes/ModifiableAttributeInstance;removeModifier(Lnet/minecraft/entity/ai/attributes/AttributeModifier;)V", shift = At.Shift.AFTER)})
/*    */   public void removeAttributesModifiersFromEntity(LivingEntity entity, AttributeModifierManager map, int amplifier, CallbackInfo info) {
/* 42 */     AttributeModifierRemoveEvent event = new AttributeModifierRemoveEvent(entity, map, amplifier);
/* 43 */     MinecraftForge.EVENT_BUS.post((Event)event);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\mixins\EffectMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */