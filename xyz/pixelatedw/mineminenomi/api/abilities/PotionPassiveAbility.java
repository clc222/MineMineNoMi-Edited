/*    */ package xyz.pixelatedw.mineminenomi.api.abilities;
/*    */ 
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public abstract class PotionPassiveAbility
/*    */   extends PassiveAbility2
/*    */ {
/*    */   protected ICheckPotionEvent checkPotionEvent = (player, effect) -> true;
/*    */   
/*    */   public PotionPassiveAbility(AbilityCore<?> core) {
/* 14 */     super(core);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean check(PlayerEntity user, EffectInstance effect) {
/* 23 */     return this.checkPotionEvent.checkPotion(user, effect);
/*    */   }
/*    */   
/*    */   public static interface ICheckPotionEvent {
/*    */     boolean checkPotion(PlayerEntity param1PlayerEntity, EffectInstance param1EffectInstance);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\PotionPassiveAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */