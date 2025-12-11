/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ 
/*    */ import net.minecraft.potion.EffectType;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.ModEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class BindEffect
/*    */   extends ModEffect
/*    */ {
/*    */   public BindEffect() {
/* 11 */     super(EffectType.HARMFUL, WyHelper.hexToRGB("#000000").getRGB());
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldUpdateClient() {
/* 16 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isBlockingSwings() {
/* 22 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isRemoveable() {
/* 28 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\effects\BindEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */