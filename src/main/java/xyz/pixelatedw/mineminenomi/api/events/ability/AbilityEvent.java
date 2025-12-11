/*    */ package xyz.pixelatedw.mineminenomi.api.events.ability;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraftforge.event.entity.living.LivingEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ 
/*    */ public class AbilityEvent extends LivingEvent {
/*    */   private IAbility ability;
/*    */   
/*    */   public AbilityEvent(LivingEntity entity, IAbility ability) {
/* 11 */     super(entity);
/* 12 */     this.ability = ability;
/*    */   }
/*    */   
/*    */   public IAbility getAbility() {
/* 16 */     return this.ability;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\events\ability\AbilityEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */