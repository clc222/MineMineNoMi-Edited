/*    */ package xyz.pixelatedw.mineminenomi.api.events.ability;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraftforge.event.entity.living.LivingEvent;
/*    */ import net.minecraftforge.eventbus.api.Event.HasResult;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ 
/*    */ @HasResult
/*    */ public class UnlockAbilityEvent extends LivingEvent {
/*    */   private AbilityCore<?> abilityCore;
/*    */   
/*    */   public UnlockAbilityEvent(LivingEntity entity, AbilityCore<?> abilityCore) {
/* 13 */     super(entity);
/* 14 */     this.abilityCore = abilityCore;
/*    */   }
/*    */   
/*    */   public AbilityCore<?> getAbilityCore() {
/* 18 */     return this.abilityCore;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\events\ability\UnlockAbilityEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */