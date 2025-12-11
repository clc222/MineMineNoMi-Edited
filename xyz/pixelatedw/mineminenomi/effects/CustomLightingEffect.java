/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ 
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.EffectType;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CustomLightingEffect
/*    */   extends Effect
/*    */ {
/*    */   public CustomLightingEffect() {
/* 15 */     super(EffectType.HARMFUL, WyHelper.hexToRGB("#000000").getRGB());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldRender(EffectInstance effect) {
/* 21 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldRenderHUD(EffectInstance effect) {
/* 27 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\effects\CustomLightingEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */