/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ 
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.IColorOverlayEffect;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.ILinkedEffect;
/*    */ 
/*    */ public class DoaVanishEffect extends VanishEffect implements ILinkedEffect, IColorOverlayEffect {
/*  7 */   private static final float[] OVERLAY_COLOR = new float[] { 0.2F, 0.68F, 0.53F, 0.5F };
/*    */ 
/*    */   
/*    */   public boolean shouldUpdateClient() {
/* 11 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean disableParticles() {
/* 16 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public float[] getBodyOverlayColor(int duration, int amplifier) {
/* 21 */     return OVERLAY_COLOR;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\effects\DoaVanishEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */