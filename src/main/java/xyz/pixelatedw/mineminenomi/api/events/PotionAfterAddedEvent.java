/*    */ package xyz.pixelatedw.mineminenomi.api.events;
/*    */ 
/*    */ import javax.annotation.Nonnull;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraftforge.event.entity.living.PotionEvent;
/*    */ 
/*    */ public class PotionAfterAddedEvent
/*    */   extends PotionEvent
/*    */ {
/*    */   public PotionAfterAddedEvent(LivingEntity living, EffectInstance newEffect) {
/* 12 */     super(living, newEffect);
/*    */   }
/*    */ 
/*    */   
/*    */   @Nonnull
/*    */   public EffectInstance getPotionEffect() {
/* 18 */     return super.getPotionEffect();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\events\PotionAfterAddedEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */