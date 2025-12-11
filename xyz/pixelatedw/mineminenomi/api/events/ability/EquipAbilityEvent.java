/*    */ package xyz.pixelatedw.mineminenomi.api.events.ability;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraftforge.event.entity.living.LivingEvent;
/*    */ import net.minecraftforge.eventbus.api.Cancelable;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ 
/*    */ @Cancelable
/*    */ public class EquipAbilityEvent extends LivingEvent {
/*    */   private IAbility ability;
/*    */   
/*    */   public EquipAbilityEvent(LivingEntity entity, IAbility ability) {
/* 13 */     super(entity);
/*    */     
/* 15 */     this.ability = ability;
/*    */   }
/*    */   
/*    */   public IAbility getAbility() {
/* 19 */     return this.ability;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\events\ability\EquipAbilityEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */