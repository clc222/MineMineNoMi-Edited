/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.ai.attributes.Attribute;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.entity.ai.attributes.Attributes;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.EffectType;
/*    */ import net.minecraftforge.common.ForgeMod;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.SToggleAnimationPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*    */ 
/*    */ public class CarryingEffect extends ModEffect {
/*    */   public CarryingEffect() {
/* 18 */     super(EffectType.BENEFICIAL, WyHelper.hexToRGB("#000000").getRGB());
/* 19 */     func_220304_a(Attributes.field_233821_d_, "37056f15-9d27-4687-9346-c0bd4e97e878", -0.15000000596046448D, AttributeModifier.Operation.MULTIPLY_TOTAL);
/* 20 */     func_220304_a((Attribute)ForgeMod.SWIM_SPEED.get(), "85c3342e-da01-4db1-a9ff-b6963e6b009a", -0.15000000596046448D, AttributeModifier.Operation.MULTIPLY_TOTAL);
/* 21 */     func_220304_a((Attribute)ModAttributes.JUMP_HEIGHT.get(), "5b64a909-6c13-4c64-8bde-9facf4c4e12c", -0.15000000596046448D, AttributeModifier.Operation.MULTIPLY_TOTAL);
/* 22 */     func_220304_a(Attributes.field_233825_h_, "c59f0bc1-0b13-4db5-80e2-9cf75a9a64ee", -0.15000000596046448D, AttributeModifier.Operation.MULTIPLY_TOTAL);
/* 23 */     func_220304_a((Attribute)ModAttributes.MINING_SPEED.get(), "0d0ce5e1-cb49-42de-a747-f67c8f473327", -0.15000000596046448D, AttributeModifier.Operation.MULTIPLY_TOTAL);
/*    */   }
/*    */ 
/*    */   
/*    */   public void startEffect(LivingEntity entity, EffectInstance instance) {
/* 28 */     WyNetwork.sendToAllTrackingAndSelf(SToggleAnimationPacket.playAnimation(entity, ModAnimations.RAISE_ARMS, instance.func_76459_b(), true), (Entity)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public void stopEffect(LivingEntity entity) {
/* 33 */     WyNetwork.sendToAllTrackingAndSelf(SToggleAnimationPacket.stopAnimation(entity, ModAnimations.RAISE_ARMS), (Entity)entity);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\effects\CarryingEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */