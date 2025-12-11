/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ 
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.DamageOverTimeEffect;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.ITransmisibleByTouchEffect;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ 
/*    */ public class DokuPoisonEffect
/*    */   extends DamageOverTimeEffect implements ITransmisibleByTouchEffect {
/*    */   public DokuPoisonEffect() {
/* 10 */     super(ModDamageSource.POISON, 10.0F, 20);
/* 11 */     setDamageFunction(a -> Float.valueOf(getBaseDamage() + a.intValue()));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isTransmisibleByTouch() {
/* 16 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\effects\DokuPoisonEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */