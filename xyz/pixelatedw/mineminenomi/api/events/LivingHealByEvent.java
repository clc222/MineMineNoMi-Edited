/*    */ package xyz.pixelatedw.mineminenomi.api.events;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraftforge.event.entity.living.LivingHealEvent;
/*    */ import net.minecraftforge.eventbus.api.Cancelable;
/*    */ 
/*    */ @Cancelable
/*    */ public class LivingHealByEvent extends LivingHealEvent {
/*    */   private LivingEntity healer;
/*    */   
/*    */   public LivingHealByEvent(LivingEntity healer, LivingEntity entity, float amount) {
/* 12 */     super(entity, amount);
/* 13 */     this.healer = healer;
/*    */   }
/*    */   
/*    */   public LivingEntity getHealer() {
/* 17 */     return this.healer;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\events\LivingHealByEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */