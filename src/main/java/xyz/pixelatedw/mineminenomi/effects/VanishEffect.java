/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.EffectType;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.IVanishEffect;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.ModEffect;
/*    */ 
/*    */ public class VanishEffect extends ModEffect implements IVanishEffect {
/*    */   public VanishEffect() {
/* 10 */     super(EffectType.BENEFICIAL, 8356754);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldUpdateClient() {
/* 15 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isVanished(LivingEntity entity, int duration, int amplifier) {
/* 20 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean disableParticles() {
/* 25 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\effects\VanishEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */