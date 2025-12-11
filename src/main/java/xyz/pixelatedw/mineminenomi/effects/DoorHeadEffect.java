/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ 
/*    */ import net.minecraft.potion.EffectType;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.ModEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class DoorHeadEffect extends ModEffect {
/*    */   public DoorHeadEffect() {
/*  9 */     super(EffectType.HARMFUL, WyHelper.hexToRGB("#000000").getRGB());
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldUpdateClient() {
/* 14 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isRemoveable() {
/* 19 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\effects\DoorHeadEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */