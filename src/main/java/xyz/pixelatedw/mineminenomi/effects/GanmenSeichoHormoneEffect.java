/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ 
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.entity.ai.attributes.Attributes;
/*    */ import net.minecraft.potion.EffectType;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.ModEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class GanmenSeichoHormoneEffect extends ModEffect {
/*    */   public GanmenSeichoHormoneEffect() {
/* 11 */     super(EffectType.NEUTRAL, WyHelper.hexToRGB("#000000").getRGB());
/*    */     
/* 13 */     func_220304_a(Attributes.field_233821_d_, "37360b52-ad8e-4c8a-a5a1-fd35a5e98d92", -0.4D, AttributeModifier.Operation.MULTIPLY_TOTAL);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldUpdateClient() {
/* 18 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isRemoveable() {
/* 23 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\effects\GanmenSeichoHormoneEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */