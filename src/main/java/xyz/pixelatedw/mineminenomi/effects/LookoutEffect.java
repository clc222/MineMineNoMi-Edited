/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ 
/*    */ import net.minecraft.potion.EffectType;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.IColorOverlayEffect;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.ModEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class LookoutEffect
/*    */   extends ModEffect
/*    */   implements IColorOverlayEffect {
/*    */   public LookoutEffect() {
/* 12 */     super(EffectType.NEUTRAL, WyHelper.hexToRGB("#000000").getRGB());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldUpdateClient() {
/* 18 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public float[] getBodyOverlayColor(int duration, int amplifier) {
/* 23 */     return new float[] { 0.97F, 0.8F, 1.0F, 0.6F };
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\effects\LookoutEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */