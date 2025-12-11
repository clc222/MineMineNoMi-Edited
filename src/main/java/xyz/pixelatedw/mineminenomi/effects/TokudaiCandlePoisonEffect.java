/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ 
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.DamageOverTimeEffect;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ 
/*    */ public class TokudaiCandlePoisonEffect
/*    */   extends DamageOverTimeEffect {
/*    */   public TokudaiCandlePoisonEffect() {
/*  9 */     super(ModDamageSource.POISON, 10.0F, 30);
/* 10 */     setDamageFunction(amp -> {
/*    */           if (amp.intValue() <= 0)
/*    */             amp = Integer.valueOf(1); 
/*    */           return Float.valueOf(getBaseDamage() * amp.intValue());
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\effects\TokudaiCandlePoisonEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */