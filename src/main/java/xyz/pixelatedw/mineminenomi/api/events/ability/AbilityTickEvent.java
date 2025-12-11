/*    */ package xyz.pixelatedw.mineminenomi.api.events.ability;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ 
/*    */ public class AbilityTickEvent
/*    */   extends AbilityEvent
/*    */ {
/*    */   public AbilityTickEvent(LivingEntity entity, IAbility ability) {
/* 10 */     super(entity, ability);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\events\ability\AbilityTickEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */