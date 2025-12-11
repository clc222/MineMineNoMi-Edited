/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ 
/*    */ import net.minecraft.potion.EffectType;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.ITextureOverlayEffect;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.ITransmisibleByTouchEffect;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.ModEffect;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*    */ 
/*    */ public class OilCoveredEffect
/*    */   extends ModEffect
/*    */   implements ITextureOverlayEffect, ITransmisibleByTouchEffect {
/*    */   public OilCoveredEffect() {
/* 14 */     super(EffectType.NEUTRAL, 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldUpdateClient() {
/* 19 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float[] getOverlayColor() {
/* 25 */     return new float[] { 0.0F, 0.0F, 0.0F, 1.0F };
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation getBodyTexture(int duration, int amplifier) {
/* 31 */     return ModResources.FROSTBITE_3;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isTransmisibleByTouch() {
/* 36 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\effects\OilCoveredEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */