/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ import net.minecraft.entity.ai.attributes.Attribute;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.entity.ai.attributes.Attributes;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.common.ForgeMod;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.DamageOverTimeEffect;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.IBreakableEffect;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ 
/*    */ public class FrozenEffect extends DamageOverTimeEffect implements ITextureOverlayEffect, IBreakableEffect {
/* 14 */   private static final ResourceLocation TEXTURE = new ResourceLocation("minecraft", "textures/block/blue_ice.png");
/*    */   
/* 16 */   private static final ModDamageSource FROZEN_DAMAGE = (ModDamageSource)(new ModDamageSource("frost")).setUnavoidable().setPiercing(1.0F).func_82726_p().func_151518_m();
/*    */   
/*    */   public FrozenEffect() {
/* 19 */     super((DamageSource)FROZEN_DAMAGE, 10.0F, 40);
/*    */     
/* 21 */     func_220304_a(Attributes.field_233821_d_, "4c6e221d-2191-4960-a642-d38fcbea9c4f", -1000.0D, AttributeModifier.Operation.ADDITION)
/* 22 */       .func_220304_a((Attribute)ForgeMod.SWIM_SPEED.get(), "0aca6c19-061a-4b3f-9ba8-262f5e6f3815", -256.0D, AttributeModifier.Operation.ADDITION)
/* 23 */       .func_220304_a(Attributes.field_233825_h_, "777f14bf-fe3f-4f19-9d2c-eb69c04d5209", -4.0D, AttributeModifier.Operation.ADDITION)
/* 24 */       .func_220304_a(Attributes.field_233820_c_, "c24353cb-2efd-491f-a3f3-55a853d707ef", 100.0D, AttributeModifier.Operation.ADDITION)
/* 25 */       .func_220304_a((Attribute)ModAttributes.TOUGHNESS.get(), "77fb7784-2d75-4e0a-8f39-f5c2dcd4fca8", 2.0D, AttributeModifier.Operation.ADDITION)
/* 26 */       .func_220304_a((Attribute)ModAttributes.REGEN_RATE.get(), "cbbcac4a-a068-47e9-befa-b4324a0faeaf", -4.0D, AttributeModifier.Operation.ADDITION)
/* 27 */       .func_220304_a((Attribute)ModAttributes.JUMP_HEIGHT.get(), "fc7efdce-785b-4a61-bde2-10b95f99d7a4", -256.0D, AttributeModifier.Operation.ADDITION);
/*    */     
/* 29 */     setDamageFunction(amp -> Float.valueOf(getBaseDamage()));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldUpdateClient() {
/* 34 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isBlockingRotations() {
/* 39 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public ResourceLocation getBodyTexture(int duration, int amplifier) {
/* 44 */     return TEXTURE;
/*    */   }
/*    */ 
/*    */   
/*    */   public float[] getOverlayColor() {
/* 49 */     return new float[] { 1.0F, 1.0F, 1.0F, 0.8F };
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isRemoveable() {
/* 54 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isBlockingSwings() {
/* 59 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\effects\FrozenEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */