/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ 
/*    */ import net.minecraft.potion.EffectType;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.ModEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class HanaEffect extends ModEffect {
/*    */   public HanaEffect() {
/*  9 */     super(EffectType.NEUTRAL, WyHelper.hexToRGB("#000000").getRGB());
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isRemoveable() {
/* 14 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\effects\HanaEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */