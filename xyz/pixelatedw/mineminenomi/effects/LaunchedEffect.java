/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ 
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.EffectType;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.ModEffect;
/*    */ 
/*    */ public class LaunchedEffect extends ModEffect {
/*    */   public LaunchedEffect() {
/*  9 */     super(EffectType.NEUTRAL, 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldRender(EffectInstance effect) {
/* 14 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldRenderHUD(EffectInstance effect) {
/* 19 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\effects\LaunchedEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */