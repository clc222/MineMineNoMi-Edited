/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ 
/*    */ import net.minecraft.potion.EffectType;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.IColorOverlayEffect;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.ModEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class BubblyCoralEffect
/*    */   extends ModEffect
/*    */   implements IColorOverlayEffect {
/*    */   public BubblyCoralEffect() {
/* 12 */     super(EffectType.NEUTRAL, WyHelper.hexToRGB("#000000").getRGB());
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldUpdateClient() {
/* 17 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float[] getBodyOverlayColor(int duration, int amplifier) {
/* 23 */     return new float[] { 0.0F, 0.41F, 0.58F, 0.11F };
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isBlockingRotations() {
/* 29 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isRemoveable() {
/* 35 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\effects\BubblyCoralEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */