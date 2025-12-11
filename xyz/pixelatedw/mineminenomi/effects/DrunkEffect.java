/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.entity.ai.attributes.Attributes;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.EffectType;
/*    */ import net.minecraft.potion.Effects;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.ModEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class DrunkEffect extends ModEffect {
/*    */   public DrunkEffect() {
/* 14 */     super(EffectType.HARMFUL, WyHelper.hexToRGB("#000000").getRGB());
/* 15 */     func_220304_a(Attributes.field_233821_d_, "51b6c31a-cc9a-42d9-aa4c-d4fd98dcef2a", -0.005D, AttributeModifier.Operation.ADDITION);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_76397_a(int duration, int amplifier) {
/* 20 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_76394_a(LivingEntity entity, int amplifier) {
/* 25 */     if (amplifier >= 2 && amplifier < 4) {
/* 26 */       entity.func_195064_c(new EffectInstance(Effects.field_76431_k, 100, 0));
/*    */     }
/* 28 */     else if (amplifier >= 4) {
/* 29 */       entity.func_195064_c(new EffectInstance(Effects.field_76431_k, 100, 2));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isRemoveable() {
/* 35 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\effects\DrunkEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */