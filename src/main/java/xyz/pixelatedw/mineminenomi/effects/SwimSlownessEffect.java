/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ import net.minecraft.entity.ai.attributes.Attribute;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.EffectType;
/*    */ import net.minecraftforge.common.ForgeMod;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.ModEffect;
/*    */ 
/*    */ public class SwimSlownessEffect extends ModEffect {
/*    */   public SwimSlownessEffect() {
/* 11 */     super(EffectType.HARMFUL, 5926017);
/* 12 */     func_220304_a((Attribute)ForgeMod.SWIM_SPEED.get(), "d4e714aa-9792-4b12-91d6-4b46a248c9e0", -0.15D, AttributeModifier.Operation.MULTIPLY_TOTAL);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldRender(EffectInstance effect) {
/* 17 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldRenderHUD(EffectInstance effect) {
/* 22 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\effects\SwimSlownessEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */