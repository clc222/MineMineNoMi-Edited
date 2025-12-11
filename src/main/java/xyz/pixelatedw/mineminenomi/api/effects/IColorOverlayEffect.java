/*    */ package xyz.pixelatedw.mineminenomi.api.effects;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ public interface IColorOverlayEffect {
/*    */   @Nullable
/*    */   float[] getBodyOverlayColor(int paramInt1, int paramInt2);
/*    */   
/*    */   @Nullable
/*    */   default float[] getViewOverlayColor(int duration, int amplifier) {
/* 11 */     return getBodyOverlayColor(duration, amplifier);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\effects\IColorOverlayEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */