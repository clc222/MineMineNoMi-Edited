/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.ai.attributes.Attribute;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.entity.ai.attributes.Attributes;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraftforge.common.ForgeMod;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.ITextureOverlayEffect;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*    */ 
/*    */ public class FrostbiteEffect extends DamageOverTimeEffect implements ITextureOverlayEffect {
/* 17 */   private static final ModDamageSource FROSTBITE_DAMAGE = (ModDamageSource)(new ModDamageSource("frostbite")).setPiercing(1.0F).func_151518_m().func_82726_p();
/*    */   
/*    */   public FrostbiteEffect() {
/* 20 */     super((DamageSource)FROSTBITE_DAMAGE, 1.0F, 40);
/*    */     
/* 22 */     func_220304_a(Attributes.field_233821_d_, "11147784-f615-47da-a28c-20c721cf5e9f", -0.03999999910593033D, AttributeModifier.Operation.MULTIPLY_TOTAL)
/* 23 */       .func_220304_a((Attribute)ModAttributes.JUMP_HEIGHT.get(), "6bd1439e-f380-47a6-806b-6864093a1c32", -0.05000000074505806D, AttributeModifier.Operation.MULTIPLY_TOTAL)
/* 24 */       .func_220304_a((Attribute)ForgeMod.SWIM_SPEED.get(), "f84aa019-b1b1-496e-a03a-249eaafe039e", -0.05000000074505806D, AttributeModifier.Operation.MULTIPLY_TOTAL)
/* 25 */       .func_220304_a(Attributes.field_233825_h_, "d23c3332-a0bf-4dde-80e1-1a6b936caf41", -0.03999999910593033D, AttributeModifier.Operation.MULTIPLY_TOTAL);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldUpdateClient() {
/* 30 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_76394_a(LivingEntity entity, int amplifier) {
/* 36 */     super.func_76394_a(entity, amplifier);
/*    */     
/* 38 */     if (amplifier > 10) {
/*    */       
/* 40 */       EffectInstance forstbite = entity.func_70660_b((Effect)ModEffects.FROSTBITE.get());
/* 41 */       if (forstbite != null) {
/* 42 */         int duration = forstbite.func_76459_b();
/* 43 */         entity.func_195064_c(new EffectInstance((Effect)ModEffects.FROZEN.get(), duration, 0));
/*    */       } 
/* 45 */       entity.func_195063_d((Effect)ModEffects.FROSTBITE.get());
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isBlockingRotations() {
/* 52 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation getBodyTexture(int duration, int amplifier) {
/* 58 */     if (amplifier < 2)
/* 59 */       return ModResources.FROSTBITE_1; 
/* 60 */     if (amplifier >= 2 && amplifier < 4)
/* 61 */       return ModResources.FROSTBITE_2; 
/* 62 */     if (amplifier >= 4 && amplifier < 6)
/* 63 */       return ModResources.FROSTBITE_3; 
/* 64 */     if (amplifier >= 6 && amplifier < 8) {
/* 65 */       return ModResources.FROSTBITE_4;
/*    */     }
/* 67 */     return ModResources.FROSTBITE_5;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\effects\FrostbiteEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */