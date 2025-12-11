/*    */ package xyz.pixelatedw.mineminenomi.api.events;
/*    */ 
/*    */ import net.minecraftforge.eventbus.api.Event;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ 
/*    */ public class HitTriggerBlockedEvent extends Event {
/*    */   private IAbility ability;
/*    */   
/*    */   public HitTriggerBlockedEvent(IAbility ability) {
/* 10 */     this.ability = ability;
/*    */   }
/*    */   
/*    */   public IAbility getAbility() {
/* 14 */     return this.ability;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\events\HitTriggerBlockedEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */